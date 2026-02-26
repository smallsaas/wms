//! 仓库服务
//!
//! 实现仓库、储位、库存相关的业务逻辑

use sea_orm::{
    ActiveModelTrait, ColumnTrait, DatabaseConnection, EntityTrait, PaginatorTrait,
    QueryFilter, QueryOrder, Set,
};

use crate::{
    domain::tenant::TenantContext,
    error::{AppError, Result},
    models::dto::{
        AdjustInventoryRequest, CreateSlotRequest, CreateWarehouseRequest,
        InventoryResponse, InventoryWarning, PageResponse, QueryInventoryRequest,
        QueryWarehouseRequest, SlotResponse, TransferInventoryRequest,
        UpdateSlotRequest, UpdateWarehouseRequest, WarehouseResponse,
    },
    models::entity::{
        inventory_entity::{self, Entity as Inventory},
        slot_entity::{self, Entity as Slot},
        warehouse_entity::{self, Entity as Warehouse},
    },
    domain::tenant::TenantScoped,
};

/// 仓库服务
#[derive(Debug, Clone)]
pub struct WarehouseService {
    db: DatabaseConnection,
}

impl WarehouseService {
    /// 创建新的仓库服务实例
    pub fn new(db: DatabaseConnection) -> Self {
        Self { db }
    }

    // ==================== 仓库管理 ====================

    /// 查询仓库列表
    pub async fn list_warehouses(
        &self,
        tenant: &TenantContext,
        query: QueryWarehouseRequest,
    ) -> Result<PageResponse<WarehouseResponse>> {
        let page = query.page();
        let page_size = query.page_size();

        let mut select = Warehouse::find().with_tenant(tenant.org_id, &tenant.app_id);

        if let Some(keyword) = &query.keyword {
            select = select.filter(
                warehouse_entity::Column::WarehouseName
                    .contains(keyword)
                    .or(warehouse_entity::Column::WarehouseCode.contains(keyword)),
            );
        }

        if let Some(status) = &query.status {
            select = select.filter(warehouse_entity::Column::Status.eq(status));
        }

        select = select.order_by_desc(warehouse_entity::Column::CreatedAt);

        let paginator = select.paginate(&self.db, page_size);
        let total = paginator.num_items().await?;
        let warehouses = paginator.fetch_page(page - 1).await?;

        let data: Vec<WarehouseResponse> = warehouses.into_iter().map(WarehouseResponse::from).collect();

        Ok(PageResponse::new(data, total, page, page_size))
    }

    /// 获取单个仓库
    pub async fn get_warehouse(
        &self,
        tenant: &TenantContext,
        id: i64,
    ) -> Result<WarehouseResponse> {
        let warehouse = Warehouse::find_by_id(id)
            .with_tenant(tenant.org_id, &tenant.app_id)
            .one(&self.db)
            .await?
            .ok_or(AppError::NotFound)?;

        Ok(WarehouseResponse::from(warehouse))
    }

    /// 创建仓库
    pub async fn create_warehouse(
        &self,
        tenant: &TenantContext,
        req: CreateWarehouseRequest,
    ) -> Result<WarehouseResponse> {
        // 检查仓库编码是否已存在
        let existing = Warehouse::find()
            .with_tenant(tenant.org_id, &tenant.app_id)
            .filter(warehouse_entity::Column::WarehouseCode.eq(&req.warehouse_code))
            .one(&self.db)
            .await?;

        if existing.is_some() {
            return Err(AppError::BadRequest(format!(
                "仓库编码 '{}' 已存在",
                req.warehouse_code
            )));
        }

        let now = chrono::Local::now().naive_local();

        let warehouse = warehouse_entity::ActiveModel {
            org_id: Set(tenant.org_id),
            org_tag: Set(None),
            warehouse_code: Set(req.warehouse_code),
            warehouse_name: Set(req.warehouse_name),
            warehouse_pcd: Set(req.warehouse_pcd),
            warehouse_address: Set(req.warehouse_address),
            warehouse_charger: Set(req.warehouse_charger),
            status: Set("ACTIVE".to_string()),
            created_at: Set(now),
            updated_at: Set(now),
            ..Default::default()
        };

        let result = warehouse.insert(&self.db).await?;
        self.get_warehouse(tenant, result.id).await
    }

    /// 更新仓库
    pub async fn update_warehouse(
        &self,
        tenant: &TenantContext,
        id: i64,
        req: UpdateWarehouseRequest,
    ) -> Result<WarehouseResponse> {
        let warehouse = Warehouse::find_by_id(id)
            .with_tenant(tenant.org_id, &tenant.app_id)
            .one(&self.db)
            .await?
            .ok_or(AppError::NotFound)?;

        let mut warehouse: warehouse_entity::ActiveModel = warehouse.into();
        let now = chrono::Local::now().naive_local();

        if let Some(name) = req.warehouse_name {
            warehouse.warehouse_name = Set(name);
        }
        if let Some(pcd) = req.warehouse_pcd {
            warehouse.warehouse_pcd = Set(Some(pcd));
        }
        if let Some(address) = req.warehouse_address {
            warehouse.warehouse_address = Set(Some(address));
        }
        if let Some(charger) = req.warehouse_charger {
            warehouse.warehouse_charger = Set(Some(charger));
        }
        if let Some(status) = req.status {
            warehouse.status = Set(status);
        }

        warehouse.updated_at = Set(now);
        warehouse.update(&self.db).await?;

        self.get_warehouse(tenant, id).await
    }

    /// 删除仓库
    pub async fn delete_warehouse(&self, tenant: &TenantContext, id: i64) -> Result<()> {
        let warehouse = Warehouse::find_by_id(id)
            .with_tenant(tenant.org_id, &tenant.app_id)
            .one(&self.db)
            .await?
            .ok_or(AppError::NotFound)?;

        Warehouse::delete(warehouse_entity::ActiveModel::from(warehouse))
            .exec(&self.db)
            .await?;

        Ok(())
    }

    // ==================== 储位管理 ====================

    /// 查询储位列表
    pub async fn list_slots(
        &self,
        tenant: &TenantContext,
        warehouse_id: i64,
    ) -> Result<Vec<SlotResponse>> {
        let slots = Slot::find()
            .with_tenant(tenant.org_id, &tenant.app_id)
            .filter(slot_entity::Column::WarehouseId.eq(warehouse_id))
            .order_by_asc(slot_entity::Column::SlotCode)
            .all(&self.db)
            .await?;

        Ok(slots.into_iter().map(SlotResponse::from).collect())
    }

    /// 创建储位
    pub async fn create_slot(
        &self,
        tenant: &TenantContext,
        req: CreateSlotRequest,
    ) -> Result<SlotResponse> {
        // 验证仓库是否存在
        let _ = self.get_warehouse(tenant, req.warehouse_id).await?;

        // 检查储位编码是否已存在
        let existing = Slot::find()
            .with_tenant(tenant.org_id, &tenant.app_id)
            .filter(slot_entity::Column::SlotCode.eq(&req.slot_code))
            .filter(slot_entity::Column::WarehouseId.eq(req.warehouse_id))
            .one(&self.db)
            .await?;

        if existing.is_some() {
            return Err(AppError::BadRequest(format!(
                "储位编码 '{}' 在该仓库中已存在",
                req.slot_code
            )));
        }

        let now = chrono::Local::now().naive_local();

        let slot = slot_entity::ActiveModel {
            org_id: Set(tenant.org_id),
            org_tag: Set(None),
            warehouse_id: Set(req.warehouse_id),
            slot_code: Set(req.slot_code),
            slot_name: Set(req.slot_name),
            slot_note: Set(req.slot_note),
            status: Set("ACTIVE".to_string()),
            created_at: Set(now),
            updated_at: Set(now),
            ..Default::default()
        };

        let result = slot.insert(&self.db).await?;

        let slot = Slot::find_by_id(result.id)
            .one(&self.db)
            .await?
            .ok_or(AppError::NotFound)?;

        Ok(SlotResponse::from(slot))
    }

    // ==================== 库存管理 ====================

    /// 查询库存列表
    pub async fn list_inventory(
        &self,
        tenant: &TenantContext,
        query: QueryInventoryRequest,
    ) -> Result<PageResponse<InventoryResponse>> {
        let page = query.page();
        let page_size = query.page_size();

        let mut select = Inventory::find().with_tenant(tenant.org_id, &tenant.app_id);

        if let Some(warehouse_id) = query.warehouse_id {
            select = select.filter(inventory_entity::Column::WarehouseId.eq(warehouse_id));
        }
        if let Some(slot_id) = query.slot_id {
            select = select.filter(inventory_entity::Column::SlotId.eq(slot_id));
        }
        if let Some(sku_id) = query.sku_id {
            select = select.filter(inventory_entity::Column::SkuId.eq(sku_id));
        }

        select = select.order_by_desc(inventory_entity::Column::UpdatedAt);

        let paginator = select.paginate(&self.db, page_size);
        let total = paginator.num_items().await?;
        let inventories = paginator.fetch_page(page - 1).await?;

        let data: Vec<InventoryResponse> = inventories
            .into_iter()
            .map(InventoryResponse::from)
            .collect();

        Ok(PageResponse::new(data, total, page, page_size))
    }

    /// 获取单个库存记录
    pub async fn get_inventory(
        &self,
        tenant: &TenantContext,
        id: i64,
    ) -> Result<InventoryResponse> {
        let inventory = Inventory::find_by_id(id)
            .with_tenant(tenant.org_id, &tenant.app_id)
            .one(&self.db)
            .await?
            .ok_or(AppError::NotFound)?;

        Ok(InventoryResponse::from(inventory))
    }

    /// 调整库存
    pub async fn adjust_inventory(
        &self,
        tenant: &TenantContext,
        req: AdjustInventoryRequest,
    ) -> Result<InventoryResponse> {
        let now = chrono::Local::now().naive_local();

        // 查找现有库存记录
        let inventory = Inventory::find()
            .with_tenant(tenant.org_id, &tenant.app_id)
            .filter(inventory_entity::Column::WarehouseId.eq(req.warehouse_id))
            .filter(inventory_entity::Column::SlotId.eq(req.slot_id))
            .filter(inventory_entity::Column::SkuId.eq(req.sku_id))
            .one(&self.db)
            .await?;

        if let Some(inv) = inventory {
            // 更新现有库存
            let mut inv: inventory_entity::ActiveModel = inv.into();
            let current_valid = inv.valid_sku.clone().unwrap().unwrap_or(0);
            let new_valid = current_valid + req.adjust_quantity;

            if new_valid < 0 {
                return Err(AppError::BadRequest("库存不足，无法调整".to_string()));
            }

            inv.valid_sku = Set(Some(new_valid));
            inv.updated_at = Set(now);
            inv.update(&self.db).await?;
        } else {
            // 创建新库存记录
            if req.adjust_quantity < 0 {
                return Err(AppError::BadRequest("新库存记录不能为负数".to_string()));
            }

            let inventory = inventory_entity::ActiveModel {
                org_id: Set(tenant.org_id),
                org_tag: Set(None),
                warehouse_id: Set(req.warehouse_id),
                slot_id: Set(req.slot_id),
                sku_id: Set(req.sku_id),
                valid_sku: Set(Some(req.adjust_quantity)),
                max_inventory: Set(None),
                min_inventory: Set(None),
                advance_quantities: Set(Some(0)),
                transmit_quantities: Set(Some(0)),
                order_count: Set(Some(0)),
                created_at: Set(now),
                updated_at: Set(now),
                ..Default::default()
            };
            inventory.insert(&self.db).await?;
        }

        // 返回更新后的库存记录
        let updated = Inventory::find()
            .with_tenant(tenant.org_id, &tenant.app_id)
            .filter(inventory_entity::Column::WarehouseId.eq(req.warehouse_id))
            .filter(inventory_entity::Column::SlotId.eq(req.slot_id))
            .filter(inventory_entity::Column::SkuId.eq(req.sku_id))
            .one(&self.db)
            .await?
            .ok_or(AppError::NotFound)?;

        Ok(InventoryResponse::from(updated))
    }

    /// 获取库存预警列表
    pub async fn get_inventory_warnings(
        &self,
        tenant: &TenantContext,
    ) -> Result<Vec<InventoryWarning>> {
        let inventories = Inventory::find()
            .with_tenant(tenant.org_id, &tenant.app_id)
            .all(&self.db)
            .await?;

        let mut warnings = Vec::new();

        for inv in inventories {
            let valid = inv.valid_sku.unwrap_or(0);
            let min = inv.min_inventory.unwrap_or(0);
            let max = inv.max_inventory.unwrap_or(i32::MAX);

            if valid < min {
                warnings.push(InventoryWarning {
                    inventory_id: inv.id,
                    warehouse_id: inv.warehouse_id,
                    slot_id: inv.slot_id,
                    sku_id: inv.sku_id,
                    current_stock: valid,
                    min_inventory: min,
                    max_inventory: max,
                    warning_type: "LOW".to_string(),
                });
            } else if valid > max {
                warnings.push(InventoryWarning {
                    inventory_id: inv.id,
                    warehouse_id: inv.warehouse_id,
                    slot_id: inv.slot_id,
                    sku_id: inv.sku_id,
                    current_stock: valid,
                    min_inventory: min,
                    max_inventory: max,
                    warning_type: "HIGH".to_string(),
                });
            }
        }

        Ok(warnings)
    }
}

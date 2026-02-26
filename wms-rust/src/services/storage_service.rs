//! 出入库单据服务
//!
//! 实现入库单、出库单、盘点单的业务逻辑

use sea_orm::{
    ActiveModelTrait, ColumnTrait, DatabaseConnection, EntityTrait, PaginatorTrait,
    QueryFilter, QueryOrder, Set, TransactionTrait,
};

use crate::{
    domain::tenant::TenantContext,
    error::{AppError, Result},
    models::dto::{
        AdjustCheckRequest, CheckItemAdjustRequest, CheckItemResponse, CheckResponse,
        CreateCheckRequest, CreateStorageInRequest, CreateStorageOutRequest,
        PageResponse, QueryCheckRequest, QueryStorageInRequest, QueryStorageOutRequest,
        StorageInItemResponse, StorageInResponse, StorageOutItemResponse, StorageOutResponse,
        StorageInItemRequest, StorageOutItemRequest, UpdateCheckRequest, UpdateStorageInRequest,
        CheckItemRequest,
    },
    models::entity::{
        inventory_entity::{self, Entity as Inventory},
        storage_in_entity::{self, Entity as StorageIn},
        storage_in_item_entity::{self, Entity as StorageInItem},
        storage_out_entity::{self, Entity as StorageOut},
        storage_out_item_entity::{self, Entity as StorageOutItem},
        sku_entity::{self, Entity as Sku},
    },
    domain::tenant::TenantScoped,
};

use rust_decimal::Decimal;

/// 单据服务
#[derive(Debug, Clone)]
pub struct StorageService {
    db: DatabaseConnection,
}

impl StorageService {
    pub fn new(db: DatabaseConnection) -> Self {
        Self { db }
    }

    /// 生成单号
    fn generate_code(&self, prefix: &str) -> String {
        let timestamp = chrono::Local::now().format("%Y%m%d%H%M%S");
        let random: u32 = rand::random::<u32>() % 10000;
        format!("{}{}{:04}", prefix, timestamp, random)
    }

    // ==================== 入库单管理 ====================

    /// 查询入库单列表
    pub async fn list_storage_in(
        &self,
        tenant: &TenantContext,
        query: QueryStorageInRequest,
    ) -> Result<PageResponse<StorageInResponse>> {
        let page = query.page();
        let page_size = query.page_size();

        let mut select = StorageIn::find().with_tenant(tenant.org_id, &tenant.app_id);

        if let Some(code) = &query.transaction_code {
            select = select.filter(storage_in_entity::Column::TransactionCode.eq(code));
        }
        if let Some(type_) = &query.transaction_type {
            select = select.filter(storage_in_entity::Column::TransactionType.eq(type_));
        }
        if let Some(warehouse_id) = query.warehouse_id {
            select = select.filter(storage_in_entity::Column::WarehouseId.eq(warehouse_id));
        }
        if let Some(status) = &query.status {
            select = select.filter(storage_in_entity::Column::Status.eq(status));
        }

        select = select.order_by_desc(storage_in_entity::Column::CreatedAt);

        let paginator = select.paginate(&self.db, page_size);
        let total = paginator.num_items().await?;
        let storage_ins = paginator.fetch_page(page - 1).await?;

        let mut data = Vec::new();
        for storage_in in storage_ins {
            let items = self.get_storage_in_items(storage_in.id).await?;
            data.push(self.build_storage_in_response(storage_in, items));
        }

        Ok(PageResponse::new(data, total, page, page_size))
    }

    /// 获取入库单明细
    async fn get_storage_in_items(&self, storage_in_id: i64) -> Result<Vec<StorageInItemResponse>> {
        let items = StorageInItem::find()
            .filter(storage_in_item_entity::Column::StorageInId.eq(storage_in_id))
            .all(&self.db)
            .await?;

        let mut result = Vec::new();
        for item in items {
            // 查询SKU信息
            let sku = Sku::find_by_id(item.sku_id).one(&self.db).await?;

            result.push(StorageInItemResponse {
                id: item.id,
                sku_id: item.sku_id,
                sku_name: sku.as_ref().map(|s| s.sku_name.clone()),
                sku_code: sku.as_ref().map(|s| s.sku_code.clone()),
                quantity: item.transaction_quantities,
                unit_price: item.transaction_sku_price,
                total_price: item.transaction_sku_price.unwrap_or(Decimal::ZERO)
                    * Decimal::from(item.transaction_quantities),
                note: None,
            });
        }

        Ok(result)
    }

    /// 构建入库单响应
    fn build_storage_in_response(
        &self,
        model: storage_in_entity::Model,
        items: Vec<StorageInItemResponse>,
    ) -> StorageInResponse {
        let total_quantity: i32 = items.iter().map(|i| i.quantity).sum();
        let total_amount: Decimal = items.iter().map(|i| i.total_price).sum();

        StorageInResponse {
            id: model.id,
            org_id: model.org_id,
            transaction_code: model.transaction_code,
            transaction_type: model.transaction_type.unwrap_or_default(),
            warehouse_id: model.warehouse_id,
            slot_id: model.slot_id,
            batch_no: model.batch_no,
            status: model.status,
            note: model.note,
            procurement_id: model.procurement_id,
            originator_id: model.originator_id,
            originator_name: model.originator_name,
            transaction_by: model.transaction_by,
            total_quantity,
            total_amount,
            created_at: model.created_at.to_string(),
            updated_at: model.updated_at.to_string(),
            items,
        }
    }

    /// 获取单个入库单
    pub async fn get_storage_in(
        &self,
        tenant: &TenantContext,
        id: i64,
    ) -> Result<StorageInResponse> {
        let storage_in = StorageIn::find_by_id(id)
            .with_tenant(tenant.org_id, &tenant.app_id)
            .one(&self.db)
            .await?
            .ok_or(AppError::NotFound)?;

        let items = self.get_storage_in_items(id).await?;
        Ok(self.build_storage_in_response(storage_in, items))
    }

    /// 创建入库单
    pub async fn create_storage_in(
        &self,
        tenant: &TenantContext,
        req: CreateStorageInRequest,
    ) -> Result<StorageInResponse> {
        let code = req.transaction_code.unwrap_or_else(|| self.generate_code("IN"));
        let now = chrono::Local::now().naive_local();

        // 开启事务
        let txn = self.db.begin().await?;

        // 创建入库单
        let storage_in = storage_in_entity::ActiveModel {
            org_id: Set(tenant.org_id),
            org_tag: Set(None),
            transaction_code: Set(code),
            transaction_type: Set(Some(req.transaction_type)),
            warehouse_id: Set(req.warehouse_id),
            slot_id: Set(req.slot_id),
            batch_no: Set(req.batch_no),
            note: Set(req.note),
            procurement_id: Set(req.procurement_id),
            status: Set("DRAFT".to_string()),
            originator_id: Set(tenant.user_id),
            originator_name: Set(None),
            transaction_by: Set(None),
            storage_in_time: Set(None),
            transaction_time: Set(None),
            readjust_cost_price: Set(Some(Decimal::ZERO)),
            distributor_customer: Set(None),
            out_order_num: Set(None),
            field1: Set(None),
            field2: Set(None),
            created_at: Set(now),
            updated_at: Set(now),
            ..Default::default()
        };

        let result = storage_in.insert(&txn).await?;

        // 创建入库明细
        for item in req.items {
            let item_model = storage_in_item_entity::ActiveModel {
                org_id: Set(tenant.org_id),
                org_tag: Set(None),
                storage_in_id: Set(result.id),
                sku_id: Set(item.sku_id),
                transaction_sku_price: Set(item.unit_price),
                transaction_quantities: Set(item.quantity),
                after_transaction_quantities: Set(None),
                before_transaction_quantities: Set(None),
                demand_quantities: Set(None),
                transaction_time: Set(None),
                r#type: Set(None),
                relation_code: Set(None),
                created_at: Set(now),
                ..Default::default()
            };
            item_model.insert(&txn).await?;
        }

        txn.commit().await?;

        self.get_storage_in(tenant, result.id).await
    }

    /// 审核入库单
    pub async fn approve_storage_in(
        &self,
        tenant: &TenantContext,
        id: i64,
    ) -> Result<StorageInResponse> {
        let now = chrono::Local::now().naive_local();

        let storage_in = StorageIn::find_by_id(id)
            .with_tenant(tenant.org_id, &tenant.app_id)
            .one(&self.db)
            .await?
            .ok_or(AppError::NotFound)?;

        if storage_in.status != "DRAFT" && storage_in.status != "PENDING" {
            return Err(AppError::BadRequest("入库单状态不正确".to_string()));
        }

        let txn = self.db.begin().await?;

        // 更新入库单状态
        let mut storage_in_model: storage_in_entity::ActiveModel = storage_in.into();
        storage_in_model.status = Set("APPROVED".to_string());
        storage_in_model.transaction_time = Set(Some(now));
        storage_in_model.transaction_by = Set(tenant.user_id.map(|id| id.to_string()));
        storage_in_model.update(&txn).await?;

        txn.commit().await?;

        self.get_storage_in(tenant, id).await
    }

    /// 完成入库单（入库确认，更新库存）
    pub async fn complete_storage_in(
        &self,
        tenant: &TenantContext,
        id: i64,
    ) -> Result<StorageInResponse> {
        let now = chrono::Local::now().naive_local();

        let storage_in = StorageIn::find_by_id(id)
            .with_tenant(tenant.org_id, &tenant.app_id)
            .one(&self.db)
            .await?
            .ok_or(AppError::NotFound)?;

        if storage_in.status != "APPROVED" {
            return Err(AppError::BadRequest("入库单未审核".to_string()));
        }

        let txn = self.db.begin().await?;

        // 获取入库明细
        let items = StorageInItem::find()
            .filter(storage_in_item_entity::Column::StorageInId.eq(id))
            .all(&txn)
            .await?;

        // 更新库存
        for item in items {
            self.increase_inventory(
                &txn,
                tenant,
                storage_in.warehouse_id,
                storage_in.slot_id,
                item.sku_id,
                item.transaction_quantities,
            ).await?;
        }

        // 更新入库单状态
        let mut storage_in_model: storage_in_entity::ActiveModel = storage_in.into();
        storage_in_model.status = Set("COMPLETED".to_string());
        storage_in_model.storage_in_time = Set(Some(now));
        storage_in_model.update(&txn).await?;

        txn.commit().await?;

        self.get_storage_in(tenant, id).await
    }

    /// 增加库存
    async fn increase_inventory(
        &self,
        txn: &sea_orm::DatabaseTransaction,
        tenant: &TenantContext,
        warehouse_id: i64,
        slot_id: Option<i64>,
        sku_id: i64,
        quantity: i32,
    ) -> Result<()> {
        let now = chrono::Local::now().naive_local();

        let inventory = Inventory::find()
            .filter(inventory_entity::Column::OrgId.eq(tenant.org_id))
            .filter(inventory_entity::Column::WarehouseId.eq(warehouse_id))
            .filter(inventory_entity::Column::SlotId.eq(slot_id))
            .filter(inventory_entity::Column::SkuId.eq(sku_id))
            .one(txn)
            .await?;

        if let Some(inv) = inventory {
            let mut inv_model: inventory_entity::ActiveModel = inv.into();
            let current = inv_model.valid_sku.clone().unwrap().unwrap_or(0);
            inv_model.valid_sku = Set(Some(current + quantity));
            inv_model.updated_at = Set(now);
            inv_model.update(txn).await?;
        } else {
            let inventory = inventory_entity::ActiveModel {
                org_id: Set(tenant.org_id),
                org_tag: Set(None),
                warehouse_id: Set(warehouse_id),
                slot_id: Set(slot_id),
                sku_id: Set(sku_id),
                valid_sku: Set(Some(quantity)),
                max_inventory: Set(None),
                min_inventory: Set(None),
                advance_quantities: Set(Some(0)),
                transmit_quantities: Set(Some(0)),
                order_count: Set(Some(0)),
                created_at: Set(now),
                updated_at: Set(now),
                ..Default::default()
            };
            inventory.insert(txn).await?;
        }

        Ok(())
    }

    // ==================== 出库单管理 ====================

    /// 创建出库单
    pub async fn create_storage_out(
        &self,
        tenant: &TenantContext,
        req: CreateStorageOutRequest,
    ) -> Result<StorageOutResponse> {
        let code = req.transaction_code.unwrap_or_else(|| self.generate_code("OUT"));
        let now = chrono::Local::now().naive_local();

        let txn = self.db.begin().await?;

        // 检查库存是否充足
        for item in &req.items {
            let inventory = Inventory::find()
                .filter(inventory_entity::Column::OrgId.eq(tenant.org_id))
                .filter(inventory_entity::Column::WarehouseId.eq(req.warehouse_id))
                .filter(inventory_entity::Column::SlotId.eq(req.slot_id))
                .filter(inventory_entity::Column::SkuId.eq(item.sku_id))
                .one(&txn)
                .await?;

            let available = inventory.map(|i| i.valid_sku.unwrap_or(0)).unwrap_or(0);
            if available < item.quantity {
                return Err(AppError::BadRequest(format!(
                    "SKU {} 库存不足，可用: {}, 需要: {}",
                    item.sku_id, available, item.quantity
                )));
            }
        }

        // 创建出库单
        let storage_out = storage_out_entity::ActiveModel {
            org_id: Set(tenant.org_id),
            org_tag: Set(None),
            transaction_code: Set(code),
            transaction_type: Set(Some(req.transaction_type)),
            warehouse_id: Set(req.warehouse_id),
            slot_id: Set(req.slot_id),
            batch_no: Set(req.batch_no),
            note: Set(req.note),
            sales_id: Set(req.sales_id),
            status: Set("DRAFT".to_string()),
            originator_id: Set(tenant.user_id),
            originator_name: Set(None),
            transaction_by: Set(None),
            storage_out_time: Set(None),
            transaction_time: Set(None),
            out_order_num: Set(None),
            distributor_customer: Set(None),
            field1: Set(None),
            field2: Set(None),
            created_at: Set(now),
            updated_at: Set(now),
            ..Default::default()
        };

        let result = storage_out.insert(&txn).await?;

        // 创建出库明细
        for item in req.items {
            let item_model = storage_out_item_entity::ActiveModel {
                org_id: Set(tenant.org_id),
                org_tag: Set(None),
                storage_out_id: Set(result.id),
                sku_id: Set(item.sku_id),
                transaction_sku_price: Set(item.unit_price),
                transaction_quantities: Set(item.quantity),
                after_transaction_quantities: Set(None),
                before_transaction_quantities: Set(None),
                demand_quantities: Set(None),
                transaction_time: Set(None),
                relation_code: Set(None),
                r#type: Set(None),
                created_at: Set(now),
                ..Default::default()
            };
            item_model.insert(&txn).await?;
        }

        txn.commit().await?;

        self.get_storage_out(tenant, result.id).await
    }

    /// 获取出库单
    pub async fn get_storage_out(
        &self,
        tenant: &TenantContext,
        id: i64,
    ) -> Result<StorageOutResponse> {
        let storage_out = StorageOut::find_by_id(id)
            .with_tenant(tenant.org_id, &tenant.app_id)
            .one(&self.db)
            .await?
            .ok_or(AppError::NotFound)?;

        let items = self.get_storage_out_items(id).await?;
        Ok(self.build_storage_out_response(storage_out, items))
    }

    /// 获取出库单明细
    async fn get_storage_out_items(&self, storage_out_id: i64) -> Result<Vec<StorageOutItemResponse>> {
        let items = StorageOutItem::find()
            .filter(storage_out_item_entity::Column::StorageOutId.eq(storage_out_id))
            .all(&self.db)
            .await?;

        let mut result = Vec::new();
        for item in items {
            let sku = Sku::find_by_id(item.sku_id).one(&self.db).await?;

            result.push(StorageOutItemResponse {
                id: item.id,
                sku_id: item.sku_id,
                sku_name: sku.as_ref().map(|s| s.sku_name.clone()),
                sku_code: sku.as_ref().map(|s| s.sku_code.clone()),
                quantity: item.transaction_quantities,
                unit_price: item.transaction_sku_price,
                total_price: item.transaction_sku_price.unwrap_or(Decimal::ZERO)
                    * Decimal::from(item.transaction_quantities),
                note: None,
            });
        }

        Ok(result)
    }

    /// 构建出库单响应
    fn build_storage_out_response(
        &self,
        model: storage_out_entity::Model,
        items: Vec<StorageOutItemResponse>,
    ) -> StorageOutResponse {
        let total_quantity: i32 = items.iter().map(|i| i.quantity).sum();
        let total_amount: Decimal = items.iter().map(|i| i.total_price).sum();

        StorageOutResponse {
            id: model.id,
            org_id: model.org_id,
            transaction_code: model.transaction_code,
            transaction_type: model.transaction_type.unwrap_or_default(),
            warehouse_id: model.warehouse_id,
            slot_id: model.slot_id,
            batch_no: model.batch_no,
            status: model.status,
            note: model.note,
            sales_id: model.sales_id,
            originator_id: model.originator_id,
            originator_name: model.originator_name,
            transaction_by: model.transaction_by,
            total_quantity,
            total_amount,
            created_at: model.created_at.to_string(),
            updated_at: model.updated_at.to_string(),
            items,
        }
    }

    /// 完成出库单（出库确认，扣减库存）
    pub async fn complete_storage_out(
        &self,
        tenant: &TenantContext,
        id: i64,
    ) -> Result<StorageOutResponse> {
        let now = chrono::Local::now().naive_local();

        let storage_out = StorageOut::find_by_id(id)
            .with_tenant(tenant.org_id, &tenant.app_id)
            .one(&self.db)
            .await?
            .ok_or(AppError::NotFound)?;

        if storage_out.status != "APPROVED" {
            return Err(AppError::BadRequest("出库单未审核".to_string()));
        }

        let txn = self.db.begin().await?;

        // 获取出库明细
        let items = StorageOutItem::find()
            .filter(storage_out_item_entity::Column::StorageOutId.eq(id))
            .all(&txn)
            .await?;

        // 扣减库存
        for item in items {
            self.decrease_inventory(
                &txn,
                tenant,
                storage_out.warehouse_id,
                storage_out.slot_id,
                item.sku_id,
                item.transaction_quantities,
            ).await?;
        }

        // 更新出库单状态
        let mut storage_out_model: storage_out_entity::ActiveModel = storage_out.into();
        storage_out_model.status = Set("COMPLETED".to_string());
        storage_out_model.storage_out_time = Set(Some(now));
        storage_out_model.update(&txn).await?;

        txn.commit().await?;

        self.get_storage_out(tenant, id).await
    }

    /// 减少库存
    async fn decrease_inventory(
        &self,
        txn: &sea_orm::DatabaseTransaction,
        tenant: &TenantContext,
        warehouse_id: i64,
        slot_id: Option<i64>,
        sku_id: i64,
        quantity: i32,
    ) -> Result<()> {
        let now = chrono::Local::now().naive_local();

        let inventory = Inventory::find()
            .filter(inventory_entity::Column::OrgId.eq(tenant.org_id))
            .filter(inventory_entity::Column::WarehouseId.eq(warehouse_id))
            .filter(inventory_entity::Column::SlotId.eq(slot_id))
            .filter(inventory_entity::Column::SkuId.eq(sku_id))
            .one(txn)
            .await?
            .ok_or(AppError::BadRequest("库存记录不存在".to_string()))?;

        let current = inventory.valid_sku.unwrap_or(0);
        if current < quantity {
            return Err(AppError::BadRequest("库存不足".to_string()));
        }

        let mut inv_model: inventory_entity::ActiveModel = inventory.into();
        inv_model.valid_sku = Set(Some(current - quantity));
        inv_model.updated_at = Set(now);
        inv_model.update(txn).await?;

        Ok(())
    }

    // ==================== 盘点单管理（简化实现）====================

    /// 创建盘点单
    pub async fn create_check(
        &self,
        tenant: &TenantContext,
        req: CreateCheckRequest,
    ) -> Result<CheckResponse> {
        // TODO: 实现盘点单创建
        Err(AppError::NotFound)
    }

    /// 获取盘点单
    pub async fn get_check(
        &self,
        tenant: &TenantContext,
        id: i64,
    ) -> Result<CheckResponse> {
        // TODO: 实现盘点单查询
        Err(AppError::NotFound)
    }
}

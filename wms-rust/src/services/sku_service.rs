//! SKU 服务
//!
//! 实现SKU相关的业务逻辑

use sea_orm::{
    ActiveModelTrait, ColumnTrait, DatabaseConnection, EntityTrait, PaginatorTrait,
    QueryFilter, QueryOrder, Set,
};

use crate::{
    domain::tenant::TenantContext,
    error::{AppError, Result},
    models::dto::{
        CreateSkuRequest, PageResponse, QuerySkuRequest, SkuResponse, SkuDetailResponse,
        SkuSpecItem, SkuPhotoItem, UpdateSkuRequest,
    },
    models::entity::{
        sku_entity::{self, Entity as Sku},
        sku_spec_entity::{self, Entity as SkuSpec},
    },
    domain::tenant::TenantScoped,
};

/// SKU服务
#[derive(Debug, Clone)]
pub struct SkuService {
    db: DatabaseConnection,
}

impl SkuService {
    /// 创建新的SKU服务实例
    pub fn new(db: DatabaseConnection) -> Self {
        Self { db }
    }

    /// 查询SKU列表（自动应用租户隔离）
    pub async fn list_skus(
        &self,
        tenant: &TenantContext,
        query: QuerySkuRequest,
    ) -> Result<PageResponse<SkuResponse>> {
        let page = query.page();
        let page_size = query.page_size();

        // 构建基础查询（自动应用租户隔离）
        let mut select = Sku::find().with_tenant(tenant.org_id, &tenant.app_id);

        // 应用动态条件
        if let Some(product_id) = query.product_id {
            select = select.filter(sku_entity::Column::ProductId.eq(product_id));
        }

        if let Some(keyword) = &query.keyword {
            select = select.filter(
                sku_entity::Column::SkuName
                    .contains(keyword)
                    .or(sku_entity::Column::SkuCode.contains(keyword)),
            );
        }

        if let Some(status) = &query.status {
            select = select.filter(sku_entity::Column::Status.eq(status));
        }

        if let Some(bar_code) = &query.bar_code {
            select = select.filter(sku_entity::Column::BarCode.eq(bar_code));
        }

        // 排序
        select = select.order_by_desc(sku_entity::Column::CreatedAt);

        // 执行分页查询
        let paginator = select.paginate(&self.db, page_size);
        let total = paginator.num_items().await?;
        let skus = paginator.fetch_page(page - 1).await?;

        // 转换为响应DTO
        let data: Vec<SkuResponse> = skus.into_iter().map(SkuResponse::from).collect();

        Ok(PageResponse::new(data, total, page, page_size))
    }

    /// 获取单个SKU（自动应用租户隔离）
    pub async fn get_sku(
        &self,
        tenant: &TenantContext,
        id: i64,
    ) -> Result<SkuDetailResponse> {
        let sku = Sku::find_by_id(id)
            .with_tenant(tenant.org_id, &tenant.app_id)
            .one(&self.db)
            .await?
            .ok_or(AppError::NotFound)?;

        // 查询规格信息
        let specs = SkuSpec::find()
            .filter(sku_spec_entity::Column::SkuId.eq(id))
            .all(&self.db)
            .await?;

        let spec_items: Vec<SkuSpecItem> = specs.into_iter().map(|s| SkuSpecItem {
            group_id: s.group_id,
            group_name: format!("Group {}", s.group_id), // 实际应该查询规格组表
            specification_id: s.specification_id,
            specification_name: None,
            specification_value: None,
        }).collect();

        let photos: Vec<SkuPhotoItem> = vec![]; // 实际应该查询SKU图片表

        Ok(SkuDetailResponse {
            sku: SkuResponse::from(sku),
            specifications: spec_items,
            photos,
        })
    }

    /// 创建SKU（自动注入租户信息）
    pub async fn create_sku(
        &self,
        tenant: &TenantContext,
        req: CreateSkuRequest,
    ) -> Result<SkuResponse> {
        // 检查SKU编码是否已存在（在同一组织内）
        let existing = Sku::find()
            .with_tenant(tenant.org_id, &tenant.app_id)
            .filter(sku_entity::Column::SkuCode.eq(&req.sku_code))
            .one(&self.db)
            .await?;

        if existing.is_some() {
            return Err(AppError::BadRequest(format!(
                "SKU编码 '{}' 已存在",
                req.sku_code
            )));
        }

        let now = chrono::Local::now().naive_local();

        let sku = sku_entity::ActiveModel {
            org_id: Set(tenant.org_id),
            org_tag: Set(None),
            product_id: Set(req.product_id),
            sku_code: Set(req.sku_code),
            sku_name: Set(req.sku_name),
            bar_code: Set(req.bar_code),
            description: Set(req.description),
            sku_price: Set(req.sku_price),
            suggested_price: Set(req.suggested_price),
            cost_price: Set(req.cost_price),
            stock_cost: Set(Some(Decimal::ZERO)),
            weight: Set(req.weight),
            volume: Set(req.volume),
            spec: Set(req.spec),
            sort_value: Set(req.sort_value),
            status: Set("ACTIVE".to_string()),
            readjust_cost_price: Set(Some(Decimal::ZERO)),
            field1: Set(None),
            field2: Set(None),
            field3: Set(None),
            created_at: Set(now),
            updated_at: Set(now),
            ..Default::default()
        };

        let result = sku.insert(&self.db).await?;

        // 如果有规格关系，创建规格关系记录
        if let Some(specs) = req.specifications {
            for spec in specs {
                let spec_relation = sku_spec_entity::ActiveModel {
                    org_id: Set(tenant.org_id),
                    org_tag: Set(None),
                    sku_id: Set(result.id),
                    group_id: Set(spec.group_id),
                    specification_id: Set(spec.specification_id),
                    created_at: Set(now),
                    ..Default::default()
                };
                spec_relation.insert(&self.db).await?;
            }
        }

        self.get_sku(tenant, result.id).await.map(|d| d.sku)
    }

    /// 更新SKU（自动应用租户隔离）
    pub async fn update_sku(
        &self,
        tenant: &TenantContext,
        id: i64,
        req: UpdateSkuRequest,
    ) -> Result<SkuResponse> {
        // 先查询确保属于当前租户
        let sku = Sku::find_by_id(id)
            .with_tenant(tenant.org_id, &tenant.app_id)
            .one(&self.db)
            .await?
            .ok_or(AppError::NotFound)?;

        let mut sku: sku_entity::ActiveModel = sku.into();
        let now = chrono::Local::now().naive_local();

        // 更新字段
        if let Some(sku_name) = req.sku_name {
            sku.sku_name = Set(sku_name);
        }
        if let Some(bar_code) = req.bar_code {
            sku.bar_code = Set(Some(bar_code));
        }
        if let Some(description) = req.description {
            sku.description = Set(Some(description));
        }
        if let Some(sku_price) = req.sku_price {
            sku.sku_price = Set(Some(sku_price));
        }
        if let Some(suggested_price) = req.suggested_price {
            sku.suggested_price = Set(Some(suggested_price));
        }
        if let Some(cost_price) = req.cost_price {
            sku.cost_price = Set(Some(cost_price));
        }
        if let Some(weight) = req.weight {
            sku.weight = Set(Some(weight));
        }
        if let Some(volume) = req.volume {
            sku.volume = Set(Some(volume));
        }
        if let Some(spec) = req.spec {
            sku.spec = Set(Some(spec));
        }
        if let Some(sort_value) = req.sort_value {
            sku.sort_value = Set(Some(sort_value));
        }
        if let Some(status) = req.status {
            sku.status = Set(status);
        }

        sku.updated_at = Set(now);

        sku.update(&self.db).await?;

        self.get_sku(tenant, id).await.map(|d| d.sku)
    }

    /// 删除SKU（自动应用租户隔离）
    pub async fn delete_sku(&self, tenant: &TenantContext, id: i64) -> Result<()> {
        // 先查询确保属于当前租户
        let sku = Sku::find_by_id(id)
            .with_tenant(tenant.org_id, &tenant.app_id)
            .one(&self.db)
            .await?
            .ok_or(AppError::NotFound)?;

        // 删除相关的规格关系
        SkuSpec::delete_many()
            .filter(sku_spec_entity::Column::SkuId.eq(id))
            .exec(&self.db)
            .await?;

        // 删除SKU
        Sku::delete(sku_entity::ActiveModel::from(sku))
            .exec(&self.db)
            .await?;

        Ok(())
    }
}

use rust_decimal::Decimal;

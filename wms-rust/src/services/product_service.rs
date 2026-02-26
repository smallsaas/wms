//! 产品服务
//!
//! 实现产品相关的业务逻辑

use sea_orm::{
    ActiveModelTrait, ColumnTrait, DatabaseConnection, EntityTrait, PaginatorTrait,
    QueryFilter, QueryOrder, Set,
};

use crate::{
    domain::tenant::TenantContext,
    error::{AppError, Result},
    models::dto::{
        CreateProductRequest, PageResponse, ProductResponse, QueryProductRequest,
        UpdateProductRequest,
    },
    models::entity::product_entity::{self, Entity as Product},
    domain::tenant::TenantScoped,
};

/// 产品服务
#[derive(Debug, Clone)]
pub struct ProductService {
    db: DatabaseConnection,
}

impl ProductService {
    /// 创建新的产品服务实例
    pub fn new(db: DatabaseConnection) -> Self {
        Self { db }
    }

    /// 查询产品列表（自动应用租户隔离）
    pub async fn list_products(
        &self,
        tenant: &TenantContext,
        query: QueryProductRequest,
    ) -> Result<PageResponse<ProductResponse>> {
        let page = query.page();
        let page_size = query.page_size();

        // 构建基础查询（自动应用租户隔离）
        let mut select = Product::find().with_tenant(tenant.org_id, &tenant.app_id);

        // 应用动态条件
        if let Some(keyword) = &query.keyword {
            select = select.filter(
                product_entity::Column::Name
                    .contains(keyword)
                    .or(product_entity::Column::ProductCode.contains(keyword)),
            );
        }

        if let Some(category_id) = query.category_id {
            select = select.filter(product_entity::Column::ProductCategoryId.eq(category_id));
        }

        if let Some(status) = &query.status {
            select = select.filter(product_entity::Column::Status.eq(status));
        }

        if let Some(bar_code) = &query.bar_code {
            select = select.filter(product_entity::Column::BarCode.eq(bar_code));
        }

        // 排序
        select = select.order_by_desc(product_entity::Column::CreatedAt);

        // 执行分页查询
        let paginator = select.paginate(&self.db, page_size);
        let total = paginator.num_items().await?;
        let products = paginator.fetch_page(page - 1).await?;

        // 转换为响应DTO
        let data: Vec<ProductResponse> = products.into_iter().map(ProductResponse::from).collect();

        Ok(PageResponse::new(data, total, page, page_size))
    }

    /// 获取单个产品（自动应用租户隔离）
    pub async fn get_product(
        &self,
        tenant: &TenantContext,
        id: i64,
    ) -> Result<ProductResponse> {
        let product = Product::find_by_id(id)
            .with_tenant(tenant.org_id, &tenant.app_id)
            .one(&self.db)
            .await?
            .ok_or(AppError::NotFound)?;

        Ok(ProductResponse::from(product))
    }

    /// 创建产品（自动注入租户信息）
    pub async fn create_product(
        &self,
        tenant: &TenantContext,
        req: CreateProductRequest,
    ) -> Result<ProductResponse> {
        // 检查产品编码是否已存在（在同一组织内）
        let existing = Product::find()
            .with_tenant(tenant.org_id, &tenant.app_id)
            .filter(product_entity::Column::ProductCode.eq(&req.product_code))
            .one(&self.db)
            .await?;

        if existing.is_some() {
            return Err(AppError::BadRequest(format!(
                "产品编码 '{}' 已存在",
                req.product_code
            )));
        }

        let now = chrono::Local::now().naive_local();

        let product = product_entity::ActiveModel {
            org_id: Set(tenant.org_id),
            org_tag: Set(None),
            product_category_id: Set(req.category_id),
            product_code: Set(req.product_code),
            name: Set(req.name),
            english_name: Set(req.english_name),
            weight: Set(req.weight),
            volume: Set(req.volume),
            spec: Set(req.spec),
            product_standard: Set(req.product_standard),
            price: Set(req.price),
            suggested_price: Set(req.suggested_price),
            cost_price: Set(req.cost_price),
            specification: Set(req.specification),
            sort_value: Set(req.sort_value),
            search_key_word: Set(req.search_key_word),
            bar_code: Set(req.bar_code),
            description: Set(req.description),
            status: Set("ACTIVE".to_string()),
            quantities: Set(Some(0)),
            stock_cost: Set(Some(Decimal::ZERO)),
            purchase_advance: Set(Some(0)),
            readjust_cost_price: Set(Some(Decimal::ZERO)),
            field1: Set(None),
            field4: Set(None),
            field5: Set(None),
            created_at: Set(now),
            updated_at: Set(now),
            ..Default::default()
        };

        let result = product.insert(&self.db).await?;

        self.get_product(tenant, result.id).await
    }

    /// 更新产品（自动应用租户隔离）
    pub async fn update_product(
        &self,
        tenant: &TenantContext,
        id: i64,
        req: UpdateProductRequest,
    ) -> Result<ProductResponse> {
        // 先查询确保属于当前租户
        let product = Product::find_by_id(id)
            .with_tenant(tenant.org_id, &tenant.app_id)
            .one(&self.db)
            .await?
            .ok_or(AppError::NotFound)?;

        let mut product: product_entity::ActiveModel = product.into();
        let now = chrono::Local::now().naive_local();

        // 更新字段
        if let Some(category_id) = req.category_id {
            product.product_category_id = Set(Some(category_id));
        }
        if let Some(name) = req.name {
            product.name = Set(name);
        }
        if let Some(english_name) = req.english_name {
            product.english_name = Set(Some(english_name));
        }
        if let Some(weight) = req.weight {
            product.weight = Set(Some(weight));
        }
        if let Some(volume) = req.volume {
            product.volume = Set(Some(volume));
        }
        if let Some(spec) = req.spec {
            product.spec = Set(Some(spec));
        }
        if let Some(product_standard) = req.product_standard {
            product.product_standard = Set(Some(product_standard));
        }
        if let Some(price) = req.price {
            product.price = Set(Some(price));
        }
        if let Some(suggested_price) = req.suggested_price {
            product.suggested_price = Set(Some(suggested_price));
        }
        if let Some(cost_price) = req.cost_price {
            product.cost_price = Set(Some(cost_price));
        }
        if let Some(specification) = req.specification {
            product.specification = Set(Some(specification));
        }
        if let Some(sort_value) = req.sort_value {
            product.sort_value = Set(Some(sort_value));
        }
        if let Some(search_key_word) = req.search_key_word {
            product.search_key_word = Set(Some(search_key_word));
        }
        if let Some(bar_code) = req.bar_code {
            product.bar_code = Set(Some(bar_code));
        }
        if let Some(description) = req.description {
            product.description = Set(Some(description));
        }
        if let Some(status) = req.status {
            product.status = Set(status);
        }

        product.updated_at = Set(now);

        product.update(&self.db).await?;

        self.get_product(tenant, id).await
    }

    /// 删除产品（自动应用租户隔离）
    pub async fn delete_product(&self, tenant: &TenantContext, id: i64) -> Result<()> {
        // 先查询确保属于当前租户
        let product = Product::find_by_id(id)
            .with_tenant(tenant.org_id, &tenant.app_id)
            .one(&self.db)
            .await?
            .ok_or(AppError::NotFound)?;

        Product::delete(product_entity::ActiveModel::from(product))
            .exec(&self.db)
            .await?;

        Ok(())
    }
}

// 导入 Decimal 类型
use rust_decimal::Decimal;

# WMS 用户数据隔离实施计划 (Rust Web API 版)

## 项目概述

基于 Rust Web API 最佳实践，对 WMS 系统进行用户数据隔离改造，实现多租户数据隔离机制。

**设计原则**:
- 不依赖原有 Java 框架 (uaas, crud-runtime, uaas-dev-org)
- 不采用 Mapper、DAO 分层模式
- 采用 Rust 现代 Web 开发最佳实践
- 使用 sqlx / sea-orm 等现代化 ORM
- **无需考虑 Auth 认证**（由外部网关/代理处理）
- **租户信息通过第三方依赖接口获取**

---

## 技术栈选型

| 层级 | 技术选型 | 说明 |
|------|----------|------|
| Web 框架 | Axum / Actix-web | 高性能异步 Web 框架 |
| ORM | Sea-ORM / sqlx | 类型安全的数据库操作 |
| 中间件 | tower-http | 日志、CORS、压缩等 |
| 配置 | config + dotenv | 环境配置管理 |
| 序列化 | serde | JSON 序列化/反序列化 |
| 校验 | validator | 请求参数校验 |
| 错误处理 | thiserror + anyhow | 结构化错误处理 |
| 租户 SDK | wms-tenant-sdk | 第三方租户信息获取接口 |
| 数据库 | PostgreSQL 15+ | 主数据库 |

---

## 架构设计

### 目录结构
```
wms-rust/
├── Cargo.toml
├── src/
│   ├── main.rs                 # 应用入口
│   ├── config.rs               # 配置管理
│   ├── lib.rs                  # 库导出
│   ├── middleware/             # 中间件
│   │   ├── mod.rs
│   │   └── tenant.rs           # 租户上下文中间件
│   ├── handlers/               # HTTP 处理器 (Controller)
│   │   ├── mod.rs
│   │   ├── product.rs
│   │   ├── sku.rs
│   │   └── warehouse.rs
│   ├── services/               # 业务逻辑层
│   │   ├── mod.rs
│   │   ├── product_service.rs
│   │   ├── sku_service.rs
│   │   └── warehouse_service.rs
│   ├── models/                 # 数据模型
│   │   ├── mod.rs
│   │   ├── entity/             # 数据库实体 (Sea-ORM)
│   │   │   ├── mod.rs
│   │   │   ├── product.rs
│   │   │   ├── sku.rs
│   │   │   └── warehouse.rs
│   │   ├── dto/                # 数据传输对象
│   │   │   ├── mod.rs
│   │   │   ├── request.rs
│   │   │   └── response.rs
│   │   └── vo/                 # 视图对象
│   │       └── mod.rs
│   ├── infrastructure/         # 基础设施
│   │   ├── mod.rs
│   │   ├── database.rs         # 数据库连接池
│   │   └── tenant_sdk.rs       # 第三方租户 SDK 封装
│   ├── domain/                 # 领域层
│   │   ├── mod.rs
│   │   └── tenant.rs           # 租户领域模型
│   └── utils/                  # 工具函数
│       ├── mod.rs
│       └── error.rs            # 错误处理
├── migrations/                 # 数据库迁移 (sea-orm-cli)
│   └── README.md
├── tests/                      # 集成测试
│   └── integration_tests.rs
└── Cargo.lock
```

---

## 核心设计

### 1. 租户 SDK 接口定义

```rust
// src/infrastructure/tenant_sdk.rs
use async_trait::async_trait;
use serde::{Deserialize, Serialize};

/// 第三方租户 SDK 接口定义
/// 实际项目中，这可能是外部依赖 crate 提供的接口
#[async_trait]
pub trait TenantSdk: Send + Sync {
    /// 根据请求上下文获取租户信息
    async fn get_tenant(&self, ctx: &RequestContext) -> Result<TenantInfo, TenantSdkError>;
}

/// 请求上下文
#[derive(Debug, Clone)]
pub struct RequestContext {
    pub request_id: String,
    pub client_ip: String,
    pub headers: std::collections::HashMap<String, String>,
}

/// 租户信息
#[derive(Debug, Clone, Serialize, Deserialize)]
pub struct TenantInfo {
    pub org_id: i64,
    pub app_id: String,
    pub user_id: Option<i64>,
    pub org_name: Option<String>,
    pub app_name: Option<String>,
}

/// SDK 错误
#[derive(Debug, thiserror::Error)]
pub enum TenantSdkError {
    #[error("Tenant not found")]
    NotFound,
    #[error("SDK request failed: {0}")]
    RequestFailed(String),
    #[error("Invalid response: {0}")]
    InvalidResponse(String),
}

// ============================================
// 模拟实现（用于开发和测试）
// ============================================

/// 模拟租户 SDK 实现
pub struct MockTenantSdk;

impl MockTenantSdk {
    pub fn new() -> Self {
        Self
    }
}

#[async_trait]
impl TenantSdk for MockTenantSdk {
    async fn get_tenant(&self, ctx: &RequestContext) -> Result<TenantInfo, TenantSdkError> {
        // 模拟从 Header 或环境变量获取租户信息
        // 实际项目中，这里会调用外部服务或 SDK
        
        let org_id = ctx.headers
            .get("X-Org-Id")
            .and_then(|v| v.parse::<i64>().ok())
            .unwrap_or(1);
            
        let app_id = ctx.headers
            .get("X-App-Id")
            .cloned()
            .unwrap_or_else(|| "default".to_string());
            
        let user_id = ctx.headers
            .get("X-User-Id")
            .and_then(|v| v.parse::<i64>().ok());
        
        Ok(TenantInfo {
            org_id,
            app_id,
            user_id,
            org_name: Some(format!("Org {}", org_id)),
            app_name: Some(format!("App {}", app_id)),
        })
    }
}

// ============================================
// HTTP 客户端实现（用于生产环境）
// ============================================

/// HTTP 租户 SDK 实现（调用外部租户服务）
pub struct HttpTenantSdk {
    base_url: String,
    client: reqwest::Client,
    api_key: String,
}

impl HttpTenantSdk {
    pub fn new(base_url: impl Into<String>, api_key: impl Into<String>) -> Self {
        Self {
            base_url: base_url.into(),
            client: reqwest::Client::new(),
            api_key: api_key.into(),
        }
    }
}

#[async_trait]
impl TenantSdk for HttpTenantSdk {
    async fn get_tenant(&self, ctx: &RequestContext) -> Result<TenantInfo, TenantSdkError> {
        let response = self.client
            .get(format!("{}/api/v1/tenant", self.base_url))
            .header("X-API-Key", &self.api_key)
            .header("X-Request-Id", &ctx.request_id)
            .send()
            .await
            .map_err(|e| TenantSdkError::RequestFailed(e.to_string()))?;
            
        if response.status() == 404 {
            return Err(TenantSdkError::NotFound);
        }
        
        let tenant_info = response
            .json::<TenantInfo>()
            .await
            .map_err(|e| TenantSdkError::InvalidResponse(e.to_string()))?;
            
        Ok(tenant_info)
    }
}
```

### 2. 租户上下文 (Tenant Context)

```rust
// src/domain/tenant.rs
use serde::{Deserialize, Serialize};

#[derive(Debug, Clone, Serialize, Deserialize)]
pub struct TenantContext {
    pub org_id: i64,
    pub app_id: String,
    pub user_id: Option<i64>,
    pub org_name: Option<String>,
    pub app_name: Option<String>,
}

impl TenantContext {
    pub fn new(
        org_id: i64,
        app_id: impl Into<String>,
        user_id: Option<i64>,
    ) -> Self {
        Self {
            org_id,
            app_id: app_id.into(),
            user_id,
            org_name: None,
            app_name: None,
        }
    }
    
    pub fn with_names(
        mut self,
        org_name: impl Into<String>,
        app_name: impl Into<String>,
    ) -> Self {
        self.org_name = Some(org_name.into());
        self.app_name = Some(app_name.into());
        self
    }
}

// Axum 扩展，用于在请求中传递租户上下文
#[derive(Clone)]
pub struct TenantContextExtractor(pub TenantContext);
```

### 3. 租户上下文中间件

```rust
// src/middleware/tenant.rs
use axum::{
    extract::{Request, State},
    middleware::Next,
    response::Response,
};
use std::sync::Arc;

use crate::{
    domain::tenant::{TenantContext, TenantContextExtractor},
    infrastructure::tenant_sdk::{TenantSdk, RequestContext},
    utils::error::AppError,
    AppState,
};

/// 租户上下文中间件
/// 通过第三方 SDK 获取租户信息
pub async fn tenant_context_middleware<S: TenantSdk>(
    State(state): State<Arc<AppState<S>>>,
    mut request: Request,
    next: Next,
) -> Result<Response, AppError> {
    // 构建请求上下文
    let request_ctx = build_request_context(&request);
    
    // 通过 SDK 获取租户信息
    let tenant_info = state
        .tenant_sdk
        .get_tenant(&request_ctx)
        .await
        .map_err(|e| AppError::BadRequest(format!("Failed to get tenant: {}", e)))?;
    
    // 创建租户上下文
    let tenant_ctx = TenantContext::new(
        tenant_info.org_id,
        tenant_info.app_id,
        tenant_info.user_id,
    );
    
    // 将租户上下文注入请求扩展
    request.extensions_mut().insert(TenantContextExtractor(tenant_ctx));
    
    Ok(next.run(request).await)
}

fn build_request_context(request: &Request) -> RequestContext {
    use std::collections::HashMap;
    
    let headers = request
        .headers()
        .iter()
        .filter_map(|(k, v)| {
            v.to_str()
                .ok()
                .map(|v| (k.as_str().to_string(), v.to_string()))
        })
        .collect::<HashMap<_, _>>();
    
    RequestContext {
        request_id: uuid::Uuid::new_v4().to_string(),
        client_ip: "127.0.0.1".to_string(), // 实际应从连接信息获取
        headers,
    }
}
```

### 4. 实体模型设计 (Sea-ORM)

```rust
// src/models/entity/product.rs
use sea_orm::entity::prelude::*;
use serde::{Deserialize, Serialize};

#[derive(Clone, Debug, PartialEq, DeriveEntityModel, Serialize, Deserialize)]
#[sea_orm(table_name = "wms_product")]
pub struct Model {
    #[sea_orm(primary_key)]
    pub id: i64,
    pub org_id: i64,                    // 组织ID（隔离字段）
    pub app_id: String,                 // 应用ID（隔离字段）
    pub product_category_id: Option<i64>,
    pub product_code: String,
    pub name: String,
    pub english_name: Option<String>,
    pub weight: Option<String>,
    pub price: Option<Decimal>,
    pub cost_price: Option<Decimal>,
    pub status: String,
    pub created_at: DateTimeWithTimeZone,
    pub updated_at: DateTimeWithTimeZone,
}

#[derive(Copy, Clone, Debug, EnumIter, DeriveRelation)]
pub enum Relation {}

impl ActiveModelBehavior for ActiveModel {}

// 租户作用域 trait
pub trait TenantScoped {
    fn with_tenant(self, org_id: i64, app_id: &str) -> Self;
}

impl TenantScoped for Select<Entity> {
    fn with_tenant(self, org_id: i64, app_id: &str) -> Self {
        self.filter(
            Condition::all()
                .add(Column::OrgId.eq(org_id))
                .add(Column::AppId.eq(app_id))
        )
    }
}
```

### 5. Service 层设计

```rust
// src/services/product_service.rs
use sea_orm::{DatabaseConnection, EntityTrait, QueryFilter, Condition};
use crate::{
    domain::tenant::TenantContext,
    models::entity::product::{self, Entity as Product, TenantScoped},
    models::dto::request::{CreateProductRequest, UpdateProductRequest, QueryProductRequest},
    models::dto::response::ProductResponse,
    utils::error::AppError,
};

pub struct ProductService {
    db: DatabaseConnection,
}

impl ProductService {
    pub fn new(db: DatabaseConnection) -> Self {
        Self { db }
    }

    /// 查询产品列表（自动应用租户隔离）
    pub async fn list_products(
        &self,
        tenant: &TenantContext,
        query: QueryProductRequest,
    ) -> Result<Vec<ProductResponse>, AppError> {
        let mut select = Product::find()
            .with_tenant(tenant.org_id, &tenant.app_id);  // 自动应用租户过滤

        // 动态条件
        if let Some(name) = query.name {
            select = select.filter(product::Column::Name.contains(name));
        }
        
        if let Some(status) = query.status {
            select = select.filter(product::Column::Status.eq(status));
        }

        let products = select.all(&self.db).await?;
        
        Ok(products.into_iter().map(ProductResponse::from).collect())
    }

    /// 获取单个产品（自动应用租户隔离）
    pub async fn get_product(
        &self,
        tenant: &TenantContext,
        id: i64,
    ) -> Result<ProductResponse, AppError> {
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
    ) -> Result<ProductResponse, AppError> {
        let product = product::ActiveModel {
            org_id: Set(tenant.org_id),           // 自动注入
            app_id: Set(tenant.app_id.clone()),   // 自动注入
            product_code: Set(req.product_code),
            name: Set(req.name),
            price: Set(req.price),
            status: Set("active".to_string()),
            ..Default::default()
        };

        let result = Product::insert(product).exec(&self.db).await?;
        
        self.get_product(tenant, result.last_insert_id).await
    }

    /// 更新产品（自动应用租户隔离）
    pub async fn update_product(
        &self,
        tenant: &TenantContext,
        id: i64,
        req: UpdateProductRequest,
    ) -> Result<ProductResponse, AppError> {
        // 先查询确保属于当前租户
        let product = Product::find_by_id(id)
            .with_tenant(tenant.org_id, &tenant.app_id)
            .one(&self.db)
            .await?
            .ok_or(AppError::NotFound)?;

        let mut product: product::ActiveModel = product.into();
        
        if let Some(name) = req.name {
            product.name = Set(name);
        }
        if let Some(price) = req.price {
            product.price = Set(Some(price));
        }
        
        product.update(&self.db).await?;
        
        self.get_product(tenant, id).await
    }

    /// 删除产品（自动应用租户隔离）
    pub async fn delete_product(
        &self,
        tenant: &TenantContext,
        id: i64,
    ) -> Result<(), AppError> {
        // 先查询确保属于当前租户
        let product = Product::find_by_id(id)
            .with_tenant(tenant.org_id, &tenant.app_id)
            .one(&self.db)
            .await?
            .ok_or(AppError::NotFound)?;

        Product::delete(product::ActiveModel::from(product))
            .exec(&self.db)
            .await?;
            
        Ok(())
    }
}
```

### 6. Handler 层 (Controller)

```rust
// src/handlers/product.rs
use axum::{
    extract::{Path, Query, State, Extension},
    routing::{get, post, put, delete},
    Json, Router,
};
use std::sync::Arc;

use crate::{
    domain::tenant::TenantContextExtractor,
    models::dto::request::{CreateProductRequest, UpdateProductRequest, QueryProductRequest},
    models::dto::response::ProductResponse,
    services::product_service::ProductService,
    utils::error::AppError,
    AppState,
};

pub fn product_routes<S: TenantSdk>() -> Router<Arc<AppState<S>>> {
    Router::new()
        .route("/api/products", get(list_products).post(create_product))
        .route("/api/products/:id", get(get_product).put(update_product).delete(delete_product))
}

/// GET /api/products
async fn list_products<S: TenantSdk>(
    State(state): State<Arc<AppState<S>>>,
    Extension(TenantContextExtractor(tenant)): Extension<TenantContextExtractor>,
    Query(query): Query<QueryProductRequest>,
) -> Result<Json<Vec<ProductResponse>>, AppError> {
    let service = ProductService::new(state.db.clone());
    let products = service.list_products(&tenant, query).await?;
    Ok(Json(products))
}

/// GET /api/products/:id
async fn get_product<S: TenantSdk>(
    State(state): State<Arc<AppState<S>>>,
    Extension(TenantContextExtractor(tenant)): Extension<TenantContextExtractor>,
    Path(id): Path<i64>,
) -> Result<Json<ProductResponse>, AppError> {
    let service = ProductService::new(state.db.clone());
    let product = service.get_product(&tenant, id).await?;
    Ok(Json(product))
}

/// POST /api/products
async fn create_product<S: TenantSdk>(
    State(state): State<Arc<AppState<S>>>,
    Extension(TenantContextExtractor(tenant)): Extension<TenantContextExtractor>,
    Json(req): Json<CreateProductRequest>,
) -> Result<Json<ProductResponse>, AppError> {
    let service = ProductService::new(state.db.clone());
    let product = service.create_product(&tenant, req).await?;
    Ok(Json(product))
}

/// PUT /api/products/:id
async fn update_product<S: TenantSdk>(
    State(state): State<Arc<AppState<S>>>,
    Extension(TenantContextExtractor(tenant)): Extension<TenantContextExtractor>,
    Path(id): Path<i64>,
    Json(req): Json<UpdateProductRequest>,
) -> Result<Json<ProductResponse>, AppError> {
    let service = ProductService::new(state.db.clone());
    let product = service.update_product(&tenant, id, req).await?;
    Ok(Json(product))
}

/// DELETE /api/products/:id
async fn delete_product<S: TenantSdk>(
    State(state): State<Arc<AppState<S>>>,
    Extension(TenantContextExtractor(tenant)): Extension<TenantContextExtractor>,
    Path(id): Path<i64>,
) -> Result<(), AppError> {
    let service = ProductService::new(state.db.clone());
    service.delete_product(&tenant, id).await?;
    Ok(())
}
```

### 7. DTO 设计

```rust
// src/models/dto/request.rs
use serde::{Deserialize, Serialize};
use validator::Validate;

#[derive(Debug, Deserialize, Validate)]
pub struct CreateProductRequest {
    #[validate(length(min = 1, max = 100))]
    pub product_code: String,
    #[validate(length(min = 1, max = 200))]
    pub name: String,
    pub price: Option<Decimal>,
    pub product_category_id: Option<i64>,
}

#[derive(Debug, Deserialize, Validate)]
pub struct UpdateProductRequest {
    #[validate(length(min = 1, max = 200))]
    pub name: Option<String>,
    pub price: Option<Decimal>,
}

#[derive(Debug, Deserialize)]
pub struct QueryProductRequest {
    pub name: Option<String>,
    pub status: Option<String>,
    pub page: Option<u64>,
    pub page_size: Option<u64>,
}

// src/models/dto/response.rs
#[derive(Debug, Serialize)]
pub struct ProductResponse {
    pub id: i64,
    pub product_code: String,
    pub name: String,
    pub price: Option<Decimal>,
    pub status: String,
    pub created_at: DateTimeWithTimeZone,
}

impl From<product::Model> for ProductResponse {
    fn from(model: product::Model) -> Self {
        Self {
            id: model.id,
            product_code: model.product_code,
            name: model.name,
            price: model.price,
            status: model.status,
            created_at: model.created_at,
        }
    }
}
```

### 8. 错误处理

```rust
// src/utils/error.rs
use axum::{
    http::StatusCode,
    response::{IntoResponse, Response},
    Json,
};
use serde_json::json;
use thiserror::Error;

#[derive(Error, Debug)]
pub enum AppError {
    #[error("Not Found")]
    NotFound,
    
    #[error("Bad Request: {0}")]
    BadRequest(String),
    
    #[error("Database error: {0}")]
    Database(#[from] sea_orm::DbErr),
    
    #[error("Validation error: {0}")]
    Validation(#[from] validator::ValidationErrors),
    
    #[error("Tenant SDK error: {0}")]
    TenantSdk(String),
    
    #[error("Internal Server Error")]
    Internal,
}

impl IntoResponse for AppError {
    fn into_response(self) -> Response {
        let (status, error_message) = match self {
            AppError::NotFound => (StatusCode::NOT_FOUND, "Not Found"),
            AppError::BadRequest(msg) => (StatusCode::BAD_REQUEST, msg.as_str()),
            AppError::Database(_) => (StatusCode::INTERNAL_SERVER_ERROR, "Database error"),
            AppError::Validation(_) => (StatusCode::BAD_REQUEST, "Validation error"),
            AppError::TenantSdk(msg) => (StatusCode::BAD_REQUEST, msg.as_str()),
            AppError::Internal => (StatusCode::INTERNAL_SERVER_ERROR, "Internal Server Error"),
        };

        let body = Json(json!({
            "error": error_message,
            "code": status.as_u16(),
        }));

        (status, body).into_response()
    }
}
```

### 9. 主应用入口

```rust
// src/main.rs
use axum::{
    middleware,
    routing::get,
    Router,
};
use sea_orm::Database;
use std::sync::Arc;

mod config;
mod domain;
mod handlers;
mod infrastructure;
mod middleware;
mod models;
mod services;
mod utils;

use config::AppConfig;
use handlers::{product, sku, warehouse};
use infrastructure::tenant_sdk::{TenantSdk, MockTenantSdk, HttpTenantSdk};
use middleware::tenant::tenant_context_middleware;

/// 应用状态，泛型参数 S 为 TenantSdk 实现
pub struct AppState<S: TenantSdk> {
    db: sea_orm::DatabaseConnection,
    config: Arc<AppConfig>,
    tenant_sdk: Arc<S>,
}

impl<S: TenantSdk> Clone for AppState<S> {
    fn clone(&self) -> Self {
        Self {
            db: self.db.clone(),
            config: self.config.clone(),
            tenant_sdk: self.tenant_sdk.clone(),
        }
    }
}

#[tokio::main]
async fn main() -> anyhow::Result<()> {
    // 加载配置
    let config = Arc::new(AppConfig::from_env()?);
    
    // 连接数据库
    let db = Database::connect(&config.database_url).await?;
    
    // 选择租户 SDK 实现
    // 开发/测试环境使用 Mock
    // 生产环境使用 HTTP 客户端
    let tenant_sdk: Arc<dyn TenantSdk> = if config.use_mock_tenant {
        Arc::new(MockTenantSdk::new())
    } else {
        Arc::new(HttpTenantSdk::new(
            &config.tenant_service_url,
            &config.tenant_api_key,
        ))
    };
    
    let state = Arc::new(AppState {
        db,
        config,
        tenant_sdk,
    });

    // 构建路由
    let app = Router::new()
        .route("/health", get(health_check))
        .merge(product::product_routes())
        .merge(sku::sku_routes())
        .merge(warehouse::warehouse_routes())
        .layer(middleware::from_fn_with_state(state.clone(), tenant_context_middleware))
        .with_state(state);

    // 启动服务
    let listener = tokio::net::TcpListener::bind("0.0.0.0:8080").await?;
    axum::serve(listener, app).await?;
    
    Ok(())
}

async fn health_check() -> &'static str {
    "OK"
}
```

---

## 数据库设计

### 核心字段

所有业务表都包含以下隔离字段：

| 字段名 | 类型 | 说明 |
|--------|------|------|
| `org_id` | BIGINT | 组织ID，用于组织级别数据隔离 |
| `app_id` | VARCHAR(64) | 应用ID，用于应用级别数据隔离 |

### 索引设计

```sql
-- 每个业务表都需要创建的索引
CREATE INDEX idx_{table}_org_app ON {table_name} (org_id, app_id);
CREATE INDEX idx_{table}_org ON {table_name} (org_id);
CREATE INDEX idx_{table}_app ON {table_name} (app_id);

-- 可选：创建租户级别的唯一约束（防止同一租户内重复数据）
-- CREATE UNIQUE INDEX idx_{table}_code_per_tenant ON {table_name} (org_id, app_id, unique_field);
```

### 示例表结构

```sql
-- wms_product 表
CREATE TABLE wms_product (
    id BIGSERIAL PRIMARY KEY,
    org_id BIGINT NOT NULL,
    app_id VARCHAR(64) NOT NULL,
    product_category_id BIGINT,
    product_code VARCHAR(100) NOT NULL,
    name VARCHAR(200) NOT NULL,
    english_name VARCHAR(200),
    price NUMERIC(18, 2),
    cost_price NUMERIC(18, 2),
    status VARCHAR(20) NOT NULL DEFAULT 'active',
    created_at TIMESTAMP DEFAULT CURRENT_TIMESTAMP,
    updated_at TIMESTAMP DEFAULT CURRENT_TIMESTAMP
);

-- 添加字段注释
COMMENT ON COLUMN wms_product.org_id IS '组织ID，用于数据隔离';
COMMENT ON COLUMN wms_product.app_id IS '应用ID，用于数据隔离';

-- 创建索引
CREATE INDEX idx_product_org_app ON wms_product (org_id, app_id);
CREATE INDEX idx_product_code ON wms_product (product_code);
CREATE INDEX idx_product_status ON wms_product (status);

-- 可选：创建租户级别的唯一约束
-- CREATE UNIQUE INDEX idx_product_code_per_tenant ON wms_product (org_id, app_id, product_code);
```

---

## 实施阶段规划

### 📊 进度总览

```
总任务数: 63
已完成: 0
进行中: 0
待开始: 63

进度: [░░░░░░░░░░░░░░░░░░░░] 0%
```

---

## Phase 1: 项目初始化（P1）
**优先级**: 🔴 Critical  
**预计工期**: 1-2 天  
**目标**: 搭建 Rust 项目基础架构

| 任务ID | 任务描述 | 状态 | 文件 |
|--------|----------|------|------|
| P1-T001 | 初始化 Cargo 项目 | ⬜ Not Started | Cargo.toml |
| P1-T002 | 配置依赖 (axum, sea-orm, async-trait 等) | ⬜ Not Started | Cargo.toml |
| P1-T003 | 创建基础目录结构 | ⬜ Not Started | src/ |
| P1-T004 | 实现配置管理模块 | ⬜ Not Started | src/config.rs |
| P1-T005 | 实现错误处理模块 | ⬜ Not Started | src/utils/error.rs |
| P1-T006 | 实现数据库连接池 | ⬜ Not Started | src/infrastructure/database.rs |
| P1-T007 | 配置数据库迁移工具 | ⬜ Not Started | migrations/ |

---

## Phase 2: 核心基础设施（P2）
**优先级**: 🔴 Critical  
**预计工期**: 2-3 天  
**目标**: 实现租户隔离核心机制

| 任务ID | 任务描述 | 状态 | 文件 |
|--------|----------|------|------|
| P2-T001 | 定义 TenantSdk trait 接口 | ⬜ Not Started | src/infrastructure/tenant_sdk.rs |
| P2-T002 | 实现 MockTenantSdk（模拟） | ⬜ Not Started | src/infrastructure/tenant_sdk.rs |
| P2-T003 | 实现 HttpTenantSdk（HTTP 客户端） | ⬜ Not Started | src/infrastructure/tenant_sdk.rs |
| P2-T004 | 实现 TenantContext 领域模型 | ⬜ Not Started | src/domain/tenant.rs |
| P2-T005 | 实现租户上下文中间件 | ⬜ Not Started | src/middleware/tenant.rs |
| P2-T006 | 实现 TenantScoped trait | ⬜ Not Started | src/models/entity/mod.rs |
| P2-T007 | 编写基础迁移脚本 (org_id, app_id) | ⬜ Not Started | migrations/ |

---

## Phase 3: Product 模块（P3）
**优先级**: 🔴 Critical  
**预计工期**: 2-3 天  
**目标**: 完成 Product 模块完整实现

| 任务ID | 任务描述 | 状态 | 文件 |
|--------|----------|------|------|
| P3-T001 | 创建 Product 实体 (Sea-ORM) | ⬜ Not Started | src/models/entity/product.rs |
| P3-T002 | 创建 Product DTOs | ⬜ Not Started | src/models/dto/ |
| P3-T003 | 实现 ProductService | ⬜ Not Started | src/services/product_service.rs |
| P3-T004 | 实现 Product Handlers | ⬜ Not Started | src/handlers/product.rs |
| P3-T005 | 创建 Product 表迁移 | ⬜ Not Started | migrations/ |
| P3-T006 | 编写 Product 单元测试 | ⬜ Not Started | tests/ |

---

## Phase 4: SKU 模块（P4）
**优先级**: 🟡 High  
**预计工期**: 3-4 天  
**目标**: 完成 SKU 模块完整实现

| 任务ID | 任务描述 | 状态 | 文件 |
|--------|----------|------|------|
| P4-T001 | 创建 SkuProduct 实体 | ⬜ Not Started | src/models/entity/sku_product.rs |
| P4-T002 | 创建 SkuCondition 实体 | ⬜ Not Started | src/models/entity/sku_condition.rs |
| P4-T003 | 创建 SkuPhoto 实体 | ⬜ Not Started | src/models/entity/sku_photo.rs |
| P4-T004 | 创建 SkuSpecification 实体 | ⬜ Not Started | src/models/entity/sku_specification.rs |
| P4-T005 | 创建 SkuTag 实体 | ⬜ Not Started | src/models/entity/sku_tag.rs |
| P4-T006 | 实现 SKU Service 层 | ⬜ Not Started | src/services/sku_service.rs |
| P4-T007 | 实现 SKU Handlers | ⬜ Not Started | src/handlers/sku.rs |
| P4-T008 | 创建 SKU 表迁移 | ⬜ Not Started | migrations/ |

---

## Phase 5: Warehouse 模块（P5）
**优先级**: 🟡 High  
**预计工期**: 4-5 天  
**目标**: 完成 Warehouse 模块完整实现

| 任务ID | 任务描述 | 状态 | 文件 |
|--------|----------|------|------|
| P5-T001 | 创建 Warehouse 实体 | ⬜ Not Started | src/models/entity/warehouse.rs |
| P5-T002 | 创建 StorageIn 实体 | ⬜ Not Started | src/models/entity/storage_in.rs |
| P5-T003 | 创建 StorageOut 实体 | ⬜ Not Started | src/models/entity/storage_out.rs |
| P5-T004 | 创建 Inventory 实体 | ⬜ Not Started | src/models/entity/inventory.rs |
| P5-T005 | 创建 Procurement 实体 | ⬜ Not Started | src/models/entity/procurement.rs |
| P5-T006 | 创建 Suppliers 实体 | ⬜ Not Started | src/models/entity/suppliers.rs |
| P5-T007 | 实现 Warehouse Service 层 | ⬜ Not Started | src/services/warehouse_service.rs |
| P5-T008 | 实现 Warehouse Handlers | ⬜ Not Started | src/handlers/warehouse.rs |
| P5-T009 | 创建 Warehouse 表迁移 | ⬜ Not Started | migrations/ |

---

## Phase 6: 测试与优化（P6）
**优先级**: 🟢 Medium  
**预计工期**: 7-10 天  
**目标**: 确保系统稳定性和数据隔离正确性

### 6.1 单元测试

| 任务ID | 任务描述 | 状态 | 目标文件 | 覆盖率要求 |
|--------|----------|------|----------|-----------|
| P6-T001 | TenantSdk Mock 实现测试 | ⬜ Not Started | `src/infrastructure/tenant_sdk.rs` | 100% |
| P6-T002 | TenantContext 领域模型测试 | ⬜ Not Started | `src/domain/tenant.rs` | 100% |
| P6-T003 | ProductService 单元测试 | ⬜ Not Started | `src/services/product_service.rs` | > 80% |
| P6-T004 | SkuService 单元测试 | ⬜ Not Started | `src/services/sku_service.rs` | > 80% |
| P6-T005 | WarehouseService 单元测试 | ⬜ Not Started | `src/services/warehouse_service.rs` | > 80% |
| P6-T006 | 错误处理模块测试 | ⬜ Not Started | `src/utils/error.rs` | 100% |
| P6-T007 | DTO 校验测试 | ⬜ Not Started | `src/models/dto/` | 100% |

### 6.2 集成测试

| 任务ID | 任务描述 | 状态 | 测试范围 |
|--------|----------|------|----------|
| P6-T008 | Product API 端到端测试 | ⬜ Not Started | CRUD 全流程 |
| P6-T009 | SKU API 端到端测试 | ⬜ Not Started | CRUD 全流程 |
| P6-T010 | Warehouse API 端到端测试 | ⬜ Not Started | CRUD 全流程 |
| P6-T011 | 数据库迁移测试 | ⬜ Not Started | 升级/回滚 |
| P6-T012 | 中间件集成测试 | ⬜ Not Started | 租户上下文传递 |

### 6.3 租户隔离专项测试

| 任务ID | 任务描述 | 状态 | 测试场景 |
|--------|----------|------|----------|
| P6-T013 | 跨组织数据隔离测试 | ⬜ Not Started | Org A 无法访问 Org B 数据 |
| P6-T014 | 跨应用数据隔离测试 | ⬜ Not Started | App A 无法访问 App B 数据 |
| P6-T015 | 租户字段自动注入测试 | ⬜ Not Started | 创建时自动填充 org_id/app_id |
| P6-T016 | 租户查询过滤测试 | ⬜ Not Started | 查询结果仅限当前租户 |
| P6-T017 | 租户更新隔离测试 | ⬜ Not Started | 无法更新其他租户数据 |
| P6-T018 | 租户删除隔离测试 | ⬜ Not Started | 无法删除其他租户数据 |
| P6-T019 | 缺失租户信息测试 | ⬜ Not Started | 无 Tenant Header 时拒绝请求 |
| P6-T020 | 无效租户信息测试 | ⬜ Not Started | 非法 org_id/app_id 处理 |

### 6.4 性能测试

| 任务ID | 任务描述 | 状态 | 目标指标 |
|--------|----------|------|----------|
| P6-T021 | 并发查询性能测试 | ⬜ Not Started | QPS > 1000 |
| P6-T022 | 并发写入性能测试 | ⬜ Not Started | TPS > 500 |
| P6-T023 | 大数据量分页测试 | ⬜ Not Started | 百万级数据分页 < 100ms |
| P6-T024 | 租户隔离索引性能测试 | ⬜ Not Started | 索引命中率 > 99% |
| P6-T025 | 内存使用测试 | ⬜ Not Started | 无内存泄漏 |
| P6-T026 | 长连接稳定性测试 | ⬜ Not Started | 24小时无异常 |

### 6.5 文档与代码质量

| 任务ID | 任务描述 | 状态 | 备注 |
|--------|----------|------|------|
| P6-T027 | 添加 OpenAPI 文档 | ⬜ Not Started | utoipa |
| P6-T028 | 代码审查与重构 | ⬜ Not Started | - |
| P6-T029 | 测试覆盖率报告 | ⬜ Not Started | cargo-tarpaulin |
| P6-T030 | 编写测试文档 | ⬜ Not Started | 测试用例说明 |

---

---

## 测试策略详解

### 测试目录结构

```
wms-rust/
├── src/
│   └── ...
├── tests/
│   ├── common/                 # 测试工具模块
│   │   ├── mod.rs
│   │   ├── test_db.rs         # 测试数据库连接
│   │   └── test_client.rs     # HTTP 测试客户端
│   ├── unit/                   # 单元测试
│   │   ├── tenant_sdk_test.rs
│   │   ├── product_service_test.rs
│   │   └── sku_service_test.rs
│   ├── integration/            # 集成测试
│   │   ├── product_api_test.rs
│   │   ├── sku_api_test.rs
│   │   └── warehouse_api_test.rs
│   └── isolation/              # 租户隔离专项测试
│       ├── cross_tenant_test.rs
│       └── tenant_injection_test.rs
└── Cargo.toml
```

### 单元测试示例

```rust
// src/services/product_service.rs
#[cfg(test)]
mod tests {
    use super::*;
    use sea_orm::{Database, DatabaseBackend, MockDatabase, MockExecResult, Transaction};

    #[tokio::test]
    async fn test_list_products_with_tenant_isolation() {
        // 准备 Mock 数据库
        let db = MockDatabase::new(DatabaseBackend::Postgres)
            .into_connection();

        let service = ProductService::new(db);
        
        // 创建租户上下文
        let tenant = TenantContext::new(1, "app1", Some(100));
        
        let query = QueryProductRequest {
            name: Some("test".to_string()),
            status: Some("active".to_string()),
            page: None,
            page_size: None,
        };

        // 执行查询
        let result = service.list_products(&tenant, query).await;
        
        // 验证结果
        assert!(result.is_ok());
    }

    #[tokio::test]
    async fn test_create_product_auto_inject_tenant() {
        let db = MockDatabase::new(DatabaseBackend::Postgres)
            .append_exec_results([
                MockExecResult {
                    last_insert_id: 1,
                    rows_affected: 1,
                },
            ])
            .into_connection();

        let service = ProductService::new(db);
        let tenant = TenantContext::new(1, "app1", Some(100));
        
        let req = CreateProductRequest {
            product_code: "P001".to_string(),
            name: "Test Product".to_string(),
            price: Some(Decimal::new(10000, 2)),
            product_category_id: None,
        };

        let result = service.create_product(&tenant, req).await;
        assert!(result.is_ok());
        
        // 验证数据库执行记录
        // assert_eq!(db.into_transaction_log(), [...]);
    }
}
```

### 集成测试示例

```rust
// tests/integration/product_api_test.rs
use wms_rust::{
    infrastructure::tenant_sdk::{MockTenantSdk, TenantInfo},
    AppState,
};
use axum::Router;
use sea_orm::Database;

mod common;

#[tokio::test]
async fn test_create_and_get_product() {
    // 初始化测试数据库
    let db = Database::connect("postgres://postgres:postgres@localhost/wms_test")
        .await
        .unwrap();
    
    // 清理测试数据
    clean_test_data(&db).await;

    // 创建 Mock Tenant SDK
    let tenant_sdk = MockTenantSdk::new();

    // 创建应用状态
    let state = std::sync::Arc::new(AppState {
        db: db.clone(),
        config: std::sync::Arc::new(common::test_config()),
        tenant_sdk: std::sync::Arc::new(tenant_sdk),
    });

    // 构建测试路由
    let app = Router::new()
        .merge(wms_rust::handlers::product::product_routes())
        .with_state(state);

    // 创建产品
    let create_response = common::test_client(&app)
        .post("/api/products")
        .header("X-Org-Id", "1")
        .header("X-App-Id", "app1")
        .json(&serde_json::json!({
            "product_code": "P001",
            "name": "Test Product",
            "price": "100.00"
        }))
        .send()
        .await;

    assert_eq!(create_response.status(), 200);
    
    let created: serde_json::Value = create_response.json().await;
    let product_id = created["id"].as_i64().unwrap();

    // 获取产品
    let get_response = common::test_client(&app)
        .get(&format!("/api/products/{}", product_id))
        .header("X-Org-Id", "1")
        .header("X-App-Id", "app1")
        .send()
        .await;

    assert_eq!(get_response.status(), 200);
    
    let fetched: serde_json::Value = get_response.json().await;
    assert_eq!(fetched["product_code"], "P001");
    assert_eq!(fetched["org_id"], 1);
    assert_eq!(fetched["app_id"], "app1");
}
```

### 租户隔离专项测试

```rust
// tests/isolation/cross_tenant_test.rs
use wms_rust::infrastructure::tenant_sdk::MockTenantSdk;

#[tokio::test]
async fn test_cross_org_data_isolation() {
    let (app, db) = common::setup_test_app().await;
    
    // Org 1 创建产品
    let org1_product = common::create_product(&app, 1, "app1", "P001", "Org1 Product").await;
    let product_id = org1_product["id"].as_i64().unwrap();
    
    // Org 2 尝试访问 Org 1 的产品 - 应该返回 404
    let response = common::test_client(&app)
        .get(&format!("/api/products/{}", product_id))
        .header("X-Org-Id", "2")  // 不同的 org_id
        .header("X-App-Id", "app1")
        .send()
        .await;
    
    assert_eq!(response.status(), 404);
}

#[tokio::test]
async fn test_cross_app_data_isolation() {
    let (app, db) = common::setup_test_app().await;
    
    // App 1 创建产品
    let app1_product = common::create_product(&app, 1, "app1", "P001", "App1 Product").await;
    let product_id = app1_product["id"].as_i64().unwrap();
    
    // App 2 尝试访问 App 1 的产品 - 应该返回 404
    let response = common::test_client(&app)
        .get(&format!("/api/products/{}", product_id))
        .header("X-Org-Id", "1")
        .header("X-App-Id", "app2")  // 不同的 app_id
        .send()
        .await;
    
    assert_eq!(response.status(), 404);
}

#[tokio::test]
async fn test_tenant_auto_injection_on_create() {
    let (app, db) = common::setup_test_app().await;
    
    let response = common::test_client(&app)
        .post("/api/products")
        .header("X-Org-Id", "42")
        .header("X-App-Id", "myapp")
        .json(&serde_json::json!({
            "product_code": "P001",
            "name": "Test Product",
            "price": "100.00"
        }))
        .send()
        .await;
    
    assert_eq!(response.status(), 200);
    
    let created: serde_json::Value = response.json().await;
    
    // 验证租户字段被自动注入
    assert_eq!(created["org_id"], 42);
    assert_eq!(created["app_id"], "myapp");
    
    // 验证数据库中存储了正确的租户信息
    let db_product = common::get_product_from_db(&db, created["id"].as_i64().unwrap()).await;
    assert_eq!(db_product.org_id, 42);
    assert_eq!(db_product.app_id, "myapp");
}

#[tokio::test]
async fn test_missing_tenant_headers_rejected() {
    let (app, _db) = common::setup_test_app().await;
    
    // 不带 X-Org-Id 的请求
    let response = common::test_client(&app)
        .get("/api/products")
        .header("X-App-Id", "app1")
        .send()
        .await;
    
    assert_eq!(response.status(), 400);
}
```

### 性能测试示例

```rust
// tests/performance/tenant_query_perf_test.rs
use std::time::{Duration, Instant};

#[tokio::test]
async fn test_concurrent_tenant_queries() {
    let (app, _db) = common::setup_test_app_with_large_dataset(1_000_000).await;
    
    let start = Instant::now();
    let mut handles = vec![];
    
    // 并发 1000 个请求
    for i in 0..1000 {
        let app = app.clone();
        let handle = tokio::spawn(async move {
            let org_id = i % 10 + 1;  // 10 个不同的租户
            common::test_client(&app)
                .get("/api/products?page_size=20")
                .header("X-Org-Id", org_id.to_string())
                .header("X-App-Id", "app1")
                .send()
                .await
        });
        handles.push(handle);
    }
    
    // 等待所有请求完成
    for handle in handles {
        let response = handle.await.unwrap();
        assert_eq!(response.status(), 200);
    }
    
    let elapsed = start.elapsed();
    println!("1000 concurrent queries took: {:?}", elapsed);
    
    // 断言性能指标
    assert!(elapsed < Duration::from_secs(5), "Queries took too long!");
}

#[tokio::test]
async fn test_tenant_index_efficiency() {
    let (app, db) = common::setup_test_app_with_large_dataset(1_000_000).await;
    
    // 执行查询并获取执行计划
    let explain = sqlx::query(
        "EXPLAIN SELECT * FROM wms_product WHERE org_id = 1 AND app_id = 'app1'"
    )
    .fetch_one(&db)
    .await
    .unwrap();
    
    // 验证使用了索引
    let explain_str: String = explain.get("Extra");
    assert!(explain_str.contains("Using index"), "Query should use index!");
}
```

### 测试工具模块

```rust
// tests/common/mod.rs
use axum::Router;
use sea_orm::DatabaseConnection;
use wms_rust::{AppState, config::AppConfig};

pub fn test_config() -> AppConfig {
    AppConfig {
        database_url: "postgres://postgres:postgres@localhost/wms_test".to_string(),
        use_mock_tenant: true,
        tenant_service_url: "".to_string(),
        tenant_api_key: "".to_string(),
    }
}

pub async fn setup_test_app() -> (Router, DatabaseConnection) {
    let db = Database::connect(&test_config().database_url)
        .await
        .unwrap();
    
    let tenant_sdk = wms_rust::infrastructure::tenant_sdk::MockTenantSdk::new();
    
    let state = std::sync::Arc::new(AppState {
        db: db.clone(),
        config: std::sync::Arc::new(test_config()),
        tenant_sdk: std::sync::Arc::new(tenant_sdk),
    });

    let app = Router::new()
        .merge(wms_rust::handlers::product::product_routes())
        .merge(wms_rust::handlers::sku::sku_routes())
        .merge(wms_rust::handlers::warehouse::warehouse_routes())
        .with_state(state);

    (app, db)
}

pub async fn setup_test_app_with_large_dataset(count: usize) -> (Router, DatabaseConnection) {
    let (app, db) = setup_test_app().await;
    
    // 插入大量测试数据
    for i in 0..count {
        let org_id = (i % 10 + 1) as i64;
        let app_id = format!("app{}", i % 5 + 1);
        
        sqlx::query(
            "INSERT INTO wms_product (org_id, app_id, product_code, name, status) 
             VALUES ($1, $2, $3, $4, 'active')"
        )
        .bind(org_id)
        .bind(&app_id)
        .bind(format!("P{:08}", i))
        .bind(format!("Product {}", i))
        .execute(&db)
        .await
        .unwrap();
    }
    
    (app, db)
}

pub fn test_client(app: &Router) -> TestClient {
    // 返回一个包装了路由的测试客户端
    TestClient::new(app.clone())
}

pub async fn clean_test_data(db: &DatabaseConnection) {
    sqlx::query("DELETE FROM wms_product WHERE id > 0")
        .execute(db)
        .await
        .unwrap();
}

pub async fn create_product(
    app: &Router,
    org_id: i64,
    app_id: &str,
    code: &str,
    name: &str,
) -> serde_json::Value {
    let response = test_client(app)
        .post("/api/products")
        .header("X-Org-Id", org_id.to_string())
        .header("X-App-Id", app_id)
        .json(&serde_json::json!({
            "product_code": code,
            "name": name,
            "price": "100.00"
        }))
        .send()
        .await;
    
    assert_eq!(response.status(), 200);
    response.json().await
}
```

### 测试执行命令

```bash
# 运行所有测试
cargo test

# 运行单元测试
cargo test --lib

# 运行集成测试
cargo test --test '*'

# 运行租户隔离专项测试
cargo test --test isolation

# 运行性能测试（发布模式）
cargo test --release --test performance

# 生成测试覆盖率报告
cargo tarpaulin --out Html --output-dir ./coverage

# 持续测试（开发时）
cargo watch -x test
```

### CI/CD 测试配置

```yaml
# .github/workflows/test.yml
name: Test

on: [push, pull_request]

jobs:
  test:
    runs-on: ubuntu-latest
    
    services:
      postgres:
        image: postgres:15
        env:
          POSTGRES_PASSWORD: postgres
          POSTGRES_DB: wms_test
        ports:
          - 5432:5432
    
    steps:
      - uses: actions/checkout@v3
      
      - name: Install Rust
        uses: actions-rs/toolchain@v1
        with:
          toolchain: stable
          
      - name: Run migrations
        run: |
          cargo install sea-orm-cli
          sea-orm-cli migrate up
          
      - name: Run unit tests
        run: cargo test --lib
        
      - name: Run integration tests
        run: cargo test --test '*'
        env:
          DATABASE_URL: postgres://postgres:postgres@localhost/wms_test
          
      - name: Run tenant isolation tests
        run: cargo test --test isolation
        env:
          DATABASE_URL: postgres://postgres:postgres@localhost/wms_test
          
      - name: Generate coverage report
        run: cargo tarpaulin --out Xml
        
      - name: Upload coverage
        uses: codecov/codecov-action@v3
```

---

## Phase 7: 部署上线（P7）
**优先级**: 🟢 Medium  
**预计工期**: 1-2 天  
**目标**: 生产环境部署

| 任务ID | 任务描述 | 状态 | 备注 |
|--------|----------|------|------|
| P7-T001 | 编写 Dockerfile | ⬜ Not Started | 多阶段构建 |
| P7-T002 | 配置 CI/CD 流水线 | ⬜ Not Started | GitHub Actions |
| P7-T003 | 准备生产数据库迁移 | ⬜ Not Started | - |
| P7-T004 | 部署到生产环境 | ⬜ Not Started | - |
| P7-T005 | 配置监控和日志 | ⬜ Not Started | Prometheus + Grafana |

---

## 技术规范

### 1. 代码组织原则

```
┌─────────────────────────────────────┐
│           Handlers (API)            │  HTTP 请求处理
├─────────────────────────────────────┤
│            Services                 │  业务逻辑
├─────────────────────────────────────┤
│   Models (Entity + DTO + VO)        │  数据模型
├─────────────────────────────────────┤
│   Infrastructure (DB, Tenant SDK)   │  基础设施
├─────────────────────────────────────┤
│           Domain                    │  领域模型
└─────────────────────────────────────┘
```

### 2. 命名规范

| 类型 | 命名规范 | 示例 |
|------|----------|------|
| 实体 | PascalCase + Entity | `ProductEntity` |
| Service | PascalCase + Service | `ProductService` |
| Handler | snake_case | `list_products` |
| DTO | PascalCase + Request/Response | `CreateProductRequest` |
| 表名 | snake_case + wms_前缀 | `wms_product` |
| Trait | PascalCase + Sdk | `TenantSdk` |

### 3. 错误处理规范

```rust
// 使用 ? 操作符传播错误
let product = Product::find_by_id(id)
    .one(&self.db)
    .await?                          // 自动转换 DbErr -> AppError
    .ok_or(AppError::NotFound)?;     // 转换为业务错误

// Service 返回 Result<T, AppError>
pub async fn create(&self, req: CreateRequest) -> Result<ProductResponse, AppError>
```

### 4. 数据库查询规范

```rust
// 所有查询必须应用租户隔离
let products = Product::find()
    .with_tenant(tenant.org_id, &tenant.app_id)  // 必须调用
    .filter(...)                                  // 业务条件
    .all(&self.db)
    .await?;
```

### 5. Tenant SDK 接口规范

```rust
#[async_trait]
pub trait TenantSdk: Send + Sync {
    async fn get_tenant(&self, ctx: &RequestContext) -> Result<TenantInfo, TenantSdkError>;
}
```

---

## 风险评估

| 风险 | 影响 | 概率 | 应对措施 |
|------|------|------|----------|
| Rust 学习曲线 | 中 | 高 | 提前培训，代码审查 |
| 异步编程复杂性 | 中 | 中 | 使用成熟的 async 模式 |
| Sea-ORM 限制 | 低 | 低 | 复杂查询使用原生 SQL |
| 第三方 SDK 依赖 | 中 | 中 | 提供 Mock 实现用于测试 |
| 性能优化 | 中 | 中 | 早期进行性能测试 |

---

## 附录

### A. 推荐 Crate 版本

```toml
[dependencies]
# Web 框架
axum = "0.7"
tokio = { version = "1", features = ["full"] }
tower = "0.4"
tower-http = { version = "0.5", features = ["cors", "trace"] }

# ORM
sea-orm = { version = "0.12", features = ["sqlx-postgres", "runtime-tokio-native-tls"] }

# 异步 trait
async-trait = "0.1"

# HTTP 客户端 (用于 Tenant SDK)
reqwest = { version = "0.11", features = ["json"] }

# 序列化/反序列化
serde = { version = "1", features = ["derive"] }
serde_json = "1"

# 配置
config = "0.14"
dotenvy = "0.15"

# 错误处理
thiserror = "1"
anyhow = "1"

# 校验
validator = { version = "0.16", features = ["derive"] }

# 日志
tracing = "0.1"
tracing-subscriber = { version = "0.3", features = ["env-filter"] }

# 其他
chrono = { version = "0.4", features = ["serde"] }
rust_decimal = { version = "1", features = ["serde"] }
uuid = { version = "1", features = ["serde", "v4"] }
```

### B. 开发工具

```bash
# 安装 sea-orm-cli 用于迁移
cargo install sea-orm-cli

# 安装 cargo-watch 用于开发热重载
cargo install cargo-watch

# 安装 cargo-tarpaulin 用于测试覆盖率
cargo install cargo-tarpaulin
```

---

**最后更新**: 2026-02-26  
**版本**: v1.0  
**技术栈**: Rust + Axum + Sea-ORM

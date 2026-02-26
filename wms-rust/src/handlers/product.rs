//! 产品处理器
//!
//! 处理产品相关的HTTP请求

use axum::{
    extract::{Extension, Path, Query, State},
    routing::{delete, get, post, put},
    Json, Router,
};
use sea_orm::DatabaseConnection;
use std::sync::Arc;
use validator::Validate;

use crate::{
    domain::tenant::TenantContextExtractor,
    error::{AppError, Result},
    infrastructure::{
        app_state::AppState,
        tenant_sdk::TenantSdk,
    },
    models::dto::{
        ApiResponse, CreateProductRequest, PageResponse, ProductResponse, QueryProductRequest,
        UpdateProductRequest,
    },
    services::product_service::ProductService,
};

/// 创建产品路由
pub fn product_routes<S: TenantSdk + 'static>() -> Router<Arc<AppState<S>>> {
    Router::new()
        .route("/api/products", get(list_products).post(create_product))
        .route(
            "/api/products/:id",
            get(get_product).put(update_product).delete(delete_product),
        )
}

/// 获取产品列表
///
/// GET /api/products
async fn list_products<S: TenantSdk>(
    State(state): State<Arc<AppState<S>>>,
    Extension(TenantContextExtractor(tenant)): Extension<TenantContextExtractor>,
    Query(query): Query<QueryProductRequest>,
) -> Result<Json<ApiResponse<PageResponse<ProductResponse>>>> {
    let service = ProductService::new(state.db.clone());
    let products = service.list_products(&tenant, query).await?;
    Ok(Json(ApiResponse::success(products)))
}

/// 获取单个产品
///
/// GET /api/products/:id
async fn get_product<S: TenantSdk>(
    State(state): State<Arc<AppState<S>>>,
    Extension(TenantContextExtractor(tenant)): Extension<TenantContextExtractor>,
    Path(id): Path<i64>,
) -> Result<Json<ApiResponse<ProductResponse>>> {
    let service = ProductService::new(state.db.clone());
    let product = service.get_product(&tenant, id).await?;
    Ok(Json(ApiResponse::success(product)))
}

/// 创建产品
///
/// POST /api/products
async fn create_product<S: TenantSdk>(
    State(state): State<Arc<AppState<S>>>,
    Extension(TenantContextExtractor(tenant)): Extension<TenantContextExtractor>,
    Json(req): Json<CreateProductRequest>,
) -> Result<Json<ApiResponse<ProductResponse>>> {
    // 验证请求
    req.validate()
        .map_err(|e| AppError::Validation(e.to_string()))?;

    let service = ProductService::new(state.db.clone());
    let product = service.create_product(&tenant, req).await?;
    Ok(Json(ApiResponse::success(product)))
}

/// 更新产品
///
/// PUT /api/products/:id
async fn update_product<S: TenantSdk>(
    State(state): State<Arc<AppState<S>>>,
    Extension(TenantContextExtractor(tenant)): Extension<TenantContextExtractor>,
    Path(id): Path<i64>,
    Json(req): Json<UpdateProductRequest>,
) -> Result<Json<ApiResponse<ProductResponse>>> {
    // 验证请求
    if let Err(e) = req.validate() {
        return Err(AppError::Validation(e.to_string()));
    }

    let service = ProductService::new(state.db.clone());
    let product = service.update_product(&tenant, id, req).await?;
    Ok(Json(ApiResponse::success(product)))
}

/// 删除产品
///
/// DELETE /api/products/:id
async fn delete_product<S: TenantSdk>(
    State(state): State<Arc<AppState<S>>>,
    Extension(TenantContextExtractor(tenant)): Extension<TenantContextExtractor>,
    Path(id): Path<i64>,
) -> Result<Json<ApiResponse<()>>> {
    let service = ProductService::new(state.db.clone());
    service.delete_product(&tenant, id).await?;
    Ok(Json(ApiResponse::success(())))
}

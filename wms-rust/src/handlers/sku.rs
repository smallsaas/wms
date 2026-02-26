//! SKU 处理器
//!
//! 处理SKU相关的HTTP请求

use axum::{
    extract::{Extension, Path, Query, State},
    routing::{delete, get, post, put},
    Json, Router,
};
use std::sync::Arc;
use validator::Validate;

use crate::{
    domain::tenant::TenantContextExtractor,
    error::{AppError, Result},
    infrastructure::{app_state::AppState, tenant_sdk::TenantSdk},
    models::dto::{
        ApiResponse, CreateSkuRequest, PageResponse, QuerySkuRequest, SkuResponse,
        SkuDetailResponse, UpdateSkuRequest,
    },
    services::sku_service::SkuService,
};

/// 创建SKU路由
pub fn sku_routes<S: TenantSdk + 'static>() -> Router<Arc<AppState<S>>> {
    Router::new()
        .route("/api/skus", get(list_skus).post(create_sku))
        .route("/api/skus/:id", get(get_sku).put(update_sku).delete(delete_sku))
}

/// 获取SKU列表
///
/// GET /api/skus
async fn list_skus<S: TenantSdk>(
    State(state): State<Arc<AppState<S>>>,
    Extension(TenantContextExtractor(tenant)): Extension<TenantContextExtractor>,
    Query(query): Query<QuerySkuRequest>,
) -> Result<Json<ApiResponse<PageResponse<SkuResponse>>>> {
    let service = SkuService::new(state.db.clone());
    let skus = service.list_skus(&tenant, query).await?;
    Ok(Json(ApiResponse::success(skus)))
}

/// 获取单个SKU
///
/// GET /api/skus/:id
async fn get_sku<S: TenantSdk>(
    State(state): State<Arc<AppState<S>>>,
    Extension(TenantContextExtractor(tenant)): Extension<TenantContextExtractor>,
    Path(id): Path<i64>,
) -> Result<Json<ApiResponse<SkuDetailResponse>>> {
    let service = SkuService::new(state.db.clone());
    let sku = service.get_sku(&tenant, id).await?;
    Ok(Json(ApiResponse::success(sku)))
}

/// 创建SKU
///
/// POST /api/skus
async fn create_sku<S: TenantSdk>(
    State(state): State<Arc<AppState<S>>>,
    Extension(TenantContextExtractor(tenant)): Extension<TenantContextExtractor>,
    Json(req): Json<CreateSkuRequest>,
) -> Result<Json<ApiResponse<SkuResponse>>> {
    // 验证请求
    req.validate()
        .map_err(|e| AppError::Validation(e.to_string()))?;

    let service = SkuService::new(state.db.clone());
    let sku = service.create_sku(&tenant, req).await?;
    Ok(Json(ApiResponse::success(sku)))
}

/// 更新SKU
///
/// PUT /api/skus/:id
async fn update_sku<S: TenantSdk>(
    State(state): State<Arc<AppState<S>>>,
    Extension(TenantContextExtractor(tenant)): Extension<TenantContextExtractor>,
    Path(id): Path<i64>,
    Json(req): Json<UpdateSkuRequest>,
) -> Result<Json<ApiResponse<SkuResponse>>> {
    // 验证请求
    if let Err(e) = req.validate() {
        return Err(AppError::Validation(e.to_string()));
    }

    let service = SkuService::new(state.db.clone());
    let sku = service.update_sku(&tenant, id, req).await?;
    Ok(Json(ApiResponse::success(sku)))
}

/// 删除SKU
///
/// DELETE /api/skus/:id
async fn delete_sku<S: TenantSdk>(
    State(state): State<Arc<AppState<S>>>,
    Extension(TenantContextExtractor(tenant)): Extension<TenantContextExtractor>,
    Path(id): Path<i64>,
) -> Result<Json<ApiResponse<()>>> {
    let service = SkuService::new(state.db.clone());
    service.delete_sku(&tenant, id).await?;
    Ok(Json(ApiResponse::success(())))
}

//! 出入库单据处理器
//!
//! 处理入库单、出库单、盘点单的HTTP请求

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
        AdjustCheckRequest, ApiResponse, CheckResponse, CreateCheckRequest,
        CreateStorageInRequest, CreateStorageOutRequest, PageResponse,
        QueryCheckRequest, QueryStorageInRequest, QueryStorageOutRequest,
        StorageInResponse, StorageOutResponse, UpdateCheckRequest,
        UpdateStorageInRequest,
    },
    services::storage_service::StorageService,
};

/// 创建出入库路由
pub fn storage_routes<S: TenantSdk + 'static>() -> Router<Arc<AppState<S>>> {
    Router::new()
        // 入库单
        .route("/api/storage/in", get(list_storage_in).post(create_storage_in))
        .route(
            "/api/storage/in/:id",
            get(get_storage_in)
                .put(update_storage_in)
                .delete(delete_storage_in),
        )
        .route("/api/storage/in/:id/approve", post(approve_storage_in))
        .route("/api/storage/in/:id/complete", post(complete_storage_in))
        // 出库单
        .route("/api/storage/out", get(list_storage_out).post(create_storage_out))
        .route(
            "/api/storage/out/:id",
            get(get_storage_out)
                .put(update_storage_out)
                .delete(delete_storage_out),
        )
        .route("/api/storage/out/:id/approve", post(approve_storage_out))
        .route("/api/storage/out/:id/complete", post(complete_storage_out))
        // 盘点单
        .route("/api/storage/check", get(list_checks).post(create_check))
        .route(
            "/api/storage/check/:id",
            get(get_check).put(update_check).delete(delete_check),
        )
        .route("/api/storage/check/:id/adjust", post(adjust_check))
}

// ==================== 入库单 ====================

/// 查询入库单列表
async fn list_storage_in<S: TenantSdk>(
    State(state): State<Arc<AppState<S>>>,
    Extension(TenantContextExtractor(tenant)): Extension<TenantContextExtractor>,
    Query(query): Query<QueryStorageInRequest>,
) -> Result<Json<ApiResponse<PageResponse<StorageInResponse>>>> {
    let service = StorageService::new(state.db.clone());
    let result = service.list_storage_in(&tenant, query).await?;
    Ok(Json(ApiResponse::success(result)))
}

/// 获取入库单详情
async fn get_storage_in<S: TenantSdk>(
    State(state): State<Arc<AppState<S>>>,
    Extension(TenantContextExtractor(tenant)): Extension<TenantContextExtractor>,
    Path(id): Path<i64>,
) -> Result<Json<ApiResponse<StorageInResponse>>> {
    let service = StorageService::new(state.db.clone());
    let result = service.get_storage_in(&tenant, id).await?;
    Ok(Json(ApiResponse::success(result)))
}

/// 创建入库单
async fn create_storage_in<S: TenantSdk>(
    State(state): State<Arc<AppState<S>>>,
    Extension(TenantContextExtractor(tenant)): Extension<TenantContextExtractor>,
    Json(req): Json<CreateStorageInRequest>,
) -> Result<Json<ApiResponse<StorageInResponse>>> {
    req.validate()
        .map_err(|e| AppError::Validation(e.to_string()))?;

    let service = StorageService::new(state.db.clone());
    let result = service.create_storage_in(&tenant, req).await?;
    Ok(Json(ApiResponse::success(result)))
}

/// 更新入库单
async fn update_storage_in<S: TenantSdk>(
    State(_state): State<Arc<AppState<S>>>,
    Extension(_tenant): Extension<TenantContextExtractor>,
    Path(_id): Path<i64>,
    Json(_req): Json<UpdateStorageInRequest>,
) -> Result<Json<ApiResponse<StorageInResponse>>> {
    // TODO: 实现入库单更新
    Err(AppError::NotFound)
}

/// 删除入库单
async fn delete_storage_in<S: TenantSdk>(
    State(_state): State<Arc<AppState<S>>>,
    Extension(_tenant): Extension<TenantContextExtractor>,
    Path(_id): Path<i64>,
) -> Result<Json<ApiResponse<()>>> {
    // TODO: 实现入库单删除
    Err(AppError::NotFound)
}

/// 审核入库单
async fn approve_storage_in<S: TenantSdk>(
    State(state): State<Arc<AppState<S>>>,
    Extension(TenantContextExtractor(tenant)): Extension<TenantContextExtractor>,
    Path(id): Path<i64>,
) -> Result<Json<ApiResponse<StorageInResponse>>> {
    let service = StorageService::new(state.db.clone());
    let result = service.approve_storage_in(&tenant, id).await?;
    Ok(Json(ApiResponse::success(result)))
}

/// 完成入库单（确认入库）
async fn complete_storage_in<S: TenantSdk>(
    State(state): State<Arc<AppState<S>>>,
    Extension(TenantContextExtractor(tenant)): Extension<TenantContextExtractor>,
    Path(id): Path<i64>,
) -> Result<Json<ApiResponse<StorageInResponse>>> {
    let service = StorageService::new(state.db.clone());
    let result = service.complete_storage_in(&tenant, id).await?;
    Ok(Json(ApiResponse::success(result)))
}

// ==================== 出库单 ====================

/// 查询出库单列表
async fn list_storage_out<S: TenantSdk>(
    State(_state): State<Arc<AppState<S>>>,
    Extension(_tenant): Extension<TenantContextExtractor>,
    Query(_query): Query<QueryStorageOutRequest>,
) -> Result<Json<ApiResponse<PageResponse<StorageOutResponse>>>> {
    // TODO: 实现出库单列表查询
    Err(AppError::NotFound)
}

/// 获取出库单详情
async fn get_storage_out<S: TenantSdk>(
    State(state): State<Arc<AppState<S>>>,
    Extension(TenantContextExtractor(tenant)): Extension<TenantContextExtractor>,
    Path(id): Path<i64>,
) -> Result<Json<ApiResponse<StorageOutResponse>>> {
    let service = StorageService::new(state.db.clone());
    let result = service.get_storage_out(&tenant, id).await?;
    Ok(Json(ApiResponse::success(result)))
}

/// 创建出库单
async fn create_storage_out<S: TenantSdk>(
    State(state): State<Arc<AppState<S>>>,
    Extension(TenantContextExtractor(tenant)): Extension<TenantContextExtractor>,
    Json(req): Json<CreateStorageOutRequest>,
) -> Result<Json<ApiResponse<StorageOutResponse>>> {
    req.validate()
        .map_err(|e| AppError::Validation(e.to_string()))?;

    let service = StorageService::new(state.db.clone());
    let result = service.create_storage_out(&tenant, req).await?;
    Ok(Json(ApiResponse::success(result)))
}

/// 更新出库单
async fn update_storage_out<S: TenantSdk>(
    State(_state): State<Arc<AppState<S>>>,
    Extension(_tenant): Extension<TenantContextExtractor>,
    Path(_id): Path<i64>,
) -> Result<Json<ApiResponse<StorageOutResponse>>> {
    // TODO: 实现出库单更新
    Err(AppError::NotFound)
}

/// 删除出库单
async fn delete_storage_out<S: TenantSdk>(
    State(_state): State<Arc<AppState<S>>>,
    Extension(_tenant): Extension<TenantContextExtractor>,
    Path(_id): Path<i64>,
) -> Result<Json<ApiResponse<()>>> {
    // TODO: 实现出库单删除
    Err(AppError::NotFound)
}

/// 审核出库单
async fn approve_storage_out<S: TenantSdk>(
    State(_state): State<Arc<AppState<S>>>,
    Extension(_tenant): Extension<TenantContextExtractor>,
    Path(_id): Path<i64>,
) -> Result<Json<ApiResponse<StorageOutResponse>>> {
    // TODO: 实现出库单审核
    Err(AppError::NotFound)
}

/// 完成出库单（确认出库）
async fn complete_storage_out<S: TenantSdk>(
    State(state): State<Arc<AppState<S>>>,
    Extension(TenantContextExtractor(tenant)): Extension<TenantContextExtractor>,
    Path(id): Path<i64>,
) -> Result<Json<ApiResponse<StorageOutResponse>>> {
    let service = StorageService::new(state.db.clone());
    let result = service.complete_storage_out(&tenant, id).await?;
    Ok(Json(ApiResponse::success(result)))
}

// ==================== 盘点单 ====================

/// 查询盘点单列表
async fn list_checks<S: TenantSdk>(
    State(_state): State<Arc<AppState<S>>>,
    Extension(_tenant): Extension<TenantContextExtractor>,
    Query(_query): Query<QueryCheckRequest>,
) -> Result<Json<ApiResponse<PageResponse<CheckResponse>>>> {
    // TODO: 实现盘点单列表查询
    Err(AppError::NotFound)
}

/// 获取盘点单详情
async fn get_check<S: TenantSdk>(
    State(state): State<Arc<AppState<S>>>,
    Extension(TenantContextExtractor(tenant)): Extension<TenantContextExtractor>,
    Path(id): Path<i64>,
) -> Result<Json<ApiResponse<CheckResponse>>> {
    let service = StorageService::new(state.db.clone());
    let result = service.get_check(&tenant, id).await?;
    Ok(Json(ApiResponse::success(result)))
}

/// 创建盘点单
async fn create_check<S: TenantSdk>(
    State(state): State<Arc<AppState<S>>>,
    Extension(TenantContextExtractor(tenant)): Extension<TenantContextExtractor>,
    Json(req): Json<CreateCheckRequest>,
) -> Result<Json<ApiResponse<CheckResponse>>> {
    let service = StorageService::new(state.db.clone());
    let result = service.create_check(&tenant, req).await?;
    Ok(Json(ApiResponse::success(result)))
}

/// 更新盘点单
async fn update_check<S: TenantSdk>(
    State(_state): State<Arc<AppState<S>>>,
    Extension(_tenant): Extension<TenantContextExtractor>,
    Path(_id): Path<i64>,
    Json(_req): Json<UpdateCheckRequest>,
) -> Result<Json<ApiResponse<CheckResponse>>> {
    // TODO: 实现盘点单更新
    Err(AppError::NotFound)
}

/// 删除盘点单
async fn delete_check<S: TenantSdk>(
    State(_state): State<Arc<AppState<S>>>,
    Extension(_tenant): Extension<TenantContextExtractor>,
    Path(_id): Path<i64>,
) -> Result<Json<ApiResponse<()>>> {
    // TODO: 实现盘点单删除
    Err(AppError::NotFound)
}

/// 盘点调整
async fn adjust_check<S: TenantSdk>(
    State(_state): State<Arc<AppState<S>>>,
    Extension(_tenant): Extension<TenantContextExtractor>,
    Path(_id): Path<i64>,
    Json(_req): Json<AdjustCheckRequest>,
) -> Result<Json<ApiResponse<CheckResponse>>> {
    // TODO: 实现盘点调整
    Err(AppError::NotFound)
}

//! 仓库处理器
//!
//! 处理仓库、储位、库存相关的HTTP请求

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
        AdjustInventoryRequest, ApiResponse, CreateSlotRequest, CreateWarehouseRequest,
        InventoryResponse, InventoryWarning, PageResponse, QueryInventoryRequest,
        QueryWarehouseRequest, SlotResponse, UpdateSlotRequest, UpdateWarehouseRequest,
        WarehouseResponse,
    },
    services::warehouse_service::WarehouseService,
};

/// 创建仓库路由
pub fn warehouse_routes<S: TenantSdk + 'static>() -> Router<Arc<AppState<S>>> {
    Router::new()
        // 仓库管理
        .route("/api/warehouses", get(list_warehouses).post(create_warehouse))
        .route(
            "/api/warehouses/:id",
            get(get_warehouse).put(update_warehouse).delete(delete_warehouse),
        )
        // 储位管理
        .route("/api/warehouses/:id/slots", get(list_slots).post(create_slot))
        .route("/api/slots/:id", put(update_slot).delete(delete_slot))
        // 库存管理
        .route("/api/inventory", get(list_inventory).post(adjust_inventory))
        .route("/api/inventory/warnings", get(get_inventory_warnings))
}

// ==================== 仓库管理 ====================

/// 获取仓库列表
async fn list_warehouses<S: TenantSdk>(
    State(state): State<Arc<AppState<S>>>,
    Extension(TenantContextExtractor(tenant)): Extension<TenantContextExtractor>,
    Query(query): Query<QueryWarehouseRequest>,
) -> Result<Json<ApiResponse<PageResponse<WarehouseResponse>>>> {
    let service = WarehouseService::new(state.db.clone());
    let warehouses = service.list_warehouses(&tenant, query).await?;
    Ok(Json(ApiResponse::success(warehouses)))
}

/// 获取单个仓库
async fn get_warehouse<S: TenantSdk>(
    State(state): State<Arc<AppState<S>>>,
    Extension(TenantContextExtractor(tenant)): Extension<TenantContextExtractor>,
    Path(id): Path<i64>,
) -> Result<Json<ApiResponse<WarehouseResponse>>> {
    let service = WarehouseService::new(state.db.clone());
    let warehouse = service.get_warehouse(&tenant, id).await?;
    Ok(Json(ApiResponse::success(warehouse)))
}

/// 创建仓库
async fn create_warehouse<S: TenantSdk>(
    State(state): State<Arc<AppState<S>>>,
    Extension(TenantContextExtractor(tenant)): Extension<TenantContextExtractor>,
    Json(req): Json<CreateWarehouseRequest>,
) -> Result<Json<ApiResponse<WarehouseResponse>>> {
    req.validate()
        .map_err(|e| AppError::Validation(e.to_string()))?;

    let service = WarehouseService::new(state.db.clone());
    let warehouse = service.create_warehouse(&tenant, req).await?;
    Ok(Json(ApiResponse::success(warehouse)))
}

/// 更新仓库
async fn update_warehouse<S: TenantSdk>(
    State(state): State<Arc<AppState<S>>>,
    Extension(TenantContextExtractor(tenant)): Extension<TenantContextExtractor>,
    Path(id): Path<i64>,
    Json(req): Json<UpdateWarehouseRequest>,
) -> Result<Json<ApiResponse<WarehouseResponse>>> {
    if let Err(e) = req.validate() {
        return Err(AppError::Validation(e.to_string()));
    }

    let service = WarehouseService::new(state.db.clone());
    let warehouse = service.update_warehouse(&tenant, id, req).await?;
    Ok(Json(ApiResponse::success(warehouse)))
}

/// 删除仓库
async fn delete_warehouse<S: TenantSdk>(
    State(state): State<Arc<AppState<S>>>,
    Extension(TenantContextExtractor(tenant)): Extension<TenantContextExtractor>,
    Path(id): Path<i64>,
) -> Result<Json<ApiResponse<()>>> {
    let service = WarehouseService::new(state.db.clone());
    service.delete_warehouse(&tenant, id).await?;
    Ok(Json(ApiResponse::success(())))
}

// ==================== 储位管理 ====================

/// 获取储位列表
async fn list_slots<S: TenantSdk>(
    State(state): State<Arc<AppState<S>>>,
    Extension(TenantContextExtractor(tenant)): Extension<TenantContextExtractor>,
    Path(warehouse_id): Path<i64>,
) -> Result<Json<ApiResponse<Vec<SlotResponse>>>> {
    let service = WarehouseService::new(state.db.clone());
    let slots = service.list_slots(&tenant, warehouse_id).await?;
    Ok(Json(ApiResponse::success(slots)))
}

/// 创建储位
async fn create_slot<S: TenantSdk>(
    State(state): State<Arc<AppState<S>>>,
    Extension(TenantContextExtractor(tenant)): Extension<TenantContextExtractor>,
    Path(warehouse_id): Path<i64>,
    Json(mut req): Json<CreateSlotRequest>,
) -> Result<Json<ApiResponse<SlotResponse>>> {
    req.warehouse_id = warehouse_id;
    req.validate()
        .map_err(|e| AppError::Validation(e.to_string()))?;

    let service = WarehouseService::new(state.db.clone());
    let slot = service.create_slot(&tenant, req).await?;
    Ok(Json(ApiResponse::success(slot)))
}

/// 更新储位
async fn update_slot<S: TenantSdk>(
    State(_state): State<Arc<AppState<S>>>,
    Extension(_tenant): Extension<TenantContextExtractor>,
    Path(_id): Path<i64>,
    Json(_req): Json<UpdateSlotRequest>,
) -> Result<Json<ApiResponse<SlotResponse>>> {
    // TODO: 实现储位更新
    Err(AppError::NotFound)
}

/// 删除储位
async fn delete_slot<S: TenantSdk>(
    State(_state): State<Arc<AppState<S>>>,
    Extension(_tenant): Extension<TenantContextExtractor>,
    Path(_id): Path<i64>,
) -> Result<Json<ApiResponse<()>>> {
    // TODO: 实现储位删除
    Err(AppError::NotFound)
}

// ==================== 库存管理 ====================

/// 获取库存列表
async fn list_inventory<S: TenantSdk>(
    State(state): State<Arc<AppState<S>>>,
    Extension(TenantContextExtractor(tenant)): Extension<TenantContextExtractor>,
    Query(query): Query<QueryInventoryRequest>,
) -> Result<Json<ApiResponse<PageResponse<InventoryResponse>>>> {
    let service = WarehouseService::new(state.db.clone());
    let inventory = service.list_inventory(&tenant, query).await?;
    Ok(Json(ApiResponse::success(inventory)))
}

/// 调整库存
async fn adjust_inventory<S: TenantSdk>(
    State(state): State<Arc<AppState<S>>>,
    Extension(TenantContextExtractor(tenant)): Extension<TenantContextExtractor>,
    Json(req): Json<AdjustInventoryRequest>,
) -> Result<Json<ApiResponse<InventoryResponse>>> {
    req.validate()
        .map_err(|e| AppError::Validation(e.to_string()))?;

    let service = WarehouseService::new(state.db.clone());
    let inventory = service.adjust_inventory(&tenant, req).await?;
    Ok(Json(ApiResponse::success(inventory)))
}

/// 获取库存预警
async fn get_inventory_warnings<S: TenantSdk>(
    State(state): State<Arc<AppState<S>>>,
    Extension(TenantContextExtractor(tenant)): Extension<TenantContextExtractor>,
) -> Result<Json<ApiResponse<Vec<InventoryWarning>>>> {
    let service = WarehouseService::new(state.db.clone());
    let warnings = service.get_inventory_warnings(&tenant).await?;
    Ok(Json(ApiResponse::success(warnings)))
}

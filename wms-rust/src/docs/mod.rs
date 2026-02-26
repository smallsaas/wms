//! OpenAPI 文档模块
//!
//! 提供 API 文档和 Swagger UI

use utoipa::OpenApi;

/// API 文档
#[derive(OpenApi)]
#[openapi(
    info(
        title = "WMS 仓库管理系统 API",
        description = "基于 Rust + Axum + Sea-ORM 的高性能仓库管理系统",
        version = "1.0.0",
        contact(name = "WMS Team")
    ),
    paths(
        // 健康检查
        crate::handlers::product::list_products,
        crate::handlers::product::get_product,
        crate::handlers::product::create_product,
        crate::handlers::product::update_product,
        crate::handlers::product::delete_product,
        // SKU
        crate::handlers::sku::list_skus,
        crate::handlers::sku::get_sku,
        crate::handlers::sku::create_sku,
        crate::handlers::sku::update_sku,
        crate::handlers::sku::delete_sku,
        // Warehouse
        crate::handlers::warehouse::list_warehouses,
        crate::handlers::warehouse::get_warehouse,
        crate::handlers::warehouse::create_warehouse,
        crate::handlers::warehouse::update_warehouse,
        crate::handlers::warehouse::delete_warehouse,
        crate::handlers::warehouse::list_inventory,
        crate::handlers::warehouse::adjust_inventory,
        crate::handlers::warehouse::get_inventory_warnings,
    ),
    components(
        schemas(
            // Product DTOs
            crate::models::dto::CreateProductRequest,
            crate::models::dto::UpdateProductRequest,
            crate::models::dto::QueryProductRequest,
            crate::models::dto::ProductResponse,
            // SKU DTOs
            crate::models::dto::CreateSkuRequest,
            crate::models::dto::UpdateSkuRequest,
            crate::models::dto::QuerySkuRequest,
            crate::models::dto::SkuResponse,
            crate::models::dto::SkuDetailResponse,
            // Warehouse DTOs
            crate::models::dto::CreateWarehouseRequest,
            crate::models::dto::UpdateWarehouseRequest,
            crate::models::dto::QueryWarehouseRequest,
            crate::models::dto::WarehouseResponse,
            crate::models::dto::CreateSlotRequest,
            crate::models::dto::SlotResponse,
            crate::models::dto::QueryInventoryRequest,
            crate::models::dto::InventoryResponse,
            crate::models::dto::AdjustInventoryRequest,
            crate::models::dto::InventoryWarning,
            // Common
            crate::models::dto::PageResponse<crate::models::dto::ProductResponse>,
            crate::models::dto::ApiResponse<crate::models::dto::ProductResponse>,
        )
    ),
    tags(
        (name = "健康检查", description = "服务健康状态检查"),
        (name = "产品管理", description = "产品信息的增删改查"),
        (name = "SKU管理", description = "SKU信息的增删改查"),
        (name = "仓库管理", description = "仓库、储位、库存管理"),
    )
)]
pub struct ApiDoc;

/// 生成 OpenAPI JSON
pub fn generate_openapi() -> String {
    ApiDoc::openapi().to_pretty_json().unwrap()
}

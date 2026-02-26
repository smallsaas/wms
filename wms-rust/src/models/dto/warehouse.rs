//! Warehouse DTOs

use rust_decimal::Decimal;
use serde::{Deserialize, Serialize};
use validator::Validate;

/// 创建仓库请求
#[derive(Debug, Clone, Serialize, Deserialize, Validate)]
pub struct CreateWarehouseRequest {
    /// 仓库编码
    #[validate(length(min = 1, max = 100, message = "仓库编码不能为空且不能超过100字符"))]
    pub warehouse_code: String,
    /// 仓库名称
    #[validate(length(min = 1, max = 255, message = "仓库名称不能为空且不能超过255字符"))]
    pub warehouse_name: String,
    /// 省市区
    pub warehouse_pcd: Option<String>,
    /// 详细地址
    pub warehouse_address: Option<String>,
    /// 负责人ID
    pub warehouse_charger: Option<i64>,
}

/// 更新仓库请求
#[derive(Debug, Clone, Serialize, Deserialize, Validate)]
pub struct UpdateWarehouseRequest {
    /// 仓库名称
    #[validate(length(max = 255, message = "仓库名称不能超过255字符"))]
    pub warehouse_name: Option<String>,
    /// 省市区
    pub warehouse_pcd: Option<String>,
    /// 详细地址
    pub warehouse_address: Option<String>,
    /// 负责人ID
    pub warehouse_charger: Option<i64>,
    /// 状态
    pub status: Option<String>,
}

/// 仓库查询请求
#[derive(Debug, Clone, Serialize, Deserialize)]
pub struct QueryWarehouseRequest {
    /// 关键字搜索
    pub keyword: Option<String>,
    /// 状态
    pub status: Option<String>,
    /// 页码
    pub page: Option<u64>,
    /// 每页大小
    pub page_size: Option<u64>,
}

impl QueryWarehouseRequest {
    /// 获取页码，默认为1
    pub fn page(&self) -> u64 {
        self.page.unwrap_or(1).max(1)
    }

    /// 获取每页大小，默认为20
    pub fn page_size(&self) -> u64 {
        self.page_size.unwrap_or(20).clamp(1, 100)
    }
}

/// 仓库响应
#[derive(Debug, Clone, Serialize, Deserialize)]
pub struct WarehouseResponse {
    pub id: i64,
    pub org_id: i64,
    pub warehouse_code: String,
    pub warehouse_name: String,
    pub warehouse_pcd: Option<String>,
    pub warehouse_address: Option<String>,
    pub warehouse_charger: Option<i64>,
    pub status: String,
    pub created_at: String,
    pub updated_at: String,
}

impl From<crate::models::entity::warehouse_entity::Model> for WarehouseResponse {
    fn from(model: crate::models::entity::warehouse_entity::Model) -> Self {
        Self {
            id: model.id,
            org_id: model.org_id,
            warehouse_code: model.warehouse_code,
            warehouse_name: model.warehouse_name,
            warehouse_pcd: model.warehouse_pcd,
            warehouse_address: model.warehouse_address,
            warehouse_charger: model.warehouse_charger,
            status: model.status,
            created_at: model.created_at.to_string(),
            updated_at: model.updated_at.to_string(),
        }
    }
}

/// 创建储位请求
#[derive(Debug, Clone, Serialize, Deserialize, Validate)]
pub struct CreateSlotRequest {
    /// 仓库ID
    pub warehouse_id: i64,
    /// 储位编码
    #[validate(length(min = 1, max = 100, message = "储位编码不能为空且不能超过100字符"))]
    pub slot_code: String,
    /// 储位名称
    #[validate(length(min = 1, max = 255, message = "储位名称不能为空且不能超过255字符"))]
    pub slot_name: String,
    /// 储位说明
    pub slot_note: Option<String>,
}

/// 更新储位请求
#[derive(Debug, Clone, Serialize, Deserialize, Validate)]
pub struct UpdateSlotRequest {
    /// 储位名称
    #[validate(length(max = 255, message = "储位名称不能超过255字符"))]
    pub slot_name: Option<String>,
    /// 储位说明
    pub slot_note: Option<String>,
    /// 状态
    pub status: Option<String>,
}

/// 储位响应
#[derive(Debug, Clone, Serialize, Deserialize)]
pub struct SlotResponse {
    pub id: i64,
    pub org_id: i64,
    pub warehouse_id: i64,
    pub slot_code: String,
    pub slot_name: String,
    pub slot_note: Option<String>,
    pub status: String,
    pub created_at: String,
    pub updated_at: String,
}

impl From<crate::models::entity::slot_entity::Model> for SlotResponse {
    fn from(model: crate::models::entity::slot_entity::Model) -> Self {
        Self {
            id: model.id,
            org_id: model.org_id,
            warehouse_id: model.warehouse_id,
            slot_code: model.slot_code,
            slot_name: model.slot_name,
            slot_note: model.slot_note,
            status: model.status,
            created_at: model.created_at.to_string(),
            updated_at: model.updated_at.to_string(),
        }
    }
}

/// 库存查询请求
#[derive(Debug, Clone, Serialize, Deserialize)]
pub struct QueryInventoryRequest {
    /// 仓库ID
    pub warehouse_id: Option<i64>,
    /// 储位ID
    pub slot_id: Option<i64>,
    /// SKU ID
    pub sku_id: Option<i64>,
    /// 页码
    pub page: Option<u64>,
    /// 每页大小
    pub page_size: Option<u64>,
}

impl QueryInventoryRequest {
    /// 获取页码，默认为1
    pub fn page(&self) -> u64 {
        self.page.unwrap_or(1).max(1)
    }

    /// 获取每页大小，默认为20
    pub fn page_size(&self) -> u64 {
        self.page_size.unwrap_or(20).clamp(1, 100)
    }
}

/// 库存响应
#[derive(Debug, Clone, Serialize, Deserialize)]
pub struct InventoryResponse {
    pub id: i64,
    pub org_id: i64,
    pub warehouse_id: i64,
    pub slot_id: Option<i64>,
    pub sku_id: i64,
    pub max_inventory: Option<i32>,
    pub min_inventory: Option<i32>,
    pub valid_sku: Option<i32>,
    pub advance_quantities: Option<i32>,
    pub transmit_quantities: Option<i32>,
    pub order_count: Option<i32>,
    pub created_at: String,
    pub updated_at: String,
}

impl From<crate::models::entity::inventory_entity::Model> for InventoryResponse {
    fn from(model: crate::models::entity::inventory_entity::Model) -> Self {
        Self {
            id: model.id,
            org_id: model.org_id,
            warehouse_id: model.warehouse_id,
            slot_id: model.slot_id,
            sku_id: model.sku_id,
            max_inventory: model.max_inventory,
            min_inventory: model.min_inventory,
            valid_sku: model.valid_sku,
            advance_quantities: model.advance_quantities,
            transmit_quantities: model.transmit_quantities,
            order_count: model.order_count,
            created_at: model.created_at.to_string(),
            updated_at: model.updated_at.to_string(),
        }
    }
}

/// 库存调整请求
#[derive(Debug, Clone, Serialize, Deserialize, Validate)]
pub struct AdjustInventoryRequest {
    /// 仓库ID
    pub warehouse_id: i64,
    /// 储位ID
    pub slot_id: Option<i64>,
    /// SKU ID
    pub sku_id: i64,
    /// 调整数量（正数增加，负数减少）
    pub adjust_quantity: i32,
    /// 调整原因
    pub reason: Option<String>,
}

/// 库存转移请求
#[derive(Debug, Clone, Serialize, Deserialize, Validate)]
pub struct TransferInventoryRequest {
    /// 源仓库ID
    pub from_warehouse_id: i64,
    /// 源储位ID
    pub from_slot_id: Option<i64>,
    /// 目标仓库ID
    pub to_warehouse_id: i64,
    /// 目标储位ID
    pub to_slot_id: Option<i64>,
    /// SKU ID
    pub sku_id: i64,
    /// 转移数量
    pub quantity: i32,
    /// 转移原因
    pub reason: Option<String>,
}

/// 库存预警响应
#[derive(Debug, Clone, Serialize, Deserialize)]
pub struct InventoryWarning {
    pub inventory_id: i64,
    pub warehouse_id: i64,
    pub slot_id: Option<i64>,
    pub sku_id: i64,
    pub current_stock: i32,
    pub min_inventory: i32,
    pub max_inventory: i32,
    pub warning_type: String, // "LOW" 或 "HIGH"
}

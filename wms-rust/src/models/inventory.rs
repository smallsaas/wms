//! 库存域模型定义

use chrono::{DateTime, Local};
use serde::{Deserialize, Serialize};

use super::base::{TenantBase, TimestampBase};

/// 库存实体
#[derive(Debug, Clone, Serialize, Deserialize)]
pub struct Inventory {
    pub id: i64,
    pub tenant: TenantBase,
    pub warehouse_id: i64,
    pub slot_id: Option<i64>,
    pub sku_id: i64,
    pub max_inventory: i32,
    pub min_inventory: i32,
    pub valid_sku: i32,
    pub advance_quantities: i32,
    pub transmit_quantities: i32,
    pub order_count: i32,
    pub timestamps: TimestampBase,
}

impl Inventory {
    /// 计算可用库存
    ///
    /// 可用库存 = 实际库存 - 预购量 - 订单占用量
    pub fn available_stock(&self) -> i32 {
        self.valid_sku - self.advance_quantities - self.order_count
    }

    /// 计算实际库存
    ///
    /// 实际库存 = 可用库存 + 在途量
    pub fn actual_stock(&self) -> i32 {
        self.valid_sku + self.transmit_quantities
    }

    /// 是否低于安全库存
    pub fn is_below_safety_stock(&self) -> bool {
        self.valid_sku < self.min_inventory
    }

    /// 是否超出最大库存
    pub fn is_above_max_stock(&self) -> bool {
        self.valid_sku > self.max_inventory && self.max_inventory > 0
    }

    /// 计算库存预警状态
    pub fn warning_status(&self) -> InventoryWarningStatus {
        if self.valid_sku <= 0 {
            InventoryWarningStatus::OutOfStock
        } else if self.valid_sku < self.min_inventory {
            InventoryWarningStatus::LowStock
        } else if self.max_inventory > 0 && self.valid_sku > self.max_inventory {
            InventoryWarningStatus::OverStock
        } else {
            InventoryWarningStatus::Normal
        }
    }

    /// 是否可以出库
    pub fn can_outbound(&self, quantity: i32) -> bool {
        self.available_stock() >= quantity
    }

    /// 增加库存
    pub fn increase(&mut self, quantity: i32) {
        self.valid_sku += quantity;
        self.timestamps.updated_at = Local::now();
    }

    /// 减少库存
    pub fn decrease(&mut self, quantity: i32) -> Result<(), InventoryError> {
        if self.valid_sku < quantity {
            return Err(InventoryError::InsufficientStock);
        }
        self.valid_sku -= quantity;
        self.timestamps.updated_at = Local::now();
        Ok(())
    }

    /// 预占库存
    pub fn reserve(&mut self, quantity: i32) -> Result<(), InventoryError> {
        if self.available_stock() < quantity {
            return Err(InventoryError::InsufficientStock);
        }
        self.advance_quantities += quantity;
        self.timestamps.updated_at = Local::now();
        Ok(())
    }

    /// 释放预占
    pub fn release(&mut self, quantity: i32) {
        self.advance_quantities = (self.advance_quantities - quantity).max(0);
        self.timestamps.updated_at = Local::now();
    }

    /// 确认出库（从预占转为实际扣减）
    pub fn confirm_outbound(&mut self, quantity: i32) -> Result<(), InventoryError> {
        if self.advance_quantities < quantity {
            return Err(InventoryError::InsufficientReservedStock);
        }
        self.advance_quantities -= quantity;
        self.valid_sku -= quantity;
        self.timestamps.updated_at = Local::now();
        Ok(())
    }
}

impl Default for Inventory {
    fn default() -> Self {
        Self {
            id: 0,
            tenant: TenantBase::default(),
            warehouse_id: 0,
            slot_id: None,
            sku_id: 0,
            max_inventory: 0,
            min_inventory: 0,
            valid_sku: 0,
            advance_quantities: 0,
            transmit_quantities: 0,
            order_count: 0,
            timestamps: TimestampBase::default(),
        }
    }
}

/// 库存预警状态
#[derive(Debug, Clone, Copy, PartialEq, Eq, Serialize, Deserialize)]
pub enum InventoryWarningStatus {
    /// 正常
    Normal,
    /// 低库存
    LowStock,
    /// 缺货
    OutOfStock,
    /// 超储
    OverStock,
}

impl ToString for InventoryWarningStatus {
    fn to_string(&self) -> String {
        match self {
            InventoryWarningStatus::Normal => "NORMAL".to_string(),
            InventoryWarningStatus::LowStock => "LOW_STOCK".to_string(),
            InventoryWarningStatus::OutOfStock => "OUT_OF_STOCK".to_string(),
            InventoryWarningStatus::OverStock => "OVER_STOCK".to_string(),
        }
    }
}

/// 库存错误类型
#[derive(Debug, Clone, Copy, PartialEq, Eq)]
pub enum InventoryError {
    InsufficientStock,
    InsufficientReservedStock,
    InvalidQuantity,
}

impl std::fmt::Display for InventoryError {
    fn fmt(&self, f: &mut std::fmt::Formatter<'_>) -> std::fmt::Result {
        match self {
            InventoryError::InsufficientStock => write!(f, "库存不足"),
            InventoryError::InsufficientReservedStock => write!(f, "预占库存不足"),
            InventoryError::InvalidQuantity => write!(f, "无效的库存数量"),
        }
    }
}

impl std::error::Error for InventoryError {}

// ==================== DTOs ====================

/// 库存查询条件
#[derive(Debug, Clone, Serialize, Deserialize)]
pub struct InventoryQuery {
    pub org_id: i64,
    pub warehouse_id: Option<i64>,
    pub slot_id: Option<i64>,
    pub sku_id: Option<i64>,
    pub warning_only: bool,
}

/// 库存调整请求
#[derive(Debug, Clone, Serialize, Deserialize)]
pub struct AdjustInventoryRequest {
    pub warehouse_id: i64,
    pub slot_id: Option<i64>,
    pub sku_id: i64,
    pub adjustment_quantity: i32,
    pub reason: String,
}

/// 库存转移请求
#[derive(Debug, Clone, Serialize, Deserialize)]
pub struct TransferInventoryRequest {
    pub from_warehouse_id: i64,
    pub from_slot_id: Option<i64>,
    pub to_warehouse_id: i64,
    pub to_slot_id: Option<i64>,
    pub sku_id: i64,
    pub quantity: i32,
}

/// 库存响应DTO
#[derive(Debug, Clone, Serialize, Deserialize)]
pub struct InventoryResponse {
    pub id: i64,
    pub warehouse_id: i64,
    pub warehouse_name: Option<String>,
    pub slot_id: Option<i64>,
    pub slot_name: Option<String>,
    pub sku_id: i64,
    pub sku_name: Option<String>,
    pub sku_code: Option<String>,
    pub max_inventory: i32,
    pub min_inventory: i32,
    pub valid_sku: i32,
    pub advance_quantities: i32,
    pub transmit_quantities: i32,
    pub order_count: i32,
    pub available_stock: i32,
    pub warning_status: String,
    pub updated_at: String,
}

impl From<&Inventory> for InventoryResponse {
    fn from(inv: &Inventory) -> Self {
        Self {
            id: inv.id,
            warehouse_id: inv.warehouse_id,
            warehouse_name: None,
            slot_id: inv.slot_id,
            slot_name: None,
            sku_id: inv.sku_id,
            sku_name: None,
            sku_code: None,
            max_inventory: inv.max_inventory,
            min_inventory: inv.min_inventory,
            valid_sku: inv.valid_sku,
            advance_quantities: inv.advance_quantities,
            transmit_quantities: inv.transmit_quantities,
            order_count: inv.order_count,
            available_stock: inv.available_stock(),
            warning_status: inv.warning_status().to_string(),
            updated_at: inv.timestamps.updated_at.to_rfc3339(),
        }
    }
}

/// 库存汇总DTO
#[derive(Debug, Clone, Serialize, Deserialize)]
pub struct InventorySummary {
    pub sku_id: i64,
    pub sku_name: String,
    pub sku_code: String,
    pub total_valid: i32,
    pub total_advance: i32,
    pub total_transmit: i32,
    pub total_available: i32,
    pub warehouse_count: i32,
}

/// 库存预警DTO
#[derive(Debug, Clone, Serialize, Deserialize)]
pub struct InventoryWarning {
    pub inventory_id: i64,
    pub warehouse_id: i64,
    pub warehouse_name: String,
    pub sku_id: i64,
    pub sku_name: String,
    pub sku_code: String,
    pub current_stock: i32,
    pub min_inventory: i32,
    pub warning_type: String,
    pub warning_message: String,
}

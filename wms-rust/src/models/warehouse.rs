//! 仓库域模型定义

use chrono::{DateTime, Local};
use serde::{Deserialize, Serialize};

use super::base::{EntityStatus, TenantBase, TimestampBase};

/// 仓库实体
#[derive(Debug, Clone, Serialize, Deserialize)]
pub struct Warehouse {
    pub id: i64,
    pub tenant: TenantBase,
    pub warehouse_code: String,
    pub warehouse_name: String,
    pub warehouse_pcd: Option<String>,
    pub warehouse_address: Option<String>,
    pub warehouse_charger: Option<i64>,
    pub status: EntityStatus,
    pub timestamps: TimestampBase,
}

impl Warehouse {
    /// 是否启用
    pub fn is_active(&self) -> bool {
        matches!(self.status, EntityStatus::Active)
    }

    /// 获取完整地址
    pub fn full_address(&self) -> String {
        match (&self.warehouse_pcd, &self.warehouse_address) {
            (Some(pcd), Some(addr)) => format!("{} {}", pcd, addr),
            (Some(pcd), None) => pcd.clone(),
            (None, Some(addr)) => addr.clone(),
            (None, None) => String::new(),
        }
    }
}

impl Default for Warehouse {
    fn default() -> Self {
        Self {
            id: 0,
            tenant: TenantBase::default(),
            warehouse_code: String::new(),
            warehouse_name: String::new(),
            warehouse_pcd: None,
            warehouse_address: None,
            warehouse_charger: None,
            status: EntityStatus::Active,
            timestamps: TimestampBase::default(),
        }
    }
}

/// 储位实体
#[derive(Debug, Clone, Serialize, Deserialize)]
pub struct WarehouseSlot {
    pub id: i64,
    pub tenant: TenantBase,
    pub slot_code: String,
    pub slot_name: String,
    pub warehouse_id: i64,
    pub slot_note: Option<String>,
    pub status: EntityStatus,
    pub timestamps: TimestampBase,
}

impl WarehouseSlot {
    /// 是否启用
    pub fn is_active(&self) -> bool {
        matches!(self.status, EntityStatus::Active)
    }
}

impl Default for WarehouseSlot {
    fn default() -> Self {
        Self {
            id: 0,
            tenant: TenantBase::default(),
            slot_code: String::new(),
            slot_name: String::new(),
            warehouse_id: 0,
            slot_note: None,
            status: EntityStatus::Active,
            timestamps: TimestampBase::default(),
        }
    }
}

// ==================== DTOs ====================

/// 创建仓库请求
#[derive(Debug, Clone, Serialize, Deserialize)]
pub struct CreateWarehouseRequest {
    pub warehouse_code: String,
    pub warehouse_name: String,
    pub warehouse_pcd: Option<String>,
    pub warehouse_address: Option<String>,
    pub warehouse_charger: Option<i64>,
}

/// 更新仓库请求
#[derive(Debug, Clone, Serialize, Deserialize)]
pub struct UpdateWarehouseRequest {
    pub warehouse_name: Option<String>,
    pub warehouse_pcd: Option<String>,
    pub warehouse_address: Option<String>,
    pub warehouse_charger: Option<i64>,
    pub status: Option<EntityStatus>,
}

/// 创建储位请求
#[derive(Debug, Clone, Serialize, Deserialize)]
pub struct CreateSlotRequest {
    pub slot_code: String,
    pub slot_name: String,
    pub warehouse_id: i64,
    pub slot_note: Option<String>,
}

/// 仓库查询条件
#[derive(Debug, Clone, Serialize, Deserialize)]
pub struct WarehouseQuery {
    pub org_id: i64,
    pub keyword: Option<String>,
    pub status: Option<EntityStatus>,
}

/// 储位查询条件
#[derive(Debug, Clone, Serialize, Deserialize)]
pub struct SlotQuery {
    pub org_id: i64,
    pub warehouse_id: i64,
    pub keyword: Option<String>,
    pub status: Option<EntityStatus>,
}

/// 仓库响应DTO
#[derive(Debug, Clone, Serialize, Deserialize)]
pub struct WarehouseResponse {
    pub id: i64,
    pub warehouse_code: String,
    pub warehouse_name: String,
    pub warehouse_pcd: Option<String>,
    pub warehouse_address: Option<String>,
    pub status: String,
    pub created_at: String,
}

impl From<&Warehouse> for WarehouseResponse {
    fn from(w: &Warehouse) -> Self {
        Self {
            id: w.id,
            warehouse_code: w.warehouse_code.clone(),
            warehouse_name: w.warehouse_name.clone(),
            warehouse_pcd: w.warehouse_pcd.clone(),
            warehouse_address: w.warehouse_address.clone(),
            status: w.status.to_string(),
            created_at: w.timestamps.created_at.to_rfc3339(),
        }
    }
}

/// 储位响应DTO
#[derive(Debug, Clone, Serialize, Deserialize)]
pub struct SlotResponse {
    pub id: i64,
    pub slot_code: String,
    pub slot_name: String,
    pub warehouse_id: i64,
    pub warehouse_name: Option<String>,
    pub slot_note: Option<String>,
    pub status: String,
    pub created_at: String,
}

impl From<&WarehouseSlot> for SlotResponse {
    fn from(s: &WarehouseSlot) -> Self {
        Self {
            id: s.id,
            slot_code: s.slot_code.clone(),
            slot_name: s.slot_name.clone(),
            warehouse_id: s.warehouse_id,
            warehouse_name: None,
            slot_note: s.slot_note.clone(),
            status: s.status.to_string(),
            created_at: s.timestamps.created_at.to_rfc3339(),
        }
    }
}

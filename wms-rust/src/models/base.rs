//! 基础模型定义
//!
//! 包含所有实体共享的基础字段和 trait

use chrono::{DateTime, Local};
use serde::{Deserialize, Serialize};

/// 多租户基础字段
///
/// 所有业务实体都需要包含这些字段用于数据隔离
#[derive(Debug, Clone, Serialize, Deserialize)]
pub struct TenantBase {
    /// 组织ID，用于数据隔离
    pub org_id: i64,
    /// 组织标识，参考 Docker tag 设计
    pub org_tag: Option<String>,
}

impl Default for TenantBase {
    fn default() -> Self {
        Self {
            org_id: 0,
            org_tag: None,
        }
    }
}

/// 时间戳基础字段
#[derive(Debug, Clone, Serialize, Deserialize)]
pub struct TimestampBase {
    /// 创建时间
    pub created_at: DateTime<Local>,
    /// 更新时间
    pub updated_at: DateTime<Local>,
}

impl Default for TimestampBase {
    fn default() -> Self {
        let now = Local::now();
        Self {
            created_at: now,
            updated_at: now,
        }
    }
}

/// 操作人基础字段
#[derive(Debug, Clone, Serialize, Deserialize)]
pub struct OperatorBase {
    /// 制单人ID
    pub originator_id: Option<i64>,
    /// 制单人名称
    pub originator_name: Option<String>,
    /// 操作人
    pub transaction_by: Option<String>,
    /// 操作时间
    pub transaction_time: Option<DateTime<Local>>,
}

/// 单据状态枚举
#[derive(Debug, Clone, Copy, PartialEq, Eq, Serialize, Deserialize)]
pub enum DocumentStatus {
    /// 草稿
    Draft,
    /// 待审核
    Pending,
    /// 已审核
    Approved,
    /// 部分完成
    Partial,
    /// 已完成
    Completed,
    /// 已取消
    Cancelled,
}

impl Default for DocumentStatus {
    fn default() -> Self {
        DocumentStatus::Draft
    }
}

impl ToString for DocumentStatus {
    fn to_string(&self) -> String {
        match self {
            DocumentStatus::Draft => "DRAFT".to_string(),
            DocumentStatus::Pending => "PENDING".to_string(),
            DocumentStatus::Approved => "APPROVED".to_string(),
            DocumentStatus::Partial => "PARTIAL".to_string(),
            DocumentStatus::Completed => "COMPLETED".to_string(),
            DocumentStatus::Cancelled => "CANCELLED".to_string(),
        }
    }
}

impl From<&str> for DocumentStatus {
    fn from(s: &str) -> Self {
        match s.to_uppercase().as_str() {
            "DRAFT" => DocumentStatus::Draft,
            "PENDING" => DocumentStatus::Pending,
            "APPROVED" => DocumentStatus::Approved,
            "PARTIAL" => DocumentStatus::Partial,
            "COMPLETED" => DocumentStatus::Completed,
            "CANCELLED" => DocumentStatus::Cancelled,
            _ => DocumentStatus::Draft,
        }
    }
}

/// 实体状态枚举
#[derive(Debug, Clone, Copy, PartialEq, Eq, Serialize, Deserialize)]
pub enum EntityStatus {
    /// 启用
    Active,
    /// 禁用
    Inactive,
}

impl Default for EntityStatus {
    fn default() -> Self {
        EntityStatus::Active
    }
}

impl ToString for EntityStatus {
    fn to_string(&self) -> String {
        match self {
            EntityStatus::Active => "ACTIVE".to_string(),
            EntityStatus::Inactive => "INACTIVE".to_string(),
        }
    }
}

impl From<&str> for EntityStatus {
    fn from(s: &str) -> Self {
        match s.to_uppercase().as_str() {
            "ACTIVE" => EntityStatus::Active,
            "INACTIVE" => EntityStatus::Inactive,
            _ => EntityStatus::Active,
        }
    }
}

/// 入库类型枚举
#[derive(Debug, Clone, Copy, PartialEq, Eq, Serialize, Deserialize)]
pub enum StorageInType {
    /// 采购入库
    Procurement,
    /// 退货入库
    Return,
    /// 调拨入库
    Transfer,
    /// 调整入库
    Adjust,
}

impl Default for StorageInType {
    fn default() -> Self {
        StorageInType::Procurement
    }
}

impl ToString for StorageInType {
    fn to_string(&self) -> String {
        match self {
            StorageInType::Procurement => "PROCUREMENT".to_string(),
            StorageInType::Return => "RETURN".to_string(),
            StorageInType::Transfer => "TRANSFER".to_string(),
            StorageInType::Adjust => "ADJUST".to_string(),
        }
    }
}

/// 出库类型枚举
#[derive(Debug, Clone, Copy, PartialEq, Eq, Serialize, Deserialize)]
pub enum StorageOutType {
    /// 销售出库
    Sales,
    /// 退货出库
    Return,
    /// 调拨出库
    Transfer,
    /// 调整出库
    Adjust,
}

impl Default for StorageOutType {
    fn default() -> Self {
        StorageOutType::Sales
    }
}

impl ToString for StorageOutType {
    fn to_string(&self) -> String {
        match self {
            StorageOutType::Sales => "SALES".to_string(),
            StorageOutType::Return => "RETURN".to_string(),
            StorageOutType::Transfer => "TRANSFER".to_string(),
            StorageOutType::Adjust => "ADJUST".to_string(),
        }
    }
}

/// 盘点状态枚举
#[derive(Debug, Clone, Copy, PartialEq, Eq, Serialize, Deserialize)]
pub enum CheckStatus {
    /// 草稿
    Draft,
    /// 盘点中
    InProgress,
    /// 已完成
    Completed,
    /// 已调整
    Adjusted,
}

impl Default for CheckStatus {
    fn default() -> Self {
        CheckStatus::Draft
    }
}

impl ToString for CheckStatus {
    fn to_string(&self) -> String {
        match self {
            CheckStatus::Draft => "DRAFT".to_string(),
            CheckStatus::InProgress => "IN_PROGRESS".to_string(),
            CheckStatus::Completed => "COMPLETED".to_string(),
            CheckStatus::Adjusted => "ADJUSTED".to_string(),
        }
    }
}

/// 调拨状态枚举
#[derive(Debug, Clone, Copy, PartialEq, Eq, Serialize, Deserialize)]
pub enum TransferStatus {
    /// 草稿
    Draft,
    /// 待审核
    Pending,
    /// 运输中
    InTransit,
    /// 已完成
    Completed,
    /// 已取消
    Cancelled,
}

impl Default for TransferStatus {
    fn default() -> Self {
        TransferStatus::Draft
    }
}

impl ToString for TransferStatus {
    fn to_string(&self) -> String {
        match self {
            TransferStatus::Draft => "DRAFT".to_string(),
            TransferStatus::Pending => "PENDING".to_string(),
            TransferStatus::InTransit => "IN_TRANSIT".to_string(),
            TransferStatus::Completed => "COMPLETED".to_string(),
            TransferStatus::Cancelled => "CANCELLED".to_string(),
        }
    }
}

/// 价格类型枚举
#[derive(Debug, Clone, Copy, PartialEq, Eq, Serialize, Deserialize)]
pub enum PriceType {
    /// 成本价
    Cost,
    /// 销售价
    Sale,
    /// 建议零售价
    Suggested,
}

impl ToString for PriceType {
    fn to_string(&self) -> String {
        match self {
            PriceType::Cost => "COST".to_string(),
            PriceType::Sale => "SALE".to_string(),
            PriceType::Suggested => "SUGGESTED".to_string(),
        }
    }
}

/// 分页请求
#[derive(Debug, Clone, Serialize, Deserialize)]
pub struct PageRequest {
    pub page: u64,
    pub size: u64,
}

impl Default for PageRequest {
    fn default() -> Self {
        Self {
            page: 1,
            size: 20,
        }
    }
}

/// 分页响应
#[derive(Debug, Clone, Serialize, Deserialize)]
pub struct PageResponse<T> {
    pub data: Vec<T>,
    pub total: u64,
    pub page: u64,
    pub size: u64,
    pub pages: u64,
}

impl<T> PageResponse<T> {
    pub fn new(data: Vec<T>, total: u64, page: u64, size: u64) -> Self {
        let pages = (total + size - 1) / size;
        Self {
            data,
            total,
            page,
            size,
            pages,
        }
    }
}

/// API 统一响应
#[derive(Debug, Clone, Serialize, Deserialize)]
pub struct ApiResponse<T> {
    pub code: i32,
    pub message: String,
    pub data: Option<T>,
    pub timestamp: i64,
}

impl<T> ApiResponse<T> {
    pub fn success(data: T) -> Self {
        Self {
            code: 200,
            message: "success".to_string(),
            data: Some(data),
            timestamp: Local::now().timestamp_millis(),
        }
    }

    pub fn error(code: i32, message: impl Into<String>) -> Self {
        Self {
            code,
            message: message.into(),
            data: None,
            timestamp: Local::now().timestamp_millis(),
        }
    }
}

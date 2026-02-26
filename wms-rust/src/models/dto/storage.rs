//! 出入库单据 DTOs

use rust_decimal::Decimal;
use serde::{Deserialize, Serialize};
use validator::Validate;

/// 入库单类型
#[derive(Debug, Clone, Serialize, Deserialize)]
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

/// 单据状态
#[derive(Debug, Clone, Serialize, Deserialize)]
pub enum DocumentStatus {
    /// 草稿
    Draft,
    /// 待审核
    Pending,
    /// 已审核
    Approved,
    /// 已完成
    Completed,
    /// 已取消
    Cancelled,
}

impl ToString for DocumentStatus {
    fn to_string(&self) -> String {
        match self {
            DocumentStatus::Draft => "DRAFT".to_string(),
            DocumentStatus::Pending => "PENDING".to_string(),
            DocumentStatus::Approved => "APPROVED".to_string(),
            DocumentStatus::Completed => "COMPLETED".to_string(),
            DocumentStatus::Cancelled => "CANCELLED".to_string(),
        }
    }
}

/// 创建入库单请求
#[derive(Debug, Clone, Serialize, Deserialize, Validate)]
pub struct CreateStorageInRequest {
    /// 入库单号（不传则自动生成）
    pub transaction_code: Option<String>,
    /// 入库类型
    pub transaction_type: String,
    /// 仓库ID
    pub warehouse_id: i64,
    /// 储位ID
    pub slot_id: Option<i64>,
    /// 关联采购单ID
    pub procurement_id: Option<i64>,
    /// 批次号
    pub batch_no: Option<String>,
    /// 备注
    pub note: Option<String>,
    /// 入库明细
    #[validate(length(min = 1, message = "入库明细不能为空"))]
    pub items: Vec<StorageInItemRequest>,
}

/// 入库单明细请求
#[derive(Debug, Clone, Serialize, Deserialize, Validate)]
pub struct StorageInItemRequest {
    /// SKU ID
    pub sku_id: i64,
    /// 入库数量
    #[validate(range(min = 1, message = "入库数量必须大于0"))]
    pub quantity: i32,
    /// 入库单价
    pub unit_price: Option<Decimal>,
    /// 备注
    pub note: Option<String>,
}

/// 更新入库单请求
#[derive(Debug, Clone, Serialize, Deserialize, Validate)]
pub struct UpdateStorageInRequest {
    /// 仓库ID
    pub warehouse_id: Option<i64>,
    /// 储位ID
    pub slot_id: Option<i64>,
    /// 批次号
    pub batch_no: Option<String>,
    /// 备注
    pub note: Option<String>,
}

/// 入库单查询请求
#[derive(Debug, Clone, Serialize, Deserialize)]
pub struct QueryStorageInRequest {
    /// 入库单号
    pub transaction_code: Option<String>,
    /// 入库类型
    pub transaction_type: Option<String>,
    /// 仓库ID
    pub warehouse_id: Option<i64>,
    /// 状态
    pub status: Option<String>,
    /// 页码
    pub page: Option<u64>,
    /// 每页大小
    pub page_size: Option<u64>,
}

impl QueryStorageInRequest {
    pub fn page(&self) -> u64 {
        self.page.unwrap_or(1).max(1)
    }

    pub fn page_size(&self) -> u64 {
        self.page_size.unwrap_or(20).clamp(1, 100)
    }
}

/// 入库单响应
#[derive(Debug, Clone, Serialize, Deserialize)]
pub struct StorageInResponse {
    pub id: i64,
    pub org_id: i64,
    pub transaction_code: String,
    pub transaction_type: String,
    pub warehouse_id: i64,
    pub slot_id: Option<i64>,
    pub batch_no: Option<String>,
    pub status: String,
    pub note: Option<String>,
    pub procurement_id: Option<i64>,
    pub originator_id: Option<i64>,
    pub originator_name: Option<String>,
    pub transaction_by: Option<String>,
    pub total_quantity: i32,
    pub total_amount: Decimal,
    pub created_at: String,
    pub updated_at: String,
    /// 明细列表
    pub items: Vec<StorageInItemResponse>,
}

/// 入库单明细响应
#[derive(Debug, Clone, Serialize, Deserialize)]
pub struct StorageInItemResponse {
    pub id: i64,
    pub sku_id: i64,
    pub sku_name: Option<String>,
    pub sku_code: Option<String>,
    pub quantity: i32,
    pub unit_price: Option<Decimal>,
    pub total_price: Decimal,
    pub note: Option<String>,
}

// ==================== 出库单 ====================

/// 出库单类型
#[derive(Debug, Clone, Serialize, Deserialize)]
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

/// 创建出库单请求
#[derive(Debug, Clone, Serialize, Deserialize, Validate)]
pub struct CreateStorageOutRequest {
    /// 出库单号（不传则自动生成）
    pub transaction_code: Option<String>,
    /// 出库类型
    pub transaction_type: String,
    /// 仓库ID
    pub warehouse_id: i64,
    /// 储位ID
    pub slot_id: Option<i64>,
    /// 关联销售单ID
    pub sales_id: Option<i64>,
    /// 批次号
    pub batch_no: Option<String>,
    /// 备注
    pub note: Option<String>,
    /// 出库明细
    #[validate(length(min = 1, message = "出库明细不能为空"))]
    pub items: Vec<StorageOutItemRequest>,
}

/// 出库单明细请求
#[derive(Debug, Clone, Serialize, Deserialize, Validate)]
pub struct StorageOutItemRequest {
    /// SKU ID
    pub sku_id: i64,
    /// 出库数量
    #[validate(range(min = 1, message = "出库数量必须大于0"))]
    pub quantity: i32,
    /// 出库单价
    pub unit_price: Option<Decimal>,
    /// 备注
    pub note: Option<String>,
}

/// 出库单查询请求
#[derive(Debug, Clone, Serialize, Deserialize)]
pub struct QueryStorageOutRequest {
    /// 出库单号
    pub transaction_code: Option<String>,
    /// 出库类型
    pub transaction_type: Option<String>,
    /// 仓库ID
    pub warehouse_id: Option<i64>,
    /// 状态
    pub status: Option<String>,
    /// 页码
    pub page: Option<u64>,
    /// 每页大小
    pub page_size: Option<u64>,
}

impl QueryStorageOutRequest {
    pub fn page(&self) -> u64 {
        self.page.unwrap_or(1).max(1)
    }

    pub fn page_size(&self) -> u64 {
        self.page_size.unwrap_or(20).clamp(1, 100)
    }
}

/// 出库单响应
#[derive(Debug, Clone, Serialize, Deserialize)]
pub struct StorageOutResponse {
    pub id: i64,
    pub org_id: i64,
    pub transaction_code: String,
    pub transaction_type: String,
    pub warehouse_id: i64,
    pub slot_id: Option<i64>,
    pub batch_no: Option<String>,
    pub status: String,
    pub note: Option<String>,
    pub sales_id: Option<i64>,
    pub originator_id: Option<i64>,
    pub originator_name: Option<String>,
    pub transaction_by: Option<String>,
    pub total_quantity: i32,
    pub total_amount: Decimal,
    pub created_at: String,
    pub updated_at: String,
    /// 明细列表
    pub items: Vec<StorageOutItemResponse>,
}

/// 出库单明细响应
#[derive(Debug, Clone, Serialize, Deserialize)]
pub struct StorageOutItemResponse {
    pub id: i64,
    pub sku_id: i64,
    pub sku_name: Option<String>,
    pub sku_code: Option<String>,
    pub quantity: i32,
    pub unit_price: Option<Decimal>,
    pub total_price: Decimal,
    pub note: Option<String>,
}

// ==================== 盘点单 ====================

/// 盘点单状态
#[derive(Debug, Clone, Serialize, Deserialize)]
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

/// 创建盘点单请求
#[derive(Debug, Clone, Serialize, Deserialize, Validate)]
pub struct CreateCheckRequest {
    /// 盘点单号（不传则自动生成）
    pub check_code: Option<String>,
    /// 仓库ID
    pub warehouse_id: i64,
    /// 备注
    pub note: Option<String>,
}

/// 盘点单明细请求
#[derive(Debug, Clone, Serialize, Deserialize)]
pub struct CheckItemRequest {
    /// 库存ID
    pub inventory_id: i64,
    /// 实际数量
    pub actual_quantity: i32,
    /// 备注
    pub note: Option<String>,
}

/// 更新盘点单请求
#[derive(Debug, Clone, Serialize, Deserialize)]
pub struct UpdateCheckRequest {
    /// 备注
    pub note: Option<String>,
}

/// 盘点单查询请求
#[derive(Debug, Clone, Serialize, Deserialize)]
pub struct QueryCheckRequest {
    /// 盘点单号
    pub check_code: Option<String>,
    /// 仓库ID
    pub warehouse_id: Option<i64>,
    /// 状态
    pub status: Option<String>,
    /// 页码
    pub page: Option<u64>,
    /// 每页大小
    pub page_size: Option<u64>,
}

impl QueryCheckRequest {
    pub fn page(&self) -> u64 {
        self.page.unwrap_or(1).max(1)
    }

    pub fn page_size(&self) -> u64 {
        self.page_size.unwrap_or(20).clamp(1, 100)
    }
}

/// 盘点单响应
#[derive(Debug, Clone, Serialize, Deserialize)]
pub struct CheckResponse {
    pub id: i64,
    pub org_id: i64,
    pub check_code: String,
    pub warehouse_id: i64,
    pub status: String,
    pub note: Option<String>,
    pub profit_lost: i32,
    pub originator_id: Option<i64>,
    pub originator_name: Option<String>,
    pub transaction_by: Option<String>,
    pub created_at: String,
    pub updated_at: String,
    /// 明细列表
    pub items: Vec<CheckItemResponse>,
}

/// 盘点单明细响应
#[derive(Debug, Clone, Serialize, Deserialize)]
pub struct CheckItemResponse {
    pub id: i64,
    pub inventory_id: i64,
    pub sku_id: i64,
    pub sku_name: Option<String>,
    pub sku_code: Option<String>,
    pub warehouse_id: i64,
    pub slot_id: Option<i64>,
    pub before_quantity: i32,
    pub actual_quantity: i32,
    pub profit_lost: i32,
}

/// 盘点调整请求
#[derive(Debug, Clone, Serialize, Deserialize)]
pub struct AdjustCheckRequest {
    /// 盘点明细调整
    pub items: Vec<CheckItemAdjustRequest>,
}

/// 盘点明细调整项
#[derive(Debug, Clone, Serialize, Deserialize)]
pub struct CheckItemAdjustRequest {
    /// 盘点明细ID
    pub item_id: i64,
    /// 实际数量
    pub actual_quantity: i32,
}

//! 数据传输对象 (DTO)
//!
//! 定义请求和响应的数据结构

use chrono::{DateTime, Local};
use rust_decimal::Decimal;
use serde::{Deserialize, Serialize};
use validator::Validate;

pub mod sku;
pub mod warehouse;
pub mod storage;

pub use sku::*;
pub use warehouse::*;
pub use storage::*;

/// 创建产品请求
#[derive(Debug, Clone, Serialize, Deserialize, Validate)]
pub struct CreateProductRequest {
    /// 产品分类ID
    pub category_id: Option<i64>,
    /// 产品编码
    #[validate(length(min = 1, max = 100, message = "产品编码不能为空且不能超过100字符"))]
    pub product_code: String,
    /// 产品名称
    #[validate(length(min = 1, max = 255, message = "产品名称不能为空且不能超过255字符"))]
    pub name: String,
    /// 外文名称
    pub english_name: Option<String>,
    /// 重量
    pub weight: Option<String>,
    /// 体积
    pub volume: Option<String>,
    /// 规格
    pub spec: Option<String>,
    /// 产品标准
    pub product_standard: Option<String>,
    /// 零售价
    pub price: Option<Decimal>,
    /// 建议零售价
    pub suggested_price: Option<Decimal>,
    /// 成本价
    pub cost_price: Option<Decimal>,
    /// 规格描述
    pub specification: Option<String>,
    /// 排序值
    pub sort_value: Option<i32>,
    /// 搜索关键字
    pub search_key_word: Option<String>,
    /// 条形码
    pub bar_code: Option<String>,
    /// 产品描述
    pub description: Option<String>,
}

/// 更新产品请求
#[derive(Debug, Clone, Serialize, Deserialize, Validate)]
pub struct UpdateProductRequest {
    /// 产品分类ID
    pub category_id: Option<i64>,
    /// 产品名称
    #[validate(length(max = 255, message = "产品名称不能超过255字符"))]
    pub name: Option<String>,
    /// 外文名称
    pub english_name: Option<String>,
    /// 重量
    pub weight: Option<String>,
    /// 体积
    pub volume: Option<String>,
    /// 规格
    pub spec: Option<String>,
    /// 产品标准
    pub product_standard: Option<String>,
    /// 零售价
    pub price: Option<Decimal>,
    /// 建议零售价
    pub suggested_price: Option<Decimal>,
    /// 成本价
    pub cost_price: Option<Decimal>,
    /// 规格描述
    pub specification: Option<String>,
    /// 排序值
    pub sort_value: Option<i32>,
    /// 搜索关键字
    pub search_key_word: Option<String>,
    /// 条形码
    pub bar_code: Option<String>,
    /// 产品描述
    pub description: Option<String>,
    /// 状态
    pub status: Option<String>,
}

/// 产品查询请求
#[derive(Debug, Clone, Serialize, Deserialize)]
pub struct QueryProductRequest {
    /// 关键字搜索
    pub keyword: Option<String>,
    /// 分类ID
    pub category_id: Option<i64>,
    /// 状态
    pub status: Option<String>,
    /// 条形码
    pub bar_code: Option<String>,
    /// 页码
    pub page: Option<u64>,
    /// 每页大小
    pub page_size: Option<u64>,
}

impl QueryProductRequest {
    /// 获取页码，默认为1
    pub fn page(&self) -> u64 {
        self.page.unwrap_or(1).max(1)
    }

    /// 获取每页大小，默认为20
    pub fn page_size(&self) -> u64 {
        self.page_size.unwrap_or(20).clamp(1, 100)
    }
}

/// 产品响应
#[derive(Debug, Clone, Serialize, Deserialize)]
pub struct ProductResponse {
    pub id: i64,
    pub org_id: i64,
    pub category_id: Option<i64>,
    pub product_code: String,
    pub name: String,
    pub english_name: Option<String>,
    pub weight: Option<String>,
    pub volume: Option<String>,
    pub spec: Option<String>,
    pub product_standard: Option<String>,
    pub price: Option<Decimal>,
    pub suggested_price: Option<Decimal>,
    pub cost_price: Option<Decimal>,
    pub specification: Option<String>,
    pub quantities: Option<i32>,
    pub status: String,
    pub sort_value: Option<i32>,
    pub bar_code: Option<String>,
    pub description: Option<String>,
    pub created_at: DateTime<Local>,
    pub updated_at: DateTime<Local>,
}

impl From<crate::models::entity::product_entity::Model> for ProductResponse {
    fn from(model: crate::models::entity::product_entity::Model) -> Self {
        Self {
            id: model.id,
            org_id: model.org_id,
            category_id: model.product_category_id,
            product_code: model.product_code,
            name: model.name,
            english_name: model.english_name,
            weight: model.weight,
            volume: model.volume,
            spec: model.spec,
            product_standard: model.product_standard,
            price: model.price,
            suggested_price: model.suggested_price,
            cost_price: model.cost_price,
            specification: model.specification,
            quantities: model.quantities,
            status: model.status,
            sort_value: model.sort_value,
            bar_code: model.bar_code,
            description: model.description,
            created_at: Local::now(),
            updated_at: Local::now(),
        }
    }
}

/// 分页响应包装
#[derive(Debug, Clone, Serialize, Deserialize)]
pub struct PageResponse<T> {
    /// 数据列表
    pub data: Vec<T>,
    /// 总记录数
    pub total: u64,
    /// 当前页码
    pub page: u64,
    /// 每页大小
    pub page_size: u64,
    /// 总页数
    pub pages: u64,
}

impl<T> PageResponse<T> {
    pub fn new(data: Vec<T>, total: u64, page: u64, page_size: u64) -> Self {
        let pages = if total == 0 {
            1
        } else {
            (total + page_size - 1) / page_size
        };
        Self {
            data,
            total,
            page,
            page_size,
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

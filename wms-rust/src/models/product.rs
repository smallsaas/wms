//! 产品域模型定义

use chrono::{DateTime, Local};
use rust_decimal::Decimal;
use serde::{Deserialize, Serialize};

use super::base::{EntityStatus, TenantBase, TimestampBase};

/// 产品主实体
#[derive(Debug, Clone, Serialize, Deserialize)]
pub struct Product {
    pub id: i64,
    pub tenant: TenantBase,
    pub category_id: Option<i64>,
    pub product_code: String,
    pub name: String,
    pub english_name: Option<String>,
    pub weight: Option<String>,
    pub volume: Option<String>,
    pub spec: Option<String>,
    pub readjust_cost_price: Decimal,
    pub product_standard: Option<String>,
    pub price: Decimal,
    pub suggested_price: Decimal,
    pub cost_price: Decimal,
    pub specification: Option<String>,
    pub quantities: i32,
    pub stock_cost: Decimal,
    pub purchase_advance: i32,
    pub status: EntityStatus,
    pub sort_value: i32,
    pub search_key_word: Option<String>,
    pub bar_code: Option<String>,
    pub description: Option<String>,
    pub field1: Option<String>,
    pub field4: Option<String>,
    pub field5: Option<String>,
    pub timestamps: TimestampBase,
}

impl Product {
    /// 计算可用库存
    pub fn available_stock(&self) -> i32 {
        self.quantities - self.purchase_advance
    }

    /// 计算毛利
    pub fn profit_margin(&self) -> Decimal {
        if self.cost_price > Decimal::ZERO {
            ((self.price - self.cost_price) / self.cost_price) * Decimal::from(100)
        } else {
            Decimal::ZERO
        }
    }

    /// 是否启用
    pub fn is_active(&self) -> bool {
        matches!(self.status, EntityStatus::Active)
    }
}

impl Default for Product {
    fn default() -> Self {
        Self {
            id: 0,
            tenant: TenantBase::default(),
            category_id: None,
            product_code: String::new(),
            name: String::new(),
            english_name: None,
            weight: None,
            volume: None,
            spec: None,
            readjust_cost_price: Decimal::ZERO,
            product_standard: None,
            price: Decimal::ZERO,
            suggested_price: Decimal::ZERO,
            cost_price: Decimal::ZERO,
            specification: None,
            quantities: 0,
            stock_cost: Decimal::ZERO,
            purchase_advance: 0,
            status: EntityStatus::Active,
            sort_value: 0,
            search_key_word: None,
            bar_code: None,
            description: None,
            field1: None,
            field4: None,
            field5: None,
            timestamps: TimestampBase::default(),
        }
    }
}

/// 产品分类实体
#[derive(Debug, Clone, Serialize, Deserialize)]
pub struct ProductCategory {
    pub id: i64,
    pub tenant: TenantBase,
    pub category_name: String,
    pub category_code: Option<String>,
    pub pid: i64,
    pub category_description: Option<String>,
    pub created_at: DateTime<Local>,
    pub updated_at: DateTime<Local>,
}

impl ProductCategory {
    /// 是否为顶级分类
    pub fn is_root(&self) -> bool {
        self.pid == 0
    }
}

impl Default for ProductCategory {
    fn default() -> Self {
        Self {
            id: 0,
            tenant: TenantBase::default(),
            category_name: String::new(),
            category_code: None,
            pid: 0,
            category_description: None,
            created_at: Local::now(),
            updated_at: Local::now(),
        }
    }
}

/// 产品规格组实体
#[derive(Debug, Clone, Serialize, Deserialize)]
pub struct SpecificationGroup {
    pub id: i64,
    pub tenant: TenantBase,
    pub group_name: String,
    pub product_id: i64,
    pub created_at: DateTime<Local>,
    pub updated_at: DateTime<Local>,
}

impl Default for SpecificationGroup {
    fn default() -> Self {
        Self {
            id: 0,
            tenant: TenantBase::default(),
            group_name: String::new(),
            product_id: 0,
            created_at: Local::now(),
            updated_at: Local::now(),
        }
    }
}

/// 产品规格实体
#[derive(Debug, Clone, Serialize, Deserialize)]
pub struct Specification {
    pub id: i64,
    pub tenant: TenantBase,
    pub specification_name: String,
    pub specification_value: String,
    pub group_id: i64,
    pub product_id: i64,
    pub created_at: DateTime<Local>,
    pub updated_at: DateTime<Local>,
}

impl Default for Specification {
    fn default() -> Self {
        Self {
            id: 0,
            tenant: TenantBase::default(),
            specification_name: String::new(),
            specification_value: String::new(),
            group_id: 0,
            product_id: 0,
            created_at: Local::now(),
            updated_at: Local::now(),
        }
    }
}

/// 产品图片实体
#[derive(Debug, Clone, Serialize, Deserialize)]
pub struct ProductPhoto {
    pub id: i64,
    pub tenant: TenantBase,
    pub product_id: i64,
    pub photo_url: String,
    pub sort_order: i32,
    pub created_at: DateTime<Local>,
}

impl Default for ProductPhoto {
    fn default() -> Self {
        Self {
            id: 0,
            tenant: TenantBase::default(),
            product_id: 0,
            photo_url: String::new(),
            sort_order: 0,
            created_at: Local::now(),
        }
    }
}

/// 产品单位实体
#[derive(Debug, Clone, Serialize, Deserialize)]
pub struct ProductUnit {
    pub id: i64,
    pub tenant: TenantBase,
    pub unit_name: String,
    pub unit_code: Option<String>,
    pub product_id: i64,
    pub conversion_rate: Decimal,
    pub is_base_unit: bool,
    pub created_at: DateTime<Local>,
}

impl Default for ProductUnit {
    fn default() -> Self {
        Self {
            id: 0,
            tenant: TenantBase::default(),
            unit_name: String::new(),
            unit_code: None,
            product_id: 0,
            conversion_rate: Decimal::from(1),
            is_base_unit: false,
            created_at: Local::now(),
        }
    }
}

/// 产品条件实体
#[derive(Debug, Clone, Serialize, Deserialize)]
pub struct Condition {
    pub id: i64,
    pub tenant: TenantBase,
    pub condition_name: String,
    pub condition_type: Option<String>,
    pub created_at: DateTime<Local>,
    pub updated_at: DateTime<Local>,
}

impl Default for Condition {
    fn default() -> Self {
        Self {
            id: 0,
            tenant: TenantBase::default(),
            condition_name: String::new(),
            condition_type: None,
            created_at: Local::now(),
            updated_at: Local::now(),
        }
    }
}

/// 产品条件关系实体
#[derive(Debug, Clone, Serialize, Deserialize)]
pub struct ProductCondition {
    pub id: i64,
    pub tenant: TenantBase,
    pub product_id: i64,
    pub condition_id: i64,
    pub condition_value: Option<String>,
    pub created_at: DateTime<Local>,
}

impl Default for ProductCondition {
    fn default() -> Self {
        Self {
            id: 0,
            tenant: TenantBase::default(),
            product_id: 0,
            condition_id: 0,
            condition_value: None,
            created_at: Local::now(),
        }
    }
}

// ==================== DTOs ====================

/// 创建产品请求
#[derive(Debug, Clone, Serialize, Deserialize)]
pub struct CreateProductRequest {
    pub category_id: Option<i64>,
    pub product_code: String,
    pub name: String,
    pub english_name: Option<String>,
    pub weight: Option<String>,
    pub volume: Option<String>,
    pub spec: Option<String>,
    pub product_standard: Option<String>,
    pub price: Decimal,
    pub suggested_price: Decimal,
    pub cost_price: Decimal,
    pub specification: Option<String>,
    pub sort_value: i32,
    pub search_key_word: Option<String>,
    pub bar_code: Option<String>,
    pub description: Option<String>,
}

/// 更新产品请求
#[derive(Debug, Clone, Serialize, Deserialize)]
pub struct UpdateProductRequest {
    pub category_id: Option<i64>,
    pub name: Option<String>,
    pub english_name: Option<String>,
    pub weight: Option<String>,
    pub volume: Option<String>,
    pub spec: Option<String>,
    pub product_standard: Option<String>,
    pub price: Option<Decimal>,
    pub suggested_price: Option<Decimal>,
    pub cost_price: Option<Decimal>,
    pub specification: Option<String>,
    pub sort_value: Option<i32>,
    pub search_key_word: Option<String>,
    pub bar_code: Option<String>,
    pub description: Option<String>,
    pub status: Option<EntityStatus>,
}

/// 产品查询条件
#[derive(Debug, Clone, Serialize, Deserialize)]
pub struct ProductQuery {
    pub org_id: i64,
    pub keyword: Option<String>,
    pub category_id: Option<i64>,
    pub status: Option<EntityStatus>,
    pub bar_code: Option<String>,
}

/// 产品响应DTO
#[derive(Debug, Clone, Serialize, Deserialize)]
pub struct ProductResponse {
    pub id: i64,
    pub product_code: String,
    pub name: String,
    pub price: Decimal,
    pub cost_price: Decimal,
    pub status: String,
    pub quantities: i32,
    pub category_name: Option<String>,
    pub created_at: String,
}

impl From<&Product> for ProductResponse {
    fn from(p: &Product) -> Self {
        Self {
            id: p.id,
            product_code: p.product_code.clone(),
            name: p.name.clone(),
            price: p.price,
            cost_price: p.cost_price,
            status: p.status.to_string(),
            quantities: p.quantities,
            category_name: None, // 需要额外查询
            created_at: p.timestamps.created_at.to_rfc3339(),
        }
    }
}

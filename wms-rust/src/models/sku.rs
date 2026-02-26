//! SKU 域模型定义

use chrono::{DateTime, Local};
use rust_decimal::Decimal;
use serde::{Deserialize, Serialize};

use super::base::{EntityStatus, PriceType, TenantBase, TimestampBase};

/// SKU 主实体
#[derive(Debug, Clone, Serialize, Deserialize)]
pub struct SkuProduct {
    pub id: i64,
    pub tenant: TenantBase,
    pub product_id: i64,
    pub sku_code: String,
    pub sku_name: String,
    pub status: EntityStatus,
    pub sort_value: i32,
    pub search_key_word: Option<String>,
    pub bar_code: Option<String>,
    pub description: Option<String>,
    pub sku_price: Decimal,
    pub readjust_cost_price: Decimal,
    pub suggested_price: Decimal,
    pub cost_price: Decimal,
    pub stock_cost: Decimal,
    pub weight: Option<String>,
    pub volume: Option<String>,
    pub spec: Option<String>,
    pub field1: Option<String>,
    pub field2: Option<String>,
    pub field3: Option<String>,
    pub timestamps: TimestampBase,
}

impl SkuProduct {
    /// 计算毛利
    pub fn profit_margin(&self) -> Decimal {
        if self.cost_price > Decimal::ZERO {
            ((self.sku_price - self.cost_price) / self.cost_price) * Decimal::from(100)
        } else {
            Decimal::ZERO
        }
    }

    /// 是否启用
    pub fn is_active(&self) -> bool {
        matches!(self.status, EntityStatus::Active)
    }

    /// 获取完整规格描述
    pub fn full_spec(&self) -> String {
        match &self.spec {
            Some(s) => format!("{} ({})", self.sku_name, s),
            None => self.sku_name.clone(),
        }
    }
}

impl Default for SkuProduct {
    fn default() -> Self {
        Self {
            id: 0,
            tenant: TenantBase::default(),
            product_id: 0,
            sku_code: String::new(),
            sku_name: String::new(),
            status: EntityStatus::Active,
            sort_value: 0,
            search_key_word: None,
            bar_code: None,
            description: None,
            sku_price: Decimal::ZERO,
            readjust_cost_price: Decimal::ZERO,
            suggested_price: Decimal::ZERO,
            cost_price: Decimal::ZERO,
            stock_cost: Decimal::ZERO,
            weight: None,
            volume: None,
            spec: None,
            field1: None,
            field2: None,
            field3: None,
            timestamps: TimestampBase::default(),
        }
    }
}

/// SKU 规格关系实体
#[derive(Debug, Clone, Serialize, Deserialize)]
pub struct SkuSpecification {
    pub id: i64,
    pub tenant: TenantBase,
    pub sku_id: i64,
    pub group_id: i64,
    pub specification_id: Option<i64>,
    pub created_at: DateTime<Local>,
}

impl Default for SkuSpecification {
    fn default() -> Self {
        Self {
            id: 0,
            tenant: TenantBase::default(),
            sku_id: 0,
            group_id: 0,
            specification_id: None,
            created_at: Local::now(),
        }
    }
}

/// SKU 规格组实体
#[derive(Debug, Clone, Serialize, Deserialize)]
pub struct SkuSpecificationGroup {
    pub id: i64,
    pub tenant: TenantBase,
    pub group_name: String,
    pub sku_id: i64,
    pub created_at: DateTime<Local>,
}

impl Default for SkuSpecificationGroup {
    fn default() -> Self {
        Self {
            id: 0,
            tenant: TenantBase::default(),
            group_name: String::new(),
            sku_id: 0,
            created_at: Local::now(),
        }
    }
}

/// SKU 标签实体
#[derive(Debug, Clone, Serialize, Deserialize)]
pub struct SkuTag {
    pub id: i64,
    pub tenant: TenantBase,
    pub tag_name: String,
    pub tag_description: Option<String>,
    pub created_at: DateTime<Local>,
    pub updated_at: DateTime<Local>,
}

impl Default for SkuTag {
    fn default() -> Self {
        Self {
            id: 0,
            tenant: TenantBase::default(),
            tag_name: String::new(),
            tag_description: None,
            created_at: Local::now(),
            updated_at: Local::now(),
        }
    }
}

/// SKU 标签关系实体
#[derive(Debug, Clone, Serialize, Deserialize)]
pub struct SkuTagRelation {
    pub id: i64,
    pub tenant: TenantBase,
    pub sku_id: i64,
    pub tag_id: i64,
    pub created_at: DateTime<Local>,
}

impl Default for SkuTagRelation {
    fn default() -> Self {
        Self {
            id: 0,
            tenant: TenantBase::default(),
            sku_id: 0,
            tag_id: 0,
            created_at: Local::now(),
        }
    }
}

/// SKU 图片实体
#[derive(Debug, Clone, Serialize, Deserialize)]
pub struct SkuPhoto {
    pub id: i64,
    pub tenant: TenantBase,
    pub sku_id: i64,
    pub photo_url: String,
    pub sort_order: i32,
    pub created_at: DateTime<Local>,
}

impl Default for SkuPhoto {
    fn default() -> Self {
        Self {
            id: 0,
            tenant: TenantBase::default(),
            sku_id: 0,
            photo_url: String::new(),
            sort_order: 0,
            created_at: Local::now(),
        }
    }
}

/// SKU 单位实体
#[derive(Debug, Clone, Serialize, Deserialize)]
pub struct SkuUnit {
    pub id: i64,
    pub tenant: TenantBase,
    pub unit_name: String,
    pub unit_code: Option<String>,
    pub sku_id: i64,
    pub conversion_rate: Decimal,
    pub is_base_unit: bool,
    pub created_at: DateTime<Local>,
}

impl Default for SkuUnit {
    fn default() -> Self {
        Self {
            id: 0,
            tenant: TenantBase::default(),
            unit_name: String::new(),
            unit_code: None,
            sku_id: 0,
            conversion_rate: Decimal::from(1),
            is_base_unit: false,
            created_at: Local::now(),
        }
    }
}

/// SKU 条件实体
#[derive(Debug, Clone, Serialize, Deserialize)]
pub struct SkuCondition {
    pub id: i64,
    pub tenant: TenantBase,
    pub condition_name: String,
    pub condition_type: Option<String>,
    pub created_at: DateTime<Local>,
    pub updated_at: DateTime<Local>,
}

impl Default for SkuCondition {
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

/// SKU 条件关系实体
#[derive(Debug, Clone, Serialize, Deserialize)]
pub struct SkuConditionRelation {
    pub id: i64,
    pub tenant: TenantBase,
    pub sku_id: i64,
    pub condition_id: i64,
    pub condition_value: Option<String>,
    pub created_at: DateTime<Local>,
}

impl Default for SkuConditionRelation {
    fn default() -> Self {
        Self {
            id: 0,
            tenant: TenantBase::default(),
            sku_id: 0,
            condition_id: 0,
            condition_value: None,
            created_at: Local::now(),
        }
    }
}

/// SKU 价格历史实体
#[derive(Debug, Clone, Serialize, Deserialize)]
pub struct SkuPriceHistory {
    pub id: i64,
    pub tenant: TenantBase,
    pub sku_id: i64,
    pub price_type: PriceType,
    pub old_price: Option<Decimal>,
    pub new_price: Decimal,
    pub changed_by: Option<String>,
    pub change_reason: Option<String>,
    pub created_at: DateTime<Local>,
}

impl Default for SkuPriceHistory {
    fn default() -> Self {
        Self {
            id: 0,
            tenant: TenantBase::default(),
            sku_id: 0,
            price_type: PriceType::Cost,
            old_price: None,
            new_price: Decimal::ZERO,
            changed_by: None,
            change_reason: None,
            created_at: Local::now(),
        }
    }
}

// ==================== DTOs ====================

/// 创建 SKU 请求
#[derive(Debug, Clone, Serialize, Deserialize)]
pub struct CreateSkuRequest {
    pub product_id: i64,
    pub sku_code: String,
    pub sku_name: String,
    pub sku_price: Decimal,
    pub cost_price: Decimal,
    pub suggested_price: Decimal,
    pub spec: Option<String>,
    pub weight: Option<String>,
    pub volume: Option<String>,
    pub bar_code: Option<String>,
    pub description: Option<String>,
    pub specification_ids: Vec<i64>,
}

/// 更新 SKU 请求
#[derive(Debug, Clone, Serialize, Deserialize)]
pub struct UpdateSkuRequest {
    pub sku_name: Option<String>,
    pub sku_price: Option<Decimal>,
    pub cost_price: Option<Decimal>,
    pub suggested_price: Option<Decimal>,
    pub spec: Option<String>,
    pub weight: Option<String>,
    pub volume: Option<String>,
    pub bar_code: Option<String>,
    pub description: Option<String>,
    pub status: Option<EntityStatus>,
}

/// SKU 查询条件
#[derive(Debug, Clone, Serialize, Deserialize)]
pub struct SkuQuery {
    pub org_id: i64,
    pub product_id: Option<i64>,
    pub keyword: Option<String>,
    pub status: Option<EntityStatus>,
    pub bar_code: Option<String>,
    pub tag_ids: Vec<i64>,
}

/// SKU 响应DTO
#[derive(Debug, Clone, Serialize, Deserialize)]
pub struct SkuResponse {
    pub id: i64,
    pub product_id: i64,
    pub sku_code: String,
    pub sku_name: String,
    pub sku_price: Decimal,
    pub cost_price: Decimal,
    pub status: String,
    pub spec: Option<String>,
    pub bar_code: Option<String>,
    pub created_at: String,
}

impl From<&SkuProduct> for SkuResponse {
    fn from(s: &SkuProduct) -> Self {
        Self {
            id: s.id,
            product_id: s.product_id,
            sku_code: s.sku_code.clone(),
            sku_name: s.sku_name.clone(),
            sku_price: s.sku_price,
            cost_price: s.cost_price,
            status: s.status.to_string(),
            spec: s.spec.clone(),
            bar_code: s.bar_code.clone(),
            created_at: s.timestamps.created_at.to_rfc3339(),
        }
    }
}

/// SKU 详情响应（包含产品信息）
#[derive(Debug, Clone, Serialize, Deserialize)]
pub struct SkuDetailResponse {
    pub id: i64,
    pub product_id: i64,
    pub product_name: String,
    pub sku_code: String,
    pub sku_name: String,
    pub sku_price: Decimal,
    pub cost_price: Decimal,
    pub suggested_price: Decimal,
    pub status: String,
    pub spec: Option<String>,
    pub weight: Option<String>,
    pub volume: Option<String>,
    pub bar_code: Option<String>,
    pub description: Option<String>,
    pub tags: Vec<String>,
    pub photos: Vec<String>,
    pub created_at: String,
}

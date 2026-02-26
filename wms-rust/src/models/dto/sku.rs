//! SKU DTOs

use rust_decimal::Decimal;
use serde::{Deserialize, Serialize};
use validator::Validate;

/// 创建SKU请求
#[derive(Debug, Clone, Serialize, Deserialize, Validate)]
pub struct CreateSkuRequest {
    /// 产品ID
    pub product_id: i64,
    /// SKU编码
    #[validate(length(min = 1, max = 100, message = "SKU编码不能为空且不能超过100字符"))]
    pub sku_code: String,
    /// SKU名称
    #[validate(length(min = 1, max = 255, message = "SKU名称不能为空且不能超过255字符"))]
    pub sku_name: String,
    /// 条形码
    pub bar_code: Option<String>,
    /// 描述
    pub description: Option<String>,
    /// SKU售价
    pub sku_price: Option<Decimal>,
    /// 建议零售价
    pub suggested_price: Option<Decimal>,
    /// 成本价
    pub cost_price: Option<Decimal>,
    /// 重量
    pub weight: Option<String>,
    /// 体积
    pub volume: Option<String>,
    /// 规格
    pub spec: Option<String>,
    /// 排序值
    pub sort_value: Option<i32>,
    /// 规格组合（group_id:specification_id 的列表）
    pub specifications: Option<Vec<SkuSpecRelation>>,
}

/// SKU规格关系
#[derive(Debug, Clone, Serialize, Deserialize)]
pub struct SkuSpecRelation {
    pub group_id: i64,
    pub specification_id: Option<i64>,
}

/// 更新SKU请求
#[derive(Debug, Clone, Serialize, Deserialize, Validate)]
pub struct UpdateSkuRequest {
    /// SKU名称
    #[validate(length(max = 255, message = "SKU名称不能超过255字符"))]
    pub sku_name: Option<String>,
    /// 条形码
    pub bar_code: Option<String>,
    /// 描述
    pub description: Option<String>,
    /// SKU售价
    pub sku_price: Option<Decimal>,
    /// 建议零售价
    pub suggested_price: Option<Decimal>,
    /// 成本价
    pub cost_price: Option<Decimal>,
    /// 重量
    pub weight: Option<String>,
    /// 体积
    pub volume: Option<String>,
    /// 规格
    pub spec: Option<String>,
    /// 排序值
    pub sort_value: Option<i32>,
    /// 状态
    pub status: Option<String>,
}

/// SKU查询请求
#[derive(Debug, Clone, Serialize, Deserialize)]
pub struct QuerySkuRequest {
    /// 产品ID
    pub product_id: Option<i64>,
    /// 关键字搜索
    pub keyword: Option<String>,
    /// 状态
    pub status: Option<String>,
    /// 条形码
    pub bar_code: Option<String>,
    /// 页码
    pub page: Option<u64>,
    /// 每页大小
    pub page_size: Option<u64>,
}

impl QuerySkuRequest {
    /// 获取页码，默认为1
    pub fn page(&self) -> u64 {
        self.page.unwrap_or(1).max(1)
    }

    /// 获取每页大小，默认为20
    pub fn page_size(&self) -> u64 {
        self.page_size.unwrap_or(20).clamp(1, 100)
    }
}

/// SKU响应
#[derive(Debug, Clone, Serialize, Deserialize)]
pub struct SkuResponse {
    pub id: i64,
    pub org_id: i64,
    pub product_id: i64,
    pub sku_code: String,
    pub sku_name: String,
    pub bar_code: Option<String>,
    pub description: Option<String>,
    pub sku_price: Option<Decimal>,
    pub suggested_price: Option<Decimal>,
    pub cost_price: Option<Decimal>,
    pub stock_cost: Option<Decimal>,
    pub weight: Option<String>,
    pub volume: Option<String>,
    pub spec: Option<String>,
    pub status: String,
    pub sort_value: Option<i32>,
    pub created_at: String,
    pub updated_at: String,
}

impl From<crate::models::entity::sku_entity::Model> for SkuResponse {
    fn from(model: crate::models::entity::sku_entity::Model) -> Self {
        Self {
            id: model.id,
            org_id: model.org_id,
            product_id: model.product_id,
            sku_code: model.sku_code,
            sku_name: model.sku_name,
            bar_code: model.bar_code,
            description: model.description,
            sku_price: model.sku_price,
            suggested_price: model.suggested_price,
            cost_price: model.cost_price,
            stock_cost: model.stock_cost,
            weight: model.weight,
            volume: model.volume,
            spec: model.spec,
            status: model.status,
            sort_value: model.sort_value,
            created_at: model.created_at.to_string(),
            updated_at: model.updated_at.to_string(),
        }
    }
}

/// SKU详情响应（包含规格信息）
#[derive(Debug, Clone, Serialize, Deserialize)]
pub struct SkuDetailResponse {
    #[serde(flatten)]
    pub sku: SkuResponse,
    /// 规格列表
    pub specifications: Vec<SkuSpecItem>,
    /// 图片列表
    pub photos: Vec<SkuPhotoItem>,
}

/// SKU规格项
#[derive(Debug, Clone, Serialize, Deserialize)]
pub struct SkuSpecItem {
    pub group_id: i64,
    pub group_name: String,
    pub specification_id: Option<i64>,
    pub specification_name: Option<String>,
    pub specification_value: Option<String>,
}

/// SKU图片项
#[derive(Debug, Clone, Serialize, Deserialize)]
pub struct SkuPhotoItem {
    pub id: i64,
    pub photo_url: String,
    pub sort_order: i32,
}

/// 创建SKU标签请求
#[derive(Debug, Clone, Serialize, Deserialize, Validate)]
pub struct CreateSkuTagRequest {
    #[validate(length(min = 1, max = 100, message = "标签名称不能为空且不能超过100字符"))]
    pub tag_name: String,
    pub tag_description: Option<String>,
}

/// SKU标签响应
#[derive(Debug, Clone, Serialize, Deserialize)]
pub struct SkuTagResponse {
    pub id: i64,
    pub org_id: i64,
    pub tag_name: String,
    pub tag_description: Option<String>,
    pub created_at: String,
    pub updated_at: String,
}

impl From<crate::models::entity::sku_tag_entity::Model> for SkuTagResponse {
    fn from(model: crate::models::entity::sku_tag_entity::Model) -> Self {
        Self {
            id: model.id,
            org_id: model.org_id,
            tag_name: model.tag_name,
            tag_description: model.tag_description,
            created_at: model.created_at.to_string(),
            updated_at: model.updated_at.to_string(),
        }
    }
}

//! 产品实体 (Sea-ORM)
//!
//! 对应数据库表 wms_product

use sea_orm::entity::prelude::*;
use serde::{Deserialize, Serialize};

use crate::domain::tenant::TenantScoped;

/// 产品实体模块
pub mod product {
    use super::*;

    #[derive(Clone, Debug, PartialEq, DeriveEntityModel, Serialize, Deserialize)]
    #[sea_orm(table_name = "wms_product")]
    pub struct Model {
        #[sea_orm(primary_key)]
        pub id: i64,
        /// 组织ID（隔离字段）
        pub org_id: i64,
        /// 组织标识（隔离字段）
        pub org_tag: Option<String>,
        /// 产品分类ID
        pub product_category_id: Option<i64>,
        /// 产品编码
        pub product_code: String,
        /// 产品名称
        pub name: String,
        /// 外文名称
        pub english_name: Option<String>,
        /// 重量
        pub weight: Option<String>,
        /// 体积
        pub volume: Option<String>,
        /// 规格
        pub spec: Option<String>,
        /// 入库成本调整
        pub readjust_cost_price: Option<Decimal>,
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
        /// 总数量
        pub quantities: Option<i32>,
        /// 库存成本
        pub stock_cost: Option<Decimal>,
        /// 预购数量
        pub purchase_advance: Option<i32>,
        /// 状态
        pub status: String,
        /// 排序值
        pub sort_value: Option<i32>,
        /// 搜索关键字
        pub search_key_word: Option<String>,
        /// 条形码
        pub bar_code: Option<String>,
        /// 产品描述
        pub description: Option<String>,
        /// 保留字段1
        pub field1: Option<String>,
        /// 保留字段4
        pub field4: Option<String>,
        /// 保留字段5
        pub field5: Option<String>,
        /// 创建时间
        pub created_at: DateTime,
        /// 更新时间
        pub updated_at: DateTime,
    }

    #[derive(Copy, Clone, Debug, EnumIter, DeriveRelation)]
    pub enum Relation {}

    impl ActiveModelBehavior for ActiveModel {}

    /// 租户作用域实现
    impl TenantScoped for Select<Entity> {
        fn with_tenant(self, org_id: i64, _app_id: &str) -> Self {
            self.filter(Column::OrgId.eq(org_id))
        }
    }
}

pub use product::*;

/// 产品分类实体模块
pub mod category {
    use super::*;

    #[derive(Clone, Debug, PartialEq, DeriveEntityModel, Serialize, Deserialize)]
    #[sea_orm(table_name = "wms_product_category")]
    pub struct Model {
        #[sea_orm(primary_key)]
        pub id: i64,
        /// 组织ID（隔离字段）
        pub org_id: i64,
        /// 组织标识（隔离字段）
        pub org_tag: Option<String>,
        /// 分类名称
        pub category_name: String,
        /// 分类编码
        pub category_code: Option<String>,
        /// 父级ID
        pub pid: Option<i64>,
        /// 分类描述
        pub category_description: Option<String>,
        /// 创建时间
        pub created_at: DateTime,
        /// 更新时间
        pub updated_at: DateTime,
    }

    #[derive(Copy, Clone, Debug, EnumIter, DeriveRelation)]
    pub enum Relation {}

    impl ActiveModelBehavior for ActiveModel {}
}

pub use category::*;

/// 产品图片实体模块
pub mod photo {
    use super::*;

    #[derive(Clone, Debug, PartialEq, DeriveEntityModel, Serialize, Deserialize)]
    #[sea_orm(table_name = "wms_product_photo")]
    pub struct Model {
        #[sea_orm(primary_key)]
        pub id: i64,
        /// 组织ID（隔离字段）
        pub org_id: i64,
        /// 组织标识（隔离字段）
        pub org_tag: Option<String>,
        /// 产品ID
        pub product_id: i64,
        /// 图片URL
        pub photo_url: String,
        /// 排序
        pub sort_order: Option<i32>,
        /// 创建时间
        pub created_at: DateTime,
    }

    #[derive(Copy, Clone, Debug, EnumIter, DeriveRelation)]
    pub enum Relation {}

    impl ActiveModelBehavior for ActiveModel {}
}

pub use photo::*;

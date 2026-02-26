//! SKU 实体 (Sea-ORM)
//!
//! 对应数据库表 wms_sku_product

use sea_orm::entity::prelude::*;
use serde::{Deserialize, Serialize};

use crate::domain::tenant::TenantScoped;

/// SKU 实体模块
pub mod sku_product {
    use super::*;

    #[derive(Clone, Debug, PartialEq, DeriveEntityModel, Serialize, Deserialize)]
    #[sea_orm(table_name = "wms_sku_product")]
    pub struct Model {
        #[sea_orm(primary_key)]
        pub id: i64,
        /// 组织ID（隔离字段）
        pub org_id: i64,
        /// 组织标识（隔离字段）
        pub org_tag: Option<String>,
        /// 产品ID
        pub product_id: i64,
        /// SKU编码
        pub sku_code: String,
        /// SKU名称
        pub sku_name: String,
        /// 状态
        pub status: String,
        /// 排序值
        pub sort_value: Option<i32>,
        /// 搜索关键字
        pub search_key_word: Option<String>,
        /// 条形码
        pub bar_code: Option<String>,
        /// 描述
        pub description: Option<String>,
        /// SKU售价
        pub sku_price: Option<Decimal>,
        /// 入库成本调整
        pub readjust_cost_price: Option<Decimal>,
        /// 建议零售价
        pub suggested_price: Option<Decimal>,
        /// 成本价
        pub cost_price: Option<Decimal>,
        /// 库存成本
        pub stock_cost: Option<Decimal>,
        /// 重量
        pub weight: Option<String>,
        /// 体积
        pub volume: Option<String>,
        /// 规格
        pub spec: Option<String>,
        /// 保留字段1
        pub field1: Option<String>,
        /// 保留字段2
        pub field2: Option<String>,
        /// 保留字段3
        pub field3: Option<String>,
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

pub use sku_product::*;

/// SKU 规格关系实体
pub mod sku_specification {
    use super::*;

    #[derive(Clone, Debug, PartialEq, DeriveEntityModel, Serialize, Deserialize)]
    #[sea_orm(table_name = "wms_sku_specification")]
    pub struct Model {
        #[sea_orm(primary_key)]
        pub id: i64,
        /// 组织ID（隔离字段）
        pub org_id: i64,
        /// 组织标识（隔离字段）
        pub org_tag: Option<String>,
        /// SKU ID
        pub sku_id: i64,
        /// 规格组ID
        pub group_id: i64,
        /// 规格ID
        pub specification_id: Option<i64>,
        /// 创建时间
        pub created_at: DateTime,
    }

    #[derive(Copy, Clone, Debug, EnumIter, DeriveRelation)]
    pub enum Relation {}

    impl ActiveModelBehavior for ActiveModel {}
}

pub use sku_specification::*;

/// SKU 标签实体
pub mod sku_tag {
    use super::*;

    #[derive(Clone, Debug, PartialEq, DeriveEntityModel, Serialize, Deserialize)]
    #[sea_orm(table_name = "wms_sku_tag")]
    pub struct Model {
        #[sea_orm(primary_key)]
        pub id: i64,
        /// 组织ID（隔离字段）
        pub org_id: i64,
        /// 组织标识（隔离字段）
        pub org_tag: Option<String>,
        /// 标签名称
        pub tag_name: String,
        /// 标签描述
        pub tag_description: Option<String>,
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

pub use sku_tag::*;

/// SKU 图片实体
pub mod sku_photo {
    use super::*;

    #[derive(Clone, Debug, PartialEq, DeriveEntityModel, Serialize, Deserialize)]
    #[sea_orm(table_name = "wms_sku_photo")]
    pub struct Model {
        #[sea_orm(primary_key)]
        pub id: i64,
        /// 组织ID（隔离字段）
        pub org_id: i64,
        /// 组织标识（隔离字段）
        pub org_tag: Option<String>,
        /// SKU ID
        pub sku_id: i64,
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

pub use sku_photo::*;

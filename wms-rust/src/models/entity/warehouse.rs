//! 仓库实体 (Sea-ORM)
//!
//! 对应数据库表 wms_warehouse, wms_warehouse_slot, wms_inventory

use sea_orm::entity::prelude::*;
use serde::{Deserialize, Serialize};

use crate::domain::tenant::TenantScoped;

/// 仓库实体模块
pub mod warehouse {
    use super::*;

    #[derive(Clone, Debug, PartialEq, DeriveEntityModel, Serialize, Deserialize)]
    #[sea_orm(table_name = "wms_warehouse")]
    pub struct Model {
        #[sea_orm(primary_key)]
        pub id: i64,
        /// 组织ID（隔离字段）
        pub org_id: i64,
        /// 组织标识（隔离字段）
        pub org_tag: Option<String>,
        /// 仓库编码
        pub warehouse_code: String,
        /// 仓库名称
        pub warehouse_name: String,
        /// 省市区
        pub warehouse_pcd: Option<String>,
        /// 详细地址
        pub warehouse_address: Option<String>,
        /// 负责人ID
        pub warehouse_charger: Option<i64>,
        /// 状态
        pub status: String,
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

pub use warehouse::*;

/// 储位实体模块
pub mod warehouse_slot {
    use super::*;

    #[derive(Clone, Debug, PartialEq, DeriveEntityModel, Serialize, Deserialize)]
    #[sea_orm(table_name = "wms_warehouse_slot")]
    pub struct Model {
        #[sea_orm(primary_key)]
        pub id: i64,
        /// 组织ID（隔离字段）
        pub org_id: i64,
        /// 组织标识（隔离字段）
        pub org_tag: Option<String>,
        /// 储位编码
        pub slot_code: String,
        /// 储位名称
        pub slot_name: String,
        /// 仓库ID
        pub warehouse_id: i64,
        /// 储位说明
        pub slot_note: Option<String>,
        /// 状态
        pub status: String,
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

pub use warehouse_slot::*;

/// 库存实体模块
pub mod inventory {
    use super::*;

    #[derive(Clone, Debug, PartialEq, DeriveEntityModel, Serialize, Deserialize)]
    #[sea_orm(table_name = "wms_inventory")]
    pub struct Model {
        #[sea_orm(primary_key)]
        pub id: i64,
        /// 组织ID（隔离字段）
        pub org_id: i64,
        /// 组织标识（隔离字段）
        pub org_tag: Option<String>,
        /// 仓库ID
        pub warehouse_id: i64,
        /// 储位ID
        pub slot_id: Option<i64>,
        /// SKU ID
        pub sku_id: i64,
        /// 库存上限
        pub max_inventory: Option<i32>,
        /// 库存下限
        pub min_inventory: Option<i32>,
        /// 可用库存
        pub valid_sku: Option<i32>,
        /// 预购量
        pub advance_quantities: Option<i32>,
        /// 在途量
        pub transmit_quantities: Option<i32>,
        /// 订单占用量
        pub order_count: Option<i32>,
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

pub use inventory::*;

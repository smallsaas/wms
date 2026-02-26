//! 出入库单据实体 (Sea-ORM)
//!
//! 对应数据库表 wms_storage_in, wms_storage_out, wms_storage_in_item, wms_storage_out_item

use sea_orm::entity::prelude::*;
use serde::{Deserialize, Serialize};

use crate::domain::tenant::TenantScoped;

/// 入库单实体模块
pub mod storage_in {
    use super::*;

    #[derive(Clone, Debug, PartialEq, DeriveEntityModel, Serialize, Deserialize)]
    #[sea_orm(table_name = "wms_storage_in")]
    pub struct Model {
        #[sea_orm(primary_key)]
        pub id: i64,
        /// 组织ID（隔离字段）
        pub org_id: i64,
        /// 组织标识（隔离字段）
        pub org_tag: Option<String>,
        /// 入库单号
        pub transaction_code: String,
        /// 入库类型: PROCUREMENT, RETURN, TRANSFER, ADJUST
        pub transaction_type: Option<String>,
        /// 入库时间
        pub storage_in_time: Option<DateTime>,
        /// 操作时间
        pub transaction_time: Option<DateTime>,
        /// 批次号
        pub batch_no: Option<String>,
        /// 仓库ID
        pub warehouse_id: i64,
        /// 储位ID
        pub slot_id: Option<i64>,
        /// 备注
        pub note: Option<String>,
        /// 状态: DRAFT, PENDING, APPROVED, COMPLETED, CANCELLED
        pub status: String,
        /// 操作人
        pub transaction_by: Option<String>,
        /// 制单人ID
        pub originator_id: Option<i64>,
        /// 制单人
        pub originator_name: Option<String>,
        /// 成本调整
        pub readjust_cost_price: Option<Decimal>,
        /// 关联采购单ID
        pub procurement_id: Option<i64>,
        /// 分销商/客户
        pub distributor_customer: Option<String>,
        /// 外部订单号
        pub out_order_num: Option<String>,
        /// 保留字段1
        pub field1: Option<String>,
        /// 保留字段2
        pub field2: Option<String>,
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

pub use storage_in::*;

/// 入库单明细实体模块
pub mod storage_in_item {
    use super::*;

    #[derive(Clone, Debug, PartialEq, DeriveEntityModel, Serialize, Deserialize)]
    #[sea_orm(table_name = "wms_storage_in_item")]
    pub struct Model {
        #[sea_orm(primary_key)]
        pub id: i64,
        /// 组织ID（隔离字段）
        pub org_id: i64,
        /// 组织标识（隔离字段）
        pub org_tag: Option<String>,
        /// 入库单ID
        pub storage_in_id: i64,
        /// SKU ID
        pub sku_id: i64,
        /// 入库单价
        pub transaction_sku_price: Option<Decimal>,
        /// 入库数量
        pub transaction_quantities: i32,
        /// 入库后数量
        pub after_transaction_quantities: Option<i32>,
        /// 入库前数量
        pub before_transaction_quantities: Option<i32>,
        /// 需求数量
        pub demand_quantities: Option<i32>,
        /// 操作时间
        pub transaction_time: Option<DateTime>,
        /// 类型
        pub r#type: Option<String>,
        /// 关联单号
        pub relation_code: Option<String>,
        /// 创建时间
        pub created_at: DateTime,
    }

    #[derive(Copy, Clone, Debug, EnumIter, DeriveRelation)]
    pub enum Relation {}

    impl ActiveModelBehavior for ActiveModel {}
}

pub use storage_in_item::*;

/// 出库单实体模块
pub mod storage_out {
    use super::*;

    #[derive(Clone, Debug, PartialEq, DeriveEntityModel, Serialize, Deserialize)]
    #[sea_orm(table_name = "wms_storage_out")]
    pub struct Model {
        #[sea_orm(primary_key)]
        pub id: i64,
        /// 组织ID（隔离字段）
        pub org_id: i64,
        /// 组织标识（隔离字段）
        pub org_tag: Option<String>,
        /// 出库单号
        pub transaction_code: String,
        /// 出库类型: SALES, RETURN, TRANSFER, ADJUST
        pub transaction_type: Option<String>,
        /// 操作时间
        pub transaction_time: Option<DateTime>,
        /// 出库时间
        pub storage_out_time: Option<DateTime>,
        /// 批次号
        pub batch_no: Option<String>,
        /// 仓库ID
        pub warehouse_id: i64,
        /// 储位ID
        pub slot_id: Option<i64>,
        /// 备注
        pub note: Option<String>,
        /// 状态: DRAFT, PENDING, APPROVED, COMPLETED, CANCELLED
        pub status: String,
        /// 操作人
        pub transaction_by: Option<String>,
        /// 制单人ID
        pub originator_id: Option<i64>,
        /// 制单人
        pub originator_name: Option<String>,
        /// 外部订单号
        pub out_order_num: Option<String>,
        /// 分销商/客户
        pub distributor_customer: Option<String>,
        /// 关联销售单ID
        pub sales_id: Option<i64>,
        /// 保留字段1
        pub field1: Option<String>,
        /// 保留字段2
        pub field2: Option<String>,
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

pub use storage_out::*;

/// 出库单明细实体模块
pub mod storage_out_item {
    use super::*;

    #[derive(Clone, Debug, PartialEq, DeriveEntityModel, Serialize, Deserialize)]
    #[sea_orm(table_name = "wms_storage_out_item")]
    pub struct Model {
        #[sea_orm(primary_key)]
        pub id: i64,
        /// 组织ID（隔离字段）
        pub org_id: i64,
        /// 组织标识（隔离字段）
        pub org_tag: Option<String>,
        /// 出库单ID
        pub storage_out_id: i64,
        /// SKU ID
        pub sku_id: i64,
        /// 出库单价
        pub transaction_sku_price: Option<Decimal>,
        /// 出库数量
        pub transaction_quantities: i32,
        /// 出库后数量
        pub after_transaction_quantities: Option<i32>,
        /// 出库前数量
        pub before_transaction_quantities: Option<i32>,
        /// 需求数量
        pub demand_quantities: Option<i32>,
        /// 操作时间
        pub transaction_time: Option<DateTime>,
        /// 关联单号
        pub relation_code: Option<String>,
        /// 类型
        pub r#type: Option<String>,
        /// 创建时间
        pub created_at: DateTime,
    }

    #[derive(Copy, Clone, Debug, EnumIter, DeriveRelation)]
    pub enum Relation {}

    impl ActiveModelBehavior for ActiveModel {}
}

pub use storage_out_item::*;

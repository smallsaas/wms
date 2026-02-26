//! Sea-ORM 实体模块
//!
//! 定义数据库表对应的 Sea-ORM 实体

pub mod product;
pub mod sku;
pub mod warehouse;
pub mod storage;

// 重新导出产品实体
pub use product::product as product_entity;
pub use product::category as category_entity;
pub use product::photo as photo_entity;

// 重新导出SKU实体
pub use sku::sku_product as sku_entity;
pub use sku::sku_specification as sku_spec_entity;
pub use sku::sku_tag as sku_tag_entity;
pub use sku::sku_photo as sku_photo_entity;

// 重新导出仓库实体
pub use warehouse::warehouse as warehouse_entity;
pub use warehouse::warehouse_slot as slot_entity;
pub use warehouse::inventory as inventory_entity;

// 重新导出出入库实体
pub use storage::storage_in as storage_in_entity;
pub use storage::storage_in_item as storage_in_item_entity;
pub use storage::storage_out as storage_out_entity;
pub use storage::storage_out_item as storage_out_item_entity;

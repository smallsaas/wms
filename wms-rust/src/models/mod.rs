//! WMS 模型模块
//!
//! 包含所有数据模型定义

// 传统领域模型（保留用于业务逻辑）
pub mod base;
pub mod inventory;
pub mod product;
pub mod sku;
pub mod warehouse;

// Sea-ORM 实体
pub mod entity;

// DTOs
pub mod dto;

// 重新导出
pub use base::*;
pub use dto::*;
pub use entity::*;
pub use inventory::*;
pub use product::*;
pub use sku::*;
pub use warehouse::*;

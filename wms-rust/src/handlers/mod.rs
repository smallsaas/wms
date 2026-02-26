//! HTTP 处理器模块
//!
//! 处理HTTP请求并返回响应

pub mod product;
pub mod sku;
pub mod warehouse;
pub mod storage;

pub use product::*;
pub use sku::*;
pub use warehouse::*;
pub use storage::*;

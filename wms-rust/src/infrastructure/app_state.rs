//! 应用状态
//!
//! 定义应用程序共享状态

use std::sync::Arc;

use crate::infrastructure::tenant_sdk::{MockTenantSdk, TenantSdk};

/// 应用状态
#[derive(Clone)]
pub struct AppState<S: TenantSdk> {
    /// 数据库连接
    pub db: sea_orm::DatabaseConnection,
    /// 租户SDK
    pub tenant_sdk: Arc<S>,
}

/// 使用 MockTenantSdk 的应用状态类型别名
pub type MockAppState = AppState<MockTenantSdk>;

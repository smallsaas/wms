//! 测试工具模块
//!
//! 提供测试所需的通用功能

use wms_rust::infrastructure::tenant_sdk::MockTenantSdk;
use wms_rust::infrastructure::app_state::AppState;
use sea_orm::Database;

/// 测试配置
pub fn test_database_url() -> String {
    std::env::var("TEST_DATABASE_URL")
        .unwrap_or_else(|_| "mysql://root:password@localhost:3306/wms_test".to_string())
}

/// 设置测试应用状态
pub async fn setup_test_state() -> AppState<MockTenantSdk> {
    let db = Database::connect(&test_database_url())
        .await
        .expect("Failed to connect to test database");

    AppState {
        db,
        tenant_sdk: std::sync::Arc::new(MockTenantSdk::new()),
    }
}

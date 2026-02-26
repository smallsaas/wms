//! 租户SDK测试

use std::collections::HashMap;
use wms_rust::infrastructure::tenant_sdk::{MockTenantSdk, RequestContext, TenantSdk};

#[tokio::test]
async fn test_mock_tenant_sdk_success() {
    let sdk = MockTenantSdk::new();
    let mut headers = HashMap::new();
    headers.insert("X-Org-Id".to_string(), "1".to_string());
    headers.insert("X-App-Id".to_string(), "test-app".to_string());
    headers.insert("X-User-Id".to_string(), "100".to_string());

    let ctx = RequestContext {
        request_id: "test-123".to_string(),
        client_ip: "127.0.0.1".to_string(),
        headers,
    };

    let result = sdk.get_tenant(&ctx).await;
    assert!(result.is_ok());

    let tenant = result.unwrap();
    assert_eq!(tenant.org_id, 1);
    assert_eq!(tenant.app_id, "test-app");
    assert_eq!(tenant.user_id, Some(100));
}

#[tokio::test]
async fn test_mock_tenant_sdk_missing_org_id() {
    let sdk = MockTenantSdk::new();
    let ctx = RequestContext {
        request_id: "test-123".to_string(),
        client_ip: "127.0.0.1".to_string(),
        headers: HashMap::new(),
    };

    let result = sdk.get_tenant(&ctx).await;
    assert!(result.is_err());
}

#[tokio::test]
async fn test_mock_tenant_sdk_default_app_id() {
    let sdk = MockTenantSdk::new();
    let mut headers = HashMap::new();
    headers.insert("X-Org-Id".to_string(), "1".to_string());

    let ctx = RequestContext {
        request_id: "test-123".to_string(),
        client_ip: "127.0.0.1".to_string(),
        headers,
    };

    let result = sdk.get_tenant(&ctx).await;
    assert!(result.is_ok());

    let tenant = result.unwrap();
    assert_eq!(tenant.app_id, "default");
}

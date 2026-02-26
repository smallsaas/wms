//! 租户上下文测试

use wms_rust::domain::tenant::TenantContext;

#[test]
fn test_tenant_context_new() {
    let ctx = TenantContext::new(1, "app1", Some(100));
    assert_eq!(ctx.org_id, 1);
    assert_eq!(ctx.app_id, "app1");
    assert_eq!(ctx.user_id, Some(100));
}

#[test]
fn test_tenant_context_with_names() {
    let ctx = TenantContext::new(1, "app1", None)
        .with_names("Test Org", "Test App");
    assert_eq!(ctx.org_name, Some("Test Org".to_string()));
    assert_eq!(ctx.app_name, Some("Test App".to_string()));
}

#[test]
fn test_can_access_org() {
    let ctx = TenantContext::new(1, "app1", None);
    assert!(ctx.can_access_org(1));
    assert!(!ctx.can_access_org(2));
}

#[test]
fn test_can_access_app() {
    let ctx = TenantContext::new(1, "app1", None);
    assert!(ctx.can_access_app("app1"));
    assert!(!ctx.can_access_app("app2"));
}

//! 租户领域模型
//!
//! 定义租户上下文和相关领域概念

use serde::{Deserialize, Serialize};

/// 租户上下文
///
/// 在请求处理过程中传递租户信息
#[derive(Debug, Clone, Serialize, Deserialize)]
pub struct TenantContext {
    /// 组织ID
    pub org_id: i64,
    /// 应用ID
    pub app_id: String,
    /// 用户ID
    pub user_id: Option<i64>,
    /// 组织名称
    pub org_name: Option<String>,
    /// 应用名称
    pub app_name: Option<String>,
}

impl TenantContext {
    /// 创建新的租户上下文
    pub fn new(org_id: i64, app_id: impl Into<String>, user_id: Option<i64>) -> Self {
        Self {
            org_id,
            app_id: app_id.into(),
            user_id,
            org_name: None,
            app_name: None,
        }
    }

    /// 设置组织名称和应用名称
    pub fn with_names(
        mut self,
        org_name: impl Into<String>,
        app_name: impl Into<String>,
    ) -> Self {
        self.org_name = Some(org_name.into());
        self.app_name = Some(app_name.into());
        self
    }

    /// 检查是否有权限访问指定组织的数据
    pub fn can_access_org(&self, org_id: i64) -> bool {
        self.org_id == org_id
    }

    /// 检查是否有权限访问指定应用的数据
    pub fn can_access_app(&self, app_id: &str) -> bool {
        self.app_id == app_id
    }
}

impl Default for TenantContext {
    fn default() -> Self {
        Self {
            org_id: 0,
            app_id: "default".to_string(),
            user_id: None,
            org_name: None,
            app_name: None,
        }
    }
}

/// 租户上下文提取器
///
/// 用于从请求扩展中提取租户上下文
#[derive(Clone)]
pub struct TenantContextExtractor(pub TenantContext);

impl std::ops::Deref for TenantContextExtractor {
    type Target = TenantContext;

    fn deref(&self) -> &Self::Target {
        &self.0
    }
}

/// 租户作用域 trait
///
/// 用于在数据库查询中自动应用租户过滤
pub trait TenantScoped {
    /// 应用租户过滤条件
    fn with_tenant(self, org_id: i64, app_id: &str) -> Self;
}

#[cfg(test)]
mod tests {
    use super::*;

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

    #[test]
    fn test_tenant_context_extractor() {
        let ctx = TenantContext::new(1, "app1", None);
        let extractor = TenantContextExtractor(ctx);
        assert_eq!(extractor.org_id, 1);
    }
}

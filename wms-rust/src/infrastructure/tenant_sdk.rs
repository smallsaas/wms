//! 租户SDK模块
//!
//! 定义租户SDK接口和实现，用于获取租户信息

use async_trait::async_trait;
use serde::{Deserialize, Serialize};
use std::collections::HashMap;

/// 请求上下文
#[derive(Debug, Clone)]
pub struct RequestContext {
    /// 请求ID
    pub request_id: String,
    /// 客户端IP
    pub client_ip: String,
    /// 请求头
    pub headers: HashMap<String, String>,
}

/// 租户信息
#[derive(Debug, Clone, Serialize, Deserialize)]
pub struct TenantInfo {
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

/// 租户SDK错误
#[derive(Debug, thiserror::Error)]
pub enum TenantSdkError {
    #[error("租户未找到")]
    NotFound,
    #[error("SDK请求失败: {0}")]
    RequestFailed(String),
    #[error("无效响应: {0}")]
    InvalidResponse(String),
    #[error("缺少必要的请求头: {0}")]
    MissingHeader(String),
}

/// 租户SDK trait
#[async_trait]
pub trait TenantSdk: Send + Sync {
    /// 根据请求上下文获取租户信息
    async fn get_tenant(&self, ctx: &RequestContext) -> Result<TenantInfo, TenantSdkError>;
}

// ============================================
// 模拟实现（用于开发和测试）
// ============================================

/// 模拟租户SDK实现
#[derive(Debug, Clone)]
pub struct MockTenantSdk;

impl MockTenantSdk {
    /// 创建新的模拟租户SDK
    pub fn new() -> Self {
        Self
    }
}

impl Default for MockTenantSdk {
    fn default() -> Self {
        Self::new()
    }
}

#[async_trait]
impl TenantSdk for MockTenantSdk {
    async fn get_tenant(&self, ctx: &RequestContext) -> Result<TenantInfo, TenantSdkError> {
        // 从请求头中获取租户信息
        let org_id = ctx
            .headers
            .get("X-Org-Id")
            .or_else(|| ctx.headers.get("x-org-id"))
            .ok_or_else(|| TenantSdkError::MissingHeader("X-Org-Id".to_string()))?
            .parse::<i64>()
            .map_err(|_| TenantSdkError::InvalidResponse("Invalid org_id format".to_string()))?;

        let app_id = ctx
            .headers
            .get("X-App-Id")
            .or_else(|| ctx.headers.get("x-app-id"))
            .cloned()
            .unwrap_or_else(|| "default".to_string());

        let user_id = ctx
            .headers
            .get("X-User-Id")
            .or_else(|| ctx.headers.get("x-user-id"))
            .and_then(|v| v.parse::<i64>().ok());

        let app_name = format!("App {}", app_id);

        Ok(TenantInfo {
            org_id,
            app_id,
            user_id,
            org_name: Some(format!("Org {}", org_id)),
            app_name: Some(app_name),
        })
    }
}

// ============================================
// HTTP 客户端实现（用于生产环境）
// ============================================

/// HTTP 租户SDK实现（调用外部租户服务）
#[derive(Debug, Clone)]
pub struct HttpTenantSdk {
    base_url: String,
    api_key: String,
}

impl HttpTenantSdk {
    /// 创建新的HTTP租户SDK
    pub fn new(base_url: impl Into<String>, api_key: impl Into<String>) -> Self {
        Self {
            base_url: base_url.into(),
            api_key: api_key.into(),
        }
    }
}

#[async_trait]
impl TenantSdk for HttpTenantSdk {
    async fn get_tenant(&self, ctx: &RequestContext) -> Result<TenantInfo, TenantSdkError> {
        let client = reqwest::Client::new();

        let response = client
            .get(format!("{}/api/v1/tenant", self.base_url))
            .header("X-API-Key", &self.api_key)
            .header("X-Request-Id", &ctx.request_id)
            .send()
            .await
            .map_err(|e| TenantSdkError::RequestFailed(e.to_string()))?;

        if response.status() == reqwest::StatusCode::NOT_FOUND {
            return Err(TenantSdkError::NotFound);
        }

        if !response.status().is_success() {
            return Err(TenantSdkError::RequestFailed(format!(
                "HTTP {}",
                response.status()
            )));
        }

        let tenant_info = response
            .json::<TenantInfo>()
            .await
            .map_err(|e| TenantSdkError::InvalidResponse(e.to_string()))?;

        Ok(tenant_info)
    }
}

#[cfg(test)]
mod tests {
    use super::*;

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
    async fn test_mock_tenant_sdk_missing_header() {
        let sdk = MockTenantSdk::new();
        let ctx = RequestContext {
            request_id: "test-123".to_string(),
            client_ip: "127.0.0.1".to_string(),
            headers: HashMap::new(),
        };

        let result = sdk.get_tenant(&ctx).await;
        assert!(result.is_err());
        assert!(matches!(result.unwrap_err(), TenantSdkError::MissingHeader(_)));
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
}

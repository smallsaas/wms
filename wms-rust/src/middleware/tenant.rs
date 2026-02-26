//! 租户上下文中间件
//!
//! 从请求中提取租户信息并注入到请求上下文中

use axum::{
    extract::{Request, State},
    middleware::Next,
    response::Response,
};
use std::collections::HashMap;
use std::sync::Arc;

use crate::{
    domain::tenant::{TenantContext, TenantContextExtractor},
    infrastructure::tenant_sdk::{RequestContext, TenantSdk},
    error::AppError,
};



/// 租户上下文中间件
///
/// 从请求头中提取租户信息，并通过SDK获取完整的租户上下文
pub async fn tenant_context_middleware<S: TenantSdk>(
    State(state): State<Arc<crate::infrastructure::app_state::AppState<S>>>,
    mut request: Request,
    next: Next,
) -> Result<Response, AppError> {
    // 构建请求上下文
    let request_ctx = build_request_context(&request);

    // 通过SDK获取租户信息
    let tenant_info = state
        .tenant_sdk
        .get_tenant(&request_ctx)
        .await
        .map_err(|e| AppError::TenantSdk(e.to_string()))?;

    // 创建租户上下文
    let tenant_ctx = TenantContext::new(
        tenant_info.org_id,
        tenant_info.app_id,
        tenant_info.user_id,
    )
    .with_names(
        tenant_info.org_name.unwrap_or_default(),
        tenant_info.app_name.unwrap_or_default(),
    );

    // 将租户上下文注入请求扩展
    request.extensions_mut().insert(TenantContextExtractor(tenant_ctx));

    Ok(next.run(request).await)
}

/// 从请求中构建请求上下文
fn build_request_context(request: &Request) -> RequestContext {
    let headers = request
        .headers()
        .iter()
        .filter_map(|(k, v)| {
            v.to_str()
                .ok()
                .map(|v| (k.as_str().to_string(), v.to_string()))
        })
        .collect::<HashMap<_, _>>();

    RequestContext {
        request_id: uuid::Uuid::new_v4().to_string(),
        client_ip: extract_client_ip(request),
        headers,
    }
}

/// 提取客户端IP地址
fn extract_client_ip(request: &Request) -> String {
    // 首先尝试从 X-Forwarded-For 头获取
    if let Some(forwarded) = request.headers().get("X-Forwarded-For") {
        if let Ok(ip) = forwarded.to_str() {
            return ip.split(',').next().unwrap_or(ip).trim().to_string();
        }
    }

    // 然后尝试从 X-Real-IP 头获取
    if let Some(real_ip) = request.headers().get("X-Real-IP") {
        if let Ok(ip) = real_ip.to_str() {
            return ip.to_string();
        }
    }

    // 最后从连接信息获取
    request
        .extensions()
        .get::<axum::extract::connect_info::ConnectInfo<std::net::SocketAddr>>()
        .map(|info| info.ip().to_string())
        .unwrap_or_else(|| "127.0.0.1".to_string())
}

#[cfg(test)]
mod tests {
    use super::*;
    use crate::infrastructure::tenant_sdk::MockTenantSdk;

    #[test]
    fn test_build_request_context() {
        // 创建一个模拟请求
        let request = Request::builder()
            .header("X-Org-Id", "1")
            .header("X-App-Id", "test")
            .body(axum::body::Body::empty())
            .unwrap();

        let ctx = build_request_context(&request);
        // HTTP头名称会被规范化为小写
        assert_eq!(ctx.headers.get("x-org-id"), Some(&"1".to_string()));
        assert_eq!(ctx.headers.get("x-app-id"), Some(&"test".to_string()));
    }

    #[tokio::test]
    async fn test_tenant_context_middleware() {
        // 这个测试需要更复杂的设置，包括创建AppState和模拟请求
        // 这里只是一个示例框架
    }
}

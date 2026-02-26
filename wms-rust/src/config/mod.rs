//! 配置管理模块
//!
//! 负责加载和管理应用程序配置

use anyhow::{Context, Result};
use serde::Deserialize;
use std::env;

/// 应用配置
#[derive(Debug, Clone, Deserialize)]
pub struct AppConfig {
    /// 数据库连接URL
    pub database_url: String,
    /// 服务器端口
    pub server_port: u16,
    /// 服务器主机
    pub server_host: String,
    /// 是否使用模拟租户SDK
    pub use_mock_tenant: bool,
    /// 租户服务URL
    pub tenant_service_url: String,
    /// 租户服务API Key
    pub tenant_api_key: String,
    /// 日志级别
    pub log_level: String,
}

impl AppConfig {
    /// 从环境变量加载配置
    pub fn from_env() -> Result<Self> {
        // 加载 .env 文件（如果存在）
        let _ = dotenvy::dotenv();

        let config = Self {
            database_url: env::var("DATABASE_URL")
                .context("DATABASE_URL environment variable not set")?,
            server_port: env::var("SERVER_PORT")
                .unwrap_or_else(|_| "8080".to_string())
                .parse()
                .context("Invalid SERVER_PORT")?,
            server_host: env::var("SERVER_HOST").unwrap_or_else(|_| "0.0.0.0".to_string()),
            use_mock_tenant: env::var("USE_MOCK_TENANT")
                .unwrap_or_else(|_| "true".to_string())
                .parse()
                .context("Invalid USE_MOCK_TENANT")?,
            tenant_service_url: env::var("TENANT_SERVICE_URL").unwrap_or_default(),
            tenant_api_key: env::var("TENANT_API_KEY").unwrap_or_default(),
            log_level: env::var("LOG_LEVEL").unwrap_or_else(|_| "info".to_string()),
        };

        Ok(config)
    }

    /// 创建测试配置
    pub fn test_config() -> Self {
        Self {
            database_url: "mysql://root:password@localhost/wms_test".to_string(),
            server_port: 8080,
            server_host: "127.0.0.1".to_string(),
            use_mock_tenant: true,
            tenant_service_url: "".to_string(),
            tenant_api_key: "".to_string(),
            log_level: "debug".to_string(),
        }
    }
}

#[cfg(test)]
mod tests {
    use super::*;

    #[test]
    fn test_test_config() {
        let config = AppConfig::test_config();
        assert_eq!(config.server_port, 8080);
        assert!(config.use_mock_tenant);
    }
}

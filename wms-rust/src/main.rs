//! WMS 仓库管理系统 - Rust 实现
//!
//! 基于 Axum + Sea-ORM 的高性能Web API

use axum::{
    middleware as axum_middleware,
    routing::get,
    Router,
};
use sea_orm::Database;
use std::sync::Arc;
use tracing::{info, Level};
use tracing_subscriber;

mod config;
mod domain;
mod error;
mod handlers;
mod infrastructure;
mod middleware;
mod models;
mod services;

use config::AppConfig;
use handlers::{product::product_routes, sku::sku_routes, warehouse::warehouse_routes, storage::storage_routes};
use infrastructure::{
    app_state::AppState,
    tenant_sdk::MockTenantSdk,
};
use middleware::tenant::tenant_context_middleware;

/// 应用程序入口
#[tokio::main]
async fn main() -> anyhow::Result<()> {
    // 初始化日志
    tracing_subscriber::fmt()
        .with_max_level(Level::INFO)
        .init();

    info!("Starting WMS Server...");

    // 加载配置
    let config = AppConfig::from_env()?;
    info!("Configuration loaded successfully");

    // 连接数据库
    let db = Database::connect(&config.database_url).await?;
    info!("Database connected successfully");

    // 创建租户SDK（使用Mock实现）
    let tenant_sdk = Arc::new(MockTenantSdk::new());
    info!("Tenant SDK initialized");

    // 创建应用状态
    let state = Arc::new(AppState {
        db: db.clone(),
        tenant_sdk,
    });

    // 构建路由
    let app = Router::new()
        .route("/health", get(health_check))
        .merge(product_routes())
        .merge(sku_routes())
        .merge(warehouse_routes())
        .merge(storage_routes())
        .layer(axum_middleware::from_fn_with_state(
            state.clone(),
            tenant_context_middleware::<MockTenantSdk>,
        ))
        .with_state(state);

    // 启动服务
    let addr = format!("{}:{}", config.server_host, config.server_port);
    let listener = tokio::net::TcpListener::bind(&addr).await?;

    info!("WMS Server listening on http://{}", addr);

    axum::serve(listener, app).await?;

    Ok(())
}

/// 健康检查端点
async fn health_check() -> &'static str {
    "OK"
}

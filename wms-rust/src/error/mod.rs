//! 错误处理模块
//!
//! 定义应用程序统一的错误类型和处理逻辑

use axum::{
    http::StatusCode,
    response::{IntoResponse, Response},
    Json,
};
use serde_json::json;
use thiserror::Error;

/// 应用错误类型
#[derive(Error, Debug)]
pub enum AppError {
    #[error("资源未找到")]
    NotFound,

    #[error("请求参数错误: {0}")]
    BadRequest(String),

    #[error("数据库错误: {0}")]
    Database(#[from] sea_orm::DbErr),

    #[error("验证错误: {0}")]
    Validation(String),

    #[error("租户SDK错误: {0}")]
    TenantSdk(String),

    #[error("未授权")]
    Unauthorized,

    #[error("禁止访问")]
    Forbidden,

    #[error("内部服务器错误")]
    Internal,

    #[error("{0}")]
    Custom(String),
}

impl AppError {
    /// 获取错误对应的HTTP状态码
    pub fn status_code(&self) -> StatusCode {
        match self {
            AppError::NotFound => StatusCode::NOT_FOUND,
            AppError::BadRequest(_) => StatusCode::BAD_REQUEST,
            AppError::Database(_) => StatusCode::INTERNAL_SERVER_ERROR,
            AppError::Validation(_) => StatusCode::BAD_REQUEST,
            AppError::TenantSdk(_) => StatusCode::BAD_REQUEST,
            AppError::Unauthorized => StatusCode::UNAUTHORIZED,
            AppError::Forbidden => StatusCode::FORBIDDEN,
            AppError::Internal => StatusCode::INTERNAL_SERVER_ERROR,
            AppError::Custom(_) => StatusCode::INTERNAL_SERVER_ERROR,
        }
    }

    /// 获取错误代码
    pub fn error_code(&self) -> i32 {
        match self {
            AppError::NotFound => 404,
            AppError::BadRequest(_) => 400,
            AppError::Database(_) => 500,
            AppError::Validation(_) => 400,
            AppError::TenantSdk(_) => 400,
            AppError::Unauthorized => 401,
            AppError::Forbidden => 403,
            AppError::Internal => 500,
            AppError::Custom(_) => 500,
        }
    }
}

impl IntoResponse for AppError {
    fn into_response(self) -> Response {
        let status = self.status_code();
        let error_code = self.error_code();
        let message = self.to_string();

        let body = Json(json!({
            "code": error_code,
            "message": message,
            "data": null,
            "timestamp": chrono::Local::now().timestamp_millis(),
        }));

        (status, body).into_response()
    }
}

/// 结果类型别名
pub type Result<T> = std::result::Result<T, AppError>;

#[cfg(test)]
mod tests {
    use super::*;

    #[test]
    fn test_error_status_code() {
        assert_eq!(AppError::NotFound.status_code(), StatusCode::NOT_FOUND);
        assert_eq!(AppError::Unauthorized.status_code(), StatusCode::UNAUTHORIZED);
        assert_eq!(AppError::Internal.status_code(), StatusCode::INTERNAL_SERVER_ERROR);
    }

    #[test]
    fn test_error_code() {
        assert_eq!(AppError::NotFound.error_code(), 404);
        assert_eq!(AppError::BadRequest("test".to_string()).error_code(), 400);
    }
}

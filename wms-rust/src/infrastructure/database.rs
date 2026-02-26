//! 数据库连接池管理
//!
//! 负责创建和管理数据库连接池

use sea_orm::{Database, DatabaseConnection, DbErr};

/// 数据库连接池
#[derive(Debug, Clone)]
pub struct DatabasePool {
    connection: DatabaseConnection,
}

impl DatabasePool {
    /// 创建新的数据库连接池
    pub async fn new(database_url: &str) -> Result<Self, DbErr> {
        let connection = Database::connect(database_url).await?;
        Ok(Self { connection })
    }

    /// 获取数据库连接
    pub fn connection(&self) -> &DatabaseConnection {
        &self.connection
    }

    /// 关闭数据库连接
    pub async fn close(self) -> Result<(), DbErr> {
        self.connection.close().await
    }
}

/// 初始化数据库连接
pub async fn init_database(database_url: &str) -> Result<DatabaseConnection, DbErr> {
    Database::connect(database_url).await
}

#[cfg(test)]
mod tests {
    use super::*;

    // 注意：这些测试需要实际的数据库连接
    // 在实际项目中应该使用测试数据库或mock

    #[tokio::test]
    async fn test_database_pool_creation() {
        // 这是一个示例，实际测试需要使用有效的数据库URL
        // let pool = DatabasePool::new("mysql://root:password@localhost/wms_test").await;
        // assert!(pool.is_ok());
    }
}

# WMS 仓库管理系统 - Rust 实现

基于 Axum + Sea-ORM 的高性能 Web API，实现多租户数据隔离机制。

## 技术栈

- **Web 框架**: Axum 0.7
- **ORM**: Sea-ORM 0.12
- **数据库**: MySQL 8.0+
- **异步运行时**: Tokio
- **序列化**: Serde
- **验证**: Validator
- **错误处理**: Thiserror + Anyhow

## 项目结构

```
wms-rust/
├── src/
│   ├── main.rs              # 应用入口
│   ├── lib.rs               # 库导出
│   ├── config/              # 配置管理
│   │   └── mod.rs
│   ├── domain/              # 领域层
│   │   ├── mod.rs
│   │   └── tenant.rs        # 租户领域模型
│   ├── error/               # 错误处理
│   │   └── mod.rs
│   ├── handlers/            # HTTP 处理器 (Controller)
│   │   ├── mod.rs
│   │   └── product.rs       # 产品处理器
│   ├── infrastructure/      # 基础设施层
│   │   ├── mod.rs
│   │   ├── app_state.rs     # 应用状态
│   │   ├── database.rs      # 数据库连接
│   │   └── tenant_sdk.rs    # 租户SDK
│   ├── middleware/          # 中间件
│   │   ├── mod.rs
│   │   └── tenant.rs        # 租户上下文中间件
│   ├── models/              # 数据模型
│   │   ├── mod.rs
│   │   ├── base.rs          # 基础模型
│   │   ├── dto/             # DTOs
│   │   │   └── mod.rs
│   │   ├── entity/          # Sea-ORM 实体
│   │   │   ├── mod.rs
│   │   │   └── product.rs
│   │   ├── product.rs       # 产品领域模型
│   │   ├── sku.rs           # SKU领域模型
│   │   ├── warehouse.rs     # 仓库领域模型
│   │   └── inventory.rs     # 库存领域模型
│   ├── services/            # 业务逻辑层
│   │   ├── mod.rs
│   │   └── product_service.rs
│   └── utils/               # 工具函数
│       └── mod.rs
├── tests/                   # 集成测试
│   ├── common/
│   │   └── mod.rs
│   ├── tenant_sdk_test.rs
│   └── tenant_context_test.rs
├── sql/                     # SQL脚本
│   ├── 01_schema.sql        # 表结构
│   ├── 02_indexes.sql       # 索引
│   └── 03_seed.sql          # 初始数据
├── Cargo.toml
├── .env.example
└── README.md
```

## 核心特性

### 多租户数据隔离

- **租户字段**: 所有业务表包含 `org_id` 和 `org_tag` 字段用于数据隔离
- **租户SDK**: 通过 `TenantSdk` trait 获取租户信息，支持 Mock 和 HTTP 两种实现
- **中间件自动注入**: 租户上下文中间件自动从请求头提取租户信息
- **查询自动过滤**: `TenantScoped` trait 自动为查询添加租户过滤条件

### API 端点

#### 产品管理

| 方法 | 端点 | 描述 |
|------|------|------|
| GET | `/api/products` | 查询产品列表 |
| GET | `/api/products/:id` | 获取单个产品 |
| POST | `/api/products` | 创建产品 |
| PUT | `/api/products/:id` | 更新产品 |
| DELETE | `/api/products/:id` | 删除产品 |

#### 健康检查

| 方法 | 端点 | 描述 |
|------|------|------|
| GET | `/health` | 服务健康检查 |

## 快速开始

### 1. 环境准备

```bash
# 安装 Rust
curl --proto '=https' --tlsv1.2 -sSf https://sh.rustup.rs | sh

# 安装 sea-orm-cli
cargo install sea-orm-cli
```

### 2. 数据库配置

```bash
# 创建数据库
mysql -u root -p -e "CREATE DATABASE wms CHARACTER SET utf8mb4 COLLATE utf8mb4_unicode_ci;"

# 执行初始化脚本
mysql -u root -p wms < sql/01_schema.sql
mysql -u root -p wms < sql/02_indexes.sql
```

### 3. 配置环境变量

```bash
cp .env.example .env
# 编辑 .env 文件，配置数据库连接信息
```

### 4. 运行应用

```bash
# 开发模式
cargo run

# 生产模式
cargo run --release
```

### 5. 测试

```bash
# 运行所有测试
cargo test

# 运行特定测试
cargo test tenant

# 生成测试覆盖率报告
cargo tarpaulin --out Html --output-dir ./coverage
```

## 请求示例

### 创建产品

```bash
curl -X POST http://localhost:8080/api/products \
  -H "Content-Type: application/json" \
  -H "X-Org-Id: 1" \
  -H "X-App-Id: default" \
  -d '{
    "product_code": "P001",
    "name": "测试产品",
    "price": "99.99",
    "cost_price": "50.00"
  }'
```

### 查询产品列表

```bash
curl "http://localhost:8080/api/products?page=1&page_size=20" \
  -H "X-Org-Id: 1" \
  -H "X-App-Id: default"
```

### 获取单个产品

```bash
curl http://localhost:8080/api/products/1 \
  -H "X-Org-Id: 1" \
  -H "X-App-Id: default"
```

## 租户隔离机制

### 请求头要求

所有 API 请求（除健康检查外）必须包含以下请求头：

| 请求头 | 必填 | 说明 |
|--------|------|------|
| X-Org-Id | 是 | 组织ID |
| X-App-Id | 否 | 应用ID，默认为 "default" |
| X-User-Id | 否 | 用户ID |

### 数据隔离规则

1. **查询隔离**: 所有查询自动添加 `org_id = ?` 条件
2. **创建注入**: 创建数据时自动注入当前租户的 `org_id`
3. **更新隔离**: 只能更新属于当前租户的数据
4. **删除隔离**: 只能删除属于当前租户的数据

## 开发指南

### 添加新模块

1. 在 `src/models/entity/` 下创建 Sea-ORM 实体
2. 在 `src/models/dto/` 下添加 DTOs
3. 在 `src/services/` 下实现 Service 层
4. 在 `src/handlers/` 下实现 Handlers
5. 在 `src/main.rs` 中注册路由

### 代码规范

- 使用 `?` 操作符传播错误
- Service 返回 `Result<T, AppError>`
- 所有查询必须应用租户隔离
- 使用 `validator` 进行请求参数校验

## 许可证

MIT

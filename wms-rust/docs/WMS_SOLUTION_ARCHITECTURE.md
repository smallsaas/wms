# WMS 仓库管理系统 - 解决方案架构文档

## 1. 系统概述

### 1.1 项目背景
WMS (Warehouse Management System) 是一个基于 Java Spring Boot 开发的多模块仓库管理系统，现需要将其核心业务逻辑分析并重构为 Rust 实现版本，统一维护在 `wms-rust` 模块中。

### 1.2 核心目标
- 多租户数据隔离（org_id / org_tag）
- 完整的仓库进销存管理
- 支持多仓库、多储位
- 完整的库存追踪与盘点

---

## 2. 业务模块分析

### 2.1 模块划分

```
wms/
├── product/          # 产品管理模块
├── sku/              # SKU 管理模块  
├── warehouse/        # 仓库核心业务模块
└── wms-rust/         # Rust 重构版本
```

### 2.2 核心业务领域

#### 2.2.1 产品域 (Product Domain)
| 实体 | 说明 | 核心功能 |
|------|------|----------|
| Product | 产品主数据 | 产品信息管理、价格管理 |
| ProductCategory | 产品分类 | 树形分类结构 |
| Specification | 产品规格 | 规格名称与值 |
| SpecificationGroup | 规格组 | 规格分组管理 |
| ProductPhoto | 产品图片 | 多图片管理 |
| ProductUnit | 产品单位 | 计量单位 |
| Condition | 产品条件 | 筛选条件管理 |

#### 2.2.2 SKU 域 (SKU Domain)
| 实体 | 说明 | 核心功能 |
|------|------|----------|
| SkuProduct | SKU 主数据 | 具体可售卖的 SKU |
| SkuSpecification | SKU 规格关系 | SKU 与规格关联 |
| SkuSpecificationGroup | SKU 规格组 | SKU 规格分组 |
| SkuTag | SKU 标签 | 标签管理 |
| SkuTagRelation | SKU 标签关系 | SKU 与标签关联 |
| SkuPhoto | SKU 图片 | SKU 图片管理 |
| SkuUnit | SKU 单位 | SKU 计量单位 |
| SkuCondition | SKU 条件 | SKU 筛选条件 |
| SkuConditionRelation | SKU 条件关系 | 条件值关联 |
| SkuPriceHistory | 价格历史 | 价格变更记录 |

#### 2.2.3 仓库域 (Warehouse Domain)
| 实体 | 说明 | 核心功能 |
|------|------|----------|
| Warehouse | 仓库 | 仓库基础信息 |
| WarehouseSlot | 储位 | 仓库内具体储位 |
| Inventory | 库存 | 实时库存记录 |
| StorageIn | 入库单 | 入库业务单据 |
| StorageInItem | 入库明细 | 入库商品明细 |
| StorageOut | 出库单 | 出库业务单据 |
| StorageOutItem | 出库明细 | 出库商品明细 |
| Procurement | 采购单 | 采购业务管理 |
| Sales | 销售单 | 销售业务管理 |
| Refund | 退货单 | 退货业务管理 |
| Transfer | 调拨单 | 仓库间调拨 |
| Check | 盘点单 | 库存盘点 |
| CheckSku | 盘点明细 | 盘点商品明细 |
| Suppliers | 供应商 | 供应商管理 |
| Trader | 分销商 | 分销商/客户管理 |

---

## 3. 核心业务逻辑

### 3.1 库存管理流程

```
┌─────────────┐     ┌─────────────┐     ┌─────────────┐
│  采购订单    │────▶│  入库作业    │────▶│  库存更新    │
│ Procurement │     │ StorageIn   │     │  Inventory  │
└─────────────┘     └─────────────┘     └─────────────┘
                                               │
                                               ▼
┌─────────────┐     ┌─────────────┐     ┌─────────────┐
│  销售订单    │◀────│  出库作业    │◀────│  库存扣减    │
│   Sales     │     │ StorageOut  │     │  Inventory  │
└─────────────┘     └─────────────┘     └─────────────┘
```

### 3.2 业务流程详解

#### 3.2.1 采购入库流程
1. 创建采购单 (Procurement)
2. 采购单审批通过
3. 创建入库单 (StorageIn)，关联采购单
4. 入库单明细 (StorageInItem) 记录入库 SKU 及数量
5. 入库完成后更新库存 (Inventory)
6. 更新 SKU 成本价格

#### 3.2.2 销售出库流程
1. 创建销售单 (Sales)
2. 销售单审批通过
3. 创建出库单 (StorageOut)，关联销售单
4. 出库单明细 (StorageOutItem) 记录出库 SKU 及数量
5. 出库完成后扣减库存 (Inventory)

#### 3.2.3 库存调拨流程
1. 创建调拨单 (Transfer)
2. 调出仓库创建出库单 (StorageOut)
3. 调入仓库创建入库单 (StorageIn)
4. 更新双方库存

#### 3.2.4 库存盘点流程
1. 创建盘点单 (Check)
2. 盘点单明细 (CheckSku) 记录各 SKU 应有库存
3. 实际盘点录入实际库存
4. 计算盈亏 (profit_lost)
5. 根据盈亏创建出入库单调整库存

### 3.3 库存计算逻辑

```rust
// 可用库存 = 实际库存 - 预购量
valid_sku = actual_quantity - advance_quantities

// 在途库存
transmit_quantities = 已采购未入库数量

// 盘点盈亏
profit_lost = fact_quantities - deserved_quantities
```

---

## 4. 数据隔离设计

### 4.1 多租户字段
所有业务表均包含以下隔离字段：

```sql
org_id      BIGINT      -- 组织ID，用于数据隔离
org_tag     VARCHAR(64) -- 组织标识，参考 Docker tag 设计
```

### 4.2 隔离策略
- **物理隔离**：不同组织数据存储在同一表，通过 org_id 区分
- **查询隔离**：所有查询自动添加 org_id 过滤条件
- **级联隔离**：关联查询时同时检查关联表的 org_id

---

## 5. 技术架构

### 5.1 原 Java 技术栈
- Spring Boot 2.x
- MyBatis-Plus (ORM)
- MySQL (数据库)
- Maven (构建)

### 5.2 Rust 目标技术栈
- **Web 框架**: Actix-web / Axum
- **ORM**: Sea-ORM / Diesel
- **数据库**: PostgreSQL
- **构建**: Cargo
- **异步**: Tokio

### 5.3 模块结构

```
wms-rust/
├── docs/                           # 文档
│   └── WMS_SOLUTION_ARCHITECTURE.md
├── sql/                            # 数据库脚本
│   ├── 01_schema.sql              # 表结构
│   ├── 02_indexes.sql             # 索引
│   └── 03_seed.sql                # 初始数据
├── src/
│   ├── main.rs                    # 入口
│   ├── config.rs                  # 配置
│   ├── db.rs                      # 数据库连接
│   ├── models/                    # 领域模型
│   │   ├── mod.rs
│   │   ├── product.rs             # 产品模型
│   │   ├── sku.rs                 # SKU 模型
│   │   ├── warehouse.rs           # 仓库模型
│   │   ├── inventory.rs           # 库存模型
│   │   ├── transaction.rs         # 出入库模型
│   │   ├── procurement.rs         # 采购模型
│   │   ├── sales.rs               # 销售模型
│   │   └── check.rs               # 盘点模型
│   ├── services/                  # 业务服务
│   │   ├── mod.rs
│   │   ├── product_service.rs
│   │   ├── inventory_service.rs
│   │   └── transaction_service.rs
│   └── controllers/               # API 控制器
│       ├── mod.rs
│       └── api/
└── Cargo.toml
```

---

## 6. 关键业务规则

### 6.1 库存规则
- 库存不能为负数（valid_sku >= 0）
- 出库时检查可用库存是否充足
- 库存预警：当 valid_sku < min_inventory 时触发预警

### 6.2 单据规则
- 单据编号唯一，格式：前缀 + 日期 + 序号
- 单据状态流转：草稿 -> 待审核 -> 已审核 -> 已完成
- 已完成的单据不可修改

### 6.3 价格规则
- 成本价采用移动加权平均算法
- 销售价可以低于成本价（促销场景）
- 价格变更记录历史

---

## 7. 状态定义

### 7.1 入库单状态 (StorageInStatus)
- `DRAFT` - 草稿
- `PENDING` - 待审核
- `APPROVED` - 已审核
- `COMPLETED` - 已完成
- `CANCELLED` - 已取消

### 7.2 出库单状态 (StorageOutStatus)
- `DRAFT` - 草稿
- `PENDING` - 待审核
- `APPROVED` - 已审核
- `COMPLETED` - 已完成
- `CANCELLED` - 已取消

### 7.3 采购单状态 (ProcurementStatus)
- `DRAFT` - 草稿
- `PENDING` - 待审核
- `APPROVED` - 已审核
- `PARTIAL` - 部分入库
- `COMPLETED` - 已完成
- `CANCELLED` - 已取消

### 7.4 销售单状态 (SalesStatus)
- `DRAFT` - 草稿
- `PENDING` - 待审核
- `APPROVED` - 已审核
- `PARTIAL` - 部分出库
- `COMPLETED` - 已完成
- `CANCELLED` - 已取消

### 7.5 盘点单状态 (CheckStatus)
- `DRAFT` - 草稿
- `IN_PROGRESS` - 盘点中
- `COMPLETED` - 已完成
- `ADJUSTED` - 已调整

---

## 8. 接口设计原则

### 8.1 RESTful API 规范
- GET /api/v1/{resource} - 列表查询
- GET /api/v1/{resource}/{id} - 详情查询
- POST /api/v1/{resource} - 创建
- PUT /api/v1/{resource}/{id} - 更新
- DELETE /api/v1/{resource}/{id} - 删除

### 8.2 统一响应格式
```json
{
  "code": 200,
  "message": "success",
  "data": {},
  "timestamp": 1700000000000
}
```

---

## 9. 部署架构

### 9.1 单体部署
```
┌─────────────────────────────────────┐
│           Load Balancer             │
└─────────────┬───────────────────────┘
              │
┌─────────────▼───────────────────────┐
│         WMS Rust Application        │
│  ┌─────────┐ ┌─────────┐ ┌──────┐  │
│  │ Product │ │   SKU   │ │Warehouse│ │
│  │ Module  │ │ Module  │ │ Module │  │
│  └─────────┘ └─────────┘ └──────┘  │
└─────────────┬───────────────────────┘
              │
┌─────────────▼───────────────────────┐
│       PostgreSQL Database           │
└─────────────────────────────────────┘
```

---

## 10. 迁移计划

### Phase 1: 基础架构
- [ ] 数据库表结构迁移
- [ ] 基础模型定义
- [ ] 数据库连接池配置

### Phase 2: 产品域
- [ ] Product 模块
- [ ] ProductCategory 模块
- [ ] Specification 模块

### Phase 3: SKU 域
- [ ] SkuProduct 模块
- [ ] SkuSpecification 模块
- [ ] SkuTag 模块

### Phase 4: 仓库域
- [ ] Warehouse 模块
- [ ] Inventory 模块
- [ ] StorageIn/StorageOut 模块

### Phase 5: 业务域
- [ ] Procurement 模块
- [ ] Sales 模块
- [ ] Transfer 模块
- [ ] Check 模块

### Phase 6: 集成测试
- [ ] 端到端测试
- [ ] 性能测试
- [ ] 数据迁移验证

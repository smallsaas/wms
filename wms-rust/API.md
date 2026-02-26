# WMS API 文档

## 基础信息

- **Base URL**: `http://localhost:8080`
- **Content-Type**: `application/json`
- **认证方式**: Header 传递租户信息

## 请求头

| 请求头 | 必填 | 说明 |
|--------|------|------|
| X-Org-Id | 是 | 组织ID |
| X-App-Id | 否 | 应用ID，默认 "default" |
| X-User-Id | 否 | 用户ID |

## 通用响应格式

```json
{
  "code": 200,
  "message": "success",
  "data": { ... },
  "timestamp": 1700000000000
}
```

## API 端点

### 健康检查

#### GET /health
检查服务健康状态

**响应**:
```
OK
```

---

### 产品管理

#### GET /api/products
查询产品列表

**查询参数**:
| 参数 | 类型 | 说明 |
|------|------|------|
| keyword | string | 关键字搜索（名称/编码） |
| category_id | integer | 分类ID |
| status | string | 状态: ACTIVE/INACTIVE |
| bar_code | string | 条形码 |
| page | integer | 页码，默认1 |
| page_size | integer | 每页大小，默认20 |

**响应**:
```json
{
  "code": 200,
  "data": {
    "data": [...],
    "total": 100,
    "page": 1,
    "page_size": 20,
    "pages": 5
  }
}
```

#### GET /api/products/:id
获取单个产品

#### POST /api/products
创建产品

#### PUT /api/products/:id
更新产品

#### DELETE /api/products/:id
删除产品

---

### SKU 管理

#### GET /api/skus
查询SKU列表

**查询参数**:
| 参数 | 类型 | 说明 |
|------|------|------|
| product_id | integer | 产品ID |
| keyword | string | 关键字搜索 |
| status | string | 状态 |
| page | integer | 页码 |
| page_size | integer | 每页大小 |

#### GET /api/skus/:id
获取SKU详情

#### POST /api/skus
创建SKU

#### PUT /api/skus/:id
更新SKU

#### DELETE /api/skus/:id
删除SKU

---

### 仓库管理

#### GET /api/warehouses
查询仓库列表

#### GET /api/warehouses/:id
获取单个仓库

#### POST /api/warehouses
创建仓库

#### PUT /api/warehouses/:id
更新仓库

#### DELETE /api/warehouses/:id
删除仓库

---

### 储位管理

#### GET /api/warehouses/:id/slots
获取仓库的储位列表

#### POST /api/warehouses/:id/slots
创建储位

---

### 库存管理

#### GET /api/inventory
查询库存列表

**查询参数**:
| 参数 | 类型 | 说明 |
|------|------|------|
| warehouse_id | integer | 仓库ID |
| slot_id | integer | 储位ID |
| sku_id | integer | SKU ID |
| page | integer | 页码 |
| page_size | integer | 每页大小 |

#### POST /api/inventory
调整库存

**请求体**:
```json
{
  "warehouse_id": 1,
  "slot_id": 1,
  "sku_id": 1,
  "adjust_quantity": 100,
  "reason": "采购入库"
}
```

#### GET /api/inventory/warnings
获取库存预警列表

---

### 入库单管理

#### GET /api/storage/in
查询入库单列表

**查询参数**:
| 参数 | 类型 | 说明 |
|------|------|------|
| transaction_code | string | 入库单号 |
| transaction_type | string | 类型: PROCUREMENT/RETURN/TRANSFER/ADJUST |
| warehouse_id | integer | 仓库ID |
| status | string | 状态 |
| page | integer | 页码 |
| page_size | integer | 每页大小 |

#### GET /api/storage/in/:id
获取入库单详情

#### POST /api/storage/in
创建入库单

**请求体**:
```json
{
  "transaction_type": "PROCUREMENT",
  "warehouse_id": 1,
  "slot_id": 1,
  "batch_no": "BATCH001",
  "note": "采购入库",
  "items": [
    {
      "sku_id": 1,
      "quantity": 100,
      "unit_price": "50.00",
      "note": "备注"
    }
  ]
}
```

#### PUT /api/storage/in/:id
更新入库单

#### DELETE /api/storage/in/:id
删除入库单

#### POST /api/storage/in/:id/approve
审核入库单

#### POST /api/storage/in/:id/complete
完成入库单（确认入库，更新库存）

---

### 出库单管理

#### GET /api/storage/out
查询出库单列表

#### GET /api/storage/out/:id
获取出库单详情

#### POST /api/storage/out
创建出库单

**请求体**:
```json
{
  "transaction_type": "SALES",
  "warehouse_id": 1,
  "slot_id": 1,
  "note": "销售出库",
  "items": [
    {
      "sku_id": 1,
      "quantity": 50,
      "unit_price": "99.00"
    }
  ]
}
```

#### PUT /api/storage/out/:id
更新出库单

#### DELETE /api/storage/out/:id
删除出库单

#### POST /api/storage/out/:id/approve
审核出库单

#### POST /api/storage/out/:id/complete
完成出库单（确认出库，扣减库存）

---

### 盘点单管理

#### GET /api/storage/check
查询盘点单列表

#### GET /api/storage/check/:id
获取盘点单详情

#### POST /api/storage/check
创建盘点单

**请求体**:
```json
{
  "warehouse_id": 1,
  "note": "月度盘点"
}
```

#### PUT /api/storage/check/:id
更新盘点单

#### DELETE /api/storage/check/:id
删除盘点单

#### POST /api/storage/check/:id/adjust
盘点调整

**请求体**:
```json
{
  "items": [
    {
      "item_id": 1,
      "actual_quantity": 95
    }
  ]
}
```

---

## 业务流程

### 入库流程

```
创建入库单 (DRAFT) 
    ↓
审核入库单 (APPROVED)
    ↓
完成入库单 (COMPLETED) → 更新库存
```

### 出库流程

```
创建出库单 (DRAFT)
    ↓
审核出库单 (APPROVED)
    ↓
完成出库单 (COMPLETED) → 扣减库存
```

### 盘点流程

```
创建盘点单 (DRAFT)
    ↓
开始盘点 (IN_PROGRESS)
    ↓
录入盘点数据 (COMPLETED)
    ↓
盘点调整 (ADJUSTED) → 调整库存
```

---

## 错误码

| 状态码 | 说明 |
|--------|------|
| 200 | 成功 |
| 400 | 请求参数错误 |
| 401 | 未授权 |
| 403 | 禁止访问 |
| 404 | 资源未找到 |
| 500 | 服务器内部错误 |

## 数据隔离说明

所有 API 都实现了多租户数据隔离：

1. **查询隔离**: 自动过滤当前租户的数据
2. **创建注入**: 自动填充 `org_id` 字段
3. **更新隔离**: 只能更新当前租户的数据
4. **删除隔离**: 只能删除当前租户的数据

示例：
```bash
# 组织1 创建入库单
curl -X POST http://localhost:8080/api/storage/in \
  -H "X-Org-Id: 1" \
  -H "Content-Type: application/json" \
  -d '{
    "transaction_type": "PROCUREMENT",
    "warehouse_id": 1,
    "items": [{"sku_id": 1, "quantity": 100}]
  }'

# 组织2 无法访问组织1的入库单
curl http://localhost:8080/api/storage/in/1 \
  -H "X-Org-Id: 2"
# 返回 404
```

---

## 库存计算

### 库存字段说明

| 字段 | 说明 |
|------|------|
| valid_sku | 可用库存 |
| advance_quantities | 预购量（已下单未出库）|
| transmit_quantities | 在途量（已采购未入库）|
| order_count | 订单占用量 |

### 可用库存计算

```
实际可用库存 = valid_sku - advance_quantities - order_count
```

### 入库操作

```
valid_sku += 入库数量
```

### 出库操作

```
valid_sku -= 出库数量
```

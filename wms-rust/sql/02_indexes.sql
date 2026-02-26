-- ========================================================
-- WMS 仓库管理系统 - 数据库索引优化
-- ========================================================

-- --------------------------------------------------------
-- 1. 产品域索引
-- --------------------------------------------------------

-- 产品表复合索引
CREATE INDEX idx_product_org_status ON wms_product(org_id, status);
CREATE INDEX idx_product_org_category ON wms_product(org_id, product_category_id);
CREATE INDEX idx_product_search ON wms_product(org_id, name, search_key_word);

-- 产品分类层级索引
CREATE INDEX idx_category_org_pid ON wms_product_category(org_id, pid);

-- 规格查询索引
CREATE INDEX idx_spec_org_product ON wms_specification(org_id, product_id, group_id);

-- --------------------------------------------------------
-- 2. SKU 域索引
-- --------------------------------------------------------

-- SKU 表复合索引
CREATE INDEX idx_sku_org_status ON wms_sku_product(org_id, status);
CREATE INDEX idx_sku_org_product ON wms_sku_product(org_id, product_id);
CREATE INDEX idx_sku_search ON wms_sku_product(org_id, sku_name, search_key_word);

-- SKU 规格关系索引
CREATE INDEX idx_sku_spec_relation ON wms_sku_specification(org_id, sku_id, specification_id);

-- SKU 标签关系索引
CREATE INDEX idx_sku_tag_relation ON wms_sku_tag_relation(org_id, tag_id);

-- 价格历史查询索引
CREATE INDEX idx_price_history_sku ON wms_sku_price_history(org_id, sku_id, price_type, created_at);

-- --------------------------------------------------------
-- 3. 仓库域索引
-- --------------------------------------------------------

-- 库存查询索引（核心高频查询）
CREATE INDEX idx_inventory_org_warehouse ON wms_inventory(org_id, warehouse_id, sku_id);
CREATE INDEX idx_inventory_org_slot ON wms_inventory(org_id, slot_id, sku_id);
CREATE INDEX idx_inventory_sku ON wms_inventory(org_id, sku_id);

-- 库存预警查询
CREATE INDEX idx_inventory_warning ON wms_inventory(org_id, valid_sku, min_inventory)
    WHERE valid_sku < min_inventory;

-- 储位查询
CREATE INDEX idx_slot_org_warehouse ON wms_warehouse_slot(org_id, warehouse_id);

-- --------------------------------------------------------
-- 4. 业务单据索引
-- --------------------------------------------------------

-- 采购单查询索引
CREATE INDEX idx_procurement_org_status ON wms_procurement(org_id, procure_status);
CREATE INDEX idx_procurement_org_supplier ON wms_procurement(org_id, supplier_id);
CREATE INDEX idx_procurement_time ON wms_procurement(org_id, procurement_time);

-- 采购明细索引
CREATE INDEX idx_procurement_item_sku ON wms_procurement_item(org_id, sku_id);

-- 销售单查询索引
CREATE INDEX idx_sales_org_status ON wms_sales(org_id, sales_status);
CREATE INDEX idx_sales_org_trader ON wms_sales(org_id, trader_id);
CREATE INDEX idx_sales_time ON wms_sales(org_id, sales_time);

-- 销售明细索引
CREATE INDEX idx_sales_item_sku ON wms_sales_item(org_id, sku_id);

-- 入库单查询索引
CREATE INDEX idx_storage_in_org_status ON wms_storage_in(org_id, status);
CREATE INDEX idx_storage_in_org_warehouse ON wms_storage_in(org_id, warehouse_id);
CREATE INDEX idx_storage_in_time ON wms_storage_in(org_id, storage_in_time);
CREATE INDEX idx_storage_in_type ON wms_storage_in(org_id, transaction_type);

-- 入库明细索引
CREATE INDEX idx_storage_in_item_sku ON wms_storage_in_item(org_id, sku_id);
CREATE INDEX idx_storage_in_item_time ON wms_storage_in_item(org_id, transaction_time);

-- 出库单查询索引
CREATE INDEX idx_storage_out_org_status ON wms_storage_out(org_id, status);
CREATE INDEX idx_storage_out_org_warehouse ON wms_storage_out(org_id, warehouse_id);
CREATE INDEX idx_storage_out_time ON wms_storage_out(org_id, storage_out_time);
CREATE INDEX idx_storage_out_type ON wms_storage_out(org_id, transaction_type);

-- 出库明细索引
CREATE INDEX idx_storage_out_item_sku ON wms_storage_out_item(org_id, sku_id);
CREATE INDEX idx_storage_out_item_time ON wms_storage_out_item(org_id, transaction_time);

-- 调拨单查询索引
CREATE INDEX idx_transfer_org_status ON wms_transfer(org_id, status);
CREATE INDEX idx_transfer_from_warehouse ON wms_transfer(org_id, from_warehouse_id);
CREATE INDEX idx_transfer_to_warehouse ON wms_transfer(org_id, to_warehouse_id);

-- 盘点单查询索引
CREATE INDEX idx_check_org_status ON wms_check(org_id, status);
CREATE INDEX idx_check_org_warehouse ON wms_check(org_id, warehouse_id);

-- 盘点明细索引
CREATE INDEX idx_check_sku_sku ON wms_check_sku(org_id, sku_id);

-- --------------------------------------------------------
-- 5. 供应商/客户索引
-- --------------------------------------------------------

-- 供应商查询
CREATE INDEX idx_suppliers_org_status ON wms_suppliers(org_id, supplier_status);

-- 供应商产品关系
CREATE INDEX idx_suppliers_product_sku ON wms_suppliers_product(org_id, sku_id);

-- 客户查询
CREATE INDEX idx_trader_org_status ON wms_trader(org_id, trader_status);

-- --------------------------------------------------------
-- 6. 全文搜索索引 (MySQL 5.7+)
-- --------------------------------------------------------

-- 产品名称全文索引
ALTER TABLE wms_product ADD FULLTEXT INDEX ft_product_name (name, search_key_word);

-- SKU 名称全文索引
ALTER TABLE wms_sku_product ADD FULLTEXT INDEX ft_sku_name (sku_name, search_key_word);

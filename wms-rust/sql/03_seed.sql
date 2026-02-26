-- ========================================================
-- WMS 仓库管理系统 - 初始数据
-- ========================================================

-- --------------------------------------------------------
-- 1. 系统配置数据
-- --------------------------------------------------------

-- 入库类型
INSERT INTO wms_config (config_key, config_value, config_group, description) VALUES
('storage_in_type', 'PROCUREMENT', 'storage_in', '采购入库'),
('storage_in_type', 'RETURN', 'storage_in', '退货入库'),
('storage_in_type', 'TRANSFER', 'storage_in', '调拨入库'),
('storage_in_type', 'ADJUST', 'storage_in', '调整入库');

-- 出库类型
INSERT INTO wms_config (config_key, config_value, config_group, description) VALUES
('storage_out_type', 'SALES', 'storage_out', '销售出库'),
('storage_out_type', 'RETURN', 'storage_out', '退货出库'),
('storage_out_type', 'TRANSFER', 'storage_out', '调拨出库'),
('storage_out_type', 'ADJUST', 'storage_out', '调整出库');

-- 单据状态
INSERT INTO wms_config (config_key, config_value, config_group, description) VALUES
('document_status', 'DRAFT', 'status', '草稿'),
('document_status', 'PENDING', 'status', '待审核'),
('document_status', 'APPROVED', 'status', '已审核'),
('document_status', 'PARTIAL', 'status', '部分完成'),
('document_status', 'COMPLETED', 'status', '已完成'),
('document_status', 'CANCELLED', 'status', '已取消');

-- --------------------------------------------------------
-- 2. 示例组织数据 (org_id = 1)
-- --------------------------------------------------------

-- 产品分类
INSERT INTO wms_product_category (org_id, category_name, category_code, pid, category_description) VALUES
(1, '电子产品', 'ELEC', 0, '电子数码产品'),
(1, '手机', 'PHONE', 1, '智能手机'),
(1, '电脑', 'COMPUTER', 1, '笔记本电脑'),
(1, '服装', 'CLOTHES', 0, '服装鞋帽'),
(1, '男装', 'MEN', 4, '男士服装'),
(1, '女装', 'WOMEN', 4, '女士服装');

-- 仓库
INSERT INTO wms_warehouse (org_id, warehouse_code, warehouse_name, warehouse_pcd, warehouse_address, status) VALUES
(1, 'WH001', '主仓库', '北京市,北京市,朝阳区', '朝阳路1号', 'ACTIVE'),
(1, 'WH002', '分仓库', '上海市,上海市,浦东新区', '浦东大道2号', 'ACTIVE'),
(1, 'WH003', '备用仓库', '广州市,广东省,天河区', '天河路3号', 'ACTIVE');

-- 储位
INSERT INTO wms_warehouse_slot (org_id, slot_code, slot_name, warehouse_id, slot_note) VALUES
(1, 'A-01-01', 'A区01排01位', 1, '电子产品区'),
(1, 'A-01-02', 'A区01排02位', 1, '电子产品区'),
(1, 'B-01-01', 'B区01排01位', 1, '服装区'),
(1, 'B-01-02', 'B区01排02位', 1, '服装区');

-- 供应商
INSERT INTO wms_suppliers (org_id, supplier_name, supplier_code, supplier_pcd, supplier_address, supplier_contact_name, supplier_contact_cell_phone, supplier_status) VALUES
(1, '华为技术有限公司', 'SUP001', '广东省,深圳市,南山区', '华为基地', '张经理', '13800138001', 'ACTIVE'),
(1, '联想集团有限公司', 'SUP002', '北京市,北京市,海淀区', '联想大厦', '李经理', '13800138002', 'ACTIVE'),
(1, '浙江淘宝网络有限公司', 'SUP003', '浙江省,杭州市,余杭区', '淘宝城', '王经理', '13800138003', 'ACTIVE');

-- 分销商/客户
INSERT INTO wms_trader (org_id, trader_name, trader_code, trader_pcd, trader_address, trader_contact_name, trader_contact_cell_phone, trader_status) VALUES
(1, '京东自营', 'TRD001', '北京市,北京市,大兴区', '京东总部', '刘经理', '13900139001', 'ACTIVE'),
(1, '天猫超市', 'TRD002', '浙江省,杭州市,余杭区', '阿里巴巴园区', '陈经理', '13900139002', 'ACTIVE'),
(1, '苏宁易购', 'TRD003', '江苏省,南京市,玄武区', '苏宁总部', '赵经理', '13900139003', 'ACTIVE');

-- --------------------------------------------------------
-- 3. 示例产品数据
-- --------------------------------------------------------

-- 产品
INSERT INTO wms_product (org_id, product_category_id, product_code, name, price, suggested_price, cost_price, status, bar_code, description) VALUES
(1, 2, 'P001', '华为 Mate 60 Pro', 6999.00, 6999.00, 5000.00, 'ACTIVE', '6901443400587', '华为旗舰手机'),
(1, 2, 'P002', 'iPhone 15 Pro', 8999.00, 8999.00, 7000.00, 'ACTIVE', '194253401179', '苹果旗舰手机'),
(1, 3, 'P003', 'MacBook Pro 14', 14999.00, 14999.00, 12000.00, 'ACTIVE', '194253402345', '苹果笔记本电脑'),
(1, 5, 'P004', '男士T恤', 99.00, 129.00, 45.00, 'ACTIVE', '1234567890123', '纯棉男士T恤'),
(1, 6, 'P005', '女士连衣裙', 299.00, 399.00, 120.00, 'ACTIVE', '1234567890124', '时尚女士连衣裙');

-- SKU
INSERT INTO wms_sku_product (org_id, product_id, sku_code, sku_name, sku_price, cost_price, status, bar_code, spec) VALUES
(1, 1, 'SKU001', '华为 Mate 60 Pro 12GB+512GB 黑色', 6999.00, 5000.00, 'ACTIVE', '6901443400587-01', '12GB+512GB/黑色'),
(1, 1, 'SKU002', '华为 Mate 60 Pro 12GB+512GB 白色', 6999.00, 5000.00, 'ACTIVE', '6901443400587-02', '12GB+512GB/白色'),
(1, 2, 'SKU003', 'iPhone 15 Pro 256GB 黑色', 8999.00, 7000.00, 'ACTIVE', '194253401179-01', '256GB/黑色钛金属'),
(1, 3, 'SKU004', 'MacBook Pro 14 M3 8GB+512GB', 14999.00, 12000.00, 'ACTIVE', '194253402345-01', 'M3/8GB+512GB'),
(1, 4, 'SKU005', '男士T恤 L码 白色', 99.00, 45.00, 'ACTIVE', '1234567890123-01', 'L码/白色'),
(1, 4, 'SKU006', '男士T恤 XL码 黑色', 99.00, 45.00, 'ACTIVE', '1234567890123-02', 'XL码/黑色'),
(1, 5, 'SKU007', '女士连衣裙 M码 红色', 299.00, 120.00, 'ACTIVE', '1234567890124-01', 'M码/红色');

-- 初始库存
INSERT INTO wms_inventory (org_id, warehouse_id, slot_id, sku_id, max_inventory, min_inventory, valid_sku, advance_quantities, transmit_quantities) VALUES
(1, 1, 1, 1, 1000, 50, 500, 0, 100),
(1, 1, 1, 2, 1000, 50, 300, 0, 50),
(1, 1, 1, 3, 500, 30, 200, 0, 0),
(1, 1, 2, 4, 200, 10, 80, 0, 20),
(1, 1, 3, 5, 2000, 200, 1500, 0, 500),
(1, 1, 3, 6, 2000, 200, 1200, 0, 300),
(1, 1, 4, 7, 1500, 150, 800, 0, 200);

-- --------------------------------------------------------
-- 4. 示例单据数据
-- --------------------------------------------------------

-- 采购单
INSERT INTO wms_procurement (org_id, procurement_code, supplier_id, procurement_total, procurement_time, procure_status, originator_name) VALUES
(1, 'CG202401010001', 1, 500000.00, '2024-01-01', 'COMPLETED', '管理员'),
(1, 'CG202401150001', 2, 300000.00, '2024-01-15', 'APPROVED', '管理员');

-- 采购明细
INSERT INTO wms_procurement_item (org_id, procurement_id, product_id, sku_id, quantity, unit_price, total_price) VALUES
(1, 1, 1, 1, 100, 5000.00, 500000.00),
(1, 2, 3, 4, 20, 12000.00, 240000.00),
(1, 2, 4, 5, 1000, 45.00, 45000.00);

-- 销售单
INSERT INTO wms_sales (org_id, sales_code, trader_id, sales_total, sales_time, sales_status, originator_name) VALUES
(1, 'XS202401020001', 1, 699900.00, '2024-01-02', 'COMPLETED', '管理员'),
(1, 'XS202401200001', 2, 299000.00, '2024-01-20', 'APPROVED', '管理员');

-- 销售明细
INSERT INTO wms_sales_item (org_id, sales_id, product_id, sku_id, quantity, unit_price, total_price) VALUES
(1, 1, 1, 1, 100, 6999.00, 699900.00),
(1, 2, 4, 5, 1000, 99.00, 99000.00),
(1, 2, 5, 7, 500, 299.00, 149500.00);

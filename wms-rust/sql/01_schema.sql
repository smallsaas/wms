-- ========================================================
-- WMS 仓库管理系统 - 数据库表结构
-- 基于 Java WMS 业务逻辑重构
-- 支持多租户数据隔离 (org_id, org_tag)
-- ========================================================

-- --------------------------------------------------------
-- 1. 产品域 (Product Domain)
-- --------------------------------------------------------

-- 产品分类表
CREATE TABLE wms_product_category (
    id                  BIGINT PRIMARY KEY AUTO_INCREMENT,
    org_id              BIGINT NOT NULL DEFAULT 0 COMMENT '组织ID，用于数据隔离',
    org_tag             VARCHAR(64) COMMENT '组织标识',
    category_name       VARCHAR(255) NOT NULL COMMENT '分类名称',
    category_code       VARCHAR(100) COMMENT '分类编码',
    pid                 BIGINT DEFAULT 0 COMMENT '父级ID，0为顶级',
    category_description VARCHAR(500) COMMENT '分类描述',
    created_at          DATETIME DEFAULT CURRENT_TIMESTAMP,
    updated_at          DATETIME DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP,

    INDEX idx_org_id (org_id),
    INDEX idx_pid (pid),
    INDEX idx_category_code (org_id, category_code)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COMMENT='产品分类表';

-- 产品主表
CREATE TABLE wms_product (
    id                  BIGINT PRIMARY KEY AUTO_INCREMENT,
    org_id              BIGINT NOT NULL DEFAULT 0 COMMENT '组织ID，用于数据隔离',
    org_tag             VARCHAR(64) COMMENT '组织标识',
    product_category_id BIGINT COMMENT '产品分类ID',
    product_code        VARCHAR(100) NOT NULL COMMENT '产品编码',
    name                VARCHAR(255) NOT NULL COMMENT '产品名称',
    english_name        VARCHAR(255) COMMENT '外文名称',
    weight              VARCHAR(50) COMMENT '重量',
    volume              VARCHAR(50) COMMENT '体积',
    spec                VARCHAR(100) COMMENT '规格',
    readjust_cost_price DECIMAL(18,4) DEFAULT 0 COMMENT '入库成本调整',
    product_standard    VARCHAR(100) COMMENT '产品标准',
    price               DECIMAL(18,4) DEFAULT 0 COMMENT '零售价',
    suggested_price     DECIMAL(18,4) DEFAULT 0 COMMENT '建议零售价',
    cost_price          DECIMAL(18,4) DEFAULT 0 COMMENT '成本价',
    specification       VARCHAR(500) COMMENT '规格描述',
    quantities          INT DEFAULT 0 COMMENT '总数量',
    stock_cost          DECIMAL(18,4) DEFAULT 0 COMMENT '库存成本',
    purchase_advance    INT DEFAULT 0 COMMENT '预购数量',
    status              VARCHAR(20) DEFAULT 'ACTIVE' COMMENT '状态: ACTIVE, INACTIVE',
    sort_value          INT DEFAULT 0 COMMENT '排序值',
    search_key_word     VARCHAR(500) COMMENT '搜索关键字',
    bar_code            VARCHAR(100) COMMENT '条形码',
    description         TEXT COMMENT '产品描述',
    field1              VARCHAR(255) COMMENT '保留字段1',
    field4              VARCHAR(255) COMMENT '保留字段4',
    field5              VARCHAR(255) COMMENT '保留字段5',
    created_at          DATETIME DEFAULT CURRENT_TIMESTAMP,
    updated_at          DATETIME DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP,

    UNIQUE KEY uk_product_code (org_id, product_code),
    INDEX idx_org_id (org_id),
    INDEX idx_category_id (product_category_id),
    INDEX idx_status (status),
    INDEX idx_bar_code (bar_code)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COMMENT='产品主表';

-- 产品规格组表
CREATE TABLE wms_specification_group (
    id                  BIGINT PRIMARY KEY AUTO_INCREMENT,
    org_id              BIGINT NOT NULL DEFAULT 0 COMMENT '组织ID',
    org_tag             VARCHAR(64) COMMENT '组织标识',
    group_name          VARCHAR(100) NOT NULL COMMENT '组名称',
    product_id          BIGINT NOT NULL COMMENT '产品ID',
    created_at          DATETIME DEFAULT CURRENT_TIMESTAMP,
    updated_at          DATETIME DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP,

    INDEX idx_org_id (org_id),
    INDEX idx_product_id (product_id)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COMMENT='产品规格组表';

-- 产品规格表
CREATE TABLE wms_specification (
    id                  BIGINT PRIMARY KEY AUTO_INCREMENT,
    org_id              BIGINT NOT NULL DEFAULT 0 COMMENT '组织ID',
    org_tag             VARCHAR(64) COMMENT '组织标识',
    specification_name  VARCHAR(100) NOT NULL COMMENT '规格名称',
    specification_value VARCHAR(255) NOT NULL COMMENT '规格值',
    group_id            BIGINT NOT NULL COMMENT '规格组ID',
    product_id          BIGINT NOT NULL COMMENT '产品ID',
    created_at          DATETIME DEFAULT CURRENT_TIMESTAMP,
    updated_at          DATETIME DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP,

    INDEX idx_org_id (org_id),
    INDEX idx_group_id (group_id),
    INDEX idx_product_id (product_id)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COMMENT='产品规格表';

-- 产品图片表
CREATE TABLE wms_product_photo (
    id                  BIGINT PRIMARY KEY AUTO_INCREMENT,
    org_id              BIGINT NOT NULL DEFAULT 0 COMMENT '组织ID',
    org_tag             VARCHAR(64) COMMENT '组织标识',
    product_id          BIGINT NOT NULL COMMENT '产品ID',
    photo_url           VARCHAR(500) NOT NULL COMMENT '图片URL',
    sort_order          INT DEFAULT 0 COMMENT '排序',
    created_at          DATETIME DEFAULT CURRENT_TIMESTAMP,

    INDEX idx_org_id (org_id),
    INDEX idx_product_id (product_id)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COMMENT='产品图片表';

-- 产品单位表
CREATE TABLE wms_product_unit (
    id                  BIGINT PRIMARY KEY AUTO_INCREMENT,
    org_id              BIGINT NOT NULL DEFAULT 0 COMMENT '组织ID',
    org_tag             VARCHAR(64) COMMENT '组织标识',
    unit_name           VARCHAR(50) NOT NULL COMMENT '单位名称',
    unit_code           VARCHAR(50) COMMENT '单位编码',
    product_id          BIGINT NOT NULL COMMENT '产品ID',
    conversion_rate     DECIMAL(10,4) DEFAULT 1 COMMENT '换算率',
    is_base_unit        TINYINT DEFAULT 0 COMMENT '是否基础单位',
    created_at          DATETIME DEFAULT CURRENT_TIMESTAMP,

    INDEX idx_org_id (org_id),
    INDEX idx_product_id (product_id)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COMMENT='产品单位表';

-- 产品条件表
CREATE TABLE wms_condition (
    id                  BIGINT PRIMARY KEY AUTO_INCREMENT,
    org_id              BIGINT NOT NULL DEFAULT 0 COMMENT '组织ID',
    org_tag             VARCHAR(64) COMMENT '组织标识',
    condition_name      VARCHAR(100) NOT NULL COMMENT '条件名称',
    condition_type      VARCHAR(50) COMMENT '条件类型',
    created_at          DATETIME DEFAULT CURRENT_TIMESTAMP,
    updated_at          DATETIME DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP,

    INDEX idx_org_id (org_id)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COMMENT='产品条件表';

-- 产品条件关系表
CREATE TABLE wms_product_condition (
    id                  BIGINT PRIMARY KEY AUTO_INCREMENT,
    org_id              BIGINT NOT NULL DEFAULT 0 COMMENT '组织ID',
    org_tag             VARCHAR(64) COMMENT '组织标识',
    product_id          BIGINT NOT NULL COMMENT '产品ID',
    condition_id        BIGINT NOT NULL COMMENT '条件ID',
    condition_value     VARCHAR(255) COMMENT '条件值',
    created_at          DATETIME DEFAULT CURRENT_TIMESTAMP,

    INDEX idx_org_id (org_id),
    INDEX idx_product_id (product_id),
    UNIQUE KEY uk_product_condition (org_id, product_id, condition_id)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COMMENT='产品条件关系表';

-- --------------------------------------------------------
-- 2. SKU 域 (SKU Domain)
-- --------------------------------------------------------

-- SKU 主表
CREATE TABLE wms_sku_product (
    id                  BIGINT PRIMARY KEY AUTO_INCREMENT,
    org_id              BIGINT NOT NULL DEFAULT 0 COMMENT '组织ID',
    org_tag             VARCHAR(64) COMMENT '组织标识',
    product_id          BIGINT NOT NULL COMMENT '产品ID',
    sku_code            VARCHAR(100) NOT NULL COMMENT 'SKU编码',
    sku_name            VARCHAR(255) NOT NULL COMMENT 'SKU名称',
    status              VARCHAR(20) DEFAULT 'ACTIVE' COMMENT '状态',
    sort_value          INT DEFAULT 0 COMMENT '排序值',
    search_key_word     VARCHAR(500) COMMENT '搜索关键字',
    bar_code            VARCHAR(100) COMMENT '条形码',
    description         TEXT COMMENT '描述',
    sku_price           DECIMAL(18,4) DEFAULT 0 COMMENT 'SKU售价',
    readjust_cost_price DECIMAL(18,4) DEFAULT 0 COMMENT '入库成本调整',
    suggested_price     DECIMAL(18,4) DEFAULT 0 COMMENT '建议零售价',
    cost_price          DECIMAL(18,4) DEFAULT 0 COMMENT '成本价',
    stock_cost          DECIMAL(18,4) DEFAULT 0 COMMENT '库存成本',
    weight              VARCHAR(50) COMMENT '重量',
    volume              VARCHAR(50) COMMENT '体积',
    spec                VARCHAR(100) COMMENT '规格',
    field1              VARCHAR(255) COMMENT '保留字段1',
    field2              VARCHAR(255) COMMENT '保留字段2',
    field3              VARCHAR(255) COMMENT '保留字段3',
    created_at          DATETIME DEFAULT CURRENT_TIMESTAMP,
    updated_at          DATETIME DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP,

    UNIQUE KEY uk_sku_code (org_id, sku_code),
    INDEX idx_org_id (org_id),
    INDEX idx_product_id (product_id),
    INDEX idx_status (status),
    INDEX idx_bar_code (bar_code)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COMMENT='SKU主表';

-- SKU 规格关系表
CREATE TABLE wms_sku_specification (
    id                  BIGINT PRIMARY KEY AUTO_INCREMENT,
    org_id              BIGINT NOT NULL DEFAULT 0 COMMENT '组织ID',
    org_tag             VARCHAR(64) COMMENT '组织标识',
    sku_id              BIGINT NOT NULL COMMENT 'SKU ID',
    group_id            BIGINT NOT NULL COMMENT '规格组ID',
    specification_id    BIGINT COMMENT '规格ID',
    created_at          DATETIME DEFAULT CURRENT_TIMESTAMP,

    INDEX idx_org_id (org_id),
    INDEX idx_sku_id (sku_id),
    INDEX idx_group_id (group_id)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COMMENT='SKU规格关系表';

-- SKU 规格组表
CREATE TABLE wms_sku_specification_group (
    id                  BIGINT PRIMARY KEY AUTO_INCREMENT,
    org_id              BIGINT NOT NULL DEFAULT 0 COMMENT '组织ID',
    org_tag             VARCHAR(64) COMMENT '组织标识',
    group_name          VARCHAR(100) NOT NULL COMMENT '组名称',
    sku_id              BIGINT NOT NULL COMMENT 'SKU ID',
    created_at          DATETIME DEFAULT CURRENT_TIMESTAMP,

    INDEX idx_org_id (org_id),
    INDEX idx_sku_id (sku_id)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COMMENT='SKU规格组表';

-- SKU 标签表
CREATE TABLE wms_sku_tag (
    id                  BIGINT PRIMARY KEY AUTO_INCREMENT,
    org_id              BIGINT NOT NULL DEFAULT 0 COMMENT '组织ID',
    org_tag             VARCHAR(64) COMMENT '组织标识',
    tag_name            VARCHAR(100) NOT NULL COMMENT '标签名称',
    tag_description     VARCHAR(255) COMMENT '标签描述',
    created_at          DATETIME DEFAULT CURRENT_TIMESTAMP,
    updated_at          DATETIME DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP,

    INDEX idx_org_id (org_id),
    UNIQUE KEY uk_tag_name (org_id, tag_name)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COMMENT='SKU标签表';

-- SKU 标签关系表
CREATE TABLE wms_sku_tag_relation (
    id                  BIGINT PRIMARY KEY AUTO_INCREMENT,
    org_id              BIGINT NOT NULL DEFAULT 0 COMMENT '组织ID',
    org_tag             VARCHAR(64) COMMENT '组织标识',
    sku_id              BIGINT NOT NULL COMMENT 'SKU ID',
    tag_id              BIGINT NOT NULL COMMENT '标签ID',
    created_at          DATETIME DEFAULT CURRENT_TIMESTAMP,

    INDEX idx_org_id (org_id),
    INDEX idx_sku_id (sku_id),
    UNIQUE KEY uk_sku_tag (org_id, sku_id, tag_id)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COMMENT='SKU标签关系表';

-- SKU 图片表
CREATE TABLE wms_sku_photo (
    id                  BIGINT PRIMARY KEY AUTO_INCREMENT,
    org_id              BIGINT NOT NULL DEFAULT 0 COMMENT '组织ID',
    org_tag             VARCHAR(64) COMMENT '组织标识',
    sku_id              BIGINT NOT NULL COMMENT 'SKU ID',
    photo_url           VARCHAR(500) NOT NULL COMMENT '图片URL',
    sort_order          INT DEFAULT 0 COMMENT '排序',
    created_at          DATETIME DEFAULT CURRENT_TIMESTAMP,

    INDEX idx_org_id (org_id),
    INDEX idx_sku_id (sku_id)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COMMENT='SKU图片表';

-- SKU 单位表
CREATE TABLE wms_sku_unit (
    id                  BIGINT PRIMARY KEY AUTO_INCREMENT,
    org_id              BIGINT NOT NULL DEFAULT 0 COMMENT '组织ID',
    org_tag             VARCHAR(64) COMMENT '组织标识',
    unit_name           VARCHAR(50) NOT NULL COMMENT '单位名称',
    unit_code           VARCHAR(50) COMMENT '单位编码',
    sku_id              BIGINT NOT NULL COMMENT 'SKU ID',
    conversion_rate     DECIMAL(10,4) DEFAULT 1 COMMENT '换算率',
    is_base_unit        TINYINT DEFAULT 0 COMMENT '是否基础单位',
    created_at          DATETIME DEFAULT CURRENT_TIMESTAMP,

    INDEX idx_org_id (org_id),
    INDEX idx_sku_id (sku_id)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COMMENT='SKU单位表';

-- SKU 条件表
CREATE TABLE wms_sku_condition (
    id                  BIGINT PRIMARY KEY AUTO_INCREMENT,
    org_id              BIGINT NOT NULL DEFAULT 0 COMMENT '组织ID',
    org_tag             VARCHAR(64) COMMENT '组织标识',
    condition_name      VARCHAR(100) NOT NULL COMMENT '条件名称',
    condition_type      VARCHAR(50) COMMENT '条件类型',
    created_at          DATETIME DEFAULT CURRENT_TIMESTAMP,
    updated_at          DATETIME DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP,

    INDEX idx_org_id (org_id)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COMMENT='SKU条件表';

-- SKU 条件关系表
CREATE TABLE wms_sku_condition_relation (
    id                  BIGINT PRIMARY KEY AUTO_INCREMENT,
    org_id              BIGINT NOT NULL DEFAULT 0 COMMENT '组织ID',
    org_tag             VARCHAR(64) COMMENT '组织标识',
    sku_id              BIGINT NOT NULL COMMENT 'SKU ID',
    condition_id        BIGINT NOT NULL COMMENT '条件ID',
    condition_value     VARCHAR(255) COMMENT '条件值',
    created_at          DATETIME DEFAULT CURRENT_TIMESTAMP,

    INDEX idx_org_id (org_id),
    INDEX idx_sku_id (sku_id),
    UNIQUE KEY uk_sku_condition (org_id, sku_id, condition_id)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COMMENT='SKU条件关系表';

-- SKU 价格历史表
CREATE TABLE wms_sku_price_history (
    id                  BIGINT PRIMARY KEY AUTO_INCREMENT,
    org_id              BIGINT NOT NULL DEFAULT 0 COMMENT '组织ID',
    org_tag             VARCHAR(64) COMMENT '组织标识',
    sku_id              BIGINT NOT NULL COMMENT 'SKU ID',
    price_type          VARCHAR(20) COMMENT '价格类型: COST, SALE, SUGGESTED',
    old_price           DECIMAL(18,4) COMMENT '旧价格',
    new_price           DECIMAL(18,4) COMMENT '新价格',
    changed_by          VARCHAR(100) COMMENT '修改人',
    change_reason       VARCHAR(255) COMMENT '修改原因',
    created_at          DATETIME DEFAULT CURRENT_TIMESTAMP,

    INDEX idx_org_id (org_id),
    INDEX idx_sku_id (sku_id),
    INDEX idx_created_at (created_at)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COMMENT='SKU价格历史表';

-- --------------------------------------------------------
-- 3. 仓库域 (Warehouse Domain)
-- --------------------------------------------------------

-- 仓库表
CREATE TABLE wms_warehouse (
    id                  BIGINT PRIMARY KEY AUTO_INCREMENT,
    org_id              BIGINT NOT NULL DEFAULT 0 COMMENT '组织ID',
    org_tag             VARCHAR(64) COMMENT '组织标识',
    warehouse_code      VARCHAR(100) NOT NULL COMMENT '仓库编码',
    warehouse_name      VARCHAR(255) NOT NULL COMMENT '仓库名称',
    warehouse_pcd       VARCHAR(100) COMMENT '省市区',
    warehouse_address   VARCHAR(500) COMMENT '详细地址',
    warehouse_charger   BIGINT COMMENT '负责人ID',
    status              VARCHAR(20) DEFAULT 'ACTIVE' COMMENT '状态',
    created_at          DATETIME DEFAULT CURRENT_TIMESTAMP,
    updated_at          DATETIME DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP,

    UNIQUE KEY uk_warehouse_code (org_id, warehouse_code),
    INDEX idx_org_id (org_id),
    INDEX idx_status (status)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COMMENT='仓库表';

-- 储位表
CREATE TABLE wms_warehouse_slot (
    id                  BIGINT PRIMARY KEY AUTO_INCREMENT,
    org_id              BIGINT NOT NULL DEFAULT 0 COMMENT '组织ID',
    org_tag             VARCHAR(64) COMMENT '组织标识',
    slot_code           VARCHAR(100) NOT NULL COMMENT '储位编码',
    slot_name           VARCHAR(255) NOT NULL COMMENT '储位名称',
    warehouse_id        BIGINT NOT NULL COMMENT '仓库ID',
    slot_note           VARCHAR(500) COMMENT '储位说明',
    status              VARCHAR(20) DEFAULT 'ACTIVE' COMMENT '状态',
    created_at          DATETIME DEFAULT CURRENT_TIMESTAMP,
    updated_at          DATETIME DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP,

    INDEX idx_org_id (org_id),
    INDEX idx_warehouse_id (warehouse_id),
    UNIQUE KEY uk_slot_code (org_id, warehouse_id, slot_code)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COMMENT='储位表';

-- 库存表
CREATE TABLE wms_inventory (
    id                  BIGINT PRIMARY KEY AUTO_INCREMENT,
    org_id              BIGINT NOT NULL DEFAULT 0 COMMENT '组织ID',
    org_tag             VARCHAR(64) COMMENT '组织标识',
    warehouse_id        BIGINT NOT NULL COMMENT '仓库ID',
    slot_id             BIGINT COMMENT '储位ID',
    sku_id              BIGINT NOT NULL COMMENT 'SKU ID',
    max_inventory       INT DEFAULT 0 COMMENT '库存上限',
    min_inventory       INT DEFAULT 0 COMMENT '库存下限',
    valid_sku           INT DEFAULT 0 COMMENT '可用库存',
    advance_quantities  INT DEFAULT 0 COMMENT '预购量',
    transmit_quantities INT DEFAULT 0 COMMENT '在途量',
    order_count         INT DEFAULT 0 COMMENT '订单占用量',
    created_at          DATETIME DEFAULT CURRENT_TIMESTAMP,
    updated_at          DATETIME DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP,

    INDEX idx_org_id (org_id),
    INDEX idx_warehouse_id (warehouse_id),
    INDEX idx_slot_id (slot_id),
    INDEX idx_sku_id (sku_id),
    UNIQUE KEY uk_inventory (org_id, warehouse_id, slot_id, sku_id)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COMMENT='库存表';

-- --------------------------------------------------------
-- 4. 供应商与客户域
-- --------------------------------------------------------

-- 供应商表
CREATE TABLE wms_suppliers (
    id                          BIGINT PRIMARY KEY AUTO_INCREMENT,
    org_id                      BIGINT NOT NULL DEFAULT 0 COMMENT '组织ID',
    org_tag                     VARCHAR(64) COMMENT '组织标识',
    supplier_name               VARCHAR(255) NOT NULL COMMENT '供应商名称',
    supplier_code               VARCHAR(100) COMMENT '供应商编码',
    supplier_pcd                VARCHAR(100) COMMENT '省市区',
    supplier_address            VARCHAR(500) COMMENT '详细地址',
    supplier_postcode           VARCHAR(20) COMMENT '邮编',
    supplier_contact_name       VARCHAR(100) COMMENT '联系人',
    supplier_contact_phone      VARCHAR(50) COMMENT '联系电话',
    supplier_contact_fax        VARCHAR(50) COMMENT '传真',
    supplier_contact_email      VARCHAR(100) COMMENT '邮箱',
    supplier_contact_position   VARCHAR(100) COMMENT '职位',
    supplier_contact_cell_phone VARCHAR(50) COMMENT '手机',
    supplier_account_name       VARCHAR(255) COMMENT '开户名称',
    supplier_account_bank       VARCHAR(255) COMMENT '开户银行',
    supplier_account_bank_no    VARCHAR(100) COMMENT '银行账号',
    supplier_invoice_title      VARCHAR(255) COMMENT '发票抬头',
    supplier_status             VARCHAR(20) DEFAULT 'ACTIVE' COMMENT '状态',
    supplier_note               TEXT COMMENT '备注',
    supplier_register_time      DATETIME COMMENT '注册时间',
    field1                      VARCHAR(255) COMMENT '保留字段1',
    field2                      VARCHAR(255) COMMENT '保留字段2',
    created_at                  DATETIME DEFAULT CURRENT_TIMESTAMP,
    updated_at                  DATETIME DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP,

    INDEX idx_org_id (org_id),
    INDEX idx_supplier_code (org_id, supplier_code),
    INDEX idx_status (supplier_status)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COMMENT='供应商表';

-- 供应商产品关系表
CREATE TABLE wms_suppliers_product (
    id                  BIGINT PRIMARY KEY AUTO_INCREMENT,
    org_id              BIGINT NOT NULL DEFAULT 0 COMMENT '组织ID',
    org_tag             VARCHAR(64) COMMENT '组织标识',
    supplier_id         BIGINT NOT NULL COMMENT '供应商ID',
    product_id          BIGINT NOT NULL COMMENT '产品ID',
    sku_id              BIGINT COMMENT 'SKU ID',
    supply_price        DECIMAL(18,4) COMMENT '供应价格',
    min_order_quantity  INT DEFAULT 1 COMMENT '最小订货量',
    is_primary          TINYINT DEFAULT 0 COMMENT '是否主供应商',
    created_at          DATETIME DEFAULT CURRENT_TIMESTAMP,

    INDEX idx_org_id (org_id),
    INDEX idx_supplier_id (supplier_id),
    INDEX idx_product_id (product_id),
    UNIQUE KEY uk_supplier_product (org_id, supplier_id, product_id, sku_id)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COMMENT='供应商产品关系表';

-- 分销商/客户表
CREATE TABLE wms_trader (
    id                          BIGINT PRIMARY KEY AUTO_INCREMENT,
    org_id                      BIGINT NOT NULL DEFAULT 0 COMMENT '组织ID',
    org_tag                     VARCHAR(64) COMMENT '组织标识',
    trader_name                 VARCHAR(255) NOT NULL COMMENT '分销商名称',
    trader_code                 VARCHAR(100) COMMENT '分销商编码',
    trader_pcd                  VARCHAR(100) COMMENT '省市区',
    trader_address              VARCHAR(500) COMMENT '详细地址',
    trader_postcode             VARCHAR(20) COMMENT '邮编',
    trader_contact_name         VARCHAR(100) COMMENT '联系人',
    trader_contact_phone        VARCHAR(50) COMMENT '联系电话',
    trader_contact_fax          VARCHAR(50) COMMENT '传真',
    trader_contact_email        VARCHAR(100) COMMENT '邮箱',
    trader_contact_position     VARCHAR(100) COMMENT '职位',
    trader_contact_cell_phone   VARCHAR(50) COMMENT '手机',
    trader_account_name         VARCHAR(255) COMMENT '开户名称',
    trader_account_bank         VARCHAR(255) COMMENT '开户银行',
    trader_account_bank_no      VARCHAR(100) COMMENT '银行账号',
    trader_invoice_title        VARCHAR(255) COMMENT '发票抬头',
    trader_status               VARCHAR(20) DEFAULT 'ACTIVE' COMMENT '状态',
    trader_note                 TEXT COMMENT '备注',
    trader_register_time        DATETIME COMMENT '注册时间',
    field1                      VARCHAR(255) COMMENT '保留字段1',
    field2                      VARCHAR(255) COMMENT '保留字段2',
    created_at                  DATETIME DEFAULT CURRENT_TIMESTAMP,
    updated_at                  DATETIME DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP,

    INDEX idx_org_id (org_id),
    INDEX idx_trader_code (org_id, trader_code),
    INDEX idx_status (trader_status)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COMMENT='分销商/客户表';

-- --------------------------------------------------------
-- 5. 业务单据域
-- --------------------------------------------------------

-- 采购单表
CREATE TABLE wms_procurement (
    id                      BIGINT PRIMARY KEY AUTO_INCREMENT,
    org_id                  BIGINT NOT NULL DEFAULT 0 COMMENT '组织ID',
    org_tag                 VARCHAR(64) COMMENT '组织标识',
    procurement_code        VARCHAR(100) NOT NULL COMMENT '采购单号',
    supplier_id             BIGINT NOT NULL COMMENT '供应商ID',
    procurement_others_payment DECIMAL(18,4) DEFAULT 0 COMMENT '其他支出',
    procurement_discount    INT DEFAULT 0 COMMENT '折扣',
    procurement_total       DECIMAL(18,4) DEFAULT 0 COMMENT '总金额',
    procurement_time        DATE COMMENT '采购日期',
    procurement_note        TEXT COMMENT '备注',
    procure_status          VARCHAR(20) DEFAULT 'DRAFT' COMMENT '状态: DRAFT, PENDING, APPROVED, PARTIAL, COMPLETED, CANCELLED',
    transaction_by          VARCHAR(100) COMMENT '操作人',
    originator_id           BIGINT COMMENT '制单人ID',
    originator_name         VARCHAR(100) COMMENT '制单人',
    buyer                   VARCHAR(100) COMMENT '采购员',
    transaction_time        DATETIME COMMENT '操作时间',
    field1                  VARCHAR(255) COMMENT '保留字段1',
    field2                  VARCHAR(255) COMMENT '保留字段2',
    created_at              DATETIME DEFAULT CURRENT_TIMESTAMP,
    updated_at              DATETIME DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP,

    UNIQUE KEY uk_procurement_code (org_id, procurement_code),
    INDEX idx_org_id (org_id),
    INDEX idx_supplier_id (supplier_id),
    INDEX idx_status (procure_status),
    INDEX idx_procurement_time (procurement_time)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COMMENT='采购单表';

-- 采购单明细表
CREATE TABLE wms_procurement_item (
    id                  BIGINT PRIMARY KEY AUTO_INCREMENT,
    org_id              BIGINT NOT NULL DEFAULT 0 COMMENT '组织ID',
    org_tag             VARCHAR(64) COMMENT '组织标识',
    procurement_id      BIGINT NOT NULL COMMENT '采购单ID',
    product_id          BIGINT NOT NULL COMMENT '产品ID',
    sku_id              BIGINT NOT NULL COMMENT 'SKU ID',
    quantity            INT NOT NULL COMMENT '数量',
    unit_price          DECIMAL(18,4) NOT NULL COMMENT '单价',
    total_price         DECIMAL(18,4) NOT NULL COMMENT '总价',
    received_quantity   INT DEFAULT 0 COMMENT '已入库数量',
    note                VARCHAR(500) COMMENT '备注',
    created_at          DATETIME DEFAULT CURRENT_TIMESTAMP,

    INDEX idx_org_id (org_id),
    INDEX idx_procurement_id (procurement_id),
    INDEX idx_sku_id (sku_id)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COMMENT='采购单明细表';

-- 销售单表
CREATE TABLE wms_sales (
    id                  BIGINT PRIMARY KEY AUTO_INCREMENT,
    org_id              BIGINT NOT NULL DEFAULT 0 COMMENT '组织ID',
    org_tag             VARCHAR(64) COMMENT '组织标识',
    sales_code          VARCHAR(100) NOT NULL COMMENT '销售单号',
    trader_id           BIGINT NOT NULL COMMENT '分销商ID',
    sales_others_payment DECIMAL(18,4) DEFAULT 0 COMMENT '其他支出',
    sales_discount      INT DEFAULT 0 COMMENT '折扣',
    sales_total         DECIMAL(18,4) DEFAULT 0 COMMENT '总金额',
    sales_time          DATE COMMENT '销售日期',
    sales_note          TEXT COMMENT '备注',
    sales_status        VARCHAR(20) DEFAULT 'DRAFT' COMMENT '状态: DRAFT, PENDING, APPROVED, PARTIAL, COMPLETED, CANCELLED',
    transaction_by      VARCHAR(100) COMMENT '操作人',
    originator_id       BIGINT COMMENT '制单人ID',
    originator_name     VARCHAR(100) COMMENT '制单人',
    transaction_time    DATETIME COMMENT '操作时间',
    total_count         INT DEFAULT 0 COMMENT '总数量',
    delivered_address   VARCHAR(500) COMMENT '收货地址',
    field1              VARCHAR(255) COMMENT '保留字段1',
    field2              VARCHAR(255) COMMENT '保留字段2',
    created_at          DATETIME DEFAULT CURRENT_TIMESTAMP,
    updated_at          DATETIME DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP,

    UNIQUE KEY uk_sales_code (org_id, sales_code),
    INDEX idx_org_id (org_id),
    INDEX idx_trader_id (trader_id),
    INDEX idx_status (sales_status),
    INDEX idx_sales_time (sales_time)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COMMENT='销售单表';

-- 销售单明细表
CREATE TABLE wms_sales_item (
    id                  BIGINT PRIMARY KEY AUTO_INCREMENT,
    org_id              BIGINT NOT NULL DEFAULT 0 COMMENT '组织ID',
    org_tag             VARCHAR(64) COMMENT '组织标识',
    sales_id            BIGINT NOT NULL COMMENT '销售单ID',
    product_id          BIGINT NOT NULL COMMENT '产品ID',
    sku_id              BIGINT NOT NULL COMMENT 'SKU ID',
    quantity            INT NOT NULL COMMENT '数量',
    unit_price          DECIMAL(18,4) NOT NULL COMMENT '单价',
    total_price         DECIMAL(18,4) NOT NULL COMMENT '总价',
    delivered_quantity  INT DEFAULT 0 COMMENT '已出库数量',
    note                VARCHAR(500) COMMENT '备注',
    created_at          DATETIME DEFAULT CURRENT_TIMESTAMP,

    INDEX idx_org_id (org_id),
    INDEX idx_sales_id (sales_id),
    INDEX idx_sku_id (sku_id)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COMMENT='销售单明细表';

-- 入库单表
CREATE TABLE wms_storage_in (
    id                  BIGINT PRIMARY KEY AUTO_INCREMENT,
    org_id              BIGINT NOT NULL DEFAULT 0 COMMENT '组织ID',
    org_tag             VARCHAR(64) COMMENT '组织标识',
    transaction_code    VARCHAR(100) NOT NULL COMMENT '入库单号',
    transaction_type    VARCHAR(50) COMMENT '入库类型: PROCUREMENT, RETURN, TRANSFER, ADJUST',
    storage_in_time     DATETIME COMMENT '入库时间',
    transaction_time    DATETIME COMMENT '操作时间',
    batch_no            VARCHAR(100) COMMENT '批次号',
    warehouse_id        BIGINT NOT NULL COMMENT '仓库ID',
    slot_id             BIGINT COMMENT '储位ID',
    note                TEXT COMMENT '备注',
    status              VARCHAR(20) DEFAULT 'DRAFT' COMMENT '状态: DRAFT, PENDING, APPROVED, COMPLETED, CANCELLED',
    transaction_by      VARCHAR(100) COMMENT '操作人',
    originator_id       BIGINT COMMENT '制单人ID',
    originator_name     VARCHAR(100) COMMENT '制单人',
    readjust_cost_price DECIMAL(18,4) DEFAULT 0 COMMENT '成本调整',
    procurement_id      BIGINT COMMENT '关联采购单ID',
    distributor_customer VARCHAR(255) COMMENT '分销商/客户',
    out_order_num       VARCHAR(100) COMMENT '外部订单号',
    field1              VARCHAR(255) COMMENT '保留字段1',
    field2              VARCHAR(255) COMMENT '保留字段2',
    created_at          DATETIME DEFAULT CURRENT_TIMESTAMP,
    updated_at          DATETIME DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP,

    UNIQUE KEY uk_transaction_code (org_id, transaction_code),
    INDEX idx_org_id (org_id),
    INDEX idx_warehouse_id (warehouse_id),
    INDEX idx_status (status),
    INDEX idx_procurement_id (procurement_id),
    INDEX idx_transaction_type (transaction_type)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COMMENT='入库单表';

-- 入库单明细表
CREATE TABLE wms_storage_in_item (
    id                          BIGINT PRIMARY KEY AUTO_INCREMENT,
    org_id                      BIGINT NOT NULL DEFAULT 0 COMMENT '组织ID',
    org_tag                     VARCHAR(64) COMMENT '组织标识',
    storage_in_id               BIGINT NOT NULL COMMENT '入库单ID',
    sku_id                      BIGINT NOT NULL COMMENT 'SKU ID',
    transaction_sku_price       DECIMAL(18,4) COMMENT '入库单价',
    transaction_quantities      INT NOT NULL COMMENT '入库数量',
    after_transaction_quantities INT COMMENT '入库后数量',
    before_transaction_quantities INT COMMENT '入库前数量',
    demand_quantities           INT COMMENT '需求数量',
    transaction_time            DATETIME COMMENT '操作时间',
    type                        VARCHAR(50) COMMENT '类型',
    relation_code               VARCHAR(100) COMMENT '关联单号',
    created_at                  DATETIME DEFAULT CURRENT_TIMESTAMP,

    INDEX idx_org_id (org_id),
    INDEX idx_storage_in_id (storage_in_id),
    INDEX idx_sku_id (sku_id)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COMMENT='入库单明细表';

-- 出库单表
CREATE TABLE wms_storage_out (
    id                  BIGINT PRIMARY KEY AUTO_INCREMENT,
    org_id              BIGINT NOT NULL DEFAULT 0 COMMENT '组织ID',
    org_tag             VARCHAR(64) COMMENT '组织标识',
    transaction_code    VARCHAR(100) NOT NULL COMMENT '出库单号',
    transaction_type    VARCHAR(50) COMMENT '出库类型: SALES, RETURN, TRANSFER, ADJUST',
    transaction_time    DATETIME COMMENT '操作时间',
    storage_out_time    DATETIME COMMENT '出库时间',
    batch_no            VARCHAR(100) COMMENT '批次号',
    warehouse_id        BIGINT NOT NULL COMMENT '仓库ID',
    slot_id             BIGINT COMMENT '储位ID',
    note                TEXT COMMENT '备注',
    status              VARCHAR(20) DEFAULT 'DRAFT' COMMENT '状态: DRAFT, PENDING, APPROVED, COMPLETED, CANCELLED',
    transaction_by      VARCHAR(100) COMMENT '操作人',
    originator_id       BIGINT COMMENT '制单人ID',
    originator_name     VARCHAR(100) COMMENT '制单人',
    out_order_num       VARCHAR(100) COMMENT '外部订单号',
    distributor_customer VARCHAR(255) COMMENT '分销商/客户',
    sales_id            BIGINT COMMENT '关联销售单ID',
    field1              VARCHAR(255) COMMENT '保留字段1',
    field2              VARCHAR(255) COMMENT '保留字段2',
    created_at          DATETIME DEFAULT CURRENT_TIMESTAMP,
    updated_at          DATETIME DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP,

    UNIQUE KEY uk_transaction_code (org_id, transaction_code),
    INDEX idx_org_id (org_id),
    INDEX idx_warehouse_id (warehouse_id),
    INDEX idx_status (status),
    INDEX idx_sales_id (sales_id),
    INDEX idx_transaction_type (transaction_type)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COMMENT='出库单表';

-- 出库单明细表
CREATE TABLE wms_storage_out_item (
    id                          BIGINT PRIMARY KEY AUTO_INCREMENT,
    org_id                      BIGINT NOT NULL DEFAULT 0 COMMENT '组织ID',
    org_tag                     VARCHAR(64) COMMENT '组织标识',
    storage_out_id              BIGINT NOT NULL COMMENT '出库单ID',
    sku_id                      BIGINT NOT NULL COMMENT 'SKU ID',
    transaction_sku_price       DECIMAL(18,4) COMMENT '出库单价',
    transaction_quantities      INT NOT NULL COMMENT '出库数量',
    after_transaction_quantities INT COMMENT '出库后数量',
    before_transaction_quantities INT COMMENT '出库前数量',
    demand_quantities           INT COMMENT '需求数量',
    transaction_time            DATETIME COMMENT '操作时间',
    relation_code               VARCHAR(100) COMMENT '关联单号',
    type                        VARCHAR(50) COMMENT '类型',
    created_at                  DATETIME DEFAULT CURRENT_TIMESTAMP,

    INDEX idx_org_id (org_id),
    INDEX idx_storage_out_id (storage_out_id),
    INDEX idx_sku_id (sku_id)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COMMENT='出库单明细表';

-- 退货单表
CREATE TABLE wms_refund (
    id                      BIGINT PRIMARY KEY AUTO_INCREMENT,
    org_id                  BIGINT NOT NULL DEFAULT 0 COMMENT '组织ID',
    org_tag                 VARCHAR(64) COMMENT '组织标识',
    product_refund_code     VARCHAR(100) NOT NULL COMMENT '退货单号',
    product_procurement_id  BIGINT COMMENT '关联采购单ID',
    storage_out_id          BIGINT COMMENT '关联出库单ID',
    product_refund_warehouse_id BIGINT NOT NULL COMMENT '退货仓库ID',
    product_refund_quantities INT DEFAULT 0 COMMENT '退货数量',
    product_refund_time     DATETIME COMMENT '退货时间',
    product_refund_status   VARCHAR(20) DEFAULT 'DRAFT' COMMENT '状态',
    product_refund_note     TEXT COMMENT '备注',
    originator_id           BIGINT COMMENT '制单人ID',
    originator_name         VARCHAR(100) COMMENT '制单人',
    transaction_by          VARCHAR(100) COMMENT '操作人',
    transaction_time        DATETIME COMMENT '操作时间',
    supplier_id             BIGINT COMMENT '供应商ID',
    field1                  VARCHAR(255) COMMENT '保留字段1',
    field2                  VARCHAR(255) COMMENT '保留字段2',
    created_at              DATETIME DEFAULT CURRENT_TIMESTAMP,
    updated_at              DATETIME DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP,

    UNIQUE KEY uk_refund_code (org_id, product_refund_code),
    INDEX idx_org_id (org_id),
    INDEX idx_procurement_id (product_procurement_id),
    INDEX idx_status (product_refund_status)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COMMENT='退货单表';

-- 调拨单表
CREATE TABLE wms_transfer (
    id                  BIGINT PRIMARY KEY AUTO_INCREMENT,
    org_id              BIGINT NOT NULL DEFAULT 0 COMMENT '组织ID',
    org_tag             VARCHAR(64) COMMENT '组织标识',
    transaction_code    VARCHAR(100) NOT NULL COMMENT '调拨单号',
    from_warehouse_id   BIGINT NOT NULL COMMENT '调出仓库ID',
    to_warehouse_id     BIGINT NOT NULL COMMENT '调入仓库ID',
    transaction_time    DATETIME COMMENT '操作时间',
    finish_time         DATETIME COMMENT '完成时间',
    storage_in_id       BIGINT COMMENT '关联入库单ID',
    storage_out_id      BIGINT COMMENT '关联出库单ID',
    note                TEXT COMMENT '备注',
    status              VARCHAR(20) DEFAULT 'DRAFT' COMMENT '状态: DRAFT, PENDING, IN_TRANSIT, COMPLETED, CANCELLED',
    transaction_by      VARCHAR(100) COMMENT '操作人',
    originator_id       BIGINT COMMENT '制单人ID',
    originator_name     VARCHAR(100) COMMENT '制单人',
    transfer_time       DATETIME COMMENT '调拨时间',
    field1              VARCHAR(255) COMMENT '保留字段1',
    field2              VARCHAR(255) COMMENT '保留字段2',
    created_at          DATETIME DEFAULT CURRENT_TIMESTAMP,
    updated_at          DATETIME DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP,

    UNIQUE KEY uk_transaction_code (org_id, transaction_code),
    INDEX idx_org_id (org_id),
    INDEX idx_from_warehouse (from_warehouse_id),
    INDEX idx_to_warehouse (to_warehouse_id),
    INDEX idx_status (status)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COMMENT='调拨单表';

-- 调拨单明细表
CREATE TABLE wms_transfer_item (
    id                  BIGINT PRIMARY KEY AUTO_INCREMENT,
    org_id              BIGINT NOT NULL DEFAULT 0 COMMENT '组织ID',
    org_tag             VARCHAR(64) COMMENT '组织标识',
    transfer_id         BIGINT NOT NULL COMMENT '调拨单ID',
    sku_id              BIGINT NOT NULL COMMENT 'SKU ID',
    quantity            INT NOT NULL COMMENT '数量',
    transferred_quantity INT DEFAULT 0 COMMENT '已调拨数量',
    note                VARCHAR(500) COMMENT '备注',
    created_at          DATETIME DEFAULT CURRENT_TIMESTAMP,

    INDEX idx_org_id (org_id),
    INDEX idx_transfer_id (transfer_id),
    INDEX idx_sku_id (sku_id)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COMMENT='调拨单明细表';

-- 盘点单表
CREATE TABLE wms_check (
    id                  BIGINT PRIMARY KEY AUTO_INCREMENT,
    org_id              BIGINT NOT NULL DEFAULT 0 COMMENT '组织ID',
    org_tag             VARCHAR(64) COMMENT '组织标识',
    check_code          VARCHAR(100) NOT NULL COMMENT '盘点单号',
    check_time          DATETIME COMMENT '盘点时间',
    finish_time         DATETIME COMMENT '完成时间',
    begin_time          DATETIME COMMENT '开始时间',
    warehouse_id        BIGINT NOT NULL COMMENT '仓库ID',
    profit_lost         INT DEFAULT 0 COMMENT '盈亏数量',
    check_note          TEXT COMMENT '备注',
    transaction_by      VARCHAR(100) COMMENT '经手人',
    originator_id       BIGINT COMMENT '制单人ID',
    originator_name     VARCHAR(100) COMMENT '制单人',
    status              VARCHAR(20) DEFAULT 'DRAFT' COMMENT '状态: DRAFT, IN_PROGRESS, COMPLETED, ADJUSTED',
    field1              VARCHAR(255) COMMENT '保留字段1',
    field2              VARCHAR(255) COMMENT '保留字段2',
    created_at          DATETIME DEFAULT CURRENT_TIMESTAMP,
    updated_at          DATETIME DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP,

    UNIQUE KEY uk_check_code (org_id, check_code),
    INDEX idx_org_id (org_id),
    INDEX idx_warehouse_id (warehouse_id),
    INDEX idx_status (status)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COMMENT='盘点单表';

-- 盘点单明细表
CREATE TABLE wms_check_sku (
    id                      BIGINT PRIMARY KEY AUTO_INCREMENT,
    org_id                  BIGINT NOT NULL DEFAULT 0 COMMENT '组织ID',
    org_tag                 VARCHAR(64) COMMENT '组织标识',
    check_id                BIGINT NOT NULL COMMENT '盘点单ID',
    sku_id                  BIGINT NOT NULL COMMENT 'SKU ID',
    warehouse_id            BIGINT NOT NULL COMMENT '仓库ID',
    fact_quantities         INT DEFAULT 0 COMMENT '实际数量',
    before_proof_quantities INT DEFAULT 0 COMMENT '校对前数量',
    deserved_quantities     INT DEFAULT 0 COMMENT '应有数量',
    profit_lost             INT DEFAULT 0 COMMENT '盈亏',
    field1                  VARCHAR(255) COMMENT '保留字段1',
    field2                  VARCHAR(255) COMMENT '保留字段2',
    created_at              DATETIME DEFAULT CURRENT_TIMESTAMP,
    updated_at              DATETIME DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP,

    INDEX idx_org_id (org_id),
    INDEX idx_check_id (check_id),
    INDEX idx_sku_id (sku_id),
    INDEX idx_warehouse_id (warehouse_id)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COMMENT='盘点单明细表';

module.exports =  {
  "columns": 1,
  "listAPI": "/api/wms/storages/out",
  "createAPI": "/api/wms/storages/out",
  "getAPI": "/api/wms/storages/out/[id]",
  "updateAPI": "/api/wms/storages/out/[id]",
  "passAPI": "/api/wms/storages/out/[id]/passed",
  "refuseAPI": "/api/wms/storages/out/[id]/closed",
  "deleteAPI": "/api/wms/storages/out/(id)",
  "searchType": "MoreSearch",
  "searchButtonType": "text",
  "pageName": {
    "table": "出库管理",
    "new": "新增",
    "edit": "编辑",
    "name": "出库管理",
    "view": "详情"
  },
  "createFields": [
    {
      "label": "出库编号",
      "field": "transactionCode",
      "type": "serial-code",
      "rules": [ "required" ],
      "props":{
          "prefixValue": "OUT"
      },
    },
    {
      "label": "操作类型",
      "field": 'transactionType', "type": "select",
      "options": [
        { "key": '销售出货', "value": 'SalesOut' },
        { "label": '分销商退货', "value": 'CustomerStorageIn' },
        { "label": '其它入库', "value": 'OthersStorageIn' },
        { "label": '批发出库', "value": 'WholesalesOut' },
      ],
      "rules": [ "required" ],
      "props":{
          "placeholder":"请选择",
          "style":{
            "width": "240px"
          }
      },
    },
    {
      "label": "仓库",
      "field": "warehouseId",
      "span": 24,
      "type": "modal-radio",
      "props": {},
      "rules": [ "required" ],
      "options": {
        "title": "选择仓库",
        "label": "warehouseName",
        "editLabel": "warehouseName",
        "value": "id",
        "pagination": true,
        "API": "/api/wms/warehouses",
        "fields": [
          {
            "label": "名称",
            "field": "warehouseName"
          }
        ]
      }
    },
    {
      "label": "经办人",
      "field": "transactionBy",
      "type": "input",
      "rules": [],
      "props":{
          "placeholder":"请输入",
      },
    },
    {
      "label": "订单号信息",
      "field": "outOrderNum",
      "type": "input",
      "rules": [],
      "props":{
          "placeholder":"请输入",
      },
    },
    {
      "label": "客户",
      "field": "distributorCustomer",
      "type": "input",
      "rules": [],
      "props":{
          "placeholder":"请输入",
      },
    },
    {
      "label": "操作时间",
      "field": "transactionTime",
      "type": "date",
      "rules": [],
      "props":{
          "placeholder":"请输入",
      },
    },
    {
      "label": "制单人",
      "field": "originatorName",
      "type": "input",
      "rules": [ "required" ],
      "props":{
          "placeholder":"请输入",
      },
    },
    {
      "label": "商品",
      "field": "storageOutItems",
      "type": "modal-checkbox",
      "rules": [ "required" ],
      "options": {
        "title": "选择商品",
        "label": "skuName",
        "value": "id",
        "pagination": true,
        "API": "/api/wms/skus",
        "fields": [
          {
            "label": "商品条码",
            "field": "barCode"
          },
          {
            "label": "商品编号",
            "field": "skuCode"
          },
          {
            "label": "商品名称",
            "field": "skuName"
          },
          {
            "label": "单位",
            "field": "field1"
          }
        ]
      },
      "expect": {}
    },
    {
      "label": "备注",
      "field": "note",
      "type": "input",
      "rules": [],
      "props":{
          "placeholder":"请输入",
      },
    },
  ],
  "updateFields": [
    {
      "label": "出库编号",
      "field": "transactionCode",
      "type": "plain",
    },
    {
      "label": "操作类型",
      "field": 'transactionType', "type": "select",
      "options": [
        // { key: '销售退货', value: 'SalesIn' },
        { "label": '分销商退货', "value": 'CustomerStorageIn' },
        { "label": '其它入库', "value": 'OthersStorageIn' },
      ],
      "rules": [ "required" ],
      "props":{
          "placeholder":"请选择",
          "style":{
            "width": "240px"
          }
      },
    },
    {
      "label": "仓库",
      "field": "warehouseId",
      "span": 24,
      "type": "modal-radio",
      "props": {},
      "rules": [ "required" ],
      "options": {
        "title": "选择仓库",
        "label": "warehouseName",
        "editLabel": "warehouseName",
        "value": "id",
        "pagination": true,
        "API": "/api/wms/warehouses",
        "fields": [
          {
            "label": "名称",
            "field": "warehouseName"
          }
        ]
      }
    },
    {
      "label": "经办人",
      "field": "transactionBy",
      "type": "input",
      "rules": [],
      "props":{
          "placeholder":"请输入",
      },
    },
    {
      "label": "订单号信息",
      "field": "outOrderNum",
      "type": "input",
      "rules": [],
      "props":{
          "placeholder":"请输入",
      },
    },
    {
      "label": "客户",
      "field": "distributorCustomer",
      "type": "input",
      "rules": [],
      "props":{
          "placeholder":"请输入",
      },
    },
    {
      "label": "操作时间",
      "field": "transactionTime",
      "type": "date",
      "rules": [],
      "props":{
          "placeholder":"请输入",
      },
    },
    {
      "label": "制单人",
      "field": "originatorName",
      "type": "input",
      "rules": [ "required" ],
      "props":{
          "placeholder":"请输入",
      },
    },
    {
      "label": "商品",
      "field": "storageOutItems",
      "type": "modal-checkbox",
      "rules": [ "required" ],
      "options": {
        "title": "选择商品",
        "label": "skuName",
        "value": "id",
        "pagination": true,
        "API": "/api/wms/skus",
        "fields": [
          {
            "label": "商品条码",
            "field": "barCode"
          },
          {
            "label": "商品编号",
            "field": "skuCode"
          },
          {
            "label": "商品名称",
            "field": "skuName"
          },
          {
            "label": "单位",
            "field": "field1"
          }
        ]
      },
      "expect": {}
    },
    {
      "label": "备注",
      "field": "note",
      "type": "input",
      "rules": [],
      "props":{
          "placeholder":"请输入",
      },
    },
  ],
  "layout": {
    "table": "Content",
    "form": "TitleContent"
  },
  "tableActions": [
    {
      "title":"添加","type":"path",
      "options":{
          "style":"primary",
          "path":"/wms/storagesOut/storagesOut-add"
      }
    }
  ],
  "tableOperation": [
    {
      "title": "编辑",
      "type": "path",
      "options": {
        "outside": true,
        "path": "/wms/storagesOut/storagesOut-edit"
      },
      "expect":{
        "field": "status",
        "value": "Draft"
      }
    },
    {
      "title": "审核",
      "type": "path",
      "options": {
        "outside": true,
        "path": "/wms/storagesOut/storagesOut-audit"
      },
      "expect":{
        "field": "status",
        "value": "Wait_To_Audit"
      }
    },
    {
      "title": "完成入库", 
      "type": "request",
      "options": {
        "tips": "确定要将该出库记录标记为 ‘已完成’ 吗？",
        "outside": true,
        "API": "/api/wms/storages/out/(id)/execution",
        "method": "put"
      },
      "expect":{
        "field": "status",
        "value": "Audit_Passed"
      }
    },
    {
      "title": "删除",
      "type": "delete",
    }
  ],
  "searchFields": [
    {
        "label": "出库单编号",
        "field": "transactionCode",
        "type": "input",
        "props": {
          "placeholder": "请输入"
        }
    },
    {
      "field": "status", "label": "状态", "type": "select",
      "props": {
        "placeholder": "请选择"
      },
      "options": [
        { label: "草稿", value: "Draft" },
        { label: "待审核", value: "Wait_To_Audit" },
        { label: "审核通过", value: "Audit_Passed" },
        { label: "完成", value: "Done" },
        { label: "关闭", value: "Closed" },
      ]
    },
    {
      "field": "transactionType", "label": "出库类型", "type": "select",
      "props": {
        "placeholder": "请选择"
      },
      "options": [
        { label: "销售出货", value: "SalesOut" },
        { label: "分销商出库", value: "CustomerStorageOut" },
        { label: "采购退货", value: "Refund" },
        { label: "调拨出库", value: "TransferOut" },
        { label: "其它出库", value: "OthersStorageOut" },
      ]
    },

  ],
  "tableFields": [
    {
      title: "出库单编号", field: "transactionCode", valueType: "path",
      options: {
        path: "/wms/storagesOut/storagesOut-view"
      }
    },
    { title: "出库时间", field: "transactionTime" },
    
    { "title": "出库类型", "field": "transactionType", "valueType": "tag",
        "theme":"status",
        "options": {
          "map": {
            "SalesOut": "销售出货",
            "CustomerStorageOut": "分销商出库",
            "Refund": "采购退货",
            "TransferOut": "调拨出库",
            "OthersStorageOut": "其它出库"
          }
        }
    },
    // { title: "批次号", field: "batchNo" },
    { title: "仓库名称", field: "warehouseName" },
    { title: "订单号信息", field: "outOrderNum" },
    { title: "客户", field: "distributorCustomer" },
    { "title": "状态", "field": "status", "valueType": "tag",
        "theme":"status",
        "options": {
          "map": {
            "Draft": "草稿",
            "Wait_To_Audit": "待审核",
            "Audit_Passed": "审核通过",
            "Done": "完成",
            "Closed": "关闭",
          }
        }
    },
    { title: "经办人", field: "transactionBy" },
  ],
  "viewConfig": [
    { "label": "出库编号", "field": "transactionCode", "type": "plain" },
    { "label": "仓库", "field": "warehouseName", "type": "plain" },
    { "label": "经办人", "field": "transactionBy", "type": "plain" },
    { "label": "出库日期", "field": "transactionTime", "type": "plain" },
    { "label": "订单号信息", "field": "outOrderNum", "type": "plain" },
    { "label": "客户", "field": "distributorCustomer", "type": "plain" },
    { "title": "制单人", "field": "originatorName", "type": "plain" },
    { "title": "备注", "field": "note", "type": "plain" },
    {
      "field": "storageOutItems",
      "label": "",
      "type": "one-mary",
      "options": {
        "fields": [
            { "title": "商品条码", "field": "skuBarcode" },
            { "title": "商品编号", "field": "skuCode" },
            { "title": "商品名称", "field": "skuName" },
            { "title": "商品价格", "field": "transactionSkuPrice", "valueType": "currency" },
            { "title": "需求数量", "field": "demandQuantities" },
            { "title": "出库数量", "field": "transactionQuantities" },
            { "title": "单位", "field": "skuUnit" },
            
        ]
      }
    }
  ]
}
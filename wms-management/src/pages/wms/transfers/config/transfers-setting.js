
module.exports =  {
  "columns": 1,
  "listAPI": "/api/wms/transfers",
  "createAPI": "/api/wms/transfers",
  "getAPI": "/api/wms/transfers/[id]",
  "passAPI": "/api/wms/transfers/[id]/passed",
  "refuseAPI": "/api/wms/transfers/[id]/closed",
  "updateAPI": "/api/wms/transfers/[id]",
  "deleteAPI": "/api/wms/transfers/(id)",
  "searchType": "MoreSearch",
  "searchButtonType": "text",
  "pageName": {
    "table": "调拨管理",
    "new": "新增",
    "edit": "编辑",
    "name": "调拨管理",
    "view": "详情"
  },
  "createFields": [
    {
      "label": "调拨编号",
      "field": "transactionCode",
      "type": "serial-code",
      "rules": [ "required" ],
      "props":{
          "prefixValue": "TRF"
      },
    },
    {
      "label": "出库编号",
      "field": "field1",
      "type": "serial-code",
      "rules": [ "required" ],
      "props":{
          "prefixValue": "OUT"
      },
    },
    {
      "label": "操作时间",
      "field": "transactionTime",
      "type": "date",
      "rules": [],
      "props":{
          "placeholder":"请选择",
      },
    },
    {
      "label": "调出仓库",
      "field": "fromWarehouseId",
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
      "field": "outItems",
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
      "label": "调拨编号",
      "field": "transactionCode",
      "type": "plain",
    },
    {
      "label": "出库编号",
      "field": "field1",
      "type": "plain",
    },
    {
      "label": "操作时间",
      "field": "transactionTime",
      "type": "date",
      "rules": [],
      "props":{
          "placeholder":"请选择",
      },
    },
    {
      "label": "调出仓库",
      "field": "fromWarehouseId",
      "span": 24,
      "type": "modal-radio",
      "props": {},
      "rules": [ "required" ],
      "options": {
        "title": "选择仓库",
        "label": "warehouseName",
        "editLabel": "fromWarehouseName",
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
      "field": "outItems",
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
          "path":"/wms/transfers/transfers-add"
      }
    }
  ],
  "tableOperation": [
    
    {
      "title": "开始调拨",
      "type": "request",
      "options": {
        "outside": true,
        "tips": "您确定要 开始调拨 吗？",
        "API": "/api/wms/transfers/(id)/execution",
        "method": "post"
      },
      "expect": {
        "field": "status",
        "value": "Audit_Passed",
        "permission": ""
      }
    },
    {
      "title": "完成",
      "type": "request",
      "options": {
        "outside": true,
        "tips": "您确定要标记这份调拨单标记为 完成入库 吗？",
        "API": "/api/wms/transfers/(id)/done",
        "method": "put"
      },
      "expect": {
        "field": "status",
        "value": "Transfer",
        "permission": ""
      }
    },
    {
      "title": "作废",
      "type": "request",
      "options": {
        "outside": true,
        "tips": "您确定要标记这份调拨单标记为 作废 吗？",
        "API": "/api/wms/transfers/(id)/cancel",
        "method": "put"
      },
      "expect": {
        "field": "status",
        "value": "Transfer",
        "permission": ""
      }
    },
    {
      "title": "审核",
      "type": "path",
      "options": {
        "outside": true,
        "path": "/wms/transfers/transfers-audit"
      },
      "expect":{
        "field": "status",
        "value": "Wait_To_Audit"
      }
    },
    {
      "title": "编辑",
      "type": "path",
      "options": {
        "outside": true,
        "path": "/wms/transfers/transfers-edit"
      },
      "expect": {
        "field": "status",
        "value": "Draft",
        "permission": ""
      }
    },
    {
      "title": "详情",
      "type": "path",
      "options": {
        "outside": false,
        "path": "/wms/transfers/transfers-view"
      }
    },
    {
      "title": "删除",
      "type": "delete"
    }
  ],
  "searchFields": [
    {
        "label": "调拨编号",
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
        { label: '调拨中', value: 'Transfer' },
        { label: '草稿', value: 'Draft' },
        { label: '待审核', value: 'Wait_To_Audit' },
        { label: '审核通过', value: 'Audit_Passed' },
        { label: '完成', value: 'Done' },
        { label: '关闭', value: 'Closed' },
      ]
    },
    {
      "label": "调出仓库",
      "type": "select-fetch",
      "props": {
        "placeholder": "请选择"
      },
      "rules": [],
      "options": {
        "API": "/api/wms/warehouses",
        "label": "warehouseName",
        "value": "id"
      },
      "field": "fromWarehouseName"
    },
    {
      "label": "调入仓库",
      "type": "select-fetch",
      "props": {
        "placeholder": "请选择"
      },
      "rules": [],
      "options": {
        "API": "/api/wms/warehouses",
        "label": "warehouseName",
        "value": "id"
      },
      "field": "toWarehouseName"
    },
    {
      label: "调出时间",
      field: 'transactionTime',
      placeholder: ['开始时间', '结束时间'],
      type: 'range',
      format: 'YYYY-MM-DD'
    },

  ],
  "tableFields": [
    { label: "调拨编号", field: "transactionCode", valueType: 'path', options: {
        path: '/wms/transfers/transfers-view',
      } 
    },
    { label: "调出仓库", field: "fromWarehouseName" },
    { label: "调入仓库", field: "toWarehouseName" },
    { label: "调出时间", field: "transactionTime" },
    { label: "调入时间", field: "finishTime" },
    { "label": "状态", "field": "status", "valueType": "tag",
        "theme":"status",
        "options": {
          "map": {
            "Transfer": '调拨中',
            "Draft": '草稿', 
            "Wait_To_Audit": '待审核',
            "Audit_Passed": '审核通过', 
            "Done": '完成', 
            "Closed": '关闭'
          }
        }
    }
  ],
  "viewConfig": [
    { "label": "调出仓库", "field": "fromWarehouseName", "type": "plain" },
    { "label": "调出时间", "field": "transactionTime", "type": "plain" },
    { "label": "调入仓库", "field": "toWarehouseName", "type": "plain" },
    { "label": "调入时间", "field": "finishTime", "type": "plain" },
    { "label": "经办人", "field": "transactionBy", "type": "plain" },
    { "label": "制单人", "field": "originatorName", "type": "plain" },
    { "label": "备注", "field": "note", "type": "plain" },
    {
      "field": "outItems",
      "label": "",
      "type": "one-mary",
      "options": {
        "fields": [
            { "title": "商品条码", "field": "skuBarcode" },
            { "title": "商品编号", "field": "skuCode" },
            { "title": "商品名称", "field": "skuName" },
            { "title": "需求数量", "field": "demandQuantities" },
            { "title": "调拨数量", "field": "transactionQuantities" },
            { "title": "单位", "field": "skuUnit" },
        ]
      }
    }
  ]
}
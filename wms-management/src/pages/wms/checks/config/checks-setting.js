const formFields = require("../formSettings/checks-form-settings");

module.exports =  {
  "columns": 1,
  "listAPI": "/api/wms/checks",
  "createAPI": "/api/wms/checks",
  "getAPI": "/api/wms/checks/[id]",
  "updateAPI": "/api/wms/checks/[id]",
  "checkingAPI": "/api/wms/checks/[id]/checking",
  "passAPI": "/api/wms/checks/[id]/passed",
  "refuseAPI": "/api/wms/checks/[id]/closed",
  "deleteAPI": "/api/wms/checks/(id)",
  "searchType": "MoreSearch",
  "searchButtonType": "text",
  "pageName": {
    "table": "库存盘点管理",
    "new": "新增",
    "edit": "编辑",
    "name": "库存盘点管理",
    "view": "详情",
    "checking": "盘点"
  },
  "createFields": formFields,
  "updateFields": formFields,
  "checkingFields": formFields,
  "layout": {
    "table": "Content",
    "form": "TitleContent"
  },
  "tableActions": [
    {
      "title": "新增",
      "type": "path",
      "options": {
        "style":"primary",
        "path": "/wms/checks/checks-add"
      },
      "expect": {
        "permission": ""
      }
    }
  ],
  "tableOperation": [
    {
      "title": "查看",
      "type": "path",
      "options": {
        "outside": false,
        "path": "/wms/checks/checks-view"
      }
    },
    {
      "title": "编辑",
      "type": "path",
      "options": {
        "outside": true,
        "path": "/wms/checks/checks-edit"
      },
      expect: {
        field: "status",
        value: "Draft"
      }
    },
    {
      "title": "盘点",
      "type": "path",
      "options": {
        "outside": false,
        "path": "/wms/checks/checks-checking"
      },
      expect:{
        field: "status",
        value: "Draft"
      }
    },
    {
      "title": "继续盘点",
      "type": "path",
      "options": {
        "outside": false,
        "path": "/wms/checks/checks-checkGoOn"
      },
      expect:{
        field: "status",
        value: "Checking"
      }
    },
    {
      "title": "完成盘点",
      "type": "request",
      "options": {
        "outside": false,
        "tips": "确定要将该盘点记录标记为 ‘已完成盘点’ 吗？",
        "API": "/api/wms/checks/(id)/done",
        "method": "post"
      },
      "expect": {
        "field": "status",
        "value": "Checking",
        "permission": ""
      }
    },
    {
      "title": "审核",
      "type": "path",
      "options": {
        "outside": false,
        "path": "/wms/checks/checks-audit"
      },
      expect:{
        field: "status",
        value: "CheckOut"
      }
    },
    {
      "title": "删除",
      "type": "delete"
    }
  ],
  "searchFields": [
      {
        "field": "checkCode",
        "label": "盘点编号",
        "type": "input",
        "props": {
          "placeholder": "请输入..."
        }
      },
      {
        "field": "status", "label": "状态", "type": "select",
        "props": {
          "placeholder": "请选择"
        },
        "options": [
          { label: '待盘点', value: 'WaitForCheck' },
          { label: '盘点中', value: 'Checking' },
          { label: '盘点结束', value: 'CheckOut' },
          { label: '草稿', value: 'Draft' },
          { label: '待审核', value: 'Wait_To_Audit' },
          { label: '完成', value: 'Done' },
          { label: '关闭', value: 'Closed' },
        ]
      }
  ],
  "tableFields": [
    {
      "title": "盘点编号", "field": "checkCode", "valueType": "path",
      "options": {
        "path": "/wms/checks/checks-view"
      }
    },
    { "title": "盘点仓库", "field": "warehouseName" },
    { "title": "经办人", "field": "transactionBy" },
    { "title": "备注", "field": "checkNote" },
    { "title": "盘点时间", "field": "checkTime" },
    { "title": "状态", "field": "status", "valueType": "tag",
        "theme":"status",
        "options": {
          "map": {
            'WaitForCheck': '待盘点',
            'Checking': '盘点中',
            'CheckOut': '盘点结束',
            'Draft': '草稿',
            'Wait_To_Audit': '待审核',
            'Done': '完成',
            'Closed': '关闭',
          }
        }
    },
    { "title": "制单人", "field": "originatorName" }
  ],
  "viewConfig": [
    { "label": "盘点编号", "field": "checkCode", "type": "plain" },
    { "label": "仓库", "field": "warehouseName", "type": "plain" },
    { "label": "经办人", "field": "transactionBy", "type": "plain" },
    { "label": "盘点时间", "field": "checkTime", "type": "plain"},
    { "label": "备注", "field": "checkNote", "type": "plain"},
    { "label": "制单人", "field": "originatorName", "type": "plain" },

    {
      "field": "checkSkus",
      "label": "",
      "type": "one-mary",
      "options": {
        "fields": [
            { "title": "商品条码", "field": "skuBarcode" },
            { "title": "商品编号", "field": "skuCode" },
            { "title": "商品名称", "field": "skuName" },
            { "title": "需求数量", "field": "demandQuantities" },
            { "title": "盘点前数量", "field": "beforeProofQuantities" },
            { "title": "盘点后数量", "field": "factQuantities" },
            { "title": "盈亏数量", "field": "profitLost" },
            { "title": "单位", "field": "skuUnit" }
        ]
      }
    }
  ]
}
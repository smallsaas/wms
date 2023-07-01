const { getUserName } = require('zero-element/lib/utils/request/token');

module.exports =  {
  "columns": 1,
  "listAPI": "/api/warehouse/sales",
  "createAPI": "/api/warehouse/sales",
  "getAPI": "/api/warehouse/sales/[id]",
  "updateAPI": "/api/warehouse/sales/[id]",
  "passAPI": "/api/warehouse/sales/[id]/passed",
  "refuseAPI": "/api/warehouse/sales/[id]/closed",
  "deleteAPI": "/api/warehouse/sales/(id)",
  "searchType": "MoreSearch",
  "searchButtonType": "text",
  "pageName": {
    "table": "分销单管理",
    "new": "新增",
    "edit": "编辑",
    "name": "分销单管理",
    "view": "详情"
  },
  "createFields": [
    {
      "label": "分销订单编号",
      "field": "salesCode",
      "type": "serial-code",
      "rules": [ "required" ],
      "props":{
          "placeholder":"请输入",
          "prefixValue": "SAL"
      },
    },
    {
      "label": "分销商",
      "field": "traderId",
      "span": 24,
      "type": "modal-radio",
      "props": {},
      "rules": [ "required" ],
      "options": {
        "title": "选择分销商",
        "label": "traderName",
        "editLabel": "traderName",
        "value": "id",
        "pagination": true,
        "saveData":{
          "traderId": 'id',
          "traderContactPhone": 'traderContactPhone',
          "traderName": 'traderName',
          "deliveredAddress": 'deliveredAddress',
        },
        "API": "/api/warehouse/traders?traderStatus=Normal",
        "fields": [
          {
            "label": "名称",
            "field": "traderName"
          }
        ]
      },
    },
    {
      "label": "收货地址",
      "field": "deliveredAddress",
      "type": "input",
      "rules": [ "required" ],
      "props":{
          "placeholder":"请输入",
      },
    },
    {
      "label": "供应商电话",
      "field": "traderContactPhone",
      "type": "input",
      "rules": [ "required" ],
      "props":{
          "placeholder":"请输入",
      },
    },
    {
      "label": "时间",
      "field": "salesTime",
      "type": "date",
      "rules": [],
      "props":{
          "placeholder":"请选择",
          "style":{
            "width": "240px"
          }
      },
    },
    {
      "label": "经办员",
      "field": "transactionBy",
      "type": "input",
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
          "defaultValue": getUserName()
      },
    },
    {
      "label": "备注",
      "field": "salesNote",
      "type": "input",
      "rules": [],
      "props":{
          "placeholder":"请输入",
      },
    },
    {
      "label": "商品",
      "field": "items",
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
  ],
  "updateFields": [

    {
      "label": "分销订单编号",
      "field": "salesCode",
      "type": "serial-code",
      "rules": [ "required" ],
      "props":{
          "placeholder":"请输入",
          "prefixValue": "SAL"
      },
    },
    {
      "label": "分销商",
      "field": "traderId",
      "span": 24,
      "type": "modal-radio",
      "props": {},
      "rules": [ "required" ],
      "options": {
        "title": "选择分销商",
        "label": "traderName",
        "editLabel": "traderName",
        "value": "id",
        "pagination": true,
        "saveData":{
          "traderId": 'id',
          "traderContactPhone": 'traderContactPhone',
          "traderName": 'traderName',
          "deliveredAddress": 'deliveredAddress',
        },
        "API": "/api/warehouse/traders?traderStatus=Normal",
        "fields": [
          {
            "label": "名称",
            "field": "traderName"
          }
        ]
      },
    },
    {
      "label": "收货地址",
      "field": "deliveredAddress",
      "type": "input",
      "rules": [ "required" ],
      "props":{
          "placeholder":"请输入",
      },
    },
    {
      "label": "供应商电话",
      "field": "traderContactPhone",
      "type": "input",
      "rules": [ "required" ],
      "props":{
          "placeholder":"请输入",
      },
    },
    {
      "label": "时间",
      "field": "salesTime",
      "type": "date",
      "rules": [],
      "props":{
          "placeholder":"请选择",
          "style":{
            "width": "240px"
          }
      },
    },
    {
      "label": "经办员",
      "field": "transactionBy",
      "type": "input",
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
          "defaultValue": getUserName()
      },
    },
    {
      "label": "备注",
      "field": "salesNote",
      "type": "input",
      "rules": [],
      "props":{
          "placeholder":"请输入",
      },
    },
    {
      "label": "商品",
      "field": "items",
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
    }
  ],
  "warehouseOutFields":[
    {
      "label": "出库仓库",
      "field": "field1",
      "span": 24,
      "type": "modal-radio",
      "props": {},
      "rules": [ "required" ],
      "options": {
        "title": "选择商品",
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
      "label": "出库单号",
      "field": "field2",
      "type": "serial-code",
      "rules": [ "required" ],
      "props":{
          "placeholder":"请输入",
          "prefixValue": "IN"
      },
    },
    {
      "label": "操作时间",
      "field": "transactionTime",
      "type": "date",
      "rules": [],
      "props":{
          "placeholder":"请选择",
          "style":{
            "width": "240px"
          }
      },
    },
    {
      "label": "经办员",
      "field": "transactionBy",
      "type": "input",
      "rules": [],
      "props":{
          "placeholder":"请输入",
      },
    },
    {
      "label": "制单人",
      "field": "originatorName",
      "type": "input",
      "rules": ["required"],
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
        "API": "/api/wms/inventories",
        "fields": [
          {
            "label": "商品条码",
            "field": "skuBarcode"
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
            "label": "数量",
            "field": "validSku"
          },
          {
            "label": "单位",
            "field": "skuUnit"
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
          "path":"/wms/sales/sales-add"
      }
    }
  ],
  "tableOperation": [
    {
      "title": "出库",
      "type": "path",
      "options": {
        "outside": true,
        "path": "/wms/sales/sales-warehouseOut"
      },
      "expect":{
        "field": "salesStatus",
        "value": "WaitForStorageOut"
      }
    },
    {
      "title": "审核",
      "type": "path",
      "options": {
        "outside": true,
        "path": "/wms/sales/sales-audit"
      },
      "expect":{
        "field": "salesStatus",
        "value": "Wait_To_Audit"
      }
    },
    {
      "title": "编辑",
      "type": "path",
      "options": {
        "outside": true,
        "path": "/wms/sales/sales-edit"
      },
      "expect":{
        "field": "salesStatus",
        "value": "Draft"
      }
    },
    {
      "title": "详情",
      "type": "path",
      "options": {
        "outside": true,
        "path": "/wms/sales/sales-view",
        "query": {
          "id": "id",
          "salesStatus": "salesStatus"
        }
      }
    },
    {
      "title": "删除",
      "type": "delete",
      "expect":{
        "field": "salesStatus",
        "value": "Draft"
      }
    }
  ],
  "searchFields": [
    {
        "label": "分销订单编号",
        "field": "salesCode",
        "type": "input",
        "props": {
          "placeholder": "请输入"
        }
    },
    {
      "label": "状态",
      field: 'status',
      placeholder: '状态',
      type: 'select',
      options: [
        { label: '待出库', value: 'WaitForStorageOut' },
        { label: '部分出库', value: 'SectionStorageOut' },
        { label: '全部出库', value: 'TotalStorageOut' },
        { label: '草稿', value: 'Draft' },
        { label: '待审核', value: 'Wait_To_Audit' },
        { label: '关闭', value: 'Closed' },
      ],
    }

  ],
  "tableFields": [
    {
      title: '订单编号', field: 'salesCode', valueType: 'path',
      options: {
        path: '/wms/sales/sales-view',
      }
    },
    { title: '分销商', field: 'traderName' },
    { title: '订单总费用', field: 'salesTotal', valueType: 'currency' },
    { title: '订单总数量', field: 'totalCount' },
    { title: '创建时间', field: 'transactionTime' },
    { "title": "状态", "field": "salesStatus", "valueType": "tag",
        "theme":"status",
        "options": {
          "map": {
            'WaitForStorageOut': '待出库',
            'SectionStorageOut': '部分出库',
            'TotalStorageOut': '全部出库',
            'Draft': '草稿',
            'Wait_To_Audit': '待审核',
            'Closed': '关闭'
          }
        }
    }
  ],
  "viewConfig": [
    
    { "label": "订单编号：", "field": "salesCode", "type": "plain" },
    { "label": "订单创建人：", "field": "originatorName", "type": "plain" },
    { "label": "创建日期：", "field": "transactionTime", "type": "plain" },
    { "label": "分销商名称：", "field": "traderName", "type": "plain" },
    { "label": "联系电话：", "field": "traderContactPhone", "type": "plain" },
    { "label": "经办人：", "field": "transactionBy", "type": "plain" },
    { "label": "收货人：", "field": "traderContactName", "type": "plain" },
    { "label": "收货地址：", "field": "deliveredAddress", "type": "plain" },
    { "label": "备注：", "field": "salesNote", "type": "plain" },
    {
      "field": "outItems",
      "label": "",
      "type": "one-mary",
      "options": {
        "fields": [
            { "title": "商品编号", "field": "skuCode" },
            { "title": "商品条码", "field": "skuBarcode" },
            { "title": "商品名称", "field": "skuName" },
            { "title": "单位", "field": "skuUnit" },
            { "title": "需求数量", "field": "demandQuantities" },
            { "title": "销售数量", "field": "transactionQuantities" },
            { "title": "销售单价", "field": "transactionSkuPrice" },
            // { "title": "销售总价", "field": "transactionTotalSkuPrice", "valueType": "currency" },
            
        ]
      }
    }
  ],
  "warehouseOutViewConfig":[
    { "label": "订单编号", "field": "salesCode", "type": "plain" },
    { "label": "订单创建人", "field": "originatorName", "type": "plain" },
    { "label": "创建日期", "field": "transactionTime", "type": "plain"},
    { "label": "分销商名称", "field": "traderName", "type": "plain" },
    { "label": "联系电话", "field": "traderContactPhone", "type": "plain"},
    { "label": "经办人", "field": "transactionBy", "type": "plain"},
    { "label": "收货地址", "field": "deliveredAddress", "type": "plain"},
    { "label": "备注", "field": "salesNote", "type": "plain"},
    {
      "field": "outItems",
      "label": "出库记录",
      "type": "one-mary",
      "options": {
        "fields": [
          { "title": "商品编号", "field": "skuCode" },
          { "title": "商品条码", "field": "skuBarcode" },
          { "title": "商品名称", "field": "skuName" },
          { "title": "单位", "field": "skuUnit" },
          { "title": "本次出库数量", "field": "transactionQuantities" },
          { "title": "总数", "field": "totalCount" },
          { "title": "已出库数", "field": "sectionInCount" },
        ]
      }
    }
  ]
}
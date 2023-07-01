module.exports =  {
  "columns": 1,
  "listAPI": "/api/wms/storages/in",
  "createAPI": "/api/wms/storages/in",
  "getAPI": "/api/wms/storages/in/[id]",
  "updateAPI": "/api/wms/storages/in/[id]",
  "passAPI": "/api/wms/storages/in/[id]/passed",
  "refuseAPI": "/api/wms/storages/in/[id]/closed",
  "deleteAPI": "/api/wms/storages/in/(id)",
  "searchType": "MoreSearch",
  "searchButtonType": "text",
  "pageName": {
    "table": "入库管理",
    "new": "新增",
    "edit": "编辑",
    "name": "入库管理",
    "view": "详情"
  },
  "createFields": [
    {
      "label": "入库编号",
      "field": "transactionCode",
      "type": "serial-code",
      "rules": [ "required" ],
      "props":{
          "prefixValue": "IN"
      },
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
      "label": "入库时间",
      "field": "transactionTime",
      "type": "date",
      "rules": [],
      "props":{
          "placeholder":"请选择",
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
      "field": "storageInItems",
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
      "label": "入库编号",
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
      "label": "入库时间",
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
      "field": "storageInItems",
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
      "title": "添加",
      "type": "path",
      "options": {
        "style":"primary",
        "path": "/wms/storagesIn/storagesIn-add"
      },
      "expect": {
        "permission": ""
      }
    }
  ],
  "tableOperation": [
    {
      "title": "编辑",
      "type": "path",
      "options": {
        "outside": true,
        "path": "/wms/storagesIn/storagesIn-edit"
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
        "path": "/wms/storagesIn/storagesIn-audit"
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
        "tips": "确定要将该入库记录标记为 ‘已完成’ 吗？",
        "outside": true,
        "API": "/api/wms/storages/in/(id)/execution",
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
        "label": "入库单编号",
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
        { label: '草稿', value: 'Draft' },
        { label: '待审核', value: 'Wait_To_Audit' },
        { label: '审核通过', value: 'Audit_Passed' },
        { label: '完成', value: 'Done' },
        { label: '关闭', value: 'Closed' },
      ]
    },
    {
      "field": "transactionType", "label": "入库类型", "type": "select",
      "props": {
        "placeholder": "请选择"
      },
      "options": [
        { label: '销售退货', value: 'SalesIn' },
        { label: '调拨入库', value: 'TransferIn' },
        { label: '采购入库', value: 'Procurement' },
        { label: '分销商退货', value: 'CustomerStorageIn' },
        { label: '其它入库', value: 'OthersStorageIn' },
      ]
    },
  ],
  "tableFields": [
    {
      title: '入库单编号', field: 'transactionCode', valueType: 'path',
      options: {
        path: '/wms/storagesIn/storagesIn-view',
      }
    },
    { title: '入库时间', field: 'transactionTime' },
    { "title": "入库类型", "field": "transactionType", "valueType": "tag",
        "theme":"status",
        "options": {
          "map": {
            "SalesIn": "销售退货",
            "TransferIn": "调拨入库",
            "Procurement": "采购入库",
            "CustomerStorageIn": "分销商退货",
            "OthersStorageIn": "其它入库",
          }
        }
    },
    // { title: '批次号', field: 'batchNo', sorter: (a, b) => a.batchNo - b.batchNo },
    { title: '仓库', field: 'warehouseName' },
    // { title: '储位', field: 'slotName' },
    { "title": "状态", "field": "status", "valueType": "tag",
        "theme":"status",
        "options": {
          "map": {
            'Draft': '草稿',
            'Wait_To_Audit': '待审核',
            'Audit_Passed': '审核通过',
            'Done': '完成',
            'Closed': '关闭',
          }
        }
    },
    // { title: '备注', field: 'note' },
    { title: '经办人', field: 'transactionBy' }
  ],
  "viewConfig": [
    { "label": "仓库", "field": "warehouseName", "type": "plain" },
    { "label": "入库编号", "field": "transactionCode", "type": "plain" },
    { "label": "入库时间", "field": "transactionTime", "type": "plain" },
    { "label": "制单人", "field": "originatorName", "type": "plain" },
    {
      "label": "入库类型",
      "field": "transactionType",
      "type": "map",
      "options": {
        "map": {
          'SalesIn': '销售退货',
          'TransferIn': '调拨入库',
          'Procurement': '采购入库',
          'CustomerStorageIn': '分销商退货',
          'OthersStorageIn': '其它入库',
        }
      }
    },
    {
      "field": "storageInItems",
      "label": "",
      "type": "one-mary",
      "options": {
        "fields": [
            { "title": "商品条码", "field": "skuBarcode" },
            { "title": "商品名称", "field": "skuName" },
            { "title": "单位", "field": "skuUnit" },
            { "title": "数量", "field": "transactionQuantities" },
            { "title": "单价", "field": "transactionSkuPrice", "valueType": "currency" },
            
        ]
      }
    }
  ]
}
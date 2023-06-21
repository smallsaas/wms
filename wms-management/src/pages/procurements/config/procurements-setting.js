module.exports =  {
  "columns": 1,
  "listAPI": "/api/wms/procurements",
  "createAPI": "/api/wms/procurements",
  "getAPI": "/api/wms/procurements/[id]",
  "updateAPI": "/api/wms/procurements/[id]",
  "deleteAPI": "/api/wms/procurements/(id)",
  "searchType": "MoreSearch",
  "searchButtonType": "text",
  "pageName": {
    "table": "采购单管理",
    "new": "新增",
    "edit": "编辑",
    "name": "采购单管理",
    "view": "详情"
  },
  "createFields": [
      {
        "label": "采购单编号",
        "field": "procurementCode",
        "type": "serial-code",
        "rules": [ "required" ],
        "props":{
            "placeholder":"请输入",
            "prefixValue": "PUR"
        },
      },
      
      {
        "label": "供应商",
        "field": "supplierId",
        "span": 24,
        "type": "modal-radio",
        "props": {},
        "rules": [ "required" ],
        "options": {
          "title": "选择商品",
          "label": "supplierName",
          "editLabel": "supplierName",
          "value": "id",
          "pagination": true,
          "API": "/api/wms/suppliers?supplierStatus=Normal",
          "fields": [
            {
              "label": "名称",
              "field": "supplierName"
            }
          ]
        }
      },
      {
        "label": "采购时间",
        "field": "procurementTime",
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
        "label": "采购备注",
        "field": "procurementNote",
        "type": "input",
        "rules": [],
        "props":{
            "placeholder":"请输入",
        },
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
        "label": "制单人名称",
        "field": "originatorName",
        "type": "input",
        "rules": [ "required" ],
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
              "label": "单位",
              "field": "skuUnit"
            }
          ]
        },
        "expect": {}
      },
    
  ],
  "updateFields": [
    {
      "label": "采购单编号",
      "field": "procurementCode",
      "type": "plain"
    },
    
    {
      "label": "供应商",
      "field": "supplierId",
      "span": 24,
      "type": "modal-radio",
      "props": {},
      "rules": [ "required" ],
      "options": {
        "title": "选择商品",
        "label": "supplierName",
        "editLabel": "supplierName",
        "value": "id",
        "pagination": true,
        "API": "/api/wms/suppliers?supplierStatus=Normal",
        "fields": [
          {
            "label": "名称",
            "field": "supplierName"
          }
        ]
      }
    },
    {
      "label": "采购时间",
      "field": "procurementTime",
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
      "label": "采购备注",
      "field": "procurementNote",
      "type": "input",
      "rules": [],
      "props":{
          "placeholder":"请输入",
      },
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
      "label": "制单人名称",
      "field": "originatorName",
      "type": "input",
      "rules": [ "required" ],
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
            "label": "单位",
            "field": "skuUnit"
          }
        ]
      },
      "expect": {}
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
        "path": "/procurements/procurements-add"
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
        "outside": true,
        "path": "/procurements/procurements-view"
      }
    },
    {
      "title": "编辑",
      "type": "path",
      "options": {
        "outside": true,
        "path": "/procurements/procurements-edit"
      }
    },
    // {
    //   "title": "删除",
    //   "type": "delete"
    // }
  ],
  "searchFields": [
    {
      "field": "search",
      "label": "搜索",
      "type": "input",
      "props": {
        "placeholder": "采购单编号/供应商"
      }
    },
    {
      "field": "status", "label": "状态", "type": "select",
      "props": {
        "placeholder": "请选择"
      },
      "options": [
        { label: '等待入库', value: 'WaitForStorageIn' },
        { label: '部分入库', value: 'SectionStorageIn' },
        { label: '全部入库', value: 'TotalStorageIn' },
        { label: '草稿', value: 'Draft' },
        { label: '待审核', value: 'Wait_To_Audit' },
        { label: '审核通过', value: 'Audit_Passed' },
        { label: '关闭', value: 'Closed' },
      ]
    },
    {
      label: "日期",
      field: 'procurementTime',
      placeholder: ['开始时间', '结束时间'],
      type: 'range',
      format: 'YYYY-MM-DD'
    },
  ],
  "tableFields": [
    {
      title: '采购单编号', field: 'procurementCode', valueType: 'path',
      options: {
        path: '/procurements',
        queryData: {
          type: 'query',
          id: '{id}',
          tabs: '1',
        }
      }
    },
    { title: '供应商', field: 'supplierName' },
    // { title: '仓库名', field: 'warehouseName' },
    // { title: '采购折扣', field: 'procurementDiscount', align: 'right', valueType: 'currency' },
    // { title: '其它支出', field: 'procurementOthersPayment', align: 'right', valueType: 'currency' },
    { title: '总花费', field: 'procurementTotal', align: 'right', valueType: 'currency' },
    { title: '采购时间', field: 'procurementTime' },
    
    { "title": "状态", "field": "procureStatus", "valueType": "tag",
        "theme":"status",
        "options": {
          "map": {
            'Audit_Passed': '待入库',
            'SectionStorageIn': '部分入库',
            'TotalStorageIn': '全部入库',
            'Closed': '关闭'
          }
        }
    }
  ],
  "viewConfig": [
    
  ]
}
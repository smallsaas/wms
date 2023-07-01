module.exports =  {
  "columns": 1,
  "listAPI": "/api/wms/procurements",
  "createAPI": "/api/wms/procurements",
  "getAPI": "/api/wms/procurements/[id]",
  "updateAPI": "/api/wms/procurements/[id]",
  "passAPI": "/api/wms/procurements/[id]/passed",
  "refuseAPI": "/api/wms/procurements/[id]/closed",
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
  "warehousingFields":[
    {
      "label": "入库仓库",
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
      "label": "入库单号",
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
      "label": "制单人ID",
      "field": "originatorId",
      "type": "input",
      "rules": ["required"],
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
      "label": "入库商品",
      "field": "items",
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
      "title": "添加",
      "type": "path",
      "options": {
        "style":"primary",
        "path": "/wms/procurements/procurements-add"
      },
      "expect": {
        "permission": ""
      }
    }
  ],
  "tableOperation": [
    {
      "title": "入库",
      "type": "path",
      "options": {
        "outside": true,
        "path": "/wms/procurements/procurements-warehousing",
      },
      "expect":{
        "field": "procureStatus",
        "value": "/(Audit_Passed|SectionStorageIn)/"
      }
    },
    
    {
      "title": "提交审核",
      "type": "path",
      "options": {
        "outside": true,
        "path": "/wms/procurements/procurements-submitAudit",
      },
      "expect":{
        "field": "procureStatus",
        "value": "Draft"
      }
    },
        
    {
      "title": "审核",
      "type": "path",
      "options": {
        "outside": true,
        "path": "/wms/procurements/procurements-audit",
      },
      "expect":{
        "field": "procureStatus",
        "value": "Wait_To_Audit"
      }
    },
    {
      "title": "查看",
      "type": "path",
      "options": {
        "path": "/wms/procurements/procurements-view",
        "query": {
          "id": "id",
          "procureStatus": "procureStatus"
        }
      }
    },
    {
      "title": "编辑",
      "type": "path",
      "options": {
        "path": "/wms/procurements/procurements-edit"
      },
      "expect":{
        "field": "procureStatus",
        "value": "Draft"
      }
    },
    {
      "title": "删除",
      "type": "delete",
      "expect":{
        "field": "procureStatus",
        "value": "Wait_To_Audit"
      }
    }
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
        path: '/wms/procurements/procurements-view',
        query: {
          id: "id",
          procureStatus: "procureStatus"
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
            'WaitForStorageIn': '等待入库',
            'SectionStorageIn': '部分入库',
            'TotalStorageIn': '全部入库',
            'Draft': '草稿',
            'Wait_To_Audit': '待审核',
            'Audit_Passed': '审核通过',
            'Closed': '关闭',
          }
        }
    }
  ],
  "viewConfig": [
    { "label": "采购单编号", "field": "procurementCode", "type": "plain" },
    { "label": "供应商", "field": "supplierName", "type": "plain" },
    { "label": "采购员", "field": "transactionBy", "type": "plain" },
    { "label": "采购日期", "field": "procurementTime", "type": "plain"},
    { "label": "制单人", "field": "originatorName", "type": "plain" },
    { "label": "备注", "field": "procurementNote", "type": "plain"},

    {
      "field": "items",
      "label": "",
      "type": "one-mary",
      "options": {
        "fields": [
            { "title": "商品条码", "field": "skuBarcode" },
            { "title": "商品编号", "field": "skuCode" },
            { "title": "商品名称", "field": "skuName" },
            { "title": "需求数量", "field": "demandQuantities" },
            { "title": "采购数量", "field": "transactionQuantities" },
            { "title": "已入库数量", "field": "sectionInCount" },
            { "title": "采购单价", "field": "transactionSkuPrice", "valueType": "currency" },
            // { "title": "采购总价", "field": "transactionTotalSkuPrice", "valueType": "currency" },
            { "title": "单位", "field": "skuUnit" },
        ]
      }
    }
  ],
  "warehousingViewConfig": [
    { "label": "采购单编号", "field": "procurementCode", "type": "plain" },
    { "label": "供应商", "field": "supplierName", "type": "plain" },
    { "label": "创建时间：", "field": "transactionTime", "type": "plain"},
    { "label": "制单人", "field": "originatorName", "type": "plain" },
    { "label": "备注", "field": "procurementNote", "type": "plain"},
    {
      "field": "inHistories",
      "label": "",
      "type": "one-mary",
      "options": {
        "fields": [
            { "title": "商品条码", "field": "skuBarcode" },
            { "title": "入库时间", "field": "transactionTime" },
            { "title": "商品编号", "field": "skuCode" },
            { "title": "商品名称", "field": "skuName" },
            { "title": "入库数量", "field": "transactionQuantities" },
            { "title": "入库价格", "field": "transactionSkuPrice", "valueType": "currency" },
            { "title": "单位", "field": "skuUnit" }
        ]
      }
    }
  ]
}
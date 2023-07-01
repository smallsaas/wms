module.exports =  {
  "columns": 1,
  "listAPI": "/api/wms/refunds",
  "createAPI": "/api/wms/refunds",
  "getAPI": "/api/wms/refunds/[id]",
  "passAPI": "/api/wms/refunds/[id]/passed",
  "refuseAPI": "/api/wms/refunds/[id]/closed",
  "updateAPI": "/api/wms/refunds/[id]",
  "deleteAPI": "/api/wms/refunds/(id)",
  "searchType": "MoreSearch",
  "searchButtonType": "text",
  "pageName": {
    "table": "采购退货管理",
    "new": "新增",
    "edit": "编辑",
    "name": "采购退货管理",
    "view": "详情"
  },
  "createFields": [
    {
      "label":"退货单编号",
      "field": 'productRefundCode', "type": 'serial-code',
      "rules": [ "required" ],
      "props":{
          "prefixValue": "REF"
      },
    },
    {
      "label":"出库单编号",
      "field": 'field1', "type": 'serial-code',
      "rules": [ "required" ],
      "props":{
          "prefixValue": "OUT"
      },
    },
    
    {
      "label": "供应商",
      "field": "supplierId",
      "type": "modal-radio",
      "rules": [
        { "type": "required" }
      ],
      "options": {
        "title": "请选择",
        "label": "supplierName",
        "editLabel": "supplierName",
        "value": "id",
        "API": "/api/wms/suppliers?supplierStatus=Normal",
        "fields": [
          {
            "label": "供应商",
            "field": "supplierName"
          }
        ],
        "saveData": {
          "supplierId": "id",
          "supplierName": "supplierName",
        }
      }
    },

    {
      "label": "关联采购单",
      "field": "productProcurementId",
      "type": "modal-radio",
      "rules": [ "required" ],
      "options": {
        "title": "请选择",
        "label": "procurementCode",
        "editLabel": "procurementCode",
        "value": "id",
        "API": "/api/wms/procurements?procureStatus=TotalStorageIn&supplierId=(supplierId)",
        "fields": [
          {
            "label": "单号",
            "field": "procurementCode"
          }
        ]
      },
      "expect":{
        "field": "supplierId",
        "value": "IS_NOT_NULL"
      }
    },
    
    {
      "label": "退货所在仓库",
      "field": "productRefundWarehouseId",
      "type": "modal-radio",
      "rules": [
        { "type": "required" }
      ],
      "options": {
        "title": "请选择",
        "label": "warehouseName",
        "editLabel": "warehouseName",
        "value": "id",
        "API": "/api/wms/warehouses",
        "fields": [
          {
            "label": "仓库",
            "field": "warehouseName"
          }
        ]
      }
    },
    { "label": "退货时间", field: 'productRefundTime', type: 'date',
      "props":{
          "placeholder":"请选择",
      }
    },
    { "label": "经办人", field: 'transactionBy', type: 'input',
      "props":{
          "placeholder":"请输入",
      } 
    },
    // { "label": "操作时间", field: 'transactionTime', type: 'datetime', intlPrefix: 'refunds.' },
    { "label": "制单人", field: 'originatorName', type: 'input',
      "rules": [
        { 
          "type": "required", 
          "message": "请输入制单人" 
        }
      ],
      "props":{
          "placeholder":"请输入",
      } 
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
    { "label": "备注", field: 'productRefundNote', type: 'input',
      "props":{
          "placeholder":"请输入",
      } 
    },
  ],
  "updateFields": [
      {
        "label":"退货单编号",
        "field": 'productRefundCode', "type": 'plain',
        "rules": [ "required" ],
      },
      {
        "label":"出库单编号",
        "field": 'field1', "type": 'plain',
        "rules": [ "required" ]
      },
      
      {
        "label": "供应商",
        "field": "supplierId",
        "type": "modal-radio",
        "rules": [
          { "type": "required" }
        ],
        "options": {
          "title": "请选择",
          "label": "supplierName",
          "editLabel": "supplierName",
          "value": "id",
          "API": "/api/wms/suppliers?supplierStatus=Normal",
          "fields": [
            {
              "label": "供应商",
              "field": "supplierName"
            }
          ],
          "saveData": {
            "supplierId": "id",
            "supplierName": "supplierName",
          }
        }
      },

      {
        "label": "关联采购单",
        "field": "productProcurementId",
        "type": "modal-radio",
        "rules": [ "required" ],
        "options": {
          "title": "请选择",
          "label": "procurementCode",
          "editLabel": "procurementCode",
          "value": "id",
          "API": "/api/wms/procurements?procureStatus=TotalStorageIn&supplierId=(supplierId)",
          "fields": [
            {
              "label": "单号",
              "field": "procurementCode"
            }
          ]
        },
        "expect":{
          "field": "supplierId",
          "value": "IS_NOT_NULL"
        }
      },
      
      {
        "label": "退货所在仓库",
        "field": "productRefundWarehouseId",
        "type": "modal-radio",
        "rules": [
          { "type": "required" }
        ],
        "options": {
          "title": "请选择",
          "label": "warehouseName",
          "editLabel": "warehouseName",
          "value": "id",
          "API": "/api/wms/warehouses",
          "fields": [
            {
              "label": "仓库",
              "field": "warehouseName"
            }
          ]
        }
      },
      { "label": "退货时间", field: 'productRefundTime', type: 'date',
        "props":{
            "placeholder":"请选择",
        }
      },
      { "label": "经办人", field: 'transactionBy', type: 'input',
        "props":{
            "placeholder":"请输入",
        } 
      },
      // { "label": "操作时间", field: 'transactionTime', type: 'datetime', intlPrefix: 'refunds.' },
      { "label": "制单人", field: 'originatorName', type: 'input',
        "rules": [
          { 
            "type": "required", 
            "message": "请输入制单人" 
          }
        ],
        "props":{
            "placeholder":"请输入",
        } 
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
      { "label": "备注", field: 'productRefundNote', type: 'input',
        "props":{
            "placeholder":"请输入",
        } 
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
          "path":"/wms/refunds/refunds-add"
      }
    }
  ],
  "tableOperation": [
    
    {
      "title": "提交审核",
      "type": "path",
      "options": {
        "outside": true,
        "path": "/wms/refunds/refunds-submitAudit",
      },
      "expect":{
        "field": "productRefundStatus",
        "value": "Draft"
      }
    },
    {
      "title": "审核",
      "type": "path",
      "options": {
        "outside": true,
        "path": "/wms/refunds/refunds-audit",
      },
      "expect":{
        "field": "productRefundStatus",
        "value": "Wait_To_Audit"
      }
    },
    {
      "title": "编辑",
      "type": "path",
      "options": {
        "outside": true,
        "path": "/wms/refunds/refunds-edit"
      },
      "expect":{
        "field": "productRefundStatus",
        "value": "Draft"
      }
    },
    {
      "title": "详情",
      "type": "path",
      "options": {
        "path": "/wms/refunds/refunds-view"
      }
    },
    {
      "title": "删除",
      "type": "delete"
    }
  ],
  "searchFields": [
    {
        "label": "单号",
        "field": "search",
        "type": "search",
        "props": {
          "placeholder": "退货单编号/供应商/采购单编号"
        }
    },
    {
      "label": "状态",
      field: 'status',
      placeholder: '状态',
      type: 'select',
      options: [
        { key: '草稿', value: 'Draft' },
        { key: '待审核', value: 'Wait_To_Audit' },
        { key: '审核通过', value: 'Audit_Passed' },
        { key: '完成', value: 'Done' },
        { key: '关闭', value: 'Closed' },
      ],
    },
    {
      "label": "时间",
      field: 'productRefundTime',
      placeholder: ['开始时间', '结束时间'],
      type: 'range',
      format: 'YYYY-MM-DD'
    }

  ],
  "tableFields": [
    {
      title: '退货单编号', field: 'productRefundCode', valueType: 'path',
      options: {
        path: '/wms/refunds/refunds-view'
      }
    },
    { title: '退货数量', field: 'productRefundQuantities' },
    { title: '退货时间', field: 'productRefundTime' },
    { "title": "退货状态", "field": "productRefundStatus", "valueType": "tag",
        "theme":"status",
        "options": {
          "map": {
            'Draft': '草稿',
            'Wait_To_Audit': '待审核',
            'Audit_Passed': '待审核',
            'WaitForCheck': '审核通过',
            'Done': '完成',
            'Closed': '关闭',
          }
        }
    },
    { title: '备注', field: 'productRefundNote' },
    { title: '经办人', field: 'transactionBy' },
    { title: '交易时间', field: 'transactionTime' }
  ],
  "viewConfig": [
    { "label": "退货编号", "field": "productRefundCode", "type": "plain" },
    { "label": "仓库", "field": "warehouseName", "type": "plain" },
    { "label": "经办人", "field": "transactionBy", "type": "plain" },
    { "label": "退货日期", "field": "transactionTime", "type": "plain"},
    { "label": "制单人", "field": "originatorName", "type": "plain" },
    { "label": "关联的采购单", "field": "procurementCode", "type": "plain" },
    { "label": "产生的出库单", "field": "field1", "type": "plain" },
    { "label": "供应商", "field": "supplierName", "type": "plain" },
    { "label": "备注", "field": "productRefundNote", "type": "plain"},

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
            { "title": "退货数量", "field": "transactionQuantities" },
            { "title": "采购单价", "field": "transactionSkuPrice", "valueType": "currency" },
            // { "title": "采购总价", "field": "transactionTotalSkuPrice", "valueType": "currency" },
            { "title": "单位", "field": "skuUnit" },
        ]
      }
    }
  ]
}
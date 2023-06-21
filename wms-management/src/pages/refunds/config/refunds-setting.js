module.exports =  {
  "columns": 1,
  "listAPI": "/api/wms/refunds",
  "createAPI": "/api",
  "getAPI": "/api/wms/refunds/[id]",
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
      label:"退货单编号",
      field: 'productRefundCode', type: 'input',
      props: { prefix: 'REF' },
      rules: [
        { type: "required" }
      ]
    },
    {
      label:"出库单编号",
      field: 'field1', type: 'input',
      props: { prefix: 'OUT' },
      rules: [
        { type: "required" }
      ]
    },
    {
      "label": "关联采购单",
      "field": "productProcurementId",
      "type": "modal-radio",
      rules: [
        { type: "required" }
      ],
      "options": {
        "title": "请选择",
        "label": "procurementCode",
        "editLabel": "procurementCode",
        "value": "id",
        "API": "/api/wms/procurements?procureStatus=TotalStorageIn",
        "fields": [
          {
            "label": "单号",
            "field": "procurementCode"
          }
        ],
      }
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
          supplierName: 'supplierName',
        }
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
      rules: [
        { type: "required" }
      ],
      "props":{
          "placeholder":"请输入",
      }
    },
    { "label": "经办人", field: 'transactionBy', type: 'input',
    rules: [
      { type: "required" }
    ],
      "props":{
          "placeholder":"请输入",
      } 
    },
    // { "label": "操作时间", field: 'transactionTime', type: 'datetime', intlPrefix: 'refunds.' },
    { "label": "制单人", field: 'originatorName', type: 'input',
    rules: [
      { type: "required" }
    ],
      "props":{
          "placeholder":"请输入",
      } 
    },
    { "label": "备注", field: 'productRefundNote', type: 'input',
      "props":{
          "placeholder":"请输入",
      } 
    },
    // {
    //   field: 'items', type: 'children', intlPrefix: 'procurements.',
    //   API: '/api/wms/skus',
    //   mapItemFieldTo: 'items',
    //   fieldMap: [
    //     { from: 'id', to: 'skuId' },
    //     { from: 'skuPrice', to: 'transactionSkuPrice' },
    //     { from: '', to: 'transactionQuantities', value: 1 },
    //     { from: 'createTime', to: 'transactionTime' },
    //     { from: 'barCode', to: 'skuBarcode' },
    //     { from: 'field1', to: 'skuUnit' },
    //   ],
    //   columns: [
    //     { title: '商品条码', field: 'barCode' },
    //     { title: '退货时间', field: 'createTime', value: new Date(), options: [{ key: 'format', value: 'YYYY-MM-DD HH:mm:ss' }] },
    //     { title: '商品编号', field: 'skuCode' },
    //     { title: '商品名称', field: 'skuName' },
    //     { title: '退货价格', field: 'skuPrice', align: 'right', valueType: 'currency' },
    //     { title: '单位', field: 'field1' },
    //   ],
    //   operation: {
    //     fields: [
    //       {
    //         field: 'search',
    //         placeholder: '商品名称/编号/条码',
    //         type: 'input',
    //       },
    //     ],
    //   }
    // },
  ],
  "updateFields": [

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
          "path":"/refunds/refunds-add"
      }
    }
  ],
  "tableOperation": [
    {
      "title": "编辑",
      "type": "path",
      "options": {
        "outside": true,
        "path": "/refunds/refunds-edit"
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
        path: '/refunds',
        queryData: {
          type: 'query',
          id: '{id}',
        }
      }
    },
    { title: '退货数量', field: 'productRefundQuantities' },
    { title: '退货时间', field: 'productRefundTime' },
    { "title": "退货状态", "field": "productRefundStatus", "valueType": "tag",
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
    { title: '备注', field: 'productRefundNote' },
    { title: '经办人', field: 'transactionBy' },
    { title: '交易时间', field: 'transactionTime' }
  ],
  "viewConfig": [
    
  ]
}
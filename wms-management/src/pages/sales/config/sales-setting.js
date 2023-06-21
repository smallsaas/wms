module.exports =  {
  "columns": 1,
  "listAPI": "/api/warehouse/sales",
  "createAPI": "/api/warehouse/sales",
  "getAPI": "/api/warehouse/sales/[id]",
  "updateAPI": "/api/warehouse/sales/[id]",
  "deleteAPI": "/api/warehouse/sales/(id)",
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
      "label": "分销商",
      "field": "traderId",
      "span": 24,
      "type": "modal-radio",
      "props": {},
      "rules": [ "required" ],
      "saveData":{
        "traderContactPhone": 'traderContactPhone',
        "traderName": 'traderName',
        "deliveredAddress": 'deliveredAddress',
      },
      "options": {
        "title": "选择分销商",
        "label": "traderName",
        "editLabel": "traderName",
        "value": "id",
        "pagination": true,
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
      "rules": [],
      "props":{
          "placeholder":"请输入",
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
          "path":"/sales/sales-add"
      }
    }
  ],
  "tableOperation": [
    {
      "title": "编辑",
      "type": "path",
      "options": {
        "outside": true,
        "path": "/sales/sales-edit"
      }
    },
    {
      "title": "删除",
      "type": "delete"
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
      title: '分销订单编号', field: 'salesCode', valueType: 'path',
      options: {
        path: '/sales/sales-view',
        queryData: {
          type: 'query',
          id: '{id}',
        }
      }
    },
    { title: '分销商', field: 'traderName' },
    { title: '订单总费用', field: 'salesTotal', valueType: 'currency' },
    { title: '订单总数量', field: 'totalCount' },
    { title: '创建时间', field: 'transactionTime' },
    { "title": "退货状态", "field": "productRefundStatus", "valueType": "tag",
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
    
  ]
}
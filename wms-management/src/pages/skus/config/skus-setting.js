const formFields = require("../formSettings/skus-form-settings");

module.exports =  {
  "columns": 1,
  "listAPI": "/api/wms/skus",
  "createAPI": "/api/wms/skus",
  "getAPI": "/api/wms/skus/[id]",
  "updateAPI": "/api/wms/skus/[id]",
  "deleteAPI": "/api/wms/skus/(id)",
  "searchType": "MoreSearch",
  "searchButtonType": "text",
  "pageName": {
    "table": "商品管理",
    "new": "新增",
    "edit": "编辑",
    "name": "商品管理",
    "view": "详情"
  },
  "createFields": formFields,
  "updateFields": formFields,
  "layout": {
    "table": "Content",
    "form": "TitleContent"
  },
  "tableActions": [
    {
      "title":"添加","type":"path",
      "options":{
          "style":"primary",
          "path":"/skus/skus-add"
      }
    }
  ],
  "tableOperation": [
    {
      "title": "详情",
      "type": "path",
      "options": {
        "outside": true,
        "path": "/skus/skus-view"
      }
    },
    {
      "title": "编辑",
      "type": "path",
      "options": {
        "outside": true,
        "path": "/skus/skus-edit"
      }
    },
    {
      "title": "删除",
      "type": "delete"
    }
  ],
  "searchFields": [
    {
        "label": "商品名称",
        "field": "skuName",
        "type": "input",
        "props": {
          "placeholder": "请输入"
        }
    },
    {
        "label": "条形码",
        "field": "barCode",
        "type": "input",
        "props": {
          "placeholder": "请输入"
        }
    },

  ],
  "tableFields": [
    {
      title: '商品编号', field: 'skuCode', valueType: 'path',
      options: {
        path: '/skus/skus-view',
        queryData: {
          type: 'query',
          id: '{id}',
        }
      }
    },
    { title: '商品名称', field: 'skuName' },
    // { title: '价格', field: 'skuPrice', valueType: 'currency' },
    { title: '条形码', field: 'barCode' },
    { title: '单位', field: 'field1' },
    // { title: '参考成本价', field: 'costPrice' },
    { "title": "创建时间", "field": "createTime" }
  ],
  "viewConfig": [
    
  ]
}
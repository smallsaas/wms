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
    "table": "商品库存管理",
    "new": "新增",
    "edit": "编辑",
    "name": "商品库存管理",
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
          "path":"/wms/skus/skus-add"
      }
    }
  ],
  "tableOperation": [
    {
      "title": "编辑",
      "type": "path",
      "options": {
        "outside": true,
        "path": "/wms/skus/skus-edit"
      }
    },
    {
      "title": "详情",
      "type": "path",
      "options": {
        "outside": true,
        "path": "/wms/skus/skus-view"
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
      title: "商品编号", field: "skuCode", valueType: "path",
      options: {
        path: "/wms/skus/skus-view"
      }
    },
    { title: "商品名称", field: "skuName" },
    { title: "商品分类", field: "categoryName" },
    { title: "条形码", field: "barCode" },
    { title: "单位", field: "field1" },
    { title: "价格", field: "skuPrice", valueType: "currency" },
    { title: "参考成本价", field: "costPrice", valueType: "currency" },
    {  title: "创建时间", field: "createTime" }
  ],
  "viewConfig": [
    { "label": "商品编号", "field": "productCode", "type": "plain" },
    { "label": "商品名称", "field": "name", "type": "plain" },
    { "label": "商品分类", "field": "categoryName", "type": "plain" },
    { "label": "条形码", "field": "barCode", "type": "plain" },
    { "label": "单位", "field": "field1", "type": "plain" },
    { "label": "参考成本价格", "field": "costPrice", "type": "plain" },
    { "label": "商品规格", "field": "spec", "type": "plain" },
    { "label": "商品体积", "field": "volume", "type": "plain" },
    { "label": "商品重量", "field": "weight", "type": "plain" },
  ]
}
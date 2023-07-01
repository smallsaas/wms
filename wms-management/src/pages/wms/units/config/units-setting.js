module.exports =  {
  "columns": 1,
  "listAPI": "/api/wms/sku/units",
  "createAPI": "/api/wms/sku/units",
  "getAPI": "/api/wms/sku/units/[id]",
  "updateAPI": "/api/wms/sku/units/[id]",
  "deleteAPI": "/api/wms/sku/units/(id)",
  "searchType": "MoreSearch",
  "searchButtonType": "text",
  "pageName": {
    "table": "商品单位管理",
    "new": "新增",
    "edit": "编辑",
    "name": "商品单位管理",
    "view": "详情"
  },
  "createFields": [],
  "updateFields": [],
  "layout": {
    "table": "Content",
    "form": "TitleContent"
  },
  "tableActions": [
    {
      "title": "添加",
      "type": "modal",
      "options": {
        "style": "primary",
        "modalTitle": "添加",
        "modalWidth": 600,
        "items": [
          {
            "component": 'Form',
            "config": {
              "layout": "Grid",
              "API": {
                "createAPI": "/api/wms/sku/units"
              },
              "fields": [
                {
                  "label": "商品",
                  "field": "skuIdString",
                  "type": "modal-radio",
                  "rules": [ "required" ],
                  "options": {
                    "title": "选择商品",
                    "label": "skuName",
                    "value": "productId",
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
                  field: 'unitName', label: '单位名称', 
                  type: 'input', rules: ['required'],
                  props: {
                    placeholder: "请输入"
                  }
                },
              ]
            }
          }
        ]
      },
      "expect": {
      }
    }
  ],
  "tableOperation": [
    {
      "title": "编辑",
      "type": "modal",
      "options": {
        "style": "primary",
        "modalTitle": "添加",
        "modalWidth": 600,
        "outside": true,
        "items": [
          {
            "component": 'Form',
            "config": {
              "layout": "Grid",
              "API": {
                "getAPI": '/api/wms/sku/units/(id)',
                "updateAPI": "/api/wms/sku/units/(id)"
              },
              "fields": [
                {
                  "label": "商品",
                  "field": "skuIdString",
                  "type": "modal-radio",
                  "rules": [ "required" ],
                  "options": {
                    "title": "选择商品",
                    "label": "skuName",
                    "value": "productId",
                    "editLabel": "skuName",
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
                      // {
                      //   "label": "单位",
                      //   "field": "field1"
                      // }
                    ]
                  },
                  "expect": {}
                },
                {
                  field: 'unitName', label: '单位名称', 
                  type: 'input', rules: ['required'],
                  props: {
                    placeholder: "请输入"
                  }
                },
              ]
            }
          }
        ]
      },
      "expect": {
      }
    },
    {
      "title": "删除",
      "type": "delete",
      "options":{
        "outside": true,
      }
    }
  ],
  "searchFields": [
    {
        "label": "单位名称",
        "field": "unitName",
        "type": "input",
        "props": {
          "placeholder": "请输入"
        }
    },
  ],
  "tableFields": [
    { title: '单位名称', field: 'unitName' },
    { title: '商品', field: 'skuName'}
  ],
  "viewConfig": [
  ]
}
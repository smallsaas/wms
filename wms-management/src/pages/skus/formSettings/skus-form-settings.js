module.exports = [
    
    {
        "label": "商品编号",
        "field": "productCode",
        "type": "serial-code",
        "rules": [ "required" ],
        "props":{
            "placeholder":"请输入",
            "prefixValue": "P"
        },
      },
      {
        "label": "商品分类",
        "field": "productCategoryId",
        "span": 24,
        "type": "modal-radio",
        "props": {},
        "rules": [ "required" ],
        "options": {
          "title": "选择商品分类",
          "label": "categoryName",
          "editLabel": "categoryName",
          "value": "id",
          "pagination": true,
          "API": "/api/product/categories",
          "fields": [
            {
              "label": "名称",
              "field": "categoryName"
            }
          ]
        },
      },
      {
        "label": "条形码",
        "field": "barCode",
        "type": "input",
        "rules": [ "required" ],
        "props":{
            "placeholder":"请输入",
        },
      },
      {
        "label": "商品名称",
        "field": "name",
        "type": "input",
        "rules": [ "required" ],
        "props":{
            "placeholder":"请输入",
        },
      },
      {
        "label":"单位",
        "field":"field1",
        "type":"select-fetch",
        "rules": [ "required" ],
        "props":{
          "placeholder":"请选择",
          "style":{
            "width":"240px"
          }
        },
        "options":{
          "API":"/api/wms/sku/units",
          "label":"unitName",
          "value":"id"
        }
      },
      {
        "label": "参考成本价格",
        "field": "costPrice",
        "type": "number",
        "rules": [ "required" ],
        "props":{
            step: 0.01,
            min: 0,
            "placeholder":"请输入",
            "style":{
              "width":"240px"
            }
        },
      },
  
      {
        "label": "商品规格",
        "field": "spec",
        "type": "input",
        "rules": [],
        "props":{
            "placeholder":"请输入",
        },
      },
      {
        "label": "商品体积",
        "field": "volume",
        "type": "input",
        "rules": [],
        "props":{
            "placeholder":"请输入",
        },
      },
      {
        "label": "商品重量",
        "field": "weight",
        "type": "input",
        "rules": [],
        "props":{
            "placeholder":"请输入",
        },
      },
]
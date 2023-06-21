module.exports = [
    {
        "label": "盘点编号",
        "field": "checkCode",
        "type": "serial-code",
        "rules": [ "required" ],
        "props":{
            "placeholder":"请输入",
            "prefixValue": "CHK"
        },
      },
      // {
      //   "label": "利润损失",
      //   "field": "profitLost",
      //   "type": "input",
      //   "rules": [],
      //   "props":{
      //       "placeholder":"请输入",
      //   },
      // },
      {
        "label":"盘点仓库",
        "field":"warehouseId",
        "type":"select-fetch",
        "rules": [ "required" ],
        "props":{
          "placeholder": "请选择",
          "style":{
            "width":"240px"
          }
        },
        "options":{
          "API":"/api/wms/warehouses",
          "label":"warehouseName",
          "value":"id"
        }
      },
      {
        "label": "盘点人",
        "field": "transactionBy",
        "type": "input",
        "rules": [],
        "props":{
            "placeholder":"请输入",
        },
      },
      {
        "label": "盘点时间",
        "field": "checkTime",
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
        "label": "制单人ID",
        "field": "originatorId",
        "type": "number",
        "rules": [ "required" ],
        "props":{
            "placeholder":"请输入",
            "style":{
              "width": "240px"
            }
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
        "label": "库存商品",
        "field": "checkSkus",
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
        "field": "checkNote",
        "type": "input",
        "rules": [],
        "props":{
            "placeholder":"请输入",
        },
      },
]
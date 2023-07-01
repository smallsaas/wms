module.exports =  {
  "columns": 1,
  "listAPI": "/api/wms/inventories",
  "createAPI": "/api/wms/inventories",
  "getSkuAPI": "/api/wms/products/[id]",
  "getAPI": "/api/wms/inventories/skus/[id]?warehouseName=[warehouseName]",
  "updateAPI": "/api/wms/inventories/[id]",
  "deleteAPI": "/api/wms/inventories/(id)",
  "searchType": "MoreSearch",
  "searchButtonType": "text",
  "pageName": {
    "table": "库存管理",
    "new": "新增",
    "edit": "编辑",
    "name": "库存管理",
    "view": "出入库详情"
  },
  "createFields": [
      {
        "label": "仓库",
        "field": "warehouseId",
        "span": 24,
        "type": "modal-radio",
        "props": {},
        "rules": [ "required" ],
        "options": {
          "title": "选择仓库",
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
        "label": "商品",
        "field": "skuId",
        "span": 24,
        "type": "modal-radio",
        "props": {},
        "rules": [ "required" ],
        "options": {
          "title": "选择商品",
          "label": "skuName",
          "editLabel": "skuName",
          "value": "id",
          "pagination": true,
          "API": "/api/wms/skus",
          "fields": [
            {
              "label": "名称",
              "field": "skuName"
            }
          ]
        }
      },
      {
        "label": "最大库存",
        "type": "number",
        "field": "maxInventory",
        "props":{
            "placeholder":"请输入",
            "style": {
              "width": "240px"
            },
            "min":0
        }
      },
      {
        "label": "最小库存",
        "type": "number",
        "field": "minInventory",
        "props":{
            "placeholder":"请输入",
            "style": {
              "width": "240px"
            },
            "min":0
        }
      },
      {
        "label": "有效SKU",
        "type": "number",
        "field": "validSku",
        "props":{
            "placeholder":"请输入",
            "style": {
              "width": "240px"
            },
            "min":0
        }
      },
      {
        "label": "预购数量",
        "type": "number",
        "field": "advanceQuantities",
        "props":{
            "placeholder":"请输入",
            "style": {
              "width": "240px"
            },
            "min":0
        }
      },
      {
        "label": "传输数量",
        "type": "number",
        "field": "transmitQuantities",
        "props":{
            "placeholder":"请输入",
            "style": {
              "width": "240px"
            },
            "min":0
        }
      },
      // {
      //   "label": "盘点时间",
      //   "type": "date",
      //   "field": "createTime",
      //   "props":{
      //       "placeholder":"请输入",
      //       "style": {
      //         "width": "240px"
      //       }
      //   }
      // },
      
  ],
  "updateFields": [
    
      {
        "label": "仓库",
        "field": "warehouseId",
        "span": 24,
        "type": "modal-radio",
        "props": {},
        "rules": [ "required" ],
        "options": {
          "title": "选择仓库",
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
        "label": "商品",
        "field": "skuId",
        "span": 24,
        "type": "modal-radio",
        "props": {},
        "rules": [ "required" ],
        "options": {
          "title": "选择商品",
          "label": "skuName",
          "editLabel": "skuName",
          "value": "id",
          "pagination": true,
          "API": "/api/wms/skus",
          "fields": [
            {
              "label": "名称",
              "field": "skuName"
            }
          ]
        }
      },
      {
        "label": "最大库存",
        "type": "number",
        "field": "maxInventory",
        "props":{
            "placeholder":"请输入",
            "style": {
              "width": "240px"
            },
            "min":0
        }
      },
      {
        "label": "最小库存",
        "type": "number",
        "field": "minInventory",
        "props":{
            "placeholder":"请输入",
            "style": {
              "width": "240px"
            },
            "min":0
        }
      },
      {
        "label": "有效SKU",
        "type": "number",
        "field": "validSku",
        "props":{
            "placeholder":"请输入",
            "style": {
              "width": "240px"
            },
            "min":0
        }
      },
      {
        "label": "预购数量",
        "type": "number",
        "field": "advanceQuantities",
        "props":{
            "placeholder":"请输入",
            "style": {
              "width": "240px"
            },
            "min":0
        }
      },
      {
        "label": "传输数量",
        "type": "number",
        "field": "transmitQuantities",
        "props":{
            "placeholder":"请输入",
            "style": {
              "width": "240px"
            },
            "min":0
        }
      },
      // {
      //   "label": "盘点时间",
      //   "type": "date",
      //   "field": "createTime",
      //   "props":{
      //       "placeholder":"请输入",
      //       "style": {
      //         "width": "240px"
      //       }
      //   }
      // },
  ],
  "layout": {
    "table": "Content",
    "form": "TitleContent"
  },
  "tableActions": [
    {
      "title": "库存盘点",
      "type": "path",
      "options": {
        "style":"primary",
        "path": "/wms/inventories/inventories-add"
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
        "path": "/wms/inventories/inventories-view",
        "query":{
          "id": "skuId",
          "warehouseName": "warehouseName"
        }
      }
    },
    // {
    //   "title": "编辑",
    //   "type": "path",
    //   "options": {
    //     "outside": true,
    //     "path": "/inventories/inventories-edit"
    //   }
    // },
    {
      "title": "删除",
      "type": "delete"
    }
  ],
  "searchFields": [
    {
        "label": "选择仓库",
        "type": "select-fetch",
        "props": {
          "placeholder": "请选择"
        },
        "rules": [],
        "options": {
          "API": "/api/wms/warehouses",
          "label": "warehouseName",
          "value": "id"
        },
        "field": "warehouseId"
    },
    {
      "field": "search",
      "label": "",
      "type": "input",
      "props": {
        "placeholder": "商品名称/编号/条码"
      }
    }

  ],
  "tableFields": [
    { title: '仓库', field: 'warehouseName' },
      // { title: '储位', field: 'slotName'},
      { title: '商品名称', field: 'skuName' },
      { title: '商品条码', field: 'skuBarcode' },
      {
        title: '商品编号', field: 'skuCode', valueType: 'path',
        options: {
          path: '/skus/skus-view',
          query: {
            id: 'skuId',
          }
        }
      },
      // { title: '最大库存量', field: 'maxInventory' },
      // { title: '最小库存量', field: 'minInventory' },
      {
        title: '本库存总量', field: 'totalSku', align: 'right',
        options: { 
          //fields: ['validSku', 'transmitQuantities', 'orderCount'] 
        }
      },
      { title: '可用库存量', field: 'validSku', align: 'right', },
      {
        title: '占用库存量', field: 'orderCount', align: 'right',
        render: (text) => {
          return text === '' ? 0 : text;
        }
      },
      // { title: '预购数量', field: 'advanceQuantities' },
      { title: '在途数量', field: 'transmitQuantities', align: 'right', },
      // { title: '制单人', field: 'transactionBy' }
  ],
  "viewConfig": [
    {
      "field": "records",
      "label": "",
      "type": "one-mary",
      "options": {
        "fields": [
          { "title": "仓库名", "field": "warehouseName" },
          { "title": "类型", "field": "transactionType", "valueType": "tag",
              "theme":"status",
              "options": {
                "map": {
                  'Procurement': '采购',
                  'Refund': '退货',
                  'SalesIn': '销售入库',
                  'SalesOut': '销售出库',
                  'TransferIn': '调拨出库',
                  'TransferOut': '调拨入库',
                  'StorageIn': '入库',
                  'StorageOut': '出库',
                  'OthersStorageOut': '其他出库',
                  'OthersStorageIn': '其他入库',
                  'CustomerStorageOut': '分销商出库',
                  'checkUpdate': '盘点更新'
                }
              }
          }
        ]
      }
    }
  ]
}
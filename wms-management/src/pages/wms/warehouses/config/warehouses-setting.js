
module.exports =  {
  "columns": 1,
  "listAPI": "/api/wms/warehouses",
  "createAPI": "/api/wms/warehouses",
  "getAPI": "/api/wms/warehouses/[id]",
  "updateAPI": "/api/wms/warehouses/[id]",
  "deleteAPI": "/api/wms/warehouses/(id)",
  "searchType": "MoreSearch",
  "searchButtonType": "text",
  "pageName": {
    "table": "仓库管理",
    "new": "新增",
    "edit": "编辑",
    "name": "仓库管理",
    "view": "详情"
  },
  "createFields": [
    {
      "label": "仓库编号",
      "field": "warehouseCode",
      "type": "serial-code",
      "rules": [ "required" ],
      "props":{
          "prefixValue": "WH"
      },
    },
    {
      "label": "仓库名称",
      "field": "warehouseName",
      "type": "input",
      "rules": [ "required" ],
      "props":{
          "placeholder":"请输入",
      },
    },
    // {
    //   "label": "所在省市",
    //   "field": "warehousePCD",
    //   "type": "pcd",
    //   "rules": [ "required" ],
    //   "props":{
    //       "placeholder":"请选择",
    //       "style":{
    //         "minWidth": '300px'
    //       }
    //   },
    // },
    {
      "label": "详细地址",
      "field": "warehouseAddress",
      "type": "input",
      "rules": [ "required" ],
      "props":{
          "placeholder":"请输入",
      },
    }
  ],
  "updateFields": [
    {
      "label": "仓库编号",
      "field": "warehouseCode",
      "type": "plain",
    },
    {
      "label": "仓库名称",
      "field": "warehouseName",
      "type": "input",
      "rules": [ "required" ],
      "props":{
          "placeholder":"请输入",
      },
    },
    // {
    //   "label": "所在省市",
    //   "field": "warehousePCD",
    //   "type": "pcd",
    //   "rules": [ "required" ],
    //   "props":{
    //       "placeholder":"请选择",
    //       "style":{
    //         "minWidth": '300px'
    //       }
    //   },
    // },
    {
      "label": "详细地址",
      "field": "warehouseAddress",
      "type": "input",
      "rules": [ "required" ],
      "props":{
          "placeholder":"请输入",
      },
    }
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
          "path":"/wms/warehouses/warehouses-add"
      }
    }
  ],
  "tableOperation": [
    {
      "title": "编辑",
      "type": "path",
      "options": {
        "outside": true,
        "path": "/wms/warehouses/warehouses-edit"
      }
    },
    {
      "title": "详情",
      "type": "path",
      "options": {
        "outside": true,
        "path": "/wms/warehouses/warehouses-view"
      }
    },
    {
      "title": "删除",
      "type": "delete"
    }
  ],
  "searchFields": [
    {
        "label": "分销商名",
        "field": "traderName",
        "type": "input",
        "props": {
          "placeholder": "请输入"
        }
    },
    {
        "label": "分销商编号",
        "field": "traderCode",
        "type": "input",
        "props": {
          "placeholder": "请输入"
        }
    },

  ],
  "tableFields": [
    { label: "仓库编号", field: "warehouseCode", valueType: 'path', options: {
        path: '/wms/warehouses/warehouses-view',
      } 
    },
    { label: "仓库名称", field: "warehouseName"  },
    { label: "所在省市", field: "warehousePCD", "valueType": "pcd-format" },
    { label: "详细地址", field: "warehouseAddress" },
  ],
  "viewConfig": [
    { "label": "仓库编号", "field": "warehouseCode", "type": "plain" },
    { "label": "仓库名称", "field": "warehouseName", "type": "plain" },
    { "label": "所在省市", "field": "warehousePCD", "type": "plain" },
    { "label": "详细地址", "field": "warehouseAddress", "type": "plain" },
  ]
}
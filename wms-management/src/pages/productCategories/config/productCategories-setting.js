module.exports =  {
  "columns": 1,
  "listAPI": "/api/product/categories",
  "createAPI": "/api",
  "getAPI": "/api/product/categories/[id]",
  "updateAPI": "/api/product/categories/[id]",
  "deleteAPI": "/api/product/categories/(id)",
  "searchType": "MoreSearch",
  "searchButtonType": "text",
  "pageName": {
    "table": "商品分类管理",
    "new": "新增",
    "edit": "编辑",
    "name": "商品分类管理",
    "view": "详情"
  },
  "createFields": [
    {
      "label": "类别名称",
      "type": "input",
      "field": "categoryName",
      "rules": [
        { "type": "required" }
      ],
      "props":{
          "placeholder":"请输入",
      },
    }
    
  ],
  "updateFields": [
      {
        "label": "类别编号",
        "type": "input",
        "field": "categoryCode",
        "rules": [
          { "type": "required" }
        ],
        "props":{
            "placeholder":"请输入",
        },
      },
      {
        "label": "类别名称",
        "type": "input",
        "field": "categoryName",
        "rules": [
          { "type": "required" }
        ],
        "props":{
            "placeholder":"请输入",
        },
      },
      {
        "label": "类别描述",
        "type": "input",
        "field": "categoryDescription",
        "rules": [
        ],
        "props":{
            "placeholder":"请输入模板名称",
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
          "path":"/productCategories/productCategories-add"
      }
    }
  ],
  "tableOperation": [
    {
      "title": "编辑",
      "type": "path",
      "options": {
        "outside": true,
        "path": "/productCategories/productCategories-edit"
      }
    },
    {
      "title": "删除",
      "type": "delete"
    }
  ],
  "searchFields": [
  ],
  "tableFields": [
    { title: '类别编号', field: 'categoryCode' },
    { title: '类别名称', field: 'categoryName' },
    { title: '类别描述', field: 'categoryDescription' },
  ],
  "viewConfig": [
    
  ]
}
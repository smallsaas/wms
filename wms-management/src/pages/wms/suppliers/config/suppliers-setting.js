
module.exports =  {
  "columns": 1,
  "listAPI": "/api/wms/suppliers",
  "createAPI": "/api/wms/suppliers",
  "getAPI": "/api/wms/suppliers/[id]",
  "updateAPI": "/api/wms/suppliers/[id]",
  "deleteAPI": "/api/wms/suppliers/(id)",
  "searchType": "MoreSearch",
  "searchButtonType": "text",
  "pageName": {
    "table": "供应商管理",
    "new": "新增",
    "edit": "编辑",
    "name": "供应商管理",
    "view": "详情"
  },
  "createFields": [
    {
      "label": "供应商编号",
      "field": "supplierCode",
      "type": "serial-code",
      "rules": [ "required" ],
      "props":{
          "prefixValue": "SUP"
      },
    },
    {
      "label": "供应商名称",
      "field": "supplierName",
      "type": "input",
      "rules": [ "required" ],
      "props":{
          "placeholder":"请输入",
      },
    },
    // {
    //   "label": "所在省市",
    //   "field": "supplierPCD",
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
      "field": "supplierAddress",
      "type": "input",
      "rules": [ "required" ],
      "props":{
          "placeholder":"请输入",
      },
    },
    {
      "label": "邮政编码",
      "field": "supplierPostcode",
      "type": "input",
      "rules": [],
      "props":{
          "placeholder":"请输入",
      },
    },
    
    {
      "label": "状态",
      "type": "radio",
      "field": "supplierStatus",
      "options": [
          {
              "label": "启用",
              "value": "Normal"
          },
          {
              "label": "禁用",
              "value": "Forbidden"
          }
      ],
      "props":{
          "placeholder":"请选择",
      },
    },
    
    { field: 'group_1', type: 'group', value: '个人信息', span: 24 },
    
    {
      "label": "联系人",
      "field": "supplierContactName",
      "type": "input",
      "rules": [ "required" ],
      "props":{
          "placeholder":"请输入",
      },
    },
    {
      "label": "联系人电话",
      "field": "supplierContactPhone",
      "type": "input",
      rules :[
        {
          type: "phone",
          message: '请输入正确的手机号'
        }
      ],
      "props":{
          "placeholder":"请输入",
      },
    },
    {
      "label": "联系人邮箱",
      "field": "supplierContactEmail",
      "type": "input",
      rules :[
        {
          type: "email",
          message: '请输入正确的邮箱'
        }
      ],
      "props":{
          "placeholder":"请输入",
      },
    },
    {
      "label": "联系人职位",
      "field": "supplierContactPosition",
      "type": "input",
      "props":{
          "placeholder":"请输入",
      },
    },
    {
      "label": "联系人手机",
      "field": "supplierContactCellPhone",
      "type": "input",
      rules :[
        {
          type: "phone",
          message: '请输入正确的手机号'
        }
      ],
      "props":{
          "placeholder":"请输入",
      },
    },

    { field: 'group_2', type: 'group', value: '财务信息', span: 24 },
    
    {
      "label": "供应商开户名称",
      "field": "supplierAccountName",
      "type": "input",
      "props":{
          "placeholder":"请输入",
      },
    },
    {
      "label": "供应商开户银行",
      "field": "supplierAccountBank",
      "type": "input",
      "props":{
          "placeholder":"请输入",
      },
    },
    {
      "label": "供应商银行账号",
      "field": "supplierAccountBankNo",
      "type": "input",
      "props":{
          "placeholder":"请输入",
      },
    },
    {
      "label": "供应商发票抬头",
      "field": "supplierInvoiceTitle",
      "type": "input",
      "props":{
          "placeholder":"请输入",
      },
    },

    { field: 'group_3', type: 'group', value: '其它信息', span: 24 },
    {
      "label": "供应商注册时间",
      "field": "supplierRegisterTime",
      "type": "date",
      "props":{
          "placeholder":"请输入",
      },
    },
    {
      "label": "备注",
      "field": "supplierNote",
      "type": "input",
      "props":{
          "placeholder":"请输入",
      },
    },
  ],
  "updateFields": [
    {
      "label": "供应商编号",
      "field": "supplierCode",
      "type": "plain"
    },
    {
      "label": "供应商名称",
      "field": "supplierName",
      "type": "input",
      "rules": [ "required" ],
      "props":{
          "placeholder":"请输入",
      },
    },
    // {
    //   "label": "所在省市",
    //   "field": "supplierPCD",
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
      "field": "supplierAddress",
      "type": "input",
      "rules": [ "required" ],
      "props":{
          "placeholder":"请输入",
      },
    },
    {
      "label": "邮政编码",
      "field": "supplierPostcode",
      "type": "input",
      "rules": [],
      "props":{
          "placeholder":"请输入",
      },
    },
    
    {
      "label": "状态",
      "type": "radio",
      "field": "supplierStatus",
      "options": [
          {
              "label": "启用",
              "value": "Normal"
          },
          {
              "label": "禁用",
              "value": "Forbidden"
          }
      ],
      "props":{
          "placeholder":"请选择",
      },
    },
    
    { field: 'group_1', type: 'group', value: '个人信息', span: 24 },
    
    {
      "label": "联系人",
      "field": "supplierContactName",
      "type": "input",
      "rules": [ "required" ],
      "props":{
          "placeholder":"请输入",
      },
    },
    {
      "label": "联系人电话",
      "field": "supplierContactPhone",
      "type": "input",
      rules :[
        {
          type: "phone",
          message: '请输入正确的手机号'
        }
      ],
      "props":{
          "placeholder":"请输入",
      },
    },
    {
      "label": "联系人邮箱",
      "field": "supplierContactEmail",
      "type": "input",
      rules :[
        {
          type: "email",
          message: '请输入正确的邮箱'
        }
      ],
      "props":{
          "placeholder":"请输入",
      },
    },
    {
      "label": "联系人职位",
      "field": "supplierContactPosition",
      "type": "input",
      "props":{
          "placeholder":"请输入",
      },
    },
    {
      "label": "联系人手机",
      "field": "supplierContactCellPhone",
      "type": "input",
      rules :[
        {
          type: "phone",
          message: '请输入正确的手机号'
        }
      ],
      "props":{
          "placeholder":"请输入",
      },
    },

    { field: 'group_2', type: 'group', value: '财务信息', span: 24 },
    
    {
      "label": "供应商开户名称",
      "field": "supplierAccountName",
      "type": "input",
      "props":{
          "placeholder":"请输入",
      },
    },
    {
      "label": "供应商开户银行",
      "field": "supplierAccountBank",
      "type": "input",
      "props":{
          "placeholder":"请输入",
      },
    },
    {
      "label": "供应商银行账号",
      "field": "supplierAccountBankNo",
      "type": "input",
      "props":{
          "placeholder":"请输入",
      },
    },
    {
      "label": "供应商发票抬头",
      "field": "supplierInvoiceTitle",
      "type": "input",
      "props":{
          "placeholder":"请输入",
      },
    },

    { field: 'group_3', type: 'group', value: '其它信息', span: 24 },
    {
      "label": "供应商注册时间",
      "field": "supplierRegisterTime",
      "type": "date",
      "props":{
          "placeholder":"请输入",
      },
    },
    {
      "label": "备注",
      "field": "supplierNote",
      "type": "input",
      "props":{
          "placeholder":"请输入",
      },
    },
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
          "path":"/wms/suppliers/suppliers-add"
      }
    }
  ],
  "tableOperation": [
    {
      "title": "编辑",
      "type": "path",
      "options": {
        "outside": true,
        "path": "/wms/suppliers/suppliers-edit"
      }
    },
    {
      "title": "详情",
      "type": "path",
      "options": {
        "outside": true,
        "path": "/wms/suppliers/suppliers-view"
      }
    },
    {
      "title": "删除",
      "type": "delete"
    }
  ],
  "searchFields": [
    {
        "label": "供应商名",
        "field": "supplierName",
        "type": "input",
        "props": {
          "placeholder": "请输入"
        }
    },
    {
        "label": "供应商编号",
        "field": "supplierCode",
        "type": "input",
        "props": {
          "placeholder": "请输入"
        }
    },

  ],
  "tableFields": [
    { label: "供应商名", field: "supplierName"  },
    { label: "供应商编号", field: "supplierCode", valueType: 'path', options: {
        path: '/wms/suppliers/suppliers-view',
      } 
    },
    { label: "所在省市", field: "supplierPCD", "valueType": "pcd-format" },
    { label: "详细地址", field: "supplierAddress" },
    { label: "邮政编码", field: "supplierPostcode" },
    { label: "联系人", field: "supplierContactName" },
    { label: "联系人电话", field: "supplierContactPhone" },
    { "label": "状态", "field": "supplierStatus", "valueType": "tag",
        "theme":"status",
        "options": {
          "map": {
            'Normal': '启用',
            'Forbidden': '禁用',
          }
        }
    }
  ],
  "viewConfig": [
    { "label": "供应商编号", "field": "supplierCode", "type": "plain" },
    { "label": "供应商名称", "field": "supplierName", "type": "plain" },
    { "label": "所在省市", "field": "supplierPCD", "type": "plain" },
    { "label": "详细地址", "field": "supplierAddress", "type": "plain" },
    { "label": "邮政编码", "field": "supplierPostcode", "type": "plain" },
    {
      "label": "状态",
      "field": "supplierStatus",
      "type": "map",
      "options": {
        "map": {
          'Normal': '启用',
          'Forbidden': '禁用',
        }
      }
    },
    { field: 'group_1', type: 'group', value: '个人信息', span: 24 },
    { "label": "联系人", "field": "supplierContactName", "type": "plain" },
    { "label": "联系人邮箱", "field": "supplierContactEmail", "type": "plain" },
    { "label": "联系人职位", "field": "supplierContactPosition", "type": "plain" },
    { "label": "联系人手机", "field": "supplierContactCellPhone", "type": "plain" },
    { field: 'group_2', type: 'group', value: '财务信息', span: 24 },
    { "label": "供应商开户名称", "field": "supplierAccountName", "type": "plain" },
    { "label": "供应商开户银行", "field": "supplierAccountBank", "type": "plain" },
    { "label": "供应商银行账号", "field": "supplierAccountBankNo", "type": "plain" },
    { "label": "供应商发票抬头", "field": "supplierInvoiceTitle", "type": "plain" },
    { field: 'group_3', type: 'group', value: '其它信息', span: 24 },
    { "label": "供应商注册时间", "field": "supplierRegisterTime", "type": "plain" },
    { "label": "备注", "field": "supplierNote", "type": "plain" },
  ]
}
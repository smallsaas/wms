
module.exports =  {
  "columns": 1,
  "listAPI": "/api/warehouse/traders",
  "createAPI": "/api/warehouse/traders",
  "getAPI": "/api/warehouse/traders/[id]",
  "updateAPI": "/api/warehouse/traders/[id]",
  "deleteAPI": "/api/warehouse/traders/(id)",
  "searchType": "MoreSearch",
  "searchButtonType": "text",
  "pageName": {
    "table": "分销商管理",
    "new": "新增",
    "edit": "编辑",
    "name": "分销商管理",
    "view": "详情"
  },
  "createFields": [
    {
      "label": "分销商编号",
      "field": "traderCode",
      "type": "serial-code",
      "rules": [ "required" ],
      "props":{
          "prefixValue": "SEL"
      },
    },
    {
      "label": "分销商名称",
      "field": "traderName",
      "type": "input",
      "rules": [ "required" ],
      "props":{
          "placeholder":"请输入",
      },
    },
    // {
    //   "label": "所在省市",
    //   "field": "traderPCD",
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
      "field": "traderAddress",
      "type": "input",
      "rules": [ "required" ],
      "props":{
          "placeholder":"请输入",
      },
    },
    {
      "label": "邮政编码",
      "field": "traderPostcode",
      "type": "input",
      "rules": [],
      "props":{
          "placeholder":"请输入",
      },
    },
    
    {
      "label": "状态",
      "type": "radio",
      "field": "traderStatus",
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
      "field": "traderContactName",
      "type": "input",
      "rules": [ "required" ],
      "props":{
          "placeholder":"请输入",
      },
    },
    {
      "label": "联系人电话",
      "field": "traderContactPhone",
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
      "field": "traderContactEmail",
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
      "field": "traderContactPosition",
      "type": "input",
      "props":{
          "placeholder":"请输入",
      },
    },
    {
      "label": "联系人手机",
      "field": "traderContactCellPhone",
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
      "label": "分销商开户名称",
      "field": "traderAccountName",
      "type": "input",
      "props":{
          "placeholder":"请输入",
      },
    },
    {
      "label": "分销商开户银行",
      "field": "traderAccountBank",
      "type": "input",
      "props":{
          "placeholder":"请输入",
      },
    },
    {
      "label": "分销商银行账号",
      "field": "traderAccountBankNo",
      "type": "input",
      "props":{
          "placeholder":"请输入",
      },
    },
    {
      "label": "分销商发票抬头",
      "field": "traderInvoiceTitle",
      "type": "input",
      "props":{
          "placeholder":"请输入",
      },
    },

    { field: 'group_3', type: 'group', value: '其它信息', span: 24 },
    {
      "label": "分销商注册时间",
      "field": "traderRegisterTime",
      "type": "date",
      "props":{
          "placeholder":"请输入",
      },
    },
    {
      "label": "备注",
      "field": "traderNote",
      "type": "input",
      "props":{
          "placeholder":"请输入",
      },
    },
  ],
  "updateFields": [
    {
      "label": "分销商编号",
      "field": "traderCode",
      "type": "plain",
    },
    {
      "label": "分销商名称",
      "field": "traderName",
      "type": "input",
      "rules": [ "required" ],
      "props":{
          "placeholder":"请输入",
      },
    },
    // {
    //   "label": "所在省市",
    //   "field": "traderPCD",
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
      "field": "traderAddress",
      "type": "input",
      "rules": [ "required" ],
      "props":{
          "placeholder":"请输入",
      },
    },
    {
      "label": "邮政编码",
      "field": "traderPostcode",
      "type": "input",
      "rules": [],
      "props":{
          "placeholder":"请输入",
      },
    },
    
    {
      "label": "状态",
      "type": "radio",
      "field": "traderStatus",
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
      "field": "traderContactName",
      "type": "input",
      "rules": [ "required" ],
      "props":{
          "placeholder":"请输入",
      },
    },
    {
      "label": "联系人电话",
      "field": "traderContactPhone",
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
      "field": "traderContactEmail",
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
      "field": "traderContactPosition",
      "type": "input",
      "props":{
          "placeholder":"请输入",
      },
    },
    {
      "label": "联系人手机",
      "field": "traderContactCellPhone",
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
      "label": "分销商开户名称",
      "field": "traderAccountName",
      "type": "input",
      "props":{
          "placeholder":"请输入",
      },
    },
    {
      "label": "分销商开户银行",
      "field": "traderAccountBank",
      "type": "input",
      "props":{
          "placeholder":"请输入",
      },
    },
    {
      "label": "分销商银行账号",
      "field": "traderAccountBankNo",
      "type": "input",
      "props":{
          "placeholder":"请输入",
      },
    },
    {
      "label": "分销商发票抬头",
      "field": "traderInvoiceTitle",
      "type": "input",
      "props":{
          "placeholder":"请输入",
      },
    },

    { field: 'group_3', type: 'group', value: '其它信息', span: 24 },
    {
      "label": "分销商注册时间",
      "field": "traderRegisterTime",
      "type": "date",
      "props":{
          "placeholder":"请输入",
      },
    },
    {
      "label": "备注",
      "field": "traderNote",
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
          "path":"/wms/traders/traders-add"
      }
    }
  ],
  "tableOperation": [
    {
      "title": "编辑",
      "type": "path",
      "options": {
        "outside": true,
        "path": "/wms/traders/traders-edit"
      }
    },
    {
      "title": "详情",
      "type": "path",
      "options": {
        "outside": true,
        "path": "/wms/traders/traders-view"
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
    { label: "分销商名", field: "traderName"  },
    { label: "分销商编号", field: "traderCode", valueType: 'path', options: {
        path: '/wms/traders/traders-view',
      } 
    },
    { label: "所在省市", field: "traderPCD", "valueType": "pcd-format" },
    { label: "详细地址", field: "traderAddress" },
    { label: "邮政编码", field: "traderPostcode" },
    { label: "联系人", field: "traderContactName" },
    { label: "联系人电话", field: "traderContactPhone" },
    { "label": "状态", "field": "traderStatus", "valueType": "tag",
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
    { "label": "分销商编号", "field": "traderCode", "type": "plain" },
    { "label": "分销商名", "field": "traderName", "type": "plain" },
    { "label": "所在省市", "field": "traderPCD", "type": "plain" },
    { "label": "详细地址", "field": "traderAddress", "type": "plain" },
    { "label": "邮政编码", "field": "traderPostcode", "type": "plain" },
    {
      "label": "状态",
      "field": "traderStatus",
      "type": "map",
      "options": {
        "map": {
          'Normal': '启用',
          'Forbidden': '禁用',
        }
      }
    },
    { field: 'group_1', type: 'group', value: '个人信息', span: 24 },
    { "label": "联系人", "field": "traderContactName", "type": "plain" },
    { "label": "联系人手机", "field": "traderContactPhone", "type": "plain" },
    { "label": "联系人邮箱", "field": "traderContactEmail", "type": "plain" },
    { "label": "联系人职位", "field": "traderContactPosition", "type": "plain" },
    { field: 'group_2', type: 'group', value: '财务信息', span: 24 },
    { "label": "供应商开户名称", "field": "traderAccountName", "type": "plain" },
    { "label": "供应商开户银行", "field": "traderAccountBank", "type": "plain" },
    { "label": "供应商银行账号", "field": "traderAccountBankNo", "type": "plain" },
    { "label": "供应商发票抬头", "field": "traderInvoiceTitle", "type": "plain" },
    { field: 'group_3', type: 'group', value: '其它信息', span: 24 },
    { "label": "供应商注册时间", "field": "traderRegisterTime", "type": "plain" },
    { "label": "备注", "field": "traderNote", "type": "plain" },
  ]
}
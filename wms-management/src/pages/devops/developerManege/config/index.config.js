const formFields = require('./form.config');
const editFormFields = require('./edit.form.config');

module.exports = {
  layout: 'Content',
  title: '开发用户',
  items: [
    {
      component: 'Search',
      config: {
        fields: [
          {
            field: 'search', label: '搜索', type: 'input',
            props: {
              placeholder: '姓名 电话 email 账号',
            }
          }
        ],
      },
    },
    {
      component: 'TreeList',
      config: {
        API: {
          listAPI: '/api/adm/users?devuser=dev&orgId=<id>',
          deleteAPI: '/api/adm/users/(id)'
        },
        tree: {
          API: {
            initAPI: '/api/sys/org',
            appendAPI: undefined,
          }
        },
        actions: [
          {
            title: '添加', type: 'modal',
            options: {
              modalTitle: '添加用户',
              modalWidth: 900,
              items: [
                {
                  component: 'Form',
                  config: {
                    layout: 'Grid',
                    API: {
                      createAPI: '/api/adm/users?devuser=dev'
                    },
                    fields: formFields,
                  }
                }
              ]
            }
          }
        ],
        fields: [
          { field: 'name', label: '用户名' },
          { field: 'account', label: '登录账号' },
          {
            label: '类型',
            field: 'devUserType',
            align: 'center',
            valueType: 'tag',
            options: {
              map: {
                1: '测试',
                2: '开发',
                3: '运维'
              },
              colorMap: {
                1: '#486D9F',
                2: '#1E8DD0',
                // 3: 'blue'
              }
            }
          },
          { field: 'phone', label: '电话' },
          { field: 'orgName', label: '部门' },
          // { field: 'f2', label: '附属部门' },
          /* { field: 'positionName', label: '职位' },*/
          // {
          //   field: 'platformRoles', label: '角色', valueType: 'status',
          //   options: {
          //     statusMap: {
          //       'SUBSYS_OWNER': '子系统持有者',
          //       'MODULE_OWNER': '模块持有者',
          //       'TENANT': '租户',
          //       'AGENT': '代理'
          //     }
          //   }
          // },

          { field: 'email', label: 'Email' },
          {
            field: 'status', label: '状态', valueType: 'tag',
            options: {
              map: {
                '0': '停用',
                '1': '正常',
              },
              colorMap: {
                '0': '#ff2233',
                // '1': '',
              }
            }
          },
          /* {
             field: 'bUserType', label: '用户类型', valueType: 'tag',
             options: {
               map: {
                  '0': '普通用户',
                  '101':'平台管理员',
                  '102':'平台总主管',
                  '103':'平台店小二主管',
                  '104':'平台店小二',
                  '105':'平台商务主管',
                  '106':'平台商务',
                  '201':'广告主租户',
                  '202':'广告主用户',
                  '301':'影院管理员',
                  '302':'影院广告执行人',
               },
                color: {
                 '0': '#0099FF',
                 '101':'#ff2233',
                 '102':'#993333',
                 '103':'#999966',
                 '104':'#6699CC',
                 '105':'#999966',
                 '106':'#6699CC',
                 '201':'#99CC00',
                 '202':'#66CC00',
                 '301':'#99CC00',
                 '302':'#66CC00',
               }
             }
           },*/
        ],
        operation: [
          {
            title: '编辑', type: 'modal',
            options: {
              modalTitle: '编辑用户',
              modalWidth: 800,

              layout: 'Empty',
              items: [
                {
                  layout: 'Empty',
                  component: 'Form',
                  config: {
                    layout: 'Grid',
                    API: {
                      getAPI: '/api/adm/users/(id)?devuser=dev',
                      updateAPI: '/api/adm/users/(id)?devuser=dev',
                    },
                    fields: editFormFields,
                  }
                }
              ]
            }
          },
          {
            title: '停用', type: 'request',
            options: {
              field: 'status',
              value: 1,
              API: '/api/adm/users/(id)/status',
              method: 'put',
            }
          },
          {
            title: '启用', type: 'request',
            options: {
              field: 'status',
              value: 0,
              API: '/api/adm/users/(id)/status',
              method: 'put',
            }
          },
          {
            title: '删除', type: 'delete'
          }
        ]
      },
    },
  ],
};

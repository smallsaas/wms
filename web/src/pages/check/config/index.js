const setting = require('./check-setting.json');

module.exports = {
  layout: 'TitleContent',
  title: setting.pageName,
  items: [
    {
      component: 'Search',
      config: {
        fields: setting.searchFields,
      },
    },
    {
      component: 'Table',
      config: {
        API: {
          listAPI: setting.listAPI,
          deleteAPI: setting.deleteAPI,
        },
        actions: [
          {
            title: '新增', type: 'path',
            options: {
              path: 'check/check-add',
            },
          }
        ],
        fields: setting.tableFields,
        operation: [
          {
            title: '编辑', type: 'path',
            options: {
              outside: true,
              path: 'check/check-edit',
            },
          },
          {
            title: '删除', type: 'delete',
          },
        ]
      },
    },
  ],
};


const setting = require('./taskHistory-setting.json');

module.exports = {
  layout: setting.layout.table,
  title: setting.pageName.table,
  items: [
    // {
    //   component: 'Search',
    //   config: {
    //     fields: setting.searchFields,
    //   },
    // },
    {
      component: 'Table',
      config: {
        API: {
          listAPI: setting.listAPI,
          appendAPI: '',
          deleteAPI: setting.deleteAPI,
          actionAPI: setting.actionAPI

        },
        actions: setting.tableActions,
        fields: setting.tableFields,
        operation: setting.tableOperation,
      },
    },



  ],
};

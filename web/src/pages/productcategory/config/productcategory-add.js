const setting = require('./productcategory-setting.json');

module.exports = {
  layout: 'TitleContent',
  title: '新增' + setting.pageName,
  items: [
    {
      component: 'Form',
      config: {
        API: {
          createAPI: setting.createAPI,
        },
        layout: 'Grid',
        layoutConfig: {
          value: Array(setting.columns).fill(~~(24 / setting.columns)),
        },
        fields: setting.createFields || setting.formFields,
      },
    },
  ],
};

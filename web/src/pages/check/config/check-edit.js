const setting = require('./check-setting.json');

module.exports = {
  layout: 'TitleContent',
  title: '编辑' + setting.pageName,
  items: [
    {
      component: 'Form',
      config: {
        API: {
          getAPI: setting.getAPI,
          updateAPI: setting.updateAPI,
        },
        layout: 'Grid',
        layoutConfig: {
          value: Array(setting.columns).fill(~~(24 / setting.columns)),
        },
        fields: setting.updateFields || setting.formFields,
      },
    },
  ],
};

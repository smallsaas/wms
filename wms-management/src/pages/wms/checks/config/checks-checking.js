const setting = require('./checks-setting.js');

module.exports = {
  layout: setting.layout.form,
  title: setting.pageName.checking,
  items: [
    {
      component: 'Form',
      config: {
        API: {
            getAPI: setting.getAPI,
            updateAPI: setting.checkingAPI,
        },
        layout: 'Grid',
        layoutConfig: {
          value: Array(setting.columns).fill(~~(24 / setting.columns)),
        },
        fields: setting.checkingFields || setting.formFields,
      },
    },
  ],
};

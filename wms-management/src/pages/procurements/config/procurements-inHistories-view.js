const setting = require('./procurements-setting.js');

module.exports = {
  layout: setting.layout.form,
  title: '',
  items: [
    {
      component: 'Form',
      config: {
        API: {
          getAPI: setting.getAPI,
        },
        layout: 'Grid',
        layoutConfig: {
          value: Array(setting.columns).fill(~~(24 / setting.columns)),
        },
        fields: setting.inHistoriesViewConfig || setting.formFields,
        otherProps: {
          footerButton: false
        }
      },
    },
  ],
};

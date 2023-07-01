const setting = require('./procurements-setting.js');

module.exports = {
  layout: setting.layout.form,
  title: setting.pageName.view,
  items: [
    {
      component: 'AuditForm',
      config: {
        API: {
          getAPI: setting.getAPI,
          passAPI: setting.passAPI,
          refuseAPI: setting.refuseAPI
        },
        layout: 'Grid',
        layoutConfig: {
          value: Array(setting.columns).fill(~~(24 / setting.columns)),
        },
        fields: setting.viewConfig || setting.formFields,
        // otherProps: {
        //   footerButton: false
        // }
      },
    },
  ],
};

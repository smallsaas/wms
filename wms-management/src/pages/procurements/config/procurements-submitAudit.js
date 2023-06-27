const setting = require('./procurements-setting.js');

module.exports = {
  layout: setting.layout.form,
  title: "提交审核",
  items: [
    {
      component: 'Form',
      config: {
        API: {
            getAPI: setting.getAPI,
            updateAPI: "/api/wms/procurements/[id]/audit",
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

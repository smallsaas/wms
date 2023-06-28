const setting = require('./procurements-setting.js');

module.exports = {
  layout: setting.layout.form,
  title: "入库",
  items: [
    {
      component: 'Form',
      config: {
        API: {
          getAPI: setting.getAPI,
          updateAPI: "/api/wms/procurements/[id]/execution",
        },
        layout: 'Grid',
        layoutConfig: {
          value: Array(setting.columns).fill(~~(24 / setting.columns)),
        },
        fields: setting.warehousingFields || setting.formFields,
      },
    },
  ],
};

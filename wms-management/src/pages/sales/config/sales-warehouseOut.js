const setting = require('./sales-setting.js');

module.exports = {
  layout: setting.layout.form,
  title: "出库",
  items: [
    {
      component: 'Form',
      config: {
        API: {
          getAPI: setting.getAPI,
          updateAPI: "/api/warehouse/sales/[id]/execution",
        },
        layout: 'Grid',
        layoutConfig: {
          value: Array(setting.columns).fill(~~(24 / setting.columns)),
        },
        fields: setting.warehouseOutFields || setting.formFields,
      },
    },
  ],
};

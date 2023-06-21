const setting = require('./401-setting.json');

module.exports = {
  layout: setting.layout.table,
  title: '',
  items: [
    {
      component: 'ErrorLayout',
      config: {
        status: 401
      },
    },
  ],
};

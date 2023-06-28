const setting = require('./procurements-setting.js');

const viewConf = {
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
        fields: setting.viewConfig || setting.formFields,
        otherProps: {
          footerButton: false
        }
      },
    },
  ],
}

const logsConf = {
  layout: setting.layout.table,
  title: '日志记录',
  items: [
    {
      component: 'Search',
      config: {
        fields: [],
      },
    },
    {
      component: 'Table',
      config: {
        API: {
          listAPI: '',
          appendAPI: '',
          deleteAPI: '',
        },
        actions: [],
        fields: [
          { label: '记录时间', field: 'createTime' },
          { label: '主体人员', field: 'userName' },
          { label: '操作内容', field: 'logName' },
        ],
        operation: [],
        gobackOpt: false

      },
    },
  ],
}

const warehousingConf = {
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
        fields: setting.warehousingViewConfig || setting.formFields,
        otherProps: {
          footerButton: false
        }
      },
    },
  ],
}

export {
  viewConf,
  logsConf,
  warehousingConf
}

'use strict';

Object.defineProperty(exports, "__esModule", {
  value: true
});

var _index = require('../index');

var routePath = (0, _index.getRoutePath)();

exports.default = [{
  path: routePath.SALES,
  models: [{ namespace: 'sales', handle: require('../models/sales').default }],
  component: function component() {
    return import('../routes/sales');
  }
}];
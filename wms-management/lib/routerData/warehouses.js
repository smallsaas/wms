'use strict';

Object.defineProperty(exports, "__esModule", {
  value: true
});

var _index = require('../index');

var routePath = (0, _index.getRoutePath)();

exports.default = [{
  path: routePath.WAREHOUSES,
  models: [{ namespace: 'warehouses', handle: require('../models/warehouses').default }],
  component: function component() {
    return import('../routes/warehouses');
  }
}];
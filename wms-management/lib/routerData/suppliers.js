'use strict';

Object.defineProperty(exports, "__esModule", {
  value: true
});

var _index = require('../index');

var routePath = (0, _index.getRoutePath)();

exports.default = [{
  path: routePath.SUPPLIERS,
  models: [{ namespace: 'suppliers', handle: require('../models/suppliers').default }],
  component: function component() {
    return import('../routes/suppliers');
  }
}];
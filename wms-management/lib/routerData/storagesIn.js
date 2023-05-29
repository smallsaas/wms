'use strict';

Object.defineProperty(exports, "__esModule", {
  value: true
});

var _index = require('../index');

var routePath = (0, _index.getRoutePath)();

exports.default = [{
  path: routePath.STORAGESIN,
  models: [{ namespace: 'storagesIn', handle: require('../models/storagesIn').default }],
  component: function component() {
    return import('../routes/storagesIn');
  }
}];
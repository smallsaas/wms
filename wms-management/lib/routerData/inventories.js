'use strict';

Object.defineProperty(exports, "__esModule", {
  value: true
});

var _index = require('../index');

var routePath = (0, _index.getRoutePath)();

exports.default = [{
  path: routePath.INVENTORIES,
  models: [{ namespace: 'inventories', handle: require('../models/inventories').default }],
  component: function component() {
    return import('../routes/inventories');
  }
}];
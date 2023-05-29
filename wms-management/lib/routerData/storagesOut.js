'use strict';

Object.defineProperty(exports, "__esModule", {
  value: true
});

var _index = require('../index');

var routePath = (0, _index.getRoutePath)();

exports.default = [{
  path: routePath.STORAGESOUT,
  models: [{ namespace: 'storagesOut', handle: require('../models/storagesOut').default }],
  component: function component() {
    return import('../routes/storagesOut');
  }
}];
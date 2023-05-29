'use strict';

Object.defineProperty(exports, "__esModule", {
  value: true
});

var _index = require('../index');

var routePath = (0, _index.getRoutePath)();

exports.default = [{
  path: routePath.REFUNDS,
  models: [{ namespace: 'refunds', handle: require('../models/refunds').default }],
  component: function component() {
    return import('../routes/refunds');
  }
}];
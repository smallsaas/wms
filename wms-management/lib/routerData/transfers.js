'use strict';

Object.defineProperty(exports, "__esModule", {
  value: true
});

var _index = require('../index');

var routePath = (0, _index.getRoutePath)();

exports.default = [{
  path: routePath.TRANSFERS,
  models: [{ namespace: 'transfers', handle: require('../models/transfers').default }],
  component: function component() {
    return import('../routes/transfers');
  }
}];
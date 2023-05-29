'use strict';

Object.defineProperty(exports, "__esModule", {
  value: true
});

var _index = require('../index');

var routePath = (0, _index.getRoutePath)();

exports.default = [{
  path: routePath.CHECKS,
  models: [{ namespace: 'checks', handle: require('../models/checks').default }],
  component: function component() {
    return import('../routes/checks');
  }
}];
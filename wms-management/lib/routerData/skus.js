'use strict';

Object.defineProperty(exports, "__esModule", {
  value: true
});

var _index = require('../index');

var routePath = (0, _index.getRoutePath)();

exports.default = [{
  path: routePath.SKUS,
  models: [{ namespace: 'skus', handle: require('../models/skus').default }],
  component: function component() {
    return import('../routes/skus');
  }
}];
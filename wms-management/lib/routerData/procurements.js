'use strict';

Object.defineProperty(exports, "__esModule", {
  value: true
});

var _index = require('../index');

var routePath = (0, _index.getRoutePath)();

exports.default = [{
  path: routePath.PROCUREMENTS,
  models: [{ namespace: 'procurements', handle: require('../models/procurements').default }],
  component: function component() {
    return import('../routes/procurements');
  }
}];
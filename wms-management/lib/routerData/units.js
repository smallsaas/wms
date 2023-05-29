'use strict';

Object.defineProperty(exports, "__esModule", {
  value: true
});

var _index = require('../index');

var routePath = (0, _index.getRoutePath)();

exports.default = [{
  path: routePath.UNITS,
  models: [{ namespace: 'units', handle: require('../models/units').default }],
  component: function component() {
    return import('../routes/units');
  }
}];
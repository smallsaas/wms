'use strict';

Object.defineProperty(exports, "__esModule", {
  value: true
});

var _index = require('../index');

var routePath = (0, _index.getRoutePath)();

exports.default = [{
  path: routePath.TRADERS,
  models: [{ namespace: 'traders', handle: require('../models/traders').default }],
  component: function component() {
    return import('../routes/traders');
  }
}];
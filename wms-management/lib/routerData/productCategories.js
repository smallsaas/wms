'use strict';

Object.defineProperty(exports, "__esModule", {
  value: true
});

var _index = require('../index');

var routePath = (0, _index.getRoutePath)();

exports.default = [{
  path: routePath.PRODUCTCATEGORIES,
  models: [{ namespace: 'productCategories', handle: require('../models/productCategories').default }],
  component: function component() {
    return import('../routes/productCategories');
  }
}];
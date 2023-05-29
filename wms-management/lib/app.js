'use strict';

var _keys = require('babel-runtime/core-js/object/keys');

var _keys2 = _interopRequireDefault(_keys);

require('antd/dist/antd.css');

var _kqdFramework = require('kqd-framework');

var _kqdGeneral = require('kqd-general');

var _kqdGeneral2 = _interopRequireDefault(_kqdGeneral);

require('./app.css');

var _index = require('./index');

var _index2 = _interopRequireDefault(_index);

var _config = require('kqd-general/lib/config');

function _interopRequireDefault(obj) { return obj && obj.__esModule ? obj : { default: obj }; }

(0, _kqdGeneral2.default)();


(0, _config.setConfig)('submitConfirm', true);

var menu = [];
var menuSort = require.context('./routePath/', true, /\.js$/).keys().sort(function (a, b) {
  var aN = a.replace(/[^0-9]/ig, '');
  var bN = b.replace(/[^0-9]/ig, '');
  return aN - bN;
});
menuSort.forEach(function (file) {
  var res = require('./routePath/' + file.slice(2)).default;
  var m = (0, _keys2.default)(res)[0];
  if (m !== 'UNITS') menu.push({
    name: m,
    intl: m,
    path: res[m].slice(1)
  });
});

(0, _kqdFramework.addModule)((0, _index2.default)({
  menu: menu
}));

(0, _kqdFramework.setDefaultRoutePath)((0, _index.getRoutePath)().WAREHOUSES);

var inject = {
  'Copyright': '索芙特',
  'Company': '仓库管理系统',
  'Logo': 'none'
};
(0, _kqdFramework.framework)(inject);
(0, _kqdFramework.app)();
'use strict';

Object.defineProperty(exports, "__esModule", {
  value: true
});

var _toConsumableArray2 = require('babel-runtime/helpers/toConsumableArray');

var _toConsumableArray3 = _interopRequireDefault(_toConsumableArray2);

var _keys = require('babel-runtime/core-js/object/keys');

var _keys2 = _interopRequireDefault(_keys);

var _extends2 = require('babel-runtime/helpers/extends');

var _extends3 = _interopRequireDefault(_extends2);

exports.setRoutePath = setRoutePath;
exports.getRoutePath = getRoutePath;
exports.default = init;

var _kqdGeneral = require('kqd-general');

function _interopRequireDefault(obj) { return obj && obj.__esModule ? obj : { default: obj }; }

var routePath = {};

var routerData = [];

var locales = [{ zh: {} }, { en: {} }];

function getRouterData() {
  return routerData;
}

function getLocales() {
  return locales;
}

var defaultMenu = [];

function getMenu() {
  return defaultMenu;
}

function setRoutePath(data) {
  routePath = (0, _extends3.default)({}, routePath, data);
}

function getRoutePath() {
  return routePath;
}

function combineLocales(res, name) {
  var currentLang = void 0;
  var locale = locales.find(function (item) {
    var result = (0, _keys2.default)(item).filter(function (key) {
      return name.indexOf(key) > -1;
    });
    if (result.length > 0) {
      currentLang = result[0];
      return true;
    }
    return false;
  });
  if (locale) {
    locale[currentLang] = (0, _extends3.default)({}, locale[currentLang], res);
  }
}

/**
 * account 模块的初始化入口
 * @param routePath: Object
 * @param menu: Array
 * @param headerMenu: Array
 * @return Object
 */
function init(_ref) {
  var routePath = _ref.routePath,
      menu = _ref.menu,
      headerMenu = _ref.headerMenu;

  console.log(".init:", routePath, menu, headerMenu);

  var routePathData = {};
  require.context('./routePath/', true, /\.js$/).keys().forEach(function (file) {
    var res = require('./routePath/' + file.slice(2)).default;
    routePathData = (0, _extends3.default)({}, routePathData, res);
  });
  setRoutePath(routePathData);

  routePath && setRoutePath(routePath);

  /**
   * 绑定 model
   */
  require.context('./routerData/', true, /\.js$/).keys().forEach(function (file) {
    var res = require('./routerData/' + file.slice(2)).default;
    routerData.push.apply(routerData, (0, _toConsumableArray3.default)(res));
  });

  require.context('./locales/', true, /\.js$/).keys().forEach(function (file) {
    var fileName = file.slice(2); //去掉./
    var res = require('./locales/' + fileName).default;
    combineLocales(res, fileName);
  });
  _kqdGeneral.locales.map(function (item) {
    (0, _keys2.default)(item).map(function (key) {
      combineLocales(item[key], key);
    });
  });

  var res = {
    locales: getLocales(),
    routerData: getRouterData(),
    menu: menu ? menu : getMenu(),
    headerMenu: headerMenu ? headerMenu : []
  };
  console.log(" res = ", res);
  return res;
}
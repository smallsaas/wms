"use strict";

Object.defineProperty(exports, "__esModule", {
  value: true
});
exports.LS = undefined;

var _stringify = require("babel-runtime/core-js/json/stringify");

var _stringify2 = _interopRequireDefault(_stringify);

var _typeof2 = require("babel-runtime/helpers/typeof");

var _typeof3 = _interopRequireDefault(_typeof2);

function _interopRequireDefault(obj) { return obj && obj.__esModule ? obj : { default: obj }; }

var LS = exports.LS = {
  isLocalStorage: function isLocalStorage() {
    return !!window.localStorage;
  },
  set: function set(key, value) {
    value = (typeof value === "undefined" ? "undefined" : (0, _typeof3.default)(value)) === "object" ? (0, _stringify2.default)(value) : value;
    localStorage.setItem(key, value);
  },
  get: function get(key) {
    var value = localStorage.getItem(key) || '';
    if (value === "" || value.indexOf("{") < 0 && value.indexOf("[") < 0) {
      return value;
    } else {
      return JSON.parse(value);
    }
  },
  del: function del(key) {
    localStorage.removeItem(key);
  },
  clear: function clear() {
    localStorage.clear();
  }
};
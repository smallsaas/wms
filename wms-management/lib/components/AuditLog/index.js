'use strict';

Object.defineProperty(exports, "__esModule", {
  value: true
});
exports.default = undefined;

var _extends2 = require('babel-runtime/helpers/extends');

var _extends3 = _interopRequireDefault(_extends2);

var _getPrototypeOf = require('babel-runtime/core-js/object/get-prototype-of');

var _getPrototypeOf2 = _interopRequireDefault(_getPrototypeOf);

var _classCallCheck2 = require('babel-runtime/helpers/classCallCheck');

var _classCallCheck3 = _interopRequireDefault(_classCallCheck2);

var _createClass2 = require('babel-runtime/helpers/createClass');

var _createClass3 = _interopRequireDefault(_createClass2);

var _possibleConstructorReturn2 = require('babel-runtime/helpers/possibleConstructorReturn');

var _possibleConstructorReturn3 = _interopRequireDefault(_possibleConstructorReturn2);

var _inherits2 = require('babel-runtime/helpers/inherits');

var _inherits3 = _interopRequireDefault(_inherits2);

var _react = require('react');

var _react2 = _interopRequireDefault(_react);

var _kqdGeneral = require('kqd-general');

var _config = require('./config');

var _config2 = _interopRequireDefault(_config);

function _interopRequireDefault(obj) { return obj && obj.__esModule ? obj : { default: obj }; }

var List = _kqdGeneral.GMApp.List;

var AuditLog = function (_Component) {
  (0, _inherits3.default)(AuditLog, _Component);

  function AuditLog() {
    (0, _classCallCheck3.default)(this, AuditLog);
    return (0, _possibleConstructorReturn3.default)(this, (AuditLog.__proto__ || (0, _getPrototypeOf2.default)(AuditLog)).apply(this, arguments));
  }

  (0, _createClass3.default)(AuditLog, [{
    key: 'render',
    value: function render() {
      var props = (0, _extends3.default)({}, this.props, {
        listProps: {
          title: '日志记录',
          rowSelection: false
        },
        config: _config2.default
      });
      return _react2.default.createElement(
        'div',
        null,
        _react2.default.createElement('br', null),
        _react2.default.createElement(List, (0, _extends3.default)({}, props, { dataSourceMap: 'logs' }))
      );
    }
  }]);
  return AuditLog;
}(_react.Component);

exports.default = AuditLog;
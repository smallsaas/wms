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

var Form = _kqdGeneral.GMApp.Form,
    TitleLayout = _kqdGeneral.GMApp.TitleLayout;

var Query = function (_PureComponent) {
  (0, _inherits3.default)(Query, _PureComponent);

  function Query() {
    (0, _classCallCheck3.default)(this, Query);
    return (0, _possibleConstructorReturn3.default)(this, (Query.__proto__ || (0, _getPrototypeOf2.default)(Query)).apply(this, arguments));
  }

  (0, _createClass3.default)(Query, [{
    key: 'render',
    value: function render() {
      var submitForm = _react2.default.createElement(_react.Fragment, null);
      return _react2.default.createElement(
        TitleLayout,
        { title: '\u5546\u54C1\u8BE6\u60C5' },
        _react2.default.createElement(Form, (0, _extends3.default)({}, this.props, {
          config: _config2.default.details,
          formProps: {
            submitForm: submitForm
          }
        }))
      );
    }
  }]);
  return Query;
}(_react.PureComponent);

exports.default = Query;
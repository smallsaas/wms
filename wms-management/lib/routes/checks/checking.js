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

var _antd = require('antd');

var _router = require('dva/router');

var _config = require('./config');

var _config2 = _interopRequireDefault(_config);

function _interopRequireDefault(obj) { return obj && obj.__esModule ? obj : { default: obj }; }

var Form = _kqdGeneral.GMApp.Form;

var Checking = function (_PureComponent) {
  (0, _inherits3.default)(Checking, _PureComponent);

  function Checking() {
    var _ref;

    var _temp, _this, _ret;

    (0, _classCallCheck3.default)(this, Checking);

    for (var _len = arguments.length, args = Array(_len), _key = 0; _key < _len; _key++) {
      args[_key] = arguments[_key];
    }

    return _ret = (_temp = (_this = (0, _possibleConstructorReturn3.default)(this, (_ref = Checking.__proto__ || (0, _getPrototypeOf2.default)(Checking)).call.apply(_ref, [this].concat(args))), _this), _this.changeSubmitAPI = function (API) {
      var modelStatus = _this.props.modelStatus;
      // 直接修改 status 的数据

      modelStatus.updateAPI = API;
    }, _this.handleCancel = function () {
      var _this$props = _this.props,
          dispatch = _this$props.dispatch,
          namespace = _this$props.namespace;

      dispatch({
        type: namespace + '/fetchSuccess',
        payload: {
          currentItem: {}
        }
      });
      dispatch(_router.routerRedux.goBack());
    }, _temp), (0, _possibleConstructorReturn3.default)(_this, _ret);
  }
  // componentDidMount(){
  //   const { requester } = this.props;
  //   requester.fetchOne();
  // }

  (0, _createClass3.default)(Checking, [{
    key: 'render',
    value: function render() {
      var modelStatus = this.props.modelStatus;


      var GMFormProps = (0, _extends3.default)({}, this.props, {
        config: _config2.default.details,
        dataSourceMap: 'checking',
        APIObject: {
          updateAPI: '/api/wms/checks/[id]/checking'
        },
        formProps: {
          title: '盘点',
          rowSelection: false,
          submitForm: _react2.default.createElement(
            _react.Fragment,
            null,
            _react2.default.createElement('br', null),
            _react2.default.createElement('br', null),
            modelStatus.checking && modelStatus.checking.currentItem.status === 'Draft' ? _react2.default.createElement(
              _antd.Button,
              { type: 'primary', htmlType: 'submit', onClick: this.changeSubmitAPI.bind(this, '/api/wms/checks/{ID}/checking') },
              '\u5F00\u59CB\u76D8\u70B9'
            ) : '',
            modelStatus.currentItem.status === 'Checking' ? _react2.default.createElement(
              _antd.Button,
              { type: 'primary', htmlType: 'submit', onClick: this.changeSubmitAPI.bind(this, '/api/wms/checks/{ID}/checking') },
              '\u6682\u5B58\u76D8\u70B9'
            ) : '',
            _react2.default.createElement(
              _antd.Button,
              { onClick: this.handleCancel },
              '\u53D6\u6D88'
            )
          )
        }
      });
      return _react2.default.createElement(Form, GMFormProps);
    }
  }]);
  return Checking;
}(_react.PureComponent);

exports.default = Checking;
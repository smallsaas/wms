'use strict';

Object.defineProperty(exports, "__esModule", {
  value: true
});

var _extends2 = require('babel-runtime/helpers/extends');

var _extends3 = _interopRequireDefault(_extends2);

var _react = require('react');

var _react2 = _interopRequireDefault(_react);

var _router = require('dva/router');

var _antd = require('antd');

var _querystring = require('querystring');

var _querystring2 = _interopRequireDefault(_querystring);

var _services = require('kqd-utils/lib/services');

var _token = require('kqd-utils/lib/token');

function _interopRequireDefault(obj) { return obj && obj.__esModule ? obj : { default: obj }; }

exports.default = function (props) {
  var dispatch = props.dispatch,
      namespace = props.namespace,
      search = props.location.search,
      _props$data = props.data,
      data = _props$data === undefined ? {} : _props$data,
      queryConfig = props.queryConfig,
      _props$audit = props.audit,
      audit = _props$audit === undefined ? {} : _props$audit;
  var API = audit.API,
      _audit$statusField = audit.statusField,
      statusField = _audit$statusField === undefined ? 'status' : _audit$statusField;
  // const { itemsField, itemsFieldMap = itemsField } = queryConfig;

  function handlePass() {
    var id = _querystring2.default.parse(search.replace('?', '')).id;
    var payload = (0, _extends3.default)({}, data);
    // if(itemsFieldMap !== itemsField){
    //   payload[itemsFieldMap] = payload[itemsField];
    //   delete payload[itemsField];
    // }
    var rst = (0, _services.update)(API + '/' + id + '/passed', payload);
    rst.then(handleRst);
  }
  function handleClosed() {
    var id = _querystring2.default.parse(search.replace('?', '')).id;
    var rst = (0, _services.update)(API + '/' + id + '/closed');
    rst.then(handleRst);
  }
  function handleRst(_ref) {
    var code = _ref.code;

    if (code === 200) {
      _antd.message.success('操作成功');
      dispatch({
        type: namespace + '/fetchSuccess',
        payload: {
          currentItem: {}
        }
      });
      dispatch(_router.routerRedux.goBack());
    }
  }
  var permission = (0, _token.getPermissions)();
  var permissionData = typeof permission === 'string' ? JSON.parse(permission) : permission;

  if (Array.isArray(permissionData) && permissionData.findIndex(function (item) {
    return item === audit.permission;
  }) > -1) {
    if (data[statusField] === 'Wait_To_Audit' || data[statusField] === 'CheckOut') {
      return _react2.default.createElement(
        _react.Fragment,
        null,
        _react2.default.createElement(
          _antd.Button,
          { type: 'primary', onClick: handlePass },
          '\u5BA1\u6838\u901A\u8FC7'
        ),
        _react2.default.createElement(
          _antd.Button,
          { type: 'danger', onClick: handleClosed },
          '\u5BA1\u6838\u62D2\u7EDD'
        )
      );
    }
  }
  return null;
};
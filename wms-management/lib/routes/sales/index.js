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

var _dec, _class;

var _react = require('react');

var _react2 = _interopRequireDefault(_react);

var _dva = require('dva');

var _kqdGeneral = require('kqd-general');

var _config = require('./config');

var _config2 = _interopRequireDefault(_config);

var _query = require('./query');

var _query2 = _interopRequireDefault(_query);

var _Audit = require('./Audit');

var _Audit2 = _interopRequireDefault(_Audit);

function _interopRequireDefault(obj) { return obj && obj.__esModule ? obj : { default: obj }; }

var Index = (_dec = (0, _dva.connect)(function (_ref) {
  var sales = _ref.sales,
      loading = _ref.loading;
  return {
    modelStatus: sales,
    namespace: 'sales',
    loading: loading.models.sales
  };
}), _dec(_class = function (_PureComponent) {
  (0, _inherits3.default)(Index, _PureComponent);

  function Index() {
    (0, _classCallCheck3.default)(this, Index);
    return (0, _possibleConstructorReturn3.default)(this, (Index.__proto__ || (0, _getPrototypeOf2.default)(Index)).apply(this, arguments));
  }

  (0, _createClass3.default)(Index, [{
    key: 'render',
    value: function render() {
      var formProps = this.props.location.search.indexOf('edit') > -1 ? {
        submitButton: [{ title: '提交审核', method: 'put', API: '/api/warehouse/sales/[id]/audit' }, { title: '保存修改', method: 'put', API: '/api/warehouse/sales/[id]' }, { title: '取消', type: 'return' }]
      } : undefined;
      return _react2.default.createElement(_kqdGeneral.GMApp, (0, _extends3.default)({}, this.props, { config: _config2.default,
        listProps: {
          rowSelection: false
        },
        routerMap: {
          query: _query2.default,
          audit: _Audit2.default
        },
        formProps: formProps
      }));
    }
  }]);
  return Index;
}(_react.PureComponent)) || _class);
exports.default = Index;
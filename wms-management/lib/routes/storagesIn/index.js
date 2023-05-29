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

var _query = require('../../components/query');

var _query2 = _interopRequireDefault(_query);

var _Audit = require('./Audit');

var _Audit2 = _interopRequireDefault(_Audit);

function _interopRequireDefault(obj) { return obj && obj.__esModule ? obj : { default: obj }; }

var Index = (_dec = (0, _dva.connect)(function (_ref) {
  var storagesIn = _ref.storagesIn,
      loading = _ref.loading;
  return {
    modelStatus: storagesIn,
    namespace: 'storagesIn',
    loading: loading.models.storagesIn
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
      var submitButton = this.props.location.search.indexOf('edit') > -1 ? [{ title: '提交审核', method: 'put', API: '/api/wms/storages/in/[id]/audit' }, { title: '保存修改', method: 'put', API: '/api/wms/storages/in/[id]' }, { title: '取消', type: 'return' }] : [];
      return _react2.default.createElement(_kqdGeneral.GMApp, (0, _extends3.default)({}, this.props, { config: _config2.default,
        listProps: {
          rowSelection: false
        },
        formProps: {
          submitButton: submitButton
        },
        routerMap: {
          query: _react2.default.createElement(_query2.default, { queryConfig: {
              title: '入库详情',
              itemsField: 'storageInItems',
              info: [{ label: '入库编号', field: 'transactionCode' }, { label: '仓库', field: 'warehouseName' }, { label: '经办人', field: 'transactionBy' }, { label: '入库日期', field: 'transactionTime' }, {
                label: '订单号信息',
                field: 'outOrderNum',
                substitute: 'procurementCode'
              }, { label: '客户', field: 'distributorCustomer' }, { label: '制单人', field: 'originatorName' }, { label: '备注', field: 'note' }],
              columns: [{ title: ' ', field: 'total' }, { title: '商品条码', field: 'skuBarcode' }, { title: '商品编号', field: 'skuCode' }, { title: '商品名称', field: 'skuName' }, { title: '需求数量', field: 'demandQuantities', align: 'right', bottomTotal: true }, { title: '入库数量', field: 'transactionQuantities', align: 'right', bottomTotal: true }, { title: '单位', field: 'skuUnit' }]
            },
            logType: 'in'
          }),
          audit: _Audit2.default
        }
      }));
    }
  }]);
  return Index;
}(_react.PureComponent)) || _class);
exports.default = Index;
'use strict';

Object.defineProperty(exports, "__esModule", {
  value: true
});
exports.default = undefined;

var _extends2 = require('babel-runtime/helpers/extends');

var _extends3 = _interopRequireDefault(_extends2);

var _toArray2 = require('babel-runtime/helpers/toArray');

var _toArray3 = _interopRequireDefault(_toArray2);

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

var _services = require('kqd-utils/lib/services');

var _kqdGeneral = require('kqd-general');

var _config = require('./config');

var _config2 = _interopRequireDefault(_config);

var _query = require('../../components/query');

var _query2 = _interopRequireDefault(_query);

var _Audit = require('./Audit');

var _Audit2 = _interopRequireDefault(_Audit);

function _interopRequireDefault(obj) { return obj && obj.__esModule ? obj : { default: obj }; }

var Index = (_dec = (0, _dva.connect)(function (_ref) {
  var transfers = _ref.transfers,
      loading = _ref.loading;
  return {
    modelStatus: transfers,
    namespace: 'transfers',
    loading: loading.models.transfers
  };
}), _dec(_class = function (_PureComponent) {
  (0, _inherits3.default)(Index, _PureComponent);

  function Index() {
    var _ref2;

    var _temp, _this, _ret;

    (0, _classCallCheck3.default)(this, Index);

    for (var _len = arguments.length, args = Array(_len), _key = 0; _key < _len; _key++) {
      args[_key] = arguments[_key];
    }

    return _ret = (_temp = (_this = (0, _possibleConstructorReturn3.default)(this, (_ref2 = Index.__proto__ || (0, _getPrototypeOf2.default)(Index)).call.apply(_ref2, [this].concat(args))), _this), _this.warehouseId = null, _this.getValidSku = function () {
      var _this2 = _this,
          fieldName = _this2.fieldName,
          warehouseId = _this2.warehouseId;

      if (!fieldName || !warehouseId) return false;
      (0, _services.query)('/api/wms/inventories?warehouseId=' + warehouseId + '&pageSize=99').then(function (_ref3) {
        var code = _ref3.code,
            data = _ref3.data;

        if (code === 200) {
          var records = data.records;
          var currentItem = _this.props.modelStatus.currentItem;

          var _ref4 = currentItem[fieldName] || [],
              _ref5 = (0, _toArray3.default)(_ref4),
              childrenData = _ref5.slice(0);

          childrenData.forEach(function (child) {
            var item = records.find(function (item) {
              return child.skuId === item.skuId;
            });
            if (item) {
              child.validSku = item.validSku;
            } else {
              child.validSku = 0;
            }
          });

          var dispatch = _this.props.dispatch;

          var tempObj = {};
          tempObj[fieldName] = childrenData;

          dispatch({
            type: 'transfers/save',
            payload: {
              currentItem: (0, _extends3.default)({}, currentItem, tempObj)
            }
          });
        }
      });
    }, _temp), (0, _possibleConstructorReturn3.default)(_this, _ret);
  }

  (0, _createClass3.default)(Index, [{
    key: 'render',
    value: function render() {
      var _this3 = this;

      var submitButton = this.props.location.search.indexOf('edit') > -1 ? [{ title: '提交审核', method: 'put', API: '/api/wms/transfers/[id]/audit' }, { title: '保存修改', method: 'put', API: '/api/wms/transfers/[id]' }, { title: '取消', type: 'return' }] : [];
      return _react2.default.createElement(_kqdGeneral.GMApp, (0, _extends3.default)({}, this.props, { config: _config2.default,
        listProps: {
          rowSelection: false
        },
        formProps: {
          onChildrenSelected: function onChildrenSelected(field, items) {
            // validSku
            _this3.fieldName = field;
            _this3.getValidSku();
          },
          onSelectChange: function onSelectChange(value) {
            _this3.warehouseId = value;
            _this3.getValidSku();
          },
          submitButton: submitButton
        },
        routerMap: {
          query: _react2.default.createElement(_query2.default, { queryConfig: {
              title: '调拨详情',
              itemsField: 'outItems',
              info: [{ label: '调出仓库', field: 'fromWarehouseName' }, { label: '调出时间', field: 'transactionTime' }, { label: '调入仓库', field: 'toWarehouseName' }, { label: '调入时间', field: 'finishTime' }, { label: '经办人', field: 'transactionBy' }, { label: '制单人', field: 'originatorName' }, { label: '备注', field: 'note' }],
              columns: [{ title: ' ', field: 'total' }, { title: '商品条码', field: 'skuBarcode' }, { title: '商品编号', field: 'skuCode' }, { title: '商品名称', field: 'skuName' }, { title: '需求数量', field: 'demandQuantities', align: 'right', bottomTotal: true }, { title: '调拨数量', field: 'transactionQuantities', align: 'right', bottomTotal: true }, { title: '单位', field: 'skuUnit' }]
            },
            logType: 'transfer'
          }),
          audit: _Audit2.default
        }
      }));
    }
  }]);
  return Index;
}(_react.PureComponent)) || _class);
exports.default = Index;
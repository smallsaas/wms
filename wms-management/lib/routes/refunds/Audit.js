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

var _query = require('../../components/query');

var _query2 = _interopRequireDefault(_query);

function _interopRequireDefault(obj) { return obj && obj.__esModule ? obj : { default: obj }; }

var Audit = function (_PureComponent) {
  (0, _inherits3.default)(Audit, _PureComponent);

  function Audit() {
    (0, _classCallCheck3.default)(this, Audit);
    return (0, _possibleConstructorReturn3.default)(this, (Audit.__proto__ || (0, _getPrototypeOf2.default)(Audit)).apply(this, arguments));
  }

  (0, _createClass3.default)(Audit, [{
    key: 'render',
    value: function render() {
      return _react2.default.createElement(_query2.default, (0, _extends3.default)({}, this.props, {
        queryConfig: {
          title: '退货审核',
          itemsField: 'items',
          // itemsFieldMap: 'items',
          info: [{ label: '退货编号', field: 'productRefundCode' }, { label: '仓库', path: 'records[0] && records[0].warehouseName' }, { label: '经办人', field: 'transactionBy' }, { label: '退货日期', field: 'transactionTime' }, { label: '备注', field: 'productRefundNote' }, { label: '制单人', field: 'originatorName' }, { label: '关联的采购单', field: 'procurementCode' }, { label: '产生的出库单', field: 'field1' }, { label: '供应商', field: 'supplierName' }],
          columns: [{ title: ' ', field: 'total' }, { title: '商品条码', field: 'skuBarcode' }, { title: '商品编号', field: 'skuCode' }, { title: '商品名称', field: 'skuName' }, { title: '需求数量', field: 'demandQuantities', align: 'right', bottomTotal: true }, { title: '退货数量', field: 'transactionQuantities', align: 'right', valueType: 'editQuantity', bottomTotal: true, width: 114 }, { title: '退货单价', field: 'transactionSkuPrice', align: 'right', valueType: 'currency' }, {
            title: '退货总价', field: 'transactionTotalSkuPrice', align: 'right', valueType: 'totalPrice', bottomTotal: true, width: 114,
            options: {
              field: 'transactionTotalSkuPrice'
            },
            format: function format(v) {
              return '\uFFE5 ' + v;
            }
          }, { title: '单位', field: 'skuUnit' }]
        },
        audit: {
          API: '/api/wms/refunds',
          permission: 'wms.purchase.audit',
          statusField: 'productRefundStatus'
        }
      }));
    }
  }]);
  return Audit;
}(_react.PureComponent);

exports.default = Audit;
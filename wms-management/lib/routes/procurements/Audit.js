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
          title: '采购审核',
          itemsField: 'items',
          // itemsFieldMap: 'items',
          info: [{ label: '采购单编号', field: 'procurementCode' }, { label: '供应商', field: 'supplierName' }, { label: '采购员', field: 'transactionBy' }, { label: '采购日期', field: 'procurementTime' }, { label: '制单人', field: 'originatorName' }, { label: '备注', field: 'procurementNote' }],
          columns: [{ title: ' ', field: 'total' }, { title: '商品条码', field: 'skuBarcode' }, { title: '采购时间', field: 'transactionTime' }, { title: '商品编号', field: 'skuCode' }, { title: '商品名称', field: 'skuName' }, { title: '需求数量', field: 'demandQuantities', align: 'right', bottomTotal: true }, { title: '采购数量', field: 'transactionQuantities', align: 'right', valueType: 'editQuantity', bottomTotal: true }, { title: '已入库数量', field: 'sectionInCount', align: 'right', bottomTotal: true }, { title: '采购单价', field: 'transactionSkuPrice', align: 'right', valueType: 'currency' }, {
            title: '采购总价', field: 'transactionTotalSkuPrice', align: 'right', valueType: 'totalPrice', bottomTotal: true,
            options: {
              field: 'transactionTotalSkuPrice'
            },
            format: function format(v) {
              return '\uFFE5 ' + v;
            }
          }, { title: '单位', field: 'skuUnit' }]
        },
        audit: {
          API: '/api/wms/procurements',
          permission: 'wms.purchase.audit',
          statusField: 'procureStatus'
        }
      }));
    }
  }]);
  return Audit;
}(_react.PureComponent);

exports.default = Audit;
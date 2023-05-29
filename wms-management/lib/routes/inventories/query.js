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

var _kqdPageHeader = require('kqd-page-header');

var _kqdGeneral = require('kqd-general');

require('../../components/query/style.css');

var _config = require('./config');

var _config2 = _interopRequireDefault(_config);

function _interopRequireDefault(obj) { return obj && obj.__esModule ? obj : { default: obj }; }

var TitleLayout = _kqdGeneral.GMApp.TitleLayout;
var List = _kqdGeneral.GMApp.List;

var Quert = function (_Component) {
  (0, _inherits3.default)(Quert, _Component);

  function Quert() {
    var _ref;

    var _temp, _this, _ret;

    (0, _classCallCheck3.default)(this, Quert);

    for (var _len = arguments.length, args = Array(_len), _key = 0; _key < _len; _key++) {
      args[_key] = arguments[_key];
    }

    return _ret = (_temp = (_this = (0, _possibleConstructorReturn3.default)(this, (_ref = Quert.__proto__ || (0, _getPrototypeOf2.default)(Quert)).call.apply(_ref, [this].concat(args))), _this), _this.formatData = function () {
      var data = arguments.length > 0 && arguments[0] !== undefined ? arguments[0] : [];

      data.map(function (item) {
        if (item.recordType === 'sin') {
          item.inQuantities = Math.abs(item.transactionQuantities);
          item.outQuantities = '-';
        } else {
          item.inQuantities = '-';
          item.outQuantities = Math.abs(item.transactionQuantities);
        }
        return item;
      });
      return data;
    }, _temp), (0, _possibleConstructorReturn3.default)(_this, _ret);
  }

  (0, _createClass3.default)(Quert, [{
    key: 'componentDidMount',
    value: function componentDidMount() {
      var requester = this.props.requester;

      requester.fetchOne({
        API: '/api/wms/skus/[id]'
      }, 'skuData');
    }
  }, {
    key: 'render',
    value: function render() {
      var modelStatus = this.props.modelStatus;
      var _modelStatus$skuData = modelStatus.skuData,
          skuData = _modelStatus$skuData === undefined ? {} : _modelStatus$skuData;


      var appProps = (0, _extends3.default)({}, this.props, {
        listProps: {
          title: false,
          rowSelection: false,
          formatListData: this.formatData
        },
        config: _config2.default.details,
        visible: skuData.id !== undefined
      });

      return _react2.default.createElement(
        TitleLayout,
        (0, _extends3.default)({ title: '\u5546\u54C1\u51FA\u5165\u5E93\u8BE6\u60C5' }, this.props),
        _react2.default.createElement(
          'div',
          { className: 'wms-query-info' },
          _react2.default.createElement(
            'span',
            null,
            '\u5546\u54C1\u7F16\u53F7\uFF1A',
            skuData.productCode
          ),
          _react2.default.createElement(
            'span',
            null,
            '\u5546\u54C1\u6761\u7801\uFF1A',
            skuData.barCode
          ),
          _react2.default.createElement(
            'span',
            null,
            '\u5546\u54C1\u540D\u79F0\uFF1A',
            skuData.name
          )
        ),
        _react2.default.createElement('br', null),
        _react2.default.createElement(
          _kqdPageHeader.TitledHeader,
          { title: '\u5546\u54C1\u51FA\u5165\u5E93\u8BB0\u5F55'
            // showCollapse={ true }
          },
          _react2.default.createElement(List, (0, _extends3.default)({}, appProps, { APIObject: { listAPI: '/api/wms/inventories/skus/[id]?warehouseName=[warehouseName]' }, dataSourceMap: 'inAndOut' }))
        )
      );
    }
  }]);
  return Quert;
}(_react.Component);

exports.default = Quert;
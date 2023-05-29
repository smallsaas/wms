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

var _kqdPageHeader = require('kqd-page-header');

var _querystring = require('querystring');

var _querystring2 = _interopRequireDefault(_querystring);

var _antd = require('antd');

var _endpoint = require('kqd-utils/lib/endpoint');

var _token = require('kqd-utils/lib/token');

var _AuditLog = require('../../components/AuditLog');

var _AuditLog2 = _interopRequireDefault(_AuditLog);

var _config = require('./config');

var _config2 = _interopRequireDefault(_config);

require('../../components/query/style.css');

function _interopRequireDefault(obj) { return obj && obj.__esModule ? obj : { default: obj }; }

var TabPane = _antd.Tabs.TabPane;
var List = _kqdGeneral.GMApp.List,
    Form = _kqdGeneral.GMApp.Form,
    TitleLayout = _kqdGeneral.GMApp.TitleLayout;


var Footer = function Footer(props) {
  return _react2.default.createElement(
    'div',
    { className: 'price' },
    '\u9500\u552E\u603B\u989D\uFF1A\uFFE5',
    _react2.default.createElement(
      'span',
      null,
      props.modelStatus.currentItem.salesTotal
    )
  );
};
var InHistoriesFooter = function InHistoriesFooter(props) {
  var _props$item = props.item,
      item = _props$item === undefined ? {} : _props$item;

  return _react2.default.createElement(
    _react.Fragment,
    null,
    _react2.default.createElement(
      'div',
      { className: 'wms-query-info' },
      _react2.default.createElement(
        'span',
        null,
        '\u51FA\u5E93\u5355\u7F16\u53F7\uFF1A',
        item.transactionCode || '-'
      ),
      _react2.default.createElement(
        'span',
        null,
        '\u51FA\u5E93\u4ED3\uFF1A',
        item.warehouseName || '-'
      ),
      _react2.default.createElement(
        'span',
        null,
        '\u7ECF\u529E\u4EBA\uFF1A',
        item.transactionBy || '-'
      ),
      _react2.default.createElement(
        'span',
        null,
        '\u51FA\u5E93\u5907\u6CE8\uFF1A',
        item.note || '-'
      )
    ),
    _react2.default.createElement(
      'div',
      { className: 'wms-query-info' },
      _react2.default.createElement(
        'span',
        null,
        '\u51FA\u5E93\u65E5\u671F\uFF1A',
        item.storageOutTime || '-'
      )
    )
  );
};

var Query = function (_PureComponent) {
  (0, _inherits3.default)(Query, _PureComponent);

  function Query() {
    var _ref;

    var _temp, _this, _ret;

    (0, _classCallCheck3.default)(this, Query);

    for (var _len = arguments.length, args = Array(_len), _key = 0; _key < _len; _key++) {
      args[_key] = arguments[_key];
    }

    return _ret = (_temp = (_this = (0, _possibleConstructorReturn3.default)(this, (_ref = Query.__proto__ || (0, _getPrototypeOf2.default)(Query)).call.apply(_ref, [this].concat(args))), _this), _this.tabs = '2', _this.print = function () {
      var link = document.createElement('a');
      link.target = '_blank';
      link.href = (0, _endpoint.getEndpoint)() + '/api/pub/cloud/io/pdf/out/distributor-order?identifier=' + _this.id + '&key=94a08da1fecbb6e8b46990538c7b50b2';
      link.click();
    }, _temp), (0, _possibleConstructorReturn3.default)(_this, _ret);
  }

  (0, _createClass3.default)(Query, [{
    key: 'componentWillMount',
    value: function componentWillMount() {
      var search = this.props.location.search;

      this.tabs = _querystring2.default.parse(search.replace('?', '')).tabs || '2';
      this.id = _querystring2.default.parse(search.replace('?', '')).id;
    }
  }, {
    key: 'componentDidMount',
    value: function componentDidMount() {
      var requester = this.props.requester;

      requester.fetchOne();
    }
  }, {
    key: 'render',
    value: function render() {
      var _this2 = this;

      var columns = [{ title: ' ', field: 'total' }, { title: '商品编号', field: 'skuCode' }, { title: '商品条码', field: 'skuBarcode' }, { title: '商品名称', field: 'skuName' }, { title: '单位', field: 'skuUnit' }, { title: '需求数量', field: 'demandQuantities', align: 'right', bottomTotal: true }, { title: '销售数量', field: 'transactionQuantities', align: 'right', bottomTotal: true }, { title: '销售单价', field: 'transactionSkuPrice', align: 'right', valueType: 'currency' }, {
        title: '销售总价', field: 'transactionTotalSkuPrice', align: 'right', valueType: 'totalPrice', bottomTotal: true,
        options: {
          field: 'transactionTotalSkuPrice'
        },
        format: function format(v) {
          return '\uFFE5 ' + v;
        }
      }];
      var storageOutItemRecordsColumns = [{ title: ' ', field: 'total' }, { title: '商品编号', field: 'skuCode' }, { title: '商品条码', field: 'skuBarcode' }, { title: '商品名称', field: 'skuName' }, { title: '单位', field: 'skuUnit' },
      // { title: '总数', field: 'totalCount' },
      // { title: '已出库数', field: 'sectionInCount' },
      { title: '本次出库数量', field: 'transactionQuantities' }];
      var statusMap = {
        'WaitForStorageOut': '等待出库',
        'SectionStorageOut': '部分出库',
        'TotalStorageOut': '全部出库',
        'Draft': '草稿',
        'Audit_Passed': '审核通过',
        'undefined': '-'
      };
      var modelStatus = this.props.modelStatus;
      var currentItem = modelStatus.currentItem;

      var outItems = currentItem.outItems || [];
      // 这里直接修改了 model 里面的数据
      // outItems.forEach( item => {
      //   item.skuId = item.id;
      //   // item.totalCount = item.transactionQuantities;
      // });
      var storageOutItemRecords = currentItem.storageOutItemRecords || [];
      var formatOutItemRecords = [];
      if (storageOutItemRecords.length > 0) {
        storageOutItemRecords.forEach(function (item) {
          var index = formatOutItemRecords.findIndex(function (row) {
            return row.transactionCode === item.transactionCode;
          });
          if (index === -1) {
            formatOutItemRecords.push((0, _extends3.default)({}, item, {
              items: [{
                skuBarcode: item.skuBarcode,
                transactionTime: item.transactionTime,
                skuCode: item.skuCode,
                skuName: item.skuName,
                transactionQuantities: item.transactionQuantities,
                transactionSkuPrice: item.transactionSkuPrice
              }]
            }));
          } else {
            formatOutItemRecords[index].items.push({
              skuBarcode: item.skuBarcode,
              transactionTime: item.transactionTime,
              skuCode: item.skuCode,
              skuName: item.skuName,
              transactionQuantities: item.transactionQuantities,
              transactionSkuPrice: item.transactionSkuPrice
            });
          }
        });
      }

      var GMFormProps = (0, _extends3.default)({}, this.props, {
        formProps: {
          title: '出库',
          rowSelection: false,
          extra: null
        },
        config: _config2.default.details,
        APIObject: {
          updateAPI: '/api/warehouse/sales/[id]/execution'
        },
        visible: currentItem.salesStatus !== 'TotalStorageIn'
        // /api/cloud/io/pdf/out/t2
      });var queryExtra = _react2.default.createElement(
        'div',
        null,
        _react2.default.createElement(
          _antd.Button,
          { type: 'primary', onClick: this.print },
          '\u6253\u5370'
        ),
        _react2.default.createElement(
          _antd.Button,
          { onClick: function onClick() {
              return _this2.props.router.goBack();
            } },
          '\u8FD4\u56DE'
        )
      );

      var auditLogProps = (0, _extends3.default)({}, this.props, {
        APIObject: {
          listAPI: '/api/logs?targetType=distributor_out&targetId=[id]'
        }
      });

      var permission = (0, _token.getPermissions)();
      var permissionData = typeof permission === 'string' ? JSON.parse(permission) : permission;
      return _react2.default.createElement(
        _antd.Tabs,
        { defaultActiveKey: this.tabs },
        _react2.default.createElement(
          TabPane,
          { tab: '\u8BA2\u5355\u8BE6\u60C5', key: '1' },
          _react2.default.createElement(
            TitleLayout,
            { title: '\u5206\u9500\u5355\u8BE6\u60C5', extra: queryExtra, goBack: false },
            _react2.default.createElement(
              'div',
              { className: 'status' },
              statusMap[currentItem.salesStatus] || '-'
            ),
            _react2.default.createElement(
              'div',
              { className: 'wms-query-info' },
              _react2.default.createElement(
                'span',
                null,
                '\u8BA2\u5355\u7F16\u53F7\uFF1A',
                currentItem.salesCode || '-'
              ),
              _react2.default.createElement(
                'span',
                null,
                '\u8BA2\u5355\u521B\u5EFA\u4EBA\uFF1A',
                currentItem.originatorName || '-'
              ),
              _react2.default.createElement(
                'span',
                null,
                '\u521B\u5EFA\u65E5\u671F\uFF1A',
                currentItem.transactionTime || '-'
              ),
              _react2.default.createElement(
                'span',
                null,
                '\u5206\u9500\u5546\u540D\u79F0\uFF1A',
                currentItem.traderName || '-'
              ),
              _react2.default.createElement(
                'span',
                null,
                '\u8054\u7CFB\u7535\u8BDD\uFF1A',
                currentItem.traderContactPhone || '-'
              ),
              _react2.default.createElement(
                'span',
                null,
                '\u7ECF\u529E\u4EBA\uFF1A',
                currentItem.transactionBy || '-'
              ),
              _react2.default.createElement(
                'div',
                null,
                '\u6536\u8D27\u4EBA\uFF1A',
                currentItem.traderContactName || '-'
              ),
              _react2.default.createElement(
                'div',
                null,
                '\u6536\u8D27\u5730\u5740\uFF1A',
                currentItem.deliveredAddress || '-'
              ),
              _react2.default.createElement(
                'div',
                null,
                '\u5907\u6CE8\uFF1A',
                currentItem.salesNote || '-'
              )
            ),
            _react2.default.createElement(_kqdGeneral.GMGeneralTable, {
              columns: columns,
              data: outItems,
              footer: function footer(data) {
                return _react2.default.createElement(Footer, { data: data, modelStatus: modelStatus });
              },
              rowSelection: false,
              pagination: { total: outItems.length }
            }),
            _react2.default.createElement(_AuditLog2.default, auditLogProps)
          )
        ),
        _react2.default.createElement(
          TabPane,
          { tab: '\u5546\u54C1\u51FA\u5E93', key: '2' },
          _react2.default.createElement(
            TitleLayout,
            { title: '\u9500\u552E\u4FE1\u606F' },
            _react2.default.createElement(
              'div',
              { className: 'wms-query-info' },
              _react2.default.createElement(
                'span',
                null,
                '\u8BA2\u5355\u7F16\u53F7\uFF1A',
                currentItem.salesCode || '-'
              ),
              _react2.default.createElement(
                'span',
                null,
                '\u8BA2\u5355\u521B\u5EFA\u4EBA\uFF1A',
                currentItem.originatorName || '-'
              ),
              _react2.default.createElement(
                'span',
                null,
                '\u521B\u5EFA\u65E5\u671F\uFF1A',
                currentItem.transactionTime || '-'
              ),
              _react2.default.createElement(
                'span',
                null,
                '\u5206\u9500\u5546\u540D\u79F0\uFF1A',
                currentItem.traderName || '-'
              ),
              _react2.default.createElement(
                'span',
                null,
                '\u8054\u7CFB\u7535\u8BDD\uFF1A',
                currentItem.traderContactPhone || '-'
              ),
              _react2.default.createElement(
                'span',
                null,
                '\u7ECF\u529E\u4EBA\uFF1A',
                currentItem.transactionBy || '-'
              ),
              _react2.default.createElement(
                'div',
                null,
                '\u6536\u8D27\u5730\u5740\uFF1A',
                currentItem.deliveredAddress || '-'
              ),
              _react2.default.createElement(
                'div',
                null,
                '\u5907\u6CE8\uFF1A',
                currentItem.salesNote || '-'
              )
            )
          ),
          (currentItem.salesStatus === 'WaitForStorageOut' || currentItem.salesStatus === 'SectionStorageOut') && Array.isArray(permissionData) && permissionData.findIndex(function (item) {
            return item === 'wms.sales.edit';
          }) > -1 ? _react2.default.createElement(Form, GMFormProps) : null,
          _react2.default.createElement(
            _kqdPageHeader.TitledHeader,
            { title: '\u51FA\u5E93\u8BB0\u5F55' },
            formatOutItemRecords.map(function (item, i) {
              return _react2.default.createElement(
                _react.Fragment,
                { key: i },
                _react2.default.createElement(_kqdGeneral.GMGeneralTable, { columns: storageOutItemRecordsColumns, data: item.storageOutItems,
                  footer: function footer(data) {
                    return _react2.default.createElement(InHistoriesFooter, { data: data, item: item });
                  },
                  rowSelection: false }),
                _react2.default.createElement(_antd.Divider, null)
              );
            }),
            storageOutItemRecords.length === 0 ? '暂无记录' : ''
          )
        )
      );
    }
  }]);
  return Query;
}(_react.PureComponent);

exports.default = Query;
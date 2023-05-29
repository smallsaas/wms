'use strict';

Object.defineProperty(exports, "__esModule", {
  value: true
});
exports.default = undefined;

var _extends2 = require('babel-runtime/helpers/extends');

var _extends3 = _interopRequireDefault(_extends2);

var _defineProperty2 = require('babel-runtime/helpers/defineProperty');

var _defineProperty3 = _interopRequireDefault(_defineProperty2);

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

var _reactDom = require('react-dom');

var _reactDom2 = _interopRequireDefault(_reactDom);

var _kqdGeneral = require('kqd-general');

var _kqdPageHeader = require('kqd-page-header');

var _querystring = require('querystring');

var _querystring2 = _interopRequireDefault(_querystring);

var _antd = require('antd');

var _services = require('kqd-utils/lib/services');

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
    '\u91C7\u8D2D\u603B\u989D\uFF1A\uFFE5',
    _react2.default.createElement(
      'span',
      null,
      props.modelStatus.currentItem.procurementTotal
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
        '\u5165\u5E93\u5355\u7F16\u53F7\uFF1A',
        item.storageInCode || '-'
      ),
      _react2.default.createElement(
        'span',
        null,
        '\u5165\u5E93\u4ED3\uFF1A',
        item.warehouseName || '-'
      ),
      _react2.default.createElement(
        'span',
        null,
        '\u7ECF\u529E\u4EBA\uFF1A',
        item.transactionName || '-'
      ),
      _react2.default.createElement(
        'span',
        null,
        '\u5165\u5E93\u5907\u6CE8\uFF1A',
        item.storageInNote || '-'
      )
    ),
    _react2.default.createElement(
      'div',
      { className: 'wms-query-info' },
      _react2.default.createElement(
        'span',
        null,
        '\u91C7\u8D2D\u5355\u7F16\u53F7\uFF1A',
        item.procurementCode || '-'
      ),
      _react2.default.createElement(
        'span',
        null,
        '\u91C7\u8D2D\u5458\uFF1A',
        item.buyer || '-'
      ),
      _react2.default.createElement(
        'span',
        null,
        '\u91C7\u8D2D\u65E5\u671F\uFF1A',
        item.procurementDate || '-'
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
      link.href = (0, _endpoint.getEndpoint)() + '/api/pub/cloud/io/pdf/out/deliver?identifier=' + _this.id + '&key=94a08da1fecbb6e8b46990538c7b50b2';
      link.click();

      // create('/api/cloud/io/pdf/out/deliver', {
      //   identifier: this.id,
      // })
      //   .then(blob => {
      //     // console.log(1111,blob);
      //     // console.log(2222,URL.createObjectURL(blob));

      //     // tempWindow.location.href = URL.createObjectURL(blob);
      //     link.href = URL.createObjectURL(blob);
      //     // link.download = "file.pdf";
      //     link.click();
      //     // tempWindow.location.href = 'http://test.com';
      //   })
    }, _this.export = function () {
      var link = document.createElement('a');
      link.target = '_blank';
      link.href = (0, _endpoint.getEndpoint)() + '/api/pub/poi/agent/export/wms/procurement/' + _this.id + '?key=94a08da1fecbb6e8b46990538c7b50b2';
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
      var _statusMap,
          _this2 = this;

      var columns = [{ title: ' ', field: 'total' }, { title: '商品条码', field: 'skuBarcode' },
      // { title: '采购时间', field: 'transactionTime' },
      { title: '商品编号', field: 'skuCode' }, { title: '商品名称', field: 'skuName' }, { title: '需求数量', field: 'demandQuantities', align: 'right', bottomTotal: true }, { title: '采购数量', field: 'transactionQuantities', align: 'right', bottomTotal: true }, { title: '已入库数量', field: 'sectionInCount', align: 'right', bottomTotal: true }, {
        title: '采购单价', field: 'transactionSkuPrice', align: 'right', valueType: 'currency',
        options: {
          permission: 'wms.purchase.price'
        }
      }, {
        title: '采购总价', field: 'transactionTotalSkuPrice', align: 'right', valueType: 'totalPrice', bottomTotal: true,
        options: {
          field: 'transactionTotalSkuPrice',
          permission: 'wms.purchase.price'
        },
        format: function format(v) {
          return '\uFFE5 ' + v;
        }
      }, { title: '单位', field: 'skuUnit' }];
      var inHistoriesColumns = [{ title: ' ', field: 'total' }, { title: '商品条码', field: 'skuBarcode' }, { title: '入库时间', field: 'transactionTime' }, { title: '商品编号', field: 'skuCode' }, { title: '商品名称', field: 'skuName' }, { title: '入库数量', field: 'transactionQuantities', align: 'right', bottomTotal: true }, {
        title: '入库价格', field: 'transactionSkuPrice', align: 'right', valueType: 'currency',
        options: {
          permission: 'wms.purchase.price'
        }
      }, { title: '单位', field: 'skuUnit' }];
      var statusMap = (_statusMap = {
        'Audit_Passed': '等待入库',
        'SectionStorageIn': '部分入库',
        'TotalStorageIn': '全部入库',
        'Closed': '被关闭',
        'Draft': '草稿'
      }, (0, _defineProperty3.default)(_statusMap, 'Audit_Passed', '审核通过'), (0, _defineProperty3.default)(_statusMap, 'undefined', '-'), _statusMap);
      var modelStatus = this.props.modelStatus;

      var items = modelStatus.currentItem.items || [];
      // 这里直接修改了 model 里面的数据
      // records.forEach( item => {
      //   item.skuId = item.id;
      //   // item.totalCount = item.transactionQuantities;
      // });
      var inHistories = modelStatus.currentItem.inHistories || [];
      var formatInHistories = [];
      if (inHistories.length > 0) {
        inHistories.forEach(function (item) {
          var index = formatInHistories.findIndex(function (row) {
            return row.storageInCode === item.storageInCode;
          });
          if (index === -1) {
            formatInHistories.push({
              id: item.id,
              storageInCode: item.storageInCode,
              warehouseName: item.warehouseName,
              transactionName: item.transactionName,
              storageInNote: item.storageInNote,
              procurementCode: item.procurementCode,
              transactionBy: item.transactionBy,
              procurementDate: item.procurementDate,
              items: [{
                skuBarcode: item.skuBarcode,
                transactionTime: item.transactionTime,
                skuCode: item.skuCode,
                skuName: item.skuName,
                transactionQuantities: item.transactionQuantities,
                transactionSkuPrice: item.transactionSkuPrice,
                skuUnit: item.skuUnit
              }]
            });
          } else {
            formatInHistories[index].items.push({
              skuBarcode: item.skuBarcode,
              transactionTime: item.transactionTime,
              skuCode: item.skuCode,
              skuName: item.skuName,
              transactionQuantities: item.transactionQuantities,
              transactionSkuPrice: item.transactionSkuPrice,
              skuUnit: item.skuUnit
            });
          }
        });
      }

      var GMFormProps = (0, _extends3.default)({}, this.props, {
        formProps: {
          title: '入库',
          rowSelection: false,
          extra: null
        },
        config: _config2.default.details,
        APIObject: {
          updateAPI: '/api/wms/procurements/[id]/execution'
        },
        visible: modelStatus.currentItem.procureStatus !== 'TotalStorageIn'
      });
      var auditLogProps = (0, _extends3.default)({}, this.props, {
        APIObject: {
          listAPI: '/api/logs?targetType=purchase&targetId=[id]'
        }
      });
      // /api/cloud/io/pdf/out/t2
      var queryExtra = _react2.default.createElement(
        'div',
        null,
        _react2.default.createElement(
          _antd.Button,
          { type: 'primary', onClick: this.export },
          '\u5BFC\u51FA'
        ),
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
            { title: '\u91C7\u8D2D\u5355\u8BE6\u60C5', extra: queryExtra, goBack: false },
            _react2.default.createElement(
              'div',
              { className: 'status' },
              statusMap[modelStatus.currentItem.procureStatus] || '-'
            ),
            _react2.default.createElement(
              'div',
              { className: 'wms-query-info' },
              _react2.default.createElement(
                'span',
                null,
                '\u91C7\u8D2D\u5355\u7F16\u53F7\uFF1A',
                modelStatus.currentItem.procurementCode || '-'
              ),
              _react2.default.createElement(
                'span',
                null,
                '\u4F9B\u5E94\u5546\uFF1A',
                modelStatus.currentItem.supplierName || '-'
              ),
              _react2.default.createElement(
                'span',
                null,
                '\u91C7\u8D2D\u5458\uFF1A',
                modelStatus.currentItem.transactionBy || '-'
              ),
              _react2.default.createElement(
                'span',
                null,
                '\u91C7\u8D2D\u65E5\u671F\uFF1A',
                modelStatus.currentItem.procurementTime || '-'
              ),
              _react2.default.createElement(
                'span',
                null,
                '\u5236\u5355\u4EBA\uFF1A',
                modelStatus.currentItem.originatorName || '-'
              ),
              _react2.default.createElement(
                'div',
                null,
                '\u5907\u6CE8\uFF1A',
                modelStatus.currentItem.procurementNote || '-'
              )
            ),
            _react2.default.createElement(_kqdGeneral.GMGeneralTable, {
              columns: columns,
              data: items,
              footer: function footer(data) {
                return _react2.default.createElement(Footer, { data: data, modelStatus: modelStatus });
              },
              rowSelection: false,
              pagination: { total: items.length }
            }),
            _react2.default.createElement(_AuditLog2.default, auditLogProps)
          )
        ),
        _react2.default.createElement(
          TabPane,
          { tab: '\u5546\u54C1\u5165\u5E93', key: '2' },
          _react2.default.createElement(
            TitleLayout,
            { title: '\u91C7\u8D2D\u4FE1\u606F' },
            _react2.default.createElement(
              'div',
              { className: 'wms-query-info' },
              _react2.default.createElement(
                'span',
                null,
                '\u91C7\u8D2D\u5355\u7F16\u53F7\uFF1A',
                modelStatus.currentItem.procurementCode || '-'
              ),
              _react2.default.createElement(
                'span',
                null,
                '\u4F9B\u5E94\u5546\uFF1A',
                modelStatus.currentItem.supplierName || '-'
              ),
              _react2.default.createElement(
                'span',
                null,
                '\u521B\u5EFA\u65F6\u95F4\uFF1A',
                modelStatus.currentItem.transactionTime || '-'
              ),
              _react2.default.createElement(
                'span',
                null,
                '\u5236\u5355\u4EBA\uFF1A',
                modelStatus.currentItem.originatorName || '-'
              ),
              _react2.default.createElement(
                'span',
                null,
                '\u5907\u6CE8\uFF1A',
                modelStatus.currentItem.procurementNote || '-'
              )
            )
          ),
          (modelStatus.currentItem.procureStatus === 'Audit_Passed' || modelStatus.currentItem.procureStatus === 'SectionStorageIn') && Array.isArray(permissionData) && permissionData.findIndex(function (item) {
            return item === 'wms.purchase.in';
          }) > -1 ? _react2.default.createElement(Form, GMFormProps) : null,
          _react2.default.createElement(
            _kqdPageHeader.TitledHeader,
            { title: '\u5165\u5E93\u8BB0\u5F55' },
            formatInHistories.map(function (item, i) {
              return _react2.default.createElement(
                _react.Fragment,
                { key: i },
                _react2.default.createElement(_kqdGeneral.GMGeneralTable, { columns: inHistoriesColumns, data: item.items || [],
                  footer: function footer(data) {
                    return _react2.default.createElement(InHistoriesFooter, { data: data, item: item });
                  },
                  rowSelection: false }),
                _react2.default.createElement(_antd.Divider, null)
              );
            }),
            inHistories.length === 0 ? '暂无记录' : ''
          )
        )
      );
    }
  }]);
  return Query;
}(_react.PureComponent);

exports.default = Query;
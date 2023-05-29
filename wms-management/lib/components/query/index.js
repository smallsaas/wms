'use strict';

Object.defineProperty(exports, "__esModule", {
  value: true
});
exports.default = undefined;

var _extends2 = require('babel-runtime/helpers/extends');

var _extends3 = _interopRequireDefault(_extends2);

var _toConsumableArray2 = require('babel-runtime/helpers/toConsumableArray');

var _toConsumableArray3 = _interopRequireDefault(_toConsumableArray2);

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

var _querystring = require('querystring');

var _querystring2 = _interopRequireDefault(_querystring);

var _AuditButton = require('../AuditButton');

var _AuditButton2 = _interopRequireDefault(_AuditButton);

var _AuditLog = require('../AuditLog');

var _AuditLog2 = _interopRequireDefault(_AuditLog);

require('./style.css');

function _interopRequireDefault(obj) { return obj && obj.__esModule ? obj : { default: obj }; }

var TitleLayout = _kqdGeneral.GMApp.TitleLayout;

var Query = function (_Component) {
  (0, _inherits3.default)(Query, _Component);

  function Query() {
    var _ref;

    var _temp, _this, _ret;

    (0, _classCallCheck3.default)(this, Query);

    for (var _len = arguments.length, args = Array(_len), _key = 0; _key < _len; _key++) {
      args[_key] = arguments[_key];
    }

    return _ret = (_temp = (_this = (0, _possibleConstructorReturn3.default)(this, (_ref = Query.__proto__ || (0, _getPrototypeOf2.default)(Query)).call.apply(_ref, [this].concat(args))), _this), _this.handleChangeFieldValue = function (index, field, record, value) {
      var _this$props = _this.props,
          dispatch = _this$props.dispatch,
          namespace = _this$props.namespace,
          modelStatus = _this$props.modelStatus,
          queryConfig = _this$props.queryConfig;

      var records = modelStatus.currentItem[queryConfig.itemsField] || [];
      records[index][field] = value;
      modelStatus.currentItem[queryConfig.itemsField] = [].concat((0, _toConsumableArray3.default)(records));
      dispatch({
        type: namespace + '/save',
        payload: {
          modelStatus: (0, _extends3.default)({}, modelStatus)
        }
      });
    }, _temp), (0, _possibleConstructorReturn3.default)(_this, _ret);
  }

  (0, _createClass3.default)(Query, [{
    key: 'componentDidMount',
    value: function componentDidMount() {
      var _props = this.props,
          dispatch = _props.dispatch,
          namespace = _props.namespace,
          search = _props.location.search,
          modelStatus = _props.modelStatus;

      var API = modelStatus.queryAPI || this.props.API || modelStatus.API;
      var id = _querystring2.default.parse(search.replace('?', '')).id;

      if (id) {
        dispatch({
          type: namespace + '/fetchOne',
          payload: {
            id: id,
            API: API
          }
        });
      }
    }
  }, {
    key: 'render',
    value: function render() {
      var _props2 = this.props,
          modelStatus = _props2.modelStatus,
          queryConfig = _props2.queryConfig,
          statusField = _props2.statusField,
          logType = _props2.logType;

      var records = modelStatus.currentItem[queryConfig.itemsField] || [];
      // api 直接返回正确的 id，故不再做 id 映射
      // records.forEach(item => {
      //   item.skuId = item.id;
      // });
      var auditLogProps = (0, _extends3.default)({}, this.props, {
        APIObject: {
          listAPI: '/api/logs?targetType=' + logType + '&targetId=[id]'
        }
      });

      return _react2.default.createElement(
        TitleLayout,
        { title: queryConfig.title },
        _react2.default.createElement(
          'div',
          { className: 'wms-query-info' },
          queryConfig.info.map(function (item, i) {
            return _react2.default.createElement(
              'span',
              { key: i, style: {
                  width: item.field === 'note' ? '100%' : '40%',
                  marginTop: '.5em'
                } },
              item.label,
              '\uFF1A',
              item.field ? modelStatus.currentItem[item.field] || modelStatus.currentItem[item.substitute] : item.path ? eval(item.path) : '-'
            );
          })
        ),
        _react2.default.createElement(_kqdGeneral.GMGeneralTable, { columns: queryConfig.columns, data: records,
          rowSelection: false,
          onChangeColValue: this.handleChangeFieldValue
        }),
        _react2.default.createElement('br', null),
        _react2.default.createElement(_AuditButton2.default, (0, _extends3.default)({}, this.props, {
          data: modelStatus.currentItem,
          queryConfig: queryConfig
        })),
        _react2.default.createElement(_AuditLog2.default, auditLogProps)
      );
    }
  }]);
  return Query;
}(_react.Component);

exports.default = Query;
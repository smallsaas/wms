'use strict';

Object.defineProperty(exports, "__esModule", {
  value: true
});

var _regenerator = require('babel-runtime/regenerator');

var _regenerator2 = _interopRequireDefault(_regenerator);

var _extends2 = require('babel-runtime/helpers/extends');

var _extends3 = _interopRequireDefault(_extends2);

var _kqdGeneral = require('kqd-general');

var _services = require('kqd-utils/lib/services');

function _interopRequireDefault(obj) { return obj && obj.__esModule ? obj : { default: obj }; }

exports.default = (0, _kqdGeneral.modelExtend)(_kqdGeneral.baseModel, {
  namespace: 'suppliers',
  state: {
    API: '/api/wms/suppliers',
    config: {}
  },
  reducers: {
    save: function save(state, _ref) {
      var payload = _ref.payload;

      return (0, _extends3.default)({}, state, payload);
    }
  },
  effects: {
    demoQuery: /*#__PURE__*/_regenerator2.default.mark(function demoQuery(_ref2, _ref3) {
      var payload = _ref2.payload;
      var call = _ref3.call,
          put = _ref3.put;
      var API, result;
      return _regenerator2.default.wrap(function demoQuery$(_context) {
        while (1) {
          switch (_context.prev = _context.next) {
            case 0:
              console.log("demoQuery");
              API = payload.API;

              delete payload.API;
              _context.next = 5;
              return call(_services.query, API, (0, _extends3.default)({}, payload));

            case 5:
              result = _context.sent;

            case 6:
            case 'end':
              return _context.stop();
          }
        }
      }, demoQuery, this);
    }),
    getConfig: /*#__PURE__*/_regenerator2.default.mark(function getConfig(_ref4, _ref5) {
      var API = _ref4.payload.API;
      var call = _ref5.call,
          put = _ref5.put,
          select = _ref5.select;
      var rst;
      return _regenerator2.default.wrap(function getConfig$(_context2) {
        while (1) {
          switch (_context2.prev = _context2.next) {
            case 0:
              _context2.next = 2;
              return call(_services.query, API + '/config');

            case 2:
              rst = _context2.sent;
              _context2.next = 5;
              return put({
                type: 'save',
                payload: {
                  config: rst.data._config
                }
              });

            case 5:
            case 'end':
              return _context2.stop();
          }
        }
      }, getConfig, this);
    })
  }
});
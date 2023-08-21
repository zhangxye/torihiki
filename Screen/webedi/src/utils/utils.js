import _ from 'lodash'
import moment from 'moment'

// ;(function (global, factory) {
// 	typeof exports === 'object' && typeof module !== 'undefined' ? module.exports = factory() :
// 	typeof define === 'function' && define.amd ? define(factory) :
// 	(global.utils = factory());
// }(this, (function () { 'use strict';


// //日付項目変換
// var convertDate = function(x_date) {
//    if( _.isEmpty(x_date) ) return '';
//    return moment(x_date).toDate();
// };

// var version = '1.0.0';

// var _utils = {
//   version: version,
//   convertDate : convertDate,
// };

//  return _utils;

// })));

const utils = {
  //日付項目変換
  convertDate : function(x_date) {
    if( _.isUndefined(x_date) ) return '';
    return moment(x_date).toDate();
  },

  //日付フォーマット
  formatDate : function(x_date, x_format) {
    if( _.isNil(x_date) || x_date ==='') return '';
    var p_format = x_format || 'YYYY-MM-DD';

    return  moment(x_date).format(x_format);
  },
  //金額フォーマット
  formatMoney: function(number, places, symbol, thousand, decimal) {
    number = number || 0;
    places = !isNaN(places = Math.abs(places)) ? places : 0;
    symbol = symbol !== undefined ? symbol : "￥";
    thousand = thousand || ",";
    decimal = decimal || ".";
    var negative = number < 0 ? "-" : "",
        i = _.toInteger(number = Math.abs(+number || 0).toFixed(places), 10) + "",
        j = (j = i.length) > 3 ? j % 3 : 0;
    return symbol + negative + (j ? i.substr(0, j) + thousand : "") + i.substr(j).replace(/(\d{3})(?=\d)/g, "$1" + thousand) + (places ? decimal + Math.abs(number - i).toFixed(places).slice(2) : "");
},


  version : '1.0.0'
};

export default utils;

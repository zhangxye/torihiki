
import _ from 'lodash';
import validator from 'validator';
import ElementUI from 'element-ui';

(function (global, factory) {
	typeof exports === 'object' && typeof module !== 'undefined' ? module.exports = factory() :
	typeof define === 'function' && define.amd ? define(factory) :
	(global.formValidator = factory());
}(this, (function () { 'use strict';

//正数Check
var chkInt = (rule, value, callback) => {
  if(!validator.isInt(_.trim(value))){
    callback(new ElementUI.Error('正数を入力してください。'));
  }
  callback();
};

var version = '8.1.0';

var formValidator = {
  version: version,
  chkInt : chkInt,
};

return formValidator;

})));

import Vue from 'vue'
import utils from '@/utils/utils'
export default (function () {
  Vue.filter('formatAmt',function(value){return utils.formatMoney(value ,0 ,"￥")});
})()

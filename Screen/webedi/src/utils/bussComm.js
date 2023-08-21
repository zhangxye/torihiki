import _ from 'lodash';
import moment from 'moment';

  function _calPrice(x_price, x_wh){
    if(_.isUndefined(x_wh) || x_wh === '' || x_wh === 0) return 0;
    return _.floor(x_price / x_wh,-1);
  }

    //超過単価計算
    function calExcessPrice(calObj){
      // 1：上下割
      // 2：中割
      // 3：固定o
      const p_accounting_pat_k = calObj.x_accounting_pat_k;
      const p_sell_price = calObj.x_sell_price;
      const p_wh_max = calObj.x_wh_max;
      const p_wh_mid = calObj.x_wh_mid;
      var p_excess_price = calObj.x_excess_price;
      // console.table(calObj);
      // console.log('p_accounting_pat_k = %s',p_accounting_pat_k);
      // console.log('p_sell_price = %d',p_sell_price);
      // console.log('p_wh_max = %d',p_wh_max);
      // console.log('p_wh_mid = %d',p_wh_mid);
        if(p_accounting_pat_k === '1'){
          p_excess_price = _calPrice(p_sell_price, p_wh_max);
        }else if(p_accounting_pat_k === '2'){
          p_excess_price = _calPrice(p_sell_price, p_wh_mid);
        }
        return p_excess_price;
      }

    //超過単価計算
    function calDeducationPrice(calObj){
      // 1：上下割
      // 2：中割
      // 3：固定o
      const p_accounting_pat_k = calObj.x_accounting_pat_k;
      const p_sell_price = calObj.x_sell_price;
      const p_wh_min = calObj.x_wh_min;
      const p_wh_mid = calObj.x_wh_mid;
      var p_deducation_price = calObj.x_deducation_price;
      // console.table(calObj);
      // console.log('p_accounting_pat_k = %s',p_accounting_pat_k);
      // console.log('p_sell_price = %d',p_sell_price);
      // console.log('p_wh_min = %d',p_wh_min);
      // console.log('p_wh_mid = %d',p_wh_mid);
        if(p_accounting_pat_k === '1'){
          p_deducation_price = _calPrice(p_sell_price, p_wh_min);
        }else if(p_accounting_pat_k === '2'){
          p_deducation_price = _calPrice(p_sell_price, p_wh_mid);
        }
        return p_deducation_price;
      }

    //社会保険計算
   function calInsuranceCost(x_cal_obj){

    //  console.table(x_cal_obj);
    //保険加入
    // '1':'雇用保険のみ'
    // '2':'社保険完備'
    // '3':'保険無し'
    var p_insurance_k = x_cal_obj.x_insurance_k;
    //原価
    var p_cost_price = _.toInteger(x_cal_obj.x_cost_price);
    //年齢
    var p_emp_age = _.toInteger(x_cal_obj.x_emp_age);
    //交通費
    var p_travel_cost = _.toInteger(x_cal_obj.x_travel_cost);
    //その他
    var p_other_cost = _.toInteger(x_cal_obj.x_other_cost);

    // var p_base_cost = p_cost_price + p_travel_cost + p_other_cost;
    var p_base_cost = p_cost_price + p_travel_cost;

    //雇用保険
    var p_employee_insurance = x_cal_obj.x_employee_insurance;
    var p_health_insurance_base_price_list = x_cal_obj.x_health_insurance_base_price_list;
    var p_employee_pension_base_price_list = x_cal_obj.x_employee_pension_base_price_list;

    var p_health_insurance_ratio_list = x_cal_obj.x_health_insurance_ratio_list;
    var p_employee_pension_ratio_list = x_cal_obj.x_employee_pension_ratio_list;

    if(!_.includes(['1','2'],p_insurance_k)){
        return 0;
      }

    if(p_insurance_k === '1'){
       return p_employee_insurance;
      }

    //健康保険標準報酬
    var health_insurance_base_price = _.result(_.find(
        p_health_insurance_base_price_list, function(item) {
                            return item.salary_from <= p_base_cost &&
                            p_base_cost < item.salary_to;
                            }), 'base_price');
    //  console.log("health_insurance_base_price=%s",health_insurance_base_price);
    //厚生年金標準報酬
    var employee_pension_base_price = _.result(_.find(
        p_employee_pension_base_price_list, function(item) {
                            return item.salary_from <=  p_base_cost &&
                            p_base_cost < item.salary_to;
                            }), 'base_price');

    //  console.log("employee_pension_base_price=%s",employee_pension_base_price);
    //健康保険料率
    var health_insurance_ratio = _.result(_.find(
        p_health_insurance_ratio_list, function(item) {
                            return item.age_from <=  p_emp_age &&
                                  p_emp_age < item.age_to;
                            }), 'ratio');
    //  console.log("health_insurance_ratio=%s",health_insurance_ratio);

    //厚生年金料率
    var employee_pension_ratio = _.result(_.find(
        p_employee_pension_ratio_list, function(item) {
                            return item.age_from <=  p_emp_age &&
                                  p_emp_age < item.age_to;
                            }), 'ratio');
    //  console.log("employee_pension_ratio=%s",employee_pension_ratio);

    //健康保険
    var health_insurance = _.floor(health_insurance_base_price * health_insurance_ratio / 100/2+0.5);
    //  console.log("health_insurance=%s",health_insurance);

    //厚生年金
    var employee_pension = _.floor(employee_pension_base_price * employee_pension_ratio / 100/2+0.5);
    //  console.log("employee_pension=%s",employee_pension);

    //社会保険料金=(原価＋交通費＋健康保険＋厚生年金)*1.0085　－　原価 ー　交通費
    //計算方法がおかしい、検討要
    var insurance_cost = health_insurance + employee_pension;
    insurance_cost = _.round((p_base_cost + insurance_cost)*1.0085,0) - p_base_cost;
    return insurance_cost;
  }

  //手当金額を計算
  function calAllowAmt(x_cost_price, x_allow_ratio){
    // console.log("x_cost_price=%s",x_cost_price);
    // console.log("x_allow_ratio=%s",x_allow_ratio);
    if(_.isUndefined(x_cost_price) || x_cost_price===''){
      return '';
    }
    if(_.isUndefined(x_allow_ratio) || x_allow_ratio===''){
      return '';
    }

    return _.round(x_cost_price*x_allow_ratio/100);
  }

  //手当率を計算
  function calAllowRatio(x_cost_price, x_allow_amt){
    // console.log("x_cost_price=%s",x_cost_price);
    // console.log("x_allow_amt=%s",x_allow_amt);
    if(_.isUndefined(x_cost_price) || x_cost_price===''){
      return '';
    }
    if(_.isUndefined(x_allow_amt) || x_allow_amt===''){
      return '';
    }

    return _.round(x_allow_amt/x_cost_price*100,2);
  }

const bussComm = {
  //超過単価計算
  calExcessPrice: calExcessPrice,
  //超過単価計算
  calDeducationPrice: calDeducationPrice,
  //社会保険計算
  calInsuranceCost : calInsuranceCost,
  //手当金額を計算
  calAllowAmt : calAllowAmt,
  //手当率を計算
  calAllowRatio : calAllowRatio,

  version : '1.0.0'
};

export default bussComm;

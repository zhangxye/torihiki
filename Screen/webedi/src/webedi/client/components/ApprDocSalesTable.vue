<template>
        <table cellspacing="0"
        class="table table-bordered"
        id="salesTable">
            <thead>
                <tr>
                    <th></th>
                    <th>売上(単金)</th>
                    <th>仕入(単金)</th>
                    <th>交通費</th>
                    <th>社保</th>
                    <th>その他</th>
                    <th>仕入合計</th>
                    <th>粗利金額</th>
                    <th>粗利率</th>
                </tr>
            </thead>
            <tbody>
                <tr>
                  <th>初月(日割り)</th>
                  <td>{{formatMoney(sell_price_first_mon,0,"￥")}}</td>
                  <td>{{formatMoney(cost_price_first_mon,0,"￥")}}</td>
                  <td>{{formatMoney(apprDocInfo.travel_cost_first_mon ,0,"￥")}}</td>
                  <td>-</td>
                  <td>{{formatMoney(apprDocInfo.other_cost_first_mon ,0,"￥")}}</td>
                  <td>{{formatMoney(buying_in_cost_first_mon,0,"￥")}}</td>
                  <td>{{formatMoney(gross_profit_amt_first_mon,0,"￥")}}</td>
                  <td>{{formatRatio(gross_profit_amt_first_mon,sell_price_first_mon,1)}}</td>
                </tr>
                <tr>
                  <th>通常月</th>
                  <td>{{formatMoney(apprDocInfo.sell_price,0,"￥")}}</td>
                  <td>{{formatMoney(apprDocInfo.cost_price,0,"￥")}}</td>
                  <td>{{formatMoney(apprDocInfo.travel_cost ,0,"￥")}}</td>
                  <td>-</td>
                  <td>{{formatMoney(apprDocInfo.other_cost ,0,"￥")}}</td>
                  <td>{{formatMoney(buying_in_cost-apprDocInfo.insurance_cost,0,"￥")}}</td>
                  <td>{{formatMoney(gross_profit_amt+apprDocInfo.insurance_cost,0,"￥")}}</td>
                  <td>{{formatRatio(gross_profit_amt+apprDocInfo.insurance_cost,apprDocInfo.sell_price,1)}}</td>
                </tr>
                <tr>
                  <th>社保加入後</th>
                  <td>{{formatMoney(apprDocInfo.sell_price,0,"￥")}}</td>
                  <td>{{formatMoney(apprDocInfo.cost_price,0,"￥")}}</td>
                  <td>{{formatMoney(apprDocInfo.travel_cost ,0,"￥")}}</td>
                  <td>{{formatMoney(apprDocInfo.insurance_cost ,0,"￥")}}</td>
                  <td>{{formatMoney(apprDocInfo.other_cost ,0,"￥")}}</td>
                  <td>{{formatMoney(buying_in_cost,0,"￥")}}</td>
                  <td>{{formatMoney(gross_profit_amt,0,"￥")}}</td>
                  <td>{{formatRatio(gross_profit_amt,apprDocInfo.sell_price,1)}}</td>
                </tr>
            </tbody>
            </table>
</template>

<script>
    export default {
      props: {

        //稟議書データ
        apprDocInfo : {
          type: Object,
          required: true,
          default: function () {
            return {};
          }

        }
      },//props end

      created() {

      },//created end

      data() {
        return {

        }
      },//data end

      methods: {

          formatMoney: function(number, places, symbol, thousand, decimal) {
          number = number || 0;
          places = !isNaN(places = Math.abs(places)) ? places : 2;
          symbol = symbol !== undefined ? symbol : "$";
          thousand = thousand || ",";
          decimal = decimal || ".";
          var negative = number < 0 ? "-" : "",
              i = _.toInteger(number = Math.abs(+number || 0).toFixed(places), 10) + "",
              j = (j = i.length) > 3 ? j % 3 : 0;
          return symbol + negative + (j ? i.substr(0, j) + thousand : "") + i.substr(j).replace(/(\d{3})(?=\d)/g, "$1" + thousand) + (places ? decimal + Math.abs(number - i).toFixed(places).slice(2) : "");
         },

        formatRatio : function (num1,num2,pos){
            var ratio = this.calRatio(num1,num2,pos);
            if (ratio == '') return '';
            return ratio + '%';
          },

        calRatio : function (num1,num2,pos){
            var p_num1 = _.toInteger(num1);
            var p_num2 = _.toInteger(num2);
            if (isNaN(p_num1) || isNaN(p_num2)){
              return '';
            }

            if (p_num2 === 0){ return '';}
            return this.$_.round((p_num1/p_num2)*100,pos);
          },

      },//methods end

      computed: {

          buying_in_cost : function (){
            return  _.toInteger(this.apprDocInfo.travel_cost) +
                    _.toInteger(this.apprDocInfo.insurance_cost) +
                    _.toInteger(this.apprDocInfo.other_cost);
          },

          buying_in_cost_last : function (){
            return  _.toInteger(this.apprDocInfoLast.travel_cost) +
                    _.toInteger(this.apprDocInfoLast.insurance_cost) +
                    _.toInteger(this.apprDocInfoLast.other_cost);
          },

          gross_profit_amt : function (){
              return  _.toInteger(this.apprDocInfo.sell_price) -
                      _.toInteger(this.apprDocInfo.cost_price) -
                      _.toInteger(this.buying_in_cost);
            },

          sell_price_first_mon : function () {
                var p_work_rate =  _.floor(_.toInteger(this.apprDocInfo.work_days) /
                        _.toInteger(this.apprDocInfo.business_days),2);
                return _.round(_.toInteger(this.apprDocInfo.sell_price) * p_work_rate ,0 )
          },

          cost_price_first_mon : function () {
              var p_work_rate =  _.floor(_.toInteger(this.apprDocInfo.work_days) /
                      _.toInteger(this.apprDocInfo.business_days),2);
              return _.round(_.toInteger(this.apprDocInfo.cost_price) * p_work_rate ,0 )
          },

          buying_in_cost_first_mon : function (){
            return  _.toInteger(this.apprDocInfo.travel_cost_first_mon) +
                  _.toInteger(this.apprDocInfo.other_cost_first_mon);
          },

          gross_profit_amt_first_mon : function (){
            return  _.toInteger(this.sell_price_first_mon) -
                    _.toInteger(this.cost_price_first_mon) -
                    _.toInteger(this.buying_in_cost_first_mon);
          },


      }//computed end

    };
</script>

<style scoped>
th{
  background-color:hsla(120,65%,75%,0.1);
}
</style>

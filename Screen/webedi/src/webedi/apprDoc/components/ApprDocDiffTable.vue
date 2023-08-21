<template>
        <table cellspacing="0"
        class="table table-bordered"
        style="height: 110px; width: 580px;"
        id="empTable">
            <thead>
                <tr>
                    <th colspan="3"></th>
                    <th>前回</th>
                    <th>今回</th>
                    <th>差異</th>
                    <th>増減</th>
                </tr>
            </thead>
            <tbody>
                <tr>
                    <th colspan="2" rowspan="2">単金</th>
                    <th>上</th>
                    <td><span v-if="!apprDocInfoLast.sell_price">-</span>
                        <span v-else>{{formatMoney(apprDocInfoLast.sell_price,0,"￥")}}</span>
                    </td>
                    <td>{{formatMoney(apprDocInfo.sell_price,0,"￥")}}</td>
                    <td>
                      <span v-if="!apprDocInfoLast.sell_price">-</span>
                      <span v-else-if="apprDocInfoLast.sell_price>apprDocInfo.sell_price"
                       class="minus">
                      {{formatMoney(apprDocInfo.sell_price - apprDocInfoLast.sell_price,0,"￥")}}
                    </span>
                      <span v-else>
                      {{formatMoney(apprDocInfo.sell_price - apprDocInfoLast.sell_price,0,"￥")}}
                    </span>
                    </td>
                    <td>
                        <span v-if="!apprDocInfoLast.sell_price">-</span>
                        <span v-else-if="apprDocInfoLast.sell_price<apprDocInfo.sell_price">↑</span>
                        <span class="minus" v-else-if="apprDocInfoLast.sell_price>apprDocInfo.sell_price">↓</span>
                        <span v-else-if="apprDocInfoLast.sell_price===apprDocInfo.sell_price">→</span>
                    </td>
                 </tr>
                <tr>
                   <th>下</th>
                    <td>
                        <span v-if="!apprDocInfoLast.sell_price">-</span>
                        <span v-else>{{formatMoney(apprDocInfoLast.cost_price,0,"￥")}}</span>
                    </td>
                    <td>{{formatMoney(apprDocInfo.cost_price,0,"￥")}}</td>
                    <td>
                        <span v-if="!apprDocInfoLast.sell_price">-</span>
                        <span v-else-if="apprDocInfoLast.cost_price>apprDocInfo.cost_price"
                         class="minus">
                        {{formatMoney(apprDocInfo.cost_price - apprDocInfoLast.cost_price,0,"￥")}}
                      </span>
                        <span v-else>
                        {{formatMoney(apprDocInfo.cost_price - apprDocInfoLast.cost_price,0,"￥")}}
                      </span>
                    </td>
                    <td>
                        <span v-if="!apprDocInfoLast.cost_price">-</span>
                        <span v-else-if="apprDocInfoLast.cost_price<apprDocInfo.cost_price">↑</span>
                        <span class="minus" v-else-if="apprDocInfoLast.cost_price>apprDocInfo.cost_price">↓</span>
                        <span v-else-if="apprDocInfoLast.cost_price===apprDocInfo.cost_price">→</span>
                    </td>
                </tr>
                
                <tr >
                    <th colspan="3">粗利金額</th>
                    <td>
                        <span v-if="!apprDocInfoLast.sell_price">-</span>
                        <span v-else>{{formatMoney(gross_profit_amt_last,0,"￥")}}</span>
                    </td>
                    <td>{{formatMoney(gross_profit_amt,0,"￥")}}</td>
                    <td>
                        <span v-if="!apprDocInfoLast.sell_price">-</span>
                        <span v-else-if="gross_profit_amt_last>gross_profit_amt"
                         class="minus">
                        {{formatMoney(gross_profit_amt - gross_profit_amt_last,0,"￥")}}
                      </span>
                        <span v-else>
                        {{formatMoney(gross_profit_amt - gross_profit_amt_last,0,"￥")}}
                      </span>
                    </td>
                    <td>
                        <span v-if="!apprDocInfoLast.cost_price">-</span>
                        <span v-else-if="gross_profit_amt_last<gross_profit_amt">↑</span>
                        <span class="minus" v-else-if="gross_profit_amt_last>gross_profit_amt">↓</span>
                        <span v-else-if="gross_profit_amt_last===gross_profit_amt">→</span>
                    </td>
                </tr>
                  <tr >
                    <th colspan="3">
                      粗利率
                      <el-tooltip
                      placement="bottom-end"
                      content=""
                      effect="light">
                        <div slot="content">
                        <ul>
                          <li> 5%以下の場合、　背景色は赤色表示</li>
                          <li> 30%以下の場合、　背景色は黄色表示</li>
                        </ul>
                         </div>
                         <i class="el-icon-information"></i>
                        </el-tooltip>
                    </th>
                    <td :class="gross_profit_ratio_last_class">
                        <span v-if="!apprDocInfoLast.sell_price">-</span>
                        <span v-else>{{formatRatio(gross_profit_amt_last,apprDocInfoLast.sell_price,1)}}</span>
                    </td>
                    <td :class="gross_profit_ratio_class">{{formatRatio(gross_profit_amt,apprDocInfo.sell_price,1)}}</td>
                    <td>-</td>
                    <td>-</td>
                </tr>
                
            </tbody>
        </table>
</template>

<script>
    export default {
      props: {
        //直前の稟議書データ
        apprDocInfoLast : {
          type: Object,
          required: true,
          default: function () {
            return {};
          }
        },
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

      data: () => {
        return {}
      },//data end

      methods: {
          //数値の差を求める
          minus(num1, num2){
            // if(!_.isNumber(num1) || _.isNumber(num2)) return '';
            return _.toInteger(num1) - _.toInteger(num2);
          },

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

          gross_profit_amt_last : function (){
            return _.toInteger(this.apprDocInfoLast.sell_price) -
                  _.toInteger(this.apprDocInfoLast.cost_price) -
                  _.toInteger(this.buying_in_cost_last);
            },

          gross_profit_ratio_last_class : function(){
            var ratio = this.calRatio(this.gross_profit_amt_last,
              this.apprDocInfoLast.sell_price,1);
            if(ratio == '') return '';
            if(ratio <= 5.0 ) return 'red';
            if(ratio <= 30.0 ) return 'yellow';
            return '';
            },

            gross_profit_ratio_class : function(){
              var ratio = this.calRatio(this.gross_profit_amt,
                this.apprDocInfo.sell_price,1);
              if(ratio == '') return '';
              if(ratio <= 5.0 ) return 'red';
              if(ratio <= 30.0 ) return 'yellow';
              return '';
            }

      }//computed end

    };
</script>

<style scoped>
span.money{
border-radius:1px;
width: 150px;
height: 50px;
font-size: 15px;
vertical-align: middle;
}

span.minus{
  color: red;
}

th{
  background-color:hsla(120,65%,75%,0.1);
}

td.yellow{
  background-color: yellow;
}

td.red{
  background-color: red;
}


</style>

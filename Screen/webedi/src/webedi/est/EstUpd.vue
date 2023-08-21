<template>
<div id="est_info">
  <el-form
  :inline="true"
  :rules="rules"
  :model="estInfo"
  ref="estInfo"
  label-width="120px">
    <el-row>
    <el-col :span="4">
      <h3>【見積】</h3>
    </el-col>
    </el-row>
    <br>
    <est-menu :est_hid="estInfo.est_hid"></est-menu>
    <br>
    <el-row>
    <el-col :span="8">
    <el-form-item
    label="見積NO"
    prop="est_no">
      {{estInfo.est_no}}
    </el-form-item>
    </el-col>
    <el-col :span="16">
        <el-form-item label="見積区分"
        prop="est_k">
          <el-select
          size="small"
          style="width:120px"
          v-model="estInfo.est_k"
          placeholder="見積区分" >
              <el-option
              v-for="item in est_k_list"
              :key="item.value"
              :label="item.label"
              :value="item.value">
              </el-option>
          </el-select>
         </el-form-item >
     </el-col>
    </el-row>
    <el-row>
    <el-col :span="8">
    <el-form-item
      size="small"
      prop="submit_dt"
      label="提出日">
        <el-date-picker
          size="small"
          type="date"
          style="width:130px"
          placeholder="YYYY-MM-DD"
          v-model="estInfo.submit_dt">
        </el-date-picker>
    </el-form-item>
    </el-col>
    <el-col :span="16">
    <el-form-item
    label="発注仕様書NO"
    prop="shipping_order_spec_no">
      <el-input
      size="small"
      :maxlength="10"
      type="text"
      style="width:120px;"
      v-model="estInfo.shipping_order_spec_no"
      auto-complete="on">
      </el-input>
    </el-form-item>
    </el-col>
    </el-row>
    <el-row>
    <el-col :span="10">
    <el-form-item
      label="見積金額(税抜)"
      prop="est_amt_without_tax">
       {{estInfo.est_amt_without_tax | formatAmt}}
              (税込: {{estInfo.est_amt_with_tax | formatAmt}})
    </el-form-item>
    </el-col>
    <el-col :span="14">
    <el-form-item
      required
      size="small"
      label="ｸﾗｲｱﾝﾄ名">
      {{estInfo.client_nm}}
    </el-form-item>
    </el-col>
     </el-row>
     <el-row>
      <el-col :span="8">
      <el-form-item
      required
      size="small"
      label="案件番号">
      {{estInfo.pj_cd}}
    </el-form-item>
    </el-col>
    <el-col :span="16">
    <el-form-item
      required
      size="small"
      label="案件名称">
      {{estInfo.pj_nm}}
    </el-form-item>
    </el-col>
     </el-row>
    <el-row>
      <el-col :span="8">
    <el-form-item
      required
      size="small"
      label="作業開始日">
      {{estInfo.work_start_ymd}}
    </el-form-item>
      </el-col>
    <el-col :span="16">
    <el-form-item
      required
      size="small"
      label="作業終了日">
      {{estInfo.work_end_ymd}}
    </el-form-item>
    </el-col>
    </el-row>
    <el-row>
    <el-col :span="8">
    <el-form-item
      required
      size="small"
      label="作業場所">
      {{estInfo.work_place}}
    </el-form-item>
    </el-col>
    <el-col :span="16">
    <el-form-item
      required
      size="small"
      label="作業内容">
      {{estInfo.work_content}}
    </el-form-item>
      </el-col>
    </el-row>


    <el-form-item
      size="small"
      label="注文内容">
      <el-input
        type="textarea"
        style="width:800px"
        :rows="5"
        placeholder="注文内容を入力ください。(500文字まで)"
        v-model="estInfo.order_content">
      </el-input>
    </el-form-item>
    <el-form-item
      size="small"
      label="特記事項">
      <el-input
        type="textarea"
        style="width:800px"
        :rows="5"
        placeholder="特記事項を入力ください。(500文字まで)"
        v-model="estInfo.special_affairs">
      </el-input>
    </el-form-item>
    <el-form-item
      size="small"
      label="お支払条件">
      <el-input
        type="textarea"
        style="width:800px"
        :rows="5"
        placeholder="お支払条件を入力ください。(500文字まで)"
        v-model="estInfo.payment_cond">
      </el-input>
    </el-form-item>
    <el-form-item
      size="small"
      label="備考">
      <el-input
        type="textarea"
        style="width:800px"
        :rows="5"
        placeholder="備考を入力ください。(500文字まで)"
        v-model="estInfo.remark">
      </el-input>
    </el-form-item>
    </el-form>
    <hr>
    <br>
    <est-dtl :list_data="estInfo.est_dtl"></est-dtl>
    <br>
  <el-col :span="10">
    <span id="save_button">
      <el-button
      type="success"
      @click="save">更新
    </el-button>
    </span>
  </el-col>
</div>
</template>

<script>
import { EstMenu } from '@/webedi/est/components';
import { EstDtl } from '@/webedi/est/components';
  export default {
      components: {
        'est-menu' : EstMenu,
        'est-dtl' : EstDtl
      },
  data(){
    //check

    //data
    return {
      estInfo: {
        est_no : '',                 // 見積NO
        use_flg : '',                 // 使用FLG:Y:使用N:削除
        est_k : '',                   // 見積区分　1：概算　2：確定
        shipping_order_spec_no : '',  // 発注仕様書NO
        submit_dt : new Date(),               // 提出日
        client_nm : '',               // クライアント名
        est_amt_without_tax : 0,     // 見積金額(税抜)
        work_start_ymd : '',          // 作業開始日
        work_end_ymd : '',            // 作業終了日
        pj_cd : '',                   // 案件番号
        pj_nm : '',                   // 案件名称
        work_content : '',            // 作業内容
        work_place : '',              // 作業場所
        order_content : '',           // 注文内容
        special_affairs : '',         // 特記事項
        payment_cond : '',            // お支払条件
        remark : '',                  // 備考

        est_dtl:[],                   // 見積明細データ
      },//estInfo end

    tax_rate: 8,

    //見積区分リスト
    est_k_list:[],

      //rules
      rules:{
        est_k:[
          {
            required: true,
            message: '入力必須です。',
            trigger: 'blur'
          }
        ],
        submit_dt:[
          {
            type: 'date',
            required: true,
            message: '入力必須です。',
            trigger: 'blur'
          }
        ],
        est_amt_without_tax:[
          {
            type : 'number',
            required: true,
            message: '数値を入力してください。',
            trigger: 'blur'
          },
        ],
        order_content:[
          {
            required: true,
            message: '入力必須です。',
            trigger: 'blur'
          },
        ],
        payment_cond:[
          {
            required: true,
            message: '入力必須です。',
            trigger: 'blur'
          }
        ],
      },//rules end
    };
  },//data end

  methods:{
    save(){
      var _this = this;
      //console.log(JSON.stringify(_this.estInfo));
      _this.$refs.estInfo.validate(
        (valid) => {
          if(valid){
            _this.$confirm('見積情報を更新してもよろしいでしょうか？','注意',{
                  confirmButtonText: '確定',
                  cancelButtonText: '中止',
                  type: 'warning'
            }).then(
              //Post start>>>
              () => {
                _this.$http.post('/api/est/update', _this.estInfo).then(
                  function(response){
                    var p_error_code = response.data.errorcode;
                    if(p_error_code === '200'){
                      _this.$message({
                        type: 'success',
                        message:'更新が成功しました！'
                      });
                      // _this.estInfo.est_no = response.data.data.est_no;
                      _this.estInfo.est_hid = response.data.data;
                      _this.goToDetail();
                    }else if(p_error_code === '500'){
                      _this.$message.$error('更新が失敗しました。[' + response.data.errormsg + ']');
                    }
                  }
                ).catch(function(x_error){
                  console.log(x_error);
                }
              );
              }
              //Post end <<<
            ).catch(
              () => {
                _this.$message({
                  type: 'info',
                  message: '更新が中止しました！'
                })
              }
            )
          } else {
              _this.$message.error("入力項目にエラーがあります！");
          }
        }
      )
    },//ent() end

    //照会画面へ遷移
    goToDetail(){
      this.$router.push({ path: 'EstDetail', query: { est_hid: this.estInfo.est_hid }});
    },

    loadEstDetailInfo() {
        var p_est_hid = this.$route.query.est_hid;
        if(this.$_.isUndefined(p_est_hid)) return;
        var _this = this;
        _this.$http.get('/api/est/selectForDetail', {
            params: {
              est_hid: p_est_hid
            }
          })
          .then(function(response) {
            var errorcode = response.data.errorcode;
            if (errorcode == "200") {
              _this.estInfo = response.data.data;
              _this.convertEstInfo(_this.estInfo);
            } else if (errorcode == "500") {
              _this.$message.error(response.data.errormsg);
            }
          })
          .catch(function(error) {
            console.log(error);
          });
      },

      convertEstInfo(estEnt) {
        //提出日
        estEnt.submit_dt=this.$utils.convertDate(estEnt.submit_dt);
      },

      formatAmt(row, column, cellValue){
            return this.$utils.formatMoney(cellValue);
      },

      getDropDownList() {
        var _this = this;
        _this.$http.get('/api/common/dropDownList', {
            params: {
              class_cd: 'EST_K'
            }
          })
          .then(function(response) {
            var errorcode = response.data.errorcode;
            if (errorcode == "200") {
              _this.est_k_list = response.data.data;

            } else if (errorcode == "500") {
              _this.$message.error(response.data.errormsg);
            }
          })
          .catch(function(error) {
            console.log(error);
          });
      },

      getTax(){
        this.tax_rate = 8;
      },

      initScreenValue(){
          this.getTax();
          this.getDropDownList();

      }


  },//methods end

  computed: {
   },//coumputed end;

  filters:{
  },

  created(){
    this.loadEstDetailInfo();
    this.initScreenValue();

  },//created end

};

</script>

<style scoped>
#est_info{
  width: 950px;
  margin-top: 25px;
  margin-left:30px;
}
h3 h4{
  margin-bottom: 30px;
}
#ent_button{
   margin-left:20px;
   margin-right: 15px;
}

  .transfer-footer {
    margin-left: 20px;
    padding: 6px 5px;
  }

span.item_label{
border-radius:1px;
width: 150px;
height: 50px;
font-size: 15px;
vertical-align: middle;
}

th{
  background-color:hsla(120,65%,75%,0.1);
}

div.client_erea{
  background-color:cornsilk;
}
</style>

<template>
<div id="est_info">
  <el-form
  :inline="true"
  :rules="rules"
  :model="orderInfo"
  ref="orderInfo"
  label-width="120px">
    <el-row>
    <el-col :span="4">
      <h3>【受注】</h3>
    </el-col>
    </el-row>
    <br>
    <order-menu :order_id="orderInfo.order_id"></order-menu>
    <br>
    <el-row>
    <el-col :span="8">
    <el-form-item
    label="受注NO"
    prop="order_no">
      {{orderInfo.order_no}}
    </el-form-item>
    </el-col>
    <el-col :span="16">
    <el-form-item
    label="見積NO"
    prop="est_no">
      {{orderInfo.est_no}}
    </el-form-item>
    </el-col>
    </el-row>
    <el-row>
    <el-col :span="24">
    <el-form-item
      label="受注金額(税抜)"
      prop="order_amt_without_tax">
      {{orderInfo.order_amt_without_tax|formatAmt}}
      (税込: {{orderInfo.order_amt_with_tax|formatAmt}})
    </el-form-item>
    </el-col>
    </el-row>
    <el-row>
    <el-col :span="24">
   <el-form-item
      size="small"
      prop="create_dt"
      label="作成日">
        {{orderInfo.create_dt}}
    </el-form-item>
    </el-col>
    </el-row>
    <el-row>
    <el-col :span="24">
    <el-form-item
      required
      size="small"
      label="ｸﾗｲｱﾝﾄ名">
      {{orderInfo.client_nm}}
    </el-form-item>
    </el-col>
    </el-row>
     <el-row>
      <el-col :span="8">
      <el-form-item
      required
      size="small"
      label="案件番号">
      {{orderInfo.pj_cd}}
    </el-form-item>
    </el-col>
    <el-col :span="16">
    <el-form-item
      required
      size="small"
      label="案件名称">
      {{orderInfo.pj_nm}}
    </el-form-item>
    </el-col>
     </el-row>
    <el-row>
      <el-col :span="8">
    <el-form-item
      required
      size="small"
      label="作業開始日">
      {{orderInfo.work_start_ymd}}
    </el-form-item>
      </el-col>
    <el-col :span="16">
    <el-form-item
      required
      size="small"
      label="作業終了日">
      {{orderInfo.work_end_ymd}}
    </el-form-item>
    </el-col>
    </el-row>
    <el-row>
    <el-col :span="8">
    <el-form-item
      required
      size="small"
      label="作業場所">
      {{orderInfo.work_place}}
    </el-form-item>
    </el-col>
    <el-col :span="16">
    <el-form-item
      required
      size="small"
      label="作業内容">
      {{orderInfo.work_content}}
    </el-form-item>
      </el-col>
    </el-row>
    <el-row>
      <el-col :span="24">
        <el-form-item label="注文書"
        prop="order_doc_file_id">
        <el-upload
          :multiple="false"
          ref="file"
          action="/api/file/upload"
          :onSuccess="uploadSuccess"
          :auto-upload="true">
          <el-button slot="trigger" size="small" type="primary">＋</el-button>
        </el-upload>
         </el-form-item >
      </el-col>
    </el-row>
    <div style=" background-color:cornsilk;">
    <h4>【確定情報】</h4>
    <el-row>
      <el-col :span="8">
      <el-form-item label="確定者">
        {{orderInfo.order_confirm_user_nm}}
      </el-form-item>
      </el-col>
      <el-col :span="16">
      <el-form-item label="確定日">
        {{orderInfo.order_confirm_dt}}
      </el-form-item>
      </el-col>
    </el-row>
    <el-row>
      <el-col :span="24">
      <el-form-item label="確定備考">
        {{orderInfo.order_confirm_remark}}
      </el-form-item>
      </el-col>
    </el-row>
    </div>
    <el-form-item
      required
      size="small"
      label="注文内容">
      <el-input
        type="textarea"
        style="width:800px"
        :rows="5"
        resize="none"
        :autosize="false"
        :disabled="true"
        placeholder=""
        v-model="orderInfo.order_content">
      </el-input>
    </el-form-item>
    <el-form-item
      required
      size="small"
      label="特記事項">
      <el-input
        type="textarea"
        style="width:800px"
        :rows="5"
        resize="none"
        :autosize="false"
        :disabled="true"
        placeholder=""
        v-model="orderInfo.special_affairs">
      </el-input>
    </el-form-item>
    <el-form-item
      required
      size="small"
      label="お支払条件">
      <el-input
        type="textarea"
        style="width:800px"
        :rows="5"
        resize="none"
        :autosize="false"
        :disabled="true"
        placeholder=""
        v-model="orderInfo.payment_cond">
      </el-input>
    </el-form-item>
    <el-form-item
      required
      size="small"
      label="備考">
      <el-input
        type="textarea"
        style="width:800px"
        :rows="5"
        resize="none"
        :autosize="false"
        :disabled="true"
        placeholder=""
        v-model="orderInfo.remark">
      </el-input>
    </el-form-item>
    </el-form>
    <hr>
    <br>
    <est-dtl :list_data="orderInfo.est_dtl"></est-dtl>
    <br>
  <el-col :span="10">
    <span id="upd_button">
      <el-button
      type="success"
      @click="upd">更新
    </el-button>
    </span>
  </el-col>
</div>
</template>

<script>
import { OrderMenu } from '@/webedi/order/components';
import { EstDtl } from '@/webedi/est/components';
  export default {
      components: {
        'order-menu' : OrderMenu,
        'est-dtl' : EstDtl
      },
  data(){
    //check

    //data
    return {
      orderInfo: {
        est_no : '',                 // 見積NO
        use_flg : '',                 // 使用FLG:Y:使用N:削除
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
        order_doc_file_id : -1,       // 注文書

        est_dtl:[],                   // 見積明細データ
      },//orderInfo end

    tax_rate: 8,

      //rules
      rules:{

        create_dt:[
          {
            type: 'date',
            required: true,
            message: '入力必須です。',
            trigger: 'blur'
          }
        ],

        order_amt_without_tax:[
          {
            type : 'number',
            required: true,
            message: '数値を入力してください。',
            trigger: 'blur'
          },
        ]
      },//rules end
    };
  },//data end

  methods:{

    //アップロード処理
    uploadSuccess(response, file, fileList) {
          this.orderInfo.order_doc_file_id = response.data.fileId;
      },

    upd(){
      var _this = this;
      //console.log(JSON.stringify(_this.orderInfo));
      _this.$refs.orderInfo.validate(
        (valid) => {
          if(valid){
            _this.$confirm('受注情報を更新してもよろしいでしょうか？','注意',{
                  confirmButtonText: '確定',
                  cancelButtonText: '中止',
                  type: 'warning'
            }).then(
              //Post start>>>
              () => {
                _this.$http.post('/api/order/update', _this.orderInfo).then(
                  function(response){
                    var p_error_code = response.data.errorcode;
                    if(p_error_code === '200'){
                      _this.$message({
                        type: 'success',
                        message:'更新が成功しました！'
                      });
                      // _this.orderInfo.est_no = response.data.data.est_no;
                      _this.orderInfo.order_id = response.data.data;
                      _this.goToDetail();
                    }else if(p_error_code === '500'){
                      _this.$message.error('更新が失敗しました。[' + response.data.errormsg + ']');
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
              this.$message.error("入力項目にエラーがあります！");
          }
        }
      )
    },//ent() end

    //照会画面へ遷移
    goToDetail(){
      this.$router.push({ path: 'OrderDetail', query: { order_id: this.orderInfo.order_id }});
    },

    loadOrderDetailInfo() {
        var p_order_id = this.$route.query.order_id;
        if(this.$_.isUndefined(p_order_id)) return;
        var _this = this;
        _this.$http.get('/api/order/selectForDetail', {
            params: {
              order_id: p_order_id
            }
          })
          .then(function(response) {
            var errorcode = response.data.errorcode;
            if (errorcode == "200") {
              _this.orderInfo = response.data.data;
               _this.convertOrderInfo(_this.orderInfo);
            } else if (errorcode == "500") {
              _this.$message.error(response.data.errormsg);
            }
          })
          .catch(function(error) {
            console.log(error);
          });
      },

      convertOrderInfo(orderEnt) {
        //作成日
        orderEnt.create_dt=this.$utils.convertDate(orderEnt.create_dt);
        orderEnt.work_start_ymd=this.$utils.convertDate(orderEnt.work_start_ymd);
        orderEnt.work_end_ymd=this.$utils.convertDate(orderEnt.work_end_ymd);
        orderEnt.order_confirm_dt=this.$utils.convertDate(orderEnt.order_confirm_dt);
      },

      formatAmt(row, column, cellValue){
            return this.$utils.formatMoney(cellValue);
      },

      getTax(){
        this.tax_rate = 8;
      }


  },//methods end

  computed: {
   },//coumputed end;

  created(){
    this.loadOrderDetailInfo();
    this.getTax();

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

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
    <est-menu :est_hid="estInfo.est_hid" :upd-menu-disable-f="estInfo.del_flg ==='Y'"></est-menu>
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
    <el-form-item
    label="見積区分"
    prop="est_k_nm">
      {{estInfo.est_k_nm}}
    </el-form-item>
    </el-col>
    </el-row>
    <el-row>
    <el-col :span="8">
    <el-form-item
    label="発注仕様書NO"
    prop="shipping_order_spec_no">
      {{estInfo.shipping_order_spec_no}}
    </el-form-item>
    </el-col>
    <el-col :span="16">
    <el-form-item
      size="small"
      prop="submit_dt"
      label="提出日">
        {{estInfo.submit_dt}}
    </el-form-item>
    </el-col>
    </el-row>
    <el-row>
    <el-col :span="8">
    <el-form-item
    label="発行日"
    prop="issue_dt">
      {{estInfo.issue_dt}}
    </el-form-item>
    </el-col>
    <el-col :span="16">
    <el-form-item
      size="small"
      prop="submit_dt"
      label="送付日">
        {{estInfo.delivery_dt}}
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
        v-model="estInfo.order_content">
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
        v-model="estInfo.special_affairs">
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
        v-model="estInfo.payment_cond">
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
        v-model="estInfo.remark">
      </el-input>
    </el-form-item>
    </el-form>
    <hr>
    <br>
    <est-dtl :list_data="estInfo.est_dtl"></est-dtl>
    <br>
    <el-col :span="24">
      <span id="apply_button">
        <el-button
              type="danger"
              :disabled="estInfo.del_flg ==='Y'"
              @click="delEstData">削除
        </el-button>
      </span>
      <span id="apply_button">
        <el-button
              type="info"
              icon="document"
              @click="issueEstDoc">見積書発行
        </el-button>
      </span>
      <span id="apply_button">
        <el-button
              type="info"
              icon="document"
              @click="deliveryEstDoc">見積書送付
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
      //rules
      rules:{ },//rules end
    };
  },//data end

  methods:{
      //削除処理
      delEstData(){
        var p_est_hid = this.estInfo.est_hid;
        var p_est_no = this.estInfo.est_no;
        var compiled = this.$_.template('見積データ(見積NO：<%= x_est_no %>)を削除してもよろしいでしょうか？');
        var confirmMsg = compiled({ 'x_est_no':  p_est_no});
        this.$confirm(confirmMsg, '注意', {
          confirmButtonText: '確定',
          cancelButtonText: '中止',
          type: 'warning'
        }).then(() => {
        	var _this = this;
        	   _this.$http.delete('/api/est/delete',
				                        {params:{"est_hid":p_est_hid}})
        	      .then(function (response) {
                    var errorcode=response.data.errorcode;
                      if(errorcode=="200"){
                      _this.goToList();
				          _this.$message({
				            type: 'success',
				            message: '削除が成功しました!'
				          });
                      }else if(errorcode=="500"){
				          _this.$message({
				            type: 'error',
				            message: '削除が失敗しました!['+response.data.errormsg+']'
				          });
                      }
                    })
                  .catch(function (error) {
                      console.log(error);
                  });
        }).catch(() => {
          this.$message({
            type: 'info',
            message: '削除が中止しました！'
          });
        });
      },

      //見積書発行
      issueEstDoc(){
        var p_est_hid = this.estInfo.est_hid;
        var p_est_no = this.estInfo.est_no;
        var compiled = this.$_.template('見積書(見積NO：<%= x_est_no %>)を発行しますか？');
        var confirmMsg = compiled({ 'x_est_no':  p_est_no});
        this.$confirm(confirmMsg, '注意', {
          confirmButtonText: '確定',
          cancelButtonText: '中止',
          type: 'warning'
        }).then(() => {
        	var _this = this;
        	   _this.$http({
                          method: 'GET',
                          url: '/api/est/issueEstDoc',
                          params:{"est_hid":p_est_hid},
                          responseType:'blob'
                             })
        	    .then(function (response) {
                      _this.download(response.data);
                      _this.loadEstDetailInfo();
                      _this.$message({
                        type: 'success',
                        message: '発行が成功しました!'
                      });
                    })
                  .catch(function (error) {
                      console.log(error);
                  });

        }).catch(() => {
          this.$message({
            type: 'info',
            message: '発行が中止しました！'
          });
        });
      },


        download (data) {
        if (!data) {
            return
        }
        let url = window.URL.createObjectURL(new Blob([data]))
        let link = document.createElement('a')
        link.style.display = 'none'
        link.href = url
        link.setAttribute('download', '【TSRS】見積書.xls')

        document.body.appendChild(link)
        link.click()
      },


      //見積書送付
      deliveryEstDoc(){
        var p_est_hid = this.estInfo.est_hid;
        var p_est_no = this.estInfo.est_no;
        var compiled = this.$_.template('見積書(見積NO：<%= x_est_no %>)を送付しますか？');
        var confirmMsg = compiled({ 'x_est_no':  p_est_no});
        this.$confirm(confirmMsg, '注意', {
          confirmButtonText: '確定',
          cancelButtonText: '中止',
          type: 'warning'
        }).then(() => {
        	var _this = this;
        	   _this.$http.get('/api/est/deliveryEstDoc',
				                        {params:{"est_hid":p_est_hid}})
        	      .then(function (response) {
                    var errorcode=response.data.errorcode;
                      if(errorcode=="200"){
                      _this.loadEstDetailInfo();
				          _this.$message({
				            type: 'success',
				            message: '送付が成功しました!'
				          });
                      }else if(errorcode=="500"){
				          _this.$message({
				            type: 'error',
				            message: '送付が失敗しました!['+response.data.errormsg+']'
				          });
                      }
                    })
                  .catch(function (error) {
                      console.log(error);
                  });

        }).catch(() => {
          this.$message({
            type: 'info',
            message: '送付が中止しました！'
          });
        });
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
            } else if (errorcode == "500") {
              _this.$message.error(response.data.errormsg);
            }
          })
          .catch(function(error) {
            console.log(error);
          });
      },

      formatAmt(row, column, cellValue){
            return this.$utils.formatMoney(cellValue);
      },

      goToList() {
        this.$router.push({
          path: 'EstInq'
        });
      },

      getTax(){
        this.tax_rate = 8;
      }


  },//methods end

  computed: {
   },//coumputed end;
  created(){
    this.loadEstDetailInfo();
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

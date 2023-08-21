<template>
  <div id="EstInq">
    <el-form
      class="EstInqForm"
      :inline="true"
      ref="orderConfirmInfo"
      :model="filters">
      <el-row>
        <el-form-item
        prop="confirm_flg"
        label="状態">
        <el-radio-group v-model="filters.confirm_flg" @change="searchData">
        <el-radio-button label="1">確定待</el-radio-button>
        <el-radio-button label="2">確定済</el-radio-button>
        </el-radio-group>
        </el-form-item>
        <el-form-item
           label="受注NO"
           prop="order_no">
            <el-input
             size="small"
              type="text"
              style="width:150px"
              v-model="filters.order_no"
              placeholder="前方一致">
            </el-input>
          </el-form-item>
        <el-form-item
           label="見積NO"
           prop="est_no">
            <el-input
             size="small"
              type="text"
              style="width:150px"
              v-model="filters.est_no"
              placeholder="前方一致">
            </el-input>
          </el-form-item>
        <el-form-item
           label="クライアント名称"
           label-width="130px"
           prop="client_nm">
            <el-input
             size="small"
              type="text"
              style="width:150px"
              v-model="filters.client_nm"
              placeholder="部分一致">
            </el-input>
          </el-form-item>
        <el-form-item
           label="案件名称"
           label-width="100px"
           prop="pj_nm">
            <el-input
             size="small"
              type="text"
              style="width:150px"
              v-model="filters.pj_nm"
              placeholder="部分一致">
            </el-input>
          </el-form-item>
        <el-form-item
           label="要員氏名"
           label-width="100px"
           prop="emp_nm">
            <el-input
             size="small"
              type="text"
              style="width:150px"
              v-model="filters.emp_nm"
              placeholder="部分一致">
            </el-input>
          </el-form-item>
      </el-row>
      <el-row>
         <el-form-item
          label="稟議書NO"
          prop="appr_doc_no" >
          <el-input
          size="small"
          style="width:100px"
          v-model="filters.appr_doc_no"
          placeholder="前方一致">
        　</el-input>
          </el-form-item>
        <el-form-item
         label="稼動月"
         label-width="60px"
         prod="work_mon">
           <el-date-picker
           size="small"
           type="month"
           style="width:100px"
           placeholder="YYYY-MM"
           v-model="filters.work_mon">
           </el-date-picker>
        </el-form-item>
        <el-form-item
          label="営業主担当"
          label-width="100px"
          prop="business_main_user_id">
          <el-select
          size="small"
          clearable
          style="width:120px"
          v-model="filters.business_main_user_id"
          placeholder="営業主担当" >
             <el-option
                v-for="item in business_user_id_list"
                :key="item.value"
                :label="item.label"
                :value="item.value">
                </el-option>
          </el-select>
          </el-form-item >
      </el-row>
      <el-row>
      <el-col :span="6" >
      <el-form-item
      label-width="80px"
      label="改頁件数"
      prop="size">
      <el-select
      size="small"
      v-model="page_info.size"
      style="width:100px"
      @change="handleChgPageSize"
      placeholder="改頁件数" >
        <el-option
        v-for="item in pageSize_list"
        :key="item"
        :label="item+'件'"
        :value="item">
        </el-option>
      </el-select>
      </el-form-item >
      <el-button type="success" v-if="filters.confirm_flg==='1'" @click="confirm">
        確定登録
      </el-button>
      <el-button type="success" v-if="filters.confirm_flg==='2'" @click="confirm">
        確定取消
      </el-button>
      </el-col>
      <el-col :span="16" >
      &nbsp;
      </el-col>
      <el-col :span="2" >
      <el-form-item>
          <el-button type="primary" @click="searchData" icon="search">検索</el-button>
       </el-form-item>
      </el-col>
    </el-row>
     <el-table :data="page_info.records"
      height="400"
      border
      stripe>
      <el-table-column fixed width="60" label="NO">
      <template slot-scope="scope">
      {{(scope.$index +  1)+ (page_info.current - 1) *page_info.size}}
      </template>
      </el-table-column>
      <el-table-column
      fixed
      width="60"
      label="選択" >
        <template slot-scope="scope">
        <el-checkbox v-model="scope.row.sel">
        </el-checkbox>
      </template>
      </el-table-column>

        <el-table-column
        sortable
        fixed
        prop="order_no"
        label="受注NO"
        width="120"/>
        <el-table-column
        sortable
        prop="est_no"
        label="見積NO"
        width="130"/>
        <el-table-column
        sortable
        prop="client_nm"
        label="ｸﾗｲｱﾝﾄ名称"
        width="300"/>
        <el-table-column
        sortable
        prop="pj_cd"
        label="案件ID"
        width="100" />
        <el-table-column
        sortable
        prop="pj_nm"
        label="案件名"
        width="350" />
        <el-table-column
        sortable
        prop="work_content"
        label="作業内容"
        width="300" />
        <el-table-column
        sortable
        prop="work_place"
        label="作業場所"
        width="150" />
        <el-table-column
        sortable
        prop="create_dt"
        label="作成日"
        width="120"/>
       <el-table-column
        sortable
        label="稼動日"
        width="130">
        <template slot-scope="scope">
        始:{{scope.row.work_start_ymd}}
        <br>
        終:{{scope.row.work_end_ymd}}
       </template>
        </el-table-column>
        <el-table-column
        label="受注金額(税抜)"
        :formatter="formatAmt"
        prop="order_amt_without_tax"
        width="140"/>

        <el-table-column
        fixed="right"
        width="200"
        label="備考">
        <template slot-scope="scope">
        <template v-if="filters.confirm_flg==='1'">
        <el-form-item
            prop=""
            :rules="[
                    { required: true, message: '入力必須です', trigger: 'blur' },
                    ]">
          <el-input
           v-model="scope.row.order_confirm_remark"
           :maxlength="100"
           placeholder="コメント">
          </el-input>
        </el-form-item>
        </template>
        <template v-if="filters.confirm_flg==='2'">
          {{scope.row.order_confirm_remark}}
        </template>
        </template>
        </el-table-column>
    </el-table>
  <el-pagination
    layout="prev, pager, next"
    :current-page="page_info.current"
    :total="page_info.total"
    :page-size="page_info.size"
     @current-change="handleCurrentChange">
   </el-pagination>
</el-form>
  </div>
</template>

<script>
  export default {
    data(){
      return {

        filters:{
          confirm_flg: '1', //承認状態　１:未承認　２：承認済
          order_no: '',
          est_no: '',
          client_nm: '',
          pj_nm: '',
          emp_nm: '',
          appr_doc_no: '',
          work_mon: '',      //稼働月
          business_main_user_id: ''                       //営業主担当
        },//filters end

        pageSize_list:[],

        page_info:{
          current:1,
          size:10,
          total:20,
          records:[],
        },//page_info end

        result_list:[],
        business_user_id_list:[]
      }
    },//data() end

    methods:{

      //
      handleCurrentChange(val) {
        this.page_info.current = val;
        this.getList();
      },
      //改頁件数を選択
      handleChgPageSize(val) {
        this.getList();
      },
     searchData() {
        this.page_info.current = 1;
        this.getList();
      },

   getPageSizeList(){
    this.pageSize_list = [ 5, 10, 20, 50, 100 ];
   },

   getList(){
 		      var _this = this;
          this.$http.post('/api/order/searchForDetail'
            ,{
                current:_this.page_info.current,
                size:_this.page_info.size,
                confirm_flg:_this.filters.confirm_flg,
                order_no:_this.filters.order_no,
                est_no:_this.filters.est_no,
                client_nm: _this.filters.client_nm,
                pj_nm: _this.filters.pj_nm,
                emp_nm: _this.filters.emp_nm,
                appr_doc_no: _this.filters.appr_doc_no,
                work_mon: this.$utils.formatDate(_this.filters.work_mon,"YYYY-MM"),   //稼働月
                business_main_user_id: _this.filters.business_main_user_id,
              }
            )
            .then(function (response) {
              var errorcode=response.data.errorcode;
                 if(errorcode=="200"){
                    _this.page_info=response.data.data;
                }else if(errorcode=="500"){
                   _this.$message.error(response.data.errormsg);
                }
              })
            .catch(function (error) {
                console.log(error);
            });
   },

  //  confirm(){
  //         var _this = this;
  //         var ret = _this.checkConfirm();
  //         if(ret.valid === false) return;
  //         var p_order_confirm_list = ret.order_confirm_list;
  //         var p_url = '';
  //         if(_this.filters.confirm_flg ==='1'){
  //           p_url = '/api/order/confirmOrder';
  //         }else{
  //           p_url = '/api/order/cancelConfirmOrder';
  //         };

  //         _this.$http.post(p_url,
  //              {'est_hid':p_order_confirm_list}
  //           )
  //           .then(function (response) {
  //             var errorcode=response.data.errorcode;
  //                if(errorcode=="200"){
  //                      _this.$message({
  //                       type: 'success',
  //                       message:'成功しました！'
  //                     });

  //               }else if(errorcode=="500"){
  //                  _this.$message.error(response.data.errormsg);
  //               }
  //             })
  //           .catch(function (error) {
  //               console.log(error);
  //           });
  //  },

    confirm(){
        var _this = this;
          var ret = _this.checkConfirm();
          if(ret.valid === false) return;
          var p_order_confirm_list = ret.order_confirm_list;
          var p_url = '';
          var p_op_str = '';
          if(_this.filters.confirm_flg ==='1'){
            p_url = '/api/order/confirmOrder';
            p_op_str = "確定登録";
          }else{
            p_url = '/api/order/cancelConfirmOrder';
            p_op_str = "確定取消";
          }
      _this.$refs.orderConfirmInfo.validate(
        (valid) => {
          if(valid){
            _this.$confirm('見積情報を'+p_op_str+'してもよろしいでしょうか？','注意',{
                  confirmButtonText: '確定',
                  cancelButtonText: '中止',
                  type: 'warning'
            }).then(
              //Post start>>>
              () => {
                console.log(JSON.stringify(p_order_confirm_list));
                _this.$http({
                  url:p_url,
                  method:`post`,
                  headers: {'content-type': 'application/json; charset=UTF-8'},
                  data:JSON.stringify(p_order_confirm_list)
                }).then(
                  function(response){
                    var p_error_code = response.data.errorcode;
                    if(p_error_code === '200'){
                      _this.$message({
                        type: 'success',
                        message:p_op_str+'が成功しました！'
                      });
                      _this.getList();
                    }else if(p_error_code === '500'){
                      _this.$message.error(p_op_str+'が失敗しました。[' + response.data.errormsg + ']');
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
                  message: p_op_str+'が中止しました！'
                })
              }
            )
          } else {
              this.$message.error("入力項目にエラーがあります！");
          }
        }
      )
    },//ent() end



    checkConfirm(){
        var ret={'order_confirm_list':[],valid:HTMLSourceElement};
        // var p_order_confirm_list =[];
        var sel_cnt = 0;
        for(let i=0; i< this.page_info.records.length; ++i){
          const element = this.page_info.records[i];
            if(element.sel === true){
              if(this.$_.isUndefined(element.order_confirm_remark) ||
                 element.order_confirm_remark === '' ){
                ret.valid = false;
                this.$message.error('備考入力してください。');
                return ret;
              }
              ret.order_confirm_list.push({
                'order_id': element.order_id,
                'order_confirm_remark': element.order_confirm_remark,
              })
            }
        }

        if(ret.order_confirm_list.length === 0){
          ret.valid = false;
          this.$message.error('データは一件以上を選択してください');
          return ret;
        }
        return ret;
    },

   	  //営業担当
      loadBusinessUserList() {
        var _this = this;
        _this.$http({
          method:'get',
          url:'/api/user/jobUserList',
          params:{jobTitleCd:'001'}
        }).then(function (response) {
          var errorcode=response.data.errorcode;
            if(errorcode=="200"){
            _this.business_user_id_list =  response.data.data;
          }else if(errorcode=="500"){
            _this.business_user_id_list = [];
            _this.errormsg(response.data.errormsg);
          }
          })
        .catch(function (error) {
          _this.business_user_id_list = [];
          console.log(error);
        });

      },

      formatAmt(row, column, cellValue){
            return this.$utils.formatMoney(cellValue);
      },

    },//methods end

    mounted(){
      this.getPageSizeList();
      this.getList();
      this.loadBusinessUserList();
    }//mounted end

  }
</script>

<style scoped>
#EstInq{
  width: 90%;
  margin-top: 25px;
  margin-left:30px;
}

.EstInqTopForm{
  margin-top: 12px;
  margin-left: 10px;
}
#EstInqMiddle{
  margin-top: 10px;
}
</style>

<template>
  <div id="EstEntInq">
    <el-form
      class="EstEntInqForm"
      :inline="true"
      :model="filters">
      <el-row>
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
      <el-button type="success" @click="goToEnt">登録</el-button>
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
    </el-form>

     <el-table :data="page_info.records"
      height="400"
      border
      stripe
      id="ApprDocInq_middle">
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
        prop="appr_doc_no"
        label="稟議書NO"
        width="130"/>
       <el-table-column
        sortable
        prop="client_no"
        label="ｸﾗｲｱﾝﾄID"
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
        prop="emp_no"
        label="社員ｺｰﾄﾞ"
        width="120"/>
        <el-table-column
        sortable
        prop="emp_nm"
        label="氏名"
        width="150"/>
        <el-table-column
        sortable
        prop="appr_doc_create_dt"
        label="作成日"
        width="120"/>
        <el-table-column
        sortable
        label="稼動日"
        prop="appr_doc_dt_from"
        width="130">
        <template slot-scope="scope">
        始:{{scope.row.appr_doc_dt_from}}
        <br>
        終:{{scope.row.appr_doc_dt_to}}
       </template>
        </el-table-column>
        <el-table-column
        sortable
        label="基準単価"
        :formatter="formatAmt"
        prop="sell_price"
        width="130"/>
        <el-table-column
        sortable
        label="支払ｻｲﾄ"
        prop="client_payment"
        width="250"/>
    </el-table>
  <el-pagination
    layout="prev, pager, next"
    :current-page="page_info.current"
    :total="page_info.total"
    :page-size="page_info.size"
     @current-change="handleCurrentChange">
   </el-pagination>

  </div>
</template>

<script>
  export default {
    data(){
      return {

        filters:{
          client_nm: '',
          pj_nm: '',
          emp_nm: '',
          appr_doc_no: '',
          work_mon: this.$moment().add(1,'months').format("YYYY-MM"),      //稼働月
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

      //登録画面へ遷移
       goToEnt(){
        var ret = this.checkEnt();
        if(ret.valid === false) return;
        this.$router.push({ path: 'EstEnt', query: { appr_doc_id_list: ret.appr_doc_id_list }});
      },

     isMergeable(firstVal, secVal){
      var field_list=['client_no','client_nm',
      'pj_cd','pj_nm',
      'work_content','work_place',
      'appr_doc_dt_from','appr_doc_dt_to',
      'client_payment'];
      return field_list.every( (element,index) =>{
         return firstVal[element] === secVal[element] ? true:false;
       });

    },

    //登録画面へ遷移する前のチェック
    checkEnt(){
        var ret={'appr_doc_id_list':[],valid:true};
        var p_row_data_old = {};
        var sel_cnt = 0;
        this.page_info.records.forEach(element => {
            if(element.sel ===true){
                sel_cnt = sel_cnt + 1;
                if(this.$_.isUndefined(p_row_data_old.appr_doc_id)){
                  ret.appr_doc_id_list = element.appr_doc_id;
                  p_row_data_old = element;
                }else{
                   ret.appr_doc_id_list = ret.appr_doc_id_list + '#' + element.appr_doc_id;
                  if(!this.$_.isEqualWith(p_row_data_old,element,this.isMergeable)){
                      ret.valid = false;
                      this.$message.error('合併できる稟議書データを選択してください');
                      return ret;
                  }
                  p_row_data_old = element;
                }
            }
        });

        if(ret.appr_doc_id_list.length === 0){
          ret.valid = false;
          this.$message.error('データは一件以上を選択してください');
          return ret;
        }

        if(sel_cnt > 4){
          ret.valid = false;
          this.$message.error('データは四件以下を選択してください');
          return ret;
        }

        return ret;
    },

   getPageSizeList(){
    this.pageSize_list = [ 5, 10, 20, 50, 100 ];
   },

   getList(){
 		      var _this = this;
          this.$http.post('/api/est/searchForEntry'
            ,{
                current:_this.page_info.current,
                size:_this.page_info.size,
                client_nm: _this.filters.client_nm,
                pj_nm: _this.filters.pj_nm,
                emp_nm: _this.filters.emp_nm,
                appr_doc_no: _this.filters.appr_doc_no,
                work_mon: this.$utils.formatDate(_this.filters.work_mon,"YYYY-MM"),   //稼働月
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
#EstEntInq{
  width: 90%;
  margin-top: 25px;
  margin-left:30px;
}

.EstEntInqTopForm{
  margin-top: 12px;
  margin-left: 10px;
}
#EstEntInqMiddle{
  margin-top: 10px;
}
</style>

<template>
  <div id="EstInq">
    <el-form
      class="EstInqForm"
      :inline="true"
      :model="filters">
      <el-row>
        <el-form-item
           label="見積NO"
           prop="client_nm">
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
      stripe>
      <el-table-column fixed width="60" label="NO">
      <template slot-scope="scope">
      {{(scope.$index +  1)+ (page_info.current - 1) *page_info.size}}
      </template>
      </el-table-column>
        <el-table-column
        sortable
        fixed
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
        prop="submit_dt"
        label="提出日"
        width="120"/>
        <el-table-column
        label="稼動日"
        width="130">
        <template slot-scope="scope">
        始:{{scope.row.work_start_ymd}}
        <br>
        終:{{scope.row.work_end_ymd}}
       </template>
        </el-table-column>
       <el-table-column
        label="見積書発行日見積書送付日"
        width="130">
        <template slot-scope="scope">
        <template v-if="scope.row.issue_dt || '' !== ''">
        発:{{scope.row.issue_dt}}
        <br>
        </template>
        <template v-if="scope.row.delivery_dt || '' !== ''">
        送:{{scope.row.delivery_dt}}
         </template>
       </template>
        </el-table-column>
        <el-table-column
        label="見積金額(税抜)"
        :formatter="formatAmt"
        prop="est_amt_without_tax"
        width="140"/>
        <el-table-column
        fixed="right"
        width="100"
        label="操作">
        <template slot-scope="scope">
        <el-button type="primary"
        @click="goToEntry(scope.$index,scope.row)" size="mini">登録へ</el-button>
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

  </div>
</template>

<script>
  export default {
    data(){
      return {

        filters:{
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

      //登録画面へ遷移
       goToEntry(index , row){
        this.$router.push({ path: 'OrderEnt', query: { est_hid: row.est_hid }});
      },

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
          this.$http.post('/api/order/searchForEntry'
            ,{
                current:_this.page_info.current,
                size:_this.page_info.size,
                est_no:_this.filters.est_no,
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

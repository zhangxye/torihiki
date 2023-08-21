<template>
  <div id="ApprDocInq">
      <div class="ApprDocInq_top">
          <el-form
          class="ApprDocInq_top_form"
          :inline="true"
          label-width="80px"
          :model="filters">

          <el-row>
          <el-col :span="24" >
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
             label="氏名"
             label-width="60px"
              prop="emp_nm">
                <el-input
                size="small"
                style="width:100px"
                v-model="filters.emp_nm"
                placeholder="部分一致">
              　</el-input>
            </el-form-item >
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
        label="部署"
        label-width="60px"
        prop="dept_cd">
          <el-select
          clearable
          size="small"
          style="width:120px"
          v-model="filters.dept_cd"
          placeholder="" >
             <el-option
				v-for="item1 in optionsdept"
				:label="item1.label"
				:value="item1.value"
				:key="item1.value">
			</el-option>
          </el-select>
         </el-form-item >

          </el-col>
          </el-row>

        <el-row>
        <el-col :span="24" >
          <el-form-item
          label-width="60px"
          label="作成日">
                <el-form-item prop="appr_doc_create_dt_from">
                  <el-date-picker
                  size="small"
                  type="date"
                  style="width:120px"
                  placeholder="日付From"
                  v-model="filters.appr_doc_create_dt_from">
                  </el-date-picker>
                </el-form-item>
                -
                <el-form-item prop="appr_doc_create_dt_to">
                  <el-date-picker
                   size="small"
                   type="date"
                   style="width:120px"
                   placeholder="日付To"
                   v-model="filters.appr_doc_create_dt_to">
                  </el-date-picker>
                </el-form-item>
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
          clearable
          size="small"
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
           <el-form-item
            label="状態"
            label-width="60px"
            prop="dept_cd">
              <el-select
              clearable
              size="small"
              style="width:120px"
              v-model="filters.appr_doc_st"
              placeholder="" >
                 <el-option
            v-for="item1 in appr_doc_st_list"
            :label="item1.label"
            :value="item1.value"
            :key="item1.value">
          </el-option>
          </el-select>
         </el-form-item >
        </el-col>
        </el-row>

      <el-row>
      <el-col :span="6" >
      <el-form-item
      label-width="80px"
      label="改頁件数"
      prop="pageSize">
      <el-select
      size="small"
      v-model="pageinfo.pageSize"
      style="width:100px"
      @change="handleChgPageSize"
      placeholder="改頁件数" >
        <el-option
        v-for="item in page_size_list"
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
     </div>
     <el-table :data="pageinfo.pageData"
      height="400"
      border
      stripe
      id="ApprDocInq_middle">
    <el-table-column fixed width="60" label="NO">
    <template slot-scope="scope">
    {{(scope.$index +  1)+ (pageinfo.pageNo - 1) *pageinfo.pageSize}}
    </template>
    </el-table-column>
        <el-table-column
        fixed
        sortable
        prop="appr_doc_st"
        label="状態"
        width="100"/>
        <el-table-column
        fixed
        sortable
        prop="appr_doc_create_k_nm"
        label="作成区分"
        width="120"/>
        <el-table-column
        sortable
        prop="appr_doc_no"
        label="稟議書NO"
        width="130"/>
        <el-table-column
        sortable
        prop="appr_doc_create_dt"
        label="作成日"
        width="120"/>
        <el-table-column
        sortable
        label="稼動開始日"
        prop="appr_doc_dt_from"
        width="160"/>
        <el-table-column
        sortable
        label="稼動終了日"
        prop="appr_doc_dt_to"
        width="160"/>
        <el-table-column
        sortable
        prop="emp_no"
        label="社員コード"
        width="130"/>
        <el-table-column
        sortable
        prop="emp_nm"
        label="氏名"
        width="180"/>
        <el-table-column
        sortable
        prop="client_no"
        label="クライアントID"
        width="160"/>
        <el-table-column
        sortable
        prop="client_nm"
        label="クライアント名称"
        width="250"/>
        <el-table-column
        sortable
        prop="pj_cd"
        label="案件ID"
        width="120" />
        <el-table-column
        sortable
        prop="pj_nm"
        label="案件名"
        width="250" />
        <el-table-column
        fixed="right"
        width="160"
        label="操作" >
        <template slot-scope="scope">
        <el-button type="info"
        @click="goToDetail(scope.$index,scope.row)" size="mini" icon="view">詳細</el-button>
        <el-button type="danger"
        @click="delApprDoc(scope.$index,scope.row)" size="mini" :disabled="!scope.row.del_flg" icon="delete">削除</el-button>
        </template>
    </el-table-column>
    </el-table>
  <el-pagination
    layout="prev, pager, next"
    :current-page="pageinfo.pageNo"
    :total="pageinfo.total"
    :page-size="pageinfo.pageSize"
     @current-change="handleCurrentChange">
   </el-pagination>

  </div>
</template>

<script>
export default {
  data(){
    return{
         filters: {
          client_nm:'',
          appr_doc_no: '',
          appr_doc_create_dt_from: '',
          appr_doc_create_dt_to: '',
          work_mon:this.$moment().format("YYYY-MM"),
          emp_nm: '',
          business_main_user_id : '',
          dept_cd: '',
          appr_doc_st:''
        },
        page_size_list:[],
        pageinfo:{
          pageNo:1,
          pageSize:10,
          total:20,
          pageData:[]
        },
		optionsdept:[],
        resultList:[],
        business_user_id_list: [],
         appr_doc_st_list : [
       {
          value : '1',
          label : '申請待',
          comment : 'データ登録直後'
        },
        {
          value : '2',
          label : '承認待',
          comment : 'データ承認申請後'
        },
         {
          value : '3',
          label : '承認中',
          comment : '承認WFがスタート後'
        },
         {
          value : '4',
          label : '承認済',
          comment : '承認経路の承認者がすべて承認済'
        },
         {
          value : '5',
          label : '契約済',
          comment : '受注先と合意済後'
        },
         {
          value : 'A',
          label : '取消',
          comment : 'ユーザー取消（画面上で検索可能）'
        }
         ],
    }
  },//data end

  methods:{

      //点击页数
      handleCurrentChange(val) {
        this.pageinfo.pageNo = val;
        this.getList();
      },
      //改頁件数を選択
      handleChgPageSize(val) {
        this.getList();
      },
     searchData() {
        this.pageinfo.pageNo = 1;
        this.getList();
      },
      //照会画面へ遷移
       goToDetail(index , row){
        this.$router.push({ path: 'ApprDocDetail', query: { appr_doc_no: row.appr_doc_no }});
      },

      delApprDoc(index , row){
        var compiled = this.$_.template('稟議書データ(稟議書NO：<%= appr_doc_no %>)を削除してもよろしいでしょうか？');
        var confirmMsg = compiled({ 'appr_doc_no':  row.appr_doc_no});
        this.$confirm(confirmMsg, '注意', {
          confirmButtonText: '確定',
          cancelButtonText: '中止',
          type: 'warning'
        }).then(() => {
        	var _this = this;
        	   _this.$http.delete('/api/apprDoc/delete',
				 {params:{"apprDocNo":row.appr_doc_no}})
        	   .then(function (response) {
                    var errorcode=response.data.errorcode;
                      if(errorcode=="200"){
                          _this.pageinfo.pageData.splice(index,1);
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
        // _.remove(this.pageData,function(item) {
        //             return item.appr_doc_no == row.appr_doc_no;
        // });

      },

   getPageSizeList(){
    this.page_size_list = [ 5, 10, 20, 50, 100 ];
   },

   getList(){
    //var startLine = (this.pageinfo.pageNo - 1) *this.pageinfo.pageSize;
    //var endLine = startLine + this.pageinfo.pageSize
    //this.pageinfo.pageData = this.$_.slice(this.resultList,startLine,endLine);
 		var _this = this;
            this.$http.get('/api/apprDoc/selectApprDoc'
            ,{
              params:{
              	page:_this.pageinfo.pageNo,
              	pageSize:_this.pageinfo.pageSize,
                clientNm:_this.filters.client_nm,
				empNm:_this.filters.emp_nm,
				apprDocNo:_this.filters.appr_doc_no,
				deptCd:_this.filters.dept_cd,
				createDtFr: this.$utils.formatDate(_this.filters.appr_doc_create_dt_from,"YYYY-MM-DD"),
				createDtTo: this.$utils.formatDate(_this.filters.appr_doc_create_dt_to,"YYYY-MM-DD"),
				workMonth:this.$utils.formatDate(_this.filters.work_mon,"YYYY-MM"),
				mainUserId:_this.filters.business_main_user_id,
        arrpDocSt:_this.filters.appr_doc_st
              },
            }
            )
            .then(function (response) {
              var errorcode=response.data.errorcode;
                 if(errorcode=="200"){
                    _this.pageinfo=response.data.data;

                }else if(errorcode=="500"){
                   _this.$message.error(response.data.errormsg);
                }
              })
            .catch(function (error) {
                console.log(error);
            });


   },
    //取得職位
	  getDept(){
		  var _this = this;
		    this.$http({
				 method:'get',
				 url:'/api/dept/dcitList'
			 })
             .then(function (response) {
               var errorcode=response.data.errorcode;
                  if(errorcode=="200"){
                     _this.optionsdept =response.data.data;
                 }else if(errorcode=="500"){
                     // console.debug(response);
                     _this.errormsg(response.data.errormsg);
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

  },//method end

  mounted(){
      this.getPageSizeList();
      this.getList();
      this.getDept();
      this.loadBusinessUserList();
  }//mounted end
}
</script>

<style>
#ApprDocInq{
  width: 90%;
  margin-top: 25px;
  margin-left:30px;
}

.ApprDocInq_top_form{
  margin-top: 12px;

  margin-left: 10px;
}
#ApprDocInq_middle{
  margin-top: 10px;
}
</style>

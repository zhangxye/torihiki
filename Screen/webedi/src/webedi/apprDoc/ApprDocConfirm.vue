<template>
  <div id="ApprDocConfirm">
      <div class="ApprDocConfirm_top">
          <el-form
          class="ApprDocConfirm_top_form"
          :inline="true"
          label-width="120px"
          :model="filters">

    　　　<h3>【検索条件】</h3>

          <el-col :span="24" >
          <el-form-item
          label="稟議書NO"
          prop="appr_doc_no" >
          <el-input
          size="small"
          style="width:120px"
          v-model="filters.appr_doc_no"
          placeholder="前方一致">
        　</el-input>
          </el-form-item>
          <el-form-item
          label="承認日">
                <el-form-item prop="appr_doc_approve_dt_from">
                  <el-date-picker
                  size="small"
                  type="date"
                  style="width:120px"
                  placeholder="日付From"
                  v-model="filters.appr_doc_approve_dt_from">
                  </el-date-picker>
                </el-form-item>
                -
                <el-form-item prop="appr_doc_approve_dt_to">
                  <el-date-picker
                   size="small"
                   type="date"
                   style="width:120px"
                   placeholder="日付To"
                   v-model="filters.appr_doc_approve_dt_to">
                  </el-date-picker>
                </el-form-item>
            </el-form-item>
            <el-form-item label="状態" prop="status" >
          <el-select v-model="filters.appr_status"  :disabled="false" filterable size="small"  style="width:120px">
            <el-option label="" value=""></el-option>
              <el-option
                v-for="item in optionsStatus"
                :label="item.label"
                :value="item.value"
                :key="item.value">
              </el-option>
            </el-select>
            </el-form-item>
        </el-col>
        <el-col :span="24" >
            <el-form-item
            label="社員コード"
            prop="emp_no">
               <el-input
               size="small"
                type="text"
                style="width:120px"
                v-model="filters.emp_no"
                auto-complete="on"
                placeholder="前方一致">
              </el-input>
            </el-form-item >
            <el-form-item
             label="氏名"
              prop="emp_nm">
                <el-input
                size="small"
                v-model="filters.emp_nm"
                placeholder="部分一致">
              　</el-input>
            </el-form-item >

          <el-form-item>
              <el-button type="primary" @click="searchData" icon="search">検索</el-button>
           </el-form-item>
          </el-col>

          </el-form>

     </div>
     <el-table :data="pageinfo.pageData"
      height="400"
      border
      id="ApprDocInq_middle">
    <el-table-column fixed width="60" label="NO">
    <template slot-scope="scope">
    {{(scope.$index +  1)+ (pageinfo.pageNo - 1) *pageinfo.pageSize}}
    </template>
    </el-table-column>
        <el-table-column
        fixed
        prop="appr_doc_no"
        label="稟議書NO"
        width="130"/>
        <el-table-column
        fixed
        prop="appr_status"
        label="状態"
        width="100"/>
        <el-table-column fixed prop="appr_doc_approve_dt" label="承認日" width="120"/>
        <el-table-column prop="appr_doc_create_k_nm" label="作成区分" width="100"/>
        <el-table-column prop="appr_doc_dt_from" label="受注期間~" width="120"/>
        <el-table-column prop="appr_doc_dt_to" label="受注期間" width="120"/>
        <el-table-column prop="emp_no" label="社員コード" width="120"/>
        <el-table-column prop="emp_nm" label="氏名" width="180"/>
        <el-table-column prop="client_no" label="クライアントID" width="150"/>
        <el-table-column prop="client_nm" label="クライアント名称" width="250"/>
        <el-table-column prop="pj_cd" label="案件ID" width="120" />
        <el-table-column prop="pj_nm" label="案件名" width="250" />
        <el-table-column
        fixed="right"
        label="操作" width="160">
        <template slot-scope="scope">
          <el-button type="info"
        @click="goToDetail(scope.$index,scope.row)" size="mini" icon="view">詳細</el-button>
         <el-button type="success" :disabled="scope.row.confirm_st !== '1'"
        @click="approve(scope.$index,scope.row)" size="mini" icon="edit">承認</el-button>
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
	<el-dialog title="稟議書承認" v-model="dialogVisible" >
		<h3>【承認履歴】</h3>
			<el-table :data="apprInfo.apprApplyDtl" id="AcceptRoute" height="180" >
				<el-table-column fixed prop="display_order" label="番号" width="65"/>
				<el-table-column fixed prop="accept_user_name" label="承認者" width="120"/>
				<el-table-column fixed prop="job_title_name" label="職位" width="100"/>
				<el-table-column fixed prop="confirm_st_name" label="承認状況" width="100"/>
				<el-table-column fixed prop="con_rej_dt" label="承認日" width="120"/>
				<el-table-column  prop="con_rej_comment" label="コメント" width="500"/>
			</el-table>
			<el-form :inline="true" :rules="rules1" :model="apprInfo" ref="apprInfo" label-width="110px" >
			<el-form-item label="稟議書申請理由" prop="apply_reason" >
				<el-input type="textarea" v-model="apprInfo.apply_reason" style="width:510px" auto-complete="off" :disabled="true" ></el-input>
			</el-form-item>
      <!--
      <el-form-item  prop="apply_reason" >
        <router-link to="/ApprDoc/ApprDocEnt" >稟議書詳細</router-link>
      </el-form-item>
      -->
			<el-form-item label="承認状況" prop="confrim_st" >
				<el-radio-group v-model="apprInfo.confrim_st" >
					<el-radio label="2">同意</el-radio>
					<el-radio label="3">拒否</el-radio>
				</el-radio-group>
			</el-form-item>
			<el-form-item label="コメント" prop="con_rej_comment" >
				<el-input type="textarea" v-model="apprInfo.con_rej_comment" style="width:510px" auto-complete="off" :disabled="false" ></el-input>
			</el-form-item>
		</el-form>
		<el-col :span="10">
		<span id="save_button"><el-button type="success" :disabled="!apprInfo.save_btn_valid_flg" @click="save" >保存</el-button></span>
		</el-col>
		<br/>
	</el-dialog>
  </div>
</template>

<script>
export default {
  data(){
  	var validateComment= (rule, value, callback) => {
			if(value!='' && value.length>150) {
			   callback(new Error('コメントは150桁以内で入力してください'));
			}
			callback();
		};
    return{
         filters: {
          appr_doc_no: '',
          appr_doc_approve_dt_from: '',
          appr_doc_approve_dt_to: '',
          emp_no: '',
          emp_nm: '',
          appr_status:'1'
        },
        rules1: {
		  confrim_st: [
            { required: true, message: '承認状況が選択してください。', trigger: 'blur' },

          ],
		  con_rej_comment: [
		  	{ required: true, message: 'コメントが入力してください。', trigger: 'blur' },
            { validator: validateComment, trigger: 'blur' }
          ]
        },
        optionsStatus:[{label:'未承認',value:'1',},{label:'同意',value:'2'},{label:'拒否',value:'3'}],
        dialogVisible:false,
        apprInfo:{
          save_btn_valid_flg:true,
          apprDocId:'',
          apply_reason:'',
          confrim_st:'',
          con_rej_comment:'',
      		apprApplyDtl:[
      		]
        },
        pageinfo:{
          pageNo:1,
          pageSize:10,
          total:10,
          pageData:[
          ]
        },
    }
  },

  methods:{
      //照会画面へ遷移
      goToDetail(index , row){
        this.$router.push({ path: 'ApprDocDetail', params: { appr_doc_no: row.appr_doc_no,linkSrc:'0' }});
      },
      //点击页数
      handleCurrentChange(val) {
        this.pageinfo.pageNo = val;
        // this.searchData();
      },
      searchData(){
          var _this = this;
            this.$http.get('/api/apprDoc/selectApprApprove'
            ,{
              params:{
                page:_this.pageinfo.pageNo,
                pageSize:_this.pageinfo.pageSize,
                apprDocNo:_this.filters.appr_doc_no,
                approveDtFr: this.$utils.formatDate(_this.filters.appr_doc_approve_dt_from,"YYYY-MM-DD"),
                approveDtTo: this.$utils.formatDate(_this.filters.appr_doc_approve_dt_to,"YYYY-MM-DD"),
                approveStatus:_this.filters.appr_status,
                empNo:_this.filters.emp_no,
                empNm:_this.filters.emp_nm
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
      approve(index, row) {
        var _this = this;
            this.$http.get('/api/apprDoc/goApprove'
            ,{
              params:{
                apprDocId:row.appr_doc_id
                ,apprApplyHid:row.appr_apply_hid
              },
            }
            )
            .then(function (response) {
              var errorcode=response.data.errorcode;
                 if(errorcode=="200"){
                  _this.apprInfo=response.data.data;
                  _this.dialogVisible =true;

                }else if(errorcode=="500"){
                   _this.$message.error(response.data.errormsg);
                }
              })
            .catch(function (error) {
                console.log(error);
            });

      },
      // //照会画面へ遷移
      //  goToDetail(index , row){
      //   this.$router.push({ path: 'ApprDocDetail', query: { appr_doc_no: row.appr_doc_no }});
      // },
      save() {
        var _this = this;
          this.$refs.apprInfo.validate((valid) => {
          if (valid){
            this.$http.put('/api/apprDoc/approve',_this.apprInfo)
            .then(function (response) {
              var errorcode=response.data.errorcode;
              if(errorcode=="200"){
                _this.$alert('承認することに成功します', '提示', {
                        confirmButtonText: '確認',
                        callback: action => {
                         _this.dialogVisible=false;
                        _this.searchData();
                        }
                      });
              }else if(errorcode=="500"){
                _this.$message.error(response.data.errormsg);
                 }
                })
              .catch(function (error) {
                console.log(error);
              });
            this.dialogVisible =false;
          }
        })
      }
  },
  mounted(){
    this.searchData();
  }
}
</script>

<style>
#ApprDocConfirm{
  width: 90%;
  margin-top: 25px;
  margin-left:30px;
}

.ApprDocConfirm_top_form{
  margin-top: 12px;

  margin-left: 10px;
}
#ApprDocConfirm_middle{
  margin-top: 10px;
}
</style>

<template>
  <div id="UserManager">
      <div class="UserManager_top">
      <el-col class="UserManager_top" :span="24" >
          <el-form class="UserManager_top_form" :inline="true" :model="filters">
              <el-form-item class="UserManager_top_form_name">
                <el-input class="UserManager_top_form_name_frame" v-model="filters.name" placeholder="ユーザー名"></el-input>
              </el-form-item>
			  <el-form-item>
              <el-select v-model="filters.jobTitleCd"  :disabled="false" filterable placeholder="職位">
					<el-option label="" value=""></el-option>
					<el-option
						v-for="item in optionsjob"
						:label="item.label"
						:value="item.value"
						:key="item.value">
					</el-option>
              </el-select>
			  </el-form-item>
           <el-form-item>
              <el-button type="primary" @click="searchData" icon="search">検索</el-button>
           </el-form-item>
          </el-form>
      </el-col>
     </div>
     <el-table :data="pageinfo.pageData" id="UserManager_middle" >
		<el-table-column  width="60px" label="#">
		<template slot-scope="scope">
		{{(scope.$index +  1)+ (pageinfo.pageNo - 1) *pageinfo.pageSize}}
		</template>
		</el-table-column>
        <el-table-column prop="account" label="ユーザーID" width="110px"/>
        <el-table-column prop="name" label="ユーザー名称" width="200px"/>
        <el-table-column prop="role_nm" label="ロール" width="300px"/>
        <el-table-column prop="job_title_nm" label="職位" width="200px"/>
        <el-table-column prop="last_login_dt_str" label="最後ログイン日時" width="170px"/>
		<el-table-column prop="status_nm" label="状態" width="98px" />
        <el-table-column label="操作" >
			<template slot-scope="scope">
			<el-button type="primary"
			@click="handleEdit(scope.$index, scope.row)" size="mini" icon="edit">修正</el-button>
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
	<el-dialog title="ユーザー詳細情報‐修正" v-model="dialogVisible" width="3000">
		<el-form :inline="true" :rules="rules1" :model="userInfo" ref="userInfo" label-width="110px" class="userInfo-ruleForm">
      <el-row>
        <el-col :span="8">
			<el-form-item label="ユーザーID" prop="account" >
				<el-input type="text" v-model="userInfo.account" style="width:100px;" auto-complete="off" :disabled="true" ></el-input>
			</el-form-item>
      </el-col>
      <el-col :span="16">
			<el-form-item label="ユーザー名" prop="name" >
				<el-input type="text" v-model="userInfo.name" auto-complete="off" :disabled="false"></el-input>
			</el-form-item>
      </el-col>
      </el-row>
     <el-row>
        <el-col :span="8">
			<el-form-item label="状態" prop="status" >
				 <el-radio-group v-model="userInfo.status" >
					<el-radio label="1">利用中</el-radio>
					<el-radio label="2">终止中</el-radio>
				</el-radio-group>
			</el-form-item>
      </el-col>
      <el-col :span="16">
			<el-form-item label="ロール" prop="roleid"  >
				<el-select style="width:180px" v-model="userInfo.roleid" filterable placeholder="選択してください"  :disabled="false">
					<el-option
						v-for="item in optionsrole"
						:key="item.value"
						:label="item.label"
						:value="item.value">
					</el-option>
				</el-select>
			</el-form-item>
     </el-col>
      </el-row>
    <el-row>
        <el-col :span="8">
			<el-form-item label="部署" prop="deptid" >
				<el-select style="width:150px" v-model="userInfo.deptid" filterable placeholder="選択してください">
					<el-option
						v-for="item in optionsdept"
						:label="item.label"
						:value="item.value"
						:key="item.value">
					</el-option>
				</el-select>
			</el-form-item>
      </el-col>
      <el-col :span="16">
			<el-form-item label="職位" prop="jobTitleCd" >
				<el-select v-model="userInfo.jobTitleCd" filterable placeholder="選択してください">
					<el-option
						v-for="item1 in optionsjob"
						:label="item1.label"
						:value="item1.value"
						:key="item1.value">
					</el-option>
				</el-select>
			</el-form-item>
     </el-col>
      </el-row>
		</el-form>
		<h3>ユーザー承認経路</h3>
		<el-table :data="userInfo.userwfconfirmroute" style="width: 537px" id="AcceptRoute" >
			<el-table-column label="表示順" width="90">
				<template slot-scope="scope">
					<el-input type="number"
					  auto-complete="off" v-model="scope.row.display_order" :disabled="false" max=99 min=1 size="mini"></el-input>
				</template>
			</el-table-column>
			<el-table-column  prop="accept_user_name" label="承認者" width="225"/>
			<el-table-column  prop="job_title_name" label="職位" width="120"/>
			<el-table-column label="操作" width="100">
				<template slot-scope="scope">
					<el-button type="danger"
					@click="acceptDelete(scope.$index, scope.row)" size="mini" icon="delete">削除</el-button>
				</template>
			</el-table-column>
		</el-table>
		<br/>
		<el-col :span="10">
		<span id="save_button"><el-button type="success" @click="save" >保存</el-button></span>
		<span id="accept_button"><el-button type="primary" @click="insertAcceptRoute" >追加承認者</el-button></span>
		</el-col>
		<br/>
	</el-dialog>
	<el-dialog title="ユーザー承認者" v-model="dialogAcceptModVisible" >
	 <el-form :inline="true" :model="acceptUserMod" :rules="wfRules" ref="acceptUserMod" label-width="100px" class="acceptUserModForm">
		<el-form-item label="表示順" prop="display_order" >
			<el-input-number v-model="acceptUserMod.display_order" auto-complete="off" :disabled="false" ></el-input-number>
		</el-form-item>
		<el-form-item label="ユーザー" prop="accept_user_id" >
			<el-select v-model="acceptUserMod.accept_user_id" filterable placeholder="選択してください">
					<el-option
						v-for="item in optionsuser"
						:label="item.label"
						:value="item.value"
						:key="item.value">
					</el-option>
			</el-select>
		</el-form-item>
	</el-form>
	<span id="save_button1"><el-button type="success" @click="addAcceptUser">追加</el-button></span>
  </el-dialog>
  </div>
</template>

<script>
export default {
  data(){
		var validateName= (rule, value, callback) => {
			if(value.length>15) {
			   callback(new Error('ユーザー名は15桁以内で入力してください'));
			}
			callback();
		};
	  var validateDispOrder= (rule, value, callback) => {
			if (value ===0) {
			  callback(new Error('表示順が1以上で入力してください'));
			}

			if (this.userInfo.userwfconfirmroute.filter(o => o.display_order === value)[0] !== undefined
			&& this.userInfo.userwfconfirmroute.filter(o => o.display_order === value)[0] !== 'undefined') {
				 callback(new Error('表示順が承認一覧中に存在するので、再入力してください'));
			}
			callback();
		};
		var validateAcceptUserid= (rule, value, callback) => {
			if (this.userInfo.userwfconfirmroute.filter(o => o.accept_user_id === value)[0] !== undefined
			&& this.userInfo.userwfconfirmroute.filter(o => o.accept_user_id === value)[0] !== 'undefined') {
				 callback(new Error('承認者が承認一覧中に存在するので、再入力してください'));
			}
			callback();
		};
      return{
         filters: {
					name: '',
					jobTitleCd: ''
				},
        userInfo: {
          account:'',
          name: '',
          status: '',
          roleid:'',
          deptid:'',
          jobTitleCd:'',
		  userwfconfirmroute:[]
        },
		pageinfo:{
			pageNo:1,
			pageSize:10,
			total:20,
			pageData:[]
		},
        dialogVisible:false,
		dialogAcceptModVisible:false,
		acceptUserMod: {
          display_order:'',
          accept_user_id: '',
        },
	optionsdept:[],
	optionsrole:[],
	optionsuser:[],
	optionsjob:[],
	rules1: {
		  name: [
            { required: true, message: 'ユーザー名が入力必須です。', trigger: 'blur' },
			      { validator: validateName, trigger: 'blur' }
          ],
		  roleid: [
            { required: true, message: 'ロールが入力必須です。', trigger: 'blur' }
          ],
		  deptid: [
            { required: true, message: '部署が入力必須です。', trigger: 'blur' }
          ],
          jobTitleCd: [
            { required: true, message: '職位が入力必須です。', trigger: 'blur' }
          ],
		   status: [
            { required: true, message: '状態が入力必須です。', trigger: 'blur' }
          ]
        },
	wfRules:{
			display_order: [
            { validator: validateDispOrder, trigger: 'blur' }
			],
		  accept_user_id: [
            { required: true, message: '承認者が入力必須です。', trigger: 'change' },
			{ validator: validateAcceptUserid, trigger: 'blur' }
          ]
		}
      }
  },
  methods:{
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
	   //ロール
	   getRole(){
		  var _this = this;
		    this.$http({
				 method:'get',
				 url:'/api/role/roleList'
			 })
             .then(function (response) {
               var errorcode=response.data.errorcode;
                  if(errorcode=="200"){
                     _this.optionsrole =response.data.data;
                 }else if(errorcode=="500"){
                     // console.debug(response);
                     _this.errormsg(response.data.errormsg);
                 }
               })
             .catch(function (error) {
                 console.log(error);
             });
	  },
	  getJob(){
		  var _this = this;
		    this.$http({
				 method:'get',
				 url:'/api/common/joblist'
			 })
             .then(function (response) {
               var errorcode=response.data.errorcode;
                  if(errorcode=="200"){
                     _this.optionsjob =response.data.data;
                 }else if(errorcode=="500"){
                     // console.debug(response);
                     _this.errormsg(response.data.errormsg);
                 }
               })
             .catch(function (error) {
                 console.log(error);
             });
	  },
	    getUserList(account){

		  var _this = this;
		    this.$http({
				 method:'get',
				 url:'/api/user/userList/'+account
			 })
             .then(function (response) {
               var errorcode=response.data.errorcode;
                  if(errorcode=="200"){
                     _this.optionsuser =response.data.data;
                 }else if(errorcode=="500"){
                     // console.debug(response);
                     _this.errormsg(response.data.errormsg);
                 }
               })
             .catch(function (error) {
                 console.log(error);
             });
	  },

      //查询（获取用户列表）
      getUsers(){
         var _this = this;
            this.$http.get('/api/user/selectall'
            ,{
              params:{
                username:_this.filters.name,
				jobTitleCd:_this.filters.jobTitleCd,
                page:_this.pageinfo.pageNo
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
      //点击页数
      handleCurrentChange(val) {
        this.pageinfo.pageNo = val;
        this.getUsers();
      },
	   searchData() {
        this.pageinfo.pageNo = 1;
        this.getUsers();
      },
	  //新規承認者
      insertAcceptRoute(){
		this.dialogAcceptModVisible=true;
		this.getUserList(this.userInfo.account);
      },
	  	  //新規承認者
	  addAcceptUser(){
		this.$refs.acceptUserMod.validate((valid) => {
		if (valid){
			var selObj = this.optionsuser.filter(o => o.value === this.acceptUserMod.accept_user_id)[0];
			var addData =  { display_order: this.acceptUserMod.display_order ,accept_user_id:this.acceptUserMod.accept_user_id,accept_user_name:selObj.label,"job_title_name":selObj.fullname};
			this.userInfo.userwfconfirmroute.push(addData);
			this.dialogAcceptModVisible=false;
			}
		})
	  },
	  //删除
      acceptDelete(index, row) {
          var _this = this;
          _this.$confirm('このユーザーが削除しますか？', '提示', {
			  confirmButtonText: 'はい',
			  cancelButtonText: 'いいえ',
			  type: 'warning'
			}).then(() => {
			 _this.userInfo.userwfconfirmroute.splice(index,1);
				}
			);
      },
      //编辑
      handleEdit(index, row) {
        var _this = this;
            this.$http.get('/api/user/select'
            ,{
              params:{
                account:row.account
              },
            }
            )
            .then(function (response) {
              var errorcode=response.data.errorcode;
                 if(errorcode=="200"){
                  _this.userInfo=response.data.data;

                }else if(errorcode=="500"){
                   _this.$message.error(response.data.errormsg);
                }
              })
            .catch(function (error) {
                console.log(error);
            });
          this.dialogVisible=true;
      },
      //保存
     save(){
			var _this = this;
			 this.$refs.userInfo.validate((valid) => {
			if (valid){
			_this.$confirm('更新するはよろしいですか？', '提示', {
			confirmButtonText: 'はい',
			cancelButtonText: 'いいえ',
			type: 'success'
			}).then(() => {
				this.$http.put('/api/user/edit',_this.userInfo)
				.then(function (response) {
				  var errorcode=response.data.errorcode;
					if(errorcode=="200"){
						_this.$alert('更新することに成功します', '提示', {
									  confirmButtonText: '確認',
									  callback: action => {
									   _this.dialogVisible=false;
									  _this.getUsers();
									  }
								  });
					}else if(errorcode=="500"){
					  _this.$message.error(response.data.errormsg);
						 }
					  })
					.catch(function (error) {
						console.log(error);
					});
			  }).catch(() => {
					_this.$notify({
						title: '提示',
						type: 'error',
						message: 'データが格納しません！',
						offset: 100
					 });
			   });
			}
		})
     }
  },
  mounted(){
	this.getUsers();
	this.getDept();
	this.getRole();
	this.getJob();
  }
}
</script>

<style>
#UserManager{
  width: 90%;
  margin-top: 25px;
  margin-left:30px;
}

.UserManager_top{
  height: 58px;
  border:50px;
  background-color: #F2F2F2;
}
.UserManager_top_form{
  margin-top: 12px;

  margin-left: 10px;
}
#UserManager_middle{
  margin-top: 10px;
}
</style>

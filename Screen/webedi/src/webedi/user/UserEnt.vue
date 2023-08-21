<template>
  <div id="UserManager">
         <el-form :inline="true"  :rules="rules1" :model="userInfo" ref="userInfo" label-width="100px" class="userForm">
		 <h2>ユーザー詳細情報‐新規</h2>
		<el-row>
      <el-col :span="5">
    	<el-form-item label="ユーザーID" prop="account" >
				<el-input type="text" v-model="userInfo.account" :maxlength="10" :max="10" auto-complete="off" :disabled="false" ></el-input>
			</el-form-item>
      </el-col>
      <el-col :span="19">
			<el-form-item label="氏名" prop="name" >
				<el-input type="text" v-model="userInfo.name" auto-complete="off" :maxlength="25" :max="25"  :disabled="false"></el-input>
			</el-form-item>
      </el-col>
		</el-row>
		<el-row>
    <el-col :span="5">
			<el-form-item label="パスワード" prop="password">
				<el-input type="password"  v-model="userInfo.password" auto-complete="off" :disabled="false"></el-input>
			</el-form-item>
    </el-col>
    <el-col :span="19">
			<el-form-item label="状態" prop="status" >
			  	<el-radio-group v-model="userInfo.status" >
					<el-radio label="1">利用中</el-radio>
					<el-radio label="2">停止中</el-radio>
				</el-radio-group>
			</el-form-item>
    </el-col>
      </el-row>
    <el-row>
      <el-col :span="24">
 			<el-form-item label="ロール" prop="roleid" >
				<el-select v-model="userInfo.roleid" filterable placeholder="選択してください" >
					<el-option
						v-for="item in optionsrole"
						:label="item.label"
						:value="item.value"
						:key="item.value">
					</el-option>
				</el-select>
			</el-form-item>
      </el-col>
    </el-row>
			<el-form-item label="管轄部署" prop="deptid" >
				<el-select v-model="userInfo.deptid" filterable placeholder="選択してください">
					<el-option
						v-for="item1 in optionsdept"
						:label="item1.label"
						:value="item1.value"
						:key="item1.value">
					</el-option>
				</el-select>
			</el-form-item>
			<el-form-item label="役職" prop="jobTitleCd" >
				<el-select v-model="userInfo.jobTitleCd" filterable placeholder="選択してください">
					<el-option
						v-for="item1 in optionsjob"
						:label="item1.label"
						:value="item1.value"
						:key="item1.value">
					</el-option>
				</el-select>
			</el-form-item>
		</el-form>
		 <h3>ユーザー承認経路</h3>
		<el-table :data="userInfo.userwfconfirmroute" style="width: 537px" id="AcceptRoute" >
			<el-table-column label="表示順"  prop="display_order1" width="90">
				<template slot-scope="scope">
					<el-input type="number"
					  auto-complete="off" v-model="scope.row.display_order" :disabled="false" max=99 min=1 size="mini"></el-input>
				</template>
			</el-table-column>
			<el-table-column  prop="accept_user_name" label="承認者" width="225"/>
			<el-table-column  prop="job_title_name" label="役職" width="120"/>
			<el-table-column label="操作" width="100">
				<template slot-scope="scope">
					<el-button type="danger"
					@click="acceptDelete(scope.$index, scope.row)" size="mini" icon="delete">削除</el-button>
				</template>
			</el-table-column>
		</el-table>
		<br/>
		<el-col :span="10">
			<span id="save_button"><el-button type="success" @click="save" >登録</el-button></span>
			<span id="accept_button"><el-button type="primary" @click="insertAcceptRoute" >追加承認者</el-button></span>
		</el-col>
  <el-dialog title="ユーザー承認者" v-model="dialogAcceptVisible" >
	 <el-form :inline="true" :model="acceptUser" :rules="wfRules" ref="acceptUser" label-width="100px" class="acceptUserForm">
		<el-form-item label="表示順" prop="display_order" >
			<el-input-number v-model="acceptUser.display_order"  auto-complete="off" :disabled="false" ></el-input-number>
		</el-form-item>
		<el-form-item label="承認者" prop="accept_user_id" >
			<el-select v-model="acceptUser.accept_user_id" filterable placeholder="選択してください">
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
		var validateAccount= (rule, value, callback) => {
			if(/^[a-zA-Z0-9_]{4,16}$/g.test(value)==false) {
			   callback(new Error('4～16桁半角英数字で入力してください'));
			}
			callback();
		};
		var validateName= (rule, value, callback) => {
			if(value.length>15) {
			   callback(new Error('氏名は15桁以内で入力してください'));
			}
			callback();
		};
		var validateDispOrder= (rule, value, callback) => {
			if (value <=0) {
			  callback(new Error('表示順が1以上で入力してください'));
			}

			if (this.userInfo.userwfconfirmroute.filter(o => o.display_order === value)[0] !== undefined
			&& this.userInfo.userwfconfirmroute.filter(o => o.display_order === value)[0] !== 'undefined') {
				 callback(new Error('表示順が承認一覧中に存在するので、再入力してください'));
			}
			callback();
		};
		var validateAcceptUserid= (rule, value, callback) => {
			if (value ===0) {
			  callback(new Error('表示順が1以上で入力してください'));
			}

			if (this.userInfo.userwfconfirmroute.filter(o => o.accept_user_id === value)[0] !== undefined
			&& this.userInfo.userwfconfirmroute.filter(o => o.accept_user_id === value)[0] !== 'undefined') {
				 callback(new Error('承認者が承認一覧中に存在するので、再入力してください'));
			}
			callback();
		};
      return{
        userInfo: {
          account:'',
          name: '',
          password: '',
		      status:'1',
          roleid:'',
		  deptid:'',
		  jobTitleCd:'',
		  userwfconfirmroute:[]
			},
		dialogAcceptVisible:false,
		acceptUser: {
          display_order:'',
          accept_user_id:'',
        },
		optionsdept:[],
		optionsjob:[],
		optionsrole:[],
		optionsuser:[],
		rules1: {
          account: [
            { required: true, message: 'ユーザーIDが入力必須です。', trigger: 'blur' },
			{ validator: validateAccount, trigger: 'blur' }
          ],
		  name: [
            { required: true, message: '氏名が入力必須です。', trigger: 'blur' },
			{ validator: validateName, trigger: 'blur' }
          ],
          password: [
            { required: true, message: 'パスワードが入力必須です。', trigger: 'blur' },
          ],
		  roleid: [
            { required: true, message: 'ロールが入力必須です。', trigger: 'blur' }
          ],
		  deptid: [
            { required: true, message: '管轄部署が入力必須です。', trigger: 'blur' }
          ],
          jobTitleCd: [
            { required: true, message: '役職が入力必須です。', trigger: 'blur' }
          ],
		   status: [
            { required: true, message: '状態が入力必須です。', trigger: 'blur' }
          ]
		  ,
		   display_order1: [
            { required: true, message: '状態が入力必須です。', trigger: 'blur' },
			{ validator: validateDispOrder, trigger: 'blur' }
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
	   //新規承認者
      insertAcceptRoute(){
			this.dialogAcceptVisible=true;
			this.getUserList('');
      },
	  //新規承認者
	  addAcceptUser(){
		    this.$refs.acceptUser.validate((valid) => {
			if (valid){
				var selObj = this.optionsuser.filter(o => o.value === this.acceptUser.accept_user_id)[0];
				var addData =  { display_order: this.acceptUser.display_order ,accept_user_id:this.acceptUser.accept_user_id,accept_user_name:selObj.label,"job_title_name":selObj.fullname};
				this.userInfo.userwfconfirmroute.push(addData);
				this.dialogAcceptVisible=false;
				}
			})
	  },
	  //取得役職
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
	        //删除
      acceptDelete(index, row) {
          var _this = this;
          _this.$confirm('このユーザーが削除しますか？', '提示', {
                                      confirmButtonText: 'はい',
                                      cancelButtonText: 'いいえ',
                                      type: 'warning'
                                    }).then(() => {
										this.userInfo.userwfconfirmroute.splice(index,1);
                                            }
                                      );
      },
      //新規追加登録
      save(){
          var _this = this;
		  this.$refs.userInfo.validate((valid) => {
			if (valid){
           _this.$confirm('登録しますか？', '提示', {
                confirmButtonText: '確認',
                cancelButtonText: '取消',
                type: 'success'
                }).then(() => {
                       _this.$http.post('/api/user/regiter',_this.userInfo)
                        .then(function (response) {
                                        var errorcode=response.data.errorcode;
                                          if(errorcode=="200"){
                                              _this.$alert('登録完了', '提示', {
                                                  confirmButtonText: '確認',
                                                  callback: action => {
                                                }
                                                  });
                                          }else if(errorcode=="500"){
                                             _this.$alert('登録失敗['+response.data.errormsg+']', '提示', {
                                                  confirmButtonText: '確認',
                                                  callback: action => {
                                                  }
                                                });
                                          }
                                        })
                                      .catch(function (error) {
                                          console.log(error);
                                      });

                            }).catch(() => {
                                _this.$notify({
                                  title: '提示',
                                  type: 'error',
                                  message: 'データ登録失敗',
                                  offset: 100
                                  });
                               });
				}
			})

      }
	},
mounted(){
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

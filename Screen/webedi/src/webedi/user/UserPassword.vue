<template>
  <div id="UserPassword">
         <el-form :model="userInfo" :rules="rules1" ref="userInfo" label-width="140px">
		 <h2>修正パスワード</h2>
			<el-input type="hidden" v-model="userInfo.account"  style="width:100px;" auto-complete="off" ></el-input>
			<el-row style="height:40px;">
			<el-col>
				<el-form-item label="ユーザー" prop="name" >
					{{userInfo.name}}
				</el-form-item>
			</el-col>
			</el-row>
			<el-row style="height:40px;">
			<el-col>
				<el-form-item label="元パスワード" prop="passowrd" >
					<el-input type="password" v-model="userInfo.passowrd" style="width:200px;" auto-complete="off" :disabled="false"></el-input>
				</el-form-item>
			</el-col>
			</el-row >
			<el-row style="height:40px;">
			<el-col>
				<el-form-item label="新パスワード" prop="passowrd1">
					<el-input type="password"  v-model="userInfo.passowrd1" style="width:200px;" auto-complete="off" :disabled="false"></el-input>
				</el-form-item>
			</el-col>
			</el-row>
			<el-row style="height:40px;">
			<el-col>
				<el-form-item label="再新パスワード" prop="user_pwd2">
					<el-input type="password"  v-model="userInfo.user_pwd2" style="width:200px;" auto-complete="off" :disabled="false"></el-input>
				</el-form-item>
			</el-col>
			</el-row>
		</el-form>
		<el-col :span="10">
		<span id="save_button"><el-button type="success" @click="save" >保存</el-button></span>
		</el-col>
  </div>
</template>
<script>

export default {
  data(){
      return{
        userInfo: {
          account:this.$store.state.user.user_id,
		  name:this.$store.state.user.username,
          passowrd: '',
		  passowrd1: '',
          user_pwd2: ''
        },
		rules1: {
		  passowrd: [
            { required: true, message: '元パスワードが入力必須です。', trigger: 'blur' }
          ],
		  passowrd1: [
            { required: true, message: '新パスワードが入力必須です。', trigger: 'blur' }
          ],
		  user_pwd2: [
			{ required: true, message: '新パスワードが再入力必須です。', trigger: 'blur' }
			
          ]
        }
      }
  },
  methods:{
      save(){
          var _this = this;
		  _this.$refs.userInfo.validate((valid) => {
			if (valid) {
				_this.$confirm('パスワードを更新するはよろしいですか？', '提示', {
				confirmButtonText: '確認',
				cancelButtonText: '取消',
				type: 'success'
				}).then(() => {
					   this.$http.get('/api/user/changePwd'
						,{
						  params:{
							oldPwd:_this.userInfo.passowrd,
							newPwd:_this.userInfo.passowrd1,
							rePwd:_this.userInfo.user_pwd2
						  },
						},
						)
						.then(function (response) {
								var errorcode=response.data.errorcode;
								  if(errorcode=="200"){
									  _this.$alert('パスワードを更新することに成功します', '提示', {
										  confirmButtonText: '確認',
										  callback: action => {
										  }
										  });
								  }else if(errorcode=="500"){
									 _this.$alert(response.data.errormsg, '提示', {
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
						  message: 'データが格納しません！',
						  offset: 100
					});
				});
			}
		 })
      }
 }
}

</script>

<style>
#UserPassword{
  width: 90%;
  margin-top: 25px;
  margin-left:30px;
}

.UserPassword_top{
  height: 58px;
  border:50px;
  background-color: #F2F2F2;
}
.UserPassword_top_form{
  margin-top: 12px;

  margin-left: 10px;
}
#UserPassword_middle{
  margin-top: 10px;
}
</style>

<template>
  <div id="ResetPass">
         <el-form :model="userInfo" :rules="rules1" ref="userInfo" label-width="140px">
		 <h2>リセットパスワード</h2>

			<el-row style="height:40px;">
			<el-col>
				<el-form-item label="ユーザー" prop="account" >
					<el-input type="text" v-model="userInfo.account" style="width:100px;" auto-complete="off" :disabled="false"></el-input>
				</el-form-item>
			</el-col>
			</el-row >
		
		</el-form>
		<el-col :span="10">
		<span id="save_button"><el-button type="success" @click="save" >リセット</el-button></span>
		</el-col>
  </div>
</template>
<script>

export default {
  data(){
      return{
        userInfo: {
          account:''
        },
		rules1: {
		  account: [
            { required: true, message: 'ユーザーが入力必須です。', trigger: 'blur' }
          ]
        }
      }
  },
  methods:{
      save(){
          var _this = this;
		  _this.$refs.userInfo.validate((valid) => {
			if (valid) {
				_this.$confirm('パスワードをリセットするはよろしいですか？', '提示', {
				confirmButtonText: '確認',
				cancelButtonText: '取消',
				type: 'success'
				}).then(() => {
					   this.$http.post('/api/user/resetPwd',
						{"account":_this.userInfo.account}
						)
						.then(function (response) {
								var errorcode=response.data.errorcode;
								  if(errorcode=="200"){
									  _this.$alert('パスワードをリセットすることに成功します', '提示', {
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
#ResetPass{
  width: 90%;
  margin-top: 25px;
  margin-left:30px;
}

.ResetPass_top{
  height: 58px;
  border:50px;
  background-color: #F2F2F2;
}
.ResetPass_top_form{
  margin-top: 12px;

  margin-left: 10px;
}
#ResetPass_middle{
  margin-top: 10px;
}
</style>


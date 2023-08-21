<template>
  <el-form :model="ruleForm2" :rules="rules2" ref="ruleForm2" label-position="left" label-width="0px" class="demo-ruleForm login-container">
    <h3 class="title">システム登録</h3>
    <el-form-item prop="account">
      <el-input type="text" v-model="ruleForm2.account" auto-complete="off" placeholder="ユーザーID"></el-input>
    </el-form-item>
    <el-form-item prop="checkPass">
      <el-input type="password" v-model="ruleForm2.checkPass" auto-complete="off" placeholder="パスワード"></el-input>
    </el-form-item>
    <el-checkbox v-model="checked" checked class="remember">りメンバー</el-checkbox>
    <el-form-item style="width:100%;">
      <el-button type="primary" style="width:100%;" @click.native.prevent="handleSubmit2" :loading="logining">登録</el-button>
      <!--<el-button @click.native.prevent="handleReset2">リセット</el-button>-->
    </el-form-item>
  </el-form>
</template>

<script>
  import NProgress from 'nprogress'
  export default {
    data() {
      return {
        logining: false,
        ruleForm2: {
          account: 'admin',
          checkPass: '123456'
        },
        rules2: {
          account: [
            { required: true, message: 'ユーザーIDを入力してください', trigger: 'blur' },
            //{ validator: validaePass }
          ],
          checkPass: [
            { required: true, message: 'パスワードを入力してください', trigger: 'blur' },
            //{ validator: validaePass2 }
          ]
        },
        checked: true
      };
    },
    methods: {
      handleReset2() {
        this.$refs.ruleForm2.resetFields();
      },
      handleSubmit2(ev) {
        var _this = this;
        this.$refs.ruleForm2.validate((valid) => {
          if (valid) {
            //_this.$router.replace('/table');
            this.logining = true;
            NProgress.start();
            let url = 'localhost/login?userName=' + this.ruleForm2.account + '&passWord=' + this.ruleForm2.checkPass
            this.$http.get(url).then(response => {
              this.logining = false;
              NProgress.done();
              let { data, errorcode, errormsg } = response.data;
                let user =  {
                  id: 1,
                  username: this.ruleForm2.account,
                  avatar: 'https://raw.githubusercontent.com/taylorchen709/markdown-images/master/vueadmin/user.png',
                  name: this.ruleForm2.account
                }
                sessionStorage.setItem('user', JSON.stringify(user));
                this.$router.push({ path: '/main' });
            }, response => {
              console.log('error submit!!');
              return false;
            });
          }
        })
      }
    }
  }

</script>

<style lang="scss" scoped>
  .login-container {
    /*box-shadow: 0 0px 8px 0 rgba(0, 0, 0, 0.06), 0 1px 0px 0 rgba(0, 0, 0, 0.02);*/
    -webkit-border-radius: 5px;
    border-radius: 5px;
    -moz-border-radius: 5px;
    background-clip: padding-box;
    margin: 180px auto;
    width: 350px;
    padding: 35px 35px 15px 35px;
    background: #fff;
    border: 1px solid #eaeaea;
    box-shadow: 0 0 25px #cac6c6;
    }
    .remember {
      margin: 0px 0px 35px 0px;
    }
    .title {
      margin: 0px auto 40px auto;
      text-align: center;
      color: #505458;
    }
  
</style>
<template>
  <div id="login">
    <div id="login_img"><img src="./img/login.jpg" /></div>
    <div id="login_main">
        <div id="login_main_top">
          <h1>WEBEDIシステム</h1>
          <a>アクロリーチ株式会社</a>
        </div>
        <div id="login_main_center">
            <el-form :model="loginForm" :rules="rules2"  ref="loginForm" label-width="90px" class="demo-ruleForm">
              <el-form-item label="ユーザー" prop="account" >
                <el-input type="text" placeholder="ユーザー" v-model="loginForm.account" auto-complete="off"></el-input>
              </el-form-item>
              <el-form-item label="パスワード" prop="password">
                <el-input type="password" placeholder="パスワード" @keyup.enter.native="doLogin" v-model="loginForm.password" auto-complete="off"></el-input>
              </el-form-item>
              <el-form-item>
                <div id="login_button_user"><el-button type="primary" style="width:100%;" @click="doLogin" :loading="loading">ログイン</el-button>
                </div>
              </el-form-item>
           </el-form>
        </div><!-- main-->
    </div><!-- main-->
  </div><!-- login-->
  </template>
  <script>
import router from "@/router";

    export default {
      data() {
        var validateUsername = (rule, value, callback) => {
          if (value === '') {
            callback(new Error('ユーザーを入力してください'));
          } else {
            //if(/\d{8}/g.test(value)==false) {
              // callback(new Error('8桁半角数字で入力してください'));
            //}
          }
            callback();
        };
        var validatePassword = (rule, value, callback) => {
           if (value === '') {
            callback(new Error('パスワードを入力してください'));
          }
            callback();
        };
        return {
          loginForm: {
            account: 'test',
            password: '111111'
          },
          rules2: {
            account: [
              { validator: validateUsername, trigger: 'blur' }
            ],
            password: [
              { validator: validatePassword, trigger: 'blur' }
            ]
          },
          loading:false,
        };
      },
      methods: {
          errormsg(msgerror) {
          this.$message.error(msgerror);
        },
       //把获取参数加入全局store中
    change(id, username, password, deptName, userType, accessedRouters, token) {
      this.$store.commit('SET_TOKEN', token);
            this.$store.commit('SET_USER_ID', id);
            this.$store.commit('SET_ACCOUNT', this.loginForm.account);
            this.$store.commit('SET_USER_NAME', username);
            this.$store.commit('SET_DEPT_NAME', deptName);
            this.$store.commit('SET_USER_TYPE', userType);
            //メニューデータ
            this.$store.commit('SET_ROUTERS', accessedRouters);
      console.log(this.$router);
           },
      load(){
         this.loading = true;
      setTimeout(() => {
        this.loading = false;
      }, 1000);
         this.$Loading.start();
      setTimeout(() => {
        this.$Loading.finish();
      }, 1000);
      },
       doLogin(){
         var _this = this;
       this.$refs.loginForm.validate((valid) => {
       if (valid) {
               this.$http({
           method:'post',
           url:'/api/login',
            data: {
              "account": _this.loginForm.account,
            "password":_this.loginForm.password
           }
         }).then(function (response) {
                 var errorcode=response.data.errorcode;
                    if(errorcode=="200"){
                         _this.load();
              // console.log(JSON.stringify(response.data.data));
               _this.change(_this.loginForm.account,
               response.data.data.name,
               _this.loginForm.password,
                response.data.data.deptName,
                response.data.data.userType,
                  response.data.data.asyncRouterMapList,
                  response.data.data.token
               );
              //  console.table(response.data.data.asyncRouterMapList);
              //  console.log(JSON.stringify(response.data.data.asyncRouterMapList));
   //                    Cookies.set('Admin-Token', 'jin-test');
              setTimeout(() => {
                _this.$router.push({path: '/'}).catch(() => {});
              }, 1000);
                   }else if(errorcode=="500"){
                       // console.debug(response);
                       _this.errormsg(response.data.errormsg);
                   }
                 })
               .catch(function (error) {
                   console.log(error);
               });
        }
      })
       }
    }
  }
  </script>
  
  <style>
  #login{
    width: 100%;
    margin: 0 auto;
    /*position:relative;*/
    /*background-color:black;*/
  }
  #login_img{
    height: 720px;
    position:absolute
  }
  
  #login_main{
     width: 400px;
     margin:200px 50px 150px 450px;
     border:1px solid #C0CCDA;
     opacity:0.8;
     border-radius:10px;
     box-shadow:inset 0 0 1px 1px #C0CCDA;
     position:absolute;
     background-color:#F9FAFC;
  }
  #login_main_top{
    height: 60px;
    background-color:Linen;
    text-align: center;
  }
  #login_main_center{
     margin: 0px 30% 0px 0px;
     /*background-color:green;*/
  }
  
  #login_button_user{
    margin-left:90px;
  }
  </style>
  
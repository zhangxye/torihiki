<template>
	<section>
		<label>ユーザー<input type="text" v-model="username" /></label>
		パスワード<input type="password" v-model="password" />
        <button  @click="doLogin">ログイン</button>
	</section>
</template>

<script>

export default {
  name: 'hello',
  data () {
    return {
            username:'',
            password:''            
    }
  },
  computed: {
    usernameC () {
      return this.$store.state.username
    },
    passwordC () {
      return this.$store.state.password
    }
  },
  methods:{
        doLogin(){
          //vuex/store.js的mutations: {login
            this.$store.commit('login', {
            username:this.username,
            password:this.password
           })

           
            var _this = this;
            this.$http.get('/api/user'
            ,{
              params:{
                username:this.username,
                password:this.password
              }, 
            }
            )
            .then(function (response) {
              var errorcode=response.data.errorcode;
                 if(errorcode=="200"){
                     _this.$router.push({ path: '/Hello' });
                }else{
                     _this.$router.push({ path: '/Error' });
                }
              })
            .catch(function (error) {
                console.log(error);
            });
             
              
           
    }
  }
}
</script>


<style >

</style>

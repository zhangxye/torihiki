import {removeSessionItem, setSessionItem} from "@/utils/storage";

const user = {

  state: {
    user_id:'',
    account:'',
    username: 'テスト',
    password: '',
    dept_code:'',
    dept_name:'',
    address:'',
    phone:'',
    principal:'',
    email:'',
    business_scope:'',
    manager_id:'',
    islogin:false,
    roles : [],
    token: '',
    user_type : ''
  },

  // state: {
  //   token: Cookies.get('Admin-Token'),
  //   name: '',
  //   avatar: '',
  //   roles: []
  // },

  mutations: {
    SET_TOKEN: (state, token) => {
      state.token = token;
            setSessionItem('token', token);
    },
    SET_USER_ID: (state, userid) => {
      state.user_id = userid;
    },
    SET_ACCOUNT: (state, account) => {
      state.account = account;
    },
    SET_USER_NAME: (state, username) => {
      state.username = username;
    },
    SET_DEPT_NAME: (state, deptName) => {
      state.dept_name = deptName;
    },
    // SET_AVATAR: (state, avatar) => {
    //   state.avatar = avatar;
    // },
    SET_ROLES: (state, roles) => {
      state.roles = roles;
    },
    
    SET_USER_TYPE: (state, userType) => {
      state.user_type = userType;
    },

    RESET:(state) =>{
      state.user_id='';
      state.account='';
      state.username= 'テスト';
      state.password= '';
      state.dept_code='';
      state.dept_name='';
      state.address='';
      state.phone='';
      state.principal='';
      state.email='';
      state.business_scope='';
      state.manager_id='';
      state.islogin=false;
      state.roles = [];
      state.token= '';
      state.user_type='';
    }
  },

   actions: {
      //ログアウト
      LOGOUT(context){
        context.commit('RESET');
            removeSessionItem('token');
      }
  //   // 登录
  //   Login({ commit }, userInfo) {
  //     const username = userInfo.username.trim();
  //     return new Promise((resolve, reject) => {
  //       login(username, userInfo.password).then(response => {
  //         const data = response.data;
  //         Cookies.set('Admin-Token', data.token);
  //         commit('SET_TOKEN', data.token);
  //         resolve();
  //       }).catch(error => {
  //         reject(error);
  //       });
  //     });
  //   },


  //   // 获取用户信息
  //   GetInfo({ commit, state }) {
  //     return new Promise((resolve, reject) => {
  //       getInfo(state.token).then(response => {
  //         const data = response.data;
  //         commit('SET_ROLES', data.role);
  //         commit('SET_NAME', data.name);
  //         commit('SET_AVATAR', data.avatar);
  //         resolve(response);
  //       }).catch(error => {
  //         reject(error);
  //       });
  //     });
  //   },

  //   // 登出
  //   LogOut({ commit, state }) {
  //     return new Promise((resolve, reject) => {
  //       logout(state.token).then(() => {
  //         commit('SET_TOKEN', '');
  //         commit('SET_ROLES', []);
  //         Cookies.remove('Admin-Token');
  //         resolve();
  //       }).catch(error => {
  //         reject(error);
  //       });
  //     });
  //   },

  //   // 前端 登出
  //   FedLogOut({ commit }) {
  //     return new Promise(resolve => {
  //       commit('SET_TOKEN', '');
  //       Cookies.remove('Admin-Token');
  //       resolve();
  //     });
  //   }
   }
};

export default user;

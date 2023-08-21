// The Vue build version to load with the `import` command
import Vue from 'vue'
import Router from 'vue-router'

//import Element from 'element-ui'
import ElementUI from 'element-ui'
import locale from 'element-ui/lib/locale/lang/ja'
import 'element-ui/lib/theme-default/index.css'
import iView from 'iview'
import NProgress from 'nprogress'
import 'iview/dist/styles/iview.css'

import App from './App.vue'
import router from './router'
import axios from 'axios'
import store from './vuex/store'
import moment from 'moment'
import _ from 'lodash'
import validator from 'validator'
//import formValidator from '@/utils/formValidator'
//import Icon from 'vue-svg-icon/Icon.vue'
import filter from '@/utils/filter'
import utils from '@/utils/utils'
import bussComm from '@/utils/bussComm'
import '@/assets/iconfont/iconfont'
import IconSvg from '@/components/Icon-svg/index.vue'
import BackToTop from '@/components/BackToTop/backToTop.vue'
import {getSessionItem} from "@/utils/storage";

Vue.use(Router);
//Vue.use(Element)
Vue.use(ElementUI, { locale });
Vue.use(iView);
axios.interceptors.request.use(
    config => {
        if (getSessionItem('token')) {  // 判断是否存在token，如果存在的话，则每个http header都加上token
            config.headers.Authorization = `${getSessionItem('token')}`;
        }
        return config;
    },
    err => {
        return Promise.reject(err);
    });
Vue.prototype.$http = axios;
Vue.config.productionTip = false;
Vue.prototype.$moment = moment;
// Vue.prototype.$_ = _;
Object.defineProperty(Vue.prototype, '$_', { value: _ });
Vue.prototype.$valid = validator;

Vue.prototype.$utils = utils;
Vue.prototype.$bc = bussComm;
//Vue.prototype.$formValid = formValidator;

// Vue.component('icon', Icon);
// Icon.inject('home-normal'); // Home Icon
Vue.component('icon-svg', IconSvg);
Vue.component('back-to-top', BackToTop);

// Vue.config.debug = true  //开启debug模式
// window.log = console.log

const whiteList = ['/login'];
router.beforeEach((to, from, next) => {
  NProgress.start();
    if (getSessionItem('token')) {
    if (to.path === '/login') {
      next({ path: '/' });
    } else {
      if (store.getters.roles.length === 0) {
        store.getters.permission_routers.map((item) => {
          router.addRoute(item);
        });
           store.commit('SET_ROLES', ['admin']);
            //  router.addRoutes(store.getters.addRouters);
        // initMenu(router, store);


        next({...to, replace: true});
          //  });
       } else {
         next();
       }
    }
  } else {
    if (whiteList.indexOf(to.path) !== -1) {
      next()
    } else {
      next('/login');
      NProgress.done();
    }
  }
});

router.afterEach(() => {
  NProgress.done();
});

Vue.config.productionTip = false
// Vue.filter('formatAmt',function(value){return utils.formatMoney(value ,0 ,"￥")});

/* eslint-disable no-new */
new Vue({
  // el: '#app',
  router,
  store,
  // template: '<App/>',
  filters: filter,
  // components: { App },
  render: h => h(App)
}).$mount('#app')

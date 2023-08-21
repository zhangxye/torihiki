import Vue from 'vue'
import Router from 'vue-router'

import Layout from '../webedi/layout/Layout.vue';
import Temp from '../webedi/Template.vue';
import Login from '../webedi/Login.vue';
import dashboard from '../webedi/dashboard/index.vue';
import Err404 from '../webedi/404.vue';
import UserEnt from '../webedi/user/UserEnt.vue';
import UserInq from '../webedi/user/UserInq.vue';
import UserPassword from '../webedi/user/UserPassword.vue';
import ResetPassword from '../webedi/user/ResetPassword.vue';
import ApprDocEnt from '../webedi/apprDoc/ApprDocEnt.vue';
import ApprDocInq from '../webedi/apprDoc/ApprDocInq.vue';
import ApprDocDetail from '../webedi/apprDoc/ApprDocDetail.vue';
import ApprDocUpd from '../webedi/apprDoc/ApprDocUpd.vue';
import ApprDocConfirm from '../webedi/apprDoc/ApprDocConfirm.vue';
import ApprDocDownLoad from '../webedi/apprDoc/ApprDocDownLoad.vue';
import EstEntInq from '../webedi/est/EstEntInq.vue';
import EstEnt from '../webedi/est/EstEnt.vue';
import EstInq from '../webedi/est/EstInq.vue';
import EstUpd from '../webedi/est/EstUpd.vue';
import EstDetail from '../webedi/est/EstDetail.vue';
import OrderEnt from "@/webedi/order/OrderEnt.vue";
import OrderInq from "@/webedi/order/OrderInq.vue";
import OrderUpd from "@/webedi/order/OrderUpd.vue";
import OrderDetail from "@/webedi/order/OrderDetail.vue";
import OrderConfirm from "@/webedi/order/OrderConfirm.vue";
import OrderEntInq from "@/webedi/order/OrderEntInq.vue";
import MonReqInq from "@/webedi/salesReq/MonReqInq.vue";
import RoleUpd from "@/webedi/role/RoleUpd.vue";
import RoleInq from "@/webedi/role/RoleInq.vue";
import RoleEnt from "@/webedi/role/RoleEnt.vue";
import RoleDetail from "@/webedi/role/RoleDetail.vue";
import WorkerInq from "@/webedi/worker/WorkerInq.vue";
import ClientStaffEnt from '@/webedi/client/ClientStaffEnt.vue';
import ClientStaffInq from '@/webedi/client/ClientStaffInq.vue';
import ClientStaffUpd from '@/webedi/client/ClientStaffUpd.vue';
import ClientStaffConfirm from '@/webedi/client/ClientStaffConfirm.vue';

Vue.use(Router);

const componentsMap = {
    "Layout": Layout,
    "Temp": Temp,                //開発中テンプレート
    "Login": Login,               //ログイン
    "dashboard": dashboard,           //ホームページ
    "UserEnt": UserEnt,             //ユーザー登録
    "UserInq": UserInq,             //ユーザー検索
    "UserPassword": UserPassword,        //パスワード変更
    "ResetPassword": ResetPassword,       //パスワードリセット
    "ApprDocEnt": ApprDocEnt,          //稟議書登録
    "ApprDocInq": ApprDocInq,          //稟議書検索
    "ApprDocDetail": ApprDocDetail,       //稟議書詳細
    "ApprDocUpd": ApprDocUpd,          //稟議書更新
    "ApprDocConfirm": ApprDocConfirm,      //稟議書確認
    "ApprDocDownLoad": ApprDocDownLoad,     //月次処理用の稟議書データダウンロード
    "EstEntInq": EstEntInq,           //見積検索登録
    "EstEnt": EstEnt,              //見積登録
    "EstInq": EstInq,              //見積検索
    "EstDetail": EstDetail,           //見積詳細
    "EstUpd": EstUpd,              //見積更新
    "OrderEntInq": OrderEntInq,         //受注検索登録
    "OrderEnt": OrderEnt,            //受注登録
    "OrderInq": OrderInq,            //受注検索
    "OrderDetail": OrderDetail,         //受注詳細
    "OrderUpd": OrderUpd,            //受注更新
    "OrderConfirm": OrderConfirm,        //受注確定
    "MonReqInq": MonReqInq,           //月次請求一覧

    "RoleEnt": RoleEnt,             //ロール登録
    "RoleInq": RoleInq,             //ロール検索
    "RoleDetail": RoleDetail,          //ロール詳細
    "RoleUpd": RoleUpd,             //ロール更新
    
    "WorkerInq": WorkerInq,          //要員検索

    "ClientStaffEnt": ClientStaffEnt,          //取引先担当登録
    "ClientStaffInq": ClientStaffInq,          //取引先担当検索
    // "ClientStaffDetail": ClientStaffDetail,       //取引先担当詳細
    "ClientStaffUpd": ClientStaffUpd,          //取引先担当更新
    "ClientStaffConfirm": ClientStaffConfirm,      //取引先担当確認


    "Err404": Err404              //エラー画面
};

export const constantRouterMap = [
    {path: '/login', component: componentsMap.Login, hidden: true, meta: {keepAlive: false}},
    {path: '/404', component: componentsMap.Err404, hidden: true},
    {
        path: '/',
        component: componentsMap.Layout,
        redirect: '/dashboard',
        name: 'Home',
        hidden: true,
        meta: {keepAlive: false},
        children: [{path: 'dashboard', name: '掲示板', component: dashboard}]
    }
];

export default new Router({
    // mode: 'history', //后端支持可开
    mode: 'hash',
    scrollBehavior: () => ({y: 0}),
    routes: constantRouterMap
});

//Compenentの名称から対象へ変換処理
export function convertMenu(x_menu_list = [], routes = [], level = 1, father = null) {
    for (let i = 0, len = x_menu_list.length; i < len; i++) {
        let item = x_menu_list[i];
        if (typeof item.component !== "undefined") {
            if (typeof componentsMap[item.component] !== "undefined") {
                item.component = componentsMap[item.component];
            } else {
                item.component = Temp;
            }
        }

        if (typeof item.children !== "undefined") {
            convertMenu(item.children, routes, level + 1, item.path);
            }

        if (level === 1) routes.push(item);
        else {
            if (father !== null) {
                item.path = father + "/" + item.path;
        }
        }

    }//end for
    return routes;
}





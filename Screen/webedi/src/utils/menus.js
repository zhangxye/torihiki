import {constantRouterMap, convertMenu} from "@/router";

export const initMenu = (router, store) => {
    if (store.state.routers === undefined) {
        this.$http({
            method: 'get',
            url: '/api/mapping',
        }).then(res => {
            const p_routers = convertMenu(res.data);
            store.state.routers = constantRouterMap.concat(p_routers).concat({
                path: '*',
                redirect: '/404',
                hidden: true
            });
            store.state.routers.forEach(data => {
                if (data) {
                    router.addRoute(data);
                }
            })
        })
    }

}
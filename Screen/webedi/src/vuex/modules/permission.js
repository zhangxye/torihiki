import {constantRouterMap, convertMenu} from '@/router';
import {getSessionItem, setSessionItem} from "@/utils/storage";


const permission = {
    state: {
        routers: getSessionItem('routers'),
        addRouters: []
    },
    mutations: {
        SET_ROUTERS: (state, routers) => {
            const p_routers = convertMenu(routers);
            state.addRouters = p_routers;
            state.routers = constantRouterMap.concat(p_routers).concat({path: '*', redirect: '/404', hidden: true});
            setSessionItem('routers', state.routers)
        },
    },
    actions: {}
};

export default permission;

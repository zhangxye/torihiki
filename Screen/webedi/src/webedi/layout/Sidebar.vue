<template>
    <el-menu style="background-color:rgb(41, 106, 201)" mode="vertical" theme="dark" :default-active="$route.path">

   　　 <router-link class="menu-indent" :to="'/dashboard'">
        <el-menu-item style="background-color:beige;" index="1">
        <icon-svg icon-class="home"/>
        トップページ
         </el-menu-item>
         </router-link>
    <sidebar-item :routes="getSessionItem('routers')"></sidebar-item>
    </el-menu>
</template>

<script>
    import { mapGetters } from 'vuex';
    import SidebarItem from './SidebarItem.vue';
import {getSessionItem} from "@/utils/storage";

    export default {
  methods: {getSessionItem},
      components: { SidebarItem },
      computed: {
        ...mapGetters([
          'permission_routers'
        ])
      }
    }
</script>

<style rel="stylesheet/scss" lang="scss" scoped>
.el-menu {
    min-height: 100%;
}

</style>

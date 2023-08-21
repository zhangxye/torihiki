<template>
    <div>
        <template v-for="item in routes">
          <router-link v-if="!item.hidden&&item.noDropdown&&item.children.length>0" :to="item.children[0].path"
                       :key="item.path">
            <el-menu-item :index="item.children[0].path">
              <icon-svg v-if='item.icon' :icon-class="item.icon"/>
              {{ item.children[0].name }}
                </el-menu-item>
            </router-link>
            <el-submenu :index="item.name" v-if="!item.noDropdown&&!item.hidden" :key="item.path">
                <template slot="title">
                    <icon-svg v-if='item.icon' :icon-class="item.icon" /> {{item.name}}
                </template>
                <!-- <template v-for="child in item.children" v-if='!child.hidden' > -->
                <template v-for="child in item.children">
                    <template v-if="!child.hidden"> 
                <sidebar-item class='menu-indent' v-if='child.children&&child.children.length>0' :routes='[child]'
                              :key="child.path"></sidebar-item>
                <router-link v-else class="menu-indent" :to="child.path" :key="child.path+'1'">
                  <el-menu-item :index="child.path">
                            {{child.name}}
                        </el-menu-item>
                        </router-link>
                    </template>
                </template>
            </el-submenu>
        </template>
    </div>
</template>

<script>
// import Template from '../Template.vue'
    export default {
//   components: { Template },
      name: 'SidebarItem',
      props: {
        routes: {
          type: Array
        }
      }
    }
</script>

<style rel="stylesheet/scss" lang="scss" scoped>
    .svg-icon {
        margin-right: 10px;
    }
    .hideSidebar .menu-indent{
        display: block;
        text-indent: 10px;
    }
</style>


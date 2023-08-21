<template>
    <el-menu class="navbar" mode="horizontal">
        <hamburger class="hamburger-container" :toggleClick="toggleSideBar" :isActive="sidebar.opened"></hamburger>
        <levelbar></levelbar>
        <div class="avatar-container">
            <h3>
            <icon-svg icon-class="user"></icon-svg>{{username}}
            <icon-svg icon-class="logout"></icon-svg>
            <a href="javascript:void(0);" @click="logout">
            ログアウト
            </a>
            </h3>
        </div>
 <!--        <el-dropdown class="avatar-container" trigger="click">
            <div class="avatar-wrapper">
                <i class="el-icon-caret-bottom"></i>
            </div>
            <el-dropdown-menu class="user-dropdown" slot="dropdown">
                <router-link class='inlineBlock' to="/">
                    <el-dropdown-item>
                        Home
                    </el-dropdown-item>
                </router-link>
                <el-dropdown-item divided><span @click="logout" style="display:block;">ログアウト</span></el-dropdown-item>
            </el-dropdown-menu>
        </el-dropdown>
 -->    </el-menu>
</template>

<script>
    import { mapGetters } from 'vuex';
    import Levelbar from './Levelbar.vue';
    import Hamburger from '@/components/Hamburger/index.vue';

    export default {
      components: {
        Levelbar,
        Hamburger
      },
      computed: {
        ...mapGetters([
          'sidebar',
 //         'avatar'
           'username',
          'roles'
        ])
      },
      methods: {
        toggleSideBar() {
          this.$store.dispatch('ToggleSideBar')
        },
        // logout() {
        //   this.$store.dispatch('LogOut').then(() => {
        //     location.reload();  // 为了重新实例化vue-router对象 避免bug
        //   });
        // }
        logout(){
        this.$alert('ログアウトしてもよろしいでしょうか？', '提示', {
              showCancelButton: true,
              confirmButtonText: 'はい',
              cancelButtonText: 'いいえ',
              type: 'warning'
            }).then(() => {
                var data='account='+this.$store.state.user_id+'&password='+this.$store.state.password;
                this.$http.post('/api/logout',data);
                // this.$store.commit('SET_TOKEN', '');
                this.$store.dispatch('LOGOUT');
                this.$router.push('/login');
            }).catch(() => {
            });
      }
      }
    }
</script>

<style rel="stylesheet/scss" lang="scss" scoped>
    .navbar {
        background-color:rgb(242, 242, 242);
        height: 50px;
        line-height: 50px;
        border-radius: 0px !important;
        .hamburger-container {
            line-height: 58px;
            height: 50px;
            float: left;
            padding: 0 10px;
        }
        .errLog-container {
            display: inline-block;
            position: absolute;
            right: 150px;
        }
        .screenfull {
            position: absolute;
            right: 90px;
            top: 16px;
            color: red;
        }
        .avatar-container {
            height: 50px;
            display: inline-block;
            position: absolute;
            right: 35px;
            .avatar-wrapper {
                cursor: pointer;
                margin-top: 5px;
                position: relative;
                .user-avatar {
                    width: 40px;
                    height: 40px;
                    border-radius: 10px;
                }
                .el-icon-caret-bottom {
                    position: absolute;
                    right: -20px;
                    top: 25px;
                    font-size: 12px;
                }
            }
        }
    }
</style>




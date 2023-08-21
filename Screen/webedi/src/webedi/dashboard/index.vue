<template>
  <div class="dashboard-container">
    <span class='dashboard-text'>name:{{username}}</span>
    <span class='dashboard-text'>(role:<span v-for='role in roles' :key='role'>{{role}})</span></span>
    <span class='dashboard-text'>(部署:{{dept_name}})</span>

   <div class='dashboard-text'>お知らせ </div>
    <el-carousel indicator-position="outside">
    <el-carousel-item v-for="item in notifyList" :key="item.value">
      <h3>{{ item.content }}</h3>
    </el-carousel-item>
  </el-carousel>
</div>
</template>

<script>
    import { mapGetters } from 'vuex';
    export default {
      data(){
       return { notifyList : [
        {value:1, content:'お知らせ１'},
        {value:2, content:'お知らせ２'},
        {value:3, content:'お知らせ３'},
        {value:4, content:'お知らせ４'}
        ]
      }
      },
      name: 'dashboard',
      computed: {
        ...mapGetters([
          'username',
          'roles',
         'dept_code',
         'dept_name'
        ])
      }
    }
</script>

<style rel="stylesheet/scss" lang="scss">
.dashboard {
  &-container {
    margin: 30px;
  }
  &-text {
    font-size: 30px;
    line-height: 46px;
  }
}
  .el-carousel__item h3 {
    color: #475669;
    font-size: 18px;
    opacity: 0.75;
    line-height: 300px;
    margin: 0;
  }

  .el-carousel__item:nth-child(2n) {
    background-color: #99a9bf;
  }

  .el-carousel__item:nth-child(2n+1) {
    background-color: #d3dce6;
  }
</style>

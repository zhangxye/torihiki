<template>
<div>
    以下のデータの操作:
  　 <el-button
      size="mini"
      type="success"
      :disabled="$router.currentRoute.path.search(/RoleDetail$/)>0"
      @click="goToDetail">詳細
    </el-button>
    <el-button
      size="mini"
      type="success"
      :disabled="$router.currentRoute.path.search(/RoleUpd$/)>0"
      @click="goToUpdate">編集
    </el-button>
    <el-button
      size="mini"
      type="success"
      @click="goToList">一覧へ戻る
    </el-button>
</div>
</template>

<script>
//import {} from '@/webedi/'
export default {
  props: {
    //ロールID
    roleId : {
      required: true
    }
  },//props end

  methods:{
    goToDetail() {
        this.$router.push({
          path: 'RoleDetail',
          query: {
            'role_id': this.roleId
          }
        });
      },

    goToUpdate() {
        this.$router.push({
          path: 'RoleUpd',
          query: {
            'role_id': this.roleId
          }
        });
      },
    goToList() {
        this.$router.push({
          path: 'RoleInq'
        });
      }
  },//methods end
}
</script>

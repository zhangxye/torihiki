<template>
<div>
    以下のデータの操作:
  　 <el-button
      size="mini"
      type="success"
      :disabled="$router.currentRoute.path.search(/EstDetail$/)>0"
      @click="goToDetail">詳細
    </el-button>
    <el-button
      size="mini"
      type="success"
      :disabled="$router.currentRoute.path.search(/EstUpd$/)>0 || updMenuDisableF"
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
    //見積ID
    est_hid : {
      required: true
    },
    updMenuDisableF:{
      type:Boolean,
      default : function (){
        return false
      }
    }
  },//props end

  methods:{
    goToDetail() {
        this.$router.push({
          path: 'EstDetail',
          query: {
            'est_hid': this.est_hid
          }
        });
      },

    goToUpdate() {
        this.$router.push({
          path: 'EstUpd',
          query: {
            'est_hid': this.est_hid
          }
        });
      },
    goToList() {
        this.$router.push({
          path: 'EstInq'
        });
      }
  },//methods end
}
</script>

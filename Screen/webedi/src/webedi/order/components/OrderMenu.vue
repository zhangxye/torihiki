<template>
<div>
    以下のデータの操作:
  　 <el-button
      size="mini"
      type="success"
      :disabled="$router.currentRoute.path.search(/OrderDetail$/)>0"
      @click="goToDetail">詳細
    </el-button>
    <el-button
      size="mini"
      type="success"
      :disabled="$router.currentRoute.path.search(/OrderUpd$/)>0 || updMenuDisableF"
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
    order_id : {
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
          path: 'OrderDetail',
          query: {
            'order_id': this.order_id
          }
        });
      },

    goToUpdate() {
        this.$router.push({
          path: 'OrderUpd',
          query: {
             'order_id': this.order_id
          }
        });
      },
    goToList() {
        this.$router.push({
          path: 'OrderInq'
        });
      }
  },//methods end
}
</script>

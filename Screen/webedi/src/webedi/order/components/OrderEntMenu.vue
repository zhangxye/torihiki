<template>
<div>
    以下のデータの操作:
    <el-button
      size="mini"
      type="success"
      @click="goToList">一覧へ戻る
    </el-button>
</div>
</template>

<script>
export default {
  props: {
  },//props end

  methods:{
    goToList() {
        this.$router.push({
          path: 'OrderEntInq'
        });
      }
  },//methods end
}
</script>

<template>
<div>
     <el-table :data="list_data"
      border
      stripe>
      <el-table-column fixed width="60" label="NO">
      <template slot-scope="scope">
      {{(scope.$index + 1)}}
      </template>
      </el-table-column>
        <el-table-column
        fixed
        prop="appr_doc_no"
        label="稟議書NO"
        width="120">
        <template slot-scope="scope">
          <el-button type="text"
          @click="goToDetail(scope.$index,scope.row)" size="mini">
          {{scope.row.appr_doc_no}}
        </el-button>
        </template>
        </el-table-column>
        <el-table-column
        fixed
        prop="emp_no"
        label="社員ｺｰﾄﾞ"
        width="100"/>
        <el-table-column
        fixed
        prop="emp_nm"
        label="氏名"
        width="150"/>
        <el-table-column
        fixed
        label="基準単価"
        :formatter="formatAmt"
        prop="sell_price"
        width="120"/>
        <el-table-column
        label="上限時間"
        prop="wh_max"
        width="100"/>
        <el-table-column
        label="下限時間"
        prop="wh_min"
        width="100"/>
        <el-table-column
        label="中割時間"
        prop="wh_mid"
        width="100"/>
        <el-table-column
        label="超過単価"
        :formatter="formatAmt"
        prop="excess_price"
        width="110"/>
        <el-table-column
        label="控除単価"
        :formatter="formatAmt"
        prop="deducation_price"
        width="110"/>
    </el-table>
</div>
</template>

<script>
export default {
  props: {
      list_data:{
        type: Array,
        required:true
      }
  },//props end

  methods:{
      formatAmt(row, column, cellValue){
            return this.$utils.formatMoney(cellValue);
      },

      //照会画面へ遷移
       goToDetail(index , row){
        this.$router.push({ path: '/ApprDoc/ApprDocDetail', query: { appr_doc_no: row.appr_doc_no }});
      },
  },//methods end
}
</script>

<template>
<el-dialog
   title="担当者選択"
   size ="mini"
   :close-on-press-escape="false">
  <!-- v-model="client_staff_dlg.show_flg"> -->

     <el-table :data="client_staff_dlg.data_list" id="client_staff_dlg_id" height="400">
        <el-table-column label="NO" width="60">
            <template slot-scope="scope">
            {{scope.$index+1}}
            </template>
       </el-table-column>
        <el-table-column label="担当者ID" width="100">
            <template slot-scope="scope">
            <el-button type="text"
              @click="returnVal(scope.$index,scope.row)"
              size="mini">
              {{scope.row.client_staff_id}}
            </el-button>
            </template>
       </el-table-column>

        <el-table-column
        label="担当者名称"
        prop="client_staff_nm"
        width="200">
       </el-table-column>
        <el-table-column
        label="フリガナ"
        prop="client_staff_nm_fuli"
        width="200">
       </el-table-column>
        <el-table-column
        label="所属部署"
        prop="client_staff_dept_nm"
        width="300">
       </el-table-column>
        <el-table-column
        label="職位"
        prop="client_staff_position"
        width="200">
       </el-table-column>
        <el-table-column
        label="電話番号"
        prop="client_staff_tel_no"
        width="150">
       </el-table-column>
        <el-table-column
        label="FAX番号"
        prop="client_staff_fax_no"
        width="150">
       </el-table-column>
        <el-table-column
        label="携帯"
        prop="client_staff_mobile_no"
        width="150">
       </el-table-column>
        <el-table-column
        label="メールアドレス"
        prop="client_mail_addr"
        width="200">
       </el-table-column>
        <el-table-column
        label="住所"
        prop="client_staff_addr"
        width="250">
       </el-table-column>
        <el-table-column
        label="郵便番号"
        prop="client_staff_post_no"
        width="150">
       </el-table-column>
      </el-table>
  </el-dialog>
</template>

<script>
    export default {
      props: {

        //稟議書データ
        client_staff_dlg : {
          type: Object,
          required: true,
          default: function () {
            return {
                data_list : [],
                show_flg : false,
                callback_fun : undefined
                };
          }
        }
      },//props end

      created() {

      },//created end

      data() {
        return {
        }
      },//data end

      methods: {

          returnVal(x_index,x_ret_data){
          // this.client_staff_dlg.show_flg = false;
          this.client_staff_dlg.callback_fun.call(this,x_ret_data);
      },

      },//methods end

      computed: {

      }//computed end

    };
</script>

<style scoped>
</style>

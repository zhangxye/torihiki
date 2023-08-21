<template>
  <div id="RoleInq">
    <el-form
      class="RoleInqTopForm"
      :inline="true"
      :model="filters">
      <el-row>
        <el-form-item label="ロールID">
          <el-input v-model="filters.role_id" placeholder="前方一致"></el-input>
        </el-form-item>
         <el-form-item label="ロール名称">
          <el-input v-model="filters.role_nm" placeholder="部分一致"></el-input>
        </el-form-item>
      </el-row>

      <el-row>
      <el-col :span="6" >
      <el-form-item
      label-width="80px"
      label="改頁件数"
      prop="pageSize">
      <el-select
      size="small"
      v-model="page_info.pageSize"
      style="width:100px"
      @change="handleChgPageSize"
      placeholder="改頁件数" >
        <el-option
        v-for="item in pageSize_list"
        :key="item"
        :label="item+'件'"
        :value="item">
        </el-option>
      </el-select>
      </el-form-item >
      </el-col>
      <el-col :span="16" >
      &nbsp;
      </el-col>
      <el-col :span="2" >
      <el-form-item>
          <el-button type="primary" @click="searchData" icon="search">検索</el-button>
       </el-form-item>
      </el-col>
    </el-row>
    </el-form>

    <el-table :data="page_info.pageData"
      height="400"
      border
      witdh="500"
      id="RoleInqMiddle">
    <el-table-column width="60" label="NO">
    <template slot-scope="scope">
    {{(scope.$index +  1)+ (page_info.pageNo - 1) *page_info.pageSize}}
    </template>
    </el-table-column>
        <el-table-column
        prop="role_id"
        label="ロールID"
        width="100"/>
        <el-table-column
        prop="role_nm"
        label="ロール名称"
        width="300"/>
        <el-table-column
        width="160"
        label="操作">
        <template slot-scope="scope">
        <el-button type="info"
        @click="goToDetail(scope.$index,scope.row)" size="mini" icon="view">詳細</el-button>
        <el-button type="danger"
        @click="delRoleData(scope.$index,scope.row)" size="mini" :disabled="scope.row.del_flg === 'Y'" icon="delete">削除</el-button>
        </template>
      </el-table-column>
    </el-table>
  <el-pagination
    layout="prev, pager, next"
    :current-page="page_info.pageNo"
    :total="page_info.total"
    :page-size="page_info.pageSize"
     @current-change="handleCurrentChange">
   </el-pagination>

  </div>
</template>

<script>
  export default {
    data(){
      return {

        filters:{
          role_id: '',
          role_nm: ''
        },//filters end

        pageSize_list:[],

        page_info:{
          pageNo:1,
          pageSize:10,
          total:20,
          pageData:[]
        },//page_info end

        result_list:[],
      }
    },//data() end

    methods:{
      //
      handleCurrentChange(val) {
        this.page_info.pageNo = val;
        this.getList();
      },
      //改頁件数を選択
      handleChgPageSize(val) {
        this.getList();
      },
     searchData() {
        this.page_info.pageNo = 1;
        this.getList();
      },

        //詳細画面へ遷移
       goToDetail(index , row){
        this.$router.push({ path: 'RoleDetail', query: { role_id: row.role_id }});
      },

      delRoleData(index , row){
        var compiled = this.$_.template('ロールデータ(ロールID：<%= x_role_id %>)を削除してもよろしいでしょうか？');
        var confirmMsg = compiled({ 'x_role_id':  row.role_id});
        this.$confirm(confirmMsg, '注意', {
          confirmButtonText: '確定',
          cancelButtonText: '中止',
          type: 'warning'
        }).then(() => {
        	var _this = this;
        	   _this.$http.delete('/api/role/delete',
				 {params:{"role_id":row.role_id}})
        	   .then(function (response) {
                    var errorcode=response.data.errorcode;
                      if(errorcode=="200"){
                          _this.page_info.pageData.splice(index,1);
				          _this.$message({
				            type: 'success',
				            message: '削除が成功しました!'
				          });
                      }else if(errorcode=="500"){
				          _this.$message({
				            type: 'error',
				            message: '削除が失敗しました!['+response.data.errormsg+']'
				          });
                      }
                    })
                  .catch(function (error) {
                      console.log(error);
                  });
        }).catch(() => {
          this.$message({
            type: 'info',
            message: '削除が中止しました！'
          });
        });
      },

   getPageSizeList(){
    this.pageSize_list = [ 5, 10, 20, 50, 100 ];
   },

   getList(){
    //var startLine = (this.page_info.pageNo - 1) *this.page_info.pageSize;
    //var endLine = startLine + this.page_info.pageSize
    //this.page_info.pageData = this.$_.slice(this.resultList,startLine,endLine);
 		      var _this = this;
          this.$http.get('/api/role/select'
            ,{
              params:{
              	page:_this.page_info.pageNo,
              	page_size:_this.page_info.pageSize,
                role_id:this.$_.toString(_this.filters.role_id),
				        role_nm:_this.filters.role_nm
              }
            }
            )
            .then(function (response) {
              var errorcode=response.data.errorcode;
                 if(errorcode=="200"){
                    _this.page_info=response.data.data;
                }else if(errorcode=="500"){
                   _this.$message.error(response.data.errormsg);
                }
              })
            .catch(function (error) {
                console.log(error);
            });
   },

    },//methods end

    mounted(){
      this.getPageSizeList();
      this.getList();
    }//mounted end

  }
</script>

<style scoped>
#RoleInq{
  width: 90%;
  margin-top: 25px;
  margin-left:30px;
}

.RoleInqTopForm{
  margin-top: 12px;
  margin-left: 10px;
}
#RoleInqMiddle{
  margin-top: 10px;
}
</style>

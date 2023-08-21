<template>
  <div id="RoleDetail">
    <back-to-top text="トップへ戻る"></back-to-top>
    <el-form :inline="true" :model="roleInfo" ref="roleInfo" label-width="120px">
        <el-row>
        <el-col :span="4">
          <h3>【ロール管理】</h3>
        </el-col>
        </el-row>
    <br>
    <role-menu :role-id="roleInfo.role_id"></role-menu>
    <br>
    <el-form-item
    label-width="80px"
    label="ロールID"
    prop="role_id">
      {{roleInfo.role_id}}
    </el-form-item>
    <el-form-item
    label="ロール名称"
    prop="role_nm">
      {{roleInfo.role_nm}}
    </el-form-item>
    <hr>
    <br>
    <el-row>
    <h4>【メニュー】</h4>
    <el-tree
    :data="roleInfo.menu_list"
    :default-expand-all="false"
    :render-content="renderContent"
    node-key="id">
    </el-tree>
    </el-row>
    </el-form>
    <br>
    <el-col :span="24">
      <span id="apply_button">
        <el-button
              type="danger"
              :disabled="roleInfo.del_flg"
              @click="delRoleData">削除
        </el-button>
      </span>
      <span id="apply_button">
        <el-button
          type="success"
          @click="goToSimilarEnt">コピー
        </el-button>
      </span>
    </el-col>
  </div>
</template>

<script>
import { RoleMenu } from '@/webedi/role/components';
  export default {

      components: {
        'role-menu' : RoleMenu
      },

    data() {
      return {
        roleInfo:{},
      }

    },//data() end

    methods: {
      //削除処理
      delRoleData(){
        var p_role_id = this.roleInfo.role_id
        var compiled = this.$_.template('ロールデータ(ロールID：<%= x_role_id %>)を削除してもよろしいでしょうか？');
        var confirmMsg = compiled({ 'x_role_id':  p_role_id});
        this.$confirm(confirmMsg, '注意', {
          confirmButtonText: '確定',
          cancelButtonText: '中止',
          type: 'warning'
        }).then(() => {
        	var _this = this;
        	   _this.$http.delete('/api/role/delete',
				 {params:{"role_id":p_role_id}})
        	   .then(function (response) {
                    var errorcode=response.data.errorcode;
                      if(errorcode=="200"){
                      _this.goToList();
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

     goToSimilarEnt() {
        this.$router.push({
          path: 'RoleEnt',
          query: {
            role_id: this.roleInfo.role_id
          }
        });
      },

    goToList() {
        this.$router.push({
          path: 'RoleInq'
        });
      },

    loadRoleInfo() {
        var _this = this;
        _this.$http.get('/api/role/detail', {
            params: {
              'role_id': _this.$route.query.role_id
            }
          })
          .then(function(response) {
            var errorcode = response.data.errorcode;
            if (errorcode == "200") {
              _this.roleInfo = response.data.data;
              //  console.log(JSON.stringify(_this.roleInfo));

            } else if (errorcode == "500") {
              _this.$message.error(response.data.errormsg);
            }
          })
          .catch(function(error) {
            console.log(error);
          });
      },

        renderContent(h, { node, data, store }) {

              if(data.isMenu===true){
                return ( <span>{node.label}({data.path})</span>);
              }

              if(data.displayFlg ==='N'){
                  return ( <span>{node.label}(メニュー非表示)</span>);
              }else{
                  return ( <span>{node.label}</span>);
              }


      }

    },//methods end

    mounted() {
      this.loadRoleInfo();
    }
  }

</script>

<style>
#RoleDetail{
  width: 90%;
  margin-top: 25px;
  margin-left:30px;
}
</style>

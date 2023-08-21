<template>
<div id="role_info">
  <el-form
  :inline="true"
  :rules="rules"
  :model="roleInfo"
  ref="roleInfo"
  label-width="120px"
  >
  <h3>【ロール管理】</h3>
    <el-form-item
    label="ロールID"
    label-width="80px"
    prop="role_id">
      <el-input
      size="small"
      type="text"
      style="width:100px;"
      v-model="roleInfo.role_id"
      auto-complete="off"
      :disabled="true"
      >
      </el-input>
    </el-form-item>
    <el-form-item
    label="ロール名称"
    prop="role_nm">
      <el-input
      size="small"
      :maxlength="50"
      type="text"
      style="width:300px;"
      v-model="roleInfo.role_nm"
      auto-complete="on"
      >
      </el-input>
    </el-form-item>
    <hr>
    <br>
    <el-row>
    <h4>【メニュー】</h4>
    <el-table
      border
      :data="roleInfo.menu_list">
      <el-table-column
        property="menu_path"
        :width="250"
        label="メニューパス">
        <template slot-scope="scope">
            <el-form-item
            :prop='"menu_list[" + scope.$index + "].menu_path"'
            :rules="[
                     { required: true, message: '入力必須です', trigger: 'blur' },
                     { pattern: /^[A-Za-z0-9]+$/, message: '半角英数のみを入力してください', trigger: 'blur' },
                   ]"
            >
              <el-input
              size="small"
              :maxlength="20"
              type="text"
              style="width:200px;"
              v-model="scope.row.menu_path"
              placeholder="半角英数のみ"
              auto-complete="on"
              >
              </el-input>
            </el-form-item>
        </template>
        </el-table-column>
      <el-table-column
        property="menu_nm"
        label="メニュー名称">
        <template slot-scope="scope">
            <el-form-item
            :prop='"menu_list[" + scope.$index + "].menu_nm"'
            :rules="[
                     { required: true, message: '入力必須です', trigger: 'blur' },
                   ]"
            >
              <el-input
              :maxlength="30"
              size="small"
              type="text"
              style="width:300px;"
              v-model="scope.row.menu_nm"
              auto-complete="on"
              >
              </el-input>
            </el-form-item>
        </template>
        </el-table-column>
        <el-table-column
          :width="150"
          label="ﾌﾟﾛｸﾞﾗﾑﾘｽﾄ数">

          <template slot-scope="scope">
            <el-button type="info"
              @click="pgListUpd(scope.$index,scope.row)"
              size="mini"
              icon="edit">
              {{scope.row.program_list.length}}
              </el-button>
          </template>
        </el-table-column>
        <el-table-column
        :width="200"
        label="操作" >
        <template slot-scope="scope">
       <el-button type="danger"
        @click="delMenu(scope.$index,scope.row)" size="mini" icon="delete">削除</el-button>
        <el-button type="success"
        @click="addMenu(scope.$index,scope.row)" size="mini" icon="plus">追加</el-button>
         </template>
    </el-table-column>
    </el-table>
    </el-row>
    <br>
    <hr>
    <br>
    <template v-if="sel_menu_index >= 0">
     <el-row>
    <h4>【プログラム】</h4>
    (先頭に<font color="red">*</font>が付いている機能はメニューに表示しない)
    <el-transfer
    style="width:500px;"
    :data="all_program_list"
    v-model="roleInfo.menu_list[sel_menu_index].program_list"
    filterable
    :render-content="renderFunc"
    filter-placeholder="機能名を入力してください"
    :props="{
      key: 'pg_id',
      label: 'pg_nm',
      menu_display_flg: 'menu_display_flg'
    }"
    :titles="['プログラムリスト', 'メニュー']"
    :button-texts="['削除','追加']"
    :footer-format="{
      noChecked: '${total}',
      hasChecked: '${checked}/${total}'
    }"
    @change="handleChange">
    </el-transfer>
     </el-row>
      <br>
  <hr>
  <br>
   </template>
  </el-form>
            <el-col :span="10">
          <span id="ent_button">
            <el-button
            type="success"
            @click="ent">登録
          </el-button>
          </span>
         </el-col>
</div>
</template>

<script>
//import {} from '@/webedi/'
export default {
  //   components: {
  //     '' : XXX
  //   }
  // }
  data(){
    //check

    //data
    return {
      roleInfo: {
        role_id: '',          //ロールID
        role_nm: '',        //ロール名称

        menu_list: [           //メニューリスト
          {
            menu_id: '',       //メニューID
            menu_nm: '',       //メMaニュー名称
            display_order: 1, //メニュー表示順
            menu_path: '',     //メニューPath
            menu_component: '',     //メニューCompenent
            menu_icon: '',     //メニューICON

             program_list: []    //メニューのプログラムリスト
            // program_list: [    //メニューのプログラムリスト
            //   {
            //     pg_id: '',        //プログラムID
            //     pg_nm: '',        //プログラム名称
            //     display_order: 1, //プログラム表示順
            //     display_flg: 'Y', //表示FLG
            //   }
            // ]//program_list end
          }
        ],//menu_list end
      },//roleInfo end

      sel_menu_index : -1,   //選択中のメニューのIndex

      all_program_list:[],  //利用可能なプログラムリスト

      //rules
      rules:{
        role_nm:[
          {
            required: true,
            message: '入力必須です。',
            trigger: 'blur'
          }
        ],
      },//rules end

      renderFunc(h, option) {
        console.log(JSON.stringify(option))
        let sComment = '';
        if(option.display_flg !== 0){
            sComment = '*'
        }

         return <span><font color="red">{sComment}</font>{ option.pg_nm }</span>;
      },
    };
  },//data end

  methods:{
    ent(){
      var _this = this;
      console.log(JSON.stringify(_this.roleInfo));
      _this.$refs.roleInfo.validate(
        (valid) => {
          if(valid){
            _this.$confirm('ロール情報を登録してもよろしいでしょうか？','注意',{
                  confirmButtonText: '確定',
                  cancelButtonText: '中止',
                  type: 'warning'
            }).then(
              //Post start>>>
              () => {
                _this.$http.post('/api/role/insert', _this.roleInfo).then(
                  function(response){
                    var p_error_code = response.data.errorcode;
                    if(p_error_code === '200'){
                      _this.$message({
                        type: 'success',
                        message:'登録が成功しました！'
                      });
                      _this.roleInfo.role_id = response.data.data;
                      _this.goToDetail();
                    }else if(p_error_code === '500'){
                      _this.$message.$error('登録が失敗しました。[' + response.data.errormsg + ']');
                    }
                  }
                ).catch(function(x_error){
                  console.log(x_error);
                }
              );
              }
              //Post end <<<
            ).catch(
              () => {
                _this.$message({
                  type: 'info',
                  message: '登録が中止しました！'
                })
              }
            )
          } else {
              this.$message.error("入力項目にエラーがあります！");
          }
        }
      )
    },//ent() end

    //照会画面へ遷移
    goToDetail(){
      this.$router.push({ path: 'RoleDetail', query: { role_id: this.roleInfo.role_id }});
    },

    pgListUpd(x_index, x_menu){
        this.sel_menu_index = x_index;
    },

    addMenu(x_index, x_menu){

        var p_new_menu_item =  {
            menu_id: '',       //メニューID
            menu_nm: '',       //メニュー名称
            display_order: 1, //メニュー表示順
            menu_path: '',     //メニューPath
            menu_component: '',     //メニューCompenent
            menu_icon: '',     //メニューICON

            program_list: []  //メニューのプログラムリスト
            //program_list end
          };

        this.roleInfo.menu_list.splice(x_index+1,0,p_new_menu_item);
    },

    delMenu(x_index, x_menu){
      var p_menu_list = this.roleInfo.menu_list;
        if(p_menu_list.length <= 1) return;
        this.roleInfo.menu_list.splice(x_index,1);
    },

     handleChange(value, direction, movedKeys) {
        // console.log(value, direction, movedKeys);
      },

    getAllProgramList(){
			var _this = this;
			_this.$http({
				 method:'get',
				 url:'/api/role/getAllProgramList',
			 }).then(function (response) {
			   var errorcode=response.data.errorcode;
				  if(errorcode==="200"){
           _this.all_program_list = response.data.data;
				 }else if(errorcode==="500"){
           _this.all_program_list = [];
					 _this.errormsg(response.data.errormsg);
				 }
			   })
			 .catch(function (error) {
         _this.all_program_list = [];
				 console.log(error);
			 });
    },

    loadRoleInfo() {
        var p_role_id = this.$route.query.role_id;
        if(this.$_.isUndefined(p_role_id)) return;


        var _this = this;
        _this.$http.get('/api/role/detail2', {
            params: {
              role_id: p_role_id
            }
          })
          .then(function(response) {
            var errorcode = response.data.errorcode;
            if (errorcode == "200") {
              _this.roleInfo = response.data.data;
              _this.setInitValue(_this.roleInfo);

            } else if (errorcode == "500") {
              _this.$message.error(response.data.errormsg);
            }
          })
          .catch(function(error) {
            console.log(error);
          });
      },

      setInitValue(role_info){
        role_info.role_id = '';
      },


  },//methods end

  computed: {

   },//coumputed end;

  //mounted(){}
  created(){
    this.loadRoleInfo();
    this.getAllProgramList();
    // console.log(JSON.stringify(this.all_program_list));

  },//created end

};

</script>

<style scoped>
#role_info{
  width: 950px;
  margin-top: 25px;
  margin-left:30px;
}
h3 h4{
  margin-bottom: 30px;
}
#ent_button{
   margin-left:20px;
   margin-right: 15px;
}

  .transfer-footer {
    margin-left: 20px;
    padding: 6px 5px;
  }

span.item_label{
border-radius:1px;
width: 150px;
height: 50px;
font-size: 15px;
vertical-align: middle;
}

th{
  background-color:hsla(120,65%,75%,0.1);
}

div.client_erea{
  background-color:cornsilk;
}
</style>

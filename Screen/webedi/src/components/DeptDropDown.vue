<template>
  <div id="UserManager">
    <el-form
      :inline="true"
      :rules="rules"
      :model="userInfo"
      ref="userInfo"
      label-width="110px"
      class="userForm"
    >
      <h2>ユーザー詳細情報‐新規</h2>
      <el-form-item label="ユーザーID" prop="account">
        <el-input
          type="text"
          v-model="userInfo.account"
          style="width: 100px"
          auto-complete="off"
          :disabled="false"
        ></el-input>
      </el-form-item>
      <el-form-item label="ユーザー名" prop="name">
        <el-input
          type="text"
          v-model="userInfo.name"
          auto-complete="off"
          :disabled="false"
        ></el-input>
      </el-form-item>
      <el-form-item label="パスワード" prop="password">
        <el-input
          type="password"
          v-model="userInfo.password"
          auto-complete="off"
          :disabled="true"
        ></el-input>
      </el-form-item>
      <el-form-item label="状態" prop="status">
        <el-radio class="radio" v-model="userInfo.status" label="1"
          >利用中</el-radio
        >
        <el-radio class="radio" v-model="userInfo.status" label="2"
          >终止中</el-radio
        >
        <el-radio
          class="radio"
          v-model="userInfo.status"
          v-for="item in optionsdept"
          :value="item.value"
          :key="item.value"
        >
          {{ item.label }}
        </el-radio>
      </el-form-item>
      <el-form-item label="タイプ" prop="user_type">
        <el-radio class="radio" v-model="userInfo.user_type" label="C"
          >普通</el-radio
        >
        <el-radio class="radio" v-model="userInfo.user_type" label="A"
          >管理者</el-radio
        >
      </el-form-item>
      <el-form-item label="メニュー" prop="roleid">
        <el-select
          v-model="userInfo.roleid"
          placeholder="選択してください"
          :disabled="false"
        >
          <el-option
            v-for="item in optionsrole"
            :label="item.label"
            :value="item.value"
            :key="item.value"
          >
          </el-option>
        </el-select>
      </el-form-item>
      <el-form-item label="職位" prop="depid">
        <el-select v-model="userInfo.depid" placeholder="選択してください">
          <el-option
            v-for="item in optionsdept"
            :label="item.label"
            :value="item.value"
            :key="item.value"
          >
          </el-option>
        </el-select>
      </el-form-item>
    </el-form>
    <h3>ユーザー承認経路</h3>
    <el-table
      :data="userInfo.userwfconfirmroute"
      style="width: 537px"
      id="AcceptRoute"
    >
      <el-table-column label="表示順" width="90">
        <template scope="scope">
          <el-input
            type="text"
            auto-complete="off"
            v-model="scope.row.display_order"
            :disabled="false"
            size="mini"
          ></el-input>
        </template>
      </el-table-column>
      <el-table-column prop="accept_user_id" label="承認者" width="225" />
      <el-table-column prop="job_title_cd" label="職位" width="120" />
      <el-table-column label="操作" width="100">
        <template scope="scope">
          <el-button
            type="danger"
            @click="acceptDelete(scope.$index, scope.row)"
            size="mini"
            icon="delete"
            >削除</el-button
          >
        </template>
      </el-table-column>
    </el-table>

    <el-col :span="10">
      <span id="save_button"
        ><el-button type="success" @click="save">保存</el-button></span
      >
      <span id="accept_button"
        ><el-button type="primary" @click="insertAcceptRoute"
          >追加承認者</el-button
        ></span
      >
    </el-col>
    <el-dialog
      title="ユーザー承認者"
      v-model="dialogAcceptVisible"
      size="500px"
    >
      <el-form
        :inline="true"
        :model="acceptUser"
        ref="acceptUser"
        label-width="100px"
        class="acceptUserForm"
      >
        <el-form-item label="表示順" prop="display_order">
          <el-input-number
            v-model="acceptUser.display_order"
            auto-complete="off"
            :disabled="false"
          ></el-input-number>
        </el-form-item>
        <el-form-item label="ユーザー" prop="accept_user_id">
          <el-select
            v-model="acceptUser.accept_user_id"
            placeholder="選択してください"
          >
            <el-option label="田中　幸一" value="0"></el-option>
            <el-option label="佐藤　英明" value="1"></el-option>
            <el-option label="小林　英紀" value="2"></el-option>
            <el-option label="深田　美紀子" value="3"></el-option>
          </el-select>
        </el-form-item>
      </el-form>
      <span id="save_button1"
        ><el-button type="success" @click="addAcceptUser">保存</el-button></span
      >
    </el-dialog>
  </div>
</template>
<script>
export default {
  data() {
    return {
      userInfo: {
        account: "",
        name: "",
        password: "",
        status: "1",
        user_type: "C",
        roleid: "",
        depid: "",
        userwfconfirmroute: [
          {
            display_order: "1",
            accept_user_id: "田中　幸一",
            job_title_cd: "上長",
          },
          {
            display_order: "2",
            accept_user_id: "佐藤　英明",
            job_title_cd: "上長",
          },
          {
            display_order: "3",
            accept_user_id: "小林　英紀",
            job_title_cd: "部長",
          },
        ],
      },
      list: [
        { id: "id1", title: "bbbb" },
        { id: "id2", title: "2222" },
        { id: "id3", title: "3333" },
        { id: "id4", title: "4444" },
      ],
      dialogAcceptVisible: false,
      userwfconfirmroute: [
        {
          display_order: "1",
          accept_user_id: "田中　幸一",
          job_title_cd: "上長",
        },
        {
          display_order: "2",
          accept_user_id: "佐藤　英明",
          job_title_cd: "上長",
        },
        {
          display_order: "3",
          accept_user_id: "小林　英紀",
          job_title_cd: "部長",
        },
      ],
      acceptUser: {
        display_order: "",
        accept_user_id: "",
      },
      optionsdept: [],
      optionsrole: [],
      rules: {
        user_id: [
          {
            required: true,
            message: "ユーザーIDが入力必須です。",
            trigger: "blur",
          },
        ],
        user_nm: [
          {
            required: true,
            message: "ユーザー名が入力必須です。",
            trigger: "blur",
          },
        ],
        user_type: [
          { required: true, message: "利用が入力必須です。", trigger: "blur" },
        ],
        login_able_flg: [
          {
            required: true,
            message: "タイプが入力必須です。",
            trigger: "blur",
          },
        ],
        menu_cd: [
          {
            required: true,
            message: "メニューが入力必須です。",
            trigger: "blur",
          },
        ],
        job_title_cd: [
          { required: true, message: "職位が入力必須です。", trigger: "blur" },
        ],
      },
    };
  },
  methods: {
    //新規承認者
    insertAcceptRoute() {
      this.dialogAcceptVisible = true;
    },
	loadEmpInfoList() {
	  return {
		acceptRouteData: [{ display_order: "1", accept_user_id: "田中　幸一" }],
	  };
	},
    addAcceptUser() {
      this.dialogAcceptVisible = false;
      this.loadEmpInfoList();
    },
    getDept() {
      var _this = this;
      this.$http({
        method: "get",
        url: "/api/dept/dcitList",
      })
        .then(function (response) {
          var errorcode = response.data.errorcode;
          if (errorcode == "200") {
            _this.optionsdept = response.data.data;
          } else if (errorcode == "500") {
            // console.debug(response);
            _this.errormsg(response.data.errormsg);
          }
        })
        .catch(function (error) {
          console.log(error);
        });
    },
    getRole() {
      var _this = this;
      this.$http({
        method: "get",
        url: "/api/role/roleList",
      })
        .then(function (response) {
          var errorcode = response.data.errorcode;
          if (errorcode == "200") {
            _this.optionsrole = response.data.data;
          } else if (errorcode == "500") {
            // console.debug(response);
            _this.errormsg(response.data.errormsg);
          }
        })
        .catch(function (error) {
          console.log(error);
        });
    },
    //删除
    acceptDelete() {
      var _this = this;
      _this
        .$confirm("このうユーザーが削除しまうか？", "提示", {
          confirmButtonText: "はい",
          cancelButtonText: "いいえ",
          type: "warning",
        })
        .then(() => {
          _this.$alert("削除するは成功", "提示", {
            confirmButtonText: "確認",
          });
        });
    },
    //新增里的保存
    save() {
      var _this = this;
      _this
        .$confirm("登録するはよろしいですか？", "提示", {
          confirmButtonText: "確認",
          cancelButtonText: "取消",
          type: "success",
        })
        .then(() => {
        //   var data = {
        //     account: _this.userInfo.account,
        //     name: _this.userInfo.name,
        //   };
          _this.$http
            .post("/api/user/regiter", _this.userInfo)
            .then(function (response) {
              var errorcode = response.data.errorcode;
              if (errorcode == "200") {
                _this.$alert("登録することに成功します", "提示", {
                  confirmButtonText: "確認",
                  callback: () => {},
                });
              } else if (errorcode == "500") {
                _this.$alert(
                  "登録することに失敗します" + response.data.errormsg,
                  "提示",
                  {
                    confirmButtonText: "確認",
                    callback: () => {},
                  }
                );
              }
            })
            .catch(function (error) {
              console.log(error);
            });
        })
        .catch(() => {
          _this.$notify({
            title: "提示",
            type: "error",
            message: "データが格納しません！",
            offset: 100,
          });
        });
    },
  },
  mounted() {
    this.getDept();
    this.getRole();
  },
};
</script>

<style>
#UserManager {
  width: 1090px;
  margin-top: 25px;
  margin-left: 250px;
}

.UserManager_top {
  height: 58px;
  border: 50px;
  background-color: #f2f2f2;
}
.UserManager_top_form {
  margin-top: 12px;

  margin-left: 10px;
}
#UserManager_middle {
  margin-top: 10px;
}
</style>

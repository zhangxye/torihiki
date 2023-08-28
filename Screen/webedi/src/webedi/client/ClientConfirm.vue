<template>
  <div id="ClientConfirm">
    <el-dialog v-if="showFlag" :visible.sync="showFlag">
      <ClientUpd v-model="showFlag"></ClientUpd>
    </el-dialog>
    <el-dialog v-if="showFlag1" :visible.sync="showFlag1">
      <ClientEnt v-model="showFlag1"></ClientEnt>
    </el-dialog>
    <el-dialog v-if="showFlag2" :visible.sync="showFlag2">
      <ClientInq v-model="showFlag2"></ClientInq>
    </el-dialog>
    <p> 取引先 一覧</p>

    <div v-if="isCollapsed">
    <div class="ClientConfirm_top">
      <el-form class="ClientConfirm_top_form" :inline="true" label-width="120px" :model="filters">
        <el-link type="primary">＜条件オプション＞</el-link>

        <form id="myForm">
          <el-form-item label="取引先名称：" prop="trhks_nam">
            <el-input size="small" style="width:200px" v-model="filters.trhks_nam">
            </el-input>
          </el-form-item>

          <el-form-item label="備考：" prop="bikou">
            <el-input size="small" style="width:200px" v-model="filters.bikou">
            </el-input>
          </el-form-item>

          <el-form-item label="取引区分：" prop="kubun">
            <el-select
              clearable
              size="small"
              style="width:200px"
              v-model="filters.torihiki"
              placeholder="すべて" >
                <el-option
                  v-for="item in status"
                  :key="item.value"
                  :label="item.label"
                  :value="item.value">
                </el-option>
            </el-select>
          </el-form-item>

          <el-form-item label="営業担当：" prop="tantou">
            <el-select
              clearable
              size="small"
              style="width:200px"
              v-model="filters.tantou"
              placeholder="すべて" >
                <el-option
                  v-for="item in tantou"
                  :key="item.value"
                  :label="item.label"
                  :value="item.value">
                </el-option>
            </el-select>
          </el-form-item>

          <el-form-item label="提案区分：" prop="teian">
            <el-select
              clearable
              size="small"
              style="width:200px"
              v-model="filters.teian"
              placeholder="すべて" >
                <el-option
                  v-for="item in teian"
                  :key="item.value"
                  :label="item.label"
                  :value="item.value">
                </el-option>
            </el-select>
          </el-form-item>    
        
          <el-form-item>
            <el-button type="success" @click="searchData" icon="search">検索</el-button>
          </el-form-item>

          <el-form-item>
            <el-button type="warning" @click="clearitems" icon="circle-close">クリア</el-button>
          </el-form-item>       
        </form>
      </el-form>
    </div>
  </div>

    <el-form class="ClientConfirm_top_form" :inline="true" label-width="120px" :model="filters">
      <el-form-item>
        <el-button type="info" @click="tourokuDate()" icon="plus">新規登録</el-button>
      </el-form-item>
      <el-form-item>
        <el-button type="danger" @click="searchData" icon="close">一括削除</el-button>
      </el-form-item>
      <el-button-group style="float:right;">
        <el-button icon="search" @click="toggleCollapse"></el-button>
        <el-button icon="circle-close" @click="clearitem"></el-button>
      </el-button-group>
    </el-form>

    <span>{{ temp }}</span>
    <form id="Forms">
    <el-table :data="pageinfo.pageData" height="400" border >
      <el-table-column>
        <el-checkbox v-model="checked" @change="change()"></el-checkbox>
      </el-table-column>
      <el-table-column prop="trhks_nam" label="取引先名称" width="130" />
      <el-table-column prop="web" label="web" width="100"/>
      <el-table-column prop="denwa_no" label="代表電話番号" width="120" />
      <el-table-column prop="jyusyou" label="住所" width="100" />
      <el-table-column prop="teian" label="提案区分" width="120" />
      <el-table-column prop="jisyaeigyou" label="自社営業-担当" width="120" />
      <el-table-column label="操作" width="210">
        <template slot-scope="scope">
          <el-button type="info" @click="kousinDate(scope.$index, scope.row)" size="mini" icon="edit">更新</el-button>
          <el-button type="warning" @click="syoukaiDate(scope.$index, scope.row)" size="mini" icon="search">照会</el-button>
          <el-button type="danger" @click="delete (scope.$index, scope.row)" size="mini" icon="close">削除</el-button>
        </template>
      </el-table-column>
    </el-table>
    </form>
    <span>{{ temp }}</span>
  </div>
</template>

<script>
import ClientUpd from "@/webedi/client/ClientUpd.vue";
import ClientEnt from "@/webedi/client/ClientEnt.vue";
import ClientInq from "@/webedi/client/ClientInq.vue";
export default {
  data() {
    var validateComment= (callback) => {
			callback();
		};
    return {
      showFlag: false,
      showFlag1: false,
      showFlag2: false,
      isCollapsed: true,
      filters: {
        trhks_nam: '',
        bikou: '',
        torihiki: '',
        tantou: '',
        teian: '',
      },
      status: [
        { label: '重要客', value:'1' },
        { label: '通常客', value:'2' },
        { label: '低ポテンシャル', value:'3' },
        { label: '取引停止', value:'4' },
      ],
      tantou: [
        { label: '西谷　健', value:'1' },
        { label: '戸川　浩一', value:'2' },
      ],
      teian: [
        { label: '案件（保有企業）', value:'1' },
        { label: '人材（保有企業）', value:'2' },
        { label: '案件・人材（保有企業）', value:'3' },
      ],
      pageinfo: {
        pageNo: 1,
        pageSize: 10,
        total: 10,
        pageData: [
        ]
      },
      temp: '総件数：1件、1 〜 1 件目',
    }
  },

  components: {
    ClientUpd,
    ClientEnt,
    ClientInq
  },

  methods: {
    toggleCollapse() {
      this.isCollapsed = !this.isCollapsed; // 切换折叠状态
    },
    getList(){
 		var _this = this;
            this.$http.get('/api/apprDoc/selectApprDoc'
            ,{
              params:{
              	page:_this.pageinfo.pageNo,
              	pageSize:_this.pageinfo.pageSize,
                empNm:_this.filters.emp_nm,
                apprDocNo:_this.filters.appr_doc_no,
                bikou:_this.filters.appr_doc_approve_dt_from,
                torihikikubun:_this.filters.status,
                tantou:_this.filters.emp_no
                     },
             }
                         )
            .then(function (response) {
              var errorcode=response.data.errorcode;
                 if(errorcode=="200"){
                    _this.pageinfo=response.data.data;
                }else if(errorcode=="500"){
                   _this.$message.error(response.data.errormsg);
                    _this.$message.error(response.data.errormsg);                        }
                                       });
              },

    searchData() {
      this.getList();
    },
    syoukaiDate() {
          this.showFlag2 = true       
        },
    tourokuDate() {
          this.showFlag1 = true
        },
    clearitems() {
      var form = document.getElementById('myForm');
      if (form) {
        form.reset();
      }
    },
    clearitem() {
      var form = document.getElementById('Forms');
      if (form) {
        form.reset();
      }
    },
    kousinDate() {
          this.showFlag = true
        },
  },
  mounted() {
    //this.searchData();
  }
}
</script>

<style>
#ClientConfirm {
  width: 90%;
  margin-top: 25px;
  margin-left: 30px;
}

.ClientConfirm_top_form {
  margin-top: 12px;
  margin-left: 10px;
}

</style>
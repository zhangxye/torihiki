<template>
  <div id="ApprDocConfirm">
    <el-dialog v-if="showFlag" :visible.sync="showFlag">
      <ClientStaffUpd v-model="showFlag"></ClientStaffUpd>
    </el-dialog>
    <el-dialog v-if="showFlag1" :visible.sync="showFlag1">
      <ClientStaffEnt v-model="showFlag1"></ClientStaffEnt>
    </el-dialog>
    <el-dialog v-if="showFlag2" :visible.sync="showFlag2">
      <ClientStaffDetail v-model="showFlag2"></ClientStaffDetail>
    </el-dialog>
    <p> 取引先 一覧</p>

    <div class="ApprDocConfirm_top">
      <el-form class="ApprDocConfirm_top_form" :inline="true" label-width="120px" :model="filters">
        <el-link type="primary">＜条件オプション＞</el-link>

        <form id="myForm">
          <el-form-item label="取引先名称：" prop="appr_doc_no">
            <el-input size="small" style="width:200px" v-model="filters.appr_doc_no">
            </el-input>
          </el-form-item>

          <el-form-item label="備考：" prop="appr_doc_approve_dt_from">
            <el-input size="small" style="width:200px" v-model="filters.appr_doc_approve_dt_from">
            </el-input>
          </el-form-item>

          <el-form-item label="取引区分：" prop="status">
            <el-select
              clearable
              size="small"
              style="width:200px"
              v-model="filters.status"
              placeholder="すべて" >
                <el-option
                  v-for="item in status"
                  :key="item.value"
                  :label="item.label"
                  :value="item.value">
                </el-option>
            </el-select>
          </el-form-item>

          <el-form-item label="営業担当：" prop="emp_no">
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

          <el-form-item label="提案区分：" prop="emp_nm">
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
        </form>

          <el-form-item>
            <el-button type="success" @click="searchData" icon="search">検索</el-button>
          </el-form-item>

          <el-form-item>
            <el-button type="warning" @click="clearitems" icon="circle-close">クリア</el-button>
          </el-form-item>       
      </el-form>
    </div>

    <el-form class="ApprDocConfirm_top_form" :inline="true" label-width="120px" :model="filters">
      <el-form-item>
        <el-button type="info" @click="tourokuDate()" icon="plus">新規登録</el-button>
      </el-form-item>
      <el-form-item>
        <el-button type="danger" @click="searchData" icon="close">一括削除</el-button>
      </el-form-item>
      <el-button-group style="float:right;">
        <el-button icon="search"></el-button>
        <el-button icon="loading"></el-button>
      </el-button-group>
    </el-form>

    <span>{{ temp }}</span>

    <el-table :data="pageinfo.pageData" height="100%" border id="ApprDocInq_middle">
      <el-table-column>
        <el-checkbox v-model="checked" @change="change()"></el-checkbox>
      </el-table-column>
      <el-table-column prop="appr_doc_no" label="取引先名称" width="130" />
      <el-table-column prop="appr_status" label="web" width="100"/>
      <el-table-column prop="appr_doc_approve_dt" label="代表電話番号" width="120" />
      <el-table-column prop="appr_doc_create_k_nm" label="住所" width="100" />
      <el-table-column prop="appr_doc_dt_from" label="提案区分" width="120" />
      <el-table-column prop="appr_doc_dt_to" label="自社営業-担当" width="120" />
      <el-table-column label="操作" width="210">
        <template slot-scope="scope">
          <el-button type="info" @click="kousinDate(scope.$index, scope.row)" size="mini" icon="edit">更新</el-button>
          <el-button type="warning" @click="syoukaiDate(scope.$index, scope.row)" size="mini" icon="search">照会</el-button>
          <el-button type="danger" @click="delete (scope.$index, scope.row)" size="mini" icon="close">削除</el-button>
        </template>
      </el-table-column>
    </el-table>

    <span>{{ temp }}</span>

  </div>
</template>

<script>
import ClientStaffUpd from "@/webedi/client/ClientStaffUpd.vue";
import ClientStaffEnt from "@/webedi/client/ClientStaffEnt.vue";
import ClientStaffDetail from "@/webedi/client/ClientStaffInq.vue";
export default {
  data() {
    var validateComment = (callback) => {
      callback();
    };
    return {
      showFlag: false,
      showFlag1: false,
      showFlag2: false,
      filters: {
        appr_doc_no: '',
        appr_doc_approve_dt_from: '',
        emp_no: '',
        emp_nm: '',
        status: '',
        appr_status: '1'
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
    ClientStaffUpd,
    ClientStaffEnt,
    ClientStaffDetail
  },

  methods: {
    getList(){
    //var startLine = (this.pageinfo.pageNo - 1) *this.pageinfo.pageSize;
    //var endLine = startLine + this.pageinfo.pageSize
    //this.pageinfo.pageData = this.$_.slice(this.resultList,startLine,endLine);
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
      var _this = this;
      this.getList();
      this.$http.get("http://localhost:3000/syoukai"
        , {
          params: {
            page: _this.pageinfo.pageNo,
            pageSize: _this.pageinfo.pageSize,
            apprDocNo: _this.filters.appr_doc_no,
            approveStatus: _this.filters.appr_status,
            empNo: _this.filters.emp_no,
            empNm: _this.filters.emp_nm
          },
        }
      );
    },
    syoukaiDate() {
      this.$http.get("http://localhost:3000/syoukai")
        .then((result) => {
          this.showFlag2 = result.data[0].show
          console.log(result);
        });
    },
    tourokuDate() {
      this.$http.get("http://localhost:3000/touroku")
        .then((result) => {
          this.showFlag1 = result.data[0].show
          console.log(result);
        });
    },
    clearitems() {
      var form = document.getElementById('myForm');
      if (form) {
        form.reset();
      }
    },
    kousinDate() {
      // this.showFlag = !this.showFlag;
      this.$http.get("http://localhost:3000/nextpage")
        .then((result) => {
          this.showFlag = result.data[0].show
          console.log(result);
        });
      // this.$router.push({ path: 'Kousin'});
    },
  },
  mounted() {
    this.searchData();
  }
}
</script>

<style>
#ApprDocConfirm {
  width: 90%;
  margin-top: 25px;
  margin-left: 30px;
}

.ApprDocConfirm_top_form {
  margin-top: 12px;

  margin-left: 10px;
}

#ApprDocConfirm_middle {
  margin-top: 10px;
}
</style>
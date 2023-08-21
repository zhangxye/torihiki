<template>
  <div id="MonReqInq">
    <el-form class="EstInqForm" :inline="true" ref="monReqInfo" :model="filters">
      <el-row>
        <el-form-item label="稼働月" label-width="60px" prod="work_mon">
          <el-date-picker size="small" type="month" style="width:120px" placeholder="YYYY-MM" v-model="filters.work_mon">
          </el-date-picker>
        </el-form-item>
        <el-form-item label="ステータス" label-width="100px" prop="mon_req_st">
          <el-select size="small" clearable style="width:120px" v-model="filters.mon_req_st" placeholder="ステータス">
            <el-option v-for="item in mon_req_st_list" :key="item.value" :label="item.label" :value="item.value">
            </el-option>
          </el-select>
        </el-form-item>
        <el-form-item label="区分" prop="mon_pro_k">
          <el-select size="small" clearable style="width:120px" v-model="filters.mon_pro_k" placeholder="区分">
            <el-option v-for="item in mon_pro_k_list" :key="item.value" :label="item.label" :value="item.value">
            </el-option>
          </el-select>
        </el-form-item>
      </el-row>
      <el-row>
        <el-form-item label="作業報告書〆" prop="work_rep_dl_k">
          <el-select size="small" clearable style="width:120px" v-model="filters.work_rep_dl_k" placeholder="作業報告書〆">
            <el-option v-for="item in work_rep_dl_k_list" :key="item.value" :label="item.label" :value="item.value">
            </el-option>
          </el-select>
        </el-form-item>
        <el-form-item label="請求書〆" prop="bill_dl_k">
          <el-select size="small" clearable style="width:120px" v-model="filters.bill_dl_k" placeholder="請求書〆">
            <el-option v-for="item in bill_dl_k_list" :key="item.value" :label="item.label" :value="item.value">
            </el-option>
          </el-select>
        </el-form-item>
        <el-form-item label="営業主担当" label-width="100px" prop="business_main_user_id">
          <el-select size="small" clearable style="width:120px" v-model="filters.business_main_user_id" placeholder="営業主担当">
            <el-option v-for="item in business_user_id_list" :key="item.value" :label="item.label" :value="item.value">
            </el-option>
          </el-select>
        </el-form-item>
      </el-row>
      <el-row>
        <el-form-item label="状態" label-width="60px" prop="dept_cd">
          <el-select clearable size="small" style="width:120px" v-model="filters.appr_doc_st" placeholder="">
            <el-option v-for="item1 in appr_doc_st_list" :label="item1.label" :value="item1.value" :key="item1.value">
            </el-option>
          </el-select>
        </el-form-item>

        <el-form-item label="クライアント名称" label-width="130px" prop="client_nm">
          <el-input size="small" type="text" style="width:150px" v-model="filters.client_nm" placeholder="部分一致">
          </el-input>
        </el-form-item>
        <el-form-item label="要員氏名" label-width="100px" prop="emp_nm">
          <el-input size="small" type="text" style="width:150px" v-model="filters.emp_nm" placeholder="部分一致">
          </el-input>
        </el-form-item>
      </el-row>
      <el-row>
        <el-col :span="8">
          <el-pagination
          layout="prev, pager, next"
          :current-page="page_info.current"
          :total="page_info.total"
          :page-size="page_info.size"
          @current-change="handleCurrentChange">
        </el-pagination>
        </el-col>
         <el-col :span="12">
          <el-form-item label-width="80px" label="改頁件数" prop="size">
            <el-select size="small" v-model="page_info.size" style="width:100px" @change="handleChgPageSize" placeholder="改頁件数">
              <el-option v-for="item in pageSize_list" :key="item" :label="item+'件'" :value="item">
              </el-option>
            </el-select>
          </el-form-item>
        </el-col>
        <el-col :span="2">
          <el-form-item>
            <el-button type="primary" @click="searchData" icon="search">検索</el-button>
          </el-form-item>
        </el-col>
      </el-row>
      <el-table :data="page_info.records"
      :row-style="tableRowStyle"
       border
       height="500" >
        <el-table-column width="60" label="NO">
          <template slot-scope="scope">
        {{(scope.$index +  1)+ (page_info.current - 1) *page_info.size}}
        </template>
      </el-table-column>
        <el-table-column label="詳細" type="expand">
        <template slot-scope="scope">
          <div class="mon_req_detail_div">
          <el-form label-position="left" inline class="mon_req_detail">
            <el-row>
              <el-form-item label="受注期間">
                {{scope.row.appr_doc_dt_from}} ～ {{scope.row.appr_doc_dt_to}}
              </el-form-item>
              <el-form-item label="案件ID" prop="pj_cd">
                {{scope.row.pj_cd}}
              </el-form-item>
              <el-form-item label="稟議書NO" prop="appr_doc_no">
                {{scope.row.appr_doc_no}}
              </el-form-item>
              <el-form-item label="稟議書作成区分" prop="appr_doc_create_k">
                {{scope.row.appr_doc_create_k_nm}}
              </el-form-item>
            </el-row>
            <el-row>
              <el-form-item label="社員区分（雇用形態）" label-width="170px" prop="emp_pat_k">
                {{scope.row.emp_pat_k_nm}}
              </el-form-item>
              <el-form-item label-width="160px" label="契約（上位）" prop="client_contract_k_nm">
                {{scope.row.client_contract_nm}}
              </el-form-item>
              <el-form-item label="社員コード" prop="emp_no">
                {{scope.row.emp_no}}
              </el-form-item>
              <el-form-item label="クライアントID" label-width="130px" prop="client_no">
                {{scope.row.client_no}}
              </el-form-item>
            </el-row>
            <el-collapse>
              <el-collapse-item title="クライアント担当者" name="1">
                <el-row :gutter="0">
                  <el-col :span="7">
                    <el-form-item label-width="140px" label="氏名" prop="client_staff_nm">
                      {{scope.row.client_staff_nm}}
                    </el-form-item>
                  </el-col>
                  <el-col :span="7">
                    <el-form-item label="所属部署" prop="client_staff_dept_nm">
                      {{scope.row.client_staff_dept_nm}}
                    </el-form-item>
                  </el-col>
                  <el-col :span="10">
                    <el-form-item label="メールアドレス" prop="client_mail_addr">
                      {{scope.row.client_mail_addr}}
                    </el-form-item>
                  </el-col>
                </el-row>

                <el-row :gutter="0">
                  <el-col :span="7">
                    <el-form-item label-width="140px" label="フリガナ" prop="client_staff_nm_fuli">
                      {{scope.row.client_staff_nm_fuli}}
                    </el-form-item>
                  </el-col>
                  <el-col :span="7">
                    <el-form-item label="役職" prop="client_staff_position">
                      {{scope.row.client_staff_position}}
                    </el-form-item>
                  </el-col>
                  <el-col :span="10">
                    <el-form-item label="メールアドレス（CC）" label-width="170px" prop="client_mail_addr_cc">
                      {{scope.row.client_mail_addr_cc}}
                    </el-form-item>
                  </el-col>
                </el-row>

                <el-row :gutter="0">
                  <el-col :span="7">
                    <el-form-item label="電話番号（直通）" label-width="140px" prop="client_tel_no">
                      {{scope.row.client_staff_tel_no}}
                    </el-form-item>
                  </el-col>
                  <el-col :span="7">
                    <el-form-item label-width="80px" label="FAX番号" prop="client_fax_no">
                      {{scope.row.client_staff_fax_no}}
                    </el-form-item>
                  </el-col>
                  <el-col :span="10">
                    <el-form-item label-width="100px" label="携帯番号" prop="client_staff_mobile_no">
                      {{scope.row.client_staff_mobile_no}}
                    </el-form-item>
                  </el-col>
                </el-row>

              </el-collapse-item>
              <el-collapse-item title="請求担当者" name="2">
                <el-row :gutter="0">
                  <el-col :span="7">
                    <el-form-item label-width="140px" label="氏名" prop="client_req_staff_nm">
                      {{scope.row.client_req_staff_nm}}
                    </el-form-item>
                  </el-col>
                  <el-col :span="7">
                    <el-form-item label="所属部署" prop="client_req_staff_dept_nm">
                      {{scope.row.client_req_staff_dept_nm}}
                    </el-form-item>
                  </el-col>
                  <el-col :span="10">
                    <el-form-item label="メールアドレス" prop="client_req_mail_addr">
                      {{scope.row.client_req_mail_addr}}
                    </el-form-item>
                  </el-col>
                </el-row>

                <el-row :gutter="0">
                  <el-col :span="7">
                    <el-form-item label-width="140px" label="フリガナ" prop="client_req_staff_nm_fuli">
                      {{scope.row.client_req_staff_nm_fuli}}
                    </el-form-item>
                  </el-col>
                  <el-col :span="7">
                    <el-form-item label-width="70px" label="役職" prop="client_req_staff_position">
                      {{scope.row.client_req_staff_position}}
                    </el-form-item>
                  </el-col>
                  <el-col :span="10">
                    <el-form-item label="メールアドレス（CC）" label-width="170px" prop="client_req_mail_addr_cc">
                      {{scope.row.client_req_mail_addr_cc}}
                    </el-form-item>
                  </el-col>
                </el-row>

                <el-row :gutter="0">
                  <el-col :span="7">
                    <el-form-item label-width="140px" label="電話番号（直通）" prop="client_req_tel_no">
                      {{scope.row.client_req_tel_no}}
                    </el-form-item>
                  </el-col>
                  <el-col :span="7">
                    <el-form-item label-width="80px" label="FAX番号" prop="client_req_fax_no">
                      {{scope.row.client_req_fax_no}}
                    </el-form-item>
                  </el-col>
                  <el-col :span="10">
                    <el-form-item label-width="100px" label="携帯番号" prop="client_req_staff_mobile_no">
                      {{scope.row.client_req_staff_mobile_no}}
                    </el-form-item>
                  </el-col>
                </el-row>

                <el-row :gutter="0">
                  <el-col :span="8">
                    <el-form-item label-width="140px" label="郵便番号〒" prop="client_req_url">
                      {{scope.row.client_req_post_no}}
                    </el-form-item>
                  </el-col>
                  <el-col :span="16">
                    <el-form-item label-width="60px" label="住所" prop="client_req_addr">
                      {{scope.row.client_req_addr}}
                    </el-form-item>
                  </el-col>
                </el-row>

              </el-collapse-item>
            </el-collapse>
            <el-row>
              <el-col :span="4">
              <el-form-item label="添付資料" prop="mon_attach_file_k">
                {{scope.row.mon_attach_file_k_nm}}
              </el-form-item>
              </el-col>
              <el-col :span="12">
              <el-form-item label="添付資料詳細" prop="mon_attach_file_k">
                  <el-input
                  type="textarea"
                  style="width:500px"
                  :rows="2"
                  resize="none"
                  :autosize="false"
                  :disabled="scope.row.edit_f === 'N'"
                  placeholder=""
                  v-model="scope.row.mon_attach_file_remark">
                </el-input>
              </el-form-item>
              </el-col>
            </el-row>
            <el-row>
            <el-col :span="4">
            <el-form-item label="ステータス" prop="mon_req_st">
            <template v-if="scope.row.edit_f === 'Y'">
              <el-select size="small" clearable style="width:120px" v-model="scope.row.mon_req_st" placeholder="ステータス">
              <el-option v-for="item in mon_req_st_list" :key="item.value" :label="item.label" :value="item.value">
              </el-option>
              </el-select>
            </template>
            <template v-else>
              {{scope.row.mon_req_st_nm}}
            </template>
            </el-form-item>
            </el-col>
            <el-col :span="12">
              <el-form-item label="備考欄（月次補足）" prop="mon_add_remark">
                  <el-input
                  type="textarea"
                  style="width:500px"
                  :rows="2"
                  resize="none"
                  :autosize="false"
                  :disabled="scope.row.edit_f === 'N'"
                  placeholder=""
                  v-model="scope.row.mon_add_remark">
                </el-input>
              </el-form-item>
            </el-col>
            </el-row>
            <el-row>
            <el-col :span="1"></el-col>
            <el-col :span="3">
            <template v-if="scope.row.edit_f === 'N'">
            <el-button type="info"
            @click="edit(scope.$index,scope.row)">編集</el-button>
          </template>
          <template v-else>
            <el-button type="success"
            @click="update(scope.$index,scope.row)">更新</el-button>
          </template>
            </el-col>
            <el-col :span="20">
              <el-steps :space="100" :active="scope.row.appr_doc_st-0" finish-status="success">
              <el-step v-for="item in appr_doc_st_list" :key="item.value">
                <div slot="title">
                  {{item.label}}
                </div>
              </el-step>
              </el-steps>
              </el-col>
            </el-row>
          </el-form>
          </div>
        </template>
        </el-table-column>
        <el-table-column
        sortable
        prop="client_nm"
        label="ｸﾗｲｱﾝﾄ名称"
        show-overflow-tooltip
        width="200"/>

        <el-table-column
        sortable
        label="ｽﾃｰﾀｽ"
        width="150">
          <template slot-scope="scope">
            <template v-if="scope.row.edit_f === 'Y'">
              <el-select size="small" clearable style="width:120px" v-model="scope.row.mon_req_st" placeholder="ステータス">
              <el-option v-for="item in mon_req_st_list" :key="item.value" :label="item.label" :value="item.value">
              </el-option>
              </el-select>
            </template>
            <template v-else>
              {{scope.row.mon_req_st_nm}}
            </template>
          </template>
        </el-table-column>

        <el-table-column
        sortable
        prop="emp_nm"
        label="氏名"
        width="130"/>
       <el-table-column
        label="作業報告書〆"
        align="center"
        width="140">
          <template slot-scope="scope">
              <template v-if="scope.row.work_rep_dl_k === '1'">
                ー
              </template>
            <template v-else>
            {{scope.row.work_rep_dl_k_nm}}
            <template v-if="scope.row.work_rep_f === '2'">
              <span class="cus_f">客F</span>
            </template>
            </template>
          </template>
        </el-table-column>
       <el-table-column
        label="請求書〆"
        align="center"
        width="140">
          <template slot-scope="scope">
            <template v-if="scope.row.bill_dl_k === '1'">
             ー
            </template>
            <template v-else>
            {{scope.row.bill_dl_k_nm}}
            <template v-if="scope.row.bill_f === '2'">
              <span class="cus_f">客F</span>
            </template>
            </template>
          </template>
        </el-table-column>
        <el-table-column
        prop="mon_attach_file_k_nm"
        width="70"
        label="添付">
          <template slot-scope="scope">
            <template v-if="scope.row.mon_attach_file_k ==='1'">
            <span class="exist_f">有</span>
            </template>
          </template>
        </el-table-column>
        <el-table-column
        width="70"
        label="区分">
        <template slot-scope="scope">
            <template v-if="scope.row.mon_pro_k !=='1'">
            {{scope.row.mon_pro_k_nm}}
            </template>
        </template>
        </el-table-column>
        <el-table-column
        sortable
        prop="business_main_user_nm"
        width="130"
        label="営業担当"/>
        <el-table-column
        width="100"
        label="操作" >
        <template slot-scope="scope">
          <template v-if="scope.row.edit_f === 'N'">
            <el-button type="info"
            @click="edit(scope.$index,scope.row)" size="mini">編集</el-button>
          </template>
          <template v-else>
            <el-button type="success"
            @click="update(scope.$index,scope.row)" size="mini">更新</el-button>
          </template>
        </template>
      </el-table-column>
      <el-table-column
      width="300"
      label="添付資料詳細">
      <template slot-scope="scope">
          <template v-if="scope.row.edit_f === 'Y'">
          <el-input
          type="textarea"
          style="width:300px"
          :rows="2"
          resize="none"
          :autosize="false"
          placeholder=""
          v-model="scope.row.mon_attach_file_remark">
        </el-input>
        </template>
        <template v-else>
          <el-tooltip effect="dark" :content="scope.row.mon_attach_file_remark" placement="top-start">
             <div class="fixed_text">{{scope.row.mon_attach_file_remark}}</div>
          </el-tooltip>
        </template>
      </template>
      </el-table-column>
      <el-table-column
      width="300"
      label="備考欄（月次補足）">
      <template slot-scope="scope">
          <template v-if="scope.row.edit_f === 'Y'">
          <el-input
          type="textarea"
          style="width:300px"
          :rows="2"
          resize="none"
          :autosize="false"
          placeholder=""
          v-model="scope.row.mon_add_remark">
        </el-input>
        </template>
        <template v-else>
          <el-tooltip effect="dark" :content="scope.row.mon_add_remark" placement="top-start">
            <div class="fixed_text">{{scope.row.mon_add_remark}}</div>
          </el-tooltip>
        </template>
      </template>
      </el-table-column>
    </el-table>
</el-form>
  </div>
</template>

<script>
  import pageComm from '@/utils/pageComm'
  export default {
    data() {
      return {

        filters: {
          work_mon: this.$moment().format("YYYY-MM"), //稼働日
          mon_req_st: '',
          mon_pro_k: '',
          work_rep_dl_k: '',
          bill_dl_k: '',
          business_main_user_id: '', //営業主担当
          appr_doc_st: '',
          client_nm: '',
          emp_nm: ''
        }, //filters end

        pageSize_list: [],

        page_info: {
          current: 1,
          size: 10,
          total: 20,
          records: [],
        }, //page_info end

        result_list: [],
        business_user_id_list: [],
        mon_req_st_list: [],
        mon_pro_k_list: [],
        work_rep_dl_k_list: [],
        bill_dl_k_list: [],
        appr_doc_st_list: [],
      }
    }, //data() end

    methods: {

      //
      handleCurrentChange(val) {
        this.page_info.current = val;
        this.getList();
      },
      //改頁件数を選択
      handleChgPageSize(val) {
        this.getList();
      },
      searchData() {
        this.page_info.current = 1;
        this.getList();
      },

      getPageSizeList() {
        this.pageSize_list = [5, 10, 20, 50, 100];
      },

      setScreenInitValue() {
        //ステータス
        this.setDropDownList('MON_REQ_ST', 'mon_req_st_list');
        //区分
        this.setDropDownList('MON_PRO_K', 'mon_pro_k_list');
        //作業報告書〆
        this.setDropDownList('REP_DL_K', 'work_rep_dl_k_list');
        //請求書〆
        this.setDropDownList('BILL_DL_K', 'bill_dl_k_list');
        //状態
        this.setDropDownList('APPR_ST', 'appr_doc_st_list');
        //営業担当
        this.loadBusinessUserList();
      },


      //営業担当
      loadBusinessUserList() {
        var _this = this;
        _this.$http({
            method: 'get',
            url: '/api/user/jobUserList',
            params: {
              jobTitleCd: '001'
            }
          }).then(function(response) {
            var errorcode = response.data.errorcode;
            if (errorcode == "200") {
              _this.business_user_id_list = response.data.data;
            } else if (errorcode == "500") {
              _this.business_user_id_list = [];
              _this.errormsg(response.data.errormsg);
            }
          })
          .catch(function(error) {
            _this.business_user_id_list = [];
            console.log(error);
          });

      },


      getList() {
        var _this = this;
        this.$http.post('/api/monReq/searchForMonReqList', {
            current: _this.page_info.current,
            size: _this.page_info.size,
            work_mon: this.$utils.formatDate(_this.filters.work_mon, "YYYY-MM"), //稼働月
            mon_req_st: _this.filters.mon_req_st,
            mon_pro_k: _this.filters.mon_pro_k,
            work_rep_dl_k: _this.filters.work_rep_dl_k,
            bill_dl_k: _this.filters.bill_dl_k,
            business_main_user_id: _this.filters.business_main_user_id,
            appr_doc_st: _this.filters.appr_doc_st,
            client_nm: _this.filters.client_nm,
            emp_nm: _this.filters.emp_nm
          })
          .then(function(response) {
            var errorcode = response.data.errorcode;
            if (errorcode == "200") {
              _this.page_info = response.data.data;
            } else if (errorcode == "500") {
              _this.$message.error(response.data.errormsg);
            }
          })
          .catch(function(error) {
            console.log(error);
          });
      },

      edit(x_index, x_row){
        x_row.edit_f = 'Y';
      },

      update(x_index, x_row){
      var _this = this;
        _this.$confirm('月次請求情報を更新してもよろしいでしょうか？','注意',{
              confirmButtonText: '確定',
              cancelButtonText: '中止',
              type: 'warning'
        }).then(
          //Post start>>>
          () => {
            _this.$http.post('/api/monReq/update',
             {
               appr_doc_id : x_row.appr_doc_id,
               mon_req_st : x_row.mon_req_st,
               mon_attach_file_remark : x_row.mon_attach_file_remark,
               mon_add_remark : x_row.mon_add_remark
             }
             ).then(
              function(response){
                var p_error_code = response.data.errorcode;
                if(p_error_code === '200'){
                  _this.$message({
                    type: 'success',
                    message:'更新が成功しました！'
                  });
                  x_row.edit_f = 'N';
                  x_row.mon_req_st_nm = _this.getLabelByValue(_this.mon_req_st_list,x_row.mon_req_st);
                }else if(p_error_code === '500'){
                  _this.$message.error('更新が失敗しました。[' + response.data.errormsg + ']');
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
              message: '更新が中止しました！'
            })
          }
        )
    },//update() end

    tableRowStyle(row, index) {
        //請求完了の場合、グレー色を付ける
        if (!this.$_.isUndefined(row.mon_req_st) && row.mon_req_st === '4') {
          console.log("tableRowClassName row.mon_req_st = %s",row.mon_req_st);
          return { "background-color": "gray" };
        }
      }

    }, //methods end


    mounted() {
      this.setScreenInitValue();
      this.getPageSizeList();
      this.getList();
    }, //mounted end

    mixins : [pageComm]

  }
</script>

<style>
  #MonReqInq {
    width: 90%;
    margin-top: 25px;
    margin-left: 30px;
  }

  div.mon_req_detail_div{
    background-color:lightyellow;
  }
  .mon_req_detail {
    font-size: 0;
  }
  .mon_req_detail label {
    width: 90px;
    color: #99a9bf;
  }
  .mon_req_detail .el-form-item {
    margin-right: 50px;
    margin-bottom: 10px;
  }

  .cus_f{
    width: 50px;
    height: 25px;
    background-color:royalblue;
    color:white;
    font-weight:bold;
    text-align:center;
  }

  .exist_f{
    width: 40px;
    height: 25px;
    background-color:red;
    color:white;
    font-weight:bold;
    text-align:center;
  }

  .fixed_text{
    overflow: hidden;
    white-space: nowrap;
    text-overflow: ellipsis;
  }
  
</style>

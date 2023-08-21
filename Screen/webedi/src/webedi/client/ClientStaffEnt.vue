<template>
  <div id="ApprDocConfirm" v-if="value">
    <p> 取引先追加</p>
      <div class="ApprDocConfirm_top">
          <el-form
          class="ApprDocConfirm_top_form"
          :inline="true"
          label-width="120px"
          :model="filters">
        
          <el-form-item>
          <span class="red-text">（必須）</span>取引先名：     
          <el-input
          size="small"
          style="width:110px"
          placeholder="例)田中　太郎"
          v-model="filters.appr_doc_no">
          </el-input>
          </el-form-item>

          <el-col>
          <el-form-item>
          <span class="red-text">（必須）</span>取引先名（カナ）：
          <el-input
          size="small"
          style="width:110px"
          placeholder="例）セイ　メイ"
          v-model="filters.appr_doc_approve_dt_from">
          </el-input>
          </el-form-item>
          </el-col>

          <el-col>
          <div>
          <el-form-item>
          <span class="red-text">（必須）</span>郵便番号：
          <el-input
          size="small"
          style="width:110px"
          placeholder="例)000-0000"
          v-model="filters.appr_doc_approve_dt_from">
          </el-input>
          <el-col>
          <el-form-item>
              <el-button type="success" size = "mini" @click="searchData" icon="">住所検索</el-button>
           </el-form-item>
          </el-col>
          </el-form-item>  
          </div>        
          </el-col>

          <el-col>
          <el-form-item>
          <span class="red-text">（必須）</span>住所：
          <el-input
          size="small"
          style="width:400px"
          v-model="filters.appr_doc_approve_dt_from">
          </el-input>
          </el-form-item>
          </el-col>

          <el-form-item>
          <span>ビル名：</span>
          <el-input
          size="small"
          style="width:400px"
          v-model="filters.appr_doc_no">
          </el-input>
          </el-form-item>  
          
          <el-col>
          <el-form-item>
          <span class="red-text">（必須）</span>代表電話番号：
          <el-input
          size="small"
          style="width:70px"
          placeholder="例)01"
          v-model="filters.appr_doc_no">
          </el-input>
          <span> - </span>
          <el-input
          size="small"
          style="width:70px"
          placeholder="例)000"
          v-model="filters.appr_doc_no">
          </el-input>
          <span> - </span>
          <el-input
          size="small"
          style="width:70px"
          placeholder="例)000"
          v-model="filters.appr_doc_no">
          </el-input>
          </el-form-item>
          </el-col>   
          
          <el-col>
          <el-form-item>
          <span class="red-text">（必須）</span>代表FAX番号：
          <el-input
          size="small"
          style="width:70px"
          placeholder="例)01"
          v-model="filters.appr_doc_no">
          </el-input>
          <span> - </span>
          <el-input
          size="small"
          style="width:70px"
          placeholder="例)000"
          v-model="filters.appr_doc_no">
          </el-input>
          <span> - </span>
          <el-input
          size="small"
          style="width:70px"
          placeholder="例)000"
          v-model="filters.appr_doc_no">
          </el-input>
          </el-form-item>
          </el-col>    
          
          <el-col>
          <el-form-item>
          <span>サイトURL：</span>
          <el-input
          size="small"
          style="width:150px"
          placeholder="例)http://"
          v-model="filters.appr_doc_no">
          </el-input>         
          </el-form-item>
          </el-col>   

          <el-col>
          <el-form-item>
          <span>従業員数：</span>
          <el-input
          size="small"
          style="width:150px"         
          v-model="filters.appr_doc_no">
          </el-input>         
          </el-form-item>
          </el-col>   

          <el-col>
          <el-form-item>
          <span>資本金：</span>
          <el-input
          size="small"
          style="width:150px"
          v-model="filters.appr_doc_no">
          </el-input>         
          </el-form-item>
          </el-col> 

          <el-col>
          <el-form-item>
          <span>設立年：</span>
          <el-input
          size="small"
          style="width:150px"
          placeholder="yyyy"
          v-model="filters.appr_doc_no">
          </el-input>         
          </el-form-item>
          </el-col> 

          <el-col>
          <el-form-item>
          <span>年間売上：</span>
          <el-input
          size="small"
          style="width:150px"
          v-model="filters.appr_doc_no">
          </el-input>         
          </el-form-item>
          </el-col> 

          
          <el-form-item label="支払：" prop="status" >
          <el-dropdown>
          <span class="el-dropdown-link" style="
          appearance: none;
          background-image: none;
          border-radius: 4px;
          border: 1px solid #bfcbd9;
          box-sizing: border-box;
          color: #1f2d3d;
          font-size: inherit;
          height: 36px;
          line-height: 1;
          outline: 0;
          padding: 6px 132px;
          padding-left: 5px;
          transition: border-color .2s cubic-bezier(.645,.045,.355,1);">
          重要客<i class="el-icon-caret-bottom el-icon--right"></i>
          </span>
          <el-dropdown-menu slot="dropdown">
          <el-dropdown-item>重要客</el-dropdown-item>
          <el-dropdown-item>通常客</el-dropdown-item>
          <el-dropdown-item>低ポテンシャル</el-dropdown-item>
         <el-dropdown-item >取引停止</el-dropdown-item>
          </el-dropdown-menu>
          </el-dropdown>
          </el-form-item>

          <el-col>
          <el-form-item
            label="業種："
            prop="emp_no">
            <el-dropdown>
          <span class="el-dropdown-link" style="
          appearance: none;
          background-image: none;
          border-radius: 4px;
          border: 1px solid #bfcbd9;
          box-sizing: border-box;
          color: #1f2d3d;
          font-size: inherit;
          height: 36px;
          line-height: 1;
          outline: 0;
          padding: 6px 132px;
          padding-left: 5px;
          transition: border-color .2s cubic-bezier(.645,.045,.355,1);">
          重要客<i class="el-icon-caret-bottom el-icon--right"></i>
          </span>
          <el-dropdown-menu slot="dropdown">
          <el-dropdown-item>重要客</el-dropdown-item>
          <el-dropdown-item>通常客</el-dropdown-item>
          <el-dropdown-item>低ポテンシャル</el-dropdown-item>
         <el-dropdown-item >取引停止</el-dropdown-item>
          </el-dropdown-menu>
          </el-dropdown>
          </el-form-item>
          </el-col>
        
          <el-col>
          <el-form-item
            label="カテゴリー："
            prop="emp_no">
            <el-dropdown>
          <span class="el-dropdown-link" style="
          appearance: none;
          background-image: none;
          border-radius: 4px;
          border: 1px solid #bfcbd9;
          box-sizing: border-box;
          color: #1f2d3d;
          font-size: inherit;
          height: 36px;
          line-height: 1;
          outline: 0;
          padding: 6px 132px;
          padding-left: 5px;
          transition: border-color .2s cubic-bezier(.645,.045,.355,1);">
          重要客<i class="el-icon-caret-bottom el-icon--right"></i>
          </span>
          <el-dropdown-menu slot="dropdown">
          <el-dropdown-item>重要客</el-dropdown-item>
          <el-dropdown-item>通常客</el-dropdown-item>
          <el-dropdown-item>低ポテンシャル</el-dropdown-item>
         <el-dropdown-item >取引停止</el-dropdown-item>
          </el-dropdown-menu>
          </el-dropdown>
          </el-form-item>
          </el-col>

          <el-col>
          <el-form-item
            label="ステータス："
            prop="emp_no">
            <el-dropdown>
          <span class="el-dropdown-link" style="
          appearance: none;
          background-image: none;
          border-radius: 4px;
          border: 1px solid #bfcbd9;
          box-sizing: border-box;
          color: #1f2d3d;
          font-size: inherit;
          height: 36px;
          line-height: 1;
          outline: 0;
          padding: 6px 132px;
          padding-left: 5px;
          transition: border-color .2s cubic-bezier(.645,.045,.355,1);">
          重要客<i class="el-icon-caret-bottom el-icon--right"></i>
          </span>
          <el-dropdown-menu slot="dropdown">
          <el-dropdown-item>重要客</el-dropdown-item>
          <el-dropdown-item>通常客</el-dropdown-item>
          <el-dropdown-item>低ポテンシャル</el-dropdown-item>
         <el-dropdown-item >取引停止</el-dropdown-item>
          </el-dropdown-menu>
          </el-dropdown>
          </el-form-item>
          </el-col>

          <el-col>
          <el-form-item>
          <span>事象概要：</span>
          <el-input
          size="small"
          style="width:150px"
          placeholder="例)http://"
          v-model="filters.appr_doc_no">
          </el-input>         
          </el-form-item>
          </el-col>  
          
          <el-col>
          <el-form-item>
          <span>関連会社：</span>
          <el-input
          size="small"
          style="width:150px"
          placeholder="例)http://"
          v-model="filters.appr_doc_no">
          </el-input>         
          </el-form-item>
          </el-col>

          <el-col>
          <el-form-item>
          <span>取引銀行：</span>
          <el-input
          size="small"
          style="width:150px"
          placeholder="例)http://"
          v-model="filters.appr_doc_no">
          </el-input>         
          </el-form-item>
          </el-col>


        <el-col :span="24" >
            <el-form-item
             label="提案区分："
              prop="emp_nm">
              <el-checkbox v-model="checked">案件</el-checkbox>
              <el-checkbox v-model="checked">人材</el-checkbox>   
              <span>案件（保有企業）</span>
              <span>案件（保有企業）</span>            
            </el-form-item>
          </el-col>

            <el-col>
          <el-form-item
            label="重要度："
            prop="emp_no">
            <el-dropdown>
          <span class="el-dropdown-link" style="
          appearance: none;
          background-image: none;
          border-radius: 4px;
          border: 1px solid #bfcbd9;
          box-sizing: border-box;
          color: #1f2d3d;
          font-size: inherit;
          height: 36px;
          line-height: 1;
          outline: 0;
          padding: 6px 132px;
          padding-left: 5px;
          transition: border-color .2s cubic-bezier(.645,.045,.355,1);">
          重要客<i class="el-icon-caret-bottom el-icon--right"></i>
          </span>
          <el-dropdown-menu slot="dropdown">
          <el-dropdown-item>重要客</el-dropdown-item>
          <el-dropdown-item>通常客</el-dropdown-item>
          <el-dropdown-item>低ポテンシャル</el-dropdown-item>
         <el-dropdown-item >取引停止</el-dropdown-item>
          </el-dropdown-menu>
          </el-dropdown>
          </el-form-item>
          </el-col>

          <el-col>
          <el-form-item>
          <span>自社担当営業：</span>
          <span>主担当：</span>
          <span>副担当：</span>
          <el-col> 
          <el-dropdown>
          <span class="el-dropdown-link" style="
          appearance: none;
          background-image: none;
          border-radius: 4px;
          border: 1px solid #bfcbd9;
          box-sizing: border-box;
          color: #1f2d3d;
          font-size: inherit;
          height: 36px;
          line-height: 1;
          outline: 0;
          padding: 6px 132px;
          padding-left: 5px;
          transition: border-color .2s cubic-bezier(.645,.045,.355,1);">
          西谷　健<i class="el-icon-caret-bottom el-icon--right"></i>
          </span>
          <el-dropdown-menu slot="dropdown">
            <el-select
              v-model="value9"
              multiple
              filterable
              remote
              :remote-method="remoteMethod"
              :loading="loading">
              <el-option
                v-for="item in options4"
                :key="item.value"
                :label="item.label"
                :value="item.value">
              </el-option>
            </el-select>
          <el-dropdown-item>西谷　健</el-dropdown-item>
          <el-dropdown-item>戸川　浩一</el-dropdown-item>
          </el-dropdown-menu>
          </el-dropdown>
          <el-dropdown>
          <span class="el-dropdown-link" style="
          appearance: none;
          background-image: none;
          border-radius: 4px;
          border: 1px solid #bfcbd9;
          box-sizing: border-box;
          color: #1f2d3d;
          font-size: inherit;
          height: 36px;
          line-height: 1;
          outline: 0;
          padding: 6px 132px;
          padding-left: 5px;
          transition: border-color .2s cubic-bezier(.645,.045,.355,1);">
          西谷　健<i class="el-icon-caret-bottom el-icon--right"></i>
          </span>
          <el-dropdown-menu slot="dropdown">
            <el-select
              v-model="value9"
              multiple
              filterable
              remote
              :remote-method="remoteMethod"
              :loading="loading">
              <el-option
                v-for="item in options4"
                :key="item.value"
                :label="item.label"
                :value="item.value">
              </el-option>
            </el-select>
          <el-dropdown-item>西谷　健</el-dropdown-item>
          <el-dropdown-item>戸川　浩一</el-dropdown-item>
          </el-dropdown-menu>
          </el-dropdown>
          </el-col>      
          </el-form-item>
          </el-col>

          <el-col>
          <el-form-item>
          <span>備考：</span>
          <el-input
          type="textarea"
          :rows="2"
          style="width:400px"
          v-model="textarea">
          </el-input>         
          </el-form-item>
          </el-col>            
          </el-form>
          </div>
           
          <el-form
          class="ApprDocConfirm_top_form"
          :inline="true"
          label-width="120px"
          :model="filters">
          <el-form-item>
              <el-button type="info" @click="searchData" icon="plus">支店を追加する</el-button>
           </el-form-item>
           <el-form-item>
              <el-button type="info" @click="searchData" icon="close">取引先担当者を追加する</el-button>
           </el-form-item>          
        </el-form>

        <el-form
          class="ApprDocConfirm_top_form"
          :inline="true"
          label-width="120px"
          :model="filters">

          <el-form-item>
              <el-button style="float:right;" type="info" @click="searchData" icon="plus">確定</el-button>
           </el-form-item>

           <el-form-item>
              <el-button style="float:right;"  @click="closeShow()">閉じる</el-button>
           </el-form-item>          
        </el-form>

</div>
</template>

<script>
export default {
  data(){
  	var validateComment= (callback) => {
			callback();
		};
    return{
         filters: {
          appr_doc_no: '',
          appr_doc_approve_dt_from: '',
          emp_no: '',
          emp_nm: '',
        },
        temp: '総件数：1件、1 〜 1 件目',
    }
  },
  props: ["value"],
  model: {
            prop: 'value',
            event: 'balabala'
        },
  methods:{
      searchData(){
          var _this = this;
            this.$http.get('/api/apprDoc/selectApprApprove'
            ,{
              params:{
                page:_this.pageinfo.pageNo,
                pageSize:_this.pageinfo.pageSize,
                apprDocNo:_this.filters.appr_doc_no,
                empNo:_this.filters.emp_no,
                empNm:_this.filters.emp_nm
              },
            }
            );
   },
      closeShow() {
        this.$emit("balabala",false);
              }
  },
}
</script>

<style>
 .red-text {
    color: red;
  }
  .black-text {
    color: black;
  }
</style>

<template>
  <div id="ClientStaffInq">
    <p> 取引先担当者追加</p>
      <div>
          <el-form
          :inline="true"
          label-width="120px"
          :model="filters">
        
          <el-form-item>
          <span class="red-text">（必須）</span>取引先担当者名：     
          <el-input
          size="small"
          style="width:110px"
          placeholder="例)田中　太郎"
          :disabled="true"
          v-model="filters.tts_nam">
          </el-input>
          </el-form-item>

          <el-col>
          <el-form-item>
          <span class="red-text">（必須）</span>取引先名（カナ）：
          <el-input
          size="small"
          style="width:110px"
          placeholder="例）セイ　メイ"
          :disabled="true"
          v-model="filters.trhksk_kana">
          </el-input>
          </el-form-item>
          </el-col>

          <p> 企業情報</p>

          <el-col>
          <el-form-item>
          <span>取引企業名：</span>
          <el-input
          size="small"
          style="width:400px"
          :disabled="true"
          v-model="filters.trhkkg_nam">
          </el-input>
          <span class="red-text">*(新規登録時以外は変更できません)</span>
          </el-form-item>
          </el-col>  
          
          <el-col>
          <el-form-item>
          <span>部署：</span>
          <el-input
          size="small"
          style="width:400px"
          :disabled="true"
          v-model="filters.busyo">
          </el-input>
          </el-form-item>
          </el-col>

          <el-col>
          <el-form-item>
          <span>役職：</span>
          <el-input
          size="small"
          style="width:400px"
          :disabled="true"
          v-model="filters.yakusyoku">
          </el-input>
          </el-form-item>
          </el-col>

          <el-col>
          <el-form-item>
          <span class="red-text">（必須）</span>代表電話番号：
          <el-input
          size="small"
          style="width:300px"
          :disabled="true"
          v-model="filters.denwa_no">
          </el-input>         
          </el-form-item>
          </el-col>   
          
          <el-col>
          <el-form-item>
          <span>携帯番号：</span>
          <el-input
          size="small"
          style="width:300px"
          :disabled="true"
          v-model="filters.keidai_no">
          </el-input>
          </el-form-item>
          </el-col>    

          <el-col>
          <el-form-item>
          <span>送信用メールアドレス：</span>
          <el-input
          size="small"
          style="width:200px" 
          :disabled="true"        
          v-model="filters.sosinyou_adress">
          </el-input>         
          </el-form-item>
          </el-col> 
          
          <el-col>
          <el-form-item>
          <span>サブメールアドレス：</span>
          <el-input
          size="small"
          style="width:200px"   
          :disabled="true"      
          v-model="filters.sabu_adress">
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
          :disabled="true"
          v-model="filters.yuben_no">
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
          v-model="filters.jyusyou">
          </el-input>
          </el-form-item>
          </el-col>

          <el-col>
          <el-form-item>
          <span>名刺：</span>
          <el-input
          size="small"
          style="width:400px"
          v-model="filters.meisi">
          </el-input>         
          </el-form-item>
          </el-col> 

          <el-col>
          <el-form-item>
          <span>その他：</span>
          <el-checkbox-group v-model="checkList">
            <el-checkbox label="キーマンフラグ"></el-checkbox>
            <el-checkbox label="メール送信フラグ"></el-checkbox>
          </el-checkbox-group>  
          <span>メール送信時宛先表示優先度:</span>  
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
          :inline="true"
          label-width="120px"
          :model="filters">
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
          tts_nam: '',
          trhksk_kana: '',
          trhkkg_nam: '',
          busyo: '',
          yakusyoku: '',
          denwa_no: '',
          keidai_no: '',
          sosinyou_adress: '',
          sabu_adress: '',
          yuben_no: '',
          jyusyou: '',
          meisi: '',
        },
        pageinfo:{
          pageNo:1,
          pageSize:10,
          total:10,
          pageData:[
          ]
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

<template>
  <div id="DownLoad">
      <div class="DownLoad_top">
          <el-form
          class="DownLoad_top_form"
          :inline="true"
          label-width="80px"
          :model="filters">
      <el-col :span="16" >
     	 &nbsp;
      </el-col>
        <el-col :span="24" >
        <el-form-item
         label="対象月"
         label-width="60px"
         prod="work_mon">
           <el-date-picker
           size="small"
           type="month"
           style="width:120px"
           placeholder="YYYY-MM"
           v-model="filters.work_mon">
           </el-date-picker>
        </el-form-item>
       <el-form-item>
          <!--<el-button type="success" @click="exportData" >データ出力</el-button>-->
          <a :href="req_url" download="TSRS帳票管理マスタ.xls" style="color: #fff;background-color: #20a0ff;border-color: #20a0ff;display: inline-block;line-height: 1;white-space: nowrap;cursor: pointer;background: #fff;border: 1px solid #c4c4c4;color: #1f2d3d;margin: 0;padding: 10px 15px;border-radius: 4px;">データ出力</a>
       </el-form-item>
         <el-form-item>
		    <el-checkbox v-model="checked1" label="月次" border></el-checkbox>
		    <el-checkbox v-model="checked2" label="契約" border></el-checkbox>
  		</el-form-item>
        </el-col>
     </el-form>
     </div>
  </div>
</template>

<script>
export default {
  data(){
    return{
         filters: {
          work_mon:this.$moment().format("YYYY-MM"),
        },
        checked1: false,
        checked2: false,
    }
  },//data end

  methods:{
   exportData() {
        this.createFile();
      },
   	createFile(){
 		var _this = this;
           _this.$http.get('/api/ApprDocDownLoad/export'
           ,{
              params:{
				workMonth:_this.$moment(_this.filters.work_mon).format("YYYY-MM"),
				check1:checked1,
				check2:checked2,
              },
            }
            )
            .then(function (response) {
              var errorcode=response.data.errorcode;
                   if(errorcode=="200"){
                      _this.$message({
                      type: 'success',
                      message: '出力成功しました!'
                    });
                }else if(errorcode=="500"){
                  _this.$message.error('出力失敗しました。['+response.data.errormsg+']');
                }
              })
            .catch(function (error) {
                console.log(error);
            });
   	},
  },//method end
  computed: {
       req_url : function(){
         return '/api/ApprDocDownLoad/export?workMonth='+ this.$moment(this.filters.work_mon).format("YYYY-MM") +'&check1='+this.checked1+'&check2='+this.checked2
       }
  }
}
</script>

<style>
#DownLoad{
  width: 90%;
  margin-top: 25px;
  margin-left:30px;
}

.DownLoad_top_form{
  margin-top: 12px;

  margin-left: 10px;
}
#DownLoad_middle{
  margin-top: 10px;
}
</style>

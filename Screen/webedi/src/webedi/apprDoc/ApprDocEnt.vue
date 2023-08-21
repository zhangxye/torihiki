<template>
      <div id="appr_doc_info">
         <back-to-top text="トップへ戻る"></back-to-top>
        <el-form
        :inline="true"
        :rules="rules"
        :model="apprDocInfo"
        ref="apprDocInfo"
        label-width="120px"
        class="apprDoc-ruleForm">
        <h3>【稟議書】</h3>
<div style="background-color: #E0FFFF">
              <el-form-item
              label="稟議書NO"
              prop="appr_doc_no" >
                <el-input
                size="small"
                type="text"
                style="width:200px;"
                v-model="apprDocInfo.appr_doc_no"
                auto-complete="off"
                :disabled="true">
                </el-input>
              </el-form-item>

              <el-form-item
              required
              label-width="130px"
              label="稟議書作成区分"
              prop="appr_doc_create_k">
              <el-select
              size="small"
              v-model="apprDocInfo.appr_doc_create_k"
              style="width:160px"
              placeholder="稟議書作成区分" >
                <el-option
                v-for="item in appr_doc_create_k_list"
                :key="item.value"
                :label="item.label"
                :value="item.value">
                </el-option>
              </el-select>
              </el-form-item >
              <el-form-item
              required
              size="small"
              label="作成日">
                <el-date-picker
                size="small"
                 type="date"
                 style="width:120px"
                 placeholder="作成日"
                 v-model="apprDocInfo.appr_doc_create_dt"
                 value-format="yyyy-MM-dd"
                  :disabled="true">
                </el-date-picker>
              </el-form-item>
            <el-form-item
            required
            label="受注期間">
                <el-form-item prop="appr_doc_dt_from">
                  <el-date-picker
                  size="small"
                  type="date"
                  style="width:120px"
                  placeholder="日付"
                  value-format="yyyy-MM-dd"
                  v-model="apprDocInfo.appr_doc_dt_from">
                  </el-date-picker>
                </el-form-item>
                -
                <el-form-item prop="appr_doc_dt_to">
                  <el-date-picker
                   size="small"
                   type="date"
                   style="width:120px"
                   placeholder="日付"
                   value-format="yyyy-MM-dd"
                   v-model="apprDocInfo.appr_doc_dt_to">
                  </el-date-picker>
                </el-form-item>
            </el-form-item>

             <el-form-item
              label-width="350px"
              prop="site_entrance_dt"
              label="入場日">
                <el-date-picker
                size="small"
                 type="date"
                 style="width:120px"
                 placeholder="入場日"
                 value-format="yyyy-MM-dd"
                 v-model="apprDocInfo.site_entrance_dt">
               </el-date-picker>
              </el-form-item>

            <el-form-item
             label-width="120px"
             label="案件名称"
              prop="pj_cd">
            <el-input
            placeholder="部分一致"
            style="width:550px"
            v-model="pj_nm_key">
              <el-select
              slot="prepend"
              size="small"
              style="width:350px"
              @change="fetchPjAllInfo"
              v-model="pj_cd_key">
                <el-option
                  v-for="item in pj_info_list"
                  :key="item.pj_cd"
                  :label="item.pj_nm"
                  :value="item.pj_cd">
                  <span style="float: left">{{ item.pj_nm }}</span>
                  <span style="float: right; color: #8492a6; font-size: 13px">{{ item.pj_cd }}</span>
                </el-option>
             </el-select>
              <el-button
              slot="append"
              @click="loadPjInfoList"
              icon="search">
              </el-button>
            </el-input>
            </el-form-item >

            <el-form-item
            label-width="70px"
             label="案件ID"
              prop="pj_cd">
            <el-input
               size="small"
                type="text"
                style="width:120px"
                v-model="apprDocInfo.pj_cd"
                placeholder="完全一致"
                @blur="setPjInfo"
                auto-complete="on">
              </el-input>
            </el-form-item >

            <el-form-item
              required
              label="営業主担当"
              prop="business_main_user_id">
              <el-select
              size="small"
              style="width:120px"
              v-model="apprDocInfo.business_main_user_id"
              placeholder="営業主担当" >
                <el-option
                v-for="item in business_user_id_list"
                :key="item.value"
                :label="item.label"
                :value="item.value">
                </el-option>
              </el-select>
              </el-form-item >


            <el-form-item label="営業副担当" 
            label-width="210px"
            prop="business_sub_user_id">
              <el-select
              clearable
              size="small"
              style="width:120px"
              v-model="apprDocInfo.business_sub_user_id"
              placeholder="営業副担当" >
                  <el-option
                  v-for="item in business_user_id_list"
                  :key="item.value"
                  :label="item.label"
                  :value="item.value">
                  </el-option>
              </el-select>
             </el-form-item >

             <el-form-item label="部署"
             label-width="160px"
             prop="dept_cd">
               <el-select
               clearable
               size="small"
               style="width:120px"
               v-model="apprDocInfo.dept_cd"
               placeholder="" >
                   <el-option
                   v-for="item in dept_list"
                   :key="item.dept_cd"
                   :label="item.dept_nm"
                   :value="item.dept_cd">
                   </el-option>
               </el-select>
              </el-form-item >

            </div>

            <h3>【SES契約技術者登録】</h3>
            <div style="background-color: #E0FFFF">

            <el-form-item
             label-width="60px"
             label="氏名"
              prop="emp_nm">
            <el-input
            placeholder="部分一致"
            style="width:450px"
            v-model="emp_nm_key">
              <el-select
              slot="prepend"
              size="small"
              style="width:280px"
              @change="fetchEmpAllInfo"
              v-model="emp_no_key">
                <el-option
                  v-for="item in emp_info_list"
                  :key="item.emp_no"
                  :label="item.emp_nm"
                  :value="item.emp_no">
                  <span style="float: left">{{ item.emp_nm }}</span>
                  <span style="float: right; color: #8492a6; font-size: 13px">{{ item.emp_no }}</span>
                </el-option>
             </el-select>
              <el-button
              slot="append"
              @click="loadEmpInfoList"
              icon="search">
              </el-button>
            </el-input>
            </el-form-item >

            <el-form-item
            required
            label="社員コード"
            label-width="230px"
            prop="emp_no">
               <el-input
               size="small"
                type="text"
                style="width:120px"
                v-model="apprDocInfo.emp_no"
                placeholder="完全一致"
                @blur="setEmpInfo"
                auto-complete="on">
              </el-input>
            </el-form-item >

              <el-row :gutter="0">
              <el-form-item
              required
              label="要員区分（雇用形態）"
              label-width="170px"
              prop="emp_pat_k">
                <el-select
                size="small"
                style="width:120px"
                v-model="apprDocInfo.emp_pat_k"
                placeholder="要員区分" >
                    <el-option
                    v-for="item in emp_pat_k_list"
                    :key="item.value"
                    :label="item.label"
                    :value="item.value">
                    </el-option>
                </el-select>
               </el-form-item >
              </el-row>
              <el-form-item
                label="所属区分（再委託先）"
                label-width="170px"
                prop="affiliation_k">
                <el-select
                clearable
                size="small"
                style="width:120px"
               :disabled="!affiliation_k_valid_f"
                v-model="apprDocInfo.affiliation_k"
                placeholder="" >
                    <el-option
                    v-for="item in affiliation_k_list"
                    :key="item.value"
                    :label="item.label"
                    :value="item.value">
                    </el-option>
                </el-select>
               </el-form-item >

           <el-form-item
              label-width="120px"
              label="所属会社名"
              prop="bp_comp_id">
            <el-input
            placeholder="部分一致"
            style="width:450px"
            v-model="bp_comp_emp_nm_key">
              <el-select
              slot="prepend"
              size="small"
              style="width:250px"
              @change="fetchBpAllInfo"
              v-model="bp_comp_id_key">
                <el-option
                  v-for="item in bp_comp_info_list"
                  :key="item.client_no"
                  :label="item.client_nm"
                  :value="item.client_no">
                  <span style="float: left">{{ item.client_nm }}</span>
                  <span style="float: right; color: #8492a6; font-size: 13px">{{ item.client_no }}</span>
                </el-option>
             </el-select>
              <el-button
              slot="append"
              @click="loadBpNoList"
              icon="search">
              </el-button>
            </el-input>
            </el-form-item >
          </div>
          <h3>【クライアント契約】</h3>
          <div style="background-color: #E0FFFF">
          <el-form-item
             label-width="140px"
             label="クライアント名称"
              prop="client_no">
            <el-input
            placeholder="部分一致"
            style="width:450px"
            v-model="client_nm_key">
              <el-select
              slot="prepend"
              size="small"
              style="width:250px"
              @change="fetchClientAllInfo"
              v-model="client_no_key">
                <el-option
                  v-for="item in client_info_list"
                  :key="item.client_no"
                  :label="item.client_nm"
                  :value="item.client_no">
                  <span style="float: left">{{ item.client_nm }}</span>
                  <span style="float: right; color: #8492a6; font-size: 13px">{{ item.client_no }}</span>
                </el-option>
             </el-select>
              <el-button
              slot="append"
              @click="loadClientNoList"
              icon="search">
              </el-button>
            </el-input>
            </el-form-item >

            <el-form-item
             label="クライアントID"
             label-width="150px"
             prop="client_no">
            <el-input
               size="small"
                type="text"
                v-model="apprDocInfo.client_no"
                placeholder="完全一致"
                @blur="setClientInfo"
                auto-complete="on">
              </el-input>
            </el-form-item >

              <el-form-item label="契約条件"
              label-width="140px"
              prop="client_contract_k">
                <el-select
                clearable
                size="small"
                style="width:120px"
                v-model="apprDocInfo.client_contract_k"
                placeholder="契約条件" >
                    <el-option
                    v-for="item in client_contract_k_list"
                    :key="item.value"
                    :label="item.label"
                    :value="item.value">
                    </el-option>
                </el-select>
               </el-form-item>

             <el-form-item
              label="受注（注文）書No."
              label-width="160px"
              prop="client_order_no">
              <el-input
                 size="small"
                 type="text"
                 style="width:150px"
                 v-model="apprDocInfo.client_order_no"
                 :disabled="true">
               </el-input>
             </el-form-item>

             <el-form-item
              label="クライアント名称"
              label-width="140px"
              prop="client_nm">
                <el-input
                size="small"
                 type="text"
                 style="width:250px"
                 v-model="apprDocInfo.client_nm"
                 :disabled="true">
               </el-input>
             </el-form-item>

<el-row :gutter="0">
              <el-form-item
               label="注文書受領日"
               label-width="140px"
               prop="client_nm">
               <el-date-picker
               size="small"
               type="date"
               style="width:120px"
               placeholder=""
               value-format="yyyy-MM-dd"
               v-model="apprDocInfo.order_recv_hope_dt">
               </el-date-picker>
             </el-form-item>

              <el-form-item
              label="取引区分"
              label-width="160px"
              prop="take_over_k">
                <el-select
                size="small"
                style="width:120px"
                v-model="apprDocInfo.take_over_k"
                placeholder="取引区分" >
                    <el-option
                    v-for="item in take_over_k_list"
                    :key="item.value"
                    :label="item.label"
                    :value="item.value">
                    </el-option>
                </el-select>
               </el-form-item >
               </el-row>
               </div>
              
     <el-row :gutter="0">
      <el-col :span="12">
      <h3>【契約金情報（上位）】</h3>
          <div style="background-color: #E0FFFF; border-right:1px solid #AFEEEE">
            <el-row :gutter="0">
              <el-form-item
              label-width="150px"
              label="契約工数(人月)"
              prop="sum_contract_wm">
              <el-input
                size="small"
                 type="text"
                 style="width:150px"
                 :minlength="0"
                 :maxlength="7"
                 v-model.number="apprDocInfo.sum_contract_wm">
                <el-button
                slot="append"
                @click="calcSumConstractWm">
                計算
                </el-button>
               </el-input>

             </el-form-item>
            </el-row>
             <el-form-item
              label-width="150px"
              label="単価（上位）"
              prop="sell_price">
              <el-input
                size="small"
                 type="text"
                 style="width:100px"
                 :minlength="2"
                 :maxlength="10"
                 v-model="apprDocInfo.sell_price">
               </el-input>
              (税込: {{formatMoney(apprDocInfo.sell_price*(1+tax_rate/100),0,"￥")}})
             </el-form-item>
             <el-form-item label="精算条件"
                prop="accounting_pat_k">
                <el-select
                 size="small"
                 style="width:100px"
                 v-model="apprDocInfo.accounting_pat_k"
                 placeholder="精算条件" >
                 <el-option
                 v-for="item in accounting_pat_k_list"
                 :key="item.value"
                 :label="item.label"
                 :value="item.value">
                 </el-option>
               </el-select>
         
              </el-form-item >
               <el-form-item
                label="上限時間"
                prop="wh_max">
                <el-input
                size="small"
                type="text"
                style="width:100px"
                placeholder="時間"
                :maxlength="3"
                :min="0"
                :max="999"
                :disabled="!wh_max_valid_f"
                v-model.number.lazy="apprDocInfo.wh_max">
               </el-input>
              </el-form-item >
           <el-form-item
              label="下限時間"
               prop="wh_min">
                <el-input
                size="small"
                type="text"
                style="width:100px"
                placeholder="時間"
                :maxlength="3"
                :min="0"
                :max="999"
                :disabled="!wh_min_valid_f"
                v-model.number.lazy="apprDocInfo.wh_min">
              </el-input>
             </el-form-item >
          <el-form-item
              label="中割時間"
               prop="wh_mid">
                <el-input
                size="small"
                type="text"
                style="width:100px"
                placeholder="時間"
                :maxlength="3"
                :min="0"
                :max="999"
                :disabled="!wh_mid_valid_f"
                v-model.number.lazy="apprDocInfo.wh_mid">
              </el-input>
             </el-form-item >
             <el-form-item
                label="超過単価"
                 prop="excess_price">
                  <el-input
                  size="small"
                  type="text"
                  style="width:120px"
                  :disabled="false"
                  v-model="excess_price">
                </el-input>
            <el-tooltip
            placement="bottom-end"
            effect="light">
              <div slot="content">
               <b>計算方法</b>
               <br>----------
               <br>①上下割の場合、 上位単金÷上限時間（十円まで切捨て）
               <br>②中割の場合、 上位単金÷中割時間（十円まで切捨て）
               </div>
               <i class="el-icon-information"></i>
              </el-tooltip>
               </el-form-item >
             <el-form-item
                label="控除単価"
                 prop="deducation_price">
                  <el-input
                  size="small"
                  type="text"
                  style="width:120px"
                  :disabled="false"
                  v-model="deducation_price">
                </el-input>
            <el-tooltip
            placement="bottom-end"
            effect="light">
              <div slot="content">
               <b>計算方法</b>
               <br>----------
               <br>①上下割の場合、 上位単金÷下限時間（十円まで切捨て）
               <br>②中割の場合、 上位単金÷中割時間（十円まで切捨て）
               </div>
               <i class="el-icon-information"></i>
              </el-tooltip>
               </el-form-item >

        <el-form-item label="時間単位"
        prop="work_time_unit_k">
        <el-select
          size="small"
          style="width:100px"
          v-model="apprDocInfo.work_time_unit_k"
          placeholder="" >
              <el-option
              v-for="item in work_time_unit_k_list"
              :key="item.value"
              :label="item.label"
              :value="item.value">
              </el-option>
          </el-select>
         </el-form-item >
         
            <el-form-item
               label="休憩時間"
                prop="break_time_min">
                 <el-input
                 size="small"
                 type="text"
                 style="width:100px"
                :maxlength="3"
                :min="0"
                :max="180"
                 placeholder="分"
                 v-model="apprDocInfo.break_time_min">
               </el-input>
              </el-form-item >
              <el-row :gutter="0">
             <el-form-item label="支払サイト" :show-message="false" required label-width="110px"></el-form-item>
            <el-form-item
              prop="payment_plan_d">
            <el-autocomplete
              size="small"
              class="inline-input"
              style="width:100px"
              :maxlength="3"
              :min="1"
              :max="365"
              v-model="apprDocInfo.payment_plan_d"
              :fetch-suggestions="listPaymentPlanD"
              placeholder="日"
              @select="selectPaymentPlanD">
            </el-autocomplete>
            日
            </el-form-item>
            </el-row>
            <el-form-item
            label-width="100px"
              label="（">
            <el-select
              size="small"
              style="width:80px"
              v-model="apprDocInfo.payment_due_k"
              placeholder="" >
                <el-option
                v-for="item in payment_due_k_list"
                :key="item.value"
                :label="item.label"
                :value="item.value">
                </el-option>
              </el-select>
              締め
            </el-form-item>

            <el-form-item
              prop="pay_month_k">
            <el-select
              size="small"
              style="width:80px"
              v-model="apprDocInfo.pay_month_k"
              placeholder="" >
                <el-option
                v-for="item in pay_month_k_list"
                :key="item.value"
                :label="item.label"
                :value="item.value">
                </el-option>
              </el-select>
               </el-form-item>
              <el-form-item
                prop="pay_plan_d">
              <el-select
                size="small"
                style="width:80px"
                v-model="apprDocInfo.pay_plan_d"
                placeholder="" >
                  <el-option
                  v-for="item in payment_due_k_list"
                  :key="item.value"
                  :label="item.label"
                  :value="item.value">
                  </el-option>
                </el-select>
                支払い）
               </el-form-item>
             
             </div>
           </el-col>
        <el-col :span="12">
           <h3>【契約金情報（下位）】</h3>
           <div style="background-color: #E0FFFF">
            <el-row :gutter="0" style="height: 58px;">
            <el-form-item
              label-width="650px"
              label=" ">
             </el-form-item>
            </el-row>
            
        <el-form-item
              required
              label="単価（下位）"
              prop="cost_price">
              <el-input
                size="small"
                 type="text"
                 style="width:100px"
                 :minlength="2"
                 :maxlength="10"
                 v-model="apprDocInfo.cost_price">
               </el-input>
              (税込: {{formatMoney(apprDocInfo.cost_price*(1+tax_rate/100),0,"￥")}})
            </el-form-item>
        <el-form-item
        label-width="100px"
        label="精算条件"
        prop="bp_accounting_pat_k">
        <el-select
          size="small"
          style="width:100px"
          v-model="apprDocInfo.bp_accounting_pat_k"
          placeholder="精算条件" >
              <el-option
              v-for="item in accounting_pat_k_list"
              :key="item.value"
              :label="item.label"
              :value="item.value">
              </el-option>
          </el-select>
         </el-form-item >

         <el-form-item
            label-width="100px"
            label="上限時間"
             prop="bp_wh_max">
              <el-input
              size="small"
              type="text"
              style="width:100px"
              placeholder="時間"
              :disabled="!bp_wh_max_valid_f"
              v-model.number.lazy="apprDocInfo.bp_wh_max">
            </el-input>
           </el-form-item >
          <el-form-item
              label-width="100px"
              label="下限時間"
               prop="bp_wh_min">
                <el-input
                size="small"
                type="text"
                style="width:100px"
                placeholder="時間"
                :disabled="!bp_wh_min_valid_f"
                v-model.number.lazy="apprDocInfo.bp_wh_min">
              </el-input>
             </el-form-item >
          <el-form-item
              label-width="100px"
              label="中割時間"
               prop="bp_wh_mid">
                <el-input
                size="small"
                type="text"
                style="width:100px"
                placeholder="時間"
                :maxlength="3"
                :min="0"
                :max="999"
                :disabled="!bp_wh_mid_valid_f"
                v-model.number="apprDocInfo.bp_wh_mid">
              </el-input>
             </el-form-item >

             <el-form-item
              label-width="100px"
                label="超過単価"
                 prop="bp_excess_price">
                  <el-input
                  size="small"
                  type="text"
                  style="width:120px"
                  :disabled="false"
                  v-model="bp_excess_price">
                </el-input>
               </el-form-item >
               <el-row :gutter="0">
             <el-form-item
              label-width="100px"
                label="控除単価"
                 prop="bp_deducation_price">
                  <el-input
                  size="small"
                  type="text"
                  style="width:120px"
                  :disabled="false"
                  v-model="bp_deducation_price">
                </el-input>
               </el-form-item >
              </el-row>
        <el-form-item
        label-width="100px"
        label="時間単位"
        prop="work_time_unit_k">
        <el-select
          size="small"
          style="width:80px"
          v-model="apprDocInfo.bp_work_unit_k"
          placeholder="" >
              <el-option
              v-for="item in work_time_unit_k_list"
              :key="item.value"
              :label="item.label"
              :value="item.value">
              </el-option>
          </el-select>
         </el-form-item >

            <el-form-item
            label-width="100px"
               label="休憩時間"
                prop="bp_break_time_min">
                 <el-input
                 size="small"
                 type="text"
                 style="width:100px"
                 placeholder="分"
                 v-model="apprDocInfo.bp_break_time_min">
               </el-input>
              </el-form-item >
              <el-row :gutter="0">
            <el-form-item label="支払サイト" :show-message="false" required label-width="110px"></el-form-item>
            <el-form-item
             prop="bp_payment_plan_d">
            <el-autocomplete
              size="small"
              class="inline-input"
              style="width:100px"
              v-model="apprDocInfo.bp_payment_plan_d"
              :fetch-suggestions="listPaymentPlanD"
              placeholder="日"
              @select="selectBpPaymentPlanD">
            </el-autocomplete>
            日
            </el-form-item>
            </el-row>
            <el-form-item
            label-width="100px"
              label="（">
            <el-select
              size="small"
              style="width:80px"
              v-model="apprDocInfo.bp_payment_due_k"
              placeholder="" >
                <el-option
                v-for="item in payment_due_k_list"
                :key="item.value"
                :label="item.label"
                :value="item.value">
                </el-option>
              </el-select>
              締め
            </el-form-item>
            <el-form-item
              prop="bp_pay_month_k">
            <el-select
              size="small"
              style="width:80px"
              v-model="apprDocInfo.bp_pay_month_k"
              placeholder="" >
                <el-option
                v-for="item in pay_month_k_list"
                :key="item.value"
                :label="item.label"
                :value="item.value">
                </el-option>
              </el-select>
            </el-form-item>
            <el-form-item
              prop="bp_pay_plan_d">
              <el-select
                size="small"
                style="width:80px"
                v-model="apprDocInfo.bp_pay_plan_d"
                placeholder="" >
                  <el-option
                  v-for="item in payment_due_k_list"
                  :key="item.value"
                  :label="item.label"
                  :value="item.value">
                  </el-option>
                </el-select>
                支払い）
            </el-form-item>
            </div>
        </el-col>
       </el-row>
          <el-row :gutter="0">
             <el-form-item
              label-width="650px"
              label=" ">
             </el-form-item>
            </el-row>
        <apprdoc-diff-table
          :apprDocInfo="apprDocInfo"
          :apprDocInfoLast="apprDocInfo_last"
        >
        </apprdoc-diff-table>
      <el-row :gutter="0">
             <el-form-item
              label-width="650px"
              label=" ">
             </el-form-item>
            </el-row>
          <h3>【コメント事項】</h3>
           <el-form-item
           label-width="140px"
            label="商流記載"
            prop="commercial_dist_remark">
            <el-input
              type="textarea"
              :rows="12"
              style="width:800px"
              placeholder=""
              v-model="apprDocInfo.commercial_dist_remark">
            </el-input>
          </el-form-item >
           

      </el-form>
          <el-col :span="10">
          <span id="ent_button">
            <el-button
            type="success"
            @click="ent">登録
          </el-button>
          </span>
         </el-col>
<client-staff_popup
  :client_staff_dlg = "client_staff_dlg">
</client-staff_popup>
</div>
</template>


<script>
import {ApprDocDiffTable, ApprDocSalesTable, ClientStaffPopUp} from '@/webedi/apprDoc/components';

  export default {
    components: {
        'apprdoc-diff-table' : ApprDocDiffTable,
        'client-staff_popup' : ClientStaffPopUp
      },
    data() {

      //保険加入Check
      var chkInsuranceK = (rule, value, callback) => {
          //1：正社員
          //2：契約社員
          //3：出向社員
          //4：派遣社員
          //5：委託契約
          //１～４社保あり、５社保なし
        if(this.$_.includes(['1', '2', '3', '4'], this.apprDocInfo.emp_pat_k)){
            if(this.$_.trim(value) === ''){
              callback(new Error('社員の場合、入力必須です'));
            }
        }else{
             if(this.$_.trim(value) !== ''){
              callback(new Error('社員以外の場合、入力不可です'));
            }
        }
        callback();
      };

      //契約Check
      var chkAffiliationK = (rule, value, callback) => {
          //1：正社員
          //2：契約社員
          //3：出向社員
          //4：派遣社員
          //5：委託契約
          //１～４社保あり、５社保なし
        if(this.apprDocInfo.emp_pat_k === '5'){
            if(this.$_.trim(value) === ''){
              callback(new Error('委託契約の場合、入力必須です'));
            }
        }else{
             if(this.$_.trim(value) !== ''){
              callback(new Error('委託契約以外の場合、入力不可です'));
            }
        }
        callback();
      };

      //正数Check
      var chkPlusInt = (rule, value, callback) => {
        var p_val = this.$_.trim(value);
        if(!this.$valid.isEmpty(p_val) &&
            !this.$valid.isInt(p_val)){
          callback(new Error('整数を入力してください。'));
        }else{
          if(p_val < 0){
            callback(new Error('正整数を入力してください。'));
          }
        }
        callback();
      };

      //精算条件 上限時間 下限時間
      var chkWhMaxMin = (rule, value, callback) => {
      // 1：上下割
      // 2：中割
      // 3：固定
        var p_val = this.$_.trim(value);
        if(this.apprDocInfo.accounting_pat_k === '1'){
          if(this.$valid.isEmpty(p_val)){
            callback(new Error('精算条件が上下割の場合、入力必須です'));
          }
        }else{
            /*if(!this.$valid.isEmpty(p_val)){
            callback(new Error('精算条件が上下割以外の場合、入力不可です'));
          }*/
          if(!this.$valid.isEmpty(p_val) && this.apprDocInfo.accounting_pat_k === '3'){
            callback(new Error('精算条件が固定の場合、入力不可です'));
          }
        }
        callback();
      };

      //精算条件 中割時間
      var chkWhMid = (rule, value, callback) => {
      // 1：上下割
      // 2：中割
      // 3：固定
        var p_val = this.$_.trim(value);
        if(this.apprDocInfo.accounting_pat_k === '2'){
          if(this.$valid.isEmpty(p_val)){
            callback(new Error('精算条件が中割の場合、入力必須です'));
          }
        }else{
            if(!this.$valid.isEmpty(p_val)){
            callback(new Error('精算条件が中割以外の場合、入力不可です'));
          }
        }
        callback();
      };

      //精算条件 上限時間 下限時間
      var chkBpWhMaxMin = (rule, value, callback) => {
      // 1：上下割
      // 2：中割
      // 3：固定
        var p_val = this.$_.trim(value);
        if(this.apprDocInfo.bp_accounting_pat_k === '1'){
          if(this.$valid.isEmpty(p_val)){
            callback(new Error('精算条件が上下割の場合、入力必須です'));
          }
        }else{
            /*if(!this.$valid.isEmpty(p_val)){
            callback(new Error('精算条件が上下割以外の場合、入力不可です'));
          }*/
           if(!this.$valid.isEmpty(p_val) && this.apprDocInfo.accounting_pat_k === '3'){
            callback(new Error('精算条件が固定の場合、入力不可です'));
          }
        }
        callback();
      };

      //精算条件 中割時間
      var chkBpWhMid = (rule, value, callback) => {
      // 1：上下割
      // 2：中割
      // 3：固定
        var p_val = this.$_.trim(value);
        if(this.apprDocInfo.bp_accounting_pat_k === '2'){
          if(this.$valid.isEmpty(p_val)){
            callback(new Error('精算条件が中割の場合、入力必須です'));
          }
        }else{
            if(!this.$valid.isEmpty(p_val)){
            callback(new Error('精算条件が中割以外の場合、入力不可です'));
          }
        }
        callback();
      };

      //手当名称と手当率がペアチェック
      var chkOtherAllow1Nm = (rule, value, callback) => {
        var p_val = this.$_.trim(value);
        var p_other_allow1_amt = this.apprDocInfo.other_allow1_amt;
        if(p_other_allow1_amt !== ''
               && this.$valid.isEmpty(p_val)){
              callback(new Error('手当金額が入力されている場合、手当名称が必須です。'));
         }
        callback();
      };
      var chkOtherAllow1Amt = (rule, value, callback) => {
        var p_val = this.$_.trim(value);
        var p_other_allow1_nm = this.apprDocInfo.other_allow1_nm;
        if(p_other_allow1_nm !== ''
               && p_val === ''){
              callback(new Error('手当名称が入力されている場合、手当金額が必須です。'));
         }
        callback();
      };
      //手当名称と手当率がペアチェック
      var chkOtherAllow2Nm = (rule, value, callback) => {
        var p_val = this.$_.trim(value);
        var p_other_allow2_amt = this.apprDocInfo.other_allow2_amt;
             if(p_other_allow2_amt !== ''
               && this.$valid.isEmpty(p_val)){
              callback(new Error('手当金額が入力されている場合、手当名称が必須です。'));
         }
        callback();
      };

        var chkOtherAllow2Amt = (rule, value, callback) => {
        var p_val = this.$_.trim(value);
        var p_other_allow2_nm = this.apprDocInfo.other_allow2_nm;
        if(p_other_allow2_nm !== ''
               && p_val === ''){
              callback(new Error('手当名称が入力されている場合、手当金額が必須です。'));
         }
        callback();
      };

      var chkApprDocDtFromAndTo = (rule, value, callback) => {
        var from = this.apprDocInfo.appr_doc_dt_from;
        var to = this.apprDocInfo.appr_doc_dt_to;
        if(from !=='' && to !==''
           && this.$moment(to).isBefore(from)){
             callback(new Error('受注期間が逆転しています'));
           }
           callback();
      };

      var chkBussinessDaysAndWorkDays = (rule, value, callback) => {
        var work_days = this.apprDocInfo.work_days; //当月稼働日
        var business_days = this.apprDocInfo.business_days; //当月営業日
        if(this.$_.gt(work_days,business_days)){
          callback(new Error('稼働日は営業日より大きいです'));
        }
        callback();
      };

     var chkBasicSalary = (rule, value, callback) => {

        var allow_amt_sum = this.$_.sumBy([this.apprDocInfo.appoint_allow_amt    /*役職手当*/
                  , this.apprDocInfo.house_allow_amt    /*住宅手当*/
                  , this.apprDocInfo.communication_allow_amt   /*//通信手当*/
                  , this.apprDocInfo.site_allow_amt    /*//現場手当 */
                  , this.apprDocInfo.overwork_allow_amt   /* //残業手当*/
                  , this.apprDocInfo.other_allow1_amt  /*//追加項目1*/
                  , this.apprDocInfo.other_allow2_amt],
                  function (o) {return parseInt(o)}
                  );  /*//追加項目2*/

        if(this.$_.gt(allow_amt_sum, this.apprDocInfo.cost_price)){
            callback(new Error('各手当の合計が要員単金を超えています'));
            // this.$message.error("合計が間違いです。");
         }
        callback();
      };

      var chkWhMin = (rule, value, callback) => {
        var wh_max = this.$_.toNumber(this.$_.trim(this.apprDocInfo.wh_max));
        var wh_min = this.$_.toNumber(this.$_.trim(this.apprDocInfo.wh_min));
        // console.log('wh_max=%s',wh_max);
        // console.log('wh_min=%s',wh_min);
        if(wh_max !=='' && wh_min !=='' && this.$_.gt(wh_min,wh_max)){
          callback(new Error('下限時間は上限時間より大きい'));
        }
        callback();
      };

      var chkBpWhMin = (rule, value, callback) => {
        var wh_max = this.$_.toNumber(this.$_.trim(this.apprDocInfo.bp_wh_max));
        var wh_min = this.$_.toNumber(this.$_.trim(this.apprDocInfo.bp_wh_min));
        if(wh_max !=='' && wh_min !=='' && this.$_.gt(wh_min,wh_max)){
          callback(new Error('下限時間は上限時間より大きい'));
        }
        callback();
      };

      return {

        apprDocInfo: {
          appr_doc_no : '',  //稟議書NO
          // use_flg : '',  //使用FLG
          appr_doc_dt_from : '',  //対象期間From
          appr_doc_dt_to : '',  //対象期間To
          appr_doc_create_k : '',  //稟議書作成区分
          appr_doc_create_dt : new Date(),  //稟議書作成日
          // appr_doc_st : '',  //稟議書状態
          business_main_user_id : '',  //営業主担当
          business_sub_user_id : '',  //営業副担当
          dept_cd : '',  //部署C
          emp_no : '',  //社員番号
          emp_nm : '',  //社員名
          emp_nm_fuli : '',  //社員名（フリガナ）
          emp_sex : '',  //性別
          emp_birthday : '',  //生年月日
          emp_age : '', //年齢（計算項目）
          site_entrance_dt : '',  //入場日
          entrance_dt : '',  //入社日
          emp_my_number : '',  //要員マイナンバー
          emp_pat_k : '',  //希望雇用形態
          affiliation_k : '',  //所属区分
          entrance_record_k : '',  //入社経歴
          old_appr_doc_no : '',  //前回稟議書NO
          take_over_k : '',  //引取区分
          order_recv_hope_dt : '',  //注文書受領日（見込）
          from_home_station_nm : '',  //最寄駅（自宅）
          client_contract_k : '',  //契約（上位）
          appoint_allow_ratio : 0,  //役職手当割合
          house_allow_ratio : 0,  //住宅手当割合
          communication_allow_ratio : 0,  //通信手当割合
          site_allow_ratio : 0,  //現場手当割合
          overwork_allow_ratio : 0,  //残業手当割合
          other_allow1_nm : '',  //そのた手当1名称
          other_allow1_ratio : 0,  //そのた手当1割合
          other_allow2_nm : '',  //そのた手当２名称
          other_allow2_ratio : 0,  //そのた手当２割合
          sales_addup_month : '',  //売上販売計上月
          sum_contract_wm : '',  //契約総工数(人月)
          work_days : '',  //当月稼働日
          billing_month : '',  //請求計上月
          sell_price : '',  //上位単金/単価
          business_days : '',  //当月営業日
          cost_price : '',  //要員渡し/原価
          travel_cost_first_mon : '',  //交通費(初月)
          other_cost_first_mon : '',  //その他(初月)
          travel_cost : '',  //交通費
          insurance_cost : '',  //社保費
          other_cost : '',  //その他
          insurance_k : '',  //保険加入区分
          insurance_join_ymd : '',  //社保加入予定日
          client_no : '',  //クライアント番号
          client_nm : '',  //クライアント名
          client_url : '',  //クライアントURL
          client_my_number : '',  //クライアント法人マイナンバー
          client_post_no : '',  //クライアント郵便番号
          client_addr : '',  //クライアント連絡先住所
          client_tel_no : '',  //クライアント電話番号
          client_fax_no : '',  //クライアントFAX番号
          client_staff_nm : '',  //クライアント担当者名
          client_staff_nm_fuli : '',  //クライアント担当者名（フリガナ）
          client_staff_dept_nm : '',  //クライアント担当者所属部署
          client_staff_position : '',  //クライアント担当者役職
          client_staff_mobile_no : '',  //クライアント担当者携帯番号
          client_mail_addr : '',  //クライアントメール
          client_mail_addr_cc : '',  //クライアントメールCC
          client_req_staff_nm : '',  //請求担当者名
          client_req_staff_nm_fuli : '',  //請求担当者名（フリガナ）
          client_req_staff_dept_nm : '',  //請求担当者所属部署
          client_req_staff_position : '',  //請求担当者役職
          client_req_tel_no : '',  //請求担当者電話番号
          client_req_fax_no : '',  //請求担当者FAX番号
          client_req_staff_mobile_no : '',  //請求担当者携帯番号
          client_req_mail_addr : '',  //請求担当者メール
          client_req_mail_addr_cc : '',  //請求担当者メールCC
          client_req_post_no : '',  //請求担当者郵便番号
          client_req_addr : '',  //請求担当者連絡先住所
          client_order_no : '',  //受注（注文）書No
          pj_cd : '',  //作業案件CD
          pj_nm : '',  //作業案件名称
          work_place : '',  //勤務地/最寄駅
          work_content : '',  //作業内容・範囲
          accounting_pat_k : '',  //精算条件
          salary_pay_k : '',  //給料支払区分
          wh_max : '',  //作業時間（上限・H）
          wh_min : '',  //作業時間（下限・H）
          wh_mid : '',  //中割時間
          excess_price : '',  //超過単価（円）
          deducation_price : '',  //控除単価（円）
          pj_start_dt : '',  //業務案件今月分の開始日
          pj_end_dt : '',  //業務案件今月分の終了日
          work_time_unit_k : '',  //作業時間単位
          contract_work_month : '',  //工数（契約用）
          pj_class_nm : '',  //案件分類名称
          work_start_time : '09:00',  //出勤時刻
          work_end_time : '18:00',  //退勤時刻
          break_time_min : '',  //休憩時間（分）
          payment_plan_d : '',  //支払サイト/出金日付の日
          payment_due_k : '',  //支払サイト/締日区分
          pay_month_k : '',  //支払サイト/支払月区分
          pay_plan_d : '',  //支払サイト/支払日付の日
          salary_pay_plan_dt : '',  //支払サイト/出金予定日
          bp_comp_id : '',  //所属会社ID
          bp_comp_emp_nm : '',  //所属会社名/社員氏名
          bp_comp_url : '',  //所属会社URL
          bp_my_number : '',  //所属会社法人マイナンバー
          bp_post_no : '',  //所属会社郵便番号
          bp_addr : '',  //所属会社連絡先住所
          bp_tel_no : '',  //所属会社電話番号
          bp_fax_no : '',  //所属会社FAX番号
          bp_staff_nm : '',  //所属会社担当者名
          bp_staff_nm_fuli : '',  //所属会社担当者名（フリガナ）
          bp_staff_dept_nm : '',  //所属会社担当者所属部署
          bp_staff_position : '',  //所属会社担当者役職
          bp_staff_tel_no : '',  //所属会社担当者電話番号
          bp_staff_fax_no : '',  //所属会社担当者FAX番号
          bp_staff_mobile_no : '',  //所属会社担当者携帯番号
          bp_staff_mail_addr : '',  //所属会社担当者個人メール
          bp_staff_mail_addr_cc : '',  //所属会社担当者個人メールCC
          staff_addr : '',  //要員住所
          staff_post_no : '',  //要員郵便番号
          staff_mail_addr : '',  //要員メールアドレス
          staff_tel_no : '',  //要員電話番号
          staff_mobile_no : '',  //要員携帯電話番号
          bp_accounting_pat_k : '',  //精算条件（BP）
          bp_salary_pay_k : '', //給料支払区分（BP）
          bp_wh_max : '',  //作業時間（上限・H）（BP）
          bp_wh_min : '',  //作業時間（下限・H）（BP）
          bp_wh_mid : '',  //中割時間（BP）
          bp_excess_price : '',  //超過単価（円）（BP）
          bp_deducation_price : '',  //控除単価（円）（BP）
          bp_work_unit_k : '',  //作業時間単位（BP）
          bp_work_start_time : '09:00',  //出勤時刻（BP）
          bp_work_end_time : '18:00',  //退勤時刻（BP）
          bp_break_time_min : '',  //休憩時間（分）（BP）
          bp_payment_plan_d : '',  //支払サイト/出金日付の日（BP）
          bp_payment_due_k : '',  //支払サイト/締日区分（BP）
          bp_pay_month_k : '',  //支払サイト/支払月区分（BP）
          bp_pay_plan_d : '',  //支払サイト/支払日付の日（BP）
          bp_salary_pay_plan_dt : '',  //支払サイト/出金予定日（BP）
          mon_pro_k : '',  //月次処理区分
          work_rep_f : '',  //作業報告書F
          bill_f : '',  //請求書F
          work_rep_dl_k : '',  //作業報告書締め区分
          bill_dl_k : '',  //請求書締め区分
          mon_attach_file_k : '2',  //添付資料F
          mon_attach_file_id : '',  //添付ファイルID
          procedure_method_k : '',  //手続き方法
          withdrawal_reason_k : '',  //退社理由
          withdrawal_letter_k : '',  //離職票の有無
          insurance_con_k : '',  //社保任意継続
          withdrawal_req_doc : '',  //必要書類
          procedure_dt : '',  //手続き日付
          withdrawal_dt : '',  //退社日
          after_withdrawal_post_no : '',  //退社後郵便番号
          after_withdrawal_addr : '',  //退社後住所
          purchase_remark : '',  //連絡事項（購買へ）
          commercial_dist_remark : '',  //商流記載
          emp_remark : '',  //特記事項（要員）
          emp_info_chg_flg : 'N', //個人情報変更Flg:Y：変更あり、N：変更なし
          procedure_k : '1',  // 手続き区分:1:新規入社処理 2:契約期間更新処理 3:退社処理
          appoint_allow_amt : '', //役職手当
          house_allow_amt : '',  //住宅手当
          communication_allow_amt : '', //通信手当
          site_allow_amt : '',  //現場手当
          overwork_allow_amt : '',  //残業手当
          other_allow1_amt :' ',  //その他１手当
          other_allow2_amt : '',  //その他２手当

        day_travel_cost : '', // 交通費日額（往復）
        month_pass_travel_cost : '', // 1ヶ月の通勤代（定期）
        pay_travel_costa_max : 20000, // 支払上限
        rount_dlt_content : '', // 自宅→現場（乗り換え含め交通経路記入）

        //エビデンス
        work_evidence_list :[{display_order:'1',workEvidence:''}],

        commute_route_list :[
        {
          from_home_line_nm : '', // 自宅最寄駅の線
          from_home_station_nm : '', // 自宅最寄駅の駅
          to_comp_line_nm : '', // 現場最寄駅の線
          to_comp_station_nm : '', // 現場最寄駅の駅
          travel_cost : 0 // 通勤経路の交通費
        }
        ],
        },

        apprDocInfo_last: {},

        //要員検索条件
        emp_no_key : '',
        emp_nm_key : '',

        //案件検索条件
        pj_cd_key : '',
        pj_nm_key : '',

        //クライアント検索条件
        client_no_key : '',
        client_nm_key : '',

        //クライアント検索条件
        bp_comp_id_key : '',
        bp_comp_emp_nm_key : '',

        //担当者Dialog情報
        client_staff_dlg : {
            data_list : [],
            show_flg : false,
            callback_fun : undefined,
        },

        tax_rate: 8,
        appr_doc_create_k_list:
        [
        {
          value: '1',
          label: '通常更新'
        }, {
          value: '2',
          label: '新規'
        }, {
          value: '3',
          label: '条件変更'
        }, {
          value: '4',
          label: '正社員化'
        }, {
          value: '5',
          label: '退職'
        }, {
          value: '6',
          label: '再販'
        }],

        business_user_id_list : [],
        emp_sex_list: [{
          value: '1',
          label: '男'
        }, {
          value: '2',
          label: '女'
        }],
        emp_pat_k_list: [{
          value: '1',
          label: '正社員'
        },
        {
          value: '2',
          label: '契約社員'
        },
          {
          value: '3',
          label: '出向社員'
        },
          {
          value: '4',
          label: '派遣社員'
        },
          {
          value: '5',
          label: '委託契約'
        }
        ],
        insurance_k_list: [{
          value: '1',
          label: '雇用保険のみ'
        },
        {
          value: '2',
          label: '社保険完備'
        },
          {
          value: '3',
          label: '保険無し'
        }
        ],
        affiliation_k_list: [{
          value: '1',
          label: '個人事業主'
        },
        {
          value: '2',
          label: '協力会社（ＢＰ）'
        }
        ],
        client_contract_k_list: [{
          value: '1',
          label: '委任契約'
        },
        {
          value: '2',
          label: '派遣契約'
        }
        ],
        take_over_k_list: [{
          value: '1',
          label: '新規取引先'
        },
        {
          value: '2',
          label: '既存取引先'
        }
        ],

        mon_pro_k_list :[
        {
          value: '1',
          label: '通常締結'
        },
        {
          value: '2',
          label: 'EDI'
        }
        ],

        work_rep_f_list :[
        {
          value: '1',
          label: '指定なし'
        },
        {
          value: '2',
          label: '客先F'
        }
        ],

        bill_f_list :[
        {
          value: '1',
          label: '指定なし'
        },
        {
          value: '2',
          label: '客先F'
        }
        ],

        work_rep_dl_k_list :[
        {
          value: '1',
          label: '指定なし'
        },
        {
          value: '2',
          label: '第1営業日'
        },
        {
          value: '3',
          label: '第2営業日'
        },
        {
          value: '4',
          label: '第3営業日'
        },
        {
          value: '5',
          label: '第4-5営業日'
        }
        ],
        bill_dl_k_list :[
        {
          value: '1',
          label: '指定なし'
        },
        {
          value: '2',
          label: '第1営業日'
        },
        {
          value: '3',
          label: '第2営業日'
        },
        {
          value: '4',
          label: '第3営業日'
        },
        {
          value: '5',
          label: '第4-5営業日'
        }
        ],
        salary_pay_k_list :[
        {
          value: '1',
          label: '月給'
        },
        {
          value: '2',
          label: '時給'
        }
        ],

        accounting_pat_k_list :[
        {
          value: '1',
          label: '上下割'
        },
        {
          value: '2',
          label: '中割'
        },
        {
          value: '3',
          label: '固定'
        }
        ],

        work_time_unit_k_list :[
        {
          value: '0',
          label: '0分'
        },
        {
          value: '1',
          label: '1分'
        },
        {
          value: '2',
          label: '5分'
        },
        {
          value: '3',
          label: '10分'
        },
        {
          value: '4',
          label: '15分'
        },
        {
          value: '5',
          label: '30分'
        },
        {
          value: '6',
          label: '60分'
        }
        ],
        payment_due_k_list :[
        {value: 'U',label: '月末'},
        {value: '1',label: '1日'},
        {value: '2',label: '2日'},
        {value: '3',label: '3日'},
        {value: '4',label: '4日'},
        {value: '5',label: '5日'},
        {value: '6',label: '6日'},
        {value: '7',label: '7日'},
        {value: '8',label: '8日'},
        {value: '9',label: '9日'},
        {value: 'A',label: '10日'},
        {value: 'B',label: '11日'},
        {value: 'C',label: '12日'},
        {value: 'D',label: '13日'},
        {value: 'E',label: '14日'},
        {value: 'F',label: '15日'},
        {value: 'G',label: '16日'},
        {value: 'H',label: '17日'},
        {value: 'I',label: '18日'},
        {value: 'J',label: '19日'},
        {value: 'K',label: '20日'},
        {value: 'L',label: '21日'},
        {value: 'M',label: '22日'},
        {value: 'N',label: '23日'},
        {value: 'O',label: '24日'},
        {value: 'P',label: '25日'},
        {value: 'Q',label: '26日'},
        {value: 'R',label: '27日'},
        {value: 'S',label: '28日'},
        {value: 'T',label: '29日'}
        ],

        pay_month_k_list :[
        {
          value: '1',
          label: '当月'
        },
        {
          value: '2',
          label: '翌月'
        },
        {
          value: '3',
          label: '翌々月'
        }
        ],

        procedure_method_k_list :[
        {
          value: '1',
          label: '来社（訪問）'
        },
        {
          value: '2',
          label: '郵送'
        }
        ],

        withdrawal_reason_k_list :[
        {
          value: '1',
          label: '期間満了'
        },
        {
          value: '2',
          label: '自己都合'
        }
        ],

        withdrawal_req_doc_list :[
        {
          value: '1',
          label: '雇用契約書'
        },
        {
          value: '2',
          label: '退職願（届）'
        }
        ],

        dept_list:[
          {
            dept_cd : '001',
            dept_nm : '人財開発室'
          },
          {
            dept_cd : '002',
            dept_nm : '営業部'
          },
          {
            dept_cd : '003',
            dept_nm : '購買部'
          },
          {
            dept_cd : '004',
            dept_nm : '人事部'
          },
         ],

        procedure_k_list:[
          {
            value : '1',
            label : '新規入社処理'
          },
          {
            value : '2',
            label : '契約期間更新処理'
          },
          {
            value : '3',
            label : '退社処理'
          }
         ],

        entrance_record_list : [],



        emp_info_list:[],

        pj_info_list :[],

        client_info_list :[],

        bp_comp_info_list :[],

        // client_no_list : [],
        // bp_no_list : [],
        timeout:  null,

        //健康保険標準報酬リスト
        health_insurance_base_price_list :[],
        //厚生年金標準報酬リスト
        employee_pension_base_price_list :[],
        //健康保険料率リスト
        health_insurance_ratio_list :[],
        //厚生年金料率リスト
        employee_pension_ratio_list :[],

        //雇用保険
        employee_insurance : 0,


        rules: {
          appr_doc_create_k: [
            { required: true,
              message: '入力必須です。',
              trigger: 'blur'
            }
          ],
           appr_doc_dt_from: [
            { type: 'date',
              required: true,
              message: '入力必須です。'
            },
             { type : 'date',
               messsage : '日付のフォーマットが間違った。'
             },
             {
               validator : chkApprDocDtFromAndTo,
               trigger: 'change,blur'
             }
          ],
          appr_doc_dt_to: [
            { type: 'date',
              required: true,
              message: '入力必須です。',
              trigger: 'change,blur'
            },
            {
               validator : chkApprDocDtFromAndTo,
               trigger: 'change,blur'
             }
          ],
          pj_cd: [
            { required: true,
              message: '入力必須です。',
              trigger: 'blur'
            }
          ],

          //入場日
          site_entrance_dt: [
            { type: 'date',
              required: true,
              message: '入力必須です。',
              trigger: 'change,blur'
            }
          ],
          //入社日
          entrance_dt: [
            { type: 'date',
              required: true,
              message: '入力必須です。',
              trigger: 'change,blur'
            }
          ],

          //営業主担当
          business_main_user_id: [
            { required: true,
              message: '入力必須です。',
              trigger: 'blur'
            }
          ],

          //氏名
          emp_nm: [
            { required: true,
              message: '入力必須です。',
              trigger: 'change'
            }
          ],

          //社員ID
          emp_no: [
            { required: true,
              message: '入力必須です。',
              trigger: 'blur'
            }
          ],

          //社員区分
          emp_pat_k: [
            { required: true,
              message: '入力必須です。',
              trigger: 'change'
            }
          ],

          //保険加入
          insurance_k: [
           {
             validator: chkInsuranceK,
             trigger: 'change'
           }
          ],

          //社保加入予定日
          insurance_join_ymd: [
           {
             validator: chkInsuranceK,
             trigger: 'blur,change'
           }
          ],

          //所属区分（再委託先）
          affiliation_k: [
           {
             validator: chkAffiliationK,
             trigger: 'change'
           }
          ],

          //クライアントID
         client_no : [
            { required: true,
              message: '入力必須です。',
              trigger: 'blur'
            }
          ],
          //作業内容
         work_content : [
            { required: true,
              message: '入力必須です。',
              trigger: 'blur'
            }
          ],
          //売上販売計上月
          sales_addup_month : [
            { type: 'date',
              required: true,
              message: '入力必須です。',
              trigger: 'blur,change'
            }
          ],
          //請求計上月
          billing_month :  [
            { type: 'date',
              required: true,
              message: '入力必須です。',
              trigger: 'blur,change'
            }
          ],

        //契約総工数(人月)
        sum_contract_wm : [
            { type: 'number',
              required: true,
              message: '入力必須です。',
              trigger: 'blur'
            },
            { type: 'number',
              min:0,
              max:99999.99,
              message: '0～99999.99の値を入力してください。',
              trigger: 'blur'
            }
        ],

        //当月稼働日
        work_days : [
          {
            validator : chkBussinessDaysAndWorkDays,
            trigger : 'blur, change'
          }
        ],

        //当月営業日
        business_days : [
          {
            validator : chkBussinessDaysAndWorkDays,
            trigger : 'blur, change'
          }
        ],
          //上位単金
          sell_price :  [
            { required: true,
              message: '入力必須です。',
              trigger: 'blur'
            },
            {
              validator : chkPlusInt,
              trigger : 'blur'
            }
          ],
          //要員渡し
          cost_price :  [
            { required: true,
              message: '入力必須です。',
              trigger: 'blur'
            },
            {
              validator : chkPlusInt,
              trigger : 'blur'
            }
          ],

          //その他
          other_cost :  [
            { required: true,
              message: '入力必須です。',
              trigger: 'blur'
            },
            {
              validator : chkPlusInt,
              trigger : 'blur'
            }
          ],

          //その他(初月)
          other_cost_first_mon :  [
            { required: true,
              message: '入力必須です。',
              trigger: 'blur'
            },
            {
              validator : chkPlusInt,
              trigger : 'blur'
            }
          ],

          //交通費(初月)
          travel_cost_first_mon :  [
            { required: true,
              message: '入力必須です。',
              trigger: 'blur'
            },
            {
              validator : chkPlusInt,
              trigger : 'blur'
            }
          ],

          //現場手当割合
          site_allow_amt :[
            {
              validator : chkBasicSalary,
              trigger : 'blur'
            },
            {
              validator : chkPlusInt,
              trigger : 'blur'
            }
          ],
          //残業手当割合
          overwork_allow_amt :[
          {
              validator : chkBasicSalary,
              trigger : 'blur'
            },
            {
              validator : chkPlusInt,
              trigger : 'blur'
            }
          ],
          //役職手当割合
          appoint_allow_amt :[
            {
              validator : chkBasicSalary,
              trigger : 'blur'
            },
            {
              validator : chkPlusInt,
              trigger : 'blur'
            }
          ],
          //通信手当割合
          communication_allow_amt : [
            {
              validator : chkBasicSalary,
              trigger : 'blur'
            },
            {
              validator : chkPlusInt,
              trigger : 'blur'
            }
          ],
          //住宅手当割合
          house_allow_amt : [
          {
              validator : chkBasicSalary,
              trigger : 'blur'
            },
            {
              validator : chkPlusInt,
              trigger : 'blur'
            }
          ],

          //手当名称
           other_allow1_nm : [
             {
              validator : chkOtherAllow1Nm,
              trigger : 'blur'
             },
           ],
          //追加項目
          other_allow1_amt :[
            {
              validator : chkBasicSalary,
              trigger : 'blur'
            },
              {
              validator : chkOtherAllow1Amt,
              trigger : 'blur'
             },
            {
              validator : chkPlusInt,
              trigger : 'blur'
            }
          ],
          other_allow2_nm : [
             {
              validator : chkOtherAllow2Nm,
              trigger : 'blur'
             },
           ],

          //追加項目
          other_allow2_amt : [
          {
              validator : chkBasicSalary,
              trigger : 'blur'
            },
             {
              validator : chkOtherAllow2Amt,
              trigger : 'blur'
             },
            {
              validator : chkPlusInt,
              trigger : 'blur'
            }
          ],

          //クライアント名称
          client_nm :  [
            { required: true,
              message: '入力必須です。',
              trigger: 'blur'
            },
          ],

        //月次説明
        //区分
        mon_pro_k : [
            { required: true,
              message: '入力必須です。',
              trigger: 'change'
            },
          ],
        // 作業報告書F
        work_rep_f : [
            { required: true,
              message: '入力必須です。',
              trigger: 'change'
            },
          ],
        //請求書F
       bill_f : [
            { required: true,
              message: '入力必須です。',
              trigger: 'change'
            },
       ],
      //作業報告書締め区分
      work_rep_dl_k : [
            { required: true,
              message: '入力必須です。',
              trigger: 'change'
            },
       ],
      //請求書締め区分
      bill_dl_k : [
            { required: true,
              message: '入力必須です。',
              trigger: 'change'
            },
        ],
      //給料支払区分　新規追加項目
      salary_pay_k : [
            { required: true,
              message: '入力必須です。',
              trigger: 'change'
            },
        ],
      //精算条件
      accounting_pat_k : [
            { required: true,
              message: '入力必須です。',
              trigger: 'change'
            },
        ],
      //作業時間（上限・H）
      wh_max : [
            {
              validator : chkPlusInt,
              trigger : 'blur'
            },
            {
              validator : chkWhMaxMin,
              trigger : 'blur'
            },
            {
              validator : chkWhMin,
              trigger : 'blur'
            }
          ],

      //作業時間（下限・H）
      wh_min : [
             {
              validator : chkPlusInt,
              trigger : 'blur'
            },
            {
              validator : chkWhMaxMin,
              trigger : 'blur'
            },
            {
              validator : chkWhMin,
              trigger : 'blur'
            }
         ],

      //中割時間
      wh_mid : [
             {
              validator : chkPlusInt,
              trigger : 'blur'
            },
           {
              validator : chkWhMid,
              trigger : 'blur'
            }

          ],


      //超過単価（円）
      excess_price : [
            // { type:'number',
            //   required: true,
            //   message: '入力必須です。',
            //   trigger: 'blur'
            // },
            {
              validator : chkPlusInt,
              trigger : 'blur'
            }
          ],

      //控除単価（円）
      deducation_price : [
            // { type:'number',
            //   required: true,
            //   message: '入力必須です。',
            //   trigger: 'blur'
            // },
            {
              validator : chkPlusInt,
              trigger : 'blur'
            }
          ],

      //時間単位
      work_time_unit_k : [
            { required: true,
              message: '入力必須です。',
              trigger: 'change'
            },
        ],

      //就業時間
      work_start_time : [
            { required: true,
              message: '入力必須です。',
              trigger: 'change'
            },
        ],
      work_end_time : [
            { required: true,
              message: '入力必須です。',
              trigger: 'change'
            },
        ],

      //休憩時間
      break_time_min : [
            { required: true,
              message: '入力必須です。',
              trigger: 'blur'
            },
            {
              validator : chkPlusInt,
              trigger : 'blur'
            }
        ],
      //支払サイト
      //支払サイト/出金日付の日
      payment_plan_d : [
            {
              required: true,
              message: '支払サイトが入力必須です。',
              trigger: 'blur,change'
            },
            {
              validator : chkPlusInt,
              trigger : 'blur,change'
            }
        ],
       //支払サイト/締日区分
      payment_due_k : [
            { required: true,
              message: '入力必須です。',
              trigger: 'change'
            },
        ],
      //支払サイト/支払月区分
      pay_month_k : [
            { required: true,
              message: '入力必須です。',
              trigger: 'blur,change'
            },
        ],
      //支払サイト/支払日付の日
      pay_plan_d : [
            { required: true,
              message: '入力必須です。',
              trigger: 'change'
            },
        ],

      //BP
      //給料支払区分　新規追加項目
      bp_salary_pay_k : [
            { required: true,
              message: '入力必須です。',
              trigger: 'change'
            },
        ],
      //精算条件
      bp_accounting_pat_k : [
            { required: true,
              message: '入力必須です。',
              trigger: 'change'
            },
        ],
      //作業時間（上限・H）
      bp_wh_max : [
            {
              validator : chkPlusInt,
              trigger : 'blur'
            },
            {
              validator : chkBpWhMaxMin,
              trigger : 'blur'
            },
            {
              validator : chkBpWhMin,
              trigger : 'blur'
            }
          ],

      //作業時間（下限・H）
      bp_wh_min : [
             {
              validator : chkPlusInt,
              trigger : 'blur'
            },
            {
              validator : chkBpWhMaxMin,
              trigger : 'blur'
            },
            {
              validator : chkBpWhMin,
              trigger : 'blur'
            }

         ],

      //中割時間
      bp_wh_mid : [
             {
              validator : chkPlusInt,
              trigger : 'blur'
            },
           {
              validator : chkBpWhMid,
              trigger : 'blur'
            }

          ],


      //超過単価（円）
      bp_excess_price : [
            // {
            //   type:'number',
            //   required: true,
            //   message: '入力必須です。',
            //   trigger: 'blur'
            // },
            {
              validator : chkPlusInt,
              trigger : 'blur'
            }
          ],

      //控除単価（円）
      bp_deducation_price : [
            // { type:'number',
            //   required: true,
            //   message: '入力必須です。',
            //   trigger: 'blur'
            // },
            {
              validator : chkPlusInt,
              trigger : 'blur'
            }
          ],

      //時間単位
      bp_work_time_unit_k : [
            { required: true,
              message: '入力必須です。',
              trigger: 'change'
            },
        ],

      //就業時間
      bp_work_start_time : [
            { required: true,
              message: '入力必須です。',
              trigger: 'change'
            },
        ],
      bp_work_end_time : [
            { required: true,
              message: '入力必須です。',
              trigger: 'change'
            },
        ],

      //休憩時間
      bp_break_time_min : [
            { required: true,
              message: '入力必須です。',
              trigger: 'blur'
            },
            {
              validator : chkPlusInt,
              trigger : 'blur'
            }
        ],

      //BP支払サイト/出金日付の日
      bp_payment_plan_d : [
            { required: true,
              message: '入力必須です。',
              trigger: 'blur,change'
            },
            {
              validator : chkPlusInt,
              trigger : 'blur,change'
            }
        ],
       //BP支払サイト/締日区分
      bp_payment_due_k : [
            { required: true,
              message: '入力必須です。',
              trigger: 'change'
            },
        ],
      //支払サイト/支払月区分
      bp_pay_month_k : [
            { required: true,
              message: '入力必須です。',
              trigger: 'change'
            },
        ],
      //支払サイト/支払日付の日
      bp_pay_plan_d : [
            { required: true,
              message: '入力必須です。',
              trigger: 'change'
            },
        ],

      // //手続予定日
      // procedure_dt : [
      //       { type: 'date',
      //         required: true,
      //         message: '入力必須です。',
      //         trigger: 'change,blur'
      //       }
      //     ],

        }//rules end
      };
    }, //data end

    methods: {
      uploadSuccess(response, file, fileList) {

        //  console.log('上传文件', response);
        //  console.log('上传文件ID', response.data.fileId);
          this.apprDocInfo.mon_attach_file_id = response.data.fileId;
          this.apprDocInfo.mon_attach_file_k = '1';//ファイルフラグを有にする
       },

      ent(){
         var _this = this;
         //自動計算項目を登録Entに同期化する
        //  this.setEntInfoBeforeCommit();
        // console.log("_this.apprDocInfo=%s",JSON.stringify(_this.apprDocInfo));
         this.$refs.apprDocInfo.validate((valid) => {
          if (valid){
                _this.$confirm('稟議書データを登録してもよろしいでしょうか？', '注意', {
                  confirmButtonText: '確定',
                  cancelButtonText: '中止',
                  type: 'warning'
                }).then(() => {

                  

                  // _this.apprDocInfo = _this.apprDocInfo.map(
                  //     data => {
                  //       if (!data && data instanceof Date) {
                  //         return utils.formatDate(data, 'YYYY-MM-DD HH:mm:ss');
                  //       }
                  //     }
                  // );
                  console.table(_this.apprDocInfo);
                  _this.$http.post('/api/apprDoc/insert',_this.apprDocInfo)
                  .then(function (response) {
                                  var errorcode=response.data.errorcode;
                                    if(errorcode==="200"){
                                      _this.$message({
                                          type: 'success',
                                          message: '登録が成功しました!'
                                        });
                                      _this.apprDocInfo.appr_doc_no = response.data.data;
                                      _this.goToDetail();
                                    }else if(errorcode==="500"){
                                      _this.$message.error('登録が失敗しました。['+response.data.errormsg+']');
                                    }
                                  })
                                .catch(function (error) {
                                    console.log(error);
                                });
                }).catch((e) => {
                  console.log(e);
                  _this.$message({
                    type: 'info',
                    message: '登録が中止しました！'
                  });
                });
            }else{
               this.$message.error("入力項目にエラーがあります！");
            }
          })
      },

          //照会画面へ遷移
           goToDetail(){
            this.$router.push({ path: 'ApprDocDetail', query: { appr_doc_no: this.apprDocInfo.appr_doc_no }});
          },

          addEvidence(){

            this.apprDocInfo.work_evidence_list.push({display_order:0,work_evidence:''});
          },
          delEvidence(){
            this.apprDocInfo.work_evidence_list.pop();
          },
          addCommuteRoute(){
            this.apprDocInfo.commute_route_list.push({
            from_home_line_nm : '', // 自宅最寄駅の線
            from_home_station_nm : '', // 自宅最寄駅の駅
            to_comp_line_nm : '', // 現場最寄駅の線
            to_comp_station_nm : '', // 現場最寄駅の駅
            travel_cost : 0 // 通勤経路の交通費
            });

          },
          delCommuteRoute(){
            var p_commute_route_list = this.apprDocInfo.commute_route_list;
            if(p_commute_route_list.length > 0){
              this.apprDocInfo.commute_route_list.pop();
            }
          },

    //氏名により、要員リストを取得する
		loadEmpInfoList() {
      if(this.emp_nm_key ===''){return;}
      this.emp_no_key ='';
			var _this = this;
			_this.$http({
				 method:'get',
				 url:'/api/jobnavi/candidateList',
         params:{
                  empName: _this.emp_nm_key
                }
			 }).then(function (response) {
        //  alert(response.data);
			   var errorcode=response.data.errorcode;
				  if(errorcode==="200"){
           _this.emp_info_list = response.data.data;
				 }else if(errorcode==="500"){
           _this.emp_info_list = [];
					 _this.errormsg(response.data.errormsg);
				 }
			   })
			 .catch(function (error) {
         _this.emp_info_list = [];
				 console.log(error);
			 });
    },

    //案件ID
    loadPjInfoList() {
          if(this.pj_nm_key ===''){return;}
          this.pj_cd_key ='';
          var _this = this;
          this.$http({
             method:'get',
             url:'/api/jobnavi/jobList',
            params:{
                  pjNm: _this.pj_nm_key
                }
           })
           .then(function (response) {
             var errorcode=response.data.errorcode;
              if(errorcode=="200"){
               _this.pj_info_list =  response.data.data;
              // console.log(_this.pj_info_list );
             }else if(errorcode=="500"){
               // console.debug(response);
               _this.errormsg(response.data.errormsg);
             }
             })
           .catch(function (error) {
             console.log(error);
           });
        },

        //要員の全て情報を取得する
        fetchEmpAllInfo(x_emp_no) {
           var _this = this;
           this.$http({
             method:'get',
             url:'/api/apprDoc/getEmpAllInfo',
             params:{empno:x_emp_no}
           })
            .then( (response) => {
              // console.log(response.data);
              var errorcode=response.data.errorcode;
              if(errorcode=="200"){
                // var retData = JSON.parse(response.data.data);
                var retData = response.data.data;
                this.apprDocInfo.emp_no = retData.emp_info.emp_no;
                // this.emp_info_list = [{'emp_nm':retData.emp_info.emp_nm,'emp_no':retData.emp_info.emp_no}];
                this.emp_no_key = retData.emp_info.emp_no;
                this.apprDocInfo.emp_nm = retData.emp_info.emp_nm;
                this.apprDocInfo.emp_birthday = retData.emp_info.emp_birthday;
                this.apprDocInfo.emp_sex = retData.emp_info.emp_sex;
                this.apprDocInfo.emp_nm_fuli = retData.emp_info.emp_nm_fuli;
                this.apprDocInfo.from_home_station_nm = retData.emp_info.from_home_station_nm;
                this.apprDocInfo.emp_age = retData.emp_info.emp_age;
                this.apprDocInfo.staff_post_no = retData.emp_info.staff_post_no;
                this.apprDocInfo.staff_addr = retData.emp_info.staff_addr;
                this.apprDocInfo.staff_mail_addr = retData.emp_info.staff_mail_addr;
                this.apprDocInfo.staff_tel_no = retData.emp_info.staff_tel_no;
                this.apprDocInfo.staff_mobile_no = retData.emp_info.staff_mobile_no;

                // //前回稟議書情報
                var p_apprDocInfo_last = retData.apprDocInfo_last;
                if(this.$_.isUndefined(p_apprDocInfo_last)){
                  this.apprDocInfo_last = {};
                }else{
                  this.apprDocInfo_last = p_apprDocInfo_last;
                }
                this.apprDocInfo.old_appr_doc_no = this.apprDocInfo_last.appr_doc_no;
                // //入社履歴情報取得
                 this.apprDocInfo.entrance_record_k =  retData.entrance_record_k;
                 this.entrance_record_list =  retData.entrance_record_list;

                }else if(errorcode=="500"){
                   this.$message.error(response.data.errormsg);
                }else{
                  // console.log(response.data);
                }
              })
            .catch(function (error) {
                console.log(error);
            });
        },

      //クライアント情報
      loadClientNoList() {
        if(this.client_nm_key ===''){return;}
        this.client_no_key ='';
        var _this = this;
        _this.$http({
          method:'get',
          url:'/api/jobnavi/clientList',
          params:{categoryId:'', clientNm : this.client_nm_key}
        }).then(function (response) {
          var errorcode=response.data.errorcode;
            if(errorcode=="200"){
            _this.client_info_list =  response.data.data;
          }else if(errorcode=="500"){
            _this.client_info_list = [];
            _this.errormsg(response.data.errormsg);
          }
          })
        .catch(function (error) {
          _this.client_info_list = [];
          console.log(error);
        });
      },

      //bp情報
      loadBpNoList() {
        if(this.bp_comp_emp_nm_key ===''){return;}
        this.bp_comp_id_key ='';
        var _this = this;
        _this.$http({
          method:'get',
          url:'/api/jobnavi/clientList',
          params:{categoryId:'4', clientNm:this.bp_comp_emp_nm_key}
        }).then(function (response) {
          var errorcode=response.data.errorcode;
            if(errorcode=="200"){
            _this.bp_comp_info_list =  response.data.data;
          }else if(errorcode=="500"){
            _this.bp_comp_info_list = [];
            _this.errormsg(response.data.errormsg);
          }
          })
        .catch(function (error) {
          _this.bp_comp_info_list = [];
          console.log(error);
        });
      },

      //営業担当
      loadBusinessUserList() {
        var _this = this;
        _this.$http({
          method:'get',
          url:'/api/user/jobUserList',
           params:{jobTitleCd:''}
        }).then(function (response) {
          var errorcode=response.data.errorcode;
            if(errorcode=="200"){
            _this.business_user_id_list =  response.data.data;
          }else if(errorcode=="500"){
            _this.business_user_id_list = [];
            _this.errormsg(response.data.errormsg);
          }
          })
        .catch(function (error) {
          _this.business_user_id_list = [];
          console.log(error);
        });

      },

      // //要員氏名
      // queryEmpInfo(queryString, cb) {
      //   var emp_info_list = this.emp_info_list;
      //   console.log(emp_info_list);
      //   var results = queryString ? emp_info_list.filter(this.createEmpNmFilter(queryString)) : emp_info_list;
      //   console.log(results);
      //   clearTimeout(this.timeout);
      //   this.timeout = setTimeout(() => {
      //     cb(results);
      //   }, 1000 * Math.random());
      // },

      // createEmpNmFilter(queryString) {
      //   return (item) => {
      //     return (item.value.indexOf(queryString.toLowerCase()) >= 0);
      //   };
      // },

      // queryEmpInfo(queryString, cb) {
      //   this.fetchEmpInfoList(queryString,'');
      //   var emp_info_list = this.emp_info_list;
      //   clearTimeout(this.timeout);
      //   this.timeout = setTimeout(() => {
      //     cb(emp_info_list);
      //   }, 100);

      // },

      // fetchEmpInfoList(empNm, empNo) {
      //   var _this = this;
      //   _this.$http({
      //     method:'get',
      //     url:'/api/jobnavi/candidateList',
      //     params:{
      //             empName:empNm,
      //             empno:empNo
      //           }
      //   }).then(function (response) {
      //     var errorcode=response.data.errorcode;
      //       if(errorcode=="200"){
      //       _this.emp_info_list =  response.data.data;
      //       console.log(_this.emp_info_list);
      //     }else if(errorcode=="500"){
      //       _this.emp_info_list = [];
      //       _this.errormsg(response.data.errormsg);
      //     }
      //     })
      //   .catch(function (error) {
      //     _this.emp_info_list = [];
      //     console.log(error);
      //   });
      // },

      handleSelectEmpNm(item) {
        this.apprDocInfo.emp_no = item.emp_no;
        this.fetchEmpAllInfo(item.emp_no);
      },

      setEmpInfo(e){
        var p_emp_no = this.$_.trim(e.target.value)
        // console.log("x_emp_no=%s",p_emp_no);
        if(p_emp_no ===''){
          this.clearEmpInfo();
          return;
        }
        // this.apprDocInfo.emp_birthday = '2017-01-02';
        // this.apprDocInfo.emp_sex = '1';
        // this.apprDocInfo.emp_nm_fuli = 'dafdaf';
        // this.apprDocInfo.from_home_station_nm = '53453453';
        // this.apprDocInfo.emp_age = '23';
         this.fetchEmpAllInfo(p_emp_no);
      },

      clearEmpInfo(){
        this.apprDocInfo.emp_no = '';
        this.apprDocInfo.emp_nm = '';
        this.emp_info_list = [];
        this.apprDocInfo.emp_birthday = '';
        this.apprDocInfo.emp_sex = '';
        this.apprDocInfo.emp_nm_fuli = '';
        this.apprDocInfo.from_home_station_nm = '';
        this.apprDocInfo.emp_age = '';
        this.apprDocInfo.staff_post_no = '';
        this.apprDocInfo.staff_addr = '';
        this.apprDocInfo.staff_mail_addr = '';
        this.apprDocInfo.staff_tel_no = '';
        this.apprDocInfo.staff_mobile_no = '';

        this.apprDocInfo.old_appr_doc_no = '';

        this.apprDocInfo_last = {};
        this.apprDocInfo.entrance_record_k = '';
        this.entrance_record_list = [];

      },

    //   //案件情報を取得
    //  queryPjCd(queryString, cb) {
    //     var pj_info_list = this.pj_info_list;
    //     var results = queryString ?  pj_info_list.filter(this.fetchPjCdFilter(queryString)) : pj_info_list;
    //     // console.log(results);
    //     clearTimeout(this.timeout);
    //     this.timeout = setTimeout(() => {
    //       cb(results);
    //     }, 100);
    //   },

    //   fetchPjCdFilter(queryString) {
    //     return (item) => {
    //       return (item.pj_cd.indexOf(queryString) === 0);
    //     };
    //   },

      // handleSelectPjCd(item) {
      //   this.apprDocInfo.pj_cd = item.pj_cd;
      //   this.apprDocInfo.pj_nm = item.pj_nm;
      //   this.apprDocInfo.work_place = item.work_place;
      //   this.apprDocInfo.pj_class_nm = item.pj_class_nm;
      // },

      setPjInfo(e){
        var p_pj_cd = this.$_.trim(e.target.value)
        // console.log("pj_no=%s",p_pj_cd);
        if(p_pj_cd ===''){
          this.clearPjInfo();
          return;
        }
         this.fetchPjAllInfo(p_pj_cd);
      },

      clearPjInfo(){
        this.apprDocInfo.pj_cd = '';
        this.apprDocInfo.pj_nm = '';
        this.apprDocInfo.work_place = '';
        this.apprDocInfo.pj_class_nm = '';
      },

      //案件の全て情報を取得する
        fetchPjAllInfo(x_pj_cd) {
           var _this = this;
           this.$http({
             method:'get',
             url:'/api/jobnavi/getPjAllInfo',
             params:{pjCd : x_pj_cd}
           })
            .then( (response) => {
              var errorcode=response.data.errorcode;
              if(errorcode=="200"){
                var retData = response.data.data;
                _this.apprDocInfo.pj_cd = retData.pj_cd;
                _this.apprDocInfo.pj_nm = retData.pj_nm;
                _this.apprDocInfo.work_place = retData.work_place;
                _this.apprDocInfo.pj_class_nm = retData.pj_class_nm;
                }else if(errorcode=="500"){
                   _this.$message.error(response.data.errormsg);
                   _this.clearPjInfo();
                }else{
                  // console.log(response.data);
                  _this.clearPjInfo();
                }
              })
            .catch(function (error) {
                console.log(error);
                _this.clearPjInfo();
            });
        },

      //クライアント情報
      setClientInfo(e){
        var p_client_no = this.$_.trim(e.target.value)
        // console.log("p_client_no=%s",p_client_no);
        if(p_client_no ===''){
          this.clearClientInfo();
          return;
        }
         this.fetchClientAllInfo(p_client_no);
      },


      clearClientInfo(item) {
        this.apprDocInfo.client_no = '';
        this.apprDocInfo.client_nm = '';
        this.apprDocInfo.client_url = '';
        this.apprDocInfo.client_post_no = '';
        this.apprDocInfo.client_addr = '';
        this.apprDocInfo.client_tel_no = '';
        this.apprDocInfo.client_fax_no = '';
        this.apprDocInfo.client_staff_nm = '';
        this.apprDocInfo.client_staff_nm_fuli = '';
        this.apprDocInfo.client_staff_dept_nm = '';
        this.apprDocInfo.client_staff_position = '';
        this.apprDocInfo.client_staff_tel_no = '';
        this.apprDocInfo.client_staff_fax_no = '';
        this.apprDocInfo.client_staff_mobile_no = '';
        this.apprDocInfo.client_mail_addr = '';
        this.apprDocInfo.client_mail_addr  = '';      //クライアントメール
        this.apprDocInfo.client_mail_addr_cc  = '';      //クライアントメールCC 追加項目
        this.apprDocInfo.client_req_staff_nm  = '';      //請求担当者名
        this.apprDocInfo.client_req_staff_nm_fuli  = '';      //請求担当者名（フリガナ）
        this.apprDocInfo.client_req_staff_dept_nm  = '';      //請求担当者所属部署
        this.apprDocInfo.client_req_staff_position  = '';      //請求担当者役職
        this.apprDocInfo.client_req_tel_no  = '';      //請求担当者電話番号
        this.apprDocInfo.client_req_fax_no  = '';      //請求担当者FAX番号
        this.apprDocInfo.client_req_staff_mobile_no  = '';      //請求担当者携帯番号
        this.apprDocInfo.client_req_mail_addr  = '';      //請求担当者メール
        this.apprDocInfo.client_req_mail_addr_cc  = '';      //請求担当者メールCC 追加項目
        this.apprDocInfo.client_req_post_no  = '';      //請求担当者郵便番号 追加項目
        this.apprDocInfo.client_req_addr = '';      //請求担当者連絡先住所 追加項目
      },

      //クライアントの全て情報を取得する
      fetchClientAllInfo(x_client_no) {
           var _this = this;
           this.$http({
             method:'get',
             url:'/api/jobnavi/getClientAllInfo',
             params:{clientNo:x_client_no}
           })
            .then( (response) => {
              var errorcode=response.data.errorcode;
              if(errorcode=="200"){
              var retData = response.data.data;
              _this.apprDocInfo.client_no = retData.client_no;
              _this.apprDocInfo.client_nm = retData.client_nm;
              _this.apprDocInfo.client_url = retData.client_url;
              _this.apprDocInfo.client_post_no = retData.client_post_no;
              _this.apprDocInfo.client_addr = retData.client_addr;
              _this.apprDocInfo.client_tel_no = retData.client_tel_no;
              _this.apprDocInfo.client_staff_nm = retData.client_staff_nm;
              _this.apprDocInfo.client_staff_nm_fuli = retData.client_staff_nm_fuli;
              _this.apprDocInfo.client_staff_dept_nm = retData.client_staff_dept_nm;
              _this.apprDocInfo.client_staff_position = retData.client_staff_position;
              _this.apprDocInfo.client_staff_fax_no = retData.client_staff_fax_no;
              _this.apprDocInfo.client_staff_tel_no = retData.client_staff_tel_no;
              _this.apprDocInfo.client_fax_no = retData.client_staff_fax_no;
              _this.apprDocInfo.client_staff_mobile_no = retData.client_staff_mobile_no;
              _this.apprDocInfo.client_mail_addr = retData.client_mail_addr;
              _this.apprDocInfo.client_req_staff_nm = retData.client_req_staff_nm;      //請求担当者名
              _this.apprDocInfo.client_req_staff_nm_fuli  = retData.client_req_staff_nm_fuli;      //請求担当者名（フリガナ）
              _this.apprDocInfo.client_req_staff_dept_nm  = retData.client_req_staff_dept_nm;      //請求担当者所属部署
              _this.apprDocInfo.client_req_staff_position  = retData.client_req_staff_position;      //請求担当者役職
              _this.apprDocInfo.client_req_tel_no  = retData.client_req_tel_no;      //請求担当者電話番号
              _this.apprDocInfo.client_req_fax_no  = retData.client_req_fax_no;      //請求担当者FAX番号
              _this.apprDocInfo.client_req_staff_mobile_no  = retData.client_req_staff_mobile_no;      //請求担当者携帯番号
              _this.apprDocInfo.client_req_mail_addr  = retData.client_req_mail_addr;      //請求担当者メール
              _this.apprDocInfo.client_req_mail_addr_cc  = retData.client_req_mail_addr_cc;      //請求担当者メールCC 追加項目
              _this.apprDocInfo.client_req_post_no  = retData.client_req_post_no;      //請求担当者郵便番号 追加項目
              _this.apprDocInfo.client_req_addr = retData.client_req_addr;      //請求担当者連絡先住所 追加項目

                }else if(errorcode=="500"){
                   _this.$message.error(response.data.errormsg);
                   _this.clearClientInfo();
                }else{
                  // console.log(response.data);
                  _this.clearClientInfo();
                }
              })
            .catch(function (error) {
                console.log(error);
                _this.clearClientInfo();
            });
        },

      //クライアント情報
      setBpInfo(e){
        var p_client_no = this.$_.trim(e.target.value)
        // console.log("p_client_no=%s",p_client_no);
        if(p_client_no ===''){
          this.clearBpInfo();
          return;
        }
         this.fetchBpAllInfo(p_client_no);
      },


      clearBpInfo(item) {
        this.apprDocInfo.bp_comp_id = '';
        this.apprDocInfo.bp_comp_emp_nm = '';
        this.apprDocInfo.bp_staff_nm_fuli = '';
        this.apprDocInfo.bp_comp_url = '';
        this.apprDocInfo.bp_post_no = '';
        this.apprDocInfo.bp_addr = '';
        this.apprDocInfo.bp_tel_no = '';
        this.apprDocInfo.bp_fax_no = '';
        this.apprDocInfo.bp_my_number = '';
        this.apprDocInfo.bp_staff_nm = '';
        this.apprDocInfo.bp_staff_dept_nm = '';
        this.apprDocInfo.bp_staff_position = '';
        this.apprDocInfo.bp_tel_no = '';
        this.apprDocInfo.bp_fax_no = '';
        this.apprDocInfo.bp_staff_mail_addr = '';
        this.apprDocInfo.bp_staff_mobile_no = '';
        this.apprDocInfo.bp_staff_tel_no = '';
        this.apprDocInfo.bp_staff_fax_no = '';
      },

      //クライアントの全て情報を取得する
      fetchBpAllInfo(x_client_no) {
           var _this = this;
           this.$http({
             method:'get',
             url:'/api/jobnavi/getBpAllInfo',
             params:{clientNo:x_client_no}
           })
            .then( (response) => {
              var errorcode=response.data.errorcode;
              if(errorcode=="200"){
              var retData = response.data.data;
              // console.log(retData);
              _this.apprDocInfo.bp_comp_id = retData.client_no;
              _this.apprDocInfo.bp_comp_emp_nm = retData.client_nm;
              _this.apprDocInfo.bp_comp_url = retData.client_url;
              _this.apprDocInfo.bp_post_no = retData.client_post_no;
              _this.apprDocInfo.bp_addr = retData.client_addr;
              _this.apprDocInfo.bp_tel_no = retData.client_tel_no;
              _this.apprDocInfo.bp_staff_nm = retData.client_staff_nm;
              _this.apprDocInfo.bp_staff_nm_fuli = retData.client_staff_nm_fuli;
              _this.apprDocInfo.bp_staff_dept_nm = retData.client_staff_dept_nm;
              _this.apprDocInfo.bp_staff_position = retData.client_staff_position;
              _this.apprDocInfo.bp_staff_mobile_no = retData.client_staff_mobile_no;
              _this.apprDocInfo.bp_staff_tel_no = retData.client_staff_tel_no;
              _this.apprDocInfo.bp_staff_fax_no = retData.client_staff_fax_no;

              _this.apprDocInfo.bp_fax_no = retData.client_fax_no;
              _this.apprDocInfo.bp_staff_mail_addr = retData.client_mail_addr;
                }else if(errorcode=="500"){
                   _this.$message.error(response.data.errormsg);
                   _this.clearBpInfo();
                }else{
                  // console.log(response.data);
                  _this.clearBpInfo();
                }
              })
            .catch(function (error) {
                console.log(error);
                _this.clearBpInfo();
            });
        },

      //社会保険計算要の全て情報を取得する
      fetchInsuranceInfo(x_base_dt) {
          // console.log('x_base_dt=%s',x_base_dt);
           var _this = this;
           this.$http({
             method:'get',
             url:'/api/common/insuranceInfo',
             params:{baseDt:x_base_dt}
           })
            .then( (response) => {
              var errorcode=response.data.errorcode;
              if(errorcode=="200"){
              var retData = response.data.data;
              // console.log(retData);
              //健康保険標準報酬リスト
              _this.health_insurance_base_price_list = retData.health_insurance_base_price_list;
              //厚生年金標準報酬リスト
              _this.employee_pension_base_price_list = retData.employee_pension_base_price_list;
              //健康保険料率リスト
              _this.health_insurance_ratio_list = retData.health_insurance_ratio_list;
              //厚生年金料率リスト
              _this.employee_pension_ratio_list = retData.employee_pension_ratio_list;
              //雇用保険
              _this.employee_insurance = retData.employee_insurance;
                }else if(errorcode=="500"){
                   _this.clearInsuranceInfo();
                }else{
                  // console.log(response.data);
                  _this.clearInsuranceInfo();
                }
              })
            .catch(function (error) {
                console.log(error);
                _this.clearInsuranceInfo();
            });
        },

       clearInsuranceInfo() {
          //健康保険標準報酬リスト
          this.health_insurance_base_price_list = [];
          //厚生年金標準報酬リスト
          this.employee_pension_base_price_list = [];
          //健康保険料率リスト
          this.health_insurance_ratio_list = [];
          //厚生年金料率リスト
          this.employee_pension_ratio_list = [];
          //雇用保険
          this.employee_insurance = 0;
        },


    // queryClientNo(queryString, cb) {
    //     var client_no_list = this.client_no_list;
    //     var results = queryString ? client_no_list.filter(this.fetchClientNoFilter(queryString)) : client_no_list;
    //     clearTimeout(this.timeout);
    //     this.timeout = setTimeout(() => {
    //       cb(results);
    //     }, 100);
    //   },

    //   fetchClientNoFilter(queryString) {
    //     return (item) => {
    //       return (item.client_no.indexOf(queryString.toLowerCase()) === 0);
    //     };
    //   },

      // handleSelectClientNo(item) {
      //   this.apprDocInfo.client_no = item.client_no;
      //   this.apprDocInfo.client_nm = item.client_nm;
      //   this.apprDocInfo.client_url = item.client_url;
      //   this.apprDocInfo.client_post_no = item.client_post_no;
      //   this.apprDocInfo.client_tel_no = item.client_tel_no;
      //   this.apprDocInfo.client_staff_nm = item.client_staff_nm;
      //   this.apprDocInfo.client_staff_nm_fuli = item.client_staff_nm_fuli;
      //   this.apprDocInfo.client_staff_dept_nm = item.client_staff_dept_nm;
      //   this.apprDocInfo.client_staff_position = item.client_staff_position;
      //   this.apprDocInfo.client_tel_no = item.client_tel_no;
      //   this.apprDocInfo.client_fax_no = item.client_fax_no;
      //   this.apprDocInfo.client_mail_addr = item.client_mail_addr;
      // },

      // //自動計算項目を登録Entに同期化する
      // setEntInfoBeforeCommit(){
      //     //超過単価（円）
      //     this.apprDocInfo.excess_price = this.excess_price;
      //     console.log('this.excess_price=%s',this.excess_price);
      //     //控除単価（円）
      //     this.apprDocInfo.deducation_price = this.deducation_price;
      //     //超過単価（円）（BP）
      //     this.apprDocInfo.bp_excess_price = this.bp_excess_price;
      //     //控除単価（円）（BP）
      //     this.apprDocInfo.bp_deducation_price = this.bp_deducation_price;

      //     // 1ヶ月の通勤代（定期）
      //     this.apprDocInfo.month_pass_travel_cost = this.travel_cost;
      // },

      listPaymentPlanD(queryString, cb) {
        var results = [{value:'60'},
        {value:'55'},
        {value:'50'},
        {value:'45'},
        {value:'40'},
        {value:'35'},
        {value:'30'},
        {value:'25'}];
        cb(results);
      },

      selectPaymentPlanD(item) {
        this.apprDocInfo.payment_plan_d = item.value;
      },

      selectBpPaymentPlanD(item) {
        this.apprDocInfo.bp_payment_plan_d = item.value;
      },

      chgWithdrawalReasonK(value){
        //⑱で”期間満了”選択の場合⇒　雇用契約書
        //・⑱で””自己都合”選択の場合⇒　退職願（届）
         this.apprDocInfo.withdrawal_req_doc = value;
      },

      formatMoney: function(number, places, symbol, thousand, decimal) {
          number = number || 0;
          places = !isNaN(places = Math.abs(places)) ? places : 2;
          symbol = symbol !== undefined ? symbol : "$";
          thousand = thousand || ",";
          decimal = decimal || ".";
          var negative = number < 0 ? "-" : "",
              i = this.$_.toInteger(number = Math.abs(+number || 0).toFixed(places), 10) + "",
              j = (j = i.length) > 3 ? j % 3 : 0;
          return symbol + negative + (j ? i.substr(0, j) + thousand : "") + i.substr(j).replace(/(\d{3})(?=\d)/g, "$1" + thousand) + (places ? decimal + Math.abs(number - i).toFixed(places).slice(2) : "");
      },

     formatRatio : function (num1,num2,pos){
        var ratio = this.calRatio(num1,num2,pos);
        if (ratio == '') return '';
        return ratio + '%';
      },

     calRatio : function (num1,num2,pos){
        var p_num1 = this.$_.toInteger(num1);
        var p_num2 = this.$_.toInteger(num2);
        if (isNaN(p_num1) || isNaN(p_num2)){
          return '';
        }

        if (p_num2 === 0){ return '';}
        return this.$_.round((p_num1/p_num2)*100,pos);
      },

     numberOnly(obj){
      //  console.log("obj.travxel_cost=%s",obj.travel_cost);
       obj.travel_cost=obj.travel_cost.replace(/[^0-9]/g,'');
     },

    loadApprInfo() {
        var p_appr_doc_no = this.$route.query.appr_doc_no;
        if(this.$_.isUndefined(p_appr_doc_no)) return;

        var _this = this;
            _this.$http.get('/api/apprDoc/detail2'
            ,{params:{apprDocNo:p_appr_doc_no}}
            )
            .then(function (response) {
              var errorcode=response.data.errorcode;
                 if(errorcode=="200"){
                    _this.apprDocInfo=response.data.data;
                    // console.log(JSON.stringify( _this.apprDocInfo));
                    _this.convertApprDocInfo(_this.apprDocInfo);
                    _this.setInitValue(_this.apprDocInfo);

                }else if(errorcode=="500"){
                   _this.$message.error(response.data.errormsg);
                }
              })
            .catch(function (error) {
                console.log(error);
            });
      },

      convertApprDocInfo(apprDocInfoEnt) {
      //作成日
        apprDocInfoEnt.appr_doc_create_dt=this.$utils.convertDate(apprDocInfoEnt.appr_doc_create_dt);
        //受注期間
        apprDocInfoEnt.appr_doc_dt_from = this.$utils.convertDate(apprDocInfoEnt.appr_doc_dt_from);
        //受注期間
        apprDocInfoEnt.appr_doc_dt_to =this.$utils.convertDate(apprDocInfoEnt.appr_doc_dt_to);
        //入場日
        apprDocInfoEnt.site_entrance_dt =this.$utils.convertDate(apprDocInfoEnt.site_entrance_dt);
        //入社日
        apprDocInfoEnt.entrance_dt =this.$utils.convertDate(apprDocInfoEnt.entrance_dt);
        //生年月日
        apprDocInfoEnt.emp_birthday =this.$utils.convertDate(apprDocInfoEnt.emp_birthday);
        //退社日
        apprDocInfoEnt.withdrawal_dt =this.$utils.convertDate(apprDocInfoEnt.withdrawal_dt);
        //社保加入予定日
        apprDocInfoEnt.insurance_join_ymd =this.$utils.convertDate(apprDocInfoEnt.insurance_join_ymd);
        //注文書受領日（見込）
        apprDocInfoEnt.order_recv_hope_dt =this.$utils.convertDate(apprDocInfoEnt.order_recv_hope_dt);
        //売上販売計上月
        apprDocInfoEnt.sales_addup_month =this.$utils.convertDate(apprDocInfoEnt.sales_addup_month);
        //請求計上月
        apprDocInfoEnt.billing_month =this.$utils.convertDate(apprDocInfoEnt.billing_month);
        //受注期間
        apprDocInfoEnt.pj_start_dt =this.$utils.convertDate(apprDocInfoEnt.pj_start_dt);
        apprDocInfoEnt.pj_end_dt =this.$utils.convertDate(apprDocInfoEnt.pj_end_dt);
        //手続予定日
        apprDocInfoEnt.procedure_dt =this.$utils.convertDate(apprDocInfoEnt.procedure_dt);
        //年齢
        apprDocInfoEnt.emp_age = this.$_.toString(apprDocInfoEnt.emp_age);
        //上位単金
        apprDocInfoEnt.sell_price = this.$_.toString(apprDocInfoEnt.sell_price);
        //要員渡し
        apprDocInfoEnt.cost_price = this.$_.toString(apprDocInfoEnt.cost_price);
        //その他
        apprDocInfoEnt.other_cost = this.$_.toString(apprDocInfoEnt.other_cost);
        //その他(初月)
        apprDocInfoEnt.other_cost_first_mon = this.$_.toString(apprDocInfoEnt.other_cost_first_mon);
        //交通費
        apprDocInfoEnt.travel_cost = this.$_.toString(apprDocInfoEnt.travel_cost);
        //交通費(初月)
        apprDocInfoEnt.travel_cost_first_mon = this.$_.toString(apprDocInfoEnt.travel_cost_first_mon);
        //休憩時間
        apprDocInfoEnt.break_time_min = this.$_.toString(apprDocInfoEnt.break_time_min);
        //休憩時間(BP)
        apprDocInfoEnt.bp_break_time_min = this.$_.toString(apprDocInfoEnt.bp_break_time_min);

      },

      loadApprInfoLast() {
        var p_appr_doc_no = this.$route.query.appr_doc_no;
        if(p_appr_doc_no === '' || p_appr_doc_no ==null){
          return;
        }

        var _this = this;
            _this.$http.get('/api/apprDoc/detailLast'
            ,{params:{apprDocNo:p_appr_doc_no}}
            )
            .then(function (response) {
              var errorcode=response.data.errorcode;
                 if(errorcode=="200"){
                     if(response.data.data == null || response.data.data =='' || response.data.data =={}){
                     return;
                   }
                   _this.apprDocInfo_last=response.data.data;

                }else if(errorcode=="500"){
                   _this.apprDocInfo_last={};
                   _this.$message.error(response.data.errormsg);
                }
              })
            .catch(function (error) {
               _this.apprDocInfo_last ={};
                console.log(error);
            });
      },
       loadEntranceRecordList() {

         var p_appr_doc_no = this.$route.query.appr_doc_no;
        if(p_appr_doc_no === '' || p_appr_doc_no ==null){
          return;
        }
        var _this = this;
            _this.$http.get('/api/apprDoc/emphis'
            ,{params:{apprDocNo:p_appr_doc_no}}
            )
            .then(function (response) {
              var errorcode=response.data.errorcode;
                 if(errorcode=="200"){
                    _this.entrance_record_list=response.data.data;
                   // console.log(JSON.stringify( _this.entrance_record_list));

                }else if(errorcode=="500"){
                   _this.$message.error(response.data.errormsg);
                }
              })
            .catch(function (error) {
                console.log(error);
            });
      },

      setInitValue(appr_doc_info){
        appr_doc_info.appr_doc_no = '';
        appr_doc_info.appr_doc_create_dt = new Date();
        appr_doc_info.pay_travel_costa_max = 20000;
      },

      //数値の差を求める
      minus(num1, num2){
        if(!this.$_.isNumber(num1) || this.$_.isNumber(num2)) return '';
        return num1 - num2;
      },

      showClientStaffDlg(x_client_no, x_callback_fun){
          if(this.$_.isEmpty(x_client_no)) return;
           var _this = this;
           this.$http({
             method:'get',
             url:'/api/jobnavi/staffList',
             params:{compayId:x_client_no}
           })
            .then( (response) => {
                  var errorcode=response.data.errorcode;
                  if(errorcode=="200"){
                  var retData = response.data.data;
                  // console.log(retData);
                   _this.client_staff_dlg.data_list = retData;
                   _this.client_staff_dlg.show_flg = true;
                   _this.client_staff_dlg.callback_fun = x_callback_fun;
                }else if(errorcode=="500"){
                   _this.$message.error(response.data.errormsg);
                   _this.client_staff_dlg.data_list=[];
                }else{
                  // console.log(response.data);
                  _this.client_staff_dlg.data_list=[];
                }
              })
            .catch(function (error) {
                console.log(error);
                _this.client_staff_dlg.data_list=[];
            });
      },

      setClientStaffInfo(x_ret_value){
            this.apprDocInfo.client_staff_nm = x_ret_value.client_staff_nm;
            this.apprDocInfo.client_staff_nm_fuli = x_ret_value.client_staff_nm_fuli;
            this.apprDocInfo.client_staff_dept_nm = x_ret_value.client_staff_dept_nm;
            this.apprDocInfo.client_staff_position = x_ret_value.client_staff_position;
            this.apprDocInfo.client_staff_fax_no = x_ret_value.client_staff_fax_no;
            this.apprDocInfo.client_staff_tel_no = x_ret_value.client_staff_tel_no;
            this.apprDocInfo.client_staff_mobile_no = x_ret_value.client_staff_mobile_no;
            this.apprDocInfo.client_mail_addr = x_ret_value.client_mail_addr;
      },

      setClientReqStaffInfo(x_ret_value){
            this.apprDocInfo.client_req_staff_nm = x_ret_value.client_staff_nm;      //請求担当者名
            this.apprDocInfo.client_req_staff_nm_fuli  = x_ret_value.client_staff_nm_fuli;      //請求担当者名（フリガナ）
            this.apprDocInfo.client_req_staff_dept_nm  = x_ret_value.client_staff_dept_nm;      //請求担当者所属部署
            this.apprDocInfo.client_req_staff_position  = x_ret_value.client_staff_position;      //請求担当者役職
            this.apprDocInfo.client_req_tel_no  = x_ret_value.client_staff_tel_no;      //請求担当者電話番号
            this.apprDocInfo.client_req_fax_no  = x_ret_value.client_staff_fax_no;      //請求担当者FAX番号
            this.apprDocInfo.client_req_staff_mobile_no  = x_ret_value.client_staff_mobile_no;      //請求担当者携帯番号
            this.apprDocInfo.client_req_mail_addr  = x_ret_value.client_mail_addr;      //請求担当者メール
            // this.apprDocInfo.client_req_mail_addr_cc  = x_ret_value.client_stall_mail_addr_cc;      //請求担当者メールCC 追加項目
            this.apprDocInfo.client_req_post_no  = x_ret_value.client_staff_post_no;      //請求担当者郵便番号 追加項目
            this.apprDocInfo.client_req_addr = x_ret_value.client_staff_addr;      //請求担当者連絡先住所 追加項目
      },

      setBpStaffInfo(x_ret_value){
            this.apprDocInfo.bp_staff_nm = x_ret_value.client_staff_nm;
            this.apprDocInfo.bp_staff_nm_fuli = x_ret_value.client_staff_nm_fuli;
            this.apprDocInfo.bp_staff_dept_nm = x_ret_value.client_staff_dept_nm;
            this.apprDocInfo.bp_staff_position = x_ret_value.client_staff_position;
            this.apprDocInfo.bp_staff_mobile_no = x_ret_value.client_staff_mobile_no;
            this.apprDocInfo.bp_staff_tel_no = x_ret_value.client_staff_tel_no;
            this.apprDocInfo.bp_staff_fax_no = x_ret_value.client_staff_fax_no;
            this.apprDocInfo.bp_staff_mail_addr = x_ret_value.client_mail_addr;
      },

      //受注期間と初月の稼働率により、契約総工数（人月）を計算する
      calcSumConstractWm(){
          //(1)初月を抜いて、人月数
          let p_end = this.apprDocInfo.appr_doc_dt_to;
          let p_start = this.apprDocInfo.appr_doc_dt_from;
          if (p_end === '' || p_start === ''){
          return 0;
          }
          let p_end_mons = this.$moment(p_end).year() * 12 + this.$moment(p_end).month();
          let p_start_mons = this.$moment(p_start).year() * 12 + this.$moment(p_start).month();
          // console.log("p_end_mons=%s",p_end_mons);
          // console.log("p_start_mons=%s",p_start_mons);
          let p_wm_without_first_mon = p_end_mons - p_start_mons;

          //(2)初月の人月数
          let p_work_days = this.$_.toInteger(this.apprDocInfo.work_days);
          let p_business_days = this.$_.toInteger(this.apprDocInfo.business_days);
          // console.log("p_work_days=%s",p_work_days);
          // console.log("p_business_days=%s",p_business_days);
         if (isNaN(p_work_days) || isNaN(p_business_days)){
          return 0;
          }
          if (p_business_days === 0){ return 0;}
          let p_wm_first_mon = this.$_.divide(p_work_days,p_business_days);
          // console.log("p_wm_first_mon=%s",p_wm_first_mon);
          //契約総工数（人月） = (1)初月を抜いて、人月数 + (2)初月の人月数
          let p_sum_contract_wm = this.$_.floor(p_wm_without_first_mon + p_wm_first_mon,2);
          // console.log("p_sum_contract_wm=%s",p_sum_contract_wm);
          this.apprDocInfo.sum_contract_wm = p_sum_contract_wm;
      },

    }, //method end

    // mounted() {
    created() {

      this.loadApprInfo();
      this.loadApprInfoLast();
      this.loadEntranceRecordList();

      this.loadBusinessUserList();

      this.fetchInsuranceInfo(this.$moment().format('YYYY-MM-DD'));
    },

    computed: {

      work_business_ratio : function (){
        var p_num1 = this.$_.toInteger(this.apprDocInfo.work_days);
        var p_num2 = this.$_.toInteger(this.apprDocInfo.business_days);
        if (isNaN(p_num1) || isNaN(p_num2)){
          return '';
        }

        if (p_num2 === 0){ return '';}
        return this.$_.floor((p_num1/p_num2)*100,0) + '%';
      },

     basic_salary : function (){
      var allow_amt_sum = this.$_.sumBy([this.apprDocInfo.appoint_allow_amt    /*役職手当*/
                  , this.apprDocInfo.house_allow_amt    /*住宅手当*/
                  , this.apprDocInfo.communication_allow_amt   /*//通信手当*/
                  , this.apprDocInfo.site_allow_amt    /*//現場手当 */
                  , this.apprDocInfo.overwork_allow_amt   /* //残業手当*/
                  , this.apprDocInfo.other_allow1_amt  /*//追加項目1*/
                  , this.apprDocInfo.other_allow2_amt], /*//追加項目2*/
                  function (o) {return parseInt(o)}
                  );
      // console.log("allow_amt_sum=%s",allow_amt_sum);
       return this.apprDocInfo.cost_price - allow_amt_sum;
      },

      //保険加入
      insurance_k_valid_f : function (){
          //1：正社員
          //2：契約社員
          //3：出向社員
          //4：派遣社員
          //5：委託契約
          //１～４社保あり、５社保なし
        if(this.$_.includes(['1', '2', '3', '4'], this.apprDocInfo.emp_pat_k)){
          return true;
        }else{
          this.apprDocInfo.insurance_k = '';
          return false;
        }
      },

      insurance_join_ymd_valid_f: function (){
        if(this.$_.includes(['1', '2', '3', '4'], this.apprDocInfo.emp_pat_k)){
          return true;
        }else{
          this.apprDocInfo.insurance_join_ymd = '';
          return false;
        }
      },

      affiliation_k_valid_f: function (){
        if(this.apprDocInfo.emp_pat_k === '5'){
          return true;
        }else{
          this.apprDocInfo.affiliation_k = '';
          return false;
        }
      },

      wh_max_valid_f : function(){
      // 1：上下割
      // 2：中割
      // 3：固定
        /*if(this.apprDocInfo.accounting_pat_k === '1'){
          return true;
        }else{
          this.apprDocInfo.wh_max = '';
          return false;
        }*/
        if(this.apprDocInfo.accounting_pat_k === '3'){
          this.apprDocInfo.wh_max = '';
          return false;
        }else{
          return true;
        }
      },

      wh_min_valid_f : function(){
      // 1：上下割
      // 2：中割
      // 3：固定
        /*if(this.apprDocInfo.accounting_pat_k === '1'){
          return true;
        }else{
          this.apprDocInfo.wh_min = '';
          return false;
        }*/
        if(this.apprDocInfo.accounting_pat_k === '3'){
          this.apprDocInfo.wh_min = '';
          return false;
        }else{
          return true;
        }
      },

     wh_mid_valid_f : function(){
      // 1：上下割
      // 2：中割
      // 3：固定
        if(this.apprDocInfo.accounting_pat_k === '2'){
          return true;
        }else{
          this.apprDocInfo.wh_mid = '';
          return false;
        }
      },


      excess_price : {
        get:function (){
        // 1：上下割
        // 2：中割
        // 3：固定
        var param = {
                      x_accounting_pat_k:this.apprDocInfo.accounting_pat_k,
                      x_sell_price:this.apprDocInfo.sell_price,
                      x_wh_max:this.apprDocInfo.wh_max,
                      x_wh_mid:this.apprDocInfo.wh_mid,
                      x_excess_price:this.apprDocInfo.excess_price
                    };
          var p_excess_price = this.$bc.calExcessPrice(param);
          this.apprDocInfo.excess_price = p_excess_price ;
          return p_excess_price;
        },
        set:function(newValue){
          this.apprDocInfo.excess_price = newValue;
        }
      },

      deducation_price :{
        get : function (){
        // 1：上下割
        // 2：中割
        // 3：固定
        var param = {
                      x_accounting_pat_k:this.apprDocInfo.accounting_pat_k,
                      x_sell_price:this.apprDocInfo.sell_price,
                      x_wh_min:this.apprDocInfo.wh_min,
                      x_wh_mid:this.apprDocInfo.wh_mid,
                      x_deducation_price:this.apprDocInfo.deducation_price
                    };
          var p_deducation_price = this.$bc.calDeducationPrice(param);
          this.apprDocInfo.deducation_price = p_deducation_price ;
          return p_deducation_price;
        },
        set:function(newValue){
          this.apprDocInfo.deducation_price = newValue;
        }
    },

    bp_wh_max_valid_f : function(){
      // 1：上下割
      // 2：中割
      // 3：固定
        if(this.apprDocInfo.bp_accounting_pat_k === '3'){
          this.apprDocInfo.bp_wh_max = '';
          return false;
        }else{
          return true;
        }
      },
      bp_wh_min_valid_f : function(){
      // 1：上下割
      // 2：中割
      // 3：固定
        if(this.apprDocInfo.bp_accounting_pat_k === '3'){
          this.apprDocInfo.bp_wh_min = '';
          return false;
        }else{
          return true;
        }
      },
     bp_wh_mid_valid_f : function(){
      // 1：上下割
      // 2：中割
      // 3：固定
        if(this.apprDocInfo.bp_accounting_pat_k === '2'){
          return true;
        }else{
          this.apprDocInfo.bp_wh_mid = '';
          return false;
        }
      },

      bp_excess_price : {
        get : function (){
        // 1：上下割
        // 2：中割
        // 3：固定
         var param = {
                      x_accounting_pat_k:this.apprDocInfo.bp_accounting_pat_k,
                      x_sell_price:this.apprDocInfo.cost_price,
                      x_wh_max:this.apprDocInfo.bp_wh_max,
                      x_wh_mid:this.apprDocInfo.bp_wh_mid,
                      x_excess_price:this.apprDocInfo.bp_excess_price
                    };
          var p_excess_price = this.$bc.calExcessPrice(param);
          this.apprDocInfo.bp_excess_price = p_excess_price ;
          return p_excess_price;
      },
      set:function(newValue){
          this.apprDocInfo.bp_excess_price = newValue;
      }
      },

      bp_deducation_price : {
        get : function (){
        // 1：上下割
        // 2：中割
        // 3：固定
        var param = {
                      x_accounting_pat_k:this.apprDocInfo.bp_accounting_pat_k,
                      x_sell_price:this.apprDocInfo.cost_price,
                      x_wh_min:this.apprDocInfo.bp_wh_min,
                      x_wh_mid:this.apprDocInfo.bp_wh_mid,
                      x_deducation_price:this.apprDocInfo.bp_deducation_price
                    };
          var p_deducation_price = this.$bc.calDeducationPrice(param);
          this.apprDocInfo.bp_deducation_price = p_deducation_price ;
          return p_deducation_price;
        },
        set : function(newValue){
            this.apprDocInfo.bp_deducation_price = newValue;
        }
      },

      travel_cost_sum : function(){
        var p_commute_route_list = this.apprDocInfo.commute_route_list;
        var travel_cost_sum_ret = 0;
        for(var i =0; i< p_commute_route_list.length; ++i){
          travel_cost_sum_ret += this.$_.toInteger(p_commute_route_list[i].travel_cost)
        }
        this.apprDocInfo.day_travel_cost = travel_cost_sum_ret * 2;
        //console.log("travel_cost_sum_ret=%s",travel_cost_sum_ret);
        return travel_cost_sum_ret;
      },

      //交通費
      travel_cost :{
          get : function() {
              this.apprDocInfo.day_travel_cost = this.travel_cost_sum * 2;
              // console.log("this.apprDocInfo.work_days=%s",this.apprDocInfo.work_days);
              // console.log("this.apprDocInfo.day_travel_cost=%s",this.apprDocInfo.day_travel_cost);
              var p_travel_cost = this.$_.toInteger(this.apprDocInfo.work_days) * this.$_.toInteger(this.apprDocInfo.day_travel_cost);
              if(p_travel_cost > this.apprDocInfo.pay_travel_costa_max){
                p_travel_cost = this.apprDocInfo.pay_travel_costa_max;
              }
              this.apprDocInfo.travel_cost = p_travel_cost;
              this.apprDocInfo.month_pass_travel_cost = p_travel_cost;
              return p_travel_cost;
            },//get end
          set : function(newValue) {
                this.apprDocInfo.travel_cost = newValue;
            } //set end
      },//travel_cost end

    },//computed end

      watch : {
          apprDocInfo :{
          deep: true,
          handler: function(newValue, oldValue) {
            // if(newValue.emp_no ===''){
            //     clearEmpInfo();
            //   }
            if(this.$_.isUndefined(newValue)){ return ;}
            this.apprDocInfo.pj_start_dt = newValue.appr_doc_dt_from
            this.apprDocInfo.pj_end_dt = newValue.appr_doc_dt_to

        //現場手当
        this.apprDocInfo.site_allow_ratio = this.$bc.calAllowRatio(newValue.cost_price,newValue.site_allow_amt);
        //残業手当
        this.apprDocInfo.overwork_allow_ratio = this.$bc.calAllowRatio(newValue.cost_price,newValue.overwork_allow_amt);
       //役職手当
        this.apprDocInfo.appoint_allow_ratio = this.$bc.calAllowRatio(newValue.cost_price,newValue.appoint_allow_amt);
       //通信手当
        this.apprDocInfo.communication_allow_ratio = this.$bc.calAllowRatio(newValue.cost_price,newValue.communication_allow_amt);
       //住宅手当
        this.apprDocInfo.house_allow_ratio = this.$bc.calAllowRatio(newValue.cost_price,newValue.house_allow_amt);
       //その他１手当
        this.apprDocInfo.other_allow1_ratio = this.$bc.calAllowRatio(newValue.cost_price,newValue.other_allow1_amt);
       //その他２手当
        this.apprDocInfo.other_allow2_ratio = this.$bc.calAllowRatio(newValue.cost_price,newValue.other_allow2_amt);

      //   //現場手当
      //   this.apprDocInfo.site_allow_amt = this.$bc.calAllowAmt(newValue.cost_price,newValue.site_allow_ratio);
      //   //残業手当
      //   this.apprDocInfo.overwork_allow_amt = this.$bc.calAllowAmt(newValue.cost_price,newValue.overwork_allow_ratio);
      //  //役職手当
      //   this.apprDocInfo.appoint_allow_amt = this.$bc.calAllowAmt(newValue.cost_price,newValue.appoint_allow_ratio);
      //  //通信手当
      //   this.apprDocInfo.communication_allow_amt = this.$bc.calAllowAmt(newValue.cost_price,newValue.communication_allow_ratio);
      //  //住宅手当
      //   this.apprDocInfo.house_allow_amt = this.$bc.calAllowAmt(newValue.cost_price,newValue.house_allow_ratio);
      //  //その他１手当
      //   this.apprDocInfo.other_allow1_amt = this.$bc.calAllowAmt(newValue.cost_price,newValue.other_allow1_ratio);
      //  //その他２手当
      //   this.apprDocInfo.other_allow2_amt = this.$bc.calAllowAmt(newValue.cost_price,newValue.other_allow2_ratio);

        var param = {
              //保険加入
              // '1':'雇用保険のみ'
              // '2':'社保険完備'
              // '3':'保険無し'
              x_insurance_k : newValue.insurance_k,
              //原価
              x_cost_price : newValue.cost_price,
              //年齢
              x_emp_age : newValue.emp_age,
              //交通費
              x_travel_cost : newValue.travel_cost,
              //その他
              x_other_cost : newValue.other_cost,

              //雇用保険
              x_employee_insurance : this.employee_insurance,
              x_health_insurance_base_price_list : this.health_insurance_base_price_list,
              x_employee_pension_base_price_list : this.employee_pension_base_price_list,

              x_health_insurance_ratio_list : this.health_insurance_ratio_list,
              x_employee_pension_ratio_list : this.employee_pension_ratio_list
        }
        //社会保険料金=健康保険+厚生年金
        var insurance_cost = this.$bc.calInsuranceCost(param);
        // console.log("insurance_cost=%s",insurance_cost);
        if(!this.$_.isNaN(insurance_cost)){
          this.apprDocInfo.insurance_cost = insurance_cost;
        }

        },
      },// apprDocInfo end

    }//watch end

  };
</script>

<style scoped>
#appr_doc_info{
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

span.item_label{
border-radius:1px;
width: 150px;
height: 50px;
font-size: 15px;
vertical-align: middle;
}
span.money{
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

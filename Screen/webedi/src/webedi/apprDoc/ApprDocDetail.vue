<template>
  <div id="appr_doc_info">
    <back-to-top text="トップへ戻る"></back-to-top>
    <el-form :inline="true" :model="apprDocInfo" ref="apprDocInfo" label-width="120px" class="apprDoc-ruleForm">

      <el-row>
        <el-col :span="4">
          <h3>【稟議書】</h3>
        </el-col>
        <el-col :span="12">
          <el-steps :space="100" :active="apprDocInfo.appr_doc_st-0" finish-status="success">
            <el-step v-for="item in appr_doc_st_list" :key="item.value">
              <div slot="title">
                {{item.label}}
                <el-tooltip placement="bottom-end" :content="item.comment" effect="light">
                  <i class="el-icon-information"></i>
                </el-tooltip>
              </div>
            </el-step>
          </el-steps>
        </el-col>
        <el-col :span="4">
          <el-popover ref="wf_record" placement="bottom" width="900" trigger="click">
            <el-table border style="width:100%" :data="wf_record_list" :row-class-name="tableRowClassName">
              <el-table-column width="80" property="display_order" label="表示順">
              </el-table-column>
              <el-table-column width="120" property="accept_user_nm" label="承認者名称">
              </el-table-column>
              <el-table-column width="120" property="job_title_nm" label="職位">
              </el-table-column>
              <el-table-column width="100" property="confirm_st_nm" label="承認状況">
              </el-table-column>
              <el-table-column width="200" property="con_rej_dt" label="承認（拒否）日時">
              </el-table-column>
              <el-table-column width="300" property="con_rej_comment" label="コメント">
              </el-table-column>
            </el-table>
          </el-popover>
          <el-button size="mini" v-popover:wf_record>
            承認詳細状況
          </el-button>
        </el-col>
        <el-col :span="4">
          <el-button size="mini" :plain="true" @click="returnToInq" type="info">
            一覧へ
          </el-button>
        </el-col>
      </el-row>
      <el-row>
        <el-col :span="8">
          <el-form-item label="稟議書NO" prop="appr_doc_no">
            {{apprDocInfo.appr_doc_no}}
          </el-form-item>
        </el-col>
        <el-col :span="8">
          <el-form-item label="稟議書作成区分" prop="appr_doc_create_k">
            {{apprDocInfo.appr_doc_create_k_nm}}
          </el-form-item>
        </el-col>
        <el-col :span="8">
          <el-form-item size="small" label="作成日">
            {{apprDocInfo.appr_doc_create_dt}}
          </el-form-item>
        </el-col>
      </el-row>

      <el-row>
        <el-col :span="8">
          <el-form-item label="受注期間">
            {{apprDocInfo.appr_doc_dt_from}} ～ {{apprDocInfo.appr_doc_dt_to}}
          </el-form-item>
        </el-col>
        <el-col :span="8">
          <el-form-item label="案件ID" prop="pj_cd">
            {{apprDocInfo.pj_cd}}
          </el-form-item>
        </el-col>
        <el-col :span="8">
          <el-form-item label="入場日">
            {{apprDocInfo.site_entrance_dt}}
          </el-form-item>
        </el-col>
      </el-row>

      <el-row>
        <el-col :span="8">
          <el-form-item label="営業主担当" prop="business_main_user_nm">
            {{apprDocInfo.business_main_user_nm}}
          </el-form-item>
        </el-col>
        <el-col :span="8">
          <el-form-item label="営業副担当" prop="business_sub_user_nm">
            {{apprDocInfo.business_sub_user_nm}}
          </el-form-item>
        </el-col>
        <el-col :span="8">
          <el-form-item
           label-width="180px"
           label="法人・個人基本情報変更"
            prop="emp_info_chg_flg_nm">
            {{apprDocInfo.emp_info_chg_flg_nm}}
          </el-form-item>
        </el-col>
      </el-row>

      <h3>【SES契約技術者登録】</h3>
      <el-row>
        <el-col :span="8">
          <el-form-item label="氏名" prop="emp_nm">
            {{apprDocInfo.emp_nm}}
          </el-form-item>
        </el-col>
        <el-col :span="8">
          <el-form-item label="社員コード" prop="emp_no">
            {{apprDocInfo.emp_no}}
          </el-form-item>
        </el-col>
        <el-col :span="8">
          <el-form-item label="生年月日">
            {{apprDocInfo.emp_birthday}}
          </el-form-item>
        </el-col>
      </el-row>
      <el-row>
        <el-col :span="8">
          <el-form-item label="フリガナ" prop="emp_nm_fuli">
            {{apprDocInfo.emp_nm_fuli}}
          </el-form-item>
        </el-col>
        <el-col :span="8">
          <el-form-item label="性別" prop="emp_sex">
            {{apprDocInfo.emp_sex_nm}}
          </el-form-item>
        </el-col>
        <el-col :span="8">
          <el-form-item label="年齢" prop="emp_age">
            {{apprDocInfo.emp_age}}
          </el-form-item>
        </el-col>
      </el-row>

      <el-row>
        <el-col :span="8">
          <el-form-item label="入社日">
            {{apprDocInfo.entrance_dt}}
          </el-form-item>
        </el-col>
        <el-col :span="8">
          <el-form-item label="退社日">
            {{apprDocInfo.withdrawal_dt}}
          </el-form-item>
        </el-col>
        <el-col :span="8">
          <el-form-item label="最寄駅（自宅）" prop="from_home_station_nm">
            {{apprDocInfo.from_home_station_nm}}
          </el-form-item>
        </el-col>
      </el-row>

      <el-row>
        <el-col :span="8">
          <el-form-item label="入社経歴" prop="entrance_record_k">
            <el-popover :disabled="this.apprDocInfo.entrance_record_k === '1'?false:true" ref="entrance_record" placement="right" width="800" trigger="click">
              <el-table stripe :data="entrance_record_list">
                <el-table-column width="120px" property="entrance_dt_dlt" label="入場日">
                </el-table-column>
                <el-table-column width="150" property="appr_doc_dt_from" label="注文期間(From)">
                </el-table-column>
                <el-table-column width="150" property="appr_doc_dt_to" label="注文期間(To)">
                </el-table-column>
                <el-table-column width="300" property="pj_nm" label="案件名">
                </el-table-column>
                <el-table-column width="300" property="work_content" label="作業内容">
                </el-table-column>
              </el-table>
            </el-popover>
            <el-button size="mini" v-popover:entrance_record>
              {{apprDocInfo.entrance_record_k === '1'?'有':'無'}}
            </el-button>
          </el-form-item>
        </el-col>
        <el-col :span="8">
          <el-form-item label="マイナンバー" prop="emp_my_number">
            {{apprDocInfo.emp_my_number}}
          </el-form-item>
        </el-col>
      </el-row>

      <h4>技術者契約</h4>
      <el-row>
        <el-col :span="8">
          <el-form-item label="社員区分（雇用形態）" label-width="170px" prop="emp_pat_k">
            {{apprDocInfo.emp_pat_k_nm}}
          </el-form-item>
        </el-col>
        <el-col :span="8">
          <el-form-item label="保険加入" prop="insurance_k">
            {{apprDocInfo.insurance_k_nm}}
          </el-form-item>
        </el-col>
        <el-col :span="8">
          <el-form-item label="社保加入予定日" prod="insurance_join_ymd">
            {{apprDocInfo.insurance_join_ymd}}
            <span><b>から</b></span>
          </el-form-item>
        </el-col>
      </el-row>
      <el-form-item label="所属区分（再委託先）" label-width="170px" prop="affiliation_k">
        {{apprDocInfo.affiliation_k_nm}}
      </el-form-item>


      <h4>クライアント契約</h4>
      <el-row>
        <el-col :span="8">
          <el-form-item label="クライアントID" label-width="130px" prop="client_no">
            {{apprDocInfo.client_no}}
          </el-form-item>
        </el-col>
        <el-col :span="8">
          <el-form-item label-width="160px" label="契約（上位）" prop="client_contract_k_nm">
            {{apprDocInfo.client_contract_nm}}
          </el-form-item>
        </el-col>
        <el-col :span="8">
          <el-form-item label="受注（注文）書No." label-width="160px" prop="client_order_no">
            {{apprDocInfo.client_order_no}}
          </el-form-item>
        </el-col>
      </el-row>

      <el-row>
        <el-col :span="10">
          <el-form-item label="クライアント名称" label-width="130px" prop="client_nm">
            {{apprDocInfo.client_nm}}
          </el-form-item>
        </el-col>
        <el-col :span="8">
          <el-form-item label="注文書受領日（見込）" label-width="160px" prop="client_nm">
            {{apprDocInfo.order_recv_hope_dt}}
          </el-form-item>
        </el-col>
        <el-col :span="6">
          <el-form-item label="取引区分" label-width="80px" prop="take_over_k_nm">
            {{apprDocInfo.take_over_k_nm}}
          </el-form-item>
        </el-col>
      </el-row>
      <h3>【売上／粗利／明細】</h3>
      <el-row :gutter="10">
        <el-col :span="10">
          <table border="0" cellspacing="0">
            <tr>
              <td>
                <el-form-item label="営業日数（入場月）" label-width="150px" prop="business_days">
                  {{apprDocInfo.business_days}}
                </el-form-item>
              </td>
            </tr>
            <tr>
              <td>
                <el-form-item label="稼働日数（入場月）" label-width="150px" prop="work_days">
                  {{apprDocInfo.work_days}}
                </el-form-item>
              </td>
              <tr>
                <tr>
                  <td>
                    <el-form-item label="工数人月（日割）" label-width="150px" prop="work_business_ratio">
                      {{work_business_ratio}}
                    </el-form-item>
                  </td>
                </tr>
                <tr>
                  <td>
                    <el-form-item label-width="150px" label="契約工数(人月)" prop="sum_contract_wm">
                       {{apprDocInfo.sum_contract_wm}}
                    </el-form-item>
                  </td>
                </tr>
                <tr>
                  <td>
                    <el-form-item label="売上販売計上月" label-width="150px" prod="sales_addup_month">
                      {{apprDocInfo.sales_addup_month}}
                    </el-form-item>
                  </td>
                </tr>
                <tr>
                  <td>
                    <el-form-item label-width="150px" label="請求計上月" prod="billing_month">
                      {{apprDocInfo.billing_month}}
                    </el-form-item>
                  </td>
                </tr>
                <tr>
                  <td>
                    <el-form-item label-width="100px" label="上位単金" prop="sell_price">
                      {{formatMoney(apprDocInfo.sell_price,0,"￥")}}
                      <span class="money">
                (税込: {{formatMoney(apprDocInfo.sell_price*(1+tax_rate/100),0,"￥")}})
                </span>
                    </el-form-item>
                  </td>
                </tr>
                <tr>
                  <td>
                    <el-form-item label="要員渡し" label-width="100px" prop="cost_price">
                      {{formatMoney(apprDocInfo.cost_price,0,"￥")}}
                      <span class="money">
                (税込: {{formatMoney(apprDocInfo.cost_price*(1+tax_rate/100),0,"￥")}})
                </span>
                    </el-form-item>
                  </td>
                </tr>
                <tr>
                  <td>
                    <el-form-item label="その他" label-width="120px" prop="other_cost">
                      {{formatMoney(apprDocInfo.other_cost,0,"￥")}}
                      <span class="money">
                (税込: {{formatMoney(apprDocInfo.other_cost*(1+tax_rate/100),0,"￥")}})
                </span>
                    </el-form-item>
                  </td>
                </tr>
                <tr>
                  <td>
                    <el-form-item label="その他(初月)" label-width="120px" prop="other_cost_first_mon">
                      {{formatMoney(apprDocInfo.other_cost_first_mon,0,"￥")}}
                      <span class="money">
                (税込: {{formatMoney(apprDocInfo.other_cost_first_mon*(1+tax_rate/100),0,"￥")}})
                </span>
                    </el-form-item>
                  </td>
                </tr>
          </table>
        </el-col>
        <el-col :span="13">
         <apprdoc-diff-table
          :apprDocInfo="apprDocInfo"
          :apprDocInfoLast="apprDocInfo_last"
         >
        </apprdoc-diff-table>
        </el-col>
      </el-row>

      <h4>売上</h4>
      <el-row :gutter="0">
        <el-col :span="24">
         <apprdoc-sales-table
          :apprDocInfo="apprDocInfo"
        >
        </apprdoc-sales-table>
        </el-col>
      </el-row>

      <h4>技術者月額手当（内訳）</h4>
      <el-row :gutter="0">
        <el-col :span="8">
          <el-row>
            <el-form-item label="基本給" prop="basic_salary">
              <span class="money">
            {{formatMoney(basic_salary,0,"￥")}}
          </span>
            </el-form-item>
          </el-row>
          <el-row>
            <el-form-item label="現場手当" prop="site_allow_amt">
              {{formatMoney(apprDocInfo.site_allow_amt,0,"￥")}}
              (<span class="ratio">{{apprDocInfo.site_allow_ratio}}%</span>)

            </el-form-item>
          </el-row>
          <el-row>
            <el-form-item label="残業手当" prop="overwork_allow_ratio">
              {{formatMoney(apprDocInfo.overwork_allow_amt,0,"￥")}}
              (<span class="ratio">{{apprDocInfo.overwork_allow_ratio}}%</span>)
            </el-form-item>
          </el-row>
        </el-col>
        <el-col :span="8">
          <el-row>
            <el-form-item label="役職手当" prop="appoint_allow_ratio">
              {{formatMoney(apprDocInfo.appoint_allow_amt,0,"￥")}}
              (<span class="ratio">{{apprDocInfo.appoint_allow_ratio}}%</span>)
            </el-form-item>
          </el-row>
          <el-row>
            <el-form-item label="通信手当" prop="communication_allow_ratio">
              {{formatMoney(apprDocInfo.communication_allow_amt,0,"￥")}}
              (<span class="ratio">{{apprDocInfo.communication_allow_ratio}}%</span>)
            </el-form-item>
          </el-row>
          <el-row>
            <el-form-item label="住宅手当" prop="house_allow_ratio">
              {{formatMoney(apprDocInfo.house_allow_amt,0,"￥")}}
              (<span class="ratio">{{apprDocInfo.house_allow_ratio}}%</span>)
            </el-form-item>
          </el-row>
        </el-col>

        <el-col :span="8">
          <el-row>
            <el-form-item :label="apprDocInfo.other_allow1_nm+'手当'" prop="other_allow1_nm">
              {{formatMoney(apprDocInfo.other_allow1_amt,0,"￥")}}
              (<span class="ratio">{{apprDocInfo.other_allow1_ratio}}%</span>)
            </el-form-item>
          </el-row>
          <el-row>
            <el-form-item :label="apprDocInfo.other_allow2_nm+'手当'" prop="other_allow2_nm">
              {{formatMoney(apprDocInfo.other_allow2_amt,0,"￥")}}
              (<span class="ratio">{{apprDocInfo.other_allow2_ratio}}%</span>)
            </el-form-item>
          </el-row>
        </el-col>
      </el-row>
      <h3>【クライアント連絡先】</h3>
      <el-row :gutter="0">
        <el-col :span="8">
          <el-form-item label-width="100px" label="クライアント" prop="client_nm">
            {{apprDocInfo.client_nm}}
          </el-form-item>
        </el-col>
        <el-col :span="8">
          <el-form-item label="クライアントID" label-width="140px" prop="client_no">
            {{apprDocInfo.client_no}}
          </el-form-item>
        </el-col>
        <el-col :span="8">
        </el-col>
        <el-form-item label="URL" label-width="60px" prop="client_url">
          {{apprDocInfo.client_url}}
        </el-form-item>
      </el-row>
      <h4>基本情報</h4>
      <el-row :gutter="0">
        <el-col :span="8">
          <el-form-item label="郵便番号〒" prop="client_url">
            {{apprDocInfo.client_post_no}}
          </el-form-item>
        </el-col>
        <el-col :span="16">
          <el-form-item label-width="80px" label="住所" prop="client_addr">
            {{apprDocInfo.client_addr}}
          </el-form-item>
        </el-col>
      </el-row>
      <el-row :gutter="0">
        <el-col :span="8">
          <el-form-item label="電話番号" prop="client_tel_no">
            {{apprDocInfo.client_tel_no}}
          </el-form-item>
        </el-col>
        <el-col :span="8">
          <el-form-item label="FAX番号" label-width="80px" prop="client_fax_no">
            {{apprDocInfo.client_fax_no}}
          </el-form-item>
        </el-col>
        <el-col :span="8">
          <el-form-item label="法人マイナンバー" label-width="140px" prop="client_my_number">
            {{apprDocInfo.client_my_number}}
          </el-form-item>
        </el-col>
      </el-row>

      <el-collapse>
        <el-collapse-item title="クライアント担当者" name="1">
          <el-row :gutter="0">
            <el-col :span="7">
              <el-form-item label-width="140px" label="氏名" prop="client_staff_nm">
                {{apprDocInfo.client_staff_nm}}
              </el-form-item>
            </el-col>
            <el-col :span="7">
              <el-form-item label="所属部署" prop="client_staff_dept_nm">
                {{apprDocInfo.client_staff_dept_nm}}
              </el-form-item>
            </el-col>
            <el-col :span="10">
              <el-form-item label="メールアドレス" prop="client_mail_addr">
                {{apprDocInfo.client_mail_addr}}
              </el-form-item>
            </el-col>
          </el-row>

          <el-row :gutter="0">
            <el-col :span="7">
              <el-form-item label-width="140px" label="フリガナ" prop="client_staff_nm_fuli">
                {{apprDocInfo.client_staff_nm_fuli}}
              </el-form-item>
            </el-col>
            <el-col :span="7">
              <el-form-item label="役職" prop="client_staff_position">
                {{apprDocInfo.client_staff_position}}
              </el-form-item>
            </el-col>
            <el-col :span="10">
              <el-form-item label="メールアドレス（CC）" label-width="170px" prop="client_mail_addr_cc">
                {{apprDocInfo.client_mail_addr_cc}}
              </el-form-item>
            </el-col>
          </el-row>

          <el-row :gutter="0">
            <el-col :span="7">
              <el-form-item label="電話番号（直通）" label-width="140px" prop="client_tel_no">
                {{apprDocInfo.client_staff_tel_no}}
              </el-form-item>
            </el-col>
            <el-col :span="7">
              <el-form-item label-width="80px" label="FAX番号" prop="client_fax_no">
                {{apprDocInfo.client_staff_fax_no}}
              </el-form-item>
            </el-col>
            <el-col :span="10">
              <el-form-item label-width="100px" label="携帯番号" prop="client_staff_mobile_no">
                {{apprDocInfo.client_staff_mobile_no}}
              </el-form-item>
            </el-col>
          </el-row>

        </el-collapse-item>
        <el-collapse-item title="請求担当者" name="2">
          <el-row :gutter="0">
            <el-col :span="7">
              <el-form-item label-width="140px" label="氏名" prop="client_req_staff_nm">
                {{apprDocInfo.client_req_staff_nm}}
              </el-form-item>
            </el-col>
            <el-col :span="7">
              <el-form-item label="所属部署" prop="client_req_staff_dept_nm">
                {{apprDocInfo.client_req_staff_dept_nm}}
              </el-form-item>
            </el-col>
            <el-col :span="10">
              <el-form-item label="メールアドレス" prop="client_req_mail_addr">
                {{apprDocInfo.client_req_mail_addr}}
              </el-form-item>
            </el-col>
          </el-row>

          <el-row :gutter="0">
            <el-col :span="7">
              <el-form-item label-width="140px" label="フリガナ" prop="client_req_staff_nm_fuli">
                {{apprDocInfo.client_req_staff_nm_fuli}}
              </el-form-item>
            </el-col>
            <el-col :span="7">
              <el-form-item label-width="70px" label="役職" prop="client_req_staff_position">
                {{apprDocInfo.client_req_staff_position}}
              </el-form-item>
            </el-col>
            <el-col :span="10">
              <el-form-item label="メールアドレス（CC）" label-width="170px" prop="client_req_mail_addr_cc">
                {{apprDocInfo.client_req_mail_addr_cc}}
              </el-form-item>
            </el-col>
          </el-row>

          <el-row :gutter="0">
            <el-col :span="7">
              <el-form-item label-width="140px" label="電話番号（直通）" prop="client_req_tel_no">
                {{apprDocInfo.client_req_tel_no}}
              </el-form-item>
            </el-col>
            <el-col :span="7">
              <el-form-item label-width="80px" label="FAX番号" prop="client_req_fax_no">
                {{apprDocInfo.client_req_fax_no}}
              </el-form-item>
            </el-col>
            <el-col :span="10">
              <el-form-item label-width="100px" label="携帯番号" prop="client_req_staff_mobile_no">
                {{apprDocInfo.client_req_staff_mobile_no}}
              </el-form-item>
            </el-col>
          </el-row>

          <el-row :gutter="0">
            <el-col :span="8">
              <el-form-item label-width="140px" label="郵便番号〒" prop="client_req_url">
                {{apprDocInfo.client_req_post_no}}
              </el-form-item>
            </el-col>
            <el-col :span="16">
              <el-form-item label-width="60px" label="住所" prop="client_req_addr">
                {{apprDocInfo.client_req_addr}}
              </el-form-item>
            </el-col>
          </el-row>

        </el-collapse-item>
      </el-collapse>
      <h4>月次説明</h4>
      <el-row :gutter="0">
        <el-col :span="10">
          <el-form-item label="区分" prop="mon_pro_k_nm">
            {{apprDocInfo.mon_pro_k_nm}}
          </el-form-item>
        </el-col>
      </el-row>

      <el-row :gutter="0">
        <el-col :span="8">
          <el-form-item label="作業報告書F" prop="work_rep_f">
            {{apprDocInfo.work_rep_f_nm}}
          </el-form-item>
        </el-col>
        <el-col :span="8">
          <el-form-item label="作業報告書〆" prop="work_rep_dl_k">
            {{apprDocInfo.work_rep_dl_k_nm}}
          </el-form-item>
        </el-col>
        <el-col :span="8">
          <el-form-item label="添付資料" prop="mon_attach_file_k">
            {{apprDocInfo.mon_attach_file_k_nm}}
          </el-form-item>
        </el-col>
      </el-row>

      <el-row :gutter="0">
        <el-col :span="8">
          <el-form-item label="請求書F" prop="bill_f">
            {{apprDocInfo.bill_f_nm}}
          </el-form-item>
        </el-col>
        <el-col :span="8">
          <el-form-item label="請求書〆" prop="bill_dl_k">
            {{apprDocInfo.bill_dl_k_nm}}
          </el-form-item>
        </el-col>
        <el-col :span="8">
          <el-form-item label="添付資料詳細" prop="mon_attach_file_id">
            <el-upload action="" :disabled="true" :file-list="apprDocInfo.fileList">
            </el-upload>
          </el-form-item>
        </el-col>
      </el-row>

      <h3>【クライアント　契約条件】</h3>
      <el-row :gutter="0">
        <el-col :span="8">
          <el-form-item 　label="案件名" label-width="80px" prop="pj_nm">
            {{apprDocInfo.pj_nm}}
          </el-form-item>
        </el-col>
        <el-col :span="8">
          <el-form-item label="案件ID" label-width="80px" prop="pj_cd">
            {{apprDocInfo.pj_cd}}
          </el-form-item>
        </el-col>
        <el-col :span="8">
          <el-form-item label="最寄駅" label-width="80px" prop="work_place">
            {{apprDocInfo.work_place}}
          </el-form-item>
        </el-col>
      </el-row>
      <el-row :gutter="0">
        <el-col :span="8">
          <el-form-item label="作業内容/範囲" prop="work_content">
            {{apprDocInfo.work_content}}
          </el-form-item>
        </el-col>
        <el-col :span="8">
          <el-form-item label="区分" label-width="80px" prop="salary_pay_k_nm">
            {{apprDocInfo.salary_pay_k_nm}}
          </el-form-item>
        </el-col>
        <el-col :span="8">

          <el-form-item label="単金" label-width="80px" prop="cost_price">
            {{formatMoney(apprDocInfo.sell_price,0,'￥')}}
          </el-form-item>
        </el-col>
      </el-row>
      <el-row :gutter="0">
        <el-col :span="6">
          <el-form-item label="精算条件" prop="accounting_pat_k_nm">
            {{apprDocInfo.accounting_pat_k_nm}}
          </el-form-item>
        </el-col>
        <el-col :span="6">
          <el-form-item label="上限時間" prop="wh_max">
            {{apprDocInfo.wh_max}}
          </el-form-item>
        </el-col>
        <el-col :span="6">
          <el-form-item label="下限時間" prop="wh_min">
            {{apprDocInfo.wh_min}}
          </el-form-item>
        </el-col>
        <el-col :span="6">
          <el-form-item label="中割時間" prop="wh_mid">
            {{apprDocInfo.wh_mid}}
          </el-form-item>
        </el-col>
      </el-row>
      <el-row :gutter="0">
        <el-col :span="8">
          <el-form-item label="超過単価" prop="excess_price">
            {{formatMoney(apprDocInfo.excess_price,0,'￥')}}
          </el-form-item>
        </el-col>
        <el-col :span="8">
          <el-form-item label="控除単価" prop="deducation_price">
            {{formatMoney(apprDocInfo.deducation_price,0,'￥')}}
          </el-form-item>
        </el-col>
        <el-col :span="8">
          <el-form-item label="時間単位" prop="work_time_unit_k_nm">
            {{apprDocInfo.work_time_unit_k_nm}}
          </el-form-item>
        </el-col>
      </el-row>
      <el-row :gutter="0">
        <el-col :span="12">
          <el-form-item label="受注期間">
            <el-form-item prop="pj_start_dt">
              <span>{{apprDocInfo.pj_start_dt}}</span> ～
              <span>{{apprDocInfo.pj_end_dt}}</span>
            </el-form-item>
            <span class="item_label">(書面上での契約時間)</span>
          </el-form-item>
        </el-col>
        <el-col :span="12">
          <el-form-item label="案件分類" prop="pj_class_nm">
            {{apprDocInfo.pj_class_nm}}
          </el-form-item>
        </el-col>
      </el-row>
      <el-row :gutter="0">
        <el-col :span="6">
          <el-form-item label="就業時間">
            <span>{{apprDocInfo.work_start_time}}</span> -
            <span>{{apprDocInfo.work_end_time}}</span>
          </el-form-item>
        </el-col>
        <el-col :span="6">
          <el-form-item label="休憩時間" prop="break_time_min">
            {{apprDocInfo.break_time_min}}分
          </el-form-item>
        </el-col>
        <el-col :span="12">
          <el-form-item label="支払サイト" prop="payment">
            <span style="color: orange;">{{apprDocInfo.payment_plan_d}}</span> 日（
            <span style="color: orange;">{{apprDocInfo.payment_due_k_nm}}</span> 締め
            <span style="color: orange;">{{apprDocInfo.pay_month_k_nm}}</span>
            <span style="color: orange;">{{apprDocInfo.pay_plan_d_nm}}</span> 支払い）
          </el-form-item>
        </el-col>
      </el-row>

      <el-form-item
               v-for="(item, index) in apprDocInfo.work_evidence_list"
              :label="'エビデンス'+(index+1)"
              :key="'work_evidence'+index"
              :prop="'work_evidence_list.'+index+'.work_evidence'">
        <el-input
              type="textarea"
              style="width:800px"
              :rows="5"
              resize="none"
              :autosize="false"
              :disabled="true"
              placeholder=""
              v-model="item.work_evidence">
        </el-input>
      </el-form-item >

      <h3>【SES契約技術者情報】</h3>
      <el-row :gutter="0">
        <el-col :span="6">
          <el-form-item label="所属会社ID" label-width="100px" prop="bp_comp_id">
            {{apprDocInfo.bp_comp_id}}
          </el-form-item>
        </el-col>
        <el-col :span="10">
          <el-form-item label-width="150px" label="所属会社／社員氏名" prop="bp_comp_emp_nm">
            {{apprDocInfo.bp_comp_emp_nm}}
          </el-form-item>
        </el-col>
        <el-col :span="8">
          <el-form-item label="URL" label-width="40px" prop="client_url">
            {{apprDocInfo.bp_comp_url}}
          </el-form-item>
        </el-col>
      </el-row>

      <h4>基本情報</h4>
      <el-row :gutter="0">
        <el-col :span="6">
          <el-form-item label-width="100px" label="郵便番号〒" prop="bp_post_no">
            {{apprDocInfo.bp_post_no}}
          </el-form-item>
        </el-col>
        <el-col :span="18">
          <el-form-item label-width="60px" label="住所" prop="bp_addr">
            {{apprDocInfo.bp_addr}}
          </el-form-item>
        </el-col>
      </el-row>
      <el-row :gutter="0">
        <el-col :span="6">
          <el-form-item label-width="100px" label="電話番号" prop="bp_tel_no">
            {{apprDocInfo.bp_tel_no}}
          </el-form-item>
        </el-col>
        <el-col :span="8">
          <el-form-item label-width="80px" label="FAX番号" prop="bp_fax_no">
            {{apprDocInfo.bp_fax_no}}
          </el-form-item>
        </el-col>
        <el-col :span="10">
          <el-form-item label="法人マイナンバー" label-width="140px" prop="bp_my_number">
            {{apprDocInfo.bp_my_number}}
          </el-form-item>
        </el-col>
      </el-row>
      <el-row :gutter="0">
        <el-col :span="6">
          <el-form-item label-width="100px" label="氏名" prop="bp_staff_nm">
            {{apprDocInfo.bp_staff_nm}}
          </el-form-item>
        </el-col>
        <el-col :span="8">
          <el-form-item label-width="80px" label="所属部署" prop="bp_staff_dept_nm">
            {{apprDocInfo.bp_staff_dept_nm}}
          </el-form-item>
        </el-col>
        <el-col :span="10">
          <el-form-item label="メールアドレス" prop="bp_mail_addr">
            {{apprDocInfo.bp_staff_mail_addr}}
          </el-form-item>
        </el-col>
      </el-row>
      <el-row :gutter="0">
        <el-col :span="6">
          <el-form-item label-width="100px" label="フリガナ" prop="bp_staff_nm_fuli">
            {{apprDocInfo.bp_staff_nm_fuli}}
          </el-form-item>
        </el-col>
        <el-col :span="8">
          <el-form-item label-width="80px" label="役職" prop="bp_staff_position">
            {{apprDocInfo.bp_staff_position}}
          </el-form-item>
        </el-col>
        <el-col :span="10">
          <el-form-item label="メールアドレス（CC）" label-width="170px" prop="bp_mail_addr_cc">
            {{apprDocInfo.bp_staff_mail_addr_cc}}
          </el-form-item>
        </el-col>
      </el-row>
      <el-row :gutter="0">
        <el-col :span="6">
          <el-form-item label="電話番号（直通）" label-width="130px" prop="bp_staff_tel_no">
            {{apprDocInfo.bp_staff_tel_no}}
          </el-form-item>
        </el-col>
        <el-col :span="8">
          <el-form-item label-width="80px" label="FAX番号" prop="bp_staff_fax_no">
            {{apprDocInfo.bp_staff_fax_no}}
          </el-form-item>
        </el-col>
        <el-col :span="10">
          <el-form-item label-width="80px" label="携帯番号" prop="bp_staff_mobile_no">
            {{apprDocInfo.bp_staff_mobile_no}}
          </el-form-item>
        </el-col>
      </el-row>

      <h4>要員情報</h4>
      <el-row :gutter="0">
        <el-col :span="24">
          <el-form-item label-width="100px" label="氏名" prop="emp_nm">
            {{apprDocInfo.emp_nm}}
          </el-form-item>
        </el-col>
      </el-row>
      <el-row :gutter="0">
        <el-col :span="8">
          <el-form-item label-width="100px" label="郵便番号〒" prop="client_req_url">
            {{apprDocInfo.staff_post_no}}
          </el-form-item>
        </el-col>
        <el-col :span="16">
          <el-form-item label="住所" label-width="80px" prop="staff_post_no">
            {{apprDocInfo.staff_addr}}
          </el-form-item>
        </el-col>
      </el-row>

      <el-row :gutter="0">
        <el-col :span="8">
          <el-form-item label-width="100px" label="電話番号" prop="staff_tel_no">
            {{apprDocInfo.staff_tel_no}}
          </el-form-item>
        </el-col>
        <el-col :span="8">
          <el-form-item label-width="80px" label="携帯番号" prop="staff_mobile_no">
            {{apprDocInfo.staff_mobile_no}}
          </el-form-item>
        </el-col>
        <el-col :span="8">
          <el-form-item label-width="120px" label="E-mail（自宅）" prop="staff_mail_addr">
            {{apprDocInfo.staff_mail_addr}}
          </el-form-item>
        </el-col>
      </el-row>

      <h3>【要員　契約条件】</h3>
      <el-row :gutter="0">
        <el-col :span="12">
          <el-form-item 　label="案件名" label-width="100px" prop="pj_nm">
            {{apprDocInfo.pj_nm}}
          </el-form-item>
        </el-col>
        <el-col :span="6">
          <el-form-item label="案件ID" label-width="80px" prop="pj_cd">
            {{apprDocInfo.pj_cd}}
          </el-form-item>
        </el-col>
        <el-col :span="6">
          <el-form-item label="最寄駅" label-width="80px" prop="work_place">
            {{apprDocInfo.work_place}}
          </el-form-item>
        </el-col>
      </el-row>
      <el-row :gutter="0">
        <el-col :span="12">
          <el-form-item label="作業内容/範囲" prop="work_content">
            {{apprDocInfo.work_content}}
          </el-form-item>
        </el-col>
        <el-col :span="6">
          <el-form-item label="区分" label-width="80px" prop="bp_salary_pay_k">
            {{apprDocInfo.bp_salary_pay_k_nm}}
          </el-form-item>
        </el-col>
        <el-col :span="6">
          <el-form-item label="単金" label-width="80px" prop="cost_price">
            {{formatMoney(apprDocInfo.cost_price,0,'￥')}}
          </el-form-item>
        </el-col>
      </el-row>
      <el-row :gutter="0">
        <el-col :span="6">
          <el-form-item label-width="100px" label="精算条件" prop="bp_accounting_pat_k_nm">
            {{apprDocInfo.bp_accounting_pat_k_nm}}
          </el-form-item>
        </el-col>
        <el-col :span="6">
          <el-form-item label-width="100px" label="上限時間" prop="wh_max">
            {{apprDocInfo.bp_wh_max}}
          </el-form-item>
        </el-col>
        <el-col :span="6">
          <el-form-item label-width="100px" label="下限時間" prop="wh_min">
            {{apprDocInfo.bp_wh_min}}
          </el-form-item>
        </el-col>
        <el-col :span="6">
          <el-form-item label-width="100px" label="中割時間" prop="wh_mid">
            {{apprDocInfo.bp_wh_mid}}
          </el-form-item>
        </el-col>
      </el-row>
      <el-row :gutter="0">
        <el-col :span="8">
          <el-form-item label-width="100px" label="超過単価" prop="bp_excess_price">
            {{formatMoney(apprDocInfo.bp_excess_price,0,'￥')}}
          </el-form-item>
        </el-col>
        <el-col :span="8">
          <el-form-item label-width="100px" label="控除単価" prop="bp_deducation_price">
            {{formatMoney(apprDocInfo.bp_deducation_price,0,'￥')}}
          </el-form-item>
        </el-col>
        <el-col :span="8">
          <el-form-item label-width="80px" label="時間単位" prop="bp_work_unit_k_nm">
            {{apprDocInfo.bp_work_unit_k_nm}}
          </el-form-item>
        </el-col>
      </el-row>
      <el-row :gutter="0">
        <el-col :span="12">
          <el-form-item label="受注期間">
            <span>{{apprDocInfo.pj_start_dt}}</span> ～
            <span>{{apprDocInfo.pj_end_dt}}</span> (書面上での契約時間)
          </el-form-item>
        </el-col>
        <el-col :span="12">
          <el-form-item label="案件分類" prop="pj_class_nm">
            {{apprDocInfo.pj_class_nm}}
          </el-form-item>
        </el-col>
      </el-row>
      <el-row :gutter="0">
        <el-col :span="6">
          <el-form-item label="就業時間">
            <span>{{apprDocInfo.bp_work_start_time}}</span> -
            <span>{{apprDocInfo.bp_work_end_time}}</span>
          </el-form-item>
        </el-col>
        <el-col :span="6">
          <el-form-item label="休憩時間" prop="break_time_min">
            {{apprDocInfo.bp_break_time_min}}
          </el-form-item>
        </el-col>
        <el-col :span="12">
          <el-form-item label="支払サイト" prop="payment">
            <span style="color: orange;">{{apprDocInfo.bp_payment_plan_d}}</span> 日（
            <span style="color: orange;">{{apprDocInfo.bp_payment_due_k_nm}}</span> 締め
            <span style="color: orange;">{{apprDocInfo.bp_pay_month_k_nm}}</span>
            <span style="color: orange;">{{apprDocInfo.bp_pay_plan_d_nm}}</span> 支払い）
          </el-form-item>
        </el-col>
      </el-row>

      <h4>通勤経路</h4>
      <table cellspacing="0" class="table table-bordered" id="salesTable">
        <thead>
          <tr>
            <th align=center colspan="8">通勤経路 &nbsp;
              <a href="https://transit.yahoo.co.jp" target="_blank">
                        https://transit.yahoo.co.jp(GO)
                      </a>
            </th>
            <th>片道交通費</th>
          </tr>
        </thead>

        <tbody>
          <tr v-for="(item, index) in apprDocInfo.commute_route_list" :key="'item'+index">
            <td>
              {{apprDocInfo.commute_route_list[index].from_home_line_nm}}
            </td>
            <td>線</td>
            <td>
              {{apprDocInfo.commute_route_list[index].from_home_station_nm}}
            </td>
            <td>駅～</td>
            <td>
              {{apprDocInfo.commute_route_list[index].to_comp_line_nm}}
            </td>
            <td>線</td>
            <td>
              {{apprDocInfo.commute_route_list[index].to_comp_station_nm}}
            </td>
            <td>駅</td>
            <td>
              {{apprDocInfo.commute_route_list[index].travel_cost}}
            </td>
          </tr>

          <tr>
            <td colspan="8" align="right">
              <font color=red>
                <b>
                      ※バス申請について、『自宅～最寄駅』の距離が2ｋｍ以上の場合のみ、申請可能となります。
                    </b>
              </font>
              <span class="item_label">合計</span>
            </td>
            <td>
              <span class="money">{{formatMoney(apprDocInfo.travel_cost_sum,0,"￥")}}</span>
            </td>
          </tr>
        </tbody>
      </table>
      <el-form-item label="日額（往復）" prop="deducation_price">
        {{formatMoney(apprDocInfo.day_travel_cost,0,"￥")}}
      </el-form-item>
      <el-form-item label-width="180px" label="１ヶ月の通勤代（定期）" prop="travel_cost">
        {{formatMoney(apprDocInfo.travel_cost,0,"￥")}}
      </el-form-item>
      <el-form-item label="支払上限" prop="pay_travel_costa_max">
        {{formatMoney(apprDocInfo.pay_travel_costa_max,0,"￥")}}
      </el-form-item>

      <el-form-item 　label-width="150px" label="交通経路　　　　　 (自宅->現場乗換含む)">
        <el-input type="textarea" style="width:750px" placeholder="交通経路" :disabled="true" v-model="apprDocInfo.rount_dlt_content">
        </el-input>
      </el-form-item>

      <h4>購買の依頼内容</h4>
      <el-form-item
             label="購買手続き区分"
              prop="procedure_k_nm">
        {{apprDocInfo.procedure_k_nm}}
      </el-form-item>

      <h5>入社手続きについて</h5>
      <el-row :gutter="0">
        <el-col :span="8">
          <el-form-item prop="procedure_dt" label="手続予定日">
            {{apprDocInfo.procedure_dt}}
          </el-form-item>
        </el-col>
        <el-col :span="8">
          <el-form-item prop="entrance_dt" label="入社日">
            {{apprDocInfo.entrance_dt}}
          </el-form-item>
        </el-col>
      </el-row>

      <h5>退社手続きについて</h5>
      <el-row :gutter="0">
        <el-col :span="8">
          <el-form-item prop="withdrawal_dt" label="退社日">
            {{apprDocInfo.withdrawal_dt}}
          </el-form-item>
        </el-col>
        <el-col :span="8">
          <el-form-item label="手続方法" prop="procedure_method_k">
            {{apprDocInfo.procedure_method_k_nm}}
          </el-form-item>
        </el-col>
      </el-row>
      <el-row :gutter="0">
        <el-col :span="8">
          <el-form-item label="退社理由" prop="withdrawal_reason_k">
            {{apprDocInfo.withdrawal_reason_k_nm}}
          </el-form-item>
        </el-col>
        <el-col :span="8">
          <el-form-item label="離職票の有無" prop="withdrawal_letter_k">
            {{apprDocInfo.withdrawal_letter_k_nm}}
          </el-form-item>
        </el-col>
      </el-row>
      <el-row :gutter="0">
        <el-col :span="8">
          <el-form-item label="必要書類" prop="withdrawal_req_doc">
            {{apprDocInfo.withdrawal_req_doc_nm}}
          </el-form-item>
        </el-col>
        <el-col :span="8">
          <el-form-item label="社保任意継続" prop="insurance_con_k">
            {{apprDocInfo.insurance_con_k_nm}}
          </el-form-item>
        </el-col>
      </el-row>

      <el-row :gutter="0">
        <el-col :span="8">
          <el-form-item 　label-width="150px" label="退職後の郵便番号〒" prod="after_withdrawal_post_no">
            {{apprDocInfo.after_withdrawal_post_no}}
          </el-form-item>
        </el-col>
        <el-col :span="8">
          <el-form-item 　label-width="120px" label="退職後の住所" prod="after_withdrawal_addr">
            {{apprDocInfo.after_withdrawal_addr}}
          </el-form-item>
        </el-col>
      </el-row>

      <h4>コメント事項</h4>
      <el-form-item 　label-width="140px" label="商流記載" prod="commercial_dist_remark">
        <el-input type="textarea" :rows="12" style="width:800px" placeholder="" :disabled="true" v-model="apprDocInfo.commercial_dist_remark">
        </el-input>
      </el-form-item>
      <el-form-item 　label-width="140px" label="連絡事項（購買へ）" prod="purchase_remark">
        <el-input type="textarea" :rows="12" style="width:800px" :disabled="true" placeholder="" v-model="apprDocInfo.purchase_remark">
        </el-input>
      </el-form-item>

      <el-form-item 　label-width="140px" label="特記事項（要員）" prod="emp_remark">
        <el-input type="textarea" :rows="12" style="width:800px" :disabled="true" placeholder="" v-model="apprDocInfo.emp_remark">
        </el-input>
      </el-form-item>

    </el-form>
    <el-col :span="24">
      <span id="to_upd_button">
              <el-button
              type="success"
              :disabled="!update_btn_valid_flg"
              @click="goToUpdate">編集
            </el-button>
            </span>
      <span id="apply_button">
              <el-button
              type="info"
              :disabled="!apply_btn_valid_flg"
              @click="openConfirmApplyDlg">承認申請
            </el-button>
            </span>
      <span id="apply_button">
              <el-button
              type="warning"
              :disabled="!invalidate_btn_valid_flg"
              @click="disuseApprDoc">取消
            </el-button>
          </span>
      <span id="apply_button">
              <el-button
              type="success"
              :disabled="!copy_btn_valid_flg"
              @click="goToSimilarEnt">コピー
            </el-button>
            </span>
      <span id="apply_button">
              <el-button
              type="danger"
              :disabled="!delete_btn_valid_flg"
              @click="deleteApprDoc">削除
            </el-button>
            </span>
    </el-col>

    <el-dialog title="稟議書申請" v-model="appr_doc_dlg_show_flg">
      <el-form :inline="true" :rules="dlg_rules" :model="appr_doc_apply_info" ref="appr_doc_apply_info" label-width="120px">

        <el-form-item label="申請理由" prop="apply_reason">
          <el-input type="textarea" v-model="appr_doc_apply_info.apply_reason" style="width:500px" auto-complete="off">
          </el-input>
        </el-form-item>

        <el-row>
          <el-col :span="20">
            <h4>【承認経路】</h4>
          </el-col>
          <el-col :span="4">
            <span id="apply_button">
        <el-button
        size="mini"
        type="info"
        @click="addConfirmRounte">
        承認ルート追加
       </el-button>
      </span>
          </el-col>
        </el-row>
        <el-table :data="appr_doc_apply_info.appr_doc_apply_dtl" id="AcceptRoute" height="500px">
          <el-table-column label="表示順" width="150">
            <template slot-scope="scope">
                <el-input-number
                size="small"
                style="width:100px"
                v-model="scope.row.display_order"
                :min="1"
                :max="99">
                </el-input-number>
</template>
       </el-table-column>
        <el-table-column
        label="承認者ID"
        width="150">

<template slot-scope="scope">
  <el-form-item required :prop="'appr_doc_apply_dtl.' + scope.$index + '.accept_user_id'" :rules="[
                   { required: true, message: '入力必須です', trigger : 'blur' },
                  // {validator: chkAcceptUserId, trigger:'blur'},
                 ]">
    <el-input size="small" type="text" style="width:120px;" v-model="scope.row.accept_user_id" auto-complete="off" @blur="setUserInfo(scope.$index, scope.row)" :disabled="false">
    </el-input>
  </el-form-item>
</template>
       </el-table-column>
        <el-table-column
        label="承認者名称"
        prop="accept_user_name"
        width="250">
       </el-table-column>
        <el-table-column
        label="職位"
        width="200">
<template slot-scope="scope">
   {{scope.row.job_title_name}}
</template>
       </el-table-column>
      <el-table-column
           width="100"
           label="操作">
<template slot-scope="scope">
  <el-button type="danger" @click="delConfirmRounte(scope.$index,scope.row)" size="mini">
    削除</el-button>
</template>
       </el-table-column>
      </el-table>
    </el-form>
    <el-row>
    <el-col :span="10">
    <span id="apply_button">
      <el-button
      type="success"
      @click="applyApprDoc">
      申請
     </el-button>
   </span>
 </el-col>
  </el-row>
    <br/>
  </el-dialog>
</div>
</template>


<script>
      import {
        mapGetters
      } from 'vuex';
      import { ApprDocDiffTable } from '@/webedi/apprDoc/components';
      import { ApprDocSalesTable } from '@/webedi/apprDoc/components';

      export default {

        components: {
        'apprdoc-diff-table' : ApprDocDiffTable,
        'apprdoc-sales-table' : ApprDocSalesTable
      },

    data() {
      var chkAcceptUserId = (rule, value, callback) => {
        var p_val = this.$_.trim(value);
        var user_list = this.appr_doc_apply_info.appr_doc_apply_dtl;
        // console.log("user_list=%s",user_list);
        var same_user_list = this.$_.filter(user_list, {accept_user_id:p_val})
        if(this.$_.size(same_user_list) > 1){
          callback(new Error('承認者が重複しています'));
        }
        callback();
      };

      return {
        apprDocInfo: {
          fileList: [],
          appr_doc_id: '', //稟議書ID
          appr_doc_st: '',
          appr_doc_no: '',
          appr_doc_create_k: '',
          appr_doc_create_k_nm: '',
          appr_doc_create_dt: '',
          appr_doc_dt_from: '',
          appr_doc_dt_to: '',
          pj_cd: '',
          site_entrance_dt: '',
          entrance_dt: '',
          business_main_user_id: '',
          business_main_user_nm: '',
          business_sub_user_id: '',
          business_sub_user_nm: '',
          emp_nm: '',
          emp_no: '',
          emp_birthday: '',
          emp_nm_fuli: '',
          emp_sex: '',
          emp_sex_nm: '',
          withdrawal_dt: '',
          from_home_station_nm: '', //追加項目
          emp_age: '',
          entrance_record_k: '',
          emp_my_number: '',
          emp_pat_k: '',
          emp_pat_k_nm: '',
          insurance_k: '',
          insurance_k_nm: '',
          insurance_join_ymd: '',
          affiliation_k: '',
          affiliation_k_nm: '',
          client_no: '',
          client_nm: '',
          client_contract_k: '', //追加項目
          client_contract_nm: '', //追加項目
          client_order_no: '', //追加項目
          order_recv_hope_dt: '',
          take_over_k: '',
          take_over_k_nm: '',
          business_days: '',
          work_days: '',
          sales_addup_month: '',
          billing_month: '',
          sell_price: '',
          cost_price: '',
          travel_cost: '',
          travel_cost_first_mon: '',
          insurance_cost: '', //追加項目？
          other_cost: '',
          other_cost_first_mon: '',

          //          basic_salary   : '', //追加項目
          appoint_allow_ratio: '', //役職手当割合
          house_allow_ratio: '', //住宅手当割合
          aliment_allow_ratio: '', //扶養手当割合   不要？
          communication_allow_ratio: '', //通信手当割合
          site_allow_ratio: '', //現場手当割合
          duty_allow_ratio: '', //当番手当割合 不要？
          overwork_allow_ratio: '', //残業手当割合
          qualification_allow_ratio: '', //資格手当割合　不要？
          other_allow2_ratio: '', //そのた手当２割合

          other_allow1_nm: '', //追加項目
          other_allow1_ratio: '', //追加項目
          other_allow2_nm: '', //追加項目
          // other_allow2_ratio: '', //追加項目

          client_url: 'http://client.order.so.jp', //クライアントURL
          client_my_number: '', //クライアント法人マイナンバー
          client_post_no: '', //クライアント郵便番号
          client_addr: '', //クライアント連絡先住所
          client_tel_no: '', //クライアント電話番号
          client_fax_no: '', //クライアントFAX番号
          client_staff_nm: '', //クライアント担当者名
          client_staff_nm_fuli: '', //クライアント担当者名（フリガナ）
          client_staff_dept_nm: '', //クライアント担当者所属部署
          client_staff_position: '', //クライアント担当者役職
          client_staff_mobile_no: '', //クライアント担当者携帯番号
          // client_staff_mobile_mail_addr : '' ,      //クライアント担当者メールアドレス 不要
          client_mail_addr: '', //クライアントメール
          client_mail_addr_cc: '', //クライアントメールCC 追加項目
          client_req_staff_nm: '', //請求担当者名
          client_req_staff_nm_fuli: '', //請求担当者名（フリガナ）
          client_req_staff_dept_nm: '', //請求担当者所属部署
          client_req_staff_position: '', //請求担当者役職
          client_req_tel_no: '', //請求担当者電話番号
          client_req_fax_no: '', //請求担当者FAX番号
          client_req_staff_mobile_no: '', //請求担当者携帯番号
          client_req_mail_addr: '', //請求担当者メール
          client_req_mail_addr_cc: '', //請求担当者メールCC 追加項目
          client_req_post_no: '', //請求担当者郵便番号 追加項目
          client_req_addr: '', //請求担当者連絡先住所 追加項目

          //月次処理設定　追加項目
          mon_pro_k: '', //月次処理区分
          mon_pro_k_nm: '', //月次処理区分
          work_rep_f: '', //作業報告書F
          work_rep_f_nm: '', //作業報告書F
          bill_f: '', //請求書F
          bill_f_nm: '', //請求書F
          work_rep_dl_k: '', //作業報告書締め区分
          work_rep_dl_k_nm: '', //作業報告書締め区分
          bill_dl_k: '', //請求書締め区分
          bill_dl_k_nm: '', //請求書締め区分
          mon_attach_file_k: '', //添付資料F
          mon_attach_file_k_nm: '', //添付資料F
          mon_attach_file_id: '', //添付ファイルID

          pj_nm: '○○○○システム再構築フェーズ１', //作業案件名称
          work_place: '', //勤務地/最寄駅
          work_content: '', //作業内容・範囲
          salary_pay_k: '', //給料支払区分　新規追加項目
          salary_pay_k_nm: '', //給料支払区分　新規追加項目
          accounting_pat_k: '', //精算条件
          accounting_pat_k_nm: '', //精算条件
          wh_max: '', //作業時間（上限・H）
          wh_min: '', //作業時間（下限・H）
          wh_mid: '', //中割時間
          wh_other: '', //その他（時間）　削除項目
          excess_price: '', //超過単価（円）
          deducation_price: '', //控除単価（円）
          pj_start_dt: '', //業務案件今月分の開始日
          pj_end_dt: '', //業務案件今月分の終了日
          work_time_unit_k: '', //作業時間単位
          work_time_unit_k_nm: '', //作業時間単位
          contract_work_month: '', //工数（契約用）
          pj_class_cd: '', //案件分類
          pj_class_nm: '', //案件分類名称　追加項目
          work_start_time: '', //出勤時刻
          work_end_time: '', //退勤時刻
          break_time_min: '', //休憩時間（分）
          payment_plan_d: '', //支払サイト/出金日付の日
          payment_due_k: '', //支払サイト/締日区分
          payment_due_k_nm: '', //支払サイト/締日区分
          pay_month_k: '', //支払サイト/支払月区分
          pay_month_k_nm: '', //支払サイト/支払月区分
          pay_plan_d: '', //支払サイト/支払日付の日
          pay_plan_d_nm: '', //支払サイト/支払日付の日 名称
          salary_pay_plan_dt: '', //支払サイト/出金予定日
          work_content_dtl: '', //業務内容詳細
          //          work_evidence1 : '' , //エビデンス1
          //          work_evidence2 : '' , //エビデンス2
          work_evidence_list: [{
              display_order: '1',
              workEvidence: 'XXXXXXXXXXXXXXXXXXXXXXXXXXX\r\n○○○○○○○○○○○○○○\r\n△△△△△△△△△△△△△'
            },
            {
              display_order: '2',
              workEvidence: '222222222222222222223333333333333333333333334444444444444'
            }
          ],

          bp_comp_id: '', // 所属会社ID
          bp_comp_emp_nm: 'XXXXX株式会社', // 所属会社名/社員氏名
          bp_comp_url: 'http://bpcomp.co.jp', // 所属会社URL
          bp_my_number: '', // 所属会社法人マイナンバー
          bp_post_no: '', // 所属会社郵便番号
          bp_addr: '', // 所属会社連絡先住所
          bp_tel_no: '', // 所属会社電話番号
          bp_fax_no: '', // 所属会社FAX番号
          bp_staff_nm: '', // 所属会社担当者名
          bp_staff_nm_fuli: '', // 所属会社担当者名（フリガナ）
          bp_staff_dept_nm: '', // 所属会社担当者所属部署
          bp_staff_position: '', // 所属会社担当者役職
          bp_staff_mobile_no: '', // 所属会社担当者携帯番号
          //del          bp_staff_mobile_mail_addr : '' , // 所属会社担当者携帯番号メールアドレス
          bp_staff_mail_addr: 'bpemp1@bpcomp.co.jp', // 所属会社担当者個人メール
          bp_staff_mail_addr_cc: 'bpperson@yahoo.co.jp', // 所属会社担当者個人メールCC 追加項目
          bp_staff_tel_no: '', // 所属会社担当者電話番号 追加項目
          bp_staff_fax_no: '', // 所属会社担当者FAX番号 追加項目
          staff_addr: '', // 要員住所
          staff_post_no: '', // 要員郵便番号
          staff_mail_addr: '', // 要員メールアドレス
          staff_tel_no: '', // 要員電話番号
          staff_mobile_no: '', // 要員携帯電話番号

          //クライアント契約条件とほぼ同じ
          // working_pj_no : '' , // 稼働中案件番号
          // pj_nm : '' , // 作業案件名称
          // work_place : '' , // 勤務地/最寄駅
          // work_content : '' , // 作業内容・範囲
          // cost_price : '' , // 要員渡し/原価
          // accounting_pat_k : '' , // 精算条件
          // wh_max : '' , // 作業時間（上限・H）
          // wh_min : '' , // 作業時間（下限・H）
          // wh_mid : '' , // 中割時間
          // wh_other : '' , // その他（時間）
          // excess_price : '' , // 超過単価（円）
          // deducation_price : '' , // 控除単価（円）
          // pj_start_dt : '' , // 業務案件今月分の開始日
          // pj_end_dt : '' , // 業務案件今月分の終了日
          // work_unit_k : '' , // 作業時間単位
          // total_work_month : '' , // 工数
          // hope_job_type_cd : '' , // 希望職種
          // pri_exp_job_type_cd : '' , // 主な経験職種
          // pj_class_cd : '' , // 案件分類
          // work_start_time : '' , // 出勤時刻
          // work_end_time : '' , // 退勤時刻
          // break_time_min : '' , // 休憩時間（分）
          bp_payment_plan_d: '', //支払サイト/出金日付の日
          bp_payment_due_k: '', //支払サイト/締日区分
          bp_payment_due_k_nm: '', //支払サイト/締日区分
          bp_pay_month_k: '', //支払サイト/支払月区分
          bp_pay_month_k_nm: '', //支払サイト/支払月区分
          bp_pay_plan_d: '', //支払サイト/支払日付の日
          bp_pay_plan_d_nm: '', //支払サイト/支払日付の日 名称

          bp_salary_pay_plan_dt: '', // 支払サイト/出金予定日
          // work_content_dtl : '' , // 業務内容詳細

          day_travel_cost: '', // 交通費日額（往復）
          month_pass_travel_cost: '', // 1ヶ月の通勤代（定期）
          pay_travel_costa_max: '', // 支払上限
          rount_dlt_content: '', // 自宅→現場（乗り換え含め交通経路記入）
          travel_cost_sum: '', //
          commute_route_list: [],


          //        del       contract_pat_k : '', // 契約形態
          //        del       procedure_k : '', // 手続き区分
          //        del       withdrawal_dt : '', // 退社日
          procedure_method_k: '', // 手続き区分 新規追加項目
          procedure_method_k_nm: '', // 手続き区分 新規追加項目
          withdrawal_reason_k: '', // 退社理由　新規追加項目
          withdrawal_reason_nm: '', // 退社理由　新規追加項目
          withdrawal_letter_k: '', // 離職票の有無　新規追加項目
          withdrawal_letter_k_nm: '', // 離職票の有無　新規追加項目
          insurance_con_k: '', // 社保任意継続　新規追加項目
          insurance_con_k_nm: '', // 社保任意継続　新規追加項目
          withdrawal_req_doc: '', // 必要書類　新規追加項目
          withdrawal_req_doc_nm: '', // 必要書類　新規追加項目
          after_withdrawal_post_no: '', // 退社後郵便番号
          after_withdrawal_addr: '', // 退社後住所
          purchase_remark: '', // 備考→連絡事項（購買へ）
          commercial_dist_remark: '', //商流記載　新規追加項目
          emp_remark: '', //特記事項（要員）　新規追加項目

          dina_reg_f: true, // DINA 依頼依頼へ登録するかフラグ
        }, //apprDocInfo end

        //申請情報
        appr_doc_apply_info: {
          appr_doc_id: '',
          appr_doc_no: '',
          confirm_wait_display_order: '', //承認待表示順
          apply_reason: '',
          //WF申請経路
          appr_doc_apply_dtl: [{
              display_order: 1,
              accept_user_id: '10001',
              accept_user_name: '山田　真一',
              job_title_cd　: '001',
              job_title_name: '営業担当',
            },
            {
              display_order: 2,
              accept_user_id: '20001',
              accept_user_name: '伊藤　太郎',
              job_title_cd　: '002',
              job_title_name: '課長',
            },
            {
              display_order: 3,
              accept_user_id: '30001',
              accept_user_name: '坂本　剛',
              job_title_cd　: '003',
              job_title_name: '部長',
            },
            {
              display_order: 4,
              accept_user_id: '40001',
              accept_user_name: 'XX　XX',
              job_title_cd　: '900',
              job_title_name: '社長',
            },
          ],
        }, //appr_doc_apply_info end

        dlg_rules: {
          apply_reason: [{
            required: true,
            message: '申請理由が入力必須です。',
            trigger: 'blur'
          }]
        },

        apprDocInfo_last: {
          appr_doc_no: '',
          appr_doc_create_k: '',
          appr_doc_create_dt: new Date(),
          appr_doc_dt_from: '',
          appr_doc_dt_to: '',
          pj_cd: '',
          site_entrance_dt: '',
          entrance_dt: '',
          business_main_user_id: '',
          business_sub_user_id: '',
          emp_nm: '',
          emp_no: '',
          emp_birthday: '',
          emp_nm_fuli: '',
          emp_sex: '',
          withdrawal_dt: '',
          from_home_station_nm: '', //追加項目
          emp_age: '',
          entrance_record_k: '',
          emp_my_number: '',
          emp_pat_k: '',
          insurance_k: '',
          insurance_join_ymd: '',
          affiliation_k: '',
          client_no: '',
          client_nm: '',
          client_contract_k: '', //追加項目
          client_order_no: '', //追加項目
          order_recv_hope_dt: '',
          take_over_k: '',
          business_days: '',
          work_days: '',
          sales_addup_month: '',
          billing_month: '',
          sell_price: '',
          cost_price: '',
          travel_cost: '',
          travel_cost_first_mon: '',
          insurance_cost: '',
          other_cost: '',
          other_cost_first_mon: '',


          payment_plan_d: '',
          bp_payment_plan_d: '',

        },
        tax_rate: 8,

        appr_doc_dlg_show_flg: false,

        entrance_record_list: [],

        appr_doc_st_list: [{
            value: '1',
            label: '申請待',
            comment: 'データ登録直後'
          },
          {
            value: '2',
            label: '承認待',
            comment: 'データ承認申請後'
          },
          {
            value: '3',
            label: '承認中',
            comment: '承認WFがスタート後'
          },
          {
            value: '4',
            label: '承認済',
            comment: '承認経路の承認者がすべて承認済'
          },
          {
            value: '5',
            label: '契約済',
            comment: '受注先と合意済後'
          },
          //  {
          //   value : 'A',
          //   label : '取消',
          //   comment : 'ユーザー取消（画面上で検索可能）'
          // }
        ],

        wf_record_list: []
      };
    },

    methods: {
      goToUpdate() {
        this.$router.push({
          path: 'ApprDocUpd',
          query: {
            appr_doc_no: this.apprDocInfo.appr_doc_no
          }
        });
      },

      //申請画面を開く
      openConfirmApplyDlg() {
        this.appr_doc_dlg_show_flg = true;
      },

      //申請
      applyApprDoc() {
        var _this = this;
        _this.appr_doc_apply_info.appr_doc_no = _this.apprDocInfo.appr_doc_no;
        _this.appr_doc_apply_info.appr_doc_id = _this.apprDocInfo.appr_doc_id;

       //承認者重複チェック
        var user_list = this.appr_doc_apply_info.appr_doc_apply_dtl;

      if(this.$_.size(user_list) ===0){
           _this.$message.error('承認者がありません。');
           return;
      }

      //承認者は自分の場合、エラー
      var login_userid = this.user_id
      // console.log("login_userid=%s",login_userid);
      var user_self = this.$_.find(user_list, function(ent){return ent.accept_user_id === login_userid});
      // console.log("user_self=%s",user_self);
      if(!this.$_.isUndefined(user_self)){
        _this.$message.error('申請者は承認者リストに入っています');
              return;
      }
        for(var i=0; i< user_list.length; i++){
            var user_info = user_list[i]
            var same_user_list = this.$_.filter(user_list, {accept_user_id:user_info.accept_user_id})
            if(this.$_.size(same_user_list) > 1){
              _this.$message.error('承認者が重複しています');
              return;
            }
        }


        this.$refs.appr_doc_apply_info.validate((valid) => {
          if (valid) {
            _this.$confirm('稟議書データを申請してもよろしいでしょうか？', '注意', {
              confirmButtonText: '確定',
              cancelButtonText: '中止',
              type: 'warning'
            }).then(() => {
              _this.$http.post('/api/apprDoc/apply', _this.appr_doc_apply_info)
                .then(function(response) {
                  var errorcode = response.data.errorcode;
                  if (errorcode == "200") {
                    _this.$message({
                      type: 'success',
                      message: '申請が成功しました!'
                    });
                    _this.appr_doc_dlg_show_flg = false;
                    _this.returnToInq();
                  } else if (errorcode == "500") {
                    _this.$message.error("申請が失敗しました。[" + response.data.errormsg + "]");
                  }
                })
                .catch(function(error) {
                  console.log(error);
                });
            }).catch(() => {
              _this.$message({
                type: 'info',
                message: '申請が中止しました！'
              });
            });
          } else {
            this.$message.error("入力項目にエラーがあります！");
          }
        })
      },


      //承認ルートを削除
      delConfirmRounte(index, row) {
        this.appr_doc_apply_info.appr_doc_apply_dtl.splice(index, 1);
      },
      //承認ルートを追加
      addConfirmRounte() {
        var appr_doc_apply_dtl = this.appr_doc_apply_info.appr_doc_apply_dtl;
        var p_display_order = 1;
        if(this.$_.size(appr_doc_apply_dtl) !==0){
            p_display_order = this.$_.add(this.$_.maxBy(appr_doc_apply_dtl, function(item) {
                        return this.$_.toInteger(item.display_order);
                      }).display_order, 1)
        }
        appr_doc_apply_dtl.push({
          display_order: p_display_order,
          accept_user_id: '',
          accept_user_name: '',
          job_title_cd　: '',
          job_title_nm　: '',
        });
      },

      //承認ルートの初期値を取得
      getInitConfirmRounte(account) {
        var _this = this;
        this.$http.get('/api/user/select', {
            params: {
              account: account
            },
          })
          .then(function(response) {
            var errorcode = response.data.errorcode;
            if (errorcode == "200") {
              // var userInfo=response.data.data;
              _this.appr_doc_apply_info.appr_doc_apply_dtl = response.data.data.userwfconfirmroute;

            } else if (errorcode == "500") {
              _this.appr_doc_apply_info.appr_doc_apply_dtl = [];
              _this.$message.error(response.data.errormsg);
            }
          })
          .catch(function(error) {
            _this.appr_doc_apply_info.appr_doc_apply_dtl = [];
            console.log(error);
          });
      },

      setUserInfo(index, row) {
        var user_id = row.accept_user_id;

        if (user_id === '') {
          row.accept_user_id = '';
          row.accept_user_name = '';
          row.job_title_name = '';
          return;
        }
        this.setConfirmUserInfo(user_id, row);
      },

      //承認者情報を取得し設定する
      setConfirmUserInfo(user_id, row) {
        var _this = this;
        this.$http.get('/api/user/select', {
            params: {
              account: user_id
            },
          })
          .then(function(response) {
            var errorcode = response.data.errorcode;
            if (errorcode == "200") {
              row.accept_user_name = response.data.data.name;
              row.job_title_name = response.data.data.job_title_nm;
            } else if (errorcode == "500") {
              row.accept_user_id = '';
              row.accept_user_name = '';
              row.job_title_name = '';
              _this.$message.error(response.data.errormsg);
            }
          })
          .catch(function(error) {
            row.accept_user_id = '';
            row.accept_user_name = '';
            row.job_title_name = '';
            console.log(error);
          });
      },

      //取消
      disuseApprDoc() {
        var p_appr_doc_no = this.apprDocInfo.appr_doc_no;
        var compiled = this.$_.template('稟議書データ(稟議書NO：<%= appr_doc_no %>)を取消してもよろしいでしょうか？');
        var confirmMsg = compiled({
          'appr_doc_no': p_appr_doc_no
        });
        var p_appr_doc_id = this.apprDocInfo.appr_doc_id;
        var _this = this;
        this.$confirm(confirmMsg, '注意', {
          confirmButtonText: '確定',
          cancelButtonText: '中止',
          type: 'warning'
        }).then(() => {
          var _this = this;
          _this.$http.get('/api/apprDoc/invalidate', {
              params: {
                "apprDocId": p_appr_doc_id
              }
            })
            .then(function(response) {
              var errorcode = response.data.errorcode;
              if (errorcode == "200") {
                _this.$message({
                  type: 'success',
                  message: '取消が成功しました!'
                });
                _this.returnToInq();
              } else if (errorcode == "500") {
                _this.$message({
                  type: 'error',
                  message: '取消が失敗しました![' + response.data.errormsg + ']'
                });
              }
            })
            .catch(function(error) {
              console.log(error);
            });
        }).catch(() => {
          this.$message({
            type: 'info',
            message: '取消が中止しました！'
          });
        });

      },

      goToSimilarEnt() {
        this.$router.push({
          path: 'ApprDocEnt',
          query: {
            appr_doc_no: this.apprDocInfo.appr_doc_no
          }
        });
      },

      //削除
      deleteApprDoc() {
        var p_appr_doc_no = this.apprDocInfo.appr_doc_no;
        var compiled = this.$_.template('稟議書データ(稟議書NO：<%= appr_doc_no %>)を削除してもよろしいでしょうか？');
        var confirmMsg = compiled({
          'appr_doc_no': p_appr_doc_no
        });
        var _this = this;
        this.$confirm(confirmMsg, '注意', {
          confirmButtonText: '確定',
          cancelButtonText: '中止',
          type: 'warning'
        }).then(() => {
          var _this = this;
          _this.$http.delete('/api/apprDoc/delete', {
              params: {
                "apprDocNo": p_appr_doc_no
              }
            })
            .then(function(response) {
              var errorcode = response.data.errorcode;
              if (errorcode == "200") {
                _this.$message({
                  type: 'success',
                  message: '削除が成功しました!'
                });
                _this.returnToInq();
              } else if (errorcode == "500") {
                _this.$message({
                  type: 'error',
                  message: '削除が失敗しました![' + response.data.errormsg + ']'
                });
              }
            })
            .catch(function(error) {
              console.log(error);
            });
        }).catch(() => {
          this.$message({
            type: 'info',
            message: '削除が中止しました！'
          });
        });

      },

      returnToInq() {
        if (this.linkSrc == '0') {
          this.$router.push({
            path: 'ApprDocConfirm'
          });
        } else {
          this.$router.push({
            path: 'ApprDocInq'
          });
        }
      },
      tableRowClassName(row, index) {
        if (row.display_order === this.apprDocInfo.confirm_wait_display_order) {
          return 'process-row';
        }
        return '';
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

      formatRatio: function(num1, num2, pos) {
        var p_num1 = this.$_.toInteger(num1);
        var p_num2 = this.$_.toInteger(num2);
        if (isNaN(p_num1) || isNaN(p_num2)) {
          return '';
        }

        if (p_num2 === 0) {
          return '';
        }
        return this.$_.round((p_num1 / p_num2) * 100, pos) + '%';
      },
      loadApprInfo() {
        var _this = this;
        _this.$http.get('/api/apprDoc/detail', {
            params: {
              apprDocNo: _this.$route.query.appr_doc_no
            }
          })
          .then(function(response) {
            var errorcode = response.data.errorcode;
            if (errorcode == "200") {
              _this.apprDocInfo = response.data.data;
              //  console.log(JSON.stringify(_this.apprDocInfo));

            } else if (errorcode == "500") {
              _this.$message.error(response.data.errormsg);
            }
          })
          .catch(function(error) {
            console.log(error);
          });
      },
      loadApprInfoLast() {
        var _this = this;
        _this.$http.get('/api/apprDoc/detailLast', {
            params: {
              apprDocNo: _this.$route.query.appr_doc_no
            }
          })
          .then(function(response) {
            var errorcode = response.data.errorcode;
            if (errorcode == "200") {
              if (response.data.data == null || response.data.data == '' || response.data.data == {}) {
                return;
              }
              _this.apprDocInfo_last = response.data.data;
            } else if (errorcode == "500") {
              _this.$message.error(response.data.errormsg);
            }
          })
          .catch(function(error) {

            console.log(error);
          });
      },
      loadEntranceRecordList() {
        var _this = this;
        _this.$http.get('/api/apprDoc/emphis', {
            params: {
              apprDocNo: _this.$route.query.appr_doc_no
            }
          })
          .then(function(response) {
            var errorcode = response.data.errorcode;
            if (errorcode == "200") {
              _this.entrance_record_list = response.data.data;

            } else if (errorcode == "500") {
              _this.entrance_record_list = [];
              _this.$message.error(response.data.errormsg);
            }
          })
          .catch(function(error) {
            _this.entrance_record_list = [];
            console.log(error);
          });
      },
      loadWfRecordList() {
        var _this = this;
        _this.$http.get('/api/apprDoc/wflist', {
            params: {
              apprDocNo: _this.$route.query.appr_doc_no
            }
          })
          .then(function(response) {
            var errorcode = response.data.errorcode;
            if (errorcode == "200") {
              _this.wf_record_list = response.data.data;

            } else if (errorcode == "500") {
              _this.$message.error(response.data.errormsg);
            }
          })
          .catch(function(error) {
            console.log(error);
          });
      }

    },
    mounted() {
      this.loadApprInfo();
      this.loadApprInfoLast();
      this.loadEntranceRecordList();
      this.loadWfRecordList();
      this.getInitConfirmRounte(this.account);
    },


    computed: {

      ...mapGetters([
        'user_id',
        'account',
        'username',
        'roles'
      ]),


      work_business_ratio: function() {
        var p_num1 = this.$_.toInteger(this.apprDocInfo.work_days);
        var p_num2 = this.$_.toInteger(this.apprDocInfo.business_days);
        if (isNaN(p_num1) || isNaN(p_num2)) {
          return '';
        }

        if (p_num2 === 0) {
          return '';
        }
        return this.$_.floor((p_num1 / p_num2) * 100, 0) + '%';
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



      //  *  稟議書状態
      //  *  1：申請待    稟議書データ登録直後
      //  *  2：承認待    稟議書データ承認WFを申請後　※申請後、承認中前、申請取消が可能
      //  *  3：承認中    稟議書承認WFがスタート後
      //  *  4：承認済    承認経路の承認者がすべて承認済
      //  *  5：契約済    受注先と合意済の後
      //  *  A:取消     ユーザー取消（画面上で検索可能）
      //編集ボタン利用可フラグ
      update_btn_valid_flg: function() {
        var valid_arry = ['1'];
        var st = this.apprDocInfo.appr_doc_st;
        if (this.$_.includes(valid_arry, st)) return true;
        return false;
      },
      //承認申請ボタン利用可フラグ
      apply_btn_valid_flg: function() {
        var valid_arry = ['1'];
        var st = this.apprDocInfo.appr_doc_st;
        if (this.$_.includes(valid_arry, st)) return true;
        return false;
      },
      //取消ボタン利用可フラグ
      invalidate_btn_valid_flg: function() {
        var valid_arry = ['1', '4', '5'];
        var st = this.apprDocInfo.appr_doc_st;
        if (this.$_.includes(valid_arry, st)) return true;
        return false;
      },
      //コピーボタン利用可フラグ
      copy_btn_valid_flg: function() {
        var valid_arry = ['1', '2', '3', '4', '5', 'A'];
        var st = this.apprDocInfo.appr_doc_st;
        if (this.$_.includes(valid_arry, st)) return true;
        return false;
      },
      //削除ボタン利用可フラグ
      delete_btn_valid_flg: function() {
        var valid_arry = ['1'];
        var st = this.apprDocInfo.appr_doc_st;
        if (this.$_.includes(valid_arry, st)) return true;
        return false;
      }

    } // computed end


  };
</script>

<style>
  #appr_doc_info {
    width: 950px;
    margin-top: 25px;
    margin-left: 30px;
  }

  h3 h4 {
    margin-bottom: 30px;
  }

  #ent_button {
    margin-left: 20px;
    margin-right: 15px;
  }

  span.ratio {
    color : orange;
  }

  span.item_label {
    border-radius: 1px;
    width: 150px;
    height: 50px;
    font-size: 15px;
    vertical-align: middle;
  }

  span.money {
    border-radius: 1px;
    width: 150px;
    height: 50px;
    font-size: 15px;
    vertical-align: middle;
  }

  span.minus {
    color: red;
  }

  th {
    background-color: hsla(120, 65%, 75%, 0.1);
  }

  img.doc {
    width: auto;
    height: auto;
    max-width: 100%;
    max-height: 100%;
  }

  .el-table .process-row {
    background: yellow;
  }
</style>

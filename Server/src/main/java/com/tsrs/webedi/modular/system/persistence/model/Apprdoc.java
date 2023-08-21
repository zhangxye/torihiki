package com.tsrs.webedi.modular.system.persistence.model;

import com.baomidou.mybatisplus.annotation.FieldFill;
import com.baomidou.mybatisplus.annotation.IdType;
import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.Date;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableLogic;
import com.baomidou.mybatisplus.annotation.TableName;
import com.baomidou.mybatisplus.annotation.TableField;
import com.alibaba.fastjson.annotation.JSONField;
import com.baomidou.mybatisplus.extension.activerecord.Model;

import java.io.Serializable;

/**
 * <p>
 * 稟議書
 * </p>
 *
 * @author tsrs
 * @since 2017-09-03
 */
@TableName("apprdoc")
public class Apprdoc extends Model<Apprdoc> {

    private static final long serialVersionUID = 1L;

    /**
     * 稟議書ID
     */
	@TableId(value="appr_doc_id", type= IdType.AUTO)
	private Integer apprDocId;
    /**
     * 稟議書NO
     */
	@TableField("appr_doc_no")
	private String apprDocNo;
    /**
     * 使用FLG:Y:使用
N:削除
     */
    @TableLogic
	@TableField("use_flg")
	private String useFlg;
    /**
     * 対象期間From
     */
	@TableField("appr_doc_dt_from")
	private Date apprDocDtFrom;
    /**
     * 対象期間To
     */
	@TableField("appr_doc_dt_to")
	private Date apprDocDtTo;
    /**
     * 稟議書作成区分:1：通常更新
2：新規
3：条件変更
4：正社員化
5：退職
6：再販
     */
	@TableField("appr_doc_create_k")
	private String apprDocCreateK;
    /**
     * 稟議書作成日
     */
	@TableField("appr_doc_create_dt")
	private Date apprDocCreateDt;
    /**
     * 稟議書状態:1：申請待    稟議書データ登録直後
2：承認待    稟議書データ承認WFを申請後　※申請後、承認中前、申請取消が可能
3：承認中    稟議書承認WFがスタート後
4：承認済    承認経路の承認者がすべて承認済、
5：契約済    受注先と合意済の後
A:取消     ユーザー取消（画面上で検索可能）
     */
	@TableField("appr_doc_st")
	private String apprDocSt;
    /**
     * 営業主担当
     */
	@TableField("business_main_user_id")
	private String businessMainUserId;
    /**
     * 営業副担当
     */
	@TableField("business_sub_user_id")
	private String businessSubUserId;
    /**
     * 部署C
     */
	@TableField("dept_cd")
	private String deptCd;
    /**
     * 社員番号
     */
	@TableField("emp_no")
	private String empNo;
    /**
     * 社員名
     */
	@TableField("emp_nm")
	private String empNm;
    /**
     * 社員名（フリガナ）
     */
	@TableField("emp_nm_fuli")
	private String empNmFuli;
    /**
     * 性別:M：男
F：女
     */
	@TableField("emp_sex")
	private String empSex;
    /**
     * 生年月日
     */
	@TableField("emp_birthday")
	private Date empBirthday;
	/**
	 * 入場日
	 */
	@TableField("site_entrance_dt")
	private Date siteEntranceDt;
    /**
     * 入社日
     */
	@TableField("entrance_dt")
	private Date entranceDt;
    /**
     * 要員マイナンバー
     */
	@TableField("emp_my_number")
	private String empMyNumber;
    /**
     * 希望雇用形態:1：正社員
2：契約社員
3：出向社員
4：派遣社員
5：委託契約
     */
	@TableField("emp_pat_k")
	private String empPatK;
    /**
     * 所属区分:1：個人事業主
2：協力会社（ＢＰ）
     */
	@TableField("affiliation_k")
	private String affiliationK;
    /**
     * 入社経歴:1：有
2：無
     */
	@TableField("entrance_record_k")
	private String entranceRecordK;
    /**
     * 前回稟議書NO
     */
	@TableField("old_appr_doc_no")
	private String oldApprDocNo;
    /**
     * 引取区分:1：新規取引先
2：既存取引先
     */
	@TableField("take_over_k")
	private String takeOverK;
    /**
     * 注文書受領日（見込）
     */
	@TableField("order_recv_hope_dt")
	private Date orderRecvHopeDt;
    /**
     * 最寄駅（自宅）
     */
	@TableField("from_home_station_nm")
	private String fromHomeStationNm;
    /**
     * 契約（上位）:1：委任契約
2：派遣契約
     */
	@TableField("client_contract_k")
	private String clientContractK;
    /**
     * 役職手当割合
     */
	@TableField("appoint_allow_ratio")
	private BigDecimal appointAllowRatio;
    /**
     * 住宅手当割合
     */
	@TableField("house_allow_ratio")
	private BigDecimal houseAllowRatio;
    /**
     * 通信手当割合
     */
	@TableField("communication_allow_ratio")
	private BigDecimal communicationAllowRatio;
    /**
     * 現場手当割合
     */
	@TableField("site_allow_ratio")
	private BigDecimal siteAllowRatio;
    /**
     * 残業手当割合
     */
	@TableField("overwork_allow_ratio")
	private BigDecimal overworkAllowRatio;
    /**
     * そのた手当1名称
     */
	@TableField("other_allow1_nm")
	private String otherAllow1Nm;
    /**
     * そのた手当1割合
     */
	@TableField("other_allow1_ratio")
	private BigDecimal otherAllow1Ratio;
    /**
     * そのた手当２名称
     */
	@TableField("other_allow2_nm")
	private String otherAllow2Nm;
    /**
     * そのた手当２割合
     */
	@TableField("other_allow2_ratio")
	private BigDecimal otherAllow2Ratio;
    /**
     * 売上販売計上月:1～12
売上販売の計上月
（請求対象月ではない）
     */
	@TableField("sales_addup_month")
	private Date salesAddupMonth;
	
	/**
	 * 契約総工数(人月)
	 */
	@TableField("sum_contract_wm")
	private BigDecimal sumContractWm;
    /**
     * 当月稼働日:1～31
     */
	@TableField("work_days")
	private Integer workDays;
    /**
     * 請求計上月:1～12
     */
	@TableField("billing_month")
	private Date billingMonth;
    /**
     * 上位単金/単価
     */
	@TableField("sell_price")
	private BigDecimal sellPrice;
    /**
     * 当月営業日:1～31
     */
	@TableField("business_days")
	private Integer businessDays;
    /**
     * 要員渡し/原価
     */
	@TableField("cost_price")
	private BigDecimal costPrice;
    /**
     * 交通費(初月)
     */
	@TableField("travel_cost_first_mon")
	private BigDecimal travelCostFirstMon;
    /**
     * その他(初月)
     */
	@TableField("other_cost_first_mon")
	private BigDecimal otherCostFirstMon;
    /**
     * 交通費
     */
	@TableField("travel_cost")
	private BigDecimal travelCost;
    /**
     * 社保費
     */
	@TableField("insurance_cost")
	private BigDecimal insuranceCost;
    /**
     * その他
     */
	@TableField("other_cost")
	private BigDecimal otherCost;
    /**
     * 保険加入区分:1：雇用保険のみ
2：社保険完備
3：保険無し
     */
	@TableField("insurance_k")
	private String insuranceK;
    /**
     * 社保加入予定日
     */
	@TableField("insurance_join_ymd")
	private Date insuranceJoinYmd;
    /**
     * クライアント番号
     */
	@TableField("client_no")
	private String clientNo;
    /**
     * クライアント名
     */
	@TableField("client_nm")
	private String clientNm;
    /**
     * クライアントURL
     */
	@TableField("client_url")
	private String clientUrl;
    /**
     * クライアント法人マイナンバー
     */
	@TableField("client_my_number")
	private String clientMyNumber;
    /**
     * クライアント郵便番号
     */
	@TableField("client_post_no")
	private String clientPostNo;
    /**
     * クライアント連絡先住所
     */
	@TableField("client_addr")
	private String clientAddr;
    /**
     * クライアント電話番号:XX-XXXX-XXXX
     */
	@TableField("client_tel_no")
	private String clientTelNo;
    /**
     * クライアントFAX番号:XX-XXXX-XXXX
     */
	@TableField("client_fax_no")
	private String clientFaxNo;
    /**
     * クライアント担当者名
     */
	@TableField("client_staff_nm")
	private String clientStaffNm;
    /**
     * クライアント担当者名（フリガナ）
     */
	@TableField("client_staff_nm_fuli")
	private String clientStaffNmFuli;
    /**
     * クライアント担当者所属部署
     */
	@TableField("client_staff_dept_nm")
	private String clientStaffDeptNm;
    /**
     * クライアント担当者役職
     */
	@TableField("client_staff_position")
	private String clientStaffPosition;
	/**
	 * クライアント担当者電話番号
	 */
	@TableField("client_staff_tel_no")
	private String clientStaffTelNo;
	/**
	 * クライアント担当者ファクス番号
	 */
	@TableField("client_staff_fax_no")
	private String clientStaffFaxNo;
    /**
     * クライアント担当者携帯番号
     */
	@TableField("client_staff_mobile_no")
	private String clientStaffMobileNo;
    /**
     * クライアントメール
     */
	@TableField("client_mail_addr")
	private String clientMailAddr;
    /**
     * クライアントメールCC
     */
	@TableField("client_mail_addr_cc")
	private String clientMailAddrCc;
    /**
     * 請求担当者名
     */
	@TableField("client_req_staff_nm")
	private String clientReqStaffNm;
    /**
     * 請求担当者名（フリガナ）
     */
	@TableField("client_req_staff_nm_fuli")
	private String clientReqStaffNmFuli;
    /**
     * 請求担当者所属部署
     */
	@TableField("client_req_staff_dept_nm")
	private String clientReqStaffDeptNm;
    /**
     * 請求担当者役職
     */
	@TableField("client_req_staff_position")
	private String clientReqStaffPosition;
    /**
     * 請求担当者電話番号:XX-XXXX-XXXX
     */
	@TableField("client_req_tel_no")
	private String clientReqTelNo;
    /**
     * 請求担当者FAX番号:XX-XXXX-XXXX
     */
	@TableField("client_req_fax_no")
	private String clientReqFaxNo;
    /**
     * 請求担当者携帯番号
     */
	@TableField("client_req_staff_mobile_no")
	private String clientReqStaffMobileNo;
    /**
     * 請求担当者メール
     */
	@TableField("client_req_mail_addr")
	private String clientReqMailAddr;
    /**
     * 請求担当者メールCC
     */
	@TableField("client_req_mail_addr_cc")
	private String clientReqMailAddrCc;
    /**
     * 請求担当者郵便番号
     */
	@TableField("client_req_post_no")
	private String clientReqPostNo;
    /**
     * 請求担当者連絡先住所
     */
	@TableField("client_req_addr")
	private String clientReqAddr;
    /**
     * 受注（注文）書No
     */
	@TableField("client_order_no")
	private String clientOrderNo;
    /**
     * 作業案件CD
     */
	@TableField("pj_cd")
	private String pjCd;
    /**
     * 作業案件名称
     */
	@TableField("pj_nm")
	private String pjNm;
    /**
     * 勤務地/最寄駅
     */
	@TableField("work_place")
	private String workPlace;
    /**
     * 作業内容・範囲
     */
	@TableField("work_content")
	private String workContent;
    /**
     * 精算条件:1：上下割
2：中割
3：固定
 ← 記入例：140-200（36-58）/稼働時間200H以内想定
     */
	@TableField("accounting_pat_k")
	private String accountingPatK;
    /**
     * 給料支払区分:1：月給
2：時給
     */
	@TableField("salary_pay_k")
	private String salaryPayK;
    /**
     * 作業時間（上限・H）
     */
	@TableField("wh_max")
	private BigDecimal whMax;
    /**
     * 作業時間（下限・H）
     */
	@TableField("wh_min")
	private BigDecimal whMin;
    /**
     * 中割時間
     */
	@TableField("wh_mid")
	private BigDecimal whMid;
    /**
     * 超過単価（円）
     */
	@TableField("excess_price")
	private BigDecimal excessPrice;
    /**
     * 控除単価（円）
     */
	@TableField("deducation_price")
	private BigDecimal deducationPrice;
    /**
     * 業務案件今月分の開始日
     */
	@TableField("pj_start_dt")
	private Date pjStartDt;
    /**
     * 業務案件今月分の終了日
     */
	@TableField("pj_end_dt")
	private Date pjEndDt;
    /**
     * 作業時間単位
     */
	@TableField("work_time_unit_k")
	private String workTimeUnitK;
    /**
     * 工数（契約用）
     */
	@TableField("contract_work_month")
	private BigDecimal contractWorkMonth;
    /**
     * 案件分類名称
     */
	@TableField("pj_class_nm")
	private String pjClassNm;
    /**
     * 出勤時刻
     */
	@JSONField(format="HH:mm")
	@TableField("work_start_time")
	private LocalDateTime workStartTime;
    /**
     * 退勤時刻
     */
	@JSONField(format="HH:mm")
	@TableField("work_end_time")
	private LocalDateTime workEndTime;
    /**
     * 休憩時間（分）
     */
	@TableField("break_time_min")
	private Integer breakTimeMin;
    /**
     * 支払サイト/出金日付の日
     */
	@TableField("payment_plan_d")
	private Integer paymentPlanD;
    /**
     * 支払サイト/締日区分
     */
	@TableField("payment_due_k")
	private String paymentDueK;
    /**
     * 支払サイト/支払月区分
     */
	@TableField("pay_month_k")
	private String payMonthK;
    /**
     * 支払サイト/支払日付の日
     */
	@TableField("pay_plan_d")
	private String payPlanD;
    /**
     * 支払サイト/出金予定日
     */
	@TableField("salary_pay_plan_dt")
	private Integer salaryPayPlanDt;
    /**
     * 所属会社ID
     */
	@TableField("bp_comp_id")
	private String bpCompId;
    /**
     * 所属会社名/社員氏名
     */
	@TableField("bp_comp_emp_nm")
	private String bpCompEmpNm;
    /**
     * 所属会社URL
     */
	@TableField("bp_comp_url")
	private String bpCompUrl;
    /**
     * 所属会社法人マイナンバー
     */
	@TableField("bp_my_number")
	private String bpMyNumber;
    /**
     * 所属会社郵便番号
     */
	@TableField("bp_post_no")
	private String bpPostNo;
    /**
     * 所属会社連絡先住所
     */
	@TableField("bp_addr")
	private String bpAddr;
    /**
     * 所属会社電話番号
     */
	@TableField("bp_tel_no")
	private String bpTelNo;
    /**
     * 所属会社FAX番号
     */
	@TableField("bp_fax_no")
	private String bpFaxNo;
    /**
     * 所属会社担当者名
     */
	@TableField("bp_staff_nm")
	private String bpStaffNm;
    /**
     * 所属会社担当者名（フリガナ）
     */
	@TableField("bp_staff_nm_fuli")
	private String bpStaffNmFuli;
    /**
     * 所属会社担当者所属部署
     */
	@TableField("bp_staff_dept_nm")
	private String bpStaffDeptNm;
    /**
     * 所属会社担当者役職
     */
	@TableField("bp_staff_position")
	private String bpStaffPosition;
    /**
     * 所属会社担当者電話番号
     */
	@TableField("bp_staff_tel_no")
	private String bpStaffTelNo;
    /**
     * 所属会社担当者FAX番号
     */
	@TableField("bp_staff_fax_no")
	private String bpStaffFaxNo;
    /**
     * 所属会社担当者携帯番号
     */
	@TableField("bp_staff_mobile_no")
	private String bpStaffMobileNo;
    /**
     * 所属会社担当者個人メール
     */
	@TableField("bp_staff_mail_addr")
	private String bpStaffMailAddr;
    /**
     * 所属会社担当者個人メールCC
     */
	@TableField("bp_staff_mail_addr_cc")
	private String bpStaffMailAddrCc;
    /**
     * 要員住所
     */
	@TableField("staff_addr")
	private String staffAddr;
    /**
     * 要員郵便番号
     */
	@TableField("staff_post_no")
	private String staffPostNo;
    /**
     * 要員メールアドレス
     */
	@TableField("staff_mail_addr")
	private String staffMailAddr;
    /**
     * 要員電話番号
     */
	@TableField("staff_tel_no")
	private String staffTelNo;
    /**
     * 要員携帯電話番号
     */
	@TableField("staff_mobile_no")
	private String staffMobileNo;
    /**
     * 精算条件（BP）:1：上下割
2：中割
3：固定
 ← 記入例：140-200（36-58）/稼働時間200H以内想定
     */
	@TableField("bp_accounting_pat_k")
	private String bpAccountingPatK;
    /**
     * 給料支払区分（BP）:1：月給
2：時給
     */
	@TableField("bp_salary_pay_k")
	private String bpSalaryPayK;
    /**
     * 作業時間（上限・H）（BP）
     */
	@TableField("bp_wh_max")
	private BigDecimal bpWhMax;
    /**
     * 作業時間（下限・H）（BP）
     */
	@TableField("bp_wh_min")
	private BigDecimal bpWhMin;
    /**
     * 中割時間（BP）
     */
	@TableField("bp_wh_mid")
	private BigDecimal bpWhMid;
    /**
     * 超過単価（円）（BP）
     */
	@TableField("bp_excess_price")
	private BigDecimal bpExcessPrice;
    /**
     * 控除単価（円）（BP）
     */
	@TableField("bp_deducation_price")
	private BigDecimal bpDeducationPrice;
    /**
     * 作業時間単位（BP）
     */
	@TableField("bp_work_unit_k")
	private String bpWorkUnitK;
    /**
     * 出勤時刻（BP）
     */
	@JSONField(format="HH:mm")
	@TableField("bp_work_start_time")
	private LocalDateTime bpWorkStartTime;
    /**
     * 退勤時刻（BP）
     */
	@JSONField(format="HH:mm")
	@TableField("bp_work_end_time")
	private LocalDateTime bpWorkEndTime;
    /**
     * 休憩時間（分）（BP）
     */
	@TableField("bp_break_time_min")
	private Integer bpBreakTimeMin;
    /**
     * 支払サイト/出金日付の日（BP）
     */
	@TableField("bp_payment_plan_d")
	private Integer bpPaymentPlanD;
    /**
     * 支払サイト/締日区分（BP）
     */
	@TableField("bp_payment_due_k")
	private String bpPaymentDueK;
    /**
     * 支払サイト/支払月区分（BP）
     */
	@TableField("bp_pay_month_k")
	private String bpPayMonthK;
    /**
     * 支払サイト/支払日付の日（BP）
     */
	@TableField("bp_pay_plan_d")
	private String bpPayPlanD;
    /**
     * 支払サイト/出金予定日（BP）
     */
	@TableField("bp_salary_pay_plan_dt")
	private Integer bpSalaryPayPlanDt;
    /**
     * 月次処理区分:1：通常締結
2：EDI
     */
	@TableField("mon_pro_k")
	private String monProK;
    /**
     * 作業報告書F:1：指定なし
2：客先F
     */
	@TableField("work_rep_f")
	private String workRepF;
    /**
     * 請求書F:1：指定なし
2：客先F
     */
	@TableField("bill_f")
	private String billF;
    /**
     * 作業報告書締め区分:1：指定なし
2：第1営業日
3：第2営業日
4：第3営業日
5：第4-5営業日
     */
	@TableField("work_rep_dl_k")
	private String workRepDlK;
    /**
     * 請求書締め区分:1：指定なし
2：第1営業日
3：第2営業日
4：第3営業日
5：第4-5営業日
     */
	@TableField("bill_dl_k")
	private String billDlK;
    /**
     * 添付資料F:1：有
2：無
     */
	@TableField("mon_attach_file_k")
	private String monAttachFileK;
    /**
     * 添付ファイルID
     */
	@TableField("mon_attach_file_id")
	private Integer monAttachFileId;
    /**
     * 手続き方法
     */
	@TableField("procedure_method_k")
	private String procedureMethodK;
    /**
     * 退社理由
     */
	@TableField("withdrawal_reason_k")
	private String withdrawalReasonK;
    /**
     * 離職票の有無
     */
	@TableField("withdrawal_letter_k")
	private String withdrawalLetterK;
    /**
     * 社保任意継続
     */
	@TableField("insurance_con_k")
	private String insuranceConK;
    /**
     * 必要書類
     */
	@TableField("withdrawal_req_doc")
	private String withdrawalReqDoc;
    /**
     * 手続き日付
     */
	@TableField("procedure_dt")
	private Date procedureDt;
    /**
     * 退社日
     */
	@TableField("withdrawal_dt")
	private Date withdrawalDt;
    /**
     * 退社後郵便番号
     */
	@TableField("after_withdrawal_post_no")
	private String afterWithdrawalPostNo;
    /**
     * 退社後住所
     */
	@TableField("after_withdrawal_addr")
	private String afterWithdrawalAddr;
    /**
     * 連絡事項（購買へ）
     */
	@TableField("purchase_remark")
	private String purchaseRemark;
    /**
     * 商流記載
     */
	@TableField("commercial_dist_remark")
	private String commercialDistRemark;
    /**
     * 特記事項（要員）
     */
	@TableField("emp_remark")
	private String empRemark;
	
	/**
	 * 個人情報変更Flg:Y：変更あり、N：変更なし
	 */
	@TableField("emp_info_chg_flg")
	private String empInfoChgFlg;
	
	/**
	 * 手続き区分:1:新規入社処理 2:契約期間更新処理 3:退社処理'
	 */
	@TableField("procedure_k")
	private String procedureK;

    /**
     * 役職手当金額
     */
	@TableField("appoint_allow_amt")
	private BigDecimal appointAllowAmt;
    /**
     * 住宅手当金額
     */
	@TableField("house_allow_amt")
	private BigDecimal houseAllowAmt;
    /**
     * 通信手当金額
     */
	@TableField("communication_allow_amt")
	private BigDecimal communicationAllowAmt;
    /**
     * 現場手当金額
     */
	@TableField("site_allow_amt")
	private BigDecimal siteAllowAmt;
    /**
     * 残業手当金額
     */
	@TableField("overwork_allow_amt")
	private BigDecimal overworkAllowAmt;
    /**
     * そのた手当1金額
     */
	@TableField("other_allow1_amt")
	private BigDecimal otherAllow1Amt;
    /**
     * そのた手当２金額
     */
	@TableField("other_allow2_amt")
	private BigDecimal otherAllow2Amt;

    /**
     * 月次請求ステータス:1:作業報告○ 2:作業報告× 3:作業報告▲ 4:請求完了
     */
    @TableField("mon_req_st")
    private String monReqSt;
    /**
     * 月次請求添付資料備考
     */
    @TableField("mon_attach_file_remark")
    private String monAttachFileRemark;
    /**
     * 月次請求補足備考
     */
    @TableField("mon_add_remark")
    private String monAddRemark;
	
    /**
     * 登録者
     */
	@TableField(value="sys_ins_user_id", fill = FieldFill.INSERT)
	private String sysInsUserId;
    /**
     * 登録日時
     */
	@TableField(value="sys_ins_dt", fill = FieldFill.INSERT)
	private Date sysInsDt;
    /**
     * 登録機能ID
     */
	@TableField(value="sys_ins_pg_id", fill = FieldFill.INSERT)
	private String sysInsPgId;
    /**
     * 更新者
     */
	@TableField(value="sys_upd_user_id", fill = FieldFill.INSERT_UPDATE)
	private String sysUpdUserId;
    /**
     * 更新日時
     */
	@TableField(value="sys_upd_dt", fill = FieldFill.INSERT_UPDATE)
	private Date sysUpdDt;
    /**
     * 更新機能ID
     */
	@TableField(value="sys_upd_pg_id", fill = FieldFill.INSERT_UPDATE)
	private String sysUpdPgId;


	public Integer getApprDocId() {
		return apprDocId;
	}

	public void setApprDocId(Integer apprDocId) {
		this.apprDocId = apprDocId;
	}

	public String getApprDocNo() {
		return apprDocNo;
	}

	public void setApprDocNo(String apprDocNo) {
		this.apprDocNo = apprDocNo;
	}

	public String getUseFlg() {
		return useFlg;
	}

	public void setUseFlg(String useFlg) {
		this.useFlg = useFlg;
	}

	public Date getApprDocDtFrom() {
		return apprDocDtFrom;
	}

	public void setApprDocDtFrom(Date apprDocDtFrom) {
		this.apprDocDtFrom = apprDocDtFrom;
	}

	public Date getApprDocDtTo() {
		return apprDocDtTo;
	}

	public void setApprDocDtTo(Date apprDocDtTo) {
		this.apprDocDtTo = apprDocDtTo;
	}

	public String getApprDocCreateK() {
		return apprDocCreateK;
	}

	public void setApprDocCreateK(String apprDocCreateK) {
		this.apprDocCreateK = apprDocCreateK;
	}

	public Date getApprDocCreateDt() {
		return apprDocCreateDt;
	}

	public void setApprDocCreateDt(Date apprDocCreateDt) {
		this.apprDocCreateDt = apprDocCreateDt;
	}

	public String getApprDocSt() {
		return apprDocSt;
	}

	public void setApprDocSt(String apprDocSt) {
		this.apprDocSt = apprDocSt;
	}

	public String getBusinessMainUserId() {
		return businessMainUserId;
	}

	public void setBusinessMainUserId(String businessMainUserId) {
		this.businessMainUserId = businessMainUserId;
	}

	public String getBusinessSubUserId() {
		return businessSubUserId;
	}

	public void setBusinessSubUserId(String businessSubUserId) {
		this.businessSubUserId = businessSubUserId;
	}

	public String getDeptCd() {
		return deptCd;
	}

	public void setDeptCd(String deptCd) {
		this.deptCd = deptCd;
	}

	public String getEmpNo() {
		return empNo;
	}

	public void setEmpNo(String empNo) {
		this.empNo = empNo;
	}

	public String getEmpNm() {
		return empNm;
	}

	public void setEmpNm(String empNm) {
		this.empNm = empNm;
	}

	public String getEmpNmFuli() {
		return empNmFuli;
	}

	public void setEmpNmFuli(String empNmFuli) {
		this.empNmFuli = empNmFuli;
	}

	public String getEmpSex() {
		return empSex;
	}

	public void setEmpSex(String empSex) {
		this.empSex = empSex;
	}

	public Date getEmpBirthday() {
		return empBirthday;
	}

	public void setEmpBirthday(Date empBirthday) {
		this.empBirthday = empBirthday;
	}

	public Date getEntranceDt() {
		return entranceDt;
	}

	public void setEntranceDt(Date entranceDt) {
		this.entranceDt = entranceDt;
	}

	public String getEmpMyNumber() {
		return empMyNumber;
	}

	public void setEmpMyNumber(String empMyNumber) {
		this.empMyNumber = empMyNumber;
	}

	public String getEmpPatK() {
		return empPatK;
	}

	public void setEmpPatK(String empPatK) {
		this.empPatK = empPatK;
	}

	public String getAffiliationK() {
		return affiliationK;
	}

	public void setAffiliationK(String affiliationK) {
		this.affiliationK = affiliationK;
	}

	public String getEntranceRecordK() {
		return entranceRecordK;
	}

	public void setEntranceRecordK(String entranceRecordK) {
		this.entranceRecordK = entranceRecordK;
	}

	public String getOldApprDocNo() {
		return oldApprDocNo;
	}

	public void setOldApprDocNo(String oldApprDocNo) {
		this.oldApprDocNo = oldApprDocNo;
	}

	public String getTakeOverK() {
		return takeOverK;
	}

	public void setTakeOverK(String takeOverK) {
		this.takeOverK = takeOverK;
	}

	public Date getOrderRecvHopeDt() {
		return orderRecvHopeDt;
	}

	public void setOrderRecvHopeDt(Date orderRecvHopeDt) {
		this.orderRecvHopeDt = orderRecvHopeDt;
	}

	public String getFromHomeStationNm() {
		return fromHomeStationNm;
	}

	public void setFromHomeStationNm(String fromHomeStationNm) {
		this.fromHomeStationNm = fromHomeStationNm;
	}

	public String getClientContractK() {
		return clientContractK;
	}

	public void setClientContractK(String clientContractK) {
		this.clientContractK = clientContractK;
	}

	public BigDecimal getAppointAllowRatio() {
		return appointAllowRatio;
	}

	public void setAppointAllowRatio(BigDecimal appointAllowRatio) {
		this.appointAllowRatio = appointAllowRatio;
	}

	public BigDecimal getHouseAllowRatio() {
		return houseAllowRatio;
	}

	public void setHouseAllowRatio(BigDecimal houseAllowRatio) {
		this.houseAllowRatio = houseAllowRatio;
	}

	public BigDecimal getCommunicationAllowRatio() {
		return communicationAllowRatio;
	}

	public void setCommunicationAllowRatio(BigDecimal communicationAllowRatio) {
		this.communicationAllowRatio = communicationAllowRatio;
	}

	public BigDecimal getSiteAllowRatio() {
		return siteAllowRatio;
	}

	public void setSiteAllowRatio(BigDecimal siteAllowRatio) {
		this.siteAllowRatio = siteAllowRatio;
	}

	public BigDecimal getOverworkAllowRatio() {
		return overworkAllowRatio;
	}

	public void setOverworkAllowRatio(BigDecimal overworkAllowRatio) {
		this.overworkAllowRatio = overworkAllowRatio;
	}

	public String getOtherAllow1Nm() {
		return otherAllow1Nm;
	}

	public void setOtherAllow1Nm(String otherAllow1Nm) {
		this.otherAllow1Nm = otherAllow1Nm;
	}

	public BigDecimal getOtherAllow1Ratio() {
		return otherAllow1Ratio;
	}

	public void setOtherAllow1Ratio(BigDecimal otherAllow1Ratio) {
		this.otherAllow1Ratio = otherAllow1Ratio;
	}

	public String getOtherAllow2Nm() {
		return otherAllow2Nm;
	}

	public void setOtherAllow2Nm(String otherAllow2Nm) {
		this.otherAllow2Nm = otherAllow2Nm;
	}

	public BigDecimal getOtherAllow2Ratio() {
		return otherAllow2Ratio;
	}

	public void setOtherAllow2Ratio(BigDecimal otherAllow2Ratio) {
		this.otherAllow2Ratio = otherAllow2Ratio;
	}

	public Date getSalesAddupMonth() {
		return salesAddupMonth;
	}

	public void setSalesAddupMonth(Date salesAddupMonth) {
		this.salesAddupMonth = salesAddupMonth;
	}

	public Integer getWorkDays() {
		return workDays;
	}

	public void setWorkDays(Integer workDays) {
		this.workDays = workDays;
	}

	public Date getBillingMonth() {
		return billingMonth;
	}

	public void setBillingMonth(Date billingMonth) {
		this.billingMonth = billingMonth;
	}

	public BigDecimal getSellPrice() {
		return sellPrice;
	}

	public void setSellPrice(BigDecimal sellPrice) {
		this.sellPrice = sellPrice;
	}

	public Integer getBusinessDays() {
		return businessDays;
	}

	public void setBusinessDays(Integer businessDays) {
		this.businessDays = businessDays;
	}

	public BigDecimal getCostPrice() {
		return costPrice;
	}

	public void setCostPrice(BigDecimal costPrice) {
		this.costPrice = costPrice;
	}

	public BigDecimal getTravelCostFirstMon() {
		return travelCostFirstMon;
	}

	public void setTravelCostFirstMon(BigDecimal travelCostFirstMon) {
		this.travelCostFirstMon = travelCostFirstMon;
	}

	public BigDecimal getOtherCostFirstMon() {
		return otherCostFirstMon;
	}

	public void setOtherCostFirstMon(BigDecimal otherCostFirstMon) {
		this.otherCostFirstMon = otherCostFirstMon;
	}

	public BigDecimal getTravelCost() {
		return travelCost;
	}

	public void setTravelCost(BigDecimal travelCost) {
		this.travelCost = travelCost;
	}

	public BigDecimal getInsuranceCost() {
		return insuranceCost;
	}

	public void setInsuranceCost(BigDecimal insuranceCost) {
		this.insuranceCost = insuranceCost;
	}

	public BigDecimal getOtherCost() {
		return otherCost;
	}

	public void setOtherCost(BigDecimal otherCost) {
		this.otherCost = otherCost;
	}

	public String getInsuranceK() {
		return insuranceK;
	}

	public void setInsuranceK(String insuranceK) {
		this.insuranceK = insuranceK;
	}

	public Date getInsuranceJoinYmd() {
		return insuranceJoinYmd;
	}

	public void setInsuranceJoinYmd(Date insuranceJoinYmd) {
		this.insuranceJoinYmd = insuranceJoinYmd;
	}

	public String getClientNo() {
		return clientNo;
	}

	public void setClientNo(String clientNo) {
		this.clientNo = clientNo;
	}

	public String getClientNm() {
		return clientNm;
	}

	public void setClientNm(String clientNm) {
		this.clientNm = clientNm;
	}

	public String getClientUrl() {
		return clientUrl;
	}

	public void setClientUrl(String clientUrl) {
		this.clientUrl = clientUrl;
	}

	public String getClientMyNumber() {
		return clientMyNumber;
	}

	public void setClientMyNumber(String clientMyNumber) {
		this.clientMyNumber = clientMyNumber;
	}

	public String getClientPostNo() {
		return clientPostNo;
	}

	public void setClientPostNo(String clientPostNo) {
		this.clientPostNo = clientPostNo;
	}

	public String getClientAddr() {
		return clientAddr;
	}

	public void setClientAddr(String clientAddr) {
		this.clientAddr = clientAddr;
	}

	public String getClientTelNo() {
		return clientTelNo;
	}

	public void setClientTelNo(String clientTelNo) {
		this.clientTelNo = clientTelNo;
	}

	public String getClientFaxNo() {
		return clientFaxNo;
	}

	public void setClientFaxNo(String clientFaxNo) {
		this.clientFaxNo = clientFaxNo;
	}

	public String getClientStaffNm() {
		return clientStaffNm;
	}

	public void setClientStaffNm(String clientStaffNm) {
		this.clientStaffNm = clientStaffNm;
	}

	public String getClientStaffNmFuli() {
		return clientStaffNmFuli;
	}

	public void setClientStaffNmFuli(String clientStaffNmFuli) {
		this.clientStaffNmFuli = clientStaffNmFuli;
	}

	public String getClientStaffDeptNm() {
		return clientStaffDeptNm;
	}

	public void setClientStaffDeptNm(String clientStaffDeptNm) {
		this.clientStaffDeptNm = clientStaffDeptNm;
	}

	public String getClientStaffPosition() {
		return clientStaffPosition;
	}

	public void setClientStaffPosition(String clientStaffPosition) {
		this.clientStaffPosition = clientStaffPosition;
	}

	public String getClientStaffMobileNo() {
		return clientStaffMobileNo;
	}

	public void setClientStaffMobileNo(String clientStaffMobileNo) {
		this.clientStaffMobileNo = clientStaffMobileNo;
	}

	public String getClientMailAddr() {
		return clientMailAddr;
	}

	public void setClientMailAddr(String clientMailAddr) {
		this.clientMailAddr = clientMailAddr;
	}

	public String getClientMailAddrCc() {
		return clientMailAddrCc;
	}

	public void setClientMailAddrCc(String clientMailAddrCc) {
		this.clientMailAddrCc = clientMailAddrCc;
	}

	public String getClientReqStaffNm() {
		return clientReqStaffNm;
	}

	public void setClientReqStaffNm(String clientReqStaffNm) {
		this.clientReqStaffNm = clientReqStaffNm;
	}

	public String getClientReqStaffNmFuli() {
		return clientReqStaffNmFuli;
	}

	public void setClientReqStaffNmFuli(String clientReqStaffNmFuli) {
		this.clientReqStaffNmFuli = clientReqStaffNmFuli;
	}

	public String getClientReqStaffDeptNm() {
		return clientReqStaffDeptNm;
	}

	public void setClientReqStaffDeptNm(String clientReqStaffDeptNm) {
		this.clientReqStaffDeptNm = clientReqStaffDeptNm;
	}

	public String getClientReqStaffPosition() {
		return clientReqStaffPosition;
	}

	public void setClientReqStaffPosition(String clientReqStaffPosition) {
		this.clientReqStaffPosition = clientReqStaffPosition;
	}

	public String getClientReqTelNo() {
		return clientReqTelNo;
	}

	public void setClientReqTelNo(String clientReqTelNo) {
		this.clientReqTelNo = clientReqTelNo;
	}

	public String getClientReqFaxNo() {
		return clientReqFaxNo;
	}

	public void setClientReqFaxNo(String clientReqFaxNo) {
		this.clientReqFaxNo = clientReqFaxNo;
	}

	public String getClientReqStaffMobileNo() {
		return clientReqStaffMobileNo;
	}

	public void setClientReqStaffMobileNo(String clientReqStaffMobileNo) {
		this.clientReqStaffMobileNo = clientReqStaffMobileNo;
	}

	public String getClientReqMailAddr() {
		return clientReqMailAddr;
	}

	public void setClientReqMailAddr(String clientReqMailAddr) {
		this.clientReqMailAddr = clientReqMailAddr;
	}

	public String getClientReqMailAddrCc() {
		return clientReqMailAddrCc;
	}

	public void setClientReqMailAddrCc(String clientReqMailAddrCc) {
		this.clientReqMailAddrCc = clientReqMailAddrCc;
	}

	public String getClientReqPostNo() {
		return clientReqPostNo;
	}

	public void setClientReqPostNo(String clientReqPostNo) {
		this.clientReqPostNo = clientReqPostNo;
	}

	public String getClientReqAddr() {
		return clientReqAddr;
	}

	public void setClientReqAddr(String clientReqAddr) {
		this.clientReqAddr = clientReqAddr;
	}

	public String getClientOrderNo() {
		return clientOrderNo;
	}

	public void setClientOrderNo(String clientOrderNo) {
		this.clientOrderNo = clientOrderNo;
	}

	public String getPjCd() {
		return pjCd;
	}

	public void setPjCd(String pjCd) {
		this.pjCd = pjCd;
	}

	public String getPjNm() {
		return pjNm;
	}

	public void setPjNm(String pjNm) {
		this.pjNm = pjNm;
	}

	public String getWorkPlace() {
		return workPlace;
	}

	public void setWorkPlace(String workPlace) {
		this.workPlace = workPlace;
	}

	public String getWorkContent() {
		return workContent;
	}

	public void setWorkContent(String workContent) {
		this.workContent = workContent;
	}

	public String getAccountingPatK() {
		return accountingPatK;
	}

	public void setAccountingPatK(String accountingPatK) {
		this.accountingPatK = accountingPatK;
	}

	public String getSalaryPayK() {
		return salaryPayK;
	}

	public void setSalaryPayK(String salaryPayK) {
		this.salaryPayK = salaryPayK;
	}

	public BigDecimal getWhMax() {
		return whMax;
	}

	public void setWhMax(BigDecimal whMax) {
		this.whMax = whMax;
	}

	public BigDecimal getWhMin() {
		return whMin;
	}

	public void setWhMin(BigDecimal whMin) {
		this.whMin = whMin;
	}

	public BigDecimal getWhMid() {
		return whMid;
	}

	public void setWhMid(BigDecimal whMid) {
		this.whMid = whMid;
	}

	public BigDecimal getExcessPrice() {
		return excessPrice;
	}

	public void setExcessPrice(BigDecimal excessPrice) {
		this.excessPrice = excessPrice;
	}

	public BigDecimal getDeducationPrice() {
		return deducationPrice;
	}

	public void setDeducationPrice(BigDecimal deducationPrice) {
		this.deducationPrice = deducationPrice;
	}

	public Date getPjStartDt() {
		return pjStartDt;
	}

	public void setPjStartDt(Date pjStartDt) {
		this.pjStartDt = pjStartDt;
	}

	public Date getPjEndDt() {
		return pjEndDt;
	}

	public void setPjEndDt(Date pjEndDt) {
		this.pjEndDt = pjEndDt;
	}

	public String getWorkTimeUnitK() {
		return workTimeUnitK;
	}

	public void setWorkTimeUnitK(String workTimeUnitK) {
		this.workTimeUnitK = workTimeUnitK;
	}

	public BigDecimal getContractWorkMonth() {
		return contractWorkMonth;
	}

	public void setContractWorkMonth(BigDecimal contractWorkMonth) {
		this.contractWorkMonth = contractWorkMonth;
	}

	public String getPjClassNm() {
		return pjClassNm;
	}

	public void setPjClassNm(String pjClassNm) {
		this.pjClassNm = pjClassNm;
	}

	public LocalDateTime getWorkStartTime() {
		return workStartTime;
	}

	public void setWorkStartTime(LocalDateTime workStartTime) {
		this.workStartTime = workStartTime;
	}

	public LocalDateTime getWorkEndTime() {
		return workEndTime;
	}

	public void setWorkEndTime(LocalDateTime workEndTime) {
		this.workEndTime = workEndTime;
	}

	public Integer getBreakTimeMin() {
		return breakTimeMin;
	}

	public void setBreakTimeMin(Integer breakTimeMin) {
		this.breakTimeMin = breakTimeMin;
	}

	public Integer getPaymentPlanD() {
		return paymentPlanD;
	}

	public void setPaymentPlanD(Integer paymentPlanD) {
		this.paymentPlanD = paymentPlanD;
	}

	public String getPaymentDueK() {
		return paymentDueK;
	}

	public void setPaymentDueK(String paymentDueK) {
		this.paymentDueK = paymentDueK;
	}

	public String getPayMonthK() {
		return payMonthK;
	}

	public void setPayMonthK(String payMonthK) {
		this.payMonthK = payMonthK;
	}

	public String getPayPlanD() {
		return payPlanD;
	}

	public void setPayPlanD(String payPlanD) {
		this.payPlanD = payPlanD;
	}

	public Integer getSalaryPayPlanDt() {
		return salaryPayPlanDt;
	}

	public void setSalaryPayPlanDt(Integer salaryPayPlanDt) {
		this.salaryPayPlanDt = salaryPayPlanDt;
	}

	public String getBpCompId() {
		return bpCompId;
	}

	public void setBpCompId(String bpCompId) {
		this.bpCompId = bpCompId;
	}

	public String getBpCompEmpNm() {
		return bpCompEmpNm;
	}

	public void setBpCompEmpNm(String bpCompEmpNm) {
		this.bpCompEmpNm = bpCompEmpNm;
	}

	public String getBpCompUrl() {
		return bpCompUrl;
	}

	public void setBpCompUrl(String bpCompUrl) {
		this.bpCompUrl = bpCompUrl;
	}

	public String getBpMyNumber() {
		return bpMyNumber;
	}

	public void setBpMyNumber(String bpMyNumber) {
		this.bpMyNumber = bpMyNumber;
	}

	public String getBpPostNo() {
		return bpPostNo;
	}

	public void setBpPostNo(String bpPostNo) {
		this.bpPostNo = bpPostNo;
	}

	public String getBpAddr() {
		return bpAddr;
	}

	public void setBpAddr(String bpAddr) {
		this.bpAddr = bpAddr;
	}

	public String getBpTelNo() {
		return bpTelNo;
	}

	public void setBpTelNo(String bpTelNo) {
		this.bpTelNo = bpTelNo;
	}

	public String getBpFaxNo() {
		return bpFaxNo;
	}

	public void setBpFaxNo(String bpFaxNo) {
		this.bpFaxNo = bpFaxNo;
	}

	public String getBpStaffNm() {
		return bpStaffNm;
	}

	public void setBpStaffNm(String bpStaffNm) {
		this.bpStaffNm = bpStaffNm;
	}

	public String getBpStaffNmFuli() {
		return bpStaffNmFuli;
	}

	public void setBpStaffNmFuli(String bpStaffNmFuli) {
		this.bpStaffNmFuli = bpStaffNmFuli;
	}

	public String getBpStaffDeptNm() {
		return bpStaffDeptNm;
	}

	public void setBpStaffDeptNm(String bpStaffDeptNm) {
		this.bpStaffDeptNm = bpStaffDeptNm;
	}

	public String getBpStaffPosition() {
		return bpStaffPosition;
	}

	public void setBpStaffPosition(String bpStaffPosition) {
		this.bpStaffPosition = bpStaffPosition;
	}

	public String getBpStaffTelNo() {
		return bpStaffTelNo;
	}

	public void setBpStaffTelNo(String bpStaffTelNo) {
		this.bpStaffTelNo = bpStaffTelNo;
	}

	public String getBpStaffFaxNo() {
		return bpStaffFaxNo;
	}

	public void setBpStaffFaxNo(String bpStaffFaxNo) {
		this.bpStaffFaxNo = bpStaffFaxNo;
	}

	public String getBpStaffMobileNo() {
		return bpStaffMobileNo;
	}

	public void setBpStaffMobileNo(String bpStaffMobileNo) {
		this.bpStaffMobileNo = bpStaffMobileNo;
	}

	public String getBpStaffMailAddr() {
		return bpStaffMailAddr;
	}

	public void setBpStaffMailAddr(String bpStaffMailAddr) {
		this.bpStaffMailAddr = bpStaffMailAddr;
	}

	public String getBpStaffMailAddrCc() {
		return bpStaffMailAddrCc;
	}

	public void setBpStaffMailAddrCc(String bpStaffMailAddrCc) {
		this.bpStaffMailAddrCc = bpStaffMailAddrCc;
	}

	public String getStaffAddr() {
		return staffAddr;
	}

	public void setStaffAddr(String staffAddr) {
		this.staffAddr = staffAddr;
	}

	public String getStaffPostNo() {
		return staffPostNo;
	}

	public void setStaffPostNo(String staffPostNo) {
		this.staffPostNo = staffPostNo;
	}

	public String getStaffMailAddr() {
		return staffMailAddr;
	}

	public void setStaffMailAddr(String staffMailAddr) {
		this.staffMailAddr = staffMailAddr;
	}

	public String getStaffTelNo() {
		return staffTelNo;
	}

	public void setStaffTelNo(String staffTelNo) {
		this.staffTelNo = staffTelNo;
	}

	public String getStaffMobileNo() {
		return staffMobileNo;
	}

	public void setStaffMobileNo(String staffMobileNo) {
		this.staffMobileNo = staffMobileNo;
	}

	public String getBpAccountingPatK() {
		return bpAccountingPatK;
	}

	public void setBpAccountingPatK(String bpAccountingPatK) {
		this.bpAccountingPatK = bpAccountingPatK;
	}

	public String getBpSalaryPayK() {
		return bpSalaryPayK;
	}

	public void setBpSalaryPayK(String bpSalaryPayK) {
		this.bpSalaryPayK = bpSalaryPayK;
	}

	public BigDecimal getBpWhMax() {
		return bpWhMax;
	}

	public void setBpWhMax(BigDecimal bpWhMax) {
		this.bpWhMax = bpWhMax;
	}

	public BigDecimal getBpWhMin() {
		return bpWhMin;
	}

	public void setBpWhMin(BigDecimal bpWhMin) {
		this.bpWhMin = bpWhMin;
	}

	public BigDecimal getBpWhMid() {
		return bpWhMid;
	}

	public void setBpWhMid(BigDecimal bpWhMid) {
		this.bpWhMid = bpWhMid;
	}

	public BigDecimal getBpExcessPrice() {
		return bpExcessPrice;
	}

	public void setBpExcessPrice(BigDecimal bpExcessPrice) {
		this.bpExcessPrice = bpExcessPrice;
	}

	public BigDecimal getBpDeducationPrice() {
		return bpDeducationPrice;
	}

	public void setBpDeducationPrice(BigDecimal bpDeducationPrice) {
		this.bpDeducationPrice = bpDeducationPrice;
	}

	public String getBpWorkUnitK() {
		return bpWorkUnitK;
	}

	public void setBpWorkUnitK(String bpWorkUnitK) {
		this.bpWorkUnitK = bpWorkUnitK;
	}

	public LocalDateTime getBpWorkStartTime() {
		return bpWorkStartTime;
	}

	public void setBpWorkStartTime(LocalDateTime bpWorkStartTime) {
		this.bpWorkStartTime = bpWorkStartTime;
	}

	public LocalDateTime getBpWorkEndTime() {
		return bpWorkEndTime;
	}

	public void setBpWorkEndTime(LocalDateTime bpWorkEndTime) {
		this.bpWorkEndTime = bpWorkEndTime;
	}

	public Integer getBpBreakTimeMin() {
		return bpBreakTimeMin;
	}

	public void setBpBreakTimeMin(Integer bpBreakTimeMin) {
		this.bpBreakTimeMin = bpBreakTimeMin;
	}

	public Integer getBpPaymentPlanD() {
		return bpPaymentPlanD;
	}

	public void setBpPaymentPlanD(Integer bpPaymentPlanD) {
		this.bpPaymentPlanD = bpPaymentPlanD;
	}

	public String getBpPaymentDueK() {
		return bpPaymentDueK;
	}

	public void setBpPaymentDueK(String bpPaymentDueK) {
		this.bpPaymentDueK = bpPaymentDueK;
	}

	public String getBpPayMonthK() {
		return bpPayMonthK;
	}

	public void setBpPayMonthK(String bpPayMonthK) {
		this.bpPayMonthK = bpPayMonthK;
	}

	public String getBpPayPlanD() {
		return bpPayPlanD;
	}

	public void setBpPayPlanD(String bpPayPlanD) {
		this.bpPayPlanD = bpPayPlanD;
	}

	public Integer getBpSalaryPayPlanDt() {
		return bpSalaryPayPlanDt;
	}

	public void setBpSalaryPayPlanDt(Integer bpSalaryPayPlanDt) {
		this.bpSalaryPayPlanDt = bpSalaryPayPlanDt;
	}

	public String getMonProK() {
		return monProK;
	}

	public void setMonProK(String monProK) {
		this.monProK = monProK;
	}

	public String getWorkRepF() {
		return workRepF;
	}

	public void setWorkRepF(String workRepF) {
		this.workRepF = workRepF;
	}

	public String getBillF() {
		return billF;
	}

	public void setBillF(String billF) {
		this.billF = billF;
	}

	public String getWorkRepDlK() {
		return workRepDlK;
	}

	public void setWorkRepDlK(String workRepDlK) {
		this.workRepDlK = workRepDlK;
	}

	public String getBillDlK() {
		return billDlK;
	}

	public void setBillDlK(String billDlK) {
		this.billDlK = billDlK;
	}

	public String getMonAttachFileK() {
		return monAttachFileK;
	}

	public void setMonAttachFileK(String monAttachFileK) {
		this.monAttachFileK = monAttachFileK;
	}

	public Integer getMonAttachFileId() {
		return monAttachFileId;
	}

	public void setMonAttachFileId(Integer monAttachFileId) {
		this.monAttachFileId = monAttachFileId;
	}

	public String getProcedureMethodK() {
		return procedureMethodK;
	}

	public void setProcedureMethodK(String procedureMethodK) {
		this.procedureMethodK = procedureMethodK;
	}

	public String getWithdrawalReasonK() {
		return withdrawalReasonK;
	}

	public void setWithdrawalReasonK(String withdrawalReasonK) {
		this.withdrawalReasonK = withdrawalReasonK;
	}

	public String getWithdrawalLetterK() {
		return withdrawalLetterK;
	}

	public void setWithdrawalLetterK(String withdrawalLetterK) {
		this.withdrawalLetterK = withdrawalLetterK;
	}

	public String getInsuranceConK() {
		return insuranceConK;
	}

	public void setInsuranceConK(String insuranceConK) {
		this.insuranceConK = insuranceConK;
	}

	public String getWithdrawalReqDoc() {
		return withdrawalReqDoc;
	}

	public void setWithdrawalReqDoc(String withdrawalReqDoc) {
		this.withdrawalReqDoc = withdrawalReqDoc;
	}

	public Date getProcedureDt() {
		return procedureDt;
	}

	public void setProcedureDt(Date procedureDt) {
		this.procedureDt = procedureDt;
	}

	public Date getWithdrawalDt() {
		return withdrawalDt;
	}

	public void setWithdrawalDt(Date withdrawalDt) {
		this.withdrawalDt = withdrawalDt;
	}

	public String getAfterWithdrawalPostNo() {
		return afterWithdrawalPostNo;
	}

	public void setAfterWithdrawalPostNo(String afterWithdrawalPostNo) {
		this.afterWithdrawalPostNo = afterWithdrawalPostNo;
	}

	public String getAfterWithdrawalAddr() {
		return afterWithdrawalAddr;
	}

	public void setAfterWithdrawalAddr(String afterWithdrawalAddr) {
		this.afterWithdrawalAddr = afterWithdrawalAddr;
	}

	public String getPurchaseRemark() {
		return purchaseRemark;
	}

	public void setPurchaseRemark(String purchaseRemark) {
		this.purchaseRemark = purchaseRemark;
	}

	public String getCommercialDistRemark() {
		return commercialDistRemark;
	}

	public void setCommercialDistRemark(String commercialDistRemark) {
		this.commercialDistRemark = commercialDistRemark;
	}

	public String getEmpRemark() {
		return empRemark;
	}

	public void setEmpRemark(String empRemark) {
		this.empRemark = empRemark;
	}

	public String getSysInsUserId() {
		return sysInsUserId;
	}

	public void setSysInsUserId(String sysInsUserId) {
		this.sysInsUserId = sysInsUserId;
	}

	public Date getSysInsDt() {
		return sysInsDt;
	}

	public void setSysInsDt(Date sysInsDt) {
		this.sysInsDt = sysInsDt;
	}

	public String getSysInsPgId() {
		return sysInsPgId;
	}

	public void setSysInsPgId(String sysInsPgId) {
		this.sysInsPgId = sysInsPgId;
	}

	public String getSysUpdUserId() {
		return sysUpdUserId;
	}

	public void setSysUpdUserId(String sysUpdUserId) {
		this.sysUpdUserId = sysUpdUserId;
	}

	public Date getSysUpdDt() {
		return sysUpdDt;
	}

	public void setSysUpdDt(Date sysUpdDt) {
		this.sysUpdDt = sysUpdDt;
	}

	public String getSysUpdPgId() {
		return sysUpdPgId;
	}

	public void setSysUpdPgId(String sysUpdPgId) {
		this.sysUpdPgId = sysUpdPgId;
	}

	public Date getSiteEntranceDt() {
		return siteEntranceDt;
	}

	public void setSiteEntranceDt(Date siteEntranceDt) {
		this.siteEntranceDt = siteEntranceDt;
	}

	public String getClientStaffTelNo() {
		return clientStaffTelNo;
	}

	public void setClientStaffTelNo(String clientStaffTelNo) {
		this.clientStaffTelNo = clientStaffTelNo;
	}

	public String getClientStaffFaxNo() {
		return clientStaffFaxNo;
	}

	public void setClientStaffFaxNo(String clientStaffFaxNo) {
		this.clientStaffFaxNo = clientStaffFaxNo;
	}

	public String getEmpInfoChgFlg() {
		return empInfoChgFlg;
	}

	public void setEmpInfoChgFlg(String empInfoChgFlg) {
		this.empInfoChgFlg = empInfoChgFlg;
	}

	public String getProcedureK() {
		return procedureK;
	}

	public void setProcedureK(String procedureK) {
		this.procedureK = procedureK;
	}

	public BigDecimal getAppointAllowAmt() {
		return appointAllowAmt;
	}

	public void setAppointAllowAmt(BigDecimal appointAllowAmt) {
		this.appointAllowAmt = appointAllowAmt;
	}

	public BigDecimal getHouseAllowAmt() {
		return houseAllowAmt;
	}

	public void setHouseAllowAmt(BigDecimal houseAllowAmt) {
		this.houseAllowAmt = houseAllowAmt;
	}

	public BigDecimal getCommunicationAllowAmt() {
		return communicationAllowAmt;
	}

	public void setCommunicationAllowAmt(BigDecimal communicationAllowAmt) {
		this.communicationAllowAmt = communicationAllowAmt;
	}

	public BigDecimal getSiteAllowAmt() {
		return siteAllowAmt;
	}

	public void setSiteAllowAmt(BigDecimal siteAllowAmt) {
		this.siteAllowAmt = siteAllowAmt;
	}

	public BigDecimal getOverworkAllowAmt() {
		return overworkAllowAmt;
	}

	public void setOverworkAllowAmt(BigDecimal overworkAllowAmt) {
		this.overworkAllowAmt = overworkAllowAmt;
	}

	public BigDecimal getOtherAllow1Amt() {
		return otherAllow1Amt;
	}

	public void setOtherAllow1Amt(BigDecimal otherAllow1Amt) {
		this.otherAllow1Amt = otherAllow1Amt;
	}

	public BigDecimal getOtherAllow2Amt() {
		return otherAllow2Amt;
	}

	public void setOtherAllow2Amt(BigDecimal otherAllow2Amt) {
		this.otherAllow2Amt = otherAllow2Amt;
	}

	public BigDecimal getSumContractWm() {
		return sumContractWm;
	}

	public void setSumContractWm(BigDecimal sumContractWm) {
		this.sumContractWm = sumContractWm;
	}
	

    public String getMonReqSt() {
        return monReqSt;
    }

    public void setMonReqSt(String monReqSt) {
        this.monReqSt = monReqSt;
    }

    public String getMonAttachFileRemark() {
        return monAttachFileRemark;
    }

    public void setMonAttachFileRemark(String monAttachFileRemark) {
        this.monAttachFileRemark = monAttachFileRemark;
    }

    public String getMonAddRemark() {
        return monAddRemark;
    }

    public void setMonAddRemark(String monAddRemark) {
        this.monAddRemark = monAddRemark;
    }

    @Override
    public Serializable pkVal() {
        return this.apprDocId;
    }

    @Override
    public String toString() {
        return "Apprdoc{" +
        "apprDocId=" + apprDocId +
        ", apprDocNo=" + apprDocNo +
        ", useFlg=" + useFlg +
        ", apprDocDtFrom=" + apprDocDtFrom +
        ", apprDocDtTo=" + apprDocDtTo +
        ", apprDocCreateK=" + apprDocCreateK +
        ", apprDocCreateDt=" + apprDocCreateDt +
        ", apprDocSt=" + apprDocSt +
        ", businessMainUserId=" + businessMainUserId +
        ", businessSubUserId=" + businessSubUserId +
        ", deptCd=" + deptCd +
        ", empNo=" + empNo +
        ", empNm=" + empNm +
        ", empNmFuli=" + empNmFuli +
        ", empSex=" + empSex +
        ", empBirthday=" + empBirthday +
        ", entranceDt=" + entranceDt +
        ", empMyNumber=" + empMyNumber +
        ", empPatK=" + empPatK +
        ", affiliationK=" + affiliationK +
        ", entranceRecordK=" + entranceRecordK +
        ", oldApprDocNo=" + oldApprDocNo +
        ", takeOverK=" + takeOverK +
        ", orderRecvHopeDt=" + orderRecvHopeDt +
        ", fromHomeStationNm=" + fromHomeStationNm +
        ", clientContractK=" + clientContractK +
        ", appointAllowRatio=" + appointAllowRatio +
        ", houseAllowRatio=" + houseAllowRatio +
        ", communicationAllowRatio=" + communicationAllowRatio +
        ", siteAllowRatio=" + siteAllowRatio +
        ", overworkAllowRatio=" + overworkAllowRatio +
        ", otherAllow1Nm=" + otherAllow1Nm +
        ", otherAllow1Ratio=" + otherAllow1Ratio +
        ", otherAllow2Nm=" + otherAllow2Nm +
        ", otherAllow2Ratio=" + otherAllow2Ratio +
        ", salesAddupMonth=" + salesAddupMonth +
        ", workDays=" + workDays +
        ", billingMonth=" + billingMonth +
        ", sellPrice=" + sellPrice +
        ", businessDays=" + businessDays +
        ", costPrice=" + costPrice +
        ", travelCostFirstMon=" + travelCostFirstMon +
        ", otherCostFirstMon=" + otherCostFirstMon +
        ", travelCost=" + travelCost +
        ", insuranceCost=" + insuranceCost +
        ", otherCost=" + otherCost +
        ", insuranceK=" + insuranceK +
        ", insuranceJoinYmd=" + insuranceJoinYmd +
        ", clientNo=" + clientNo +
        ", clientNm=" + clientNm +
        ", clientUrl=" + clientUrl +
        ", clientMyNumber=" + clientMyNumber +
        ", clientPostNo=" + clientPostNo +
        ", clientAddr=" + clientAddr +
        ", clientTelNo=" + clientTelNo +
        ", clientFaxNo=" + clientFaxNo +
        ", clientStaffNm=" + clientStaffNm +
        ", clientStaffNmFuli=" + clientStaffNmFuli +
        ", clientStaffDeptNm=" + clientStaffDeptNm +
        ", clientStaffPosition=" + clientStaffPosition +
        ", clientStaffMobileNo=" + clientStaffMobileNo +
        ", clientMailAddr=" + clientMailAddr +
        ", clientMailAddrCc=" + clientMailAddrCc +
        ", clientReqStaffNm=" + clientReqStaffNm +
        ", clientReqStaffNmFuli=" + clientReqStaffNmFuli +
        ", clientReqStaffDeptNm=" + clientReqStaffDeptNm +
        ", clientReqStaffPosition=" + clientReqStaffPosition +
        ", clientReqTelNo=" + clientReqTelNo +
        ", clientReqFaxNo=" + clientReqFaxNo +
        ", clientReqStaffMobileNo=" + clientReqStaffMobileNo +
        ", clientReqMailAddr=" + clientReqMailAddr +
        ", clientReqMailAddrCc=" + clientReqMailAddrCc +
        ", clientReqPostNo=" + clientReqPostNo +
        ", clientReqAddr=" + clientReqAddr +
        ", clientOrderNo=" + clientOrderNo +
        ", pjCd=" + pjCd +
        ", pjNm=" + pjNm +
        ", workPlace=" + workPlace +
        ", workContent=" + workContent +
        ", accountingPatK=" + accountingPatK +
        ", salaryPayK=" + salaryPayK +
        ", whMax=" + whMax +
        ", whMin=" + whMin +
        ", whMid=" + whMid +
        ", excessPrice=" + excessPrice +
        ", deducationPrice=" + deducationPrice +
        ", pjStartDt=" + pjStartDt +
        ", pjEndDt=" + pjEndDt +
        ", workTimeUnitK=" + workTimeUnitK +
        ", contractWorkMonth=" + contractWorkMonth +
        ", pjClassNm=" + pjClassNm +
        ", workStartTime=" + workStartTime +
        ", workEndTime=" + workEndTime +
        ", breakTimeMin=" + breakTimeMin +
        ", paymentPlanD=" + paymentPlanD +
        ", paymentDueK=" + paymentDueK +
        ", payMonthK=" + payMonthK +
        ", payPlanD=" + payPlanD +
        ", salaryPayPlanDt=" + salaryPayPlanDt +
        ", bpCompId=" + bpCompId +
        ", bpCompEmpNm=" + bpCompEmpNm +
        ", bpCompUrl=" + bpCompUrl +
        ", bpMyNumber=" + bpMyNumber +
        ", bpPostNo=" + bpPostNo +
        ", bpAddr=" + bpAddr +
        ", bpTelNo=" + bpTelNo +
        ", bpFaxNo=" + bpFaxNo +
        ", bpStaffNm=" + bpStaffNm +
        ", bpStaffNmFuli=" + bpStaffNmFuli +
        ", bpStaffDeptNm=" + bpStaffDeptNm +
        ", bpStaffPosition=" + bpStaffPosition +
        ", bpStaffTelNo=" + bpStaffTelNo +
        ", bpStaffFaxNo=" + bpStaffFaxNo +
        ", bpStaffMobileNo=" + bpStaffMobileNo +
        ", bpStaffMailAddr=" + bpStaffMailAddr +
        ", bpStaffMailAddrCc=" + bpStaffMailAddrCc +
        ", staffAddr=" + staffAddr +
        ", staffPostNo=" + staffPostNo +
        ", staffMailAddr=" + staffMailAddr +
        ", staffTelNo=" + staffTelNo +
        ", staffMobileNo=" + staffMobileNo +
        ", bpAccountingPatK=" + bpAccountingPatK +
        ", bpSalaryPayK=" + bpSalaryPayK +
        ", bpWhMax=" + bpWhMax +
        ", bpWhMin=" + bpWhMin +
        ", bpWhMid=" + bpWhMid +
        ", bpExcessPrice=" + bpExcessPrice +
        ", bpDeducationPrice=" + bpDeducationPrice +
        ", bpWorkUnitK=" + bpWorkUnitK +
        ", bpWorkStartTime=" + bpWorkStartTime +
        ", bpWorkEndTime=" + bpWorkEndTime +
        ", bpBreakTimeMin=" + bpBreakTimeMin +
        ", bpPaymentPlanD=" + bpPaymentPlanD +
        ", bpPaymentDueK=" + bpPaymentDueK +
        ", bpPayMonthK=" + bpPayMonthK +
        ", bpPayPlanD=" + bpPayPlanD +
        ", bpSalaryPayPlanDt=" + bpSalaryPayPlanDt +
        ", monProK=" + monProK +
        ", workRepF=" + workRepF +
        ", billF=" + billF +
        ", workRepDlK=" + workRepDlK +
        ", billDlK=" + billDlK +
        ", monAttachFileK=" + monAttachFileK +
        ", monAttachFileId=" + monAttachFileId +
        ", procedureMethodK=" + procedureMethodK +
        ", withdrawalReasonK=" + withdrawalReasonK +
        ", withdrawalLetterK=" + withdrawalLetterK +
        ", insuranceConK=" + insuranceConK +
        ", withdrawalReqDoc=" + withdrawalReqDoc +
        ", procedureDt=" + procedureDt +
        ", withdrawalDt=" + withdrawalDt +
        ", afterWithdrawalPostNo=" + afterWithdrawalPostNo +
        ", afterWithdrawalAddr=" + afterWithdrawalAddr +
        ", purchaseRemark=" + purchaseRemark +
        ", commercialDistRemark=" + commercialDistRemark +
        ", empRemark=" + empRemark +
        ", sysInsUserId=" + sysInsUserId +
        ", sysInsDt=" + sysInsDt +
        ", sysInsPgId=" + sysInsPgId +
        ", sysUpdUserId=" + sysUpdUserId +
        ", sysUpdDt=" + sysUpdDt +
        ", sysUpdPgId=" + sysUpdPgId +
        ", siteEntranceDt=" + siteEntranceDt +
        ", clientStaffTelNo=" + clientStaffTelNo +
        ", clientStaffFaxNo=" + clientStaffFaxNo +
        ", empInfoChgFlg=" + empInfoChgFlg +
        ", procedureK=" + procedureK +
        ", appointAllowAmt=" + appointAllowAmt +
        ", houseAllowAmt=" + houseAllowAmt +
        ", communicationAllowAmt=" + communicationAllowAmt +
        ", siteAllowAmt=" + siteAllowAmt +
        ", overworkAllowAmt=" + overworkAllowAmt +
        ", otherAllow1Amt=" + otherAllow1Amt +
        ", otherAllow2Amt=" + otherAllow2Amt +
        ", sumContractWm=" + sumContractWm +
        ", monReqSt=" + monReqSt +
        ", monAttachFileRemark=" + monAttachFileRemark +
        ", monAddRemark=" + monAddRemark +
        "}";
    }
}

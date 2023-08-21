package com.tsrs.webedi.modular.system.persistence.model;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.Date;

import com.alibaba.fastjson.annotation.JSONField;
import com.baomidou.mybatisplus.extension.activerecord.Model;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableName;

/**
 * <p>
 * 稟議書データ作成
 * </p>
 *
 * @author tsrs
 * @since 2017-10-20
 */
@TableName("apprdoc")
public class DownLoadDt extends Model<DownLoadDt> {

	private static final long serialVersionUID = 1L;

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
	 * 希望雇用形態:1：正社員 2：契約社員 3：出向社員 4：派遣社員 5：委託契約
	 */
	@TableField("emp_pat_k")
	private String empPatK;
	/**
	 * 作業案件CD
	 */
	@TableField("pj_cd")
	private String pjCd;
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
	 * 上位単金/単価
	 */
	@TableField("sell_price")
	private BigDecimal sellPrice;
	/**
	 * 要員渡し/原価
	 */
	@TableField("cost_price")
	private BigDecimal costPrice;
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
	 * その他(初月)
	 */
	@TableField("other_cost_first_mon")
	private BigDecimal otherCostFirstMon;
	/**
	 * 要員携帯電話番号
	 */
	@TableField("staff_mobile_no")
	private String staffMobileNo;
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
	 * 要員メールアドレス
	 */
	@TableField("staff_mail_addr")
	private String staffMailAddr;
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
	private String bpPayMonthk;
	/**
	 * 入社日
	 */
	@TableField("entrance_dt")
	private Date entranceDt;
	/**
	 * 入場日
	 */
	@TableField("site_entrance_dt")
	private Date siteEntranceDt;
	/**
	 * 社保費
	 */
	@TableField("insurance_cost")
	private BigDecimal insuranceCost;
	/**
	 * 要員マイナンバー
	 */
	@TableField("emp_my_number")
	private String empMyNumber;
	/**
	 * 所属会社法人マイナンバー
	 */
	@TableField("bp_my_number")
	private String bpMyNumber;
	/**
	 * 出勤時刻
	 */
	@JSONField(format = "hh:mm")
	@TableField("work_start_time")
	private Date workStartTime;
	/**
	 * 退勤時刻
	 */
	@JSONField(format = "hh:mm")
	@TableField("work_end_time")
	private Date workEndTime;
	/**
	 * 作業案件名称
	 */
	@TableField("pj_nm")
	private String pjNm;
	/**
	 * 作業内容・範囲
	 */
	@TableField("work_content")
	private String workContent;
	/**
	 * 勤務地/最寄駅
	 */
	@TableField("work_place")
	private String workPlace;
	/**
	 * 支払サイト/締日区分
	 */
	@TableField("payment_due_k")
	private String paymentDueK;
	/**
	 * 支払サイト/支払日付の日
	 */
	@TableField("pay_plan_d")
	private String payPlanD;
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
	 * 注文書受領日（見込）
	 */
	@TableField("order_recv_hope_dt")
	private Date orderRecvHopeDt;
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
	 * クライアント担当者名
	 */
	@TableField("client_staff_nm")
	private String clientStaffNm;
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
	 * クライアント担当者名（フリガナ）
	 */
	@TableField("client_staff_nm_fuli")
	private String clientStaffNmFuli;
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
	 * 売上販売計上月:1～12 売上販売の計上月 （請求対象月ではない）
	 */
	@TableField("sales_addup_month")
	private Date salesAddupMonth;
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
	 * 要員電話番号
	 */
	@TableField("staff_tel_no")
	private String staffTelNo;
	/**
	 * クライアント法人マイナンバー
	 */
	@TableField("client_my_number")
	private String clientMyNumber;
	/**
	 * 工数（契約用）
	 */
	@TableField("kou_su1")
	private BigDecimal kouSu1;
	/**
	 * 支払サイト/出金日付の日
	 */
	@TableField("payment_plan_d")
	private Integer paymentPlanD;
	/**
	 * 性別:M：男 F：女
	 */
	@TableField("emp_sex")
	private String empSex;
	/**
	 * 生年月日
	 */
	@TableField("emp_birthday")
	private Date empBirthday;
	/**
	 * 社員名（フリガナ）
	 */
	@TableField("emp_nm_fuli")
	private String empNmFuli;
	/**
	 * 休憩時間（分）
	 */
	@TableField("break_time_min")
	private Integer breakTimeMin;
	/**
	 * 基本賃金
	 */
	@TableField("sell_price_other_cost")
	private BigDecimal sellPriceOtherCost;
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
	 * 残業手当割合
	 */
	@TableField("overwork_allow_ratio")
	private BigDecimal overworkAllowRatio;
	/**
	 * 支払サイト/支払日付の日（BP）
	 */
	@TableField("bp_pay_plan_d")
	private String bpPayPlanD;
	/**
	 * 支払サイト/支払月区分
	 */
	@TableField("pay_month_k")
	private String payMonthk;
	/**
	 * 出勤時刻（BP）
	 */
	@JSONField(format = "hh:mm")
	@TableField("bp_work_start_time")
	private Date bpWorkStartTime;
	/**
	 * 退勤時刻（BP）
	 */
	@JSONField(format = "hh:mm")
	@TableField("bp_work_end_time")
	private Date bpWorkEndTime;
	/**
	 * 役職手当割合
	 */
	@TableField("appoint_allow_ratio")
	private BigDecimal appointAllowRatio;
	/**
	 * 現場手当割合
	 */
	@TableField("site_allow_ratio")
	private BigDecimal siteAllowRatio;
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
	 * 保険加入区分:1：雇用保険のみ 2：社保険完備 3：保険無し
	 */
	@TableField("insurance_k")
	private String insuranceK;
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
	 * 交通費
	 */
	@TableField("travel_cost")
	private BigDecimal travelCost;
	/**
	 * 交通費(初月)
	 */
	@TableField("travel_cost_first_mon")
	private BigDecimal travelCostFirstMon;
	/**
	 * 稟議書作成区分:1：通常更新 2：新規 3：条件変更 4：正社員化 5：退職 6：再販
	 */
	@TableField("appr_doc_create_k")
	private String apprDocCreateK;
	/**
	 * 稟議書作成日
	 */
	@TableField("appr_doc_create_dt")
	private Date apprDocCreateDt;
	/**
	 * 稟議書NO
	 */
	@TableField("appr_doc_no")
	private String apprDocNo;
	/**
	 * 手続き日付
	 */
	@TableField("procedure_dt")
	private Date procedureDt;
	/**
	 * 案件分類名称
	 */
	@TableField("pj_class_nm")
	private String pjClassNm;
	/**
	 * 資格手当
	 */
	@TableField("other_allow1_ratio")
	private BigDecimal otherAllow1Ratio;
	/**
	 * その他手当２
	 */
	@TableField("other_allow2_ratio")
	private BigDecimal otherAllow2Ratio;
	/**
	 * 所属会社FAX番号
	 */
	@TableField("bp_fax_no")
	private String bpFaxNo;
	/**
	 * 所属会社担当者名（フリガナ）
	 */
	@TableField("bp_staff_nm_fuli")
	private String bpStaffNmFuli;
	/**
	 * 所属会社担当者役職
	 */
	@TableField("bp_staff_position")
	private String bpStaffPosition;
	/**
	 * 所属会社担当者所属部署
	 */
	@TableField("bp_staff_dept_nm")
	private String bpStaffDeptNm;
	/**
	 * 所属会社担当者個人メール
	 */
	@TableField("bp_staff_mail_addr")
	private String bpStaffMailAddr;
	/**
	 * 請求計上月
	 */
	@TableField("billing_month")
	private Date billingMonth;
	/**
	 * 往復交通費日額合計
	 */
	@TableField("day_travel_cost")
	private BigDecimal dayTravelCost;

	public String getEmpNo() {
		return empNo;
	}

	public void setempNm(String empNm) {
		this.empNm = empNm;
	}

	public String getempNm() {
		return empNm;
	}

	public void setEmpNo(String empNo) {
		this.empNo = empNo;
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

	public String getEmpPatK() {
		return empPatK;
	}

	public void setEmpPatK(String empPatK) {
		this.empPatK = empPatK;
	}

	public String getPjCd() {
		return pjCd;
	}

	public void setPjCd(String pjCd) {
		this.pjCd = pjCd;
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

	public BigDecimal getSellPrice() {
		return sellPrice;
	}

	public void setSellPrice(BigDecimal sellPrice) {
		this.sellPrice = sellPrice;
	}

	public BigDecimal getCostPrice() {
		return costPrice;
	}

	public void setCostPrice(BigDecimal costPrice) {
		this.costPrice = costPrice;
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

	public BigDecimal getOtherCostFirstMon() {
		return otherCostFirstMon;
	}

	public void setOtherCostFirstMon(BigDecimal otherCostFirstMon) {
		this.otherCostFirstMon = otherCostFirstMon;
	}

	public String getStaffMobileNo() {
		return staffMobileNo;
	}

	public void setStaffMobileNo(String staffMobileNo) {
		this.staffMobileNo = staffMobileNo;
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

	public String getStaffMailAddr() {
		return staffMailAddr;
	}

	public void setStaffMailAddr(String staffMailAddr) {
		this.staffMailAddr = staffMailAddr;
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

	public String getBpPayMonthk() {
		return bpPayMonthk;
	}

	public void setBpPayMonthk(String bpPayMonthk) {
		this.bpPayMonthk = bpPayMonthk;
	}

	public Date getEntranceDt() {
		return entranceDt;
	}

	public void setEntranceDt(Date entranceDt) {
		this.entranceDt = entranceDt;
	}

	public Date getSiteEntranceDt() {
		return siteEntranceDt;
	}

	public void setSiteEntranceDt(Date siteEntranceDt) {
		this.siteEntranceDt = siteEntranceDt;
	}

	public BigDecimal getInsuranceCost() {
		return insuranceCost;
	}

	public void setInsuranceCost(BigDecimal insuranceCost) {
		this.insuranceCost = insuranceCost;
	}

	public String getEmpMyNumber() {
		return empMyNumber;
	}

	public void setEmpMyNumber(String empMyNumber) {
		this.empMyNumber = empMyNumber;
	}

	public String getBpMyNumber() {
		return bpMyNumber;
	}

	public void setBpMyNumber(String bpMyNumber) {
		this.bpMyNumber = bpMyNumber;
	}

	public Date getWorkStartTime() {
		return workStartTime;
	}

	public void setWorkStartTime(Date workStartTime) {
		this.workStartTime = workStartTime;
	}

	public Date getWorkEndTime() {
		return workEndTime;
	}

	public void setWorkEndTime(Date workEndTime) {
		this.workEndTime = workEndTime;
	}

	public String getPjNm() {
		return pjNm;
	}

	public void setPjNm(String pjNm) {
		this.pjNm = pjNm;
	}

	public String getWorkContent() {
		return workContent;
	}

	public void setWorkContent(String workContent) {
		this.workContent = workContent;
	}

	public String getWorkPlace() {
		return workPlace;
	}

	public void setWorkPlace(String workPlace) {
		this.workPlace = workPlace;
	}

	public String getPaymentDueK() {
		return paymentDueK;
	}

	public void setPaymentDueK(String paymentDueK) {
		this.paymentDueK = paymentDueK;
	}

	public String getPayPlanD() {
		return payPlanD;
	}

	public void setPayPlanD(String payPlanD) {
		this.payPlanD = payPlanD;
	}

	public String getPayMonthk() {
		return payMonthk;
	}

	public void setPayMonthk(String payMonthk) {
		this.payMonthk = payMonthk;
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

	public Date getOrderRecvHopeDt() {
		return orderRecvHopeDt;
	}

	public void setOrderRecvHopeDt(Date orderRecvHopeDt) {
		this.orderRecvHopeDt = orderRecvHopeDt;
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

	public String getClientStaffNm() {
		return clientStaffNm;
	}

	public void setClientStaffNm(String clientStaffNm) {
		this.clientStaffNm = clientStaffNm;
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

	public String getClientStaffNmFuli() {
		return clientStaffNmFuli;
	}

	public void setClientStaffNmFuli(String clientStaffNmFuli) {
		this.clientStaffNmFuli = clientStaffNmFuli;
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

	public Date getSalesAddupMonth() {
		return salesAddupMonth;
	}

	public void setSalesAddupMonth(Date salesAddupMonth) {
		this.salesAddupMonth = salesAddupMonth;
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

	public String getStaffTelNo() {
		return staffTelNo;
	}

	public void setStaffTelNo(String staffTelNo) {
		this.staffTelNo = staffTelNo;
	}

	public String getClientMyNumber() {
		return clientMyNumber;
	}

	public void setClientMyNumber(String clientMyNumber) {
		this.clientMyNumber = clientMyNumber;
	}

	public BigDecimal getKouSu1() {
		return kouSu1;
	}

	public void setKouSu1(BigDecimal kouSu1) {
		this.kouSu1 = kouSu1;
	}

	public Integer getPaymentPlanD() {
		return paymentPlanD;
	}

	public void setPaymentPlanD(Integer paymentPlanD) {
		this.paymentPlanD = paymentPlanD;
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

	public String getEmpNmFuli() {
		return empNmFuli;
	}

	public void setEmpNmFuli(String empNmFuli) {
		this.empNmFuli = empNmFuli;
	}

	public Integer getBreakTimeMin() {
		return breakTimeMin;
	}

	public void setBreakTimeMin(Integer breakTimeMin) {
		this.breakTimeMin = breakTimeMin;
	}

	public BigDecimal getSellPriceOtherCost() {
		return sellPriceOtherCost;
	}

	public void setSellPriceOtherCost(BigDecimal sellPriceOtherCost) {
		this.sellPriceOtherCost = sellPriceOtherCost;
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

	public BigDecimal getOverworkAllowRatio() {
		return overworkAllowRatio;
	}

	public void setOverworkAllowRatio(BigDecimal overworkAllowRatio) {
		this.overworkAllowRatio = overworkAllowRatio;
	}

	public String getBpPayPlanD() {
		return bpPayPlanD;
	}

	public void setBpPayPlanD(String bpPayPlanD) {
		this.bpPayPlanD = bpPayPlanD;
	}

	public Date getBpWorkEndTime() {
		return bpWorkEndTime;
	}

	public void setBpWorkEndTime(Date bpWorkEndTime) {
		this.bpWorkEndTime = bpWorkEndTime;
	}

	public BigDecimal getAppointAllowRatio() {
		return appointAllowRatio;
	}

	public void setAppointAllowRatio(BigDecimal appointAllowRatio) {
		this.appointAllowRatio = appointAllowRatio;
	}

	public BigDecimal getSiteAllowRatio() {
		return siteAllowRatio;
	}

	public void setSiteAllowRatio(BigDecimal siteAllowRatio) {
		this.siteAllowRatio = siteAllowRatio;
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

	public String getInsuranceK() {
		return insuranceK;
	}

	public void setInsuranceK(String insuranceK) {
		this.insuranceK = insuranceK;
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

	public BigDecimal getTravelCost() {
		return travelCost;
	}

	public void setTravelCost(BigDecimal travelCost) {
		this.travelCost = travelCost;
	}

	public BigDecimal getTravelCostFirstMon() {
		return travelCostFirstMon;
	}

	public void setTravelCostFirstMon(BigDecimal travelCostFirstMon) {
		this.travelCostFirstMon = travelCostFirstMon;
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

	public String getApprDocNo() {
		return apprDocNo;
	}

	public void setApprDocNo(String apprDocNo) {
		this.apprDocNo = apprDocNo;
	}

	public Date getProcedureDt() {
		return procedureDt;
	}

	public void setProcedureDt(Date procedureDt) {
		this.procedureDt = procedureDt;
	}

	public String getPjClassNm() {
		return pjClassNm;
	}

	public void setPjClassNm(String pjClassNm) {
		this.pjClassNm = pjClassNm;
	}
	public BigDecimal getOtherAllow1Ratio() {
		return otherAllow1Ratio;
	}

	public void setOtherAllow1Ratio(BigDecimal otherAllow1Ratio) {
		this.otherAllow1Ratio = otherAllow1Ratio;
	}
	public BigDecimal getOtherAllow2Ratio() {
		return otherAllow2Ratio;
	}

	public void setOtherAllow2Ratio(BigDecimal otherAllow2Ratio) {
		this.otherAllow2Ratio = otherAllow2Ratio;
	}

	public String getBpFaxNo() {
		return bpFaxNo;
	}

	public void setBpFaxNo(String bpFaxNo) {
		this.bpFaxNo = bpFaxNo;
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
	public String getBpStaffMailAddr() {
		return bpStaffMailAddr;
	}

	public void setBpStaffMailAddr(String bpStaffMailAddr) {
		this.bpStaffMailAddr = bpStaffMailAddr;
	}

	public Date getBillingMonth() {
		return billingMonth;
	}

	public void setBillingMonth(Date billingMonth) {
		this.billingMonth = billingMonth;
	}

	public BigDecimal getDayTravelCost() {
		return dayTravelCost;
	}

	public void setDayTravelCost(BigDecimal dayTravelCost) {
		this.dayTravelCost = dayTravelCost;
	}
	@Override
	public Serializable pkVal() {
		// TODO 自動生成されたメソッド・スタブ
		return null;
	}
}

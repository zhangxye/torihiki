package com.tsrs.webedi.modular.system.persistence.model;

import java.math.BigDecimal;
import java.util.Date;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.extension.activerecord.Model;
import java.io.Serializable;

/**
 * <p>
 * 売上_請求明細
 * </p>
 *
 * @author tsrs
 * @since 2017-09-01
 */
public class Salesreqdtl extends Model<Salesreqdtl> {

    private static final long serialVersionUID = 1L;

    /**
     * 売上ID
     */
    @TableId("sales_id")
	private Integer salesId;
    /**
     * 締日:クライアントの締日
     */
	@TableField("payment_dat")
	private Date paymentDat;
    /**
     * 次のPJ:次のPJ（売上）選択し
1：既存
2：経験
     */
	@TableField("next_pj_k")
	private String nextPjK;
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
     * 期間FROM:請求書
     */
	@TableField("req_start_dt")
	private Date reqStartDt;
    /**
     * 期間TO:請求書
     */
	@TableField("req_end_dt")
	private Date reqEndDt;
    /**
     * 上位単金/単価:請求書の基準単価
     */
	@TableField("sell_price")
	private BigDecimal sellPrice;
    /**
     * 工数（月次用）:当月入社の場合、小数部、そのほか、１とする
     */
	@TableField("current_mon_work_time")
	private BigDecimal currentMonWorkTime;
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
     * 実績時間:勤怠情報から取得（単位：時間）
     */
	@TableField("real_work_hour")
	private BigDecimal realWorkHour;
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
     * 金額:工数（月次用）＊基準単価
手動変更可能か？
     */
	@TableField("work_amt")
	private BigDecimal workAmt;
    /**
     * 交通費
     */
	@TableField("travel_cost")
	private BigDecimal travelCost;
    /**
     * 作業時間単位:1:10分　2：15分　3：30分　4：1時
     */
	@TableField("work_time_unit_k")
	private String workTimeUnitK;
    /**
     * 経費/オンライン当番
     */
	@TableField("other_cost")
	private BigDecimal otherCost;
    /**
     * 支払サイト/支払月区分:1：翌月/2：翌々月/3：翌々々月
     */
	@TableField("pay_month_k")
	private String payMonthK;
    /**
     * 支払サイト/支払日付の日
     */
	@TableField("pay_plan_d")
	private Integer payPlanD;
    /**
     * お支払期日:支払月＝当月＋支払サイト/支払月区分（1：翌月/2：翌々月/3：翌々々月）
お支払期日＝ 支払月+支払サイト/支払日付の日
     */
	@TableField("client_pay_dt")
	private Date clientPayDt;
    /**
     * 残業代:計算方法が確認要
     */
	@TableField("overwork_amt")
	private BigDecimal overworkAmt;
    /**
     * 控除金額:計算方法が確認要
欠勤控除？
     */
	@TableField("deduction_amt")
	private BigDecimal deductionAmt;
    /**
     * 税抜金額:基本金額＋交通費＋経費
     */
	@TableField("client_pay_without_tax")
	private BigDecimal clientPayWithoutTax;
    /**
     * 消 費 税:税抜金額＊消費税
     */
	@TableField("client_pay_tax")
	private BigDecimal clientPayTax;
    /**
     * 税込金額:税抜合計＋税込合計
     */
	@TableField("client_pay_with_tax")
	private BigDecimal clientPayWithTax;
    /**
     * 案件分類名称
     */
	@TableField("pj_class_nm")
	private String pjClassNm;
    /**
     * 粗利額:請求の税抜金額-原価の税抜金額
     */
	@TableField("gross_amt")
	private BigDecimal grossAmt;
    /**
     * 売上請求備考
     */
	@TableField("sales_req_remark")
	private String salesReqRemark;
    /**
     * 入金予定日:手入力
     */
	@TableField("money_rec_plan_dt")
	private Date moneyRecPlanDt;
    /**
     * 支払LT:売上請求明細の入金予定日-原価明細の出金予定日
     */
	@TableField("pay_lt")
	private Integer payLt;
    /**
     * 登録者
     */
	@TableField("sys_ins_user_id")
	private String sysInsUserId;
    /**
     * 登録日時
     */
	@TableField("sys_ins_dt")
	private Date sysInsDt;
    /**
     * 登録機能ID
     */
	@TableField("sys_ins_pg_id")
	private String sysInsPgId;
    /**
     * 更新者
     */
	@TableField("sys_upd_user_id")
	private String sysUpdUserId;
    /**
     * 更新日時
     */
	@TableField("sys_upd_dt")
	private Date sysUpdDt;
    /**
     * 更新機能ID
     */
	@TableField("sys_upd_pg_id")
	private String sysUpdPgId;


	public Integer getSalesId() {
		return salesId;
	}

	public void setSalesId(Integer salesId) {
		this.salesId = salesId;
	}

	public Date getPaymentDat() {
		return paymentDat;
	}

	public void setPaymentDat(Date paymentDat) {
		this.paymentDat = paymentDat;
	}

	public String getNextPjK() {
		return nextPjK;
	}

	public void setNextPjK(String nextPjK) {
		this.nextPjK = nextPjK;
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

	public Date getReqStartDt() {
		return reqStartDt;
	}

	public void setReqStartDt(Date reqStartDt) {
		this.reqStartDt = reqStartDt;
	}

	public Date getReqEndDt() {
		return reqEndDt;
	}

	public void setReqEndDt(Date reqEndDt) {
		this.reqEndDt = reqEndDt;
	}

	public BigDecimal getSellPrice() {
		return sellPrice;
	}

	public void setSellPrice(BigDecimal sellPrice) {
		this.sellPrice = sellPrice;
	}

	public BigDecimal getCurrentMonWorkTime() {
		return currentMonWorkTime;
	}

	public void setCurrentMonWorkTime(BigDecimal currentMonWorkTime) {
		this.currentMonWorkTime = currentMonWorkTime;
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

	public BigDecimal getRealWorkHour() {
		return realWorkHour;
	}

	public void setRealWorkHour(BigDecimal realWorkHour) {
		this.realWorkHour = realWorkHour;
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

	public BigDecimal getWorkAmt() {
		return workAmt;
	}

	public void setWorkAmt(BigDecimal workAmt) {
		this.workAmt = workAmt;
	}

	public BigDecimal getTravelCost() {
		return travelCost;
	}

	public void setTravelCost(BigDecimal travelCost) {
		this.travelCost = travelCost;
	}

	public String getWorkTimeUnitK() {
		return workTimeUnitK;
	}

	public void setWorkTimeUnitK(String workTimeUnitK) {
		this.workTimeUnitK = workTimeUnitK;
	}

	public BigDecimal getOtherCost() {
		return otherCost;
	}

	public void setOtherCost(BigDecimal otherCost) {
		this.otherCost = otherCost;
	}

	public String getPayMonthK() {
		return payMonthK;
	}

	public void setPayMonthK(String payMonthK) {
		this.payMonthK = payMonthK;
	}

	public Integer getPayPlanD() {
		return payPlanD;
	}

	public void setPayPlanD(Integer payPlanD) {
		this.payPlanD = payPlanD;
	}

	public Date getClientPayDt() {
		return clientPayDt;
	}

	public void setClientPayDt(Date clientPayDt) {
		this.clientPayDt = clientPayDt;
	}

	public BigDecimal getOverworkAmt() {
		return overworkAmt;
	}

	public void setOverworkAmt(BigDecimal overworkAmt) {
		this.overworkAmt = overworkAmt;
	}

	public BigDecimal getDeductionAmt() {
		return deductionAmt;
	}

	public void setDeductionAmt(BigDecimal deductionAmt) {
		this.deductionAmt = deductionAmt;
	}

	public BigDecimal getClientPayWithoutTax() {
		return clientPayWithoutTax;
	}

	public void setClientPayWithoutTax(BigDecimal clientPayWithoutTax) {
		this.clientPayWithoutTax = clientPayWithoutTax;
	}

	public BigDecimal getClientPayTax() {
		return clientPayTax;
	}

	public void setClientPayTax(BigDecimal clientPayTax) {
		this.clientPayTax = clientPayTax;
	}

	public BigDecimal getClientPayWithTax() {
		return clientPayWithTax;
	}

	public void setClientPayWithTax(BigDecimal clientPayWithTax) {
		this.clientPayWithTax = clientPayWithTax;
	}

	public String getPjClassNm() {
		return pjClassNm;
	}

	public void setPjClassNm(String pjClassNm) {
		this.pjClassNm = pjClassNm;
	}

	public BigDecimal getGrossAmt() {
		return grossAmt;
	}

	public void setGrossAmt(BigDecimal grossAmt) {
		this.grossAmt = grossAmt;
	}

	public String getSalesReqRemark() {
		return salesReqRemark;
	}

	public void setSalesReqRemark(String salesReqRemark) {
		this.salesReqRemark = salesReqRemark;
	}

	public Date getMoneyRecPlanDt() {
		return moneyRecPlanDt;
	}

	public void setMoneyRecPlanDt(Date moneyRecPlanDt) {
		this.moneyRecPlanDt = moneyRecPlanDt;
	}

	public Integer getPayLt() {
		return payLt;
	}

	public void setPayLt(Integer payLt) {
		this.payLt = payLt;
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

	@Override
	public Serializable pkVal() {
		return this.salesId;
	}

}

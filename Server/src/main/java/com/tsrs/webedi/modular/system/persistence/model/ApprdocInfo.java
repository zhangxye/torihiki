package com.tsrs.webedi.modular.system.persistence.model;

import com.baomidou.mybatisplus.annotation.IdType;
import java.util.Date;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.extension.activerecord.Model;
import java.io.Serializable;
import java.math.BigDecimal;

/**
 * <p>
 * 稟議書
 * </p>
 *
 * @author tsrs
 * @since 2017-08-03
 */
public class ApprdocInfo extends Model<ApprdocInfo> {

    private static final long serialVersionUID = 1L;

    //Apprdoc
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
     * 副担当
     */
	@TableField("business_sub_user_id")
	private String businessSubUserId;
    /**
     * 使用FLG:Y:使用
N:削除
     */
	@TableField("use_flg")
	private String useFlg;
    /**
     * 営業コメント
     */
	@TableField("business_remark")
	private String businessRemark;
    /**
     * 購買コメント
     */
	@TableField("purchase_remark")
	private String purchaseRemark;
    /**
     * その他コメント
     */
	@TableField("other_remark")
	private String otherRemark;
	
	//Appremp
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
	private String empBirthday;
    /**
     * 入社日
     */
	@TableField("entrance_dt")
	private Date entranceDt;
    /**
     * 要員区分:1：新規要員
2：既存要員
3：要員退社
     */
	@TableField("emp_k")
	private String empK;
    /**
     * 要員マイナンバー
     */
	@TableField("emp_my_number")
	private String empMyNumber;
    /**
     * 希望雇用形態:1：正社員
2：契約社員
3：個人事業主
4：外注
9：その他
     */
	@TableField("emp_pat_k")
	private String empPatK;
    /**
     * 要員契約区分:1：準委任契約
2：派遣契約
3：雇用契約
     */
	@TableField("emp_contract_k")
	private String empContractK;
    /**
     * 所属区分:1：自社プロパー
2：BP社
     */
	@TableField("affiliation_k")
	private String affiliationK;
    /**
     * その他区分:1：準委任契約
2：派遣契約
3：雇用契約
     */
	@TableField("emp_other_k")
	private String empOtherK;
    /**
     * BP契約区分:1：準委任契約
2：派遣契約
3：雇用契約
     */
	@TableField("bp_contract_k")
	private String bpContractK;
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
     * 契約形態:1：委任（ＳＥＳ）
2：派遣契約
     */
	@TableField("contract_form_k")
	private String contractFormK;
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

	
	//Apprclient
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
     * クライアント担当者携帯番号
     */
	@TableField("client_staff_mobile_no")
	private String clientStaffMobileNo;
    /**
     * クライアント担当者メールアドレス
     */
	@TableField("client_staff_mobile_mail_addr")
	private String clientStaffMobileMailAddr;
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
     * 受注（注文）書No
     */
	@TableField("client_order_no")
	private String clientOrderNo;

    /**
     * 売上販売計上月:1～12
売上販売の計上月
（請求対象月ではない）
     */
	@TableField("sales_addup_month")
	private Integer salesAddupMonth;
    /**
     * 当月稼働日:1～31
     */
	@TableField("work_days")
	private Integer workDays;
    /**
     * 請求計上月:1～12
     */
	@TableField("billing_month")
	private Integer billingMonth;
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
     * 単価消費税区分:1：税込
2：税抜
     */
	@TableField("sell_price_tax_k")
	private String sellPriceTaxK;
    /**
     * 原価消費税区分:1：税込
2：税抜
     */
	@TableField("cost_price_tax_k")
	private String costPriceTaxK;
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
     * 社保区分:1:（業務委託契約のため、対象外）
2：(入社2ヶ月後加入)
3：(入社翌月から加入）
4：(入社日から加入）
     */
	@TableField("insurance_k")
	private String insuranceK;
    /**
     * 社保加入年:yyyy
     */
	@TableField("insurance_join_y")
	private String insuranceJoinY;
    /**
     * 社保加入月:mm
     */
	@TableField("insurance_join_ym")
	private String insuranceJoinYm;

	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
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

	public String getUseFlg() {
		return useFlg;
	}

	public void setUseFlg(String useFlg) {
		this.useFlg = useFlg;
	}

	public String getBusinessRemark() {
		return businessRemark;
	}

	public void setBusinessRemark(String businessRemark) {
		this.businessRemark = businessRemark;
	}

	public String getPurchaseRemark() {
		return purchaseRemark;
	}

	public void setPurchaseRemark(String purchaseRemark) {
		this.purchaseRemark = purchaseRemark;
	}

	public String getOtherRemark() {
		return otherRemark;
	}

	public void setOtherRemark(String otherRemark) {
		this.otherRemark = otherRemark;
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

	public String getEmpBirthday() {
		return empBirthday;
	}

	public void setEmpBirthday(String empBirthday) {
		this.empBirthday = empBirthday;
	}

	public Date getEntranceDt() {
		return entranceDt;
	}

	public void setEntranceDt(Date entranceDt) {
		this.entranceDt = entranceDt;
	}

	public String getEmpK() {
		return empK;
	}

	public void setEmpK(String empK) {
		this.empK = empK;
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

	public String getEmpContractK() {
		return empContractK;
	}

	public void setEmpContractK(String empContractK) {
		this.empContractK = empContractK;
	}

	public String getAffiliationK() {
		return affiliationK;
	}

	public void setAffiliationK(String affiliationK) {
		this.affiliationK = affiliationK;
	}

	public String getEmpOtherK() {
		return empOtherK;
	}

	public void setEmpOtherK(String empOtherK) {
		this.empOtherK = empOtherK;
	}

	public String getBpContractK() {
		return bpContractK;
	}

	public void setBpContractK(String bpContractK) {
		this.bpContractK = bpContractK;
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

	public String getInsuranceK() {
		return insuranceK;
	}

	public void setInsuranceK(String insuranceK) {
		this.insuranceK = insuranceK;
	}

	public String getTakeOverK() {
		return takeOverK;
	}

	public void setTakeOverK(String takeOverK) {
		this.takeOverK = takeOverK;
	}

	public String getContractFormK() {
		return contractFormK;
	}

	public void setContractFormK(String contractFormK) {
		this.contractFormK = contractFormK;
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

	public String getClientStaffMobileMailAddr() {
		return clientStaffMobileMailAddr;
	}

	public void setClientStaffMobileMailAddr(String clientStaffMobileMailAddr) {
		this.clientStaffMobileMailAddr = clientStaffMobileMailAddr;
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

	public String getClientOrderNo() {
		return clientOrderNo;
	}

	public void setClientOrderNo(String clientOrderNo) {
		this.clientOrderNo = clientOrderNo;
	}

	
	
	
	

	@Override
	public Serializable pkVal() {
		return this.apprDocId;
	}

}

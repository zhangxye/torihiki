package com.tsrs.webedi.modular.system.jmodel;

import java.math.BigDecimal;
import java.util.Date;
import com.alibaba.fastjson.annotation.JSONField;

public class DownLoadDt {

	// 社員コード
	private String emp_no;
	// 社員名前
	private String emp_nm;
	private String bp_comp_id;
	private String bp_comp_emp_nm;
	// 社員区分（雇用形態）
	private String emp_pat_k;
	// 案件ID
	private String pj_cd;
	// 上限時間
	private BigDecimal wh_max;
	// 下限時間
	private BigDecimal wh_min;
	// 上位単金
	private BigDecimal sell_price;
	// 要員渡し
	private BigDecimal cost_price;
	// 超過単価
	private BigDecimal excess_price;
	// 控除単価
	private BigDecimal deducation_price;
	// その他
	private BigDecimal other_cost;
	private String staff_mobile_no;
	// 受注期間
	@JSONField(format = "yyyy-MM-dd")
	private Date appr_doc_dt_from;
	@JSONField(format = "yyyy-MM-dd")
	private Date appr_doc_dt_to;
	private String staff_mail_addr;
	private String bp_payment_plan_d;
	private String bp_payment_due_k;
	private String bp_pay_month_k;
	// 入社日
	@JSONField(format = "yyyy/MM/dd")
	private Date entrance_dt;
	// 入場日
	@JSONField(format = "yyyy/MM")
	private Date site_entrance_dt;
	private BigDecimal insurance_cost;
	// マイナンバー
	private String emp_my_number;
	private String bp_my_number;
	// 就業時間 始業
	@JSONField(format = "HH:mm")
	private Date work_start_time;
	// 終業
	@JSONField(format = "HH:mm")
	private Date work_end_time;
	private String pj_nm;
	// 作業内容・範囲
	private String work_content;
	// 最寄駅
	private String work_place;
	private String payment_due_k;
	private String pay_plan_d;
	private String pay_month_k;
	// 営業主担当
	private String business_main_user_id;
	// 営業副担当
	private String business_sub_user_id;
	// 注文書受領日（見込）
	@JSONField(format = "yyyy-MM-dd")
	private Date order_recv_hope_dt;
	// 受注期間
	private Date pj_start_dt;
	private Date pj_end_dt;
	// 時間単位
	private String work_time_unit_k;
	// 氏名
	private String client_staff_nm;
	// 所属部署
	private String client_staff_dept_nm;
	// 役職
	private String client_staff_position;
	// フリガナ
	private String client_staff_nm_fuli;
	// 携帯番号
	private String client_staff_mobile_no;
	// メールアドレス
	private String client_mail_addr;
	// メールアドレス（CC）
	private String client_mail_addr_cc;
	// 売上販売計上月
	private Date sales_addup_month;
	// クライアントID
	private String client_no;
	// クライアント名称
	private String client_nm;
	// 電話番号
	private String client_tel_no;
	// FAX番号
	private String client_fax_no;
	// 郵便番号〒
	private String client_post_no;
	// 住所
	private String client_addr;
	private String staff_addr;
	private String staff_post_no;
	private String staff_tel_no;
	// 法人マイナンバー
	private String client_my_number;
	// 工数（契約用）
	private BigDecimal kou_su1;
	private String payment_plan_d;
	// emp_sex
	private String emp_sex;
	// 生年月日
	@JSONField(format = "yyyy-MM-dd")
	private Date emp_birthday;
	// フリガナ
	private String emp_nm_fuli;
	// 休憩時間
	private Integer break_time_min;
	// 基本給
	private BigDecimal sell_price_other_cost;
	// 住宅手当
	private BigDecimal house_allow_ratio;
	// 通信手当
	private BigDecimal communication_allow_ratio;
	// 残業手当
	private BigDecimal overwork_allow_ratio;
	private String bp_pay_plan_d;
	@JSONField(format = "HH:mm")
	private Date bp_work_start_time;
	@JSONField(format = "HH:mm")
	private Date bp_work_end_time;
	// 役職手当
	private BigDecimal appoint_allow_ratio;
	// 現場手当
	private BigDecimal site_allow_ratio;
	private BigDecimal bp_wh_max;
	private BigDecimal bp_wh_min;
	private BigDecimal bp_excess_price;
	private BigDecimal bp_deducation_price;
	private String bp_work_unit_k;
	// 保険加入
	private String insurance_k;
	// 退场日
	@JSONField(format = "yyyy-MM-dd")
	private Date withdrawal_dt;
	private String after_withdrawal_post_no;
	private String after_withdrawal_addr;
	private BigDecimal travel_cost;
	private BigDecimal travel_cost_first_mon;
	// 稟議書作成区分
	private String appr_doc_create_k;
	// 作成日
	@JSONField(format = "yyyy-MM-dd")
	private Date appr_doc_create_dt;
	// 稟議書NO
	private String appr_doc_no;
	// 手続き日付
	@JSONField(format = "yyyy-MM-dd")
	private Date procedure_dt;
	// 案件分類名称
	private String pj_class_nm;
	// そのた手当1割合
	private BigDecimal other_allow1_ratio;
	// そのた手当2割合
	private BigDecimal other_allow2_ratio;
	// 所属会社FAX番号
	private String bp_fax_no;
	// 所属会社担当者名（フリガナ）
	private String bp_staff_nm_fuli;
	// 所属会社担当者所属部署
	private String bp_staff_dept_nm;
	// 所属会社担当者役職
	private String bp_staff_position;
	// 所属会社担当者個人メール
	private String bp_staff_mail_addr;
	// 請求計上月
	@JSONField(format = "yyyy-MM-dd")
	private Date billing_month;
	// 往復交通費日額合計
	private BigDecimal day_travel_cost;

	public String getEmp_no() {
		return emp_no;
	}

	public void setEmp_no(String emp_no) {
		this.emp_no = emp_no;
	}

	public String getEmp_nm() {
		return emp_nm;
	}

	public void setEmp_nm(String emp_nm) {
		this.emp_nm = emp_nm;
	}

	public String getBp_comp_id() {
		return bp_comp_id;
	}

	public void setBp_comp_id(String bp_comp_id) {
		this.bp_comp_id = bp_comp_id;
	}

	public String getBp_comp_emp_nm() {
		return bp_comp_emp_nm;
	}

	public void setBp_comp_emp_nm(String bp_comp_emp_nm) {
		this.bp_comp_emp_nm = bp_comp_emp_nm;
	}

	public String getEmp_pat_k() {
		return emp_pat_k;
	}

	public void setEmp_pat_k(String emp_pat_k) {
		this.emp_pat_k = emp_pat_k;
	}

	public String getPj_cd() {
		return pj_cd;
	}

	public void setPj_cd(String pj_cd) {
		this.pj_cd = pj_cd;
	}

	public BigDecimal getWh_max() {
		return wh_max;
	}

	public void setWh_max(BigDecimal wh_max) {
		this.wh_max = wh_max;
	}

	public BigDecimal getWh_min() {
		return wh_min;
	}

	public void setWh_min(BigDecimal wh_min) {
		this.wh_min = wh_min;
	}

	public BigDecimal getSell_price() {
		return sell_price;
	}

	public void setSell_price(BigDecimal sell_price) {
		this.sell_price = sell_price;
	}

	public BigDecimal getCost_price() {
		return cost_price;
	}

	public void setCost_price(BigDecimal cost_price) {
		this.cost_price = cost_price;
	}

	public BigDecimal getExcess_price() {
		return excess_price;
	}

	public void setExcess_price(BigDecimal excess_price) {
		this.excess_price = excess_price;
	}

	public BigDecimal getDeducation_price() {
		return deducation_price;
	}

	public void setDeducation_price(BigDecimal deducation_price) {
		this.deducation_price = deducation_price;
	}

	public BigDecimal getOther_cost() {
		return other_cost;
	}

	public void setOther_cost(BigDecimal other_cost) {
		this.other_cost = other_cost;
	}

	public String getStaff_mobile_no() {
		return staff_mobile_no;
	}

	public void setStaff_mobile_no(String staff_mobile_no) {
		this.staff_mobile_no = staff_mobile_no;
	}

	public Date getAppr_doc_dt_from() {
		return appr_doc_dt_from;
	}

	public void setAppr_doc_dt_from(Date appr_doc_dt_from) {
		this.appr_doc_dt_from = appr_doc_dt_from;
	}

	public Date getAppr_doc_dt_to() {
		return appr_doc_dt_to;
	}

	public void setAppr_doc_dt_to(Date appr_doc_dt_to) {
		this.appr_doc_dt_to = appr_doc_dt_to;
	}

	public String getStaff_mail_addr() {
		return staff_mail_addr;
	}

	public void setStaff_mail_addr(String staff_mail_addr) {
		this.staff_mail_addr = staff_mail_addr;
	}

	public String getBp_payment_plan_d() {
		return bp_payment_plan_d;
	}

	public void setBp_payment_plan_d(String bp_payment_plan_d) {
		this.bp_payment_plan_d = bp_payment_plan_d;
	}

	public String getBp_payment_due_k() {
		return bp_payment_due_k;
	}

	public void setBp_payment_due_k(String bp_payment_due_k) {
		this.bp_payment_due_k = bp_payment_due_k;
	}

	public String getBp_pay_month_k() {
		return bp_pay_month_k;
	}

	public void setBp_pay_month_k(String bp_pay_month_k) {
		this.bp_pay_month_k = bp_pay_month_k;
	}

	public Date getEntrance_dt() {
		return entrance_dt;
	}

	public void setEntrance_dt(Date entrance_dt) {
		this.entrance_dt = entrance_dt;
	}

	public Date getSite_entrance_dt() {
		return site_entrance_dt;
	}

	public void setSite_entrance_dt(Date site_entrance_dt) {
		this.site_entrance_dt = site_entrance_dt;
	}

	public BigDecimal getInsurance_cost() {
		return insurance_cost;
	}

	public void setInsurance_cost(BigDecimal insurance_cost) {
		this.insurance_cost = insurance_cost;
	}

	public String getEmp_my_number() {
		return emp_my_number;
	}

	public void setEmp_my_number(String emp_my_number) {
		this.emp_my_number = emp_my_number;
	}

	public String getBp_my_number() {
		return bp_my_number;
	}

	public void setBp_my_number(String bp_my_number) {
		this.bp_my_number = bp_my_number;
	}

	public Date getWork_start_time() {
		return work_start_time;
	}

	public void setWork_start_time(Date work_start_time) {
		this.work_start_time = work_start_time;
	}

	public Date getWork_end_time() {
		return work_end_time;
	}

	public void setWork_end_time(Date work_end_time) {
		this.work_end_time = work_end_time;
	}

	public String getPj_nm() {
		return pj_nm;
	}

	public void setPj_nm(String pj_nm) {
		this.pj_nm = pj_nm;
	}

	public String getWork_content() {
		return work_content;
	}

	public void setWork_content(String work_content) {
		this.work_content = work_content;
	}

	public String getWork_place() {
		return work_place;
	}

	public void setWork_place(String work_place) {
		this.work_place = work_place;
	}

	public String getPayment_due_k() {
		return payment_due_k;
	}

	public void setPayment_due_k(String payment_due_k) {
		this.payment_due_k = payment_due_k;
	}

	public String getPay_plan_d() {
		return pay_plan_d;
	}

	public void setPay_plan_d(String pay_plan_d) {
		this.pay_plan_d = pay_plan_d;
	}

	public String getPay_month_k() {
		return pay_month_k;
	}

	public void setPay_month_k(String pay_month_k) {
		this.pay_month_k = pay_month_k;
	}

	public String getBusiness_main_user_id() {
		return business_main_user_id;
	}

	public void setBusiness_main_user_id(String business_main_user_id) {
		this.business_main_user_id = business_main_user_id;
	}

	public String getBusiness_sub_user_id() {
		return business_sub_user_id;
	}

	public void setBusiness_sub_user_id(String business_sub_user_id) {
		this.business_sub_user_id = business_sub_user_id;
	}

	public Date getOrder_recv_hope_dt() {
		return order_recv_hope_dt;
	}

	public void setOrder_recv_hope_dt(Date order_recv_hope_dt) {
		this.order_recv_hope_dt = order_recv_hope_dt;
	}

	public Date getPj_start_dt() {
		return pj_start_dt;
	}

	public void setPj_start_dt(Date pj_start_dt) {
		this.pj_start_dt = pj_start_dt;
	}

	public Date getPj_end_dt() {
		return pj_end_dt;
	}

	public void setPj_end_dt(Date pj_end_dt) {
		this.pj_end_dt = pj_end_dt;
	}

	public String getWork_time_unit_k() {
		return work_time_unit_k;
	}

	public void setWork_time_unit_k(String work_time_unit_k) {
		this.work_time_unit_k = work_time_unit_k;
	}

	public String getClient_staff_nm() {
		return client_staff_nm;
	}

	public void setClient_staff_nm(String client_staff_nm) {
		this.client_staff_nm = client_staff_nm;
	}

	public String getClient_staff_dept_nm() {
		return client_staff_dept_nm;
	}

	public void setClient_staff_dept_nm(String client_staff_dept_nm) {
		this.client_staff_dept_nm = client_staff_dept_nm;
	}

	public String getClient_staff_position() {
		return client_staff_position;
	}

	public void setClient_staff_position(String client_staff_position) {
		this.client_staff_position = client_staff_position;
	}

	public String getClient_staff_nm_fuli() {
		return client_staff_nm_fuli;
	}

	public void setClient_staff_nm_fuli(String client_staff_nm_fuli) {
		this.client_staff_nm_fuli = client_staff_nm_fuli;
	}

	public String getClient_staff_mobile_no() {
		return client_staff_mobile_no;
	}

	public void setClient_staff_mobile_no(String client_staff_mobile_no) {
		this.client_staff_mobile_no = client_staff_mobile_no;
	}

	public String getClient_mail_addr() {
		return client_mail_addr;
	}

	public void setClient_mail_addr(String client_mail_addr) {
		this.client_mail_addr = client_mail_addr;
	}

	public String getClient_mail_addr_cc() {
		return client_mail_addr_cc;
	}

	public void setClient_mail_addr_cc(String client_mail_addr_cc) {
		this.client_mail_addr_cc = client_mail_addr_cc;
	}

	public Date getSales_addup_month() {
		return sales_addup_month;
	}

	public void setSales_addup_month(Date sales_addup_month) {
		this.sales_addup_month = sales_addup_month;
	}

	public String getClient_no() {
		return client_no;
	}

	public void setClient_no(String client_no) {
		this.client_no = client_no;
	}

	public String getClient_nm() {
		return client_nm;
	}

	public void setClient_nm(String client_nm) {
		this.client_nm = client_nm;
	}

	public String getClient_tel_no() {
		return client_tel_no;
	}

	public void setClient_tel_no(String client_tel_no) {
		this.client_tel_no = client_tel_no;
	}

	public String getClient_fax_no() {
		return client_fax_no;
	}

	public void setClient_fax_no(String client_fax_no) {
		this.client_fax_no = client_fax_no;
	}

	public String getClient_post_no() {
		return client_post_no;
	}

	public void setClient_post_no(String client_post_no) {
		this.client_post_no = client_post_no;
	}

	public String getClient_addr() {
		return client_addr;
	}

	public void setClient_addr(String client_addr) {
		this.client_addr = client_addr;
	}

	public String getStaff_addr() {
		return staff_addr;
	}

	public void setStaff_addr(String staff_addr) {
		this.staff_addr = staff_addr;
	}

	public String getStaff_post_no() {
		return staff_post_no;
	}

	public void setStaff_post_no(String staff_post_no) {
		this.staff_post_no = staff_post_no;
	}

	public String getStaff_tel_no() {
		return staff_tel_no;
	}

	public void setStaff_tel_no(String staff_tel_no) {
		this.staff_tel_no = staff_tel_no;
	}

	public String getClient_my_number() {
		return client_my_number;
	}

	public void setClient_my_number(String client_my_number) {
		this.client_my_number = client_my_number;
	}

	public BigDecimal getKou_su1() {
		return kou_su1;
	}

	public void setKou_su1(BigDecimal kou_su1) {
		this.kou_su1 = kou_su1;
	}

	public String getPayment_plan_d() {
		return payment_plan_d;
	}

	public void setPayment_plan_d(String payment_plan_d) {
		this.payment_plan_d = payment_plan_d;
	}

	public String getEmp_sex() {
		return emp_sex;
	}

	public void setEmp_sex(String emp_sex) {
		this.emp_sex = emp_sex;
	}

	public Date getEmp_birthday() {
		return emp_birthday;
	}

	public void setEmp_birthday(Date emp_birthday) {
		this.emp_birthday = emp_birthday;
	}

	public String getEmp_nm_fuli() {
		return emp_nm_fuli;
	}

	public void setEmp_nm_fuli(String emp_nm_fuli) {
		this.emp_nm_fuli = emp_nm_fuli;
	}

	public Integer getBreak_time_min() {
		return break_time_min;
	}

	public void setBreak_time_min(Integer break_time_min) {
		this.break_time_min = break_time_min;
	}

	public BigDecimal getSell_price_other_cost() {
		return sell_price_other_cost;
	}

	public void setSell_price_other_cost(BigDecimal sell_price_other_cost) {
		this.sell_price_other_cost = sell_price_other_cost;
	}

	public BigDecimal getHouse_allow_ratio() {
		return house_allow_ratio;
	}

	public void setHouse_allow_ratio(BigDecimal house_allow_ratio) {
		this.house_allow_ratio = house_allow_ratio;
	}

	public BigDecimal getCommunication_allow_ratio() {
		return communication_allow_ratio;
	}

	public void setCommunication_allow_ratio(BigDecimal communication_allow_ratio) {
		this.communication_allow_ratio = communication_allow_ratio;
	}

	public BigDecimal getOverwork_allow_ratio() {
		return overwork_allow_ratio;
	}

	public void setOverwork_allow_ratio(BigDecimal overwork_allow_ratio) {
		this.overwork_allow_ratio = overwork_allow_ratio;
	}

	public String getBp_pay_plan_d() {
		return bp_pay_plan_d;
	}

	public void setBp_pay_plan_d(String bp_pay_plan_d) {
		this.bp_pay_plan_d = bp_pay_plan_d;
	}

	public Date getBp_work_start_time() {
		return bp_work_start_time;
	}

	public void setBp_work_start_time(Date bp_work_start_time) {
		this.bp_work_start_time = bp_work_start_time;
	}

	public Date getBp_work_end_time() {
		return bp_work_end_time;
	}

	public void setBp_work_end_time(Date bp_work_end_time) {
		this.bp_work_end_time = bp_work_end_time;
	}

	public BigDecimal getAppoint_allow_ratio() {
		return appoint_allow_ratio;
	}

	public void setAppoint_allow_ratio(BigDecimal appoint_allow_ratio) {
		this.appoint_allow_ratio = appoint_allow_ratio;
	}

	public BigDecimal getSite_allow_ratio() {
		return site_allow_ratio;
	}

	public void setSite_allow_ratio(BigDecimal site_allow_ratio) {
		this.site_allow_ratio = site_allow_ratio;
	}

	public BigDecimal getBp_wh_max() {
		return bp_wh_max;
	}

	public void setBp_wh_max(BigDecimal bp_wh_max) {
		this.bp_wh_max = bp_wh_max;
	}

	public BigDecimal getBp_wh_min() {
		return bp_wh_min;
	}

	public void setBp_wh_min(BigDecimal bp_wh_min) {
		this.bp_wh_min = bp_wh_min;
	}

	public BigDecimal getBp_excess_price() {
		return bp_excess_price;
	}

	public void setBp_excess_price(BigDecimal bp_excess_price) {
		this.bp_excess_price = bp_excess_price;
	}

	public BigDecimal getBp_deducation_price() {
		return bp_deducation_price;
	}

	public void setBp_deducation_price(BigDecimal bp_deducation_price) {
		this.bp_deducation_price = bp_deducation_price;
	}

	public String getBp_work_unit_k() {
		return bp_work_unit_k;
	}

	public void setBp_work_unit_k(String bp_work_unit_k) {
		this.bp_work_unit_k = bp_work_unit_k;
	}

	public String getInsurance_k() {
		return insurance_k;
	}

	public void setInsurance_k(String insurance_k) {
		this.insurance_k = insurance_k;
	}

	public Date getWithdrawal_dt() {
		return withdrawal_dt;
	}

	public void setWithdrawal_dt(Date withdrawal_dt) {
		this.withdrawal_dt = withdrawal_dt;
	}

	public String getAfter_withdrawal_post_no() {
		return after_withdrawal_post_no;
	}

	public void setAfter_withdrawal_post_no(String after_withdrawal_post_no) {
		this.after_withdrawal_post_no = after_withdrawal_post_no;
	}

	public String getAfter_withdrawal_addr() {
		return after_withdrawal_addr;
	}

	public void setAfter_withdrawal_addr(String after_withdrawal_addr) {
		this.after_withdrawal_addr = after_withdrawal_addr;
	}

	public BigDecimal getTravel_cost() {
		return travel_cost;
	}

	public void setTravel_cost(BigDecimal travel_cost) {
		this.travel_cost = travel_cost;
	}

	public BigDecimal getTravel_cost_first_mon() {
		return travel_cost_first_mon;
	}

	public void setTravel_cost_first_mon(BigDecimal travel_cost_first_mon) {
		this.travel_cost_first_mon = travel_cost_first_mon;
	}

	public String getAppr_doc_create_k() {
		return appr_doc_create_k;
	}

	public void setAppr_doc_create_k(String appr_doc_create_k) {
		this.appr_doc_create_k = appr_doc_create_k;
	}

	public Date getAppr_doc_create_dt() {
		return appr_doc_create_dt;
	}

	public void setAppr_doc_create_dt(Date appr_doc_create_dt) {
		this.appr_doc_create_dt = appr_doc_create_dt;
	}

	public String getAppr_doc_no() {
		return appr_doc_no;
	}

	public void setAppr_doc_no(String appr_doc_no) {
		this.appr_doc_no = appr_doc_no;
	}

	public Date getProcedure_dt() {
		return procedure_dt;
	}

	public void setProcedure_dt(Date procedure_dt) {
		this.procedure_dt = procedure_dt;
	}

	public String getPj_class_nm() {
		return pj_class_nm;
	}

	public void setPj_class_nm(String pj_class_nm) {
		this.pj_class_nm = pj_class_nm;
	}

	public BigDecimal getOther_allow1_ratio() {
		return other_allow1_ratio;
	}

	public void setOther_allow1_ratio(BigDecimal other_allow1_ratio) {
		this.other_allow1_ratio = other_allow1_ratio;
	}

	public BigDecimal getOther_allow2_ratio() {
		return other_allow2_ratio;
	}

	public void setOther_allow2_ratio(BigDecimal other_allow2_ratio) {
		this.other_allow2_ratio = other_allow2_ratio;
	}

	public String getBp_fax_no() {
		return bp_fax_no;
	}

	public void setBp_fax_no(String bp_fax_no) {
		this.bp_fax_no = bp_fax_no;
	}

	public String getBp_staff_nm_fuli() {
		return bp_staff_nm_fuli;
	}

	public void setBp_staff_nm_fuli(String bp_staff_nm_fuli) {
		this.bp_staff_nm_fuli = bp_staff_nm_fuli;
	}

	public String getBp_staff_dept_nm() {
		return bp_staff_dept_nm;
	}

	public void setBp_staff_dept_nm(String bp_staff_dept_nm) {
		this.bp_staff_dept_nm = bp_staff_dept_nm;
	}

	public String getBp_staff_position() {
		return bp_staff_position;
	}

	public void setBp_staff_position(String bp_staff_position) {
		this.bp_staff_position = bp_staff_position;
	}

	public String getBp_staff_mail_addr() {
		return bp_staff_mail_addr;
	}

	public void setBp_staff_mail_addr(String bp_staff_mail_addr) {
		this.bp_staff_mail_addr = bp_staff_mail_addr;
	}

	public Date getBilling_month() {
		return billing_month;
	}

	public void setBilling_month(Date billing_month) {
		this.billing_month = billing_month;
	}

	public BigDecimal getDay_travel_cost() {
		return day_travel_cost;
	}

	public void setDay_travel_cost(BigDecimal day_travel_cost) {
		this.day_travel_cost = day_travel_cost;
	}
}

package com.tsrs.webedi.modular.system.jmodel;

import java.math.BigDecimal;
import java.util.Date;
import java.util.List;

import com.alibaba.fastjson.annotation.JSONField;
import com.baomidou.mybatisplus.annotation.TableField;

public class ApprDocInfo {
	//稟議書NO
	private String appr_doc_no;
	//稟議書作成区分
	private String appr_doc_create_k;
	//作成日
	@JSONField (format="yyyy-MM-dd") 
	private Date appr_doc_create_dt;
	//受注期間
	@JSONField (format="yyyy-MM-dd") 
	private Date appr_doc_dt_from;
	@JSONField (format="yyyy-MM-dd") 
	private Date appr_doc_dt_to;
	//案件ID
	private String pj_cd;
	private String pj_nm;
	//入場日
	@JSONField (format="yyyy-MM-dd") 
	private Date site_entrance_dt;
	//入社日
	@JSONField (format="yyyy-MM-dd") 
	private Date entrance_dt;
	
	//営業主担当
	private String business_main_user_id;
	//営業副担当
	private String business_sub_user_id;
	//部署
	private String dept_cd;
	//社員コード
	private String emp_no;
	//
	private String emp_nm;
	//フリガナ
	private String emp_nm_fuli;
	//emp_sex
	private String emp_sex;
	//生年月日
	@JSONField (format="yyyy-MM-dd") 
	private Date emp_birthday;
	//年齢
	private Integer emp_age;
	//退场日
	@JSONField (format="yyyy-MM-dd") 
	private Date withdrawal_dt;
	//入社経歴
	private String entrance_record_k;
	//マイナンバー
	private String emp_my_number;
	//社員区分（雇用形態）
	private String emp_pat_k;
	//保険加入
	private String insurance_k;
	//社保加入予定日
	@JSONField (format="yyyy-MM-dd") 
	private Date insurance_join_ymd;
	//所属区分（再委託先）
	private String affiliation_k;
	//クライアントID
	private String client_no;
	//クライアント名称
	private String client_nm;
	//契約（上位）
	private String client_contract_k;
	//受注（注文）書No.
	private String client_order_no;
	//取引区分
	private String take_over_k;
	//注文書受領日（見込）
	@JSONField (format="yyyy-MM-dd") 
	private Date order_recv_hope_dt;
	//営業日数（入場月）
	private Integer business_days;
	//稼働日数（入場月）
	private Integer work_days;
	//売上販売計上月
	private Date sales_addup_month;
	//請求計上月
	private Date billing_month;
	//上位単金
	private BigDecimal sell_price;
	//要員渡し
	private BigDecimal cost_price;
	//その他
	private BigDecimal other_cost;
	//その他(初月)
	private BigDecimal other_cost_first_mon;
	private String payment_plan_d;
	private String bp_payment_plan_d;
	private BigDecimal travel_cost_first_mon;
	private BigDecimal travel_cost;
	private BigDecimal insurance_cost;
	//現場手当
	private BigDecimal site_allow_ratio;
	//残業手当
	private BigDecimal overwork_allow_ratio;
	//役職手当
	private BigDecimal appoint_allow_ratio;
	//通信手当
	private BigDecimal communication_allow_ratio;
	//住宅手当
	private BigDecimal house_allow_ratio;
	//手当
	private String other_allow1_nm;
	private BigDecimal other_allow1_ratio;
	private String other_allow2_nm;
	private BigDecimal other_allow2_ratio;
	// Client_URL
	private String client_url;
	//郵便番号〒
	private String client_post_no;
	//住所
	private String client_addr;
	//電話番号
	private String client_tel_no;
	//FAX番号
	private String client_fax_no;
	//法人マイナンバー
	private String client_my_number;
	
	//氏名
	private String client_staff_nm;
	//フリガナ
	private String client_staff_nm_fuli;
	//所属部署
	private String client_staff_dept_nm;
	//メールアドレス
	private String client_mail_addr;
	//役職
	private String client_staff_position;
	//電話番号
	private String client_staff_tel_no;
	//ファクス番号
	private String client_staff_fax_no;
	//携帯番号
	private String client_staff_mobile_no;
	//メールアドレス（CC）
	private String client_mail_addr_cc;
	
	//氏名
	private String client_req_staff_nm;
	//フリガナ
	private String client_req_staff_nm_fuli;
	//所属部署
	private String client_req_staff_dept_nm;
	//役職
	private String client_req_staff_position;
	//電話番号（直通）
	private String client_req_tel_no;
	//FAX番号
	private String client_req_fax_no;
	//携帯番号
	private String client_req_staff_mobile_no;
	//メールアドレス
	private String client_req_mail_addr;
	//メールアドレス（CC）
	private String client_req_mail_addr_cc;
	//郵便番号〒
	private String client_req_post_no;
	//住所
	private String client_req_addr;
	//区分
	private String mon_pro_k;
	//作業報告書F
	private String work_rep_f;
	//作業報告書〆
	private String work_rep_dl_k;
	//請求書F
	private String bill_f;
	//請求書〆
	private String bill_dl_k;
	//最寄駅
	private String work_place;
	//作業内容・範囲
	private String work_content;
	//区分
	private String salary_pay_k;
	//精算条件
	private String accounting_pat_k;
	//上限時間
	private BigDecimal wh_max;
	//下限時間
	private BigDecimal wh_min;
	//中割時間
	private BigDecimal wh_mid;
	//超過単価
	private BigDecimal excess_price;
	//控除単価
	private BigDecimal deducation_price;
	//時間単位
	private String work_time_unit_k;
	//受注期間
	private Date pj_start_dt;
	private Date pj_end_dt;
	//案件分類
	private String pj_class_nm;
	//就業時間 始業
	@JSONField (format="HH:mm") 
	private Date work_start_time;
	//終業
	@JSONField (format="HH:mm")
	private Date work_end_time;
	//休憩時間
	private Integer break_time_min;
	private String payment_due_k;
	private String pay_month_k;
	private String pay_plan_d;
	
	
	
	//支払サイト
	//工数人月（日割）
	private boolean dina_reg_f;
	private Integer appr_doc_id;
	private String use_flg;

	private String appr_doc_st;
	private String old_appr_doc_no;
	private String from_home_station_nm;
	private BigDecimal contract_work_month;
	private Integer salary_pay_plan_dt;
	private String bp_comp_id;
	private String bp_comp_emp_nm;
	private String bp_comp_url;
	private String bp_my_number;
	private String bp_post_no;
	private String bp_addr;
	private String bp_tel_no;
	private String bp_fax_no;
	private String bp_staff_nm;
	private String bp_staff_nm_fuli;
	private String bp_staff_dept_nm;
	private String bp_staff_position;
	private String bp_staff_tel_no;
	private String bp_staff_fax_no;
	private String bp_staff_mobile_no;
	private String bp_staff_mail_addr;
	private String bp_staff_mail_addr_cc;
	private String staff_addr;
	private String staff_post_no;
	private String staff_mail_addr;
	private String staff_tel_no;
	private String staff_mobile_no;
	private String bp_accounting_pat_k;
	private String bp_salary_pay_k;
	private BigDecimal bp_wh_max;
	private BigDecimal bp_wh_min;
	private BigDecimal bp_wh_mid;
	private BigDecimal bp_excess_price;
	private BigDecimal bp_deducation_price;
	private String bp_work_unit_k;
	@JSONField (format="HH:mm") 
	private Date bp_work_start_time;
	@JSONField (format="HH:mm") 
	private Date bp_work_end_time;
	private Integer bp_break_time_min;

	private String bp_payment_due_k;
	private String bp_pay_month_k;
	private String bp_pay_plan_d;
	private Integer bp_salary_pay_plan_dt;	
	private BigDecimal sum_contract_wm;

	private String mon_attach_file_k;
	private Integer mon_attach_file_id;
	private String procedure_method_k;
	private String withdrawal_reason_k;
	private String withdrawal_letter_k;
	private String insurance_con_k;
	private String withdrawal_req_doc;
	@JSONField (format="yyyy-MM-dd") 
	private Date procedure_dt;
	private String after_withdrawal_post_no;
	private String after_withdrawal_addr;
	private String purchase_remark;
	private String commercial_dist_remark;
	private String emp_remark;
	private String mon_req_st;
	private String mon_attach_file_remark;
	private String mon_add_remark;
	private String sys_ins_user_id;
	private Date sys_ins_dt;
	private String sys_ins_pg_id;
	private String sys_upd_user_id;
	private Date sys_upd_dt;
	private String sys_upd_pg_id;
	  
	private List<CommutRoute> commute_route_list;
	private List<EvidenceInfo> work_evidence_list;
	private List<FileInfo> fileList;
	
	//個人情報変更Flg:Y：変更あり、N：変更なし
	private String emp_info_chg_flg;
	//手続き区分:
	//1:新規入社処理
	//2:契約期間更新処理
	//3:退社処理
	private String procedure_k;
	
    /**
     * 役職手当金額
     */
	private BigDecimal appoint_allow_amt;
    /**
     * 住宅手当金額
     */
	private BigDecimal house_allow_amt;
    /**
     * 通信手当金額
     */
	private BigDecimal communication_allow_amt;
    /**
     * 現場手当金額
     */
	private BigDecimal site_allow_amt;
    /**
     * 残業手当金額
     */
	private BigDecimal overwork_allow_amt;
    /**
     * そのた手当1金額
     */
	private BigDecimal other_allow1_amt;
    /**
     * そのた手当２金額
     */
	private BigDecimal other_allow2_amt;
	
	
	public List<FileInfo> getFileList() {
		return fileList;
	}
	public void setFileList(List<FileInfo> fileList) {
		this.fileList = fileList;
	}
	public List<EvidenceInfo> getWork_evidence_list() {
		return work_evidence_list;
	}
	public void setWork_evidence_list(List<EvidenceInfo> work_evidence_list) {
		this.work_evidence_list = work_evidence_list;
	}
	public boolean isDina_reg_f() {
		return dina_reg_f;
	}
	public void setDina_reg_f(boolean dina_reg_f) {
		this.dina_reg_f = dina_reg_f;
	}
	public Integer getAppr_doc_id() {
		return appr_doc_id;
	}
	public void setAppr_doc_id(Integer appr_doc_id) {
		this.appr_doc_id = appr_doc_id;
	}
	public String getUse_flg() {
		return use_flg;
	}
	public void setUse_flg(String use_flg) {
		this.use_flg = use_flg;
	}
	public String getAppr_doc_st() {
		return appr_doc_st;
	}
	public void setAppr_doc_st(String appr_doc_st) {
		this.appr_doc_st = appr_doc_st;
	}
	public String getOld_appr_doc_no() {
		return old_appr_doc_no;
	}
	public void setOld_appr_doc_no(String old_appr_doc_no) {
		this.old_appr_doc_no = old_appr_doc_no;
	}
	public String getFrom_home_station_nm() {
		return from_home_station_nm;
	}
	public void setFrom_home_station_nm(String from_home_station_nm) {
		this.from_home_station_nm = from_home_station_nm;
	}
	public BigDecimal getContract_work_month() {
		return contract_work_month;
	}
	public void setContract_work_month(BigDecimal contract_work_month) {
		this.contract_work_month = contract_work_month;
	}
	public Integer getSalary_pay_plan_dt() {
		return salary_pay_plan_dt;
	}
	public void setSalary_pay_plan_dt(Integer salary_pay_plan_dt) {
		this.salary_pay_plan_dt = salary_pay_plan_dt;
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
	public String getBp_comp_url() {
		return bp_comp_url;
	}
	public void setBp_comp_url(String bp_comp_url) {
		this.bp_comp_url = bp_comp_url;
	}
	public String getBp_my_number() {
		return bp_my_number;
	}
	public void setBp_my_number(String bp_my_number) {
		this.bp_my_number = bp_my_number;
	}
	public String getBp_post_no() {
		return bp_post_no;
	}
	public void setBp_post_no(String bp_post_no) {
		this.bp_post_no = bp_post_no;
	}
	public String getBp_addr() {
		return bp_addr;
	}
	public void setBp_addr(String bp_addr) {
		this.bp_addr = bp_addr;
	}
	public String getBp_tel_no() {
		return bp_tel_no;
	}
	public void setBp_tel_no(String bp_tel_no) {
		this.bp_tel_no = bp_tel_no;
	}
	public String getBp_fax_no() {
		return bp_fax_no;
	}
	public void setBp_fax_no(String bp_fax_no) {
		this.bp_fax_no = bp_fax_no;
	}
	public String getBp_staff_nm() {
		return bp_staff_nm;
	}
	public void setBp_staff_nm(String bp_staff_nm) {
		this.bp_staff_nm = bp_staff_nm;
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
	public String getBp_staff_tel_no() {
		return bp_staff_tel_no;
	}
	public void setBp_staff_tel_no(String bp_staff_tel_no) {
		this.bp_staff_tel_no = bp_staff_tel_no;
	}
	public String getBp_staff_fax_no() {
		return bp_staff_fax_no;
	}
	public void setBp_staff_fax_no(String bp_staff_fax_no) {
		this.bp_staff_fax_no = bp_staff_fax_no;
	}
	public String getBp_staff_mobile_no() {
		return bp_staff_mobile_no;
	}
	public void setBp_staff_mobile_no(String bp_staff_mobile_no) {
		this.bp_staff_mobile_no = bp_staff_mobile_no;
	}
	public String getBp_staff_mail_addr() {
		return bp_staff_mail_addr;
	}
	public void setBp_staff_mail_addr(String bp_staff_mail_addr) {
		this.bp_staff_mail_addr = bp_staff_mail_addr;
	}
	public String getBp_staff_mail_addr_cc() {
		return bp_staff_mail_addr_cc;
	}
	public void setBp_staff_mail_addr_cc(String bp_staff_mail_addr_cc) {
		this.bp_staff_mail_addr_cc = bp_staff_mail_addr_cc;
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
	public String getStaff_mail_addr() {
		return staff_mail_addr;
	}
	public void setStaff_mail_addr(String staff_mail_addr) {
		this.staff_mail_addr = staff_mail_addr;
	}
	public String getStaff_tel_no() {
		return staff_tel_no;
	}
	public void setStaff_tel_no(String staff_tel_no) {
		this.staff_tel_no = staff_tel_no;
	}
	public String getStaff_mobile_no() {
		return staff_mobile_no;
	}
	public void setStaff_mobile_no(String staff_mobile_no) {
		this.staff_mobile_no = staff_mobile_no;
	}
	public String getBp_accounting_pat_k() {
		return bp_accounting_pat_k;
	}
	public void setBp_accounting_pat_k(String bp_accounting_pat_k) {
		this.bp_accounting_pat_k = bp_accounting_pat_k;
	}
	public String getBp_salary_pay_k() {
		return bp_salary_pay_k;
	}
	public void setBp_salary_pay_k(String bp_salary_pay_k) {
		this.bp_salary_pay_k = bp_salary_pay_k;
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
	public BigDecimal getBp_wh_mid() {
		return bp_wh_mid;
	}
	public void setBp_wh_mid(BigDecimal bp_wh_mid) {
		this.bp_wh_mid = bp_wh_mid;
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
	public Integer getBp_break_time_min() {
		return bp_break_time_min;
	}
	public void setBp_break_time_min(Integer bp_break_time_min) {
		this.bp_break_time_min = bp_break_time_min;
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
	public String getBp_pay_plan_d() {
		return bp_pay_plan_d;
	}
	public void setBp_pay_plan_d(String bp_pay_plan_d) {
		this.bp_pay_plan_d = bp_pay_plan_d;
	}
	public Integer getBp_salary_pay_plan_dt() {
		return bp_salary_pay_plan_dt;
	}
	public void setBp_salary_pay_plan_dt(Integer bp_salary_pay_plan_dt) {
		this.bp_salary_pay_plan_dt = bp_salary_pay_plan_dt;
	}
	public String getMon_attach_file_k() {
		return mon_attach_file_k;
	}
	public void setMon_attach_file_k(String mon_attach_file_k) {
		this.mon_attach_file_k = mon_attach_file_k;
	}
	public Integer getMon_attach_file_id() {
		return mon_attach_file_id;
	}
	public void setMon_attach_file_id(Integer mon_attach_file_id) {
		this.mon_attach_file_id = mon_attach_file_id;
	}
	public String getProcedure_method_k() {
		return procedure_method_k;
	}
	public void setProcedure_method_k(String procedure_method_k) {
		this.procedure_method_k = procedure_method_k;
	}
	public String getWithdrawal_reason_k() {
		return withdrawal_reason_k;
	}
	public void setWithdrawal_reason_k(String withdrawal_reason_k) {
		this.withdrawal_reason_k = withdrawal_reason_k;
	}
	public String getWithdrawal_letter_k() {
		return withdrawal_letter_k;
	}
	public void setWithdrawal_letter_k(String withdrawal_letter_k) {
		this.withdrawal_letter_k = withdrawal_letter_k;
	}
	public String getInsurance_con_k() {
		return insurance_con_k;
	}
	public void setInsurance_con_k(String insurance_con_k) {
		this.insurance_con_k = insurance_con_k;
	}
	public String getWithdrawal_req_doc() {
		return withdrawal_req_doc;
	}
	public void setWithdrawal_req_doc(String withdrawal_req_doc) {
		this.withdrawal_req_doc = withdrawal_req_doc;
	}
	public Date getProcedure_dt() {
		return procedure_dt;
	}
	public void setProcedure_dt(Date procedure_dt) {
		this.procedure_dt = procedure_dt;
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
	public String getPurchase_remark() {
		return purchase_remark;
	}
	public void setPurchase_remark(String purchase_remark) {
		this.purchase_remark = purchase_remark;
	}
	public String getCommercial_dist_remark() {
		return commercial_dist_remark;
	}
	public void setCommercial_dist_remark(String commercial_dist_remark) {
		this.commercial_dist_remark = commercial_dist_remark;
	}
	public String getEmp_remark() {
		return emp_remark;
	}
	public void setEmp_remark(String emp_remark) {
		this.emp_remark = emp_remark;
	}
	public List<CommutRoute> getCommute_route_list() {
		return commute_route_list;
	}
	public void setCommute_route_list(List<CommutRoute> commute_route_list) {
		this.commute_route_list = commute_route_list;
	}
	public String getAppr_doc_no() {
		return appr_doc_no;
	}
	public String getPj_cd() {
		return pj_cd;
	}
	public void setPj_cd(String pj_cd) {
		this.pj_cd = pj_cd;
	}
	public String getPj_nm() {
		return pj_nm;
	}
	public void setPj_nm(String pj_nm) {
		this.pj_nm = pj_nm;
	}
	public Date getEntrance_dt() {
		return entrance_dt;
	}
	public void setEntrance_dt(Date entrance_dt) {
		this.entrance_dt = entrance_dt;
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
	public String getDept_cd() {
		return dept_cd;
	}
	public void setDept_cd(String dept_cd) {
		this.dept_cd = dept_cd;
	}
	public void setAppr_doc_no(String appr_doc_no) {
		this.appr_doc_no = appr_doc_no;
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
	public String getEmp_nm_fuli() {
		return emp_nm_fuli;
	}
	public void setEmp_nm_fuli(String emp_nm_fuli) {
		this.emp_nm_fuli = emp_nm_fuli;
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
	public Integer getEmp_age() {
		return emp_age;
	}
	public void setEmp_age(Integer emp_age) {
		this.emp_age = emp_age;
	}
	public Date getWithdrawal_dt() {
		return withdrawal_dt;
	}
	public void setWithdrawal_dt(Date withdrawal_dt) {
		this.withdrawal_dt = withdrawal_dt;
	}
	public String getEntrance_record_k() {
		return entrance_record_k;
	}
	public void setEntrance_record_k(String entrance_record_k) {
		this.entrance_record_k = entrance_record_k;
	}
	public String getEmp_my_number() {
		return emp_my_number;
	}
	public void setEmp_my_number(String emp_my_number) {
		this.emp_my_number = emp_my_number;
	}
	public String getEmp_pat_k() {
		return emp_pat_k;
	}
	public void setEmp_pat_k(String emp_pat_k) {
		this.emp_pat_k = emp_pat_k;
	}
	public String getInsurance_k() {
		return insurance_k;
	}
	public void setInsurance_k(String insurance_k) {
		this.insurance_k = insurance_k;
	}
	public Date getInsurance_join_ymd() {
		return insurance_join_ymd;
	}
	public void setInsurance_join_ymd(Date insurance_join_ymd) {
		this.insurance_join_ymd = insurance_join_ymd;
	}
	public String getAffiliation_k() {
		return affiliation_k;
	}
	public void setAffiliation_k(String affiliation_k) {
		this.affiliation_k = affiliation_k;
	}
	public String getClient_no() {
		return client_no;
	}
	public void setClient_no(String client_no) {
		this.client_no = client_no;
	}
	public String getClient_contract_k() {
		return client_contract_k;
	}
	public void setClient_contract_k(String client_contract_k) {
		this.client_contract_k = client_contract_k;
	}
	public String getClient_order_no() {
		return client_order_no;
	}
	public void setClient_order_no(String client_order_no) {
		this.client_order_no = client_order_no;
	}
	public String getClient_nm() {
		return client_nm;
	}
	public void setClient_nm(String client_nm) {
		this.client_nm = client_nm;
	}
	public String getTake_over_k() {
		return take_over_k;
	}
	public void setTake_over_k(String take_over_k) {
		this.take_over_k = take_over_k;
	}
	public Date getOrder_recv_hope_dt() {
		return order_recv_hope_dt;
	}
	public void setOrder_recv_hope_dt(Date order_recv_hope_dt) {
		this.order_recv_hope_dt = order_recv_hope_dt;
	}
	public Integer getBusiness_days() {
		return business_days;
	}
	public void setBusiness_days(Integer business_days) {
		this.business_days = business_days;
	}
	public Integer getWork_days() {
		return work_days;
	}
	public void setWork_days(Integer work_days) {
		this.work_days = work_days;
	}
	public Date getSales_addup_month() {
		return sales_addup_month;
	}
	public void setSales_addup_month(Date sales_addup_month) {
		this.sales_addup_month = sales_addup_month;
	}
	public Date getBilling_month() {
		return billing_month;
	}
	public void setBilling_month(Date billing_month) {
		this.billing_month = billing_month;
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
	public BigDecimal getOther_cost() {
		return other_cost;
	}
	public void setOther_cost(BigDecimal other_cost) {
		this.other_cost = other_cost;
	}
	public BigDecimal getOther_cost_first_mon() {
		return other_cost_first_mon;
	}
	public void setOther_cost_first_mon(BigDecimal other_cost_first_mon) {
		this.other_cost_first_mon = other_cost_first_mon;
	}
	public String getPayment_plan_d() {
		return payment_plan_d;
	}
	public void setPayment_plan_d(String payment_plan_d) {
		this.payment_plan_d = payment_plan_d;
	}
	public String getBp_payment_plan_d() {
		return bp_payment_plan_d;
	}
	public void setBp_payment_plan_d(String bp_payment_plan_d) {
		this.bp_payment_plan_d = bp_payment_plan_d;
	}
	public BigDecimal getTravel_cost_first_mon() {
		return travel_cost_first_mon;
	}
	public void setTravel_cost_first_mon(BigDecimal travel_cost_first_mon) {
		this.travel_cost_first_mon = travel_cost_first_mon;
	}
	public BigDecimal getTravel_cost() {
		return travel_cost;
	}
	public void setTravel_cost(BigDecimal travel_cost) {
		this.travel_cost = travel_cost;
	}
	public BigDecimal getInsurance_cost() {
		return insurance_cost;
	}
	public void setInsurance_cost(BigDecimal insurance_cost) {
		this.insurance_cost = insurance_cost;
	}
	public BigDecimal getSite_allow_ratio() {
		return site_allow_ratio;
	}
	public void setSite_allow_ratio(BigDecimal site_allow_ratio) {
		this.site_allow_ratio = site_allow_ratio;
	}
	public BigDecimal getOverwork_allow_ratio() {
		return overwork_allow_ratio;
	}
	public void setOverwork_allow_ratio(BigDecimal overwork_allow_ratio) {
		this.overwork_allow_ratio = overwork_allow_ratio;
	}
	public BigDecimal getAppoint_allow_ratio() {
		return appoint_allow_ratio;
	}
	public void setAppoint_allow_ratio(BigDecimal appoint_allow_ratio) {
		this.appoint_allow_ratio = appoint_allow_ratio;
	}
	public BigDecimal getCommunication_allow_ratio() {
		return communication_allow_ratio;
	}
	public void setCommunication_allow_ratio(BigDecimal communication_allow_ratio) {
		this.communication_allow_ratio = communication_allow_ratio;
	}
	public BigDecimal getHouse_allow_ratio() {
		return house_allow_ratio;
	}
	public void setHouse_allow_ratio(BigDecimal house_allow_ratio) {
		this.house_allow_ratio = house_allow_ratio;
	}
	public String getOther_allow1_nm() {
		return other_allow1_nm;
	}
	public void setOther_allow1_nm(String other_allow1_nm) {
		this.other_allow1_nm = other_allow1_nm;
	}
	public BigDecimal getOther_allow1_ratio() {
		return other_allow1_ratio;
	}
	public void setOther_allow1_ratio(BigDecimal other_allow1_ratio) {
		this.other_allow1_ratio = other_allow1_ratio;
	}
	public String getOther_allow2_nm() {
		return other_allow2_nm;
	}
	public void setOther_allow2_nm(String other_allow2_nm) {
		this.other_allow2_nm = other_allow2_nm;
	}
	public BigDecimal getOther_allow2_ratio() {
		return other_allow2_ratio;
	}
	public void setOther_allow2_ratio(BigDecimal other_allow2_ratio) {
		this.other_allow2_ratio = other_allow2_ratio;
	}
	public String getClient_url() {
		return client_url;
	}
	public void setClient_url(String client_url) {
		this.client_url = client_url;
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
	public String getClient_my_number() {
		return client_my_number;
	}
	public void setClient_my_number(String client_my_number) {
		this.client_my_number = client_my_number;
	}
	public String getClient_staff_nm() {
		return client_staff_nm;
	}
	public void setClient_staff_nm(String client_staff_nm) {
		this.client_staff_nm = client_staff_nm;
	}
	public String getClient_staff_nm_fuli() {
		return client_staff_nm_fuli;
	}
	public void setClient_staff_nm_fuli(String client_staff_nm_fuli) {
		this.client_staff_nm_fuli = client_staff_nm_fuli;
	}
	public String getClient_staff_dept_nm() {
		return client_staff_dept_nm;
	}
	public void setClient_staff_dept_nm(String client_staff_dept_nm) {
		this.client_staff_dept_nm = client_staff_dept_nm;
	}
	public String getClient_mail_addr() {
		return client_mail_addr;
	}
	public void setClient_mail_addr(String client_mail_addr) {
		this.client_mail_addr = client_mail_addr;
	}
	public String getClient_staff_position() {
		return client_staff_position;
	}
	public void setClient_staff_position(String client_staff_position) {
		this.client_staff_position = client_staff_position;
	}
	public String getClient_staff_mobile_no() {
		return client_staff_mobile_no;
	}
	public void setClient_staff_mobile_no(String client_staff_mobile_no) {
		this.client_staff_mobile_no = client_staff_mobile_no;
	}
	public String getClient_mail_addr_cc() {
		return client_mail_addr_cc;
	}
	public void setClient_mail_addr_cc(String client_mail_addr_cc) {
		this.client_mail_addr_cc = client_mail_addr_cc;
	}
	public String getClient_req_staff_nm() {
		return client_req_staff_nm;
	}
	public void setClient_req_staff_nm(String client_req_staff_nm) {
		this.client_req_staff_nm = client_req_staff_nm;
	}
	public String getClient_req_staff_nm_fuli() {
		return client_req_staff_nm_fuli;
	}
	public void setClient_req_staff_nm_fuli(String client_req_staff_nm_fuli) {
		this.client_req_staff_nm_fuli = client_req_staff_nm_fuli;
	}
	public String getClient_req_staff_dept_nm() {
		return client_req_staff_dept_nm;
	}
	public void setClient_req_staff_dept_nm(String client_req_staff_dept_nm) {
		this.client_req_staff_dept_nm = client_req_staff_dept_nm;
	}
	public String getClient_req_staff_position() {
		return client_req_staff_position;
	}
	public void setClient_req_staff_position(String client_req_staff_position) {
		this.client_req_staff_position = client_req_staff_position;
	}
	public String getClient_req_tel_no() {
		return client_req_tel_no;
	}
	public void setClient_req_tel_no(String client_req_tel_no) {
		this.client_req_tel_no = client_req_tel_no;
	}
	public String getClient_req_fax_no() {
		return client_req_fax_no;
	}
	public void setClient_req_fax_no(String client_req_fax_no) {
		this.client_req_fax_no = client_req_fax_no;
	}
	public String getClient_req_staff_mobile_no() {
		return client_req_staff_mobile_no;
	}
	public void setClient_req_staff_mobile_no(String client_req_staff_mobile_no) {
		this.client_req_staff_mobile_no = client_req_staff_mobile_no;
	}
	public String getClient_req_mail_addr() {
		return client_req_mail_addr;
	}
	public void setClient_req_mail_addr(String client_req_mail_addr) {
		this.client_req_mail_addr = client_req_mail_addr;
	}
	public String getClient_req_mail_addr_cc() {
		return client_req_mail_addr_cc;
	}
	public void setClient_req_mail_addr_cc(String client_req_mail_addr_cc) {
		this.client_req_mail_addr_cc = client_req_mail_addr_cc;
	}
	public String getClient_req_post_no() {
		return client_req_post_no;
	}
	public void setClient_req_post_no(String client_req_post_no) {
		this.client_req_post_no = client_req_post_no;
	}
	public String getClient_req_addr() {
		return client_req_addr;
	}
	public void setClient_req_addr(String client_req_addr) {
		this.client_req_addr = client_req_addr;
	}
	public String getMon_pro_k() {
		return mon_pro_k;
	}
	public void setMon_pro_k(String mon_pro_k) {
		this.mon_pro_k = mon_pro_k;
	}
	public String getWork_rep_f() {
		return work_rep_f;
	}
	public void setWork_rep_f(String work_rep_f) {
		this.work_rep_f = work_rep_f;
	}
	public String getWork_rep_dl_k() {
		return work_rep_dl_k;
	}
	public void setWork_rep_dl_k(String work_rep_dl_k) {
		this.work_rep_dl_k = work_rep_dl_k;
	}
	public String getBill_f() {
		return bill_f;
	}
	public void setBill_f(String bill_f) {
		this.bill_f = bill_f;
	}
	public String getBill_dl_k() {
		return bill_dl_k;
	}
	public void setBill_dl_k(String bill_dl_k) {
		this.bill_dl_k = bill_dl_k;
	}
	public String getWork_place() {
		return work_place;
	}
	public void setWork_place(String work_place) {
		this.work_place = work_place;
	}
	public String getWork_content() {
		return work_content;
	}
	public void setWork_content(String work_content) {
		this.work_content = work_content;
	}
	public String getSalary_pay_k() {
		return salary_pay_k;
	}
	public void setSalary_pay_k(String salary_pay_k) {
		this.salary_pay_k = salary_pay_k;
	}
	public String getAccounting_pat_k() {
		return accounting_pat_k;
	}
	public void setAccounting_pat_k(String accounting_pat_k) {
		this.accounting_pat_k = accounting_pat_k;
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
	public BigDecimal getWh_mid() {
		return wh_mid;
	}
	public void setWh_mid(BigDecimal wh_mid) {
		this.wh_mid = wh_mid;
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
	public String getWork_time_unit_k() {
		return work_time_unit_k;
	}
	public void setWork_time_unit_k(String work_time_unit_k) {
		this.work_time_unit_k = work_time_unit_k;
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
	public String getPj_class_nm() {
		return pj_class_nm;
	}
	public void setPj_class_nm(String pj_class_nm) {
		this.pj_class_nm = pj_class_nm;
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
	public Integer getBreak_time_min() {
		return break_time_min;
	}
	public void setBreak_time_min(Integer break_time_min) {
		this.break_time_min = break_time_min;
	}
	public String getPayment_due_k() {
		return payment_due_k;
	}
	public void setPayment_due_k(String payment_due_k) {
		this.payment_due_k = payment_due_k;
	}
	public String getPay_month_k() {
		return pay_month_k;
	}
	public void setPay_month_k(String pay_month_k) {
		this.pay_month_k = pay_month_k;
	}
	public String getPay_plan_d() {
		return pay_plan_d;
	}
	public void setPay_plan_d(String pay_plan_d) {
		this.pay_plan_d = pay_plan_d;
	}
	public Date getSite_entrance_dt() {
		return site_entrance_dt;
	}
	public void setSite_entrance_dt(Date site_entrance_dt) {
		this.site_entrance_dt = site_entrance_dt;
	}
	public String getClient_staff_tel_no() {
		return client_staff_tel_no;
	}
	public void setClient_staff_tel_no(String client_staff_tel_no) {
		this.client_staff_tel_no = client_staff_tel_no;
	}
	public String getClient_staff_fax_no() {
		return client_staff_fax_no;
	}
	public void setClient_staff_fax_no(String client_staff_fax_no) {
		this.client_staff_fax_no = client_staff_fax_no;
	}
	public String getEmp_info_chg_flg() {
		return emp_info_chg_flg;
	}
	public void setEmp_info_chg_flg(String emp_info_chg_flg) {
		this.emp_info_chg_flg = emp_info_chg_flg;
	}
	public String getProcedure_k() {
		return procedure_k;
	}
	public void setProcedure_k(String procedure_k) {
		this.procedure_k = procedure_k;
	}
	public String getSys_ins_user_id() {
		return sys_ins_user_id;
	}
	public void setSys_ins_user_id(String sys_ins_user_id) {
		this.sys_ins_user_id = sys_ins_user_id;
	}
	public Date getSys_ins_dt() {
		return sys_ins_dt;
	}
	public void setSys_ins_dt(Date sys_ins_dt) {
		this.sys_ins_dt = sys_ins_dt;
	}
	public String getSys_ins_pg_id() {
		return sys_ins_pg_id;
	}
	public void setSys_ins_pg_id(String sys_ins_pg_id) {
		this.sys_ins_pg_id = sys_ins_pg_id;
	}
	public String getSys_upd_user_id() {
		return sys_upd_user_id;
	}
	public void setSys_upd_user_id(String sys_upd_user_id) {
		this.sys_upd_user_id = sys_upd_user_id;
	}
	public Date getSys_upd_dt() {
		return sys_upd_dt;
	}
	public void setSys_upd_dt(Date sys_upd_dt) {
		this.sys_upd_dt = sys_upd_dt;
	}
	public String getSys_upd_pg_id() {
		return sys_upd_pg_id;
	}
	public void setSys_upd_pg_id(String sys_upd_pg_id) {
		this.sys_upd_pg_id = sys_upd_pg_id;
	}
	public BigDecimal getAppoint_allow_amt() {
		return appoint_allow_amt;
	}
	public void setAppoint_allow_amt(BigDecimal appoint_allow_amt) {
		this.appoint_allow_amt = appoint_allow_amt;
	}
	public BigDecimal getHouse_allow_amt() {
		return house_allow_amt;
	}
	public void setHouse_allow_amt(BigDecimal house_allow_amt) {
		this.house_allow_amt = house_allow_amt;
	}
	public BigDecimal getCommunication_allow_amt() {
		return communication_allow_amt;
	}
	public void setCommunication_allow_amt(BigDecimal communication_allow_amt) {
		this.communication_allow_amt = communication_allow_amt;
	}
	public BigDecimal getSite_allow_amt() {
		return site_allow_amt;
	}
	public void setSite_allow_amt(BigDecimal site_allow_amt) {
		this.site_allow_amt = site_allow_amt;
	}
	public BigDecimal getOverwork_allow_amt() {
		return overwork_allow_amt;
	}
	public void setOverwork_allow_amt(BigDecimal overwork_allow_amt) {
		this.overwork_allow_amt = overwork_allow_amt;
	}
	public BigDecimal getOther_allow1_amt() {
		return other_allow1_amt;
	}
	public void setOther_allow1_amt(BigDecimal other_allow1_amt) {
		this.other_allow1_amt = other_allow1_amt;
	}
	public BigDecimal getOther_allow2_amt() {
		return other_allow2_amt;
	}
	public void setOther_allow2_amt(BigDecimal other_allow2_amt) {
		this.other_allow2_amt = other_allow2_amt;
	}
	public BigDecimal getSum_contract_wm() {
		return sum_contract_wm;
	}
	public void setSum_contract_wm(BigDecimal sum_contract_wm) {
		this.sum_contract_wm = sum_contract_wm;
	}
	public String getMon_req_st() {
		return mon_req_st;
	}
	public void setMon_req_st(String mon_req_st) {
		this.mon_req_st = mon_req_st;
	}
	public String getMon_attach_file_remark() {
		return mon_attach_file_remark;
	}
	public void setMon_attach_file_remark(String mon_attach_file_remark) {
		this.mon_attach_file_remark = mon_attach_file_remark;
	}
	public String getMon_add_remark() {
		return mon_add_remark;
	}
	public void setMon_add_remark(String mon_add_remark) {
		this.mon_add_remark = mon_add_remark;
	}	
	
	
	
	
	/*
	private List<> appr_doc_apply_dtl;
	private List<> work_evidence_list;*/
	

	/*
	 * commute_route_list:[ { from_home_line_nm:'XXXX',//自宅最寄駅の線
	 * from_home_station_nm:'XXX',//自宅最寄駅の駅 to_comp_line_nm:'△△△',//現場最寄駅の線
	 * to_comp_station_nm:'○○○',//現場最寄駅の駅 travel_cost:'1000'//通勤経路の交通費 }, {
	 * from_home_line_nm:'□□□□',//自宅最寄駅の線 from_home_station_nm:'★★★',//自宅最寄駅の駅
	 * to_comp_line_nm:'※※※',//現場最寄駅の線 to_comp_station_nm:'○○○',//現場最寄駅の駅
	 * travel_cost:'1200'//通勤経路の交通費 } ],
	 */
	// WF申請経路
	/*
	 * appr_doc_apply_dtl:[ { display_order:1, accept_user_id:'10001',
	 * accept_user_nm:'山田 真一', job_title_cd :'001', job_title_nm :'営業担当', }, {
	 * display_order:2, accept_user_id:'20001', accept_user_nm:'伊藤 太郎',
	 * job_title_cd :'002', job_title_nm :'課長', }, { display_order:3,
	 * accept_user_id:'30001', accept_user_nm:'坂本 剛', job_title_cd :'003',
	 * job_title_nm :'部長', }, { display_order:4, accept_user_id:'40001',
	 * accept_user_nm:'XX XX', job_title_cd :'900', job_title_nm :'社長', }, ],
	 */
	// work_evidence_list:['XXXXXXXXXXXXXXXXXXXXXXXXXXX\r\n○○○○○○○○○○○○○○\r\n△△△△△△△△△△△△△','222222222222222222223333333333333333333333334444444444444'],
}

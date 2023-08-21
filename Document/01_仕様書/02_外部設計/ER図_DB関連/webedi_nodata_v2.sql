-- MySQL dump 10.13  Distrib 5.7.17, for Linux (x86_64)
--
-- Host: localhost    Database: webedi
-- ------------------------------------------------------
-- Server version	5.7.17

/*!40101 SET @OLD_CHARACTER_SET_CLIENT=@@CHARACTER_SET_CLIENT */;
/*!40101 SET @OLD_CHARACTER_SET_RESULTS=@@CHARACTER_SET_RESULTS */;
/*!40101 SET @OLD_COLLATION_CONNECTION=@@COLLATION_CONNECTION */;
/*!40101 SET NAMES utf8 */;
/*!40103 SET @OLD_TIME_ZONE=@@TIME_ZONE */;
/*!40103 SET TIME_ZONE='+00:00' */;
/*!40014 SET @OLD_UNIQUE_CHECKS=@@UNIQUE_CHECKS, UNIQUE_CHECKS=0 */;
/*!40014 SET @OLD_FOREIGN_KEY_CHECKS=@@FOREIGN_KEY_CHECKS, FOREIGN_KEY_CHECKS=0 */;
/*!40101 SET @OLD_SQL_MODE=@@SQL_MODE, SQL_MODE='NO_AUTO_VALUE_ON_ZERO' */;
/*!40111 SET @OLD_SQL_NOTES=@@SQL_NOTES, SQL_NOTES=0 */;

--
-- Table structure for table `apprapplydtl`
--

DROP TABLE IF EXISTS `apprapplydtl`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `apprapplydtl` (
  `appr_apply_did` int(11) NOT NULL AUTO_INCREMENT COMMENT '稟議書申請DID',
  `appr_apply_hid` int(11) NOT NULL COMMENT '稟議書申請HID',
  `display_order` int(11) NOT NULL COMMENT '表示順',
  `accept_user_id` varchar(10) COLLATE utf8_bin NOT NULL COMMENT '承認者ID',
  `confirm_st` char(1) COLLATE utf8_bin NOT NULL COMMENT '承認状況:0：申請済（申請者はWFの一つ目に表示する）\n1：未承認\n2：同意\n3：拒否',
  `con_rej_comment` varchar(500) COLLATE utf8_bin NOT NULL COMMENT '承認(拒否)コメント',
  `con_rej_dt` datetime DEFAULT NULL COMMENT '承認(拒否)日時',
  `sys_ins_user_id` varchar(10) COLLATE utf8_bin NOT NULL COMMENT '登録者',
  `sys_ins_dt` datetime NOT NULL COMMENT '登録日時',
  `sys_ins_pg_id` varchar(15) COLLATE utf8_bin NOT NULL COMMENT '登録機能ID',
  `sys_upd_user_id` varchar(10) COLLATE utf8_bin DEFAULT NULL COMMENT '更新者',
  `sys_upd_dt` datetime DEFAULT NULL COMMENT '更新日時',
  `sys_upd_pg_id` varchar(15) COLLATE utf8_bin DEFAULT NULL COMMENT '更新機能ID',
  PRIMARY KEY (`appr_apply_did`),
  UNIQUE KEY `apprApplyDtl_u_1` (`appr_apply_hid`,`display_order`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COLLATE=utf8_bin COMMENT='稟議書申請DTL情報';
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Table structure for table `apprapplyhdr`
--

DROP TABLE IF EXISTS `apprapplyhdr`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `apprapplyhdr` (
  `appr_apply_hid` int(11) NOT NULL AUTO_INCREMENT COMMENT '稟議書申請HID',
  `appr_doc_id` int(11) NOT NULL COMMENT '稟議書ID',
  `use_flg` char(1) COLLATE utf8_bin NOT NULL DEFAULT 'Y' COMMENT '使用FLG:Y:使用\nN:削除',
  `apply_reason` varchar(500) COLLATE utf8_bin DEFAULT NULL COMMENT '申請理由',
  `apply_user_id` varchar(10) COLLATE utf8_bin NOT NULL COMMENT '申請者ID',
  `apply_dt` datetime NOT NULL COMMENT '申請日時',
  `confirm_wait_display_order` int(11) DEFAULT NULL COMMENT '承認待表示順:承認待となっている申請DTLの表示順を設定',
  `sys_ins_user_id` varchar(10) COLLATE utf8_bin NOT NULL COMMENT '登録者',
  `sys_ins_dt` datetime NOT NULL COMMENT '登録日時',
  `sys_ins_pg_id` varchar(15) COLLATE utf8_bin NOT NULL COMMENT '登録機能ID',
  `sys_upd_user_id` varchar(10) COLLATE utf8_bin DEFAULT NULL COMMENT '更新者',
  `sys_upd_dt` datetime DEFAULT NULL COMMENT '更新日時',
  `sys_upd_pg_id` varchar(15) COLLATE utf8_bin DEFAULT NULL COMMENT '更新機能ID',
  PRIMARY KEY (`appr_apply_hid`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COLLATE=utf8_bin COMMENT='稟議書申請HDR情報';
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Table structure for table `apprcommuteroutedtl`
--

DROP TABLE IF EXISTS `apprcommuteroutedtl`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `apprcommuteroutedtl` (
  `appr_commute_route_id` int(11) NOT NULL AUTO_INCREMENT COMMENT '通勤経路ID',
  `appr_doc_id` int(11) NOT NULL COMMENT '稟議書ID',
  `from_home_line_nm` varchar(30) COLLATE utf8_bin DEFAULT NULL COMMENT '自宅最寄駅の線',
  `from_home_station_nm` varchar(30) COLLATE utf8_bin DEFAULT NULL COMMENT '自宅最寄駅の駅',
  `to_comp_line_nm` varchar(30) COLLATE utf8_bin DEFAULT NULL COMMENT '現場最寄駅の線',
  `to_comp_station_nm` varchar(30) COLLATE utf8_bin DEFAULT NULL COMMENT '現場最寄駅の駅',
  `travel_cost` decimal(10,1) DEFAULT NULL COMMENT '通勤経路の交通費',
  `sys_ins_user_id` varchar(10) COLLATE utf8_bin NOT NULL COMMENT '登録者',
  `sys_ins_dt` datetime NOT NULL COMMENT '登録日時',
  `sys_ins_pg_id` varchar(15) COLLATE utf8_bin NOT NULL COMMENT '登録機能ID',
  `sys_upd_user_id` varchar(10) COLLATE utf8_bin DEFAULT NULL COMMENT '更新者',
  `sys_upd_dt` datetime DEFAULT NULL COMMENT '更新日時',
  `sys_upd_pg_id` varchar(15) COLLATE utf8_bin DEFAULT NULL COMMENT '更新機能ID',
  PRIMARY KEY (`appr_commute_route_id`)
) ENGINE=InnoDB AUTO_INCREMENT=11 DEFAULT CHARSET=utf8 COLLATE=utf8_bin COMMENT='通勤経路Dtl';
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Table structure for table `apprcommuteroutehdr`
--

DROP TABLE IF EXISTS `apprcommuteroutehdr`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `apprcommuteroutehdr` (
  `appr_doc_id` int(11) NOT NULL COMMENT '稟議書ID',
  `day_travel_cost` decimal(10,1) DEFAULT NULL COMMENT '交通費日額（往復）',
  `month_pass_travel_cost` decimal(10,1) DEFAULT NULL COMMENT '1ヶ月の通勤代（定期）',
  `pay_travel_costa_max` decimal(10,1) DEFAULT NULL COMMENT '支払上限',
  `rount_dlt_content` varchar(500) COLLATE utf8_bin DEFAULT NULL COMMENT '自宅→現場（乗り換え含め交通経路記入）',
  `sys_ins_user_id` varchar(10) COLLATE utf8_bin NOT NULL COMMENT '登録者',
  `sys_ins_dt` datetime NOT NULL COMMENT '登録日時',
  `sys_ins_pg_id` varchar(15) COLLATE utf8_bin NOT NULL COMMENT '登録機能ID',
  `sys_upd_user_id` varchar(10) COLLATE utf8_bin DEFAULT NULL COMMENT '更新者',
  `sys_upd_dt` datetime DEFAULT NULL COMMENT '更新日時',
  `sys_upd_pg_id` varchar(15) COLLATE utf8_bin DEFAULT NULL COMMENT '更新機能ID',
  PRIMARY KEY (`appr_doc_id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COLLATE=utf8_bin COMMENT='通勤経路Hdr';
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Table structure for table `apprdoc`
--

DROP TABLE IF EXISTS `apprdoc`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `apprdoc` (
  `appr_doc_id` int(11) NOT NULL AUTO_INCREMENT COMMENT '稟議書ID',
  `appr_doc_no` varchar(10) COLLATE utf8_bin NOT NULL COMMENT '稟議書NO',
  `use_flg` char(1) COLLATE utf8_bin NOT NULL DEFAULT 'Y' COMMENT '使用FLG:Y:使用\nN:削除',
  `appr_doc_dt_from` date NOT NULL COMMENT '対象期間From',
  `appr_doc_dt_to` date NOT NULL COMMENT '対象期間To',
  `appr_doc_create_k` char(1) COLLATE utf8_bin NOT NULL COMMENT '稟議書作成区分:1：通常更新\n2：新規\n3：条件変更\n4：正社員化\n5：退職\n6：再販',
  `appr_doc_create_dt` date NOT NULL COMMENT '稟議書作成日',
  `appr_doc_st` char(1) COLLATE utf8_bin NOT NULL COMMENT '稟議書状態:1：申請待    稟議書データ登録直後\n2：承認待    稟議書データ承認WFを申請後　※申請後、承認中前、申請取消が可能\n3：承認中    稟議書承認WFがスタート後\n4：承認済    承認経路の承認者がすべて承認済、\n5：契約済    受注先と合意済の後\nA:取消     ユーザー取消（画面上で検索可能）',
  `business_main_user_id` varchar(10) COLLATE utf8_bin DEFAULT NULL COMMENT '営業主担当',
  `business_sub_user_id` varchar(10) COLLATE utf8_bin DEFAULT NULL COMMENT '営業副担当',
  `dept_cd` varchar(3) COLLATE utf8_bin DEFAULT NULL COMMENT '部署C',
  `emp_no` varchar(10) COLLATE utf8_bin NOT NULL COMMENT '社員番号',
  `emp_nm` varchar(30) COLLATE utf8_bin NOT NULL COMMENT '社員名',
  `emp_nm_fuli` varchar(30) COLLATE utf8_bin NOT NULL COMMENT '社員名（フリガナ）',
  `emp_sex` char(1) COLLATE utf8_bin NOT NULL COMMENT '性別:M：男\nF：女',
  `emp_birthday` date NOT NULL COMMENT '生年月日',
  `entrance_dt` date NOT NULL COMMENT '入社日',
  `emp_my_number` varchar(20) COLLATE utf8_bin DEFAULT NULL COMMENT '要員マイナンバー',
  `emp_pat_k` char(1) COLLATE utf8_bin NOT NULL COMMENT '希望雇用形態:1：正社員\n2：契約社員\n3：出向社員\n4：派遣社員\n5：委託契約',
  `affiliation_k` char(1) COLLATE utf8_bin NOT NULL COMMENT '所属区分:1：個人事業主\n2：協力会社（ＢＰ）',
  `entrance_record_k` char(1) COLLATE utf8_bin NOT NULL COMMENT '入社経歴:1：有\n2：無',
  `old_appr_doc_no` varchar(10) COLLATE utf8_bin DEFAULT NULL COMMENT '前回稟議書NO',
  `take_over_k` char(1) COLLATE utf8_bin DEFAULT NULL COMMENT '引取区分:1：新規取引先\n2：既存取引先',
  `order_recv_hope_dt` date DEFAULT NULL COMMENT '注文書受領日（見込）',
  `from_home_station_nm` varchar(100) COLLATE utf8_bin DEFAULT NULL COMMENT '最寄駅（自宅）',
  `client_contract_k` char(1) COLLATE utf8_bin DEFAULT NULL COMMENT '契約（上位）:1：委任契約\n2：派遣契約',
  `appoint_allow_ratio` decimal(5,2) NOT NULL DEFAULT '0.00' COMMENT '役職手当割合',
  `house_allow_ratio` decimal(5,2) NOT NULL DEFAULT '0.00' COMMENT '住宅手当割合',
  `communication_allow_ratio` decimal(5,2) NOT NULL DEFAULT '0.00' COMMENT '通信手当割合',
  `site_allow_ratio` decimal(5,2) NOT NULL DEFAULT '0.00' COMMENT '現場手当割合',
  `overwork_allow_ratio` decimal(5,2) NOT NULL DEFAULT '0.00' COMMENT '残業手当割合',
  `other_allow1_nm` varchar(50) COLLATE utf8_bin DEFAULT NULL COMMENT 'そのた手当1名称',
  `other_allow1_ratio` decimal(5,2) DEFAULT '0.00' COMMENT 'そのた手当1割合',
  `other_allow2_nm` varchar(50) COLLATE utf8_bin DEFAULT NULL COMMENT 'そのた手当２名称',
  `other_allow2_ratio` decimal(5,2) DEFAULT '0.00' COMMENT 'そのた手当２割合',
  `sales_addup_month` date NOT NULL COMMENT '売上販売計上月:1～12\n売上販売の計上月\n（請求対象月ではない）',
  `work_days` int(11) NOT NULL COMMENT '当月稼働日:1～31',
  `billing_month` date NOT NULL COMMENT '請求計上月:1～12',
  `sell_price` decimal(10,1) NOT NULL COMMENT '上位単金/単価',
  `business_days` int(11) NOT NULL COMMENT '当月営業日:1～31',
  `cost_price` decimal(10,1) NOT NULL COMMENT '要員渡し/原価',
  `travel_cost_first_mon` decimal(10,1) NOT NULL COMMENT '交通費(初月)',
  `other_cost_first_mon` decimal(10,1) NOT NULL COMMENT 'その他(初月)',
  `travel_cost` decimal(10,1) NOT NULL COMMENT '交通費',
  `insurance_cost` decimal(10,1) DEFAULT NULL COMMENT '社保費',
  `other_cost` decimal(10,1) NOT NULL COMMENT 'その他',
  `insurance_k` char(1) COLLATE utf8_bin DEFAULT NULL COMMENT '保険加入区分:1：雇用保険のみ\n2：社保険完備\n3：保険無し',
  `insurance_join_ymd` date DEFAULT NULL COMMENT '社保加入予定日',
  `client_no` varchar(10) COLLATE utf8_bin NOT NULL COMMENT 'クライアント番号',
  `client_nm` varchar(50) COLLATE utf8_bin NOT NULL COMMENT 'クライアント名',
  `client_url` varchar(100) COLLATE utf8_bin DEFAULT NULL COMMENT 'クライアントURL',
  `client_my_number` varchar(20) COLLATE utf8_bin DEFAULT NULL COMMENT 'クライアント法人マイナンバー',
  `client_post_no` varchar(10) COLLATE utf8_bin DEFAULT NULL COMMENT 'クライアント郵便番号',
  `client_addr` varchar(200) COLLATE utf8_bin DEFAULT NULL COMMENT 'クライアント連絡先住所',
  `client_tel_no` varchar(15) COLLATE utf8_bin DEFAULT NULL COMMENT 'クライアント電話番号:XX-XXXX-XXXX',
  `client_fax_no` varchar(15) COLLATE utf8_bin DEFAULT NULL COMMENT 'クライアントFAX番号:XX-XXXX-XXXX',
  `client_staff_nm` varchar(30) COLLATE utf8_bin DEFAULT NULL COMMENT 'クライアント担当者名',
  `client_staff_nm_fuli` varchar(30) COLLATE utf8_bin DEFAULT NULL COMMENT 'クライアント担当者名（フリガナ）',
  `client_staff_dept_nm` varchar(50) COLLATE utf8_bin DEFAULT NULL COMMENT 'クライアント担当者所属部署',
  `client_staff_position` varchar(50) COLLATE utf8_bin DEFAULT NULL COMMENT 'クライアント担当者役職',
  `client_staff_mobile_no` varchar(20) COLLATE utf8_bin DEFAULT NULL COMMENT 'クライアント担当者携帯番号',
  `client_mail_addr` varchar(100) COLLATE utf8_bin DEFAULT NULL COMMENT 'クライアントメール',
  `client_mail_addr_cc` varchar(100) COLLATE utf8_bin DEFAULT NULL COMMENT 'クライアントメールCC',
  `client_req_staff_nm` varchar(30) COLLATE utf8_bin DEFAULT NULL COMMENT '請求担当者名',
  `client_req_staff_nm_fuli` varchar(30) COLLATE utf8_bin DEFAULT NULL COMMENT '請求担当者名（フリガナ）',
  `client_req_staff_dept_nm` varchar(50) COLLATE utf8_bin DEFAULT NULL COMMENT '請求担当者所属部署',
  `client_req_staff_position` varchar(50) COLLATE utf8_bin DEFAULT NULL COMMENT '請求担当者役職',
  `client_req_tel_no` varchar(15) COLLATE utf8_bin DEFAULT NULL COMMENT '請求担当者電話番号:XX-XXXX-XXXX',
  `client_req_fax_no` varchar(15) COLLATE utf8_bin DEFAULT NULL COMMENT '請求担当者FAX番号:XX-XXXX-XXXX',
  `client_req_staff_mobile_no` varchar(20) COLLATE utf8_bin DEFAULT NULL COMMENT '請求担当者携帯番号',
  `client_req_mail_addr` varchar(100) COLLATE utf8_bin DEFAULT NULL COMMENT '請求担当者メール',
  `client_req_mail_addr_cc` varchar(100) COLLATE utf8_bin DEFAULT NULL COMMENT '請求担当者メールCC',
  `client_req_post_no` varchar(10) COLLATE utf8_bin DEFAULT NULL COMMENT '請求担当者郵便番号',
  `client_req_addr` varchar(200) COLLATE utf8_bin DEFAULT NULL COMMENT '請求担当者連絡先住所',
  `client_order_no` varchar(50) COLLATE utf8_bin DEFAULT NULL COMMENT '受注（注文）書No',
  `pj_cd` varchar(10) COLLATE utf8_bin DEFAULT NULL COMMENT '作業案件CD',
  `pj_nm` varchar(200) COLLATE utf8_bin DEFAULT NULL COMMENT '作業案件名称',
  `work_place` varchar(200) COLLATE utf8_bin DEFAULT NULL COMMENT '勤務地/最寄駅',
  `work_content` varchar(200) COLLATE utf8_bin DEFAULT NULL COMMENT '作業内容・範囲',
  `accounting_pat_k` char(1) COLLATE utf8_bin DEFAULT NULL COMMENT '精算条件:1：上下割\n2：中割\n3：固定\n ← 記入例：140-200（36-58）/稼働時間200H以内想定',
  `salary_pay_k` char(1) COLLATE utf8_bin DEFAULT NULL COMMENT '給料支払区分:1：月給\n2：時給',
  `wh_max` decimal(4,1) DEFAULT NULL COMMENT '作業時間（上限・H）',
  `wh_min` decimal(4,1) DEFAULT NULL COMMENT '作業時間（下限・H）',
  `wh_mid` decimal(4,1) DEFAULT NULL COMMENT '中割時間',
  `excess_price` decimal(10,1) DEFAULT NULL COMMENT '超過単価（円）',
  `deducation_price` decimal(10,1) DEFAULT NULL COMMENT '控除単価（円）',
  `pj_start_dt` date DEFAULT NULL COMMENT '業務案件今月分の開始日',
  `pj_end_dt` date DEFAULT NULL COMMENT '業務案件今月分の終了日',
  `work_time_unit_k` char(1) COLLATE utf8_bin DEFAULT NULL COMMENT '作業時間単位',
  `contract_work_month` decimal(6,2) DEFAULT NULL COMMENT '工数（契約用）',
  `pj_class_nm` varchar(200) COLLATE utf8_bin DEFAULT NULL COMMENT '案件分類名称',
  `work_start_time` time DEFAULT NULL COMMENT '出勤時刻',
  `work_end_time` time DEFAULT NULL COMMENT '退勤時刻',
  `break_time_min` int(11) DEFAULT NULL COMMENT '休憩時間（分）',
  `payment_plan_d` int(11) DEFAULT NULL COMMENT '支払サイト/出金日付の日',
  `payment_due_k` char(1) COLLATE utf8_bin DEFAULT NULL COMMENT '支払サイト/締日区分',
  `pay_month_k` char(1) COLLATE utf8_bin DEFAULT NULL COMMENT '支払サイト/支払月区分',
  `pay_plan_d` char(1) COLLATE utf8_bin DEFAULT NULL,
  `salary_pay_plan_dt` int(11) DEFAULT NULL COMMENT '支払サイト/出金予定日',
  `bp_comp_id` varchar(10) COLLATE utf8_bin NOT NULL COMMENT '所属会社ID',
  `bp_comp_emp_nm` varchar(50) COLLATE utf8_bin NOT NULL COMMENT '所属会社名/社員氏名',
  `bp_comp_url` varchar(100) COLLATE utf8_bin DEFAULT NULL COMMENT '所属会社URL',
  `bp_my_number` varchar(20) COLLATE utf8_bin DEFAULT NULL COMMENT '所属会社法人マイナンバー',
  `bp_post_no` varchar(10) COLLATE utf8_bin DEFAULT NULL COMMENT '所属会社郵便番号',
  `bp_addr` varchar(200) COLLATE utf8_bin DEFAULT NULL COMMENT '所属会社連絡先住所',
  `bp_tel_no` varchar(15) COLLATE utf8_bin DEFAULT NULL COMMENT '所属会社電話番号',
  `bp_fax_no` varchar(15) COLLATE utf8_bin DEFAULT NULL COMMENT '所属会社FAX番号',
  `bp_staff_nm` varchar(30) COLLATE utf8_bin DEFAULT NULL COMMENT '所属会社担当者名',
  `bp_staff_nm_fuli` varchar(30) COLLATE utf8_bin DEFAULT NULL COMMENT '所属会社担当者名（フリガナ）',
  `bp_staff_dept_nm` varchar(50) COLLATE utf8_bin DEFAULT NULL COMMENT '所属会社担当者所属部署',
  `bp_staff_position` varchar(50) COLLATE utf8_bin DEFAULT NULL COMMENT '所属会社担当者役職',
  `bp_staff_tel_no` varchar(15) COLLATE utf8_bin DEFAULT NULL COMMENT '所属会社担当者電話番号',
  `bp_staff_fax_no` varchar(15) COLLATE utf8_bin DEFAULT NULL COMMENT '所属会社担当者FAX番号',
  `bp_staff_mobile_no` varchar(20) COLLATE utf8_bin DEFAULT NULL COMMENT '所属会社担当者携帯番号',
  `bp_staff_mail_addr` varchar(100) COLLATE utf8_bin DEFAULT NULL COMMENT '所属会社担当者個人メール',
  `bp_staff_mail_addr_cc` varchar(100) COLLATE utf8_bin DEFAULT NULL COMMENT '所属会社担当者個人メールCC',
  `staff_addr` varchar(200) COLLATE utf8_bin DEFAULT NULL COMMENT '要員住所',
  `staff_post_no` varchar(10) COLLATE utf8_bin DEFAULT NULL COMMENT '要員郵便番号',
  `staff_mail_addr` varchar(30) COLLATE utf8_bin DEFAULT NULL COMMENT '要員メールアドレス',
  `staff_tel_no` varchar(15) COLLATE utf8_bin DEFAULT NULL COMMENT '要員電話番号',
  `staff_mobile_no` varchar(20) COLLATE utf8_bin DEFAULT NULL COMMENT '要員携帯電話番号',
  `bp_accounting_pat_k` char(1) COLLATE utf8_bin DEFAULT NULL COMMENT '精算条件（BP）:1：上下割\n2：中割\n3：固定\n ← 記入例：140-200（36-58）/稼働時間200H以内想定',
  `bp_salary_pay_k` char(1) COLLATE utf8_bin DEFAULT NULL COMMENT '給料支払区分（BP）:1：月給\n2：時給',
  `bp_wh_max` decimal(4,1) DEFAULT NULL COMMENT '作業時間（上限・H）（BP）',
  `bp_wh_min` decimal(4,1) DEFAULT NULL COMMENT '作業時間（下限・H）（BP）',
  `bp_wh_mid` decimal(4,1) DEFAULT NULL COMMENT '中割時間（BP）',
  `bp_excess_price` decimal(10,1) DEFAULT NULL COMMENT '超過単価（円）（BP）',
  `bp_deducation_price` decimal(10,1) DEFAULT NULL COMMENT '控除単価（円）（BP）',
  `bp_work_unit_k` char(1) COLLATE utf8_bin NOT NULL COMMENT '作業時間単位（BP）',
  `bp_work_start_time` time NOT NULL COMMENT '出勤時刻（BP）',
  `bp_work_end_time` time NOT NULL COMMENT '退勤時刻（BP）',
  `bp_break_time_min` int(11) NOT NULL COMMENT '休憩時間（分）（BP）',
  `bp_payment_plan_d` int(11) NOT NULL COMMENT '支払サイト/出金日付の日（BP）',
  `bp_payment_due_k` char(1) COLLATE utf8_bin NOT NULL COMMENT '支払サイト/締日区分（BP）',
  `bp_pay_month_k` char(1) COLLATE utf8_bin NOT NULL COMMENT '支払サイト/支払月区分（BP）',
  `bp_pay_plan_d` char(1) COLLATE utf8_bin DEFAULT NULL,
  `bp_salary_pay_plan_dt` int(11) DEFAULT NULL COMMENT '支払サイト/出金予定日（BP）',
  `mon_pro_k` char(1) COLLATE utf8_bin NOT NULL COMMENT '月次処理区分:1：通常締結\n2：EDI',
  `work_rep_f` char(1) COLLATE utf8_bin NOT NULL COMMENT '作業報告書F:1：指定なし\n2：客先F',
  `bill_f` char(1) COLLATE utf8_bin NOT NULL COMMENT '請求書F:1：指定なし\n2：客先F',
  `work_rep_dl_k` char(1) COLLATE utf8_bin NOT NULL COMMENT '作業報告書締め区分:1：指定なし\n2：第1営業日\n3：第2営業日\n4：第3営業日\n5：第4-5営業日',
  `bill_dl_k` char(1) COLLATE utf8_bin NOT NULL COMMENT '請求書締め区分:1：指定なし\n2：第1営業日\n3：第2営業日\n4：第3営業日\n5：第4-5営業日',
  `mon_attach_file_k` char(1) COLLATE utf8_bin NOT NULL COMMENT '添付資料F:1：有\n2：無',
  `mon_attach_file_id` int(11) DEFAULT NULL COMMENT '添付ファイルID',
  `procedure_method_k` char(1) COLLATE utf8_bin DEFAULT NULL COMMENT '手続き方法',
  `withdrawal_reason_k` char(1) COLLATE utf8_bin DEFAULT NULL COMMENT '退社理由',
  `withdrawal_letter_k` char(1) COLLATE utf8_bin DEFAULT NULL COMMENT '離職票の有無',
  `insurance_con_k` char(1) COLLATE utf8_bin DEFAULT NULL COMMENT '社保任意継続',
  `withdrawal_req_doc` char(1) COLLATE utf8_bin DEFAULT NULL COMMENT '必要書類',
  `procedure_dt` date DEFAULT NULL COMMENT '手続き日付',
  `withdrawal_dt` date DEFAULT NULL COMMENT '退社日',
  `after_withdrawal_post_no` varchar(10) COLLATE utf8_bin DEFAULT NULL COMMENT '退社後郵便番号',
  `after_withdrawal_addr` varchar(200) COLLATE utf8_bin DEFAULT NULL COMMENT '退社後住所',
  `purchase_remark` varchar(500) COLLATE utf8_bin DEFAULT NULL COMMENT '連絡事項（購買へ）',
  `commercial_dist_remark` varchar(500) COLLATE utf8_bin DEFAULT NULL COMMENT '商流記載',
  `emp_remark` varchar(500) COLLATE utf8_bin DEFAULT NULL COMMENT '特記事項（要員）',
  `sys_ins_user_id` varchar(10) COLLATE utf8_bin NOT NULL COMMENT '登録者',
  `sys_ins_dt` datetime NOT NULL COMMENT '登録日時',
  `sys_ins_pg_id` varchar(15) COLLATE utf8_bin NOT NULL COMMENT '登録機能ID',
  `sys_upd_user_id` varchar(10) COLLATE utf8_bin DEFAULT NULL COMMENT '更新者',
  `sys_upd_dt` datetime DEFAULT NULL COMMENT '更新日時',
  `sys_upd_pg_id` varchar(15) COLLATE utf8_bin DEFAULT NULL COMMENT '更新機能ID',
  PRIMARY KEY (`appr_doc_id`),
  UNIQUE KEY `apprDoc_i_alt1` (`appr_doc_no`)
) ENGINE=InnoDB AUTO_INCREMENT=24 DEFAULT CHARSET=utf8 COLLATE=utf8_bin COMMENT='稟議書';
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Table structure for table `apprdocaccepted`
--

DROP TABLE IF EXISTS `apprdocaccepted`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `apprdocaccepted` (
  `appr_doc_id` int(11) NOT NULL COMMENT '稟議書ID',
  `last_accept_user_id` varchar(10) COLLATE utf8_bin NOT NULL COMMENT '最終承認者ID',
  `last_accept_date` datetime NOT NULL COMMENT '最終承認日時',
  `approval_apply_hid` int(11) NOT NULL COMMENT '稟議書申請HID',
  `sys_ins_user_id` varchar(10) COLLATE utf8_bin NOT NULL COMMENT '登録者',
  `sys_ins_dt` datetime NOT NULL COMMENT '登録日時',
  `sys_ins_pg_id` varchar(15) COLLATE utf8_bin NOT NULL COMMENT '登録機能ID',
  `sys_upd_user_id` varchar(10) COLLATE utf8_bin DEFAULT NULL COMMENT '更新者',
  `sys_upd_dt` datetime DEFAULT NULL COMMENT '更新日時',
  `sys_upd_pg_id` varchar(15) COLLATE utf8_bin DEFAULT NULL COMMENT '更新機能ID',
  PRIMARY KEY (`appr_doc_id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COLLATE=utf8_bin COMMENT='承認済稟議書';
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Table structure for table `apprdocclientcontractevidence`
--

DROP TABLE IF EXISTS `apprdocclientcontractevidence`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `apprdocclientcontractevidence` (
  `appr_client_contract_evidence_d` int(11) NOT NULL AUTO_INCREMENT COMMENT 'エビデンスID',
  `appr_doc_id` int(11) NOT NULL COMMENT '稟議書ID',
  `display_order` int(11) NOT NULL COMMENT '表示順',
  `work_evidence` varchar(2000) COLLATE utf8_bin DEFAULT NULL COMMENT 'エビデンス',
  `sys_ins_user_id` varchar(10) COLLATE utf8_bin NOT NULL COMMENT '登録者',
  `sys_ins_dt` datetime NOT NULL COMMENT '登録日時',
  `sys_ins_pg_id` varchar(15) COLLATE utf8_bin NOT NULL COMMENT '登録機能ID',
  `sys_upd_user_id` varchar(10) COLLATE utf8_bin DEFAULT NULL COMMENT '更新者',
  `sys_upd_dt` datetime DEFAULT NULL COMMENT '更新日時',
  `sys_upd_pg_id` varchar(15) COLLATE utf8_bin DEFAULT NULL COMMENT '更新機能ID',
  PRIMARY KEY (`appr_client_contract_evidence_d`),
  UNIQUE KEY `clientContractEvidence_uk1` (`appr_doc_id`,`display_order`)
) ENGINE=InnoDB AUTO_INCREMENT=17 DEFAULT CHARSET=utf8 COLLATE=utf8_bin COMMENT='クライアント契約契約条件エビデンス';
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Table structure for table `basepricemgr`
--

DROP TABLE IF EXISTS `basepricemgr`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `basepricemgr` (
  `base_price_mgr_id` int(11) NOT NULL AUTO_INCREMENT COMMENT '標準報酬管理ID',
  `base_price_type` char(1) COLLATE utf8_bin NOT NULL COMMENT '標準報酬タイプ:1：健康保険\n2：厚生年金',
  `start_dt` date NOT NULL COMMENT '開始日',
  `end_dt` date NOT NULL COMMENT '終了日:‘2099/12/31’',
  `salary_from` decimal(10,1) NOT NULL COMMENT '報酬月額From',
  `salary_to` decimal(10,1) NOT NULL DEFAULT '999.0' COMMENT '報酬月額To(含まれまい)',
  `base_price` decimal(10,1) NOT NULL COMMENT '標準報酬',
  `sys_ins_user_id` varchar(10) COLLATE utf8_bin NOT NULL COMMENT '登録者',
  `sys_ins_dt` datetime NOT NULL COMMENT '登録日時',
  `sys_ins_pg_id` varchar(15) COLLATE utf8_bin NOT NULL COMMENT '登録機能ID',
  `sys_upd_user_id` varchar(10) COLLATE utf8_bin DEFAULT NULL COMMENT '更新者',
  `sys_upd_dt` datetime DEFAULT NULL COMMENT '更新日時',
  `sys_upd_pg_id` varchar(15) COLLATE utf8_bin DEFAULT NULL COMMENT '更新機能ID',
  PRIMARY KEY (`base_price_mgr_id`),
  UNIQUE KEY `ratioMgr_u_atl1` (`base_price_type`,`salary_from`,`salary_to`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COLLATE=utf8_bin COMMENT='標準報酬管理';
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Table structure for table `bpestdtl`
--

DROP TABLE IF EXISTS `bpestdtl`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `bpestdtl` (
  `bp_est_did` int(11) NOT NULL AUTO_INCREMENT COMMENT 'BP見積DID',
  `bp_est_hid` int(11) NOT NULL COMMENT 'BP見積HID',
  `appr_doc_id` int(11) NOT NULL COMMENT '稟議書ID',
  `sys_ins_user_id` varchar(10) COLLATE utf8_bin NOT NULL COMMENT '登録者',
  `sys_ins_dt` datetime NOT NULL COMMENT '登録日時',
  `sys_ins_pg_id` varchar(15) COLLATE utf8_bin NOT NULL COMMENT '登録機能ID',
  `sys_upd_user_id` varchar(10) COLLATE utf8_bin DEFAULT NULL COMMENT '更新者',
  `sys_upd_dt` datetime DEFAULT NULL COMMENT '更新日時',
  `sys_upd_pg_id` varchar(15) COLLATE utf8_bin DEFAULT NULL COMMENT '更新機能ID',
  PRIMARY KEY (`bp_est_did`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COLLATE=utf8_bin COMMENT='BP見積DTL情報';
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Table structure for table `bpesthdr`
--

DROP TABLE IF EXISTS `bpesthdr`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `bpesthdr` (
  `bp_est_hid` int(11) NOT NULL AUTO_INCREMENT COMMENT 'BP見積ID',
  `bp_est_no` varchar(10) COLLATE utf8_bin NOT NULL COMMENT 'BP見積NO',
  `bp_est_request_no` varchar(10) COLLATE utf8_bin DEFAULT NULL COMMENT 'BP見積依頼NO',
  `use_flg` char(1) COLLATE utf8_bin NOT NULL DEFAULT 'Y' COMMENT '使用FLG:Y/N',
  `bp_est_amt_without_tax` decimal(13,1) NOT NULL COMMENT '見積金額(税抜)',
  `work_start_ymd` date NOT NULL COMMENT '作業開始日',
  `work_end_ymd` date NOT NULL COMMENT '作業終了日',
  `project_no` varchar(10) COLLATE utf8_bin NOT NULL COMMENT '案件番号',
  `project_nm` varchar(50) COLLATE utf8_bin DEFAULT NULL COMMENT '案件名称',
  `work_content` varchar(200) COLLATE utf8_bin DEFAULT NULL COMMENT '作業内容',
  `work_place` varchar(100) COLLATE utf8_bin DEFAULT NULL COMMENT '作業場所',
  `bp_est_content` varchar(500) COLLATE utf8_bin DEFAULT NULL COMMENT '見積内容',
  `special_affairs` varchar(500) COLLATE utf8_bin DEFAULT NULL COMMENT '特記事項',
  `remark` varchar(500) COLLATE utf8_bin DEFAULT NULL COMMENT '備考',
  `sys_ins_user_id` varchar(10) COLLATE utf8_bin NOT NULL COMMENT '登録者',
  `sys_ins_dt` datetime NOT NULL COMMENT '登録日時',
  `sys_ins_pg_id` varchar(15) COLLATE utf8_bin NOT NULL COMMENT '登録機能ID',
  `sys_upd_user_id` varchar(10) COLLATE utf8_bin DEFAULT NULL COMMENT '更新者',
  `sys_upd_dt` datetime DEFAULT NULL COMMENT '更新日時',
  `sys_upd_pg_id` varchar(15) COLLATE utf8_bin DEFAULT NULL COMMENT '更新機能ID',
  PRIMARY KEY (`bp_est_hid`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COLLATE=utf8_bin COMMENT='BP見積HDR情報';
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Table structure for table `bpestrecv`
--

DROP TABLE IF EXISTS `bpestrecv`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `bpestrecv` (
  `bp_est_recv_id` int(11) NOT NULL AUTO_INCREMENT COMMENT 'BP見積受信ID',
  `recv_dt` datetime NOT NULL COMMENT '受信日時',
  `bp_est_no` varchar(10) COLLATE utf8_bin NOT NULL COMMENT 'BP見積NO',
  `bp_est_req_no` varchar(10) COLLATE utf8_bin NOT NULL COMMENT 'BP見積依頼NO',
  `use_flg` char(1) COLLATE utf8_bin NOT NULL DEFAULT 'Y' COMMENT '使用FLG:Y/N',
  `bp_est_amt_without_tax` decimal(13,1) NOT NULL COMMENT '見積金額(税抜)',
  `work_start_ymd` date NOT NULL COMMENT '作業開始日',
  `work_end_ymd` date NOT NULL COMMENT '作業終了日',
  `project_no` varchar(10) COLLATE utf8_bin NOT NULL COMMENT '案件番号',
  `project_nm` varchar(50) COLLATE utf8_bin DEFAULT NULL COMMENT '案件名称',
  `work_content` varchar(200) COLLATE utf8_bin DEFAULT NULL COMMENT '作業内容',
  `work_place` varchar(100) COLLATE utf8_bin DEFAULT NULL COMMENT '作業場所',
  `bp_est_content` varchar(500) COLLATE utf8_bin DEFAULT NULL COMMENT '見積内容',
  `special_affairs` varchar(500) COLLATE utf8_bin DEFAULT NULL COMMENT '特記事項',
  `remark` varchar(500) COLLATE utf8_bin DEFAULT NULL COMMENT '備考',
  `appr_doc_no` varchar(10) COLLATE utf8_bin NOT NULL COMMENT '稟議書NO',
  `sys_ins_user_id` varchar(10) COLLATE utf8_bin NOT NULL COMMENT '登録者',
  `sys_ins_dt` datetime NOT NULL COMMENT '登録日時',
  `sys_ins_pg_id` varchar(15) COLLATE utf8_bin NOT NULL COMMENT '登録機能ID',
  `sys_upd_user_id` varchar(10) COLLATE utf8_bin DEFAULT NULL COMMENT '更新者',
  `sys_upd_dt` datetime DEFAULT NULL COMMENT '更新日時',
  `sys_upd_pg_id` varchar(15) COLLATE utf8_bin DEFAULT NULL COMMENT '更新機能ID',
  PRIMARY KEY (`bp_est_recv_id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COLLATE=utf8_bin COMMENT='BP見積受信情報';
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Table structure for table `bpestreqaccepted`
--

DROP TABLE IF EXISTS `bpestreqaccepted`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `bpestreqaccepted` (
  `bp_est_req_id` int(11) NOT NULL COMMENT 'BP見積依頼HID',
  `last_accept_user_id` varchar(10) COLLATE utf8_bin NOT NULL COMMENT '最終承認者ID',
  `last_accept_date` datetime NOT NULL COMMENT '最終承認日時',
  `bp_est_req_apply_hid` int(11) NOT NULL COMMENT 'BP見積依頼申請HID',
  `sys_ins_user_id` varchar(10) COLLATE utf8_bin NOT NULL COMMENT '登録者',
  `sys_ins_dt` datetime NOT NULL COMMENT '登録日時',
  `sys_ins_pg_id` varchar(15) COLLATE utf8_bin NOT NULL COMMENT '登録機能ID',
  `sys_upd_user_id` varchar(10) COLLATE utf8_bin DEFAULT NULL COMMENT '更新者',
  `sys_upd_dt` datetime DEFAULT NULL COMMENT '更新日時',
  `sys_upd_pg_id` varchar(15) COLLATE utf8_bin DEFAULT NULL COMMENT '更新機能ID',
  PRIMARY KEY (`bp_est_req_id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COLLATE=utf8_bin COMMENT='承認済見積依頼書';
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Table structure for table `bpestreqapplydtl`
--

DROP TABLE IF EXISTS `bpestreqapplydtl`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `bpestreqapplydtl` (
  `bp_est_req_apply_did` int(11) NOT NULL AUTO_INCREMENT COMMENT 'BP見積依頼申請DID',
  `bp_est_req_apply_hid` int(11) NOT NULL COMMENT 'BP見積依頼申請HID',
  `display_order` int(11) NOT NULL COMMENT '表示順',
  `accept_user_id` varchar(10) COLLATE utf8_bin NOT NULL COMMENT '承認者ID',
  `confirm_st` char(1) COLLATE utf8_bin NOT NULL COMMENT '承認状況:1：未承認\n2：承認済\n3：拒否',
  `con_rej_comment` varchar(500) COLLATE utf8_bin NOT NULL COMMENT '承認(拒否)コメント',
  `con_rej_dt` datetime DEFAULT NULL COMMENT '承認(拒否)日時',
  `sys_ins_user_id` varchar(10) COLLATE utf8_bin NOT NULL COMMENT '登録者',
  `sys_ins_dt` datetime NOT NULL COMMENT '登録日時',
  `sys_ins_pg_id` varchar(15) COLLATE utf8_bin NOT NULL COMMENT '登録機能ID',
  `sys_upd_user_id` varchar(10) COLLATE utf8_bin DEFAULT NULL COMMENT '更新者',
  `sys_upd_dt` datetime DEFAULT NULL COMMENT '更新日時',
  `sys_upd_pg_id` varchar(15) COLLATE utf8_bin DEFAULT NULL COMMENT '更新機能ID',
  PRIMARY KEY (`bp_est_req_apply_did`),
  UNIQUE KEY `bpEstReqApplyDtl_u_1` (`bp_est_req_apply_hid`,`display_order`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COLLATE=utf8_bin COMMENT='BP見積依頼申請DTL情報';
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Table structure for table `bpestreqapplyhdr`
--

DROP TABLE IF EXISTS `bpestreqapplyhdr`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `bpestreqapplyhdr` (
  `bp_est_req_apply_hid` int(11) NOT NULL AUTO_INCREMENT COMMENT 'BP見積依頼申請HID',
  `bp_est_req_id` int(11) NOT NULL COMMENT 'BP見積依頼ID',
  `use_flg` char(1) COLLATE utf8_bin NOT NULL DEFAULT 'Y' COMMENT '使用FLG:Y:使用\nN:削除',
  `apply_reason` varchar(500) COLLATE utf8_bin DEFAULT NULL COMMENT '申請理由',
  `apply_user_id` varchar(10) COLLATE utf8_bin NOT NULL COMMENT '申請者ID',
  `apply_dt` datetime NOT NULL COMMENT '申請日時',
  `confirm_wait_display_order` int(11) DEFAULT NULL COMMENT '承認待表示順:承認待となっている申請DTLの表示順を設定',
  `sys_ins_user_id` varchar(10) COLLATE utf8_bin NOT NULL COMMENT '登録者',
  `sys_ins_dt` datetime NOT NULL COMMENT '登録日時',
  `sys_ins_pg_id` varchar(15) COLLATE utf8_bin NOT NULL COMMENT '登録機能ID',
  `sys_upd_user_id` varchar(10) COLLATE utf8_bin DEFAULT NULL COMMENT '更新者',
  `sys_upd_dt` datetime DEFAULT NULL COMMENT '更新日時',
  `sys_upd_pg_id` varchar(15) COLLATE utf8_bin DEFAULT NULL COMMENT '更新機能ID',
  PRIMARY KEY (`bp_est_req_apply_hid`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COLLATE=utf8_bin COMMENT='BP見積依頼申請HDR情報';
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Table structure for table `bpestreqdtl`
--

DROP TABLE IF EXISTS `bpestreqdtl`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `bpestreqdtl` (
  `bp_est_req_did` int(11) NOT NULL AUTO_INCREMENT COMMENT 'BP見積依頼DID',
  `bp_est_req_hid` int(11) NOT NULL COMMENT 'BP見積依頼HID',
  `appr_doc_id` int(11) NOT NULL COMMENT '稟議書ID',
  `sys_ins_user_id` varchar(10) COLLATE utf8_bin NOT NULL COMMENT '登録者',
  `sys_ins_dt` datetime NOT NULL COMMENT '登録日時',
  `sys_ins_pg_id` varchar(15) COLLATE utf8_bin NOT NULL COMMENT '登録機能ID',
  `sys_upd_user_id` varchar(10) COLLATE utf8_bin DEFAULT NULL COMMENT '更新者',
  `sys_upd_dt` datetime DEFAULT NULL COMMENT '更新日時',
  `sys_upd_pg_id` varchar(15) COLLATE utf8_bin DEFAULT NULL COMMENT '更新機能ID',
  PRIMARY KEY (`bp_est_req_did`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COLLATE=utf8_bin COMMENT='BP見積依頼DTL情報';
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Table structure for table `bpestreqhdr`
--

DROP TABLE IF EXISTS `bpestreqhdr`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `bpestreqhdr` (
  `bp_est_req_hid` int(11) NOT NULL AUTO_INCREMENT COMMENT 'BP見積依頼HID',
  `bp_est_req_no` varchar(10) COLLATE utf8_bin NOT NULL COMMENT 'BP見積依頼NO',
  `bp_order_no` varchar(10) COLLATE utf8_bin NOT NULL COMMENT 'BP受注NO',
  `use_flg` char(1) COLLATE utf8_bin NOT NULL DEFAULT 'Y' COMMENT '使用FLG:Y/N',
  `order_hid` int(11) NOT NULL COMMENT '受注HID',
  `bp_est_hope_amt_without_tax` decimal(13,1) NOT NULL COMMENT '見積希望金額(税抜)',
  `work_start_ymd` date NOT NULL COMMENT '作業開始日',
  `work_end_ymd` date NOT NULL COMMENT '作業終了日',
  `project_no` varchar(10) COLLATE utf8_bin NOT NULL COMMENT '案件番号',
  `project_nm` varchar(50) COLLATE utf8_bin DEFAULT NULL COMMENT '案件名称',
  `work_content` varchar(200) COLLATE utf8_bin DEFAULT NULL COMMENT '作業内容',
  `work_place` varchar(100) COLLATE utf8_bin DEFAULT NULL COMMENT '作業場所',
  `bp_est_req_content` varchar(500) COLLATE utf8_bin DEFAULT NULL COMMENT '見積依頼内容',
  `order_special_affairs` varchar(500) COLLATE utf8_bin DEFAULT NULL COMMENT '特記事項',
  `order_remark` varchar(500) COLLATE utf8_bin DEFAULT NULL COMMENT '備考',
  `sys_ins_user_id` varchar(10) COLLATE utf8_bin NOT NULL COMMENT '登録者',
  `sys_ins_dt` datetime NOT NULL COMMENT '登録日時',
  `sys_ins_pg_id` varchar(15) COLLATE utf8_bin NOT NULL COMMENT '登録機能ID',
  `sys_upd_user_id` varchar(10) COLLATE utf8_bin DEFAULT NULL COMMENT '更新者',
  `sys_upd_dt` datetime DEFAULT NULL COMMENT '更新日時',
  `sys_upd_pg_id` varchar(15) COLLATE utf8_bin DEFAULT NULL COMMENT '更新機能ID',
  PRIMARY KEY (`bp_est_req_hid`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COLLATE=utf8_bin COMMENT='BP見積依頼HDR情報';
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Table structure for table `bpestreqsend`
--

DROP TABLE IF EXISTS `bpestreqsend`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `bpestreqsend` (
  `bp_est_req_send_id` int(11) NOT NULL AUTO_INCREMENT COMMENT 'BP見積依頼送信ID',
  `send_dt` datetime NOT NULL COMMENT '送信日時',
  `bp_est_req_id` int(11) NOT NULL COMMENT 'BP見積依頼ID',
  `bp_est_req_no` varchar(10) COLLATE utf8_bin NOT NULL COMMENT 'BP見積依頼NO',
  `use_flg` char(1) COLLATE utf8_bin NOT NULL DEFAULT 'Y' COMMENT '使用FLG:Y/N',
  `bp_est_hope_amt_without_tax` decimal(13,1) NOT NULL COMMENT '見積希望金額(税抜)',
  `work_start_ymd` date NOT NULL COMMENT '作業開始日',
  `work_end_ymd` date NOT NULL COMMENT '作業終了日',
  `project_no` varchar(10) COLLATE utf8_bin NOT NULL COMMENT '案件番号',
  `project_nm` varchar(50) COLLATE utf8_bin DEFAULT NULL COMMENT '案件名称',
  `work_content` varchar(200) COLLATE utf8_bin DEFAULT NULL COMMENT '作業内容',
  `work_place` varchar(100) COLLATE utf8_bin DEFAULT NULL COMMENT '作業場所',
  `bp_est_req_content` varchar(500) COLLATE utf8_bin DEFAULT NULL COMMENT '見積依頼内容',
  `order_special_affairs` varchar(500) COLLATE utf8_bin DEFAULT NULL COMMENT '特記事項',
  `order_remark` varchar(500) COLLATE utf8_bin DEFAULT NULL COMMENT '備考',
  `appr_doc_no` varchar(10) COLLATE utf8_bin NOT NULL COMMENT '稟議書NO',
  `sys_ins_user_id` varchar(10) COLLATE utf8_bin NOT NULL COMMENT '登録者',
  `sys_ins_dt` datetime NOT NULL COMMENT '登録日時',
  `sys_ins_pg_id` varchar(15) COLLATE utf8_bin NOT NULL COMMENT '登録機能ID',
  `sys_upd_user_id` varchar(10) COLLATE utf8_bin DEFAULT NULL COMMENT '更新者',
  `sys_upd_dt` datetime DEFAULT NULL COMMENT '更新日時',
  `sys_upd_pg_id` varchar(15) COLLATE utf8_bin DEFAULT NULL COMMENT '更新機能ID',
  PRIMARY KEY (`bp_est_req_send_id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COLLATE=utf8_bin COMMENT='BP見積依頼送信情報';
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Table structure for table `bpshippingorder`
--

DROP TABLE IF EXISTS `bpshippingorder`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `bpshippingorder` (
  `bp_shipping_order_id` int(11) NOT NULL AUTO_INCREMENT COMMENT 'BP発注ID',
  `bp_shipping_order_no` varchar(10) COLLATE utf8_bin NOT NULL COMMENT 'BP発注NO',
  `bp_estimation_id` int(11) NOT NULL COMMENT 'BP見積ID',
  `bp_estimation_no` varchar(10) COLLATE utf8_bin NOT NULL COMMENT 'BP見積NO',
  `bp_estimation_request_id` int(11) DEFAULT NULL COMMENT 'BP見積依頼ID',
  `bp_estimation_request_no` varchar(10) COLLATE utf8_bin DEFAULT NULL COMMENT 'BP見積依頼NO',
  `use_flg` char(1) COLLATE utf8_bin NOT NULL DEFAULT 'Y' COMMENT '使用FLG:Y/N',
  `order_hid` int(11) NOT NULL COMMENT '受注HID',
  `order_amt_without_tax` decimal(13,1) NOT NULL COMMENT '受注金額(税抜)',
  `work_start_ymd` date NOT NULL COMMENT '作業開始日',
  `work_end_ymd` date NOT NULL COMMENT '作業終了日',
  `project_no` varchar(10) COLLATE utf8_bin NOT NULL COMMENT '案件番号',
  `project_nm` varchar(50) COLLATE utf8_bin DEFAULT NULL COMMENT '案件名称',
  `work_content` varchar(200) COLLATE utf8_bin DEFAULT NULL COMMENT '作業内容',
  `work_place` varchar(100) COLLATE utf8_bin DEFAULT NULL COMMENT '作業場所',
  `order_content` varchar(500) COLLATE utf8_bin DEFAULT NULL COMMENT '注文内容',
  `order_special_affairs` varchar(500) COLLATE utf8_bin DEFAULT NULL COMMENT '特記事項',
  `order_remark` varchar(500) COLLATE utf8_bin DEFAULT NULL COMMENT '備考',
  `order_doc_file_id` int(11) DEFAULT NULL COMMENT '注文書',
  `sys_ins_user_id` varchar(10) COLLATE utf8_bin NOT NULL COMMENT '登録者',
  `sys_ins_dt` datetime NOT NULL COMMENT '登録日時',
  `sys_ins_pg_id` varchar(15) COLLATE utf8_bin NOT NULL COMMENT '登録機能ID',
  `sys_upd_user_id` varchar(10) COLLATE utf8_bin DEFAULT NULL COMMENT '更新者',
  `sys_upd_dt` datetime DEFAULT NULL COMMENT '更新日時',
  `sys_upd_pg_id` varchar(15) COLLATE utf8_bin DEFAULT NULL COMMENT '更新機能ID',
  PRIMARY KEY (`bp_shipping_order_id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COLLATE=utf8_bin COMMENT='BP発注情報';
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Table structure for table `calendar`
--

DROP TABLE IF EXISTS `calendar`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `calendar` (
  `calendar_id` int(11) NOT NULL AUTO_INCREMENT COMMENT 'カレンダーID',
  `ym` date NOT NULL COMMENT '年月',
  `ymd` date NOT NULL COMMENT '年月日',
  `calendar_k` char(1) COLLATE utf8_bin NOT NULL COMMENT 'カレンダー区分:1:稼働日\n2:週末\n3:祝日',
  `sys_ins_user_id` varchar(10) COLLATE utf8_bin NOT NULL COMMENT '登録者',
  `sys_ins_dt` datetime NOT NULL COMMENT '登録日時',
  `sys_ins_pg_id` varchar(15) COLLATE utf8_bin NOT NULL COMMENT '登録機能ID',
  `sys_upd_user_id` varchar(10) COLLATE utf8_bin DEFAULT NULL COMMENT '更新者',
  `sys_upd_dt` datetime DEFAULT NULL COMMENT '更新日時',
  `sys_upd_pg_id` varchar(15) COLLATE utf8_bin DEFAULT NULL COMMENT '更新機能ID',
  PRIMARY KEY (`calendar_id`),
  KEY `calendar_i_1` (`ym`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COLLATE=utf8_bin COMMENT='カレンダー';
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Table structure for table `dept`
--

DROP TABLE IF EXISTS `dept`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `dept` (
  `id` int(11) NOT NULL AUTO_INCREMENT COMMENT 'キーid',
  `num` int(11) DEFAULT NULL COMMENT '順番',
  `pid` int(11) DEFAULT NULL COMMENT '親部門id',
  `simplename` varchar(45) DEFAULT NULL COMMENT '略称',
  `fullname` varchar(255) DEFAULT NULL COMMENT 'フルネーム',
  `tips` varchar(255) DEFAULT NULL COMMENT 'ヒント',
  `version` int(11) DEFAULT NULL COMMENT 'バージョン',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=39 DEFAULT CHARSET=utf8 COMMENT='部門表';
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Table structure for table `dict`
--

DROP TABLE IF EXISTS `dict`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `dict` (
  `id` int(11) NOT NULL AUTO_INCREMENT COMMENT 'キーid',
  `num` int(11) DEFAULT NULL COMMENT '順番',
  `pid` int(11) DEFAULT NULL COMMENT '親辞書',
  `name` varchar(255) DEFAULT NULL COMMENT '名称',
  `tips` varchar(255) DEFAULT NULL COMMENT 'ヒント',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=45 DEFAULT CHARSET=utf8 COMMENT='辞書表';
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Table structure for table `estdtl`
--

DROP TABLE IF EXISTS `estdtl`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `estdtl` (
  `est_did` int(11) NOT NULL AUTO_INCREMENT COMMENT '見積DID',
  `est_hid` int(11) NOT NULL COMMENT '見積HID',
  `appr_doc_id` int(11) NOT NULL COMMENT '稟議書ID',
  `sys_ins_user_id` varchar(10) COLLATE utf8_bin NOT NULL COMMENT '登録者',
  `sys_ins_dt` datetime NOT NULL COMMENT '登録日時',
  `sys_ins_pg_id` varchar(15) COLLATE utf8_bin NOT NULL COMMENT '登録機能ID',
  `sys_upd_user_id` varchar(10) COLLATE utf8_bin DEFAULT NULL COMMENT '更新者',
  `sys_upd_dt` datetime DEFAULT NULL COMMENT '更新日時',
  `sys_upd_pg_id` varchar(15) COLLATE utf8_bin DEFAULT NULL COMMENT '更新機能ID',
  PRIMARY KEY (`est_did`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COLLATE=utf8_bin COMMENT='見積DTL情報';
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Table structure for table `esthdr`
--

DROP TABLE IF EXISTS `esthdr`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `esthdr` (
  `est_hid` int(11) NOT NULL AUTO_INCREMENT COMMENT '見積HID',
  `est_no` varchar(10) COLLATE utf8_bin NOT NULL COMMENT '見積NO',
  `shipping_order_spec_no` varchar(10) COLLATE utf8_bin NOT NULL COMMENT '発注仕様書NO',
  `submit_dt` date NOT NULL COMMENT '提出日',
  `client_nm` varchar(50) COLLATE utf8_bin NOT NULL COMMENT 'クライアント名',
  `estimation_amt_without_tax` decimal(13,1) NOT NULL COMMENT '見積金額(税抜)',
  `work_start_ymd` date NOT NULL COMMENT '作業開始日',
  `work_end_ymd` date NOT NULL COMMENT '作業終了日',
  `project_no` varchar(10) COLLATE utf8_bin NOT NULL COMMENT '案件番号',
  `project_nm` varchar(50) COLLATE utf8_bin NOT NULL COMMENT '案件名称',
  `work_content` varchar(200) COLLATE utf8_bin NOT NULL COMMENT '作業内容',
  `work_place` varchar(100) COLLATE utf8_bin NOT NULL COMMENT '作業場所',
  `order_content` varchar(500) COLLATE utf8_bin DEFAULT NULL COMMENT '注文内容',
  `special_affairs` varchar(500) COLLATE utf8_bin DEFAULT NULL COMMENT '特記事項',
  `payment_cond` varchar(500) COLLATE utf8_bin DEFAULT NULL COMMENT 'お支払条件',
  `remark` varchar(500) COLLATE utf8_bin DEFAULT NULL COMMENT '備考',
  `sys_ins_user_id` varchar(10) COLLATE utf8_bin NOT NULL COMMENT '登録者',
  `sys_ins_dt` datetime NOT NULL COMMENT '登録日時',
  `sys_ins_pg_id` varchar(15) COLLATE utf8_bin NOT NULL COMMENT '登録機能ID',
  `sys_upd_user_id` varchar(10) COLLATE utf8_bin DEFAULT NULL COMMENT '更新者',
  `sys_upd_dt` datetime DEFAULT NULL COMMENT '更新日時',
  `sys_upd_pg_id` varchar(15) COLLATE utf8_bin DEFAULT NULL COMMENT '更新機能ID',
  PRIMARY KEY (`est_hid`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COLLATE=utf8_bin COMMENT='見積HDR情報';
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Table structure for table `jobtitle`
--

DROP TABLE IF EXISTS `jobtitle`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `jobtitle` (
  `job_title_cd` char(3) COLLATE utf8_bin NOT NULL COMMENT '職位C',
  `job_tile_nm` varchar(50) COLLATE utf8_bin NOT NULL COMMENT '職位名称',
  `use_flg` char(1) COLLATE utf8_bin NOT NULL DEFAULT 'Y' COMMENT '使用FLG:Y:使用\nN：廃止',
  `sys_ins_user_id` varchar(10) COLLATE utf8_bin NOT NULL COMMENT '登録者',
  `sys_ins_dt` datetime NOT NULL COMMENT '登録日時',
  `sys_ins_pg_id` varchar(15) COLLATE utf8_bin NOT NULL COMMENT '登録機能ID',
  `sys_upd_user_id` varchar(10) COLLATE utf8_bin DEFAULT NULL COMMENT '更新者',
  `sys_upd_dt` datetime DEFAULT NULL COMMENT '更新日時',
  `sys_upd_pg_id` varchar(15) COLLATE utf8_bin DEFAULT NULL COMMENT '更新機能ID',
  PRIMARY KEY (`job_title_cd`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COLLATE=utf8_bin COMMENT='職位';
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Table structure for table `login_log`
--

DROP TABLE IF EXISTS `login_log`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `login_log` (
  `id` int(65) NOT NULL AUTO_INCREMENT COMMENT 'キー',
  `logname` varchar(255) DEFAULT NULL COMMENT 'ログ名称',
  `userid` int(65) DEFAULT NULL COMMENT '管理者id',
  `createtime` datetime DEFAULT NULL COMMENT '作成時間',
  `succeed` varchar(255) DEFAULT NULL COMMENT '実行成功',
  `message` text COMMENT '詳細情報',
  `ip` varchar(255) DEFAULT NULL COMMENT '登録ip',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=338 DEFAULT CHARSET=utf8 COMMENT='登録ログ';
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Table structure for table `menu`
--

DROP TABLE IF EXISTS `menu`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `menu` (
  `id` int(65) NOT NULL AUTO_INCREMENT COMMENT 'キーid',
  `code` varchar(255) DEFAULT NULL COMMENT 'メニー番号',
  `pcode` varchar(255) DEFAULT NULL COMMENT 'メニー親番号',
  `pcodes` varchar(255) DEFAULT NULL COMMENT '親メニー番号',
  `name` varchar(255) DEFAULT NULL COMMENT 'メニー名称',
  `icon` varchar(255) DEFAULT NULL COMMENT 'メニーアイコン',
  `url` varchar(255) DEFAULT NULL COMMENT 'urlアドレス',
  `num` int(65) DEFAULT NULL COMMENT 'メニー順番号',
  `levels` int(65) DEFAULT NULL COMMENT 'メニー等級',
  `ismenu` int(11) DEFAULT NULL COMMENT 'メニーそうですか（1：はい  0：いいえ）',
  `tips` varchar(255) DEFAULT NULL COMMENT '備考',
  `status` int(65) DEFAULT NULL COMMENT 'メニー状態 :  1:はい   0:いいえ',
  `isopen` int(11) DEFAULT NULL COMMENT '開けてない:    1:はい   0:いいえ',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=168 DEFAULT CHARSET=utf8 COMMENT='メニー表';
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Table structure for table `menusubmenu`
--

DROP TABLE IF EXISTS `menusubmenu`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `menusubmenu` (
  `menu_cd` varchar(10) COLLATE utf8_bin NOT NULL COMMENT 'メニューCD',
  `sub_menu_cd` varchar(10) COLLATE utf8_bin NOT NULL COMMENT '子メニューCD',
  `display_order` int(11) NOT NULL COMMENT '表示順',
  `sys_ins_user_id` varchar(10) COLLATE utf8_bin NOT NULL COMMENT '登録者',
  `sys_ins_dt` datetime NOT NULL COMMENT '登録日時',
  `sys_ins_pg_id` varchar(15) COLLATE utf8_bin NOT NULL COMMENT '登録機能ID',
  `sys_upd_user_id` varchar(10) COLLATE utf8_bin DEFAULT NULL COMMENT '更新者',
  `sys_upd_dt` datetime DEFAULT NULL COMMENT '更新日時',
  `sys_upd_pg_id` varchar(15) COLLATE utf8_bin DEFAULT NULL COMMENT '更新機能ID',
  PRIMARY KEY (`menu_cd`,`sub_menu_cd`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COLLATE=utf8_bin COMMENT='メニュー・サブメニュー対照表';
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Table structure for table `moneyrev`
--

DROP TABLE IF EXISTS `moneyrev`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `moneyrev` (
  `money_rec_id` int(11) NOT NULL AUTO_INCREMENT COMMENT '入金ID',
  `req_dt` date NOT NULL COMMENT '入金日',
  `money_rec_amt` decimal(10,1) NOT NULL COMMENT '入金金額',
  `money_rec_remark` varchar(500) COLLATE utf8_bin DEFAULT NULL COMMENT '入金備考',
  `req_id` int(11) NOT NULL COMMENT '請求ID',
  `sys_ins_user_id` varchar(10) COLLATE utf8_bin NOT NULL COMMENT '登録者',
  `sys_ins_dt` datetime NOT NULL COMMENT '登録日時',
  `sys_ins_pg_id` varchar(15) COLLATE utf8_bin NOT NULL COMMENT '登録機能ID',
  `sys_upd_user_id` varchar(10) COLLATE utf8_bin DEFAULT NULL COMMENT '更新者',
  `sys_upd_dt` datetime DEFAULT NULL COMMENT '更新日時',
  `sys_upd_pg_id` varchar(15) COLLATE utf8_bin DEFAULT NULL COMMENT '更新機能ID',
  PRIMARY KEY (`money_rec_id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COLLATE=utf8_bin COMMENT='入金';
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Table structure for table `noseqmgr`
--

DROP TABLE IF EXISTS `noseqmgr`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `noseqmgr` (
  `sys_cd` varchar(10) COLLATE utf8_bin NOT NULL COMMENT 'システムC',
  `sys_tbl_id` varchar(255) COLLATE utf8_bin NOT NULL COMMENT 'テーブルID',
  `sys_field` varchar(255) COLLATE utf8_bin NOT NULL COMMENT 'フィールドID',
  `sys_prefix` varchar(2) COLLATE utf8_bin NOT NULL COMMENT '接頭辞',
  `sys_ym` varchar(4) COLLATE utf8_bin NOT NULL COMMENT '年月',
  `sys_seq_start_no` int(11) NOT NULL COMMENT '開始NO',
  `sys_seq_end_no` int(11) NOT NULL COMMENT '終了NO',
  `sys_current_no` int(11) NOT NULL COMMENT 'カレントNO',
  `sys_field_remark` varchar(50) COLLATE utf8_bin DEFAULT NULL COMMENT '備考',
  `sys_ins_user_id` varchar(10) COLLATE utf8_bin NOT NULL COMMENT '登録者',
  `sys_ins_dt` datetime NOT NULL COMMENT '登録日時',
  `sys_ins_pg_id` varchar(15) COLLATE utf8_bin NOT NULL COMMENT '登録機能ID',
  `sys_upd_user_id` varchar(10) COLLATE utf8_bin DEFAULT NULL COMMENT '更新者',
  `sys_upd_dt` datetime DEFAULT NULL COMMENT '更新日時',
  `sys_upd_pg_id` varchar(15) COLLATE utf8_bin DEFAULT NULL COMMENT '更新機能ID',
  PRIMARY KEY (`sys_cd`,`sys_tbl_id`,`sys_field`),
  UNIQUE KEY `sysSetting_u1` (`sys_field`,`sys_ym`,`sys_seq_start_no`,`sys_seq_end_no`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COLLATE=utf8_bin COMMENT='採番管理';
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Table structure for table `notice`
--

DROP TABLE IF EXISTS `notice`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `notice` (
  `id` int(11) NOT NULL AUTO_INCREMENT COMMENT 'キー',
  `title` varchar(255) DEFAULT NULL COMMENT 'タイトル',
  `type` int(11) DEFAULT NULL COMMENT 'タイプ',
  `content` text COMMENT '内容',
  `createtime` datetime DEFAULT NULL COMMENT '作成時間',
  `creater` int(11) DEFAULT NULL COMMENT '作成者',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=9 DEFAULT CHARSET=utf8 COMMENT='お知らせ表';
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Table structure for table `operation_log`
--

DROP TABLE IF EXISTS `operation_log`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `operation_log` (
  `id` int(65) NOT NULL AUTO_INCREMENT COMMENT 'キー',
  `logtype` varchar(255) DEFAULT NULL COMMENT 'ログタイプ',
  `logname` varchar(255) DEFAULT NULL COMMENT 'ログ名称',
  `userid` int(65) DEFAULT NULL COMMENT 'ユーザーid',
  `classname` varchar(255) DEFAULT NULL COMMENT 'クラス名称',
  `method` text COMMENT 'メソッド名称',
  `createtime` datetime DEFAULT NULL COMMENT '作成時間',
  `succeed` varchar(255) DEFAULT NULL COMMENT '成功',
  `message` text COMMENT '備考',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=518 DEFAULT CHARSET=utf8 COMMENT='おべログ';
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Table structure for table `orderconfirm`
--

DROP TABLE IF EXISTS `orderconfirm`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `orderconfirm` (
  `order_hid` int(11) NOT NULL COMMENT '受注HID',
  `order_confirm_user_id` varchar(10) COLLATE utf8_bin NOT NULL COMMENT '受注確認者ID',
  `order_confirm_dt` datetime NOT NULL COMMENT '受注確認日時',
  `order_confirm_remark` varchar(500) COLLATE utf8_bin NOT NULL COMMENT '受注確認備考',
  `sys_ins_user_id` varchar(10) COLLATE utf8_bin NOT NULL COMMENT '登録者',
  `sys_ins_dt` datetime NOT NULL COMMENT '登録日時',
  `sys_ins_pg_id` varchar(15) COLLATE utf8_bin NOT NULL COMMENT '登録機能ID',
  `sys_upd_user_id` varchar(10) COLLATE utf8_bin DEFAULT NULL COMMENT '更新者',
  `sys_upd_dt` datetime DEFAULT NULL COMMENT '更新日時',
  `sys_upd_pg_id` varchar(15) COLLATE utf8_bin DEFAULT NULL COMMENT '更新機能ID',
  PRIMARY KEY (`order_hid`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COLLATE=utf8_bin COMMENT='受注確定情報';
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Table structure for table `orderdtl`
--

DROP TABLE IF EXISTS `orderdtl`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `orderdtl` (
  `order_did` int(11) NOT NULL AUTO_INCREMENT COMMENT '受注DID',
  `order_hid` int(11) NOT NULL COMMENT '受注HID',
  `appr_doc_id` int(11) NOT NULL COMMENT '稟議書ID',
  `sys_ins_user_id` varchar(10) COLLATE utf8_bin NOT NULL COMMENT '登録者',
  `sys_ins_dt` datetime NOT NULL COMMENT '登録日時',
  `sys_ins_pg_id` varchar(15) COLLATE utf8_bin NOT NULL COMMENT '登録機能ID',
  `sys_upd_user_id` varchar(10) COLLATE utf8_bin DEFAULT NULL COMMENT '更新者',
  `sys_upd_dt` datetime DEFAULT NULL COMMENT '更新日時',
  `sys_upd_pg_id` varchar(15) COLLATE utf8_bin DEFAULT NULL COMMENT '更新機能ID',
  PRIMARY KEY (`order_did`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COLLATE=utf8_bin COMMENT='受注DTL情報';
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Table structure for table `orderhdr`
--

DROP TABLE IF EXISTS `orderhdr`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `orderhdr` (
  `order_hid` int(11) NOT NULL AUTO_INCREMENT COMMENT '受注HID',
  `order_no` varchar(10) COLLATE utf8_bin NOT NULL COMMENT '受注NO',
  `use_flg` char(1) COLLATE utf8_bin NOT NULL DEFAULT 'Y' COMMENT '使用FLG:Y:使用\nN:削除',
  `create_dt` date NOT NULL COMMENT '作成日',
  `order_amt_without_tax` decimal(13,1) NOT NULL COMMENT '受注金額(税抜)',
  `work_start_ymd` date NOT NULL COMMENT '作業開始日',
  `work_end_ymd` date NOT NULL COMMENT '作業終了日',
  `project_no` varchar(10) COLLATE utf8_bin NOT NULL COMMENT '案件番号',
  `project_nm` varchar(50) COLLATE utf8_bin DEFAULT NULL COMMENT '案件名称',
  `work_content` varchar(200) COLLATE utf8_bin DEFAULT NULL COMMENT '作業内容',
  `work_place` varchar(100) COLLATE utf8_bin DEFAULT NULL COMMENT '作業場所',
  `order_content` varchar(500) COLLATE utf8_bin DEFAULT NULL COMMENT '注文内容',
  `special_affairs` varchar(500) COLLATE utf8_bin DEFAULT NULL COMMENT '特記事項',
  `remark` varchar(500) COLLATE utf8_bin DEFAULT NULL COMMENT '備考',
  `order_doc_file_id` int(11) DEFAULT NULL COMMENT '注文書',
  `est_hid` int(11) DEFAULT NULL COMMENT '見積HID',
  `sys_ins_user_id` varchar(10) COLLATE utf8_bin NOT NULL COMMENT '登録者',
  `sys_ins_dt` datetime NOT NULL COMMENT '登録日時',
  `sys_ins_pg_id` varchar(15) COLLATE utf8_bin NOT NULL COMMENT '登録機能ID',
  `sys_upd_user_id` varchar(10) COLLATE utf8_bin DEFAULT NULL COMMENT '更新者',
  `sys_upd_dt` datetime DEFAULT NULL COMMENT '更新日時',
  `sys_upd_pg_id` varchar(15) COLLATE utf8_bin DEFAULT NULL COMMENT '更新機能ID',
  PRIMARY KEY (`order_hid`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COLLATE=utf8_bin COMMENT='受注HDR情報';
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Table structure for table `program`
--

DROP TABLE IF EXISTS `program`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `program` (
  `pg_id` varchar(15) COLLATE utf8_bin NOT NULL DEFAULT '' COMMENT '機能ID',
  `pg_nm` varchar(50) COLLATE utf8_bin NOT NULL COMMENT '機能名',
  `use_flg` char(1) COLLATE utf8_bin NOT NULL DEFAULT 'Y' COMMENT '使用FLG:Y:使用\nN:削除',
  `sys_ins_user_id` varchar(10) COLLATE utf8_bin NOT NULL COMMENT '登録者',
  `sys_ins_dt` datetime NOT NULL COMMENT '登録日時',
  `sys_ins_pg_id` varchar(15) COLLATE utf8_bin NOT NULL COMMENT '登録機能ID',
  `sys_upd_user_id` varchar(10) COLLATE utf8_bin DEFAULT NULL COMMENT '更新者',
  `sys_upd_dt` datetime DEFAULT NULL COMMENT '更新日時',
  `sys_upd_pg_id` varchar(15) COLLATE utf8_bin DEFAULT NULL COMMENT '更新機能ID',
  PRIMARY KEY (`pg_id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COLLATE=utf8_bin COMMENT='機能情報';
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Table structure for table `ratiomgr`
--

DROP TABLE IF EXISTS `ratiomgr`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `ratiomgr` (
  `ratio_mgr_id` int(11) NOT NULL AUTO_INCREMENT COMMENT '料率管理ID',
  `ratio_type` char(1) COLLATE utf8_bin NOT NULL COMMENT '料率タイプ:1：健康保険\n2：厚生年金',
  `start_dt` date NOT NULL COMMENT '開始日',
  `end_dt` date NOT NULL DEFAULT '2099-12-31' COMMENT '終了日',
  `age_from` int(11) NOT NULL COMMENT '年齢From',
  `age_to` int(11) NOT NULL DEFAULT '999' COMMENT '年齢To(含まない)',
  `ratio` decimal(5,2) NOT NULL COMMENT '料率',
  `sys_ins_user_id` varchar(10) COLLATE utf8_bin NOT NULL COMMENT '登録者',
  `sys_ins_dt` datetime NOT NULL COMMENT '登録日時',
  `sys_ins_pg_id` varchar(15) COLLATE utf8_bin NOT NULL COMMENT '登録機能ID',
  `sys_upd_user_id` varchar(10) COLLATE utf8_bin DEFAULT NULL COMMENT '更新者',
  `sys_upd_dt` datetime DEFAULT NULL COMMENT '更新日時',
  `sys_upd_pg_id` varchar(15) COLLATE utf8_bin DEFAULT NULL COMMENT '更新機能ID',
  PRIMARY KEY (`ratio_mgr_id`),
  UNIQUE KEY `ratioMgr_u_atl1` (`ratio_type`,`age_from`,`age_to`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COLLATE=utf8_bin COMMENT='料率管理';
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Table structure for table `relation`
--

DROP TABLE IF EXISTS `relation`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `relation` (
  `id` int(11) NOT NULL AUTO_INCREMENT COMMENT 'キー',
  `menuid` int(11) DEFAULT NULL COMMENT 'メニーid',
  `roleid` int(11) DEFAULT NULL COMMENT 'ロールid',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=3679 DEFAULT CHARSET=utf8 COMMENT='ロールメニー関連表';
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Table structure for table `request`
--

DROP TABLE IF EXISTS `request`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `request` (
  `req_id` int(11) NOT NULL AUTO_INCREMENT COMMENT '請求ID',
  `req_dt` date NOT NULL COMMENT '請求日',
  `req_user_id` varchar(10) COLLATE utf8_bin NOT NULL COMMENT '請求担当',
  `req_amt_without_tax` decimal(10,1) NOT NULL COMMENT '請求金額（税抜）:請求金額（税抜）の合計',
  `req_amt_tax` decimal(10,1) DEFAULT NULL COMMENT '消費税',
  `req_amt_with_tax` decimal(10,1) DEFAULT NULL COMMENT '請求金額（税込）',
  `req_remark` varchar(500) COLLATE utf8_bin DEFAULT NULL COMMENT '請求備考',
  `sys_ins_user_id` varchar(10) COLLATE utf8_bin NOT NULL COMMENT '登録者',
  `sys_ins_dt` datetime NOT NULL COMMENT '登録日時',
  `sys_ins_pg_id` varchar(15) COLLATE utf8_bin NOT NULL COMMENT '登録機能ID',
  `sys_upd_user_id` varchar(10) COLLATE utf8_bin DEFAULT NULL COMMENT '更新者',
  `sys_upd_dt` datetime DEFAULT NULL COMMENT '更新日時',
  `sys_upd_pg_id` varchar(15) COLLATE utf8_bin DEFAULT NULL COMMENT '更新機能ID',
  PRIMARY KEY (`req_id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COLLATE=utf8_bin COMMENT='請求';
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Table structure for table `requestaccepted`
--

DROP TABLE IF EXISTS `requestaccepted`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `requestaccepted` (
  `req_id` int(11) NOT NULL COMMENT '請求ID',
  `last_accept_user_id` varchar(10) COLLATE utf8_bin NOT NULL COMMENT '最終承認者ID',
  `last_accept_date` datetime NOT NULL COMMENT '最終承認日時',
  `req_apply_hid` int(11) NOT NULL COMMENT '請求申請HID',
  `sys_ins_user_id` varchar(10) COLLATE utf8_bin NOT NULL COMMENT '登録者',
  `sys_ins_dt` datetime NOT NULL COMMENT '登録日時',
  `sys_ins_pg_id` varchar(15) COLLATE utf8_bin NOT NULL COMMENT '登録機能ID',
  `sys_upd_user_id` varchar(10) COLLATE utf8_bin DEFAULT NULL COMMENT '更新者',
  `sys_upd_dt` datetime DEFAULT NULL COMMENT '更新日時',
  `sys_upd_pg_id` varchar(15) COLLATE utf8_bin DEFAULT NULL COMMENT '更新機能ID',
  PRIMARY KEY (`req_id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COLLATE=utf8_bin COMMENT='承認済請求';
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Table structure for table `requestapplydtl`
--

DROP TABLE IF EXISTS `requestapplydtl`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `requestapplydtl` (
  `req_apply_did` int(11) NOT NULL AUTO_INCREMENT COMMENT '請求申請DID',
  `req_apply_hid` int(11) NOT NULL COMMENT '請求申請HID',
  `display_order` int(11) NOT NULL COMMENT '表示順',
  `accept_user_id` varchar(10) COLLATE utf8_bin NOT NULL COMMENT '承認者ID',
  `confirm_st` char(1) COLLATE utf8_bin NOT NULL COMMENT '承認状況:1：未承認\n2：承認済\n3：拒否',
  `con_rej_comment` varchar(500) COLLATE utf8_bin NOT NULL COMMENT '承認(拒否)コメント',
  `con_rej_dt` datetime DEFAULT NULL COMMENT '承認(拒否)日時',
  `sys_ins_user_id` varchar(10) COLLATE utf8_bin NOT NULL COMMENT '登録者',
  `sys_ins_dt` datetime NOT NULL COMMENT '登録日時',
  `sys_ins_pg_id` varchar(15) COLLATE utf8_bin NOT NULL COMMENT '登録機能ID',
  `sys_upd_user_id` varchar(10) COLLATE utf8_bin DEFAULT NULL COMMENT '更新者',
  `sys_upd_dt` datetime DEFAULT NULL COMMENT '更新日時',
  `sys_upd_pg_id` varchar(15) COLLATE utf8_bin DEFAULT NULL COMMENT '更新機能ID',
  PRIMARY KEY (`req_apply_did`),
  UNIQUE KEY `apprApplyDtl_u_1` (`req_apply_hid`,`display_order`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COLLATE=utf8_bin COMMENT='請求申請DTL情報';
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Table structure for table `requestapplyhdr`
--

DROP TABLE IF EXISTS `requestapplyhdr`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `requestapplyhdr` (
  `req_apply_hid` int(11) NOT NULL AUTO_INCREMENT COMMENT '請求申請HID',
  `req_id` int(11) NOT NULL COMMENT '稟議書ID',
  `use_flg` char(1) COLLATE utf8_bin NOT NULL DEFAULT 'Y' COMMENT '使用FLG:Y:使用\nN:削除',
  `apply_reason` varchar(500) COLLATE utf8_bin DEFAULT NULL COMMENT '申請理由',
  `apply_user_id` varchar(10) COLLATE utf8_bin NOT NULL COMMENT '申請者ID',
  `apply_dt` datetime NOT NULL COMMENT '申請日時',
  `confirm_wait_display_order` int(11) DEFAULT NULL COMMENT '承認待表示順:承認待となっている申請DTLの表示順を設定',
  `sys_ins_user_id` varchar(10) COLLATE utf8_bin NOT NULL COMMENT '登録者',
  `sys_ins_dt` datetime NOT NULL COMMENT '登録日時',
  `sys_ins_pg_id` varchar(15) COLLATE utf8_bin NOT NULL COMMENT '登録機能ID',
  `sys_upd_user_id` varchar(10) COLLATE utf8_bin DEFAULT NULL COMMENT '更新者',
  `sys_upd_dt` datetime DEFAULT NULL COMMENT '更新日時',
  `sys_upd_pg_id` varchar(15) COLLATE utf8_bin DEFAULT NULL COMMENT '更新機能ID',
  PRIMARY KEY (`req_apply_hid`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COLLATE=utf8_bin COMMENT='請求申請HDR情報';
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Table structure for table `role`
--

DROP TABLE IF EXISTS `role`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `role` (
  `id` int(11) NOT NULL AUTO_INCREMENT COMMENT 'キーid',
  `num` int(11) DEFAULT NULL COMMENT '順番',
  `pid` int(11) DEFAULT NULL COMMENT '親ロールid',
  `name` varchar(255) DEFAULT NULL COMMENT 'ロール名称',
  `deptid` int(11) DEFAULT NULL COMMENT '部門名称',
  `tips` varchar(255) DEFAULT NULL COMMENT 'ヒント',
  `version` int(11) DEFAULT NULL COMMENT '保持フィールド',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=6 DEFAULT CHARSET=utf8 COMMENT='ロール表';
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Table structure for table `salereqrel`
--

DROP TABLE IF EXISTS `salereqrel`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `salereqrel` (
  `req_id` int(11) NOT NULL COMMENT '請求ID',
  `sales_id` int(11) NOT NULL COMMENT '売上ID',
  `sys_ins_user_id` varchar(10) COLLATE utf8_bin NOT NULL COMMENT '登録者',
  `sys_ins_dt` datetime NOT NULL COMMENT '登録日時',
  `sys_ins_pg_id` varchar(15) COLLATE utf8_bin NOT NULL COMMENT '登録機能ID',
  `sys_upd_user_id` varchar(10) COLLATE utf8_bin DEFAULT NULL COMMENT '更新者',
  `sys_upd_dt` datetime DEFAULT NULL COMMENT '更新日時',
  `sys_upd_pg_id` varchar(15) COLLATE utf8_bin DEFAULT NULL COMMENT '更新機能ID',
  PRIMARY KEY (`req_id`,`sales_id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COLLATE=utf8_bin COMMENT='売上・請求・対照表';
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Table structure for table `sales`
--

DROP TABLE IF EXISTS `sales`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `sales` (
  `sales_id` int(11) NOT NULL AUTO_INCREMENT COMMENT '売上ID',
  `order_hid` int(11) NOT NULL COMMENT '受注HID',
  `order_did` int(11) NOT NULL COMMENT '受注DID',
  `appr_doc_id` int(11) NOT NULL COMMENT '稟議書ID',
  `add_up_ym` varchar(6) COLLATE utf8_bin NOT NULL COMMENT '計上年月',
  `add_up_dt` date NOT NULL COMMENT '計上日',
  `emp_k` char(1) COLLATE utf8_bin NOT NULL COMMENT '要員区分/補助:1：新規要員\n2：既存要員\n3：要員退社',
  `emp_no` varchar(10) COLLATE utf8_bin NOT NULL COMMENT '社員番号',
  `emp_nm` varchar(30) COLLATE utf8_bin NOT NULL COMMENT '社員名',
  `emp_nm_fuli` varchar(30) COLLATE utf8_bin NOT NULL COMMENT '社員名(フリガナ)',
  `bussiness_main_user_id` varchar(10) COLLATE utf8_bin NOT NULL COMMENT '営業主担当',
  `sales_remark` varchar(500) COLLATE utf8_bin DEFAULT NULL COMMENT '売上登録備考',
  `sys_ins_user_id` varchar(10) COLLATE utf8_bin NOT NULL COMMENT '登録者',
  `sys_ins_dt` datetime NOT NULL COMMENT '登録日時',
  `sys_ins_pg_id` varchar(15) COLLATE utf8_bin NOT NULL COMMENT '登録機能ID',
  `sys_upd_user_id` varchar(10) COLLATE utf8_bin DEFAULT NULL COMMENT '更新者',
  `sys_upd_dt` datetime DEFAULT NULL COMMENT '更新日時',
  `sys_upd_pg_id` varchar(15) COLLATE utf8_bin DEFAULT NULL COMMENT '更新機能ID',
  PRIMARY KEY (`sales_id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COLLATE=utf8_bin COMMENT='売上';
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Table structure for table `salescostdtl`
--

DROP TABLE IF EXISTS `salescostdtl`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `salescostdtl` (
  `sales_id` int(11) NOT NULL COMMENT '売上ID',
  `payment_dat` date DEFAULT NULL COMMENT '締日',
  `pj_nm` varchar(200) COLLATE utf8_bin DEFAULT NULL COMMENT '作業案件名称',
  `bp_comp_id` varchar(10) COLLATE utf8_bin DEFAULT NULL COMMENT '所属会社ID',
  `bp_comp_emp_nm` varchar(10) COLLATE utf8_bin DEFAULT NULL COMMENT '所属会社名/社員氏名',
  `bp_cost_price` decimal(10,1) DEFAULT NULL COMMENT '社保加算後単価:加入の場合、単価＋交通費＋（健康保険+厚生年金）＊1.0085\n加入しない場合、単価＋交通費',
  `bp_cost_amt_without_tax` decimal(10,1) DEFAULT NULL COMMENT '社保加算金額（税抜）:社保加算後単価＊工数（月次用）',
  `bp_cost_tax` decimal(10,1) DEFAULT NULL COMMENT '消費税',
  `bp_cost_amt_with_tax` decimal(10,1) DEFAULT NULL COMMENT '社保加算金額（税込）',
  `payment_plan_d` int(11) DEFAULT NULL COMMENT '支払サイト/出金日付の日',
  `payment_due_k` char(1) COLLATE utf8_bin DEFAULT NULL COMMENT '支払サイト/締日区分',
  `pay_month_k` char(1) COLLATE utf8_bin DEFAULT NULL COMMENT '支払サイト/支払月区分',
  `pay_plan_d` int(11) DEFAULT NULL COMMENT '支払サイト/支払日付の日',
  `salary_pay_plan_dt` date DEFAULT NULL COMMENT '出金予定日',
  `sales_cost_remark` varchar(500) COLLATE utf8_bin DEFAULT NULL COMMENT '売上原価備考',
  `sys_ins_user_id` varchar(10) COLLATE utf8_bin NOT NULL COMMENT '登録者',
  `sys_ins_dt` datetime NOT NULL COMMENT '登録日時',
  `sys_ins_pg_id` varchar(15) COLLATE utf8_bin NOT NULL COMMENT '登録機能ID',
  `sys_upd_user_id` varchar(10) COLLATE utf8_bin DEFAULT NULL COMMENT '更新者',
  `sys_upd_dt` datetime DEFAULT NULL COMMENT '更新日時',
  `sys_upd_pg_id` varchar(15) COLLATE utf8_bin DEFAULT NULL COMMENT '更新機能ID',
  PRIMARY KEY (`sales_id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COLLATE=utf8_bin COMMENT='売上_原価明細';
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Table structure for table `salesincentivedtl`
--

DROP TABLE IF EXISTS `salesincentivedtl`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `salesincentivedtl` (
  `sales_id` int(11) NOT NULL COMMENT '売上ID',
  `payment_dat` date DEFAULT NULL COMMENT '締日',
  `pj_nm` varchar(200) COLLATE utf8_bin DEFAULT NULL COMMENT '作業案件名称',
  `client_no` varchar(10) COLLATE utf8_bin DEFAULT NULL COMMENT 'クライアント番号',
  `client_nm` varchar(50) COLLATE utf8_bin DEFAULT NULL COMMENT 'クライアント名称',
  `sys_ins_user_id` varchar(10) COLLATE utf8_bin NOT NULL COMMENT '登録者',
  `sys_ins_dt` datetime NOT NULL COMMENT '登録日時',
  `sys_ins_pg_id` varchar(15) COLLATE utf8_bin NOT NULL COMMENT '登録機能ID',
  `sys_upd_user_id` varchar(10) COLLATE utf8_bin DEFAULT NULL COMMENT '更新者',
  `sys_upd_dt` datetime DEFAULT NULL COMMENT '更新日時',
  `sys_upd_pg_id` varchar(15) COLLATE utf8_bin DEFAULT NULL COMMENT '更新機能ID',
  PRIMARY KEY (`sales_id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COLLATE=utf8_bin COMMENT='売上_インセンティブ明細';
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Table structure for table `salesreqdtl`
--

DROP TABLE IF EXISTS `salesreqdtl`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `salesreqdtl` (
  `sales_id` int(11) NOT NULL COMMENT '売上ID',
  `payment_dat` date NOT NULL COMMENT '締日:クライアントの締日',
  `next_pj_k` char(1) COLLATE utf8_bin DEFAULT NULL COMMENT '次のPJ:次のPJ（売上）選択し\n1：既存\n2：経験',
  `pj_nm` varchar(200) COLLATE utf8_bin DEFAULT NULL COMMENT '作業案件名称',
  `work_content` varchar(200) COLLATE utf8_bin DEFAULT NULL COMMENT '作業内容・範囲',
  `work_place` varchar(200) COLLATE utf8_bin DEFAULT NULL COMMENT '勤務地/最寄駅',
  `client_no` varchar(10) COLLATE utf8_bin NOT NULL COMMENT 'クライアント番号',
  `client_nm` varchar(50) COLLATE utf8_bin NOT NULL COMMENT 'クライアント名',
  `req_start_dt` date DEFAULT NULL COMMENT '期間FROM:請求書',
  `req_end_dt` date DEFAULT NULL COMMENT '期間TO:請求書',
  `sell_price` decimal(10,1) NOT NULL COMMENT '上位単金/単価:請求書の基準単価',
  `current_mon_work_time` decimal(4,2) NOT NULL COMMENT '工数（月次用）:当月入社の場合、小数部、そのほか、１とする',
  `wh_max` decimal(4,1) DEFAULT NULL COMMENT '作業時間（上限・H）',
  `wh_min` decimal(4,1) DEFAULT NULL COMMENT '作業時間（下限・H）',
  `real_work_hour` decimal(4,1) DEFAULT NULL COMMENT '実績時間:勤怠情報から取得（単位：時間）',
  `excess_price` decimal(10,1) DEFAULT NULL COMMENT '超過単価（円）',
  `deducation_price` decimal(10,1) DEFAULT NULL COMMENT '控除単価（円）',
  `work_amt` decimal(10,1) DEFAULT NULL COMMENT '金額:工数（月次用）＊基準単価\n手動変更可能か？',
  `travel_cost` decimal(10,1) DEFAULT NULL COMMENT '交通費',
  `work_time_unit_k` char(1) COLLATE utf8_bin DEFAULT NULL COMMENT '作業時間単位:1:10分　2：15分　3：30分　4：1時',
  `other_cost` decimal(10,1) DEFAULT NULL COMMENT '経費/オンライン当番',
  `pay_month_k` char(1) COLLATE utf8_bin DEFAULT NULL COMMENT '支払サイト/支払月区分:1：翌月/2：翌々月/3：翌々々月',
  `pay_plan_d` int(11) DEFAULT NULL COMMENT '支払サイト/支払日付の日',
  `client_pay_dt` date DEFAULT NULL COMMENT 'お支払期日:支払月＝当月＋支払サイト/支払月区分（1：翌月/2：翌々月/3：翌々々月）\nお支払期日＝ 支払月+支払サイト/支払日付の日',
  `overwork_amt` decimal(10,1) DEFAULT NULL COMMENT '残業代:計算方法が確認要',
  `deduction_amt` decimal(10,1) DEFAULT NULL COMMENT '控除金額:計算方法が確認要\n欠勤控除？',
  `client_pay_without_tax` decimal(10,1) DEFAULT NULL COMMENT '税抜金額:基本金額＋交通費＋経費',
  `client_pay_tax` decimal(10,1) DEFAULT NULL COMMENT '消 費 税:税抜金額＊消費税',
  `client_pay_with_tax` decimal(10,1) DEFAULT NULL COMMENT '税込金額:税抜合計＋税込合計',
  `pj_class_nm` varchar(200) COLLATE utf8_bin DEFAULT NULL COMMENT '案件分類名称',
  `gross_amt` decimal(10,1) DEFAULT NULL COMMENT '粗利額:請求の税抜金額-原価の税抜金額',
  `sales_req_remark` varchar(500) COLLATE utf8_bin DEFAULT NULL COMMENT '売上請求備考',
  `money_rec_plan_dt` date DEFAULT NULL COMMENT '入金予定日:手入力',
  `pay_lt` int(11) DEFAULT NULL COMMENT '支払LT:売上請求明細の入金予定日-原価明細の出金予定日',
  `sys_ins_user_id` varchar(10) COLLATE utf8_bin NOT NULL COMMENT '登録者',
  `sys_ins_dt` datetime NOT NULL COMMENT '登録日時',
  `sys_ins_pg_id` varchar(15) COLLATE utf8_bin NOT NULL COMMENT '登録機能ID',
  `sys_upd_user_id` varchar(10) COLLATE utf8_bin DEFAULT NULL COMMENT '更新者',
  `sys_upd_dt` datetime DEFAULT NULL COMMENT '更新日時',
  `sys_upd_pg_id` varchar(15) COLLATE utf8_bin DEFAULT NULL COMMENT '更新機能ID',
  PRIMARY KEY (`sales_id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COLLATE=utf8_bin COMMENT='売上_請求明細';
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Table structure for table `submenu`
--

DROP TABLE IF EXISTS `submenu`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `submenu` (
  `sub_menu_cd` varchar(10) COLLATE utf8_bin NOT NULL COMMENT 'サブメニューCD',
  `sub_menu_nam` varchar(50) COLLATE utf8_bin NOT NULL COMMENT 'サブメニュー名',
  `use_flg` char(1) COLLATE utf8_bin DEFAULT 'Y' COMMENT '使用FLG:Y:使用\nN:削除',
  `sys_ins_user_id` varchar(10) COLLATE utf8_bin NOT NULL COMMENT '登録者',
  `sys_ins_dt` datetime NOT NULL COMMENT '登録日時',
  `sys_ins_pg_id` varchar(15) COLLATE utf8_bin NOT NULL COMMENT '登録機能ID',
  `sys_upd_user_id` varchar(10) COLLATE utf8_bin DEFAULT NULL COMMENT '更新者',
  `sys_upd_dt` datetime DEFAULT NULL COMMENT '更新日時',
  `sys_upd_pg_id` varchar(15) COLLATE utf8_bin DEFAULT NULL COMMENT '更新機能ID',
  PRIMARY KEY (`sub_menu_cd`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COLLATE=utf8_bin COMMENT='サブメニュー情報';
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Table structure for table `submenuprogram`
--

DROP TABLE IF EXISTS `submenuprogram`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `submenuprogram` (
  `sub_menu_cd` varchar(10) COLLATE utf8_bin NOT NULL COMMENT 'サブメニューCD',
  `menu_type` char(1) COLLATE utf8_bin NOT NULL COMMENT 'メニュータイプ:M:メニュー\nP：機能',
  `pg_id` varchar(15) COLLATE utf8_bin NOT NULL COMMENT '機能ID',
  `low_sub_menu_cd` varchar(10) COLLATE utf8_bin NOT NULL COMMENT '下位サブメニューCD',
  `display_order` int(11) NOT NULL COMMENT '表示順',
  `sys_ins_user_id` varchar(10) COLLATE utf8_bin NOT NULL COMMENT '登録者',
  `sys_ins_dt` datetime NOT NULL COMMENT '登録日時',
  `sys_ins_pg_id` varchar(15) COLLATE utf8_bin NOT NULL COMMENT '登録機能ID',
  `sys_upd_user_id` varchar(10) COLLATE utf8_bin DEFAULT NULL COMMENT '更新者',
  `sys_upd_dt` datetime DEFAULT NULL COMMENT '更新日時',
  `sys_upd_pg_id` varchar(15) COLLATE utf8_bin DEFAULT NULL COMMENT '更新機能ID',
  PRIMARY KEY (`sub_menu_cd`,`menu_type`,`pg_id`,`low_sub_menu_cd`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COLLATE=utf8_bin COMMENT='サブメニュー・機能対照表';
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Table structure for table `sysfilemgr`
--

DROP TABLE IF EXISTS `sysfilemgr`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `sysfilemgr` (
  `file_id` int(11) NOT NULL AUTO_INCREMENT COMMENT 'ファイルID',
  `file_ext` varchar(8) COLLATE utf8_bin DEFAULT NULL COMMENT '拡張子',
  `mime_type` varchar(64) COLLATE utf8_bin DEFAULT NULL COMMENT 'MimeType',
  `file_nm` varchar(64) COLLATE utf8_bin DEFAULT NULL COMMENT 'オリジナルファイル名',
  `file_path` varchar(255) COLLATE utf8_bin DEFAULT NULL COMMENT '保存相対パス',
  `file_size` int(11) DEFAULT NULL COMMENT 'ファイルサイズ',
  `file_user_id` varchar(10) COLLATE utf8_bin DEFAULT NULL COMMENT '保存ユーザ',
  `file_ymd` datetime DEFAULT NULL COMMENT '保存日時',
  `use_flg` char(1) COLLATE utf8_bin DEFAULT 'Y' COMMENT '使用FLG:Y:使用\nN:削除',
  `sys_ins_user_id` varchar(10) COLLATE utf8_bin NOT NULL COMMENT '登録者',
  `sys_ins_dt` datetime NOT NULL COMMENT '登録日時',
  `sys_ins_pg_id` varchar(15) COLLATE utf8_bin NOT NULL COMMENT '登録機能ID',
  `sys_upd_user_id` varchar(10) COLLATE utf8_bin DEFAULT NULL COMMENT '更新者',
  `sys_upd_dt` datetime DEFAULT NULL COMMENT '更新日時',
  `sys_upd_pg_id` varchar(15) COLLATE utf8_bin DEFAULT NULL COMMENT '更新機能ID',
  PRIMARY KEY (`file_id`),
  UNIQUE KEY `sysSetting_u1` (`file_ext`,`mime_type`,`file_nm`,`file_path`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COLLATE=utf8_bin COMMENT='ファイル管理';
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Table structure for table `syssetting`
--

DROP TABLE IF EXISTS `syssetting`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `syssetting` (
  `sys_setting_id` int(11) NOT NULL AUTO_INCREMENT COMMENT '設定ID',
  `sys_cd` varchar(10) COLLATE utf8_bin DEFAULT NULL COMMENT 'システムC',
  `major_class_cd` varchar(10) COLLATE utf8_bin DEFAULT NULL COMMENT '大分類C',
  `mid_class_cd` varchar(10) COLLATE utf8_bin DEFAULT NULL COMMENT '中分類C',
  `minor_class_cd` varchar(10) COLLATE utf8_bin DEFAULT NULL COMMENT '小分類C',
  `str_val1` varchar(50) COLLATE utf8_bin DEFAULT NULL COMMENT '文字列1',
  `str_val2` varchar(50) COLLATE utf8_bin DEFAULT NULL COMMENT '文字列2',
  `str_val3` varchar(50) COLLATE utf8_bin DEFAULT NULL COMMENT '文字列3',
  `str_val4` varchar(50) COLLATE utf8_bin DEFAULT NULL COMMENT '文字列4',
  `str_val5` varchar(50) COLLATE utf8_bin DEFAULT NULL COMMENT '文字列5',
  `num_val1` decimal(10,3) DEFAULT NULL COMMENT '数値1',
  `num_val2` decimal(10,3) DEFAULT NULL COMMENT '数値2',
  `num_val3` decimal(10,3) DEFAULT NULL COMMENT '数値3',
  `num_val4` decimal(10,3) DEFAULT NULL COMMENT '数値4',
  `num_val5` decimal(10,3) DEFAULT NULL COMMENT '数値5',
  `remark1` varchar(500) COLLATE utf8_bin DEFAULT NULL COMMENT '備考1',
  `remark2` varchar(500) COLLATE utf8_bin DEFAULT NULL COMMENT '備考2',
  `sys_ins_user_id` varchar(10) COLLATE utf8_bin NOT NULL COMMENT '登録者',
  `sys_ins_dt` datetime NOT NULL COMMENT '登録日時',
  `sys_ins_pg_id` varchar(15) COLLATE utf8_bin NOT NULL COMMENT '登録機能ID',
  `sys_upd_user_id` varchar(10) COLLATE utf8_bin DEFAULT NULL COMMENT '更新者',
  `sys_upd_dt` datetime DEFAULT NULL COMMENT '更新日時',
  `sys_upd_pg_id` varchar(15) COLLATE utf8_bin DEFAULT NULL COMMENT '更新機能ID',
  PRIMARY KEY (`sys_setting_id`),
  UNIQUE KEY `sysSetting_u1` (`sys_cd`,`major_class_cd`,`mid_class_cd`,`minor_class_cd`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COLLATE=utf8_bin COMMENT='設定';
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Table structure for table `taxmgr`
--

DROP TABLE IF EXISTS `taxmgr`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `taxmgr` (
  `tax_mgr_id` int(11) NOT NULL AUTO_INCREMENT COMMENT '消費税管理ID',
  `start_dt` date NOT NULL COMMENT '開始日',
  `end_dt` date NOT NULL DEFAULT '2099-12-31' COMMENT '終了日',
  `tax_rate` decimal(5,2) NOT NULL COMMENT '消費率',
  `sys_ins_user_id` varchar(10) COLLATE utf8_bin NOT NULL COMMENT '登録者',
  `sys_ins_dt` datetime NOT NULL COMMENT '登録日時',
  `sys_ins_pg_id` varchar(15) COLLATE utf8_bin NOT NULL COMMENT '登録機能ID',
  `sys_upd_user_id` varchar(10) COLLATE utf8_bin DEFAULT NULL COMMENT '更新者',
  `sys_upd_dt` datetime DEFAULT NULL COMMENT '更新日時',
  `sys_upd_pg_id` varchar(15) COLLATE utf8_bin DEFAULT NULL COMMENT '更新機能ID',
  PRIMARY KEY (`tax_mgr_id`),
  UNIQUE KEY `taxMgr_u_atl1` (`start_dt`,`end_dt`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COLLATE=utf8_bin COMMENT='消費税管理';
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Table structure for table `test`
--

DROP TABLE IF EXISTS `test`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `test` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `value` varchar(255) DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Table structure for table `tsrs_menu`
--

DROP TABLE IF EXISTS `tsrs_menu`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `tsrs_menu` (
  `menu_cd` varchar(10) COLLATE utf8_bin NOT NULL COMMENT 'メニューC',
  `menu_nm` varchar(50) COLLATE utf8_bin NOT NULL COMMENT 'メニュー名',
  `use_flg` char(1) COLLATE utf8_bin NOT NULL DEFAULT 'Y' COMMENT '使用FLG:Y:使用\nN:削除',
  `sys_ins_user_id` varchar(10) COLLATE utf8_bin NOT NULL COMMENT '登録者',
  `sys_ins_dt` datetime NOT NULL COMMENT '登録日時',
  `sys_ins_pg_id` varchar(15) COLLATE utf8_bin NOT NULL COMMENT '登録機能ID',
  `sys_upd_user_id` varchar(10) COLLATE utf8_bin DEFAULT NULL COMMENT '更新者',
  `sys_upd_dt` datetime DEFAULT NULL COMMENT '更新日時',
  `sys_upd_pg_id` varchar(15) COLLATE utf8_bin DEFAULT NULL COMMENT '更新機能ID',
  PRIMARY KEY (`menu_cd`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COLLATE=utf8_bin COMMENT='メニュー情報(TSRS)';
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Table structure for table `tsrs_user`
--

DROP TABLE IF EXISTS `tsrs_user`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `tsrs_user` (
  `user_id` varchar(10) COLLATE utf8_bin NOT NULL COMMENT 'ユーザーID',
  `user_pwd` varchar(50) COLLATE utf8_bin NOT NULL COMMENT 'パスワード',
  `user_nm` varchar(30) COLLATE utf8_bin NOT NULL COMMENT 'ユーザー名称',
  `last_login_ip` varchar(20) COLLATE utf8_bin DEFAULT NULL COMMENT '最後ログインIP',
  `last_login_dt` datetime DEFAULT NULL COMMENT '最後ログイン日時',
  `login_able_flg` char(1) COLLATE utf8_bin NOT NULL DEFAULT 'N' COMMENT 'ログイン可能FLG:Y:ログイン可能\nN:ログイン不可',
  `user_type` char(1) COLLATE utf8_bin NOT NULL DEFAULT 'C' COMMENT 'ユーザータイプ:C：普通ユーザー\nA：システム管理者',
  `use_flg` char(1) COLLATE utf8_bin NOT NULL DEFAULT 'Y' COMMENT '使用FLG:Y:使用\nN:削除',
  `menu_cd` varchar(10) COLLATE utf8_bin NOT NULL COMMENT 'メニューC',
  `job_title_cd` char(3) COLLATE utf8_bin NOT NULL COMMENT '職位C',
  `sys_ins_user_id` varchar(10) COLLATE utf8_bin NOT NULL COMMENT '登録者',
  `sys_ins_dt` datetime NOT NULL COMMENT '登録日時',
  `sys_ins_pg_id` varchar(15) COLLATE utf8_bin NOT NULL COMMENT '登録機能ID',
  `sys_upd_user_id` varchar(10) COLLATE utf8_bin DEFAULT NULL COMMENT '更新者',
  `sys_upd_dt` datetime DEFAULT NULL COMMENT '更新日時',
  `sys_upd_pg_id` varchar(15) COLLATE utf8_bin DEFAULT NULL COMMENT '更新機能ID',
  PRIMARY KEY (`user_id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COLLATE=utf8_bin COMMENT='ユーザー情報(TSRS)';
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Table structure for table `user`
--

DROP TABLE IF EXISTS `user`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `user` (
  `id` int(11) NOT NULL AUTO_INCREMENT COMMENT 'キーid',
  `avatar` varchar(255) DEFAULT NULL COMMENT 'アバター',
  `account` varchar(45) DEFAULT NULL COMMENT 'アカウント',
  `password` varchar(45) DEFAULT NULL COMMENT 'パスワード',
  `salt` varchar(45) DEFAULT NULL COMMENT 'パスワード塩',
  `name` varchar(45) DEFAULT NULL COMMENT '名前',
  `last_login_dt` datetime DEFAULT NULL COMMENT '生日',
  `sex` int(11) DEFAULT NULL COMMENT '性別（1：男 2：女）',
  `email` varchar(45) DEFAULT NULL COMMENT 'eメール',
  `phone` varchar(45) DEFAULT NULL COMMENT '電話番号',
  `roleid` int(11) DEFAULT NULL COMMENT 'ロールid',
  `deptid` int(11) DEFAULT NULL COMMENT '部門id',
  `status` int(11) DEFAULT NULL COMMENT '状態(1：起用  2：凍結  3：削除）',
  `createtime` datetime DEFAULT NULL COMMENT '作成時間',
  `version` int(11) DEFAULT NULL COMMENT '保持フィールド',
  `user_type` varchar(1) DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=314 DEFAULT CHARSET=utf8 COMMENT='管理者表';
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Table structure for table `userwfconfirmroute`
--

DROP TABLE IF EXISTS `userwfconfirmroute`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `userwfconfirmroute` (
  `user_wf_confirm_route_id` int(11) NOT NULL AUTO_INCREMENT COMMENT 'ユーザーWF承認経路ID',
  `user_id` varchar(10) COLLATE utf8_bin NOT NULL COMMENT 'ユーザーID',
  `accept_user_id` char(10) COLLATE utf8_bin NOT NULL COMMENT '承認者ID',
  `display_order` int(11) NOT NULL COMMENT '表示順',
  `sys_ins_user_id` varchar(10) COLLATE utf8_bin NOT NULL COMMENT '登録者',
  `sys_ins_dt` datetime NOT NULL COMMENT '登録日時',
  `sys_ins_pg_id` varchar(15) COLLATE utf8_bin NOT NULL COMMENT '登録機能ID',
  `sys_upd_user_id` varchar(10) COLLATE utf8_bin DEFAULT NULL COMMENT '更新者',
  `sys_upd_dt` datetime DEFAULT NULL COMMENT '更新日時',
  `sys_upd_pg_id` varchar(15) COLLATE utf8_bin DEFAULT NULL COMMENT '更新機能ID',
  PRIMARY KEY (`user_wf_confirm_route_id`)
) ENGINE=InnoDB AUTO_INCREMENT=4 DEFAULT CHARSET=utf8 COLLATE=utf8_bin COMMENT='ユーザーWF承認経路';
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Table structure for table `workcontent`
--

DROP TABLE IF EXISTS `workcontent`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `workcontent` (
  `work_content_cd` char(3) COLLATE utf8_bin NOT NULL COMMENT '作業内容C',
  `work_content_nm` varchar(50) COLLATE utf8_bin NOT NULL COMMENT '作業内容名称',
  `use_flg` char(1) COLLATE utf8_bin NOT NULL DEFAULT 'Y' COMMENT '使用FLG:Y:使用\nN：廃止',
  `sys_ins_user_id` varchar(10) COLLATE utf8_bin NOT NULL COMMENT '登録者',
  `sys_ins_dt` datetime NOT NULL COMMENT '登録日時',
  `sys_ins_pg_id` varchar(15) COLLATE utf8_bin NOT NULL COMMENT '登録機能ID',
  `sys_upd_user_id` varchar(10) COLLATE utf8_bin DEFAULT NULL COMMENT '更新者',
  `sys_upd_dt` datetime DEFAULT NULL COMMENT '更新日時',
  `sys_upd_pg_id` varchar(15) COLLATE utf8_bin DEFAULT NULL COMMENT '更新機能ID',
  PRIMARY KEY (`work_content_cd`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COLLATE=utf8_bin COMMENT='作業内容';
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Table structure for table `workhourdtl`
--

DROP TABLE IF EXISTS `workhourdtl`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `workhourdtl` (
  `wh_did` int(11) NOT NULL AUTO_INCREMENT COMMENT '勤怠管理DID',
  `wh_hid` int(11) NOT NULL COMMENT '勤怠管理HID',
  `ymd` date NOT NULL COMMENT '年月日',
  `wh_k` char(1) COLLATE utf8_bin NOT NULL COMMENT '勤怠区分:1:出勤\n2:有休\n3:休日\n4:休暇\n5:代休\n6:欠勤',
  `work_content_cd` varchar(3) COLLATE utf8_bin DEFAULT NULL COMMENT '作業内容C:出勤の場合、必須',
  `sys_ins_user_id` varchar(10) COLLATE utf8_bin NOT NULL COMMENT '登録者',
  `sys_ins_dt` datetime NOT NULL COMMENT '登録日時',
  `sys_ins_pg_id` varchar(15) COLLATE utf8_bin NOT NULL COMMENT '登録機能ID',
  `sys_upd_user_id` varchar(10) COLLATE utf8_bin DEFAULT NULL COMMENT '更新者',
  `sys_upd_dt` datetime DEFAULT NULL COMMENT '更新日時',
  `sys_upd_pg_id` varchar(15) COLLATE utf8_bin DEFAULT NULL COMMENT '更新機能ID',
  PRIMARY KEY (`wh_did`),
  KEY `calendar_i_1` (`wh_hid`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COLLATE=utf8_bin COMMENT='勤怠管理DTL';
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Table structure for table `workhourhdr`
--

DROP TABLE IF EXISTS `workhourhdr`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `workhourhdr` (
  `wh_hid` int(11) NOT NULL AUTO_INCREMENT COMMENT '勤怠管理HID',
  `user_id` varchar(10) COLLATE utf8_bin NOT NULL COMMENT 'ユーザーID',
  `ym` date NOT NULL COMMENT '年月',
  `use_flg` char(1) COLLATE utf8_bin NOT NULL DEFAULT 'Y' COMMENT '使用FLG:Y:有効\nN：削除',
  `sys_ins_user_id` varchar(10) COLLATE utf8_bin NOT NULL COMMENT '登録者',
  `sys_ins_dt` datetime NOT NULL COMMENT '登録日時',
  `sys_ins_pg_id` varchar(15) COLLATE utf8_bin NOT NULL COMMENT '登録機能ID',
  `sys_upd_user_id` varchar(10) COLLATE utf8_bin DEFAULT NULL COMMENT '更新者',
  `sys_upd_dt` datetime DEFAULT NULL COMMENT '更新日時',
  `sys_upd_pg_id` varchar(15) COLLATE utf8_bin DEFAULT NULL COMMENT '更新機能ID',
  PRIMARY KEY (`wh_hid`),
  KEY `calendar_i_1` (`ym`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COLLATE=utf8_bin COMMENT='勤怠管理HDR';
/*!40101 SET character_set_client = @saved_cs_client */;
/*!40103 SET TIME_ZONE=@OLD_TIME_ZONE */;

/*!40101 SET SQL_MODE=@OLD_SQL_MODE */;
/*!40014 SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS */;
/*!40014 SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS */;
/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
/*!40111 SET SQL_NOTES=@OLD_SQL_NOTES */;

-- Dump completed on 2017-09-12 18:30:46

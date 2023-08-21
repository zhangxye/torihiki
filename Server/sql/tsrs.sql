 -- Project Name : TSRS
-- Date/Time    : 2017/8/1 21:20:06
-- Author       : 金
-- RDBMS Type   : MySQL
-- Application  : A5:SQL Mk-2

-- クライアント契約契約条件エビデンス
drop table if exists appClientContractEvidence cascade;

create table appClientContractEvidence (
  appr_client_contract_evidence_d INT not null comment 'エビデンスID'
  , appr_doc_id INT not null comment '稟議書ID'
  , display_order INT not null comment '表示順'
  , work_evidence VARCHAR(2000) comment 'エビデンス'
  , sys_ins_user_id VARCHAR(10) not null comment '登録者'
  , sys_ins_dt DATETIME not null comment '登録日時'
  , sys_ins_pg_id VARCHAR(15) not null comment '登録機能ID'
  , sys_upd_user_id VARCHAR(10) comment '更新者'
  , sys_upd_dt DATETIME comment '更新日時'
  , sys_upd_pg_id VARCHAR(15) comment '更新機能ID'
  , constraint appClientContractEvidence_PKC primary key (appr_client_contract_evidence_d)
) comment 'クライアント契約契約条件エビデンス' ENGINE=InnoDB DEFAULT CHARSET=utf8 COLLATE=utf8_bin;

create unique index clientContractEvidence_uk1
  on appClientContractEvidence(appr_doc_id,display_order);

-- 月次処理設定
drop table if exists apprMonProSet cascade;

create table apprMonProSet (
  appr_doc_id INT not null comment '稟議書ID'
  , mon_pro_k CHAR(1) not null comment '月次処理区分:1：通常締結
2：EDI'
  , work_rep_f CHAR(1) not null comment '作業報告書F:1：指定なし
2：客先F'
  , bill_f CHAR(1) not null comment '請求書F:1：指定なし
2：客先F'
  , work_rep_dl_k CHAR(1) not null comment '作業報告書締め区分:1：指定なし
2：第1営業日　　　＃＃時
3：第2営業日　　　＃＃時
4：第3営業日　　　＃＃時
5：第4-5営業日　＃＃時'
  , bill_dl_k CHAR(1) not null comment '請求書締め区分:1：指定なし
2：第1営業日　　　＃＃時
3：第2営業日　　　＃＃時
4：第3営業日　　　＃＃時
5：第4-5営業日　＃＃時'
  , mon_attach_file_k CHAR(1) not null comment '添付資料F:1：有
2：無'
  , mon_attach_file_id INT comment '添付ファイルID'
  , mon_remark VARCHAR(1000) comment '補足事項'
  , sys_ins_user_id VARCHAR(10) not null comment '登録者'
  , sys_ins_dt DATETIME not null comment '登録日時'
  , sys_ins_pg_id VARCHAR(15) not null comment '登録機能ID'
  , sys_upd_user_id VARCHAR(10) comment '更新者'
  , sys_upd_dt DATETIME comment '更新日時'
  , sys_upd_pg_id VARCHAR(15) comment '更新機能ID'
  , constraint apprMonProSet_PKC primary key (appr_doc_id)
) comment '月次処理設定' ENGINE=InnoDB DEFAULT CHARSET=utf8 COLLATE=utf8_bin;

-- 承認済請求
drop table if exists requestAccepted cascade;

create table requestAccepted (
  req_id INT not null comment '請求ID'
  , last_accept_user_id VARCHAR(10) not null comment '最終承認者ID'
  , last_accept_date DATETIME not null comment '最終承認日時'
  , req_apply_hid INT not null comment '請求申請HID'
  , sys_ins_user_id VARCHAR(10) not null comment '登録者'
  , sys_ins_dt DATETIME not null comment '登録日時'
  , sys_ins_pg_id VARCHAR(15) not null comment '登録機能ID'
  , sys_upd_user_id VARCHAR(10) comment '更新者'
  , sys_upd_dt DATETIME comment '更新日時'
  , sys_upd_pg_id VARCHAR(15) comment '更新機能ID'
  , constraint requestAccepted_PKC primary key (req_id)
) comment '承認済請求' ENGINE=InnoDB DEFAULT CHARSET=utf8 COLLATE=utf8_bin;

-- 請求申請DTL情報
drop table if exists requestApplyDtl cascade;

create table requestApplyDtl (
  req_apply_did INT not null AUTO_INCREMENT comment '請求申請DID'
  , req_apply_hid INT not null comment '請求申請HID'
  , display_order INT not null comment '表示順'
  , accept_user_id VARCHAR(10) not null comment '承認者ID'
  , confirm_st CHAR(1) not null comment '承認状況:1：未承認
2：承認済
3：拒否'
  , con_rej_comment VARCHAR(500) not null comment '承認(拒否)コメント'
  , con_rej_dt TIME comment '承認(拒否)日時'
  , sys_ins_user_id VARCHAR(10) not null comment '登録者'
  , sys_ins_dt DATETIME not null comment '登録日時'
  , sys_ins_pg_id VARCHAR(15) not null comment '登録機能ID'
  , sys_upd_user_id VARCHAR(10) comment '更新者'
  , sys_upd_dt DATETIME comment '更新日時'
  , sys_upd_pg_id VARCHAR(15) comment '更新機能ID'
  , constraint requestApplyDtl_PKC primary key (req_apply_did)
) comment '請求申請DTL情報' ENGINE=InnoDB DEFAULT CHARSET=utf8 COLLATE=utf8_bin;

alter table requestApplyDtl add unique apprApplyDtl_u_1 (req_apply_hid,display_order) ;

-- 請求申請HDR情報
drop table if exists requestApplyHdr cascade;

create table requestApplyHdr (
  req_apply_hid INT not null AUTO_INCREMENT comment '請求申請HID'
  , req_id INT not null comment '稟議書ID'
  , use_flg CHAR(1) default 'Y' not null comment '使用FLG:Y:使用
N:削除'
  , apply_reason VARCHAR(500) comment '申請理由'
  , apply_user_id VARCHAR(10) not null comment '申請者ID'
  , apply_dt TIME not null comment '申請日時'
  , confirm_wait_display_order INT comment '承認待表示順:承認待となっている申請DTLの表示順を設定'
  , sys_ins_user_id VARCHAR(10) not null comment '登録者'
  , sys_ins_dt DATETIME not null comment '登録日時'
  , sys_ins_pg_id VARCHAR(15) not null comment '登録機能ID'
  , sys_upd_user_id VARCHAR(10) comment '更新者'
  , sys_upd_dt DATETIME comment '更新日時'
  , sys_upd_pg_id VARCHAR(15) comment '更新機能ID'
  , constraint requestApplyHdr_PKC primary key (req_apply_hid)
) comment '請求申請HDR情報' ENGINE=InnoDB DEFAULT CHARSET=utf8 COLLATE=utf8_bin;

-- 入金
drop table if exists moneyRev cascade;

create table moneyRev (
  money_rec_id INT not null AUTO_INCREMENT comment '入金ID'
  , req_dt DATE not null comment '入金日'
  , money_rec_amt DECIMAL(10,1) not null comment '入金金額'
  , money_rec_remark VARCHAR(500) comment '入金備考'
  , req_id INT not null comment '請求ID'
  , sys_ins_user_id VARCHAR(10) not null comment '登録者'
  , sys_ins_dt DATETIME not null comment '登録日時'
  , sys_ins_pg_id VARCHAR(15) not null comment '登録機能ID'
  , sys_upd_user_id VARCHAR(10) comment '更新者'
  , sys_upd_dt DATETIME comment '更新日時'
  , sys_upd_pg_id VARCHAR(15) comment '更新機能ID'
  , constraint moneyRev_PKC primary key (money_rec_id)
) comment '入金' ENGINE=InnoDB DEFAULT CHARSET=utf8 COLLATE=utf8_bin;

-- 売上・請求・対照表
drop table if exists saleReqRel cascade;

create table saleReqRel (
  req_id INT not null comment '請求ID'
  , sales_id INT not null comment '売上ID'
  , sys_ins_user_id VARCHAR(10) not null comment '登録者'
  , sys_ins_dt DATETIME not null comment '登録日時'
  , sys_ins_pg_id VARCHAR(15) not null comment '登録機能ID'
  , sys_upd_user_id VARCHAR(10) comment '更新者'
  , sys_upd_dt DATETIME comment '更新日時'
  , sys_upd_pg_id VARCHAR(15) comment '更新機能ID'
  , constraint saleReqRel_PKC primary key (req_id,sales_id)
) comment '売上・請求・対照表' ENGINE=InnoDB DEFAULT CHARSET=utf8 COLLATE=utf8_bin;

-- 請求
drop table if exists request cascade;

create table request (
  req_id INT not null AUTO_INCREMENT comment '請求ID'
  , req_dt DATE not null comment '請求日'
  , req_user_id VARCHAR(10) not null comment '請求担当'
  , req_amt_without_tax DECIMAL(10,1) not null comment '請求金額（税抜）:請求金額（税抜）の合計'
  , req_amt_tax DECIMAL(10,1) comment '消費税'
  , req_amt_with_tax DECIMAL(10,1) comment '請求金額（税込）'
  , req_remark VARCHAR(500) comment '請求備考'
  , sys_ins_user_id VARCHAR(10) not null comment '登録者'
  , sys_ins_dt DATETIME not null comment '登録日時'
  , sys_ins_pg_id VARCHAR(15) not null comment '登録機能ID'
  , sys_upd_user_id VARCHAR(10) comment '更新者'
  , sys_upd_dt DATETIME comment '更新日時'
  , sys_upd_pg_id VARCHAR(15) comment '更新機能ID'
  , constraint request_PKC primary key (req_id)
) comment '請求' ENGINE=InnoDB DEFAULT CHARSET=utf8 COLLATE=utf8_bin;

-- 売上_インセンティブ明細
drop table if exists salesIncentiveDtl cascade;

create table salesIncentiveDtl (
  sales_id INT not null comment '売上ID'
  , payment_dat DATE comment '締日'
  , pj_nm VARCHAR(200) comment '作業案件名称'
  , client_no VARCHAR(10) comment 'クライアント番号'
  , client_nm VARCHAR(50) comment 'クライアント名称'
  , sys_ins_user_id VARCHAR(10) not null comment '登録者'
  , sys_ins_dt DATETIME not null comment '登録日時'
  , sys_ins_pg_id VARCHAR(15) not null comment '登録機能ID'
  , sys_upd_user_id VARCHAR(10) comment '更新者'
  , sys_upd_dt DATETIME comment '更新日時'
  , sys_upd_pg_id VARCHAR(15) comment '更新機能ID'
  , constraint salesIncentiveDtl_PKC primary key (sales_id)
) comment '売上_インセンティブ明細' ENGINE=InnoDB DEFAULT CHARSET=utf8 COLLATE=utf8_bin;

-- 売上_原価明細
drop table if exists salesCostDtl cascade;

create table salesCostDtl (
  sales_id INT not null comment '売上ID'
  , payment_dat DATE comment '締日'
  , pj_nm VARCHAR(200) comment '作業案件名称'
  , bp_comp_id VARCHAR(10) comment '所属会社ID'
  , bp_comp_emp_nm VARCHAR(10) comment '所属会社名/社員氏名'
  , bp_cost_price DECIMAL(10,1) comment '社保加算後単価:加入の場合、単価＋交通費＋（健康保険+厚生年金）＊1.0085
加入しない場合、単価＋交通費'
  , bp_cost_amt_without_tax DECIMAL(10,1) comment '社保加算金額（税抜）:社保加算後単価＊工数（月次用）'
  , bp_cost_tax DECIMAL(10,1) comment '消費税'
  , bp_cost_amt_with_tax DECIMAL(10,1) comment '社保加算金額（税込）'
  , payment_plan_d INT comment '支払サイト/出金日付の日'
  , payment_due_k CHAR(1) comment '支払サイト/締日区分'
  , pay_month_k CHAR(1) comment '支払サイト/支払月区分'
  , pay_plan_d INT comment '支払サイト/支払日付の日'
  , salary_pay_plan_dt DATE comment '出金予定日'
  , sales_cost_remark VARCHAR(500) comment '売上原価備考'
  , sys_ins_user_id VARCHAR(10) not null comment '登録者'
  , sys_ins_dt DATETIME not null comment '登録日時'
  , sys_ins_pg_id VARCHAR(15) not null comment '登録機能ID'
  , sys_upd_user_id VARCHAR(10) comment '更新者'
  , sys_upd_dt DATETIME comment '更新日時'
  , sys_upd_pg_id VARCHAR(15) comment '更新機能ID'
  , constraint salesCostDtl_PKC primary key (sales_id)
) comment '売上_原価明細' ENGINE=InnoDB DEFAULT CHARSET=utf8 COLLATE=utf8_bin;

-- 売上_請求明細
drop table if exists salesReqDtl cascade;

create table salesReqDtl (
  sales_id INT not null comment '売上ID'
  , payment_dat DATE not null comment '締日:クライアントの締日'
  , next_pj_k CHAR(1) comment '次のPJ:次のPJ（売上）選択し
1：既存
2：経験'
  , pj_nm VARCHAR(200) comment '作業案件名称'
  , work_content VARCHAR(200) comment '作業内容・範囲'
  , work_place VARCHAR(200) comment '勤務地/最寄駅'
  , client_no VARCHAR(10) not null comment 'クライアント番号'
  , client_nm VARCHAR(50) not null comment 'クライアント名'
  , req_start_dt DATE comment '期間FROM:請求書'
  , req_end_dt DATE comment '期間TO:請求書'
  , sell_price DECIMAL(10,1) not null comment '上位単金/単価:請求書の基準単価'
  , current_mon_work_time DECIMAL(4,2) not null comment '工数（月次用）:当月入社の場合、小数部、そのほか、１とする'
  , wh_max DECIMAL(4,1) comment '作業時間（上限・H）'
  , wh_min DECIMAL(4,1) comment '作業時間（下限・H）'
  , real_work_hour DECIMAL(4,1) comment '実績時間:勤怠情報から取得（単位：時間）'
  , excess_price DECIMAL(10,1) comment '超過単価（円）'
  , deducation_price DECIMAL(10,1) comment '控除単価（円）'
  , work_amt DECIMAL(10,1) comment '金額:工数（月次用）＊基準単価
手動変更可能か？'
  , travel_cost DECIMAL(10,1) comment '交通費'
  , work_time_unit_k CHAR(1) comment '作業時間単位:1:10分　2：15分　3：30分　4：1時'
  , other_cost DECIMAL(10,1) comment '経費/オンライン当番'
  , pay_month_k CHAR(1) comment '支払サイト/支払月区分:1：翌月/2：翌々月/3：翌々々月'
  , pay_plan_d INT comment '支払サイト/支払日付の日'
  , client_pay_dt DATE comment 'お支払期日:支払月＝当月＋支払サイト/支払月区分（1：翌月/2：翌々月/3：翌々々月）
お支払期日＝ 支払月+支払サイト/支払日付の日'
  , overwork_amt DECIMAL(10,1) comment '残業代:計算方法が確認要'
  , deduction_amt DECIMAL(10,1) comment '控除金額:計算方法が確認要
欠勤控除？'
  , client_pay_without_tax DECIMAL(10,1) comment '税抜金額:基本金額＋交通費＋経費'
  , client_pay_tax DECIMAL(10,1) comment '消 費 税:税抜金額＊消費税'
  , client_pay_with_tax DECIMAL(10,1) comment '税込金額:税抜合計＋税込合計'
  , pj_class_cd CHAR(3) comment '案件分類C'
  , gross_amt DECIMAL(10,1) comment '粗利額:請求の税抜金額-原価の税抜金額'
  , sales_req_remark VARCHAR(500) comment '売上請求備考'
  , money_rec_plan_dt DATE comment '入金予定日:手入力'
  , pay_lt INT comment '支払LT:売上請求明細の入金予定日-原価明細の出金予定日'
  , sys_ins_user_id VARCHAR(10) not null comment '登録者'
  , sys_ins_dt DATETIME not null comment '登録日時'
  , sys_ins_pg_id VARCHAR(15) not null comment '登録機能ID'
  , sys_upd_user_id VARCHAR(10) comment '更新者'
  , sys_upd_dt DATETIME comment '更新日時'
  , sys_upd_pg_id VARCHAR(15) comment '更新機能ID'
  , constraint salesReqDtl_PKC primary key (sales_id)
) comment '売上_請求明細' ENGINE=InnoDB DEFAULT CHARSET=utf8 COLLATE=utf8_bin;

-- 売上
drop table if exists sales cascade;

create table sales (
  sales_id INT not null AUTO_INCREMENT comment '売上ID'
  , order_hid INT not null comment '受注HID'
  , order_did INT not null comment '受注DID'
  , appr_doc_id INT not null comment '稟議書ID'
  , add_up_ym VARCHAR(6) not null comment '計上年月'
  , add_up_dt DATE not null comment '計上日'
  , emp_k CHAR(1) not null comment '要員区分/補助:1：新規要員
2：既存要員
3：要員退社'
  , emp_no VARCHAR(10) not null comment '社員番号'
  , emp_nm VARCHAR(30) not null comment '社員名'
  , emp_nm_fuli VARCHAR(30) not null comment '社員名(フリガナ)'
  , bussiness_main_user_id VARCHAR(10) not null comment '営業主担当'
  , sales_remark VARCHAR(500) comment '売上登録備考'
  , sys_ins_user_id VARCHAR(10) not null comment '登録者'
  , sys_ins_dt DATETIME not null comment '登録日時'
  , sys_ins_pg_id VARCHAR(15) not null comment '登録機能ID'
  , sys_upd_user_id VARCHAR(10) comment '更新者'
  , sys_upd_dt DATETIME comment '更新日時'
  , sys_upd_pg_id VARCHAR(15) comment '更新機能ID'
  , constraint sales_PKC primary key (sales_id)
) comment '売上' ENGINE=InnoDB DEFAULT CHARSET=utf8 COLLATE=utf8_bin;

-- 作業内容
drop table if exists workContent cascade;

create table workContent (
  work_content_cd CHAR(3) not null comment '作業内容C'
  , work_content_nm VARCHAR(50) not null comment '作業内容名称'
  , use_flg CHAR(1) default 'Y' not null comment '使用FLG:Y:使用
N：廃止'
  , sys_ins_user_id VARCHAR(10) not null comment '登録者'
  , sys_ins_dt DATETIME not null comment '登録日時'
  , sys_ins_pg_id VARCHAR(15) not null comment '登録機能ID'
  , sys_upd_user_id VARCHAR(10) comment '更新者'
  , sys_upd_dt DATETIME comment '更新日時'
  , sys_upd_pg_id VARCHAR(15) comment '更新機能ID'
  , constraint workContent_PKC primary key (work_content_cd)
) comment '作業内容' ENGINE=InnoDB DEFAULT CHARSET=utf8 COLLATE=utf8_bin;

-- 勤怠管理DTL
drop table if exists workHourDtl cascade;

create table workHourDtl (
  wh_did INT not null AUTO_INCREMENT comment '勤怠管理DID'
  , wh_hid INT not null comment '勤怠管理HID'
  , ymd DATE not null comment '年月日'
  , wh_k CHAR(1) not null comment '勤怠区分:1:出勤
2:有休
3:休日
4:休暇
5:代休
6:欠勤'
  , work_content_cd VARCHAR(3) comment '作業内容C:出勤の場合、必須'
  , sys_ins_user_id VARCHAR(10) not null comment '登録者'
  , sys_ins_dt DATETIME not null comment '登録日時'
  , sys_ins_pg_id VARCHAR(15) not null comment '登録機能ID'
  , sys_upd_user_id VARCHAR(10) comment '更新者'
  , sys_upd_dt DATETIME comment '更新日時'
  , sys_upd_pg_id VARCHAR(15) comment '更新機能ID'
  , constraint workHourDtl_PKC primary key (wh_did)
) comment '勤怠管理DTL' ENGINE=InnoDB DEFAULT CHARSET=utf8 COLLATE=utf8_bin;

create index calendar_i_1
  on workHourDtl(wh_hid);

-- 勤怠管理HDR
drop table if exists workHourHdr cascade;

create table workHourHdr (
  wh_hid INT not null AUTO_INCREMENT comment '勤怠管理HID'
  , user_id VARCHAR(10) not null comment 'ユーザーID'
  , ym DATE not null comment '年月'
  , use_flg CHAR(1) default 'Y' not null comment '使用FLG:Y:有効
N：削除'
  , sys_ins_user_id VARCHAR(10) not null comment '登録者'
  , sys_ins_dt DATETIME not null comment '登録日時'
  , sys_ins_pg_id VARCHAR(15) not null comment '登録機能ID'
  , sys_upd_user_id VARCHAR(10) comment '更新者'
  , sys_upd_dt DATETIME comment '更新日時'
  , sys_upd_pg_id VARCHAR(15) comment '更新機能ID'
  , constraint workHourHdr_PKC primary key (wh_hid)
) comment '勤怠管理HDR' ENGINE=InnoDB DEFAULT CHARSET=utf8 COLLATE=utf8_bin;

create index calendar_i_1
  on workHourHdr(ym);

-- カレンダー
drop table if exists calendar cascade;

create table calendar (
  calendar_id INT not null AUTO_INCREMENT comment 'カレンダーID'
  , ym DATE not null comment '年月'
  , ymd DATE not null comment '年月日'
  , calendar_k CHAR(1) not null comment 'カレンダー区分:1:稼働日
2:週末
3:祝日'
  , sys_ins_user_id VARCHAR(10) not null comment '登録者'
  , sys_ins_dt DATETIME not null comment '登録日時'
  , sys_ins_pg_id VARCHAR(15) not null comment '登録機能ID'
  , sys_upd_user_id VARCHAR(10) comment '更新者'
  , sys_upd_dt DATETIME comment '更新日時'
  , sys_upd_pg_id VARCHAR(15) comment '更新機能ID'
  , constraint calendar_PKC primary key (calendar_id)
) comment 'カレンダー' ENGINE=InnoDB DEFAULT CHARSET=utf8 COLLATE=utf8_bin;

create index calendar_i_1
  on calendar(ym);

-- BP見積DTL情報
drop table if exists bpEstDtl cascade;

create table bpEstDtl (
  bp_est_did INT not null AUTO_INCREMENT comment 'BP見積DID'
  , bp_est_hid INT not null comment 'BP見積HID'
  , appr_doc_id INT not null comment '稟議書ID'
  , sys_ins_user_id VARCHAR(10) not null comment '登録者'
  , sys_ins_dt DATETIME not null comment '登録日時'
  , sys_ins_pg_id VARCHAR(15) not null comment '登録機能ID'
  , sys_upd_user_id VARCHAR(10) comment '更新者'
  , sys_upd_dt DATETIME comment '更新日時'
  , sys_upd_pg_id VARCHAR(15) comment '更新機能ID'
  , constraint bpEstDtl_PKC primary key (bp_est_did)
) comment 'BP見積DTL情報' ENGINE=InnoDB DEFAULT CHARSET=utf8 COLLATE=utf8_bin;

-- BP見積依頼DTL情報
drop table if exists bpEstReqDtl cascade;

create table bpEstReqDtl (
  bp_est_req_did INT not null AUTO_INCREMENT comment 'BP見積依頼DID'
  , bp_est_req_hid INT not null comment 'BP見積依頼HID'
  , appr_doc_id INT not null comment '稟議書ID'
  , sys_ins_user_id VARCHAR(10) not null comment '登録者'
  , sys_ins_dt DATETIME not null comment '登録日時'
  , sys_ins_pg_id VARCHAR(15) not null comment '登録機能ID'
  , sys_upd_user_id VARCHAR(10) comment '更新者'
  , sys_upd_dt DATETIME comment '更新日時'
  , sys_upd_pg_id VARCHAR(15) comment '更新機能ID'
  , constraint bpEstReqDtl_PKC primary key (bp_est_req_did)
) comment 'BP見積依頼DTL情報' ENGINE=InnoDB DEFAULT CHARSET=utf8 COLLATE=utf8_bin;

-- 職位
drop table if exists jobTitle cascade;

create table jobTitle (
  job_title_cd CHAR(3) not null comment '職位C'
  , job_tile_nm VARCHAR(50) not null comment '職位名称'
  , use_flg CHAR(1) default 'Y' not null comment '使用FLG:Y:使用
N：廃止'
  , sys_ins_user_id VARCHAR(10) not null comment '登録者'
  , sys_ins_dt DATETIME not null comment '登録日時'
  , sys_ins_pg_id VARCHAR(15) not null comment '登録機能ID'
  , sys_upd_user_id VARCHAR(10) comment '更新者'
  , sys_upd_dt DATETIME comment '更新日時'
  , sys_upd_pg_id VARCHAR(15) comment '更新機能ID'
  , constraint jobTitle_PKC primary key (job_title_cd)
) comment '職位' ENGINE=InnoDB DEFAULT CHARSET=utf8 COLLATE=utf8_bin;

-- 職種
drop table if exists jobType cascade;

create table jobType (
  job_type_cd CHAR(3) not null comment '職種C'
  , job_type_nm VARCHAR(50) not null comment '職種名称'
  , use_flg CHAR(1) default 'Y' not null comment '使用FLG:Y:使用
N：廃止'
  , sys_ins_user_id VARCHAR(10) not null comment '登録者'
  , sys_ins_dt DATETIME not null comment '登録日時'
  , sys_ins_pg_id VARCHAR(15) not null comment '登録機能ID'
  , sys_upd_user_id VARCHAR(10) comment '更新者'
  , sys_upd_dt DATETIME comment '更新日時'
  , sys_upd_pg_id VARCHAR(15) comment '更新機能ID'
  , constraint jobType_PKC primary key (job_type_cd)
) comment '職種' ENGINE=InnoDB DEFAULT CHARSET=utf8 COLLATE=utf8_bin;

-- 標準報酬管理
drop table if exists basePriceMgr cascade;

create table basePriceMgr (
  base_price_mgr_id INT not null AUTO_INCREMENT comment '標準報酬管理ID'
  , base_price_type CHAR(1) not null comment '標準報酬タイプ:1：健康保険
2：厚生年金'
  , start_dt DATE not null comment '開始日'
  , end_dt DATE not null comment '終了日:‘2099/12/31’'
  , salary_from DECIMAL(10,1) not null comment '報酬月額From'
  , salary_to DECIMAL(10,1) default 999 not null comment '報酬月額To(含まれまい)'
  , base_price DECIMAL(10,1) not null comment '標準報酬'
  , sys_ins_user_id VARCHAR(10) not null comment '登録者'
  , sys_ins_dt DATETIME not null comment '登録日時'
  , sys_ins_pg_id VARCHAR(15) not null comment '登録機能ID'
  , sys_upd_user_id VARCHAR(10) comment '更新者'
  , sys_upd_dt DATETIME comment '更新日時'
  , sys_upd_pg_id VARCHAR(15) comment '更新機能ID'
  , constraint basePriceMgr_PKC primary key (base_price_mgr_id)
) comment '標準報酬管理' ENGINE=InnoDB DEFAULT CHARSET=utf8 COLLATE=utf8_bin;

create unique index ratioMgr_u_atl1
  on basePriceMgr(base_price_type,salary_from,salary_to);

-- 料率管理
drop table if exists ratioMgr cascade;

create table ratioMgr (
  ratio_mgr_id INT not null AUTO_INCREMENT comment '料率管理ID'
  , ratio_type CHAR(1) not null comment '料率タイプ:1：健康保険
2：厚生年金'
  , start_dt DATE not null comment '開始日'
  , end_dt DATE default '2099/12/31' not null comment '終了日'
  , age_from INT not null comment '年齢From'
  , age_to INT default 999 not null comment '年齢To(含まない)'
  , ratio DECIMAL(5,2) not null comment '料率'
  , sys_ins_user_id VARCHAR(10) not null comment '登録者'
  , sys_ins_dt DATETIME not null comment '登録日時'
  , sys_ins_pg_id VARCHAR(15) not null comment '登録機能ID'
  , sys_upd_user_id VARCHAR(10) comment '更新者'
  , sys_upd_dt DATETIME comment '更新日時'
  , sys_upd_pg_id VARCHAR(15) comment '更新機能ID'
  , constraint ratioMgr_PKC primary key (ratio_mgr_id)
) comment '料率管理' ENGINE=InnoDB DEFAULT CHARSET=utf8 COLLATE=utf8_bin;

create unique index ratioMgr_u_atl1
  on ratioMgr(ratio_type,age_from,age_to);

-- 要員月額手当割合
drop table if exists apprEmpAllowanceRatio cascade;

create table apprEmpAllowanceRatio (
  appr_doc_id INT not null comment '稟議書ID'
  , appoint_allow_ratio DECIMAL(5,2) default 0 not null comment '役職手当割合'
  , house_allow_ratio DECIMAL(5,2) default 0 not null comment '住宅手当割合'
  , aliment_allow_ratio DECIMAL(5,2) default 0 not null comment '扶養手当割合'
  , communication_allow_ratio DECIMAL(5,2) default 0 not null comment '通信手当割合'
  , site_allow_ratio DECIMAL(5,2) default 0 not null comment '現場手当割合'
  , duty_allow_ratio DECIMAL(5,2) default 0 not null comment '当番手当割合'
  , overwork_allow_ratio DECIMAL(5,2) default 0 not null comment '残業手当割合'
  , qualification_allow_ratio DECIMAL(5,2) default 0 not null comment '資格手当割合'
  , other_allow1_nm VARCHAR(50) comment 'そのた手当1名称'
  , other_allow1_ratio DECIMAL(5,2) default 0 comment 'そのた手当1割合'
  , other_allow2_nm VARCHAR(50) comment 'そのた手当1名称'
  , other_allow2_ratio DECIMAL(5,2) default 0 comment 'そのた手当２割合'
  , sys_ins_user_id VARCHAR(10) not null comment '登録者'
  , sys_ins_dt DATETIME not null comment '登録日時'
  , sys_ins_pg_id VARCHAR(15) not null comment '登録機能ID'
  , sys_upd_user_id VARCHAR(10) comment '更新者'
  , sys_upd_dt DATETIME comment '更新日時'
  , sys_upd_pg_id VARCHAR(15) comment '更新機能ID'
  , constraint apprEmpAllowanceRatio_PKC primary key (appr_doc_id)
) comment '要員月額手当割合' ENGINE=InnoDB DEFAULT CHARSET=utf8 COLLATE=utf8_bin;

-- 消費税管理
drop table if exists taxMgr cascade;

create table taxMgr (
  tax_mgr_id INT not null AUTO_INCREMENT comment '消費税管理ID'
  , start_dt DATE not null comment '開始日'
  , end_dt DATE default '2099/12/31' not null comment '終了日'
  , tax_rate DECIMAL(5,2) not null comment '消費率'
  , sys_ins_user_id VARCHAR(10) not null comment '登録者'
  , sys_ins_dt DATETIME not null comment '登録日時'
  , sys_ins_pg_id VARCHAR(15) not null comment '登録機能ID'
  , sys_upd_user_id VARCHAR(10) comment '更新者'
  , sys_upd_dt DATETIME comment '更新日時'
  , sys_upd_pg_id VARCHAR(15) comment '更新機能ID'
  , constraint taxMgr_PKC primary key (tax_mgr_id)
) comment '消費税管理' ENGINE=InnoDB DEFAULT CHARSET=utf8 COLLATE=utf8_bin;

create unique index taxMgr_u_atl1
  on taxMgr(start_dt,end_dt);

-- 案件分類
drop table if exists pjClass cascade;

create table pjClass (
  pj_class_cd CHAR(3) not null comment '案件分類C'
  , pj_class_nm VARCHAR(50) not null comment '案件名称'
  , use_flg CHAR(1) default 'Y' not null comment '使用FLG:Y:使用
N：廃止'
  , sys_ins_user_id VARCHAR(10) not null comment '登録者'
  , sys_ins_dt DATETIME not null comment '登録日時'
  , sys_ins_pg_id VARCHAR(15) not null comment '登録機能ID'
  , sys_upd_user_id VARCHAR(10) comment '更新者'
  , sys_upd_dt DATETIME comment '更新日時'
  , sys_upd_pg_id VARCHAR(15) comment '更新機能ID'
  , constraint pjClass_PKC primary key (pj_class_cd)
) comment '案件分類' ENGINE=InnoDB DEFAULT CHARSET=utf8 COLLATE=utf8_bin;

-- 購買の依頼内容
drop table if exists apprPurchaseRequestContent cascade;

create table apprPurchaseRequestContent (
  appr_doc_id INT not null comment '稟議書ID'
  , contract_pat_k CHAR(1) comment '契約形態:1：BP会社
2：フリー
3：契約社員
4：正社員'
  , procedure_k CHAR(1) comment '手続き区分:1：新規入社処理
2：契約期間更新処理
3：退社処理'
  , procedure_method_k CHAR(1) comment '手続き方法'
  , withdrawal_reason_k CHAR(1) comment '退社理由'
  , withdrawal_letter_k CHAR(1) comment '離職票の有無'
  , insurance_con_k CHAR(1) comment '社保任意継続'
  , withdrawal_req_doc CHAR(1) comment '必要書類'
  , procedure_dt DATE comment '手続き日付'
  , withdrawal_dt DATE comment '退社日'
  , after_withdrawal_post_no VARCHAR(10) comment '退社後郵便番号'
  , after_withdrawal_addr VARCHAR(200) comment '退社後住所'
  , purchase_remark VARCHAR(500) comment '連絡事項（購買へ）'
  , commercial_dist_remark VARCHAR(500) comment '商流記載'
  , emp_remark VARCHAR(500) comment '特記事項（要員）'
  , sys_ins_user_id VARCHAR(10) not null comment '登録者'
  , sys_ins_dt DATETIME not null comment '登録日時'
  , sys_ins_pg_id VARCHAR(15) not null comment '登録機能ID'
  , sys_upd_user_id VARCHAR(10) comment '更新者'
  , sys_upd_dt DATETIME comment '更新日時'
  , sys_upd_pg_id VARCHAR(15) comment '更新機能ID'
  , constraint apprPurchaseRequestContent_PKC primary key (appr_doc_id)
) comment '購買の依頼内容' ENGINE=InnoDB DEFAULT CHARSET=utf8 COLLATE=utf8_bin;

-- 通勤経路Dtl
drop table if exists apprCommuteRouteDtl cascade;

create table apprCommuteRouteDtl (
  appr_commute_route_id INT not null AUTO_INCREMENT comment '通勤経路ID'
  , appr_doc_id INT not null comment '稟議書ID'
  , from_home_line_nm VARCHAR(30) comment '自宅最寄駅の線'
  , from_home_station_nm VARCHAR(30) comment '自宅最寄駅の駅'
  , to_comp_line_nm VARCHAR(30) comment '現場最寄駅の線'
  , to_comp_station_nm VARCHAR(30) comment '現場最寄駅の駅'
  , travel_cost DECIMAL(10,1) comment '通勤経路の交通費'
  , sys_ins_user_id VARCHAR(10) not null comment '登録者'
  , sys_ins_dt DATETIME not null comment '登録日時'
  , sys_ins_pg_id VARCHAR(15) not null comment '登録機能ID'
  , sys_upd_user_id VARCHAR(10) comment '更新者'
  , sys_upd_dt DATETIME comment '更新日時'
  , sys_upd_pg_id VARCHAR(15) comment '更新機能ID'
  , constraint apprCommuteRouteDtl_PKC primary key (appr_commute_route_id)
) comment '通勤経路Dtl' ENGINE=InnoDB DEFAULT CHARSET=utf8 COLLATE=utf8_bin;

-- 通勤経路Hdr
drop table if exists apprCommuteRouteHdr cascade;

create table apprCommuteRouteHdr (
  appr_doc_id INT not null comment '稟議書ID'
  , day_travel_cost DECIMAL(10,1) comment '交通費日額（往復）'
  , month_pass_travel_cost DECIMAL(10,1) comment '1ヶ月の通勤代（定期）'
  , pay_travel_costa_max DECIMAL(10,1) comment '支払上限'
  , rount_dlt_content VARCHAR(500) comment '自宅→現場（乗り換え含め交通経路記入）'
  , sys_ins_user_id VARCHAR(10) not null comment '登録者'
  , sys_ins_dt DATETIME not null comment '登録日時'
  , sys_ins_pg_id VARCHAR(15) not null comment '登録機能ID'
  , sys_upd_user_id VARCHAR(10) comment '更新者'
  , sys_upd_dt DATETIME comment '更新日時'
  , sys_upd_pg_id VARCHAR(15) comment '更新機能ID'
  , constraint apprCommuteRouteHdr_PKC primary key (appr_doc_id)
) comment '通勤経路Hdr' ENGINE=InnoDB DEFAULT CHARSET=utf8 COLLATE=utf8_bin;

-- 要員　契約条件
drop table if exists apprBpContract cascade;

create table apprBpContract (
  appr_doc_id INT not null comment '稟議書ID'
  , working_pj_no VARCHAR(10) not null comment '稼働中案件番号'
  , pj_nm VARCHAR(200) not null comment '作業案件名称'
  , work_place VARCHAR(200) not null comment '勤務地/最寄駅'
  , work_content VARCHAR(200) not null comment '作業内容・範囲'
  , cost_price DECIMAL(10,1) not null comment '要員渡し/原価'
  , accounting_pat_k CHAR(1) comment '精算条件'
  , wh_max DECIMAL(4,1) comment '作業時間（上限・H）'
  , wh_min DECIMAL(4,1) comment '作業時間（下限・H）'
  , wh_mid DECIMAL(4,1) comment '中割時間'
  , wh_other DECIMAL(4,1) comment 'その他（時間）'
  , excess_price DECIMAL(10,1) comment '超過単価（円）'
  , deducation_price DECIMAL(10,1) comment '控除単価（円）'
  , pj_start_dt DATE comment '業務案件今月分の開始日'
  , pj_end_dt DATE comment '業務案件今月分の終了日'
  , work_unit_k CHAR(1) not null comment '作業時間単位'
  , total_work_month DECIMAL(6,2) not null comment '工数'
  , hope_job_type_cd char(3) comment '希望職種'
  , pri_exp_job_type_cd char(3) comment '主な経験職種'
  , pj_class_cd char(3) comment '案件分類'
  , work_start_time TIME not null comment '出勤時刻'
  , work_end_time TIME not null comment '退勤時刻'
  , break_time_min INT not null comment '休憩時間（分）'
  , payment_plan_d INT not null comment '支払サイト/出金日付の日'
  , payment_due_k CHAR(1) not null comment '支払サイト/締日区分'
  , pay_month_k CHAR(1) not null comment '支払サイト/支払月区分'
  , pay_plan_d INT not null comment '支払サイト/支払日付の日'
  , salary_pay_plan_dt INT not null comment '支払サイト/出金予定日'
  , work_content_dtl VARCHAR(500) comment '業務内容詳細'
  , sys_ins_user_id VARCHAR(10) not null comment '登録者'
  , sys_ins_dt DATETIME not null comment '登録日時'
  , sys_ins_pg_id VARCHAR(15) not null comment '登録機能ID'
  , sys_upd_user_id VARCHAR(10) comment '更新者'
  , sys_upd_dt DATETIME comment '更新日時'
  , sys_upd_pg_id VARCHAR(15) comment '更新機能ID'
  , constraint apprBpContract_PKC primary key (appr_doc_id)
) comment '要員　契約条件' ENGINE=InnoDB DEFAULT CHARSET=utf8 COLLATE=utf8_bin;

-- クライアント　契約条件
drop table if exists apprClientContract cascade;

create table apprClientContract (
  appr_doc_id INT not null comment '稟議書ID'
  , pj_nm VARCHAR(200) comment '作業案件名称'
  , work_place VARCHAR(200) comment '勤務地/最寄駅'
  , work_content VARCHAR(200) comment '作業内容・範囲'
  , accounting_pat_k CHAR(1) comment '精算条件:1：上下割
2：中割
3：固定
4：その他
 ← 記入例：140-200（36-58）/稼働時間200H以内想定'
  , wh_max DECIMAL(4,1) comment '作業時間（上限・H）'
  , wh_min DECIMAL(4,1) comment '作業時間（下限・H）'
  , wh_mid DECIMAL(4,1) comment '中割時間'
  , wh_other DECIMAL(4,1) comment 'その他（時間）'
  , excess_price DECIMAL(10,1) comment '超過単価（円）'
  , deducation_price DECIMAL(10,1) comment '控除単価（円）'
  , pj_start_dt DATE comment '業務案件今月分の開始日'
  , pj_end_dt DATE comment '業務案件今月分の終了日'
  , work_time_unit_k CHAR(1) comment '作業時間単位'
  , contract_work_month DECIMAL(6,2) comment '工数（契約用）'
  , pj_class_cd char(3) comment '案件分類'
  , work_start_time TIME comment '出勤時刻'
  , work_end_time TIME comment '退勤時刻'
  , break_time_min INT comment '休憩時間（分）'
  , payment_plan_d INT comment '支払サイト/出金日付の日'
  , payment_due_k CHAR(1) comment '支払サイト/締日区分'
  , pay_month_k CHAR(1) comment '支払サイト/支払月区分'
  , pay_plan_d INT comment '支払サイト/支払日付の日'
  , salary_pay_plan_dt INT comment '支払サイト/出金予定日'
  , work_content_dtl VARCHAR(500) comment '業務内容詳細'
  , work_evidence1 VARCHAR(500) comment 'エビデンス1'
  , work_evidence2 VARCHAR(500) comment 'エビデンス2'
  , sys_ins_user_id VARCHAR(10) not null comment '登録者'
  , sys_ins_dt DATETIME not null comment '登録日時'
  , sys_ins_pg_id VARCHAR(15) not null comment '登録機能ID'
  , sys_upd_user_id VARCHAR(10) comment '更新者'
  , sys_upd_dt DATETIME comment '更新日時'
  , sys_upd_pg_id VARCHAR(15) comment '更新機能ID'
  , constraint apprClientContract_PKC primary key (appr_doc_id)
) comment 'クライアント　契約条件' ENGINE=InnoDB DEFAULT CHARSET=utf8 COLLATE=utf8_bin;

-- BP見積受信情報
drop table if exists bpEstRecv cascade;

create table bpEstRecv (
  bp_est_recv_id INT not null AUTO_INCREMENT comment 'BP見積受信ID'
  , recv_dt DATETIME not null comment '受信日時'
  , bp_est_no VARCHAR(10) not null comment 'BP見積NO'
  , bp_est_req_no VARCHAR(10) not null comment 'BP見積依頼NO'
  , use_flg CHAR default 'Y' not null comment '使用FLG:Y/N'
  , bp_est_amt_without_tax DECIMAL(13,1) not null comment '見積金額(税抜)'
  , work_start_ymd DATE not null comment '作業開始日'
  , work_end_ymd DATE not null comment '作業終了日'
  , project_no VARCHAR(10) not null comment '案件番号'
  , project_nm VARCHAR(50) comment '案件名称'
  , work_content VARCHAR(200) comment '作業内容'
  , work_place VARCHAR(100) comment '作業場所'
  , bp_est_content VARCHAR(500) comment '見積内容'
  , special_affairs VARCHAR(500) comment '特記事項'
  , remark VARCHAR(500) comment '備考'
  , appr_doc_no VARCHAR(10) not null comment '稟議書NO'
  , sys_ins_user_id VARCHAR(10) not null comment '登録者'
  , sys_ins_dt DATETIME not null comment '登録日時'
  , sys_ins_pg_id VARCHAR(15) not null comment '登録機能ID'
  , sys_upd_user_id VARCHAR(10) comment '更新者'
  , sys_upd_dt DATETIME comment '更新日時'
  , sys_upd_pg_id VARCHAR(15) comment '更新機能ID'
  , constraint bpEstRecv_PKC primary key (bp_est_recv_id)
) comment 'BP見積受信情報' ENGINE=InnoDB DEFAULT CHARSET=utf8 COLLATE=utf8_bin;

-- BP発注情報
drop table if exists bpShippingOrder cascade;

create table bpShippingOrder (
  bp_shipping_order_id INT not null AUTO_INCREMENT comment 'BP発注ID'
  , bp_shipping_order_no VARCHAR(10) not null comment 'BP発注NO'
  , bp_estimation_id INT not null comment 'BP見積ID'
  , bp_estimation_no VARCHAR(10) not null comment 'BP見積NO'
  , bp_estimation_request_id INT comment 'BP見積依頼ID'
  , bp_estimation_request_no VARCHAR(10) comment 'BP見積依頼NO'
  , use_flg CHAR default 'Y' not null comment '使用FLG:Y/N'
  , order_hid INT not null comment '受注HID'
  , order_amt_without_tax DECIMAL(13,1) not null comment '受注金額(税抜)'
  , work_start_ymd DATE not null comment '作業開始日'
  , work_end_ymd DATE not null comment '作業終了日'
  , project_no VARCHAR(10) not null comment '案件番号'
  , project_nm VARCHAR(50) comment '案件名称'
  , work_content VARCHAR(200) comment '作業内容'
  , work_place VARCHAR(100) comment '作業場所'
  , order_content VARCHAR(500) comment '注文内容'
  , order_special_affairs VARCHAR(500) comment '特記事項'
  , order_remark VARCHAR(500) comment '備考'
  , order_doc_file_id INT comment '注文書'
  , sys_ins_user_id VARCHAR(10) not null comment '登録者'
  , sys_ins_dt DATETIME not null comment '登録日時'
  , sys_ins_pg_id VARCHAR(15) not null comment '登録機能ID'
  , sys_upd_user_id VARCHAR(10) comment '更新者'
  , sys_upd_dt DATETIME comment '更新日時'
  , sys_upd_pg_id VARCHAR(15) comment '更新機能ID'
  , constraint bpShippingOrder_PKC primary key (bp_shipping_order_id)
) comment 'BP発注情報' ENGINE=InnoDB DEFAULT CHARSET=utf8 COLLATE=utf8_bin;

-- BP見積HDR情報
drop table if exists bpEstHdr cascade;

create table bpEstHdr (
  bp_est_hid INT not null AUTO_INCREMENT comment 'BP見積ID'
  , bp_est_no VARCHAR(10) not null comment 'BP見積NO'
  , bp_est_request_no VARCHAR(10) comment 'BP見積依頼NO'
  , use_flg CHAR default 'Y' not null comment '使用FLG:Y/N'
  , bp_est_amt_without_tax DECIMAL(13,1) not null comment '見積金額(税抜)'
  , work_start_ymd DATE not null comment '作業開始日'
  , work_end_ymd DATE not null comment '作業終了日'
  , project_no VARCHAR(10) not null comment '案件番号'
  , project_nm VARCHAR(50) comment '案件名称'
  , work_content VARCHAR(200) comment '作業内容'
  , work_place VARCHAR(100) comment '作業場所'
  , bp_est_content VARCHAR(500) comment '見積内容'
  , special_affairs VARCHAR(500) comment '特記事項'
  , remark VARCHAR(500) comment '備考'
  , sys_ins_user_id VARCHAR(10) not null comment '登録者'
  , sys_ins_dt DATETIME not null comment '登録日時'
  , sys_ins_pg_id VARCHAR(15) not null comment '登録機能ID'
  , sys_upd_user_id VARCHAR(10) comment '更新者'
  , sys_upd_dt DATETIME comment '更新日時'
  , sys_upd_pg_id VARCHAR(15) comment '更新機能ID'
  , constraint bpEstHdr_PKC primary key (bp_est_hid)
) comment 'BP見積HDR情報' ENGINE=InnoDB DEFAULT CHARSET=utf8 COLLATE=utf8_bin;

-- 承認済見積依頼書
drop table if exists bpEstReqAccepted cascade;

create table bpEstReqAccepted (
  bp_est_req_id INT not null comment 'BP見積依頼HID'
  , last_accept_user_id VARCHAR(10) not null comment '最終承認者ID'
  , last_accept_date DATETIME not null comment '最終承認日時'
  , bp_est_req_apply_hid INT not null comment 'BP見積依頼申請HID'
  , sys_ins_user_id VARCHAR(10) not null comment '登録者'
  , sys_ins_dt DATETIME not null comment '登録日時'
  , sys_ins_pg_id VARCHAR(15) not null comment '登録機能ID'
  , sys_upd_user_id VARCHAR(10) comment '更新者'
  , sys_upd_dt DATETIME comment '更新日時'
  , sys_upd_pg_id VARCHAR(15) comment '更新機能ID'
  , constraint bpEstReqAccepted_PKC primary key (bp_est_req_id)
) comment '承認済見積依頼書' ENGINE=InnoDB DEFAULT CHARSET=utf8 COLLATE=utf8_bin;

-- BP見積依頼送信情報
drop table if exists bpEstReqSend cascade;

create table bpEstReqSend (
  bp_est_req_send_id INT not null AUTO_INCREMENT comment 'BP見積依頼送信ID'
  , send_dt DATETIME not null comment '送信日時'
  , bp_est_req_id INT not null comment 'BP見積依頼ID'
  , bp_est_req_no VARCHAR(10) not null comment 'BP見積依頼NO'
  , use_flg CHAR default 'Y' not null comment '使用FLG:Y/N'
  , bp_est_hope_amt_without_tax DECIMAL(13,1) not null comment '見積希望金額(税抜)'
  , work_start_ymd DATE not null comment '作業開始日'
  , work_end_ymd DATE not null comment '作業終了日'
  , project_no VARCHAR(10) not null comment '案件番号'
  , project_nm VARCHAR(50) comment '案件名称'
  , work_content VARCHAR(200) comment '作業内容'
  , work_place VARCHAR(100) comment '作業場所'
  , bp_est_req_content VARCHAR(500) comment '見積依頼内容'
  , order_special_affairs VARCHAR(500) comment '特記事項'
  , order_remark VARCHAR(500) comment '備考'
  , appr_doc_no VARCHAR(10) not null comment '稟議書NO'
  , sys_ins_user_id VARCHAR(10) not null comment '登録者'
  , sys_ins_dt DATETIME not null comment '登録日時'
  , sys_ins_pg_id VARCHAR(15) not null comment '登録機能ID'
  , sys_upd_user_id VARCHAR(10) comment '更新者'
  , sys_upd_dt DATETIME comment '更新日時'
  , sys_upd_pg_id VARCHAR(15) comment '更新機能ID'
  , constraint bpEstReqSend_PKC primary key (bp_est_req_send_id)
) comment 'BP見積依頼送信情報' ENGINE=InnoDB DEFAULT CHARSET=utf8 COLLATE=utf8_bin;

-- 採番管理
drop table if exists noSeqMgr cascade;

create table noSeqMgr (
  sys_cd VARCHAR(10) not null comment 'システムC'
  , sys_tbl_id VARCHAR(255) not null comment 'テーブルID'
  , sys_field VARCHAR(255) not null comment 'フィールドID'
  , sys_prefix VARCHAR(2) not null comment '接頭辞'
  , sys_ym VARCHAR(4) not null comment '年月'
  , sys_seq_start_no INT not null comment '開始NO'
  , sys_seq_end_no INT not null comment '終了NO'
  , sys_current_no INT not null comment 'カレントNO'
  , sys_field_remark VARCHAR(50) comment '備考'
  , sys_ins_user_id VARCHAR(10) not null comment '登録者'
  , sys_ins_dt DATETIME not null comment '登録日時'
  , sys_ins_pg_id VARCHAR(15) not null comment '登録機能ID'
  , sys_upd_user_id VARCHAR(10) comment '更新者'
  , sys_upd_dt DATETIME comment '更新日時'
  , sys_upd_pg_id VARCHAR(15) comment '更新機能ID'
  , constraint noSeqMgr_PKC primary key (sys_cd,sys_tbl_id,sys_field)
) comment '採番管理' ENGINE=InnoDB DEFAULT CHARSET=utf8 COLLATE=utf8_bin;

create unique index sysSetting_u1
  on noSeqMgr(sys_field,sys_ym,sys_seq_start_no,sys_seq_end_no);

-- BP見積依頼申請DTL情報
drop table if exists bpEstReqApplyDtl cascade;

create table bpEstReqApplyDtl (
  bp_est_req_apply_did INT not null AUTO_INCREMENT comment 'BP見積依頼申請DID'
  , bp_est_req_apply_hid INT not null comment 'BP見積依頼申請HID'
  , display_order INT not null comment '表示順'
  , accept_user_id VARCHAR(10) not null comment '承認者ID'
  , confirm_st CHAR(1) not null comment '承認状況:1：未承認
2：承認済
3：拒否'
  , con_rej_comment VARCHAR(500) not null comment '承認(拒否)コメント'
  , con_rej_dt TIME comment '承認(拒否)日時'
  , sys_ins_user_id VARCHAR(10) not null comment '登録者'
  , sys_ins_dt DATETIME not null comment '登録日時'
  , sys_ins_pg_id VARCHAR(15) not null comment '登録機能ID'
  , sys_upd_user_id VARCHAR(10) comment '更新者'
  , sys_upd_dt DATETIME comment '更新日時'
  , sys_upd_pg_id VARCHAR(15) comment '更新機能ID'
  , constraint bpEstReqApplyDtl_PKC primary key (bp_est_req_apply_did)
) comment 'BP見積依頼申請DTL情報' ENGINE=InnoDB DEFAULT CHARSET=utf8 COLLATE=utf8_bin;

create unique index bpEstReqApplyDtl_u_1
  on bpEstReqApplyDtl(bp_est_req_apply_hid,display_order);

-- BP見積依頼申請HDR情報
drop table if exists bpEstReqApplyHdr cascade;

create table bpEstReqApplyHdr (
  bp_est_req_apply_hid INT not null AUTO_INCREMENT comment 'BP見積依頼申請HID'
  , bp_est_req_id INT not null comment 'BP見積依頼ID'
  , use_flg CHAR(1) default 'Y' not null comment '使用FLG:Y:使用
N:削除'
  , apply_reason VARCHAR(500) comment '申請理由'
  , apply_user_id VARCHAR(10) not null comment '申請者ID'
  , apply_dt TIME not null comment '申請日時'
  , confirm_wait_display_order INT comment '承認待表示順:承認待となっている申請DTLの表示順を設定'
  , sys_ins_user_id VARCHAR(10) not null comment '登録者'
  , sys_ins_dt DATETIME not null comment '登録日時'
  , sys_ins_pg_id VARCHAR(15) not null comment '登録機能ID'
  , sys_upd_user_id VARCHAR(10) comment '更新者'
  , sys_upd_dt DATETIME comment '更新日時'
  , sys_upd_pg_id VARCHAR(15) comment '更新機能ID'
  , constraint bpEstReqApplyHdr_PKC primary key (bp_est_req_apply_hid)
) comment 'BP見積依頼申請HDR情報' ENGINE=InnoDB DEFAULT CHARSET=utf8 COLLATE=utf8_bin;

-- BP見積依頼HDR情報
drop table if exists bpEstReqHdr cascade;

create table bpEstReqHdr (
  bp_est_req_hid INT not null AUTO_INCREMENT comment 'BP見積依頼HID'
  , bp_est_req_no VARCHAR(10) not null comment 'BP見積依頼NO'
  , bp_order_no VARCHAR(10) not null comment 'BP受注NO'
  , use_flg CHAR default 'Y' not null comment '使用FLG:Y/N'
  , order_hid INT not null comment '受注HID'
  , bp_est_hope_amt_without_tax DECIMAL(13,1) not null comment '見積希望金額(税抜)'
  , work_start_ymd DATE not null comment '作業開始日'
  , work_end_ymd DATE not null comment '作業終了日'
  , project_no VARCHAR(10) not null comment '案件番号'
  , project_nm VARCHAR(50) comment '案件名称'
  , work_content VARCHAR(200) comment '作業内容'
  , work_place VARCHAR(100) comment '作業場所'
  , bp_est_req_content VARCHAR(500) comment '見積依頼内容'
  , order_special_affairs VARCHAR(500) comment '特記事項'
  , order_remark VARCHAR(500) comment '備考'
  , sys_ins_user_id VARCHAR(10) not null comment '登録者'
  , sys_ins_dt DATETIME not null comment '登録日時'
  , sys_ins_pg_id VARCHAR(15) not null comment '登録機能ID'
  , sys_upd_user_id VARCHAR(10) comment '更新者'
  , sys_upd_dt DATETIME comment '更新日時'
  , sys_upd_pg_id VARCHAR(15) comment '更新機能ID'
  , constraint bpEstReqHdr_PKC primary key (bp_est_req_hid)
) comment 'BP見積依頼HDR情報' ENGINE=InnoDB DEFAULT CHARSET=utf8 COLLATE=utf8_bin;

-- 受注確定情報
drop table if exists orderConfirm cascade;

create table orderConfirm (
  order_hid INT not null comment '受注HID'
  , order_confirm_user_id VARCHAR(10) not null comment '受注確認者ID'
  , order_confirm_dt DATETIME not null comment '受注確認日時'
  , order_confirm_remark VARCHAR(500) not null comment '受注確認備考'
  , sys_ins_user_id VARCHAR(10) not null comment '登録者'
  , sys_ins_dt DATETIME not null comment '登録日時'
  , sys_ins_pg_id VARCHAR(15) not null comment '登録機能ID'
  , sys_upd_user_id VARCHAR(10) comment '更新者'
  , sys_upd_dt DATETIME comment '更新日時'
  , sys_upd_pg_id VARCHAR(15) comment '更新機能ID'
  , constraint orderConfirm_PKC primary key (order_hid)
) comment '受注確定情報' ENGINE=InnoDB DEFAULT CHARSET=utf8 COLLATE=utf8_bin;

-- 受注HDR情報
drop table if exists orderHdr cascade;

create table orderHdr (
  order_hid INT not null AUTO_INCREMENT comment '受注HID'
  , order_no VARCHAR(10) not null comment '受注NO'
  , use_flg CHAR(1) default 'Y' not null comment '使用FLG:Y:使用
N:削除'
  , create_dt DATE not null comment '作成日'
  , order_amt_without_tax DECIMAL(13,1) not null comment '受注金額(税抜)'
  , work_start_ymd DATE not null comment '作業開始日'
  , work_end_ymd DATE not null comment '作業終了日'
  , project_no VARCHAR(10) not null comment '案件番号'
  , project_nm VARCHAR(50) comment '案件名称'
  , work_content VARCHAR(200) comment '作業内容'
  , work_place VARCHAR(100) comment '作業場所'
  , order_content VARCHAR(500) comment '注文内容'
  , special_affairs VARCHAR(500) comment '特記事項'
  , remark VARCHAR(500) comment '備考'
  , order_doc_file_id INT comment '注文書'
  , est_hid INT comment '見積HID'
  , sys_ins_user_id VARCHAR(10) not null comment '登録者'
  , sys_ins_dt DATETIME not null comment '登録日時'
  , sys_ins_pg_id VARCHAR(15) not null comment '登録機能ID'
  , sys_upd_user_id VARCHAR(10) comment '更新者'
  , sys_upd_dt DATETIME comment '更新日時'
  , sys_upd_pg_id VARCHAR(15) comment '更新機能ID'
  , constraint orderHdr_PKC primary key (order_hid)
) comment '受注HDR情報' ENGINE=InnoDB DEFAULT CHARSET=utf8 COLLATE=utf8_bin;

-- 受注DTL情報
drop table if exists orderDtl cascade;

create table orderDtl (
  order_did INT not null AUTO_INCREMENT comment '受注DID'
  , order_hid INT not null comment '受注HID'
  , appr_doc_id INT not null comment '稟議書ID'
  , sys_ins_user_id VARCHAR(10) not null comment '登録者'
  , sys_ins_dt DATETIME not null comment '登録日時'
  , sys_ins_pg_id VARCHAR(15) not null comment '登録機能ID'
  , sys_upd_user_id VARCHAR(10) comment '更新者'
  , sys_upd_dt DATETIME comment '更新日時'
  , sys_upd_pg_id VARCHAR(15) comment '更新機能ID'
  , constraint orderDtl_PKC primary key (order_did)
) comment '受注DTL情報' ENGINE=InnoDB DEFAULT CHARSET=utf8 COLLATE=utf8_bin;

-- ファイル管理
drop table if exists sysFileMgr cascade;

create table sysFileMgr (
  file_id INT not null AUTO_INCREMENT comment 'ファイルID'
  , file_ext VARCHAR(8) comment '拡張子'
  , mime_type VARCHAR(64) comment 'MimeType'
  , file_nm VARCHAR(64) comment 'オリジナルファイル名'
  , file_path VARCHAR(255) comment '保存相対パス'
  , file_size INT comment 'ファイルサイズ'
  , file_user_id VARCHAR(10) comment '保存ユーザ'
  , file_ymd DATETIME comment '保存日時'
  , use_flg CHAR(1) default 'Y' comment '使用FLG:Y:使用
N:削除'
  , sys_ins_user_id VARCHAR(10) not null comment '登録者'
  , sys_ins_dt DATETIME not null comment '登録日時'
  , sys_ins_pg_id VARCHAR(15) not null comment '登録機能ID'
  , sys_upd_user_id VARCHAR(10) comment '更新者'
  , sys_upd_dt DATETIME comment '更新日時'
  , sys_upd_pg_id VARCHAR(15) comment '更新機能ID'
  , constraint sysFileMgr_PKC primary key (file_id)
) comment 'ファイル管理' ENGINE=InnoDB DEFAULT CHARSET=utf8 COLLATE=utf8_bin;

create unique index sysSetting_u1
  on sysFileMgr(file_ext,mime_type,file_nm,file_path);

-- 設定
drop table if exists sysSetting cascade;

create table sysSetting (
  sys_setting_id INT not null AUTO_INCREMENT comment '設定ID'
  , sys_cd VARCHAR(10) comment 'システムC'
  , major_class_cd VARCHAR(10) comment '大分類C'
  , mid_class_cd VARCHAR(10) comment '中分類C'
  , minor_class_cd VARCHAR(10) comment '小分類C'
  , str_val1 VARCHAR(50) comment '文字列1'
  , str_val2 VARCHAR(50) comment '文字列2'
  , str_val3 VARCHAR(50) comment '文字列3'
  , str_val4 VARCHAR(50) comment '文字列4'
  , str_val5 VARCHAR(50) comment '文字列5'
  , num_val1 DECIMAL(10,3) comment '数値1'
  , num_val2 DECIMAL(10,3) comment '数値2'
  , num_val3 DECIMAL(10,3) comment '数値3'
  , num_val4 DECIMAL(10,3) comment '数値4'
  , num_val5 DECIMAL(10,3) comment '数値5'
  , remark1 VARCHAR(500) comment '備考1'
  , remark2 VARCHAR(500) comment '備考2'
  , sys_ins_user_id VARCHAR(10) not null comment '登録者'
  , sys_ins_dt DATETIME not null comment '登録日時'
  , sys_ins_pg_id VARCHAR(15) not null comment '登録機能ID'
  , sys_upd_user_id VARCHAR(10) comment '更新者'
  , sys_upd_dt DATETIME comment '更新日時'
  , sys_upd_pg_id VARCHAR(15) comment '更新機能ID'
  , constraint sysSetting_PKC primary key (sys_setting_id)
) comment '設定' ENGINE=InnoDB DEFAULT CHARSET=utf8 COLLATE=utf8_bin;

create unique index sysSetting_u1
  on sysSetting(sys_cd,major_class_cd,mid_class_cd,minor_class_cd);

-- 見積HDR情報
drop table if exists estHdr cascade;

create table estHdr (
  est_hid INT not null AUTO_INCREMENT comment '見積HID'
  , est_no VARCHAR(10) not null comment '見積NO'
  , shipping_order_spec_no VARCHAR(10) not null comment '発注仕様書NO'
  , submit_dt DATE not null comment '提出日'
  , client_nm VARCHAR(50) not null comment 'クライアント名'
  , estimation_amt_without_tax DECIMAL(13,1) not null comment '見積金額(税抜)'
  , work_start_ymd DATE not null comment '作業開始日'
  , work_end_ymd DATE not null comment '作業終了日'
  , project_no VARCHAR(10) not null comment '案件番号'
  , project_nm VARCHAR(50) not null comment '案件名称'
  , work_content VARCHAR(200) not null comment '作業内容'
  , work_place VARCHAR(100) not null comment '作業場所'
  , order_content VARCHAR(500) comment '注文内容'
  , special_affairs VARCHAR(500) comment '特記事項'
  , payment_cond VARCHAR(500) comment 'お支払条件'
  , remark VARCHAR(500) comment '備考'
  , sys_ins_user_id VARCHAR(10) not null comment '登録者'
  , sys_ins_dt DATETIME not null comment '登録日時'
  , sys_ins_pg_id VARCHAR(15) not null comment '登録機能ID'
  , sys_upd_user_id VARCHAR(10) comment '更新者'
  , sys_upd_dt DATETIME comment '更新日時'
  , sys_upd_pg_id VARCHAR(15) comment '更新機能ID'
  , constraint estHdr_PKC primary key (est_hid)
) comment '見積HDR情報' ENGINE=InnoDB DEFAULT CHARSET=utf8 COLLATE=utf8_bin;

-- 見積DTL情報
drop table if exists estDtl cascade;

create table estDtl (
  est_did INT not null AUTO_INCREMENT comment '見積DID'
  , est_hid INT not null comment '見積HID'
  , appr_doc_id INT not null comment '稟議書ID'
  , sys_ins_user_id VARCHAR(10) not null comment '登録者'
  , sys_ins_dt DATETIME not null comment '登録日時'
  , sys_ins_pg_id VARCHAR(15) not null comment '登録機能ID'
  , sys_upd_user_id VARCHAR(10) comment '更新者'
  , sys_upd_dt DATETIME comment '更新日時'
  , sys_upd_pg_id VARCHAR(15) comment '更新機能ID'
  , constraint estDtl_PKC primary key (est_did)
) comment '見積DTL情報' ENGINE=InnoDB DEFAULT CHARSET=utf8 COLLATE=utf8_bin;

-- 承認済稟議書
drop table if exists apprDocAccepted cascade;

create table apprDocAccepted (
  appr_doc_id INT not null comment '稟議書ID'
  , last_accept_user_id VARCHAR(10) not null comment '最終承認者ID'
  , last_accept_date DATETIME not null comment '最終承認日時'
  , approval_apply_hid INT not null comment '稟議書申請HID'
  , sys_ins_user_id VARCHAR(10) not null comment '登録者'
  , sys_ins_dt DATETIME not null comment '登録日時'
  , sys_ins_pg_id VARCHAR(15) not null comment '登録機能ID'
  , sys_upd_user_id VARCHAR(10) comment '更新者'
  , sys_upd_dt DATETIME comment '更新日時'
  , sys_upd_pg_id VARCHAR(15) comment '更新機能ID'
  , constraint apprDocAccepted_PKC primary key (appr_doc_id)
) comment '承認済稟議書' ENGINE=InnoDB DEFAULT CHARSET=utf8 COLLATE=utf8_bin;

-- 稟議書申請DTL情報
drop table if exists apprApplyDtl cascade;

create table apprApplyDtl (
  appr_apply_did INT not null AUTO_INCREMENT comment '稟議書申請DID'
  , appr_apply_hid INT not null comment '稟議書申請HID'
  , display_order INT not null comment '表示順'
  , accept_user_id VARCHAR(10) not null comment '承認者ID'
  , confirm_st CHAR(1) not null comment '承認状況:1：未承認
2：承認済
3：拒否'
  , con_rej_comment VARCHAR(500) not null comment '承認(拒否)コメント'
  , con_rej_dt TIME comment '承認(拒否)日時'
  , sys_ins_user_id VARCHAR(10) not null comment '登録者'
  , sys_ins_dt DATETIME not null comment '登録日時'
  , sys_ins_pg_id VARCHAR(15) not null comment '登録機能ID'
  , sys_upd_user_id VARCHAR(10) comment '更新者'
  , sys_upd_dt DATETIME comment '更新日時'
  , sys_upd_pg_id VARCHAR(15) comment '更新機能ID'
  , constraint apprApplyDtl_PKC primary key (appr_apply_did)
) comment '稟議書申請DTL情報' ENGINE=InnoDB DEFAULT CHARSET=utf8 COLLATE=utf8_bin;

alter table apprApplyDtl add unique apprApplyDtl_u_1 (appr_apply_hid,display_order) ;

-- 稟議書申請HDR情報
drop table if exists apprApplyHdr cascade;

create table apprApplyHdr (
  appr_apply_hid INT not null AUTO_INCREMENT comment '稟議書申請HID'
  , appr_doc_id INT not null comment '稟議書ID'
  , use_flg CHAR(1) default 'Y' not null comment '使用FLG:Y:使用
N:削除'
  , apply_reason VARCHAR(500) comment '申請理由'
  , apply_user_id VARCHAR(10) not null comment '申請者ID'
  , apply_dt TIME not null comment '申請日時'
  , confirm_wait_display_order INT comment '承認待表示順:承認待となっている申請DTLの表示順を設定'
  , sys_ins_user_id VARCHAR(10) not null comment '登録者'
  , sys_ins_dt DATETIME not null comment '登録日時'
  , sys_ins_pg_id VARCHAR(15) not null comment '登録機能ID'
  , sys_upd_user_id VARCHAR(10) comment '更新者'
  , sys_upd_dt DATETIME comment '更新日時'
  , sys_upd_pg_id VARCHAR(15) comment '更新機能ID'
  , constraint apprApplyHdr_PKC primary key (appr_apply_hid)
) comment '稟議書申請HDR情報' ENGINE=InnoDB DEFAULT CHARSET=utf8 COLLATE=utf8_bin;

-- メニュー・サブメニュー対照表
drop table if exists menuSubMenu cascade;

create table menuSubMenu (
  menu_cd VARCHAR(10) not null comment 'メニューCD'
  , sub_menu_cd VARCHAR(10) not null comment '子メニューCD'
  , display_order INT not null comment '表示順'
  , sys_ins_user_id VARCHAR(10) not null comment '登録者'
  , sys_ins_dt DATETIME not null comment '登録日時'
  , sys_ins_pg_id VARCHAR(15) not null comment '登録機能ID'
  , sys_upd_user_id VARCHAR(10) comment '更新者'
  , sys_upd_dt DATETIME comment '更新日時'
  , sys_upd_pg_id VARCHAR(15) comment '更新機能ID'
  , constraint menuSubMenu_PKC primary key (menu_cd,sub_menu_cd)
) comment 'メニュー・サブメニュー対照表' ENGINE=InnoDB DEFAULT CHARSET=utf8 COLLATE=utf8_bin;

-- サブメニュー・機能対照表
drop table if exists submenuProgram cascade;

create table submenuProgram (
  sub_menu_cd VARCHAR(10) not null comment 'サブメニューCD'
  , menu_type CHAR(1) not null comment 'メニュータイプ:M:メニュー
P：機能'
  , pg_id VARCHAR(15) not null comment '機能ID'
  , low_sub_menu_cd VARCHAR(10) not null comment '下位サブメニューCD'
  , display_order INT not null comment '表示順'
  , sys_ins_user_id VARCHAR(10) not null comment '登録者'
  , sys_ins_dt DATETIME not null comment '登録日時'
  , sys_ins_pg_id VARCHAR(15) not null comment '登録機能ID'
  , sys_upd_user_id VARCHAR(10) comment '更新者'
  , sys_upd_dt DATETIME comment '更新日時'
  , sys_upd_pg_id VARCHAR(15) comment '更新機能ID'
  , constraint submenuProgram_PKC primary key (sub_menu_cd,menu_type,pg_id,low_sub_menu_cd)
) comment 'サブメニュー・機能対照表' ENGINE=InnoDB DEFAULT CHARSET=utf8 COLLATE=utf8_bin;

-- ユーザーWF承認経路
drop table if exists userWfConfirmRoute cascade;

create table userWfConfirmRoute (
  user_wf_confirm_route_id VARCHAR(10) not null comment 'ユーザーWF承認経路ID'
  , user_id VARCHAR(10) not null comment 'ユーザーID'
  , accept_user_id CHAR(10) not null comment '承認者ID'
  , display_order INT not null comment '表示順'
  , sys_ins_user_id VARCHAR(10) not null comment '登録者'
  , sys_ins_dt DATETIME not null comment '登録日時'
  , sys_ins_pg_id VARCHAR(15) not null comment '登録機能ID'
  , sys_upd_user_id VARCHAR(10) comment '更新者'
  , sys_upd_dt DATETIME comment '更新日時'
  , sys_upd_pg_id VARCHAR(15) comment '更新機能ID'
  , constraint userWfConfirmRoute_PKC primary key (user_wf_confirm_route_id)
) comment 'ユーザーWF承認経路' ENGINE=InnoDB DEFAULT CHARSET=utf8 COLLATE=utf8_bin;

-- サブメニュー情報
drop table if exists subMenu cascade;

create table subMenu (
  sub_menu_cd VARCHAR(10) not null comment 'サブメニューCD'
  , sub_menu_nam VARCHAR(50) not null comment 'サブメニュー名'
  , use_flg CHAR(1) default 'Y' comment '使用FLG:Y:使用
N:削除'
  , sys_ins_user_id VARCHAR(10) not null comment '登録者'
  , sys_ins_dt DATETIME not null comment '登録日時'
  , sys_ins_pg_id VARCHAR(15) not null comment '登録機能ID'
  , sys_upd_user_id VARCHAR(10) comment '更新者'
  , sys_upd_dt DATETIME comment '更新日時'
  , sys_upd_pg_id VARCHAR(15) comment '更新機能ID'
  , constraint subMenu_PKC primary key (sub_menu_cd)
) comment 'サブメニュー情報' ENGINE=InnoDB DEFAULT CHARSET=utf8 COLLATE=utf8_bin;

-- メニュー情報(TSRS)
drop table if exists tsrs_menu cascade;

create table tsrs_menu (
  menu_cd VARCHAR(10) not null comment 'メニューC'
  , menu_nm VARCHAR(50) not null comment 'メニュー名'
  , use_flg CHAR(1) default 'Y' not null comment '使用FLG:Y:使用
N:削除'
  , sys_ins_user_id VARCHAR(10) not null comment '登録者'
  , sys_ins_dt DATETIME not null comment '登録日時'
  , sys_ins_pg_id VARCHAR(15) not null comment '登録機能ID'
  , sys_upd_user_id VARCHAR(10) comment '更新者'
  , sys_upd_dt DATETIME comment '更新日時'
  , sys_upd_pg_id VARCHAR(15) comment '更新機能ID'
  , constraint tsrs_menu_PKC primary key (menu_cd)
) comment 'メニュー情報(TSRS)' ENGINE=InnoDB DEFAULT CHARSET=utf8 COLLATE=utf8_bin;

-- 機能情報
drop table if exists program cascade;

create table program (
  pg_id VARCHAR(15) comment '機能ID'
  , pg_nm VARCHAR(50) not null comment '機能名'
  , use_flg CHAR(1) default 'Y' not null comment '使用FLG:Y:使用
N:削除'
  , sys_ins_user_id VARCHAR(10) not null comment '登録者'
  , sys_ins_dt DATETIME not null comment '登録日時'
  , sys_ins_pg_id VARCHAR(15) not null comment '登録機能ID'
  , sys_upd_user_id VARCHAR(10) comment '更新者'
  , sys_upd_dt DATETIME comment '更新日時'
  , sys_upd_pg_id VARCHAR(15) comment '更新機能ID'
  , constraint program_PKC primary key (pg_id)
) comment '機能情報' ENGINE=InnoDB DEFAULT CHARSET=utf8 COLLATE=utf8_bin;

-- ユーザー情報(TSRS)
drop table if exists tsrs_user cascade;

create table tsrs_user (
  user_id VARCHAR(10) not null comment 'ユーザーID'
  , user_pwd VARCHAR(50) not null comment 'パスワード'
  , user_nm VARCHAR(30) not null comment 'ユーザー名称'
  , last_login_ip VARCHAR(20) comment '最後ログインIP'
  , last_login_dt DATETIME comment '最後ログイン日時'
  , login_able_flg CHAR(1) default 'N' not null comment 'ログイン可能FLG:Y:ログイン可能
N:ログイン不可'
  , user_type CHAR(1) default 'C' not null comment 'ユーザータイプ:C：普通ユーザー
A：システム管理者'
  , use_flg CHAR(1) default 'Y' not null comment '使用FLG:Y:使用
N:削除'
  , menu_cd VARCHAR(10) not null comment 'メニューC'
  , job_title_cd CHAR(3) not null comment '職位C'
  , sys_ins_user_id VARCHAR(10) not null comment '登録者'
  , sys_ins_dt DATETIME not null comment '登録日時'
  , sys_ins_pg_id VARCHAR(15) not null comment '登録機能ID'
  , sys_upd_user_id VARCHAR(10) comment '更新者'
  , sys_upd_dt DATETIME comment '更新日時'
  , sys_upd_pg_id VARCHAR(15) comment '更新機能ID'
  , constraint tsrs_user_PKC primary key (user_id)
) comment 'ユーザー情報(TSRS)' ENGINE=InnoDB DEFAULT CHARSET=utf8 COLLATE=utf8_bin;

-- 要員（外注BP）連絡先
drop table if exists apprBpConAdr cascade;

create table apprBpConAdr (
  appr_doc_id INT not null comment '稟議書ID'
  , bp_comp_id VARCHAR(10) not null comment '所属会社ID'
  , bp_comp_emp_nm VARCHAR(50) not null comment '所属会社名/社員氏名'
  , bp_comp_url VARCHAR(100) comment '所属会社URL'
  , bp_my_number VARCHAR(20) comment '所属会社法人マイナンバー'
  , bp_post_no VARCHAR(10) comment '所属会社郵便番号'
  , bp_addr VARCHAR(200) comment '所属会社連絡先住所'
  , bp_tel_no VARCHAR(15) comment '所属会社電話番号'
  , bp_fax_no VARCHAR(15) comment '所属会社FAX番号'
  , bp_staff_nm VARCHAR(30) comment '所属会社担当者名'
  , bp_staff_nm_fuli VARCHAR(30) comment '所属会社担当者名（フリガナ）'
  , bp_staff_dept_nm VARCHAR(50) comment '所属会社担当者所属部署'
  , bp_staff_position VARCHAR(50) comment '所属会社担当者役職'
  , bp_staff_tel_no VARCHAR(15) comment '所属会社担当者電話番号'
  , bp_staff_fax_no VARCHAR(15) comment '所属会社担当者FAX番号'
  , bp_staff_mobile_no VARCHAR(20) comment '所属会社担当者携帯番号'
  , bp_staff_mobile_mail_addr VARCHAR(30) comment '所属会社担当者携帯番号メールアドレス'
  , bp_staff_mail_addr VARCHAR(100) comment '所属会社担当者個人メール'
  , bp_staff_mail_addr_cc VARCHAR(100) comment '所属会社担当者個人メールCC'
  , staff_addr VARCHAR(200) comment '要員住所'
  , staff_post_no VARCHAR(10) comment '要員郵便番号'
  , staff_mail_addr VARCHAR(30) comment '要員メールアドレス'
  , staff_tel_no VARCHAR(15) comment '要員電話番号'
  , staff_mobile_no VARCHAR(20) comment '要員携帯電話番号'
  , sys_ins_user_id VARCHAR(10) not null comment '登録者'
  , sys_ins_dt DATETIME not null comment '登録日時'
  , sys_ins_pg_id VARCHAR(15) not null comment '登録機能ID'
  , sys_upd_user_id VARCHAR(10) comment '更新者'
  , sys_upd_dt DATETIME comment '更新日時'
  , sys_upd_pg_id VARCHAR(15) comment '更新機能ID'
  , constraint apprBpConAdr_PKC primary key (appr_doc_id)
) comment '要員（外注BP）連絡先' ENGINE=InnoDB DEFAULT CHARSET=utf8 COLLATE=utf8_bin;

-- 売上/粗利・明細
drop table if exists apprSalesGrossProfitDtl cascade;

create table apprSalesGrossProfitDtl (
  appr_doc_id INT not null comment '稟議書ID'
  , sales_addup_month INT not null comment '売上販売計上月:1～12
売上販売の計上月
（請求対象月ではない）'
  , work_days INT not null comment '当月稼働日:1～31'
  , billing_month INT not null comment '請求計上月:1～12'
  , sell_price DECIMAL(10,1) not null comment '上位単金/単価'
  , business_days INT not null comment '当月営業日:1～31'
  , cost_price DECIMAL(10,1) not null comment '要員渡し/原価'
  , sell_price_tax_k CHAR(1) not null comment '単価消費税区分:1：税込
2：税抜'
  , cost_price_tax_k CHAR(1) not null comment '原価消費税区分:1：税込
2：税抜'
  , travel_cost_first_mon DECIMAL(10,1) not null comment '交通費(初月)'
  , other_cost_first_mon DECIMAL(10,1) not null comment 'その他(初月)'
  , travel_cost DECIMAL(10,1) not null comment '交通費'
  , insurance_cost DECIMAL(10,1) comment '社保費'
  , other_cost DECIMAL(10,1) not null comment 'その他'
  , insurance_k CHAR(1) not null comment '社保区分:1:（業務委託契約のため、対象外）
2：(入社2ヶ月後加入)
3：(入社翌月から加入）
4：(入社日から加入）'
  , insurance_join_y CHAR(4) comment '社保加入年:yyyy'
  , insurance_join_ym CHAR(2) comment '社保加入月:mm'
  , sys_ins_user_id VARCHAR(10) not null comment '登録者'
  , sys_ins_dt DATETIME not null comment '登録日時'
  , sys_ins_pg_id VARCHAR(15) not null comment '登録機能ID'
  , sys_upd_user_id VARCHAR(10) comment '更新者'
  , sys_upd_dt DATETIME comment '更新日時'
  , sys_upd_pg_id VARCHAR(15) comment '更新機能ID'
  , constraint apprSalesGrossProfitDtl_PKC primary key (appr_doc_id)
) comment '売上/粗利・明細' ENGINE=InnoDB DEFAULT CHARSET=utf8 COLLATE=utf8_bin;

-- クライアント情報
drop table if exists apprClient cascade;

create table apprClient (
  appr_doc_id INT not null comment '稟議書ID'
  , client_no VARCHAR(10) not null comment 'クライアント番号'
  , client_nm VARCHAR(50) not null comment 'クライアント名'
  , client_url VARCHAR(100) comment 'クライアントURL'
  , client_my_number VARCHAR(20) comment 'クライアント法人マイナンバー'
  , client_post_no VARCHAR(10) comment 'クライアント郵便番号'
  , client_addr VARCHAR(200) comment 'クライアント連絡先住所'
  , client_tel_no VARCHAR(15) comment 'クライアント電話番号:XX-XXXX-XXXX'
  , client_fax_no VARCHAR(15) comment 'クライアントFAX番号:XX-XXXX-XXXX'
  , client_staff_nm VARCHAR(30) comment 'クライアント担当者名'
  , client_staff_nm_fuli VARCHAR(30) comment 'クライアント担当者名（フリガナ）'
  , client_staff_dept_nm VARCHAR(50) comment 'クライアント担当者所属部署'
  , client_staff_position VARCHAR(50) comment 'クライアント担当者役職'
  , client_staff_mobile_no VARCHAR(20) comment 'クライアント担当者携帯番号'
  , client_staff_mobile_mail_addr VARCHAR(30) comment 'クライアント担当者メールアドレス'
  , client_mail_addr VARCHAR(100) comment 'クライアントメール'
  , client_mail_addr_cc VARCHAR(100) comment 'クライアントメールCC'
  , client_req_staff_nm VARCHAR(30) comment '請求担当者名'
  , client_req_staff_nm_fuli VARCHAR(30) comment '請求担当者名（フリガナ）'
  , client_req_staff_dept_nm VARCHAR(50) comment '請求担当者所属部署'
  , client_req_staff_position VARCHAR(50) comment '請求担当者役職'
  , client_req_tel_no VARCHAR(15) comment '請求担当者電話番号:XX-XXXX-XXXX'
  , client_req_fax_no VARCHAR(15) comment '請求担当者FAX番号:XX-XXXX-XXXX'
  , client_req_staff_mobile_no VARCHAR(20) comment '請求担当者携帯番号'
  , client_req_mail_addr VARCHAR(100) comment '請求担当者メール'
  , client_req_mail_addr_cc VARCHAR(100) comment '請求担当者メールCC'
  , client_order_no VARCHAR(50) comment '受注（注文）書No'
  , sys_ins_user_id VARCHAR(10) not null comment '登録者'
  , sys_ins_dt DATETIME not null comment '登録日時'
  , sys_ins_pg_id VARCHAR(15) not null comment '登録機能ID'
  , sys_upd_user_id VARCHAR(10) comment '更新者'
  , sys_upd_dt DATETIME comment '更新日時'
  , sys_upd_pg_id VARCHAR(15) comment '更新機能ID'
  , constraint apprClient_PKC primary key (appr_doc_id)
) comment 'クライアント情報' ENGINE=InnoDB DEFAULT CHARSET=utf8 COLLATE=utf8_bin;

-- 要員入場者
drop table if exists apprEmp cascade;

create table apprEmp (
  appr_doc_id INT not null comment '稟議書ID'
  , emp_no VARCHAR(10) not null comment '社員番号'
  , emp_nm VARCHAR(30) not null comment '社員名'
  , emp_nm_fuli VARCHAR(30) not null comment '社員名（フリガナ）'
  , emp_sex CHAR(1) not null comment '性別:M：男
F：女'
  , emp_birthday CHAR(1) not null comment '生年月日'
  , entrance_dt DATE not null comment '入社日'
  , emp_k CHAR(1) not null comment '要員区分:1：新規要員
2：既存要員
3：要員退社'
  , emp_my_number VARCHAR(20) comment '要員マイナンバー'
  , emp_pat_k CHAR(1) not null comment '希望雇用形態:1：正社員
2：契約社員
3：個人事業主
4：外注
9：その他'
  , emp_contract_k CHAR(1) not null comment '要員契約区分:1：準委任契約
2：派遣契約
3：雇用契約'
  , affiliation_k CHAR(1) not null comment '所属区分:1：自社プロパー
2：BP社'
  , emp_other_k CHAR(1) comment 'その他区分:1：準委任契約
2：派遣契約
3：雇用契約'
  , bp_contract_k CHAR(1) comment 'BP契約区分:1：準委任契約
2：派遣契約
3：雇用契約'
  , entrance_record_k CHAR(1) not null comment '入社経歴:1：有
2：無'
  , old_appr_doc_no VARCHAR(10) comment '前回稟議書NO'
  , insurance_k CHAR(1) comment '保険加入:1：雇用保険のみ
2：社保険完備
3：保険無し'
  , take_over_k CHAR(1) comment '引取区分:1：新規取引先
2：既存取引先'
  , contract_form_k CHAR(1) comment '契約形態:1：委任（ＳＥＳ）
2：派遣契約'
  , order_recv_hope_dt DATE comment '注文書受領日（見込）'
  , from_home_station_nm VARCHAR(100) comment '最寄駅（自宅）'
  , client_contract_k CHAR(1) comment '契約（上位）:1：委任契約
2：派遣契約'
  , sys_ins_user_id VARCHAR(10) not null comment '登録者'
  , sys_ins_dt DATETIME not null comment '登録日時'
  , sys_ins_pg_id VARCHAR(15) not null comment '登録機能ID'
  , sys_upd_user_id VARCHAR(10) comment '更新者'
  , sys_upd_dt DATETIME comment '更新日時'
  , sys_upd_pg_id VARCHAR(15) comment '更新機能ID'
  , constraint apprEmp_PKC primary key (appr_doc_id)
) comment '要員入場者' ENGINE=InnoDB DEFAULT CHARSET=utf8 COLLATE=utf8_bin;

-- 稟議書
drop table if exists apprDoc cascade;

create table apprDoc (
  appr_doc_id INT not null AUTO_INCREMENT comment '稟議書ID'
  , appr_doc_no VARCHAR(10) not null comment '稟議書NO'
  , appr_doc_dt_from DATE not null comment '対象期間From'
  , appr_doc_dt_to DATE not null comment '対象期間To'
  , appr_doc_create_k CHAR(1) not null comment '稟議書作成区分:1：通常更新
2：新規
3：条件変更
4：正社員化
5：退職
6：再販'
  , appr_doc_create_dt DATE not null comment '稟議書作成日'
  , appr_doc_st CHAR(1) not null comment '稟議書状態:1：申請待    稟議書データ登録直後
2：承認待    稟議書データ承認WFを申請後　※申請後、承認中前、申請取消が可能
3：承認中    稟議書承認WFがスタート後
4：承認済    承認経路の承認者がすべて承認済、
5：契約済    受注先と合意済の後
A:取消     ユーザー取消（画面上で検索可能）'
  , business_main_user_id VARCHAR(10) comment '営業主担当'
  , business_sub_user_id VARCHAR(10) comment '副担当'
  , use_flg CHAR(1) default 'Y' not null comment '使用FLG:Y:使用
N:削除'
  , business_remark VARCHAR(500) comment '営業コメント'
  , purchase_remark VARCHAR(500) comment '購買コメント'
  , other_remark VARCHAR(500) comment 'その他コメント'
  , sys_ins_user_id VARCHAR(10) not null comment '登録者'
  , sys_ins_dt DATETIME not null comment '登録日時'
  , sys_ins_pg_id VARCHAR(15) not null comment '登録機能ID'
  , sys_upd_user_id VARCHAR(10) comment '更新者'
  , sys_upd_dt DATETIME comment '更新日時'
  , sys_upd_pg_id VARCHAR(15) comment '更新機能ID'
  , constraint apprDoc_PKC primary key (appr_doc_id)
) comment '稟議書' ENGINE=InnoDB DEFAULT CHARSET=utf8 COLLATE=utf8_bin;

create unique index apprDoc_i_alt1
  on apprDoc(appr_doc_no);


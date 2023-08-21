INSERT INTO webedi.program (pg_id,pg_nm,pg_seq,use_flg,pg_remark,pg_path,pg_component,pg_icon,pg_redirect,pg_props,menu_display_flg,sys_ins_user_id,sys_ins_dt,sys_ins_pg_id,sys_upd_user_id,sys_upd_dt,sys_upd_pg_id) VALUES 
('ApprDocConfirm','稟議書承認',50,'Y','稟議書承認','ApprDocConfirm','ApprDocConfirm','zonghe','','','Y','jin-qk','2018-02-28 20:52:00.000','hand','',NULL,'')
,('ApprDocDetail','稟議書詳細',30,'Y','稟議書詳細','ApprDocDetail','ApprDocDetail','zonghe','','','N','jin-qk','2018-02-28 20:52:00.000','hand','',NULL,'')
,('ApprDocDownLoad','データ作成',60,'Y','稟議書データ作成','ApprDocDownLoad','ApprDocDownLoad','zonghe','','','Y','jin-qk','2018-02-28 20:52:00.000','hand','',NULL,'')
,('ApprDocEnt','稟議書登録',10,'Y','稟議書登録','ApprDocEnt','ApprDocEnt','zonghe','','','Y','jin-qk','2018-02-28 20:52:00.000','hand','',NULL,'')
,('ApprDocInq','稟議書照会',20,'Y','稟議書照会','ApprDocInq','ApprDocInq','zonghe','','','Y','jin-qk','2018-02-28 20:52:00.000','hand','',NULL,'')
,('ApprDocUpd','稟議書更新',40,'Y','稟議書更新','ApprDocUpd','ApprDocUpd','zonghe','','','N','jin-qk','2018-02-28 20:52:00.000','hand','',NULL,'')
,('EstDetail','見積詳細',140,'Y','見積詳細','EstDetail','EstDetail','zonghe','','','N','jin-qk','2018-03-01 20:21:00.000','hand','',NULL,'')
,('EstEnt','見積登録',120,'Y','見積登録','EstEnt','EstEnt','zonghe','','','N','jin-qk','2018-03-01 20:21:00.000','hand','',NULL,'')
,('EstEntInq','見積登録検索一覧',110,'Y','見積登録検索一覧','EstEntInq','EstEntInq','zonghe','','','Y','jin-qk','2018-03-01 20:21:00.000','hand','',NULL,'')
,('EstInq','見積検索一覧',130,'Y','見積検索一覧','EstInq','EstInq','zonghe','','','Y','jin-qk','2018-03-01 20:21:00.000','hand','',NULL,'')
;
INSERT INTO webedi.program (pg_id,pg_nm,pg_seq,use_flg,pg_remark,pg_path,pg_component,pg_icon,pg_redirect,pg_props,menu_display_flg,sys_ins_user_id,sys_ins_dt,sys_ins_pg_id,sys_upd_user_id,sys_upd_dt,sys_upd_pg_id) VALUES 
('EstUpd','見積編集',150,'Y','見積編集','EstUpd','EstUpd','zonghe','','','N','jin-qk','2018-03-01 20:21:00.000','hand','',NULL,'')
,('OrderConfirm','受注確定・取消',220,'Y','受注確定・取消','OrderConfirm','OrderConfirm','zonghe','','','Y','jin-qk','2018-03-01 20:25:00.000','hand','',NULL,'')
,('OrderDetail','受注詳細',200,'Y','受注詳細','OrderDetail','OrderDetail','zonghe','','','N','jin-qk','2018-03-01 20:25:00.000','hand','',NULL,'')
,('OrderEnt','受注登録',180,'Y','受注登録','OrderEnt','OrderEnt','zonghe','','','N','jin-qk','2018-03-01 20:25:00.000','hand','',NULL,'')
,('OrderEntInq','受注登録検索一覧',170,'Y','受注登録検索一覧','OrderEntInq','OrderEntInq','zonghe','','','Y','jin-qk','2018-03-01 20:25:00.000','hand','',NULL,'')
,('OrderInq','受注検索一覧',190,'Y','受注検索一覧','OrderInq','OrderInq','zonghe','','','Y','jin-qk','2018-03-01 20:25:00.000','hand','',NULL,'')
,('OrderUpd','受注編集',210,'Y','受注編集','OrderUpd','OrderUpd','zonghe','','','N','jin-qk','2018-03-01 20:25:00.000','hand','',NULL,'')
,('ResetPassword','パスワードリセット',100,'Y','パスワードリセット','ResetPassword','ResetPassword','','','','Y','jin-qk','2018-02-28 20:52:00.000','hand','',NULL,'')
,('RoleDetail','ロール詳細',250,'Y','ロール詳細','RoleDetail','RoleDetail','','','','N','jin-qk','2018-03-01 20:25:00.000','hand','',NULL,'')
,('RoleEnt','ロール登録',230,'Y','ロール登録','RoleEnt','RoleEnt','','','','Y','jin-qk','2018-03-01 20:25:00.000','hand','',NULL,'')
;
INSERT INTO webedi.program (pg_id,pg_nm,pg_seq,use_flg,pg_remark,pg_path,pg_component,pg_icon,pg_redirect,pg_props,menu_display_flg,sys_ins_user_id,sys_ins_dt,sys_ins_pg_id,sys_upd_user_id,sys_upd_dt,sys_upd_pg_id) VALUES 
('RoleInq','ロール検索',240,'Y','ロール検索','RoleInq','RoleInq','','','','Y','jin-qk','2018-03-01 20:25:00.000','hand','',NULL,'')
,('RoleUpd','ロール更新',260,'Y','ロール更新','RoleUpd','RoleUpd','','','','N','jin-qk','2018-03-01 20:25:00.000','hand','',NULL,'')
,('UserEnt','ユーザー登録',70,'Y','ユーザー登録','UserEnt','UserEnt','','','','Y','jin-qk','2018-02-28 20:52:00.000','hand','',NULL,'')
,('UserInq','ユーザー照会',80,'Y','ユーザー照会','UserInq','UserInq','','','','Y','jin-qk','2018-02-28 20:52:00.000','hand','',NULL,'')
,('UserPassword','パスワード設定',90,'Y','パスワード設定','UserPassword','UserPassword','','','','Y','jin-qk','2018-02-28 20:52:00.000','hand','',NULL,'')
;
drop table if exists calendar cascade;

--* RestoreFromTempTable
create table calendar (
  calendar_id INT not null AUTO_INCREMENT comment 'カレンダーID'
  , ym DATE not null comment '年月'
  , ymd DATE not null comment '年月日'
  , calendar_k CHAR(1) not null comment 'カレンダー区分:1:稼働日
2:週末
3:祝日'
  , calendar_remark VARCHAR(200) comment 'カレンダー備考'
  , sys_ins_user_id VARCHAR(10) not null comment '登録者'
  , sys_ins_dt DATETIME not null comment '登録日時'
  , sys_ins_pg_id VARCHAR(15) not null comment '登録機能ID'
  , sys_upd_user_id VARCHAR(10) comment '更新者'
  , sys_upd_dt DATETIME comment '更新日時'
  , sys_upd_pg_id VARCHAR(15) comment '更新機能ID'
  , constraint calendar_PKC primary key (calendar_id)
) comment 'カレンダー' ENGINE=InnoDB DEFAULT CHARSET=utf8 COLLATE=utf8_bin;

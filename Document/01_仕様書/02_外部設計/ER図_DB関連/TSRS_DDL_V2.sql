drop table if exists calendar cascade;

--* RestoreFromTempTable
create table calendar (
  calendar_id INT not null AUTO_INCREMENT comment '�������`ID'
  , ym DATE not null comment '����'
  , ymd DATE not null comment '������'
  , calendar_k CHAR(1) not null comment '�������`����:1:�ڃP��
2:�Lĩ
3:ף��'
  , calendar_remark VARCHAR(200) comment '�������`�俼'
  , sys_ins_user_id VARCHAR(10) not null comment '���h��'
  , sys_ins_dt DATETIME not null comment '���h�Օr'
  , sys_ins_pg_id VARCHAR(15) not null comment '���h�C��ID'
  , sys_upd_user_id VARCHAR(10) comment '������'
  , sys_upd_dt DATETIME comment '�����Օr'
  , sys_upd_pg_id VARCHAR(15) comment '���C��ID'
  , constraint calendar_PKC primary key (calendar_id)
) comment '�������`' ENGINE=InnoDB DEFAULT CHARSET=utf8 COLLATE=utf8_bin;

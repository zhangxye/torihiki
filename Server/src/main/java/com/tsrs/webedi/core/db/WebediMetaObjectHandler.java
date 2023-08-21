package com.tsrs.webedi.core.db;

import java.sql.Timestamp;
import org.apache.ibatis.reflection.MetaObject;
import org.springframework.stereotype.Component;
import com.baomidou.mybatisplus.core.handlers.MetaObjectHandler;
import com.tsrs.webedi.common.constant.Const;
import com.tsrs.webedi.core.shiro.ShiroKit;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@Component
public class WebediMetaObjectHandler implements MetaObjectHandler {

	@Override
	public void insertFill(MetaObject metaObject) {
		String user_id = ShiroKit.getUser().getAccount();
		Timestamp dt = new Timestamp(System.currentTimeMillis());
		//登録者
		setFieldVal(metaObject, "sysInsUserId", user_id);
		//登録日時
		setFieldVal(metaObject, "sysInsDt", dt);
		//登録機能ID
		setFieldVal(metaObject, "sysInsPgId", Const.PG.DEF_PG);

		//更新者
		setFieldVal(metaObject, "sysUpdUserId", user_id);
		//更新日時
		setFieldVal(metaObject, "sysUpdDt", dt);
		//更新機能ID
		setFieldVal(metaObject, "sysUpdPgId", Const.PG.DEF_PG);
	}

	@Override
	public void updateFill(MetaObject metaObject) {
		String user_id = ShiroKit.getUser().getAccount();
		Timestamp dt = new Timestamp(System.currentTimeMillis());
		//更新者
		setFieldVal(metaObject, "sysUpdUserId", user_id);
		//更新日時
		setFieldVal(metaObject, "sysUpdDt", dt);
		//更新機能ID
		setFieldVal(metaObject, "sysUpdPgId", Const.PG.DEF_PG);

	}

	private void setFieldVal(MetaObject metaObject, String field, Object defVal) {
		
		Object val = getFieldValByName(field, metaObject);
		if (val == null) {
			setFieldValByName(field,defVal, metaObject);
		}
	}

}

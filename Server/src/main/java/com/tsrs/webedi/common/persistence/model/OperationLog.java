package com.tsrs.webedi.common.persistence.model;

import com.alibaba.fastjson.annotation.JSONField;
import com.baomidou.mybatisplus.annotation.IdType;
import java.util.Date;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.extension.activerecord.Model;
import com.baomidou.mybatisplus.annotation.TableName;
import java.io.Serializable;

/**
 * <p>
 * おべログ
 * </p>
 *
 * @author tsrs
 * @since 2017-09-03
 */
@TableName("operation_log")
public class OperationLog extends Model<OperationLog> {

    private static final long serialVersionUID = 1L;

    /**
     * キー
     */
	@TableId(value="id", type= IdType.AUTO)
	private Integer id;
    /**
     * ログタイプ
     */
	private String logtype;
    /**
     * ログ名称
     */
	private String logname;
    /**
     * ユーザーid
     */
	private Integer userid;
    /**
     * クラス名称
     */
	private String classname;
    /**
     * メソッド名称
     */
	private String method;
    /**
     * 作成時間
     */
    @JSONField(format = "yyyy-MM-dd HH:mm:ss")
	private Date createtime;
    /**
     * 成功
     */
	private String succeed;
    /**
     * 備考
     */
	private String message;
   

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public String getLogtype() {
		return logtype;
	}

	public void setLogtype(String logtype) {
		this.logtype = logtype;
	}

	public String getLogname() {
		return logname;
	}

	public void setLogname(String logname) {
		this.logname = logname;
	}

	public Integer getUserid() {
		return userid;
	}

	public void setUserid(Integer userid) {
		this.userid = userid;
	}

	public String getClassname() {
		return classname;
	}

	public void setClassname(String classname) {
		this.classname = classname;
	}

	public String getMethod() {
		return method;
	}

	public void setMethod(String method) {
		this.method = method;
	}

	public Date getCreatetime() {
		return createtime;
	}

	public void setCreatetime(Date createtime) {
		this.createtime = createtime;
	}

	public String getSucceed() {
		return succeed;
	}

	public void setSucceed(String succeed) {
		this.succeed = succeed;
	}

	public String getMessage() {
		return message;
	}

	public void setMessage(String message) {
		this.message = message;
	}

	
	@Override
	public Serializable pkVal() {
		return this.id;
	}

}

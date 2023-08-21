package com.tsrs.webedi.common.persistence.model;

import java.io.Serializable;
import java.util.Date;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import com.baomidou.mybatisplus.extension.activerecord.Model;

/**
 * <p>
 * 登録ログ
 * </p>
 *
 * @author tsrs
 * @since 2017-09-03
 */
@TableName("login_log")
public class LoginLog extends Model<LoginLog> {

    private static final long serialVersionUID = 1L;

    /**
     * キー
     */
	@TableId(value="id", type= IdType.AUTO)
	private Integer id;
    /**
     * ログ名称
     */
	private String logname;
    /**
     * 管理者id
     */
	private Integer userid;
    /**
     * 作成時間
     */
	private Date createtime;
    /**
     * 実行成功
     */
	private String succeed;
    /**
     * 詳細情報
     */
	private String message;
    /**
     * 登録ip
     */
	private String ip;
   


	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
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

	public String getIp() {
		return ip;
	}

	public void setIp(String ip) {
		this.ip = ip;
	}

	@Override
	public Serializable pkVal() {
		return this.id;
	}

}

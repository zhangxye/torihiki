package com.tsrs.webedi.common.persistence.model;

import java.io.Serializable;

import java.util.Date;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.extension.activerecord.Model;

/**
 * <p>
 * 機能情報
 * </p>
 *
 * @author tsrs
 * @since 2018-03-02
 */
public class Program extends Model<Program> {

    private static final long serialVersionUID = 1L;

    /**
     * 機能ID
     */
    @TableId("pg_id")
	private String pgId;
    /**
     * 機能名
     */
	@TableField("pg_nm")
	private String pgNm;
    /**
     * 使用FLG:Y:使用
N:削除
     */
	@TableField("use_flg")
	private String useFlg;
    /**
     * 備考
     */
	@TableField("pg_remark")
	private String pgRemark;
    /**
     * PATH
     */
	@TableField("pg_path")
	private String pgPath;
    /**
     * COMPONENT
     */
	@TableField("pg_componet")
	private String pgComponet;
    /**
     * ICON
     */
	@TableField("pg_icon")
	private String pgIcon;
    /**
     * REDIRECT
     */
	@TableField("pg_redirect")
	private String pgRedirect;
    /**
     * PROPS
     */
	@TableField("pg_props")
	private String pgProps;
    /**
     * メニュー表示FLG:Y:メニュー表示する
N：メニューに表示しない
     */
	@TableField("menu_display_flg")
	private String menuDisplayFlg;
    /**
     * 登録者
     */
	@TableField("sys_ins_user_id")
	private String sysInsUserId;
    /**
     * 登録日時
     */
	@TableField("sys_ins_dt")
	private Date sysInsDt;
    /**
     * 登録機能ID
     */
	@TableField("sys_ins_pg_id")
	private String sysInsPgId;
    /**
     * 更新者
     */
	@TableField("sys_upd_user_id")
	private String sysUpdUserId;
    /**
     * 更新日時
     */
	@TableField("sys_upd_dt")
	private Date sysUpdDt;
    /**
     * 更新機能ID
     */
	@TableField("sys_upd_pg_id")
	private String sysUpdPgId;


	public String getPgId() {
		return pgId;
	}

	public void setPgId(String pgId) {
		this.pgId = pgId;
	}

	public String getPgNm() {
		return pgNm;
	}

	public void setPgNm(String pgNm) {
		this.pgNm = pgNm;
	}

	public String getUseFlg() {
		return useFlg;
	}

	public void setUseFlg(String useFlg) {
		this.useFlg = useFlg;
	}

	public String getPgRemark() {
		return pgRemark;
	}

	public void setPgRemark(String pgRemark) {
		this.pgRemark = pgRemark;
	}

	public String getPgPath() {
		return pgPath;
	}

	public void setPgPath(String pgPath) {
		this.pgPath = pgPath;
	}

	public String getPgComponet() {
		return pgComponet;
	}

	public void setPgComponet(String pgComponet) {
		this.pgComponet = pgComponet;
	}

	public String getPgIcon() {
		return pgIcon;
	}

	public void setPgIcon(String pgIcon) {
		this.pgIcon = pgIcon;
	}

	public String getPgRedirect() {
		return pgRedirect;
	}

	public void setPgRedirect(String pgRedirect) {
		this.pgRedirect = pgRedirect;
	}

	public String getPgProps() {
		return pgProps;
	}

	public void setPgProps(String pgProps) {
		this.pgProps = pgProps;
	}

	public String getMenuDisplayFlg() {
		return menuDisplayFlg;
	}

	public void setMenuDisplayFlg(String menuDisplayFlg) {
		this.menuDisplayFlg = menuDisplayFlg;
	}

	public String getSysInsUserId() {
		return sysInsUserId;
	}

	public void setSysInsUserId(String sysInsUserId) {
		this.sysInsUserId = sysInsUserId;
	}

	public Date getSysInsDt() {
		return sysInsDt;
	}

	public void setSysInsDt(Date sysInsDt) {
		this.sysInsDt = sysInsDt;
	}

	public String getSysInsPgId() {
		return sysInsPgId;
	}

	public void setSysInsPgId(String sysInsPgId) {
		this.sysInsPgId = sysInsPgId;
	}

	public String getSysUpdUserId() {
		return sysUpdUserId;
	}

	public void setSysUpdUserId(String sysUpdUserId) {
		this.sysUpdUserId = sysUpdUserId;
	}

	public Date getSysUpdDt() {
		return sysUpdDt;
	}

	public void setSysUpdDt(Date sysUpdDt) {
		this.sysUpdDt = sysUpdDt;
	}

	public String getSysUpdPgId() {
		return sysUpdPgId;
	}

	public void setSysUpdPgId(String sysUpdPgId) {
		this.sysUpdPgId = sysUpdPgId;
	}

	@Override
	public Serializable pkVal() {
		return this.pgId;
	}

	@Override
	public String toString() {
		return "Program{" +
			"pgId=" + pgId +
			", pgNm=" + pgNm +
			", useFlg=" + useFlg +
			", pgRemark=" + pgRemark +
			", pgPath=" + pgPath +
			", pgComponet=" + pgComponet +
			", pgIcon=" + pgIcon +
			", pgRedirect=" + pgRedirect +
			", pgProps=" + pgProps +
			", menuDisplayFlg=" + menuDisplayFlg +
			", sysInsUserId=" + sysInsUserId +
			", sysInsDt=" + sysInsDt +
			", sysInsPgId=" + sysInsPgId +
			", sysUpdUserId=" + sysUpdUserId +
			", sysUpdDt=" + sysUpdDt +
			", sysUpdPgId=" + sysUpdPgId +
			"}";
	}
}

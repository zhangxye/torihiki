package com.tsrs.webedi.common.persistence.model;

import java.io.Serializable;
import java.util.Date;

import com.baomidou.mybatisplus.annotation.FieldFill;
import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.extension.activerecord.Model;

/**
 * <p>
 * ファイル管理
 * </p>
 *
 * @author tsrs
 * @since 2017-09-01
 */
public class Sysfilemgr extends Model<Sysfilemgr> {

	private static final long serialVersionUID = 1L;

	/**
	 * ファイルID
	 */
	@TableId(value = "file_id", type = IdType.AUTO)
	private Integer fileId;
	/**
	 * 拡張子
	 */
	@TableField("file_ext")
	private String fileExt;
	/**
	 * MimeType
	 */
	@TableField("mime_type")
	private String mimeType;
	/**
	 * オリジナルファイル名
	 */
	@TableField("file_nm")
	private String fileNm;
	/**
	 * 保存相対パス
	 */
	@TableField("file_path")
	private String filePath;
	/**
	 * ファイルサイズ
	 */
	@TableField("file_size")
	private Integer fileSize;
	/**
	 * 保存ユーザ
	 */
	@TableField("file_user_id")
	private String fileUserId;
	/**
	 * 保存日時
	 */
	@TableField("file_ymd")
	private Date fileYmd;
	/**
	 * 使用FLG:Y:使用 N:削除
	 */
	@TableField("use_flg")
	private String useFlg;
	/**
	 * 登録者
	 */
	@TableField(value = "sys_ins_user_id", fill = FieldFill.INSERT)
	private String sysInsUserId;
	/**
	 * 登録日時
	 */
	@TableField(value = "sys_ins_dt", fill = FieldFill.INSERT)
	private Date sysInsDt;
	/**
	 * 登録機能ID
	 */
	@TableField(value = "sys_ins_pg_id", fill = FieldFill.INSERT)
	private String sysInsPgId;
	/**
	 * 更新者
	 */
	@TableField(value = "sys_upd_user_id", fill = FieldFill.INSERT_UPDATE)
	private String sysUpdUserId;
	/**
	 * 更新日時
	 */
	@TableField(value = "sys_upd_dt", fill = FieldFill.INSERT_UPDATE)
	private Date sysUpdDt;
	/**
	 * 更新機能ID
	 */
	@TableField(value = "sys_upd_pg_id", fill = FieldFill.INSERT_UPDATE)
	private String sysUpdPgId;

	public Integer getFileId() {
		return fileId;
	}

	public void setFileId(Integer fileId) {
		this.fileId = fileId;
	}

	public String getFileExt() {
		return fileExt;
	}

	public void setFileExt(String fileExt) {
		this.fileExt = fileExt;
	}

	public String getMimeType() {
		return mimeType;
	}

	public void setMimeType(String mimeType) {
		this.mimeType = mimeType;
	}

	public String getFileNm() {
		return fileNm;
	}

	public void setFileNm(String fileNm) {
		this.fileNm = fileNm;
	}

	public String getFilePath() {
		return filePath;
	}

	public void setFilePath(String filePath) {
		this.filePath = filePath;
	}

	public Integer getFileSize() {
		return fileSize;
	}

	public void setFileSize(Integer fileSize) {
		this.fileSize = fileSize;
	}

	public String getFileUserId() {
		return fileUserId;
	}

	public void setFileUserId(String fileUserId) {
		this.fileUserId = fileUserId;
	}

	public Date getFileYmd() {
		return fileYmd;
	}

	public void setFileYmd(Date fileYmd) {
		this.fileYmd = fileYmd;
	}

	public String getUseFlg() {
		return useFlg;
	}

	public void setUseFlg(String useFlg) {
		this.useFlg = useFlg;
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
		return this.fileId;
	}

}

package com.tsrs.webedi.modular.system.persistence.model;

import java.io.Serializable;

import java.util.Date;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.FieldFill;
import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.extension.activerecord.Model;
import java.io.Serializable;

/**
 * <p>
 * 受注確定情報
 * </p>
 *
 * @author jin-qk
 * @since 2018-03-26
 */
public class Orderconfirm extends Model<Orderconfirm> {

    private static final long serialVersionUID = 1L;

    /**
     * 受注HID
     */
    @TableId(value="order_id",type=IdType.INPUT)
	private Integer orderId;
    /**
     * 受注確認者ID
     */
	@TableField("order_confirm_user_id")
	private String orderConfirmUserId;
    /**
     * 受注確認日時
     */
	@TableField("order_confirm_dt")
	private Date orderConfirmDt;
    /**
     * 受注確認備考
     */
	@TableField("order_confirm_remark")
	private String orderConfirmRemark;
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


	public Integer getOrderId() {
		return orderId;
	}

	public void setOrderId(Integer orderId) {
		this.orderId = orderId;
	}

	public String getOrderConfirmUserId() {
		return orderConfirmUserId;
	}

	public void setOrderConfirmUserId(String orderConfirmUserId) {
		this.orderConfirmUserId = orderConfirmUserId;
	}

	public Date getOrderConfirmDt() {
		return orderConfirmDt;
	}

	public void setOrderConfirmDt(Date orderConfirmDt) {
		this.orderConfirmDt = orderConfirmDt;
	}

	public String getOrderConfirmRemark() {
		return orderConfirmRemark;
	}

	public void setOrderConfirmRemark(String orderConfirmRemark) {
		this.orderConfirmRemark = orderConfirmRemark;
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
		return this.orderId;
	}

	@Override
	public String toString() {
		return "Orderconfirm{" +
			"orderId=" + orderId +
			", orderConfirmUserId=" + orderConfirmUserId +
			", orderConfirmDt=" + orderConfirmDt +
			", orderConfirmRemark=" + orderConfirmRemark +
			", sysInsUserId=" + sysInsUserId +
			", sysInsDt=" + sysInsDt +
			", sysInsPgId=" + sysInsPgId +
			", sysUpdUserId=" + sysUpdUserId +
			", sysUpdDt=" + sysUpdDt +
			", sysUpdPgId=" + sysUpdPgId +
			"}";
	}
}

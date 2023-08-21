package com.tsrs.webedi.modular.system.persistence.model;

import com.baomidou.mybatisplus.annotation.IdType;
import java.math.BigDecimal;
import java.util.Date;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.FieldFill;
import com.baomidou.mybatisplus.extension.activerecord.Model;
import com.baomidou.mybatisplus.annotation.TableLogic;
import com.baomidou.mybatisplus.annotation.TableName;

import java.io.Serializable;

/**
 * <p>
 * 受注HDR情報
 * </p>
 *
 * @author jin-qk
 * @since 2018-04-19
 */
@TableName("`order`")
public class Order extends Model<Order> {

    private static final long serialVersionUID = 1L;

    /**
     * 受注ID
     */
    @TableId(value = "order_id", type = IdType.AUTO)
    private Integer orderId;
    /**
     * 受注NO
     */
    @TableField("order_no")
    private String orderNo;
    /**
     * 使用FLG:Y:使用
N:削除
     */
    @TableField("use_flg")
    @TableLogic
    private String useFlg;
    /**
     * 作成日
     */
    @TableField("create_dt")
    private Date createDt;
    /**
     * クライアント名
     */
    @TableField("client_nm")
    private String clientNm;
    /**
     * 受注金額(税抜)
     */
    @TableField("order_amt_without_tax")
    private BigDecimal orderAmtWithoutTax;
    /**
     * 作業開始日
     */
    @TableField("work_start_ymd")
    private Date workStartYmd;
    /**
     * 作業終了日
     */
    @TableField("work_end_ymd")
    private Date workEndYmd;
    /**
     * 案件番号
     */
    @TableField("pj_cd")
    private String pjCd;
    /**
     * 案件名称
     */
    @TableField("pj_nm")
    private String pjNm;
    /**
     * 作業内容
     */
    @TableField("work_content")
    private String workContent;
    /**
     * 作業場所
     */
    @TableField("work_place")
    private String workPlace;
    /**
     * 注文内容
     */
    @TableField("order_content")
    private String orderContent;
    /**
     * 特記事項
     */
    @TableField("special_affairs")
    private String specialAffairs;
    /**
     * お支払条件
     */
    @TableField("payment_cond")
    private String paymentCond;
    /**
     * 備考
     */
    private String remark;
    /**
     * 注文書
     */
    @TableField("order_doc_file_id")
    private Integer orderDocFileId;
    /**
     * 見積HID
     */
    @TableField("est_hid")
    private Integer estHid;
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
    /**
     * 受注金額(税込)
     */
    @TableField("order_amt_with_tax")
    private BigDecimal orderAmtWithTax;


    public Integer getOrderId() {
        return orderId;
    }

    public void setOrderId(Integer orderId) {
        this.orderId = orderId;
    }

    public String getOrderNo() {
        return orderNo;
    }

    public void setOrderNo(String orderNo) {
        this.orderNo = orderNo;
    }

    public String getUseFlg() {
        return useFlg;
    }

    public void setUseFlg(String useFlg) {
        this.useFlg = useFlg;
    }

    public Date getCreateDt() {
        return createDt;
    }

    public void setCreateDt(Date createDt) {
        this.createDt = createDt;
    }

    public String getClientNm() {
        return clientNm;
    }

    public void setClientNm(String clientNm) {
        this.clientNm = clientNm;
    }

    public BigDecimal getOrderAmtWithoutTax() {
        return orderAmtWithoutTax;
    }

    public void setOrderAmtWithoutTax(BigDecimal orderAmtWithoutTax) {
        this.orderAmtWithoutTax = orderAmtWithoutTax;
    }

    public Date getWorkStartYmd() {
        return workStartYmd;
    }

    public void setWorkStartYmd(Date workStartYmd) {
        this.workStartYmd = workStartYmd;
    }

    public Date getWorkEndYmd() {
        return workEndYmd;
    }

    public void setWorkEndYmd(Date workEndYmd) {
        this.workEndYmd = workEndYmd;
    }

    public String getPjCd() {
        return pjCd;
    }

    public void setPjCd(String pjCd) {
        this.pjCd = pjCd;
    }

    public String getPjNm() {
        return pjNm;
    }

    public void setPjNm(String pjNm) {
        this.pjNm = pjNm;
    }

    public String getWorkContent() {
        return workContent;
    }

    public void setWorkContent(String workContent) {
        this.workContent = workContent;
    }

    public String getWorkPlace() {
        return workPlace;
    }

    public void setWorkPlace(String workPlace) {
        this.workPlace = workPlace;
    }

    public String getOrderContent() {
        return orderContent;
    }

    public void setOrderContent(String orderContent) {
        this.orderContent = orderContent;
    }

    public String getSpecialAffairs() {
        return specialAffairs;
    }

    public void setSpecialAffairs(String specialAffairs) {
        this.specialAffairs = specialAffairs;
    }

    public String getPaymentCond() {
        return paymentCond;
    }

    public void setPaymentCond(String paymentCond) {
        this.paymentCond = paymentCond;
    }

    public String getRemark() {
        return remark;
    }

    public void setRemark(String remark) {
        this.remark = remark;
    }

    public Integer getOrderDocFileId() {
        return orderDocFileId;
    }

    public void setOrderDocFileId(Integer orderDocFileId) {
        this.orderDocFileId = orderDocFileId;
    }

    public Integer getEstHid() {
        return estHid;
    }

    public void setEstHid(Integer estHid) {
        this.estHid = estHid;
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

    public BigDecimal getOrderAmtWithTax() {
        return orderAmtWithTax;
    }

    public void setOrderAmtWithTax(BigDecimal orderAmtWithTax) {
        this.orderAmtWithTax = orderAmtWithTax;
    }

    @Override
    public Serializable pkVal() {
        return this.orderId;
    }

    @Override
    public String toString() {
        return "Order{" +
        "orderId=" + orderId +
        ", orderNo=" + orderNo +
        ", useFlg=" + useFlg +
        ", createDt=" + createDt +
        ", clientNm=" + clientNm +
        ", orderAmtWithoutTax=" + orderAmtWithoutTax +
        ", workStartYmd=" + workStartYmd +
        ", workEndYmd=" + workEndYmd +
        ", pjCd=" + pjCd +
        ", pjNm=" + pjNm +
        ", workContent=" + workContent +
        ", workPlace=" + workPlace +
        ", orderContent=" + orderContent +
        ", specialAffairs=" + specialAffairs +
        ", paymentCond=" + paymentCond +
        ", remark=" + remark +
        ", orderDocFileId=" + orderDocFileId +
        ", estHid=" + estHid +
        ", sysInsUserId=" + sysInsUserId +
        ", sysInsDt=" + sysInsDt +
        ", sysInsPgId=" + sysInsPgId +
        ", sysUpdUserId=" + sysUpdUserId +
        ", sysUpdDt=" + sysUpdDt +
        ", sysUpdPgId=" + sysUpdPgId +
        ", orderAmtWithTax=" + orderAmtWithTax +
        "}";
    }
}

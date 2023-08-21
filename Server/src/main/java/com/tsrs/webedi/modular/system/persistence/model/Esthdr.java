package com.tsrs.webedi.modular.system.persistence.model;

import com.baomidou.mybatisplus.annotation.IdType;
import java.math.BigDecimal;
import java.util.Date;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.FieldFill;
import com.baomidou.mybatisplus.extension.activerecord.Model;
import com.baomidou.mybatisplus.annotation.TableLogic;
import java.io.Serializable;

/**
 * <p>
 * 見積HDR情報
 * </p>
 *
 * @author jin-qk
 * @since 2018-05-25
 */
public class Esthdr extends Model<Esthdr> {

    private static final long serialVersionUID = 1L;

    /**
     * 見積HID
     */
    @TableId(value = "est_hid", type = IdType.AUTO)
    private Integer estHid;
    /**
     * 見積NO
     */
    @TableField("est_no")
    private String estNo;
    /**
     * 使用FLG:Y:使用
N:削除
     */
    @TableField("use_flg")
    @TableLogic
    private String useFlg;
    /**
     * 見積区分:1:概算
2:確定
     */
    @TableField("est_k")
    private String estK;
    /**
     * 提出日
     */
    @TableField("submit_dt")
    private Date submitDt;
    /**
     * 発行日
     */
    @TableField("issue_dt")
    private Date issueDt;
    /**
     * 送付日
     */
    @TableField("delivery_dt")
    private Date deliveryDt;
    /**
     * 発注仕様書NO
     */
    @TableField("shipping_order_spec_no")
    private String shippingOrderSpecNo;
    /**
     * クライアント名
     */
    @TableField("client_nm")
    private String clientNm;
    /**
     * 見積金額(税抜)
     */
    @TableField("est_amt_without_tax")
    private BigDecimal estAmtWithoutTax;
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
     * 見積金額(税込)
     */
    @TableField("est_amt_with_tax")
    private BigDecimal estAmtWithTax;


    public Integer getEstHid() {
        return estHid;
    }

    public void setEstHid(Integer estHid) {
        this.estHid = estHid;
    }

    public String getEstNo() {
        return estNo;
    }

    public void setEstNo(String estNo) {
        this.estNo = estNo;
    }

    public String getUseFlg() {
        return useFlg;
    }

    public void setUseFlg(String useFlg) {
        this.useFlg = useFlg;
    }

    public String getEstK() {
        return estK;
    }

    public void setEstK(String estK) {
        this.estK = estK;
    }

    public Date getSubmitDt() {
        return submitDt;
    }

    public void setSubmitDt(Date submitDt) {
        this.submitDt = submitDt;
    }

    public Date getIssueDt() {
        return issueDt;
    }

    public void setIssueDt(Date issueDt) {
        this.issueDt = issueDt;
    }

    public Date getDeliveryDt() {
        return deliveryDt;
    }

    public void setDeliveryDt(Date deliveryDt) {
        this.deliveryDt = deliveryDt;
    }

    public String getShippingOrderSpecNo() {
        return shippingOrderSpecNo;
    }

    public void setShippingOrderSpecNo(String shippingOrderSpecNo) {
        this.shippingOrderSpecNo = shippingOrderSpecNo;
    }

    public String getClientNm() {
        return clientNm;
    }

    public void setClientNm(String clientNm) {
        this.clientNm = clientNm;
    }

    public BigDecimal getEstAmtWithoutTax() {
        return estAmtWithoutTax;
    }

    public void setEstAmtWithoutTax(BigDecimal estAmtWithoutTax) {
        this.estAmtWithoutTax = estAmtWithoutTax;
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

    public BigDecimal getEstAmtWithTax() {
        return estAmtWithTax;
    }

    public void setEstAmtWithTax(BigDecimal estAmtWithTax) {
        this.estAmtWithTax = estAmtWithTax;
    }

    @Override
    public Serializable pkVal() {
        return this.estHid;
    }

    @Override
    public String toString() {
        return "Esthdr{" +
        "estHid=" + estHid +
        ", estNo=" + estNo +
        ", useFlg=" + useFlg +
        ", estK=" + estK +
        ", submitDt=" + submitDt +
        ", issueDt=" + issueDt +
        ", deliveryDt=" + deliveryDt +
        ", shippingOrderSpecNo=" + shippingOrderSpecNo +
        ", clientNm=" + clientNm +
        ", estAmtWithoutTax=" + estAmtWithoutTax +
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
        ", sysInsUserId=" + sysInsUserId +
        ", sysInsDt=" + sysInsDt +
        ", sysInsPgId=" + sysInsPgId +
        ", sysUpdUserId=" + sysUpdUserId +
        ", sysUpdDt=" + sysUpdDt +
        ", sysUpdPgId=" + sysUpdPgId +
        ", estAmtWithTax=" + estAmtWithTax +
        "}";
    }
}

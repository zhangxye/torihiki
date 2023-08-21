package com.tsrs.webedi.common.persistence.model;

import com.baomidou.mybatisplus.annotation.IdType;
import java.util.Date;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.extension.activerecord.Model;
import java.io.Serializable;

/**
 * <p>
 * 管理者表
 * </p>
 *
 * @author jin-qk
 * @since 2018-06-19
 */
public class User extends Model<User> {

    private static final long serialVersionUID = 1L;

    /**
     * キーid
     */
    @TableId(value = "id", type = IdType.AUTO)
    private Integer id;
    /**
     * アバター
     */
    private String avatar;
    /**
     * アカウント
     */
    private String account;
    /**
     * パスワード
     */
    private String password;
    /**
     * パスワード塩
     */
    private String salt;
    /**
     * 名前
     */
    private String name;
    /**
     * 生日
     */
    @TableField("last_login_dt")
    private Date lastLoginDt;
    /**
     * 性別（1：男 2：女）
     */
    private Integer sex;
    /**
     * eメール
     */
    private String email;
    /**
     * 電話番号
     */
    private String phone;
    /**
     * ロールid
     */
    private Integer roleid;
    /**
     * 部門id
     */
    private Integer deptid;
    /**
     * 状態(1：起用  2：凍結  3：削除）
     */
    private Integer status;
    /**
     * 作成時間
     */
    private Date createtime;
    /**
     * 保持フィールド
     */
    private Integer version;
    @TableField("user_type")
    private String userType;
    /**
     * 職位C
     */
    @TableField("job_title_cd")
    private String jobTitleCd;


    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getAvatar() {
        return avatar;
    }

    public void setAvatar(String avatar) {
        this.avatar = avatar;
    }

    public String getAccount() {
        return account;
    }

    public void setAccount(String account) {
        this.account = account;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getSalt() {
        return salt;
    }

    public void setSalt(String salt) {
        this.salt = salt;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Date getLastLoginDt() {
        return lastLoginDt;
    }

    public void setLastLoginDt(Date lastLoginDt) {
        this.lastLoginDt = lastLoginDt;
    }

    public Integer getSex() {
        return sex;
    }

    public void setSex(Integer sex) {
        this.sex = sex;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public Integer getRoleid() {
        return roleid;
    }

    public void setRoleid(Integer roleid) {
        this.roleid = roleid;
    }

    public Integer getDeptid() {
        return deptid;
    }

    public void setDeptid(Integer deptid) {
        this.deptid = deptid;
    }

    public Integer getStatus() {
        return status;
    }

    public void setStatus(Integer status) {
        this.status = status;
    }

    public Date getCreatetime() {
        return createtime;
    }

    public void setCreatetime(Date createtime) {
        this.createtime = createtime;
    }

    public Integer getVersion() {
        return version;
    }

    public void setVersion(Integer version) {
        this.version = version;
    }

    public String getUserType() {
        return userType;
    }

    public void setUserType(String userType) {
        this.userType = userType;
    }

    public String getJobTitleCd() {
        return jobTitleCd;
    }

    public void setJobTitleCd(String jobTitleCd) {
        this.jobTitleCd = jobTitleCd;
    }

    @Override
	public Serializable pkVal() {
        return this.id;
    }

    @Override
    public String toString() {
        return "User{" +
        "id=" + id +
        ", avatar=" + avatar +
        ", account=" + account +
        ", password=" + password +
        ", salt=" + salt +
        ", name=" + name +
        ", lastLoginDt=" + lastLoginDt +
        ", sex=" + sex +
        ", email=" + email +
        ", phone=" + phone +
        ", roleid=" + roleid +
        ", deptid=" + deptid +
        ", status=" + status +
        ", createtime=" + createtime +
        ", version=" + version +
        ", userType=" + userType +
        ", jobTitleCd=" + jobTitleCd +
        "}";
    }
}

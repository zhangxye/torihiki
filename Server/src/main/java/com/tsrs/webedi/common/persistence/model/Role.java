package com.tsrs.webedi.common.persistence.model;

import com.baomidou.mybatisplus.annotation.IdType;
import java.util.Date;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.extension.activerecord.Model;
import java.io.Serializable;

/**
 * <p>
 * ロール
 * </p>
 *
 * @author tsrs
 * @since 2017-09-03
 */
public class Role extends Model<Role> {

    private static final long serialVersionUID = 1L;

    /**
     * キーid
     */
	@TableId(value="id", type= IdType.AUTO)
	private Integer id;
    /**
     * 順番
     */
//	@TableId(value="num")
	private Integer num;
    /**
     * 親ロールid
     */
//	@TableId(value="pid")
	private Integer pid;
    /**
     * ロール名称
     */
//	@TableId(value="name")
	private String name;
    /**
     * 部門名称
     */
//	@TableId(value="depid")
	private Integer deptid;
    /**
     * ヒント
     */
//	@TableId(value="tips")
	private String tips;
    /**
     * 保持フィールド
     */
//	@TableId(value="version")
	private Integer version;
   	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public Integer getNum() {
		return num;
	}

	public void setNum(Integer num) {
		this.num = num;
	}

	public Integer getPid() {
		return pid;
	}

	public void setPid(Integer pid) {
		this.pid = pid;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public Integer getDeptid() {
		return deptid;
	}

	public void setDeptid(Integer deptid) {
		this.deptid = deptid;
	}

	public String getTips() {
		return tips;
	}

	public void setTips(String tips) {
		this.tips = tips;
	}

	public Integer getVersion() {
		return version;
	}

	public void setVersion(Integer version) {
		this.version = version;
	}

	@Override
	public Serializable pkVal() {
		return this.id;
	}

}

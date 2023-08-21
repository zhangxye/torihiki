package com.tsrs.webedi.common.persistence.model;

import java.io.Serializable;
import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.extension.activerecord.Model;

/**
 * <p>
 * 部門
 * </p>
 *
 * @author tsrs
 * @since 2017-09-03
 */
public class Dept extends Model<Dept> {

    private static final long serialVersionUID = 1L;

    /**
     * キーid
     */
	@TableId(value="id", type= IdType.AUTO)
	private Integer id;
    /**
     * 順番
     */
	private Integer num;
    /**
     * 親部門id
     */
	private Integer pid;
    /**
     * 略称
     */
	private String simplename;
    /**
     * フルネーム
     */
	private String fullname;
    /**
     * ヒント
     */
	private String tips;
    /**
     * バージョン
     */
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

	public String getSimplename() {
		return simplename;
	}

	public void setSimplename(String simplename) {
		this.simplename = simplename;
	}

	public String getFullname() {
		return fullname;
	}

	public void setFullname(String fullname) {
		this.fullname = fullname;
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

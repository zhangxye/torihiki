package com.tsrs.webedi.common.persistence.model;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.extension.activerecord.Model;
import java.io.Serializable;

/**
 * <p>
 * 辞書
 * </p>
 *
 * @author tsrs
 * @since 2017-09-03
 */
public class Dict extends Model<Dict> {

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
     * 親辞書
     */
	private Integer pid;
    /**
     * 名称
     */
	private String name;
    /**
     * ヒント
     */
	private String tips;
   


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

	public String getTips() {
		return tips;
	}

	public void setTips(String tips) {
		this.tips = tips;
	}

	
	@Override
	public Serializable pkVal() {
		return this.id;
	}

}

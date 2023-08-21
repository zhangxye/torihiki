package com.tsrs.webedi.common.persistence.model;

import com.baomidou.mybatisplus.annotation.IdType;
import java.util.Date;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.extension.activerecord.Model;
import java.io.Serializable;

/**
 * <p>
 * お知らせ
 * </p>
 *
 * @author tsrs
 * @since 2017-09-03
 */
public class Notice extends Model<Notice> {

    private static final long serialVersionUID = 1L;

    /**
     * キー
     */
	@TableId(value="id", type= IdType.AUTO)
	private Integer id;
    /**
     * タイトル
     */
	private String title;
    /**
     * タイプ
     */
	private Integer type;
    /**
     * 内容
     */
	private String content;
    /**
     * 作成時間
     */
	private Date createtime;
    /**
     * 作成者
     */
	private Integer creater;
    
	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	public Integer getType() {
		return type;
	}

	public void setType(Integer type) {
		this.type = type;
	}

	public String getContent() {
		return content;
	}

	public void setContent(String content) {
		this.content = content;
	}

	public Date getCreatetime() {
		return createtime;
	}

	public void setCreatetime(Date createtime) {
		this.createtime = createtime;
	}

	public Integer getCreater() {
		return creater;
	}

	public void setCreater(Integer creater) {
		this.creater = creater;
	}

	@Override
	public Serializable pkVal() {
		return this.id;
	}

}

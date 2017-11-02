package com.jfeat.am.module.notice.services.persistence.model;

import java.io.Serializable;

import java.util.Date;
import com.baomidou.mybatisplus.annotations.TableField;
import com.baomidou.mybatisplus.activerecord.Model;
import com.baomidou.mybatisplus.annotations.TableName;
import java.io.Serializable;

/**
 * <p>
 * 
 * </p>
 *
 * @author Code Generator
 * @since 2017-11-02
 */
@TableName("t_notice")
public class Notice extends Model<Notice> {

    private static final long serialVersionUID = 1L;

    /**
     * 编号
     */
	private Long id;
    /**
     * 类型
     */
	private String type;
    /**
     * 是否启用 0-否 1-是
     */
	private Integer enable;
    /**
     * 名称
     */
	private String title;
    /**
     * 内容
     */
	private String content;
    /**
     * 创建时间
     */
	@TableField("create_time")
	private Date createTime;
    /**
     * 更新时间
     */
	@TableField("update_time")
	private Date updateTime;


	public Long getId() {
		return id;
	}

	public Notice setId(Long id) {
		this.id = id;
		return this;
	}

	public String getType() {
		return type;
	}

	public Notice setType(String type) {
		this.type = type;
		return this;
	}

	public Integer getEnable() {
		return enable;
	}

	public Notice setEnable(Integer enable) {
		this.enable = enable;
		return this;
	}

	public String getTitle() {
		return title;
	}

	public Notice setTitle(String title) {
		this.title = title;
		return this;
	}

	public String getContent() {
		return content;
	}

	public Notice setContent(String content) {
		this.content = content;
		return this;
	}

	public Date getCreateTime() {
		return createTime;
	}

	public Notice setCreateTime(Date createTime) {
		this.createTime = createTime;
		return this;
	}

	public Date getUpdateTime() {
		return updateTime;
	}

	public Notice setUpdateTime(Date updateTime) {
		this.updateTime = updateTime;
		return this;
	}

	public static final String ID = "id";

	public static final String TYPE = "type";

	public static final String ENABLE = "enable";

	public static final String TITLE = "title";

	public static final String CONTENT = "content";

	public static final String CREATE_TIME = "create_time";

	public static final String UPDATE_TIME = "update_time";

	@Override
	protected Serializable pkVal() {
		return this.id;
	}

	@Override
	public String toString() {
		return "Notice{" +
			"id=" + id +
			", type=" + type +
			", enable=" + enable +
			", title=" + title +
			", content=" + content +
			", createTime=" + createTime +
			", updateTime=" + updateTime +
			"}";
	}
}

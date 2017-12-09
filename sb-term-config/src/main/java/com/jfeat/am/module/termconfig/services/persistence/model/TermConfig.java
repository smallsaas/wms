package com.jfeat.am.module.termconfig.services.persistence.model;

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
 * @since 2017-12-09
 */
@TableName("t_term_config")
public class TermConfig extends Model<TermConfig> {

    private static final long serialVersionUID = 1L;

	private Long id;
    /**
     * 类型 ABOUT_US,NOTICE,CONNECT_US ...
     */
	private String type;
    /**
     * 标题
     */
	private String title;
    /**
     * 内容
     */
	private String content;
    /**
     * 创建时间
     */
	@TableField("created_time")
	private Date createdTime;


	public Long getId() {
		return id;
	}

	public TermConfig setId(Long id) {
		this.id = id;
		return this;
	}

	public String getType() {
		return type;
	}

	public TermConfig setType(String type) {
		this.type = type;
		return this;
	}

	public String getTitle() {
		return title;
	}

	public TermConfig setTitle(String title) {
		this.title = title;
		return this;
	}

	public String getContent() {
		return content;
	}

	public TermConfig setContent(String content) {
		this.content = content;
		return this;
	}

	public Date getCreatedTime() {
		return createdTime;
	}

	public TermConfig setCreatedTime(Date createdTime) {
		this.createdTime = createdTime;
		return this;
	}

	public static final String ID = "id";

	public static final String TYPE = "type";

	public static final String TITLE = "title";

	public static final String CONTENT = "content";

	public static final String CREATED_TIME = "created_time";

	@Override
	protected Serializable pkVal() {
		return this.id;
	}

	@Override
	public String toString() {
		return "TermConfig{" +
			"id=" + id +
			", type=" + type +
			", title=" + title +
			", content=" + content +
			", createdTime=" + createdTime +
			"}";
	}
}

package com.jfeat.am.module.feedback.services.persistence.model;

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
 * @author admin
 * @since 2017-11-28
 */
@TableName("t_feedback")
public class TFeedback extends Model<TFeedback> {

    private static final long serialVersionUID = 1L;

	private Long id;
	@TableField("user_id")
	private Long userId;
	private String content;
	@TableField("created_date")
	private Date createdDate;
	private Integer unread;


	public Long getId() {
		return id;
	}

	public TFeedback setId(Long id) {
		this.id = id;
		return this;
	}

	public Long getUserId() {
		return userId;
	}

	public TFeedback setUserId(Long userId) {
		this.userId = userId;
		return this;
	}

	public String getContent() {
		return content;
	}

	public TFeedback setContent(String content) {
		this.content = content;
		return this;
	}

	public Date getCreatedDate() {
		return createdDate;
	}

	public TFeedback setCreatedDate(Date createdDate) {
		this.createdDate = createdDate;
		return this;
	}

	public Integer getUnread() {
		return unread;
	}

	public TFeedback setUnread(Integer unread) {
		this.unread = unread;
		return this;
	}

	public static final String ID = "id";

	public static final String USER_ID = "user_id";

	public static final String CONTENT = "content";

	public static final String CREATED_DATE = "created_date";

	public static final String UNREAD = "unread";

	@Override
	protected Serializable pkVal() {
		return this.id;
	}

	@Override
	public String toString() {
		return "TFeedback{" +
			"id=" + id +
			", userId=" + userId +
			", content=" + content +
			", createdDate=" + createdDate +
			", unread=" + unread +
			"}";
	}
}

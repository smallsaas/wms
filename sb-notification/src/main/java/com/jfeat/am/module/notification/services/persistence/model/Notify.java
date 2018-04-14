package com.jfeat.am.module.notification.services.persistence.model;

import java.io.Serializable;

import java.util.Date;
import com.baomidou.mybatisplus.annotations.TableField;
import com.baomidou.mybatisplus.activerecord.Model;
import java.io.Serializable;

/**
 * <p>
 * 
 * </p>
 *
 * @author admin
 * @since 2018-04-14
 */
public class Notify extends Model<Notify> {

    private static final long serialVersionUID = 1L;

    /**
     * 主键
     */
	private Long id;
    /**
     * 发送者ID
     */
	@TableField("sender_id")
	private Long senderId;
    /**
     * 内容提醒
     */
	private String content;
    /**
     * 类型
     */
	private String type;
    /**
     * 目标ID
     */
	@TableField("target_id")
	private Long targetId;
    /**
     * 目标类型:topic essay
     */
	@TableField("target_type")
	private String targetType;
    /**
     * 操作:like comment
     */
	private String action;
    /**
     * 创建时间
     */
	@TableField("create_time")
	private Date createTime;


	public Long getId() {
		return id;
	}

	public Notify setId(Long id) {
		this.id = id;
		return this;
	}

	public Long getSenderId() {
		return senderId;
	}

	public Notify setSenderId(Long senderId) {
		this.senderId = senderId;
		return this;
	}

	public String getContent() {
		return content;
	}

	public Notify setContent(String content) {
		this.content = content;
		return this;
	}

	public String getType() {
		return type;
	}

	public Notify setType(String type) {
		this.type = type;
		return this;
	}

	public Long getTargetId() {
		return targetId;
	}

	public Notify setTargetId(Long targetId) {
		this.targetId = targetId;
		return this;
	}

	public String getTargetType() {
		return targetType;
	}

	public Notify setTargetType(String targetType) {
		this.targetType = targetType;
		return this;
	}

	public String getAction() {
		return action;
	}

	public Notify setAction(String action) {
		this.action = action;
		return this;
	}

	public Date getCreateTime() {
		return createTime;
	}

	public Notify setCreateTime(Date createTime) {
		this.createTime = createTime;
		return this;
	}

	public static final String ID = "id";

	public static final String SENDER_ID = "sender_id";

	public static final String CONTENT = "content";

	public static final String TYPE = "type";

	public static final String TARGET_ID = "target_id";

	public static final String TARGET_TYPE = "target_type";

	public static final String ACTION = "action";

	public static final String CREATE_TIME = "create_time";

	@Override
	protected Serializable pkVal() {
		return this.id;
	}

	@Override
	public String toString() {
		return "Notify{" +
			"id=" + id +
			", senderId=" + senderId +
			", content=" + content +
			", type=" + type +
			", targetId=" + targetId +
			", targetType=" + targetType +
			", action=" + action +
			", createTime=" + createTime +
			"}";
	}
}

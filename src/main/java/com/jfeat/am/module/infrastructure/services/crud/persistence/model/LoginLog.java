package com.jfeat.am.module.infrastructure.services.crud.persistence.model;

import java.io.Serializable;

import com.baomidou.mybatisplus.enums.IdType;
import java.util.Date;
import com.baomidou.mybatisplus.annotations.TableId;
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
 * @since 2017-10-09
 */
@TableName("login_log")
public class LoginLog extends Model<LoginLog> {

    private static final long serialVersionUID = 1L;

	@TableId(value="id", type= IdType.AUTO)
	private Long id;
    /**
     * 日志名称
     */
	@TableField("log_name")
	private String logName;
	@TableField("user_id")
	private String userId;
    /**
     * 创建时间
     */
	@TableField("create_time")
	private Date createTime;
    /**
     * 成功与否
     */
	private String succeed;
    /**
     * 附带信息
     */
	private String message;
    /**
     * 地址
     */
	private String ip;


	public Long getId() {
		return id;
	}

	public LoginLog setId(Long id) {
		this.id = id;
		return this;
	}

	public String getLogName() {
		return logName;
	}

	public LoginLog setLogName(String logName) {
		this.logName = logName;
		return this;
	}

	public String getUserId() {
		return userId;
	}

	public LoginLog setUserId(String userId) {
		this.userId = userId;
		return this;
	}

	public Date getCreateTime() {
		return createTime;
	}

	public LoginLog setCreateTime(Date createTime) {
		this.createTime = createTime;
		return this;
	}

	public String getSucceed() {
		return succeed;
	}

	public LoginLog setSucceed(String succeed) {
		this.succeed = succeed;
		return this;
	}

	public String getMessage() {
		return message;
	}

	public LoginLog setMessage(String message) {
		this.message = message;
		return this;
	}

	public String getIp() {
		return ip;
	}

	public LoginLog setIp(String ip) {
		this.ip = ip;
		return this;
	}

	public static final String ID = "id";

	public static final String LOG_NAME = "log_name";

	public static final String USER_ID = "user_id";

	public static final String CREATE_TIME = "create_time";

	public static final String SUCCEED = "succeed";

	public static final String MESSAGE = "message";

	public static final String IP = "ip";

	@Override
	protected Serializable pkVal() {
		return this.id;
	}

	@Override
	public String toString() {
		return "LoginLog{" +
			"id=" + id +
			", logName=" + logName +
			", userId=" + userId +
			", createTime=" + createTime +
			", succeed=" + succeed +
			", message=" + message +
			", ip=" + ip +
			"}";
	}
}

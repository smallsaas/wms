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
 * @since 2017-10-10
 */
@TableName("operation_log")
public class OperationLog extends Model<OperationLog> {

    private static final long serialVersionUID = 1L;

	@TableId(value="id", type= IdType.AUTO)
	private Long id;
    /**
     * 日志类型
     */
	@TableField("log_type")
	private String logType;
    /**
     * 日志名称
     */
	@TableField("log_name")
	private String logName;
	@TableField("user_id")
	private String userId;
    /**
     * 类名称
     */
	@TableField("class_name")
	private String className;
    /**
     * 方法名称
     */
	private String method;
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


	public Long getId() {
		return id;
	}

	public OperationLog setId(Long id) {
		this.id = id;
		return this;
	}

	public String getLogType() {
		return logType;
	}

	public OperationLog setLogType(String logType) {
		this.logType = logType;
		return this;
	}

	public String getLogName() {
		return logName;
	}

	public OperationLog setLogName(String logName) {
		this.logName = logName;
		return this;
	}

	public String getUserId() {
		return userId;
	}

	public OperationLog setUserId(String userId) {
		this.userId = userId;
		return this;
	}

	public String getClassName() {
		return className;
	}

	public OperationLog setClassName(String className) {
		this.className = className;
		return this;
	}

	public String getMethod() {
		return method;
	}

	public OperationLog setMethod(String method) {
		this.method = method;
		return this;
	}

	public Date getCreateTime() {
		return createTime;
	}

	public OperationLog setCreateTime(Date createTime) {
		this.createTime = createTime;
		return this;
	}

	public String getSucceed() {
		return succeed;
	}

	public OperationLog setSucceed(String succeed) {
		this.succeed = succeed;
		return this;
	}

	public String getMessage() {
		return message;
	}

	public OperationLog setMessage(String message) {
		this.message = message;
		return this;
	}

	public static final String ID = "id";

	public static final String LOG_TYPE = "log_type";

	public static final String LOG_NAME = "log_name";

	public static final String USER_ID = "user_id";

	public static final String CLASS_NAME = "class_name";

	public static final String METHOD = "method";

	public static final String CREATE_TIME = "create_time";

	public static final String SUCCEED = "succeed";

	public static final String MESSAGE = "message";

	@Override
	protected Serializable pkVal() {
		return this.id;
	}

	@Override
	public String toString() {
		return "OperationLog{" +
			"id=" + id +
			", logType=" + logType +
			", logName=" + logName +
			", userId=" + userId +
			", className=" + className +
			", method=" + method +
			", createTime=" + createTime +
			", succeed=" + succeed +
			", message=" + message +
			"}";
	}
}

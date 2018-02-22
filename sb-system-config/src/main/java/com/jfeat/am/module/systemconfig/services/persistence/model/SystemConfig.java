package com.jfeat.am.module.systemconfig.services.persistence.model;

import java.io.Serializable;

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
 * @since 2018-02-22
 */
@TableName("sb_system_config")
public class SystemConfig extends Model<SystemConfig> {

    private static final long serialVersionUID = 1L;

	private Long id;
    /**
     * key
     */
	@TableField("data_key")
	private String dataKey;
    /**
     * value
     */
	@TableField("data_value")
	private String dataValue;


	public Long getId() {
		return id;
	}

	public SystemConfig setId(Long id) {
		this.id = id;
		return this;
	}

	public String getDataKey() {
		return dataKey;
	}

	public SystemConfig setDataKey(String dataKey) {
		this.dataKey = dataKey;
		return this;
	}

	public String getDataValue() {
		return dataValue;
	}

	public SystemConfig setDataValue(String dataValue) {
		this.dataValue = dataValue;
		return this;
	}

	public static final String ID = "id";

	public static final String DATA_KEY = "data_key";

	public static final String DATA_VALUE = "data_value";

	@Override
	protected Serializable pkVal() {
		return this.id;
	}

	@Override
	public String toString() {
		return "SystemConfig{" +
			"id=" + id +
			", dataKey=" + dataKey +
			", dataValue=" + dataValue +
			"}";
	}
}

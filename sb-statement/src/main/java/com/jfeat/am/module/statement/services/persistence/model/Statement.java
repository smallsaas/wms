package com.jfeat.am.module.statement.services.persistence.model;

import java.io.Serializable;

import com.baomidou.mybatisplus.activerecord.Model;
import com.baomidou.mybatisplus.annotations.TableName;
import java.io.Serializable;

/**
 * <p>
 * 
 * </p>
 *
 * @author Code Generator
 * @since 2017-11-06
 */
@TableName("t_statement")
public class Statement extends Model<Statement> {

    private static final long serialVersionUID = 1L;

    /**
     * 主键
     */
	private Long id;
    /**
     * 方法
     */
	private String mothod;
    /**
     * sql语句
     */
	private String sql;
    /**
     * 标识符
     */
	private String identifier;
    /**
     * 描述
     */
	private String description;


	public Long getId() {
		return id;
	}

	public Statement setId(Long id) {
		this.id = id;
		return this;
	}

	public String getMothod() {
		return mothod;
	}

	public Statement setMothod(String mothod) {
		this.mothod = mothod;
		return this;
	}

	public String getSql() {
		return sql;
	}

	public Statement setSql(String sql) {
		this.sql = sql;
		return this;
	}

	public String getIdentifier() {
		return identifier;
	}

	public Statement setIdentifier(String identifier) {
		this.identifier = identifier;
		return this;
	}

	public String getDescription() {
		return description;
	}

	public Statement setDescription(String description) {
		this.description = description;
		return this;
	}

	public static final String ID = "id";

	public static final String MOTHOD = "mothod";

	public static final String SQL = "sql";

	public static final String IDENTIFIER = "identifier";

	public static final String DESCRIPTION = "description";

	@Override
	protected Serializable pkVal() {
		return this.id;
	}

	@Override
	public String toString() {
		return "Statement{" +
			"id=" + id +
			", mothod=" + mothod +
			", sql=" + sql +
			", identifier=" + identifier +
			", description=" + description +
			"}";
	}
}

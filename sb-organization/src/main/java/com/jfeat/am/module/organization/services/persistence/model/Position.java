package com.jfeat.am.module.organization.services.persistence.model;

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
 * @since 2017-10-26
 */
@TableName("t_position")
public class Position extends Model<Position> {

    private static final long serialVersionUID = 1L;

	private Long id;
    /**
     * ??
     */
	private String name;
    /**
     * ??
     */
	private String note;


	public Long getId() {
		return id;
	}

	public Position setId(Long id) {
		this.id = id;
		return this;
	}

	public String getName() {
		return name;
	}

	public Position setName(String name) {
		this.name = name;
		return this;
	}

	public String getNote() {
		return note;
	}

	public Position setNote(String note) {
		this.note = note;
		return this;
	}

	public static final String ID = "id";

	public static final String NAME = "name";

	public static final String NOTE = "note";

	@Override
	protected Serializable pkVal() {
		return this.id;
	}

	@Override
	public String toString() {
		return "Position{" +
			"id=" + id +
			", name=" + name +
			", note=" + note +
			"}";
	}
}

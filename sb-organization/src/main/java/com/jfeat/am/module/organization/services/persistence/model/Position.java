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
 * @author admin
 * @since 2018-01-17
 */
@TableName("t_position")
public class Position extends Model<Position> {

    private static final long serialVersionUID = 1L;

	private Long id;
    /**
     * 备注
     */
	private String name;
    /**
     * 备注
     */
	private String note;


	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getNote() {
		return note;
	}

	public void setNote(String note) {
		this.note = note;
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

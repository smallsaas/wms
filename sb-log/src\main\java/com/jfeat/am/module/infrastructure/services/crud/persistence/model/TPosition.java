package com.jfeat.am.module.infrastructure.services.crud.persistence.model;

import java.io.Serializable;

import com.baomidou.mybatisplus.enums.IdType;
import com.baomidou.mybatisplus.annotations.TableId;
import com.baomidou.mybatisplus.activerecord.Model;
import com.baomidou.mybatisplus.annotations.TableName;
import java.io.Serializable;

/**
 * <p>
 * 
 * </p>
 *
 * @author admin
 * @since 2017-10-15
 */
@TableName("t_position")
public class TPosition extends Model<TPosition> {

    private static final long serialVersionUID = 1L;

	@TableId(value="id", type= IdType.AUTO)
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

	public TPosition setId(Long id) {
		this.id = id;
		return this;
	}

	public String getName() {
		return name;
	}

	public TPosition setName(String name) {
		this.name = name;
		return this;
	}

	public String getNote() {
		return note;
	}

	public TPosition setNote(String note) {
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
		return "TPosition{" +
			"id=" + id +
			", name=" + name +
			", note=" + note +
			"}";
	}
}

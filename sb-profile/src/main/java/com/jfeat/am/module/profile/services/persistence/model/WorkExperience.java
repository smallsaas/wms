package com.jfeat.am.module.profile.services.persistence.model;

import com.baomidou.mybatisplus.activerecord.Model;
import com.baomidou.mybatisplus.annotations.TableField;
import com.baomidou.mybatisplus.annotations.TableId;
import com.baomidou.mybatisplus.annotations.TableName;
import com.baomidou.mybatisplus.enums.IdType;

import java.io.Serializable;
import java.util.Date;

/**
 * <p>
 * 
 * </p>
 *
 * @author admin
 * @since 2017-10-24
 */
@TableName("t_work_experience")
public class WorkExperience extends Model<WorkExperience> {

    private static final long serialVersionUID = 1L;

	@TableId(value="id", type= IdType.AUTO)
	private Long id;
	/*
	* resumeId
	* */
	private Long resumeId;
    /**
     * 工作地点
     */
	@TableField("work_area")
	private String workArea;

    /**
     * 入职时间
     */
	@TableField("entry_time")
	private String entryTime;
	/**
	 * 离职时间
	 */
	@TableField("dimission_time")
	private String dimissionTime;
    /**
     * 工作公司
     */
	@TableField("work_company")
	private String workCompany;
    /**
     * 工作描述
     */
	@TableField("work_post")
	private String workPost;
	/*
	* 在校情况
	* */
	@TableField("description")
	private String description;

	public static long getSerialVersionUID() {
		return serialVersionUID;
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public Long getResumeId() {
		return resumeId;
	}

	public void setResumeId(Long resumeId) {
		this.resumeId = resumeId;
	}

	public String getWorkArea() {
		return workArea;
	}

	public void setWorkArea(String workArea) {
		this.workArea = workArea;
	}

	public String getEntryTime() {
		return entryTime;
	}

	public void setEntryTime(String entryTime) {
		this.entryTime = entryTime;
	}

	public String getDimissionTime() {
		return dimissionTime;
	}

	public void setDimissionTime(String dimissionTime) {
		this.dimissionTime = dimissionTime;
	}

	public String getWorkCompany() {
		return workCompany;
	}

	public void setWorkCompany(String workCompany) {
		this.workCompany = workCompany;
	}

	public String getWorkPost() {
		return workPost;
	}

	public void setWorkPost(String workPost) {
		this.workPost = workPost;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	@Override
	protected Serializable pkVal() {
		return null;
	}
}

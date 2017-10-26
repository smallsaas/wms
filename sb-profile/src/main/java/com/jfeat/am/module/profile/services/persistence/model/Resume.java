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
@TableName("t_resume")
public class Resume extends Model<Resume> {

    private static final long serialVersionUID = 1L;

	@TableId(value="id", type= IdType.AUTO)
	private Long id;
    /**
     * 工作地点
     */
	@TableField("work_area")
	private String workArea;
    /**
     * 兼职时间
     */
	@TableField("jb_time")
	private Date jbTime;
    /**
     * 个人评价
     */
	@TableField("self_evaluate")
	private String selfEvaluate;
    /**
     * 工作时间
     */
	@TableField("work_time")
	private String workTime;
    /**
     * 工作公司
     */
	@TableField("work_company")
	private String workCompany;
    /**
     * 工作岗位
     */
	@TableField("work_post")
	private String workPost;
    /**
     * 学校
     */
	private String school;
    /**
     * 专业
     */
	private String major;
    /**
     * 学历
     */
	private String education;
    /**
     * 在校时间
     */
	@TableField("school_time")
	private Date schoolTime;
    /**
     * 在校经历
     */
	@TableField("school_exp")
	private String schoolExp;
    /**
     * 保留字段
     */
	private String field1;
    /**
     * 保留字段
     */
	private String field2;
    /**
     * 保留字段
     */
	private String field3;
    /**
     * 保留字段
     */
	private String field4;


	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getWorkArea() {
		return workArea;
	}

	public void setWorkArea(String workArea) {
		this.workArea = workArea;
	}

	public Date getJbTime() {
		return jbTime;
	}

	public void setJbTime(Date jbTime) {
		this.jbTime = jbTime;
	}

	public String getSelfEvaluate() {
		return selfEvaluate;
	}

	public void setSelfEvaluate(String selfEvaluate) {
		this.selfEvaluate = selfEvaluate;
	}

	public String getWorkTime() {
		return workTime;
	}

	public void setWorkTime(String workTime) {
		this.workTime = workTime;
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

	public String getSchool() {
		return school;
	}

	public void setSchool(String school) {
		this.school = school;
	}

	public String getMajor() {
		return major;
	}

	public void setMajor(String major) {
		this.major = major;
	}

	public String getEducation() {
		return education;
	}

	public void setEducation(String education) {
		this.education = education;
	}

	public Date getSchoolTime() {
		return schoolTime;
	}

	public void setSchoolTime(Date schoolTime) {
		this.schoolTime = schoolTime;
	}

	public String getSchoolExp() {
		return schoolExp;
	}

	public void setSchoolExp(String schoolExp) {
		this.schoolExp = schoolExp;
	}

	public String getField1() {
		return field1;
	}

	public void setField1(String field1) {
		this.field1 = field1;
	}

	public String getField2() {
		return field2;
	}

	public void setField2(String field2) {
		this.field2 = field2;
	}

	public String getField3() {
		return field3;
	}

	public void setField3(String field3) {
		this.field3 = field3;
	}

	public String getField4() {
		return field4;
	}

	public void setField4(String field4) {
		this.field4 = field4;
	}

	@Override
	protected Serializable pkVal() {
		return this.id;
	}

	@Override
	public String toString() {
		return "Resume{" +
			"id=" + id +
			", workArea=" + workArea +
			", jbTime=" + jbTime +
			", selfEvaluate=" + selfEvaluate +
			", workTime=" + workTime +
			", workCompany=" + workCompany +
			", workPost=" + workPost +
			", school=" + school +
			", major=" + major +
			", education=" + education +
			", schoolTime=" + schoolTime +
			", schoolExp=" + schoolExp +
			", field1=" + field1 +
			", field2=" + field2 +
			", field3=" + field3 +
			", field4=" + field4 +
			"}";
	}
}

package com.jfeat.am.module.organization.services.domain.model;

import com.jfeat.am.module.organization.services.persistence.model.Position;
import com.jfeat.am.module.organization.services.persistence.model.Staff;
import com.jfeat.am.module.profile.services.persistence.model.Profile;

/**
 * Created by Code Generator on 2017-10-26
 */
public class StaffItem extends Staff{

    private String position;

    private String name;

    private String mobile;

    private String sex;

    private int age;

    private String deptCode;

    private String deptName;


    public String getPosition() {
        return position;
    }

    public void setPosition(String position) {
        this.position = position;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getMobile() {
        return mobile;
    }

    public void setMobile(String mobile) {
        this.mobile = mobile;
    }

    public String getSex() {
        return sex;
    }

    public void setSex(String sex) {
        this.sex = sex;
    }

    public int getAge() {
        return age;
    }

    public void setAge(int age) {
        this.age = age;
    }

    public String getDeptCode() {
        return deptCode;
    }

    public void setDeptCode(String deptCode) {
        this.deptCode = deptCode;
    }

    public String getDeptName() {
        return deptName;
    }

    public void setDeptName(String deptName) {
        this.deptName = deptName;
    }
}

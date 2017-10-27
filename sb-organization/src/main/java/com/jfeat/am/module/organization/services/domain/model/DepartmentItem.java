package com.jfeat.am.module.organization.services.domain.model;
import com.jfeat.am.module.organization.services.persistence.model.Department;

/**
 * Created by Code Generator on 2017-10-27
 */
public class DepartmentItem extends Department{
    private String pn;

    public String getPn() {
        return pn;
    }

    public void setPn(String pn) {
        this.pn = pn;
    }
}

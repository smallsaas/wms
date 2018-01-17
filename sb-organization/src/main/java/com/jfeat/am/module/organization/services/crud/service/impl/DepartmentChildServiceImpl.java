package com.jfeat.am.module.organization.services.crud.service.impl;

import com.alibaba.fastjson.JSONObject;
import com.baomidou.mybatisplus.mapper.BaseMapper;
import com.jfeat.am.common.crud.impl.CRUDServiceChildImpl;
import com.jfeat.am.module.organization.services.crud.service.DepartmentChildService;
import com.jfeat.am.module.organization.services.crud.service.DepartmentService;
import com.jfeat.am.module.organization.services.persistence.mapper.DepartmentMapper;
import com.jfeat.am.module.organization.services.persistence.mapper.StaffMapper;
import com.jfeat.am.module.organization.services.persistence.model.Department;
import com.jfeat.am.module.organization.services.persistence.model.Staff;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;

/**
 * Created by Silent-Y on 2017/11/3.
 */
@Service
public class DepartmentChildServiceImpl extends CRUDServiceChildImpl<Staff, Department> implements DepartmentChildService{

    @Resource
    private StaffMapper staffMapper;
    @Resource
    private DepartmentMapper departmentMapper;

    private static final String childReferenceName = Staff.DEPT_ID;

    @Override
    protected BaseMapper<Staff> getMasterMapper() {
        return staffMapper;
    }

    @Override
    protected BaseMapper<Department> getChildMapper() {
        return departmentMapper;
    }

    @Override
    protected String getChildReference() {
        return childReferenceName;
    }
}

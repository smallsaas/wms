package com.jfeat.am.module.organization.services.crud.service.impl;

import com.jfeat.am.module.organization.services.crud.service.DepartmentStaffService;
import com.jfeat.am.module.organization.services.persistence.mapper.DepartmentStaffMapper;
import com.jfeat.am.module.organization.services.persistence.model.DepartmentStaff;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;

/**
 * Created by Silent-Y on 2018/1/17.
 */
@Service
public class DepartmentStaffServiceImpl implements DepartmentStaffService {

    @Resource
    DepartmentStaffMapper departmentStaffMapper;

    @Override
    public Integer save(DepartmentStaff departmentStaff) {
        return departmentStaffMapper.insert(departmentStaff);
    }

    @Override
    public Integer delete(Long id) {
        return departmentStaffMapper.deleteById(id);
    }
}

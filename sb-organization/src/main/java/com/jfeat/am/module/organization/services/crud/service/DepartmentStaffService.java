package com.jfeat.am.module.organization.services.crud.service;

import com.jfeat.am.module.organization.services.persistence.model.DepartmentStaff;

import java.util.List;

/**
 * Created by Silent-Y on 2018/1/17.
 */
public interface DepartmentStaffService {

    Integer save(DepartmentStaff departmentStaff);
    Integer delete(Long id);
    DepartmentStaff get(DepartmentStaff departmentStaff);
    Integer update(DepartmentStaff departmentStaff);
    List<DepartmentStaff> selectList(DepartmentStaff departmentStaff);
    Integer deleteList(DepartmentStaff departmentStaff);

}

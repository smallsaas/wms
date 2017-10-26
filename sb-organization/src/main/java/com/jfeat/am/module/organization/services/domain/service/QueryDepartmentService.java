package com.jfeat.am.module.organization.services.domain.service;

import com.jfeat.am.module.organization.services.persistence.model.Department;

import java.util.List;

/**
 * Created by vincent on 2017/10/19.
 */
public interface QueryDepartmentService {
    List<Department> findDepartments(int pageNum, int pageSize, String name, String status);

    List<Department> findDepartmentPage(int pageNum, int pageSize,Department department );
}
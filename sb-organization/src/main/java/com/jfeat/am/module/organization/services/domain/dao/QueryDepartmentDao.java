package com.jfeat.am.module.organization.services.domain.dao;

import com.baomidou.mybatisplus.mapper.BaseMapper;
import com.baomidou.mybatisplus.plugins.Page;
import com.jfeat.am.module.organization.services.persistence.model.Department;

import java.util.List;

/**
 * Created by Code Generator on 2017-10-26
 */
public interface QueryDepartmentDao extends BaseMapper<Department> {
    List<Department> findDepartmentPage(Department department);
}
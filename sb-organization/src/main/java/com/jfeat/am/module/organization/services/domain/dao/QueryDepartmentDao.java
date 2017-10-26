package com.jfeat.am.module.organization.services.domain.dao;

import com.jfeat.am.module.organization.services.persistence.model.Department;

import com.baomidou.mybatisplus.plugins.Page;
import com.baomidou.mybatisplus.mapper.BaseMapper;
import org.apache.ibatis.annotations.Param;
import java.util.List;

/**
 * Created by Code Generator on 2017-10-26
 */
public interface QueryDepartmentDao extends BaseMapper<Department> {

    List<Department> findDepartments(Page<Department> page,
            @Param("name") String name,
            @Param("status") String status);

    List<Department> findDepartmentPage(Page<Department> page,Department department);
}
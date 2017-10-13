package com.jfeat.am.module.infrastructure.services.domain.dao;

import com.jfeat.am.module.infrastructure.services.crud.persistence.model.Department;

import com.baomidou.mybatisplus.plugins.Page;
import com.baomidou.mybatisplus.mapper.BaseMapper;
import org.apache.ibatis.annotations.Param;
import java.util.List;

public interface QueryDepartmentDao  extends BaseMapper<Department> {

    List<Department> findDepartments(Page<Department> page,
            @Param("status") String status);

}
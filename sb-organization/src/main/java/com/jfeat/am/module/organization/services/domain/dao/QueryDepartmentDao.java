package com.jfeat.am.module.organization.services.domain.dao;

import com.baomidou.mybatisplus.mapper.BaseMapper;
import com.jfeat.am.module.organization.services.domain.model.DepartmentItem;
import com.jfeat.am.module.organization.services.persistence.model.Department;
import org.apache.ibatis.annotations.Param;
import org.springframework.web.bind.annotation.PathVariable;

import java.util.List;
import java.util.Map;

/**
 * Created by Code Generator on 2017-10-26
 */
public interface QueryDepartmentDao extends BaseMapper<Department> {
    List<DepartmentItem> findDepartmentPage(Department department);
    Map<String,Object> showDepartmentDetail(@Param("id")Long id,@Param("isManeger")String isManeger);
}
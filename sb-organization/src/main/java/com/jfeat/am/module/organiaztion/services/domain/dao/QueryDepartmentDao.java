package com.jfeat.am.module.organiaztion.services.domain.dao;

import com.baomidou.mybatisplus.mapper.BaseMapper;
import com.jfeat.am.module.organiaztion.services.persistence.model.Staff;

/**
 * 返回所有部门， 无需过滤查询，可删除
 */

@Deprecated
public interface QueryDepartmentDao extends BaseMapper<Staff> {
}
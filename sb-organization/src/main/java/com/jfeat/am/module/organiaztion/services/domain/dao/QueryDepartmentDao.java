package com.jfeat.am.module.organiaztion.services.domain.dao;

import com.baomidou.mybatisplus.mapper.BaseMapper;
import com.baomidou.mybatisplus.plugins.Page;
import com.jfeat.am.module.organiaztion.services.crud.persistence.model.Staff;

import java.util.List;

/**
 * 返回所有部门， 无需过滤查询，可删除
 */

@Deprecated
public interface QueryDepartmentDao extends BaseMapper<Staff> {

}
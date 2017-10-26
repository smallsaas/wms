package com.jfeat.am.module.organization.services.domain.dao;

import com.baomidou.mybatisplus.plugins.Page;
import com.jfeat.am.module.organization.services.persistence.model.Staff;

import com.baomidou.mybatisplus.mapper.BaseMapper;

import java.util.List;

public interface QueryStaffDao  extends BaseMapper<Staff> {
    List<Staff> findStaffs(Page<Staff> page, Integer department, String position, int workAge);
}
package com.jfeat.am.module.organization.services.domain.dao;

import com.jfeat.am.module.organization.services.persistence.model.Staff;

import com.baomidou.mybatisplus.plugins.Page;
import com.baomidou.mybatisplus.mapper.BaseMapper;
import org.apache.ibatis.annotations.Param;
import java.util.List;

/**
 * Created by Code Generator on 2017-10-26
 */
public interface QueryStaffDao extends BaseMapper<Staff> {
    List<Staff> findStaffs(Page<Staff> page, Staff staff);
}
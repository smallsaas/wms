package com.jfeat.am.module.infrastructure.services.domain.dao;

import com.jfeat.am.module.infrastructure.services.crud.persistence.model.Staff;

import com.baomidou.mybatisplus.plugins.Page;
import com.baomidou.mybatisplus.mapper.BaseMapper;
import org.apache.ibatis.annotations.Param;
import java.util.List;

public interface QueryStaffDao  extends BaseMapper<Staff> {

    List<Staff> findStaffs(Page<Staff> page,
            @Param("status") String status);

}
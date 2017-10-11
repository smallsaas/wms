package com.jfeat.am.module.infrastructure.services.domain.dao;

import com.jfeat.am.module.infrastructure.services.crud.persistence.model.Deptment;
import com.baomidou.mybatisplus.mapper.BaseMapper;
import org.apache.ibatis.annotations.Param;
import java.util.List;

public interface QueryDeptmentDao  extends BaseMapper<Deptment> {

    List<Deptment> findDeptments(
            @Param("status") String status);

}
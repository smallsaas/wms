package com.jfeat.am.module.infrastructure.services.domain.dao;

import com.jfeat.am.module.infrastructure.services.crud.persistence.model.Dept;
import com.baomidou.mybatisplus.mapper.BaseMapper;
import org.apache.ibatis.annotations.Param;
import java.util.List;

public interface QueryDeptDao  extends BaseMapper<Dept> {

    List<Dept> findDepts(
            @Param("status") String status);

}
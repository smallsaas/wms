package com.jfeat.am.module.organization.services.domain.dao;

import com.jfeat.am.module.organization.services.persistence.model.Position;

import com.baomidou.mybatisplus.plugins.Page;
import com.baomidou.mybatisplus.mapper.BaseMapper;
import org.apache.ibatis.annotations.Param;
import java.util.List;

/**
 * Created by Code Generator on 2017-10-27
 */
public interface QueryPositionDao extends BaseMapper<Position> {
    List<Position> findPositionPage(Page<Position> page,Position position);
}
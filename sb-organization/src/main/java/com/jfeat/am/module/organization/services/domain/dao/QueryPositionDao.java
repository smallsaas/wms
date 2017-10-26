package com.jfeat.am.module.organization.services.domain.dao;

import com.baomidou.mybatisplus.mapper.BaseMapper;
import com.baomidou.mybatisplus.plugins.Page;
import com.jfeat.am.module.organization.services.persistence.model.Position;

import java.util.List;

/**
 * Created by Code Generator on 2017-10-26
 */
public interface QueryPositionDao extends BaseMapper<Position> {
    List<Position> findPositionPage(Page<Position> page, Position position);
}
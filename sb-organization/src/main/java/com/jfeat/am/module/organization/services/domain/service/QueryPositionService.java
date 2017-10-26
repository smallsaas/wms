package com.jfeat.am.module.organization.services.domain.service;

import com.jfeat.am.module.organization.services.persistence.model.Position;

import java.util.List;

/**
 * Created by vincent on 2017/10/19.
 */
public interface QueryPositionService {
    List<Position> findPositionPage(Page<Position> page, Position position );
}
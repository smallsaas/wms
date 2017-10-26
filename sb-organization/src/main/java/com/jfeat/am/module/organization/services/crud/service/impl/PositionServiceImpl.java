package com.jfeat.am.module.organization.services.crud.service.impl;

import com.baomidou.mybatisplus.mapper.BaseMapper;
import com.jfeat.am.common.crud.impl.CRUDServiceOnlyImpl;
import com.jfeat.am.module.organization.services.crud.service.PositionService;
import com.jfeat.am.module.organization.services.persistence.dao.PositionMapper;
import com.jfeat.am.module.organization.services.persistence.model.Position;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;

/**
 * <p>
 *  implementation
 * </p>
 *
 * @author Code Generator
 * @since 2017-10-26
 */
@Service
public class PositionServiceImpl extends CRUDServiceOnlyImpl<Position> implements PositionService {

    @Resource
    private PositionMapper positionMapper;

    @Override
    protected BaseMapper<Position> getMasterMapper() {
        return positionMapper;
    }
}



package com.jfeat.am.module.organization.services.crud.service.impl;

import com.baomidou.mybatisplus.mapper.BaseMapper;
import com.jfeat.am.common.crud.impl.CRUDServiceChildImpl;
import com.jfeat.am.module.organization.services.crud.service.PositionChildService;
import com.jfeat.am.module.organization.services.persistence.dao.PositionMapper;
import com.jfeat.am.module.organization.services.persistence.dao.StaffMapper;
import com.jfeat.am.module.organization.services.persistence.model.Position;
import com.jfeat.am.module.organization.services.persistence.model.Staff;
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
public class PositionChildServiceImpl extends CRUDServiceChildImpl<Staff, Position> implements PositionChildService {

    @Resource
    private PositionMapper positionMapper;

    @Resource
    private StaffMapper staffMapper;
    

    private static final String childReferenceName = "position_id";

    @Override
    protected BaseMapper<Staff> getMasterMapper() {
        return staffMapper;
    }

    @Override
    protected BaseMapper getChildMapper() {
        return positionMapper;
    }

    @Override
    protected String getChildReference() {
        return childReferenceName;
    }
}



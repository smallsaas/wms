package com.jfeat.am.module.organization.services.crud.service.impl;

import com.baomidou.mybatisplus.mapper.BaseMapper;
import com.jfeat.am.common.crud.impl.CRUDServiceChildImpl;
import com.jfeat.am.module.organization.services.crud.service.PositionChildService;
import com.jfeat.am.module.organization.services.crud.service.ProfileChildService;
import com.jfeat.am.module.organization.services.persistence.mapper.StaffMapper;
import com.jfeat.am.module.organization.services.persistence.model.Position;
import com.jfeat.am.module.organization.services.persistence.model.Staff;
import com.jfeat.am.module.profile.services.persistence.dao.ProfileMapper;
import com.jfeat.am.module.profile.services.persistence.model.Profile;
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
public class ProfileChildServiceImpl extends CRUDServiceChildImpl<Staff, Profile> implements ProfileChildService {

    @Resource
    private ProfileMapper profileMapper;

    @Resource
    private StaffMapper staffMapper;
    

    private static final String childReferenceName = "profile_id";

    @Override
    protected BaseMapper<Staff> getMasterMapper() {
        return staffMapper;
    }

    @Override
    protected BaseMapper getChildMapper() {
        return profileMapper;
    }

    @Override
    protected String getChildReference() {
        return childReferenceName;
    }
}



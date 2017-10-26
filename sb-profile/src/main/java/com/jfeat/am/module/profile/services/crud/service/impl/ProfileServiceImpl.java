package com.jfeat.am.module.profile.services.crud.service.impl;
            
import com.baomidou.mybatisplus.mapper.BaseMapper;
import com.jfeat.am.module.profile.services.persistence.model.Profile;
import com.jfeat.am.module.profile.services.persistence.dao.ProfileMapper;
import com.jfeat.am.module.profile.services.crud.service.ProfileService;
import com.jfeat.am.common.crud.impl.CRUDServiceOnlyImpl;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;

/**
 * <p>
 *  implementation
 * </p>
 *
 * @author Code Generator
 * @since 2017-10-19
 */
@Service
public class ProfileServiceImpl  extends CRUDServiceOnlyImpl<Profile> implements ProfileService {


    @Resource
    private ProfileMapper profileMapper;

    @Override
    protected BaseMapper<Profile> getMasterMapper() {
        return profileMapper;
    }
}



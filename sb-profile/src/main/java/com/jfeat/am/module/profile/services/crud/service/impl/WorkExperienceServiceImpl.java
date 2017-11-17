package com.jfeat.am.module.profile.services.crud.service.impl;

import com.baomidou.mybatisplus.mapper.BaseMapper;
import com.jfeat.am.common.crud.impl.CRUDServiceOnlyImpl;
import com.jfeat.am.module.profile.services.crud.service.WorkExperienceService;
import com.jfeat.am.module.profile.services.persistence.dao.WorkExperienceMapper;
import com.jfeat.am.module.profile.services.persistence.model.WorkExperience;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;

/**
 * Created by J4cob on 2017/11/9.
 */
@Service
public class WorkExperienceServiceImpl extends CRUDServiceOnlyImpl<WorkExperience> implements WorkExperienceService{
    @Resource
    WorkExperienceMapper workExperienceMapper;

    @Override
    protected BaseMapper<WorkExperience> getMasterMapper() {
        return workExperienceMapper;
    }
}

package com.jfeat.am.module.profile.services.crud.service.impl;

import com.baomidou.mybatisplus.mapper.BaseMapper;
import com.jfeat.am.common.crud.impl.CRUDServiceOnlyImpl;
import com.jfeat.am.module.profile.services.crud.service.EduBackgroundService;
import com.jfeat.am.module.profile.services.persistence.dao.EduBackgroundMapper;
import com.jfeat.am.module.profile.services.persistence.model.EduBackground;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;

/**
 * Created by J4cob on 2017/11/9.
 */
@Service
public class EduBackgroundServiceImpl extends CRUDServiceOnlyImpl<EduBackground> implements EduBackgroundService{
    @Resource
    EduBackgroundMapper eduBackgroundMapper;

    @Override
    protected BaseMapper<EduBackground> getMasterMapper() {
        return eduBackgroundMapper;
    }
}

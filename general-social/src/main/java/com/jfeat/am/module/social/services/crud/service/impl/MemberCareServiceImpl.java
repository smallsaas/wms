package com.jfeat.am.module.social.services.crud.service.impl;

import com.baomidou.mybatisplus.mapper.BaseMapper;
import com.jfeat.am.common.crud.impl.CRUDServiceOnlyImpl;
import com.jfeat.am.module.social.services.crud.service.MemberCareService;
import com.jfeat.am.module.social.services.persistence.dao.MemberCareMapper;
import com.jfeat.am.module.social.services.persistence.model.MemberCare;
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
public class MemberCareServiceImpl extends CRUDServiceOnlyImpl<MemberCare> implements MemberCareService {


    @Resource
    private MemberCareMapper memberCareMapper;

    @Override
    protected BaseMapper<MemberCare> getMasterMapper() {
        return memberCareMapper;
    }
}



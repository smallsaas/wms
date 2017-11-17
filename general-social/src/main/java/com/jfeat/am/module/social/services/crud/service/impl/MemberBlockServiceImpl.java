package com.jfeat.am.module.social.services.crud.service.impl;

import com.baomidou.mybatisplus.mapper.BaseMapper;
import com.jfeat.am.common.crud.impl.CRUDServiceOnlyImpl;
import com.jfeat.am.module.social.services.crud.service.MemberBlockService;
import com.jfeat.am.module.social.services.persistence.dao.MemberBlockMapper;
import com.jfeat.am.module.social.services.persistence.model.MemberBlock;
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
public class MemberBlockServiceImpl extends CRUDServiceOnlyImpl<MemberBlock> implements MemberBlockService {


    @Resource
    private MemberBlockMapper memberBlockMapper;

    @Override
    protected BaseMapper<MemberBlock> getMasterMapper() {
        return memberBlockMapper;
    }
}



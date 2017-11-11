package com.jfeat.am.module.social.services.crud.service.impl;

import com.baomidou.mybatisplus.mapper.BaseMapper;
import com.jfeat.am.common.crud.impl.CRUDServiceOnlyImpl;
import com.jfeat.am.module.social.services.crud.service.MemberTrendsService;
import com.jfeat.am.module.social.services.persistence.dao.MemberTrendsMapper;
import com.jfeat.am.module.social.services.persistence.model.MemberTrends;
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
public class MemberTrendsImpl extends CRUDServiceOnlyImpl<MemberTrends> implements MemberTrendsService {


    @Resource
    private MemberTrendsMapper memberTrendsMapper;

    @Override
    protected BaseMapper<MemberTrends> getMasterMapper() {
        return memberTrendsMapper;
    }
}



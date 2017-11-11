package com.jfeat.am.module.social.services.crud.service.impl;

import com.baomidou.mybatisplus.mapper.BaseMapper;
import com.jfeat.am.common.crud.impl.CRUDServiceOnlyImpl;
import com.jfeat.am.module.social.services.crud.service.MemberBlackService;
import com.jfeat.am.module.social.services.persistence.dao.MemberBlackMapper;
import com.jfeat.am.module.social.services.persistence.model.MemberBlack;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;

/**
 * Created by J4cob on 2017/11/9.
 */
@Service
public class MemberBlackServiceImpl extends CRUDServiceOnlyImpl<MemberBlack> implements MemberBlackService {
    @Resource
    private MemberBlackMapper memberBlackMapper;


    @Override
    protected BaseMapper<MemberBlack> getMasterMapper() {
        return memberBlackMapper;
    }
}

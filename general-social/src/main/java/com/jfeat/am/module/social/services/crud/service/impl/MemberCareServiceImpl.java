package com.jfeat.am.module.social.services.crud.service.impl;

import com.baomidou.mybatisplus.mapper.BaseMapper;
import com.baomidou.mybatisplus.mapper.EntityWrapper;
import com.baomidou.mybatisplus.mapper.Wrapper;
import com.baomidou.mybatisplus.plugins.Page;
import com.jfeat.am.common.crud.impl.CRUDServiceOnlyImpl;
import com.jfeat.am.module.social.services.crud.service.MemberCareService;
import com.jfeat.am.module.social.services.domain.dao.QueryMemberCareDao;
import com.jfeat.am.module.social.services.persistence.dao.MemberCareMapper;
import com.jfeat.am.module.social.services.persistence.model.MemberCare;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;

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
    @Resource
    private QueryMemberCareDao queryMemberCareDao;

    @Override
    protected BaseMapper<MemberCare> getMasterMapper() {
        return memberCareMapper;
    }

    @Override
    public List<MemberCare> queryMemberCareByUserIdAndFollowedId(Long userId, Long followerId) {
        return queryMemberCareDao.selectList( new EntityWrapper<MemberCare>().eq("member_id", userId).eq("follower_id", followerId));
    }

    @Override
    public List<MemberCare> queryMemberCareWithUserByResId(Page<MemberCare> page, Long id) {
        return queryMemberCareDao.queryMemberCareWithUserByResId(page,id);
    }
}



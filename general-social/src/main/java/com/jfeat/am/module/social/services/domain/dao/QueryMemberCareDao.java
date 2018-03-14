package com.jfeat.am.module.social.services.domain.dao;

import com.baomidou.mybatisplus.mapper.Wrapper;
import com.jfeat.am.module.social.services.persistence.model.MemberCare;

import com.baomidou.mybatisplus.plugins.Page;
import com.baomidou.mybatisplus.mapper.BaseMapper;
import org.apache.ibatis.annotations.Param;
import java.util.List;

/**
 * Created by Code Generator on 2017-11-11
 */
public interface QueryMemberCareDao extends BaseMapper<MemberCare> {
    List<MemberCare> findMemberCarePage(Page<MemberCare> page,MemberCare membercare);

    List<MemberCare> queryMemberCareWithUserByResId(Page<MemberCare> page,@Param("id")Long id);

    List<MemberCare> queryMemberCareByUserId(Page<MemberCare> page,@Param("id")Long id);


}
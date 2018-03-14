package com.jfeat.am.module.social.services.crud.service;

import com.baomidou.mybatisplus.mapper.Wrapper;
import com.baomidou.mybatisplus.plugins.Page;
import com.jfeat.am.common.crud.CRUDServiceOnly;
import com.jfeat.am.module.social.services.persistence.model.MemberCare;
import org.apache.ibatis.annotations.Param;

import java.util.List;


/**
 * <p>
 *  service interface
 * </p>
 *
 * @author Code Generator
 * @since 2017-10-19
 */

public interface MemberCareService extends CRUDServiceOnly<MemberCare> {

    List<MemberCare> queryMemberCareByUserIdAndFollowedId(Long userId,Long followerId);
    List<MemberCare> queryMemberCareWithUserByResId(Page<MemberCare> page,Long id);
    List<MemberCare> queryMemberCareByUserId(Page<MemberCare> page,@Param("id")Long id);

}

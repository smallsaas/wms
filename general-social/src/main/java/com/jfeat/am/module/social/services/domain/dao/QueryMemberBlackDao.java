package com.jfeat.am.module.social.services.domain.dao;

import com.jfeat.am.module.social.services.persistence.model.MemberBlack;

import com.baomidou.mybatisplus.plugins.Page;
import com.baomidou.mybatisplus.mapper.BaseMapper;
import org.apache.ibatis.annotations.Param;
import java.util.List;

/**
 * Created by Code Generator on 2017-11-11
 */
public interface QueryMemberBlackDao extends BaseMapper<MemberBlack> {
    List<MemberBlack> findMemberBlackPage(Page<MemberBlack> page,MemberBlack memberblack);
}
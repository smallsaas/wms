package com.jfeat.am.module.social.services.domain.service;

import com.baomidou.mybatisplus.plugins.Page;
import com.jfeat.am.module.social.services.persistence.model.MemberCare;

import java.util.List;

/**
 * Created by vincent on 2017/10/19.
 */
public interface QueryMemberCareService {
    List<MemberCare> findMemberCarePage(Page<MemberCare> page, MemberCare membercare );
}
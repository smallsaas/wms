package com.jfeat.am.module.social.services.domain.service;

import com.baomidou.mybatisplus.plugins.Page;
import com.jfeat.am.module.social.services.persistence.model.MemberTrends;

import java.util.List;

/**
 * Created by vincent on 2017/10/19.
 */
public interface QueryMemberTrendsService {
    List<MemberTrends> findMemberTrendsPage(Page<MemberTrends> page, MemberTrends membertrends );
}
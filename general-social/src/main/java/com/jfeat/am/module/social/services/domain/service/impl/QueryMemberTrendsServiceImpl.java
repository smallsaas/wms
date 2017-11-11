package com.jfeat.am.module.social.services.domain.service.impl;

import com.baomidou.mybatisplus.plugins.Page;
import com.jfeat.am.module.social.services.domain.dao.QueryMemberTrendsDao;
import com.jfeat.am.module.social.services.domain.service.QueryMemberTrendsService;
import com.jfeat.am.module.social.services.persistence.model.MemberTrends;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;

/**
 * <p>
 *  服务实现类
 * </p>
 *
 * @author admin
 * @since 2017-10-16
 */
@Service
public class QueryMemberTrendsServiceImpl implements QueryMemberTrendsService {

    @Resource
    QueryMemberTrendsDao queryMemberTrendsDao;

    @Override
    public List<MemberTrends> findMemberTrendsPage(Page<MemberTrends> page, MemberTrends membertrends) {
        return queryMemberTrendsDao.findMemberTrendsPage(page, membertrends);
    }
}

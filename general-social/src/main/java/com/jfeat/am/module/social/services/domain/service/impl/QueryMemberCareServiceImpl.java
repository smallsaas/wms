package com.jfeat.am.module.social.services.domain.service.impl;

import com.baomidou.mybatisplus.plugins.Page;
import com.jfeat.am.module.social.services.domain.dao.QueryMemberCareDao;
import com.jfeat.am.module.social.services.domain.service.QueryMemberCareService;
import com.jfeat.am.module.social.services.persistence.model.MemberCare;
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
public class QueryMemberCareServiceImpl implements QueryMemberCareService {

    @Resource
    QueryMemberCareDao queryMemberCareDao;

    @Override
    public List<MemberCare> findMemberCarePage(Page<MemberCare> page, MemberCare membercare) {
        return queryMemberCareDao.findMemberCarePage(page, membercare);
    }
}

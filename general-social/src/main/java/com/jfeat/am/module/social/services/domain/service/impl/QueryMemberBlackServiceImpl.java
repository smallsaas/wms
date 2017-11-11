package com.jfeat.am.module.social.services.domain.service.impl;

import com.baomidou.mybatisplus.plugins.Page;
import com.jfeat.am.module.social.services.domain.dao.QueryMemberBlackDao;
import com.jfeat.am.module.social.services.domain.service.QueryMemberBlackService;
import com.jfeat.am.module.social.services.persistence.model.MemberBlack;
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
public class QueryMemberBlackServiceImpl implements QueryMemberBlackService {

    @Resource
    QueryMemberBlackDao queryMemberBlackDao;

    @Override
    public List<MemberBlack> findMemberBlackPage(Page<MemberBlack> page, MemberBlack memberblack) {
        return queryMemberBlackDao.findMemberBlackPage(page, memberblack);
    }
}

package com.jfeat.am.module.social.services.domain.service.impl;

import com.baomidou.mybatisplus.plugins.Page;
import com.jfeat.am.module.social.services.domain.dao.QueryMemberBlockDao;
import com.jfeat.am.module.social.services.domain.service.QueryMemberBlockService;
import com.jfeat.am.module.social.services.persistence.model.MemberBlock;
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
public class QueryMemberBlockServiceImpl implements QueryMemberBlockService {

    @Resource
    QueryMemberBlockDao queryMemberBlockDao;

    @Override
    public List<MemberBlock> findMemberBlockPage(Page<MemberBlock> page, MemberBlock memberblock) {
        return queryMemberBlockDao.findMemberBlockPage(page, memberblock);
    }
}

package com.jfeat.am.module.social.services.domain.service.impl;

import com.baomidou.mybatisplus.plugins.Page;
import com.jfeat.am.module.social.services.domain.dao.QueryMemberReportTypeDao;
import com.jfeat.am.module.social.services.domain.service.QueryMemberReportTypeService;
import com.jfeat.am.module.social.services.persistence.model.MemberReportType;
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
public class QueryMemberReportTypeServiceImpl implements QueryMemberReportTypeService {

    @Resource
    QueryMemberReportTypeDao queryMemberReportTypeDao;

    @Override
    public List<MemberReportType> findMemberReportTypePage(Page<MemberReportType> page, MemberReportType memberreporttype) {
        return queryMemberReportTypeDao.findMemberReportTypePage(page, memberreporttype);
    }
}

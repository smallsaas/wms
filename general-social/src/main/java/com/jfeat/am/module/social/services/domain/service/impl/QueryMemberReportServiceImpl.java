package com.jfeat.am.module.social.services.domain.service.impl;

import com.baomidou.mybatisplus.plugins.Page;
import com.jfeat.am.module.social.services.domain.dao.QueryMemberReportDao;
import com.jfeat.am.module.social.services.domain.service.QueryMemberReportService;
import com.jfeat.am.module.social.services.persistence.model.MemberReport;
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
public class QueryMemberReportServiceImpl implements QueryMemberReportService {

    @Resource
    QueryMemberReportDao queryMemberReportDao;

    @Override
    public List<MemberReport> findMemberReportPage(Page<MemberReport> page, MemberReport memberreport) {
        return queryMemberReportDao.findMemberReportPage(page, memberreport);
    }
}

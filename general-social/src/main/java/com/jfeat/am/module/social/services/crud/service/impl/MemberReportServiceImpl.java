package com.jfeat.am.module.social.services.crud.service.impl;

import com.baomidou.mybatisplus.mapper.BaseMapper;
import com.jfeat.am.common.crud.impl.CRUDServiceOnlyImpl;
import com.jfeat.am.module.social.services.crud.service.MemberReportService;
import com.jfeat.am.module.social.services.persistence.dao.MemberReportMapper;
import com.jfeat.am.module.social.services.persistence.model.MemberReport;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;

/**
 * <p>
 *  implementation
 * </p>
 *
 * @author Code Generator
 * @since 2017-10-19
 */
@Service
public class MemberReportServiceImpl extends CRUDServiceOnlyImpl<MemberReport> implements MemberReportService {


    @Resource
    private MemberReportMapper memberReportMapper;

    @Override
    protected BaseMapper<MemberReport> getMasterMapper() {
        return memberReportMapper;
    }
}



package com.jfeat.am.module.social.services.crud.service.impl;

import com.baomidou.mybatisplus.mapper.BaseMapper;
import com.jfeat.am.common.crud.impl.CRUDServiceOnlyImpl;
import com.jfeat.am.module.social.services.crud.service.MemberReportTypeService;
import com.jfeat.am.module.social.services.persistence.dao.MemberReportTypeMapper;
import com.jfeat.am.module.social.services.persistence.model.MemberReportType;
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
public class MemberReportTypeImpl extends CRUDServiceOnlyImpl<MemberReportType> implements MemberReportTypeService {


    @Resource
    private MemberReportTypeMapper memberReportTypeMapper;

    @Override
    protected BaseMapper<MemberReportType> getMasterMapper() {
        return memberReportTypeMapper;
    }
}



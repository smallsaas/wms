package com.jfeat.am.module.social.services.domain.service;

import com.baomidou.mybatisplus.plugins.Page;
import com.jfeat.am.module.social.services.persistence.model.MemberReportType;

import java.util.List;

/**
 * Created by vincent on 2017/10/19.
 */
public interface QueryMemberReportTypeService {
    List<MemberReportType> findMemberReportTypePage(Page<MemberReportType> page, MemberReportType memberreporttype );
}
package com.jfeat.am.module.social.services.crud.service;

import com.jfeat.am.common.crud.CRUDServiceOnly;
import com.jfeat.am.module.social.services.domain.model.MemberReportModel;
import com.jfeat.am.module.social.services.persistence.model.MemberReport;

import java.util.List;


/**
 * <p>
 * service interface
 * </p>
 *
 * @author Code Generator
 * @since 2017-10-19
 */

public interface MemberReportService extends CRUDServiceOnly<MemberReport> {

    List<MemberReportModel> returnReportCauseNameList();

    MemberReportModel returnReportCauseName(long id);
}

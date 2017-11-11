package com.jfeat.am.module.social.services.domain.model;
import com.jfeat.am.module.social.services.persistence.model.MemberReport;
/**
 * Created by Code Generator on 2017-11-11
 */
public class MemberReportModel extends MemberReport{
    String causeName;

    public String getCauseName() {
        return causeName;
    }

    public void setCauseName(String causeName) {
        this.causeName = causeName;
    }
}

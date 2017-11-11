package com.jfeat.am.module.social.services.crud.service.impl;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.baomidou.mybatisplus.mapper.BaseMapper;
import com.baomidou.mybatisplus.mapper.EntityWrapper;
import com.jfeat.am.common.crud.impl.CRUDServiceOnlyImpl;
import com.jfeat.am.module.social.services.crud.service.MemberReportService;
import com.jfeat.am.module.social.services.domain.model.MemberReportModel;
import com.jfeat.am.module.social.services.persistence.dao.MemberReportMapper;
import com.jfeat.am.module.social.services.persistence.dao.MemberReportTypeMapper;
import com.jfeat.am.module.social.services.persistence.model.MemberReport;
import com.jfeat.am.module.social.services.persistence.model.MemberReportType;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.ArrayList;
import java.util.List;

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
    @Resource
    MemberReportTypeMapper reportTypeMapper;

    @Override
    protected BaseMapper<MemberReport> getMasterMapper() {
        return memberReportMapper;
    }

    public List<MemberReportModel> returnReportCauseNameList(){
        List<MemberReport> reports = memberReportMapper.selectList(new EntityWrapper<>());
        List<MemberReportModel> models = new ArrayList<>();
        for(MemberReport report : reports){
            JSONObject reportObj = JSON.parseObject(JSON.toJSONString(report));
            MemberReportType type = reportTypeMapper.selectById(report.getCauseId());
            reportObj.put("causeName",type.getName());
            MemberReportModel model = JSON.parseObject(JSON.toJSONString(reportObj),MemberReportModel.class);
            models.add(model);
        }
        return models;
    }

    public MemberReportModel returnReportCauseName(long id){
        MemberReport report = memberReportMapper.selectById(id);
        JSONObject reportObj = JSON.parseObject(JSON.toJSONString(report));
        MemberReportType type = reportTypeMapper.selectById(report.getCauseId());
        reportObj.put("causeName",type.getName());
        MemberReportModel model = JSON.parseObject(JSON.toJSONString(reportObj),MemberReportModel.class);
        return model;
    }
}



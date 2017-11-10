package com.jfeat.am.module.profile.services.crud.service.impl;
            
import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.baomidou.mybatisplus.mapper.BaseMapper;
import com.baomidou.mybatisplus.mapper.EntityWrapper;
import com.jfeat.am.common.crud.FIELD;
import com.jfeat.am.common.crud.impl.CRUDServiceOverModelImpl;
import com.jfeat.am.common.exception.BizExceptionEnum;
import com.jfeat.am.common.exception.BusinessException;
import com.jfeat.am.module.profile.services.domain.ResumeModel;
import com.jfeat.am.module.profile.services.persistence.dao.EduBackgroundMapper;
import com.jfeat.am.module.profile.services.persistence.dao.WorkExperienceMapper;
import com.jfeat.am.module.profile.services.persistence.model.EduBackground;
import com.jfeat.am.module.profile.services.persistence.model.Resume;
import com.jfeat.am.module.profile.services.persistence.dao.ResumeMapper;
import com.jfeat.am.module.profile.services.crud.service.ResumeService;
import com.jfeat.am.common.crud.impl.CRUDServiceOnlyImpl;
import com.jfeat.am.module.profile.services.persistence.model.WorkExperience;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
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
public class ResumeServiceImpl  extends CRUDServiceOverModelImpl<Resume,ResumeModel>
        implements ResumeService {


    @Resource
    private ResumeMapper resumeMapper;
    @Resource
    EduBackgroundMapper eduBackgroundMapper;
    @Resource
    WorkExperienceMapper workExperienceMapper;

    @Override
    protected BaseMapper<Resume> getMasterMapper() {
        return resumeMapper;
    }

    @Override
    protected Class<Resume> masterClassName() {
        return Resume.class;
    }

    @Override
    protected Class<ResumeModel> modelClassName() {
        return ResumeModel.class;
    }

    /*
    *   优化/resume与workExperience eduBackground 一对多的关系
    * */
    public ResumeModel retrieveResume(long id){
        Resume resume = resumeMapper.selectById(id);
        List<EduBackground> eduBackgrounds = eduBackgroundMapper.selectList(new EntityWrapper<EduBackground>().eq("resume_id",id));
        List<WorkExperience> workExperiences = workExperienceMapper.selectList(new EntityWrapper<WorkExperience>().eq("resume_id",id));
        JSONObject resumeObj = JSON.parseObject(JSON.toJSONString(resume));
        resumeObj.put("eduBackgrounds",eduBackgrounds);
        resumeObj.put("workExperiences",workExperiences);
        ResumeModel model = JSON.parseObject(JSON.toJSONString(resumeObj),ResumeModel.class);
        return model;
    }

    static final String eduBackgroundFieldName = "resume_id";
    static final String eduBackgroundKeyName = "eduBackgrounds";

    static final String workExperienceFieldName = "resume_id";
    static final String workExperienceKeyName = "workExperiences";

    @Override
    protected String[] slaveFieldNames() {
        return new String[]{eduBackgroundFieldName, workExperienceKeyName};
    }

    @Override
    protected String[] childFieldNames() {
        return new String[0];
    }

    @Override
    protected FIELD onSlaveFieldItem(String field) {
        if(field.compareTo(eduBackgroundKeyName)==0) {
            FIELD _field = new FIELD();

            _field.setItemKeyName(field);
            _field.setItemFieldName(eduBackgroundKeyName);
            _field.setItemClassName(EduBackground.class);
            _field.setItemMapper(eduBackgroundMapper);

            return _field;
        }else if(field.compareTo(workExperienceKeyName)==0){
            FIELD _field = new FIELD();

            _field.setItemKeyName(field);
            _field.setItemFieldName(workExperienceFieldName);
            _field.setItemClassName(WorkExperience.class);
            _field.setItemMapper(workExperienceMapper);

            return _field;
        }


        throw new BusinessException(BizExceptionEnum.OUT_OF_RANGE);
    }

    @Override
    protected FIELD onChildFieldItem(String s) {
        return null;
    }

}



package com.jfeat.am.module.profile.services.crud.service.impl;
            
import com.baomidou.mybatisplus.mapper.BaseMapper;
import com.jfeat.am.module.profile.services.persistence.model.Resume;
import com.jfeat.am.module.profile.services.persistence.dao.ResumeMapper;
import com.jfeat.am.module.profile.services.crud.service.ResumeService;
import com.jfeat.am.common.crud.impl.CRUDServiceOnlyImpl;
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
public class ResumeServiceImpl  extends CRUDServiceOnlyImpl<Resume> implements ResumeService {


    @Resource
    private ResumeMapper resumeMapper;

    @Override
    protected BaseMapper<Resume> getMasterMapper() {
        return resumeMapper;
    }
}



package com.jfeat.am.module.feedback.services.crud.service.impl;
        
import com.baomidou.mybatisplus.mapper.BaseMapper;
import com.jfeat.am.common.crud.FIELD;
import com.jfeat.am.common.crud.impl.CRUDServiceOverModelImpl;
import com.jfeat.am.module.feedback.services.domain.model.TFeedbackModel;
import com.jfeat.am.module.feedback.services.persistence.model.TFeedback;
import com.jfeat.am.module.feedback.services.persistence.dao.TFeedbackMapper;
import com.jfeat.am.module.feedback.services.crud.service.TFeedbackService;
import com.jfeat.am.common.crud.impl.CRUDServiceOnlyImpl;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;

/**
 * <p>
 *  服务实现类
 * </p>
 *
 * @author admin
 * @since 2017-11-28
 */
@Deprecated
@Service
public class TFeedbackServiceImpl extends CRUDServiceOverModelImpl<TFeedback,TFeedbackModel> implements TFeedbackService {


    @Resource
    private TFeedbackMapper tFeedbackMapper;

    @Override
    protected BaseMapper<TFeedback> getMasterMapper() {
        return tFeedbackMapper;
    }

    @Override
    protected String[] slaveFieldNames() {
        return new String[0];
    }

    @Override
    protected String[] childFieldNames() {
        return new String[0];
    }

    @Override
    protected FIELD onSlaveFieldItem(String s) {
        return null;
    }

    @Override
    protected FIELD onChildFieldItem(String s) {
        return null;
    }

    @Override
    protected Class<TFeedback> masterClassName() {
        return null;
    }

    @Override
    protected Class<TFeedbackModel> modelClassName() {
        return null;
    }
}



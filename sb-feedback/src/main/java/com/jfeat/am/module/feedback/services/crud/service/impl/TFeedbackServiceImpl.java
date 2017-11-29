package com.jfeat.am.module.feedback.services.crud.service.impl;
        
import com.baomidou.mybatisplus.mapper.BaseMapper;
import com.baomidou.mybatisplus.plugins.Page;
import com.jfeat.am.common.crud.FIELD;
import com.jfeat.am.common.crud.impl.CRUDServiceOverModelImpl;
import com.jfeat.am.module.feedback.services.domain.dao.QueryTFeedbackDao;
import com.jfeat.am.module.feedback.services.domain.model.TFeedbackModel;
import com.jfeat.am.module.feedback.services.persistence.dao.TFeedbackImageMapper;
import com.jfeat.am.module.feedback.services.persistence.model.TFeedback;
import com.jfeat.am.module.feedback.services.persistence.dao.TFeedbackMapper;
import com.jfeat.am.module.feedback.services.crud.service.TFeedbackService;
import com.jfeat.am.common.crud.impl.CRUDServiceOnlyImpl;
import com.jfeat.am.module.feedback.services.persistence.model.TFeedbackImage;
import org.hibernate.validator.internal.constraintvalidators.bv.past.PastValidatorForReadableInstant;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;
import java.util.Map;

/**
 * <p>
 *  服务实现类
 * </p>
 *
 * @author admin
 * @since 2017-11-28
 */
@Service
public class TFeedbackServiceImpl extends CRUDServiceOverModelImpl<TFeedback,TFeedbackModel> implements TFeedbackService {


    @Resource
    private TFeedbackMapper tFeedbackMapper;
    @Resource
    private TFeedbackImageMapper tFeedbackImageMapper;
    @Resource
    private QueryTFeedbackDao queryTFeedbackDao;

    private static final String ItemKeyName = "tFeedbackImages";
    private static final String ItemFieldName = TFeedbackImage.FEEDBACK_ID;



    @Override
    protected BaseMapper<TFeedback> getMasterMapper() {
        return tFeedbackMapper;
    }

    @Override
    protected String[] slaveFieldNames() {
        return new String[]{ItemKeyName};
    }

    @Override
    protected String[] childFieldNames() {
        return new String[0];
    }

    @Override
    protected FIELD onSlaveFieldItem(String s) {
        FIELD _field = new FIELD();
        _field.setItemKeyName(s);
        _field.setItemFieldName(ItemFieldName);
        _field.setItemClassName(TFeedbackImage.class);
        _field.setItemMapper(tFeedbackImageMapper);
        return _field;
    }

    @Override
    protected FIELD onChildFieldItem(String s) {
        return null;
    }

    @Override
    protected Class<TFeedback> masterClassName() {
        return TFeedback.class;
    }

    @Override
    protected Class<TFeedbackModel> modelClassName() {
        return TFeedbackModel.class;
    }

    @Override
    public List<Map<String, Object>> findTFeedbacks(Page page, Long id, Long userId, Integer unread, String content, String startTime, String endTime) {
        return queryTFeedbackDao.findTFeedbacks(page,id,userId,unread,content,startTime,endTime);
    }
}



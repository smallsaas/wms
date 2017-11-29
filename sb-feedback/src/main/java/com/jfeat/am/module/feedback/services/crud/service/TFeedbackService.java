package com.jfeat.am.module.feedback.services.crud.service;
        
import com.baomidou.mybatisplus.plugins.Page;
import com.jfeat.am.common.crud.CRUDServiceOverModel;
import com.jfeat.am.module.feedback.services.domain.model.TFeedbackModel;
import com.jfeat.am.module.feedback.services.persistence.model.TFeedback;

import java.util.List;
import java.util.Map;


/**
 * <p>
 *  服务类
 * </p>
 *
 * @author admin
 * @since 2017-11-28
 */



public interface TFeedbackService extends CRUDServiceOverModel<TFeedback,TFeedbackModel> {

    List<Map<String,Object>> findTFeedbacks(Page page,Long id,Long userId,Integer unread,String content,String startTime,String endTime);

}

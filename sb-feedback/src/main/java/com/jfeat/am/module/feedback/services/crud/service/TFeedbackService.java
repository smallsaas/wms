package com.jfeat.am.module.feedback.services.crud.service;

import com.baomidou.mybatisplus.plugins.Page;
import com.jfeat.am.common.crud.CRUDServiceOverModel;
import com.jfeat.am.module.feedback.services.domain.model.TFeedbackModel;
import com.jfeat.am.module.feedback.services.persistence.model.TFeedback;

import java.util.List;
import java.util.Map;


/**
 * <p>
 * 服务类
 * </p>
 *
 * @author admin
 * @since 2017-11-28
 */


public interface TFeedbackService extends CRUDServiceOverModel<TFeedback, TFeedbackModel> {

    List<TFeedbackModel> findFeedback(Page<TFeedbackModel> page, String name, Integer unread);
    // List  include createUser name
    List<TFeedbackModel> findTFeedbacks(Page page,Integer unread, String createName, String startTime, String endTime);


    }

package com.jfeat.am.module.feedback.services.domain.dao;


import com.baomidou.mybatisplus.plugins.Page;
import com.baomidou.mybatisplus.mapper.BaseMapper;
import com.jfeat.am.module.feedback.services.persistence.model.TFeedback;
import org.apache.ibatis.annotations.Param;
import java.util.List;

public interface QueryTFeedbackDao  extends BaseMapper<TFeedback> {

    List<TFeedback> findTFeedbacks(Page<TFeedback> page,
            @Param("status") String status);

}
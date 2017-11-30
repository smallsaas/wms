package com.jfeat.am.module.feedback.services.domain.dao;


import com.baomidou.mybatisplus.plugins.Page;
import com.baomidou.mybatisplus.mapper.BaseMapper;
import com.jfeat.am.module.feedback.services.persistence.model.TFeedback;
import org.apache.ibatis.annotations.Param;
import java.util.List;
import java.util.Map;

public interface QueryTFeedbackDao  extends BaseMapper<TFeedback> {

    List<Map<String,Object>> findTFeedbacks(Page page,@Param("id") Long id,@Param("userId") Long userId,@Param("unread") Integer unread,@Param("content") String content,@Param("startTime") String startTime,@Param("endTime") String endTime);

}
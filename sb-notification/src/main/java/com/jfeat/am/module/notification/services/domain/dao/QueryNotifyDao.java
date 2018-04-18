package com.jfeat.am.module.notification.services.domain.dao;


import com.baomidou.mybatisplus.plugins.Page;
import com.baomidou.mybatisplus.mapper.BaseMapper;
import com.jfeat.am.module.notification.services.persistence.model.Notify;
import org.apache.ibatis.annotations.Param;

import java.util.Date;
import java.util.List;
import java.util.Map;

public interface QueryNotifyDao  extends BaseMapper<Notify> {

    List<Notify> findNotifys(Page<Notify> page,
            @Param("status") String status);

    List<Map<String,Object>> queryNotifyByUserIdAndIsReadAndTargetType(Page<Map<String,Object>> page, @Param("userId") Long userId,@Param("targetType") String targetType, @Param("isRead") Integer isRead);

    List<Notify> queryNotify(@Param("targetId") Long targetId,@Param("targetType") String targetType,@Param("action") String action,@Param("createAt") Date createAt);
}
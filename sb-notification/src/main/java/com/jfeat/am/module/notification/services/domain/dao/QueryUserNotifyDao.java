package com.jfeat.am.module.notification.services.domain.dao;


import com.baomidou.mybatisplus.plugins.Page;
import com.baomidou.mybatisplus.mapper.BaseMapper;
import com.jfeat.am.module.notification.services.persistence.model.UserNotify;
import org.apache.ibatis.annotations.Param;

import java.util.List;
import java.util.Map;

public interface QueryUserNotifyDao extends BaseMapper<UserNotify> {

    List<UserNotify> findUserNotifys(Page<UserNotify> page,
                                     @Param("status") String status);


    List<Map<String,Object>> getUnReadCountByUserIdAndIsRead(@Param("userId") Long userId, @Param("isRead") Integer isRead);

    Integer updateUserNotifyByUserId(@Param("userId") Long userId);
}
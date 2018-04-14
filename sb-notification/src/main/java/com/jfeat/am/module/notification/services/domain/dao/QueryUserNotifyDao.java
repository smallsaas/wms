package com.jfeat.am.module.notification.services.domain.dao;


import com.baomidou.mybatisplus.plugins.Page;
import com.baomidou.mybatisplus.mapper.BaseMapper;
import com.jfeat.am.module.notification.services.persistence.model.UserNotify;
import org.apache.ibatis.annotations.Param;
import java.util.List;

public interface QueryUserNotifyDao  extends BaseMapper<UserNotify> {

    List<UserNotify> findUserNotifys(Page<UserNotify> page,
            @Param("status") String status);

}
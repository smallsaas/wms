package com.jfeat.am.module.notification.services.domain.dao;


import com.baomidou.mybatisplus.plugins.Page;
import com.baomidou.mybatisplus.mapper.BaseMapper;
import com.jfeat.am.module.notification.services.persistence.model.Notify;
import org.apache.ibatis.annotations.Param;
import java.util.List;

public interface QueryNotifyDao  extends BaseMapper<Notify> {

    List<Notify> findNotifys(Page<Notify> page,
            @Param("status") String status);

}
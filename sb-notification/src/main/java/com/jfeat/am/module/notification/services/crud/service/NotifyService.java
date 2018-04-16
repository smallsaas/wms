package com.jfeat.am.module.notification.services.crud.service;
        
import com.baomidou.mybatisplus.plugins.Page;
import com.jfeat.am.common.crud.CRUDServiceOnly;
import com.jfeat.am.module.notification.services.persistence.model.Notify;
import com.jfeat.am.module.notification.services.persistence.model.UserNotify;

import java.util.List;


/**
 * <p>
 *  服务类
 * </p>
 *
 * @author admin
 * @since 2018-04-14
 */



public interface NotifyService extends CRUDServiceOnly<Notify> {

    List<Notify> queryNotifyByUserIdAndIsReadAndTargetType(Page<Notify> page,Long userId, Integer isRead, String targetType);

//    创建订阅
    Boolean subscribe(Long userId, Long targetId, String targetType, List<String> actions);

    Boolean unsubscribe(Long userId, Long targetId, String targetType);

//    创建提醒
    Boolean createRemind(Long targetId, String targetType, String action, Long senderId, String content);

//    返回提醒
    List<UserNotify> pullRemind(Long userId);
}


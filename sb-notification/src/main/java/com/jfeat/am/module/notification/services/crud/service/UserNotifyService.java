package com.jfeat.am.module.notification.services.crud.service;
        
import com.jfeat.am.common.crud.CRUDServiceOnly;
import com.jfeat.am.module.notification.services.persistence.model.UserNotify;

import java.util.List;
import java.util.Map;


/**
 * <p>
 *  服务类
 * </p>
 *
 * @author admin
 * @since 2018-04-14
 */



public interface UserNotifyService extends CRUDServiceOnly<UserNotify> {

    List<Map<String,Object>> queryTypeCount(Long userId, Integer isRead);

    Integer read(Long id);

    //    创建提醒
    Boolean createRemind(Long targetId, String targetType, String action, Long senderId, String content);

    //    返回提醒
    List<UserNotify> pullRemind(Long userId);
}

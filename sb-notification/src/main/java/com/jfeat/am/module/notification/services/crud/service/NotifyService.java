package com.jfeat.am.module.notification.services.crud.service;
        
import com.baomidou.mybatisplus.plugins.Page;
import com.jfeat.am.common.crud.CRUDServiceOnly;
import com.jfeat.am.module.notification.services.domain.model.NotifyModel;
import com.jfeat.am.module.notification.services.persistence.model.Notify;
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



public interface NotifyService extends CRUDServiceOnly<Notify> {

    List<NotifyModel> paginationNotifies(Page<NotifyModel> page, Long userId, String targetType, Integer isRead);

}


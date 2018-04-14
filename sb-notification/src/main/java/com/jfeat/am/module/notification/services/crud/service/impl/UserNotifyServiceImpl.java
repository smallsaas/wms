package com.jfeat.am.module.notification.services.crud.service.impl;
        
import com.baomidou.mybatisplus.mapper.BaseMapper;
import com.jfeat.am.module.notification.services.persistence.model.UserNotify;
import com.jfeat.am.module.notification.services.persistence.dao.UserNotifyMapper;
import com.jfeat.am.module.notification.services.crud.service.UserNotifyService;
import com.jfeat.am.common.crud.impl.CRUDServiceOnlyImpl;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;

/**
 * <p>
 *  服务实现类
 * </p>
 *
 * @author admin
 * @since 2018-04-14
 */
@Deprecated
@Service
public class UserNotifyServiceImpl extends CRUDServiceOnlyImpl<UserNotify>  implements UserNotifyService {


    @Resource
    private UserNotifyMapper userNotifyMapper;

    @Override
    protected BaseMapper<UserNotify> getMasterMapper() {
        return userNotifyMapper;
    }
}



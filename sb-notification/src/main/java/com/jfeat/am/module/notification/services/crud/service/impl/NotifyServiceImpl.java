package com.jfeat.am.module.notification.services.crud.service.impl;
        
import com.baomidou.mybatisplus.mapper.BaseMapper;
import com.jfeat.am.common.crud.CRUDServiceOnly;
import com.jfeat.am.module.notification.services.persistence.model.Notify;
import com.jfeat.am.module.notification.services.persistence.dao.NotifyMapper;
import com.jfeat.am.module.notification.services.crud.service.NotifyService;
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
public class NotifyServiceImpl extends CRUDServiceOnlyImpl<Notify> implements NotifyService {


    @Resource
    private NotifyMapper notifyMapper;

    @Override
    protected BaseMapper<Notify> getMasterMapper() {
        return notifyMapper;
    }
}



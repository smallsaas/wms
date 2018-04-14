package com.jfeat.am.module.notification.services.crud.service.impl;
        
import com.baomidou.mybatisplus.mapper.BaseMapper;
import com.jfeat.am.module.notification.services.persistence.model.Subscription;
import com.jfeat.am.module.notification.services.persistence.dao.SubscriptionMapper;
import com.jfeat.am.module.notification.services.crud.service.SubscriptionService;
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
@Service
public class SubscriptionServiceImpl extends CRUDServiceOnlyImpl<Subscription>  implements SubscriptionService {


    @Resource
    private SubscriptionMapper subscriptionMapper;

    @Override
    protected BaseMapper<Subscription> getMasterMapper() {
        return subscriptionMapper;
    }
}



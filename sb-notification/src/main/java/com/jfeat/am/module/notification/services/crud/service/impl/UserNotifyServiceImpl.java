package com.jfeat.am.module.notification.services.crud.service.impl;

import com.baomidou.mybatisplus.mapper.BaseMapper;
import com.baomidou.mybatisplus.mapper.EntityWrapper;
import com.jfeat.am.common.crud.impl.CRUDServiceOnlyImpl;
import com.jfeat.am.module.notification.constant.Const;
import com.jfeat.am.module.notification.constant.NotificationType;
import com.jfeat.am.module.notification.services.crud.service.UserNotifyService;
import com.jfeat.am.module.notification.services.domain.dao.QueryNotifyDao;
import com.jfeat.am.module.notification.services.domain.dao.QueryUserNotifyDao;
import com.jfeat.am.module.notification.services.persistence.dao.NotifyMapper;
import com.jfeat.am.module.notification.services.persistence.dao.SubscriptionMapper;
import com.jfeat.am.module.notification.services.persistence.dao.UserNotifyMapper;
import com.jfeat.am.module.notification.services.persistence.model.Notify;
import com.jfeat.am.module.notification.services.persistence.model.Subscription;
import com.jfeat.am.module.notification.services.persistence.model.UserNotify;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Map;

/**
 * <p>
 *  服务实现类
 * </p>
 *
 * @author admin
 * @since 2018-04-14
 */
@Service
public class UserNotifyServiceImpl extends CRUDServiceOnlyImpl<UserNotify> implements UserNotifyService {

    @Resource
    private QueryUserNotifyDao queryUserNotifyDao;
    @Resource
    private NotifyMapper notifyMapper;
    @Resource
    private QueryNotifyDao queryNotifyDao;
    @Resource
    private SubscriptionMapper subscriptionMapper;
    @Resource
    private UserNotifyMapper userNotifyMapper;

    @Override
    protected BaseMapper<UserNotify> getMasterMapper() {
        return userNotifyMapper;
    }

    @Override
    public List<Map<String,Object>> queryTypeCount(Long userId, Integer isRead) {
        return queryUserNotifyDao.queryTypeCount(userId,isRead);
    }

    @Override
    public Integer read(Long id) {
        return queryUserNotifyDao.read(id);
    }


    @Override
    public Boolean createRemind(Long targetId, String targetType, String action, Long senderId, String content) {
        Notify notify = new Notify();
        notify.setSenderId(senderId);
        notify.setAvatar("");
        notify.setName("");
        notify.setTargetType(targetType);
        notify.setTargetId(targetId);
        notify.setAction(action);
        notify.setContent(content);
        notify.setType(NotificationType.REMIND.toString());
        notify.setCreateTime(new Date());
        return notifyMapper.insert(notify) == 1;
    }

    @Override
    public List<UserNotify> pullRemind(Long userId) {
        List<Subscription> subscriptions = subscriptionMapper.selectList(
                new EntityWrapper<Subscription>().eq(Subscription.USER_ID, userId));
        List<Notify> list = new ArrayList<>();
        for (Subscription subscription : subscriptions) {
            List<Notify> notifies = queryNotifyDao.queryNotifies(
                    subscription.getTargetId(),
                    subscription.getTargetType(),
                    subscription.getAction(),
                    subscription.getCreatedAt());
            if (!notifies.isEmpty()) {
                list.addAll(notifies);
                subscription.setCreatedAt(new Date());
                subscriptionMapper.updateById(subscription);
            }
        }
        //关联新建UserNotify
        List<UserNotify> userNotifies = new ArrayList<>();
        for (Notify notify : list) {
            UserNotify userNotify = new UserNotify();
            userNotify.setUserId(userId);
            userNotify.setNotifyId(notify.getId());
            userNotify.setIsRead(Const.UN_READ);
            userNotify.setCreateTime(new Date());
            userNotifyMapper.insert(userNotify);
            userNotifies.add(userNotify);
        }
        return userNotifies;
    }
}



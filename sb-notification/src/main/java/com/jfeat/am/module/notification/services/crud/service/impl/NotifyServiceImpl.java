package com.jfeat.am.module.notification.services.crud.service.impl;
        
import com.baomidou.mybatisplus.mapper.BaseMapper;
import com.baomidou.mybatisplus.mapper.EntityWrapper;
import com.baomidou.mybatisplus.plugins.Page;
import com.google.common.collect.Lists;
import com.jfeat.am.module.notification.services.domain.dao.QueryNotifyDao;
import com.jfeat.am.module.notification.services.persistence.dao.SubscriptionMapper;
import com.jfeat.am.module.notification.services.persistence.dao.UserNotifyMapper;
import com.jfeat.am.module.notification.services.persistence.model.Notify;
import com.jfeat.am.module.notification.services.persistence.dao.NotifyMapper;
import com.jfeat.am.module.notification.services.crud.service.NotifyService;
import com.jfeat.am.common.crud.impl.CRUDServiceOnlyImpl;
import com.jfeat.am.module.notification.services.persistence.model.Subscription;
import com.jfeat.am.module.notification.services.persistence.model.UserNotify;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.Date;
import java.util.List;

/**
 * <p>
 *  服务实现类
 * </p>
 *
 * @author admin
 * @since 2018-04-14
 */
@Service
public class NotifyServiceImpl extends CRUDServiceOnlyImpl<Notify> implements NotifyService {


    @Resource
    private NotifyMapper notifyMapper;
    @Resource
    private QueryNotifyDao queryNotifyDao;
    @Resource
    private SubscriptionMapper subscriptionMapper;
    @Resource
    private UserNotifyMapper userNotifyMapper;

    @Override
    protected BaseMapper<Notify> getMasterMapper() {
        return notifyMapper;
    }

    @Override
    public List<Notify> queryNotifyByUserIdAndIsReadAndTargetType(Page<Notify> page,Long userId, Integer isRead, String targetType) {
        return queryNotifyDao.queryNotifyByUserIdAndIsReadAndTargetType(page,userId,isRead,targetType);
    }

    @Override
    public Boolean subscribe(Long userId, Long targetId, String targetType, List<String> actions) {
        for (String action:actions){
//            先删除防止重复订阅
            subscriptionMapper.delete(new EntityWrapper<Subscription>().eq(Subscription.USER_ID,userId)
                    .eq(Subscription.TARGET_TYPE,targetType)
                    .eq(Subscription.TARGET_ID,targetId)
                    .eq(Subscription.ACTION,action));

            Subscription subscription = new Subscription();
            subscription.setUserId(userId);
            subscription.setTargetId(targetId);
            subscription.setTargetType(targetType);
            subscription.setAction(action);
            subscription.setCreatedAt(new Date());
            subscriptionMapper.insert(subscription);
        }
        return true;
    }

    @Override
    public Boolean unsubscribe(Long userId, Long targetId, String targetType) {
        return subscriptionMapper.delete(new EntityWrapper<Subscription>()
                .eq(Subscription.USER_ID,userId)
                .eq(Subscription.TARGET_ID,targetId)
                .eq(Subscription.TARGET_TYPE,targetType)) > 0;
    }

    @Override
    public Boolean createRemind(Long targetId, String targetType, String action, Long senderId, String content) {
        Notify notify = new Notify();
        notify.setSenderId(senderId);
        notify.setTargetType(targetType);
        notify.setTargetId(targetId);
        notify.setAction(action);
        notify.setContent(content);
        notify.setType("REMIND");
        return notifyMapper.insert(notify) == 1;
    }

    @Override
    public List<UserNotify> pullRemind(Long userId) {
        List<Subscription>  subscriptions = subscriptionMapper.selectList(
                new EntityWrapper<Subscription>().eq(Subscription.USER_ID,userId));
        List<Notify> list = Lists.newArrayList();
        for (Subscription subscription:subscriptions){
            List<Notify> notifies = queryNotifyDao.queryNotify(
                    subscription.getTargetId(),subscription.getTargetType(),subscription.getAction(),subscription.getCreatedAt().toString());
            list.addAll(notifies);
        }
        //关联新建UserNotify
        List<UserNotify> userNotifies = Lists.newArrayList();
        for (Notify notify : list) {
            UserNotify userNotify = new UserNotify();
            userNotify.setUserId(userId);
            userNotify.setNotifyId(notify.getId());
            userNotifyMapper.insert(userNotify);
            userNotifies.add(userNotify);
        }
        return userNotifies;
    }
}



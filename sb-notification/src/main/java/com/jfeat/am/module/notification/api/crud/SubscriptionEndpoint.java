package com.jfeat.am.module.notification.api.crud;

import com.jfeat.am.common.annotation.Permission;
import com.jfeat.am.module.notification.api.permission.SubscriptionPermission;
import org.springframework.web.bind.annotation.*;

import com.jfeat.am.common.constant.tips.ErrorTip;
import com.jfeat.am.common.constant.tips.SuccessTip;
import com.jfeat.am.common.constant.tips.Tip;

import com.jfeat.am.module.notification.services.crud.service.SubscriptionService;
import com.jfeat.am.module.notification.services.persistence.model.Subscription;

import com.jfeat.am.common.controller.BaseController;

import javax.annotation.Resource;
import javax.validation.Valid;
import java.util.List;
import java.util.HashMap;

/**
 * <p>
 *  前端控制器
 * </p>
 *
 * @author admin
 * @since 2018-04-14
 */
@RestController
@RequestMapping("/api/notification/subscription")
public class SubscriptionEndpoint extends BaseController {


    @Resource
    private SubscriptionService subscriptionService;

    @PostMapping
    public Tip createSubscription(@RequestBody Subscription entity) {
            return SuccessTip.create(subscriptionService.createMaster(entity));
            }

    @GetMapping("/{id}")
    public Tip getSubscription(@PathVariable Long id) {
        return SuccessTip.create(subscriptionService.retrieveMaster(id));
    }



    @PutMapping("/{id}")
    public Tip updateSubscription(@PathVariable Long id, @RequestBody Subscription entity) {
        return SuccessTip.create(subscriptionService.updateMaster(entity));
    }

    @DeleteMapping("/{id}")
    public Tip deleteSubscription(@PathVariable Long id) {
        return SuccessTip.create(subscriptionService.deleteMaster(id));
    }

/*
    @GetMapping
    //此方法可能需要自行添加需要的参数,按需要使用
    public Tip querySubscriptions(Page page,
                @RequestParam(name = "pageNum", required = false, defaultValue = "1") Integer pageNum,
                @RequestParam(name = "pageSize", required = false, defaultValue = "5") Integer pageSize) {
            List<Subscription> records = subscriptionService.findSubscriptions(page);
            page.setCurrent(pageNum);
            page.setSize(pageSize);
            page.setRecords(records);

            return SuccessTip.create(page);
        }
        */
        @GetMapping
        @Permission({SubscriptionPermission.Subscription_VIEW})
        public Tip show(@RequestHeader("authorization") String token) {
            return SuccessTip.create();
        }
}

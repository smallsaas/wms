package com.jfeat.am.module.notification.api.crud;

import com.jfeat.am.common.annotation.Permission;
import com.jfeat.am.core.jwt.JWTKit;
import com.jfeat.am.module.notification.api.permission.UserNotifyPermission;
import com.jfeat.am.module.notification.services.crud.service.NotifyService;
import org.springframework.web.bind.annotation.*;

import com.jfeat.am.common.constant.tips.ErrorTip;
import com.jfeat.am.common.constant.tips.SuccessTip;
import com.jfeat.am.common.constant.tips.Tip;

import com.jfeat.am.module.notification.services.crud.service.UserNotifyService;
import com.jfeat.am.module.notification.services.persistence.model.UserNotify;

import com.jfeat.am.common.controller.BaseController;

import javax.annotation.Resource;
import javax.validation.Valid;
import java.util.List;
import java.util.HashMap;
import java.util.Map;

/**
 * <p>
 * 前端控制器
 * </p>
 *
 * @author admin
 * @since 2018-04-14
 */
@RestController
@RequestMapping("/api/notification/notification/user-notify")
public class UserNotifyEndpoint extends BaseController {


    @Resource
    private UserNotifyService userNotifyService;
    @Resource
    NotifyService notifyService;

    @PostMapping
    public Tip createUserNotify(@RequestBody UserNotify entity) {
        return SuccessTip.create(userNotifyService.createMaster(entity));
    }

    @GetMapping("/{id}")
    public Tip getUserNotify(@PathVariable Long id) {
        return SuccessTip.create(userNotifyService.retrieveMaster(id));
    }


    @PutMapping("/{id}")
    public Tip updateUserNotify(@PathVariable Long id, @RequestBody UserNotify entity) {
        return SuccessTip.create(userNotifyService.updateMaster(entity));
    }

    @DeleteMapping("/{id}")
    public Tip deleteUserNotify(@PathVariable Long id) {
        return SuccessTip.create(userNotifyService.deleteMaster(id));
    }

    /*
        @GetMapping
        //此方法可能需要自行添加需要的参数,按需要使用
        public Tip queryUserNotifys(Page page,
                    @RequestParam(name = "pageNum", required = false, defaultValue = "1") Integer pageNum,
                    @RequestParam(name = "pageSize", required = false, defaultValue = "5") Integer pageSize) {
                List<UserNotify> records = userNotifyService.findUserNotifys(page);
                page.setCurrent(pageNum);
                page.setSize(pageSize);
                page.setRecords(records);

                return SuccessTip.create(page);
            }
            */
    @GetMapping
    @Permission({UserNotifyPermission.UserNotify_VIEW})
    public Tip queryNotifyCountByIsRead(@RequestParam(required = false, defaultValue = "0") Integer isRead) {
        Long userId = JWTKit.getUserId(getHttpServletRequest());
        List<Map<String, Object>> maps = userNotifyService.getUnReadCountByUserIdAndIsRead(userId, isRead);
        return SuccessTip.create(maps);
    }

    @GetMapping("/pull/remind")
    public Tip pullRemind() {
        Long userId = JWTKit.getUserId(getHttpServletRequest());
        return SuccessTip.create(notifyService.pullRemind(userId));
    }
}

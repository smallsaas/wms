package com.jfeat.am.module.notification.api.crud;

import com.baomidou.mybatisplus.plugins.Page;
import com.jfeat.am.common.annotation.Permission;
import com.jfeat.am.core.jwt.JWTKit;
import com.jfeat.am.module.notification.api.permission.NotifyPermission;
import org.springframework.web.bind.annotation.*;

import com.jfeat.am.common.constant.tips.ErrorTip;
import com.jfeat.am.common.constant.tips.SuccessTip;
import com.jfeat.am.common.constant.tips.Tip;

import com.jfeat.am.module.notification.services.crud.service.NotifyService;
import com.jfeat.am.module.notification.services.persistence.model.Notify;

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
@RequestMapping("/api/notification/notification/notify")
public class NotifyEndpoint extends BaseController {


    @Resource
    private NotifyService notifyService;

    @PostMapping
    public Tip createNotify(@RequestBody Notify entity) {
            return SuccessTip.create(notifyService.createMaster(entity));
            }

    @GetMapping("/{id}")
    public Tip getNotify(@PathVariable Long id) {
        return SuccessTip.create(notifyService.retrieveMaster(id));
    }



    @PutMapping("/{id}")
    public Tip updateNotify(@PathVariable Long id, @RequestBody Notify entity) {
        return SuccessTip.create(notifyService.updateMaster(entity));
    }

    @DeleteMapping("/{id}")
    public Tip deleteNotify(@PathVariable Long id) {
        return SuccessTip.create(notifyService.deleteMaster(id));
    }

/*
    @GetMapping
    //此方法可能需要自行添加需要的参数,按需要使用
    public Tip queryNotifys(Page page,
                @RequestParam(name = "pageNum", required = false, defaultValue = "1") Integer pageNum,
                @RequestParam(name = "pageSize", required = false, defaultValue = "5") Integer pageSize) {
            List<Notify> records = notifyService.findNotifys(page);
            page.setCurrent(pageNum);
            page.setSize(pageSize);
            page.setRecords(records);

            return SuccessTip.create(page);
        }
        */
        @GetMapping
        @Permission({NotifyPermission.Notify_VIEW})
        public Tip queryNotifyByUserIdAndIsReadAndTargetType(Page<Notify> page,@RequestParam(required = false,defaultValue = "1") Integer pageNum,@RequestParam(required = false,defaultValue = "10") Integer pageSize,@RequestParam(required = false,defaultValue = "0") Integer isRead, @RequestParam(required = false)String tartgetType) {
            Long userId = JWTKit.getUserId(getHttpServletRequest());
            page.setCurrent(pageNum);
            page.setSize(pageSize);
            List<Notify> notifies = notifyService.queryNotifyByUserIdAndIsReadAndTargetType(page,userId,isRead,tartgetType);
            page.setRecords(notifies);
            return SuccessTip.create(page);
        }
}

package com.jfeat.am.module.notification.api.crud;

import com.baomidou.mybatisplus.plugins.Page;
import com.jfeat.am.common.constant.tips.SuccessTip;
import com.jfeat.am.common.constant.tips.Tip;
import com.jfeat.am.common.controller.BaseController;
import com.jfeat.am.core.jwt.JWTKit;
import com.jfeat.am.core.util.Convert;
import com.jfeat.am.module.notification.constant.Const;
import com.jfeat.am.module.notification.services.crud.service.NotifyService;
import com.jfeat.am.module.notification.services.crud.service.UserNotifyService;
import io.swagger.annotations.ApiOperation;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;
import java.util.List;
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
@RequestMapping("/api/notification/notify")
public class NotifyEndpoint extends BaseController {


    @Resource
    private NotifyService notifyService;
    @Resource
    private UserNotifyService userNotifyService;

    @ApiOperation("返回当前用户的通知列表")
    @GetMapping
    public Tip queryNotify(Page<Map<String, Object>> page,
                           @RequestParam(required = false, defaultValue = "1") Integer pageNum,
                           @RequestParam(required = false, defaultValue = "10") Integer pageSize,
                           @RequestParam(required = false) String targetType,
                           @RequestParam(required = false) Integer isRead) {
        Long userId = JWTKit.getUserId(getHttpServletRequest());
        page.setCurrent(pageNum);
        page.setSize(pageSize);
        List<Map<String, Object>> notifies = notifyService.paginationNotifies(page, userId, targetType, isRead);
        if (!notifies.isEmpty()) {
            for (Map notify : notifies) {
                Long id = Convert.toLong(notify.get("userNotifyId"));
                if (id != null && Const.UN_READ == Convert.toInt(notify.get("isRead"))) {
                    userNotifyService.read(id);
                }
            }
        }
        page.setRecords(notifies);
        return SuccessTip.create(page);
    }
}

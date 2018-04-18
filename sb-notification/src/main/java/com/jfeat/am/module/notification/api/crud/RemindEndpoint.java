package com.jfeat.am.module.notification.api.crud;

import com.jfeat.am.common.constant.tips.SuccessTip;
import com.jfeat.am.common.constant.tips.Tip;
import com.jfeat.am.common.controller.BaseController;
import com.jfeat.am.core.jwt.JWTKit;
import com.jfeat.am.module.notification.services.crud.service.NotifyService;
import com.jfeat.am.module.notification.services.crud.service.UserNotifyService;
import io.swagger.annotations.ApiOperation;
import org.springframework.web.bind.annotation.*;

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
@RequestMapping("/api/notification/remind")
public class RemindEndpoint extends BaseController {

    @Resource
    UserNotifyService userNotifyService;

    @ApiOperation("生成各种未读的数量并返回")
    @PostMapping
    public Tip pullRemind(@RequestParam(required = false, defaultValue = "0") Integer isRead) {
        Long userId = JWTKit.getUserId(getHttpServletRequest());
        userNotifyService.pullRemind(userId);
        List<Map<String, Object>> maps = userNotifyService.queryTypeCount(userId, isRead);
        return SuccessTip.create(maps);
    }

    @ApiOperation("批量设为已读")
    @PostMapping("/clear")
    public Tip clear(@RequestBody List<Long> ids) {
        Integer affected = 0;
        for (Long id : ids) {
            affected += userNotifyService.read(id);
        }
        return SuccessTip.create(affected);
    }
}

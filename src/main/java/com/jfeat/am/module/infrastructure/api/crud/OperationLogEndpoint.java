package com.jfeat.am.module.infrastructure.api.crud;

import com.baomidou.mybatisplus.plugins.Page;
import com.jfeat.am.common.constant.tips.SuccessTip;
import com.jfeat.am.common.constant.tips.Tip;
import com.jfeat.am.common.controller.BaseController;
import com.jfeat.am.core.support.DateTimeKit;
import com.jfeat.am.module.infrastructure.services.crud.persistence.model.OperationLog;
import com.jfeat.am.module.infrastructure.services.crud.service.OperationLogService;
import com.jfeat.am.module.infrastructure.services.domain.service.QueryOperationLogService;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import java.util.Date;
import java.util.List;

/**
 * <p>
 *  前端控制器
 * </p>
 *
 * @author admin
 * @since 2017-10-10
 */
@RestController
@RequestMapping("/api/log")
public class OperationLogEndpoint extends BaseController {

    @Resource
    private OperationLogService operationLogService;

    @Resource
    private QueryOperationLogService queryOperationLogService;

    @PostMapping
    public Tip createOperationLog(@RequestBody OperationLog entity) {
        return SuccessTip.create(operationLogService.createMaster(entity));
    }

    @GetMapping("/{id}")
    public Tip getOperationLog(@PathVariable Long id) {
        return SuccessTip.create(operationLogService.retrieveMaster(id));
    }

    @PutMapping("/{id}")
    public Tip updateOperationLog(@PathVariable Long id, @RequestBody OperationLog entity) {
        return SuccessTip.create(operationLogService.updateMaster(entity));
    }

    @DeleteMapping("/{id}")
    public Tip deleteOperationLog(@PathVariable Long id) {
        return SuccessTip.create(operationLogService.deleteMaster(id));
    }

    @GetMapping
    public Tip findOperationLogs(Page<OperationLog> page,
                                 @RequestParam(required = false,defaultValue = "1")Integer pageNum,
                                 @RequestParam(required = false,defaultValue = "10")Integer pageSize,
                                 @RequestParam(required = false)String logType,
                                 @RequestParam(required = false)String logName,
                                 @RequestParam(required = false)String userId,
                                 @RequestParam(required = false)String className,
                                 @RequestParam(required = false)String method,
                                 @RequestParam(required = false)Date startTime,
                                 @RequestParam(required = false)Date endTime,
                                 @RequestParam(required = false)String succeed){
        if (startTime == null){
            startTime = DateTimeKit.yesterday();
        }
        if (endTime == null){
            endTime = DateTimeKit.date();
        }
        page.setCurrent(pageNum);
        page.setSize(pageSize);
        List<OperationLog> operationLogs = queryOperationLogService.findOperationLogs(page,logType,logName,userId,className,method,startTime,endTime,succeed);
        page.setRecords(operationLogs);
        return SuccessTip.create(page);
    }

}
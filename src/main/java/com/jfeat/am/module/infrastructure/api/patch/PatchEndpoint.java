package com.jfeat.am.module.infrastructure.api.patch;

import com.baomidou.mybatisplus.plugins.Page;
import com.jfeat.am.common.constant.tips.SuccessTip;
import com.jfeat.am.common.constant.tips.Tip;
import com.jfeat.am.common.controller.BaseController;
import com.jfeat.am.core.support.DateTimeKit;
import com.jfeat.am.module.infrastructure.services.crud.persistence.model.OperationLog;
import com.jfeat.am.module.infrastructure.services.patch.PatchService;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;
import java.util.Date;
import java.util.List;

/**
 * Created by vincenthuang on 18/09/2017.
 */
@RestController
@RequestMapping("/api/infrastructure")
public class PatchEndpoint extends BaseController {

    @Resource
    private PatchService patchService;

    @GetMapping
    public Tip findOperationLogs(Page<OperationLog> page,
                                 @RequestParam(required = false,defaultValue = "1") Integer pageNum,
                                 @RequestParam(required = false,defaultValue = "30") Integer pageSize,
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
            endTime = new Date();
        }
        page.setCurrent(pageNum);
        page.setSize(pageSize);
        List<OperationLog> operationLogs = patchService.findOperationLogs(page,logType,logName,userId,className,method,startTime,endTime,succeed);
        page.setRecords(operationLogs);
        return SuccessTip.create(page);
    }

}
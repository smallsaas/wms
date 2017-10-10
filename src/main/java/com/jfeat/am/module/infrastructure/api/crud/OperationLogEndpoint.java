package com.jfeat.am.module.infrastructure.api.crud;

import com.jfeat.am.common.annotation.Permission;
import com.jfeat.am.module.infrastructure.constant.OperationLogPermission;
import org.springframework.web.bind.annotation.*;

import com.jfeat.am.common.constant.tips.ErrorTip;
import com.jfeat.am.common.constant.tips.SuccessTip;
import com.jfeat.am.common.constant.tips.Tip;

import com.jfeat.am.module.infrastructure.services.crud.service.OperationLogService;
import com.jfeat.am.module.infrastructure.services.crud.persistence.model.OperationLog;

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
 * @since 2017-10-10
 */
@Deprecated
@RestController
@RequestMapping("/api/log")
public class OperationLogEndpoint extends BaseController {


    @Resource
    private OperationLogService operationLogService;

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

    /*
        @GetMapping
        //此方法可能需要自行添加需要的参数,按需要使用
        public Tip queryOperationLogs(Page page,
                    @RequestParam(name = "pageNum", required = false, defaultValue = "1") Integer pageNum,
                    @RequestParam(name = "pageSize", required = false, defaultValue = "5") Integer pageSize) {

                 page.setCurrent(pageNum);
                page.setSize(pageSize);
                List<OperationLog> records = operationLogService.findOperationLogs(page);

                page.setRecords(records);

                return SuccessTip.create(page);
            }
            */
    @GetMapping
    @Permission({OperationLogPermission.OperationLog_VIEW})
    public Tip show(@RequestHeader("authorization") String token) {
        return null;
    }
}
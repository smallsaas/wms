package com.jfeat.am.module.infrastructure.api.patch;

import com.jfeat.am.common.constant.tips.SuccessTip;
import com.jfeat.am.common.constant.tips.Tip;
import com.jfeat.am.common.controller.BaseController;
import com.jfeat.am.module.infrastructure.services.crud.persistence.model.OperationLog;
import com.jfeat.am.module.infrastructure.services.patch.PatchService;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;
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
    public Tip findOperationLogs(){
        List<OperationLog> operationLogs = patchService.findOperationLogs();
        return SuccessTip.create(operationLogs);
    }

}
package com.jfeat.am.module.warehouse.api.path;


import com.jfeat.am.common.constant.tips.SuccessTip;
import com.jfeat.am.common.constant.tips.Tip;
import com.jfeat.am.common.controller.BaseController;
import com.jfeat.am.module.warehouse.services.domain.service.StorageService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;

@RestController
@Api("汇总 API")
@RequestMapping("/api/wms/storage/action")
public class PathEndpoint extends BaseController {

    @Resource
    StorageService storageService;

    @ApiOperation("出入库 汇总")
    @GetMapping("/total")
    public Tip totalStorage(){
        return SuccessTip.create(storageService.totalStorage());
    }
}

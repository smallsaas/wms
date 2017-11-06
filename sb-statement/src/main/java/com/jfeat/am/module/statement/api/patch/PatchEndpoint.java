package com.jfeat.am.module.statement.api.patch;

import com.jfeat.am.common.constant.tips.SuccessTip;
import com.jfeat.am.common.constant.tips.Tip;
import com.jfeat.am.common.controller.BaseController;
import com.jfeat.am.module.statement.services.patch.PatchService;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Generated;
import javax.annotation.Resource;
import java.util.List;
import java.util.Map;

/**
 * Created by Code Generator on 2017-11-06
 */
@RestController
@RequestMapping("/api/statements")
public class PatchEndpoint extends BaseController {

    @Resource
    private PatchService patchService;

    @GetMapping("/equipment")
    public Tip queryEquipmentStatement(@RequestParam(required = false)String name,
                                       @RequestParam(required = false)String spec,
                                       @RequestParam(required = false)String code,
                                       @RequestParam(required = false)String departmentName,
                                       @RequestParam(required = false)String status){
        return SuccessTip.create(patchService.queryEquipmentStatement(name,spec,code,departmentName,status));
    };


}
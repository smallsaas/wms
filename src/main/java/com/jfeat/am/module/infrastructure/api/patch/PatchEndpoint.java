package com.jfeat.am.module.infrastructure.api.patch;

import com.baomidou.mybatisplus.plugins.Page;
import com.jfeat.am.common.constant.tips.SuccessTip;
import com.jfeat.am.common.constant.tips.Tip;
import com.jfeat.am.common.controller.BaseController;
import com.jfeat.am.module.infrastructure.services.patch.PatchService;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;
import java.util.Map;

/**
 * Created by vincenthuang on 18/09/2017.
 */
@RestController
@RequestMapping("/api/infrastructure")
public class PatchEndpoint extends BaseController {

    @Resource
    PatchService patchService;

    @GetMapping("/department/{id}/staff")
    public Tip findDepartmentWithStaff( @PathVariable Long id){
        return SuccessTip.create(patchService.findDepartmentWithStaff(id));
    }
}
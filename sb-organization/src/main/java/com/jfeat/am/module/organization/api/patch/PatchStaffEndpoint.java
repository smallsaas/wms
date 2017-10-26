package com.jfeat.am.module.organization.api.patch;

import com.jfeat.am.common.constant.tips.SuccessTip;
import com.jfeat.am.common.constant.tips.Tip;
import com.jfeat.am.common.controller.BaseController;
import com.jfeat.am.module.organization.services.crud.service.StaffService;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;

/**
 * Created by vincenthuang on 18/09/2017.
 */
@RestController
@RequestMapping("/api")
public class PatchStaffEndpoint extends BaseController {

    @Resource
    StaffService staffService;

    @GetMapping("/org/dept/{id}/staffs")
    public Tip selectStaffsOfDepartment( @PathVariable Long departmentId){
        return SuccessTip.create(staffService.getStaffsOfDepartment(departmentId));
    }
}

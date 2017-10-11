package com.jfeat.am.module.infrastructure.api.crud;

import com.jfeat.am.common.annotation.Permission;
import com.jfeat.am.module.infrastructure.api.permission.StaffPermission;
import com.jfeat.am.module.infrastructure.services.crud.persistence.model.Staff;
import com.jfeat.am.module.infrastructure.services.crud.service.StaffService;
import org.springframework.web.bind.annotation.*;

import com.jfeat.am.common.constant.tips.SuccessTip;
import com.jfeat.am.common.constant.tips.Tip;

import com.jfeat.am.common.controller.BaseController;

import javax.annotation.Resource;

/**
 * <p>
 * 前端控制器
 * </p>
 *
 * @author admin
 * @since 2017-10-10
 */
@RestController
@RequestMapping("/api/hr/staff")
public class StaffEndpoint extends BaseController {

    @Resource
    private StaffService staffService;

    @PostMapping
    public Tip createStaff(@RequestBody Staff entity) {
        return SuccessTip.create(staffService.createMaster(entity));
    }

    @GetMapping("/{id}")
    public Tip getStaff(@PathVariable Long id) {
        return SuccessTip.create(staffService.retrieveMaster(id));
    }


    @PutMapping("/{id}")
    public Tip updateStaff(@PathVariable Long id, @RequestBody Staff entity) {
        return SuccessTip.create(staffService.updateMaster(entity));
    }

    @DeleteMapping("/{id}")
    public Tip deleteStaff(@PathVariable Long id) {
        return SuccessTip.create(staffService.deleteMaster(id));
    }

    @GetMapping
    @Permission({StaffPermission.Staff_VIEW})
    public Tip show(@RequestHeader("authorization") String token) {
        return null;
    }
}

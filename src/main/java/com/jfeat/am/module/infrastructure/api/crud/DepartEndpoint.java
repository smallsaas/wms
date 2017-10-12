package com.jfeat.am.module.infrastructure.api.crud;

import com.jfeat.am.common.annotation.Permission;
import com.jfeat.am.module.infrastructure.api.permission.DepartmentPermission;
import com.jfeat.am.module.infrastructure.services.crud.persistence.model.Depart;
import org.springframework.web.bind.annotation.*;

import com.jfeat.am.common.constant.tips.SuccessTip;
import com.jfeat.am.common.constant.tips.Tip;

import com.jfeat.am.module.infrastructure.services.crud.service.DepartService;

import com.jfeat.am.common.controller.BaseController;

import javax.annotation.Resource;

/**
 * <p>
 * 前端控制器
 * </p>
 *
 * @author admin
 * @since 2017-10-11
 */
@RestController
@RequestMapping("/api/hr/dept")
public class DepartEndpoint extends BaseController {

    @Resource
    private DepartService departService;

    @PostMapping
    public Tip createDepartment(@RequestBody Depart entity) {
        return SuccessTip.create(departService.createGroup(entity));
    }

    @GetMapping("/{id}")
    public Tip getDepartment(@PathVariable Long id) {
        return SuccessTip.create(departService.retrieveGroup(id));
    }


    @PutMapping("/{id}")
    public Tip updateDepartment(@PathVariable Long id, @RequestBody Depart entity) {
        return SuccessTip.create(departService.updateGroup(entity));
    }

    @DeleteMapping("/{id}")
    public Tip deleteDepartment(@PathVariable Long id) {
        return SuccessTip.create(departService.deleteGroup(id));
    }

    @GetMapping("/{id}/children")
    @Permission({DepartmentPermission.Department_VIEW})
    public Tip getGroupChildren(@RequestHeader("authorization") String token, @PathVariable Long id) {
        return SuccessTip.create(departService.getGroupChildren(id));
    }

    @GetMapping("/{id}/parent")
    public Tip getParentGroup(@PathVariable Long groupId){
        return SuccessTip.create(departService.getParentGroup(groupId));
    }

    @GetMapping("/root")
    public Tip getRootGroups(){
        return SuccessTip.create(departService.getRootGroups());
    }


    @GetMapping("/groups")
    public Tip getGroupsData(){
        return SuccessTip.create(departService.toJSONObject());
    }
}

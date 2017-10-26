package com.jfeat.am.module.organization.api.crud;

import com.jfeat.am.common.annotation.Permission;

import com.jfeat.am.module.organization.api.permission.DepartmentPermission;
import com.jfeat.am.module.organization.services.persistence.model.Department;
import com.jfeat.am.module.organization.services.crud.service.DepartmentService;
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
 * @since 2017-10-11
 */
@RestController("ORG-DepartmentEndpoint")
@RequestMapping("/api/org/dept")
public class DepartmentEndpoint extends BaseController {

    @Resource
    private DepartmentService departmentService;


    @GetMapping("/empty")
    public Tip getEmptyDepartment(){
        return SuccessTip.create(new Department());
    }

    @PostMapping
    public Tip createDepartment(@RequestBody Department entity) {
        return SuccessTip.create(departmentService.createGroup(entity));
    }

    @GetMapping("/{id}")
    public Tip getDepartment(@PathVariable Long id) {
        return SuccessTip.create(departmentService.retrieveGroup(id));
    }


    @PutMapping("/{id}")
    public Tip updateDepartment(@PathVariable Long id, @RequestBody Department entity) {
        return SuccessTip.create(departmentService.updateGroup(entity));
    }

    @DeleteMapping("/{id}")
    public Tip deleteDepartment(@PathVariable Long id) {
        return SuccessTip.create(departmentService.deleteGroup(id));
    }

    @GetMapping("/{id}/children")
    @Permission({DepartmentPermission.Department_VIEW})
    public Tip getGroupChildren(@RequestHeader("authorization") String token, @PathVariable Long id) {
        return SuccessTip.create(departmentService.getGroupChildren(id));
    }

    @GetMapping("/{id}/parent")
    public Tip getParentGroup(@PathVariable Long groupId){
        return SuccessTip.create(departmentService.getParentGroup(groupId));
    }

    @GetMapping("/groups/root")
    public Tip getRootGroups(){
        return SuccessTip.create(departmentService.getRootGroups());
    }


    @GetMapping("/groups")
    public Tip getGroupsData(){
        return SuccessTip.create(departmentService.toJSONObject());
    }

}

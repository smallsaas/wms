package com.jfeat.am.module.organization.api.crud;

import com.baomidou.mybatisplus.plugins.Page;
import com.jfeat.am.common.annotation.Permission;

import com.jfeat.am.core.support.DateTime;
import com.jfeat.am.module.organization.api.permission.DepartmentPermission;
import com.jfeat.am.module.organization.services.domain.service.QueryDepartmentService;
import com.jfeat.am.module.organization.services.persistence.model.Department;
import com.jfeat.am.module.organization.services.crud.service.DepartmentService;
import org.springframework.web.bind.annotation.*;

import com.jfeat.am.common.constant.tips.SuccessTip;
import com.jfeat.am.common.constant.tips.Tip;

import com.jfeat.am.common.controller.BaseController;

import javax.annotation.Resource;
import java.util.List;

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

    @Resource
    private QueryDepartmentService queryDepartmentService;

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

    @GetMapping
    public Tip queryDepartmentByOptions(Page page,
                                        @RequestParam(name = "pageNum", required = false, defaultValue = "1") Integer pageNum,
                                        @RequestParam(name = "pageSize", required = false, defaultValue = "5") Integer pageSize,
                                        @RequestParam(name = "code", required = false) String code,
                                        @RequestParam(name = "isOrg", required = false) int isOrg,
                                        @RequestParam(name = "name", required = false) String name,
                                        @RequestParam(name = "fullName", required = false) String fullName,
                                        @RequestParam(name = "location", required = false) String location,
                                        @RequestParam(name = "note", required = false) String note,
                                        @RequestParam(name = "createTime", required = false) DateTime createTime){
        page.setCurrent(pageNum);
        page.setSize(pageSize);

        Department department = new Department();
        department.setCode(code);
        department.setIsOrg(isOrg);
        department.setName(name);
        department.setFullName(fullName);
        department.setLocation(location);
        department.setNote(note);
        department.setCreateTime(createTime);

        List<Department> records = queryDepartmentService.findDepartmentPage(
                page, department);
        page.setRecords(records);

        return SuccessTip.create(page);
    }
}

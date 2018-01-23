package com.jfeat.am.module.organization.api.crud;

import com.alibaba.fastjson.JSONObject;
import com.baomidou.mybatisplus.plugins.Page;
import com.jfeat.am.common.annotation.Permission;
import com.jfeat.am.common.constant.tips.ErrorTip;
import com.jfeat.am.common.constant.tips.SuccessTip;
import com.jfeat.am.common.constant.tips.Tip;
import com.jfeat.am.common.controller.BaseController;
import com.jfeat.am.common.crud.CRUDObject;
import com.jfeat.am.common.crud.error.CRUDException;
import com.jfeat.am.core.support.DateTime;
import com.jfeat.am.core.util.Convert;
import com.jfeat.am.module.organization.api.permission.DepartmentPermission;
import com.jfeat.am.module.organization.constant.IsManager;
import com.jfeat.am.module.organization.services.crud.filter.StaffFilter;
import com.jfeat.am.module.organization.services.crud.service.DepartmentService;
import com.jfeat.am.module.organization.services.crud.service.DepartmentStaffService;
import com.jfeat.am.module.organization.services.crud.service.StaffService;
import com.jfeat.am.module.organization.services.domain.model.DepartmentItem;
import com.jfeat.am.module.organization.services.domain.model.StaffModel;
import com.jfeat.am.module.organization.services.domain.service.QueryDepartmentService;
import com.jfeat.am.module.organization.services.persistence.model.Department;
import com.jfeat.am.module.organization.services.persistence.model.DepartmentStaff;
import com.jfeat.am.module.organization.services.persistence.model.Staff;
import org.springframework.web.bind.annotation.*;
import sun.tools.asm.Cover;

import javax.annotation.Resource;
import java.util.List;
import java.util.Map;

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

    @Resource
    private DepartmentStaffService departmentStaffService;
    @Resource
    private StaffService staffService;
    private StaffFilter staffFilter = new StaffFilter();

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
        JSONObject department = departmentService.toJSONObject(id);
        Long departmentId = Convert.toLong(department.get("id"));
        DepartmentStaff departmentStaff = new DepartmentStaff();
        departmentStaff.setDepartmentId(departmentId);
        departmentStaff.setIsManager(IsManager.YES);
        DepartmentStaff departmentStaffNew = departmentStaffService.get(departmentStaff);
        CRUDObject<StaffModel> staff = new CRUDObject<>();
        if (departmentStaffNew != null){
            staff = staffService.retrieveModel(departmentStaffNew.getStaffId(), staffFilter);
        }
        department.put("manager",staff.toJSONObject());
        return SuccessTip.create(department);
    }


    @PutMapping("/{id}")
    public Tip updateDepartment(@PathVariable Long id, @RequestBody Department entity) {
        return SuccessTip.create(departmentService.updateGroup(entity));
    }

    @DeleteMapping("/{id}")
    public Tip deleteDepartment(@PathVariable Long id) {
        try{
            return SuccessTip.create(departmentService.deleteGroup(id));
        }catch (CRUDException e){
            return ErrorTip.create(e.getCode(), e.getMessage());
        }
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
                                        @RequestParam(name = "isOrg", required = false) Integer isOrg,
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

        List<DepartmentItem> records = queryDepartmentService.findDepartmentPage(department);
        page.setRecords(records);

        return SuccessTip.create(page);
    }
}

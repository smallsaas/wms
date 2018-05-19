package com.jfeat.am.module.organization.api.patch;

import com.baomidou.mybatisplus.plugins.Page;
import com.jfeat.am.common.constant.tips.SuccessTip;
import com.jfeat.am.common.constant.tips.Tip;
import com.jfeat.am.common.controller.BaseController;
import com.jfeat.am.core.jwt.JWTKit;
import com.jfeat.am.module.organization.services.crud.service.StaffService;
import com.jfeat.am.module.organization.services.domain.service.QueryDepartmentService;
import com.jfeat.am.module.organization.services.persistence.model.Staff;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import java.util.Map;

/**
 * Created by vincenthuang on 18/09/2017.
 */
@RestController
@RequestMapping("/api")
public class PatchStaffEndpoint extends BaseController {

    @Resource
    StaffService staffService;

    @Resource
    private QueryDepartmentService queryDepartmentService;

    @GetMapping("/org/dept/{departmentId}/staffs")
    public Tip selectStaffsOfDepartment( @PathVariable Long departmentId){
        return SuccessTip.create(staffService.getStaffsOfDepartment(departmentId));
    }

    //    返回经理列表
    @GetMapping("/org/manager")
    public Tip getDepartmentDetail(Page<Map<String,Object>> page,@RequestParam(required = false,defaultValue = "1")Integer pageNum,@RequestParam(required = false,defaultValue = "30")Integer pageSize,@RequestParam(required = false) Long id,@RequestParam(required = false)String isManager){
        page.setCurrent(pageNum);
        page.setSize(pageSize);
        page.setRecords(queryDepartmentService.showDepartmentDetail(page,id,isManager));
        return SuccessTip.create(page);

    }

    @GetMapping("/org/mydept/staffs")
    public Tip selectStaffsOfMyDepartment(){
        Long userId = JWTKit.getUserId(getHttpServletRequest());
        Staff staff = staffService.getStaffByUserId(userId);
        return SuccessTip.create(staffService.getStaffsOfDepartment(staff.getDeptId()));
    }
}

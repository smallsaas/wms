package com.jfeat.am.module.organization.api.crud;

import com.jfeat.am.common.constant.tips.ErrorTip;
import com.jfeat.am.common.constant.tips.SuccessTip;
import com.jfeat.am.common.constant.tips.Tip;
import com.jfeat.am.common.exception.BizExceptionEnum;
import com.jfeat.am.module.organization.constant.IsManager;
import com.jfeat.am.module.organization.services.crud.service.DepartmentStaffService;
import com.jfeat.am.module.organization.services.crud.service.StaffService;
import com.jfeat.am.module.organization.services.persistence.model.DepartmentStaff;
import com.jfeat.am.module.organization.services.persistence.model.Staff;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import java.awt.image.TileObserver;
import java.util.List;

/**
 * Created by Silent-Y on 2018/1/17.
 */
@RestController
@RequestMapping("/api/org/dept/staff")
public class DepartmentStaffEndpoint {

    @Resource
    DepartmentStaffService departmentStaffService;
    @Resource
    StaffService staffService;

    @PostMapping
     public Tip saveDepartmentStaff(@RequestBody DepartmentStaff departmentStaff){
//        departmentStaff.setIsManager(IsManager.YES);
        Staff staff = staffService.getById(departmentStaff.getStaffId());
        if (departmentStaff.getDepartmentId().equals(staff.getDeptId())){
            departmentStaffService.deleteList(departmentStaff);
            return SuccessTip.create(departmentStaffService.save(departmentStaff));
        }else {
            return ErrorTip.create(BizExceptionEnum.NO_PERMISSION);
        }
    }
    @PutMapping
    public Tip updateDepartmentStaff(@RequestBody DepartmentStaff departmentStaff){
        if (departmentStaff.getId() != null){
            departmentStaffService.delete(departmentStaff.getId());
        }
        return SuccessTip.create(departmentStaffService.save(departmentStaff));
    }

    @DeleteMapping
    public Tip deleteDepartmentStaff(@PathVariable Long id){
        return SuccessTip.create(departmentStaffService.delete(id));
    }
}

package com.jfeat.am.module.organization.api.crud;

import com.jfeat.am.common.constant.tips.SuccessTip;
import com.jfeat.am.common.constant.tips.Tip;
import com.jfeat.am.module.organization.services.crud.service.DepartmentStaffService;
import com.jfeat.am.module.organization.services.persistence.model.DepartmentStaff;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import java.awt.image.TileObserver;

/**
 * Created by Silent-Y on 2018/1/17.
 */
@RestController
@RequestMapping("/api/org/dept/staff")
public class DepartmentStaffEndpoint {

    @Resource
    DepartmentStaffService departmentStaffService;

    @PostMapping
    public Tip saveDepartmentStaff(@RequestBody DepartmentStaff departmentStaff){
        return SuccessTip.create(departmentStaffService.save(departmentStaff));
    }

    @DeleteMapping
    public Tip deleteDepartmentStaff(@PathVariable Long id){
        return SuccessTip.create(departmentStaffService.delete(id));
    }


}

package com.jfeat.am.module.organization.api.crud;

import com.baomidou.mybatisplus.plugins.Page;
import com.jfeat.am.module.organization.services.crud.filter.StaffFilter;
import com.jfeat.am.module.organization.services.domain.model.StaffItem;
import com.jfeat.am.module.organization.services.domain.model.StaffModel;
import com.jfeat.am.module.organization.services.domain.service.QueryStaffService;
import com.jfeat.am.module.organization.services.persistence.model.Staff;
import com.jfeat.am.module.organization.services.crud.service.StaffService;
import com.jfeat.am.module.profile.services.crud.service.ProfileService;
import com.jfeat.am.module.profile.services.persistence.model.Profile;
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
 * @since 2017-10-10
 */
@RestController
@RequestMapping("/api/org/staffs")
public class StaffEndpoint extends BaseController {

    @Resource
    private StaffService staffService;

    @Resource
    private QueryStaffService queryStaffService;


    @Resource
    private ProfileService  profileService;


    private StaffFilter staffFilter = new StaffFilter();

    /**
     * Only for debug
     * @return
     */
    @GetMapping("/empty")
    public Tip getEmptyStaff(){
        return SuccessTip.create(new StaffModel());
    }


    @PostMapping
    public Tip createStaff(@RequestBody StaffModel entity) {
        return SuccessTip.create(staffService.createModel(entity, staffFilter));
    }

    @GetMapping("/{id}")
    public Tip getStaff(@PathVariable Long id) {
        return SuccessTip.create(staffService.retrieveModel(id, staffFilter));
    }


    @PutMapping("/{id}")
    public Tip updateStaff(@PathVariable Long id, @RequestBody StaffModel entity) {
        return SuccessTip.create(staffService.updateModel(entity, staffFilter));
    }

    @PutMapping("/{id}/profile")
    public Tip updateStaffProfile(@PathVariable Long id, @RequestBody Profile entity) {

        Staff staff = staffService.retrieveMaster(id);
        entity.setId(staff.getProfileId());

        return SuccessTip.create(profileService.updateMaster(entity));
    }


    @DeleteMapping("/{id}")
    public Tip deleteStaff(@PathVariable Long id) {
        return SuccessTip.create(staffService.deleteModel(id));
    }


    @GetMapping
    public Tip findStaffs(Page<StaffItem> page,
                                 @RequestParam(required = false,defaultValue = "1")Integer pageNum,
                                 @RequestParam(required = false,defaultValue = "10")Integer pageSize,
                                 @RequestParam(required = false)String name,
                                 @RequestParam(required = false)String mobile,
                                 @RequestParam(required = false)String deptCode,
                                 @RequestParam(required = false)String deptName,
                                 @RequestParam(required = false)String position,
                                 @RequestParam(required = false)Integer workAge){
        page.setCurrent(pageNum);
        page.setSize(pageSize);

        StaffItem staff = new StaffItem();
        staff.setAge(workAge);
        staff.setName(name);
        staff.setMobile(mobile);
        staff.setDeptCode(deptCode);
        staff.setDeptName(deptName);
        staff.setPosition(position);

        List<StaffItem> staffs = queryStaffService.findStaffs(staff);
        page.setRecords(staffs);

        return SuccessTip.create(page);
    }
}

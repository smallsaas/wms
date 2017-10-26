package com.jfeat.am.module.organization.api.crud;

import com.baomidou.mybatisplus.plugins.Page;
import com.jfeat.am.module.organization.services.persistence.model.Staff;
import com.jfeat.am.module.organization.services.crud.service.StaffService;
import com.jfeat.am.module.organization.services.domain.dao.QueryStaffDao;
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
    private QueryStaffDao queryStaffDao;

    /**
     * Only for debug
     * @return
     */
    @GetMapping("/empty")
    public Tip getEmptyStaff(){
        return SuccessTip.create(new Staff());
    }

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
    public Tip findStaffs(Page<Staff> page,
                                 @RequestParam(required = false,defaultValue = "1")Integer pageNum,
                                 @RequestParam(required = false,defaultValue = "10")Integer pageSize,
                                 @RequestParam(required = false)Integer departmentId,
                                 @RequestParam(required = false)String position,
                                 @RequestParam(required = false)int workAge){
        page.setCurrent(pageNum);
        page.setSize(pageSize);
        List<Staff> staffs = queryStaffDao.findStaffs(page,departmentId,position,workAge);
        page.setRecords(staffs);
        return SuccessTip.create(page);
    }
}

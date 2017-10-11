package com.jfeat.am.module.infrastructure.api.crud;

import com.jfeat.am.common.annotation.Permission;
import com.jfeat.am.module.infrastructure.api.constant.DeptmentPermission;
import org.springframework.web.bind.annotation.*;

import com.jfeat.am.common.constant.tips.SuccessTip;
import com.jfeat.am.common.constant.tips.Tip;

import com.jfeat.am.module.infrastructure.services.crud.service.DepartmentService;
import com.jfeat.am.module.infrastructure.services.crud.persistence.model.Deptment;

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
public class DepartmentEndpoint extends BaseController {

    @Resource
    private DepartmentService departmentService;

    @PostMapping
    public Tip createDeptment(@RequestBody Deptment entity) {
        return SuccessTip.create(departmentService.createGroup(entity));
    }

    @GetMapping("/{id}")
    public Tip getDeptment(@PathVariable Long id) {
        return SuccessTip.create(departmentService.retrieveGroup(id));
    }


    @PutMapping("/{id}")
    public Tip updateDeptment(@PathVariable Long id, @RequestBody Deptment entity) {
        return SuccessTip.create(departmentService.updateGroup(entity));
    }

    @DeleteMapping("/{id}")
    public Tip deleteDeptment(@PathVariable Long id) {
        return SuccessTip.create(departmentService.deleteGroup(id));
    }

    /*
        @GetMapping
        //此方法可能需要自行添加需要的参数,按需要使用
        public Tip queryDeptments(Page page,
                    @RequestParam(name = "pageNum", required = false, defaultValue = "1") Integer pageNum,
                    @RequestParam(name = "pageSize", required = false, defaultValue = "5") Integer pageSize) {
                List<Deptment> records = deptmentService.findDeptments(page);
                page.setCurrent(pageNum);
                page.setSize(pageSize);
                page.setRecords(records);

                return SuccessTip.create(page);
            }
            */

    @GetMapping
    @Permission({DeptmentPermission.Deptment_VIEW})
    public Tip show(@RequestHeader("authorization") String token) {
        return null;
    }
}

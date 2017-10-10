package com.jfeat.am.module.infrastructure.api.crud;

import com.jfeat.am.common.annotation.Permission;
import com.jfeat.am.module.infrastructure.constant.DeptPermission;
import com.jfeat.am.module.infrastructure.services.crud.service.DeptService;
import org.springframework.web.bind.annotation.*;

import com.jfeat.am.common.constant.tips.ErrorTip;
import com.jfeat.am.common.constant.tips.SuccessTip;
import com.jfeat.am.common.constant.tips.Tip;

import com.jfeat.am.module.infrastructure.services.crud.service.DeptService;
import com.jfeat.am.module.infrastructure.services.crud.persistence.model.Dept;

import com.jfeat.am.common.controller.BaseController;

import javax.annotation.Resource;
import javax.validation.Valid;
import java.util.List;
import java.util.HashMap;

/**
 * <p>
 * 前端控制器
 * </p>
 *
 * @author admin
 * @since 2017-10-10
 */
@RestController
@RequestMapping("/api/infrastructure/infrastructure/dept")
public class DeptEndpoint extends BaseController {


    @Resource
    private DeptService deptService;

    @PostMapping
    public Tip createDept(@RequestBody Dept entity) {
        return SuccessTip.create(deptService.createGroup(entity));
    }

    @GetMapping("/{id}")
    public Tip getDept(@PathVariable Long id) {
        return SuccessTip.create(deptService.retrieveGroup(id));
    }


    @PutMapping("/{id}")
    public Tip updateDept(@PathVariable Long id, @RequestBody Dept entity) {
        return SuccessTip.create(deptService.updateGroup(entity));
    }

    @DeleteMapping("/{id}")
    public Tip deleteDept(@PathVariable Long id) {
        return SuccessTip.create(deptService.deleteGroup(id));
    }

    /*
        @GetMapping
        //此方法可能需要自行添加需要的参数,按需要使用
        public Tip queryDepts(Page page,
                    @RequestParam(name = "pageNum", required = false, defaultValue = "1") Integer pageNum,
                    @RequestParam(name = "pageSize", required = false, defaultValue = "5") Integer pageSize) {

                 page.setCurrent(pageNum);
                page.setSize(pageSize);
                List<Dept> records = deptService.findDepts(page);

                page.setRecords(records);

                return SuccessTip.create(page);
            }
            */
    @GetMapping
    @Permission({DeptPermission.Dept_VIEW})
    public Tip show(@RequestHeader("authorization") String token) {
        return null;
    }
}

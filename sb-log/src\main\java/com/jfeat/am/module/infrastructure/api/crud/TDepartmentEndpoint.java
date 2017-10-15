package com.jfeat.am.module.infrastructure.api.crud;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;

import com.jfeat.am.common.constant.tips.ErrorTip;
import com.jfeat.am.common.constant.tips.SuccessTip;
import com.jfeat.am.common.constant.tips.Tip;

import com.jfeat.am.module.infrastructure.services.crud.service.TDepartmentService;
import com.jfeat.am.module.infrastructure.services.crud.persistence.model.TDepartment;

import org.springframework.web.bind.annotation.RestController;
import com.jfeat.am.common.controller.BaseController;

import javax.annotation.Resource;
import javax.validation.Valid;
import java.util.List;
import java.util.HashMap;

/**
 * <p>
 *  前端控制器
 * </p>
 *
 * @author admin
 * @since 2017-10-15
 */
@Deprecated
@RestController
@RequestMapping("/api/infrastructure/infrastructure/t-department")
public class TDepartmentEndpoint extends BaseController {


    @Resource
    private TDepartmentService tDepartmentService;

    @PostMapping
    public Tip createTDepartment(@RequestBody TDepartment entity) {
            return SuccessTip.create(tDepartmentService.createMaster(entity));
            }

    @GetMapping("/{id}")
    public Tip getTDepartment(@PathVariable Long id) {
        return SuccessTip.create(tDepartmentService.retrieveMaster(id));
    }



    @PutMapping("/{id}")
    public Tip updateTDepartment(@PathVariable Long id, @RequestBody TDepartment entity) {
        return SuccessTip.create(tDepartmentService.updateMaster(entity));
    }

    @DeleteMapping("/{id}")
    public Tip deleteTDepartment(@PathVariable Long id) {
        return SuccessTip.create(tDepartmentService.deleteMaster(id));
    }

/*
    @GetMapping
    //此方法可能需要自行添加需要的参数,按需要使用
    public Tip queryTDepartments(Page page,
                @RequestParam(name = "pageNum", required = false, defaultValue = "1") Integer pageNum,
                @RequestParam(name = "pageSize", required = false, defaultValue = "5") Integer pageSize) {
            List<TDepartment> records = tDepartmentService.findTDepartments(page);
            page.setCurrent(pageNum);
            page.setSize(pageSize);
            page.setRecords(records);

            return SuccessTip.create(page);
        }
        */
        @GetMapping
        @Permission({TDepartmentPermission.TDepartment_VIEW})
        public Tip show(@RequestHeader("authorization") String token) {

        }
}

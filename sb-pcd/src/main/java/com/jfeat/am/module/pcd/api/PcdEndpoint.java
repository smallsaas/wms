package com.jfeat.am.module.pcd.api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import com.baomidou.mybatisplus.plugins.Page;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import com.jfeat.am.module.pcd.services.domain.service.QueryPcdService;
import com.jfeat.am.common.constant.tips.SuccessTip;
import com.jfeat.am.common.constant.tips.Tip;

import com.jfeat.am.module.pcd.services.service.PcdService;
import com.jfeat.am.module.pcd.services.persistence.model.Pcd;

import org.springframework.web.bind.annotation.RestController;
import com.jfeat.am.common.controller.BaseController;

import javax.annotation.Resource;
import java.util.Date;


/**
 * <p>
 *  api
 * </p>
 *
 * @author Code Generator
 * @since 2017-12-22
 */
@RestController
@RequestMapping("/api/pub/pcd")
public class PcdEndpoint extends BaseController {

    @Autowired
    QueryPcdService queryPcdService;

    @ApiOperation("查询省市区数据")
    @GetMapping
    public Tip findAll(@RequestParam(required = false, defaultValue = "false") Boolean grouping) {
        if (grouping) {
            return SuccessTip.create(queryPcdService.findAllGrouping());
        }
        return SuccessTip.create(queryPcdService.findAllToMap());
    }
}


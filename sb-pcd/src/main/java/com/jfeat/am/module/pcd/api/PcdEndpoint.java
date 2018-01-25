package com.jfeat.am.module.pcd.api;
import com.jfeat.am.common.constant.tips.SuccessTip;
import com.jfeat.am.common.constant.tips.Tip;
import com.jfeat.am.common.controller.BaseController;
import com.jfeat.am.module.pcd.services.domain.service.QueryPcdService;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;


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


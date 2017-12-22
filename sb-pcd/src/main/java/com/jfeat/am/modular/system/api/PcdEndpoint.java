package com.jfeat.am.modular.system.api;

import com.baomidou.mybatisplus.mapper.EntityWrapper;
import com.baomidou.mybatisplus.mapper.Wrapper;
import com.jfeat.am.common.constant.tips.SuccessTip;
import com.jfeat.am.common.constant.tips.Tip;
import com.jfeat.am.common.controller.BaseController;
import com.jfeat.am.common.persistence.dao.PcdMapper;
import com.jfeat.am.common.persistence.model.Pcd;
import com.jfeat.am.modular.system.service.PcdService;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

/**
 * Created by jackyhuang on 2017/6/17.
 */
@RestController
@RequestMapping("/api/pub/pcd")
public class PcdEndpoint extends BaseController {

    @Autowired
    private PcdService pcdService;

    @ApiOperation("查询省市区数据")
    @GetMapping
    public Tip findAll(@RequestParam(required = false, defaultValue = "false") Boolean grouping) {
        if (grouping) {
            return SuccessTip.create(pcdService.findAllGrouping());
        }
        return SuccessTip.create(pcdService.findAllToMap());
    }
}

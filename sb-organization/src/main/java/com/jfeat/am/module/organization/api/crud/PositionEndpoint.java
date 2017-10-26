package com.jfeat.am.module.organization.api.crud;
import org.springframework.web.bind.annotation.GetMapping;
import com.baomidou.mybatisplus.plugins.Page;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import com.jfeat.am.module.organization.services.domain.service.QueryPositionService;
import com.jfeat.am.common.constant.tips.ErrorTip;
import com.jfeat.am.common.constant.tips.SuccessTip;
import com.jfeat.am.common.constant.tips.Tip;

import com.jfeat.am.module.organization.services.crud.service.PositionService;
import com.jfeat.am.module.organization.services.persistence.model.Position;

import org.springframework.web.bind.annotation.RestController;
import com.jfeat.am.common.controller.BaseController;

import javax.annotation.Resource;
import javax.validation.Valid;
import java.util.List;
import java.util.HashMap;

/**
 * <p>
 *  api
 * </p>
 *
 * @author Code Generator
 * @since 2017-10-26
 */
@Deprecated
@RestController
@RequestMapping("/api/org/position")
public class PositionEndpoint extends BaseController {

    @Resource
    PositionService positionService;

    @Resource
    QueryPositionService queryPositionService;

    /// For debug purpose
    @GetMapping("/empty")
    public Tip getEmptyPosition(@PathVariable Long id) {
        return SuccessTip.create(new Position());
    }

    @PostMapping
    public Tip createPosition(@RequestBody Position entity) {
        return SuccessTip.create(positionService.createMaster(entity));
    }

    @GetMapping("/{id}")
    public Tip getPosition(@PathVariable Long id) {
        return SuccessTip.create(positionService.retrieveMaster(id));
    }


    @PutMapping("/{id}")
    public Tip updatePosition(@PathVariable Long id, @RequestBody Position entity) {
        return SuccessTip.create(positionService.updateMaster(entity));
    }

    @DeleteMapping("/{id}")
    public Tip deletePosition(@PathVariable Long id) {
        return SuccessTip.create(positionService.deleteMaster(id));
    }
}

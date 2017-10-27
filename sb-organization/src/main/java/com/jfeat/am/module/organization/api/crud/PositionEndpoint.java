package com.jfeat.am.module.organization.api.crud;
import com.jfeat.am.common.constant.tips.SuccessTip;
import com.jfeat.am.common.constant.tips.Tip;
import com.jfeat.am.common.controller.BaseController;
import com.jfeat.am.module.organization.services.crud.service.PositionService;
import com.jfeat.am.module.organization.services.persistence.model.Position;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;

/**
 * <p>
 *  api
 * </p>
 *
 * @author Code Generator
 * @since 2017-10-26
 */
@RestController("ORG-PositionEndpoint")
@RequestMapping("/api/org/position")
public class PositionEndpoint extends BaseController {

    @Resource
    PositionService positionService;

    /// For debug purpose
    @GetMapping("/empty")
    public Tip getEmptyPosition(@PathVariable Long id) {
        return SuccessTip.create(new Position());
    }

    @GetMapping
    public Tip getAllPositions() {
        return SuccessTip.create(positionService.retrieveMasterList());
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

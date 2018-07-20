package com.jfeat.am.module.warehouse.api.crud;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.web.bind.annotation.GetMapping;
import com.baomidou.mybatisplus.plugins.Page;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.dao.DuplicateKeyException;
import com.jfeat.am.module.warehouse.services.domain.dao.QueryWarehouseSlotDao;
import com.jfeat.am.common.constant.tips.SuccessTip;
import com.jfeat.am.common.constant.tips.Tip;
import com.jfeat.am.module.log.annotation.BusinessLog;
import com.jfeat.am.common.exception.BusinessCode;
import com.jfeat.am.common.exception.BusinessException;
import com.jfeat.am.module.warehouse.services.domain.service.WarehouseSlotService;
import com.jfeat.am.module.warehouse.services.domain.model.WarehouseSlotRecord;
import com.jfeat.am.module.warehouse.services.domain.model.WarehouseSlotModel;

import org.springframework.web.bind.annotation.RestController;
import com.jfeat.am.common.controller.BaseController;

import javax.annotation.Resource;


/**
 * <p>
 * api
 * </p>
 *
 * @author Code Generator
 * @since 2018-06-22
 */
@RestController
@Api("WMS-仓库储位")
@RequestMapping("/api/wms/warehouses/")
public class WarehouseSlotEndpoint extends BaseController {


    @Resource
    WarehouseSlotService warehouseSlotService;

    @Resource
    QueryWarehouseSlotDao queryWarehouseSlotDao;

    @BusinessLog(name = "WarehouseSlot", value = "create WarehouseSlot")
    @PostMapping("/{warehousesId}/slot")
    @ApiOperation(value = "新建储位")

    public Tip createWarehouseSlot(@PathVariable long warehousesId,@RequestBody WarehouseSlotModel entity) {

        Integer affected = 0;
        try {
            entity.setWarehouseId(warehousesId);
            affected = warehouseSlotService.addSlaveItem(entity);

        } catch (DuplicateKeyException e) {
            throw new BusinessException(BusinessCode.DuplicateKey);
        }

        return SuccessTip.create(affected);
    }

    @GetMapping("/{warehousesId}/slot/{id}")
    @ApiOperation(value = "查看储位信息")
    public Tip getWarehouseSlot(@PathVariable Long id) {
        return SuccessTip.create(warehouseSlotService.getSlaveItem(id));
    }

    @BusinessLog(name = "WarehouseSlot", value = "update WarehouseSlot")
    @PutMapping("/{warehousesId}/slot/{id}")
    @ApiOperation(value = "修改某个储位信息")

    public Tip updateWarehouseSlot(@PathVariable Long id, @RequestBody WarehouseSlotModel entity) {
        entity.setId(id);
        return SuccessTip.create(warehouseSlotService.updateSlaveItem(entity));
    }

    @BusinessLog(name = "WarehouseSlot", value = "delete WarehouseSlot")
    @DeleteMapping("/{warehousesId}/slot/{id}")
    @ApiOperation(value = "删除某个储位")
    public Tip deleteWarehouseSlot(@PathVariable Long id) {
        return SuccessTip.create(warehouseSlotService.removeSlaveItem(id));
    }


}

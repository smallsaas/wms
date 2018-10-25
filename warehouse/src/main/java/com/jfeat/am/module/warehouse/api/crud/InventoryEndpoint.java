package com.jfeat.am.module.warehouse.api.crud;

import com.jfeat.am.module.warehouse.services.domain.model.SkuStorageDetails;
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
import com.jfeat.am.module.warehouse.services.domain.dao.QueryInventoryDao;
import com.jfeat.am.common.constant.tips.SuccessTip;
import com.jfeat.am.common.constant.tips.Tip;
import com.jfeat.am.module.log.annotation.BusinessLog;
import com.jfeat.am.common.exception.BusinessCode;
import com.jfeat.am.common.exception.BusinessException;

import com.jfeat.am.module.warehouse.services.domain.service.InventoryService;
import com.jfeat.am.module.warehouse.services.domain.model.InventoryRecord;
import com.jfeat.am.module.warehouse.services.domain.model.InventoryModel;

import org.springframework.web.bind.annotation.RestController;
import com.jfeat.am.common.controller.BaseController;

import javax.annotation.Resource;


/**
 * <p>
 * api
 * </p>
 *
 * @author Code Generator
 * @since 2018-06-23
 */
@RestController
@Api("WMS-库存")
@RequestMapping("/api/wms/inventories")
public class InventoryEndpoint extends BaseController {


    @Resource
    InventoryService inventoryService;

    @Resource
    QueryInventoryDao queryInventoryDao;


    @GetMapping("/skus/{id}")
    @ApiOperation(value = "某个商品对应的仓库的出入库详情")
    @BusinessLog(name = "skuStorageDetails", value = "view skuStorageDetails")
    public Tip skuStorageDetails(Page<SkuStorageDetails> page,
                                 @RequestParam(name = "pageNum", required = false, defaultValue = "1") Integer pageNum,
                                 @RequestParam(name = "pageSize", required = false, defaultValue = "10") Integer pageSize,
                                 @RequestParam(name = "warehouseName", required = false) String warehouseName,
                                 @PathVariable Long id) {
        page.setCurrent(pageNum);
        page.setSize(pageSize);
        page.setRecords(queryInventoryDao.skuStorageDetails(page, id, warehouseName));
        return SuccessTip.create(page);
    }
    @GetMapping("/skus/in/{id}")
    @ApiOperation(value = "某个商品对应的仓库的入库详情")
    @BusinessLog(name = "skuStorageDetails", value = "view skuStorageDetails")
    public Tip skuStorageInDetails(Page<SkuStorageDetails> page,
                                 @RequestParam(name = "pageNum", required = false, defaultValue = "1") Integer pageNum,
                                 @RequestParam(name = "pageSize", required = false, defaultValue = "10") Integer pageSize,
                                 @RequestParam(name = "warehouseName", required = false) String warehouseName,
                                 @PathVariable Long id) {
        page.setCurrent(pageNum);
        page.setSize(pageSize);
        page.setRecords(queryInventoryDao.skuStorageInDetails(page, id, warehouseName));
        return SuccessTip.create(page);
    }

    @GetMapping("/skus/out/{id}")
    @ApiOperation(value = "某个商品对应的仓库的出库详情")
    @BusinessLog(name = "skuStorageDetails", value = "view skuStorageDetails")
    public Tip skuStorageOutDetails(Page<SkuStorageDetails> page,
                                 @RequestParam(name = "pageNum", required = false, defaultValue = "1") Integer pageNum,
                                 @RequestParam(name = "pageSize", required = false, defaultValue = "10") Integer pageSize,
                                 @RequestParam(name = "warehouseName", required = false) String warehouseName,
                                 @PathVariable Long id) {
        page.setCurrent(pageNum);
        page.setSize(pageSize);
        page.setRecords(queryInventoryDao.skuStorageOutDetails(page, id, warehouseName));
        return SuccessTip.create(page);
    }


    @BusinessLog(name = "Inventory", value = "create Inventory")
    @PostMapping
    @ApiOperation(value = "新建库存盘点")
    public Tip createInventory(@RequestBody InventoryModel entity) {

        Integer affected = 0;
        try {
            affected += inventoryService.createMaster(entity);

        } catch (DuplicateKeyException e) {
            throw new BusinessException(BusinessCode.DuplicateKey);
        }

        return SuccessTip.create(affected);
    }

    @GetMapping("/{id}")
    @ApiOperation(value = "获取库存盘点详细")

    public Tip getInventory(@PathVariable Long id) {
        return SuccessTip.create(inventoryService.retrieveMaster(id));
    }

    @BusinessLog(name = "Inventory", value = "update Inventory")
    @PutMapping("/{id}")
    @ApiOperation(value = "修改库存盘点")

    public Tip updateInventory(@PathVariable Long id, @RequestBody InventoryModel entity) {
        entity.setId(id);
        return SuccessTip.create(inventoryService.updateMaster(entity));
    }

    @BusinessLog(name = "Inventory", value = "delete Inventory")
    @DeleteMapping("/{id}")
    @ApiOperation(value = "删除库存盘点")

    public Tip deleteInventory(@PathVariable Long id) {
        return SuccessTip.create(inventoryService.deleteMaster(id));
    }

    @GetMapping
    @ApiOperation(value = "库存信息列表", response = InventoryModel.class)
    public Tip queryInventories(Page<InventoryRecord> page,
                                @RequestParam(name = "pageNum", required = false, defaultValue = "1") Integer pageNum,
                                @RequestParam(name = "pageSize", required = false, defaultValue = "10") Integer pageSize,
                                @RequestParam(name = "id", required = false) Long id,
                                @RequestParam(name = "warehouseId", required = false) Long warehouseId,
                                @RequestParam(name = "slotId", required = false) Long slotId,
                                @RequestParam(name = "maxInventory", required = false) Integer maxInventory,
                                @RequestParam(name = "minInventory", required = false) Integer minInventory,
                                @RequestParam(name = "skuId", required = false) Long skuId,
                                @RequestParam(name = "validSku", required = false) Integer validSku,
                                @RequestParam(name = "advanceQuantities", required = false) Integer advanceQuantities,
                                @RequestParam(name = "transmitQuantities", required = false) Integer transmitQuantities,
                                @RequestParam(name = "warehouseName", required = false) String warehouseName,
                                @RequestParam(name = "skuName", required = false) String skuName,
                                @RequestParam(name = "search", required = false) String search,
                                @RequestParam(name = "orderBy", required = false) String orderBy,
                                @RequestParam(name = "sort", required = false) String sort) {
        if (orderBy != null && orderBy.length() > 0) {
            if (sort != null && sort.length() > 0) {
                String pattern = "(ASC|DESC|asc|desc)";
                if (!sort.matches(pattern)) {
                    throw new BusinessException(BusinessCode.BadRequest.getCode(), "sort must be ASC or DESC");//此处异常类型根据实际情况而定
                }
            } else {
                sort = "ASC";
            }
            orderBy = "`" + orderBy + "`" + " " + sort;
        }
        page.setCurrent(pageNum);
        page.setSize(pageSize);

        InventoryRecord record = new InventoryRecord();
        record.setId(id);
        record.setWarehouseId(warehouseId);
        record.setSlotId(slotId);
        record.setMaxInventory(maxInventory);
        record.setMinInventory(minInventory);
        record.setSkuId(skuId);
        record.setValidSku(validSku);
        record.setAdvanceQuantities(advanceQuantities);
        record.setTransmitQuantities(transmitQuantities);

        page.setRecords(queryInventoryDao.findInventoryPage(page, warehouseName, skuName,search, record, orderBy));

        return SuccessTip.create(page);
    }


}

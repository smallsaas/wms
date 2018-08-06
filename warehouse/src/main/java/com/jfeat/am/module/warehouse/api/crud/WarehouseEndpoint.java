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
import com.jfeat.am.module.warehouse.services.domain.dao.QueryWarehouseDao;
import com.jfeat.am.common.constant.tips.SuccessTip;
import com.jfeat.am.common.constant.tips.Tip;
import com.jfeat.am.module.log.annotation.BusinessLog;
import com.jfeat.am.common.exception.BusinessCode;
import com.jfeat.am.common.exception.BusinessException;
import com.jfeat.am.module.warehouse.services.domain.service.WarehouseService;
import com.jfeat.am.module.warehouse.services.domain.model.WarehouseRecord;
import com.jfeat.am.module.warehouse.services.domain.model.WarehouseModel;

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
@Api("WMS-仓库管理")
@RequestMapping("/api/wms/warehouses")
public class WarehouseEndpoint extends BaseController {


    @Resource
    WarehouseService warehouseService;

    @Resource
    QueryWarehouseDao queryWarehouseDao;

    @BusinessLog(name = "Warehouse", value = "create Warehouse")
    @PostMapping
    @ApiOperation(value = "新建一个仓库", response = WarehouseModel.class)
    public Tip createWarehouse(@RequestBody WarehouseModel entity) {

        Integer affected = 0;
        try {
            affected = warehouseService.createMaster(entity, null, null, null);

        } catch (DuplicateKeyException e) {
            throw new BusinessException(BusinessCode.DuplicateKey);
        }

        return SuccessTip.create(affected);
    }

    @BusinessLog(name = "Warehouse", value = "view Warehouse")
    @GetMapping("/{id}")
    @ApiOperation(value = "查看仓库信息", response = WarehouseModel.class)
    public Tip getWarehouse(@PathVariable Long id) {
        return SuccessTip.create(warehouseService.retrieveMaster(id, null, null, null).toJSONObject());
    }

    @BusinessLog(name = "Warehouse", value = "update Warehouse")
    @PutMapping("/{id}")
    @ApiOperation(value = "修改仓库", response = WarehouseModel.class)
    public Tip updateWarehouse(@PathVariable Long id, @RequestBody WarehouseModel entity) {
        entity.setId(id);
        return SuccessTip.create(warehouseService.updateMaster(entity, null, null, null));
    }

    @BusinessLog(name = "Warehouse", value = "delete Warehouse")
    @DeleteMapping("/{id}")
    @ApiOperation(value = "删除仓库", response = WarehouseModel.class)

    public Tip deleteWarehouse(@PathVariable Long id) {
        return SuccessTip.create(warehouseService.deleteMaster(id));
    }

    @GetMapping
    @ApiOperation(value = "仓库列表", response = WarehouseRecord.class)

    public Tip queryWarehouses(Page<WarehouseRecord> page,
                               @RequestParam(name = "pageNum", required = false, defaultValue = "1") Integer pageNum,
                               @RequestParam(name = "pageSize", required = false, defaultValue = "10") Integer pageSize,
                               @RequestParam(name = "id", required = false) Long id,
                               @RequestParam(name = "warehouseCode", required = false) String warehouseCode,
                               @RequestParam(name = "warehouseName", required = false) String warehouseName,
                               @RequestParam(name = "warehousePCD", required = false) String warehousePCD,
                               @RequestParam(name = "warehouseAddress", required = false) String warehouseAddress,
                               @RequestParam(name = "warehouseCharger", required = false) String warehouseCharger,
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

        WarehouseRecord record = new WarehouseRecord();
        record.setId(id);
        record.setWarehouseCode(warehouseCode);
        record.setWarehouseName(warehouseName);
        record.setWarehousePCD(warehousePCD);
        record.setWarehouseAddress(warehouseAddress);
        record.setWarehouseCharger(warehouseCharger);

        page.setRecords(queryWarehouseDao.findWarehousePage(page, record, orderBy));

        return SuccessTip.create(page);
    }


}

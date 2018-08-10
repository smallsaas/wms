package com.jfeat.am.module.warehouse.api.crud;

import com.jfeat.am.core.jwt.JWTKit;
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
import com.jfeat.am.module.warehouse.services.domain.dao.QueryStorageInDao;
import com.jfeat.am.common.constant.tips.SuccessTip;
import com.jfeat.am.common.constant.tips.Tip;
import com.jfeat.am.module.log.annotation.BusinessLog;
import com.jfeat.am.common.exception.BusinessCode;
import com.jfeat.am.common.exception.BusinessException;

import java.math.BigDecimal;

import com.jfeat.am.module.warehouse.services.domain.service.StorageInService;
import com.jfeat.am.module.warehouse.services.domain.model.StorageInRecord;
import com.jfeat.am.module.warehouse.services.domain.model.StorageInModel;

import org.springframework.web.bind.annotation.RestController;
import com.jfeat.am.common.controller.BaseController;

import javax.annotation.Resource;
import java.util.Date;


/**
 * <p>
 * api
 * </p>
 *
 * @author Code Generator
 * @since 2018-06-21
 */
@RestController
@Api("WMS-入库表")
@RequestMapping("/api/wms/storages/in")
public class StorageInEndpoint extends BaseController {


    @Resource
    StorageInService storageInService;

    @Resource
    QueryStorageInDao queryStorageInDao;

    @BusinessLog(name = "StorageIn", value = "create StorageIn")
    @PostMapping
    @ApiOperation(value = "新建入库单",response = StorageInModel.class)
    public Tip createStorageIn(@RequestBody StorageInModel entity) {
        return SuccessTip.create(storageInService.createStorageIn(JWTKit.getUserId(getHttpServletRequest()),entity));
    }

    @GetMapping("/{id}")
    @ApiOperation(value = "查询入库单",response = StorageInModel.class)
    public Tip getStorageIn(@PathVariable Long id) {
//        return SuccessTip.create(storageInService.retrieveMaster(id, null, null, null).toJSONObject());

        return SuccessTip.create(queryStorageInDao.storagesInDetails(id));
    }


    @BusinessLog(name = "StorageIn", value = "update StorageIn")
    @PutMapping("/{id}")
    @ApiOperation(value = "修改入库单",response = StorageInModel.class)
    public Tip updateStorageIn(@PathVariable Long id, @RequestBody StorageInModel entity) {
        entity.setId(id);
        return SuccessTip.create(storageInService.updateMaster(entity, null, null, null));
    }

    @BusinessLog(name = "StorageIn", value = "delete StorageIn")
    @DeleteMapping("/{id}")
    @ApiOperation(value = "删除入库单",response = StorageInModel.class)
    public Tip deleteStorageIn(@PathVariable Long id) {
        return SuccessTip.create(storageInService.deleteMaster(id));
    }

    @GetMapping
    @ApiOperation(value = "入库单列表",response = StorageInRecord.class)
    public Tip queryStorageIns(Page<StorageInRecord> page,
                               @RequestParam(name = "pageNum", required = false, defaultValue = "1") Integer pageNum,
                               @RequestParam(name = "pageSize", required = false, defaultValue = "10") Integer pageSize,
                               @RequestParam(name = "id", required = false) Long id,
                               @RequestParam(name = "transactionCode", required = false) String transactionCode,
                               @RequestParam(name = "transactionType", required = false) String transactionType,
                               @RequestParam(name = "transactionTime", required = false) Date transactionTime,
                               @RequestParam(name = "batchNo", required = false) String batchNo,
                               @RequestParam(name = "warehouseId", required = false) Long warehouseId,
                               @RequestParam(name = "slotId", required = false) Long slotId,
                               @RequestParam(name = "note", required = false) String note,
                               @RequestParam(name = "status", required = false) String status,
                               @RequestParam(name = "transactionBy", required = false) String transactionBy,
                               @RequestParam(name = "originatorId", required = false) Long originatorId,
                               @RequestParam(name = "readjustCostPrice", required = false) BigDecimal readjustCostPrice,
                               @RequestParam(name = "field1", required = false) String field1,
                               @RequestParam(name = "field2", required = false) String field2,
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

        StorageInRecord record = new StorageInRecord();
        record.setId(id);
        record.setTransactionCode(transactionCode);
        record.setTransactionType(transactionType);
        record.setTransactionTime(transactionTime);
        record.setBatchNo(batchNo);
        record.setWarehouseId(warehouseId);
        record.setSlotId(slotId);
        record.setNote(note);
        record.setStatus(status);
        record.setTransactionBy(transactionBy);
        record.setOriginatorId(originatorId);
        record.setReadjustCostPrice(readjustCostPrice);
        record.setField1(field1);
        record.setField2(field2);

        page.setRecords(queryStorageInDao.findStorageInPage(page, record, orderBy));

        return SuccessTip.create(page);
    }


}

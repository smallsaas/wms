package com.jfeat.am.module.warehouse.api.crud;

import com.alibaba.fastjson.JSONObject;
import com.jfeat.am.core.jwt.JWTKit;
import com.jfeat.am.module.log.LogManager;
import com.jfeat.am.module.log.LogTaskFactory;
import com.jfeat.am.module.warehouse.services.definition.FormType;
import com.jfeat.am.module.warehouse.services.definition.TransactionType;
import com.jfeat.am.module.warehouse.services.domain.service.ProcurementService;
import com.jfeat.am.module.warehouse.services.persistence.model.StorageIn;
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


    @Resource
    ProcurementService procurementService;


    private void createStorageInLog(Long targetId, String methodName, String operation, String message) {
        LogManager.me().executeLog(LogTaskFactory.businessLog(JWTKit.getUserId(getHttpServletRequest()),
                JWTKit.getAccount(getHttpServletRequest()),
                operation,
                StorageInEndpoint.class.getName(),
                methodName,
                message,
                "成功",
                targetId,
                FormType.IN.toString()
        ));
    }


    @BusinessLog(name = "StorageIn", value = "create StorageIn")
    @PostMapping
    @ApiOperation(value = "新建入库单", response = StorageInModel.class)
    public Tip createStorageIn(@RequestBody StorageInModel entity) {
        String userName = JWTKit.getAccount(getHttpServletRequest());
        entity.setOriginatorName(userName);
        if (entity.getWarehouseId() == null) {
            entity.setWarehouseId(1L);
        }
        createStorageInLog(entity.getId(), "createStorageIn", "对入库单进行了新建操作", entity
                .getId() + " &");
        return SuccessTip.create(storageInService.createStorageIn(JWTKit.getUserId(getHttpServletRequest()), entity));
    }

    @BusinessLog(name = "StorageIn", value = "create StorageIn")
    @PostMapping("/mall")
    @ApiOperation(value = "商城新建入库单", response = StorageInModel.class)
    public Tip salesStorageIn(@RequestBody StorageInModel entity) {
        String userName = JWTKit.getAccount(getHttpServletRequest());
        entity.setOriginatorName(userName);
        if (entity.getWarehouseId() == null) {
            entity.setWarehouseId(1L);
        }
        createStorageInLog(entity.getId(), "createStorageIn", "对退货入库单进行了新建操作", entity
                .getId() + " &");
        return SuccessTip.create(storageInService.salesStorageIn(JWTKit.getUserId(getHttpServletRequest()), entity));
    }

    @GetMapping("/{id}")
    @ApiOperation(value = "查询入库单", response = StorageInModel.class)
    public Tip getStorageIn(@PathVariable Long id) {

        return SuccessTip.create(queryStorageInDao.storagesInDetails(id));
    }


    @BusinessLog(name = "StorageIn", value = "update StorageIn")
    @PutMapping("/{id}")
    @ApiOperation(value = "修改入库单", response = StorageInModel.class)
    public Tip updateStorageIn(@PathVariable Long id, @RequestBody StorageInModel entity) {
        entity.setId(id);
        Integer result = storageInService.updateStorageIn(JWTKit.getUserId(getHttpServletRequest()), id, entity);
        createStorageInLog(entity.getId(), "updateStorageIn", "对入库单进行了修改操作", entity
                .getId() + " &" + id + "&");
        return SuccessTip.create();
    }

    @BusinessLog(name = "StorageIn", value = "审核 StorageIn")
    @PutMapping("/{id}/audit")
    @ApiOperation(value = "审核", response = StorageInModel.class)
    public Tip commitToAuditStorageIn(@PathVariable Long id, @RequestBody StorageInModel entity) {
        entity.setId(id);
        Tip resultTip = SuccessTip.create(storageInService.commitStorageIn(JWTKit.getUserId(getHttpServletRequest()), id, entity));
        createStorageInLog(id, "commitToAuditStorageIn", "对入库单进行了提交审核操作", id + " &");
        return resultTip;
    }

    @BusinessLog(name = "StorageIn", value = "审核通过 StorageIn")
    @PutMapping("/{id}/passed")
    @ApiOperation(value = "审核通过", response = StorageInModel.class)
    public Tip auditStorageIn(@PathVariable Long id, @RequestBody StorageInModel entity) {

        entity.setId(id);
        Tip resultTip;
        if (entity.getTransactionType().compareTo(TransactionType.Procurement.toString()) == 0 && (entity.getProcurementId() != null)) {
            resultTip = SuccessTip.create(procurementService.auditPass(id, entity));
        } else {
            resultTip = SuccessTip.create(storageInService.passedStorageIn(id, entity));
        }
        createStorageInLog(id, "auditStorageIn", "对入库单进行了审核通过操作", id + " &");
        return resultTip;
    }

    @BusinessLog(name = "StorageIn", value = "审核拒绝，自动转化为关闭状态")
    @PutMapping("/{id}/closed")
    @ApiOperation(value = "closed StorageIn", response = StorageInModel.class)
    public Tip closedStorageIn(@PathVariable Long id) {
        Tip resultTip;
        StorageIn entity = storageInService.retrieveMaster(id);
        if (entity.getTransactionType().compareTo(TransactionType.Procurement.toString()) == 0 && (entity.getProcurementId() != null)) {
            resultTip = SuccessTip.create(procurementService.auditReject(id));
        } else {
            resultTip = SuccessTip.create(storageInService.auditRejectedStorageIn(id));
        }
        createStorageInLog(id, "closedStorageIn", "对入库单进行了审核拒绝操作", id + " &");
        return resultTip;
    }

    @BusinessLog(name = "StorageIn", value = "执行入库")
    @PutMapping("/{id}/execution")
    @ApiOperation(value = "executionRefund StorageIn", response = StorageInModel.class)
    public Tip executionStorageIn(@PathVariable Long id) {
        Tip resultTip;
        StorageIn entity = storageInService.retrieveMaster(id);
        if (entity.getTransactionType().compareTo(TransactionType.Procurement.toString()) == 0 && (entity.getProcurementId() != null)) {
            resultTip = SuccessTip.create(procurementService.executionProcurementStorageIn(id));
        } else {
            resultTip = SuccessTip.create(storageInService.executionStorageIn(JWTKit.getAccount(getHttpServletRequest()), id));
        }
        createStorageInLog(id, "executionStorage", "对入库单进行了执行入库操作", id + " &");
        return resultTip;
    }


    @BusinessLog(name = "StorageIn", value = "delete StorageIn")
    @DeleteMapping("/{id}")
    @ApiOperation(value = "删除入库单", response = StorageInModel.class)
    public Tip deleteStorageIn(@PathVariable Long id) {
        createStorageInLog(id, "deleteStorageIn", "对入库单进行了删除操作", id + " &");
        return SuccessTip.create(storageInService.deleteStorageIn(id));
    }

    @GetMapping
    @ApiOperation(value = "入库单列表", response = StorageInRecord.class)
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

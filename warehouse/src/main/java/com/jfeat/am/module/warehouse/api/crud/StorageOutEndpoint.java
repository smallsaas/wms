package com.jfeat.am.module.warehouse.api.crud;

import com.jfeat.am.core.jwt.JWTKit;
import com.jfeat.am.module.log.LogManager;
import com.jfeat.am.module.log.LogTaskFactory;
import com.jfeat.am.module.warehouse.services.definition.FormType;
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
import com.jfeat.am.module.warehouse.services.domain.dao.QueryStorageOutDao;
import com.jfeat.am.common.constant.tips.SuccessTip;
import com.jfeat.am.common.constant.tips.Tip;
import com.jfeat.am.module.log.annotation.BusinessLog;
import com.jfeat.am.common.exception.BusinessCode;
import com.jfeat.am.common.exception.BusinessException;
import com.jfeat.am.module.warehouse.services.domain.service.StorageOutService;
import com.jfeat.am.module.warehouse.services.domain.model.StorageOutRecord;
import com.jfeat.am.module.warehouse.services.domain.model.StorageOutModel;

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
 * @since 2018-06-20
 */
@RestController
@Api("WMS-出库单")
@RequestMapping("/api/wms/storages/out")
public class StorageOutEndpoint extends BaseController {


    @Resource
    StorageOutService storageOutService;

    @Resource
    QueryStorageOutDao queryStorageOutDao;



    private void createStorageOutLog(Long targetId, String methodName, String operation, String message) {
        LogManager.me().executeLog(LogTaskFactory.businessLog(JWTKit.getUserId(getHttpServletRequest()),
                JWTKit.getAccount(getHttpServletRequest()),
                operation,
                ProcurementEndpoint.class.getName(),
                methodName,
                message,
                "成功",
                targetId,
                FormType.PURCHASE.toString()
        ));
    }



    @BusinessLog(name = "StorageOut", value = "create StorageOut")
    @PostMapping
    @ApiOperation(value = "新建出库单",response = StorageOutModel.class)
    public Tip createStorageOut(@RequestBody StorageOutModel entity) {
        String userName = JWTKit.getAccount(getHttpServletRequest());
        entity.setOriginatorName(userName);
        if (entity.getWarehouseId()==null){
            entity.setWarehouseId(1L);
        }
        Integer result  = storageOutService.draftStorageOut(JWTKit.getUserId(getHttpServletRequest()),entity);
        createStorageOutLog(entity.getId(),  "createStorageOut", "对出库单进行了新建操作",  entity
                .getId() + " &");
        return SuccessTip.create(result);
    }


    @BusinessLog(name = "StorageOut", value = "create StorageOut")
    @PostMapping("/sales")
    @ApiOperation(value = "新建出库单",response = StorageOutModel.class)
    public Tip salesStorageOut(@RequestBody StorageOutModel entity) {
        String userName = JWTKit.getAccount(getHttpServletRequest());
        entity.setOriginatorName(userName);
        if (entity.getWarehouseId()==null){
            entity.setWarehouseId(1L);
        }
        Integer result  = storageOutService.salesStorageOut(JWTKit.getUserId(getHttpServletRequest()),entity);
        createStorageOutLog(entity.getId(),  "createStorageOut", "对出库单进行了商城出库操作",  entity
                .getId() + " &");
        return SuccessTip.create(result);
    }

    @GetMapping("/{id}")
    @ApiOperation(value = "查看出库单",response = StorageOutModel.class)
    public Tip getStorageOut(@PathVariable Long id) {
        return SuccessTip.create(queryStorageOutDao.storagesOutDetails(id));
    }

    @BusinessLog(name = "StorageOut", value = "update StorageOut")
    @PutMapping("/{id}")
    @ApiOperation(value = "修改出库单",response = StorageOutModel.class)
    public Tip updateStorageOut(@PathVariable Long id, @RequestBody StorageOutModel entity) {
        entity.setId(id);
        Integer result = storageOutService.updateStorageIn(JWTKit.getUserId(getHttpServletRequest()),id,entity);
        createStorageOutLog(entity.getId(),  "updateStorageOut", "对出库单进行了修改操作",  entity
                .getId() + " &" + id + "&");
        return SuccessTip.create(result);
    }

    @BusinessLog(name = "StorageOut", value = "审核 StorageOut")
    @PutMapping("/{id}/audit")
    @ApiOperation(value = "审核",response = StorageOutModel.class)
    public Tip commitToAuditStorageOut(@PathVariable Long id, @RequestBody StorageOutModel entity) {
        entity.setId(id);
        Tip resultTip = SuccessTip.create(storageOutService.commitStorageOut(JWTKit.getUserId(getHttpServletRequest()),id,entity));
        createStorageOutLog(id,  "commitToAuditStorageOut", "对出库单进行了提交审核操作",  id + " &");
        return resultTip;
    }

    @BusinessLog(name = "StorageOut", value = "审核通过 StorageOut")
    @PutMapping("/{id}/passed")
    @ApiOperation(value = "审核通过",response = StorageOutModel.class)
    public Tip auditStorage(@PathVariable Long id, @RequestBody StorageOutModel entity) {
        entity.setId(id);
        Tip resultTip = SuccessTip.create(storageOutService.passedStorageOut(id));
        createStorageOutLog(id,  "auditStorageOut", "对出库单进行了审核通过操作",  id + " &");
        return resultTip;
    }

    @BusinessLog(name = "StorageOut", value = "审核拒绝，自动转化为关闭状态")
    @PutMapping("/{id}/closed")
    @ApiOperation(value = "closed StorageIn",response = StorageOutModel.class)
    public Tip closedStorageIn(@PathVariable Long id, @RequestBody StorageOutModel entity) {
        Tip resultTip = SuccessTip.create(storageOutService.auditRejectedStorageOut(id));
        createStorageOutLog(id,  "closedStorageIn", "对出库单进行了审核拒绝操作",  id + " &");
        return resultTip;
    }


    @BusinessLog(name = "StorageOut", value = "执行入库")
    @PostMapping("/{id}/execution")
    @ApiOperation(value = "executionRefund StorageIn",response = StorageOutModel.class)
    public Tip executionStorageIn(@PathVariable Long id) {
        Tip resultTip = SuccessTip.create(storageOutService.executionStorageOut(JWTKit.getAccount(getHttpServletRequest()),id));
        createStorageOutLog(id,  "executionStorage", "对出库单进行了执行入库操作",  id + " &");
        return resultTip;
    }
    @BusinessLog(name = "StorageOut", value = "delete StorageOut")
    @DeleteMapping("/{id}")
    @ApiOperation(value = "删除出库单",response = StorageOutModel.class)
    public Tip deleteStorageOut(@PathVariable Long id) {
        Integer result = storageOutService.deleteMaster(id);
        createStorageOutLog(id,  "deleteStorageOut", "对出库单进行了删除操作",  id + " &");
        return SuccessTip.create(result);
    }

    @GetMapping
    @ApiOperation(value = "出库单列表",response = StorageOutModel.class)

    public Tip queryStorageOuts(Page<StorageOutRecord> page,
                                @RequestParam(name = "pageNum", required = false, defaultValue = "1") Integer pageNum,
                                @RequestParam(name = "pageSize", required = false, defaultValue = "10") Integer pageSize,
                                @RequestParam(name = "id", required = false) Long id,
                                @RequestParam(name = "transactionCode", required = false) String transactionCode,
                                @RequestParam(name = "transactionType", required = false) String transactionType,
                                @RequestParam(name = "transactionTime", required = false) Date transactionTime,
                                @RequestParam(name = "batchNo", required = false) String batchNo,
                                @RequestParam(name = "note", required = false) String note,
                                @RequestParam(name = "status", required = false) String status,
                                @RequestParam(name = "transactionBy", required = false) String transactionBy,
                                @RequestParam(name = "originatorId", required = false) Long originatorId,
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

        StorageOutRecord record = new StorageOutRecord();
        record.setId(id);
        record.setTransactionCode(transactionCode);
        record.setTransactionType(transactionType);
        record.setTransactionTime(transactionTime);
        record.setBatchNo(batchNo);
        record.setNote(note);
        record.setStatus(status);
        record.setTransactionBy(transactionBy);
        record.setOriginatorId(originatorId);
        record.setField1(field1);
        record.setField2(field2);

        page.setRecords(queryStorageOutDao.findStorageOutPage(page, record, orderBy));

        return SuccessTip.create(page);
    }




}

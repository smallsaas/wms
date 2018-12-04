package com.jfeat.am.module.warehouse.api.crud;

import com.alibaba.fastjson.JSONObject;
import com.baomidou.mybatisplus.plugins.Page;
import com.jfeat.am.common.constant.tips.SuccessTip;
import com.jfeat.am.common.constant.tips.Tip;
import com.jfeat.am.common.controller.BaseController;
import com.jfeat.am.common.exception.BusinessCode;
import com.jfeat.am.common.exception.BusinessException;
import com.jfeat.am.core.jwt.JWTKit;
import com.jfeat.am.module.log.LogManager;
import com.jfeat.am.module.log.LogTaskFactory;
import com.jfeat.am.module.warehouse.services.definition.FormType;
import com.jfeat.am.module.warehouse.services.domain.dao.QueryTransferDao;
import com.jfeat.am.module.warehouse.services.domain.model.TransferModel;
import com.jfeat.am.module.warehouse.services.domain.model.TransferRecord;
import com.jfeat.am.module.warehouse.services.domain.service.TransferService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.dao.DuplicateKeyException;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import java.util.Date;


/**
 * <p>
 * api
 * </p>
 *
 * @author Code Generator
 * @since 2018-06-22
 */
@RestController
@Api("WMS-库存调拨")
@RequestMapping("/api/wms/transfers")
public class TransferEndpoint extends BaseController {


    @Resource
    TransferService transferService;

    @Resource
    QueryTransferDao queryTransferDao;

    @PostMapping("/drafted")
    @ApiOperation(value = "新建  Draft调拨表",response = TransferModel.class)
    public Tip draftTransfer(@RequestBody TransferModel entity) {

        Integer affected = 0;
        try {
            String userName = JWTKit.getAccount(getHttpServletRequest());
            entity.setOriginatorName(userName);
            affected += transferService.draftTransfer(entity, JWTKit.getUserId(getHttpServletRequest()),JWTKit.getAccount(getHttpServletRequest()));

        } catch (DuplicateKeyException e) {
            throw new BusinessException(BusinessCode.DuplicateKey);
        }
        createPurchasekLog(entity.getId(), "createTransfer", "对调拨单进行了新建操作",  JSONObject.toJSONString(entity) + " &");
        return SuccessTip.create(affected);
    }

    @PutMapping("/{id}")
    @ApiOperation(value = "新建  Draft调拨表",response = TransferModel.class)
    public Tip updateTransfer(@PathVariable Long id,@RequestBody TransferModel entity) {

        Integer affected = 0;
            affected += transferService.updateTransfer(id,entity);
        createPurchasekLog(entity.getId(), "createTransfer", "对调拨单进行了新建操作",  JSONObject.toJSONString(entity) + " &");
        return SuccessTip.create(affected);
    }

    @PostMapping("/{id}/execution")
    @ApiOperation(value = "begin execution 调拨表",response = TransferModel.class)
    public Tip createTransfer(@PathVariable Long id,@RequestBody TransferModel entity) {

        Integer affected = 0;
        try {
            String userName = JWTKit.getAccount(getHttpServletRequest());
            entity.setOriginatorName(userName);
            affected += transferService.createTransfer(id,entity, JWTKit.getUserId(getHttpServletRequest()));

        } catch (DuplicateKeyException e) {
            throw new BusinessException(BusinessCode.DuplicateKey);
        }
        createPurchasekLog(entity.getId(), "createTransfer", "对调拨单进行了新建操作",  JSONObject.toJSONString(entity) + " &");
        return SuccessTip.create(affected);
    }

    @GetMapping("/{id}")
    @ApiOperation(value = "获取调拨表",response = TransferModel.class)
    public Tip getTransfer(@PathVariable Long id) {
        return SuccessTip.create(transferService.transferDetails(id));
    }

    @PostMapping("/{id}/done")
    @ApiOperation(value = "调拨完成",response = TransferModel.class)
    public Tip doneTransfer(@PathVariable Long id) {
        Tip resultTip = SuccessTip.create(transferService.doneTransfer(id, JWTKit.getUserId(getHttpServletRequest())));
        createPurchasekLog(id, "doneTransfer", "对调拨单进行了调拨完成操作",  id + " &");
        return resultTip;
    }

    @PostMapping("/{id}/cancel")
    @ApiOperation(value = "调拨作废",response = TransferModel.class)
    public Tip cancelTransfer(@PathVariable Long id) {
        Tip resultTip = SuccessTip.create(transferService.cancelTransfer(id,JWTKit.getUserId(getHttpServletRequest())));
        createPurchasekLog(id, "cancelTransfer", "对调拨单进行了调拨作废操作",  id + " &");
        return resultTip;
    }

    @DeleteMapping("/{id}")
    @ApiOperation(value = "删除调拨表",response = TransferModel.class)
    public Tip deleteTransfer(@PathVariable Long id) {
        Tip resultTip = SuccessTip.create(transferService.deleteTransfer(id));
        createPurchasekLog(id, "deleteTransfer", "对调拨单进行了删除操作",  id + " &");
        return resultTip;
    }

    @GetMapping
    @ApiOperation(value = "调拨表列表",response = TransferModel.class)
    public Tip queryTransfers(Page<TransferRecord> page,
                              @RequestParam(name = "pageNum", required = false, defaultValue = "1") Integer pageNum,
                              @RequestParam(name = "pageSize", required = false, defaultValue = "10") Integer pageSize,
                              @RequestParam(name = "id", required = false) Long id,
                              @RequestParam(name = "warehouseId", required = false) Long warehouseId,
                              @RequestParam(name = "transactionCode", required = false) String transactionCode,
                              @RequestParam(name = "fromWarehouseId", required = false) Long fromWarehouseId,
                              @RequestParam(name = "toWarehouseId", required = false) Long toWarehouseId,
                              @RequestParam(name = "transactionTime", required = false) Date[] transactionTime,
                              @RequestParam(name = "storageInId", required = false) Long storageInId,
                              @RequestParam(name = "storageOutId", required = false) Long storageOutId,
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

        Date startTime = (transactionTime!=null && transactionTime.length == 2)? transactionTime [0] : null;
        Date endTime = (transactionTime!=null && transactionTime.length == 2)? transactionTime [1] : null;

        page.setCurrent(pageNum);
        page.setSize(pageSize);

        TransferRecord record = new TransferRecord();
        record.setId(id);
        record.setTransactionCode(transactionCode);
        record.setFromWarehouseId(fromWarehouseId);
        record.setToWarehouseId(toWarehouseId);
        record.setStorageInId(storageInId);
        record.setStorageOutId(storageOutId);
        record.setNote(note);
        record.setStatus(status);
        record.setTransactionBy(transactionBy);
        record.setOriginatorId(originatorId);
        record.setField1(field1);
        record.setField2(field2);

        page.setRecords(queryTransferDao.findTransferPage(page, warehouseId,record, orderBy,startTime,endTime));

        return SuccessTip.create(page);
    }

    private void createPurchasekLog(Long targetId, String methodName, String operation,String message) {
        LogManager.me().executeLog(LogTaskFactory.businessLog(JWTKit.getUserId(getHttpServletRequest()),
                JWTKit.getAccount(getHttpServletRequest()),
                operation,
                TransferEndpoint.class.getName(),
                methodName,
                message,
                "成功",
                targetId,
                FormType.TRANSFER.toString()
        ));
    }

}

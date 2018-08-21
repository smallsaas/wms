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
import com.jfeat.am.module.warehouse.services.domain.dao.QueryTransferDao;
import com.jfeat.am.common.constant.tips.SuccessTip;
import com.jfeat.am.common.constant.tips.Tip;
import com.jfeat.am.module.log.annotation.BusinessLog;
import com.jfeat.am.common.exception.BusinessCode;
import com.jfeat.am.common.exception.BusinessException;
import com.jfeat.am.module.warehouse.services.domain.service.TransferService;
import com.jfeat.am.module.warehouse.services.domain.model.TransferRecord;
import com.jfeat.am.module.warehouse.services.domain.model.TransferModel;

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

    @BusinessLog(name = "Transfer", value = "create Transfer")
    @PostMapping
    @ApiOperation(value = "新建 调拨表",response = TransferModel.class)
    public Tip createTransfer(@RequestBody TransferModel entity) {

        Integer affected = 0;
        try {
            String userName = JWTKit.getAccount(getHttpServletRequest());
            entity.setOriginatorName(userName);
            affected = transferService.createTransfer(entity, JWTKit.getUserId(getHttpServletRequest()));

        } catch (DuplicateKeyException e) {
            throw new BusinessException(BusinessCode.DuplicateKey);
        }

        return SuccessTip.create(affected);
    }

    @GetMapping("/{id}")
    @ApiOperation(value = "获取调拨表",response = TransferModel.class)

    public Tip getTransfer(@PathVariable Long id) {
        return SuccessTip.create(transferService.transferDetails(id));
    }

    @BusinessLog(name = "Transfer", value = "update Transfer")
    @PostMapping("/{id}/done")
    @ApiOperation(value = "调拨完成",response = TransferModel.class)
    public Tip doneTransfer(@PathVariable Long id) {
        return SuccessTip.create(transferService.doneTransfer(id, JWTKit.getUserId(getHttpServletRequest())));
    }

    @BusinessLog(name = "Transfer", value = "update Transfer")
    @PostMapping("/{id}/cancel")
    @ApiOperation(value = "调拨作废",response = TransferModel.class)
    public Tip cancelTransfer(@PathVariable Long id) {
        return SuccessTip.create(transferService.cancelTransfer(id,JWTKit.getUserId(getHttpServletRequest())));
    }

    @BusinessLog(name = "Transfer", value = "delete Transfer")
    @DeleteMapping("/{id}")
    @ApiOperation(value = "删除调拨表",response = TransferModel.class)

    public Tip deleteTransfer(@PathVariable Long id) {
        return SuccessTip.create(transferService.deleteTransfer(id));
    }

    @GetMapping
    @ApiOperation(value = "调拨表列表",response = TransferModel.class)

    public Tip queryTransfers(Page<TransferRecord> page,
                              @RequestParam(name = "pageNum", required = false, defaultValue = "1") Integer pageNum,
                              @RequestParam(name = "pageSize", required = false, defaultValue = "10") Integer pageSize,
                              @RequestParam(name = "id", required = false) Long id,
                              @RequestParam(name = "transactionCode", required = false) String transactionCode,
                              @RequestParam(name = "fromWarehouseId", required = false) Long fromWarehouseId,
                              @RequestParam(name = "toWarehouseId", required = false) Long toWarehouseId,
                              @RequestParam(name = "transactionTime", required = false) Date transactionTime,
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
        page.setCurrent(pageNum);
        page.setSize(pageSize);

        TransferRecord record = new TransferRecord();
        record.setId(id);
        record.setTransactionCode(transactionCode);
        record.setFromWarehouseId(fromWarehouseId);
        record.setToWarehouseId(toWarehouseId);
        record.setTransactionTime(transactionTime);
        record.setStorageInId(storageInId);
        record.setStorageOutId(storageOutId);
        record.setNote(note);
        record.setStatus(status);
        record.setTransactionBy(transactionBy);
        record.setOriginatorId(originatorId);
        record.setField1(field1);
        record.setField2(field2);

        page.setRecords(queryTransferDao.findTransferPage(page, record, orderBy));

        return SuccessTip.create(page);
    }


}

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
import com.jfeat.am.module.warehouse.services.domain.dao.QueryProcurementDao;
import com.jfeat.am.common.constant.tips.SuccessTip;
import com.jfeat.am.common.constant.tips.Tip;
import com.jfeat.am.module.log.annotation.BusinessLog;
import com.jfeat.am.common.exception.BusinessCode;
import com.jfeat.am.common.exception.BusinessException;

import java.math.BigDecimal;

import com.jfeat.am.module.warehouse.services.domain.service.ProcurementService;
import com.jfeat.am.module.warehouse.services.domain.model.ProcurementRecord;
import com.jfeat.am.module.warehouse.services.domain.model.ProcurementModel;

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
@Api("WMS-采购订单")
@RequestMapping("/api/wms/procurements")
public class ProcurementEndpoint extends BaseController {


    @Resource
    ProcurementService procurementService;

    @Resource
    QueryProcurementDao queryProcurementDao;

    @BusinessLog(name = "Procurement", value = "create Procurement")
    @PostMapping
    @ApiOperation(value = "新建采购表单",response = ProcurementModel.class)
    public Tip createProcurement(@RequestBody ProcurementModel entity) {
        Integer affected = 0;
        try {
            affected = procurementService.addProcurement(JWTKit.getUserId(getHttpServletRequest()),entity);

        } catch (DuplicateKeyException e) {
            throw new BusinessException(BusinessCode.DuplicateKey);
        }

        return SuccessTip.create(affected);
    }

    @GetMapping("/{id}")
    @ApiOperation(value = "查看采购表单",response = ProcurementModel.class)

    public Tip getProcurement(@PathVariable Long id) {
        return SuccessTip.create(procurementService.procurementDetails(id));
    }

    @BusinessLog(name = "Procurement", value = "update Procurement")
    @PutMapping("/{id}")
    @ApiOperation(value = "更新采购表单",response = ProcurementModel.class)

    public Tip updateProcurement(@PathVariable Long id, @RequestBody ProcurementModel entity) {
        entity.setId(id);
        return SuccessTip.create(procurementService.executionStorageIn(JWTKit.getUserId(getHttpServletRequest()),id,entity));
    }

    @BusinessLog(name = "Procurement", value = "delete Procurement")
    @DeleteMapping("/{id}")
    @ApiOperation(value = "删除采购表单",response = ProcurementModel.class)

    public Tip deleteProcurement(@PathVariable Long id) {
        return SuccessTip.create(procurementService.deleteProcurement(id));
    }

    @GetMapping
    @ApiOperation(value = "采购表单列表",response = ProcurementModel.class)

    public Tip queryProcurements(Page<ProcurementRecord> page,
                                 @RequestParam(name = "pageNum", required = false, defaultValue = "1") Integer pageNum,
                                 @RequestParam(name = "pageSize", required = false, defaultValue = "10") Integer pageSize,
                                 @RequestParam(name = "id", required = false) Long id,
                                 @RequestParam(name = "procurementCode", required = false) String procurementCode,
                                 @RequestParam(name = "supplierId", required = false) Long supplierId,
                                 @RequestParam(name = "procurementOthersPayment", required = false) BigDecimal procurementOthersPayment,
                                 @RequestParam(name = "procurementDiscount", required = false) Integer procurementDiscount,
                                 @RequestParam(name = "procurementTotal", required = false) BigDecimal procurementTotal,
                                 @RequestParam(name = "procurementTime", required = false) Date procurementTime,
                                 @RequestParam(name = "procurementNote", required = false) String procurementNote,
                                 @RequestParam(name = "procureStatus", required = false) String procureStatus,
                                 @RequestParam(name = "operator", required = false) Long operator,
                                 @RequestParam(name = "originatorId", required = false) Long originatorId,
                                 @RequestParam(name = "transactionTime", required = false) Date transactionTime,
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

        ProcurementRecord record = new ProcurementRecord();
        record.setId(id);
        record.setProcurementCode(procurementCode);
        record.setSupplierId(supplierId);
        record.setProcurementOthersPayment(procurementOthersPayment);
        record.setProcurementTotal(procurementTotal);
        record.setProcurementDiscount(procurementDiscount);
        record.setProcurementTime(procurementTime);
        record.setProcurementNote(procurementNote);
        record.setProcureStatus(procureStatus);
        record.setOperator(operator);
        record.setOriginatorId(originatorId);
        record.setTransactionTime(transactionTime);
        record.setField1(field1);
        record.setField2(field2);

        page.setRecords(queryProcurementDao.findProcurementPage(page, record, orderBy));

        return SuccessTip.create(page);
    }


}

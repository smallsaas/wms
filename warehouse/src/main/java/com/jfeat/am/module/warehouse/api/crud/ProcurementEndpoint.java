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
import com.jfeat.am.module.log.annotation.BusinessLog;
import com.jfeat.am.module.warehouse.services.definition.FormType;
import com.jfeat.am.module.warehouse.services.definition.ProcurementStatus;
import com.jfeat.am.module.warehouse.services.domain.dao.QueryProcurementDao;
import com.jfeat.am.module.warehouse.services.domain.model.ProcurementModel;
import com.jfeat.am.module.warehouse.services.domain.model.ProcurementRecord;
import com.jfeat.am.module.warehouse.services.domain.service.ProcurementService;
import com.jfeat.am.module.warehouse.services.persistence.model.Procurement;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.dao.DuplicateKeyException;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import java.math.BigDecimal;
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

    @PostMapping
    @ApiOperation(value = "新建采购表单",response = ProcurementModel.class)
    public Tip createProcurement(@RequestBody ProcurementModel entity) {
        Integer affected = 0;
        try {
            String userName = JWTKit.getAccount(getHttpServletRequest());
            entity.setOriginatorName(userName);
            affected = procurementService.addProcurement(JWTKit.getUserId(getHttpServletRequest()),entity);
        } catch (DuplicateKeyException e) {
            throw new BusinessException(5000,"采购单编号重复");
        }
        createPurchasekLog(entity.getId(),  "createProcurement", "对采购单进行了新建操作", JSONObject.toJSONString(entity) + " &");
        // createPurchasekLog
        return SuccessTip.create(affected);
    }

    @PostMapping("/{id}/commit")
    @ApiOperation(value = "提交采购单")
    public Tip commitProcurement(@PathVariable Long id) {
        Integer affected = 0;
        Procurement procurement = new Procurement();
        procurement.setId(id);
        procurement.setProcureStatus(ProcurementStatus.Wait_Audit.toString());
        if(procurement.getId() != null) {
            affected += procurementService.updateMaster(procurement);
            createPurchasekLog(id,  "commitProcurement", "对采购单进行了提交操作", id + " &");
        }
        return SuccessTip.create(affected);
    }

    @PostMapping("/{id}/reject")
    @ApiOperation(value = "采购单审核拒绝")
    public Tip reject(@PathVariable Long id) {
        Integer affected = 0;
        affected += procurementService.closedProcurment(id);
        createPurchasekLog(id,  "reject", "对采购单进行了审核拒绝操作",  id + " &");
        return SuccessTip.create(affected);
    }

    @PostMapping("/{id}/pass")
    @ApiOperation(value = "采购单审核通过")
    public Tip pass(@PathVariable Long id) {
        Integer affected = 0;
        Procurement procurement = new Procurement();
        procurement.setId(id);
        procurement.setProcureStatus(ProcurementStatus.WaitForStorageIn.toString());
        if(procurement.getId() != null) {
            affected += procurementService.updateMaster(procurement);
            createPurchasekLog(id,  "pass", "对采购单进行了审核通过操作",  id + " &");
        }
        return SuccessTip.create(affected);
    }


    @GetMapping("/{id}")
    @ApiOperation(value = "查看采购表单",response = ProcurementModel.class)
    public Tip getProcurement(@PathVariable Long id) {
        return SuccessTip.create(procurementService.procurementDetails(id));
    }

    @PutMapping("/{id}")
    @ApiOperation(value = "更新采购单",response = ProcurementModel.class)
    public Tip updateProcurement(@PathVariable Long id, @RequestBody ProcurementModel entity) {
        entity.setId(id);
        Tip resultTip = SuccessTip.create(procurementService.updateProcurement(JWTKit.getUserId(getHttpServletRequest()),id,entity));
        createPurchasekLog(id,  "updateProcurement", "对采购单进行了更新操作", JSONObject.toJSONString(entity) + " & " + id + " &");
        return resultTip;
    }

    @PutMapping("/{id}/excution")
    @ApiOperation(value = "入库",response = ProcurementModel.class)
    public Tip excutionProcurement(@PathVariable Long id, @RequestBody ProcurementModel entity) {
        entity.setId(id);
        Tip resultTip = SuccessTip.create(procurementService.executionStorageIn(JWTKit.getUserId(getHttpServletRequest()),id,entity));
        createPurchasekLog(id,  "excutionProcurement", "对采购单进行了入库操作", JSONObject.toJSONString(entity) + " & " + id + " &");
        return resultTip;
    }

    @BusinessLog(name = "Procurement", value = "update Procurement")
    @PutMapping("/{id}/closed")
    @ApiOperation(value = "closed",response = ProcurementModel.class)
    public Tip excutionProcurement(@PathVariable Long id) {
        Tip resultTip = SuccessTip.create(procurementService.closedProcurment(id));
        createPurchasekLog(id,  "excutionProcurement", "对采购单进行了关闭操作",  id + " &");
        return resultTip;
    }


    @DeleteMapping("/{id}")
    @ApiOperation(value = "删除采购表单",response = ProcurementModel.class)
    public Tip deleteProcurement(@PathVariable Long id) {
        Tip resultTip = SuccessTip.create(procurementService.deleteProcurement(id));
        createPurchasekLog(id,  "deleteProcurement", "对采购单进行了删除操作",  id + " &");
        return resultTip;
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
                                 @RequestParam(name = "procurementNote", required = false) String procurementNote,
                                 @RequestParam(name = "procureStatus", required = false) String procureStatus,
                                 @RequestParam(name = "transactionBy", required = false) String transactionBy,
                                 @RequestParam(name = "originatorId", required = false) Long originatorId,
                                 @RequestParam(name = "transactionTime", required = false) Date transactionTime,
                                 @RequestParam(name = "field1", required = false) String field1,
                                 @RequestParam(name = "waitForStorageIn", required = false) String waitForStorageIn,
                                 @RequestParam(name = "search", required = false) String search,
                                 @RequestParam(name = "status", required = false) String status,
                                 @RequestParam(name = "field2", required = false) String field2,
                                 @RequestParam(name = "orderBy", required = false) String orderBy,
                                 @RequestParam(name = "sort", required = false) String sort,
                                 @RequestParam(required = false) @DateTimeFormat(pattern = "yyyy-MM-dd")Date[] procurementTime) {
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

        Date startTime = (procurementTime!=null && procurementTime.length == 2)? procurementTime [0] : null;
        Date endTime = (procurementTime!=null && procurementTime.length == 2)? procurementTime [1] : null;


        page.setCurrent(pageNum);
        page.setSize(pageSize);

        ProcurementRecord record = new ProcurementRecord();
        record.setId(id);
        record.setProcurementCode(procurementCode);
        record.setSupplierId(supplierId);
        record.setProcurementOthersPayment(procurementOthersPayment);
        record.setProcurementTotal(procurementTotal);
        record.setProcurementDiscount(procurementDiscount);
        record.setProcurementNote(procurementNote);
        record.setProcureStatus(procureStatus);
        record.setTransactionBy(transactionBy);
        record.setOriginatorId(originatorId);
        record.setTransactionTime(transactionTime);
        record.setField1(field1);
        record.setField2(field2);

        page.setRecords(queryProcurementDao.findProcurementPage(page, record, orderBy,waitForStorageIn,search,status,startTime, endTime));

        return SuccessTip.create(page);
    }

    private void createPurchasekLog(Long targetId, String methodName, String operation,String message) {
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
}

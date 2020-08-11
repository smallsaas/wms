package com.jfeat.am.module.warehouse.api.crud;

import com.alibaba.fastjson.JSONObject;
import com.jfeat.am.core.jwt.JWTKit;
import com.jfeat.am.module.warehouse.log.LogManager;
import com.jfeat.am.module.warehouse.log.LogTaskFactory;
import com.jfeat.am.module.warehouse.services.definition.FormType;
import com.jfeat.am.module.warehouse.services.definition.RefundStatus;
import com.jfeat.am.module.warehouse.services.persistence.model.Refund;
import com.jfeat.crud.base.exception.BusinessCode;
import com.jfeat.crud.base.exception.BusinessException;
import com.jfeat.crud.base.tips.SuccessTip;
import com.jfeat.crud.base.tips.Tip;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.format.annotation.DateTimeFormat;
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
import com.jfeat.am.module.warehouse.services.domain.dao.QueryRefundDao;
import com.jfeat.am.module.log.annotation.BusinessLog;
import com.jfeat.am.module.warehouse.services.domain.service.RefundService;
import com.jfeat.am.module.warehouse.services.domain.model.RefundRecord;
import com.jfeat.am.module.warehouse.services.domain.model.RefundModel;

import org.springframework.web.bind.annotation.RestController;

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
@Api("WMS-采购退货")
@RequestMapping("/api/wms/refunds")
public class RefundEndpoint   {

    @Resource
    RefundService refundService;

    @Resource
    QueryRefundDao queryRefundDao;


    private void createRefundLog(Long targetId, String methodName, String operation,String message) {
        LogManager.me().executeLog(LogTaskFactory.businessLog(JWTKit.getUserId(),
                JWTKit.getAccount(),
                operation,
                RefundEndpoint.class.getName(),
                methodName,
                message,
                "成功",
                targetId,
                FormType.REFUND.toString()
        ));
    }

    @BusinessLog(name = "Refund", value = "create Refund")
    @PostMapping
    @ApiOperation(value = "新建退货表",response = RefundModel.class)
    public Tip createRefund(@RequestBody RefundModel entity) {

        Integer affected = 0;
        try {
            String userName = JWTKit.getAccount();
            entity.setOriginatorName(userName);
            affected += refundService.createRefund(JWTKit.getUserId( ),entity);

        } catch (DuplicateKeyException e) {
            throw new BusinessException(BusinessCode.DuplicateKey);
        }
        createRefundLog(entity.getId(),  "createRefund", "对退货表进行了新建操作",  JSONObject.toJSONString(entity) + " &");
        return SuccessTip.create(affected);
    }

    @GetMapping("/{id}")
    @ApiOperation(value = "查看退货表",response = RefundModel.class)
    public Tip getRefund(@PathVariable Long id) {
        return SuccessTip.create(refundService.refundDetails(id));
    }

    @BusinessLog(name = "Refund", value = "update Refund")
    @PutMapping("/{id}")
    @ApiOperation(value = "修改退货表",response = RefundModel.class)
    public Tip updateRefund(@PathVariable Long id, @RequestBody RefundModel entity) {
        entity.setId(id);
        Integer result = refundService.updateRefund(id,entity);
        createRefundLog(entity.getId(),  "updateRefund", "对退货表进行了修改操作",  JSONObject.toJSONString(entity) + " &" + id + "&");
        return SuccessTip.create(result);
    }

    @PutMapping("/{id}/audit")
    @ApiOperation(value = "修改退货表and提交 审核")
    public Tip audit(@PathVariable Long id ,@RequestBody RefundModel entity) {
        Integer affected = 0;
        affected += refundService.updateAndCommitRefund(id,entity);
        createRefundLog(id,  "audit", "对退货表进行了修改操作",  JSONObject.toJSONString(entity) + " &" + id + "&");
        return SuccessTip.create(affected);
    }

    @PutMapping("/{id}/closed")
    @ApiOperation(value = "退货表审核拒绝")
    public Tip reject(@PathVariable Long id) {
        Integer affected = 0;
        Refund refund = new Refund();
        refund.setId(id);
        refund.setProductRefundStatus(RefundStatus.Closed.toString());
        if(refund.getId() != null) {
            affected += refundService.updateMaster(refund);
        }
        createRefundLog(id,  "reject", "对退货表进行了审核拒绝操作",   id + "&");
        return SuccessTip.create(affected);
    }

    @PutMapping("/{id}/passed")
    @ApiOperation(value = "退货表审核通过")
    public Tip pass(@PathVariable Long id ,@RequestBody RefundModel entity) {
        Integer affected = refundService.auditPassed(id,JWTKit.getAccount(),JWTKit.getUserId( ),entity);
        createRefundLog(id,  "pass", "对退货表进行了审核通过操作",   id + "&");
        return SuccessTip.create(affected);
    }


    @PostMapping("/{id}/execution")
    @ApiOperation(value = "执行退货")
    public Tip execution (@PathVariable Long id) {
        Integer affected =  refundService.executionRefund(JWTKit.getAccount( ),JWTKit.getUserId( ),id);
        createRefundLog(id,  "execution", "对退货表进行了执行退货操作",   id + "&");

        return SuccessTip.create(affected);
    }



    @BusinessLog(name = "Refund", value = "delete Refund")
    @DeleteMapping("/{id}")
    @ApiOperation(value = "删除退货表单",response = RefundModel.class)

    public Tip deleteRefund(@PathVariable Long id) {
        Integer result = refundService.deleteRefund(id);

        createRefundLog(id,  "deleteRefund", "对退货表进行了删除退货表单操作",   id + "&");
        return SuccessTip.create(result);
    }

    @GetMapping
    @ApiOperation(value = "退货表列表",response = RefundModel.class)
    public Tip queryRefunds(Page<RefundRecord> page,
                            @RequestParam(name = "pageNum", required = false, defaultValue = "1") Integer pageNum,
                            @RequestParam(name = "pageSize", required = false, defaultValue = "10") Integer pageSize,
                            @RequestParam(name = "id", required = false) Long id,
                            @RequestParam(name = "productRefundCode", required = false) String productRefundCode,
                            @RequestParam(name = "productProcurementId", required = false) Long productProcurementId,
                            @RequestParam(name = "storageInId", required = false) Long storageInId,
                            @RequestParam(name = "product_refundWarehouseId", required = false) Long productRefundWarehouseId,
                            @RequestParam(name = "productRefundQuantities", required = false) Integer productRefundQuantities,
                            @RequestParam(name = "productRefundStatus", required = false) String productRefundStatus,
                            @RequestParam(name = "productRefundNote", required = false) String productRefundNote,
                            @RequestParam(name = "originatorId", required = false) Long originatorId,
                            @RequestParam(name = "transactionBy", required = false) String transactionBy,
                            @RequestParam(name = "transactionTime", required = false) Date transactionTime,
                            @RequestParam(name = "field1", required = false) String field1,
                            @RequestParam(name = "field2", required = false) String field2,
                            @RequestParam(name = "orderBy", required = false) String orderBy,
                            @RequestParam(name = "sort", required = false) String sort,
                            @RequestParam(name = "search", required = false) String search,
                            @RequestParam(required = false) @DateTimeFormat(pattern = "yyyy-MM-dd")Date[]productRefundTime) {
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

        Date startTime = (productRefundTime!=null && productRefundTime.length == 2)? productRefundTime [0] : null;
        Date endTime = (productRefundTime!=null && productRefundTime.length == 2)? productRefundTime [1] : null;

        page.setCurrent(pageNum);
        page.setSize(pageSize);

        RefundRecord record = new RefundRecord();
        record.setId(id);
        record.setProductRefundCode(productRefundCode);
        record.setProductProcurementId(productProcurementId);
        record.setStorageOutId(storageInId);
        record.setProductRefundWarehouseId(productRefundWarehouseId);
        record.setProductRefundQuantities(productRefundQuantities);
        record.setProductRefundStatus(productRefundStatus);
        record.setProductRefundNote(productRefundNote);
        record.setOriginatorId(originatorId);
        record.setTransactionBy(transactionBy);
        record.setTransactionTime(transactionTime);
        record.setField1(field1);
        record.setField2(field2);

        page.setRecords(queryRefundDao.findRefundPage(page, record, orderBy,search,startTime, endTime));

        return SuccessTip.create(page);
    }


}

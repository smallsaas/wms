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
import com.jfeat.am.module.warehouse.services.domain.dao.QueryRefundDao;
import com.jfeat.am.common.constant.tips.SuccessTip;
import com.jfeat.am.common.constant.tips.Tip;
import com.jfeat.am.module.log.annotation.BusinessLog;
import com.jfeat.am.common.exception.BusinessCode;
import com.jfeat.am.common.exception.BusinessException;
import com.jfeat.am.module.warehouse.services.domain.service.RefundService;
import com.jfeat.am.module.warehouse.services.domain.model.RefundRecord;
import com.jfeat.am.module.warehouse.services.domain.model.RefundModel;

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
@Api("WMS-采购退货")
@RequestMapping("/api/wms/refunds")
public class RefundEndpoint extends BaseController {


    @Resource
    RefundService refundService;

    @Resource
    QueryRefundDao queryRefundDao;

    @BusinessLog(name = "Refund", value = "create Refund")
    @PostMapping
    @ApiOperation(value = "新建退货表",response = RefundModel.class)
    public Tip createRefund(@RequestBody RefundModel entity) {

        Integer affected = 0;
        try {
            String userName = JWTKit.getAccount(getHttpServletRequest());
            entity.setOriginatorName(userName);
            affected = refundService.createRefund(JWTKit.getUserId(getHttpServletRequest()),entity);

        } catch (DuplicateKeyException e) {
            throw new BusinessException(BusinessCode.DuplicateKey);
        }

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
        return SuccessTip.create(refundService.updateRefund(JWTKit.getUserId(getHttpServletRequest()),entity));
    }

    @BusinessLog(name = "Refund", value = "delete Refund")
    @DeleteMapping("/{id}")
    @ApiOperation(value = "删除退货表单",response = RefundModel.class)

    public Tip deleteRefund(@PathVariable Long id) {
        return SuccessTip.create(refundService.deleteRefund(id));
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
                            @RequestParam(name = "productRefundTime", required = false) Date productRefundTime,
                            @RequestParam(name = "productRefundStatus", required = false) String productRefundStatus,
                            @RequestParam(name = "productRefundNote", required = false) String productRefundNote,
                            @RequestParam(name = "originatorId", required = false) Long originatorId,
                            @RequestParam(name = "transactionBy", required = false) String transactionBy,
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

        RefundRecord record = new RefundRecord();
        record.setId(id);
        record.setProductRefundCode(productRefundCode);
        record.setProductProcurementId(productProcurementId);
        record.setStorageOutId(storageInId);
        record.setProductRefundWarehouseId(productRefundWarehouseId);
        record.setProductRefundQuantities(productRefundQuantities);
        record.setProductRefundTime(productRefundTime);
        record.setProductRefundStatus(productRefundStatus);
        record.setProductRefundNote(productRefundNote);
        record.setOriginatorId(originatorId);
        record.setTransactionBy(transactionBy);
        record.setTransactionTime(transactionTime);
        record.setField1(field1);
        record.setField2(field2);

        page.setRecords(queryRefundDao.findRefundPage(page, record, orderBy));

        return SuccessTip.create(page);
    }


}

package com.jfeat.am.module.warehouse.api.crud;

import com.alibaba.fastjson.JSONObject;
import com.baomidou.mybatisplus.plugins.Page;
import com.jfeat.am.common.constant.tips.Ids;
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
import com.jfeat.am.module.warehouse.services.definition.SalesStatus;
import com.jfeat.am.module.warehouse.services.domain.dao.QuerySalesDao;
import com.jfeat.am.module.warehouse.services.domain.model.SalesModel;
import com.jfeat.am.module.warehouse.services.domain.model.SalesRecord;
import com.jfeat.am.module.warehouse.services.domain.service.SalesService;
import com.jfeat.am.module.warehouse.services.persistence.model.Sales;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.dao.DuplicateKeyException;
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
 * @since 2018-11-14
 */
@RestController
@Api("分销商出库")
@RequestMapping("/api/warehouse/sales")
public class SalesEndpoint extends BaseController {


    @Resource
    SalesService salesService;

    @Resource
    QuerySalesDao querySalesDao;

    @PostMapping
    @ApiOperation("Create table record")
    public Tip createSales(@RequestBody SalesModel entity) {

        Integer affected = 0;
        String userName = JWTKit.getAccount(getHttpServletRequest());
        entity.setOriginatorName(userName);
        Long userId = JWTKit.getUserId(getHttpServletRequest());
        try {
            affected = salesService.createSales(userId,entity);

        } catch (DuplicateKeyException e) {
            throw new BusinessException(BusinessCode.DuplicateKey);
        }
        createSalesLog(entity.getId(),  "createSales", "对分销商出库进行了新建操作",  JSONObject.toJSONString(entity) + " &");
        return SuccessTip.create(affected);
    }

    @PostMapping("/{id}/commit")
    @ApiOperation(value = "分销商出库提交")
    public Tip commit(@PathVariable Long id) {
        Integer affected = 0;
        Sales sales = new Sales();
        sales.setId(id);
        sales.setSalesStatus(SalesStatus.Wait_Audit.toString());
        if(sales.getId() != null) {
            affected += salesService.updateMaster(sales);
            createSalesLog(id,  "commit", "对分销商出库进行了提交操作", id + " &");
        }
        return SuccessTip.create(affected);
    }

    @PostMapping("/{id}/reject")
    @ApiOperation(value = "分销商出库审核拒绝")
    public Tip reject(@PathVariable Long id) {
        Integer affected = 0;
        Sales sales = new Sales();
        sales.setId(id);
        sales.setSalesStatus(SalesStatus.Closed.toString());
        if(sales.getId() != null) {
            affected += salesService.updateMaster(sales);
            createSalesLog(id,  "reject", "对分销商出库进行了审核拒绝操作", id + " &");
        }
        return SuccessTip.create(affected);
    }

    @PostMapping("/{id}/pass")
    @ApiOperation(value = "分销商出库审核通过")
    public Tip pass(@PathVariable Long id) {
        Integer affected = 0;
        Sales sales = new Sales();
        sales.setId(id);
        sales.setSalesStatus(SalesStatus.WaitForStorageOut.toString());
        if(sales.getId() != null) {
            affected += salesService.updateMaster(sales);
            createSalesLog(id,  "pass", "对分销商出库进行了审核通过操作", id + " &");
        }
        return SuccessTip.create(affected);
    }


    @GetMapping("/{id}")
    @ApiOperation("get more salesDetails")
    public Tip getSales(@PathVariable Long id) {
        return SuccessTip.create(salesService.salesDetails(id));
    }

    @PutMapping("/{id}")
    @ApiOperation("update record while record status is Wait for storage out")
    public Tip updateSales(@PathVariable Long id, @RequestBody SalesModel entity) {
        entity.setId(id);
        Long userId = JWTKit.getUserId(getHttpServletRequest());
        Tip resultTip = SuccessTip.create(salesService.updateSales(userId,id,entity));

        createSalesLog(id,  "updateSales", "对分销商出库进行了更新操作",  JSONObject.toJSONString(entity) + " & " + id + " &");
        return resultTip;
    }

    @BusinessLog(name = "SalesModel", value = "update SalesModel")
    @PutMapping("/{id}/excution")
    @ApiOperation(value = "入库",response = SalesModel.class)
    public Tip excutionProcurement(@PathVariable Long id, @RequestBody SalesModel entity) {
        entity.setId(id);
        Tip resultTip = SuccessTip.create(salesService.executionStorageOut(JWTKit.getUserId(getHttpServletRequest()),id,entity));

        createSalesLog(id,  "excutionProcurement", "对分销商出库进行了入库操作",  JSONObject.toJSONString(entity) + " & " + id + " &");
        return resultTip;
    }


    @BusinessLog(name = "Sales", value = "delete Sales")
    @DeleteMapping("/{id}")
    @ApiOperation("delete one record")
    public Tip deleteSales(@PathVariable Long id) {
        Tip resultTip = SuccessTip.create(salesService.deleteSales(id));

        createSalesLog(id,  "deleteSales", "对分销商出库进行了删除操作",  id + " &");
        return resultTip;
    }

    @GetMapping
    @ApiOperation("record list")
    public Tip querySaleses(Page<SalesRecord> page,
                            @RequestParam(name = "pageNum", required = false, defaultValue = "1") Integer pageNum,
                            @RequestParam(name = "pageSize", required = false, defaultValue = "10") Integer pageSize,
                            @RequestParam(name = "id", required = false) Long id,
                            @RequestParam(name = "salesCode", required = false) String salesCode,
                            @RequestParam(name = "traderId", required = false) Long traderId,
                            @RequestParam(name = "sales_othersPayment", required = false) BigDecimal salesOthersPayment,
                            @RequestParam(name = "salesDiscount", required = false) Integer salesDiscount,
                            @RequestParam(name = "salesTotal", required = false) BigDecimal salesTotal,
                            @RequestParam(name = "salesTime", required = false) Date salesTime,
                            @RequestParam(name = "salesNote", required = false) String salesNote,
                            @RequestParam(name = "salesStatus", required = false) String salesStatus,
                            @RequestParam(name = "transactionBy", required = false) String transactionBy,
                            @RequestParam(name = "originatorId", required = false) Long originatorId,
                            @RequestParam(name = "originatorName", required = false) String originatorName,
                            @RequestParam(name = "transactionTime", required = false) Date[] transactionTime,
                            @RequestParam(name = "field1", required = false) String field1,
                            @RequestParam(name = "field2", required = false) String field2,
                            @RequestParam(name = "traderName", required = false) String traderName,
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


        SalesRecord record = new SalesRecord();
        record.setId(id);
        record.setSalesCode(salesCode);
        record.setTraderId(traderId);
        record.setSalesOthersPayment(salesOthersPayment);
        record.setSalesDiscount(salesDiscount);
        record.setSalesTotal(salesTotal);
        record.setSalesTime(salesTime);
        record.setSalesNote(salesNote);
        record.setSalesStatus(salesStatus);
        record.setTransactionBy(transactionBy);
        record.setOriginatorId(originatorId);
        record.setOriginatorName(originatorName);
        record.setField1(field1);
        record.setField2(field2);

        page.setRecords(querySalesDao.findSalesPage(page, traderName,record, orderBy,startTime,endTime));

        return SuccessTip.create(page);
    }

    @BusinessLog(name = "Sales", value = "delete Sales")
    @PostMapping("/bulk/delete")
    public Tip deleteList(@RequestBody Ids ids) {
        Tip resultTip = SuccessTip.create(salesService.bulkDelete(ids));
        for(Long id : ids.getIds()) {
            createSalesLog(id,  "deleteList", "对分销商出库进行了批量删除操作",  id + " &");
        }
        return resultTip;
    }

    private void createSalesLog(Long targetId, String methodName, String operation,String message) {
        LogManager.me().executeLog(LogTaskFactory.businessLog(JWTKit.getUserId(getHttpServletRequest()),
                JWTKit.getAccount(getHttpServletRequest()),
                operation,
                SalesEndpoint.class.getName(),
                methodName,
                message,
                "成功",
                targetId,
                FormType.DISTRIBUTOR_OUT.toString()
        ));
    }
}

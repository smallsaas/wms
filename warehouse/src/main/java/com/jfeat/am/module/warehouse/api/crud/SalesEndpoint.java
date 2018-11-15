package com.jfeat.am.module.warehouse.api.crud;

import com.jfeat.am.module.warehouse.services.domain.model.SalesRecord;
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
import com.jfeat.am.module.warehouse.services.domain.dao.QuerySalesDao;
import com.jfeat.am.common.constant.tips.SuccessTip;
import com.jfeat.am.common.constant.tips.Ids;
import com.jfeat.am.common.constant.tips.Tip;
import com.jfeat.am.module.log.annotation.BusinessLog;
import com.jfeat.am.common.exception.BusinessCode;
import com.jfeat.am.common.exception.BusinessException;
import com.jfeat.am.module.warehouse.services.domain.service.SalesService;
import com.jfeat.am.module.warehouse.services.persistence.model.Sales;

import org.springframework.web.bind.annotation.RestController;
import com.jfeat.am.common.controller.BaseController;

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

@RequestMapping("/api/warehouse/sales")
public class SalesEndpoint extends BaseController {


    @Resource
    SalesService salesService;

    @Resource
    QuerySalesDao querySalesDao;

    @BusinessLog(name = "Sales", value = "create Sales")
    @PostMapping
    public Tip createSales(@RequestBody Sales entity) {

        Integer affected = 0;
        try {
            affected = salesService.createMaster(entity);

        } catch (DuplicateKeyException e) {
            throw new BusinessException(BusinessCode.DuplicateKey);
        }

        return SuccessTip.create(affected);
    }

    @GetMapping("/{id}")
    public Tip getSales(@PathVariable Long id) {
        return SuccessTip.create(salesService.retrieveMaster(id));
    }

    @BusinessLog(name = "Sales", value = "update Sales")
    @PutMapping("/{id}")
    public Tip updateSales(@PathVariable Long id, @RequestBody Sales entity) {
        entity.setId(id);
        return SuccessTip.create(salesService.updateMaster(entity));
    }

    @BusinessLog(name = "Sales", value = "delete Sales")
    @DeleteMapping("/{id}")
    public Tip deleteSales(@PathVariable Long id) {
        return SuccessTip.create(salesService.deleteMaster(id));
    }

    @GetMapping
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
        record.setTransactionTime(transactionTime);
        record.setField1(field1);
        record.setField2(field2);

        page.setRecords(querySalesDao.findSalesPage(page, record, orderBy));

        return SuccessTip.create(page);
    }


    @PostMapping("/bulk/delete")
    public Tip deleteList(@RequestBody Ids ids) {
        return SuccessTip.create(salesService.bulkDelete(ids));
    }

}

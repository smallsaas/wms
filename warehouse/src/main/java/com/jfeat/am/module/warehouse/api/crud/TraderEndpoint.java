package com.jfeat.am.module.warehouse.api.crud;

import com.baomidou.mybatisplus.plugins.Page;
import com.jfeat.am.common.constant.tips.Ids;
import com.jfeat.am.common.constant.tips.SuccessTip;
import com.jfeat.am.common.constant.tips.Tip;
import com.jfeat.am.common.controller.BaseController;
import com.jfeat.am.common.exception.BusinessCode;
import com.jfeat.am.common.exception.BusinessException;
import com.jfeat.am.module.log.annotation.BusinessLog;
import com.jfeat.am.module.warehouse.services.domain.dao.QueryTraderDao;
import com.jfeat.am.module.warehouse.services.domain.model.TraderModel;
import com.jfeat.am.module.warehouse.services.domain.model.TraderRecord;
import com.jfeat.am.module.warehouse.services.domain.service.TraderService;
import com.jfeat.am.module.warehouse.services.persistence.model.Trader;
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
 * @since 2018-11-14
 */
@RestController
@Api("分销商")
@RequestMapping("/api/warehouse/traders")
public class TraderEndpoint extends BaseController {


    @Resource
    TraderService traderService;

    @Resource
    QueryTraderDao queryTraderDao;

    @BusinessLog(name = "Trader", value = "create Trader")
    @PostMapping
    @ApiOperation("create 分销商")
    public Tip createTrader(@RequestBody TraderModel entity) {

        Integer affected = 0;
        try {
            affected = traderService.createMaster(entity);

        } catch (DuplicateKeyException e) {
            throw new BusinessException(5000,"分销商编号重复");
        }

        return SuccessTip.create(affected);
    }

    @GetMapping("/{id}")
    @ApiOperation("分销商details")
    public Tip getTrader(@PathVariable Long id) {
        return SuccessTip.create(queryTraderDao.traderDetails(id));
    }

    @BusinessLog(name = "Trader", value = "update Trader")
    @PutMapping("/{id}")
    @ApiOperation("modified 分销商 salesDetails")
    public Tip updateTrader(@PathVariable Long id, @RequestBody Trader entity) {
        entity.setId(id);
        return SuccessTip.create(traderService.updateMaster(entity));
    }

    @BusinessLog(name = "Trader", value = "delete Trader")
    @DeleteMapping("/{id}")
    @ApiOperation("delete useless 分销商 salesDetails")
    public Tip deleteTrader(@PathVariable Long id) {
        return SuccessTip.create(traderService.deleteMaster(id));
    }

    @BusinessLog(name = "Suppliers", value = "update Suppliers")
    @PutMapping("/{id}/status")
    @ApiOperation(value = "设为禁用/正常",response = Trader.class)
    public Tip forbiddenTrader(@PathVariable Long id) {
        return SuccessTip.create(traderService.changeTraderStatus(id));
    }

    @GetMapping
    @ApiOperation("分销商 list,u can searching 分销商 through by this api with some property what u want")
    public Tip queryTraders(Page<TraderRecord> page,
                            @RequestParam(name = "pageNum", required = false, defaultValue = "1") Integer pageNum,
                            @RequestParam(name = "pageSize", required = false, defaultValue = "10") Integer pageSize,
                            @RequestParam(name = "id", required = false) Long id,
                            @RequestParam(name = "traderName", required = false) String traderName,
                            @RequestParam(name = "traderCode", required = false) String traderCode,
                            @RequestParam(name = "traderPCD", required = false) String traderPCD,
                            @RequestParam(name = "traderAddress", required = false) String traderAddress,
                            @RequestParam(name = "traderPostcode", required = false) String traderPostcode,
                            @RequestParam(name = "traderContactName", required = false) String traderContactName,
                            @RequestParam(name = "traderContactPhone", required = false) String traderContactPhone,
                            @RequestParam(name = "traderContactFax", required = false) String traderContactFax,
                            @RequestParam(name = "traderContactEmail", required = false) String traderContactEmail,
                            @RequestParam(name = "traderContactPosition", required = false) String traderContactPosition,
                            @RequestParam(name = "traderContactCellPhone", required = false) String traderContactCellPhone,
                            @RequestParam(name = "traderAccountName", required = false) String traderAccountName,
                            @RequestParam(name = "traderAccountBank", required = false) String traderAccountBank,
                            @RequestParam(name = "traderAccountBankNo", required = false) Long traderAccountBankNo,
                            @RequestParam(name = "traderInvoiceTitle", required = false) String traderInvoiceTitle,
                            @RequestParam(name = "traderStatus", required = false) String traderStatus,
                            @RequestParam(name = "traderNote", required = false) String traderNote,
                            @RequestParam(name = "traderRegisterTime", required = false) Date traderRegisterTime,
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

        TraderRecord record = new TraderRecord();
        record.setId(id);
        record.setTraderName(traderName);
        record.setTraderCode(traderCode);
        record.setTraderPCD(traderPCD);
        record.setTraderAddress(traderAddress);
        record.setTraderPostcode(traderPostcode);
        record.setTraderContactName(traderContactName);
        record.setTraderContactPhone(traderContactPhone);
        record.setTraderContactFax(traderContactFax);
        record.setTraderContactEmail(traderContactEmail);
        record.setTraderContactPosition(traderContactPosition);
        record.setTraderContactCellPhone(traderContactCellPhone);
        record.setTraderAccountName(traderAccountName);
        record.setTraderAccountBank(traderAccountBank);
        record.setTraderAccountBankNo(traderAccountBankNo);
        record.setTraderInvoiceTitle(traderInvoiceTitle);
        record.setTraderStatus(traderStatus);
        record.setTraderNote(traderNote);
        record.setTraderRegisterTime(traderRegisterTime);
        record.setField1(field1);
        record.setField2(field2);

        page.setRecords(queryTraderDao.findTraderPage(page, record, orderBy));

        return SuccessTip.create(page);
    }

    @BusinessLog(name = "Trader", value = "delete trader")
    @PostMapping("/bulk/delete")
    public Tip deleteList(@RequestBody Ids ids) {
        return SuccessTip.create(traderService.bulkDelete(ids));
    }

}

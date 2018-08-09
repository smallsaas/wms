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
import com.jfeat.am.module.warehouse.services.domain.dao.QueryCheckDao;
import com.jfeat.am.common.constant.tips.SuccessTip;
import com.jfeat.am.common.constant.tips.Tip;
import com.jfeat.am.module.log.annotation.BusinessLog;
import com.jfeat.am.common.exception.BusinessCode;
import com.jfeat.am.common.exception.BusinessException;
import com.jfeat.am.module.warehouse.services.domain.service.CheckService;
import com.jfeat.am.module.warehouse.services.domain.model.CheckRecord;
import com.jfeat.am.module.warehouse.services.domain.model.CheckModel;

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
 * @since 2018-06-23
 */
@RestController
@Api("WMS-库存盘点")
@RequestMapping("/api/wms/checks")
public class CheckEndpoint extends BaseController {


    @Resource
    CheckService checkService;

    @Resource
    QueryCheckDao queryCheckDao;

    @BusinessLog(name = "Check", value = "create Check")
    @ApiOperation(value = "新建库存盘点", response = CheckModel.class)
    @PostMapping
    public Tip createCheck(@RequestBody CheckModel entity) {

        Integer affected = 0;
        try {
            Long userId = JWTKit.getUserId(getHttpServletRequest());
            affected = checkService.createCheckList(userId,entity);

        } catch (DuplicateKeyException e) {
            throw new BusinessException(BusinessCode.DuplicateKey);
        }

        return SuccessTip.create(affected);
    }

    @BusinessLog(name = "Check", value = "view Check")
    @GetMapping("/{id}")
    @ApiOperation(value = "查看库存盘点", response = CheckModel.class)
    public Tip getCheck(@PathVariable Long id) {
        return SuccessTip.create(checkService.checkDetails(id));
    }

    @BusinessLog(name = "Check", value = "update Check")
    @PutMapping("/{id}/checking")
    @ApiOperation(value = "执行盘点", response = CheckModel.class)
    public Tip updateCheck(@PathVariable Long id, @RequestBody CheckModel entity) {
        entity.setId(id);
        return SuccessTip.create(checkService.actionCheck(id,entity));
    }

    @BusinessLog(name = "Check", value = "update Check")
    @PutMapping("/{id}/done")
    @ApiOperation(value = "完成盘点", response = CheckModel.class)
    public Tip doneCheck(@PathVariable Long id, @RequestBody CheckModel entity) {
        entity.setId(id);
        return SuccessTip.create(checkService.checkedCheck(id,entity));
    }

    @BusinessLog(name = "Check", value = "delete Check")
    @DeleteMapping("/{id}")
    @ApiOperation(value = "删除库存盘点 only status WaitForCheck", response = CheckModel.class)
    public Tip deleteCheck(@PathVariable Long id) {
        return SuccessTip.create(checkService.deleteCheck(id));
    }

    @GetMapping
    @ApiOperation(value = "盘点库存盘点列表", response = CheckModel.class)

    public Tip queryChecks(Page<CheckRecord> page,
                           @RequestParam(name = "pageNum", required = false, defaultValue = "1") Integer pageNum,
                           @RequestParam(name = "pageSize", required = false, defaultValue = "10") Integer pageSize,
                           @RequestParam(name = "id", required = false) Long id,
                           @RequestParam(name = "checkCode", required = false) String checkCode,
                           @RequestParam(name = "checkTime", required = false) Date checkTime,
                           @RequestParam(name = "profitLost", required = false) Integer profitLost,
                           @RequestParam(name = "checkNote", required = false) String checkNote,
                           @RequestParam(name = "checkBy", required = false) Long checkBy,
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

        CheckRecord record = new CheckRecord();
        record.setId(id);
        record.setCheckCode(checkCode);
        record.setCheckTime(checkTime);
        record.setProfitLost(profitLost);
        record.setCheckNote(checkNote);
        record.setCheckBy(checkBy);
        record.setOriginatorId(originatorId);
        record.setField1(field1);
        record.setField2(field2);

        page.setRecords(queryCheckDao.findCheckPage(page, record, orderBy));

        return SuccessTip.create(page);
    }


}

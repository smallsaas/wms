package com.jfeat.am.module.warehouse.api.crud;

import com.alibaba.fastjson.JSON;
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
import com.jfeat.am.module.warehouse.services.definition.CheckStatus;
import com.jfeat.am.module.warehouse.services.definition.FormType;
import com.jfeat.am.module.warehouse.services.domain.dao.QueryCheckDao;
import com.jfeat.am.module.warehouse.services.domain.model.CheckModel;
import com.jfeat.am.module.warehouse.services.domain.model.CheckRecord;
import com.jfeat.am.module.warehouse.services.domain.service.CheckService;
import com.jfeat.am.module.warehouse.services.persistence.model.Check;
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

    @ApiOperation(value = "新建库存盘点", response = CheckModel.class)
    @PostMapping
    public Tip createCheck(@RequestBody CheckModel entity) {

        Integer affected = 0;
        try {
            String userName = JWTKit.getAccount(getHttpServletRequest());
            Long userId = JWTKit.getUserId(getHttpServletRequest());
            entity.setOriginatorName(userName);
            entity.setOriginatorId(userId);
            affected = checkService.createCheckList(userId,entity);

        } catch (DuplicateKeyException e) {
            throw new BusinessException(BusinessCode.DuplicateKey);
        }
        createCheckLog(entity.getId(), "createCheck", "对库存盘点进行了新建操作", JSON.toJSONString(entity) + " &");
        return SuccessTip.create(affected);
    }


    @PostMapping("/{id}/commit")
    @ApiOperation(value = "提交盘点")
    public Tip commit(@PathVariable Long id) {
        Integer affected = 0;
        Check check = new Check();
        check.setId(id);
        check.setStatus(CheckStatus.Wait_Audit.toString());
        if(check.getId() != null) {
            affected += checkService.updateMaster(check);
            createCheckLog(id, "commit", "对库存盘点进行了提交操作", id + " &");
        }
        return SuccessTip.create(affected);
    }

    @PostMapping("/{id}/reject")
    @ApiOperation(value = "库存盘点审核拒绝")
    public Tip reject(@PathVariable Long id) {
        Integer affected = 0;
        Check check = new Check();
        check.setId(id);
        check.setStatus(CheckStatus.Closed.toString());
        if(check.getId() != null) {
            affected += checkService.updateMaster(check);
            createCheckLog(id, "reject", "对库存盘点进行了审核拒绝操作", id + " &");
        }
        return SuccessTip.create(affected);
    }

    @PostMapping("/{id}/pass")
    @ApiOperation(value = "库存盘点审核通过")
    public Tip pass(@PathVariable Long id) {
        Integer affected = 0;
        Check check = new Check();
        check.setId(id);
        check.setStatus(CheckStatus.WaitForCheck.toString());
        if(check.getId() != null) {
            affected += checkService.updateMaster(check);
            createCheckLog(id, "pass", "对库存盘点进行了审核通过操作", id + " &");
        }
        return SuccessTip.create(affected);
    }


    @GetMapping("/{id}")
    @ApiOperation(value = "查看库存盘点", response = CheckModel.class)
    public Tip getCheck(@PathVariable Long id) {
        return SuccessTip.create(checkService.checkDetails(id));
    }

    @PutMapping("/{id}/checking")
    @ApiOperation(value = "update check", response = CheckModel.class)
    public Tip updateCheck(@PathVariable Long id, @RequestBody CheckModel entity) {
        entity.setId(id);
        Tip resultTip = SuccessTip.create(checkService.actionCheck(id,entity));
        createCheckLog(id, "updateCheck", "对库存盘点进行了盘点操作", JSONObject.toJSONString(entity) + " & " + id + " &");
        return resultTip;
    }


    @PostMapping("/{id}/done")
    @ApiOperation(value = "doneCheck", response = CheckModel.class)
    public Tip doneCheck(@PathVariable Long id) {
        Tip resultTip = SuccessTip.create(checkService.checkedCheck(id));

        createCheckLog(id,  "doneCheck", "对库存盘点进行了完成盘点操作", id + " &");
        return resultTip;
    }

    @DeleteMapping("/{id}")
    @ApiOperation(value = "删除库存盘点 only status WaitForCheck", response = CheckModel.class)
    public Tip deleteCheck(@PathVariable Long id) {
        Tip resultTip =SuccessTip.create(checkService.deleteCheck(id));

        createCheckLog(id,  "deleteCheck", "对库存盘点进行了删除操作", id + " &");
        return resultTip;
    }

    @GetMapping
    @ApiOperation(value = "盘点库存盘点列表", response = CheckModel.class)
    public Tip queryChecks(Page<CheckRecord> page,
                           @RequestParam(name = "pageNum", required = false, defaultValue = "1") Integer pageNum,
                           @RequestParam(name = "pageSize", required = false, defaultValue = "10") Integer pageSize,
                           @RequestParam(name = "id", required = false) Long id,
                           @RequestParam(name = "checkCode", required = false) String checkCode,
                           @RequestParam(name = "warehouseId", required = false) Long warehouseId,
                           @RequestParam(name = "checkTime", required = false) Date checkTime,
                           @RequestParam(name = "profitLost", required = false) Integer profitLost,
                           @RequestParam(name = "checkNote", required = false) String checkNote,
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

        CheckRecord record = new CheckRecord();
        record.setId(id);
        record.setCheckCode(checkCode);
        record.setCheckTime(checkTime);
        record.setProfitLost(profitLost);
        record.setCheckNote(checkNote);
        record.setTransactionBy(transactionBy);
        record.setOriginatorId(originatorId);
        record.setField1(field1);
        record.setField2(field2);

        page.setRecords(queryCheckDao.findCheckPage(page, warehouseId,record, orderBy));

        return SuccessTip.create(page);
    }



    private void createCheckLog(Long targetId, String methodName, String operation,String message) {
        LogManager.me().executeLog(LogTaskFactory.businessLog(JWTKit.getUserId(getHttpServletRequest()),
                JWTKit.getAccount(getHttpServletRequest()),
                operation,
                CheckEndpoint.class.getName(),
                methodName,
                message,
                "成功",
                targetId,
                FormType.CHECK.toString()
        ));
    }
}

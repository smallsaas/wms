package com.jfeat.am.module.sku.api.crud;

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
import com.jfeat.am.module.sku.services.domain.dao.QuerySkuConditionDao;
import com.jfeat.am.common.constant.tips.SuccessTip;
import com.jfeat.am.common.constant.tips.Ids;
import com.jfeat.am.common.constant.tips.Tip;
import com.jfeat.am.module.log.annotation.BusinessLog;
import com.jfeat.am.common.exception.BusinessCode;
import com.jfeat.am.common.exception.BusinessException;

import java.math.BigDecimal;

import com.jfeat.am.module.sku.services.domain.service.SkuConditionService;
import com.jfeat.am.module.sku.services.domain.model.SkuConditionRecord;
import com.jfeat.am.module.sku.services.crud.model.SkuConditionModel;
import com.jfeat.am.module.sku.services.persistence.model.SkuCondition;

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
 * @since 2018-07-18
 */
@RestController
@Api("wms-sku-状况")
@RequestMapping("/api/wms/sku/conditions")
public class SkuConditionEndpoint extends BaseController {


    @Resource
    SkuConditionService skuConditionService;

    @Resource
    QuerySkuConditionDao querySkuConditionDao;

    @BusinessLog(name = "SkuCondition", value = "create SkuCondition")
    @PostMapping
    @ApiOperation("新建状况")
    public Tip createSkuCondition(@RequestBody SkuConditionModel entity) {

        Integer affected = 0;
        try {
            affected = skuConditionService.createMaster(entity);

        } catch (DuplicateKeyException e) {
            throw new BusinessException(BusinessCode.DuplicateKey);
        }

        return SuccessTip.create(affected);
    }

    @GetMapping("/{id}")
    @ApiOperation("查看状况")
    public Tip getSkuCondition(@PathVariable Long id) {
        return SuccessTip.create(skuConditionService.retrieveMaster(id));
    }

    @BusinessLog(name = "SkuCondition", value = "update SkuCondition")
    @PutMapping("/{id}")
    @ApiOperation("修改状况")
    public Tip updateSkuCondition(@PathVariable Long id, @RequestBody SkuConditionModel entity) {
        entity.setId(id);
        return SuccessTip.create(skuConditionService.updateMaster(entity));
    }

    @BusinessLog(name = "SkuCondition", value = "delete SkuCondition")
    @DeleteMapping("/{id}")
    @ApiOperation("删除状况")
    public Tip deleteSkuCondition(@PathVariable Long id) {
        return SuccessTip.create(skuConditionService.deleteMaster(id));
    }

    @GetMapping
    @ApiOperation("所有状况")
    public Tip querySkuConditions(Page<SkuConditionRecord> page,
                                  @RequestParam(name = "pageNum", required = false, defaultValue = "1") Integer pageNum,
                                  @RequestParam(name = "pageSize", required = false, defaultValue = "10") Integer pageSize,
                                  @RequestParam(name = "id", required = false) Long id,
                                  @RequestParam(name = "conditionName", required = false) String conditionName,
                                  @RequestParam(name = "conditionDescription", required = false) String conditionDescription,
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

        SkuConditionRecord record = new SkuConditionRecord();
        record.setId(id);
        record.setConditionName(conditionName);
        record.setConditionDescription(conditionDescription);

        page.setRecords(querySkuConditionDao.findSkuConditionPage(page, record, orderBy));

        return SuccessTip.create(page);
    }


}

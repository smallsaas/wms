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
import com.jfeat.am.module.sku.services.domain.dao.QuerySkuUnitDao;
import com.jfeat.am.common.constant.tips.SuccessTip;
import com.jfeat.am.common.constant.tips.Ids;
import com.jfeat.am.common.constant.tips.Tip;
import com.jfeat.am.module.log.annotation.BusinessLog;
import com.jfeat.am.common.exception.BusinessCode;
import com.jfeat.am.common.exception.BusinessException;

import java.math.BigDecimal;

import com.jfeat.am.module.sku.services.domain.service.SkuUnitService;
import com.jfeat.am.module.sku.services.domain.model.SkuUnitRecord;
import com.jfeat.am.module.sku.services.crud.model.SkuUnitModel;
import com.jfeat.am.module.sku.services.persistence.model.SkuUnit;

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
@Api("sku-单位")
@RequestMapping("/api/wms/sku")
public class SkuUnitEndpoint extends BaseController {


    @Resource
    SkuUnitService skuUnitService;

    @Resource
    QuerySkuUnitDao querySkuUnitDao;

    @BusinessLog(name = "SkuUnit", value = "create SkuUnit")
    @PostMapping("/{skuId}/units")
    @ApiOperation("为 sku 新增 单位")
    public Tip createSkuUnit(@PathVariable long skuId,@RequestBody SkuUnitModel entity) {

        Integer affected = 0;
        entity.setSkuId(skuId);
        try {
            affected = skuUnitService.addSlaveItem(entity);

        } catch (DuplicateKeyException e) {
            throw new BusinessException(BusinessCode.DuplicateKey);
        }

        return SuccessTip.create(affected);
    }

    @Deprecated
    @GetMapping("/units/{id}")
    @ApiOperation("查看Sku 单位")
    public Tip getSkuUnit(@PathVariable Long id) {
        return SuccessTip.create(skuUnitService.getSlaveItem(id));
    }

    @BusinessLog(name = "SkuUnit", value = "update SkuUnit")
    @PutMapping("/units/{id}")
    @Deprecated
    @ApiOperation("修改")
    public Tip updateSkuUnit(@PathVariable Long id, @RequestBody SkuUnitModel entity) {
        entity.setId(id);
        return SuccessTip.create(skuUnitService.updateSlaveItem(entity));
    }

    @BusinessLog(name = "SkuUnit", value = "delete SkuUnit")
    @DeleteMapping("/units/{id}")
    @Deprecated
    @ApiOperation("删除 sku 单位")
    public Tip deleteSkuUnit(@PathVariable Long id) {
        return SuccessTip.create(skuUnitService.removeSlaveItem(id));
    }

    @GetMapping("/units")
    @Deprecated
    @ApiOperation("所有sku单位")
    public Tip querySkuUnits(Page<SkuUnitRecord> page,
                             @RequestParam(name = "pageNum", required = false, defaultValue = "1") Integer pageNum,
                             @RequestParam(name = "pageSize", required = false, defaultValue = "10") Integer pageSize,
                             @RequestParam(name = "id", required = false) Long id,
                             @RequestParam(name = "unitName", required = false) String unitName,
                             @RequestParam(name = "isPrimary", required = false) Integer isPrimary,
                             @RequestParam(name = "skuId", required = false) Long skuId,
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

        SkuUnitRecord record = new SkuUnitRecord();
        record.setId(id);
        record.setUnitName(unitName);
        record.setIsPrimary(isPrimary);
        record.setSkuId(skuId);

        page.setRecords(querySkuUnitDao.findSkuUnitPage(page, record, orderBy));

        return SuccessTip.create(page);
    }


}

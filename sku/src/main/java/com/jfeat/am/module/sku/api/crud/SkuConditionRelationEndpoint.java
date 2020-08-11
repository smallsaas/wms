package com.jfeat.am.module.sku.api.crud;

import com.jfeat.crud.base.exception.BusinessCode;
import com.jfeat.crud.base.exception.BusinessException;
import com.jfeat.crud.base.tips.SuccessTip;
import com.jfeat.crud.base.tips.Tip;
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
import com.jfeat.am.module.sku.services.domain.dao.QuerySkuConditionRelationDao;
import com.jfeat.am.module.log.annotation.BusinessLog;

import java.math.BigDecimal;

import com.jfeat.am.module.sku.services.domain.service.SkuConditionRelationService;
import com.jfeat.am.module.sku.services.domain.model.SkuConditionRelationRecord;
import com.jfeat.am.module.sku.services.crud.model.SkuConditionRelationModel;
import com.jfeat.am.module.sku.services.persistence.model.SkuConditionRelation;

import org.springframework.web.bind.annotation.RestController;

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
@Deprecated
@RequestMapping("/api/sku/conditions/relations")
public class SkuConditionRelationEndpoint   {


    @Resource
    SkuConditionRelationService skuConditionRelationService;

    @Resource
    QuerySkuConditionRelationDao querySkuConditionRelationDao;

    @BusinessLog(name = "SkuConditionRelation", value = "create SkuConditionRelation")
    @PostMapping
    public Tip createSkuConditionRelation(@RequestBody SkuConditionRelationModel entity) {

        Integer affected = 0;
        try {
            affected = skuConditionRelationService.createMaster(entity);

        } catch (DuplicateKeyException e) {
            throw new BusinessException(BusinessCode.DuplicateKey);
        }

        return SuccessTip.create(affected);
    }

    @GetMapping("/{id}")
    public Tip getSkuConditionRelation(@PathVariable Long id) {
        return SuccessTip.create(skuConditionRelationService.retrieveMaster(id));
    }

    @BusinessLog(name = "SkuConditionRelation", value = "update SkuConditionRelation")
    @PutMapping("/{id}")
    public Tip updateSkuConditionRelation(@PathVariable Long id, @RequestBody SkuConditionRelationModel entity) {
        entity.setId(id);
        return SuccessTip.create(skuConditionRelationService.updateMaster(entity));
    }

    @BusinessLog(name = "SkuConditionRelation", value = "delete SkuConditionRelation")
    @DeleteMapping("/{id}")
    public Tip deleteSkuConditionRelation(@PathVariable Long id) {
        return SuccessTip.create(skuConditionRelationService.deleteMaster(id));
    }

    @GetMapping
    public Tip querySkuConditionRelations(Page<SkuConditionRelationRecord> page,
                                          @RequestParam(name = "pageNum", required = false, defaultValue = "1") Integer pageNum,
                                          @RequestParam(name = "pageSize", required = false, defaultValue = "10") Integer pageSize,
                                          @RequestParam(name = "id", required = false) Long id,
                                          @RequestParam(name = "skuId", required = false) Long skuId,
                                          @RequestParam(name = "conditionId", required = false) Long conditionId,
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

        SkuConditionRelationRecord record = new SkuConditionRelationRecord();
        record.setId(id);
        record.setSkuId(skuId);
        record.setConditionId(conditionId);

        page.setRecords(querySkuConditionRelationDao.findSkuConditionRelationPage(page, record, orderBy));

        return SuccessTip.create(page);
    }


}

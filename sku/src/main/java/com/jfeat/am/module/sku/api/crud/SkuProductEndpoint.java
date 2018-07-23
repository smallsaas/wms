package com.jfeat.am.module.sku.api.crud;

import com.jfeat.am.module.sku.services.domain.model.CreateSkuProductModel;
import io.swagger.annotations.Api;
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
import com.jfeat.am.module.sku.services.domain.dao.QuerySkuProductDao;
import com.jfeat.am.common.constant.tips.SuccessTip;
import com.jfeat.am.common.constant.tips.Ids;
import com.jfeat.am.common.constant.tips.Tip;
import com.jfeat.am.module.log.annotation.BusinessLog;
import com.jfeat.am.common.exception.BusinessCode;
import com.jfeat.am.common.exception.BusinessException;

import java.math.BigDecimal;

import com.jfeat.am.module.sku.services.domain.service.SkuProductService;
import com.jfeat.am.module.sku.services.domain.model.SkuProductRecord;
import com.jfeat.am.module.sku.services.crud.model.SkuProductModel;
import com.jfeat.am.module.sku.services.persistence.model.SkuProduct;

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
@Api("sku-Sku管理")
@RequestMapping("/api/sku/skus")
public class SkuProductEndpoint extends BaseController {


    @Resource
    SkuProductService skuProductService;

    @Resource
    QuerySkuProductDao querySkuProductDao;

    @BusinessLog(name = "SkuProduct", value = "create SkuProduct")
    @PostMapping
    public Tip createSkuProduct(@RequestBody CreateSkuProductModel model) {

        Integer affected = 0;
        try {
            affected = skuProductService.createSku(model);

        } catch (DuplicateKeyException e) {
            throw new BusinessException(BusinessCode.DuplicateKey);
        }

        return SuccessTip.create(affected);
    }

    @GetMapping("/{id}")
    public Tip getSkuProduct(@PathVariable Long id) {
        return SuccessTip.create(skuProductService.retrieveMaster(id, null, null, null));
    }

    @BusinessLog(name = "SkuProduct", value = "update SkuProduct")
    @PutMapping("/{id}")
    public Tip updateSkuProduct(@PathVariable Long id, @RequestBody CreateSkuProductModel entity) {
        entity.getSkus().get(0).setId(id);
        return SuccessTip.create(skuProductService.updateSku(id,entity));
    }

    @BusinessLog(name = "SkuProduct", value = "delete SkuProduct")
    @DeleteMapping("/{id}")
    public Tip deleteSkuProduct(@PathVariable Long id) {
        return SuccessTip.create(skuProductService.deleteSku(id));
    }

    @GetMapping
    public Tip querySkuProducts(Page<SkuProductRecord> page,
                                @RequestParam(name = "pageNum", required = false, defaultValue = "1") Integer pageNum,
                                @RequestParam(name = "pageSize", required = false, defaultValue = "10") Integer pageSize,
                                @RequestParam(name = "id", required = false) Long id,
                                @RequestParam(name = "productId", required = false) Long productId,
                                @RequestParam(name = "skuCode", required = false) String skuCode,
                                @RequestParam(name = "skuName", required = false) String skuName,
                                @RequestParam(name = "status", required = false) String status,
                                @RequestParam(name = "sortValue", required = false) Integer sortValue,
                                @RequestParam(name = "searchKeyWord", required = false) String searchKeyWord,
                                @RequestParam(name = "barCode", required = false) String barCode,
                                @RequestParam(name = "description", required = false) String description,
                                @RequestParam(name = "skuPrice", required = false) BigDecimal skuPrice,
                                @RequestParam(name = "readjustCostPrice", required = false) BigDecimal readjustCostPrice,
                                @RequestParam(name = "suggestedPrice", required = false) BigDecimal suggestedPrice,
                                @RequestParam(name = "costPrice", required = false) BigDecimal costPrice,
                                @RequestParam(name = "stockCost", required = false) BigDecimal stockCost,
                                @RequestParam(name = "createTime", required = false) Date createTime,
                                @RequestParam(name = "updateTime", required = false) Date updateTime,
                                @RequestParam(name = "field1", required = false) String field1,
                                @RequestParam(name = "field2", required = false) String field2,
                                @RequestParam(name = "field3", required = false) String field3,
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

        SkuProductRecord record = new SkuProductRecord();
        record.setId(id);
        record.setProductId(productId);
        record.setSkuCode(skuCode);
        record.setSkuName(skuName);
        record.setStatus(status);
        record.setSortValue(sortValue);
        record.setSearchKeyWord(searchKeyWord);
        record.setBarCode(barCode);
        record.setDescription(description);
        record.setSkuPrice(skuPrice);
        record.setReadjustCostPrice(readjustCostPrice);
        record.setSuggestedPrice(suggestedPrice);
        record.setCostPrice(costPrice);
        record.setStockCost(stockCost);
        record.setCreateTime(createTime);
        record.setUpdateTime(updateTime);
        record.setField1(field1);
        record.setField2(field2);
        record.setField3(field3);

        page.setRecords(querySkuProductDao.findSkuProductPage(page, record, orderBy));

        return SuccessTip.create(page);
    }


}

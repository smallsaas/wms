package com.jfeat.am.module.sku.api.path;


import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.jfeat.am.module.product.services.domain.dao.QueryProductDao;
import com.jfeat.am.module.product.services.domain.model.ProductRecord;
import com.jfeat.am.module.sku.services.domain.service.SkuProductService;
import com.jfeat.crud.base.exception.BusinessCode;
import com.jfeat.crud.base.exception.BusinessException;
import com.jfeat.crud.base.tips.SuccessTip;
import com.jfeat.crud.base.tips.Tip;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import java.math.BigDecimal;
import java.util.Date;

@Api("sku-Sku管理")
@RequestMapping("/api/wms")
@RestController
public class SkuProductPathEndpoint   {

    @Resource
    QueryProductDao queryProductDao;
    @Resource
    SkuProductService skuProductService;



    @GetMapping("/products/{id}")
    @ApiOperation("产品-包括属于该产品的所有SKU以及该SKU的规格图片等")
    public Tip productsTotalDetails(@PathVariable Long id){

        return SuccessTip.create(skuProductService.skuDetails(id));
    }

    @DeleteMapping("/products/{id}")
    @ApiOperation("产品-包括属于该产品的所有SKU以及该SKU的规格图片等")
    public Tip deleteProductIncludingSku(@PathVariable Long id){

        return SuccessTip.create(skuProductService.deleteProduct(id));
    }



    @GetMapping("/products")
    @ApiOperation("产品列表")
    public Tip queryProducts(Page<ProductRecord> page,
                             @RequestParam(name = "pageNum", required = false, defaultValue = "1") Integer pageNum,
                             @RequestParam(name = "pageSize", required = false, defaultValue = "10") Integer pageSize,
                             @RequestParam(name = "id", required = false) Long id,
                             @RequestParam(name = "productCategoryId", required = false) Long productCategoryId,
                             @RequestParam(name = "productCode", required = false) String productCode,
                             @RequestParam(name = "name", required = false) String name,
                             @RequestParam(name = "englishName", required = false) String englishName,
                             @RequestParam(name = "weight", required = false) String weight,
                             @RequestParam(name = "readjustCostPrice", required = false) BigDecimal readjustCostPrice,
                             @RequestParam(name = "productStandard", required = false) String productStandard,
                             @RequestParam(name = "price", required = false) BigDecimal price,
                             @RequestParam(name = "suggestedPrice", required = false) BigDecimal suggestedPrice,
                             @RequestParam(name = "costPrice", required = false) BigDecimal costPrice,
                             @RequestParam(name = "specification", required = false) String specification,
                             @RequestParam(name = "quantities", required = false) Integer quantities,
                             @RequestParam(name = "stockCost", required = false) BigDecimal stockCost,
                             @RequestParam(name = "purchaseAdvance", required = false) Integer purchaseAdvance,
                             @RequestParam(name = "status", required = false) String status,
                             @RequestParam(name = "sortValue", required = false) Integer sortValue,
                             @RequestParam(name = "searchKeyWord", required = false) String searchKeyWord,
                             @RequestParam(name = "barCode", required = false) String barCode,
                             @RequestParam(name = "createTime", required = false) Date createTime,
                             @RequestParam(name = "updateTime", required = false) Date updateTime,
                             @RequestParam(name = "description", required = false) String description,
                             @RequestParam(name = "field1", required = false) String field1,
                             @RequestParam(name = "field2", required = false) String field2,
                             @RequestParam(name = "field3", required = false) String field3,
                             @RequestParam(name = "field4", required = false) String field4,
                             @RequestParam(name = "field5", required = false) String field5,
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

        ProductRecord record = new ProductRecord();
        record.setId(id);
        record.setProductCategoryId(productCategoryId);
        record.setProductCode(productCode);
        record.setName(name);
        record.setEnglishName(englishName);
        record.setWeight(weight);
        record.setReadjustCostPrice(readjustCostPrice);
        record.setProductStandard(productStandard);
        record.setPrice(price);
        record.setSuggestedPrice(suggestedPrice);
        record.setCostPrice(costPrice);
        record.setSpecification(specification);
        record.setQuantities(quantities);
        record.setStockCost(stockCost);
        record.setPurchaseAdvance(purchaseAdvance);
        record.setStatus(status);
        record.setSortValue(sortValue);
        record.setSearchKeyWord(searchKeyWord);
        record.setBarCode(barCode);
        record.setCreateTime(createTime);
        record.setUpdateTime(updateTime);
        record.setDescription(description);
        record.setField1(field1);
        record.setField4(field4);
        record.setField5(field5);

        page.setRecords(queryProductDao.findProductPage(page, record, orderBy));

        return SuccessTip.create(page);
    }
}

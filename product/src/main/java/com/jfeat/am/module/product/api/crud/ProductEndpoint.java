package com.jfeat.am.module.product.api.crud;

import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.jfeat.am.module.product.services.domain.model.ProductModel;
import com.jfeat.am.module.product.services.domain.service.ProductCategoryService;
import com.jfeat.crud.base.annotation.BusinessLog;
import com.jfeat.crud.base.exception.BusinessCode;
import com.jfeat.crud.base.exception.BusinessException;
import com.jfeat.crud.base.tips.SuccessTip;
import com.jfeat.crud.base.tips.Tip;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.dao.DuplicateKeyException;
import com.jfeat.am.module.product.services.domain.dao.QueryProductDao;
import java.math.BigDecimal;
import com.jfeat.am.module.product.services.domain.service.ProductService;
import com.jfeat.am.module.product.services.domain.model.ProductRecord;
import org.springframework.web.bind.annotation.RestController;
import javax.annotation.Resource;
import java.util.Date;


/**
 * <p>
 * api
 * </p>
 *
 * @author Code Generator
 * @since 2018-06-19
 */
@RestController
@Api("WMS-产品-产品")
@RequestMapping("/api/product/products")
public class ProductEndpoint   {


    @Resource
    ProductService productService;
    @Resource
    ProductCategoryService productCategoryService;

    @Resource
    QueryProductDao queryProductDao;

    @BusinessLog(name = "Product", value = "create Product")
    @PostMapping
    @ApiOperation("新建产品")
    public Tip createProduct(@RequestBody ProductModel entity) {

        Integer affected = 0;
        try {
            affected = productService.createMaster(entity,null,null,null);

        } catch (DuplicateKeyException e) {
            throw new BusinessException(BusinessCode.DuplicateKey);
        }

        return SuccessTip.create(affected);
    }

    @GetMapping("/{id}")
    @ApiOperation("获取某个产品所有的属性")
    public Tip getProduct(@PathVariable Long id) {
        return SuccessTip.create(productService.productDetails(id));
    }

    @BusinessLog(name = "Product", value = "update Product")
    @PutMapping("/{id}")
    @ApiOperation("修改某个产品")
    public Tip updateProduct(@PathVariable Long id, @RequestBody ProductModel entity) {
        entity.setId(id);
        entity.setUpdateTime(new Date());
        return SuccessTip.create(productService.updateMaster(entity, null, null, null));
    }

    @BusinessLog(name = "Product", value = "delete Product")
    @DeleteMapping("/{id}")
    @ApiOperation("删除某个产品")
    public Tip deleteProduct(@PathVariable Long id) {
        return SuccessTip.create(productService.deleteMaster(id,null));
    }

    @GetMapping
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
                             @RequestParam(name = "volume", required = false) String volume,
                             @RequestParam(name = "spec", required = false) String spec,
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
        record.setVolume(volume);
        record.setSpec(spec);
        record.setField4(field4);
        record.setField5(field5);

        page.setRecords(queryProductDao.findProductPage(page, record, orderBy));

        return SuccessTip.create(page);
    }


}

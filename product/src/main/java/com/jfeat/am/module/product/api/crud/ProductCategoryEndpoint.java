package com.jfeat.am.module.product.api.crud;

import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.jfeat.am.module.product.services.domain.model.ProductCategoryModel;
import com.jfeat.crud.base.annotation.BusinessLog;
import com.jfeat.crud.base.exception.BusinessCode;
import com.jfeat.crud.base.exception.BusinessException;
import com.jfeat.crud.base.tips.SuccessTip;
import com.jfeat.crud.base.tips.Tip;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.web.bind.annotation.*;
import org.springframework.dao.DuplicateKeyException;

import com.jfeat.am.module.product.services.domain.service.ProductCategoryService;
import com.jfeat.am.module.product.services.persistence.model.ProductCategory;


import javax.annotation.Resource;


/**
 * <p>
 * api
 * </p>
 *
 * @author Code Generator
 * @since 2018-06-19
 */
@RestController
@Api("WMS-商品类别")
@RequestMapping("/api/product/categories")
public class ProductCategoryEndpoint   {


    @Resource
    ProductCategoryService productCategoryService;

    @BusinessLog(name = "ProductCategory", value = "create ProductCategory")
    @PostMapping
    @ApiOperation("新建商品类别-新建根目录")
    public Tip createProductCategory(@RequestBody ProductCategory entity) {

        Integer affected = 0;
        try {
            affected = productCategoryService.createMaster(entity);

        } catch (DuplicateKeyException e) {
            throw new BusinessException(BusinessCode.DuplicateKey);
        }

        return SuccessTip.create(affected);
    }

    @BusinessLog(name = "ProductCategory", value = "create ProductCategory")
    @PostMapping("/{pid}/children")
    @ApiOperation("新建商品类别-新建多个子节点")
    public Tip createProductCategories(@PathVariable Long pid, @RequestBody ProductCategoryModel entity) {

        return SuccessTip.create(productCategoryService.createChildren(pid, entity));
    }


    @ApiOperation("查询类别数据")
    @GetMapping()
    public Tip findAll(Page<ProductCategory> page,
                       @RequestParam(name = "pageNum", required = false, defaultValue = "1") Integer pageNum,
                       @RequestParam(name = "pageSize", required = false, defaultValue = "10") Integer pageSize,
                       @RequestParam(required = false, defaultValue = "false") Boolean grouping,
                       @RequestParam(name = "categoryName",required = false)String categoryName) {
        if (grouping) {
            return SuccessTip.create(productCategoryService.findAllGrouping());
        }
        if (categoryName!=null && categoryName.length()>0){
            page.setCurrent(pageNum);
            page.setSize(pageSize);
            page.setRecords(productCategoryService.queryByName(page,categoryName).getRecords());
            return SuccessTip.create(page);
        }

        page.setCurrent(pageNum);
        page.setSize(pageSize);
        page.setRecords(productCategoryService.categories(page).getRecords());
        return SuccessTip.create(page);
    }

    @GetMapping("/{id}")
    @ApiOperation("查看产品类别")
    public Tip getProductCategory(@PathVariable Long id) {
        return SuccessTip.create(productCategoryService.retrieveMaster(id));
    }

    @BusinessLog(name = "ProductCategory", value = "update ProductCategory")
    @PutMapping("/{id}")
    @ApiOperation("修改产品类别")
    public Tip updateProductCategory(@PathVariable Long id, @RequestBody ProductCategory entity) {
        entity.setId(id);
        return SuccessTip.create(productCategoryService.updateMaster(entity));
    }



}

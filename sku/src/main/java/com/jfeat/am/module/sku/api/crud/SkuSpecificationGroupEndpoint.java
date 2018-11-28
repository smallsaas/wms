package com.jfeat.am.module.sku.api.crud;

import com.jfeat.am.common.constant.tips.Ids;
import com.jfeat.am.common.constant.tips.SuccessTip;
import com.jfeat.am.common.constant.tips.Tip;
import com.jfeat.am.common.controller.BaseController;
import com.jfeat.am.common.exception.BusinessCode;
import com.jfeat.am.common.exception.BusinessException;
import com.jfeat.am.module.log.annotation.BusinessLog;
import com.jfeat.am.module.product.services.domain.service.ProductCategoryService;
import com.jfeat.am.module.sku.services.domain.dao.QuerySkuSpecificationGroupDao;
import com.jfeat.am.module.sku.services.domain.model.CategorySpecModel;
import com.jfeat.am.module.sku.services.domain.service.SkuSpecificationGroupService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.dao.DuplicateKeyException;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;


/**
 * <p>
 * api
 * </p>
 *
 * @author Code Generator
 * @since 2018-07-18
 */
@RestController
@Api("sku-规格")
@RequestMapping("/api/wms/sku")
public class SkuSpecificationGroupEndpoint extends BaseController {


    @Resource
    SkuSpecificationGroupService skuSpecificationGroupService;
    @Resource
    ProductCategoryService productCategoryService;

    @Resource
    QuerySkuSpecificationGroupDao querySkuSpecificationGroupDao;

    @BusinessLog(name = "SkuSpecificationGroup", value = "create SkuSpecificationGroup")
    @ApiOperation("创建类别及规格")
    @PostMapping("/specifications")
    public Tip createSpecificationGroup(@RequestBody CategorySpecModel entity) {

        Integer affected = 0;
        try {
            affected = skuSpecificationGroupService.specChildren(entity);

        } catch (DuplicateKeyException e) {
            throw new BusinessException(BusinessCode.DuplicateKey);
        }

        return SuccessTip.create(affected);
    }


    @GetMapping("/specifications")
    @ApiOperation("获取所有的规格下及该规格下的所有子规格")
    public Tip allSpecificationGroup() {
        return SuccessTip.create(skuSpecificationGroupService.allSpec());
    }


    @GetMapping("/category/{categoryId}/specifications")
    @ApiOperation("获取某个类别下的所有规格(including 子规格)")
    public Tip getSpecificationGroup(@PathVariable Long categoryId) {
        return SuccessTip.create(skuSpecificationGroupService.getSpecChildren(categoryId));
    }

    @BusinessLog(name = "SkuSpecificationGroup", value = "update SkuSpecificationGroup")
    @PutMapping("/specifications/{id}")
    @ApiOperation("修改类别及对应规格")
    public Tip updateSpecificationGroup(@PathVariable Long id, @RequestBody CategorySpecModel entity) {
        entity.setId(id);
        return SuccessTip.create(skuSpecificationGroupService.updateSpecChildren(id,entity));
    }

    @BusinessLog(name = "SkuSpecificationGroup", value = "delete SkuSpecificationGroup")
    @DeleteMapping("/specifications/{id}")
    @ApiOperation("删除某个规格(可以是父级规格也可以是子级规格)")
    public Tip deleteSpecificationGroup(@PathVariable Long id) {
        return SuccessTip.create(skuSpecificationGroupService.deleteMaster(id));
    }

    @BusinessLog(name = "ProductCategory", value = "delete ProductCategory")
    @DeleteMapping("/category/{id}")
    @ApiOperation("删除产品类别以及 类别下所有的规格信息")
    public Tip deleteProductCategory(@PathVariable Long id) {
        skuSpecificationGroupService.deleteCategory(id);
        productCategoryService.deleteMaster(id);
        return SuccessTip.create();
    }

    @BusinessLog(name = "ProductCategory", value = "delete Specification Group")
    @PostMapping("/bulk/delete")
    public Tip deleteList(@RequestBody Ids ids) {
        return SuccessTip.create(skuSpecificationGroupService.bulkDelete(ids));
    }



}

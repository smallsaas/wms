package com.jfeat.am.module.sku.api.crud;

import com.jfeat.am.module.sku.services.crud.model.SkuSpecificationGroupModel;
import com.jfeat.am.module.sku.services.domain.model.CategorySpecModel;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.dao.DuplicateKeyException;
import com.jfeat.am.module.sku.services.domain.dao.QuerySkuSpecificationGroupDao;
import com.jfeat.am.common.constant.tips.SuccessTip;
import com.jfeat.am.common.constant.tips.Ids;
import com.jfeat.am.common.constant.tips.Tip;
import com.jfeat.am.module.log.annotation.BusinessLog;
import com.jfeat.am.common.exception.BusinessCode;
import com.jfeat.am.common.exception.BusinessException;
import com.jfeat.am.module.sku.services.domain.service.SkuSpecificationGroupService;

import org.springframework.web.bind.annotation.RestController;
import com.jfeat.am.common.controller.BaseController;

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
@RequestMapping("/api/sku/specifications")
public class SkuSpecificationGroupEndpoint extends BaseController {


    @Resource
    SkuSpecificationGroupService skuSpecificationGroupService;

    @Resource
    QuerySkuSpecificationGroupDao querySkuSpecificationGroupDao;

    @BusinessLog(name = "SkuSpecificationGroup", value = "create SkuSpecificationGroup")
    @ApiOperation("创建某个类别下的规格属性")
    @PostMapping
    public Tip createSpecificationGroup(@RequestBody CategorySpecModel entity) {

        Integer affected = 0;
        try {
            affected = skuSpecificationGroupService.specChildren(entity);

        } catch (DuplicateKeyException e) {
            throw new BusinessException(BusinessCode.DuplicateKey);
        }

        return SuccessTip.create(affected);
    }

    @GetMapping("/{id}")
    @ApiOperation("获取某个规格下的所有子规格")
    public Tip getSpecificationGroup(@PathVariable Long id) {
        return SuccessTip.create(skuSpecificationGroupService.getSpecChildren(id));
    }

    @BusinessLog(name = "SkuSpecificationGroup", value = "update SkuSpecificationGroup")
    @PutMapping("/{id}")
    @ApiOperation("修改某个类别下的规格属性及本身")
    public Tip updateSpecificationGroup(@PathVariable Long id, @RequestBody SkuSpecificationGroupModel entity) {
        entity.setId(id);
        return SuccessTip.create(skuSpecificationGroupService.updateSpecChildren(id,entity));
    }

    @BusinessLog(name = "SkuSpecificationGroup", value = "delete SkuSpecificationGroup")
    @DeleteMapping("/{id}")
    @ApiOperation("删除某个父节点或子节点")
    public Tip deleteSpecificationGroup(@PathVariable Long id) {
        return SuccessTip.create(skuSpecificationGroupService.deleteMaster(id));
    }



    @PostMapping("/bulk/delete")
    public Tip deleteList(@RequestBody Ids ids) {
        return SuccessTip.create(skuSpecificationGroupService.bulkDelete(ids));
    }

}

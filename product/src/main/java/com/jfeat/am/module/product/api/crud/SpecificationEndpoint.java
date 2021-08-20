package com.jfeat.am.module.product.api.crud;

import com.jfeat.crud.base.tips.SuccessTip;
import com.jfeat.crud.base.tips.Tip;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.PathVariable;
import com.jfeat.am.module.product.services.domain.service.SpecificationService;
import org.springframework.web.bind.annotation.RestController;
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
@Api("WMS-产品-规格属组")
@RequestMapping("/api/product/specifications")
public class SpecificationEndpoint   {

    @Resource
    SpecificationService specificationService;

    @GetMapping("/{id}")
    @ApiOperation("查看组")
    public Tip getSpecification(@PathVariable Long id) {
        return SuccessTip.create(specificationService.getGroupItems(id));
    }

    @GetMapping("/groupdata")
    @ApiOperation("组数据")
    public Tip groupData() {
        return SuccessTip.create(specificationService.groupBy());
    }

}

package com.jfeat.am.module.product.api.crud;

import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
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
import com.jfeat.am.module.product.services.domain.dao.QuerySpecificationGroupDao;
import com.jfeat.am.module.log.annotation.BusinessLog;
import com.jfeat.am.module.product.services.domain.service.SpecificationGroupService;
import com.jfeat.am.module.product.services.domain.model.SpecificationGroupRecord;
import com.jfeat.am.module.product.services.persistence.model.SpecificationGroup;
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
@Api("WMS-规格组")
@RequestMapping("/api/product/specificationGroups")
public class SpecificationGroupEndpoint   {


    @Resource
    SpecificationGroupService specificationGroupService;

    @Resource
    QuerySpecificationGroupDao querySpecificationGroupDao;

    @BusinessLog(name = "SpecificationGroup", value = "create SpecificationGroup")
    @PostMapping
    @ApiOperation("新加组")
    public Tip createSpecificationGroup(@RequestBody SpecificationGroup entity) {

        Integer affected = 0;
        try {
            affected = specificationGroupService.createGroup(entity);

        } catch (DuplicateKeyException e) {
            throw new BusinessException(BusinessCode.DuplicateKey);
        }

        return SuccessTip.create(affected);
    }

    @GetMapping("/{id}")
    @ApiOperation("获取组信息")
    public Tip getSpecificationGroup(@PathVariable Long id) {
        return SuccessTip.create(specificationGroupService.getGroupChildren(id));
    }

    @BusinessLog(name = "SpecificationGroup", value = "update SpecificationGroup")
    @PutMapping("/{id}")
    @ApiOperation("修改组信息")
    public Tip updateSpecificationGroup(@PathVariable Long id, @RequestBody SpecificationGroup entity) {
        entity.setId(id);
        return SuccessTip.create(specificationGroupService.updateGroup(entity));
    }

    @BusinessLog(name = "SpecificationGroup", value = "delete SpecificationGroup")
        @DeleteMapping("/{id}")
        @ApiOperation("删除组信息")
        public Tip deleteSpecificationGroup(@PathVariable Long id) {
            return SuccessTip.create(specificationGroupService.deleteGroup(id));
        }

        @GetMapping
        @ApiOperation("组列表")
        public Tip querySpecificationGroups(Page<SpecificationGroupRecord> page,
                                            @RequestParam(name = "pageNum", required = false, defaultValue = "1") Integer pageNum,
                                            @RequestParam(name = "pageSize", required = false, defaultValue = "10") Integer pageSize,
                                            @RequestParam(name = "id", required = false) Long id,
                                            @RequestParam(name = "groupName", required = false) String groupName,
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

        SpecificationGroupRecord record = new SpecificationGroupRecord();
        record.setId(id);
        record.setGroupName(groupName);

        page.setRecords(querySpecificationGroupDao.findSpecificationGroupPage(page, record, orderBy));

        return SuccessTip.create(page);
    }


}

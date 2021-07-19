package com.jfeat.am.module.sku.api.crud;

import com.jfeat.crud.base.exception.BusinessCode;
import com.jfeat.crud.base.exception.BusinessException;
import com.jfeat.crud.base.tips.SuccessTip;
import com.jfeat.crud.base.tips.Tip;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.web.bind.annotation.GetMapping;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.dao.DuplicateKeyException;
import com.jfeat.am.module.sku.services.domain.dao.QuerySkuPhotoDao;
import com.jfeat.am.module.log.annotation.BusinessLog;

import java.math.BigDecimal;

import com.jfeat.am.module.sku.services.domain.service.SkuPhotoService;
import com.jfeat.am.module.sku.services.domain.model.SkuPhotoRecord;
import com.jfeat.am.module.sku.services.crud.model.SkuPhotoModel;
import com.jfeat.am.module.sku.services.persistence.model.SkuPhoto;

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
@Api("sku-图片")
@RequestMapping("/api/wms/sku")
public class SkuPhotoEndpoint   {


    @Resource
    SkuPhotoService skuPhotoService;

    @Resource
    QuerySkuPhotoDao querySkuPhotoDao;

    @BusinessLog(name = "SkuPhoto", value = "create SkuPhoto")
    @PostMapping("/{skuId}/photos")
    @ApiOperation("新增 sku 图片 ")
    public Tip createSkuPhoto(@PathVariable long skuId, @RequestBody SkuPhotoModel entity) {

        Integer affected = 0;
        entity.setSkuId(skuId);
        try {
            affected = skuPhotoService.addSlaveItem(entity);

        } catch (DuplicateKeyException e) {
            throw new BusinessException(BusinessCode.DuplicateKey);
        }

        return SuccessTip.create(affected);
    }

    @Deprecated
    @GetMapping("/photos/{id}")
    @ApiOperation("查看 sku 图片 ")
    public Tip getSkuPhoto(@PathVariable Long id) {
        return SuccessTip.create(skuPhotoService.getSlaveItem(id));
    }

    @Deprecated
    @BusinessLog(name = "SkuPhoto", value = "update SkuPhoto")
    @PutMapping("/photos/{id}")
    @ApiOperation("修改 sku 图片 ")
    public Tip updateSkuPhoto(@PathVariable Long id, @RequestBody SkuPhotoModel entity) {
        entity.setId(id);
        return SuccessTip.create(skuPhotoService.updateSlaveItem(entity));
    }


    @Deprecated
    @BusinessLog(name = "SkuPhoto", value = "delete SkuPhoto")
    @DeleteMapping("/photos/{id}")
    @ApiOperation("删除 sku 图片 ")
    public Tip deleteSkuPhoto(@PathVariable Long id) {
        return SuccessTip.create(skuPhotoService.removeSlaveItem(id));
    }


    @Deprecated
    @GetMapping("/photos")
    public Tip querySkuPhotos(Page<SkuPhotoRecord> page,
                              @RequestParam(name = "pageNum", required = false, defaultValue = "1") Integer pageNum,
                              @RequestParam(name = "pageSize", required = false, defaultValue = "10") Integer pageSize,
                              @RequestParam(name = "id", required = false) Long id,
                              @RequestParam(name = "photoUrl", required = false) String photoUrl,
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

        SkuPhotoRecord record = new SkuPhotoRecord();
        record.setId(id);
        record.setPhotoUrl(photoUrl);
        record.setIsPrimary(isPrimary);
        record.setSkuId(skuId);

        page.setRecords(querySkuPhotoDao.findSkuPhotoPage(page, record, orderBy));

        return SuccessTip.create(page);
    }


}

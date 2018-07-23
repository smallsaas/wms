package com.jfeat.am.module.sku.api.crud;

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
import com.jfeat.am.module.sku.services.domain.dao.QuerySkuTagRelationDao;
import com.jfeat.am.common.constant.tips.SuccessTip;
import com.jfeat.am.common.constant.tips.Ids;
import com.jfeat.am.common.constant.tips.Tip;
import com.jfeat.am.module.log.annotation.BusinessLog;
import com.jfeat.am.common.exception.BusinessCode;
import com.jfeat.am.common.exception.BusinessException;
import java.math.BigDecimal;
import com.jfeat.am.module.sku.services.domain.service.SkuTagRelationService;
import com.jfeat.am.module.sku.services.domain.model.SkuTagRelationRecord;
import com.jfeat.am.module.sku.services.crud.model.SkuTagRelationModel;
import com.jfeat.am.module.sku.services.persistence.model.SkuTagRelation;

import org.springframework.web.bind.annotation.RestController;
import com.jfeat.am.common.controller.BaseController;

import javax.annotation.Resource;
import java.util.Date;


/**
 * <p>
 *  api
 * </p>
 *
 * @author Code Generator
 * @since 2018-07-18
 */
@RestController
@Deprecated
@RequestMapping("/api/sku/skuTagRelations")
public class SkuTagRelationEndpoint extends BaseController {



    @Resource
    SkuTagRelationService skuTagRelationService;

    @Resource
    QuerySkuTagRelationDao querySkuTagRelationDao;

    @BusinessLog(name = "SkuTagRelation", value = "create SkuTagRelation")
    @PostMapping
    public Tip createSkuTagRelation(@RequestBody SkuTagRelationModel entity) {

        Integer affected = 0;
        try{
                    affected = skuTagRelationService.createMaster(entity);
        
        }catch (DuplicateKeyException e){
            throw new BusinessException(BusinessCode.DuplicateKey);
        }

        return SuccessTip.create(affected);
    }

    @GetMapping("/{id}")
    public Tip getSkuTagRelation(@PathVariable Long id) {
            return SuccessTip.create(skuTagRelationService.retrieveMaster(id));
        }

    @BusinessLog(name = "SkuTagRelation", value = "update SkuTagRelation")
    @PutMapping("/{id}")
    public Tip updateSkuTagRelation(@PathVariable Long id, @RequestBody SkuTagRelationModel entity) {
        entity.setId(id);
                    return SuccessTip.create(skuTagRelationService.updateMaster(entity));
            }

    @BusinessLog(name = "SkuTagRelation", value = "delete SkuTagRelation")
    @DeleteMapping("/{id}")
    public Tip deleteSkuTagRelation(@PathVariable Long id) {
            return SuccessTip.create(skuTagRelationService.deleteMaster(id));
        }

    @GetMapping
    public Tip querySkuTagRelations(Page<SkuTagRelationRecord> page,
                @RequestParam(name = "pageNum", required = false, defaultValue = "1") Integer pageNum,
                @RequestParam(name = "pageSize", required = false, defaultValue = "10") Integer pageSize,
                                                @RequestParam(name = "id", required = false) Long id,
                                                        @RequestParam(name = "skuId", required = false) Long skuId,
                                                        @RequestParam(name = "tagId", required = false) Long tagId,
                @RequestParam(name = "orderBy",required = false) String  orderBy,
                @RequestParam(name = "sort" ,required = false )  String sort) {
            if(orderBy!=null&&orderBy.length()>0){
            if(sort!=null&&sort.length()>0){
                String pattern = "(ASC|DESC|asc|desc)";
                if(!sort.matches(pattern)){
                    throw new BusinessException(BusinessCode.BadRequest.getCode(), "sort must be ASC or DESC");//此处异常类型根据实际情况而定
                }
            }else{
                sort = "ASC";
            }
            orderBy = "`"+orderBy+"`" +" "+sort;
        }
        page.setCurrent(pageNum);
        page.setSize(pageSize);

        SkuTagRelationRecord record = new SkuTagRelationRecord();
                record.setId(id);
                    record.setSkuId(skuId);
                    record.setTagId(tagId);

        page.setRecords(querySkuTagRelationDao.findSkuTagRelationPage(page, record, orderBy));

        return SuccessTip.create(page);
    }


}

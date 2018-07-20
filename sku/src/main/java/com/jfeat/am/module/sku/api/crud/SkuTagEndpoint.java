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
import com.jfeat.am.module.sku.services.domain.dao.QuerySkuTagDao;
import com.jfeat.am.common.constant.tips.SuccessTip;
import com.jfeat.am.common.constant.tips.Ids;
import com.jfeat.am.common.constant.tips.Tip;
import com.jfeat.am.module.log.annotation.BusinessLog;
import com.jfeat.am.common.exception.BusinessCode;
import com.jfeat.am.common.exception.BusinessException;
import java.math.BigDecimal;
import com.jfeat.am.module.sku.services.domain.service.SkuTagService;
import com.jfeat.am.module.sku.services.domain.model.SkuTagRecord;
import com.jfeat.am.module.sku.services.crud.model.SkuTagModel;
import com.jfeat.am.module.sku.services.persistence.model.SkuTag;

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

@RequestMapping("/api/sku/tags")
public class SkuTagEndpoint extends BaseController {



    @Resource
    SkuTagService skuTagService;

    @Resource
    QuerySkuTagDao querySkuTagDao;

    @BusinessLog(name = "SkuTag", value = "create SkuTag")
    @PostMapping
    public Tip createSkuTag(@PathVariable long skuId,@RequestBody SkuTagModel entity) {

        Integer affected = 0;
        try{
                    affected = skuTagService.createMaster(entity);
        
        }catch (DuplicateKeyException e){
            throw new BusinessException(BusinessCode.DuplicateKey);
        }

        return SuccessTip.create(affected);
    }

    @GetMapping("/{id}")
    public Tip getSkuTag(@PathVariable Long id) {
            return SuccessTip.create(skuTagService.retrieveMaster(id));
        }

    @BusinessLog(name = "SkuTag", value = "update SkuTag")
    @PutMapping("/{id}")
    public Tip updateSkuTag(@PathVariable Long id, @RequestBody SkuTagModel entity) {
        entity.setId(id);
                    return SuccessTip.create(skuTagService.updateMaster(entity));
            }

    @BusinessLog(name = "SkuTag", value = "delete SkuTag")
    @DeleteMapping("/{id}")
    public Tip deleteSkuTag(@PathVariable Long id) {
            return SuccessTip.create(skuTagService.deleteMaster(id));
        }

    @GetMapping
    public Tip querySkuTags(Page<SkuTagRecord> page,
                @RequestParam(name = "pageNum", required = false, defaultValue = "1") Integer pageNum,
                @RequestParam(name = "pageSize", required = false, defaultValue = "10") Integer pageSize,
                                                @RequestParam(name = "id", required = false) Long id,
                                                    @RequestParam(name = "tagName", required = false) String tagName,
                                                    @RequestParam(name = "tagDescription", required = false) String tagDescription,
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

        SkuTagRecord record = new SkuTagRecord();
                record.setId(id);
                    record.setTagName(tagName);
                    record.setTagDescription(tagDescription);

        page.setRecords(querySkuTagDao.findSkuTagPage(page, record, orderBy));

        return SuccessTip.create(page);
    }


}

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
import com.jfeat.am.module.sku.services.domain.dao.QuerySkuSpecificationDao;
import com.jfeat.am.common.constant.tips.SuccessTip;
import com.jfeat.am.common.constant.tips.Tip;
import com.jfeat.am.module.log.annotation.BusinessLog;
import com.jfeat.am.common.exception.BusinessCode;
import com.jfeat.am.common.exception.BusinessException;
import java.math.BigDecimal;
import com.jfeat.am.module.sku.services.domain.service.SkuSpecificationService;
import com.jfeat.am.module.sku.services.domain.model.SkuSpecificationRecord;
import com.jfeat.am.module.sku.services.crud.model.SkuSpecificationModel;

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

@RequestMapping("/api/sku/skuSpecifications")
public class SkuSpecificationEndpoint extends BaseController {



    @Resource
    SkuSpecificationService skuSpecificationService;

    @Resource
    QuerySkuSpecificationDao querySkuSpecificationDao;

    @BusinessLog(name = "SkuSpecification", value = "create SkuSpecification")
    @PostMapping
    public Tip createSkuSpecification(@RequestBody SkuSpecificationModel entity) {

        Integer affected = 0;
        try{
                    affected = skuSpecificationService.addRelation(entity);
        
        }catch (DuplicateKeyException e){
            throw new BusinessException(BusinessCode.DuplicateKey);
        }

        return SuccessTip.create(affected);
    }

    @GetMapping("/{id}")
    public Tip getSkuSpecification(@PathVariable Long id) {
            return SuccessTip.create(skuSpecificationService.getPeerList(id));
        }

    @BusinessLog(name = "SkuSpecification", value = "update SkuSpecification")
    @PutMapping("/{id}")
    public Tip updateSkuSpecification(@PathVariable Long id, @RequestBody SkuSpecificationModel entity) {
        entity.setId(id);
                    return SuccessTip.create(skuSpecificationService.updateRelation(entity));
            }

    @BusinessLog(name = "SkuSpecification", value = "delete SkuSpecification")
    @DeleteMapping("/{id}")
    public Tip deleteSkuSpecification(@PathVariable Long id) {
            return SuccessTip.create(skuSpecificationService.deleteMaster(id));
        }

    @GetMapping
    public Tip querySkuSpecifications(Page<SkuSpecificationRecord> page,
                @RequestParam(name = "pageNum", required = false, defaultValue = "1") Integer pageNum,
                @RequestParam(name = "pageSize", required = false, defaultValue = "10") Integer pageSize,
                                                @RequestParam(name = "id", required = false) Long id,
                                                        @RequestParam(name = "skuId", required = false) Long skuId,
                                                        @RequestParam(name = "groupId", required = false) Long groupId,
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

        SkuSpecificationRecord record = new SkuSpecificationRecord();
                record.setId(id);
                    record.setSkuId(skuId);
                    record.setGroupId(groupId);

        page.setRecords(querySkuSpecificationDao.findSkuSpecificationPage(page, record, orderBy));

        return SuccessTip.create(page);
    }


}

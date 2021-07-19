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
import com.jfeat.am.module.product.services.domain.dao.QueryConditionDao;
import com.jfeat.am.module.log.annotation.BusinessLog;
import com.jfeat.am.module.product.services.domain.service.ConditionService;
import com.jfeat.am.module.product.services.domain.model.ConditionRecord;
import com.jfeat.am.module.product.services.persistence.model.Condition;

import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;


/**
 * <p>
 *  api
 * </p>
 *
 * @author Code Generator
 * @since 2018-06-19
 */
@RestController
@Api("WMS-产品-产品状况表")
@RequestMapping("/api/product/conditions")
public class ConditionEndpoint   {



    @Resource
    ConditionService conditionService;

    @Resource
    QueryConditionDao queryConditionDao;

    @BusinessLog(name = "Condition", value = "create Condition")
    @PostMapping
    @ApiOperation("新建状况属性")
    public Tip createCondition(@RequestBody Condition entity) {

        Integer affected = 0;
        try{
                    affected = conditionService.createMaster(entity);
        
        }catch (DuplicateKeyException e){
            throw new BusinessException(BusinessCode.DuplicateKey);
        }

        return SuccessTip.create(affected);
    }

    @GetMapping("/{id}")
    @ApiOperation("查看状况属性信息")
    public Tip getCondition(@PathVariable Long id) {
            return SuccessTip.create(conditionService.retrieveMaster(id));
        }

    @BusinessLog(name = "Condition", value = "update Condition")
    @PutMapping("/{id}")
    @ApiOperation("修改状况属性")
    public Tip updateCondition(@PathVariable Long id, @RequestBody Condition entity) {
        entity.setId(id);
                    return SuccessTip.create(conditionService.updateMaster(entity));
            }

    @BusinessLog(name = "Condition", value = "delete Condition")
    @DeleteMapping("/{id}")
    @ApiOperation("删除状况属性")
    public Tip deleteCondition(@PathVariable Long id) {
            return SuccessTip.create(conditionService.deleteMaster(id));
        }

    @GetMapping
    @ApiOperation("状况属性列表")
    public Tip queryConditions(Page<ConditionRecord> page,
                               @RequestParam(name = "pageNum", required = false, defaultValue = "1") Integer pageNum,
                               @RequestParam(name = "pageSize", required = false, defaultValue = "10") Integer pageSize,
                               @RequestParam(name = "id", required = false) Long id,
                               @RequestParam(name = "conditionName", required = false) String conditionName,
                               @RequestParam(name = "pid", required = false) Long pid,
                               @RequestParam(name = "conditionDescription", required = false) String conditionDescription,
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

        ConditionRecord record = new ConditionRecord();
                record.setId(id);
                    record.setConditionName(conditionName);
                record.setPid(pid);
                    record.setConditionDescription(conditionDescription);

        page.setRecords(queryConditionDao.findConditionPage(page, record, orderBy));

        return SuccessTip.create(page);
    }


}

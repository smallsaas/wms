package com.jfeat.am.module.social.services.crud.service.impl;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.baomidou.mybatisplus.mapper.BaseMapper;
import com.baomidou.mybatisplus.mapper.EntityWrapper;
import com.jfeat.am.common.crud.impl.CRUDServiceOnlyImpl;
import com.jfeat.am.module.social.services.crud.service.StockEvaluationAdditionService;
import com.jfeat.am.module.social.services.crud.service.StockEvaluationService;
import com.jfeat.am.module.social.services.domain.model.StockEvaluationModel;
import com.jfeat.am.module.social.services.persistence.dao.StockEvaluationAdditionMapper;
import com.jfeat.am.module.social.services.persistence.dao.StockEvaluationMapper;
import com.jfeat.am.module.social.services.persistence.model.StockEvaluation;
import com.jfeat.am.module.social.services.persistence.model.StockEvaluationAddition;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;

/**
 * <p>
 *  implementation
 * </p>
 *
 * @author Code Generator
 * @since 2017-10-19
 */
@Service
public class StockEvaluationServiceImpl extends CRUDServiceOnlyImpl<StockEvaluation> implements StockEvaluationService {
    @Resource
    private StockEvaluationMapper stockEvaluationMapper;
    @Resource
    private StockEvaluationAdditionMapper stockEvaluationAdditionMapper;

    @Override
    protected BaseMapper<StockEvaluation> getMasterMapper() {
        return stockEvaluationMapper;
    }

    public StockEvaluationModel returnStockEvaluationIncludeAddition(long id){
        StockEvaluation evaluation = stockEvaluationMapper.selectById(id);
        JSONObject evaluateObj = JSON.parseObject(JSON.toJSONString(evaluation));
        List<StockEvaluationAddition> additions = stockEvaluationAdditionMapper.selectList(
                new EntityWrapper<StockEvaluationAddition>().eq("evaluate_id",id));
        evaluateObj.put("additions",additions);
        StockEvaluationModel model = JSON.parseObject(JSON.toJSONString(evaluateObj),StockEvaluationModel.class);
        return model;
}
    public Integer deleteStockEvaluationIncludeAddition(long id){
        StockEvaluation evaluation = stockEvaluationMapper.selectById(id);
        StockEvaluationAddition addition = stockEvaluationAdditionMapper.selectList(
                new EntityWrapper<StockEvaluationAddition>().eq("evaluate_id",evaluation.getId())).get(0);
        if(addition != null){
            stockEvaluationAdditionMapper.deleteById(addition.getId());
        }
        return stockEvaluationMapper.deleteById(id);
    }

}





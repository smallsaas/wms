package com.jfeat.am.module.evaluation.services.crud.service.impl;
            
import com.baomidou.mybatisplus.mapper.BaseMapper;
import com.jfeat.am.common.crud.FIELD;
import com.jfeat.am.module.evaluation.services.persistence.model.StockEvaluation;
import com.jfeat.am.module.evaluation.services.persistence.dao.StockEvaluationMapper;
import com.jfeat.am.module.evaluation.services.persistence.dao.StockEvaluationAdditionMapper;
import com.jfeat.am.module.evaluation.services.persistence.model.StockEvaluationAddition;
import com.jfeat.am.module.evaluation.services.persistence.dao.StockEvaluationImageMapper;
import com.jfeat.am.module.evaluation.services.persistence.model.StockEvaluationImage;
import com.jfeat.am.module.evaluation.services.persistence.dao.StockEvaluationStarMapper;
import com.jfeat.am.module.evaluation.services.persistence.model.StockEvaluationStar;


import com.jfeat.am.module.evaluation.services.crud.service.CRUDStockEvaluationService;
import org.springframework.stereotype.Service;
import com.jfeat.am.common.exception.BusinessCode;
import com.jfeat.am.common.exception.BusinessException;
import javax.annotation.Resource;
import com.jfeat.am.common.crud.impl.CRUDServiceOverModelImpl;
import com.jfeat.am.module.evaluation.services.domain.model.StockEvaluationModel;

/**
 * <p>
 *  implementation
 * </p>
 *CRUDStockEvaluationService
 * @author Code Generator
 * @since 2018-07-16
 */

@Service
public class CRUDStockEvaluationServiceImpl  extends CRUDServiceOverModelImpl<StockEvaluation,StockEvaluationModel> implements CRUDStockEvaluationService {









    @Resource
    private StockEvaluationMapper stockEvaluationMapper;

    
    @Override
    protected BaseMapper<StockEvaluation> getMasterMapper() {
        return stockEvaluationMapper;
    }

    @Override
    protected Class<StockEvaluation> masterClassName() {
        return StockEvaluation.class;
    }

    @Override
    protected Class<StockEvaluationModel> modelClassName() {
        return StockEvaluationModel.class;
    }


    

    @Resource
    private StockEvaluationAdditionMapper stockEvaluationAdditionMapper;

    @Deprecated
    private final static String stockEvaluationAdditionFieldName = "evaluation_id";

    private final static String stockEvaluationAdditionKeyName = "stockEvaluationAdditions";

    

    @Resource
    private StockEvaluationImageMapper stockEvaluationImageMapper;

    @Deprecated
    private final static String stockEvaluationImageFieldName = "evaluation_id";

    private final static String stockEvaluationImageKeyName = "stockEvaluationImages";

    

    @Resource
    private StockEvaluationStarMapper stockEvaluationStarMapper;

    @Deprecated
    private final static String stockEvaluationStarFieldName = "evaluation_id";

    private final static String stockEvaluationStarKeyName = "stockEvaluationStars";

    @Override
    protected String[] slaveFieldNames() {
        return new String[]{
                                             stockEvaluationAdditionKeyName
                             
                                    ,stockEvaluationImageKeyName
            
                                    ,stockEvaluationStarKeyName
            
                };
    }

    @Override
    protected FIELD onSlaveFieldItem(String field) {
                    if (field.compareTo(stockEvaluationAdditionKeyName) == 0) {
                FIELD _field = new FIELD();
            _field.setItemKeyName(field);
            _field.setItemFieldName(stockEvaluationAdditionFieldName);
            _field.setItemClassName(StockEvaluationAddition.class);
            _field.setItemMapper(stockEvaluationAdditionMapper);
            return _field;
        }
                    else if (field.compareTo(stockEvaluationImageKeyName) == 0) {
                FIELD _field = new FIELD();
            _field.setItemKeyName(field);
            _field.setItemFieldName(stockEvaluationImageFieldName);
            _field.setItemClassName(StockEvaluationImage.class);
            _field.setItemMapper(stockEvaluationImageMapper);
            return _field;
        }
                    else if (field.compareTo(stockEvaluationStarKeyName) == 0) {
                FIELD _field = new FIELD();
            _field.setItemKeyName(field);
            _field.setItemFieldName(stockEvaluationStarFieldName);
            _field.setItemClassName(StockEvaluationStar.class);
            _field.setItemMapper(stockEvaluationStarMapper);
            return _field;
        }
        
        throw new BusinessException(BusinessCode.BadRequest);
    }


    @Override
    protected String[] childFieldNames() {
        return new String[]{
                                };
    }

    @Override
    protected FIELD onChildFieldItem(String field) {
                
        throw new BusinessException(BusinessCode.BadRequest);
    }




}



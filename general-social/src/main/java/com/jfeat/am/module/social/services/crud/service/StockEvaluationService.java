package com.jfeat.am.module.social.services.crud.service;
            
import com.jfeat.am.common.crud.CRUDServiceOnly;
import com.jfeat.am.common.crud.CRUDServiceOverModel;
import com.jfeat.am.module.social.services.domain.model.StockEvaluationModel;
import com.jfeat.am.module.social.services.persistence.model.StockEvaluation;


/**
 * <p>
 *  service interface
 * </p>
 *
 * @author Code Generator
 * @since 2017-10-19
 */

public interface StockEvaluationService extends CRUDServiceOnly<StockEvaluation> {

    StockEvaluationModel returnStockEvaluationIncludeAddition(long id);

}

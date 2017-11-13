package com.jfeat.am.module.social.services.domain.model;
import com.jfeat.am.module.social.services.persistence.model.StockEvaluation;
import com.jfeat.am.module.social.services.persistence.model.StockEvaluationAddition;
import java.util.List;
/**
 * Created by Code Generator on 2017-11-11
 */
public class StockEvaluationModel extends StockEvaluation{
    List<StockEvaluationAddition> StockEvaluationAddition;

    public List<com.jfeat.am.module.social.services.persistence.model.StockEvaluationAddition> getStockEvaluationAddition() {
        return StockEvaluationAddition;
    }

    public void setStockEvaluationAddition(List<com.jfeat.am.module.social.services.persistence.model.StockEvaluationAddition> stockEvaluationAddition) {
        StockEvaluationAddition = stockEvaluationAddition;
    }
}

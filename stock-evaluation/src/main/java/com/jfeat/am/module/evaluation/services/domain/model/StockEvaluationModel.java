package com.jfeat.am.module.evaluation.services.domain.model;

import java.util.List;
import com.jfeat.am.module.evaluation.services.persistence.model.StockEvaluationAddition;
import com.jfeat.am.module.evaluation.services.persistence.model.StockEvaluationImage;
import com.jfeat.am.module.evaluation.services.persistence.model.StockEvaluationStar;
import com.jfeat.am.module.evaluation.services.persistence.model.StockEvaluation;

/**
 * Created by Code Generator on 2018-07-16
 */
public class StockEvaluationModel extends StockEvaluation{
        private List<StockEvaluationAddition> stockEvaluationAddition;
        private List<StockEvaluationImage> stockEvaluationImage;
        private List<StockEvaluationStar> stockEvaluationStar;
}

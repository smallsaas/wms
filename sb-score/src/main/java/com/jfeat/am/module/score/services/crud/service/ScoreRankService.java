package com.jfeat.am.module.score.services.crud.service;
            
import com.jfeat.am.common.crud.CRUDServiceOnly;
import com.jfeat.am.module.score.services.persistence.model.ScoreRank;


/**
 * <p>
 *  service interface
 * </p>
 *
 * @author Code Generator
 * @since 2018-04-13
 * Master: ${cfg.masterModel}
  * Slave : sb_score_rank
  */
public interface ScoreRankService  extends CRUDServiceOnly<ScoreRank> {

    Boolean deleteData();

    Boolean insertData();

    ScoreRank getScoreRankByUserId(Long userId);

}

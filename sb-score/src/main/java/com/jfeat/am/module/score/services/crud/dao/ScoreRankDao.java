package com.jfeat.am.module.score.services.crud.dao;

import com.baomidou.mybatisplus.mapper.BaseMapper;
import com.jfeat.am.module.score.services.persistence.model.ScoreRank;

/**
 * <p>
  *  Mapper 接口
 * </p>
 *
 * @author Code Generator
 * @since 2018-04-13
 */
public interface ScoreRankDao extends BaseMapper<ScoreRank> {

    Boolean deleteData();

    Boolean insertData();

}
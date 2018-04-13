package com.jfeat.am.module.score.services.crud.service.impl;
            
import com.baomidou.mybatisplus.mapper.BaseMapper;
import com.baomidou.mybatisplus.mapper.EntityWrapper;
import com.jfeat.am.module.score.services.crud.dao.ScoreRankDao;
import com.jfeat.am.module.score.services.persistence.model.ScoreRank;
import com.jfeat.am.module.score.services.persistence.dao.ScoreRankMapper;
import com.jfeat.am.module.score.services.crud.service.ScoreRankService;
import com.jfeat.am.common.crud.impl.CRUDServiceOnlyImpl;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import com.jfeat.am.common.crud.impl.CRUDServiceOnlyImpl;

/**
 * <p>
 *  implementation
 * </p>
 *
 * @author Code Generator
 * @since 2018-04-13
 */
@Service
public class ScoreRankServiceImpl  extends CRUDServiceOnlyImpl<ScoreRank> implements ScoreRankService {



    @Resource
    private ScoreRankMapper scoreRankMapper;
    @Resource
    private ScoreRankDao scoreRankDao;

    @Override
    protected BaseMapper<ScoreRank> getMasterMapper() {
        return scoreRankMapper;
    }

    @Override
    public Boolean deleteData() {
        return scoreRankDao.deleteData();
    }

    @Override
    public Boolean insertData() {
        return scoreRankDao.insertData();
    }

    @Override
    public ScoreRank getScoreRankByUserId(Long userId) {
        return scoreRankMapper.selectList(new EntityWrapper<ScoreRank>().eq(ScoreRank.USER_ID,userId)).get(0);
    }
}



package com.jfeat.am.module.score.services.crud.service.impl;

import com.baomidou.mybatisplus.mapper.BaseMapper;
import com.baomidou.mybatisplus.mapper.EntityWrapper;
import com.jfeat.am.module.score.services.persistence.model.Score;
import com.jfeat.am.module.score.services.persistence.dao.ScoreMapper;
import com.jfeat.am.module.score.services.crud.service.ScoreService;
import com.jfeat.am.common.crud.impl.CRUDServiceOnlyImpl;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;

import com.jfeat.am.common.crud.impl.CRUDServiceOnlyImpl;

import java.util.List;

/**
 * <p>
 * implementation
 * </p>
 *
 * @author Code Generator
 * @since 2018-04-11
 */
@Service
public class ScoreServiceImpl extends CRUDServiceOnlyImpl<Score> implements ScoreService {


    @Resource
    private ScoreMapper scoreMapper;

    @Override
    protected BaseMapper<Score> getMasterMapper() {
        return scoreMapper;
    }

    @Override
    public List<Score> queryScoreByUserId(Long userId) {
        return getMasterMapper().selectList(new EntityWrapper<Score>().eq(Score.USER_ID,userId));
    }
}



package com.jfeat.am.module.score.listener;

import com.jfeat.am.core.support.BeanKit;
import com.jfeat.am.module.score.event.ScoreBean;
import com.jfeat.am.module.score.event.ScoreEvent;
import com.jfeat.am.module.score.services.crud.service.ScoreService;
import com.jfeat.am.module.score.services.persistence.dao.ScoreMapper;
import com.jfeat.am.module.score.services.persistence.model.Score;
import com.jfeat.module.event.BasicEvent;
import com.jfeat.module.event.BasicEventListener;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.context.ApplicationEvent;
import org.springframework.context.annotation.Lazy;
import org.springframework.stereotype.Component;

import javax.annotation.Resource;
import java.util.Date;

@Component
public class ScoreEventListener extends BasicEventListener<ScoreBean> {

    private static final Logger logger = LoggerFactory.getLogger(ScoreEventListener.class);

    @Resource @Lazy
    private ScoreMapper scoreMapper;

    @Override
    public boolean supportsEventType(Class<? extends ApplicationEvent> aClass) {
        return aClass == ScoreEvent.class;
    }

    @Override
    protected void onBasicEvent(BasicEvent<ScoreBean> basicEvent) {
        Integer affected = 0;
        logger.debug("scan basieEvent {}", basicEvent.getClass());
        ScoreBean scoreBean = basicEvent.getTarget();
        Long userId = scoreBean.getUserId();
        String type = scoreBean.getType();
        Long score = scoreBean.getScore();
        Date updateTime = scoreBean.getUpdateTime();
        logger.debug("qrcode event: userId: {}, type: {}, score: {}, updateTime: {}",userId, type, score, updateTime);

        Score scoreReturn = scoreMapper.selectOne(new Score().setType(scoreBean.getType()).setUserId(scoreBean.getUserId()));
        if (scoreReturn != null){
            scoreReturn.setScore(scoreReturn.getScore() + scoreBean.getScore());
            affected += scoreMapper.updateById(scoreReturn);
        }else {
            scoreReturn = new Score();
            scoreReturn.setScore(score);
            scoreReturn.setType(type);
            scoreReturn.setUpdateTime(new Date());
            scoreReturn.setUserId(userId);
            affected += scoreMapper.insert(scoreReturn);
        }
        logger.debug("affected {}", affected);
    }
}

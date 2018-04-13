package com.jfeat.am.module.score.task;

import com.jfeat.am.core.support.DateTimeKit;
import com.jfeat.am.module.score.services.crud.service.ScoreRankService;
import com.jfeat.am.module.score.services.persistence.model.ScoreRank;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import javax.annotation.Resource;
import java.util.Calendar;

@Component
public class ScoreRankJob {

    private static final Logger logger = LoggerFactory.getLogger(ScoreRankJob.class);

    @Resource
    ScoreRankService scoreRankService;


    @Scheduled(cron = "0 0 2 ? * *")
    public void audit() {
        logger.info("Start to update score rank");
        scoreRankService.deleteData();
        scoreRankService.insertData();
        logger.info("End to update score rank");

    }
}

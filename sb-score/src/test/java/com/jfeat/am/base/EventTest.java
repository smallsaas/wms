package com.jfeat.am.base;

import com.jfeat.am.module.score.event.ScoreBean;
import com.jfeat.am.module.score.event.ScoreEvent;
import com.jfeat.module.event.EventService;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.Date;
import java.util.concurrent.TimeUnit;

public class EventTest extends BaseJunit {

    @Autowired
    private EventService eventService;

    @Test
    public void test() throws InterruptedException {
        ScoreBean scoreBean = new ScoreBean();
        scoreBean.setUserId(123412341234L);
        scoreBean.setType("essay");
        scoreBean.setUpdateTime(new Date());
        scoreBean.setScore(1L);
        eventService.publishEvent(new ScoreEvent(this, scoreBean));
        TimeUnit.SECONDS.sleep(20);
    }


}

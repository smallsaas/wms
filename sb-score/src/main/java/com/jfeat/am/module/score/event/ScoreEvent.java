package com.jfeat.am.module.score.event;

import com.jfeat.module.event.BasicEvent;

public class ScoreEvent extends BasicEvent<ScoreBean> {
    public ScoreEvent(Object source, ScoreBean target) {
        super(source, target);
    }


}

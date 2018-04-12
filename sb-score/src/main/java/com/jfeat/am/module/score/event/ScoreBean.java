package com.jfeat.am.module.score.event;

import java.util.Date;

public class ScoreBean {

    private Long userId;
    /**
     * 类型
     */
    private String type;
    /**
     * 积分
     */
    private Long score;
    /**
     * 最近更新时间
     */
    private Date updateTime;

    public Long getUserId() {
        return userId;
    }

    public void setUserId(Long userId) {
        this.userId = userId;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public Long getScore() {
        return score;
    }

    public void setScore(Long score) {
        this.score = score;
    }

    public Date getUpdateTime() {
        return updateTime;
    }

    public void setUpdateTime(Date updateTime) {
        this.updateTime = updateTime;
    }
}

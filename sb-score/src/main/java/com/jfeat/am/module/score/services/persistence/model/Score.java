package com.jfeat.am.module.score.services.persistence.model;

import java.io.Serializable;

import java.util.Date;
import com.baomidou.mybatisplus.annotations.TableField;
import com.baomidou.mybatisplus.activerecord.Model;
import com.baomidou.mybatisplus.annotations.TableName;
import java.io.Serializable;

/**
 * <p>
 * 
 * </p>
 *
 * @author Code Generator
 * @since 2018-04-11
 */
@TableName("sb_score")
public class Score extends Model<Score> {

    private static final long serialVersionUID = 1L;

	private Long id;
	@TableField("user_id")
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
	@TableField("update_time")
	private Date updateTime;


	public Long getId() {
		return id;
	}

	public Score setId(Long id) {
		this.id = id;
		return this;
	}

	public Long getUserId() {
		return userId;
	}

	public Score setUserId(Long userId) {
		this.userId = userId;
		return this;
	}

	public String getType() {
		return type;
	}

	public Score setType(String type) {
		this.type = type;
		return this;
	}

	public Long getScore() {
		return score;
	}

	public Score setScore(Long score) {
		this.score = score;
		return this;
	}

	public Date getUpdateTime() {
		return updateTime;
	}

	public Score setUpdateTime(Date updateTime) {
		this.updateTime = updateTime;
		return this;
	}

	public static final String ID = "id";

	public static final String USER_ID = "user_id";

	public static final String TYPE = "type";

	public static final String SCORE = "score";

	public static final String UPDATE_TIME = "update_time";

	@Override
	protected Serializable pkVal() {
		return this.id;
	}

	@Override
	public String toString() {
		return "Score{" +
			"id=" + id +
			", userId=" + userId +
			", type=" + type +
			", score=" + score +
			", updateTime=" + updateTime +
			"}";
	}
}

package com.jfeat.am.module.score.services.persistence.model;

import java.io.Serializable;

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
 * @since 2018-04-13
 */
@TableName("sb_score_rank")
public class ScoreRank extends Model<ScoreRank> {

    private static final long serialVersionUID = 1L;

	private Long id;
	@TableField("user_id")
	private Long userId;
    /**
     * 总积分
     */
	@TableField("total_score")
	private String totalScore;
    /**
     * 排名
     */
	private String rank;
    /**
     * 击败率
     */
	@TableField("beat_rate")
	private String beatRate;


	public Long getId() {
		return id;
	}

	public ScoreRank setId(Long id) {
		this.id = id;
		return this;
	}

	public Long getUserId() {
		return userId;
	}

	public ScoreRank setUserId(Long userId) {
		this.userId = userId;
		return this;
	}

	public String getTotalScore() {
		return totalScore;
	}

	public ScoreRank setTotalScore(String totalScore) {
		this.totalScore = totalScore;
		return this;
	}

	public String getRank() {
		return rank;
	}

	public ScoreRank setRank(String rank) {
		this.rank = rank;
		return this;
	}

	public String getBeatRate() {
		return beatRate;
	}

	public ScoreRank setBeatRate(String beatRate) {
		this.beatRate = beatRate;
		return this;
	}

	public static final String ID = "id";

	public static final String USER_ID = "user_id";

	public static final String TOTAL_SCORE = "total_score";

	public static final String RANK = "rank";

	public static final String BEAT_RATE = "beat_rate";

	@Override
	protected Serializable pkVal() {
		return this.id;
	}

	@Override
	public String toString() {
		return "ScoreRank{" +
			"id=" + id +
			", userId=" + userId +
			", totalScore=" + totalScore +
			", rank=" + rank +
			", beatRate=" + beatRate +
			"}";
	}
}

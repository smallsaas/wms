package com.jfeat.am.module.organization.services.persistence.model;

import com.baomidou.mybatisplus.enums.IdType;
import com.baomidou.mybatisplus.annotations.TableId;
import com.baomidou.mybatisplus.annotations.TableField;
import com.baomidou.mybatisplus.activerecord.Model;
import com.baomidou.mybatisplus.annotations.TableName;
import java.io.Serializable;

/**
 * <p>
 * 
 * </p>
 *
 * @author admin
 * @since 2018-05-17
 */
@TableName("t_team_staff")
public class TeamStaff extends Model<TeamStaff> {

    private static final long serialVersionUID = 1L;

    @TableId(value = "id", type = IdType.AUTO)
    private Long id;
    /**
     * 团队ID
     */
    @TableField("team_id")
    private Long teamId;
    /**
     * 员工ID
     */
    @TableField("staff_id")
    private Long staffId;
    /**
     * 领导者(0-N 1-Y)
     */
    @TableField("is_leader")
    private Integer isLeader;


    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Long getTeamId() {
        return teamId;
    }

    public void setTeamId(Long teamId) {
        this.teamId = teamId;
    }

    public Long getStaffId() {
        return staffId;
    }

    public void setStaffId(Long staffId) {
        this.staffId = staffId;
    }

    public Integer getIsLeader() {
        return isLeader;
    }

    public void setIsLeader(Integer isLeader) {
        this.isLeader = isLeader;
    }

    public static final String ID = "id";

    public static final String TEAM_ID = "team_id";

    public static final String STAFF_ID = "staff_id";

    public static final String IS_LEADER = "is_leader";

    @Override
    protected Serializable pkVal() {
        return this.id;
    }

    @Override
    public String toString() {
        return "TeamStaff{" +
        ", id=" + id +
        ", teamId=" + teamId +
        ", staffId=" + staffId +
        ", isLeader=" + isLeader +
        "}";
    }
}

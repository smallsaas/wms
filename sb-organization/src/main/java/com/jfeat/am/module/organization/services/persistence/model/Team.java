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
@TableName("t_team")
public class Team extends Model<Team> {

    private static final long serialVersionUID = 1L;

    @TableId(value = "id", type = IdType.AUTO)
    private Long id;
    /**
     * 团队名称
     */
    @TableField("team_name")
    private String teamName;
    /**
     * 团队描述
     */
    @TableField("team_desc")
    private String teamDesc;
    /**
     * 父级ID
     */
    private Long pid;


    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getTeamName() {
        return teamName;
    }

    public void setTeamName(String teamName) {
        this.teamName = teamName;
    }

    public String getTeamDesc() {
        return teamDesc;
    }

    public void setTeamDesc(String teamDesc) {
        this.teamDesc = teamDesc;
    }

    public Long getPid() {
        return pid;
    }

    public void setPid(Long pid) {
        this.pid = pid;
    }

    public static final String ID = "id";

    public static final String TEAM_NAME = "team_name";

    public static final String TEAM_DESC = "team_desc";

    public static final String PID = "pid";

    @Override
    protected Serializable pkVal() {
        return this.id;
    }

    @Override
    public String toString() {
        return "Team{" +
        ", id=" + id +
        ", teamName=" + teamName +
        ", teamDesc=" + teamDesc +
        ", pid=" + pid +
        "}";
    }
}

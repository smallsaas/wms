package com.jfeat.am.module.organization.services.domain.dao;

import com.baomidou.mybatisplus.mapper.BaseMapper;
import com.baomidou.mybatisplus.plugins.Page;
import com.jfeat.am.module.organization.services.domain.model.TeamModel;
import com.jfeat.am.module.organization.services.persistence.model.Team;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;

import java.util.List;

/**
 * Created by Code Generator on 2017-11-20
 */
public interface QueryTeamDao extends BaseMapper<Team> {
    List<TeamModel> findTeamPage(Page<TeamModel> page, Team team);

    List<Team> teamsLeader(Page<Team> page, @Param("staffId") long staffId);

    @Select("SELECT count(id) FROM team WHERE id = #{teamId}")
    Integer checkTeamIsExist(@Param("teamId") Long teamId);

    @Select("SELECT COUNT(id) FROM taskFollower WHERE teamId = #{teamId}")
    Integer checkTeamWithFollow(@Param("teamId") Long teamId);
}
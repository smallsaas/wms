package com.jfeat.am.module.organization.services.domain.service;

import com.baomidou.mybatisplus.plugins.Page;
import com.jfeat.am.module.organization.services.domain.model.TeamModel;
import com.jfeat.am.module.organization.services.persistence.model.Team;

import java.util.List;

/**
 * Created by vincent on 2017/10/19.
 */
public interface QueryTeamService {
    List<TeamModel> findTeamPage(Page<TeamModel> page, Team team);

    /**
     *      所有我领导的 Team。
     * */
    List<Team> teamsLeader(Page<Team> page, long staffId);
}
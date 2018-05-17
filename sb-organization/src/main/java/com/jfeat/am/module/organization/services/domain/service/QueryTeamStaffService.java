package com.jfeat.am.module.organization.services.domain.service;

import com.baomidou.mybatisplus.plugins.Page;
import com.jfeat.am.module.organization.services.domain.model.NameModel;
import com.jfeat.am.module.organization.services.persistence.model.Staff;
import com.jfeat.am.module.organization.services.persistence.model.Team;
import com.jfeat.am.module.organization.services.persistence.model.TeamStaff;

import java.util.List;

/**
 * Created by vincent on 2017/10/19.
 */
public interface QueryTeamStaffService {
    List<TeamStaff> findStaffTeamPage(Page<TeamStaff> page, TeamStaff teamStaff);

    /**
     * 取得个人所属的Team
     * @param staffId
     * @return
     */
    List<Team> getTeamsLedByStaff(long staffId);

    List<Staff> getTeamLeaders(long teamId);

    // 工作组 Add Team 复选框中，根据团队名称查找用户，根据用户查找团队名称， 或者根据两者结合查找用户
    List<NameModel> searchByStaffOrTeamName(Page<NameModel> page, String teamName, String staffName);
}
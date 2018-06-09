package com.jfeat.am.module.organization.services.domain.dao;

import com.baomidou.mybatisplus.mapper.BaseMapper;
import com.baomidou.mybatisplus.plugins.Page;
import com.jfeat.am.module.organization.services.persistence.model.TeamStaff;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Update;

import java.util.List;

/**
 * Created by Code Generator on 2017-11-20
 */
public interface QueryStaffTeamDao extends BaseMapper<TeamStaff> {
    List<TeamStaff> findStaffteamPage(Page<TeamStaff> page, TeamStaff teamStaff);

    @Update("UPDATE user_role set roleid = #{roleId} WHERE userid = #{userId}")
    Integer updateUserRole(@Param("userId") long userId, @Param("roleId") long roleId);
}
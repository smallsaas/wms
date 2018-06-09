package com.jfeat.am.module.organization.services.patch.impl;


import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.baomidou.mybatisplus.mapper.EntityWrapper;
import com.jfeat.am.common.exception.BusinessException;
import com.jfeat.am.module.organization.services.crud.service.StaffService;
import com.jfeat.am.module.organization.services.crud.service.TeamStaffService;
import com.jfeat.am.module.organization.services.domain.model.TeamStaffModel;
import com.jfeat.am.module.organization.services.patch.TeamPatchService;
import com.jfeat.am.module.organization.services.persistence.mapper.TeamMapper;
import com.jfeat.am.module.organization.services.persistence.mapper.TeamStaffMapper;
import com.jfeat.am.module.organization.services.persistence.model.Team;
import com.jfeat.am.module.organization.services.persistence.model.TeamStaff;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Created by Code Generator on 2017-11-20
 * @author Administrator
 */
@Service
public class TeamPatchServiceImpl implements TeamPatchService {

    @Resource
    StaffService staffService;
    @Resource
    TeamStaffService staffTeamService;
    @Resource
    TeamStaffMapper staffTeamMapper;
    @Resource
    TeamMapper teamMapper;


    /**
    *  // 判断某个 Staff 在某个 Team 中是否是该 Team 的 Leader 通过 staffId 以及 TeamId 去查找
     * */
    @Override
    public boolean isTeamStaffLeader(long teamId, long staffId){
        TeamStaff staffTeam = new TeamStaff();
        staffTeam.setTeamId(teamId);
        staffTeam.setStaffId(staffId);

        TeamStaff leader = staffTeamMapper.selectOne(staffTeam);
        if(leader==null || leader.getIsLeader()==null){
            return false;
        }

        return leader.getIsLeader()==1;
    }

/*    //判断某个Team是否有Leader
    public void  isHasTeamLeader(long teamId){
        //查找出属于该Team的所有记录
        List<TeamStaff> staffTeams = staffTeamMapper.selectList(new EntityWrapper<TeamStaff>().eq("teamId",teamId));
        for (TeamStaff staffTeam : staffTeams){
            if((staffTeam.getIsLeader().compareTo(1)) == 0){
                throw new BusinessException(2012,"The team already has Leaders!");
            }
        }
    }*/

    // 判断某个团队是否有 Staff  如果存在 Staff 无法执行删除操作
    /**
     *
     * */
    @Override
    public void judgeWhetherStaffInTeam(long id){
        List<TeamStaff> staffs = staffTeamMapper.selectList(new EntityWrapper<TeamStaff>().eq(TeamStaff.TEAM_ID,id));
        if(staffs.size() > 0){
            throw new BusinessException(2015,"Can not executed that operation!");
        }
    }

    @Override
    public TeamStaff setStaffToTeamLeader(long teamId, Long staffId) {
        return null;
    }

    /**
     *   批量添加Staff到某个team  批量提交的时候，所有的Staff默认都不是领导
     * */
    @Override
    public Integer batchAddStaffToTeam(long teamId, List<Long> ids){
        int result = 0 ;
        for(Long id :ids){

            //通过TeamId 以及 StaffId 判断是否已经在该Team内
            Integer isInTeam = staffTeamMapper.selectCount(new EntityWrapper<TeamStaff>().
                    eq(TeamStaff.TEAM_ID,teamId).eq(TeamStaff.STAFF_ID,id));
            if((isInTeam.compareTo(0) == 0)){
                // 不在该组的时候，允许插入该Team
                TeamStaff staffTeam = new TeamStaff();
                staffTeam.setStaffId(id);
                staffTeam.setTeamId(teamId);
                staffTeam.setIsLeader(0);
                staffTeamMapper.insert(staffTeam);
                result++;
            }else{
            }
        }
        return result;
    }

    /**
     *某个Team批量删除Staff
      */
    @Override
    public Integer batchDeleteStaffToTeam(Long teamId, List<Long> ids){
        int result = 0 ;
        for(Long id :ids){

            //通过TeamId 以及 StaffId 判断是否已经在改Team内
            Integer isInTeam = staffTeamMapper.selectCount(new EntityWrapper<TeamStaff>().
                    eq(TeamStaff.TEAM_ID,teamId).eq(TeamStaff.STAFF_ID,id));
            if((isInTeam.compareTo(1) == 0)){
                Map<String,Object> map = new HashMap<>();
                map.put("teamId",teamId);
                map.put("staffId",id);
                staffTeamMapper.deleteByMap(map);
                result++;
            }else{

            }
        }
        return result;
    }

    /**
     * 判断某个Staff是否在某个Team
     * */
    @Override
    public Boolean isStaffInTeam(long teamId, long staffId) {
        //判断该员工是否在该组里面，如果没有，插入该员工到改组里面
        Integer isInTeam = staffTeamMapper.selectCount(new EntityWrapper<TeamStaff>().
                eq(TeamStaff.TEAM_ID, teamId).eq(TeamStaff.STAFF_ID, staffId));
        if (isInTeam.compareTo(0) != 0) {
            return true;
        }
        return false;
    }



    /**
     * //判断某个Staff是否在某个Team
     * */
    @Override
    public Integer deleteStaffInTeam(long teamId, long staffId) {
        //判断该员工是否在该组里面  如果没有，不能执行删除操作
        Integer isInTeam = staffTeamMapper.selectCount(new EntityWrapper<TeamStaff>().
                eq(TeamStaff.TEAM_ID, teamId).eq(TeamStaff.STAFF_ID, staffId));
        if (isInTeam.compareTo(0) == 0) {
            return null;
        }
        Map<String,Object> map = new HashMap<>();
        map.put("teamId",teamId);
        map.put("staffId",staffId);
        return staffTeamMapper.deleteByMap(map);
    }

    /**
     *    // 我的所有团队
     * */
    @Override
    public List<TeamStaffModel> allMyTeam(long staffId){
        // 找出所有与我有关的团队
        List<TeamStaff> staffTeams = staffTeamMapper.selectList(new EntityWrapper<TeamStaff>().eq(TeamStaff.STAFF_ID,staffId));
        List<TeamStaffModel> models = new ArrayList<TeamStaffModel>();
        for(TeamStaff staffTeam : staffTeams){
            JSONObject staffTeamObj = JSON.parseObject(JSON.toJSONString(staffTeam));
            Team team = teamMapper.selectById(staffTeam.getTeamId());
            staffTeamObj.put("team",team);
            TeamStaffModel model = JSONObject.parseObject(JSON.toJSONString(staffTeamObj),TeamStaffModel.class);
            models.add(model);
        }
        return models;
    }
}

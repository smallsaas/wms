package com.jfeat.am.module.organization.services.crud.service.impl;

import com.alibaba.fastjson.JSONObject;
import com.baomidou.mybatisplus.mapper.BaseMapper;
import com.baomidou.mybatisplus.mapper.EntityWrapper;
import com.jfeat.am.common.crud.impl.CRUDServiceOnlyImpl;
import com.jfeat.am.module.organization.services.crud.service.TeamService;
import com.jfeat.am.module.organization.services.crud.service.TeamStaffService;
import com.jfeat.am.module.organization.services.domain.dao.QueryTeamDao;
import com.jfeat.am.module.organization.services.domain.model.AllTeamIncludeStaff;
import com.jfeat.am.module.organization.services.domain.model.NameModel;
import com.jfeat.am.module.organization.services.domain.model.TeamAndStaffModel;
import com.jfeat.am.module.organization.services.domain.model.TeamModel;
import com.jfeat.am.module.organization.services.persistence.mapper.StaffMapper;
import com.jfeat.am.module.organization.services.persistence.mapper.TeamMapper;
import com.jfeat.am.module.organization.services.persistence.mapper.TeamStaffMapper;
import com.jfeat.am.module.organization.services.persistence.model.Staff;
import com.jfeat.am.module.organization.services.persistence.model.Team;
import com.jfeat.am.module.organization.services.persistence.model.TeamStaff;
import com.jfeat.am.module.profile.services.persistence.dao.ProfileMapper;
import com.jfeat.am.module.profile.services.persistence.model.Profile;
import com.jfeat.am.power.base.PowerBusinessCode;
import com.jfeat.am.power.base.PowerBusinessException;
import com.jfeat.am.power.base.naming.UniversalName;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.Resource;
import java.util.ArrayList;
import java.util.List;

/**
 * <p>
 * 团队 implementation
 * </p>
 *
 * @author Code Generator
 * @since 2017-11-20
 */
@Service
public class TeamServiceImpl extends CRUDServiceOnlyImpl<Team> implements TeamService {

    @Resource
    private TeamMapper teamMapper;
    @Resource
    StaffMapper staffMapper;
    @Resource
    TeamStaffMapper staffTeamMapper;
    @Resource
    TeamStaffService staffTeamService;
    @Resource
    QueryTeamDao queryTeamDao;
    @Resource
    ProfileMapper profileMapper;

    @Override
    protected BaseMapper<Team> getMasterMapper() {
        return teamMapper;
    }

    // 重构 新建 Team Service

    @Transactional
    public Integer createTeamIncludeStaff(TeamAndStaffModel model){
        try {
            teamMapper.insert(model);
        }catch (Exception e){
            throw new PowerBusinessException(PowerBusinessCode.PowerBusinessNameRepetione);
        }
        // 插入 Leader 指派多个个Staff为TeamLeader
        if (model.getLeaders()!=null){
            staffTeamService.setStaffToTeamLeader(model.getId(),model.getLeaders());
        }else{
            // do nothings
        }
        // 插入 staff 批量添加 Staff 到指定的 Team 中
        if (model.getStaffs()!=null){
            staffTeamService.addStaffToTeam(model.getId(),model.getStaffs());
        }else{
            // do nothings
        }
        return 1;
    }

    // 重构 修改 Team Service

    @Transactional
    public Integer updateTeamIncludeStaff(long id,TeamAndStaffModel model){
        Team originTeam = teamMapper.selectById(id);
        model.setId(id);
        if (((originTeam.getTeamName()).compareTo(model.getTeamName()) != 0 )) {
            try {
                teamMapper.updateById(model);
            }catch (Exception e){
                throw new PowerBusinessException(PowerBusinessCode.PowerBusinessNameRepetione);
            }
        }
        teamMapper.updateById(model);
        // 抹去 之前的数据
        staffTeamMapper.delete(new EntityWrapper<TeamStaff>().eq(TeamStaff.TEAM_ID,id));

        // 插入 staff 批量添加 Staff 到指定的 Team 中
        if (model.getStaffs()!=null){
            staffTeamService.addStaffToTeam(id,model.getStaffs());
        }else{
            // do nothings
        }
        // 插入 Leader 指派多个个Staff为TeamLeader
        if (model.getLeaders()!=null){
            staffTeamService.setStaffToTeamLeader(id,model.getLeaders());
        }else{
            // do nothings
        }
        return 1;
    }

    /**
    *  删除 该 Team的所有成员，包括 Leader  提供 给 更新 Team的staff 以及 Leader  的时候使用
    *  1. 删除 某个 Team 下面的 所有的 staff  包括 Leader
    *  2. 该方法仅仅是提供给 修改Team信息的时候 先抹去该Team的所有的信息 在执行插入的操作
    * */
    @Override
    public Integer deleteALlStaff(long id){
        return staffTeamMapper.delete(new EntityWrapper<TeamStaff>().eq(TeamStaff.TEAM_ID,id));
    }


    /**
     *      删除某个Team  ，当 Team下面有记录的时候，不允许删除
     * */
    @Override
    public Integer deleteTeam(long id){
        Integer staffTeams = staffTeamMapper.selectCount(new EntityWrapper<TeamStaff>().eq(TeamStaff.TEAM_ID,id));
        Integer taskFollowers = queryTeamDao.checkTeamWithFollow(id);
        if (staffTeams == 0 && taskFollowers == 0){
            return teamMapper.deleteById(id);
        }
        else{
            if(staffTeams!=0){
                throw new PowerBusinessException(PowerBusinessCode.PowerBusinessDeleteAssociatedOne,"team被staffTeams关联");
            }
            if(taskFollowers!=0){
                throw new PowerBusinessException(PowerBusinessCode.PowerBusinessDeleteAssociatedOne,"team被taskFollowers关联");
            }
            throw new RuntimeException("系统异常");
        }
    }

    @Override
    public TeamModel teamBuilding(long id) {
        Team team = teamMapper.selectById(id);
        JSONObject teamObj = JSONObject.parseObject(JSONObject.toJSONString(team));

        List<TeamStaff> staffTeams = staffTeamMapper.selectList(new EntityWrapper<TeamStaff>().eq(TeamStaff.TEAM_ID, team.getId()));
        if (staffTeams == null || staffTeams.size() == 0) {
            TeamModel model = JSONObject.parseObject(JSONObject.toJSONString(teamObj), TeamModel.class);
            return model;
        } else {
            List<NameModel> teamStaffs = new ArrayList<>();
            List<NameModel> teamLeaders = new ArrayList<>();
            for (TeamStaff staffTeam : staffTeams) {

                // 确定是 Staff  而不是 Leader 加入到 员工数组
                if (staffTeam.getIsLeader().compareTo(1) != 0) {
                    NameModel nameModel = new NameModel();
                    Staff staff = staffMapper.selectById(staffTeam.getStaffId());
                    if (staff != null) {
                        nameModel.setStaffId(staffTeam.getStaffId());
                        nameModel.setTeamId(id);
                        nameModel.setTeamName(team.getTeamName());
                        Profile profile = profileMapper.selectById(staff.getProfileId());
                        nameModel.setStaffName(profile.getName());
                        teamStaffs.add(nameModel);
                    }
                } else {
                    NameModel nameModel = new NameModel();
                    Staff staff = staffMapper.selectById(staffTeam.getStaffId());
                    if (staff != null) {
                        nameModel.setStaffId(staffTeam.getStaffId());
                        nameModel.setTeamId(id);
                        nameModel.setTeamName(team.getTeamName());
                        Profile profile = profileMapper.selectById(staff.getProfileId());
                        nameModel.setStaffName(profile.getName());
                        teamLeaders.add(nameModel);
                    }

                }

            }
            teamObj.put("teamStaffs", teamStaffs);
            teamObj.put("teamLeaders", teamLeaders);
            TeamModel model = JSONObject.parseObject(JSONObject.toJSONString(teamObj), TeamModel.class);
            return model;
        }
    }


    /**
     *  所有的团队以及团队下面的所有的员工
     * */
    @Override
    public List<AllTeamIncludeStaff> allTeamAndStaff(){
        List<AllTeamIncludeStaff> models = new ArrayList<>();
        List<Team> teams = teamMapper.selectList(new EntityWrapper<Team>());
        for (Team team : teams) {
            AllTeamIncludeStaff model = new AllTeamIncludeStaff();
            model.setTeamName(team.getTeamName());
            model.setTeamId(team.getId());
            List<TeamStaff> staffTeams = staffTeamMapper.selectList(new EntityWrapper<TeamStaff>().eq(TeamStaff.TEAM_ID,team.getId()));
            List<NameModel> staffs = new ArrayList<>();
            for(TeamStaff staffTeam : staffTeams){
                NameModel nameModel = new NameModel();
                Staff staff = staffMapper.selectById(staffTeam.getStaffId());
                Profile profile = profileMapper.selectById(staff.getProfileId());
                nameModel.setStaffName(profile.getName());
                nameModel.setStaffId(staff.getId());
                staffs.add(nameModel);
            }
            model.setStaffs(staffs);
            models.add(model);
        }
        return models;
    }

    /**
     *  登录用户作为Leader的所有的Team下面的所有的员工
     * */
    @Override
    public List<AllTeamIncludeStaff> allTeamLeaderAndStaff(long staffId){
        List<AllTeamIncludeStaff> models = new ArrayList<>();
        List<Team> teams = teamMapper.selectList(new EntityWrapper<Team>());
        for (Team team : teams) {
            if(!staffTeamService.checkIsTeamLeader(team.getId(),staffId)){

            }else{
                AllTeamIncludeStaff model = new AllTeamIncludeStaff();
                model.setTeamName(team.getTeamName());
                model.setTeamId(team.getId());
                List<TeamStaff> staffTeams = staffTeamMapper.selectList(new EntityWrapper<TeamStaff>().eq(TeamStaff.TEAM_ID,team.getId()));
                List<NameModel> staffs = new ArrayList<>();
                for(TeamStaff staffTeam : staffTeams){
                    NameModel nameModel = new NameModel();
                    Staff staff = staffMapper.selectById(staffTeam.getStaffId());
                    Profile profile = profileMapper.selectById(staff.getProfileId());
                    nameModel.setStaffName(staff==null?null:profile.getName());
                    nameModel.setStaffId(staff==null?null:staff.getId());
                    staffs.add(nameModel);
                }
                model.setStaffs(staffs);
                models.add(model);
            }
        }
        return models;
    }
}
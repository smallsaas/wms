package com.jfeat.am.module.organization.services.domain.service.impl;

import com.baomidou.mybatisplus.mapper.EntityWrapper;
import com.baomidou.mybatisplus.plugins.Page;
import com.google.common.collect.Lists;
import com.jfeat.am.module.organization.services.domain.dao.QueryStaffTeamDao;
import com.jfeat.am.module.organization.services.domain.model.NameModel;
import com.jfeat.am.module.organization.services.domain.service.QueryTeamStaffService;
import com.jfeat.am.module.organization.services.persistence.mapper.StaffMapper;
import com.jfeat.am.module.organization.services.persistence.mapper.TeamMapper;
import com.jfeat.am.module.organization.services.persistence.mapper.TeamStaffMapper;
import com.jfeat.am.module.organization.services.persistence.model.Staff;
import com.jfeat.am.module.organization.services.persistence.model.Team;
import com.jfeat.am.module.organization.services.persistence.model.TeamStaff;
import com.jfeat.am.module.profile.services.crud.service.ProfileService;
import com.jfeat.am.module.profile.services.persistence.dao.ProfileMapper;
import com.jfeat.am.module.profile.services.persistence.model.Profile;
import com.jfeat.am.power.base.PowerBusinessCode;
import com.jfeat.am.power.base.PowerBusinessException;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.ArrayList;
import java.util.List;

/**
 * <p>
 *  服务实现类
 * </p>
 *
 * @author admin
 * @since 2017-10-16
 */
@Service
public class QueryTeamStaffServiceImpl implements QueryTeamStaffService {

    @Resource
    QueryStaffTeamDao queryStaffTeamDao;
    @Resource
    TeamStaffMapper teamStaffMapper;
    @Resource
    StaffMapper staffMapper;
    @Resource
    TeamMapper teamMapper;
    @Resource
    ProfileMapper profileMapper;

    @Override
    public List<TeamStaff> findStaffTeamPage(Page<TeamStaff> page, TeamStaff staffTeam) {
        return queryStaffTeamDao.findStaffteamPage(page, staffTeam);
    }

    /**
     *工作组 Add Team 复选框中，根据团队名称查找用户，根据用户查找团队名称， 或者根据两者结合查找用户
     */
    @Override
    public List<NameModel> searchByStaffOrTeamName(Page<NameModel> page, String teamName , String staffName) {

        List<NameModel> models = new ArrayList<>();

        //  使用 staffName 模糊查询员工的数据
        if (teamName == null && staffName != null) {
            // 通过对 firstName 以及 lastName 模糊查询，查找出所有有关的员工的信息
            List<Profile> profiles = profileMapper.selectList(new EntityWrapper<Profile>().like("name",staffName));
            List<Staff> staffs = Lists.newArrayList();
            for (Profile profile:profiles){
                List<Staff> staffList = staffMapper.selectList(new EntityWrapper<Staff>().eq(Staff.PROFILE_ID,profile.getId()));
                if (staffList.size() > 0 && staffList != null){
                    staffs.addAll(staffList);
                }
            }
            if (staffs==null){
                return null;
            }
            for (Staff staff : staffs) {
                // 遍历员工 可以得到每个员工的 名字
                //根据员工与Team是多对多的关系，找出乎关联的StaffTeam的信息
                List<TeamStaff> staffTeams = teamStaffMapper.selectList(new EntityWrapper<TeamStaff>().eq(TeamStaff.STAFF_ID, staff.getId()));
                // 多个小组的时候，返回拼接的小组名字
                if(staffTeams ==null || staffTeams.size() ==0){
                    NameModel model = new NameModel();
                    Profile profile = profileMapper.selectById(staff.getProfileId());
                    model.setStaffName(profile.getName());
                    //  将得到的员工名字，set到需要的Model中
                    model.setStaffId(staff.getId());
                    models.add(model);
                }

                // 一个或者多个小组的时候，返回拼接的小组名字
                StringBuffer name = new StringBuffer();
                for (TeamStaff staffTeam : staffTeams) {
                    NameModel model = new NameModel();
                    // 遍历。 通过StaffTeam的 teamId 属性，找出对应的 Team
                    Team team = teamMapper.selectById(staffTeam.getTeamId());
                    name.append(team==null?null:team.getTeamName() + " ");
                    Profile profile = profileMapper.selectById(staff.getProfileId());
                    model.setStaffName(profile.getName());
                    //  将得到的员工名字，set到需要的Model中
                    model.setStaffId(staff.getId());
                    model.setTeamName(name.toString());
                    models.add(model);
                }
            }

        }

        else if (teamName != null && staffName == null) {
            // 根据 Team Name 找出该 Team 的信息
            Team team = new Team();
            team.setTeamName(teamName);
            team = teamMapper.selectOne(team);

            List<TeamStaff> staffTeams = teamStaffMapper.selectList(new EntityWrapper<TeamStaff>().eq(TeamStaff.TEAM_ID, team.getId()));

            if(staffTeams ==null || staffTeams.size() ==0){
                NameModel model = new NameModel();
                model.setTeamName(team.getTeamName());
                models.add(model);
            }

            for (TeamStaff staffTeam : staffTeams) {
                // 遍历。 通过StaffTeam的 teamId 属性，找出对应的 StaffTeam 的记录
                NameModel model = new NameModel();
                Staff staff = staffMapper.selectById(staffTeam.getStaffId());
                //  找出在StaffTeam 记录中的Staff
                Profile profile = profileMapper.selectById(staff.getProfileId());
                model.setStaffName(staff==null?null:profile.getName());
                //  将得到的员工名字，set到需要的Model中
                model.setStaffId(staff==null?null:staff.getId());
                model.setTeamName(team.getTeamName());
                models.add(model);
            }
        }
        // 两者同时不为空
        else if (teamName != null && staffName != null) {
            // 根据 Team Name 找出该 Team 的信息
            Team newTeam = new Team();
            newTeam.setTeamName(teamName);
            Team team = teamMapper.selectOne(newTeam);
            // 先找出唯一的Team
            // 通过对 firstName 以及 lastName 模糊查询，查找出所有有关的员工的信息
            List<Profile> profiles = profileMapper.selectList(new EntityWrapper<Profile>().like("name",staffName));
            List<Staff> staffs = Lists.newArrayList();
            for (Profile profile:profiles){
                List<Staff> staffList = staffMapper.selectList(new EntityWrapper<Staff>().eq(Staff.PROFILE_ID,profile.getId()));
                if (staffList.size() > 0 && staffList != null){
                    staffs.addAll(staffList);
                }
            }
            if (staffs==null||staffs.size()==0){
                return null;
            }
            for (Staff staff : staffs) {
                // 遍历。 通过StaffTeam的 teamId 属性，找出对应的 StaffTeam 的记录
                NameModel model = new NameModel();
                TeamStaff newStaffTeam = new TeamStaff();
                newStaffTeam.setStaffId(staff==null?null:staff.getId());
                newStaffTeam.setTeamId(team==null?null:team.getId());
                TeamStaff staffTeam = teamStaffMapper.selectOne(newStaffTeam);
                if (staffTeam == null){

                }else {
                    Profile profile = profileMapper.selectById(staff.getProfileId());
                    model.setStaffName(profile.getName());
                    //  将得到的员工名字，set到需要的Model中
                    model.setStaffId(staff==null?null:staff.getId());
                    model.setTeamName(team==null?null:team.getTeamName());
                    models.add(model);
                }
            }
        }
        else{


            List<Staff> staffs = staffMapper.selectList(new EntityWrapper<Staff>());
            if (staffs==null||staffs.size()==0){
                return null;
            }
            // like("lastName", staffName)
            for (Staff staff : staffs) {
                // 遍历。 通过StaffTeam的 teamId 属性，找出对应的 StaffTeam 的记录
                List<TeamStaff> staffTeams = teamStaffMapper.selectList(new EntityWrapper<TeamStaff>().eq(TeamStaff.STAFF_ID, staff.getId()));
                if (staffTeams == null || staffTeams.size() ==0){
                    NameModel model = new NameModel();
                    Profile profile = profileMapper.selectById(staff.getProfileId());
                    model.setStaffName(profile.getName());
                    //  将得到的员工名字，set到需要的Model中
                    model.setStaffId(staff.getId());
                    models.add(model);
                }
                if(staffTeams.size() > 1){
                    NameModel model = new NameModel();
                    StringBuffer name = new StringBuffer();
                    for (int i = 0 ; i < staffTeams.size() ; i++ ) {
                        // 遍历。 通过StaffTeam的 teamId 属性，找出对应的 Team
                        Team team = teamMapper.selectById(staffTeams.get(i).getTeamId());
                        if(team==null){
                            throw new PowerBusinessException(PowerBusinessCode.PowerBusinessCodeBase, "组员所在的Team不存在: " + staffTeams.get(i).getTeamId() + "请与开发人员联系");
                        }

                        name.append(team.getTeamName() + " ");
                    }
                    model.setTeamName(name.toString());
                    Profile profile = profileMapper.selectById(staff.getProfileId());
                    model.setStaffName(profile.getName());
                    //  将得到的员工名字，set到需要的Model中
                    model.setStaffId(staff.getId());
                    models.add(model);
                }
                if (staffTeams.size() == 1){
                    NameModel model = new NameModel();
                    Team team = teamMapper.selectById(staffTeams.get(0).getTeamId());
                    if(team==null){
                        throw new PowerBusinessException(PowerBusinessCode.PowerBusinessCodeBase, "组员所在的Team不存在: " + staffTeams.get(0).getTeamId() + "请与开发人员联系");
                    }
                    Profile profile = profileMapper.selectById(staff.getProfileId());
                    model.setStaffName(profile.getName());
                    //  将得到的员工名字，set到需要的Model中
                    model.setStaffId(staff.getId());
                    model.setTeamName(team.getTeamName());
                    models.add(model);
                }
            }
        }
            return models;
    }

    @Override
    public List<Team> getTeamsLedByStaff(long staffId) {
        return null;
    }

    @Override
    public List<Staff> getTeamLeaders(long teamId) {
        return null;
    }
}

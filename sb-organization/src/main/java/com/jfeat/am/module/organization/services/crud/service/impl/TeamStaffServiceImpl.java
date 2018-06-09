package com.jfeat.am.module.organization.services.crud.service.impl;

import com.baomidou.mybatisplus.mapper.BaseMapper;
import com.baomidou.mybatisplus.mapper.EntityWrapper;
import com.jfeat.am.common.crud.impl.CRUDServicePeerImpl;
import com.jfeat.am.module.organization.services.crud.service.TeamStaffService;
import com.jfeat.am.module.organization.services.patch.TeamPatchService;
import com.jfeat.am.module.organization.services.persistence.mapper.StaffMapper;
import com.jfeat.am.module.organization.services.persistence.mapper.TeamMapper;
import com.jfeat.am.module.organization.services.persistence.mapper.TeamStaffMapper;
import com.jfeat.am.module.organization.services.persistence.model.Staff;
import com.jfeat.am.module.organization.services.persistence.model.Team;
import com.jfeat.am.module.organization.services.persistence.model.TeamStaff;
import com.jfeat.am.module.profile.services.persistence.dao.ProfileMapper;
import com.jfeat.am.module.profile.services.persistence.model.Profile;
import com.jfeat.am.power.base.PowerBusinessException;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;

import static com.jfeat.am.power.base.PowerBusinessCode.PowerBusinessBadRequest;

/**
 * <p>
 * 团队 implementation
 * </p>
 *
 * @author Code Generator
 * @since 2017-11-20
 */
@Service
public class TeamStaffServiceImpl extends CRUDServicePeerImpl<Staff,Team,TeamStaff> implements TeamStaffService {
    @Resource
    private StaffMapper staffMapper;
    @Resource
    private TeamMapper teamMapper;
    @Resource
    private TeamStaffMapper staffTeamMapper;
    @Resource
    TeamPatchService teamPatchService;


    @Override
    protected BaseMapper<Staff> getMasterMapper() {
        return staffMapper;
    }

    @Override
    protected BaseMapper<Team> getMasterPeerMapper() {
        return teamMapper;
    }

    @Override
    protected BaseMapper<TeamStaff> getRelationMapper() {
        return staffTeamMapper;
    }

    @Override
    protected String[] relationMatches() {
        return new String[]{TeamStaff.STAFF_ID, TeamStaff.TEAM_ID};
    }

    @Override
    public Boolean checkIsTeamLeader(long teamId,long staffId){
        TeamStaff leader = getRelation(staffId, teamId);

        if(leader != null && leader.getIsLeader().compareTo(1) == 0){
            return true;
        }
        return false;
    }





    //指派多个个Staff为TeamLeader
    @Override
    public Integer setStaffToTeamLeader(long teamId, List<Long> ids){
        if(ids == null)
            throw new PowerBusinessException(PowerBusinessBadRequest,"POST参数ids缺失");

        // 影响条数
        Integer affected = ids.size();

        for(Long id : ids){

            //判断该员工是否在该组里面，如果没有，插入该员工到改组里面
            TeamStaff relation = getRelation(id, teamId);
            if(relation==null){
                TeamStaff staffTeam = new TeamStaff();
                staffTeam.setStaffId(id);
                staffTeam.setTeamId(teamId);
                staffTeam.setIsLeader(1);
                staffTeamMapper.insert(staffTeam);
            }
            else if((relation.getIsLeader()) != 1) {
                //teamPatchService.isHasTeamLeader(teamId);
                relation.setIsLeader(1);
                getRelationMapper().updateById(relation);
            }
            // 如果既在里面，又是Leader ，那么，成功条数 -1.
            else {
                affected = ids.size() -1;
            }
        }
        /// 如果员工不是Leader, 更新数据库
        return affected;
    }

    /**
    *   批量添加 Staff 到指定的 Team 中
    * */
    @Override
    public Integer addStaffToTeam(long teamId, List<Long> ids){

        // 影响条数
        Integer affected = ids.size();

        for(Long id : ids){

            //判断该员工是否在该组里面，如果没有，插入该员工到改组里面
            TeamStaff relation = getRelation(id, teamId);
            if(relation==null){
                TeamStaff staffTeam = new TeamStaff();
                staffTeam.setStaffId(id);
                staffTeam.setTeamId(teamId);
                staffTeam.setIsLeader(0);
                staffTeamMapper.insert(staffTeam);
            }
            // 如果既在里面，又是Leader ，那么，成功条数 -1.
            else {
                affected = ids.size() -1;
            }
        }

        /// 如果员工不是Leader, 更新数据库
        return affected;
    }


    /**
    *   删除 批量删除 Staff 可以 复用于  批量删除 Leader  并且 从该组中删除
    * */
    @Override
    public Integer deleteTeamStaffLeader(long teamId, List<Long> ids){

        // 影响条数
        Integer affected = ids.size();

        for(Long id : ids){

            //判断该员工是否在该组里面，如果没有，插入该员工到改组里面
            TeamStaff relation = getRelation(id, teamId);
            if(relation==null){
                // 如果 查找不到 数据， 则数量 -1 ， 即理解为提交的数据 出错
                affected = ids.size() -1;
            }

            else {
                staffTeamMapper.deleteById(relation.getId());
            }
        }

        /// 如果员工不是Leader, 更新数据库
        return affected;
    }

    /**
     *      执行 删除操作的时候  会把所有的关于该 Team 的记录全部删除
     */
    @Override
    public Integer deleteTeam(long id){
        List<TeamStaff> staffTeams = staffTeamMapper.selectList(new EntityWrapper<TeamStaff>().eq(TeamStaff.TEAM_ID,id));
        for(int i = 0 ; i < staffTeams.size(); i++){

            // 删除所有的 关于该 Team 的记录
            staffTeamMapper.deleteById(staffTeams.get(i).getId());
        }
        return teamMapper.deleteById(id);
    }
}



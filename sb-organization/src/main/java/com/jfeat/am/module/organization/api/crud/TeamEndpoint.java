package com.jfeat.am.module.organization.api.crud;

import com.baomidou.mybatisplus.plugins.Page;
import com.jfeat.am.common.constant.tips.SuccessTip;
import com.jfeat.am.common.constant.tips.Tip;
import com.jfeat.am.common.controller.BaseController;
import com.jfeat.am.module.organization.services.crud.service.TeamService;
import com.jfeat.am.module.organization.services.domain.model.TeamAndStaffModel;
import com.jfeat.am.module.organization.services.domain.service.QueryTeamService;
import com.jfeat.am.module.organization.services.persistence.model.Team;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;

/**
 * <p>
 * 团队 api
 * </p>
 *
 * @author Code Generator
 * @since 2017-11-20
 */
@Api(value = "团队信息")
@RestController
@RequestMapping("/api/team/teams")
public class TeamEndpoint extends BaseController {

    @Resource
    TeamService teamService;

    @Resource
    QueryTeamService queryTeamService;



    @ApiOperation(value = "新建Team",response = Team.class)
    @PostMapping
    public Tip createTeam(@RequestBody TeamAndStaffModel entity) {
        return SuccessTip.create(teamService.createTeamIncludeStaff(entity));
    }

    @ApiOperation(value = "获取Team",response = Team.class)
    @GetMapping("/{id}")
    public Tip getTeam(@PathVariable Long id) {
        return SuccessTip.create(teamService.teamBuilding(id));
    }

    @ApiOperation(value = "修改Team的信息",response = Team.class)
    @PutMapping("/{id}")
    public Tip updateTeam(@PathVariable Long id, @RequestBody TeamAndStaffModel entity) {
        return SuccessTip.create(teamService.updateTeamIncludeStaff(id,entity));
    }


    @ApiOperation(value = "删除某个Team",response = Team.class)
    @DeleteMapping("/{id}")
    public Tip deleteTeam(@PathVariable Long id) {
        //TODO  删除关联事件 以及其他的关联信息
        Integer result = teamService.deleteTeam(id);
        return SuccessTip.create(result);
    }

    @ApiOperation(value = "根据名称或者简介去查找Team",response = Team.class)
    @GetMapping
    public Tip queryTeams(Page page,
                          @RequestParam(name = "pageNum", required = false, defaultValue = "1") Integer pageNum,
                          @RequestParam(name = "pageSize", required = false, defaultValue = "10") Integer pageSize,
                          @RequestParam(name = "name", required = false) String name,
                          @RequestParam(name = "desc", required = false) String desc) {
        page.setCurrent(pageNum);
        page.setSize(pageSize);
        Team team = new Team();
        team.setTeamName(name);
        team.setTeamDesc(desc);
        page.setRecords(queryTeamService.findTeamPage(page, team));

        return SuccessTip.create(page);
    }


}

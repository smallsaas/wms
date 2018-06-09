package com.jfeat.am.module.organization.services.domain.model;


import com.jfeat.am.module.organization.services.persistence.model.Staff;
import com.jfeat.am.module.organization.services.persistence.model.Team;
import com.jfeat.am.module.organization.services.persistence.model.TeamStaff;

/**
 * Created by Code Generator on 2017-11-20
 */
public class TeamStaffModel extends TeamStaff {
    Staff staff;
    Team team;


    public Staff getStaff() {
        return staff;
    }

    public void setStaff(Staff staff) {
        this.staff = staff;
    }

    public Team getTeam() {
        return team;
    }

    public void setTeam(Team team) {
        this.team = team;
    }
}

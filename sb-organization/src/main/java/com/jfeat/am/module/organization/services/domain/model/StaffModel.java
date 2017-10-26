package com.jfeat.am.module.organization.services.domain.model;

import com.jfeat.am.module.organization.services.persistence.model.Position;
import com.jfeat.am.module.organization.services.persistence.model.Staff;
import com.jfeat.am.module.profile.services.persistence.model.Profile;

/**
 * Created by Code Generator on 2017-10-26
 */
public class StaffModel extends Staff{

    private Position position;

    private Profile profile;


    public Position getPosition() {
        return position;
    }

    public void setPosition(Position position) {
        this.position = position;
    }

    public Profile getProfile() {
        return profile;
    }

    public void setProfile(Profile profile) {
        this.profile = profile;
    }
}

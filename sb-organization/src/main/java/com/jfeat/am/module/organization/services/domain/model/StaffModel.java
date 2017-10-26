package com.jfeat.am.module.organization.services.domain.model;

import com.jfeat.am.module.organization.services.persistence.model.Position;
import com.jfeat.am.module.organization.services.persistence.model.Staff;
/**
 * Created by Code Generator on 2017-10-26
 */
public class StaffModel extends Staff{
    private Position position;

    public Position getPosition() {
        return position;
    }

    public void setPosition(Position position) {
        this.position = position;
    }
}

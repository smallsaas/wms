package com.jfeat.am.module.organization.services.crud.service;

import com.jfeat.am.common.crud.CRUDServiceModel;
import com.jfeat.am.common.crud.CRUDServiceOnly;
import com.jfeat.am.module.organization.services.domain.model.StaffModel;
import com.jfeat.am.module.organization.services.persistence.model.Staff;

import java.util.List;


/**
 * <p>
 *  服务类
 * </p>
 *
 * @author admin
 * @since 2017-10-10
 */

public interface StaffService extends CRUDServiceOnly<Staff>, CRUDServiceModel<Staff, StaffModel> {
    List<Staff> getStaffsOfDepartment(Long departmentId);
}

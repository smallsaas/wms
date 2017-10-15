package com.jfeat.am.module.organiaztion.services.crud.service;

import com.jfeat.am.common.crud.CRUDServiceOnly;
import com.jfeat.am.module.organiaztion.services.crud.persistence.model.Staff;

import java.util.List;


/**
 * <p>
 *  服务类
 * </p>
 *
 * @author admin
 * @since 2017-10-10
 */

public interface StaffService extends CRUDServiceOnly {
    List<Staff> getStaffsOfDepartment(Long departmentId);
}

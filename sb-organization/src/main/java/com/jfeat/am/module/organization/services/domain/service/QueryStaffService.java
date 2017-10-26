package com.jfeat.am.module.organization.services.domain.service;

import com.jfeat.am.module.organization.services.persistence.model.Staff;

import java.util.List;

/**
 * Created by vincent on 2017/10/19.
 */
public interface QueryStaffService {
    List<Staff> findStaffs(int pageNum, int pageSize, Staff staff );
}
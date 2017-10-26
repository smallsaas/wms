package com.jfeat.am.module.organization.services.domain.service;

import com.baomidou.mybatisplus.plugins.Page;
import com.jfeat.am.module.organization.services.domain.model.StaffItem;
import com.jfeat.am.module.organization.services.domain.model.StaffModel;
import com.jfeat.am.module.organization.services.persistence.model.Staff;

import java.util.List;

/**
 * Created by vincent on 2017/10/19.
 */
public interface QueryStaffService {

    List<StaffItem> findStaffs(StaffItem staffItem);
}


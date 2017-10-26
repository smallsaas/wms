package com.jfeat.am.module.organization.services.domain.service.impl;

import com.jfeat.am.module.organization.services.domain.dao.QueryStaffDao;
import com.jfeat.am.module.organization.services.domain.model.StaffItem;
import com.jfeat.am.module.organization.services.domain.service.QueryStaffService;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
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
public class QueryStaffServiceImpl implements QueryStaffService {

    @Resource
    QueryStaffDao queryStaffDao;

    @Override
    public List<StaffItem> findStaffs(StaffItem staffItem) {
        return queryStaffDao.findStaffs(staffItem);
    }
}

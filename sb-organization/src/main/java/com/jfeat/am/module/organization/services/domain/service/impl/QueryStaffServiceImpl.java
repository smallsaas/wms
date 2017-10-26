package com.jfeat.am.module.organization.services.domain.service.impl;

import com.baomidou.mybatisplus.plugins.Page;
import com.jfeat.am.module.organization.services.domain.dao.QueryStaffDao;
import com.jfeat.am.module.organization.services.domain.service.QueryStaffService;
import com.jfeat.am.module.organization.services.persistence.model.Staff;
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
    public List<Staff> findStaffs(int pageNum, int pageSize, Staff staff) {
        Page page = new Page();
        page.setCurrent(pageNum);
        page.setSize(pageSize);

        page.setRecords(queryStaffDao.findStaffs(page, staff));

        return page.getRecords();
    }
}

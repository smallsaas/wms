package com.jfeat.am.module.organization.services.domain.service.impl;

import com.baomidou.mybatisplus.plugins.Page;
import com.jfeat.am.module.organization.services.domain.dao.QueryDepartmentDao;
import com.jfeat.am.module.organization.services.domain.service.QueryDepartmentService;
import com.jfeat.am.module.organization.services.persistence.model.Department;
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
public class QueryDepartmentServiceImpl implements QueryDepartmentService {

    @Resource
    QueryDepartmentDao queryDepartmentDao;

    @Override
    public List<Department> findDepartmentPage(Department department) {
        return queryDepartmentDao.findDepartmentPage(department);
    }
}

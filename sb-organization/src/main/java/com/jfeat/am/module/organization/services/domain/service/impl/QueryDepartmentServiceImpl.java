package com.jfeat.am.module.organization.services.domain.service.impl;

import com.baomidou.mybatisplus.plugins.Page;
import com.jfeat.am.module.organization.services.domain.dao.QueryDepartmentDao;
import com.jfeat.am.module.organization.services.domain.model.DepartmentItem;
import com.jfeat.am.module.organization.services.domain.service.QueryDepartmentService;
import com.jfeat.am.module.organization.services.persistence.model.Department;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;
import java.util.Map;

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
    public List<DepartmentItem> findDepartmentPage(Department department) {
        return queryDepartmentDao.findDepartmentPage(department);
    }

    @Override
    public List<Map<String,Object>> showDepartmentDetail(Page<Map<String,Object>> page,Long id, String isManager) {
        return queryDepartmentDao.showDepartmentDetail(page,id,isManager);
    }
}

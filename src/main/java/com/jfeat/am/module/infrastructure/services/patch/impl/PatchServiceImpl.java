package com.jfeat.am.module.infrastructure.services.patch.impl;


import com.baomidou.mybatisplus.mapper.EntityWrapper;
import com.baomidou.mybatisplus.plugins.Page;
import com.jfeat.am.core.support.BeanKit;
import com.jfeat.am.module.infrastructure.services.crud.persistence.model.Department;
import com.jfeat.am.module.infrastructure.services.crud.persistence.model.OperationLog;
import com.jfeat.am.module.infrastructure.services.crud.persistence.model.Staff;
import com.jfeat.am.module.infrastructure.services.domain.dao.QueryDepartmentDao;
import com.jfeat.am.module.infrastructure.services.domain.dao.QueryStaffDao;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import com.jfeat.am.module.infrastructure.services.patch.PatchService;
import javax.annotation.Resource;
import java.util.List;
import java.util.Map;
import java.util.Objects;

/**
 * Created by Silent-Y on 2017/9/14.
 */
@Service
public class PatchServiceImpl implements PatchService {

    @Resource
    QueryDepartmentDao queryDepartmentDao;
    @Resource
    QueryStaffDao queryStaffDao;

    @Override
    public Map findDepartmentWithStaff(Long id) {
        Department department = queryDepartmentDao.selectById(id);
        List<Staff> staffs = queryStaffDao.selectList(new EntityWrapper<Staff>().eq(Staff.DEPT_ID, id));
        Map departmentMap = BeanKit.beanToMap(department);
        departmentMap.put("staffs",staffs);

        return departmentMap;
    }
}

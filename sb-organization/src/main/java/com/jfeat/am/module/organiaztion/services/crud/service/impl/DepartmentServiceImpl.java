package com.jfeat.am.module.organiaztion.services.crud.service.impl;
        
import com.baomidou.mybatisplus.mapper.BaseMapper;
import com.jfeat.am.common.crud.impl.CRUDServiceGroupImpl;
import com.jfeat.am.module.organiaztion.services.persistence.dao.DepartmentMapper;
import com.jfeat.am.module.organiaztion.services.crud.service.DepartmentService;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;

/**
 * <p>
 *  服务实现类
 * </p>
 *
 * @author admin
 * @since 2017-10-11
 */
@Service
public class DepartmentServiceImpl extends CRUDServiceGroupImpl implements DepartmentService {

    @Resource
    private DepartmentMapper departmentMapper;

    @Override
    protected BaseMapper getGroupMapper() {
        return departmentMapper;
    }
}



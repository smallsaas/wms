package com.jfeat.am.module.infrastructure.services.crud.service.impl;
        
import com.baomidou.mybatisplus.mapper.BaseMapper;
import com.jfeat.am.common.crud.impl.CRUDServiceGroupImpl;
import com.jfeat.am.module.infrastructure.services.crud.persistence.model.Deptment;
import com.jfeat.am.module.infrastructure.services.crud.persistence.dao.DeptmentMapper;
import com.jfeat.am.module.infrastructure.services.crud.service.DeptmentService;
import com.jfeat.am.common.crud.impl.CRUDServiceOnlyImpl;
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
public class DeptmentServiceImpl extends CRUDServiceGroupImpl implements DeptmentService {


    @Resource
    private DeptmentMapper deptmentMapper;

    @Override
    protected BaseMapper getGroupMapper() {
        return deptmentMapper;
    }
}



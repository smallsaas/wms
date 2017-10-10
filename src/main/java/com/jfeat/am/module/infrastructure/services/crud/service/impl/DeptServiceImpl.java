package com.jfeat.am.module.infrastructure.services.crud.service.impl;
        
import com.baomidou.mybatisplus.mapper.BaseMapper;
import com.jfeat.am.common.crud.impl.CRUDServiceGroupImpl;
import com.jfeat.am.module.infrastructure.services.crud.persistence.model.Dept;
import com.jfeat.am.module.infrastructure.services.crud.persistence.dao.DeptMapper;
import com.jfeat.am.module.infrastructure.services.crud.service.DeptService;
import com.jfeat.am.common.crud.impl.CRUDServiceOnlyImpl;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;

/**
 * <p>
 *  服务实现类
 * </p>
 *
 * @author admin
 * @since 2017-10-10
 */
@Service
public class DeptServiceImpl extends CRUDServiceGroupImpl implements DeptService {


    @Resource
    private DeptMapper deptMapper;

    @Override
    protected BaseMapper getGroupMapper() {
        return null;
    }
}



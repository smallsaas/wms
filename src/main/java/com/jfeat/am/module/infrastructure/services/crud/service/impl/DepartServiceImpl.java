package com.jfeat.am.module.infrastructure.services.crud.service.impl;
        
import com.baomidou.mybatisplus.mapper.BaseMapper;
import com.jfeat.am.common.crud.impl.CRUDServiceGroupImpl;
import com.jfeat.am.module.infrastructure.services.crud.persistence.dao.DepartMapper;
import com.jfeat.am.module.infrastructure.services.crud.service.DepartService;
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
public class DepartServiceImpl extends CRUDServiceGroupImpl implements DepartService {

    @Resource
    private DepartMapper departMapper;

    @Override
    protected BaseMapper getGroupMapper() {
        return departMapper;
    }
}



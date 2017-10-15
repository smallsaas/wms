package com.jfeat.am.module.infrastructure.services.crud.service.impl;
        
import com.baomidou.mybatisplus.mapper.BaseMapper;
import com.jfeat.am.module.infrastructure.services.crud.persistence.model.TDepartment;
import com.jfeat.am.module.infrastructure.services.crud.persistence.dao.TDepartmentMapper;
import com.jfeat.am.module.infrastructure.services.crud.service.TDepartmentService;
import com.jfeat.am.common.crud.impl.CRUDServiceOnlyImpl;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;

/**
 * <p>
 *  服务实现类
 * </p>
 *
 * @author admin
 * @since 2017-10-15
 */
@Deprecated
@Service
public class TDepartmentServiceImpl  implements TDepartmentService {


    @Resource
    private TDepartmentMapper tDepartmentMapper;

    @Override
    protected BaseMapper<TDepartment> getMasterMapper() {
        return tDepartmentMapper;
    }
}



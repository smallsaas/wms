package com.jfeat.am.module.infrastructure.services.crud.service.impl;
        
import com.baomidou.mybatisplus.mapper.BaseMapper;
import com.jfeat.am.module.infrastructure.services.crud.persistence.model.TStaff;
import com.jfeat.am.module.infrastructure.services.crud.persistence.dao.TStaffMapper;
import com.jfeat.am.module.infrastructure.services.crud.service.TStaffService;
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
public class TStaffServiceImpl  implements TStaffService {


    @Resource
    private TStaffMapper tStaffMapper;

    @Override
    protected BaseMapper<TStaff> getMasterMapper() {
        return tStaffMapper;
    }
}



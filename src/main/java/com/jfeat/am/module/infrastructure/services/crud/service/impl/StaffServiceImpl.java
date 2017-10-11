package com.jfeat.am.module.infrastructure.services.crud.service.impl;
        
import com.baomidou.mybatisplus.mapper.BaseMapper;
import com.jfeat.am.common.crud.impl.CRUDServiceModelImpl;
import com.jfeat.am.module.infrastructure.services.crud.persistence.model.Staff;
import com.jfeat.am.module.infrastructure.services.crud.persistence.dao.StaffMapper;
import com.jfeat.am.module.infrastructure.services.crud.service.StaffService;
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
public class StaffServiceImpl extends CRUDServiceOnlyImpl implements StaffService {


    @Resource
    private StaffMapper staffMapper;

    protected BaseMapper<Staff> getMasterMapper() {
        return staffMapper;
    }


}



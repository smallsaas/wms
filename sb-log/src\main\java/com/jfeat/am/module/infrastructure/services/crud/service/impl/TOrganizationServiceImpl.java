package com.jfeat.am.module.infrastructure.services.crud.service.impl;
        
import com.baomidou.mybatisplus.mapper.BaseMapper;
import com.jfeat.am.module.infrastructure.services.crud.persistence.model.TOrganization;
import com.jfeat.am.module.infrastructure.services.crud.persistence.dao.TOrganizationMapper;
import com.jfeat.am.module.infrastructure.services.crud.service.TOrganizationService;
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
public class TOrganizationServiceImpl  implements TOrganizationService {


    @Resource
    private TOrganizationMapper tOrganizationMapper;

    @Override
    protected BaseMapper<TOrganization> getMasterMapper() {
        return tOrganizationMapper;
    }
}



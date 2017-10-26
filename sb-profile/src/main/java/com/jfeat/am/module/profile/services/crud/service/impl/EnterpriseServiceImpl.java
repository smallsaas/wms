package com.jfeat.am.module.profile.services.crud.service.impl;
            
import com.baomidou.mybatisplus.mapper.BaseMapper;
import com.jfeat.am.module.profile.services.persistence.dao.EnterpriseMapper;
import com.jfeat.am.module.profile.services.persistence.model.Enterprise;
import com.jfeat.am.module.profile.services.crud.service.EnterpriseService;
import com.jfeat.am.common.crud.impl.CRUDServiceOnlyImpl;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;

/**
 * <p>
 *  implementation
 * </p>
 *
 * @author Code Generator
 * @since 2017-10-19
 */
@Service
public class EnterpriseServiceImpl extends CRUDServiceOnlyImpl<Enterprise> implements EnterpriseService {


    @Resource
    private EnterpriseMapper enterpriseMapper;

    @Override
    protected BaseMapper<Enterprise> getMasterMapper() {
        return enterpriseMapper;
    }
}



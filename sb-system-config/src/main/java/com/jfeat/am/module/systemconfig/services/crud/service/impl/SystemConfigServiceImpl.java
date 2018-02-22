package com.jfeat.am.module.systemconfig.services.crud.service.impl;
            
import com.baomidou.mybatisplus.mapper.BaseMapper;
import com.jfeat.am.module.systemconfig.services.persistence.model.SystemConfig;
import com.jfeat.am.module.systemconfig.services.persistence.dao.SystemConfigMapper;
import com.jfeat.am.module.systemconfig.services.crud.service.SystemConfigService;
import com.jfeat.am.common.crud.impl.CRUDServiceOnlyImpl;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import com.jfeat.am.common.crud.impl.CRUDServiceOnlyImpl;
/**
 * <p>
 *  implementation
 * </p>
 *
 * @author Code Generator
 * @since 2018-02-22
 */
@Service
public class SystemConfigServiceImpl  extends CRUDServiceOnlyImpl<SystemConfig> implements SystemConfigService {


    @Resource
    private SystemConfigMapper systemConfigMapper;

    @Override
    protected BaseMapper<SystemConfig> getMasterMapper() {
        return systemConfigMapper;
    }

    @Override
    public SystemConfig getSystemConfigByKey(String key) {
        SystemConfig systemConfig = new SystemConfig();
        systemConfig.setDataKey(key);
        return getMasterMapper().selectOne(systemConfig);
    }
}



package com.jfeat.am.module.systemconfig.services.domain.service.impl;

import com.baomidou.mybatisplus.plugins.Page;
import com.jfeat.am.module.systemconfig.services.domain.dao.QuerySystemConfigDao;
import com.jfeat.am.module.systemconfig.services.domain.service.QuerySystemConfigService;
import com.jfeat.am.module.systemconfig.services.persistence.model.SystemConfig;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;

/**
 * <p>
 *  服务实现类
 * </p>
 *
 * @author admin
 * @since 2017-10-16
 */
@Service
public class QuerySystemConfigServiceImpl implements QuerySystemConfigService {

    @Resource
    QuerySystemConfigDao querySystemConfigDao;

    @Override
    public List<SystemConfig> findSystemConfigPage(Page<SystemConfig> page, SystemConfig systemconfig,String orderBy) {
        return querySystemConfigDao.findSystemConfigPage(page, systemconfig, orderBy);
    }
}

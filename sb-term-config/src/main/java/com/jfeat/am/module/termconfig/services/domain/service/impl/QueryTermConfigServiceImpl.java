package com.jfeat.am.module.termconfig.services.domain.service.impl;

import com.baomidou.mybatisplus.plugins.Page;
import com.jfeat.am.module.termconfig.services.domain.dao.QueryTermConfigDao;
import com.jfeat.am.module.termconfig.services.domain.service.QueryTermConfigService;
import com.jfeat.am.module.termconfig.services.persistence.model.TermConfig;
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
public class QueryTermConfigServiceImpl implements QueryTermConfigService {

    @Resource
    QueryTermConfigDao queryTermConfigDao;

    @Override
    public List<TermConfig> findTermConfigPage(Page<TermConfig> page, TermConfig termconfig) {
        return queryTermConfigDao.findTermConfigPage(page, termconfig);
    }
}

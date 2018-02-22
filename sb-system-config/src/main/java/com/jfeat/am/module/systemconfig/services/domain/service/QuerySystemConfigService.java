package com.jfeat.am.module.systemconfig.services.domain.service;

import com.baomidou.mybatisplus.plugins.Page;
import com.jfeat.am.module.systemconfig.services.persistence.model.SystemConfig;

import java.util.List;

/**
 * Created by vincent on 2017/10/19.
 */
public interface QuerySystemConfigService {
    List<SystemConfig> findSystemConfigPage(Page<SystemConfig> page, SystemConfig systemconfig ,String orderBy );
}
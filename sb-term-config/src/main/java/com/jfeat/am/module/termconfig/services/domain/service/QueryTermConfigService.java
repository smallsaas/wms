package com.jfeat.am.module.termconfig.services.domain.service;

import com.baomidou.mybatisplus.plugins.Page;
import com.jfeat.am.module.termconfig.services.persistence.model.TermConfig;

import java.util.List;

/**
 * Created by vincent on 2017/10/19.
 */
public interface QueryTermConfigService {
    List<TermConfig> findTermConfigPage(Page<TermConfig> page, TermConfig termconfig );
}
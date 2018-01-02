package com.jfeat.am.module.pcd.services.domain.service;

import com.baomidou.mybatisplus.plugins.Page;
import com.jfeat.am.module.pcd.services.persistence.model.Pcd;

import java.util.List;
import java.util.Map;

/**
 * Created by vincent on 2017/10/19.
 */
public interface QueryPcdService {
    public List<Map<String, Object>> findAllToMap();
    public List<Map<String, Object>> findAllGrouping();
}
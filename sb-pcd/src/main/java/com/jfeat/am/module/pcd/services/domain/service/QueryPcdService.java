package com.jfeat.am.module.pcd.services.domain.service;

import com.jfeat.am.module.pcd.services.persistence.model.Pcd;

import java.util.List;
import java.util.Map;

/**
 * Created by vincent on 2017/10/19.
 */
public interface QueryPcdService {
    List<Map<String, Object>> findAllToMap();
    List<Map<String, Object>> findAllGrouping();
    List<Pcd> queryPcdByName(String name);
    List<Pcd> queryPcdByPid(Long pid);
}
package com.jfeat.am.modular.system.service;

import com.jfeat.am.common.persistence.model.Pcd;

import java.util.List;
import java.util.Map;

/**
 * Created by jackyhuang on 2017/8/15.
 */
public interface PcdService {
    public List<Pcd> findAll();
    public List<Map<String, Object>> findAllToMap();
    public List<Map<String, Object>> findAllGrouping();
}

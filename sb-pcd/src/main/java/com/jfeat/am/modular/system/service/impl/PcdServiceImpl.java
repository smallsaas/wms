package com.jfeat.am.modular.system.service.impl;

import com.baomidou.mybatisplus.mapper.EntityWrapper;
import com.baomidou.mybatisplus.service.impl.ServiceImpl;
import com.jfeat.am.common.persistence.dao.PcdMapper;
import com.jfeat.am.common.persistence.model.Pcd;
import com.jfeat.am.modular.system.service.PcdService;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

/**
 * Created by jackyhuang on 2017/8/15.
 */
@Service
public class PcdServiceImpl extends ServiceImpl<PcdMapper, Pcd> implements PcdService {

    @Override
    public List<Pcd> findAll() {
        return selectList(new EntityWrapper<Pcd>());
    }

    @Override
    public List<Map<String, Object>> findAllToMap() {
        return selectMaps(new EntityWrapper<>());
    }

    @Override
    public List<Map<String, Object>> findAllGrouping() {
        List<Map<String, Object>> list = findAllToMap();
        List<Map<String, Object>> provinces = list.stream().filter(pcd -> pcd.get("pid") == null).collect(Collectors.toList());
        provinces.forEach(p -> {
            List<Map<String, Object>> cities = list.stream().filter(pcd -> p.get("id").equals(pcd.get("pid"))).collect(Collectors.toList());
            cities.forEach(c -> {
                List<Map<String, Object>> districts = list.stream().filter(pcd -> c.get("id").equals(pcd.get("pid"))).collect(Collectors.toList());
                c.put("children", districts);
            });
            p.put("children", cities);
        });
        return provinces;
    }
}

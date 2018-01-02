package com.jfeat.am.module.pcd.services.domain.service.impl;

import com.baomidou.mybatisplus.mapper.EntityWrapper;
import com.jfeat.am.module.pcd.services.domain.service.QueryPcdService;
import com.jfeat.am.module.pcd.services.persistence.dao.PcdMapper;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

/**
 * <p>
 *  服务实现类
 * </p>
 *
 * @author admin
 * @since 2017-10-16
 */
@Service
public class QueryPcdServiceImpl implements QueryPcdService {

    @Resource
    PcdMapper pcdMapper;

    @Override
    public List<Map<String, Object>> findAllToMap() {
        return pcdMapper.selectMaps(new EntityWrapper<>());
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

package com.jfeat.am.module.statement.services.service.impl;

import com.google.common.collect.Maps;
import com.jfeat.am.core.support.DateTimeKit;
import com.jfeat.am.module.statement.services.dao.TableColumnStatusNumDao;
import com.jfeat.am.module.statement.services.service.TableColumnStatusNumService;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.Date;
import java.util.Map;

/**
 * Created by Silent-Y on 2017/11/6.
 */
@Service
public class TableColumnStatusNumServiceImpl implements TableColumnStatusNumService {

    @Resource
    private TableColumnStatusNumDao tableColumnStatusNumDao;


    @Override
    public Map<String, Object> getColumnStatusNum(String table, String column,String field, String columnValue, String timeName, String startTime, String endTime) {
        Map<String,Object> map = Maps.newHashMap();
        Map<String,Integer> columnStatusNum = tableColumnStatusNumDao.getColumnStatusNum(table, column, columnValue, timeName, startTime, endTime);
        map.put("timestamp",DateTimeKit.formatDateTime(new Date()));
        map.put("data",columnStatusNum);
        map.put("field",field);
        return map;
    }

}

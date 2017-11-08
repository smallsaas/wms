package com.jfeat.am.module.statement.services.service.impl;

import com.google.common.collect.Maps;
import com.jfeat.am.core.support.DateTimeKit;
import com.jfeat.am.module.statement.services.dao.TableColumnRatesDao;
import com.jfeat.am.module.statement.services.service.TableColumnRatesService;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.Date;
import java.util.List;
import java.util.Map;

/**
 * Created by Silent-Y on 2017/11/6.
 */
@Service
public class TableColumnRatesServiceImpl implements TableColumnRatesService {

    @Resource
    private TableColumnRatesDao tableColumnRatesDao;

    /*@Override
    public List<String> queryValueOfColumn(String table, String column) {
        return tableColumnRatesDao.queryValueOfColumn(table,column);
    }*/

    @Override
    public Map<String, Object> getColumnRates(String table, String column) {
        Map<String,Object> map = Maps.newHashMap();
        List<String> strings = tableColumnRatesDao.queryValueOfColumn(table, column);
        List<Map<String,Object>> maps = tableColumnRatesDao.getColumnRates(table, column, strings);
        map.put("time",DateTimeKit.formatDateTime(new Date()));
        map.put("data",maps);
        return map;
    }
}

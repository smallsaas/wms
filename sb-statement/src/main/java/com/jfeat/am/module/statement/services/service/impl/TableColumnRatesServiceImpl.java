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

    @Override
    public Map<String,Object> queryEquipmentCountByStatus(String tableName,String columnName,List<String> columnContents,String type) {
        Map<String,Object> result = Maps.newHashMap();
        Map<String,Object> map = tableColumnRatesDao.queryEquipmentCountByStatus(tableName, columnName, columnContents);
        result.put("data",map);
        result.put("timestamp", DateTimeKit.formatDateTime(new Date()));
        result.put("type",type);
        return result;
    }

    @Override
    public List<Map<String, Object>> queryTableColumnRates(String tableName, String columnName) {
        return tableColumnRatesDao.queryTableColumnRates(tableName,columnName);
    }
}

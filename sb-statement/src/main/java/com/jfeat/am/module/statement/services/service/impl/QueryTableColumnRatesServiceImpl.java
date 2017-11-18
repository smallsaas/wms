package com.jfeat.am.module.statement.services.service.impl;

import com.alibaba.fastjson.JSONObject;
import com.jfeat.am.module.statement.services.service.QueryTableColumnRatesService;
import org.springframework.stereotype.Service;

/**
 * Created by Silent-Y on 2017/11/6.
 */
@Service
public class QueryTableColumnRatesServiceImpl implements QueryTableColumnRatesService {

    //@Resource
    //private TableColumnRatesService tableColumnRatesService;

    @Override
    public JSONObject queryTableColumnRates(String tableName, String columnName) {
        //List<String> items = tableColumnRatesService.queryTableColumnDistinct(tableName, columnName);

        return null;
    }

    //@Resource
    //private TableColumnRatesDao tableColumnRatesDao;

   /* @Override
    public Map<String,Object> queryEquipmentCountByStatus(String tableName,String columnName,List<String> columnContents,String type) {
        Map<String,Object> result = Maps.newHashMap();
        Map<String,Object> map = tableColumnRatesDao.queryEquipmentCountByStatus(tableName, columnName, columnContents);
        result.put("data",map);
        result.put("timestamp", DateTimeKit.formatDateTime(new Date()));
        result.put("type",type);
        return result;
    }*/

    /*@Override
    public JSONObject queryTableColumnRates(String tableName, String columnName) {
        return tableColumnRatesDao.queryTableColumnRates(tableName,columnName);
    }*/
}

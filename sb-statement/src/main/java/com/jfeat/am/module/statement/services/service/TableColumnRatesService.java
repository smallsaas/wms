package com.jfeat.am.module.statement.services.service;

import java.util.List;
import java.util.Map;

/**
 * Created by Silent-Y on 2017/11/6.
 */
public interface TableColumnRatesService {

    Map<String,Object> queryEquipmentCountByStatus(String tableName,String columnName,List<String> columnContents,String type);

    List<Map<String,Object>> queryTableColumnRates(String tableName,String columnName);
}

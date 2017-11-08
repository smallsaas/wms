package com.jfeat.am.module.statement.services.service;

import java.util.List;
import java.util.Map;

/**
 * Created by Silent-Y on 2017/11/6.
 */
public interface TableColumnRatesService {

    /*List<String> queryValueOfColumn(String table,String column);*/

    Map<String,Object> getColumnRates(String table,String column);
}

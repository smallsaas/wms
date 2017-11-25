package com.jfeat.am.module.statement.services.service;

import java.util.Map;

/**
 * Created by Silent-Y on 2017/11/6.
 */
public interface TableColumnStatusNumService {
    public Map<String,Object> getColumnStatusNum(String table, String column,String field, String columnValue, String timeName, String startTime, String endTime);
}

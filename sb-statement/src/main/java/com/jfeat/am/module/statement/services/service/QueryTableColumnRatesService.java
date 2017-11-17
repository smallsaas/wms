package com.jfeat.am.module.statement.services.service;

import com.alibaba.fastjson.JSONObject;

/**
 * Created by Silent-Y on 2017/11/6.
 */
public interface QueryTableColumnRatesService {

    /// 这是业务相关服务，移走
    //Map<String,Object> queryEquipmentCountByStatus(String tableName,String columnName,List<String> columnContents,String type);

    //@Select("select g.type from #{table} a LEFT JOIN t_config_group g ON a.group_id=g.id WHERE a.field=#{field}")
    //List<String> getFieldType(@Param("field") String field);

    /**
     * final simplest service
     * @param tableName
     * @param columnName
     * @return
     */
    JSONObject queryTableColumnRates(String tableName, String columnName);
}

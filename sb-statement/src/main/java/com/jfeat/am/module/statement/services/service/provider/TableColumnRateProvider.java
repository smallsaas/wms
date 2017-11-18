package com.jfeat.am.module.statement.services.service.provider;

import org.apache.ibatis.jdbc.SQL;

/**
 * Created by vincent on 2017/11/7.
 */
public class TableColumnRateProvider {
    public String queryColumnRate() {
        SQL sql = new SQL();
        sql.SELECT("now()");
        /*sql.SELECT("col_1");
        sql.SELECT("col_2");
        sql.FROM("table_1");
        sql.WHERE("col_3 IN"
                + "<foreach item='item' index='index' collection='items'"
                + "open='(' separator=',' close=')'>"
                + "#{item}"
                + "</foreach>");

        return "<script>" + sql.toString() + "</script>";*/
        return sql.toString();
    }
}

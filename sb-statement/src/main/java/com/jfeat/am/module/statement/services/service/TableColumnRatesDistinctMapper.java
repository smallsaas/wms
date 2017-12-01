package com.jfeat.am.module.statement.services.service;

import com.baomidou.mybatisplus.mapper.BaseMapper;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;

import java.util.Map;

/**
 * Created by Silent-Y on 2017/11/6.
 */
public interface TableColumnRatesDistinctMapper extends BaseMapper{
    @Select("select #{column} from #{table}")
    Map queryTableColumnItems(@Param("table") String table, @Param("column") String column);
}

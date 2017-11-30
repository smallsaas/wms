package com.jfeat.am.module.statement.services.dao;

import com.baomidou.mybatisplus.mapper.BaseMapper;
import com.jfeat.am.module.equipment.services.persistence.model.Equipment;
import org.apache.ibatis.annotations.Param;

import java.util.List;
import java.util.Map;

/**
 * Created by Administrator on 2017/8/9.
 */
public interface TableColumnRatesDao extends BaseMapper<Equipment> {

    List<String> queryValueOfColumn(@Param("table") String table,@Param("column") String column);

    List<Map<String,Integer>> getColumnRates(@Param("table") String table,@Param("column") String column,@Param("columnValues") List<String> columnValues,@Param("timeName") String timeName,@Param("startTime") String startTime,@Param("endTime") String endTime);

}



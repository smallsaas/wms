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

    /*饼状图数据结构
    "titile":"饼状图数据结构",
    "timestamp":"数据生成时间",
    "type":"数据图描述类型",
    "data":[
    {value:335, name:'直接访问'},
    {value:310, name:'邮件营销'},
    {value:274, name:'联盟广告'},
    {value:235, name:'视频广告'},
    {value:400, name:'搜索引擎'}
    ]*/

    @Override
    public Map<String, Object> getColumnRates(String table, String column,String field,String timeName,String startTime,String endTime) {
        Map<String,Object> map = Maps.newHashMap();
        List<String> strings = tableColumnRatesDao.queryValueOfColumn(table, column);
        List<Map<String,Integer>> maps = tableColumnRatesDao.getColumnRates(table, column, strings,timeName,startTime,endTime);
        map.put("timestamp",DateTimeKit.formatDateTime(new Date()));
        map.put("data",maps);
        map.put("field",field);
        return map;
    }
}

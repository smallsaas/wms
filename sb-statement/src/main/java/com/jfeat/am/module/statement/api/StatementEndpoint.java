package com.jfeat.am.module.statement.api;

import com.google.common.collect.Lists;
import com.google.common.collect.Maps;
import com.jfeat.am.common.constant.tips.SuccessTip;
import com.jfeat.am.common.constant.tips.Tip;
import com.jfeat.am.common.controller.BaseController;
import com.jfeat.am.module.statement.services.EquipmentStatus;
import com.jfeat.am.module.statement.services.service.StatementService;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import sun.tools.jconsole.AboutDialog;

import javax.annotation.Resource;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Created by Silent-Y on 2017/11/6.
 */
@RestController
@RequestMapping("/api/statements")
public class StatementEndpoint extends BaseController {

    @Resource
    private StatementService statementService;

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

    @GetMapping("/pie")
    public Tip getPieData() {

        Object innovationNum = statementService.queryEquipmentCountByStatus(EquipmentStatus.INNOVATION);
        Object diableNun = statementService.queryEquipmentCountByStatus(EquipmentStatus.DISABLE);
        Object scrapNum = statementService.queryEquipmentCountByStatus(EquipmentStatus.SCRAP);
        Object sealNum = statementService.queryEquipmentCountByStatus(EquipmentStatus.SEAL);
        Map<String,Object> result = Maps.newHashMap();
        result.put("timestamp",new Date());
        result.put("type","pie");
        Map<String,Object> map= Maps.newHashMap();
        map.put(EquipmentStatus.INNOVATION,innovationNum);
        map.put(EquipmentStatus.DISABLE,diableNun);
        map.put(EquipmentStatus.SCRAP,scrapNum);
        map.put(EquipmentStatus.SEAL,sealNum);
        List<Map<String,Object>> pie = Lists.newArrayList();
        for (Map.Entry<String,Object> entry : map.entrySet()) {
                Map<String, Object> data = Maps.newHashMap();
            data.put("name", entry.getKey());
            data.put("value", entry.getValue());
            pie.add(data);
        }
        result.put("pie",pie);
        return SuccessTip.create(result);
    }
}

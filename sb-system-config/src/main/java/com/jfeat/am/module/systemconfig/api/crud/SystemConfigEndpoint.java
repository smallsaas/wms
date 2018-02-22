package com.jfeat.am.module.systemconfig.api.crud;
import org.springframework.web.bind.annotation.GetMapping;
import com.baomidou.mybatisplus.plugins.Page;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import com.jfeat.am.module.systemconfig.services.domain.service.QuerySystemConfigService;
import com.jfeat.am.common.constant.tips.SuccessTip;
import com.jfeat.am.common.constant.tips.Tip;
import com.jfeat.am.module.log.annotation.BusinessLog;

import com.jfeat.am.module.systemconfig.services.crud.service.SystemConfigService;
import com.jfeat.am.module.systemconfig.services.persistence.model.SystemConfig;

import org.springframework.web.bind.annotation.RestController;
import com.jfeat.am.common.controller.BaseController;

import javax.annotation.Resource;
import java.util.Date;


/**
 * <p>
 *  api
 * </p>
 *
 * @author Code Generator
 * @since 2018-02-22
 */
@Deprecated
@RestController
@RequestMapping("/api/systemconfig/system-config")
public class SystemConfigEndpoint extends BaseController {

    @Resource
    SystemConfigService systemConfigService;

    @Resource
    QuerySystemConfigService querySystemConfigService;

    @PostMapping
    @BusinessLog(name = "SystemConfig", value = "create SystemConfig")
    public Tip createSystemConfig(@RequestBody SystemConfig entity) {
        return SuccessTip.create(systemConfigService.createMaster(entity));
    }

    @GetMapping("/{id}")
    public Tip getSystemConfig(@PathVariable Long id) {
        return SuccessTip.create(systemConfigService.retrieveMaster(id));
    }

    @PutMapping("/{id}")
    @BusinessLog(name = "SystemConfig", value = "update SystemConfig")
    public Tip updateSystemConfig(@PathVariable Long id, @RequestBody SystemConfig entity) {
        entity.setId(id);
        return SuccessTip.create(systemConfigService.updateMaster(entity));
    }

    @DeleteMapping("/{id}")
    @BusinessLog(name = "SystemConfig", value = "delete SystemConfig")
    public Tip deleteSystemConfig(@PathVariable Long id) {
        return SuccessTip.create(systemConfigService.deleteMaster(id));
    }

    @GetMapping
    public Tip querySystemConfigs(Page<SystemConfig> page,
                @RequestParam(name = "pageNum", required = false, defaultValue = "1") Integer pageNum,
                @RequestParam(name = "pageSize", required = false, defaultValue = "10") Integer pageSize,
                                        @RequestParam(name = "id", required = false) Long id,
                                            @RequestParam(name = "dataKey", required = false) String dataKey,
                                            @RequestParam(name = "dataValue", required = false) String dataValue,
                @RequestParam(name = "orderBy",required = false) String  orderBy,
                @RequestParam(name = "sort" ,required = false )  String sort) {
            if(orderBy!=null&&orderBy.length()>0){
            if(sort!=null&&sort.length()>0){
                String pattren = "(ASC|DESC|asc|desc)";
                if(!sort.matches(pattren)){
                    throw new RuntimeException("sort must be ASC or DESC");//此处异常类型根据实际情况而定
                }
            }else{
                sort = "ASC";
            }
            orderBy = "`"+orderBy+"`" +" "+sort;
        }
        page.setCurrent(pageNum);
        page.setSize(pageSize);
        SystemConfig systemconfig = new SystemConfig();
            systemconfig .setId(id);
                systemconfig .setDataKey(dataKey);
                systemconfig .setDataValue(dataValue);

        page.setRecords(querySystemConfigService.findSystemConfigPage(page, systemconfig,orderBy));

        return SuccessTip.create(page);
    }
}

package com.jfeat.am.module.termconfig.api.crud;
import org.springframework.web.bind.annotation.GetMapping;
import com.baomidou.mybatisplus.plugins.Page;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import com.jfeat.am.module.termconfig.services.domain.service.QueryTermConfigService;
import com.jfeat.am.common.constant.tips.SuccessTip;
import com.jfeat.am.common.constant.tips.Tip;

import com.jfeat.am.module.termconfig.services.crud.service.TermConfigService;
import com.jfeat.am.module.termconfig.services.persistence.model.TermConfig;

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
 * @since 2017-12-09
 */
@RestController
@RequestMapping("/api/term/config")
public class TermConfigEndpoint extends BaseController {

    @Resource
    TermConfigService termConfigService;

    @Resource
    QueryTermConfigService queryTermConfigService;

    /// For debug purpose
    @GetMapping("/empty")
    public Tip getEmptyTermConfig() {
        return SuccessTip.create(new TermConfig());
    }

    @GetMapping
    public Tip getTermConfigList() {
        return SuccessTip.create(termConfigService.retrieveMasterList());
    }

    @PostMapping
    public Tip createTermConfig(@RequestBody TermConfig entity) {
        return SuccessTip.create(termConfigService.createMaster(entity));
    }

    @GetMapping("/{id}")
    public Tip getTermConfig(@PathVariable Long id) {
        return SuccessTip.create(termConfigService.retrieveMaster(id));
    }

    @PutMapping("/{id}")
    public Tip updateTermConfig(@PathVariable Long id, @RequestBody TermConfig entity) {
        entity.setId(id);
        return SuccessTip.create(termConfigService.updateMaster(entity));
    }

    @DeleteMapping("/{id}")
    public Tip deleteTermConfig(@PathVariable Long id) {
        return SuccessTip.create(termConfigService.deleteMaster(id));
    }

    /*@GetMapping
    //此方法可能需要自行添加需要的参数,按需要使用
    public Tip queryTermConfigs(Page<TermConfig> page,
                @RequestParam(name = "pageNum", required = false, defaultValue = "1") Integer pageNum,
                @RequestParam(name = "pageSize", required = false, defaultValue = "10") Integer pageSize,
                                        @RequestParam(name = "id", required = false) Long id,
                                        @RequestParam(name = "type", required = false) String type,
                                        @RequestParam(name = "title", required = false) String title,
                                        @RequestParam(name = "content", required = false) String content,
                                                @RequestParam(name = "createdTime", required = false) Date createdTime) {
            page.setCurrent(pageNum);
        page.setSize(pageSize);
        TermConfig termconfig = new TermConfig();
            termconfig .setId(id);
            termconfig .setType(type);
            termconfig .setTitle(title);
            termconfig .setContent(content);
                termconfig .setCreatedTime(createdTime);

        page.setRecords(queryTermConfigService.findTermConfigPage(page, termconfig));

        return SuccessTip.create(page);
    }*/
}

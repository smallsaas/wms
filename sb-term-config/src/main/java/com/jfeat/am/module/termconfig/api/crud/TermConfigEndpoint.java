package com.jfeat.am.module.termconfig.api.crud;

import com.jfeat.am.common.constant.tips.SuccessTip;
import com.jfeat.am.common.constant.tips.Tip;
import com.jfeat.am.common.controller.BaseController;
import com.jfeat.am.module.termconfig.services.crud.service.TermConfigService;
import com.jfeat.am.module.termconfig.services.domain.service.QueryTermConfigService;
import com.jfeat.am.module.termconfig.services.persistence.model.TermConfig;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import java.util.Date;


/**
 * <p>
 * api
 * </p>
 *
 * @author Code Generator
 * @since 2017-12-09
 */
@RestController
@RequestMapping("/api")
public class TermConfigEndpoint extends BaseController {

    @Resource
    TermConfigService termConfigService;

    @Resource
    QueryTermConfigService queryTermConfigService;

    /// For debug purpose
    @GetMapping("/term/config/empty")
    public Tip getEmptyTermConfig() {
        return SuccessTip.create(new TermConfig());
    }

    @GetMapping("/term/config")
    public Tip getTermConfigList() {
        return SuccessTip.create(termConfigService.retrieveMasterList());
    }

    @PostMapping("/adm/term/config")
    public Tip createTermConfig(@RequestBody TermConfig entity) {
        Date now = new Date();
        entity.setCreatedTime(now);
        entity.setPreviousModifiedTime(now);
        entity.setLastModifiedTime(now);
        return SuccessTip.create(termConfigService.createMaster(entity));
    }

    @GetMapping("/term/config/{id}")
    public Tip getTermConfig(@PathVariable Long id) {
        return SuccessTip.create(termConfigService.retrieveMaster(id));
    }

    @GetMapping("/term/config/bytype/{type}")
    public Tip getTermConfigByType(@PathVariable String type) {
        return SuccessTip.create(termConfigService.getTermComfigByType(type));
    }

    @PutMapping("/adm/term/config/{id}")
    public Tip updateTermConfig(@PathVariable Long id, @RequestBody TermConfig entity) {
        TermConfig termConfig = termConfigService.retrieveMaster(id);
        entity.setId(id);
        entity.setPreviousModifiedTime(termConfig.getLastModifiedTime());
        entity.setLastModifiedTime(new Date());
        return SuccessTip.create(termConfigService.updateMaster(entity));
    }

    @DeleteMapping("/adm/term/config/{id}")
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

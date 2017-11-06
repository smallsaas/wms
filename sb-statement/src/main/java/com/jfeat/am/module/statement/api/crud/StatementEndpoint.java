package com.jfeat.am.module.statement.api.crud;
import org.springframework.web.bind.annotation.GetMapping;
import com.baomidou.mybatisplus.plugins.Page;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import com.jfeat.am.module.statement.services.domain.service.QueryStatementService;
import com.jfeat.am.common.constant.tips.ErrorTip;
import com.jfeat.am.common.constant.tips.SuccessTip;
import com.jfeat.am.common.constant.tips.Tip;

import com.jfeat.am.module.statement.services.crud.service.StatementService;
import com.jfeat.am.module.statement.services.persistence.model.Statement;

import org.springframework.web.bind.annotation.RestController;
import com.jfeat.am.common.controller.BaseController;

import javax.annotation.Resource;
import javax.validation.Valid;
import java.util.List;
import java.util.HashMap;

/**
 * <p>
 *  api
 * </p>
 *
 * @author Code Generator
 * @since 2017-11-06
 */
@Deprecated
@RestController
@RequestMapping("/api/statement/statement")
public class StatementEndpoint extends BaseController {

    @Resource
    StatementService statementService;

    @Resource
    QueryStatementService queryStatementService;

    /// For debug purpose
    @GetMapping("/empty")
    public Tip getEmptyStatement() {
        return SuccessTip.create(new Statement());
    }

    @PostMapping
    public Tip createStatement(@RequestBody Statement entity) {
        return SuccessTip.create(statementService.createMaster(entity));
    }

    @GetMapping("/{id}")
    public Tip getStatement(@PathVariable Long id) {
        return SuccessTip.create(statementService.retrieveMaster(id));
    }


    @PutMapping("/{id}")
    public Tip updateStatement(@PathVariable Long id, @RequestBody Statement entity) {
        return SuccessTip.create(statementService.updateMaster(entity));
    }

    @DeleteMapping("/{id}")
    public Tip deleteStatement(@PathVariable Long id) {
        return SuccessTip.create(statementService.deleteMaster(id));
    }

    /*@GetMapping
    //此方法可能需要自行添加需要的参数,按需要使用
    public Tip queryStatements(Page page,
                @RequestParam(name = "pageNum", required = false, defaultValue = "1") Integer pageNum,
                @RequestParam(name = "pageSize", required = false, defaultValue = "5") Integer pageSize,
                @RequestParam(name = "name", required = false) String name,
                @RequestParam(name = "status", required = false) String status) {

        page.setCurrent(pageNum);
        page.setSize(pageSize);
        page.setRecords(queryStatementService.findStatementPage(page, statement));

        return SuccessTip.create(page);
    }*/
}

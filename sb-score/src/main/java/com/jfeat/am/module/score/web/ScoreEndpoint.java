package com.jfeat.am.module.score.web;

import com.jfeat.am.common.exception.BusinessCode;
import com.jfeat.am.common.exception.BusinessException;
import com.jfeat.am.module.log.annotation.BusinessLog;
import org.springframework.dao.DuplicateKeyException;
import org.springframework.web.bind.annotation.GetMapping;
import com.baomidou.mybatisplus.plugins.Page;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import com.jfeat.am.common.constant.tips.SuccessTip;
import com.jfeat.am.common.constant.tips.Tip;
import com.jfeat.am.module.score.services.crud.service.ScoreService;
import com.jfeat.am.module.score.services.persistence.model.Score;

import org.springframework.web.bind.annotation.RestController;
import com.jfeat.am.common.controller.BaseController;

import javax.annotation.Resource;
import java.util.Date;


/**
 * <p>
 * api
 * </p>
 *
 * @author Code Generator
 * @since 2018-04-11
 */
@RestController
@RequestMapping("/api/score/score")
public class ScoreEndpoint extends BaseController {

    @Resource
    ScoreService scoreService;

//    @Resource
//    QueryScoreService queryScoreService;

    @BusinessLog(name = "Score", value = "create Score")
    @PostMapping
    public Tip createScore(@RequestBody Score entity) {

        Integer affected = 0;
        try {
            affected = scoreService.createMaster(entity);

        } catch (DuplicateKeyException e) {
            throw new BusinessException(BusinessCode.DuplicateKey);
        }

        return SuccessTip.create(affected);
    }

    @GetMapping("/{id}")
    public Tip getScore(@PathVariable Long id) {
        return SuccessTip.create(scoreService.retrieveMaster(id));
    }

    @BusinessLog(name = "Score", value = "update Score")
    @PutMapping("/{id}")
    public Tip updateScore(@PathVariable Long id, @RequestBody Score entity) {
        entity.setId(id);
        return SuccessTip.create(scoreService.updateMaster(entity));
    }

    @BusinessLog(name = "Score", value = "delete Score")
    @DeleteMapping("/{id}")
    public Tip deleteScore(@PathVariable Long id) {
        return SuccessTip.create(scoreService.deleteMaster(id));
    }

    @GetMapping
    public Tip queryScores(Page<Score> page,
                           @RequestParam(name = "pageNum", required = false, defaultValue = "1") Integer pageNum,
                           @RequestParam(name = "pageSize", required = false, defaultValue = "10") Integer pageSize,
                           @RequestParam(name = "id", required = false) Long id,
                           @RequestParam(name = "userId", required = false) Long userId,
                           @RequestParam(name = "type", required = false) String type,
                           @RequestParam(name = "score", required = false) Long score,
                           @RequestParam(name = "updateTime", required = false) Date updateTime,
                           @RequestParam(name = "orderBy", required = false) String orderBy,
                           @RequestParam(name = "sort", required = false) String sort) {
        if (orderBy != null && orderBy.length() > 0) {
            if (sort != null && sort.length() > 0) {
                String pattren = "(ASC|DESC|asc|desc)";
                if (!sort.matches(pattren)) {
                    throw new RuntimeException("sort must be ASC or DESC");//此处异常类型根据实际情况而定
                }
            } else {
                sort = "ASC";
            }
            orderBy = "`" + orderBy + "`" + " " + sort;
        }
        page.setCurrent(pageNum);
        page.setSize(pageSize);
        Score scoreNew = new Score();
        scoreNew.setId(id);
        scoreNew.setUserId(userId);
        scoreNew.setType(type);
        scoreNew.setScore(score);
        scoreNew.setUpdateTime(updateTime);

//        page.setRecords(queryScoreService.findScorePage(page, score,orderBy));

        return SuccessTip.create(page);
    }
}

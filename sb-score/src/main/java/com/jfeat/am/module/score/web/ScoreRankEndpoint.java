package com.jfeat.am.module.score.web;

import com.jfeat.am.common.exception.BusinessCode;
import com.jfeat.am.common.exception.BusinessException;
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
import com.jfeat.am.module.log.annotation.BusinessLog;

import com.jfeat.am.module.score.services.crud.service.ScoreRankService;
import com.jfeat.am.module.score.services.persistence.model.ScoreRank;

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
 * @since 2018-04-13
 */
@RestController
@RequestMapping("/api/score/score-rank")
public class ScoreRankEndpoint extends BaseController {

    @Resource
    ScoreRankService scoreRankService;

    /*@Resource
    QueryScoreRankService queryScoreRankService;*/

    @BusinessLog(name = "ScoreRank", value = "create ScoreRank")
    @PostMapping
    public Tip createScoreRank(@RequestBody ScoreRank entity) {

        Integer affected = 0;
        try {
            affected = scoreRankService.createMaster(entity);

        } catch (DuplicateKeyException e) {
            throw new BusinessException(BusinessCode.DuplicateKey);
        }

        return SuccessTip.create(affected);
    }

    @GetMapping("/{id}")
    public Tip getScoreRank(@PathVariable Long id) {
        return SuccessTip.create(scoreRankService.retrieveMaster(id));
    }

    @BusinessLog(name = "ScoreRank", value = "update ScoreRank")
    @PutMapping("/{id}")
    public Tip updateScoreRank(@PathVariable Long id, @RequestBody ScoreRank entity) {
        entity.setId(id);
        return SuccessTip.create(scoreRankService.updateMaster(entity));
    }

    @BusinessLog(name = "ScoreRank", value = "delete ScoreRank")
    @DeleteMapping("/{id}")
    public Tip deleteScoreRank(@PathVariable Long id) {
        return SuccessTip.create(scoreRankService.deleteMaster(id));
    }

    /*@GetMapping
    public Tip queryScoreRanks(Page<ScoreRank> page,
                               @RequestParam(name = "pageNum", required = false, defaultValue = "1") Integer pageNum,
                               @RequestParam(name = "pageSize", required = false, defaultValue = "10") Integer pageSize,
                               @RequestParam(name = "id", required = false) Long id,
                               @RequestParam(name = "userId", required = false) Long userId,
                               @RequestParam(name = "totalScore", required = false) String totalScore,
                               @RequestParam(name = "rank", required = false) String rank,
                               @RequestParam(name = "beatRate", required = false) String beatRate,
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
        ScoreRank scorerank = new ScoreRank();
        scorerank.setId(id);
        scorerank.setUserId(userId);
        scorerank.setTotalScore(totalScore);
        scorerank.setRank(rank);
        scorerank.setBeatRate(beatRate);

//        page.setRecords(queryScoreRankService.findScoreRankPage(page, scorerank,orderBy));

        return SuccessTip.create(page);
    }*/

    @GetMapping
    public Tip queryScoreRanks(@RequestParam(required = true) Long userId){
        return SuccessTip.create(scoreRankService.getScoreRankByUserId(userId));
    }
}

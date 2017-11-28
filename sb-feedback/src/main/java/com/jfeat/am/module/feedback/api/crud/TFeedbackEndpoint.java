package com.jfeat.am.module.feedback.api.crud;

import com.jfeat.am.common.annotation.Permission;
import com.jfeat.am.module.feedback.api.permission.TFeedbackPermission;
import org.springframework.web.bind.annotation.*;

import com.jfeat.am.common.constant.tips.ErrorTip;
import com.jfeat.am.common.constant.tips.SuccessTip;
import com.jfeat.am.common.constant.tips.Tip;

import com.jfeat.am.module.feedback.services.crud.service.TFeedbackService;
import com.jfeat.am.module.feedback.services.persistence.model.TFeedback;

import com.jfeat.am.common.controller.BaseController;

import javax.annotation.Resource;
import javax.validation.Valid;
import java.util.List;
import java.util.HashMap;

/**
 * <p>
 *  前端控制器
 * </p>
 *
 * @author admin
 * @since 2017-11-28
 */
@Deprecated
@RestController
@RequestMapping("/api/feedback/feedback/t-feedback")
public class TFeedbackEndpoint extends BaseController {


    @Resource
    private TFeedbackService tFeedbackService;

    @PostMapping
    public Tip createTFeedback(@RequestBody TFeedback entity) {
            return SuccessTip.create(tFeedbackService.createMaster(entity));
            }

    @GetMapping("/{id}")
    public Tip getTFeedback(@PathVariable Long id) {
        return SuccessTip.create(tFeedbackService.retrieveMaster(id));
    }



    @PutMapping("/{id}")
    public Tip updateTFeedback(@PathVariable Long id, @RequestBody TFeedback entity) {
        return SuccessTip.create(tFeedbackService.updateMaster(entity));
    }

    @DeleteMapping("/{id}")
    public Tip deleteTFeedback(@PathVariable Long id) {
        return SuccessTip.create(tFeedbackService.deleteMaster(id));
    }

/*
    @GetMapping
    //此方法可能需要自行添加需要的参数,按需要使用
    public Tip queryTFeedbacks(Page page,
                @RequestParam(name = "pageNum", required = false, defaultValue = "1") Integer pageNum,
                @RequestParam(name = "pageSize", required = false, defaultValue = "5") Integer pageSize) {
            List<TFeedback> records = tFeedbackService.findTFeedbacks(page);
            page.setCurrent(pageNum);
            page.setSize(pageSize);
            page.setRecords(records);

            return SuccessTip.create(page);
        }
        */
        @GetMapping
        @Permission({TFeedbackPermission.TFeedback_VIEW})
        public Tip show(@RequestHeader("authorization") String token) {
            return null;
        }
}

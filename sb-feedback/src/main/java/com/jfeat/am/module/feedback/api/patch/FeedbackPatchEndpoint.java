package com.jfeat.am.module.feedback.api.patch;

import com.jfeat.am.common.constant.tips.SuccessTip;
import com.jfeat.am.common.constant.tips.Tip;
import com.jfeat.am.common.controller.BaseController;
import com.jfeat.am.module.feedback.services.domain.model.TFeedbackModel;
import com.jfeat.am.module.feedback.services.patch.FeedbackPatchService;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;

/**
 * Created by vincenthuang on 18/09/2017.
 */
@RestController
@RequestMapping("/api")
public class FeedbackPatchEndpoint extends BaseController {

    @Resource
    FeedbackPatchService feedbackPatchService;

    @PutMapping("/adm/feedback/{id}/solve")
    public Tip solve(@PathVariable Long id, @RequestBody TFeedbackModel tFeedbackModel) {
        tFeedbackModel.setId(id);
        Integer result = feedbackPatchService.solve(tFeedbackModel);
        return SuccessTip.create(result);
    }

}
package com.jfeat.am.module.feedback.api.crud;

import com.baomidou.mybatisplus.plugins.Page;
import com.jfeat.am.common.constant.tips.SuccessTip;
import com.jfeat.am.common.constant.tips.Tip;
import com.jfeat.am.common.controller.BaseController;
import com.jfeat.am.core.jwt.JWTKit;
import com.jfeat.am.module.feedback.services.crud.filter.TFeedbackFilter;
import com.jfeat.am.module.feedback.services.crud.service.TFeedbackService;
import com.jfeat.am.module.feedback.services.domain.model.TFeedbackModel;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import java.util.List;
import java.util.Map;

/**
 * <p>
 *  前端控制器
 * </p>
 *
 * @author admin
 * @since 2017-11-28
 */
@RestController
@RequestMapping("/api")
public class FeedbackEndpoint extends BaseController {


    @Resource
    private TFeedbackService tFeedbackService;

    @PostMapping("/feedback")
    public Tip createTFeedback(@RequestBody TFeedbackModel entity) {
            Long userId = JWTKit.getUserId(getHttpServletRequest());
            entity.setUserId(userId);
            return SuccessTip.create(tFeedbackService.createMaster(entity,new TFeedbackFilter(),null,null));
            }

    @GetMapping("/feedback/{id}")
    public Tip getTFeedback(@PathVariable Long id) {
        return SuccessTip.create(tFeedbackService.retrieveMaster(id,new TFeedbackFilter(),null,null).toJSONObject());
    }



    @PutMapping("/adm/feedback/{id}")
    public Tip updateTFeedback(@PathVariable Long id, @RequestBody TFeedbackModel entity) {
        Long userId = JWTKit.getUserId(getHttpServletRequest());
        entity.setDealUserId(userId);
        return SuccessTip.create(tFeedbackService.updateMaster(entity,new TFeedbackFilter(),null,null));
    }

    @DeleteMapping("/feedback/{id}")
    public Tip deleteTFeedback(@PathVariable Long id) {
        return SuccessTip.create(tFeedbackService.deleteMaster(id));
    }

    @GetMapping("/feedback")
    //此方法可能需要自行添加需要的参数,按需要使用
    public Tip queryTFeedbacks(Page page,
                @RequestParam(name = "pageNum", required = false, defaultValue = "1") Integer pageNum,
                @RequestParam(name = "pageSize", required = false, defaultValue = "5") Integer pageSize,
                               @RequestParam(required = false)Long id,
                               @RequestParam(required = false)Long userId,
                               @RequestParam(required = false)Integer unread,
                               @RequestParam(required = false)String content,
                               @RequestParam(required = false)String startTime,
                               @RequestParam(required = false)String endTime) {

            List<Map<String,Object>> records = tFeedbackService.findTFeedbacks(page,id,userId,unread,content,startTime,endTime);
            page.setCurrent(pageNum);
            page.setSize(pageSize);
            page.setRecords(records);

            return SuccessTip.create(page);
        }
        @GetMapping("/null")
        public Tip show(@RequestHeader("authorization") String token) {
            return null;
        }
}

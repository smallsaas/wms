package com.jfeat.am.module.feedback.api.crud;

import com.jfeat.am.common.constant.tips.SuccessTip;
import com.jfeat.am.common.constant.tips.Tip;
import com.jfeat.am.common.controller.BaseController;
import com.jfeat.am.module.feedback.services.crud.service.TFeedbackImageService;
import com.jfeat.am.module.feedback.services.persistence.model.TFeedbackImage;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;

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
@RequestMapping("/api/feedback/image")
public class TFeedbackImageEndpoint extends BaseController {


    @Resource
    private TFeedbackImageService tFeedbackImageService;

    @PostMapping
    public Tip createTFeedbackImage(@RequestBody TFeedbackImage entity) {
            return SuccessTip.create(tFeedbackImageService.createMaster(entity));
            }

    @GetMapping("/{id}")
    public Tip getTFeedbackImage(@PathVariable Long id) {
        return SuccessTip.create(tFeedbackImageService.retrieveMaster(id));
    }



    @PutMapping("/{id}")
    public Tip updateTFeedbackImage(@PathVariable Long id, @RequestBody TFeedbackImage entity) {
        return SuccessTip.create(tFeedbackImageService.updateMaster(entity));
    }

    @DeleteMapping("/{id}")
    public Tip deleteTFeedbackImage(@PathVariable Long id) {
        return SuccessTip.create(tFeedbackImageService.deleteMaster(id));
    }

/*
    @GetMapping
    //此方法可能需要自行添加需要的参数,按需要使用
    public Tip queryTFeedbackImages(Page page,
                @RequestParam(name = "pageNum", required = false, defaultValue = "1") Integer pageNum,
                @RequestParam(name = "pageSize", required = false, defaultValue = "5") Integer pageSize) {
            List<TFeedbackImage> records = tFeedbackImageService.findTFeedbackImages(page);
            page.setCurrent(pageNum);
            page.setSize(pageSize);
            page.setRecords(records);

            return SuccessTip.create(page);
        }
        */
        @GetMapping
        public Tip show(@RequestHeader("authorization") String token) {
            return null;
        }
}

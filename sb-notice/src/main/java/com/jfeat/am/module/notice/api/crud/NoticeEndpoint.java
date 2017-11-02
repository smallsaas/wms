package com.jfeat.am.module.notice.api.crud;
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
import com.jfeat.am.module.notice.services.domain.service.QueryNoticeService;
import com.jfeat.am.common.constant.tips.ErrorTip;
import com.jfeat.am.common.constant.tips.SuccessTip;
import com.jfeat.am.common.constant.tips.Tip;

import com.jfeat.am.module.notice.services.crud.service.NoticeService;
import com.jfeat.am.module.notice.services.persistence.model.Notice;

import org.springframework.web.bind.annotation.RestController;
import com.jfeat.am.common.controller.BaseController;

import javax.annotation.Resource;
import javax.validation.Valid;
import java.util.Date;
import java.util.List;
import java.util.HashMap;

/**
 * <p>
 *  api
 * </p>
 *
 * @author Code Generator
 * @since 2017-11-02
 */
@Deprecated
@RestController
@RequestMapping("/api/notice/notice")
public class NoticeEndpoint extends BaseController {

    @Resource
    NoticeService noticeService;

    @Resource
    QueryNoticeService queryNoticeService;

    /// For debug purpose
    @GetMapping("/empty")
    public Tip getEmptyNotice(@PathVariable Long id) {
        return SuccessTip.create(new Notice());
    }

    @PostMapping
    public Tip createNotice(@RequestBody Notice entity) {
        entity.setCreateTime(new Date());
            return SuccessTip.create(noticeService.createMaster(entity));
            }

    @GetMapping("/{id}")
    public Tip getNotice(@PathVariable Long id) {
        return SuccessTip.create(noticeService.retrieveMaster(id));
    }


    @PutMapping("/{id}")
    public Tip updateNotice(@PathVariable Long id, @RequestBody Notice entity) {
        entity.setId(id);
        return SuccessTip.create(noticeService.updateMaster(entity));
    }

    @DeleteMapping("/{id}")
    public Tip deleteNotice(@PathVariable Long id) {
        return SuccessTip.create(noticeService.deleteMaster(id));
    }

    @GetMapping
    //此方法可能需要自行添加需要的参数,按需要使用
    public Tip queryNotices(
                @RequestParam(name = "pageNum", required = false, defaultValue = "1") Integer pageNum,
                @RequestParam(name = "pageSize", required = false, defaultValue = "5") Integer pageSize,
                @RequestParam(name = "name", required = false) String name,
                @RequestParam(name = "status", required = false) String status) {
        return SuccessTip.create(queryNoticeService.findNotices(pageNum, pageSize, name, status));
    }
}

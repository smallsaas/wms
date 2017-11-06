package com.jfeat.am.module.notice.api.crud;

import com.baomidou.mybatisplus.plugins.Page;
import com.jfeat.am.common.constant.tips.SuccessTip;
import com.jfeat.am.common.constant.tips.Tip;
import com.jfeat.am.common.controller.BaseController;
import com.jfeat.am.module.notice.services.crud.service.NoticeService;
import com.jfeat.am.module.notice.services.domain.service.QueryNoticeService;
import com.jfeat.am.module.notice.services.persistence.model.Notice;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import java.util.Date;

/**
 * <p>
 * api
 * </p>
 *
 * @author Code Generator
 * @since 2017-11-02
 */
@RestController
@RequestMapping("/api/notice/notices")
public class NoticeEndpoint extends BaseController {

    @Resource
    NoticeService noticeService;

    @Resource
    QueryNoticeService queryNoticeService;

    /// For debug purpose
    @GetMapping("/empty")
    public Tip getEmptyNotice() {
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
            Page<Notice> page,
            @RequestParam(name = "pageNum", required = false, defaultValue = "1") Integer pageNum,
            @RequestParam(name = "pageSize", required = false, defaultValue = "5") Integer pageSize,
            @RequestParam(name = "type", required = false) String type,
            @RequestParam(name = "enable", required = false) Integer enable,
            @RequestParam(name = "title", required = false) String title,
            @RequestParam(name = "content", required = false) String content,
            @RequestParam(name = "createTime", required = false) String createTime,
            @RequestParam(name = "updateTime", required = false) String updateTime) {
        page.setCurrent(pageNum);
        page.setSize(pageSize);

        Notice notice = new Notice();
        notice.setEnable(enable);
        notice.setType(type);
        notice.setTitle(title);
        notice.setContent(content);
        //notice.setCreateTime(new DateTimeParser(DateTimeFormat.ISO).parse(createTime));
        //notice.setUpdateTime();

        page.setRecords(queryNoticeService.findNotices(page, notice));

        return SuccessTip.create();
    }
}

package com.jfeat.am.module.notice.api;

import com.baomidou.mybatisplus.plugins.Page;
import com.jfeat.am.common.constant.tips.SuccessTip;
import com.jfeat.am.common.constant.tips.Tip;
import com.jfeat.am.common.controller.BaseController;
import com.jfeat.am.core.support.DateTimeKit;
import com.jfeat.am.core.support.StrKit;
import com.jfeat.am.module.notice.services.domain.service.QueryNoticeService;
import com.jfeat.am.module.notice.services.persistence.model.Notice;
import com.jfeat.am.module.notice.services.service.NoticeService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.hibernate.validator.constraints.Range;
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
@Api("公告")
@RequestMapping("/api/notice/notices")
public class NoticeEndpoint extends BaseController {

    @Resource
    NoticeService noticeService;

    @Resource
    QueryNoticeService queryNoticeService;

    /**
     * PATCH
     */
    @ApiOperation("执行公告")
    @PostMapping("/{id}/enable")
    public Tip enableNotice(@PathVariable Long id) {
        Notice notice = new Notice();
        notice.setId(id);
        notice.setEnabled(1);

        return SuccessTip.create(noticeService.updateMaster(notice));
    }

    @PostMapping("/{id}/disable")
    @ApiOperation("下架公告")
    public Tip disableNotice(@PathVariable Long id) {
        Notice notice = new Notice();
        notice.setId(id);
        notice.setEnabled(0);

        return SuccessTip.create(noticeService.updateMaster(notice));
    }

    @PutMapping("/{id}/switchEnabled")
    @ApiOperation("启用(或取消启用)公告")
    public Tip switchEnabled(@PathVariable Long id) {
        return SuccessTip.create(noticeService.switchEnabled(id));
    }

    /**
     * CRUD
     *
     * @param entity
     * @return
     */
    @PostMapping
    @ApiOperation("添加公告")
    public Tip createNotice(@RequestBody Notice entity) {
        entity.setCreateTime(new Date());
        entity.setEnabled(1);

        return SuccessTip.create(noticeService.createMaster(entity));
    }

    @GetMapping("/{id}")
    @ApiOperation("查看公告")
    public Tip getNotice(@PathVariable Long id) {
        return SuccessTip.create(noticeService.retrieveMaster(id));
    }

    @PutMapping("/{id}")
    @ApiOperation("修改公告")
    public Tip updateNotice(@PathVariable Long id, @RequestBody Notice entity) {
        entity.setId(id);
        entity.setUpdateTime(new Date());

        return SuccessTip.create(noticeService.updateMaster(entity));
    }

    @DeleteMapping("/{id}")
    @ApiOperation("删除公告")
    public Tip deleteNotice(@PathVariable Long id) {
        return SuccessTip.create(noticeService.deleteMaster(id));
    }

    @GetMapping
    @ApiOperation("公告列表")
    public Tip queryNotices(
            Page<Notice> page,
            @RequestParam(name = "pageNum", required = false, defaultValue = "1") Integer pageNum,
            @RequestParam(name = "pageSize", required = false, defaultValue = "5") Integer pageSize,
            @RequestParam(name = "type", required = false) String type,
            @RequestParam(name = "status", required = false) String status,
            @Range(min = 0, max = 1) @RequestParam(name = "enabled", required = false) Integer enabled,
            @Range(min = 0, max = 1) @RequestParam(name = "expired", required = false) Integer expired,
            @RequestParam(name = "title", required = false) String title,
            @RequestParam(name = "content", required = false) String content,
            @RequestParam(name = "createTime", required = false) String createTime,
            @RequestParam(name = "updateTime", required = false) String updateTime,
            @RequestParam(name = "endTime", required = false) String endTime
    ) {
        page.setCurrent(pageNum);
        page.setSize(pageSize);

        Notice notice = new Notice();
        notice.setType(type);
        notice.setStatus(status);
        notice.setTitle(title);
        notice.setContent(content);
        notice.setCreateTime(parseDate(createTime));
        notice.setUpdateTime(parseDate(updateTime));
        notice.setEndTime(parseDate(endTime));
        notice.setEnabled(enabled);

        page.setRecords(queryNoticeService.findNotices(page, notice, expired));

        return SuccessTip.create(page);
    }

    /**
     * 最新公告（必须是未过期，且已启用的。结果按有效时间倒序）
     */
    @GetMapping("/recent/notices")
    @ApiOperation("最新公告（必须是未过期，且已启用的。结果按有效时间倒序）")
    public Tip rencentNotices(Page<Notice> page,
                               @RequestParam(name = "pageNum", required = false, defaultValue = "1") Integer pageNum,
                               @RequestParam(name = "pageSize", required = false, defaultValue = "5") Integer pageSize) {
        page.setCurrent(pageNum);
        page.setSize(pageSize);
        page.setRecords(queryNoticeService.findRecentNotices(page));
        return SuccessTip.create(page);
    }

    private Date parseDate(String dateStr) {
        if (StrKit.isBlank(dateStr)) {
            return null;
        }
        return DateTimeKit.parseDate(dateStr);
    }
}

package com.jfeat.am.module.notice.task;

import com.jfeat.am.module.notice.services.persistence.model.Notice;
import com.jfeat.am.module.notice.services.service.NoticeService;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import javax.annotation.Resource;
import java.util.List;

/**
 * Created by kang on 2018/4/11.
 */
@Component
public class AuditNoticeJob {

    @Resource
    NoticeService noticeService;

    /**
     * 每10分钟执行一次，检查到期的notice，把其禁用
     */
    @Scheduled(cron = "* 1/10 * * * ?")
    public void auditNotice() {
        List<Notice> expiredNotices = noticeService.findExpiredNotices();
        if (expiredNotices.size() > 0) {
            for (Notice expiredNotice : expiredNotices) {
                if (expiredNotice.getEnabled() == 0) {
                    continue;
                }
                expiredNotice.setEnabled(0);
                noticeService.updateMaster(expiredNotice);
            }
        }
    }
}

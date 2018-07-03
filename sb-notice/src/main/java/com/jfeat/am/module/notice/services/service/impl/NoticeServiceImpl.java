package com.jfeat.am.module.notice.services.service.impl;

import com.baomidou.mybatisplus.mapper.BaseMapper;
import com.jfeat.am.common.crud.impl.CRUDServiceOnlyImpl;
import com.jfeat.am.module.notice.services.domain.dao.QueryNoticeDao;
import com.jfeat.am.module.notice.services.persistence.dao.NoticeMapper;
import com.jfeat.am.module.notice.services.persistence.model.Notice;
import com.jfeat.am.module.notice.services.service.NoticeService;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;

/**
 * <p>
 * implementation
 * </p>
 *
 * @author Code Generator
 * @since 2017-11-02
 */
@Service
public class NoticeServiceImpl extends CRUDServiceOnlyImpl<Notice> implements NoticeService {

    @Resource
    private NoticeMapper noticeMapper;

    @Resource
    private QueryNoticeDao queryNoticeDao;

    @Override
    protected BaseMapper<Notice> getMasterMapper() {
        return noticeMapper;
    }

    @Override
    public List<Notice> findExpiredNotices() {
        return queryNoticeDao.findExpiredNotices();
    }

    @Override
    public Integer switchEnabled(Long id) {
        Notice notice = noticeMapper.selectById(id);
        if (notice.getEnabled() == 1) {
            notice.setEnabled(0);
        } else {
            notice.setEnabled(1);
        }
        return noticeMapper.updateById(notice);
    }

}



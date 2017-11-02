package com.jfeat.am.module.notice.services.domain.service.impl;

import com.baomidou.mybatisplus.plugins.Page;
import com.jfeat.am.module.notice.services.domain.dao.QueryNoticeDao;
import com.jfeat.am.module.notice.services.domain.service.QueryNoticeService;
import com.jfeat.am.module.notice.services.persistence.model.Notice;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;

/**
 * <p>
 *  服务实现类
 * </p>
 *
 * @author admin
 * @since 2017-10-16
 */
@Service
public class QueryNoticeServiceImpl implements QueryNoticeService {
    @Resource
    QueryNoticeDao queryNoticeDao;

    @Override
    public List<Notice> findNotices(Page<Notice> page, Notice notice) {
        return queryNoticeDao.findNotices(page, notice);
    }
}

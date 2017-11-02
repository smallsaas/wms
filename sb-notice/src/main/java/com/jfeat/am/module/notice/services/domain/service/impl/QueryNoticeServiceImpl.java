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
    public List<Notice> findNotices(int pageNum, int pageSize, String name, String status) {

        Page page = new Page();
        page.setCurrent(pageNum);
        page.setSize(pageSize);
        page.setRecords(queryNoticeDao.findNotices(page, name, status));

        return queryNoticeDao.findNotices(page, name, status);
    }

    @Override
    public List<Notice> findNoticePage(int pageNum, int pageSize, Notice notice) {

        Page page = new Page();
        page.setCurrent(pageNum);
        page.setSize(pageSize);
        page.setRecords(queryNoticeDao.findNoticePage(page, notice));

        return queryNoticeDao.findNoticePage(page, notice);
    }
}

package com.jfeat.am.module.notice.services.domain.service;

import com.jfeat.am.module.notice.services.persistence.model.Notice;

import java.util.List;

/**
 * Created by vincent on 2017/10/19.
 */
public interface QueryNoticeService {
    List<Notice> findNotices(int pageNum, int pageSize, String name, String status);

    List<Notice> findNoticePage(int pageNum, int pageSize,Notice notice );
}
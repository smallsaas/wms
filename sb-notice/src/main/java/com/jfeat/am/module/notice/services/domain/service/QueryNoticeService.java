package com.jfeat.am.module.notice.services.domain.service;

import com.baomidou.mybatisplus.plugins.Page;
import com.jfeat.am.module.notice.services.persistence.model.Notice;

import java.util.List;

/**
 * Created by vincent on 2017/10/19.
 */
public interface QueryNoticeService {
    List<Notice> findNotices(Page<Notice> page, Notice notice, Integer expired);
}
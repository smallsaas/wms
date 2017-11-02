package com.jfeat.am.module.notice.services.domain.dao;

import com.jfeat.am.module.notice.services.persistence.model.Notice;

import com.baomidou.mybatisplus.plugins.Page;
import com.baomidou.mybatisplus.mapper.BaseMapper;
import org.apache.ibatis.annotations.Param;
import java.util.List;

/**
 * Created by Code Generator on 2017-11-02
 */
public interface QueryNoticeDao extends BaseMapper<Notice> {

    List<Notice> findNotices(Page<Notice> page,
            @Param("name") String name,
            @Param("status") String status);

    List<Notice> findNoticePage(Page<Notice> page,Notice notice);
}
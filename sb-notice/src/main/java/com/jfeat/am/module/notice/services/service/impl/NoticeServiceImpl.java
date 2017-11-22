package com.jfeat.am.module.notice.services.service.impl;
            
import com.baomidou.mybatisplus.mapper.BaseMapper;
import com.jfeat.am.common.crud.impl.CRUDServiceOnlyImpl;
import com.jfeat.am.module.notice.services.service.NoticeService;
import com.jfeat.am.module.notice.services.persistence.dao.NoticeMapper;
import com.jfeat.am.module.notice.services.persistence.model.Notice;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
/**
 * <p>
 *  implementation
 * </p>
 *
 * @author Code Generator
 * @since 2017-11-02
 */
@Service
public class NoticeServiceImpl  extends CRUDServiceOnlyImpl<Notice> implements NoticeService {

    @Resource
    private NoticeMapper noticeMapper;

    @Override
    protected BaseMapper<Notice> getMasterMapper() {
        return noticeMapper;
    }
}


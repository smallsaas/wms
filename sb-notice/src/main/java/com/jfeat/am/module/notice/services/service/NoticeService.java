package com.jfeat.am.module.notice.services.service;

import com.jfeat.am.common.crud.CRUDServiceOnly;
import com.jfeat.am.module.notice.services.persistence.model.Notice;

import java.util.List;


/**
 * <p>
 * service interface
 * </p>
 *
 * @author Code Generator
 * @since 2017-11-02
 */
public interface NoticeService extends CRUDServiceOnly<Notice> {

    public List<Notice> findExpiredNotices();

    public Integer switchEnabled(Long id);

}

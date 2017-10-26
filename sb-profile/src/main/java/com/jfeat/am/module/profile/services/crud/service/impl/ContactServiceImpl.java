package com.jfeat.am.module.profile.services.crud.service.impl;
            
import com.baomidou.mybatisplus.mapper.BaseMapper;
import com.jfeat.am.module.profile.services.persistence.model.Contact;
import com.jfeat.am.module.profile.services.persistence.dao.ContactMapper;
import com.jfeat.am.module.profile.services.crud.service.ContactService;
import com.jfeat.am.common.crud.impl.CRUDServiceOnlyImpl;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;

/**
 * <p>
 *  implementation
 * </p>
 *
 * @author Code Generator
 * @since 2017-10-19
 */
@Service
public class ContactServiceImpl  extends CRUDServiceOnlyImpl<Contact> implements ContactService {


    @Resource
    private ContactMapper contactMapper;

    @Override
    protected BaseMapper<Contact> getMasterMapper() {
        return contactMapper;
    }
}



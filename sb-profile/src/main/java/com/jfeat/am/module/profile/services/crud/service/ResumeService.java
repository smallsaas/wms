package com.jfeat.am.module.profile.services.crud.service;
            
import com.jfeat.am.common.crud.CRUDServiceOnly;
import com.jfeat.am.common.crud.CRUDServiceOverModel;
import com.jfeat.am.common.crud.impl.CRUDServiceOverModelImpl;
import com.jfeat.am.module.profile.services.domain.ResumeModel;
import com.jfeat.am.module.profile.services.persistence.model.Resume;



/**
 * <p>
 *  service interface
 * </p>
 *
 * @author Code Generator
 * @since 2017-10-19
 */

public interface ResumeService  extends CRUDServiceOverModel<Resume,ResumeModel> {
    ResumeModel retrieveResume(long id);

}

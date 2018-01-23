package com.jfeat.am.module.organization.services.crud.service;
        
import com.jfeat.am.common.crud.CRUDServiceGroup;
import com.jfeat.am.module.organization.services.persistence.model.Department;


/**
 * <p>
 *  服务类
 * </p>
 *
 * @author admin
 * @since 2017-10-11
 */

public interface DepartmentService extends CRUDServiceGroup<Department> {

    Department getById(Long id);

}

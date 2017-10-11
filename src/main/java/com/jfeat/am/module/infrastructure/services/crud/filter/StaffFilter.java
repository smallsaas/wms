package com.jfeat.am.module.infrastructure.services.crud.filter;

import com.jfeat.am.common.crud.CRUDFilter;
import com.jfeat.am.module.infrastructure.services.crud.persistence.model.Staff;


/**
 * Created by SB-Code-Generator on 2017/9/14.
 */
public class StaffFilter implements CRUDFilter<Staff> {

    private String[] ignoreFields = new String[]{};
    private String[] updateIgnoreFields = new String[]{};

    @Override
    public void filter(Staff entity, boolean insertOrUpdate) {

        //if insertOrUpdate is true,means for insert, do this
        if (insertOrUpdate){

            //then insertOrUpdate is false,means for update,do this
        }else {

        }

    }

    @Override
    public String[] ignore(boolean retrieveOrUpdate) {
        //if retrieveOrUpdate is true,means for retrieve ,do this
        if (retrieveOrUpdate){
            return ignoreFields;
            //then retrieveOrUpdate  if false ,means for update,do this
        }else {
            return updateIgnoreFields;
        }
    }
}

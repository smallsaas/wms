package com.jfeat.am.module.vip.services.crud.filter;

import com.jfeat.am.common.crud.CRUDFilter;
import com.jfeat.am.module.${cfg.en}.services.crud.persistence.model.OperationLog;


/**
 * Created by Silent-Y on 2017/9/14.
 */
public class OperationLogFilter implements CRUDFilter<OperationLog> {

    private String[] ignoreFields = new String[]{};
    private String[] updateIgnoreFields = new String[]{};

    @Override
    public void filter(OperationLog entity, boolean b) {

        //if b is true, do this
        if (b){

            //then b is false,do this
        }else {

        }

    }

    @Override
    public String[] ignore(boolean b) {
        //if b is true,do this
        if (b){
            return ignoreFields;
            //then b  if false ,do this
        }else {
            return updateIgnoreFields;
        }
    }
}

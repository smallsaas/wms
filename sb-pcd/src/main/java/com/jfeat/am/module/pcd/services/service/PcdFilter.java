package com.jfeat.am.module.pcd.services.servic;

import com.jfeat.am.common.crud.CRUDFilter;
import com.jfeat.am.module.pcd.services.persistence.model.Pcd;


/**
 * Created by Code Generator on 2017-12-22
 */
public class PcdFilter implements CRUDFilter<Pcd> {

    private String[] ignoreFields = new String[]{};
    private String[] updateIgnoreFields = new String[]{};

    @Override
    public void filter(Pcd entity, boolean insertOrUpdate) {

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

package com.jfeat.am.module.warehouse.services.crud.filter;

import com.jfeat.am.common.crud.CRUDFilter;
import com.jfeat.am.module.warehouse.services.persistence.model.Trader;


/**
 * Created by Code Generator on 2018-11-14
 */
public class TraderFilter implements CRUDFilter<Trader> {

    private String[] ignoreFields = new String[]{};
    private String[] updateIgnoreFields = new String[]{};

    @Override
    public void filter(Trader entity, boolean insertOrUpdate) {

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

package com.jfeat.am.module.warehouse.services.crud.filter;

import com.alibaba.fastjson.JSONObject;
import com.jfeat.am.module.warehouse.services.persistence.model.StorageOut;
import com.jfeat.crud.plus.CRUDFilterResult;


/**
 * Created by Code Generator on 2018-06-20
 */
public class StorageOutFilter implements CRUDFilterResult<StorageOut> {

    private String[] ignoreFields = new String[]{};
    private String[] updateIgnoreFields = new String[]{};
    private JSONObject result;


    @Override
    public void filter(StorageOut entity, boolean insertOrUpdate) {

        //if insertOrUpdate is true,means for insert, do this
        if (insertOrUpdate) {

            //then insertOrUpdate is false,means for update,do this
        } else {

        }

    }

    @Override
    public String[] ignore(boolean retrieveOrUpdate) {
        //if retrieveOrUpdate is true,means for retrieve ,do this
        if (retrieveOrUpdate) {
            return ignoreFields;
            //then retrieveOrUpdate  if false ,means for update,do this
        } else {
            return updateIgnoreFields;
        }
    }


    @Override
    public JSONObject result() {
        if (this.result == null) {
            this.result = new JSONObject();
        }
        return this.result;
    }
}

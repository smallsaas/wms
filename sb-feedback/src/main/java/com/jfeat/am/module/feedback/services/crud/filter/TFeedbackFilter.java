package com.jfeat.am.module.feedback.services.crud.filter;

import com.alibaba.fastjson.JSONObject;
import com.jfeat.am.common.crud.CRUDFilter;
import com.jfeat.am.common.crud.CRUDFilterResult;
import com.jfeat.am.module.feedback.services.persistence.model.TFeedback;


/**
 * Created by SB-Code-Generator on 2017/9/14.
 */
public class TFeedbackFilter implements CRUDFilterResult<TFeedback> {

    private String[] ignoreFields = new String[]{};
    private String[] updateIgnoreFields = new String[]{};

    @Override
    public JSONObject result() {
        return new JSONObject();
    }

    @Override
    public void filter(TFeedback entity, boolean insertOrUpdate) {

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

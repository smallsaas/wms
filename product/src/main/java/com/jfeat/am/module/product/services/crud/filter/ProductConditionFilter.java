package com.jfeat.am.module.product.services.crud.filter;

import com.jfeat.am.module.product.services.persistence.model.ProductCondition;
import com.jfeat.crud.plus.CRUDFilter;


/**
 * Created by Code Generator on 2018-06-19
 */
public class ProductConditionFilter implements CRUDFilter<ProductCondition> {

    private String[] ignoreFields = new String[]{};
    private String[] updateIgnoreFields = new String[]{};

    @Override
    public void filter(ProductCondition entity, boolean insertOrUpdate) {

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

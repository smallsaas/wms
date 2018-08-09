package com.jfeat.am.module.warehouse.services.domain.model;

import com.jfeat.am.module.warehouse.services.persistence.model.Check;
import com.jfeat.am.module.warehouse.services.persistence.model.CheckSku;

import java.util.List;

/**
 * Created by Code Generator on 2018-06-23
 */
public class CheckModel extends Check{

    List<CheckSku> checkSkus;

    public List<CheckSku> getCheckSkus() {
        return checkSkus;
    }

    public void setCheckSkus(List<CheckSku> checkSkus) {
        this.checkSkus = checkSkus;
    }
}

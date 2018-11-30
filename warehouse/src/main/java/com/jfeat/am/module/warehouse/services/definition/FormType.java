package com.jfeat.am.module.warehouse.services.definition;

/**
 * Created by zy on 2018/11/30.
 */
public enum FormType {

    CHECK("check"),     // 盘点单
    IN("in"),       // 入库单
    OUT("out"),     // 出库单
    TRANSFER("transfer"),   //调拨单
    PURCHASE("purchase"),   // 采购单
    DISTRIBUTOR_OUT("distributor_out"); // 分销商出库

    private String name;

    FormType(String name) {
        this.name = name;
    }
}

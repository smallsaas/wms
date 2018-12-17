package com.jfeat.am.module.warehouse.services.definition;

/**
 * Created by leuo on 2018/8/10.
 */
public enum RefundStatus {

    Done,
    Closed,
    Draft,    // draft ,update by all time
    Wait_To_audit, // can not update
    Audit_Passed, // u can execute storage in
}

package com.jfeat.am.module.warehouse.services.definition;

public enum  StorageInStatus {

    Draft,    // draft ,update by all time
    Wait_To_audit, // can not update
    Audit_Passed, // u can execute storage in
    Done, // finished all operation
    Cancel    // means audit reject

}
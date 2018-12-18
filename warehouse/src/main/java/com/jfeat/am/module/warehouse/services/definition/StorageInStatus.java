package com.jfeat.am.module.warehouse.services.definition;

public enum  StorageInStatus {

    Draft,    // draft ,update by all time
    Wait_To_Audit, // can not update
    Audit_Passed, // u can execute storage in
    Done, // finished all operation
    Closed    // means audit reject

}

package com.jfeat.am.module.warehouse.services.definition;

public enum  StorageInStatus {

    Draft,    // draft ,update by all time
    Wait_To_audit, // can not update
    Audit_Passed, // u can execute storage in
    Wait_Storage_In, // execution storage
    Done, // finished all operation
    Cancel    // means audit reject

}

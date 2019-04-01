package com.jfeat.am.module.warehouse.services.definition;

public enum TransactionType {

    Procurement,   // 采购
    Refund,         // 退货
    SalesIn,        // 商城入库
    SalesOut,       //删除出库
    TransferIn,     //调拨入库
    TransferOut,    //调拨出库
    StorageIn,      // 入库 无其他的入库类型的 默认的入库类型
    StorageOut,     // 出库 无其他出库类型的 默认的出库类型
    OthersStorageOut,   // 其他出库
    OthersStorageIn,    // 其他入库
    CustomerStorageIn,  // 客户入库
    CustomerStorageOut  //客户出库
}

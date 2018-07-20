package com.jfeat.am.module.warehouse.services.crud.service;
        
        
    import com.jfeat.am.common.crud.CRUDServiceOnly;
    import com.jfeat.am.common.crud.CRUDServiceSlave;
    import com.jfeat.am.module.warehouse.services.persistence.model.WarehouseSlot;
import com.jfeat.am.common.constant.tips.Ids;

/**
 * <p>
 *  service interface
 * </p>
 *
 * @author Code Generator
 * @since 2018-06-22
 * Master: wms_warehouse
  * Slave : wms_warehouse_slot
  */
public interface CRUDWarehouseSlotService  extends CRUDServiceSlave<WarehouseSlot> {

}

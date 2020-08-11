package com.jfeat.am.module.warehouse.services.crud.service;

import com.jfeat.am.module.sku.services.persistence.model.SkuProduct;
import com.jfeat.am.module.warehouse.services.persistence.model.CheckSku;
import com.jfeat.am.module.warehouse.services.persistence.model.Check;
import com.jfeat.crud.plus.CRUDServicePeer;

/**
 * <p>
 * service interface
 * </p>
 *
 * @author Code Generator
 * @since 2018-06-23
 * Master: ${cfg.masterModel}
 * Slave : wms_check_sku
 */
public interface CRUDCheckSkuService extends CRUDServicePeer<Check, SkuProduct, CheckSku> {

}

package com.jfeat.am.module.sku.services.crud.service;
            import com.jfeat.am.common.crud.CRUDServicePeer;
import com.jfeat.am.module.sku.services.persistence.model.SkuSpecification;
import com.jfeat.am.module.sku.services.persistence.model.SkuProduct;
            import com.jfeat.am.module.sku.services.persistence.model.SkuSpecificationGroup;

/**
 * <p>
 *  service interface
 * </p>
 *
 * @author Code Generator
 * @since 2018-07-18
 * Master: t_sku_product
  * Slave : t_sku_specification
  */
public interface CRUDSkuSpecificationService  extends CRUDServicePeer<SkuProduct,SkuSpecificationGroup,SkuSpecification> {

}

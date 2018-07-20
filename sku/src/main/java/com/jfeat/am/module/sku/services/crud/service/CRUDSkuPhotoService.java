package com.jfeat.am.module.sku.services.crud.service;
            import com.jfeat.am.common.crud.CRUDServiceOnly;
            import com.jfeat.am.common.crud.CRUDServiceSlave;
            import com.jfeat.am.module.sku.services.persistence.model.SkuPhoto;
import com.jfeat.am.common.constant.tips.Ids;

/**
 * <p>
 *  service interface
 * </p>
 *
 * @author Code Generator
 * @since 2018-07-18
 * Master: t_sku_product
  * Slave : t_sku_photo
  */
public interface CRUDSkuPhotoService  extends CRUDServiceSlave<SkuPhoto> {

}

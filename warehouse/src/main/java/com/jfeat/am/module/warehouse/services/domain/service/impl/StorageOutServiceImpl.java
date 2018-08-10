package com.jfeat.am.module.warehouse.services.domain.service.impl;

import com.jfeat.am.common.exception.BusinessCode;
import com.jfeat.am.common.exception.BusinessException;
import com.jfeat.am.module.warehouse.services.crud.service.CRUDStorageOutService;
import com.jfeat.am.module.warehouse.services.domain.model.StorageOutModel;
import com.jfeat.am.module.warehouse.services.domain.service.StorageOutService;

import com.jfeat.am.module.warehouse.services.crud.service.impl.CRUDStorageOutServiceImpl;
import com.jfeat.am.module.warehouse.services.persistence.dao.InventoryMapper;
import com.jfeat.am.module.warehouse.services.persistence.model.Inventory;
import com.jfeat.am.module.warehouse.services.persistence.model.StorageInItem;
import com.jfeat.am.module.warehouse.services.persistence.model.StorageOutItem;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.Resource;
import java.util.Date;

/**
 * <p>
 * 服务实现类
 * </p>
 *
 * @author admin
 * @since 2017-10-16
 */
@Service("crudStorageOutService")
public class StorageOutServiceImpl extends CRUDStorageOutServiceImpl implements StorageOutService {


    @Resource
    CRUDStorageOutService crudStorageOutService;
    @Resource
    InventoryMapper inventoryMapper;

    /**
     * SKU 肯定 存在  需要 判断 他的 可用量 是否 大于 出库的数量
     */
    @Transactional
    public Integer createStorageOut(long userId, StorageOutModel entity) {
        Integer affected = 0;
        entity.setOriginatorId(userId);
        if (entity.getTransactionBy() == null) {
            entity.setTransactionBy(userId);
        }
        entity.setTransactionTime(new Date());
        if (entity.getStorageOutItems() != null && entity.getStorageOutItems().size() > 0) {
            for (StorageOutItem outItem : entity.getStorageOutItems()) {
                Inventory isExistInventory = new Inventory();
                isExistInventory.setSkuId(outItem.getSkuId());
                isExistInventory.setWarehouseId(entity.getWarehouseId());
                Inventory originInventory = inventoryMapper.selectOne(isExistInventory);
                if (originInventory != null) {
                    if(outItem.getTransactionQuantities() > originInventory.getValidSku()){
                        throw new BusinessException(4050,"库存不足,"+ "现有库存"+ originInventory.getValidSku() +"小于出库量"+outItem.getTransactionQuantities());
                    }else {
                        originInventory.setValidSku(originInventory.getValidSku() - outItem.getTransactionQuantities());
                        affected += inventoryMapper.updateById(originInventory);
                    }
                } else {
                    throw new BusinessException(4051,"产品不存在，请核对！");
                }
            }
        }else {
            throw new BusinessException(4050,"商品不能为空，请先选择商品！");
        }
        affected = crudStorageOutService.createMaster(entity, null, null, null);
        return affected;
    }
}

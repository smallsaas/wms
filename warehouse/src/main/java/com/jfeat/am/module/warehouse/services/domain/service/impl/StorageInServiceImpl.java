package com.jfeat.am.module.warehouse.services.domain.service.impl;

import com.jfeat.am.common.exception.BusinessCode;
import com.jfeat.am.common.exception.BusinessException;
import com.jfeat.am.module.warehouse.services.crud.filter.StorageInFilter;
import com.jfeat.am.module.warehouse.services.crud.service.CRUDStorageInService;
import com.jfeat.am.module.warehouse.services.domain.dao.QueryInventoryDao;
import com.jfeat.am.module.warehouse.services.domain.model.StorageInModel;
import com.jfeat.am.module.warehouse.services.domain.model.StorageModel;
import com.jfeat.am.module.warehouse.services.domain.service.InventoryService;
import com.jfeat.am.module.warehouse.services.domain.service.StorageInService;

import com.jfeat.am.module.warehouse.services.crud.service.impl.CRUDStorageInServiceImpl;
import com.jfeat.am.module.warehouse.services.persistence.dao.InventoryMapper;
import com.jfeat.am.module.warehouse.services.persistence.model.Inventory;
import com.jfeat.am.module.warehouse.services.persistence.model.StorageInItem;
import org.springframework.dao.DuplicateKeyException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.Resource;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

/**
 * <p>
 * 服务实现类
 * </p>
 *
 * @author admin
 * @since 2017-10-16
 */
@Service("crudStorageInService")
public class StorageInServiceImpl extends CRUDStorageInServiceImpl implements StorageInService {

    @Resource
    CRUDStorageInService crudStorageInService;
    @Resource
    InventoryMapper inventoryMapper;
    @Resource
    QueryInventoryDao queryInventoryDao;


    /**
     * 假设 该提交的 SKU 不存在 库存 ，则插入 ，如果存在 ，则更新
     */
    @Transactional
    public Integer createStorageIn(long userId, StorageInModel entity) {
        Integer affected = 0;
        entity.setOriginatorId(userId);
        entity.setTransactionTime(new Date());
        StorageInFilter storageInFilter = new StorageInFilter();
        List<StorageInItem> storageInItems = new ArrayList<>();
        if (entity.getStorageInItems() != null && entity.getStorageInItems().size() > 0) {
            for (StorageInItem inItem : entity.getStorageInItems()) {
                inItem.setRelationCode(entity.getTransactionCode());

                // 设置产品的入库时间
                inItem.setTransactionTime(entity.getTransactionTime());

                Inventory isExistInventory = new Inventory();
                isExistInventory.setSkuId(inItem.getSkuId());
                isExistInventory.setWarehouseId(entity.getWarehouseId());
                Inventory originInventory = inventoryMapper.selectOne(isExistInventory);
                if (originInventory != null) {
                    //插入操作后的库存数量 原来数量+准备入库数量
                    Integer afterSkuCount = originInventory.getValidSku() + inItem.getTransactionQuantities();
                    inItem.setAfterTransactionQuantities(afterSkuCount);

                    originInventory.setValidSku(afterSkuCount);
                    affected += inventoryMapper.updateById(originInventory);
                } else {
                    //插入操作后的库存数量 == 准备入库的数量 原来的不存在
                    inItem.setAfterTransactionQuantities(inItem.getTransactionQuantities());

                    isExistInventory.setWarehouseId(entity.getWarehouseId());
                    isExistInventory.setValidSku(inItem.getTransactionQuantities());
                    affected += inventoryMapper.insert(isExistInventory);
                }
                storageInItems.add(inItem);
            }
        }else {
            throw new BusinessException(4050,"商品不能为空，请先选择商品！");
        }
        entity.setStorageInItems(storageInItems);
        affected = crudStorageInService.createMaster(entity, storageInFilter, null, null);
        return affected;
    }

}

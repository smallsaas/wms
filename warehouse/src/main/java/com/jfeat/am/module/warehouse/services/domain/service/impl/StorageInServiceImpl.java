package com.jfeat.am.module.warehouse.services.domain.service.impl;

import com.baomidou.mybatisplus.mapper.EntityWrapper;
import com.jfeat.am.common.exception.BusinessCode;
import com.jfeat.am.common.exception.BusinessException;
import com.jfeat.am.module.warehouse.services.crud.filter.StorageInFilter;
import com.jfeat.am.module.warehouse.services.crud.service.CRUDStorageInService;
import com.jfeat.am.module.warehouse.services.definition.StorageInStatus;
import com.jfeat.am.module.warehouse.services.domain.dao.QueryInventoryDao;
import com.jfeat.am.module.warehouse.services.domain.model.StorageInModel;
import com.jfeat.am.module.warehouse.services.domain.model.StorageModel;
import com.jfeat.am.module.warehouse.services.domain.service.InventoryService;
import com.jfeat.am.module.warehouse.services.domain.service.StorageInService;

import com.jfeat.am.module.warehouse.services.crud.service.impl.CRUDStorageInServiceImpl;
import com.jfeat.am.module.warehouse.services.persistence.dao.InventoryMapper;
import com.jfeat.am.module.warehouse.services.persistence.dao.StorageInItemMapper;
import com.jfeat.am.module.warehouse.services.persistence.model.Inventory;
import com.jfeat.am.module.warehouse.services.persistence.model.StorageIn;
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
    StorageInItemMapper inItemMapper;

    public Integer changeStatus(Long userId,Long storageInId,StorageInModel entity ){

        Integer affected = 0;

        affected += inItemMapper.delete(new EntityWrapper<StorageInItem>()
                .eq(StorageInItem.STORAGE_IN_ID,storageInId)
                .eq(StorageInItem.TYPE,"Others"));


        entity.setOriginatorId(userId);
        entity.setTransactionTime(new Date());
        if (entity.getStorageInTime() == null) {
            entity.setStorageInTime(new Date());
        }
        StorageInFilter storageInFilter = new StorageInFilter();
        List<StorageInItem> storageInItems = new ArrayList<>();

        if (entity.getStorageInItems() != null && entity.getStorageInItems().size() > 0) {

            for (StorageInItem inItem : entity.getStorageInItems()) {
                if (inItem.getTransactionQuantities() > 0) {
                    inItem.setRelationCode(entity.getTransactionCode());
                    inItem.setTransactionTime(entity.getStorageInTime());
                    // 设置产品的入库时间
                    inItem.setTransactionTime(entity.getTransactionTime());

                    storageInItems.add(inItem);
                } else {

                    //while transaction quantities = 0 ,do nothing
                }
            }
        } else {
            throw new BusinessException(4050, "商品不能为空，请先选择商品！");
        }
        entity.setStorageInItems(storageInItems);
        affected += crudStorageInService.createMaster(entity, storageInFilter, null, null);
        return affected;

    }

    /**
     *  while create , storage in  status is Draft;
     * */
    @Transactional
    public Integer createStorageIn(long userId, StorageInModel entity) {
        Integer affected = 0;
        entity.setOriginatorId(userId);
        entity.setTransactionTime(new Date());
        if (entity.getStorageInTime() == null) {
            entity.setStorageInTime(new Date());
        }
        StorageInFilter storageInFilter = new StorageInFilter();
        List<StorageInItem> storageInItems = new ArrayList<>();
        if (entity.getStorageInItems() != null && entity.getStorageInItems().size() > 0) {
            for (StorageInItem inItem : entity.getStorageInItems()) {
                if (inItem.getTransactionQuantities() > 0) {
                    inItem.setRelationCode(entity.getTransactionCode());
                    inItem.setTransactionTime(entity.getStorageInTime());
                    // 设置产品的入库时间
                    inItem.setTransactionTime(entity.getTransactionTime());

                    storageInItems.add(inItem);
                } else {

                    //while transaction quantities = 0 ,do nothing
                }
            }
        } else {
            throw new BusinessException(4050, "商品不能为空，请先选择商品！");
        }
        entity.setStorageInItems(storageInItems);
        entity.setStatus(StorageInStatus.Draft.toString());
        affected = crudStorageInService.createMaster(entity, storageInFilter, null, null);
        return affected;
    }

    /**
     *  update by do not modified status
     * */
    @Transactional
    public Integer updateStorageIn(long userId,Long storageInId,StorageInModel entity) {
        entity.setStatus(StorageInStatus.Draft.toString());
        return changeStatus(userId,storageInId,entity);
    }

    /**
     *  commit and wait to audit
     * */
    @Transactional
    public Integer commitStorageIn(long userId,Long storageInId,StorageInModel entity) {
        entity.setStatus(StorageInStatus.Wait_To_audit.toString());
        return changeStatus(userId,storageInId,entity);
    }

    /**
     *  audit passed
     * */
    @Transactional
    public Integer passedStorageIn(long userId,Long storageInId,StorageInModel entity) {
        entity.setStatus(StorageInStatus.Audit_Passed.toString());
        return changeStatus(userId,storageInId,entity);
    }

    /**
     *  audit rejected
     * */
    @Transactional
    public Integer auditRejectedStorageIn(long userId,Long storageInId,StorageInModel entity) {
        entity.setStatus(StorageInStatus.Cancel.toString());
        return changeStatus(userId,storageInId,entity);
    }


    /**
     * 假设 该提交的 SKU 不存在 库存 ，则插入 ，如果存在 ，则更新  execution storage in operation
     */
    @Transactional
    public Integer executionStorageIn(long userId, StorageInModel entity) {
        Integer affected = 0;
        entity.setOriginatorId(userId);
        entity.setTransactionTime(new Date());
        if (entity.getStorageInTime() == null) {
            entity.setStorageInTime(new Date());
        }
        StorageInFilter storageInFilter = new StorageInFilter();
        List<StorageInItem> storageInItems = new ArrayList<>();
        if (entity.getStorageInItems() != null && entity.getStorageInItems().size() > 0) {
            for (StorageInItem inItem : entity.getStorageInItems()) {
                if (inItem.getTransactionQuantities() > 0) {
                    inItem.setRelationCode(entity.getTransactionCode());
                    inItem.setTransactionTime(entity.getStorageInTime());
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
                } else {

                    //while transaction quantities = 0 ,do nothing
                }
            }
        } else {
            throw new BusinessException(4050, "商品不能为空，请先选择商品！");
        }
        entity.setStorageInItems(storageInItems);
        entity.setStatus(StorageInStatus.Done.toString());
        affected = crudStorageInService.createMaster(entity, storageInFilter, null, null);
        return affected;
    }

}

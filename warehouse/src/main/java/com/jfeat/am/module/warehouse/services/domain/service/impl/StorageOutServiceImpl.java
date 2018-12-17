package com.jfeat.am.module.warehouse.services.domain.service.impl;

import com.baomidou.mybatisplus.mapper.EntityWrapper;
import com.jfeat.am.common.exception.BusinessCode;
import com.jfeat.am.common.exception.BusinessException;
import com.jfeat.am.module.warehouse.services.crud.service.CRUDStorageOutService;
import com.jfeat.am.module.warehouse.services.definition.StorageOutStatus;
import com.jfeat.am.module.warehouse.services.domain.dao.QueryInventoryDao;
import com.jfeat.am.module.warehouse.services.domain.model.StorageOutModel;
import com.jfeat.am.module.warehouse.services.domain.service.StorageOutService;

import com.jfeat.am.module.warehouse.services.crud.service.impl.CRUDStorageOutServiceImpl;
import com.jfeat.am.module.warehouse.services.persistence.dao.InventoryMapper;
import com.jfeat.am.module.warehouse.services.persistence.dao.StorageOutItemMapper;
import com.jfeat.am.module.warehouse.services.persistence.model.Inventory;
import com.jfeat.am.module.warehouse.services.persistence.model.StorageInItem;
import com.jfeat.am.module.warehouse.services.persistence.model.StorageOut;
import com.jfeat.am.module.warehouse.services.persistence.model.StorageOutItem;
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
@Service("crudStorageOutService")
public class StorageOutServiceImpl extends CRUDStorageOutServiceImpl implements StorageOutService {


    @Resource
    CRUDStorageOutService crudStorageOutService;
    @Resource
    InventoryMapper inventoryMapper;
    @Resource
    QueryInventoryDao queryInventoryDao;
    @Resource
    StorageOutItemMapper outItemMapper;


    public Integer changeStatus(Long userId, Long storageOutId, StorageOutModel entity) {

        Integer affected = 0;

        affected += outItemMapper.delete(new EntityWrapper<StorageOutItem>()
                .eq(StorageOutItem.STORAGE_OUT_ID, storageOutId)
                .eq(StorageOutItem.TYPE, "Others"));

        entity.setOriginatorId(userId);
        entity.setTransactionTime(new Date());
        if (entity.getStorageOutTime() == null) {
            entity.setStorageOutTime(new Date());
        }

        if (entity.getStorageOutItems() != null && entity.getStorageOutItems().size() > 0) {

            for (StorageOutItem outItem : entity.getStorageOutItems()) {
                if (outItem.getTransactionQuantities() > 0) {
                    outItem.setRelationCode(entity.getTransactionCode());
                    outItem.setTransactionTime(entity.getStorageOutTime());
                    // 设置产品的入库时间
                    outItem.setTransactionTime(entity.getTransactionTime());

                    Inventory isExistInventory = new Inventory();
                    isExistInventory.setSkuId(outItem.getSkuId());
                    isExistInventory.setWarehouseId(entity.getWarehouseId());
                    Inventory originInventory = inventoryMapper.selectOne(isExistInventory);
                    if (originInventory != null) {
                        if (outItem.getTransactionQuantities() > originInventory.getValidSku()) {
                            throw new BusinessException(4050, "库存不足," + "现有库存" + originInventory.getValidSku() + "小于出库量" + outItem.getTransactionQuantities());
                        } else {

                            outItem.setType("Others");
                            affected += outItemMapper.insert(outItem);
                        }
                    } else {
                        throw new BusinessException(4051, "产品不存在，请核对！");
                    }

                } else {
                    //while transaction quantities = 0 ,do nothing
                }
            }
        } else {
            throw new BusinessException(4050, "商品不能为空，请先选择商品！");
        }
        return affected;

    }

    /**
     * while create , storage out  status is Draft;
     */
    public Integer draftStorageOut(Long userId, StorageOutModel entity) {

        Integer affected = 0;

        entity.setOriginatorId(userId);
        entity.setTransactionTime(new Date());
        if (entity.getStorageOutTime() == null) {
            entity.setStorageOutTime(new Date());
        }

        List<StorageOutItem> storageOutItems = new ArrayList<>();

        if (entity.getStorageOutItems() != null && entity.getStorageOutItems().size() > 0) {
            for (StorageOutItem outItem : entity.getStorageOutItems()) {
                if (outItem.getTransactionQuantities() > 0) {
                    outItem.setRelationCode(entity.getTransactionCode());
                    outItem.setTransactionTime(entity.getStorageOutTime());
                    Inventory isExistInventory = new Inventory();
                    isExistInventory.setSkuId(outItem.getSkuId());
                    isExistInventory.setWarehouseId(entity.getWarehouseId());
                    Inventory originInventory = inventoryMapper.selectOne(isExistInventory);
                    if (originInventory != null) {
                        if (outItem.getTransactionQuantities() > originInventory.getValidSku()) {
                            throw new BusinessException(4050, "库存不足," + "现有库存" + originInventory.getValidSku() + "小于出库量" + outItem.getTransactionQuantities());
                        } else {

                            outItem.setType("Others");
                            affected += outItemMapper.insert(outItem);
                        }
                    } else {
                        throw new BusinessException(4051, "产品不存在，请核对！");
                    }

                    storageOutItems.add(outItem);

                } else {
                    //while transaction quantities = 0 ,do nothing
                }
            }
        } else {
            throw new BusinessException(4050, "商品不能为空，请先选择商品！");
        }
        entity.setStorageOutItems(storageOutItems);
        entity.setStatus(StorageOutStatus.Draft.toString());
        affected = crudStorageOutService.createMaster(entity, null, null, null);
        return affected;
    }

    /**
     * update by do not modified status
     */
    @Transactional
    public Integer updateStorageIn(Long userId, Long storageOutId, StorageOutModel entity) {

        Integer affected = 0;
        StorageOut out = crudStorageOutService.retrieveMaster(storageOutId);
        if (out.getStatus().compareTo(StorageOutStatus.Draft.toString()) != 0) {
            throw new BusinessException(BusinessCode.ErrorStatus);
        }

        entity.setStatus(StorageOutStatus.Wait_To_audit.toString());
        affected += changeStatus(userId, storageOutId, entity);
        entity.setId(storageOutId);
        affected += crudStorageOutService.updateMaster(entity);

        return affected;

    }

    /**
     * commit and wait to audit
     */
    @Transactional
    public Integer commitStorageOut(Long userId, Long storageOutId, StorageOutModel entity) {
        Integer affected = 0;



        StorageOut out = crudStorageOutService.retrieveMaster(storageOutId);
        if (out.getStatus().compareTo(StorageOutStatus.Draft.toString()) != 0) {
            throw new BusinessException(BusinessCode.ErrorStatus);
        }
        entity.setStatus(StorageOutStatus.Wait_To_audit.toString());
        entity.setId(storageOutId);

        affected += changeStatus(userId, storageOutId, entity);


        affected += crudStorageOutService.updateMaster(entity);
        return affected;
    }


    /**
     * audit passed
     */
    @Transactional
    public Integer passedStorageOut(Long storageOutId) {
        StorageOut out = crudStorageOutService.retrieveMaster(storageOutId);
        if (out.getStatus().compareTo(StorageOutStatus.Draft.toString()) != 0) {
            throw new BusinessException(BusinessCode.ErrorStatus);
        }
        out.setStatus(StorageOutStatus.Audit_Passed.toString());
        out.setId(storageOutId);
        return crudStorageOutService.updateMaster(out);
    }

    /**
     * audit rejected
     */
    @Transactional
    public Integer auditRejectedStorageOut(Long storageOutId) {
        StorageOut out = crudStorageOutService.retrieveMaster(storageOutId);
        if (out.getStatus().compareTo(StorageOutStatus.Draft.toString()) != 0) {
            throw new BusinessException(BusinessCode.ErrorStatus);
        }
        out.setStatus(StorageOutStatus.Cancel.toString());
        out.setId(storageOutId);
        return crudStorageOutService.updateMaster(out);
    }


    /**
     * SKU 肯定 存在  需要 判断 他的 可用量 是否 大于 出库的数量
     */
    @Transactional
    public Integer executionStorageOut(String uasername, Long storageOutId) {

        StorageOut out = crudStorageOutService.retrieveMaster(storageOutId);


        if (out.getStatus().compareTo(StorageOutStatus.Audit_Passed.toString())!= 0){
            throw new BusinessException(BusinessCode.ErrorStatus);
        }

        Integer affected = 0;
        out.setTransactionBy(uasername);
        out.setTransactionTime(new Date());
        if (out.getStorageOutTime() == null) {
            out.setStorageOutTime(new Date());
        }

        List<StorageOutItem> items = outItemMapper.selectList(new EntityWrapper<StorageOutItem>()
                .eq(StorageOutItem.STORAGE_OUT_ID, storageOutId)
                .eq(StorageOutItem.TYPE, "Others"));

        if (items != null && items.size() > 0) {
            for (StorageOutItem outItem : items) {
                if (outItem.getTransactionQuantities() > 0) {

                    Inventory isExistInventory = new Inventory();
                    isExistInventory.setSkuId(outItem.getSkuId());
                    isExistInventory.setWarehouseId(out.getWarehouseId());
                    Inventory originInventory = inventoryMapper.selectOne(isExistInventory);

                    Integer afterCount = originInventory.getValidSku() - outItem.getTransactionQuantities();
                    outItem.setAfterTransactionQuantities(afterCount);
                    originInventory.setValidSku(afterCount);
                    affected += inventoryMapper.updateById(originInventory);

                } else {
                    //while transaction quantities = 0 ,do nothing
                }
            }
        } else {
            throw new BusinessException(4050, "商品不能为空，请先选择商品！");
        }
        out.setStatus(StorageOutStatus.Done.toString());
        affected = crudStorageOutService.createMaster(out);
        return affected;
    }
}

package com.jfeat.am.module.warehouse.services.domain.service.impl;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.baomidou.mybatisplus.mapper.EntityWrapper;
import com.baomidou.mybatisplus.toolkit.IdWorker;
import com.jfeat.am.common.crud.CRUDObject;
import com.jfeat.am.common.exception.BusinessException;
import com.jfeat.am.modular.system.service.UserService;
import com.jfeat.am.module.sku.services.persistence.dao.SkuProductMapper;
import com.jfeat.am.module.sku.services.persistence.model.SkuProduct;
import com.jfeat.am.module.warehouse.services.crud.filter.StorageInFilter;
import com.jfeat.am.module.warehouse.services.crud.service.CRUDProcurementService;
import com.jfeat.am.module.warehouse.services.crud.service.CRUDStorageInService;
import com.jfeat.am.module.warehouse.services.definition.ProcurementStatus;
import com.jfeat.am.module.warehouse.services.definition.TransactionType;
import com.jfeat.am.module.warehouse.services.domain.model.ProcurementItemRecord;
import com.jfeat.am.module.warehouse.services.domain.model.ProcurementModel;
import com.jfeat.am.module.warehouse.services.domain.model.StorageInModel;
import com.jfeat.am.module.warehouse.services.domain.service.ProcurementService;

import com.jfeat.am.module.warehouse.services.crud.service.impl.CRUDProcurementServiceImpl;
import com.jfeat.am.module.warehouse.services.domain.service.StorageInService;
import com.jfeat.am.module.warehouse.services.persistence.dao.*;
import com.jfeat.am.module.warehouse.services.persistence.model.*;
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
@Service("procurementService")
public class ProcurementServiceImpl extends CRUDProcurementServiceImpl implements ProcurementService {

    @Resource
    StorageInService storageInService;
    @Resource
    CRUDProcurementService procurementService;
    @Resource
    ProcurementMapper procurementMapper;
    @Resource
    StorageInItemMapper storageInItemMapper;
    @Resource
    StorageInMapper storageInMapper;
    @Resource
    InventoryMapper inventoryMapper;
    @Resource
    CRUDStorageInService crudStorageInService;
    @Resource
    SkuProductMapper skuProductMapper;
    @Resource
    SuppliersMapper suppliersMapper;

    /**
     * 重构 procurement 问题
     */
    @Transactional
    public Integer addProcurement(Long userId, ProcurementModel model) {

        int affected = 0;

        model.setOperator(userId);
        model.setOriginatorId(userId);
        model.setTransactionTime(new Date());
        model.setProcureStatus(ProcurementStatus.WaitForStorageIn.toString());
        affected += procurementMapper.insert(model);
        if (model.getItems() == null || model.getItems().size() == 0) {
            throw new BusinessException(5000, "请先选择需要采购的商品！");
        }
        for (StorageInItem item : model.getItems()) {
            item.setStorageInId(model.getId());
            item.setType(TransactionType.Procurement.toString());
            affected += storageInItemMapper.insert(item);
        }
        return affected;
    }


    /**
     * 执行入库 可以多次执行 ，但是不能超出总和
     */
    @Transactional
    public Integer executionStorageIn(Long userId, Long procurementId, ProcurementModel model) {

        int affected = 0;
        int inSuccess = 0;
        model.setId(procurementId);
   /*     List<StorageInItem> items = storageInItemMapper.selectList(new EntityWrapper<StorageInItem>().eq(StorageInItem.STORAGE_IN_ID, procurementId)
                .eq(StorageInItem.TYPE, TransactionType.Procurement.toString()));
*/
        if (model.getItems() != null && model.getItems().size() > 0) {

            StorageInModel in = new StorageInModel();
            in.setOriginatorId(userId);
            in.setTransactionBy(userId);
            in.setTransactionTime(new Date());

            // 使用field1去接收 warehouseId 字段
            in.setWarehouseId(Long.valueOf(model.getField1()));

            //使用 field 去接收 入库 code
            in.setTransactionCode(model.getField2());

            in.setProcurementId(procurementId);
            in.setTransactionType(TransactionType.Procurement.toString());
            StorageInFilter storageInFilter = new StorageInFilter();
            in.setStorageInItems(model.getItems());

            for (StorageInItem item : model.getItems()) {
                Inventory isExistInventory = new Inventory();
                isExistInventory.setSkuId(item.getSkuId());
                isExistInventory.setWarehouseId(Long.valueOf(model.getField1()));
                Inventory originInventory = inventoryMapper.selectOne(isExistInventory);

                if (originInventory != null) {
                    originInventory.setValidSku(originInventory.getValidSku() + item.getTransactionQuantities());
                    affected += inventoryMapper.updateById(originInventory);

                } else {
                    isExistInventory.setTransmitQuantities(item.getTransactionQuantities());
                    isExistInventory.setAdvanceQuantities(0);
                    isExistInventory.setMinInventory(0);
                    isExistInventory.setMaxInventory(item.getTransactionQuantities());
                    affected += inventoryMapper.insert(isExistInventory);
                }
            }

            inSuccess = crudStorageInService.createMaster(in, storageInFilter, null, null);
            if (inSuccess > 0) {
                model.setProcureStatus(ProcurementStatus.SectionStorageIn.toString());
                affected += procurementMapper.updateById(model);
            }
        }
        affected += procurementMapper.updateById(model);
        return affected;
    }

    /**
     * 详情
     */
    @Transactional
    public ProcurementModel procurementDetails(Long procurementId) {

        Procurement procurement = procurementMapper.selectById(procurementId);

        JSONObject object = JSON.parseObject(JSON.toJSONString(procurement));

        Suppliers suppliers = suppliersMapper.selectById(procurement.getSupplierId());

        object.put("supplierName", suppliers == null ? null : suppliers.getSupplierName());

        //采购的商品
        List<StorageInItem> items = storageInItemMapper.selectList(new EntityWrapper<StorageInItem>()
                .eq(StorageInItem.TYPE, TransactionType.Procurement.toString()).eq(StorageInItem.STORAGE_IN_ID, procurementId));

        List<ProcurementItemRecord> records = new ArrayList<>();

        if (items != null && items.size() > 0) {

            // 采购的 商品
            for (StorageInItem item : items) {
                int remainderCount = item.getTransactionQuantities();
                int sectionCount = 0;
                ProcurementItemRecord record = new ProcurementItemRecord();

                SkuProduct sku = skuProductMapper.selectById(item.getSkuId());
                record.setSkuCode(sku.getSkuCode());
                record.setSkuName(sku.getSkuName());
                record.setSkuBarcode(sku.getBarCode());
                record.setId(item.getId());
                record.setTransactionTime(item.getTransactionTime());
                record.setTransactionQuantities(item.getTransactionQuantities());
                record.setSkuUnit(sku.getField1());
                record.setTransactionSkuPrice(item.getTransactionSkuPrice());
                // 入库 记录
                List<StorageIn> ins = storageInMapper.selectList(new EntityWrapper<StorageIn>().eq(StorageIn.PROCUREMENT_ID, procurement)
                        .eq(StorageIn.TRANSACTION_TYPE, TransactionType.Procurement.toString()));
                if (ins != null && ins.size() > 0) {
                    // 有入库记录
                    // 查找 入库 记录下已经入库的商品及数量
                    for (StorageIn in : ins) {
                        // 查找是否存在 这个 商品已经入库
                        List<StorageInItem> originItems = storageInItemMapper.selectList(new EntityWrapper<StorageInItem>()
                                .eq(StorageInItem.STORAGE_IN_ID, in.getId()).eq(StorageInItem.SKU_ID, item.getSkuId()));
                        if (originItems != null && originItems.size() > 0) {
                            for (StorageInItem originItem : originItems) {
                                // 入库数 以及 剩余 入库数
                                sectionCount += originItem.getTransactionQuantities();
                                remainderCount = remainderCount -sectionCount;
                            }
                        }
                    }

                } else {
                    // 无入库记录
                    record.setSectionInCount(sectionCount);
                    record.setRemainderCount(remainderCount);
                }
                records.add(record);
            }

        } else {
            // 无采购的商品

        }
        object.put("records", records);
        ProcurementModel model = JSON.parseObject(JSON.toJSONString(object), ProcurementModel.class);

        return model;

    }


    @Transactional
    public Integer deleteProcurement(Long id) {

        int affected = 0;
        Procurement procurement = procurementService.retrieveMaster(id);

        // 先删除 入库的产品
        affected += storageInItemMapper.delete(new EntityWrapper<StorageInItem>().eq(StorageInItem.STORAGE_IN_ID, procurement.getId()).like(StorageInItem.TYPE, TransactionType.Procurement.toString()));

        List<StorageIn> ins = storageInMapper.selectList(new EntityWrapper<StorageIn>().eq(StorageIn.PROCUREMENT_ID, procurement)
                .eq(StorageIn.TRANSACTION_TYPE, TransactionType.Procurement.toString()));

        if (ins != null && ins.size() > 0) {
            for (StorageIn in : ins) {
                affected += storageInItemMapper.delete(new EntityWrapper<StorageInItem>().eq(StorageInItem.STORAGE_IN_ID, in.getId()));
                affected += storageInMapper.deleteById(in.getId());
            }

        }
        return procurementService.deleteMaster(id);
    }

}

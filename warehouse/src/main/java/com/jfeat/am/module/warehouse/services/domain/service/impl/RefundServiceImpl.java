package com.jfeat.am.module.warehouse.services.domain.service.impl;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.baomidou.mybatisplus.mapper.EntityWrapper;
import com.jfeat.am.common.exception.BusinessCode;
import com.jfeat.am.common.exception.BusinessException;
import com.jfeat.am.core.jwt.JWTKit;
import com.jfeat.am.module.sku.services.persistence.dao.SkuProductMapper;
import com.jfeat.am.module.sku.services.persistence.model.SkuProduct;
import com.jfeat.am.module.warehouse.services.crud.filter.StorageInFilter;
import com.jfeat.am.module.warehouse.services.crud.filter.StorageOutFilter;
import com.jfeat.am.module.warehouse.services.crud.service.CRUDRefundService;
import com.jfeat.am.module.warehouse.services.crud.service.CRUDStorageInService;
import com.jfeat.am.module.warehouse.services.definition.ItemEnumType;
import com.jfeat.am.module.warehouse.services.definition.RefundStatus;
import com.jfeat.am.module.warehouse.services.definition.StorageOutStatus;
import com.jfeat.am.module.warehouse.services.definition.TransactionType;
import com.jfeat.am.module.warehouse.services.domain.dao.QueryInventoryDao;
import com.jfeat.am.module.warehouse.services.domain.dao.QueryProcurementDao;
import com.jfeat.am.module.warehouse.services.domain.dao.QueryRefundDao;
import com.jfeat.am.module.warehouse.services.domain.model.*;
import com.jfeat.am.module.warehouse.services.domain.service.RefundService;

import com.jfeat.am.module.warehouse.services.crud.service.impl.CRUDRefundServiceImpl;
import com.jfeat.am.module.warehouse.services.domain.service.StorageOutService;
import com.jfeat.am.module.warehouse.services.persistence.dao.*;
import com.jfeat.am.module.warehouse.services.persistence.model.*;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
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
@Service("refundService")
public class RefundServiceImpl extends CRUDRefundServiceImpl implements RefundService {


    @Resource
    StorageOutService storageOutService;
    @Resource
    StorageOutMapper storageOutMapper;
    @Resource
    CRUDRefundService refundService;
    @Resource
    CRUDStorageInService crudStorageInService;
    @Resource
    StorageOutItemMapper storageOutItemMapper;
    @Resource
    SkuProductMapper skuProductMapper;
    @Resource
    ProcurementMapper procurementMapper;
    @Resource
    InventoryMapper inventoryMapper;
    @Resource
    QueryRefundDao queryRefundDao;
    @Resource
    QueryInventoryDao queryInventoryDao;
    @Resource
    SuppliersMapper suppliersMapper;
    @Resource
    RefundMapper refundMapper;
    @Resource
    WarehouseMapper warehouseMapper;
    @Resource
    QueryProcurementDao queryProcurementDao;

    Logger logger = LoggerFactory.getLogger(ProcurementServiceImpl.class);



    public Integer createOrUpdate(Long refundId, RefundModel model) {
        int affected = 0;
        if (model.getItems() != null && model.getItems().size() > 0) {
            // execution delete before insert
            affected += storageOutItemMapper.delete(new EntityWrapper<StorageOutItem>()
                    .eq(StorageOutItem.STORAGE_OUT_ID, refundId)
                    .eq(StorageOutItem.TYPE, ItemEnumType.REFUND));
            for (StorageOutItemRecord outItem : model.getItems()) {
                SkuProduct sku = skuProductMapper.selectById(outItem.getSkuId());
                if (outItem.getTransactionQuantities() > 0) {
                    //新建或更新时将需求数量插入实际数量
                    outItem.setDemandQuantities(outItem.getTransactionQuantities());
                    // 仅仅保存数据，将采购的可退货数量插入到 该字段中，该字段在该逻辑下无特别的用途
                    if (outItem.getCanRefundCount() != null) {
                        outItem.setAfterTransactionQuantities(outItem.getCanRefundCount());
                    }
                    outItem.setRelationCode(model.getProductRefundCode());

                    Inventory isExistInventory = new Inventory();
                    isExistInventory.setSkuId(outItem.getSkuId());
                    isExistInventory.setWarehouseId(model.getProductRefundWarehouseId());
                    Inventory originInventory = inventoryMapper.selectOne(isExistInventory);
                    if (originInventory != null) {
                        // 退货数量不能大于库存量
                        if (originInventory.getValidSku() == null) {
                            originInventory.setValidSku(0);
                        }
                        if (model.getProductProcurementId() == null) {
                            //do nothings
                            outItem.setStorageOutId(refundId);
                            outItem.setDemandQuantities(outItem.getTransactionQuantities());
                            outItem.setType(ItemEnumType.REFUND.toString());
                            affected += storageOutItemMapper.insert(outItem);
                        } else {
                            if (outItem.getTransactionQuantities() > queryProcurementDao.storageInCount(model.getProductProcurementId(), outItem.getSkuId())) {
                                throw new BusinessException(4050, "\"" + sku.getSkuName() + "\"退货数量不能大于已入库的数量");
                            } else {
                                outItem.setStorageOutId(refundId);
                                outItem.setType(ItemEnumType.REFUND.toString());
                                outItem.setTransactionQuantities(outItem.getDemandQuantities());
                                affected += storageOutItemMapper.insert(outItem);
                            }
                        }

                    } else {
                        throw new BusinessException(4060, "该仓库不存在\"" + sku.getSkuName() + "\"商品");
                    }
                } else {
                    //do nothings//throw new BusinessException(5000, "提交失败，" + "\"" + sku.getSkuName() + "\"" + "商品退货数量不能为0");
                }
            }
        } else {
            throw new BusinessException(4055, "请先选择需要退货的商品！");
        }
        return affected;
    }

    /**
     * 重构 Refund 问题
     */
    @Transactional
    public Integer createRefund(Long userId, RefundModel model) {

        int affected = 0;

        model.setOriginatorId(userId);
        model.setTransactionTime(new Date());
        model.setProductRefundStatus(RefundStatus.Draft.toString());
        affected += refundMapper.insert(model);
        affected += createOrUpdate(model.getId(), model);
        return affected;
    }


    @Transactional
    public Integer updateRefund(Long refundId, RefundModel model) {
        int affected = 0;

        Refund refund = refundMapper.selectById(refundId);
        if (refund.getProductRefundStatus().compareTo(RefundStatus.Draft.toString()) != 0) {
            throw new BusinessException(BusinessCode.ErrorStatus);
        }
        model.setId(refundId);
        model.setOriginatorName(refund.getOriginatorName());
        model.setOriginatorId(refund.getOriginatorId());
        affected += createOrUpdate(model.getId(), model);
        affected += refundMapper.updateById(model);
        return affected;
    }

    @Transactional
    public Integer updateAndCommitRefund(Long refundId, RefundModel model) {
        int affected = 0;

        Refund refund = refundMapper.selectById(refundId);
        if (refund.getProductRefundStatus().compareTo(RefundStatus.Draft.toString()) != 0) {
            throw new BusinessException(BusinessCode.ErrorStatus);
        }
        model.setId(refundId);
        model.setProductRefundStatus(RefundStatus.Wait_To_Audit.toString());
        affected += createOrUpdate(model.getId(), model);
        model.setOriginatorName(refund.getOriginatorName());
        model.setOriginatorId(refund.getOriginatorId());
        affected += refundMapper.updateById(model);
        return affected;
    }

    @Transactional
    public Integer auditPassed(Long id, String username, Long userId, RefundModel model) {
        Integer affected = 0;
        Refund refund = refundMapper.selectById(id);
        if (refund == null) {
            throw new BusinessException(BusinessCode.FileNotFound);
        }
        if (refund.getProductRefundStatus().compareTo(RefundStatus.Wait_To_Audit.toString())!=0){
            throw new BusinessException(BusinessCode.ErrorStatus);
        }
        refund.setId(id);
//        refund.setProductRefundStatus(RefundStatus.Audit_Passed.toString());
        if (refund.getId() != null) {
            affected += refundService.updateMaster(model);
            for (StorageOutItem item : model.getItems()) {
                item.setType(ItemEnumType.REFUND.toString());
                storageOutItemMapper.updateById(item);
            }
            // 成功就立即执行入库，无须再调用执行入库的API
            affected += executionRefund(username, userId, id);
        }
        return affected;
    }

    /**
     * 重构 Refund 问题
     */
    @Transactional
    @Override
    public Integer executionRefund(String username, Long userId, Long refundId) {
        int affected = 0;
        int refundTotal = 0;
        Refund refund = refundMapper.selectById(refundId);
        // 因为是立即执行的退货，所以直接判断了 是 待审核状态，因为是同步问题
        if (refund.getProductRefundStatus().compareTo(RefundStatus.Wait_To_Audit.toString()) != 0) {
            throw new BusinessException(BusinessCode.ErrorStatus);
        }

        List<StorageOutItem> items = storageOutItemMapper.selectList(new EntityWrapper<StorageOutItem>()
                .eq(StorageOutItem.STORAGE_OUT_ID, refundId)
                .eq(StorageOutItem.TYPE, ItemEnumType.REFUND));

        StorageOutModel storageOutModel = new StorageOutModel();
        storageOutModel.setStorageOutTime(new Date());

        storageOutModel.setTransactionType(TransactionType.Refund.toString());
        storageOutModel.setWarehouseId(refund.getProductRefundWarehouseId());
        storageOutModel.setStatus(StorageOutStatus.Done.toString());

        // 用field1 来接收出库的code
        storageOutModel.setTransactionCode(refund.getField1());
        storageOutModel.setTransactionBy(username);
        storageOutModel.setOriginatorName(refund.getOriginatorName());
        storageOutModel.setOriginatorId(userId);
        storageOutModel.setTransactionBy(refund.getTransactionBy());
        storageOutModel.setTransactionTime(new Date());
        storageOutMapper.insert(storageOutModel);

        for (StorageOutItem outItem : items) {
            if (outItem.getTransactionQuantities() > 0) {
                StorageOutItem item = new StorageOutItem();
                item.setStorageOutId(storageOutModel.getId());
                item.setSkuId(outItem.getSkuId());
                item.setTransactionSkuPrice(outItem.getTransactionSkuPrice());
                item.setTransactionQuantities(outItem.getTransactionQuantities());
                item.setDemandQuantities(outItem.getDemandQuantities());
                item.setRelationCode(refund.getProductRefundCode());
                item.setTransactionTime(storageOutModel.getStorageOutTime());
                item.setType(ItemEnumType.STORAGEOUT.toString());
                refundTotal += outItem.getTransactionQuantities();

                Inventory isExistInventory = new Inventory();
                isExistInventory.setSkuId(outItem.getSkuId());
                isExistInventory.setWarehouseId(refund.getProductRefundWarehouseId());
                Inventory originInventory = inventoryMapper.selectOne(isExistInventory);
                if (originInventory != null) {
                    // 退货数量不能大于库存量
                    if (originInventory.getValidSku() == null) {
                        originInventory.setValidSku(0);
                    }
                    if (refund.getProductProcurementId() == null) {
                        item.setBeforeTransactionQuantities(originInventory.getValidSku());
                        logger.info("### refund ###:操作前的数量"+ originInventory.getValidSku());
                        //操作后的数量
                        Integer afterSkuCount = originInventory.getValidSku() - outItem.getTransactionQuantities();
                        item.setAfterTransactionQuantities(afterSkuCount);
                        originInventory.setValidSku(afterSkuCount);
                        logger.info("### refund ###:操作后的数量"+ originInventory.getValidSku());
                        affected += inventoryMapper.updateById(originInventory);
                    } else {
                        item.setBeforeTransactionQuantities(originInventory.getValidSku());
                        logger.info("### refund ###:操作前的数量"+ originInventory.getValidSku());
                        //操作后的数量
                        Integer afterSkuCount = originInventory.getValidSku() - outItem.getTransactionQuantities();
                        item.setAfterTransactionQuantities(afterSkuCount);
                        originInventory.setValidSku(afterSkuCount);
                        affected += inventoryMapper.updateById(originInventory);
                        logger.info("### refund ###:操作后的数量"+ originInventory.getValidSku());
                    }
                }
                storageOutItemMapper.insert(item);
            }
        }

        refund.setStorageOutId(storageOutModel.getId());
        refund.setTransactionBy(username);
        refund.setTransactionTime(new Date());
        refund.setProductRefundQuantities(refundTotal);
        refund.setProductRefundStatus(RefundStatus.Done.toString());
        refund.setId(refundId);
        affected += refundService.updateMaster(refund);
        // field1 去接收最上层的ID  作跳转使用
        storageOutModel.setField1(refund.getId().toString());
        storageOutModel.setId(refund.getStorageOutId());
        storageOutMapper.updateById(storageOutModel);
        return affected;
    }


    public RefundModel refundDetails(Long id) {
        Refund refund = refundService.retrieveMaster(id);
        if (refund == null) {
            throw new BusinessException(BusinessCode.FileNotFound);
        }
        JSONObject refundObj = JSON.parseObject(JSONObject.toJSONString(refund));

        if (refund.getProductRefundWarehouseId() != null) {
            refundObj.put("warehouseName", warehouseMapper.selectById(refund.getProductRefundWarehouseId()).getWarehouseName());
        }

        if (refund.getProductProcurementId() != null) {
            Procurement procurement = procurementMapper.selectById(refund.getProductProcurementId());
            refundObj.put("procurementCode", procurement.getProcurementCode());
            Suppliers suppliers = suppliersMapper.selectById(procurement.getSupplierId());
            refundObj.put("supplierName", suppliers.getSupplierName());
        } else {
            if (refund.getSupplierId() != null) {
                Suppliers suppliers = suppliersMapper.selectById(refund.getSupplierId());
                refundObj.put("supplierName", suppliers.getSupplierName());
            }
        }
        List<StorageOutItemRecord> outItemRecords = new ArrayList<>();

        List<StorageOutItem> outItems = storageOutItemMapper.selectList(new EntityWrapper<StorageOutItem>()
                .eq(StorageOutItem.STORAGE_OUT_ID, refund.getId())
                .eq(StorageOutItem.TYPE, ItemEnumType.REFUND));

        if (outItems != null && outItems.size() > 0) {
            for (StorageOutItem item : outItems) {
                // 出库 商品详情
                StorageOutItemRecord itemRecord = queryRefundDao.outItemRecord(item.getId());
                //插入 可退货数量
                itemRecord.setCanRefundCount(item.getAfterTransactionQuantities());
                outItemRecords.add(itemRecord);
            }
        }
        refundObj.put("items", outItemRecords);
        RefundModel model = JSONObject.parseObject(JSONObject.toJSONString(refundObj), RefundModel.class);
        return model;
    }


    @Transactional
    public Integer deleteRefund(Long id) {
        Refund refund = refundService.retrieveMaster(id);
        storageOutItemMapper.delete(new EntityWrapper<StorageOutItem>()
                .eq(StorageOutItem.STORAGE_OUT_ID, id)
                .eq(StorageOutItem.TYPE,ItemEnumType.REFUND));
        storageOutItemMapper.delete(new EntityWrapper<StorageOutItem>()
                .eq(StorageOutItem.STORAGE_OUT_ID, refund.getStorageOutId())
                .eq(StorageOutItem.TYPE, ItemEnumType.STORAGEOUT));
        storageOutMapper.deleteById(refund.getStorageOutId());
        return refundService.deleteMaster(id);
    }


    /**
     * @Param Long refundId
     * 退货作废
     */
    @Transactional
    public Integer cancelRefund(Long userId, Long refundId) {
        int affected = 0;
        Refund refund = refundService.retrieveMaster(refundId);
        StorageInModel storageIn = new StorageInModel();
        storageIn.setStorageInTime(new Date());

        StorageOut out = storageOutMapper.selectById(refund.getStorageOutId());
        List<StorageInItem> storageInItems = new ArrayList<>();
        List<StorageOutItem> storageOutItems = storageOutItemMapper.selectList(new EntityWrapper<StorageOutItem>()
                .eq(StorageOutItem.STORAGE_OUT_ID, out.getId()).eq(StorageOutItem.RELATION_CODE, refund.getProductRefundCode()));
        if (storageOutItems == null && storageOutItems.size() == 0) {
            throw new BusinessException(5000, "未知错误");
        }
        for (StorageOutItem outItem : storageOutItems) {
            Inventory isExistInventory = new Inventory();
            isExistInventory.setSkuId(outItem.getSkuId());
            isExistInventory.setWarehouseId(refund.getProductRefundWarehouseId());
            Inventory originInventory = inventoryMapper.selectOne(isExistInventory);
            if (originInventory == null) {
                // 应该不会出现吧 出现就是数据库异常吧
                throw new BusinessException(BusinessCode.DatabaseConnectFailure);
            }
            Integer validSku = originInventory.getValidSku();
            validSku += outItem.getTransactionQuantities();
            originInventory.setValidSku(validSku);

            StorageInItem item = new StorageInItem();
            item.setRelationCode(refund.getProductRefundCode());
            item.setTransactionTime(storageIn.getStorageInTime());

            item.setTransactionQuantities(outItem.getTransactionQuantities());
            item.setTransactionSkuPrice(outItem.getTransactionSkuPrice());
            item.setSkuId(outItem.getSkuId());
            item.setType("Others");
            // 操作后的数量
            item.setAfterTransactionQuantities(validSku);
            item.setTransactionTime(new Date());
            storageInItems.add(item);

            affected += inventoryMapper.updateAllColumnById(originInventory);
        }
        storageIn.setTransactionType(TransactionType.OthersStorageIn.toString());
        storageIn.setWarehouseId(refund.getProductRefundWarehouseId());
        storageIn.setOriginatorId(userId);
        // needs code ?
        storageIn.setTransactionCode(refund.getField1().replace("OUT", "IN"));
        storageIn.setTransactionTime(new Date());
        StorageInFilter storageInFilter = new StorageInFilter();
        storageIn.setStorageInItems(storageInItems);
        // field1 去接收最上层的ID  作跳转使用
        storageIn.setField1(refundId.toString());

        affected += crudStorageInService.createMaster(storageIn, storageInFilter, null, null);
        refund.setProductRefundStatus(RefundStatus.Closed.toString());
        affected += refundService.updateMaster(refund);
        return affected;
    }

}

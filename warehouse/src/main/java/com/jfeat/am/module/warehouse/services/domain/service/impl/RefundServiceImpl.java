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
import com.jfeat.am.module.warehouse.services.definition.RefundStatus;
import com.jfeat.am.module.warehouse.services.definition.TransactionType;
import com.jfeat.am.module.warehouse.services.domain.dao.QueryInventoryDao;
import com.jfeat.am.module.warehouse.services.domain.dao.QueryRefundDao;
import com.jfeat.am.module.warehouse.services.domain.model.*;
import com.jfeat.am.module.warehouse.services.domain.service.RefundService;

import com.jfeat.am.module.warehouse.services.crud.service.impl.CRUDRefundServiceImpl;
import com.jfeat.am.module.warehouse.services.domain.service.StorageOutService;
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


    public Integer createOrUpdate(Long refundId, RefundModel model) {


        int affected = 0;

        if (model.getItems() != null && model.getItems().size() > 0) {
            // execution delete before insert
            affected += storageOutItemMapper.delete(new EntityWrapper<StorageOutItem>()
                                        .eq(StorageOutItem.STORAGE_OUT_ID,refundId)
                                        .eq(StorageOutItem.TYPE,TransactionType.Refund.toString()));

            for (StorageOutItem outItem : model.getItems()) {

                SkuProduct sku = skuProductMapper.selectById(outItem.getSkuId());
                if (outItem.getTransactionQuantities() > 0) {
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
                            outItem.setType(TransactionType.Refund.toString());
                            affected += storageOutItemMapper.insert(outItem);

                        } else {
                            if (outItem.getTransactionQuantities() > queryRefundDao.skuStorageInCount(model.getProductProcurementId(), outItem.getSkuId())) {
                                throw new BusinessException(4050, "\"" + sku.getSkuName() + "\"退货数量不能大于入库的数量");
                            } else {
                                outItem.setStorageOutId(refundId);
                                outItem.setType(TransactionType.Refund.toString());
                                affected += storageOutItemMapper.insert(outItem);
                            }
                        }

                    } else {
                        throw new BusinessException(4060, "该仓库不存在\"" + sku.getSkuName() + "\"商品");
                    }
                } else {
                    throw new BusinessException(5000, "提交失败，" + "\"" + sku.getSkuName() + "\"" + "商品退货数量不能为0");
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
        affected += createOrUpdate(model.getId(),model);
        return affected;
    }


    @Transactional
    public Integer updateRefund(Long refundId, RefundModel model) {
        int affected = 0;

        Refund refund = refundMapper.selectById(refundId);
        if (refund.getProductRefundStatus().compareTo(RefundStatus.Draft.toString())!= 0){
            throw new BusinessException(BusinessCode.ErrorStatus);
        }

        model.setId(refundId);
        model.setProductRefundStatus(RefundStatus.Draft.toString());
        affected += createOrUpdate(model.getId(),model);
        affected += refundMapper.updateById(model);
        return affected;
    }

    @Transactional
    public Integer updateAndCommitRefund(Long refundId, RefundModel model) {
        int affected = 0;

        Refund refund = refundMapper.selectById(refundId);
        if (refund.getProductRefundStatus().compareTo(RefundStatus.Draft.toString())!= 0){
            throw new BusinessException(BusinessCode.ErrorStatus);
        }

        model.setId(refundId);
        model.setProductRefundStatus(RefundStatus.Wait_To_Audit.toString());
        affected += createOrUpdate(model.getId(),model);
        affected += refundMapper.updateById(model);
        return affected;
    }

    @Transactional
    public Integer auditPassed(Long id,String username,Long userId){
        Integer affected = 0;
        Refund refund = new Refund();
        refund.setId(id);
        refund.setProductRefundStatus(RefundStatus.Audit_Passed.toString());
        if(refund.getId() != null) {
            affected += refundService.updateMaster(refund);
            affected += executionRefund(username,userId,id);
        }
        return affected;
    }

    /**
     * 重构 Refund 问题
     */
    @Transactional
    @Override
    public Integer executionRefund(String username,Long userId, Long refundId) {
        /**
         * 1.先执行生成出库单，然后拿到入库单的 id 插入的采购的表单中
         * 2.
         * */
        int affected = 0;
        int refundTotal = 0;
        Refund refund = refundMapper.selectById(refundId);
        if (refund.getProductRefundStatus().compareTo(RefundStatus.Audit_Passed.toString())!= 0){
            throw new BusinessException(BusinessCode.ErrorStatus);
        }

        List<StorageOutItem> items = storageOutItemMapper.selectList(new EntityWrapper<StorageOutItem>()
                .eq(StorageOutItem.STORAGE_OUT_ID,refundId)
                .eq(StorageOutItem.TYPE,TransactionType.Refund.toString()));

        StorageOutModel storageOutModel = new StorageOutModel();
        storageOutModel.setStorageOutTime(new Date());

        List<StorageOutItem> storageOutItems = new ArrayList<>();
        if (items != null && items.size() > 0) {
            for (StorageOutItem outItem : items) {

                SkuProduct sku = skuProductMapper.selectById(outItem.getSkuId());
                if (outItem.getTransactionQuantities() > 0) {
                    outItem.setRelationCode(refund.getProductRefundCode());
                    outItem.setTransactionTime(storageOutModel.getStorageOutTime());
                    outItem.setType("Others");
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
                    /*if (outItem.getTransactionQuantities() > originInventory.getValidSku()) {
                        throw new BusinessException(4050, "\""+sku.getSkuName()+"\"库存不足");
                    } else*/
                        if (refund.getProductProcurementId() == null) {
                            //操作后的数量
                            Integer afterSkuCount = originInventory.getValidSku() - outItem.getTransactionQuantities();
                            outItem.setAfterTransactionQuantities(afterSkuCount);

                            originInventory.setValidSku(afterSkuCount);
                            affected += inventoryMapper.updateById(originInventory);

                        } else {
                            if (outItem.getTransactionQuantities() > queryRefundDao.skuStorageInCount(refund.getProductProcurementId(), outItem.getSkuId())) {
                                throw new BusinessException(4050, "\"" + sku.getSkuName() + "\"退货数量不能大于入库的数量");
                            } else {
                                //操作后的数量
                                Integer afterSkuCount = originInventory.getValidSku() - outItem.getTransactionQuantities();
                                outItem.setAfterTransactionQuantities(afterSkuCount);

                                originInventory.setValidSku(afterSkuCount);
                                affected += inventoryMapper.updateById(originInventory);
                            }
                        }

                    } else {
                        throw new BusinessException(4060, "该仓库不存在\"" + sku.getSkuName() + "\"商品");
                    }
                    storageOutItems.add(outItem);
                } else {
                    throw new BusinessException(5000, "提交失败，" + "\"" + sku.getSkuName() + "\"" + "商品退货数量不能为0");
                }
            }
        } else {
            throw new BusinessException(4055, "请先选择需要退货的商品！");
        }


        storageOutModel.setTransactionType(TransactionType.Refund.toString());
        storageOutModel.setStorageOutItems(storageOutItems);
        storageOutModel.setWarehouseId(refund.getProductRefundWarehouseId());

        // 用field1 来接收出库的code
        storageOutModel.setTransactionCode(refund.getField1());
        storageOutModel.setTransactionBy(username);
        storageOutModel.setOriginatorName(refund.getOriginatorName());
        storageOutModel.setOriginatorId(userId);
        storageOutModel.setTransactionBy(refund.getTransactionBy());
        storageOutModel.setTransactionTime(new Date());
        StorageOutFilter storageOutFilter = new StorageOutFilter();
        affected += storageOutService.createMaster(storageOutModel, storageOutFilter, null, null);

        refund.setStorageOutId((Long) storageOutFilter.result().get("id") == null ? null : (Long) storageOutFilter.result().get("id"));
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



    public RefundModel refundDetails(Long id) {
        Refund refund = refundService.retrieveMaster(id);
        JSONObject refundObj = JSON.parseObject(JSONObject.toJSONString(refund));

        if (refund.getProductRefundWarehouseId()!=null){
            refundObj.put("warehouseName",warehouseMapper.selectById(refund.getProductRefundWarehouseId()).getWarehouseName());
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


        if (refund.getProductRefundStatus().compareTo(RefundStatus.Done.toString()) ==0
                || refund.getProductRefundStatus().compareTo(RefundStatus.Audit_Passed.toString())==0){

            List<StorageOutItem> outItems = storageOutItemMapper.selectList(new EntityWrapper<StorageOutItem>()
            .eq(StorageOutItem.STORAGE_OUT_ID,refund.getStorageOutId())
            .eq(StorageOutItem.TYPE,"Others"));


            if (outItems != null && outItems.size() > 0) {
                for (StorageOutItem item : outItems) {
                    // 出库 商品详情
                    StorageOutItemRecord itemRecord = queryRefundDao.outItemRecord(item.getId());
                    outItemRecords.add(itemRecord);
                }
            }else {

            }
        }else {

            //searching out records
            List<StorageOut> storageOuts = storageOutMapper.selectList(new EntityWrapper<StorageOut>()
                    .eq(StorageOut.ID, refund.getId())
                    .eq(StorageOut.TRANSACTION_TYPE, TransactionType.Refund.toString()));


            if (storageOuts != null && storageOuts.size() > 0) {
                for (StorageOut out : storageOuts) {
                    StorageOutRecord record = queryRefundDao.outRecord(out.getId());//  关联上级出库单的信息
                    List<StorageOutItem> outItems = queryRefundDao.outItems(out.getId());
                    if (outItems != null && outItems.size() > 0) {
                        for (StorageOutItem item : outItems) {
                            // 出库 商品详情
                            StorageOutItemRecord itemRecord = queryRefundDao.outItemRecord(item.getId());
                            if (record.getOriginatorName() != null) {
                                itemRecord.setOperator(record.getOriginatorName());
                            }
                            itemRecord.setWarehouseName(record.getWarehouseName());
                            outItemRecords.add(itemRecord);
                        }
                        record.setStorageOutItemRecords(outItemRecords);
                    }
//                outRecords.add(record);
                }
            }

        }
        refundObj.put("itemRecords", outItemRecords);
        RefundModel model = JSONObject.parseObject(JSONObject.toJSONString(refundObj), RefundModel.class);
        return model;
    }



    @Transactional
    public Integer deleteRefund(Long id) {
        Refund refund = refundService.retrieveMaster(id);
        StorageOut out = storageOutService.retrieveMaster(refund.getStorageOutId());
        storageOutItemMapper.delete(new EntityWrapper<StorageOutItem>()
                .eq(StorageOutItem.STORAGE_OUT_ID,id)
                .eq(StorageOutItem.TYPE,TransactionType.Refund.toString()));
        storageOutItemMapper.delete(new EntityWrapper<StorageOutItem>().eq(StorageOutItem.STORAGE_OUT_ID,out.getId()).eq(StorageOutItem.TYPE,"Others"));
        storageOutMapper.deleteById(refund.getStorageOutId());
        refundService.deleteMaster(id);
        return refundService.deleteMaster(id);
    }


}

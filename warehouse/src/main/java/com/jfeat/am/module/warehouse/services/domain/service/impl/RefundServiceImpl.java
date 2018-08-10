package com.jfeat.am.module.warehouse.services.domain.service.impl;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.baomidou.mybatisplus.mapper.EntityWrapper;
import com.baomidou.mybatisplus.toolkit.IdWorker;
import com.jfeat.am.common.crud.CRUDObject;
import com.jfeat.am.common.exception.BusinessCode;
import com.jfeat.am.common.exception.BusinessException;
import com.jfeat.am.modular.system.service.UserService;
import com.jfeat.am.module.sku.services.persistence.dao.SkuProductMapper;
import com.jfeat.am.module.sku.services.persistence.model.SkuProduct;
import com.jfeat.am.module.warehouse.services.crud.filter.StorageInFilter;
import com.jfeat.am.module.warehouse.services.crud.filter.StorageOutFilter;
import com.jfeat.am.module.warehouse.services.crud.service.CRUDRefundService;
import com.jfeat.am.module.warehouse.services.crud.service.CRUDStorageInService;
import com.jfeat.am.module.warehouse.services.definition.RefundStatus;
import com.jfeat.am.module.warehouse.services.definition.TransactionType;
import com.jfeat.am.module.warehouse.services.domain.dao.QueryRefundDao;
import com.jfeat.am.module.warehouse.services.domain.model.*;
import com.jfeat.am.module.warehouse.services.domain.service.RefundService;

import com.jfeat.am.module.warehouse.services.crud.service.impl.CRUDRefundServiceImpl;
import com.jfeat.am.module.warehouse.services.domain.service.StorageOutService;
import com.jfeat.am.module.warehouse.services.persistence.dao.InventoryMapper;
import com.jfeat.am.module.warehouse.services.persistence.dao.ProcurementMapper;
import com.jfeat.am.module.warehouse.services.persistence.dao.StorageOutItemMapper;
import com.jfeat.am.module.warehouse.services.persistence.dao.StorageOutMapper;
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

    /**
     * 重构 Refund 问题
     */
    @Transactional
    @Override
    public Integer createRefund(long userId, RefundModel model) {
        /**
         * 1.先执行生成出库单，然后拿到入库单的 id 插入的采购的表单中
         * 2.
         * */
        int affected = 0;
        int refundTotal = 0;

        List<StorageOutItem> storageOutItems =new ArrayList<>();
        if (model.getItems() != null && model.getItems().size() > 0) {
            for (StorageOutItem outItem : model.getItems()) {
                outItem.setRelationCode(model.getProductRefundCode());
                refundTotal += outItem.getTransactionQuantities();
                SkuProduct sku = skuProductMapper.selectById(outItem.getSkuId());
                Inventory isExistInventory = new Inventory();
                isExistInventory.setSkuId(outItem.getSkuId());
                isExistInventory.setWarehouseId(model.getProductRefundWarehouseId());
                Inventory originInventory = inventoryMapper.selectOne(isExistInventory);
                if (originInventory != null) {
                    // 退货数量不能大于库存量
                    if(originInventory.getValidSku()==null){
                        originInventory.setValidSku(0);
                    }

                    /*if (outItem.getTransactionQuantities() > originInventory.getValidSku()) {
                        throw new BusinessException(4050, "\""+sku.getSkuName()+"\"库存不足");
                    } else*/

                    if (outItem.getTransactionQuantities() > queryRefundDao.skuStorageInCount(model.getProductProcurementId(),outItem.getSkuId())){
                        throw new BusinessException(4050, "\""+sku.getSkuName()+"\"退货数量不能大于入库的数量");
                    }

                    else {
                        originInventory.setValidSku(originInventory.getValidSku() - outItem.getTransactionQuantities());
                        affected += inventoryMapper.updateById(originInventory);
                    }
                } else {
                    throw new BusinessException(4060,"该仓库不存在\""+sku.getSkuName()+"\"商品");
                }
                storageOutItems.add(outItem);
            }
        }
        else {
            throw new BusinessException(4055,"请先选择需要退货的商品！");
        }

        StorageOutModel storageOutModel = new StorageOutModel();
        storageOutModel.setTransactionType(TransactionType.Refund.toString());
        storageOutModel.setStorageOutItems(storageOutItems);
        storageOutModel.setWarehouseId(model.getProductRefundWarehouseId());

        // 用field1 来接收出库的code
        storageOutModel.setTransactionCode(model.getField1());
        storageOutModel.setOriginatorId(userId);
        storageOutModel.setTransactionBy(model.getTransactionBy());
        storageOutModel.setTransactionTime(new Date());
        StorageOutFilter storageOutFilter = new StorageOutFilter();
        affected += storageOutService.createMaster(storageOutModel, storageOutFilter, null, null);

        model.setStorageOutId((Long) storageOutFilter.result().get("id") == null ? null : (Long) storageOutFilter.result().get("id"));
        model.setOriginatorId(userId);
        model.setTransactionTime(new Date());
        model.setProductRefundQuantities(refundTotal);
        model.setProductRefundStatus(RefundStatus.Done.toString());
        affected += refundService.createMaster(model);
        return affected;
    }


    /**
     * @Param Long refundId
     *  退货作废
     * */
    @Transactional
    public Integer cancelRefund(Long userId,Long refundId){
        int affected = 0;
        Refund refund = refundService.retrieveMaster(refundId);
        StorageOut out = storageOutMapper.selectById(refund.getStorageOutId());
        List<StorageInItem> storageInItems = new ArrayList<>();
        List<StorageOutItem> storageOutItems = storageOutItemMapper.selectList(new EntityWrapper<StorageOutItem>()
                .eq(StorageOutItem.STORAGE_OUT_ID,out.getId()).eq(StorageOutItem.RELATION_CODE,refund.getProductRefundCode()));
        if (storageOutItems ==null&&storageOutItems.size()==0){
            throw new BusinessException(5000,"未知错误");
        }
        for (StorageOutItem outItem : storageOutItems){
            Inventory isExistInventory = new Inventory();
            isExistInventory.setSkuId(outItem.getSkuId());
            isExistInventory.setWarehouseId(refund.getProductRefundWarehouseId());
            Inventory originInventory = inventoryMapper.selectOne(isExistInventory);
            if (originInventory==null){
                // 应该不会出现吧 出现就是数据库异常吧
                throw new BusinessException(BusinessCode.DatabaseConnectFailure);
            }
            Integer validSku = originInventory.getValidSku();
            validSku+=outItem.getTransactionQuantities();
            originInventory.setValidSku(validSku);
            affected += inventoryMapper.updateAllColumnById(originInventory);
            StorageInItem item = new StorageInItem();
            item.setRelationCode(refund.getProductRefundCode());
            item.setTransactionQuantities(outItem.getTransactionQuantities());
            item.setTransactionSkuPrice(outItem.getTransactionSkuPrice());
            item.setSkuId(outItem.getSkuId());
            item.setType("Others");
            item.setTransactionTime(new Date());
            storageInItems.add(item);



        }
        StorageInModel storageIn = new StorageInModel();
        storageIn.setTransactionType(TransactionType.OthersStorageIn.toString());
        storageIn.setWarehouseId(refund.getProductRefundWarehouseId());
        storageIn.setOriginatorId(userId);
        // needs code ?
        storageIn.setTransactionCode(refund.getField1().replace("OUT","IN"));
        storageIn.setTransactionTime(new Date());
        StorageInFilter storageInFilter = new StorageInFilter();
        storageIn.setStorageInItems(storageInItems);

        affected += crudStorageInService.createMaster(storageIn, storageInFilter, null, null);
        refund.setProductRefundStatus(RefundStatus.Cancel.toString());
        affected += refundService.updateMaster(refund);
        return affected;
    }

    public RefundModel refundDetails(long id) {
        Refund refund = refundService.retrieveMaster(id);
        JSONObject refundObj = JSON.parseObject(JSONObject.toJSONString(refund));

        // todo 明天处理一下
        Procurement procurement = procurementMapper.selectById(refund.getProductProcurementId());
        refundObj.put("procurementCode", procurement.getProcurementCode());

        List<StorageOut> storageOuts = storageOutMapper.selectList(new EntityWrapper<StorageOut>().eq(StorageOut.ID,refund.getStorageOutId()).like(StorageOut.TRANSACTION_TYPE,TransactionType.Refund.toString()));
//        List<StorageOutRecord> outRecords = new ArrayList<>();

        List<StorageOutItemRecord> outItemRecords = new ArrayList<>();
        if (storageOuts!=null&&storageOuts.size()>0){
            for (StorageOut out : storageOuts){
                StorageOutRecord record = queryRefundDao.outRecord(out.getId());//  关联上级出库单的信息
                List<StorageOutItem> outItems = queryRefundDao.outItems(out.getId());
                if (outItems!=null&&outItems.size()>0){
                    for (StorageOutItem item : outItems){
                        // 出库 商品详情
                        StorageOutItemRecord itemRecord = queryRefundDao.outItemRecord(item.getId());
                        itemRecord.setOperator(record.getOperatorName());
                        itemRecord.setWarehouseName(record.getWarehouseName());
                        outItemRecords.add(itemRecord);
                    }
                    record.setStorageOutItemRecords(outItemRecords);
                }
//                outRecords.add(record);
            }
        }
        refundObj.put("itemRecords", outItemRecords);
        RefundModel model = JSONObject.parseObject(JSONObject.toJSONString(refundObj), RefundModel.class);
        return model;
    }


    @Deprecated
    @Transactional
    public Integer updateRefund(long userId, RefundModel model) {

        //
        CRUDObject<StorageOutModel> storageOutModel = storageOutService.retrieveMasterModel(model.getProductProcurementId());
        StorageOutModel storageOut = storageOutModel.toJavaObject(StorageOutModel.class);
        storageOut.setStorageOutItems(model.getItems());
        storageOutService.updateMaster(storageOut, null, null, null);

        return refundService.updateMaster(model);
    }

    @Transactional
    public Integer deleteRefund(long id) {
        Refund refund = refundService.retrieveMaster(id);
        StorageOut out = storageOutService.retrieveMaster(refund.getStorageOutId());
        storageOutItemMapper.delete(new EntityWrapper<StorageOutItem>().eq("storage_out_id", out.getId()));
        storageOutMapper.deleteById(refund.getStorageOutId());
        refundService.deleteMaster(id);
        return refundService.deleteMaster(id);
    }


}

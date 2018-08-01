package com.jfeat.am.module.warehouse.services.domain.service.impl;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.baomidou.mybatisplus.mapper.EntityWrapper;
import com.baomidou.mybatisplus.toolkit.IdWorker;
import com.jfeat.am.common.crud.CRUDObject;
import com.jfeat.am.common.exception.BusinessCode;
import com.jfeat.am.common.exception.BusinessException;
import com.jfeat.am.modular.system.service.UserService;
import com.jfeat.am.module.warehouse.services.crud.filter.StorageOutFilter;
import com.jfeat.am.module.warehouse.services.crud.service.CRUDRefundService;
import com.jfeat.am.module.warehouse.services.definition.TransactionType;
import com.jfeat.am.module.warehouse.services.domain.model.RefundModel;
import com.jfeat.am.module.warehouse.services.domain.model.StorageOutModel;
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
import java.util.Date;

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
    StorageOutItemMapper storageOutItemMapper;

    @Resource
    UserService userService;

    @Resource
    ProcurementMapper procurementMapper;
    @Resource
    InventoryMapper inventoryMapper;

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

        if (model.getItems() != null && model.getItems().size() > 0) {
            for (StorageOutItem outItem : model.getItems()) {
                Inventory isExistInventory = new Inventory();
                isExistInventory.setSkuId(outItem.getSkuId());
                Inventory originInventory = inventoryMapper.selectOne(isExistInventory);
                if (originInventory != null) {
                    //TODO  前端 怎么去 处理 输入的 数量，大于 库存数量呢 ？
                    if (outItem.getTransactionQuantities() > originInventory.getValidSku()) {
                        throw new BusinessException(4050, "Lack of inventories");
                    } else {
                        originInventory.setValidSku(originInventory.getValidSku() - outItem.getTransactionQuantities());
                        affected += inventoryMapper.updateById(originInventory);
                    }
                } else {
                    throw new BusinessException(BusinessCode.NotImplement);
                }
            }
        }

        StorageOutModel storageOutModel = new StorageOutModel();
        storageOutModel.setTransactionType(TransactionType.Refund.toString());
        storageOutModel.setStorageOutItems(model.getItems());
        storageOutModel.setTransactionCode(model.getProductRefundCode());
        storageOutModel.setTransactionTime(new Date());
        StorageOutFilter storageOutFilter = new StorageOutFilter();
        affected += storageOutService.createMaster(storageOutModel, storageOutFilter, null, null);

        model.setStorageOutId((Long) storageOutFilter.result().get("id") == null ? null : (Long) storageOutFilter.result().get("id"));
        model.setOriginatorId(userId);
        model.setOperator(userId);
        model.setTransactionTime(new Date());
        // TODO 产品经理说，他也不知道有什么状态 所以我随便打了状态

        affected += refundService.createMaster(model);
        return affected;
    }


    public RefundModel refundDetails(long id) {
        Refund refund = refundService.retrieveMaster(id);
        JSONObject refundObj = JSON.parseObject(JSONObject.toJSONString(refund));

//        JSONObject storage = crudStorageOutService.retrieveMaster(refund.getStorageOutId(), null, null, null).toJSONObject();
//        StorageInModel storageIn = storage.toJavaObject(StorageInModel.class);
//        refundObj.put("items", storageIn.getStorageInItems());
        Procurement procurement = procurementMapper.selectById(refund.getProductProcurementId());
        refundObj.put("procurementCode", procurement.getProcurementCode());

        JSONObject storageOut = storageOutService.retrieveMaster(refund.getStorageOutId(), null, null, null).toJSONObject();
        StorageOutModel storageOutModel = storageOut.toJavaObject(StorageOutModel.class);
        refundObj.put("items", storageOutModel.getStorageOutItems());


        refundObj.put("originatorName", userService.getById(refund.getOriginatorId()).getName());
        refundObj.put("operatorName",
                userService.getById(refund.getOperator()).getName() == null ? null : userService.getById(refund.getOperator()).getName());

        RefundModel model = JSONObject.parseObject(JSONObject.toJSONString(refundObj), RefundModel.class);
        return model;
    }

    @Transactional
    public Integer updateRefund(long userId, RefundModel model) {

        CRUDObject<StorageOutModel> storageOutModel = storageOutService.retrieveMasterModel(model.getProductProcurementId());
        StorageOutModel storageOut = storageOutModel.toJavaObject(StorageOutModel.class);
        storageOut.setStorageOutItems(model.getItems());
        storageOutService.updateMaster(storageOut, null, null, null);

        model.setOperator(userId);
        // TODO 产品经理说，他也不知道有什么状态 所以我随便打了状态
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

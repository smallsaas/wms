package com.jfeat.am.module.warehouse.services.domain.service.impl;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.baomidou.mybatisplus.mapper.EntityWrapper;
import com.baomidou.mybatisplus.toolkit.IdWorker;
import com.jfeat.am.common.crud.CRUDObject;
import com.jfeat.am.modular.system.service.UserService;
import com.jfeat.am.module.warehouse.services.crud.filter.StorageInFilter;
import com.jfeat.am.module.warehouse.services.crud.service.CRUDProcurementService;
import com.jfeat.am.module.warehouse.services.definition.ProcurementStatus;
import com.jfeat.am.module.warehouse.services.definition.TransactionType;
import com.jfeat.am.module.warehouse.services.domain.model.ProcurementModel;
import com.jfeat.am.module.warehouse.services.domain.model.StorageInModel;
import com.jfeat.am.module.warehouse.services.domain.service.ProcurementService;

import com.jfeat.am.module.warehouse.services.crud.service.impl.CRUDProcurementServiceImpl;
import com.jfeat.am.module.warehouse.services.domain.service.StorageInService;
import com.jfeat.am.module.warehouse.services.persistence.dao.InventoryMapper;
import com.jfeat.am.module.warehouse.services.persistence.dao.StorageInItemMapper;
import com.jfeat.am.module.warehouse.services.persistence.dao.StorageInMapper;
import com.jfeat.am.module.warehouse.services.persistence.model.Inventory;
import com.jfeat.am.module.warehouse.services.persistence.model.Procurement;
import com.jfeat.am.module.warehouse.services.persistence.model.StorageIn;
import com.jfeat.am.module.warehouse.services.persistence.model.StorageInItem;
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
@Service("procurementService")
public class ProcurementServiceImpl extends CRUDProcurementServiceImpl implements ProcurementService {

    @Resource
    StorageInService storageInService;
    @Resource
    CRUDProcurementService procurementService;

    @Resource
    StorageInItemMapper storageInItemMapper;
    @Resource
    StorageInMapper storageInMapper;
    @Resource
    UserService userService;
    @Resource
    InventoryMapper inventoryMapper;

    /**
     * 重构 procurement 问题
     */
    @Transactional
    @Override
    public Integer createProcurement(long userId, ProcurementModel model) {
        /**
         * 1.先执行生成入库单，然后拿到入库单的 id 插入的采购的表单中
         * 2.目前先按所有均以入库逻辑写
         * */
        int affected = 0;

        if (model.getItems() != null && model.getItems().size() > 0) {
            for (StorageInItem inItem : model.getItems()) {
                Inventory isExistInventory = new Inventory();
                isExistInventory.setSkuId(inItem.getSkuId());
                Inventory originInventory = inventoryMapper.selectOne(isExistInventory);
                if (originInventory != null) {
                    if (model.getProcureStatus().compareTo(ProcurementStatus.TotalStorageIn.toString()) == 0) {
                        //全部 入库 则全部 插入

                        originInventory.setValidSku(originInventory.getValidSku() + inItem.getTransactionQuantities());
                        affected += inventoryMapper.updateById(originInventory);

//                        (model.getProcureStatus().compareTo(ProcurementStatus.WaitForStorageIn.toString()) == 0)
                    } else{
                        originInventory.setTransmitQuantities(inItem.getTransactionQuantities());
                        affected += inventoryMapper.updateById(originInventory);
                    }
                } else {
                    isExistInventory.setValidSku(inItem.getTransactionQuantities());
                    affected += inventoryMapper.insert(isExistInventory);
                }
            }
        }

        StorageInModel storageInModel = new StorageInModel();
        storageInModel.setTransactionType(TransactionType.Procurement.toString());
        storageInModel.setStorageInItems(model.getItems());
        storageInModel.setTransactionBy(userId);
        storageInModel.setTransactionCode(model.getProcurementCode());
        storageInModel.setTransactionTime(new Date());
        storageInModel.setWarehouseId(model.getStorageInId());
        storageInModel.setOriginatorId(userId);
        StorageInFilter storageInFilter = new StorageInFilter();
        affected += storageInService.createMaster(storageInModel, storageInFilter, null, null);

        model.setStorageInId((Long) storageInFilter.result().get("id") == null ? null : (Long) storageInFilter.result().get("id"));
        model.setOriginatorId(userId);
        model.setOperator(userId);
        model.setProcurementTime(new Date());



        affected += procurementService.createMaster(model);

        return affected;
    }


    @Transactional
    public Integer updateProcurement(long userId, ProcurementModel model) {

        CRUDObject<StorageInModel> storageInModel = storageInService.retrieveMasterModel(model.getStorageInId());
        StorageInModel storageIn = storageInModel.toJavaObject(StorageInModel.class);
        storageIn.setStorageInItems(model.getItems());
        storageIn.setTransactionBy(userId);
        storageInService.updateMaster(storageIn, null, null, null);
        model.setOperator(userId);
        if (model.getProcureStatus().compareTo(ProcurementStatus.TotalStorageIn.toString())==0){
            if (model.getItems() != null && model.getItems().size() > 0) {
                for (StorageInItem inItem : model.getItems()) {
                    Inventory isExistInventory = new Inventory();
                    isExistInventory.setSkuId(inItem.getSkuId());
                    Inventory originInventory = inventoryMapper.selectOne(isExistInventory);
                    originInventory.setValidSku(originInventory.getValidSku()+inItem.getTransactionQuantities());
                    inventoryMapper.updateById(originInventory);
                }
            }

        }

        return procurementService.updateMaster(model);
    }

    public ProcurementModel procurementDetails(long id) {
        Procurement procurement = procurementService.retrieveMaster(id);

        JSONObject storage = storageInService.retrieveMaster(procurement.getStorageInId(), null, null, null).toJSONObject();
        StorageInModel storageIn = storage.toJavaObject(StorageInModel.class);
        JSONObject procurementObj = JSON.parseObject(JSONObject.toJSONString(procurement));
        procurementObj.put("items", storageIn.getStorageInItems());

        procurementObj.put("originatorName", userService.getById(procurement.getOriginatorId()).getName());
        if (procurement.getOperator() != null) {
            procurementObj.put("operatorName",
                    userService.getById(procurement.getOperator()).getName() == null ? null : userService.getById(procurement.getOperator()).getName());
        }
        ProcurementModel model = JSONObject.parseObject(JSONObject.toJSONString(procurementObj), ProcurementModel.class);
        return model;
    }

    @Transactional
    public Integer deleteProcurement(long id) {
        Procurement procurement = procurementService.retrieveMaster(id);
        storageInMapper.delete(new EntityWrapper<StorageIn>().eq("id", procurement.getStorageInId()));
        storageInItemMapper.delete(new EntityWrapper<StorageInItem>().eq("storage_in_id", procurement.getStorageInId()));
        return procurementService.deleteMaster(id);
    }

}

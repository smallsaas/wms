package com.jfeat.am.module.warehouse.services.domain.service.impl;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.baomidou.mybatisplus.mapper.EntityWrapper;
import com.baomidou.mybatisplus.toolkit.IdWorker;
import com.jfeat.am.common.crud.CRUDObject;
import com.jfeat.am.modular.system.service.UserService;
import com.jfeat.am.module.warehouse.services.crud.filter.StorageOutFilter;
import com.jfeat.am.module.warehouse.services.crud.service.CRUDStorageInService;
import com.jfeat.am.module.warehouse.services.crud.service.CRUDStorageOutService;
import com.jfeat.am.module.warehouse.services.crud.service.CRUDTransferService;
import com.jfeat.am.module.warehouse.services.definition.TransactionType;
import com.jfeat.am.module.warehouse.services.domain.model.StorageInModel;
import com.jfeat.am.module.warehouse.services.domain.model.StorageOutModel;
import com.jfeat.am.module.warehouse.services.domain.model.TransferModel;
import com.jfeat.am.module.warehouse.services.domain.service.StorageInService;
import com.jfeat.am.module.warehouse.services.domain.service.StorageOutService;
import com.jfeat.am.module.warehouse.services.domain.service.TransferService;

import com.jfeat.am.module.warehouse.services.crud.service.impl.CRUDTransferServiceImpl;
import com.jfeat.am.module.warehouse.services.domain.service.WarehouseService;
import com.jfeat.am.module.warehouse.services.persistence.dao.StorageInItemMapper;
import com.jfeat.am.module.warehouse.services.persistence.dao.StorageInMapper;
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
@Service("crudTransferService")
public class TransferServiceImpl extends CRUDTransferServiceImpl implements TransferService {

    @Resource
    CRUDStorageInService crudStorageInService;
    @Resource
    CRUDStorageOutService crudStorageOutService;
    @Resource
    CRUDTransferService crudTransferService;
    @Resource
    StorageInItemMapper storageInItemMapper;
    @Resource
    StorageInMapper storageInMapper;
    @Resource
    StorageOutItemMapper storageOutItemMapper;
    @Resource
    StorageOutMapper storageOutMapper;

    @Resource
    UserService userService;
    @Resource
    WarehouseService warehouseService;

    @Resource
    StorageInService storageInService;

    @Resource
    StorageOutService storageOutService;

    @Transactional
    public Integer createTransfer(TransferModel model, long userId) {
        /**
         * 1.调拨包括了两个仓库的出入库 主动为出 被动为入
         * 2.插入 storageIn 以及 storageInItems
         * 3.插入 storageOut 以及 storageOutItems
         * 4.插入 Transfer
         * */

        StorageInModel storageInModel = new StorageInModel();
        storageInModel.setTransactionType(TransactionType.Transfer.toString());
        storageInModel.setStorageInItems(model.getInItems());
        storageInModel.setWarehouseId(model.getFromWarehouseId());
        storageInModel.setOriginatorId(userId);
        storageInModel.setTransactionCode(model.getTransactionCode());
        storageInModel.setTransactionBy(userId);
        storageInModel.setTransactionTime(new Date());

        // TODO  暂时没有 生成策略
        storageInModel.setTransactionCode(new IdWorker().toString());
//        StorageInFilter storageInFilter = new StorageInFilter();
        storageInService.createStorageIn(userId,storageInModel);
        model.setStorageInId(storageInModel.getId());
//        model.setStorageInId((Long) storageInFilter.result().get("id") == null ? null : (Long) storageInFilter.result().get("id"));


        StorageOutModel storageOut = new StorageOutModel();
        storageOut.setStorageOutItems(model.getOutItems());
        storageOut.setTransactionType(TransactionType.Transfer.toString());
        storageOut.setWarehouseId(model.getFromWarehouseId());
        storageOut.setOriginatorId(userId);
        storageOut.setTransactionBy(userId);
        storageOut.setTransactionCode(model.getTransactionCode());
        storageOut.setTransactionTime(new Date());
//        StorageOutFilter storageOutFilter = new StorageOutFilter();
        storageOutService.createStorageOut(userId,storageOut);
        model.setStorageOutId(storageOut.getId());
//        model.setStorageOutId((Long) storageOutFilter.result().get("id") == null ? null : (Long) storageOutFilter.result().get("id"));

        model.setOriginatorId(userId);
        model.setOperator(userId);
        model.setTransactionTime(new Date());
        return crudTransferService.createMaster(model);
    }

    @Transactional
    public Integer updateTransfer(TransferModel model, long userId) {
        /**
         * 1.调拨包括了两个仓库的出入库 主动为出 被动为入
         * */

        CRUDObject<StorageInModel> storageInModel = crudStorageInService.retrieveMasterModel(model.getStorageInId());
        StorageInModel storageIn = storageInModel.toJavaObject(StorageInModel.class);
        storageIn.setStorageInItems(model.getInItems());
        crudStorageInService.updateMaster(storageIn, null, null, null);


        CRUDObject<StorageOutModel> storageOutModel = crudStorageOutService.retrieveMasterModel(model.getStorageOutId());
        StorageOutModel storageOut = storageOutModel.toJavaObject(StorageOutModel.class);
        storageOut.setStorageOutItems(model.getOutItems());
        crudStorageOutService.updateMaster(storageOut, null, null, null);


        model.setOperator(userId);
        return crudTransferService.updateMaster(model);
    }

    public TransferModel transferDetails(long id){
        /**
         * 1.包含入库明细
         * 2.包含出库明细
         * 3.tranfer本身的信息
         * */
        Transfer transfer = crudTransferService.retrieveMaster(id);
        JSONObject transferObj = JSON.parseObject(JSONObject.toJSONString(transfer));

        JSONObject storage = crudStorageInService.retrieveMaster(transfer.getStorageInId(), null, null, null).toJSONObject();
        StorageInModel storageIn = storage.toJavaObject(StorageInModel.class);
        transferObj.put("inItems", storageIn.getStorageInItems());


        transferObj.put("originatorName", userService.getById(transfer.getOriginatorId()).getName());
        transferObj.put("operatorName",
                userService.getById(transfer.getOperator()).getName() == null ? null : userService.getById(transfer.getOperator()).getName());
        transferObj.put("fromWarehouseName",
                warehouseService.retrieveMaster(transfer.getFromWarehouseId())==null?null:warehouseService.retrieveMaster(transfer.getFromWarehouseId()).getWarehouseName());
        transferObj.put("toWarehouseName",
                warehouseService.retrieveMaster(transfer.getToWarehouseId())==null?null:warehouseService.retrieveMaster(transfer.getFromWarehouseId()).getWarehouseName());

        TransferModel model = JSONObject.parseObject(JSONObject.toJSONString(transferObj), TransferModel.class);
        return model;
    }


    @Transactional
    public Integer deleteTransfer(long id){

        Transfer transfer= crudTransferService.retrieveMaster(id);

        storageInItemMapper.delete(new EntityWrapper<StorageInItem>().eq("storage_in_id", transfer.getStorageInId()));
        storageInMapper.delete(new EntityWrapper<StorageIn>().eq("id", transfer.getStorageInId()));


        storageOutItemMapper.delete(new EntityWrapper<StorageOutItem>().eq("storage_out_id", transfer.getStorageOutId()));
        storageOutMapper.delete(new EntityWrapper<StorageOut>().eq("id", transfer.getStorageOutId()));


        return crudTransferService.deleteMaster(id);
    }



}

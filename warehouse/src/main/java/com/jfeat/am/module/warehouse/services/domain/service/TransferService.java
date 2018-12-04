package com.jfeat.am.module.warehouse.services.domain.service;

import com.jfeat.am.module.warehouse.services.crud.service.CRUDTransferService;
import com.jfeat.am.module.warehouse.services.domain.model.TransferModel;
import org.springframework.transaction.annotation.Transactional;

/**
 * Created by vincent on 2017/10/19.
 */
public interface TransferService extends CRUDTransferService{

    @Transactional
    Integer deleteTransfer(Long id);

    TransferModel transferDetails(Long id);

    /**
     * 接受方完成 调拨 ，插入 调拨 货物 及 更新 库存 ,接受方拒绝 ，调拨作废，回到 原来的 仓库
     */
    @Transactional
    Integer doneTransfer(Long id,Long userId);


    @Transactional
    Integer createTransfer(Long transferId,TransferModel model, Long userId);


    /**
     * 接受方拒接 调拨 ，插入 调拨 货物 及 更新 库存 ,接受方拒绝 ，调拨作废，回到 原来的 仓库
     */
    @Transactional
    Integer cancelTransfer(Long id,Long userId);


    /**
     *   Draft Transfer
     * */
    Integer draftTransfer(TransferModel model,Long userId,String userName);

    /**
     *  update transfer  while transfer status is Draft
     * */
    @Transactional
    Integer updateTransfer(Long transderId,TransferModel model);

}
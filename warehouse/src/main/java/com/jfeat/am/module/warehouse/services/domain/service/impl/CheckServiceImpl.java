package com.jfeat.am.module.warehouse.services.domain.service.impl;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.baomidou.mybatisplus.mapper.EntityWrapper;
import com.jfeat.am.common.exception.BusinessCode;
import com.jfeat.am.common.exception.BusinessException;
import com.jfeat.am.module.warehouse.services.definition.CheckStatus;
import com.jfeat.am.module.warehouse.services.domain.dao.QueryCheckDao;
import com.jfeat.am.module.warehouse.services.domain.dao.QueryWarehouseDao;
import com.jfeat.am.module.warehouse.services.domain.model.CheckModel;
import com.jfeat.am.module.warehouse.services.domain.model.CheckRecord;
import com.jfeat.am.module.warehouse.services.domain.model.CheckSkuRecord;
import com.jfeat.am.module.warehouse.services.domain.service.CheckService;

import com.jfeat.am.module.warehouse.services.crud.service.impl.CRUDCheckServiceImpl;
import com.jfeat.am.module.warehouse.services.persistence.dao.CheckMapper;
import com.jfeat.am.module.warehouse.services.persistence.dao.CheckSkuMapper;
import com.jfeat.am.module.warehouse.services.persistence.dao.InventoryMapper;
import com.jfeat.am.module.warehouse.services.persistence.model.Check;
import com.jfeat.am.module.warehouse.services.persistence.model.CheckSku;
import com.jfeat.am.module.warehouse.services.persistence.model.Inventory;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.Resource;
import java.beans.Transient;
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
@Service("checkService")
public class CheckServiceImpl extends CRUDCheckServiceImpl implements CheckService {


    @Resource
    CheckMapper checkMapper;
    @Resource
    CheckSkuMapper checkSkuMapper;
    @Resource
    QueryCheckDao queryCheckDao;
    @Resource
    InventoryMapper inventoryMapper;
    @Resource
    QueryWarehouseDao queryWarehouseDao;

    /**
     * 新建盘点单
     */
    @Transactional
    public Integer createCheckList(Long userId, CheckModel model) {

        int affected = 0;
        model.setOriginatorId(userId);
        model.setProfitLost(0); //新建默认缺失值为0
        model.setStatus(CheckStatus.WaitForCheck.toString());
        affected += checkMapper.insert(model);
        if (model.getCheckSkus() == null && model.getCheckSkus().size() <= 0) {
            throw new BusinessException(5000, "请选择需要盘点的商品");
        }
        for (CheckSku sku : model.getCheckSkus()) {
            Integer validCount = queryCheckDao.validCount(sku.getWarehouseId(), sku.getSkuId());
            sku.setWarehouseId(model.getWarehouseId());
            sku.setDeservedQuantities(validCount);
            sku.setCheckId(model.getId());
            affected += checkSkuMapper.insert(sku);
        }
        return affected;

    }

    /**
     * 开始盘点
     */
    @Transactional
    public Integer actionCheck(Long checkId, CheckModel model) {

        int affected = 0;
        int totalProfitLostValue = 0;
        model.setId(checkId);
        model.setStatus(CheckStatus.Checking.toString());
        model.setCheckTime(new Date());

        for (CheckSku sku : model.getCheckSkus()) {
            if (sku.getFactQuantities() != null && sku.getDeservedQuantities() != null) {
                sku.setProfitLost(sku.getFactQuantities() - sku.getDeservedQuantities());
            }
            if (sku.getFactQuantities() == null) {
                sku.setProfitLost(0);
            }
            totalProfitLostValue += sku.getProfitLost();
            affected += checkSkuMapper.updateAllColumnById(sku);
        }
        affected += checkMapper.updateById(model);
        return affected;
    }

    /**
     * finish check modified data
     */
    @Transactional
    public Integer checkedCheck(Long checkId) {

        int affected = 0;
        int totalProfitLostValue = 0;
        Check check = checkMapper.selectById(checkId);
        if (check.getStatus().compareTo(CheckStatus.Checking.toString()) != 0) {
            throw new BusinessException(5002, "请先进行盘点");
        }
        List<CheckSku> checkSkus = checkSkuMapper.selectList(new EntityWrapper<CheckSku>().eq(CheckSku.CHECK_ID, checkId));
        for (CheckSku sku : checkSkus) {
            if (sku.getFactQuantities() == null) {
                throw new BusinessException(5001, "请先对未完成盘点的商品进行盘点");
            }
            if (sku.getFactQuantities() != null && sku.getDeservedQuantities() != null) {
                sku.setProfitLost(sku.getFactQuantities() - sku.getDeservedQuantities());
            }
            sku.setBeforeProofQuantities(sku.getDeservedQuantities());// 在校对之前将原本应该有的库存保存在这里
            sku.setDeservedQuantities(sku.getFactQuantities());
            totalProfitLostValue += sku.getProfitLost();

            // 更新库存
            Inventory inventory = new Inventory();
            inventory.setWarehouseId(sku.getWarehouseId());
            inventory.setSkuId(sku.getSkuId());
            Inventory originInventory = inventoryMapper.selectOne(inventory);
            if (originInventory == null) {
                throw new BusinessException(10000, "未知错误，请联系专业人员");
            }
            originInventory.setValidSku(sku.getFactQuantities());
            affected += inventoryMapper.updateAllColumnById(originInventory);
            affected += checkSkuMapper.updateAllColumnById(sku);//校对数据
        }

        check.setId(checkId);
        check.setStatus(CheckStatus.CheckOut.toString());
        check.setCheckTime(new Date());
        check.setProfitLost(totalProfitLostValue);

        affected += checkMapper.updateById(check);
        return affected;
    }

    public CheckRecord checkDetails(Long checkId) {
        Check check = checkMapper.selectById(checkId);
        JSONObject checkObj = JSON.parseObject(JSON.toJSONString(check));
        List<CheckSkuRecord> records = queryCheckDao.skuRecords(checkId);
        checkObj.put("warehouseName", queryWarehouseDao.warehouseName(check.getWarehouseId()));
        checkObj.put("skuRecords", records);
        String originatorName = queryCheckDao.originatorName(check.getOriginatorId());
        checkObj.put("originatorName", originatorName);
        CheckRecord record = JSON.parseObject(JSON.toJSONString(checkObj), CheckRecord.class);
        return record;
    }


    @Transactional
    public Integer deleteCheck(Long checkId) {

        int affected = 0;
        Check check = checkMapper.selectById(checkId);
        if (check.getStatus().compareTo(CheckStatus.WaitForCheck.toString()) != 0) {
            throw new BusinessException(BusinessCode.ErrorStatus);
        }
        affected += checkSkuMapper.delete(new EntityWrapper<CheckSku>().eq(CheckSku.CHECK_ID, checkId));
        affected += checkMapper.deleteById(checkId);
        return affected;
    }


}

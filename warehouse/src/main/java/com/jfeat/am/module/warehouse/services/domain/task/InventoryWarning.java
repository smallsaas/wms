package com.jfeat.am.module.warehouse.services.domain.task;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.jfeat.am.module.sku.services.persistence.dao.SkuProductMapper;
import com.jfeat.am.module.sku.services.persistence.model.SkuProduct;
import com.jfeat.am.module.warehouse.services.persistence.dao.InventoryMapper;
import com.jfeat.am.module.warehouse.services.persistence.model.Inventory;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import javax.annotation.Resource;
import java.util.List;

@Component
public class InventoryWarning {



    @Resource
    InventoryMapper inventoryMapper;
    @Resource
    SkuProductMapper skuProductMapper;


    @Scheduled(cron = "0 0 0 * * ?")
    public void task(){

        List<Inventory> all = inventoryMapper.selectList(new QueryWrapper<>());
        if (all == null || all.size()<=0){
            return;
        }
        for (Inventory inventory : all){

            if (inventory.getTransmitQuantities()+inventory.getValidSku() <= inventory.getMinInventory()){
                SkuProduct skuProduct = skuProductMapper.selectById(inventory.getSkuId());

            }
            else {
                return;
            }

        }

    }

}

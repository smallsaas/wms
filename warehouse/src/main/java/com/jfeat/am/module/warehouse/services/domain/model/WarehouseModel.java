package com.jfeat.am.module.warehouse.services.domain.model;

import java.util.List;
import com.jfeat.am.module.warehouse.services.persistence.model.WarehouseSlot;
import com.jfeat.am.module.warehouse.services.persistence.model.Warehouse;

/**
 * Created by Code Generator on 2018-06-22
 */
public class WarehouseModel extends Warehouse{
        private List<WarehouseSlot> items;

        public List<WarehouseSlot> getItems() {
                return items;
        }

        public void setItems(List<WarehouseSlot> items) {
                this.items = items;
        }
}

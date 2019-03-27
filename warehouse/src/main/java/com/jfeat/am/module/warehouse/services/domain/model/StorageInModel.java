package com.jfeat.am.module.warehouse.services.domain.model;

import com.jfeat.am.module.warehouse.services.persistence.model.StorageInItem;
import com.jfeat.am.module.warehouse.services.persistence.model.StorageIn;

import java.util.List;

/**
 * Created by Code Generator on 2018-06-21
 */
public class StorageInModel extends StorageIn{
        private List<StorageInItem> storageInItems;


        public List<StorageInItem> getStorageInItems() {
                return storageInItems;
        }

        public void setStorageInItems(List<StorageInItem> storageInItems) {
                this.storageInItems = storageInItems;
        }
}

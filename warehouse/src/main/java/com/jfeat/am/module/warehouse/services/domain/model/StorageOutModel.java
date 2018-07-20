package com.jfeat.am.module.warehouse.services.domain.model;

import java.util.List;

import com.jfeat.am.module.warehouse.services.persistence.model.StorageInItem;
import com.jfeat.am.module.warehouse.services.persistence.model.StorageOut;
import com.jfeat.am.module.warehouse.services.persistence.model.StorageOutItem;

/**
 * Created by Code Generator on 2018-06-20
 */
public class StorageOutModel extends StorageOut{
        private List<StorageOutItem> storageOutItems;

        public List<StorageOutItem> getStorageOutItems() {
                return storageOutItems;
        }

        public void setStorageOutItems(List<StorageOutItem> storageOutItems) {
                this.storageOutItems = storageOutItems;
        }
}

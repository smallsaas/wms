package com.jfeat.am.module.warehouse.services.domain.model;

import com.jfeat.am.module.warehouse.services.persistence.model.StorageInItem;
import com.jfeat.am.module.warehouse.services.persistence.model.StorageIn;

import java.util.List;

/**
 * Created by Code Generator on 2018-06-21
 */
public class StorageInModel extends StorageIn{
        Integer dealSuccess;
        private List<StorageInItem> storageInItems;

        public Integer getDealSuccess() {
                return dealSuccess;
        }

        public void setDealSuccess(Integer dealSuccess) {
                this.dealSuccess = dealSuccess;
        }

        public List<StorageInItem> getStorageInItems() {
                return storageInItems;
        }

        public void setStorageInItems(List<StorageInItem> storageInItems) {
                this.storageInItems = storageInItems;
        }
}

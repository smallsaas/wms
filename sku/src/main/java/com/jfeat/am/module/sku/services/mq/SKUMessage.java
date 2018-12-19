package com.jfeat.am.module.sku.services.mq;

import java.io.Serializable;

/**
 * Created by zy on 2018/12/19.
 */
public class SkuMessage implements Serializable {
    private static final long serialVersionUID = 1L;
    private String type;
    private Data data;

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public Data getData() {
        return data;
    }

    public void setData(Data data) {
        this.data = data;
    }

    @Override
    public String toString() {
        return "SkuMessage{" +
                "type='" + type + '\'' +
                ", data=" + data +
                '}';
    }

    //{"type":"SKU", "data": { "id": 11, "skuName": "xxx", "skuCode": "xxxx", "barCode": "xxvv" } }
    public static class Data {
        private Long id;
        private String skuName;
        private String skuCode;
        private String barCode;
        public Data() {}
        public Data(Long id, String skuName, String Code, String barCode) {
            this.id = id;
            this.skuName = skuName;
            this.skuCode = skuCode;
            this.barCode = barCode;
        }
        public Long getId() {
            return id;
        }

        public void setId(Long id) {
            this.id = id;
        }

        public String getSkuName() {
            return skuName;
        }

        public void setSkuName(String skuName) {
            this.skuName = skuName;
        }

        public String getSkuCode() {
            return skuCode;
        }

        public void setSkuCode(String skuCode) {
            this.skuCode = skuCode;
        }

        public String getBarCode() {
            return barCode;
        }

        public void setBarCode(String barCode) {
            this.barCode = barCode;
        }

        @Override
        public String toString() {
            return "Data{" +
                    "id=" + id +
                    ", skuName='" + skuName + '\'' +
                    ", skuCode='" + skuCode + '\'' +
                    ", barCode='" + barCode + '\'' +
                    '}';
        }
    }
}

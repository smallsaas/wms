package com.jfeat.am.module.warehouse.services.domain.model;

import java.math.BigDecimal;
import java.util.Date;

public class SkuStorageDetails {

    Long skuId;
    String type;
    BigDecimal transactionSkuPrice;
    Integer transactionQuantities;
    Date transactionTime;
    String skuName;
    String skuBarcode;
    String skuCode;
    String skuUnit;
    String warehouseName;
    String transactionType;
    String transactionCode;
    Integer validValue;
    Integer beforeTransactionQuantities;
    String relationCode;
    String recordType;
    Long parentId;
    String resultType;

    public String getRecordType() {
        return recordType;
    }

    public void setRecordType(String recordType) {
        this.recordType = recordType;
    }

    public Long getSkuId() {
        return skuId;
    }

    public void setSkuId(Long skuId) {
        this.skuId = skuId;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public BigDecimal getTransactionSkuPrice() {
        return transactionSkuPrice;
    }

    public void setTransactionSkuPrice(BigDecimal transactionSkuPrice) {
        this.transactionSkuPrice = transactionSkuPrice;
    }

    public Integer getTransactionQuantities() {
        return transactionQuantities;
    }

    public void setTransactionQuantities(Integer transactionQuantities) {
        this.transactionQuantities = transactionQuantities;
    }

    public Date getTransactionTime() {
        return transactionTime;
    }

    public void setTransactionTime(Date transactionTime) {
        this.transactionTime = transactionTime;
    }

    public String getSkuName() {
        return skuName;
    }

    public void setSkuName(String skuName) {
        this.skuName = skuName;
    }

    public String getSkuBarcode() {
        return skuBarcode;
    }

    public void setSkuBarcode(String skuBarcode) {
        this.skuBarcode = skuBarcode;
    }

    public String getSkuCode() {
        return skuCode;
    }

    public void setSkuCode(String skuCode) {
        this.skuCode = skuCode;
    }

    public String getSkuUnit() {
        return skuUnit;
    }

    public void setSkuUnit(String skuUnit) {
        this.skuUnit = skuUnit;
    }

    public String getWarehouseName() {
        return warehouseName;
    }

    public void setWarehouseName(String warehouseName) {
        this.warehouseName = warehouseName;
    }

    public String getTransactionType() {
        return transactionType;
    }

    public void setTransactionType(String transactionType) {
        this.transactionType = transactionType;
    }

    public String getTransactionCode() {
        return transactionCode;
    }

    public void setTransactionCode(String transactionCode) {
        this.transactionCode = transactionCode;
    }

    public Integer getValidValue() {
        return validValue;
    }

    public void setValidValue(Integer validValue) {
        this.validValue = validValue;
    }

    public String getRelationCode() {
        return relationCode;
    }

    public void setRelationCode(String relationCode) {
        this.relationCode = relationCode;
    }


    public Long getParentId() {
        return parentId;
    }

    public void setParentId(Long parentId) {
        this.parentId = parentId;
    }

    public String getResultType() {
        return resultType;
    }

    public void setResultType(String resultType) {
        this.resultType = resultType;
    }

    public Integer getBeforeTransactionQuantities() {
        return beforeTransactionQuantities;
    }

    public void setBeforeTransactionQuantities(Integer beforeTransactionQuantities) {
        this.beforeTransactionQuantities = beforeTransactionQuantities;
    }
}

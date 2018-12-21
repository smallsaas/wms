package com.jfeat.am.module.sku.services.utils;

/**
 * Created by zy on 2018/12/19.
 */
public class DataCheck {
    /**
     * 检查数据是否发生改变, newData为null时当作未改变处理
     * @param oldData
     * @param newData
     * @return boolean
     **/
    public static <T> boolean isUpdated(T oldData, T newData) {
        if (oldData != null && newData != null) {
            if (oldData.equals(newData)) {
                return true;
            }
        } else if(newData == null) {
            return true;
        }
        return false;
    }
}

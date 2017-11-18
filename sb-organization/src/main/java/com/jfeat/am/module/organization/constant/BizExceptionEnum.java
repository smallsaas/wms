package com.jfeat.am.module.organization.constant;

import com.jfeat.am.common.exception.BusinessException;

/**
 * Created by jackyhuang on 2017/11/17.
 */
public enum BizExceptionEnum {

    USER_ALREADY_BOUND(2001, "该用户已经绑定其他员工");


    private int friendlyCode;
    private String friendlyMsg;

    private BizExceptionEnum(int code, String message) {
        this.friendlyCode = code;
        this.friendlyMsg = message;
    }

    public int getCode() {
        return this.friendlyCode;
    }

    public void setCode(int code) {
        this.friendlyCode = code;
    }

    public String getMessage() {
        return this.friendlyMsg;
    }

    public void setMessage(String message) {
        this.friendlyMsg = message;
    }

    public BusinessException createException() {
        return new BusinessException(this.getCode(), this.getMessage());
    }
}

package com.jfeat.am.module.organization.constant;

import com.jfeat.am.common.exception.BusinessException;

/**
 * Created by jackyhuang on 2017/11/17.
 */
public enum BizExceptionEnum {

    USER_ALREADY_BOUND(2001, "该用户已经绑定其他员工"),
    STAFF_NOT_FOUND(2002, "找不到该员工"),
    DEPT_CIRCULAR_CHAIN(2003, "存在循环指定"),
    STAFF_NOT_EXIT(2004, "该员工已不存在"),
    STAFF_IS_MANAGER(2005, "该员工已经是部门经理"),
    STAFF_OUT_OF_DEPARTMENT(2005, "非该部门员工不能成为该部门经理");


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

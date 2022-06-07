package com.liumulin.exceptions;

public interface ResultCode {

    /**
     * 操作代码
     */
    int getCode();

    /**
     * 提示信息
     */
    String getMsg();

}
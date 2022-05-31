package com.liumulin.common.exceptions;

/**
 * 没有登陆异常
 *
 * @author liuqiang
 */
public class NoLoginException extends RuntimeException {

    private static final long serialVersionUID = 1L;

    public NoLoginException() {
    }

    public NoLoginException(String message) {
        super(message);
    }

    public NoLoginException(Throwable cause) {
        super(cause);
    }

    public NoLoginException(String message, Throwable cause) {
        super(message, cause);
    }

    public NoLoginException(String message, Throwable cause, boolean enableSuppression, boolean writableStackTrace) {
        super(message, cause, enableSuppression, writableStackTrace);
    }

}

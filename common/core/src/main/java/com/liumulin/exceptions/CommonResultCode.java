package com.liumulin.exceptions;

public enum CommonResultCode implements ResultCode {
    SUCCESS(1, "操作成功"),
    FAIL(0, "操作失败！"),
    SERVER_ERROR(99999, "抱歉，系统繁忙，请稍后重试！"),

    UNAUTHENTICATED(10001, "此操作需要登陆系统！"),
//    UNAUTHORIZED(10002, "权限不足，无权操作！"),
    INVALID_PARAMETER(10003, "无效参数"),

    BAD_REQUEST(400, "请求参数不正确"),
    UNAUTHORIZED(401, "账号未登录"),
    FORBIDDEN(403, "没有该操作权限"),
    NOT_FOUND(404, "请求未找到"),
    METHOD_NOT_ALLOWED(405, "请求方法不正确"),
    LOCKED(423, "请求失败，请稍后重试"), // 并发请求，不允许
    TOO_MANY_REQUESTS(429, "请求过于频繁，请稍后重试"),



    CONNECT_ERROR(430, "数据库连接异常"),



    // ========== 服务端错误段 ==========

    INTERNAL_SERVER_ERROR(500, "系统异常"),

    // ========== 自定义错误段 ==========
    REPEATED_REQUESTS(900, "重复请求，请稍后重试"), // 重复请求
    DEMO_DENY(901, "演示模式，禁止写操作"),

    UNKNOWN(999, "未知错误"),

    ;

    //操作代码
    final int code;
    //提示信息
    final String msg;

    CommonResultCode(int code, String msg) {
        this.code = code;
        this.msg = msg;
    }

    @Override
    public int getCode() {
        return code;
    }

    @Override
    public String getMsg() {
        return msg;
    }
}
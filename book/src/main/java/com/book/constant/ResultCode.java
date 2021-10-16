package com.book.constant;

public enum ResultCode implements IErrorCode {

    SUCCESS(200, "success"),

    FAILED(400, "failed"),

    PARAM_ERROR(400, "参数验证失败"),

    UNAUTHORIZED(401, "暂未登录或token已经过期"),

    USERNAME_PASSWORD_ERROR(401, "用户名或密码错误"),

    ERROR_404(404, "该接口不存在"),

    USER_NO_FOUND(404, "登录账号不存在，请注册"),

    FORBIDDEN(403, "没有相关权限"),

    SYSTEM_ERROR(500, "服务器错误");

    private int code;
    private String msg;

    private ResultCode(int code, String msg) {
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

package com.book.constant;

public interface PublicConstant {

    // 默认登录Token令牌key
    String LOGIN_IDENTITY_KEY = "COOKIE_LOGIN_IDENTITY";
    // redis缓存菜单key
    String MENU_KEY = ".MENUS";
    // 默认缓存时间,单位/秒, 2H
    int COOKIE_MAX_AGE = Integer.MAX_VALUE;
    // 保存路径,根路径
    String COOKIE_PATH = "/";

    String USERNAME_PASSWORD_ERROR  = "用户名或密码错误";

    String USER_NO_FOUND  = "用户不存在";

    String PARAM_ERROR  = "参数错误";

    String USER_NO_PERMITION  = "当前用户无该接口权限";

    String UNAUTHO_ERROR = "未登录认证";

    String INVALID_TOKEN = "无效的授权码,Token令牌可能已被篡改";

    String SYSTEM_ERROR = "系统错误";

    //默认添加和注册用户密码
    String DEFAULT_PASSWORD = "123456";

    String ADMIN_ROLE = "admin";

    String USER_ROLE = "user";

    // 默认用户状态
    Integer DEFAULT_STATUS = 0;
}

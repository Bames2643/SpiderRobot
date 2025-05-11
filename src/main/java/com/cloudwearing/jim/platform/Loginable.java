package com.cloudwearing.jim.platform;

public interface Loginable {

    void setLoginUser(String loginUser);

    void setLoginPwd(String loginPwd);

    String getLoginUser();

    String getLoginPwd();

}

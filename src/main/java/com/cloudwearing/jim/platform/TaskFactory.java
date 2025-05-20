package com.cloudwearing.jim.platform;


public class TaskFactory {

    private TaskFactory() {
    }

    private static TaskFactory instance;

    public static PlatformTask buildPlatform(String platform) {
        switch (platform.toLowerCase()) {
            case "qq":
            case "qqzone":
                return new QQZone("QQZone");
            case "wechat":

            default:
                throw new IllegalArgumentException("未实现[" + platform + "]平台的抓取！");
        }
    }

}

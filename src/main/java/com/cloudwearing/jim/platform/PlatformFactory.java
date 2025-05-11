package com.cloudwearing.jim.platform;


public class PlatformFactory {

    private PlatformFactory() {
    }

    private static PlatformFactory instance;

    private Platform platform;

    public static void set(Platform platform) {
        if (instance == null) {
            instance = new PlatformFactory();
        }
        instance.platform = platform;
    }

    public static Platform get() {
        return instance.platform;
    }


    public static Platform buildPlatform(String platform) {
        switch (platform) {
            case "QQ":
            case "qq":
            case "QQZone":
            case "qqZone":
            case "qqzone":
                return new QQZone("QQZone");
            case "wecaht":

            default:
                throw new IllegalArgumentException("未实现[" + platform + "]平台的抓取！");
        }
    }
}

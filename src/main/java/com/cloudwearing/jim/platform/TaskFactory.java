package com.cloudwearing.jim.platform;


public class TaskFactory {

    private TaskFactory() {
    }

    private static TaskFactory instance;

    private PlatformTask platformTask;

    public static void set(PlatformTask platformTask) {
        if (instance == null) {
            instance = new TaskFactory();
        }
        instance.platformTask = platformTask;
    }

    public static PlatformTask get() {
        return instance.platformTask;
    }


    public static PlatformTask buildPlatform(String platform) {
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

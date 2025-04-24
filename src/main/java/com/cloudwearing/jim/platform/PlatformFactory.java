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


}

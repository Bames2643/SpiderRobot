package com.cloudwearing.jim.platform;

import java.util.List;

public abstract class Platform {

    protected String name;
    protected Integer grabDepth;
    protected String url;

    public boolean isNeedLogin() {
        return needLogin;
    }

    public void setNeedLogin(boolean needLogin) {
        this.needLogin = needLogin;
    }

    protected boolean needLogin;

    public Platform() {
    }

    public Platform(String name) {
        if (name == null) throw new NullPointerException();
        this.name = name;
    }

    public Platform(String name, String url) {
        this(name);
        if (url == null) throw new NullPointerException();
        this.url = url;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Integer getGrabDepth() {
        return grabDepth;
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    /**
     * @param content 整个页面内容
     * @return 继续下抓的页面
     */
    public abstract List<? extends Platform> resolve(String content);

    public void setGrabDepth(Integer depth) {
        this.grabDepth = depth;
    }

}

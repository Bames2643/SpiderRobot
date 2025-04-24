package com.cloudwearing.jim.platform;

import java.util.List;

public abstract class Platform<T> {


    public abstract int getMaxDepth();

    public abstract String getLoginUser();

    public abstract String getLoginPwd();

    public abstract String getUrl();

    public abstract String getRegex();

    public abstract T resolve(String group);

    public abstract List<String> nextGenUrl(String url, String content, List<T> entityList, int currDepth);

}

package com.cloudwearing.jim.platform;

import com.cloudwearing.jim.entity.PageEntity;

import java.util.List;

public abstract class Platform<T> {

    public abstract String getUrl();

    public abstract String getRegex();

    public abstract T resolve(String group);

    public abstract List<String> nextGenUrl(String url, String content, List<T> entityList, int currDepth);

}

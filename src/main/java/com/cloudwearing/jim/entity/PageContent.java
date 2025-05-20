package com.cloudwearing.jim.entity;

/**
 * 抓取的页面结果
 */
public class PageContent {

    private String pageUrl; // 页面路径
    private String content; // 页面内容

    public PageContent() {
    }

    public PageContent(String pageUrl, String content) {
        this.pageUrl = pageUrl;
        this.content = content;
    }

    public String getPageUrl() {
        return pageUrl;
    }

    public void setPageUrl(String pageUrl) {
        this.pageUrl = pageUrl;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }


}

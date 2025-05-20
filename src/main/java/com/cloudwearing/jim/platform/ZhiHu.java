//package com.cloudwearing.jim.platform;
//
//
//import com.cloudwearing.jim.entity.PageEntity;
//import com.cloudwearing.jim.entity.ZhiHuEntity;
//
//import java.util.List;
//
//public class ZhiHu extends PlatformTask {
//
//    private String url = "https://www.zhihu.com/explore/recommendations";
//
//    private String regex = "<h2>.+?question_link.+?href=\"(.+?)\".+?</h2>";
//
//    public String getUrl() {
//        return url;
//    }
//
//    public String getRegex() {
//        return regex;
//    }
//
//    public int getMaxDepth() {
//        return 2;
//    }
//
//    @Override
//    public String getLoginUser() {
//        throw new NullPointerException();
//    }
//
//    @Override
//    public String getLoginPwd() {
//        throw new NullPointerException();
//    }
//
//    @Override
//    public PageEntity resolve(String group) {
//        return new ZhiHuEntity(group);
//    }
//
//    @Override
//    public List<String> nextGenUrl(String url, String content, List list) {
//        throw new IllegalArgumentException();
//    }
//
//}

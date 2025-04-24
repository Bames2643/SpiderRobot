package com.cloudwearing.jim.platform;

import com.cloudwearing.jim.entity.QQ;

import java.util.Collections;
import java.util.List;
import java.util.stream.Collectors;

public class QQZone extends Platform<QQ> {


    private final int maxDepth; //深度：从0开始
    private final String loginUser;
    private final String loginPwd;

    public int getMaxDepth() {
        return maxDepth;
    }

    public String getLoginUser() {
        return loginUser;
    }

    public String getLoginPwd() {
        return loginPwd;
    }

    public QQZone(String startUrl, int maxDepth, String loginUser, String loginPwd) {
        if (startUrl == null) throw new NullPointerException();
        // todo 判断：不是QQ空间地址 => err
        this.url = startUrl;
        this.maxDepth = maxDepth;
        this.loginUser = loginUser;
        this.loginPwd = loginPwd;
    }

    protected String url;

    // 想要的页内标签
    // <a href="http://user.qzone.qq.com/3058258907" class="item q_namecard" target="_blank" link="nameCard_3058258907 des_3058258907">恩赐超市生活配送</a>
    protected String regex = "<a .*href=[\'\"]https?://user.qzone.qq.com/(\\d+)/?[\'\"].*></a>";

    public String getUrl() {
        return url;
    }

    public String getRegex() {
        return regex;
    }

    @Override
    public QQ resolve(String group) {
        QQ qq = new QQ(group);
        return qq;
    }


    /**
     * 深入下一层继续挖掘；不挖返回空List
     *
     * @param url
     * @param content
     * @param entityList
     * @param currDepth
     * @return
     */
    @Override
    public List<String> nextGenUrl(String url, String content, List<QQ> entityList, int currDepth) {
        if (currDepth >= this.maxDepth) return Collections.emptyList();
        return entityList.stream().map(e -> "https://user.qzone.qq.com/" + e.getQq()).collect(Collectors.toList());
    }

}

package com.cloudwearing.jim.platform;

import com.cloudwearing.jim.dao.ConsoleHelper;
import com.cloudwearing.jim.entity.PageContext;

import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class QQZone extends PlatformTask {

    public QQZone() {
    }

    public QQZone(String name) {
        super(name);
    }

    public QQZone(String name, String url) {
        super(name, url);
    }

    // 想要的页内标签
    // <a href="http://user.qzone.qq.com/3058258907" class="item q_namecard" target="_blank" link="nameCard_3058258907 des_3058258907">恩赐超市生活配送</a>
    protected String urlRegex = "http(s)?://user.qzone.qq.com/(\\d+)(\\?.*)?";
    protected String qqLinkRegex = "<a .*href=[\'\"]https?://user.qzone.qq.com/(\\d+)/?[\'\"].*></a>";
    protected String linkRegex = "";


    @Override
    public List<QQZone> resolve(PageContext context) {
        String url = context.getPageUrl();
        String content = context.getContent();

        Pattern pattern = Pattern.compile(urlRegex);
        Matcher matcher = pattern.matcher(url);
        if (matcher.matches()) {
            String group = matcher.group(2);
            ConsoleHelper.writeToConsole(group);
        }


        return null;
    }


}

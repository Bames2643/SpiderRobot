package com.cloudwearing.jim.platform;

import com.cloudwearing.jim.entity.PageContent;

import java.util.List;

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
    protected String regex = "<a .*href=[\'\"]https?://user.qzone.qq.com/(\\d+)/?[\'\"].*></a>";

    public String getRegex() {
        return regex;
    }

    @Override
    public List<QQZone> resolve(PageContent content) {


//        ArrayList<PageEntity> results = new ArrayList<>();
//        Pattern pattern = Pattern.compile(TaskFactory.get().getRegex());
//        Matcher matcher = pattern.matcher(content);
//        Boolean isFind = matcher.find();
//        while (isFind) {
//            PageEntity pageEntity = (PageEntity) TaskFactory.get().resolve(matcher.group(1));
//            results.add(pageEntity);
//            isFind = matcher.find();
//        }
//        return results;
//
//
//        QQ qq = new QQ(group);
        return null;
    }


}

package com.cloudwearing.jim;

import com.cloudwearing.jim.dao.DBHelper;
import com.cloudwearing.jim.entity.PageEntity;
import com.cloudwearing.jim.platform.PlatformFactory;
import com.cloudwearing.jim.platform.QQZone;

import java.util.Collections;
import java.util.List;

public class Main {

    public static void main(String[] args) {

        PlatformFactory.set(new QQZone("https://user.qzone.qq.com/1337729961", 3));



        start(Collections.singletonList(PlatformFactory.get().getUrl()), 0);
    }

    private static void start(List<String> urls, int depth) {
        urls.forEach(url -> {
            System.out.println("########### [Dep:" + depth + "] ######### 开始解析页面：" + url);
            String content = Spider.fetchPageContent(url);
            List<PageEntity> entityList = Spider.pageContent2Entity(content);
            System.out.println("-- 获取结果：" + entityList.size() + "个");
            for (PageEntity pageEntity : entityList) {
//            FileReaderWriter.writeIntoFile(zhiHu.writeString(), "D:/知乎-编辑推荐.txt", true);
                try {
                    DBHelper.write(pageEntity);
                } catch (Exception e) {
                    System.err.println("！！！写入失败！！！ > " + pageEntity);
                    e.printStackTrace();
                }
            }
            System.out.println("-- 写入完成！");
            List<String> nextGenUrlList = PlatformFactory.get().nextGenUrl(url, content, entityList, depth);
            System.out.println("-- 需要解析的下一级页面：" + nextGenUrlList.size() + "个");
            if (nextGenUrlList.isEmpty()) return;
            start(nextGenUrlList, depth + 1);
        });
    }


}




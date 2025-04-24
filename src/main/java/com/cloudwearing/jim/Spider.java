package com.cloudwearing.jim;


import com.cloudwearing.jim.entity.PageEntity;
import com.cloudwearing.jim.platform.PlatformFactory;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.net.URL;
import java.net.URLConnection;
import java.util.ArrayList;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class Spider {

    public static String fetchPageContent(String url) {
        StringBuffer result = new StringBuffer();//定义一个字符串用来存储网页内容
        BufferedReader in = null;
        try {
            URL resultUrl = new URL(url);
            URLConnection connection = resultUrl.openConnection();
            connection.connect();
            in = new BufferedReader(new InputStreamReader(connection.getInputStream(), "UTF-8"));
            String line;
            while ((line = in.readLine()) != null) {
                result.append(line);
            }
        } catch (Exception e) {
            System.out.println("发送GET请求出现异常！" + e);
            e.printStackTrace();
        } finally {
            try {
                if (in != null) in.close();
            } catch (Exception e2) {
                e2.printStackTrace();
            }
        }
        return result.toString();
    }


    static ArrayList<PageEntity> pageContent2Entity(String content) {
        ArrayList<PageEntity> results = new ArrayList<>();
        Pattern pattern = Pattern.compile(PlatformFactory.get().getRegex());
        Matcher matcher = pattern.matcher(content);
        Boolean isFind = matcher.find();
        while (isFind) {
            PageEntity pageEntity = (PageEntity) PlatformFactory.get().resolve(matcher.group(1));
            results.add(pageEntity);
            isFind = matcher.find();
        }
        return results;
    }


}

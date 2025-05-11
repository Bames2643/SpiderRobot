package com.cloudwearing.jim;


import com.cloudwearing.jim.platform.Platform;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import sun.net.util.URLUtil;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.net.MalformedURLException;
import java.net.URL;
import java.net.URLConnection;
import java.util.Objects;
import java.util.Scanner;
import java.util.concurrent.TimeUnit;

public class Spider {

    public static String fetchPageContent(Platform platform) {
        return platform.isNeedLogin() ? fetchPageContentByDrive(platform) : fetchPageContentByConnect(platform);
    }

    private static String fetchPageContentByDrive(Platform platform) {
        String chromedriver = Objects.requireNonNull(Object.class.getResource("/chromedriver/chromedriver.exe")).getPath();
        System.setProperty("webdriver.chrome.driver", chromedriver);
        WebDriver driver = new ChromeDriver();
        driver.get(platform.getUrl());
        driver.manage().timeouts().implicitlyWait(10 * 60, TimeUnit.SECONDS); // 最大10分钟登录时间
        if (!driver.getCurrentUrl().equalsIgnoreCase(platform.getUrl())) { // 有跳转页面
            System.out.println("======================================================================================================");
            System.out.print("正在登录，登录完成后，请按 回车键(ENTER) 继续...");
            new Scanner(System.in).nextLine();
            System.out.println("登录结束！");
            System.out.println("======================================================================================================");
        }

        try {
            URLUtil.urlNoFragString(new URL(""));
        } catch (MalformedURLException e) {
            throw new RuntimeException(e);
        }


        /// /////////// TEST
        System.out.println("当前URL：" + driver.getCurrentUrl());
        System.out.println("预设URL：" + platform.getUrl());

        return driver.getPageSource();
    }


    private static String fetchPageContentByConnect(Platform platform) {
        String url = platform.getUrl();
        StringBuffer result = new StringBuffer();//定义一个字符串用来存储网页内容
        BufferedReader in = null;
        try {
            URL resultUrl = new URL(url);
            URLConnection connection = resultUrl.openConnection();
            connection.connect();
            in = new BufferedReader(new InputStreamReader(connection.getInputStream(), "UTF-8"));
            String line;
            while ((line = in.readLine()) != null) result.append(line);
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


//    static ArrayList<PageEntity> pageContent2Entity(String content) {
//
//
//        ArrayList<PageEntity> results = new ArrayList<>();
//        Pattern pattern = Pattern.compile(PlatformFactory.get().getRegex());
//        Matcher matcher = pattern.matcher(content);
//        Boolean isFind = matcher.find();
//        while (isFind) {
//            PageEntity pageEntity = (PageEntity) PlatformFactory.get().resolve(matcher.group(1));
//            results.add(pageEntity);
//            isFind = matcher.find();
//        }
//        return results;
//    }


}

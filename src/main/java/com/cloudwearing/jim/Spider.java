package com.cloudwearing.jim;


import com.cloudwearing.jim.entity.PageContent;
import com.cloudwearing.jim.platform.PlatformTask;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.net.URL;
import java.net.URLConnection;
import java.nio.charset.StandardCharsets;
import java.util.Objects;
import java.util.Scanner;
import java.util.concurrent.TimeUnit;

public class Spider {

    public static PageContent fetchPageContent(PlatformTask platformTask) {
        return platformTask.isNeedLogin() ? fetchPageContentByDrive(platformTask) : fetchPageContentByConnect(platformTask);
    }

    private static PageContent fetchPageContentByDrive(PlatformTask platformTask) {
        String chromedriver = Objects.requireNonNull(Object.class.getResource("/chromedriver/chromedriver.exe")).getPath();
        System.setProperty("webdriver.chrome.driver", chromedriver);
        WebDriver driver = new ChromeDriver();
        driver.get(platformTask.getUrl());
        driver.manage().timeouts().implicitlyWait(10 * 60, TimeUnit.SECONDS); // 最大10分钟登录时间
//        if (!driver.getCurrentUrl().equalsIgnoreCase(platformTask.getUrl())) { // 有跳转页面
        if (platformTask.isNeedLogin()) { // 有跳转页面
            System.out.println("======================================================================================================");
            System.out.print("正在登录，登录完成后，请按 回车键(ENTER) 继续...");
            new Scanner(System.in).nextLine();
            System.out.println("登录结束！");
            System.out.println("======================================================================================================");
        }

//        try {
//            URLUtil.urlNoFragString(new URL(""));
//        } catch (MalformedURLException e) {
//            throw new RuntimeException(e);
//        }
        /// /////////// TEST
        System.out.println("当前URL：" + driver.getCurrentUrl());
        System.out.println("预设URL：" + platformTask.getUrl());

        PageContent pageContent = new PageContent();
        pageContent.setPageUrl(driver.getCurrentUrl());
        pageContent.setContent(driver.getPageSource());

        return pageContent;
    }


    private static PageContent fetchPageContentByConnect(PlatformTask platformTask) {
        String url = platformTask.getUrl();
        StringBuilder result = new StringBuilder();//定义一个字符串用来存储网页内容
        BufferedReader in = null;
        try {
            URL resultUrl = new URL(url);
            URLConnection connection = resultUrl.openConnection();
            connection.connect();
            in = new BufferedReader(new InputStreamReader(connection.getInputStream(), StandardCharsets.UTF_8));
            String line;
            while ((line = in.readLine()) != null) result.append(line);
        } catch (Exception e) {
            System.out.println("发送GET请求出现异常！" + e.getMessage());
            e.printStackTrace();
        } finally {
            try {
                if (in != null) in.close();
            } catch (Exception e2) {
                e2.printStackTrace();
            }
        }
        return new PageContent(url, result.toString());
    }


//    static ArrayList<PageEntity> pageContent2Entity(String content) {
//
//
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
//    }


}

package com.cloudwearing.test;

import org.apache.commons.io.FileUtils;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import sun.net.util.URLUtil;

import java.io.File;
import java.io.IOException;
import java.net.MalformedURLException;
import java.net.URL;
import java.nio.charset.Charset;
import java.util.Date;
import java.util.List;
import java.util.Objects;
import java.util.Scanner;
import java.util.concurrent.TimeUnit;

public class Test {


//    public static void main(String[] args) throws IOException {
//        URL ori = new URL("https://www.baidu.com/test/in?o=3&t=44#mark9");
//        String res = URLUtil.urlNoFragString(ori);
//        System.out.println(" ==> " + ori);
//        System.out.println(res);
//        System.out.println(" ==> " + ori.getPath());
//        System.out.println(" ==> " + ori.getContent());
//        System.out.println(" ==> " + ori.getUserInfo());
//
//    }


    public static void main(String[] args) throws IOException {
        String chromedriver = Objects.requireNonNull(Object.class.getResource("/chromedriver/chromedriver.exe")).getPath();
        System.out.println("找到驱动：" + chromedriver);
        System.setProperty("webdriver.chrome.driver", chromedriver);
        WebDriver driver = new ChromeDriver();
//        driver.get("https://user.qzone.qq.com/1337729961");
        driver.get("http://user.qzone.qq.com/1337729961");

        System.out.println("start  " + new Date());
        driver.manage().timeouts().implicitlyWait(10 * 60, TimeUnit.SECONDS); // 最大10分钟加载页面时间
        System.out.println("end  " + new Date());
        System.out.print("正在登录，登录完成后，请按 回车键(ENTER) 继续...");
        new Scanner(System.in).nextLine();
        System.out.println("=====> 当前页面：" + driver.getCurrentUrl());
        FileUtils.writeStringToFile(new File("E:\\grab\\test"), driver.getPageSource(), Charset.defaultCharset());
        List<WebElement> elements = driver.findElements(By.tagName("a"));
        for (WebElement element : elements) {
            element.getAttribute("href");
        }
        System.out.println("===================================");
        driver.manage().getCookies().forEach(System.out::println);


//        System.out.println(new Date());
//        driver.manage().timeouts().implicitlyWait(60, TimeUnit.SECONDS);
//        System.out.println(new Date());
//        System.out.println("driver.getTitle() : " + driver.getTitle());
//        System.out.println("driver.getCurrentUrl() : " + driver.getCurrentUrl());
//        System.out.println("driver.getPageSource() : " + driver.getPageSource());
//        System.out.println("driver.getWindowHandles(): " + (driver.getWindowHandles()));
////        System.out.println("driver.manage().ime().getAvailableEngines(): " + driver.manage().ime().getAvailableEngines());
//
//// WebDriverWait wait = new WebDriverWait(driver, 40);
//        System.out.println(new Date());
////        wait.until(ExpectedConditions.visibilityOfElementLocated(By.className("head-nav")));
//        System.out.print("Press ENTER to continue...");
//        new Scanner(System.in).nextLine();
//        System.out.println("======================================================================================================");
//        System.out.println("driver.getTitle() : " + driver.getTitle());
//        System.out.println("driver.getCurrentUrl() : " + driver.getCurrentUrl());
//        System.out.println("driver.getPageSource() : " + driver.getPageSource());
//        System.out.println(new Date());
//
    }


}

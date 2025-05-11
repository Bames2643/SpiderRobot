package com.cloudwearing.jim;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.cloudwearing.jim.platform.Platform;
import com.cloudwearing.jim.platform.PlatformFactory;
import org.apache.commons.io.FileUtils;
import org.apache.commons.lang3.StringUtils;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;

import java.io.File;
import java.io.IOException;
import java.nio.charset.Charset;
import java.util.ArrayList;
import java.util.List;

public class BootStarter {

    private String urlsTxt = "E:\\grab\\src_urls.json";
    private List<Platform> platforms = new ArrayList<>();

    private void load() {
        File urlsF = new File(urlsTxt);
        if (!urlsF.exists() || !urlsF.isFile()) {
            throw new NullPointerException("请先设置url文件：" + urlsTxt);
        }
        try {
            String urlsContent = FileUtils.readFileToString(urlsF, Charset.defaultCharset());
            Object urlsAll = JSON.parse(urlsContent);
            if (urlsAll instanceof JSONObject) {
                json2Obj((JSONObject) urlsAll);
            } else if (urlsAll instanceof JSONArray) {
                JSONArray array = (JSONArray) urlsAll;
                array.stream().iterator().forEachRemaining(obj -> json2Obj((JSONObject) obj));
            } else {
                throw new IllegalArgumentException();
            }
        } catch (IOException e) {
            throw new IllegalArgumentException("!! 解析URL文件失败！", e);
        }
    }

    private void json2Obj(JSONObject jsonObject) {
        Platform platform = PlatformFactory.buildPlatform(jsonObject.getString("platform"));
        platforms.add(platform);
        String url = jsonObject.getString("url");
        if (StringUtils.isNotBlank(url)) platform.setUrl(url);
        Integer depth = jsonObject.getInteger("grab_depth");//抓取深度
        if (null != depth) platform.setGrabDepth(depth);
        Integer needLogin = jsonObject.getInteger("need_login");// 是否需要额外登录
        platform.setNeedLogin(null != needLogin && needLogin == 0);
    }

    public void run() {
        platforms.forEach(platform -> grabOne(platform, 0));
    }

    private static void grabOne(Platform platform, int currDepth) {
        if (platform.getGrabDepth() <= currDepth) return;
        System.out.println("########### [平台:" + platform.getName() + "] ######### 开始解析页面：" + platform.getUrl());
        String content = Spider.fetchPageContent(platform);
        if (StringUtils.isBlank(content)) {
            System.out.println("!! 无法解析页面 ===> " + platform.getUrl());
            return;
        }
        List<? extends Platform> more = platform.resolve(content);
        if (more != null) more.forEach(pm -> grabOne(pm, currDepth + 1));
    }


    public static void main(String[] args) {


        WebDriver driver = new ChromeDriver();


    }

}

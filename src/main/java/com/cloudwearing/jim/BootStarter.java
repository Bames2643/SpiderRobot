package com.cloudwearing.jim;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.cloudwearing.jim.entity.PageContent;
import com.cloudwearing.jim.platform.PlatformTask;
import com.cloudwearing.jim.platform.TaskFactory;
import org.apache.commons.io.FileUtils;
import org.apache.commons.lang3.StringUtils;

import java.io.File;
import java.io.IOException;
import java.nio.charset.Charset;
import java.util.ArrayList;
import java.util.List;

public class BootStarter implements Runnable {

    private final List<PlatformTask> platformTasks = new ArrayList<>();

    private void load(String urlsJsonFile) {
        File urlsFile = new File(urlsJsonFile);
        if (!urlsFile.exists() || !urlsFile.isFile()) throw new NullPointerException("请先设置url文件：" + urlsJsonFile);
        try {
            String urlsContent = FileUtils.readFileToString(urlsFile, Charset.defaultCharset());
            Object urlsAll = JSON.parse(urlsContent);
            if (urlsAll instanceof JSONObject) {
                json2Obj((JSONObject) urlsAll);
            } else if (urlsAll instanceof JSONArray) {
                JSONArray array = (JSONArray) urlsAll;
                array.stream().iterator().forEachRemaining(obj -> json2Obj((JSONObject) obj));
            } else {
                throw new IllegalArgumentException("未实现的模式！");
            }
        } catch (IOException e) {
            throw new IllegalArgumentException("!! 解析URL文件失败！", e);
        }
    }

    private void json2Obj(JSONObject jsonObject) {
        PlatformTask platformTask = TaskFactory.buildPlatform(jsonObject.getString("platform"));
        platformTasks.add(platformTask);
        String url = jsonObject.getString("url");
        if (StringUtils.isNotBlank(url)) platformTask.setUrl(url);
        Integer depth = jsonObject.getInteger("grab_depth");//抓取深度，默认3
        platformTask.setGrabDepth(depth == null ? 3 : depth);
        Integer needLogin = jsonObject.getInteger("need_login");// 是否需要额外登录,默认不需要
        platformTask.setNeedLogin(null != needLogin && needLogin == 1);
    }

    public void run() {
        platformTasks.forEach(platform -> grabOne(platform, 0));
    }

    private static void grabOne(PlatformTask platformTask, int currDepth) {
        if (platformTask.getGrabDepth() <= currDepth) return;
        System.out.println("########### [平台:" + platformTask.getName() + "] ######### 开始解析页面：" + platformTask.getUrl());
        PageContent content = Spider.fetchPageContent(platformTask);
        if (null == content) {
            System.out.println("!! 无法解析页面 ===> " + platformTask.getUrl());
            return;
        }
        List<? extends PlatformTask> more = platformTask.resolve(content);
        if (more != null) more.forEach(pm -> grabOne(pm, currDepth + 1));
    }


}

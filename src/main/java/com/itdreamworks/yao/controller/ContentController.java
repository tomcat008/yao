package com.itdreamworks.yao.controller;

import com.itdreamworks.yao.config.ArticleDirectoryConfig;
import com.itdreamworks.yao.entity.Article;
import com.itdreamworks.yao.model.ContentArticleForManage;
import com.itdreamworks.yao.utils.FileUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.util.ResourceUtils;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.*;
import java.util.Map;

@Controller
@RequestMapping(value = "/manage/content")
public class ContentController extends BaseArticleController {
    private static final String BASE_PATH = "/manage/content";

    @Autowired
    private ArticleDirectoryConfig articleDirectoryConfig;

    @RequestMapping(value = "/{id}")
    public String get(@PathVariable("id") int id, Map<String, Object> map) throws Exception {
        Article article = articleService.find(id);
        if (null == article.getContent()) {
            File tmpFile = ResourceUtils.getFile("classpath:tmp/art.html");
            BufferedReader br = new BufferedReader(new InputStreamReader(new FileInputStream(tmpFile)));
            StringBuilder sb = new StringBuilder();//定义一个字符串缓存，将字符串存放缓存中
            String s = "";
            while ((s = br.readLine()) != null) {
                sb.append(s);//将读取的字符串添加换行符后累加存放在缓存中
            }
            br.close();
            article.setContent(sb.toString());
        }
        map.put("article", article);
        map.put("r", Math.random());
        return "/content/edit";
    }

    @PostMapping(value = "/save")
    public String save(@RequestParam("pic") MultipartFile pic, ContentArticleForManage article, BindingResult bindingResult, Map<String, Object> map) throws Exception {
        if (articleService.modifyContent(article)) {
            if (!pic.isEmpty()) {
                String path = articleDirectoryConfig.getDirectoryOrFilePath(String.format("%d/pic.jpg", article.getId()));
                FileUtil.uploadFile(pic.getBytes(), path);
            }
            map.put("msg", "修改内容成功！");
            return "success";
        } else {
            map.put("msg", "修改内容失败！");
            return "failed";
        }
    }

}

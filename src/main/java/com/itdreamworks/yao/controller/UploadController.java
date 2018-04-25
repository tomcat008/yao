package com.itdreamworks.yao.controller;

import com.itdreamworks.yao.config.ArticleDirectoryConfig;
import com.itdreamworks.yao.config.WebMvcConfig;
import com.itdreamworks.yao.utils.FileUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.util.ResourceUtils;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.text.SimpleDateFormat;
import java.util.Date;

@RestController
@RequestMapping(value = "/upload")
public class UploadController {
    private SimpleDateFormat format = new SimpleDateFormat("yyMMddHHmmss");

    @Autowired
    private ArticleDirectoryConfig articleDirectoryConfig;

    @PostMapping(value = "/pic/{id}")
    public String uploadFile(@RequestParam("pic") MultipartFile pic, @PathVariable("id") String id) throws Exception {
        try {
            if (!pic.isEmpty()) {
                String filePath = String.format("%s/%s.jpg", id, format.format(new Date()));
                String path = articleDirectoryConfig.getDirectoryOrFilePath(filePath);
//                File path = new File(ResourceUtils.getURL("classpath:").getPath());
//                String url = String.format("/articles/%s/%s.jpg", id, format.format(new Date()));
//                File filePath = new File(path.getAbsolutePath(), String.format("static/%s", url));
//                FileUtil.uploadFile(pic.getBytes(), filePath.getAbsolutePath());
                FileUtil.uploadFile(pic.getBytes(), path);
                String url = WebMvcConfig.URL_ARTICLES_DIRECTORY+filePath;
                return String.format("{\"errno\": 0,\"data\": [\"%s\"]}", url);
            }
            return "";
        } catch (Exception ex) {
            ex.printStackTrace();
            return String.format("{\"errno\": 1,\"msg\": [\"%s\"]}", ex.getMessage());
        }
    }
}

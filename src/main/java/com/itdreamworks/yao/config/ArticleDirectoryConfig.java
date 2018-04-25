package com.itdreamworks.yao.config;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

@Component
public class ArticleDirectoryConfig {
    public String getDirectoryOrFilePath(String filePath) {
        filePath = filePath.replace('\\','/');
        return String.format("%s%s",getArticleDirectoryPath(),filePath);
    }

    public String getArticleDirectoryPath() {
        return articleDirectoryPath.endsWith("/") ? articleDirectoryPath : articleDirectoryPath + "/";
    }

    @Value("${web.articles.directory}")
    private String articleDirectoryPath;


}

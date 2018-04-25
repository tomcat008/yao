package com.itdreamworks.yao.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.ResourceHandlerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurationSupport;

@Configuration
public class WebMvcConfig extends WebMvcConfigurationSupport {
    public static final String URL_ARTICLES_DIRECTORY = "/articles/";

    @Autowired
    private ArticleDirectoryConfig articleDirectoryConfig;

    @Override
    public void addResourceHandlers(ResourceHandlerRegistry registry) {
        super.addResourceHandlers(registry);
        registry.addResourceHandler(URL_ARTICLES_DIRECTORY + "**").addResourceLocations(
                "file:" + articleDirectoryConfig.getArticleDirectoryPath());
    }
}

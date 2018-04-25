package com.itdreamworks.yao.config;

import com.itdreamworks.yao.interceptor.ManageInterceptor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.ResourceHandlerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurationSupport;

@Configuration
public class WebMvcConfig extends WebMvcConfigurationSupport {
    public static final String URL_ARTICLES_DIRECTORY = "/articles/";

    @Autowired
    private ArticleDirectoryConfig articleDirectoryConfig;
    @Autowired
    private ManageInterceptor manageInterceptor;

    @Override
    public void addResourceHandlers(ResourceHandlerRegistry registry) {
        registry.addResourceHandler(URL_ARTICLES_DIRECTORY + "**").addResourceLocations(
                "file:" + articleDirectoryConfig.getArticleDirectoryPath());
        registry.addResourceHandler("/**").addResourceLocations("/META-INF/resources/", "classpath:/resources/", "classpath:/static/", "classpath:/public/");
        super.addResourceHandlers(registry);
    }

    @Override
    protected void addInterceptors(InterceptorRegistry registry) {
        registry.addInterceptor(manageInterceptor).addPathPatterns("/manage/**");
        super.addInterceptors(registry);
    }
}

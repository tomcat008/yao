package com.itdreamworks.yao.config;

import com.google.code.kaptcha.impl.DefaultKaptcha;
import com.google.code.kaptcha.util.Config;
import com.itdreamworks.yao.interceptor.ManageInterceptor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.ResourceHandlerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurationSupport;

import java.util.Properties;

@Configuration
public class WebMvcConfig extends WebMvcConfigurationSupport {
    public static final String URL_ARTICLES_DIRECTORY = "/articles/";
    public static final String URL_CATEGORIES_DIRECTORY = "/categories/";

    @Autowired
    private ArticleDirectoryConfig articleDirectoryConfig;
    @Autowired
    private CategoryDirectoryConfig categoryDirectoryConfig;
    @Autowired
    private ManageInterceptor manageInterceptor;

    @Override
    public void addResourceHandlers(ResourceHandlerRegistry registry) {
        registry.addResourceHandler(URL_ARTICLES_DIRECTORY + "**").addResourceLocations(
                "file:" + articleDirectoryConfig.getArticleDirectoryPath());
        registry.addResourceHandler(URL_CATEGORIES_DIRECTORY + "**").addResourceLocations(
                "file:" + categoryDirectoryConfig.getCategoryDirectoryPath());
        registry.addResourceHandler("/**").addResourceLocations("/META-INF/resources/", "classpath:/resources/", "classpath:/static/", "classpath:/public/");
        super.addResourceHandlers(registry);
    }

    @Override
    protected void addInterceptors(InterceptorRegistry registry) {
        registry.addInterceptor(manageInterceptor).addPathPatterns("/manage/**");
        super.addInterceptors(registry);
    }

    @Bean
    public DefaultKaptcha getDefaultKaptcha(){
        com.google.code.kaptcha.impl.DefaultKaptcha defaultKaptcha = new com.google.code.kaptcha.impl.DefaultKaptcha();
        Properties properties = new Properties();
        properties.setProperty("kaptcha.border", "yes");
        properties.setProperty("kaptcha.border.color", "105,179,90");
        properties.setProperty("kaptcha.textproducer.font.color", "blue");
        properties.setProperty("kaptcha.image.width", "110");
        properties.setProperty("kaptcha.image.height", "40");
        properties.setProperty("kaptcha.textproducer.font.size", "30");
        properties.setProperty("kaptcha.session.key", "code");
        properties.setProperty("kaptcha.textproducer.char.length", "4");
        properties.setProperty("kaptcha.textproducer.font.names", "宋体,楷体,微软雅黑");
        Config config = new Config(properties);
        defaultKaptcha.setConfig(config);
        return defaultKaptcha;
    }
}

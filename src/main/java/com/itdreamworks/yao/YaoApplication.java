package com.itdreamworks.yao;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cache.annotation.EnableCaching;
import org.springframework.context.annotation.Bean;
import org.springframework.transaction.annotation.EnableTransactionManagement;
import org.springframework.web.servlet.LocaleResolver;
import org.springframework.web.servlet.i18n.SessionLocaleResolver;

//import java.util.Locale;

@EnableCaching
@SpringBootApplication
@EnableTransactionManagement
@MapperScan("com.itdreamworks.yao.mapper")
public class YaoApplication {


    @Bean
    public LocaleResolver localeResolver() {

        SessionLocaleResolver slr = new SessionLocaleResolver();
        //设置默认区域,
        //slr.setDefaultLocale(Locale.US);
        return slr;
    }

    public static void main(String[] args) {
        SpringApplication.run(YaoApplication.class, args);
    }
}

package com.itdreamworks.yao;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.jdbc.DataSourceAutoConfiguration;
import org.springframework.boot.web.servlet.support.SpringBootServletInitializer;
import org.springframework.context.annotation.Bean;
import org.springframework.web.servlet.LocaleResolver;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurerAdapter;
import org.springframework.web.servlet.i18n.LocaleChangeInterceptor;
import org.springframework.web.servlet.i18n.SessionLocaleResolver;

import java.util.Locale;

//@SpringBootApplication(exclude = {DataSourceAutoConfiguration.class})
@SpringBootApplication
//@EnableTransactionManagement
@MapperScan("com.itdreamworks.yao.mapper")
public class YaoApplication{

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

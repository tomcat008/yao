package com.itdreamworks.yao;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.jdbc.DataSourceAutoConfiguration;

@SpringBootApplication(exclude = {DataSourceAutoConfiguration.class})
public class YaoApplication {

	public static void main(String[] args) {
		SpringApplication.run(YaoApplication.class, args);
	}
}

package com.xyibq.lanxj.m.forum.web;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.builder.SpringApplicationBuilder;
import org.springframework.boot.web.support.SpringBootServletInitializer;
import org.springframework.transaction.annotation.EnableTransactionManagement;

@MapperScan("com.xyibq.lanxj.m.forum.mapper")
@SpringBootApplication(scanBasePackages="com.xyibq.lanxj.m.forum")
@EnableTransactionManagement//开启事务管理
public class LanxjMForumApplication extends SpringBootServletInitializer{

	/**
	 * 项目更改为tomcat启动时，需重写configure方法
	 */
	@Override
	protected SpringApplicationBuilder configure(SpringApplicationBuilder application) {
		return application.sources(LanxjMForumApplication.class);
	}

	public static void main(String[] args) {
			SpringApplication.run(LanxjMForumApplication.class, args);
	}
}

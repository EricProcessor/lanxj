package com.xyibq.lanxj.admin.forum.web;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.builder.SpringApplicationBuilder;
import org.springframework.boot.web.support.SpringBootServletInitializer;
import org.springframework.scheduling.annotation.EnableScheduling;
import org.springframework.transaction.annotation.EnableTransactionManagement;

@MapperScan("com.xyibq.lanxj.admin.forum.mapper")
@SpringBootApplication(scanBasePackages="com.xyibq.lanxj.admin.forum")
@EnableTransactionManagement//开启事务管理
@EnableScheduling	//定时任务
public class AdminForumWebApplication extends SpringBootServletInitializer {

	/**
	 * 项目更改为第三方tomcat启动时，需重写configure方法
	 */
	@Override
	protected SpringApplicationBuilder configure(SpringApplicationBuilder application) {
		return application.sources(AdminForumWebApplication.class);
	}

	public static void main(String[] args) {
		SpringApplication.run(AdminForumWebApplication.class, args);
	}
}

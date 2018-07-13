package com.xyibq.lanxj.admin.forum.web.filter;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurerAdapter;

@Configuration
public class InterceptorConfig extends WebMvcConfigurerAdapter {

    /**
     * 注册 拦截器
     */
    @Override
    public void addInterceptors(InterceptorRegistry registry) {
        registry.addInterceptor(loginInterceptorAdapter());
    }

    /**
     * 拦截器 注入服务 必须有此方法
     */
    @Bean
    public LoginInterceptorAdapter loginInterceptorAdapter(){
        return new LoginInterceptorAdapter();
    }
}

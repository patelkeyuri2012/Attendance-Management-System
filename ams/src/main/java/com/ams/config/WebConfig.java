package com.ams.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.ResourceHandlerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

import com.ams.interceptor.LoginInterceptor;

@Configuration
@EnableWebMvc
public class WebConfig implements WebMvcConfigurer {

    @Autowired
    private LoginInterceptor loginInterceptor;

    @Override
    public void addInterceptors(InterceptorRegistry registry) {
        registry.addInterceptor(loginInterceptor)
                .addPathPatterns("/admin/**", "/HOD/**", "/teacher/**", "/student/**", "/assets/**");
    }
    
    @Override
    public void addResourceHandlers(ResourceHandlerRegistry registry) {
    	
    	  registry.addResourceHandler("/front/**")
          .addResourceLocations("classpath:/static/front/");
        registry.addResourceHandler("/admin/**")
        .addResourceLocations("classpath:/static/admin/");
        registry.addResourceHandler("/HOD/**")
        .addResourceLocations("classpath:/static/HOD/");
        registry.addResourceHandler("/teacher/**")
        .addResourceLocations("classpath:/static/teacher/");
        registry.addResourceHandler("/student/**")
        .addResourceLocations("classpath:/static/student/");
        registry.addResourceHandler("/assets/**")
        .addResourceLocations("classpath:/static/assets/");
    }
}


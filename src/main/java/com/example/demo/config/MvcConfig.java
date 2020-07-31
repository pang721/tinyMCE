package com.example.demo.config;

import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.ResourceHandlerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

@Configuration
public class MvcConfig implements WebMvcConfigurer {

    @Override
    public void addResourceHandlers(ResourceHandlerRegistry registry) {
        //意思是：url中读取到/upload时，就会自动将/upload解析成D:/idea/java_workspace/image/upload
        registry.addResourceHandler("/files/**").addResourceLocations("file:C:/Users/pangx/Desktop/files/");
        /**
         * Linux系统
         * registry.addResourceHandler("/upload/**").addResourceLocations("file:/home/image/upload/");
         */
    }


}

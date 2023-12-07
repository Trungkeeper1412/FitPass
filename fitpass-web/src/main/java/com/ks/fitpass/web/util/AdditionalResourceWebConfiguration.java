package com.ks.fitpass.web.util;

import org.springframework.context.annotation.Configuration;
import org.springframework.http.CacheControl;
import org.springframework.web.servlet.config.annotation.ResourceHandlerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;


@Configuration
public class AdditionalResourceWebConfiguration implements WebMvcConfigurer {
    @Override
    public void addResourceHandlers(ResourceHandlerRegistry registry) {
        // Specify full path to images on Ubuntu file system
        String imagesPath = "/home/upload/img/";
        registry.addResourceHandler("/img/**")
                .addResourceLocations("file:" + imagesPath)
                .setCacheControl(CacheControl.noCache());
    }
}

package com.ks.fitpass.core.config;

import org.springframework.context.annotation.Configuration;
import org.springframework.http.CacheControl;
import org.springframework.web.servlet.config.annotation.ResourceHandlerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;
import java.util.concurrent.TimeUnit;

@Configuration
public class AdditionalResourceWebConfiguration implements WebMvcConfigurer {

//    @Override
//    public void addResourceHandlers(final ResourceHandlerRegistry registry) {
//        Path path = Paths.get("./upload/img");
//        String upload = path.toFile().getAbsolutePath();
//        registry.addResourceHandler("/img/**").
//                addResourceLocations("file:/" + upload + "/").
//                setCacheControl(CacheControl.noCache());
//    }

    @Override
    public void addResourceHandlers(ResourceHandlerRegistry registry) {
        // Specify full path to images on Ubuntu file system
        String imagesPath = "/home/upload/img/";
        registry.addResourceHandler("/img/**")
                .addResourceLocations("file:" + imagesPath)
                .setCacheControl(CacheControl.maxAge(2, TimeUnit.HOURS));
    }
}

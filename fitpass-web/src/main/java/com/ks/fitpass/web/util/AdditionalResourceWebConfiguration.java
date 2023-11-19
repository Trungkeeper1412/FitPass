package com.ks.fitpass.web.util;

import org.springframework.context.annotation.Configuration;
import org.springframework.http.CacheControl;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;
import org.springframework.web.servlet.config.annotation.ResourceHandlerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

import java.nio.file.Path;
import java.nio.file.Paths;

@Configuration
public class AdditionalResourceWebConfiguration implements WebMvcConfigurer {

    @Override
    public void addResourceHandlers(final ResourceHandlerRegistry registry) {
        Path path = Paths.get("./upload/img");
        String upload = path.toFile().getAbsolutePath();
        registry.addResourceHandler("/img/**").
                addResourceLocations("file:/" + upload + "/").
                setCacheControl(CacheControl.noCache());
    }
}

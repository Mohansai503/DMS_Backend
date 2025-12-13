package com.dms.dmsproject.config;

import java.nio.file.Paths;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.ResourceHandlerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

@Configuration
public class StaticResourceConfig implements WebMvcConfigurer {

    @Value("${file.upload-dir:}")
    private String uploadDir;

    @Override
    public void addResourceHandlers(ResourceHandlerRegistry registry) {

        // If path not provided → use project folder/dms-uploads (same as DocumentServiceImpl)
        if (uploadDir == null || uploadDir.isBlank()) {
            String userDir = System.getProperty("user.dir");
            uploadDir = Paths.get(userDir, "dms-uploads")
                             .toAbsolutePath()
                             .normalize()
                             .toString();
        }

        // Ensure trailing slash
        String location = "file:" + uploadDir + "/";

        registry.addResourceHandler("/uploads/**")
                .addResourceLocations(location);

        System.out.println("Static resources serving from: " + location);
    }
}

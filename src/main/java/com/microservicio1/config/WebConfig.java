package com.microservicio1.config;

import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.ResourceHandlerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

@Configuration
public class WebConfig implements WebMvcConfigurer {

    @Override
    public void addResourceHandlers(ResourceHandlerRegistry manejadorRecursos) {
        manejadorRecursos.addResourceHandler("/archivos-generados/**")
                .addResourceLocations("file:archivos-generados/");
    }
}
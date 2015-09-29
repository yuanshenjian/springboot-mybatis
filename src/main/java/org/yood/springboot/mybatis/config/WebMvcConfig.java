package org.yood.springboot.mybatis.config;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.web.servlet.config.annotation.CorsRegistry;
import org.springframework.web.servlet.config.annotation.ResourceHandlerRegistry;
import org.springframework.web.servlet.config.annotation.ViewControllerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurerAdapter;

@Configuration
public class WebMvcConfig extends WebMvcConfigurerAdapter {

    @Value("${cors.origin}")
    private String corsOrigin;

    @Override
    public void addViewControllers(ViewControllerRegistry registry) {
        registry.addViewController("/login").setViewName("login");
    }

    @Override
    public void addResourceHandlers(ResourceHandlerRegistry registry) {
        registry.addResourceHandler("/resources/**").addResourceLocations("/resources/").setCachePeriod(36000000);
        registry.addResourceHandler("/static/**").addResourceLocations("/static/").setCachePeriod(36000000);
    }

    @Override
    public void addCorsMappings(CorsRegistry registry) {
        registry.addMapping("/**")
                .allowedOrigins(corsOrigin)
                .allowCredentials(true)
                .allowedHeaders(HttpHeaders.CACHE_CONTROL)
                .allowedHeaders(HttpHeaders.PRAGMA)
                .allowedMethods(HttpMethod.GET.name(), HttpMethod.POST.name(), HttpMethod.PUT.name(),
                                HttpMethod.DELETE.name())
                .allowedHeaders(HttpHeaders.ACCESS_CONTROL_ALLOW_METHODS)
                .allowedHeaders(HttpHeaders.ACCESS_CONTROL_ALLOW_HEADERS)
                .maxAge(3600);
    }
}

package org.yood.springboot.mybatis.config;

import org.springframework.boot.context.embedded.FilterRegistrationBean;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.yood.springboot.mybatis.web.filter.CharacterEncodingFilter;

@Configuration
public class FiltersConfig {

    @Bean
    public FilterRegistrationBean registerCharacterEncodingFilter() {
        FilterRegistrationBean registration = new FilterRegistrationBean();
        registration.setFilter(new CharacterEncodingFilter());
        registration.setOrder(1);
        return registration;
    }
}

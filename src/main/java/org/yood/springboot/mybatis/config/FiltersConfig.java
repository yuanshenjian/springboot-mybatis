package org.yood.springboot.mybatis.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.context.embedded.FilterRegistrationBean;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.yood.springboot.mybatis.web.filter.CharacterEncodingFilter;

import javax.servlet.Filter;

@Configuration
public class FiltersConfig {

    @Bean
    @Autowired
    public FilterRegistrationBean registerCharacterEncodingFilter(CharacterEncodingFilter filter) {
        return createRegistrationBean(filter, 1);
    }

    private FilterRegistrationBean createRegistrationBean(Filter filter, int order) {
        FilterRegistrationBean registration = new FilterRegistrationBean();
        registration.setFilter(filter);
        registration.setOrder(order);
        return registration;
    }
}

package org.yood.springboot.mybatis;

import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.boot.context.embedded.FilterRegistrationBean;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import javax.servlet.Filter;

@Configuration
public class FiltersConfig {


    @Bean
    public FilterRegistrationBean characterFilterRegistration(@Qualifier("characterEncodingFilter") Filter filter) {
        FilterRegistrationBean registration = new FilterRegistrationBean();
        registration.setFilter(filter);
        registration.setOrder(3);
        return registration;
    }
}

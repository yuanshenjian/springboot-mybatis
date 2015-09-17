package org.yood.springboot.mybatis;

import org.yood.springboot.mybatis.web.filter.CharacterEncodingFilter;
import org.springframework.boot.context.embedded.FilterRegistrationBean;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class FiltersConfig {

	@Bean
	public FilterRegistrationBean characterFilterRegistration() {
		FilterRegistrationBean registration = new FilterRegistrationBean();
		registration.setFilter(new CharacterEncodingFilter());
		registration.setOrder(3);

		return registration;
	}
}

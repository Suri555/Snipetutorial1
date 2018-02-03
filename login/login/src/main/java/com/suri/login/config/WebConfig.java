package com.suri.login.config;

import org.springframework.boot.web.servlet.ServletRegistrationBean;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.h2.server.web.WebServlet;;

@Configuration
public class WebConfig {

	@Bean
		ServletRegistrationBean H2ServletRegistration(){
		ServletRegistrationBean registrationBean= new ServletRegistrationBean(new WebServlet());
		registrationBean.addUrlMappings("/console/*");
		return registrationBean;
		
		}
	
}

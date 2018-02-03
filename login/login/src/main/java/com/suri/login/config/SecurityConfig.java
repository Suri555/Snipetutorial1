package com.suri.login.config;

import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;

@Configuration
public class SecurityConfig extends WebSecurityConfigurerAdapter
{
	public void Configure (HttpSecurity httpSecurity) throws Exception{
	
		//formatter @off
	httpSecurity
	.authorizeRequests().antMatchers("*/*").permitAll().and()
	.authorizeRequests().antMatchers("*/console/*").permitAll();
  
	
	httpSecurity.csrf().disable();
	httpSecurity.headers().frameOptions().disable();
	//formatter @on
	}

}
